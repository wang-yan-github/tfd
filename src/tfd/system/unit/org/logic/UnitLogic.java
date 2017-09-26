package tfd.system.unit.org.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.org.data.Unit;

public class UnitLogic {
	public int saveUnitInfo(Connection conn, Unit unit) throws SQLException {
		int result=0;
		if (!getFlag(conn, unit.getOrgId())) {
			addUnitLogic(conn, unit);
		} else {
			PreparedStatement ps = null;
			String query = "UPDATE UNIT SET ORG_NAME=?,ORG_TEL=?,ORG_FAX=?,ORG_ADD=?,ORG_POST=?,ORG_EMAIL=? WHERE ORG_ID=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, unit.getOrgName());
			ps.setString(2, unit.getOrgTel());
			ps.setString(3, unit.getOrgFax());
			ps.setString(4, unit.getOrgAdd());
			ps.setString(5, unit.getOrgPost());
			ps.setString(6, unit.getOrgEmail());
			ps.setString(7, unit.getOrgId());
			result=ps.executeUpdate();
			
			query="UPDATE ORG_CONFIG SET ORG_NAME=? WHERE ORG_ID=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, unit.getOrgName());
			ps.setString(2, unit.getOrgId());
			result=ps.executeUpdate();
			ps.close();
		}
		return result;
	}

	public int addUnitLogic(Connection conn, Unit unit) throws SQLException {
		String queryStr = "INSERT INTO UNIT(ORG_NAME,ORG_TEL,ORG_FAX,ORG_ADD,ORG_POST,ORG_EMAIL,ORG_ID) VALUES(?,?,?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, unit.getOrgName());
		ps.setString(2, unit.getOrgTel());
		ps.setString(3, unit.getOrgFax());
		ps.setString(4, unit.getOrgAdd());
		ps.setString(5, unit.getOrgPost());
		ps.setString(6, unit.getOrgEmail());
		ps.setString(7, unit.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	public boolean getFlag(Connection conn, String orgId) throws SQLException {
		boolean flag = false;
		String queryStr = "SELECT COUNT(*) AS ZS FROM UNIT WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			if (rs.getInt("ZS") > 0) {
				flag = true;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}

	public String getUnitInfo(Connection conn, String orgId)
			throws SQLException {
		String query = "SELECT ORG_ID,ORG_NAME,ORG_TEL,ORG_FAX,ORG_ADD,ORG_POST,ORG_EMAIL FROM UNIT WHERE ORG_ID=?";
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, orgId);
		rs = ps.executeQuery();
		JSONObject jsonObj = new JSONObject();
		if (rs.next()) {
			jsonObj.accumulate("orgId", rs.getString("ORG_ID"));
			jsonObj.accumulate("orgName", rs.getString("ORG_NAME"));
			jsonObj.accumulate("orgTel", rs.getString("ORG_TEL"));
			jsonObj.accumulate("orgFax", rs.getString("ORG_FAX"));
			jsonObj.accumulate("orgAdd", rs.getString("ORG_ADD"));
			jsonObj.accumulate("orgPost", rs.getString("ORG_POST"));
			jsonObj.accumulate("orgEmail", rs.getString("ORG_EMAIL"));
		}
		rs.close();
		ps.close();
		return jsonObj.toString();
	}

	public int addOrgConfigLogic(Connection conn, String guId, String orgName,
		String orgAdmin, String userPriv) throws Exception {
		Account account = new Account();
		account.setAccountId(orgAdmin);
		account.setPassWord(MD5Util.getMD5("123456"));
		account.setPasswordType("1");
		account.setTheme("1");
		account.setUserPriv(userPriv);
		account.setNotLogin("0");
		account.setByName("");
		account.setLanguage("1");
		account.setOrgId(guId);
		AccountLogic accountLogic = new AccountLogic();
		accountLogic.addAccountLogic(conn, account);
		String queryStr = "INSERT INTO ORG_CONFIG (ORG_ID,ORG_NAME,ORG_ADMIN,CREATE_TIME,TYPE) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, guId);
		ps.setString(2, orgName);
		ps.setString(3, orgAdmin);
		ps.setString(4, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(5, "PC");
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	public String getOrgConfigJsonLogic(Connection conn) throws SQLException {
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT ID,ORG_NAME,ORG_ID,ORG_ADMIN FROM ORG_CONFIG";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("orgName", rs.getString("ORG_NAME"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			json.accumulate("orgAdmin", rs.getString("ORG_ADMIN"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	public int delOrgConfigLogic(Connection conn, String orgId)
			throws SQLException {
		String queryStr = "DELETE FROM ORG_CONFIG WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	public String getOrgConfigByIdLogic(Connection conn, String orgId)
			throws SQLException {
		String queryStr = "SELECT ID,ORG_ID,ORG_NAME,ORG_ADMIN FROM ORG_CONFIG WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		JSONObject json = new JSONObject();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			json.accumulate("orgName", rs.getString("ORG_NAME"));
			json.accumulate("orgAdmin", rs.getString("ORG_ADMIN"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	public int updateOrgConfigLogic(Connection conn, String orgId,
			String orgName, String orgAdmin) throws SQLException {
		String queryStr = "UPDATE ORG_CONFIG SET ORG_NAME=?, ORG_ADMIN=? WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgName);
		ps.setString(2, orgAdmin);
		ps.setString(3, orgId);
		int i = ps.executeUpdate();
		if(i>0){
			queryStr="UPDATE UNIT SET ORG_NAME =? WHERE ORG_ID=?";
			ps.setString(1, orgName);
			ps.setString(2, orgId);
			ps.executeUpdate();
		}
		ps.close();
		return i;
	}

	public Unit getUnitByOrgName(String orgName,Connection conn) throws Exception{
		BaseDao dao=new BaseDaoImpl();
		Unit unit=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from unit where org_name=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, orgName);
			rs=stmt.executeQuery();
			
			if (rs.next()) {
				unit=new Unit();
				unit.setId(rs.getInt("id"));
				unit.setOrgAdd(rs.getString("org_add"));
				unit.setOrgEmail(rs.getString("org_email"));
				unit.setOrgFax(rs.getString("org_fax"));
				unit.setOrgId(rs.getString("org_id"));
				unit.setOrgName(rs.getString("org_name"));
				unit.setOrgPost(rs.getString("org_post"));
				unit.setOrgTel(rs.getString("org_tel"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return unit;
	}
	
	
	public int getOrgAttachMaxLogic(Connection conn,Account account) throws SQLException
	{
		int returnData=0;
		String queryStr="SELECT ATTACH_MAX FROM ORG_CONFIG WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData =rs.getInt("ATTACH_MAX");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	/**
	 * 检查orgName是否已存在
	 * @param conn
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	public int checkorgLogic(Connection conn,String orgName,String orgId)throws Exception{
		String queryStr="SELECT COUNT(1) AS NUM FROM UNIT WHERE ORG_NAME =? AND ORG_ID !=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgName);
		ps.setString(2,orgId);
		ResultSet rs = ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 注册的情况下使用
	 * 检查orgName是否已存在
	 * @param conn
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	public int checkorgNameLogic(Connection conn,String orgName)throws Exception{
		String queryStr="SELECT COUNT(1) AS NUM FROM UNIT WHERE ORG_NAME =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgName);
		ResultSet rs = ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 检查 org_config 中的 orgName是否已存在
	 * @param conn
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	public int checkorgConfigLogic(Connection conn,String orgName,String orgId)throws Exception{
		String queryStr="SELECT COUNT(1) AS NUM FROM ORG_CONFIG WHERE ORG_NAME =? AND ORG_ID !=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgName);
		ps.setString(2,orgId);
		ResultSet rs = ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
}
