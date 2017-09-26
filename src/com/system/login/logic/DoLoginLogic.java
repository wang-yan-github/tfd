package com.system.login.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.system.login.data.UserOnline;
import com.system.tool.SysTool;
public class DoLoginLogic {
	public boolean queryAccount(Connection conn, String accountId,String password) throws Exception{
		setLastLoginTime(conn,accountId);
	    String querStr="SELECT COUNT(*) FROM ACCOUNT WHERE ACCOUNT_ID='"+accountId+"' AND PASS_WORD='"+password+"' AND NOT_LOGIN='0'";
	    ResultSet rs = null;
	    boolean falg= false;
	    try{
				PreparedStatement ps=conn.prepareStatement(querStr);
				rs=ps.executeQuery();
				while(rs.next())
				{
					if(rs.getInt(1)>0) falg=true;
				}
				rs.close();
				ps.close();
				return falg;
	    }catch(Exception ex) {
	      throw ex;
	    }
	  }
	 public void setLastLoginTime(Connection conn,String accountId)throws Exception{
		  String sql = "UPDATE ACCOUNT SET LAST_LOGIN_TIME = ? ,LOGIN_NUM = ? WHERE ACCOUNT_ID = ?";
		  String nd = SysTool.getDateTimeStr(new Date());
		  int loginNum = Integer.parseInt(getLoginNum(conn, accountId));
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1,nd);
		  ps.setInt(2, ++loginNum);
		  ps.setString(3, accountId);
		  ps.executeUpdate();
		  ps.close();
	  }
	 public void setInitNum(Connection conn,String accountId)throws Exception{
		  String sql = "UPDATE ACCOUNT SET LOGIN_NUM = 0 WHERE ACCOUNT_ID = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, accountId);
		  ps.executeUpdate();
		  ps.close();
	 }
	 public String getLastLoginTime(Connection conn,String accountId)throws Exception{
		 String lastTime = "";
		 ResultSet rs = null;
		  String sql = "SELECT LAST_LOGIN_TIME FROM ACCOUNT WHERE ACCOUNT_ID = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, accountId);
		  rs = ps.executeQuery();
		  if(rs.next()){
			  lastTime =  rs.getString("LAST_LOGIN_TIME");
		  }
		  rs.close();
		  ps.close();
		  return lastTime;
	  }
	 public String getLoginNum(Connection conn,String accountId)throws Exception{
		 String loginNum = "";
		 ResultSet rs = null;
		  String sql = "SELECT LOGIN_NUM FROM ACCOUNT WHERE ACCOUNT_ID = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, accountId);
		  rs = ps.executeQuery();
		  if(rs.next()){
			  loginNum =  rs.getString("LOGIN_NUM");
		  }
		  rs.close();
		  ps.close();
		  return loginNum;
	  }
	 /**
	   * 获取title
	   * @return
	   * @throws Exception
	   */
	  public void updateLastVisitInfo(Connection conn, UserOnline userOnline) throws Exception {
		  String queryStr="UPDATE USER_ONLINE SET LOGIN_TIME=?,STATE=?,SESSION_TOKEN=?,IP=? WHERE ACCOUNT_ID=? AND ORG_ID=?";
		  PreparedStatement ps = conn.prepareStatement(queryStr);
		  ps.setString(1, userOnline.getLoginTime());
		  ps.setString(2, userOnline.getState());
		  ps.setString(3, userOnline.getSessionToken());
		  ps.setString(4, userOnline.getIp());
		  ps.setString(5, userOnline.getAccountId());
		  ps.setString(6, userOnline.getOrgId());
		  ps.executeUpdate();
		  ps.close();
	   	  }
	  public void addOnline(Connection conn,UserOnline userOnline) throws SQLException
	  {
		  String queryStr="INSERT INTO USER_ONLINE(ACCOUNT_ID,LOGIN_TIME,STATE,SESSION_TOKEN,IP,ORG_ID)VALUES(?,?,?,?,?,?)";
		  PreparedStatement ps = conn.prepareStatement(queryStr);
		  ps.setString(1, userOnline.getAccountId());
		  ps.setString(2, userOnline.getLoginTime());
		  ps.setString(3, userOnline.getState());
		  ps.setString(4, userOnline.getSessionToken());
		  ps.setString(5, userOnline.getIp());
		  ps.setString(6, userOnline.getOrgId());
		  ps.executeUpdate();
		  ps.close();
	  }
	  //查询人员在线状态
	   public int queryUserOnline (Connection conn,String accountId) throws SQLException
	   {
		   String returnData="0";
		   ResultSet rs = null;
		   String queryStr="SELECT STATE FROM USER_ONLINE WHERE ACCOUNT_ID=?";
		   PreparedStatement ps =conn.prepareStatement(queryStr);
		   ps.setString(1, accountId);
		   rs = ps.executeQuery();
		   if(rs.next())
		   {
			   if(!SysTool.isNullorEmpty(rs.getString("STATE")))
			   {
				   returnData=rs.getString("STATE");
			   }
		   }
		   rs.close();
		   ps.close();
		   return Integer.parseInt(returnData.trim());
	   }
	   /**
	    * 查询一些登陆时需要的系统参数
	    * @param conn
	    * @return
	    * @throws Exception
	    */
	   public Map<String,String> getSysPara(Connection conn,String orgId) throws Exception{
		    ResultSet rs = null;
		      String queryStr = "SELECT SYS_PARAM_VALUE,SYS_PARAM_NAME" +
		      		" FROM SYS_CONFIG WHERE ORG_ID=? AND SYS_PARAM_NAME IN" +
		          " ('SEC_PASS_TIME','SEC_ON_STATUS')";
			   PreparedStatement ps = conn.prepareStatement(queryStr);
			   ps.setString(1, orgId);
			   rs=ps.executeQuery();
		      Map<String,String> map = new HashMap<String,String>();
		      while (rs.next()){
		        String value = parseString(rs.getString("SYS_PARAM_VALUE"));
		        String name = parseString(rs.getString("SYS_PARAM_NAME"));
		        map.put(name, value);
		      }
		      rs.close();
		      ps.close();
		      return map;
		  }
	   public static String parseString(String s){
		    if (s == null){
		      return "";
		    }
		    else {
		      return s.trim();
		    }
		  }
		  
		  public static String parseString(String s, String defaultValue){
		    if (s == null || s.trim().equals("")){
		      return defaultValue;
		    }
		    else {
		      return s.trim();
		    }
		  }
		 
}
