package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;

import officesupplies.data.Offmaint;
import tfd.system.unit.account.data.Account;

public class Offmaintlogic {
	/**
	 * 添加办公用品维护信息
	 * Author:Wp
	 * @param conn
	 * @param maint
	 * @return
	 * @throws SQLException
	 */
	public int addmaintlogic(Connection conn,Offmaint maint)throws SQLException{
		String queryStr="INSERT INTO OFF_MAINT (MAINT_ID,MAINT_TYPE,LIBRARY_ID,CLASSIFY_ID,RES_ID,RES_PRICE,MAINT_NUM,MAINT_REMARY,MAINT_TIME,CREATE_USER,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, maint.getMaintId());
		ps.setString(2, maint.getMaintType());
		ps.setString(3, maint.getLibraryId());
		ps.setString(4, maint.getClassifyId());
		ps.setString(5, maint.getResId());
		ps.setString(6, maint.getResPrice());
		ps.setString(7, maint.getMaintNum());
		ps.setString(8, maint.getMaintRemary());
		ps.setString(9, maint.getMaintTime());
		ps.setString(10, maint.getCreateUser());
		ps.setString(11, maint.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据createUser获取库存维护的操作信息
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getUsermaintlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT MAINT_ID,MAINT_TYPE,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID = T1.LIBRARY_ID) AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID = T1.CLASSIFY_ID)AS CLASSIFY_NAME,CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID) AS RES_NAME,(SELECT BEFORESTOCK FROM OFF_RES T4 WHERE T4.RES_ID =T1.RES_ID)AS BEFORESTOCK,RES_ID,RES_PRICE,MAINT_NUM,MAINT_REMARY,MAINT_TIME,CREATE_USER FROM OFF_MAINT T1 WHERE CREATE_USER =? AND ORG_ID=?";
		String optStr= "[{'function':'updatemaint','name':'修改','parm':'MAINT_ID'},{'function':'delmaint','name':'放弃操作','parm':'MAINT_ID,MAINT_NUM,MAINT_TYPE,RES_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据maintId获取库存维护信息
	 * Author:Wp
	 * @param conn
	 * @param maintId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getIdlogic(Connection conn,String maintId,String orgId)throws SQLException{
		String queryStr="SELECT MAINT_ID,MAINT_TYPE,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID = T1.LIBRARY_ID) AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID = T1.CLASSIFY_ID)AS CLASSIFY_NAME,CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID) AS RES_NAME,(SELECT BEFORESTOCK FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID) AS BEFORESTOCK,RES_ID,RES_PRICE,MAINT_NUM,MAINT_REMARY,MAINT_TIME FROM OFF_MAINT T1 WHERE MAINT_ID=? AND ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, maintId);
		ps.setString(2, orgId);
		ResultSet rs=null;
		rs=ps.executeQuery();
		JSONObject json=new JSONObject();
		if(rs.next()){
			json.accumulate("maintId", rs.getString("MAINT_ID"));
			json.accumulate("maintType", rs.getString("MAINT_TYPE"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("classifyName", rs.getString("CLASSIFY_NAME"));
			json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
			json.accumulate("beforestock", rs.getString("BEFORESTOCK"));
			json.accumulate("resName", rs.getString("RES_NAME"));
			json.accumulate("resId", rs.getString("RES_ID"));
			json.accumulate("resPrice", rs.getString("RES_PRICE"));
			json.accumulate("maintNum", rs.getString("MAINT_NUM"));
			json.accumulate("maintRemary", rs.getString("MAINT_REMARY"));
		}
		rs.close();
		ps.close();
		return json.toString();
	 }
	/**
	 * 修改库存维护的记录信息
	 * Author:Wp
	 * @param conn
	 * @param maint
	 * @return
	 * @throws SQLException
	 */
	public int updatemaintlogic(Connection conn,Offmaint maint)throws SQLException{
		String queryStr="UPDATE OFF_MAINT SET MAINT_NUM=?,RES_PRICE=?,MAINT_REMARY=?,MAINT_TIME=? WHERE MAINT_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, maint.getMaintNum());
		ps.setString(2, maint.getResPrice());
		ps.setString(3, maint.getMaintRemary());
		ps.setString(4, maint.getMaintTime());
		ps.setString(5, maint.getMaintId());
		ps.setString(6, maint.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 删除库存维护的记录信息
	 * Author:Wp
	 * @param conn
	 * @param maintId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delmaintlogic(Connection conn,String maintId,String orgId)throws SQLException{
		String queryStr="DELETE FROM OFF_MAINT WHERE MAINT_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, maintId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
}
