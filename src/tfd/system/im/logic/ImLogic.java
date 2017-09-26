package tfd.system.im.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONObject;

public class ImLogic {

	/**
	 * 检查token 有效性
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String checkaccesstokenLogic(Connection conn,String token)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="SELECT STATE FROM USER_ONLINE WHERE SESSION_TOKEN=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, token);
		ResultSet rs=ps.executeQuery();
		int state=0;
		if(rs.next()){
			state=rs.getInt("STATE");
		}
		datajson.accumulate("status", state);
		rs.close();
		ps.close();
		return datajson.toString();
	}
}
