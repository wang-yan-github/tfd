package com.system.logic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.system.servers.SysServers;
public class SysLogic {
	public void deleteOnline(Connection conn, String sessionToken) throws Exception{
	    synchronized(SysServers.onlineSync) {
	      String sql = "DELETE FROM USER_ONLINE WHERE SESSION_TOKEN = ?";
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, sessionToken);
	      ps.executeUpdate();
	      ps.close();
	    }
	  }
}
