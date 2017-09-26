package tfd.system.sysserver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.servers.AutoServer;
import com.system.servers.AutoThreadTool;
import com.system.servlet.ListenerSession;
import com.system.tool.SysTool;
public class SystemService extends AutoServer {
	public static byte[] onlineSync = new byte[1];
  public void doTask() throws SQLException {
	  Iterator<Entry<String, HttpSession>> it=null;
	  synchronized(SystemService.onlineSync) {
		  Connection dbConn =AutoThreadTool.getDbConn();
		  String sessionValidMinuts = SysProps.getString(SysPropKey.SESSION_VALID_MINUTS);
		  if(SysTool.isNullorEmpty(sessionValidMinuts))
    	  {
    		  sessionValidMinuts="30";
    	  }
		  List<String> sessionList =new ArrayList<String>();
		  HttpSession session=null;
		  HashMap<String,HttpSession> sessionMap = ListenerSession.getSessaionContextMap();
		  it=sessionMap.entrySet().iterator(); 
		  while(it.hasNext()){
		          Entry<String, HttpSession> entry = it.next();          
		          session=(HttpSession) entry.getValue();
		          long lastAccessedTime=session.getLastAccessedTime();
		          String sessionId = session.getId();
		    	  long sv=Integer.parseInt(sessionValidMinuts);
		    	  long sd = new Date(lastAccessedTime+sv*60*1000).getTime();
		    	  long newTime=new Date().getTime();// new Date()为获取当前系统时间
		    	  if(sd<newTime)
		    	  {
		    		  sessionList.add(sessionId);
		    	  }
		  }
			  for(int i=0;sessionList.size()>i;i++)
			  {
				  setSessionMap(dbConn,sessionList.get(i));
			  }
			  if(dbConn!=null)
			  {
				  dbConn.commit();
			  }
	  }
  }
  public  void setSessionMap(Connection conn,String sessionId) throws SQLException
  {
		  ListenerSession.removeSessaionContextMap(sessionId);
		  deluseronline(conn,sessionId);
  }
  public void deluseronline(Connection conn,String sessionId) throws SQLException
  {
		  String queryStr = "UPDATE USER_ONLINE SET STATE='0' WHERE SESSION_TOKEN=?";
		  PreparedStatement ps =conn.prepareStatement(queryStr);
		  ps.setString(1, sessionId);
		  ps.executeUpdate();
		  ps.close();
  }
}
