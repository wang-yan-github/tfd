package tfd.system.module.selectpriv.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

public class selectPrivLogic {

	public String getPrivLogic(Connection conn,Account account) throws SQLException{
		String queryStr="SELECT USER_PRIV_ID ,USER_PRIV_NAME FROM USER_PRIV WHERE ORG_ID = ?";
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("privId", rs.getString("USER_PRIV_ID"));
			json.accumulate("privName", rs.getString("USER_PRIV_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
