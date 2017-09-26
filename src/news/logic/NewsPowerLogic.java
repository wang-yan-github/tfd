package news.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import news.data.NewsPower;

public class NewsPowerLogic {
	/**
	 * 添加权限数据
	 * Author: Wp
	 * @param conn
	 * @param newspower
	 * @return
	 * @throws SQLException
	 */
	public int addpowerlogic(Connection conn,NewsPower newspower)throws SQLException{
		String queryStr="INSERT INTO NEWS_POWER(POWER_ID,ACCOUNT_ID,POWER_STATUS,ORG_ID)VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, newspower.getPowerId());
		ps.setString(2, newspower.getAccountId());
		ps.setString(3, newspower.getPowerStatus());
		ps.setString(4, newspower.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据权限ID 删除数据信息
	 * Author: Wp
	 * @param conn
	 * @param powerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delpowerlogic(Connection conn,String powerId,String orgId)throws SQLException{
		String queryStr="DELETE FROM NEWS_POWER WHERE POWER_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, powerId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据权限Id 修改数据信息
	 * Author: Wp
	 * @param conn
	 * @param newspower
	 * @return
	 * @throws SQLException
	 */
	public int updatepowerlogic(Connection conn,NewsPower newspower)throws SQLException{
		String queryStr="UPDATE NEWS_POWER SET ACCOUNT_ID=?,POWER_STATUS=? WHERE POWER_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, newspower.getAccountId());
		ps.setString(2, newspower.getPowerStatus());
		ps.setString(3, newspower.getPowerId());
		ps.setString(4, newspower.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 获取权限列表
	 * Author: Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String lookpowerlogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT POWER_ID,T2.USER_NAME AS USER_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,POWER_STATUS "
				+ "FROM NEWS_POWER T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("powerId", rs.getString("POWER_ID"));
			if(rs.getString("ACCOUNT_ID")==null){
			json.accumulate("accountId", "");
			}else{
				json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			}
			if(rs.getString("USER_NAME")==null){
				json.accumulate("userName", "");	
			}else{
			json.accumulate("userName", rs.getString("USER_NAME"));
			}
			json.accumulate("powerStatus", rs.getString("POWER_STATUS"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据accountId 查询权限信息
	 * Author: Wp
	 * @param conn
	 * @param accountId
	 * @return
	 * @throws SQLException
	 */
	public int getaccountIdlogic(Connection conn,String accountId)throws SQLException{
		String queryStr="SELECT COUNT(1) FROM NEWS_POWER WHERE ACCOUNT_ID =? AND POWER_STATUS =1";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		int data=0;
		if(rs.next()){
		data=rs.getInt(1);
		}
		rs.close();
		ps.close();
		return data;
	}
	/**
	 * 获取权限列表信息
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getapprovallogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT T1.ACCOUNT_ID AS ACCOUNT_ID,T2.USER_NAME AS USER_NAME "
				+ "FROM NEWS_POWER T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.ACCOUNT_ID WHERE T1.ORG_ID=? AND POWER_STATUS =2";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			if(rs.getString("ACCOUNT_ID")==null){
				json.accumulate("accountId", "");
			}else{
				json.accumulate("accountId", rs.getString("ACCOUNT_ID"));	
			}
			if(rs.getString("USER_NAME")==null){
				json.accumulate("userName", "");	
			}else{
			json.accumulate("userName", rs.getString("USER_NAME"));
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
