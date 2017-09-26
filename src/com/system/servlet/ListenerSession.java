package com.system.servlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import tfd.system.syslog.logic.SysLogLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysLogConst;
import com.system.logic.SysLogic;
import com.system.servers.SysServers;

public class ListenerSession implements HttpSessionListener{
	  private static HashMap<String,HttpSession> sessionContextMap = new HashMap<String,HttpSession>(); 
	  public static HashMap<String,HttpSession> getSessaionContextMap() {
	    return sessionContextMap;
	  }
	  
	  public static void removeSessaionContextMap(String sessionId) {
		  sessionContextMap.remove(sessionId);
		  }
	  /**
	   * session创建
	   */
	  public static void addSession(HttpSession session) {
		    sessionContextMap.put(session.getId(), session);
		  }
	  
	  public void sessionCreated(HttpSessionEvent event) {
	    HttpSession session = event.getSession();
	    sessionContextMap.put(session.getId(), session);
	  }

	  /**
	   * session销毁
	   */
	  public void sessionDestroyed(HttpSessionEvent event)
	  { 
		HttpSession session = event.getSession();
	    Boolean isRequest = (Boolean) session.getAttribute(SysConst.CURR_REQUEST_FLAG);
	    Account account = (Account) session.getAttribute(SysConst.LOGIN_USER);
	    Connection dbConn=null;
	    PreparedStatement ps=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
	    if (isRequest != null){
	    		SysLogic logic = new SysLogic();
		        try {
			          if (account != null){
			            String currRequestAddress = (String) session.getAttribute(SysConst.CURR_REQUEST_ADDRESS);
			            SysLogLogic.addSysLog(dbConn, SysLogConst.LOGOUT, "退出系统", account.getAccountId(), currRequestAddress);
			            logic.deleteOnline(dbConn, String.valueOf(session.getAttribute("sessionToken")));
			          }
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        }
	    }else{
	      try {
	        if (account != null){
	          String delSql = "DELETE" +
	          " FROM USER_ONLINE" +
	          " WHERE SESSION_TOKEN = '" + String.valueOf(session.getAttribute("sessionToken")) + "'";
	          	synchronized(SysServers.onlineSync) {
	          	ps = dbConn.prepareStatement(delSql);
	            ps.executeUpdate();
	            ps.close();
	          }
	          String sql = "INSERT INTO SYS_LOG (ACCOUNT_ID, DATE, IP, TYPE, REMARK)" +
	          " VALUES('"+account.getAccountId()+"','"+new java.sql.Date(new Date().getTime())+"','"+session.getAttribute("LOGIN_IP")+"','"+SysLogConst.LOGOUT+"','A')";
	          ps= dbConn.prepareStatement(sql);
	           ps.executeUpdate();
	           ps.close();
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
//	    try {
//	      if (account != null) {
//	        long time= (new Date().getTime() - account.getLastVisitTime().getTime()) / 1000;
//	        long online = account.getOnLine();
//	        online += time;
//	        String sql = "UPDATE ACCOUNT SET ON_LINE = " + online +" WHERE ACCOUNT_ID = '" + account.getAccountId()+"'";
//	        ps =dbConn.prepareStatement(sql);
//	        ps.executeUpdate();
//	        ps.close();
//	      }
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
	    if(dbConn!=null)
	    {
	    	dbConn.commit();
			dbConn.close();
	    }
	    Enumeration<String> en = session.getAttributeNames();
	    while (en.hasMoreElements()) {
	      session.removeAttribute((String) en.nextElement());
	    }
	    if (session != null) {
	      sessionContextMap.remove(session.getId());
	    }
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
       
	  }
	}
