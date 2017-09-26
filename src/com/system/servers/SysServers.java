package com.system.servers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.system.db.DbPoolConnection;
import com.system.global.SysProps;
import com.system.tool.SysTool;
import com.system.servers.AutoServer;
public class SysServers extends AutoServer {
  public static byte[] onlineSync = new byte[1];
  public void doTask() throws SQLException {
	  Connection conn = DbPoolConnection.getInstance().getConnection();
    try {
      synchronized(SysServers.onlineSync) {
        this.clearOnlineStatus(conn);
        conn.close();
      }
    } catch (Exception e) {
    	e.printStackTrace();
    } finally {
    }
  }
  public void clearOnlineStatus(Connection conn) throws Exception {
    String ref = SysProps.getProp("$ONLINE_REF_SEC");
    
    if (SysTool.isNullorEmpty(ref)) {
      ref = "120";
    }
    int refInt = (Integer.parseInt(ref) + 5) * 1000 ;
    long time = new Date().getTime() - refInt;
    String update ="DELETE FROM USER_ONLINE WHERE LOGIN_TIME < ?" ;
    PreparedStatement ps = conn.prepareStatement(update);
    try{
      ps.setTimestamp(1, new Timestamp(time));
      ps.executeUpdate();
      ps.close();
    }catch(Exception ex) {
      throw ex;
    }
  }
}
