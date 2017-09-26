package tfd.system.mobile.changeorg.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbPoolConnection;

public class ChangeOrgLogic {

	public String companyListLogic( Connection conn,String phone)throws Exception{
		JSONObject returnjson=new JSONObject();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT T1.BY_NAME AS BY_NAME,T1.PASS_WORD AS PASS_WORD,T2.ORG_NAME AS ORG_NAME,T1.ORG_ID AS ORG_ID "
					+ "FROM CHANGE_ORG T1 LEFT JOIN ORG_CONFIG T2 ON T1.ORG_ID=T2.ORG_ID "
					+ "WHERE LOCATE(?,CONCAT(',',T1.ACCOUNT_FLAG,','))>0 AND T1.STATUS='1'";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT T1.BY_NAME AS BY_NAME,T1.PASS_WORD AS PASS_WORD,T2.ORG_NAME AS ORG_NAME,T1.ORG_ID AS ORG_ID "
					+ "FROM CHANGE_ORG T1 LEFT JOIN ORG_CONFIG T2 ON T1.ORG_ID=T2.ORG_ID "
					+ "WHERE INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_FLAG))||',',? )>0 AND T1.STATUS='1'";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, ","+phone+",");
		ResultSet rs=ps.executeQuery();
		JSONArray dataArray=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("companyName", rs.getString("ORG_NAME"));
			json.accumulate("companyId", rs.getString("ORG_ID"));
			json.accumulate("phone", rs.getString("BY_NAME"));
			json.accumulate("password", rs.getString("PASS_WORD"));
			dataArray.add(json);
		}
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "获取列表成功");
		JSONObject data=new JSONObject();
		data.accumulate("time", new Date().getTime()/1000);
		data.accumulate("data", dataArray);
		returnjson.accumulate("data", data);
		rs.close();
		ps.close();
		return returnjson.toString();
	}
}
