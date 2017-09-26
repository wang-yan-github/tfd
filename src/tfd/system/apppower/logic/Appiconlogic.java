package tfd.system.apppower.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Appiconlogic {

	//查询各个模块的icon
	public String lookiconlogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT ICON_ID,SELECTED,MODULE_NAME,SORT FROM APP_ICON";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();	
			json.accumulate("id", rs.getString("ICON_ID"));
			json.accumulate("name", rs.getString("MODULE_NAME"));
			json.accumulate("selected", rs.getString("SELECTED"));
			json.accumulate("sort", rs.getInt("SORT"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
