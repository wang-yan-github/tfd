package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.DbPoolConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

public class Offztreelogic {

	/**
	 * 获取办公用品管理的ztree树
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getztreeAct(Connection conn,Account account) throws SQLException{
		String queryStr="SELECT LIBRARY_ID ,LIBRARY_NAME ,TOP_ID FROM OFF_LIBRARY WHERE LIBRARY_STAFF =? AND DEL_STATUS =0 AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("LIBRARY_ID"));
			json.accumulate("pId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("LIBRARY_NAME"));
			json.accumulate("isParent", "true");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		ResultSet rs1 =null;
		String queryStr1="SELECT RES_ID ,CLASSIFY_ID,RES_NAME FROM OFF_RES WHERE APPROVE_STAFF=? AND ORG_ID=?";
		PreparedStatement ps1 = conn.prepareStatement(queryStr1);
		ps1.setString(1, account.getAccountId());
		ps1.setString(2, account.getOrgId());
		rs1=ps1.executeQuery();
		while(rs1.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs1.getString("RES_ID"));
			json.accumulate("pId", rs1.getString("CLASSIFY_ID"));
			json.accumulate("name", rs1.getString("RES_NAME"));
			json.accumulate("isParent", "false");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		
		return jsonArr.toString();
	}
	/**
	 * 获取办公用品申领的ztree树
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param deptId
	 * @param otherDept
	 * @return
	 * @throws SQLException
	 */
	public String getapplyztreeAct(Connection conn,Account account,String deptId,String otherDept) throws SQLException{
		String queryStr="SELECT LIBRARY_ID ,LIBRARY_NAME ,TOP_ID FROM OFF_LIBRARY WHERE ORG_ID=? AND DEL_STATUS=0";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("LIBRARY_ID"));
			json.accumulate("pId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("LIBRARY_NAME"));
			json.accumulate("isParent", "true");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		ResultSet rs1 =null;
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr1="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr1="SELECT RES_ID ,CLASSIFY_ID,RES_NAME FROM OFF_RES WHERE (LOCATE(',userAll,',CONCAT(',',USER_PRIV,','))>0 OR LOCATE(?,CONCAT(',',USER_PRIV,','))>0 OR LOCATE(',deptAll,',CONCAT(',',DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',DEPT_PRIV,','))>0) AND ORG_ID =?";
		}else if(dbType.equals("oracle")){
			queryStr1="SELECT RES_ID ,CLASSIFY_ID,RES_NAME FROM OFF_RES WHERE (INSTR(CONCAT(',',USER_PRIV)||',',',userAll,')>0 OR INSTR(CONCAT(',',USER_PRIV)||',',?)>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',',deptAll,')>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',?)>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',?)>0) AND ORG_ID =?";
		}
		PreparedStatement ps1 = conn.prepareStatement(queryStr1);
		ps1.setString(1, ","+account.getAccountId()+",");
		ps1.setString(2, ","+deptId+",");
		ps1.setString(3, ","+otherDept+",");
		ps1.setString(4, account.getOrgId());
		rs1=ps1.executeQuery();
		while(rs1.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs1.getString("RES_ID"));
			json.accumulate("pId", rs1.getString("CLASSIFY_ID"));
			json.accumulate("name", rs1.getString("RES_NAME"));
			json.accumulate("isParent", "false");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		
		return jsonArr.toString();
	}
	/**
	 * 根据调拨获取ztree树
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getallotztreelogic(Connection conn,Account account) throws SQLException{
		String queryStr="SELECT LIBRARY_ID ,LIBRARY_NAME ,TOP_ID FROM OFF_LIBRARY WHERE DEL_STATUS =0 AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("LIBRARY_ID"));
			json.accumulate("pId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("LIBRARY_NAME"));
			json.accumulate("isParent", "true");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		ResultSet rs1 =null;
		String queryStr1="SELECT RES_ID ,CLASSIFY_ID,RES_NAME,BEFORESTOCK FROM OFF_RES WHERE ORG_ID=?";
		PreparedStatement ps1 = conn.prepareStatement(queryStr1);
		ps1.setString(1, account.getOrgId());
		rs1=ps1.executeQuery();
		while(rs1.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs1.getString("RES_ID"));
			json.accumulate("pId", rs1.getString("CLASSIFY_ID"));
			String resName=rs1.getString("RES_NAME")+"/库存"+rs1.getString("BEFORESTOCK");
			json.accumulate("name", resName);
			json.accumulate("isParent", "false");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
