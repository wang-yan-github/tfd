package customermanage.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tfd.system.unit.account.data.Account;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import customermanage.data.CustomerPower;

public class CustomerpowerLogic {
	/**
	 * 添加权限人员
	 * Author:Wp
	 * @param conn
	 * @param customerpower
	 * @return
	 * @throws SQLException
	 */
	public int addpowerLogic(Connection conn,CustomerPower customerpower)throws SQLException{
		String queryStr="INSERT INTO CUSTOMER_POWER (POWER_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerpower.getPowerId());
		ps.setString(2, customerpower.getAccountId());
		ps.setString(3, customerpower.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 查看权限人员
	 * Author:Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String lookpowerLogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT POWER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.USER_NAME AS USER_NAME FROM CUSTOMER_POWER T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID WHERE T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("powerId", rs.getString("POWER_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 删除权限人员
	 * Author:Wp
	 * @param conn
	 * @param powerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delpowerLogic(Connection conn,String powerId,String orgId)throws SQLException{
		String queryStr="DELETE FROM CUSTOMER_POWER WHERE POWER_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, powerId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 判断用户是否有权限
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public int checkpowerLogic(Connection conn,Account account)throws SQLException{
		String queryStr="SELECT COUNT(1) AS NUM FROM CUSTOMER_POWER WHERE ACCOUNT_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs=ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
}
