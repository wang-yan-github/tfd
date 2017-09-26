package tfd.system.mobile.diary.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import diary.data.Diary;
import diary.data.DiaryComments;
import diary.logic.DiaryFitLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

public class DiaryLogic {

	/**
	 * 查看日志列表
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAccountIdLogic(Connection conn,String accountId,String orgId,HttpServletRequest request,int page,String type)throws Exception{
		JSONObject datajson=new JSONObject();
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT * FROM ( SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ORG_ID=? AND T1.ACCOUNT_ID=? ";
			if(type.equals("2")){
				queryStr+="  AND DIARY_MOLD=0 ";
			}
			queryStr+=") DIARY ORDER BY DIARY_DATETIME DESC LIMIT "+(page*15)+",15";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM ( SELECT * FROM ( SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ORG_ID=? AND T1.ACCOUNT_ID=? "; 
			if(type.equals("2")){
				queryStr+="  AND DIARY_MOLD=0 ";
			}
			queryStr+=") DIARY ORDER BY DIARY_DATETIME DESC ) WHERE ROWNUM BETWEEN  "+(page*15)+" AND "+(page+1)*15;
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("DIARY_ID"));
			json.accumulate("createId",rs.getString("ACCOUNT_ID"));
			json.accumulate("name", rs.getString("USER_NAME"));
			if(rs.getString("HEAD_IMG")==null||rs.getString("HEAD_IMG").equals("")){
				json.accumulate("avatar", "");
			}else{
				json.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
			}
			json.accumulate("title", rs.getString("DIARY_NAME"));
			String read="";
			if(rs.getString("LOOK_STAFF")!=null){
			String[] lookStaff=rs.getString("LOOK_STAFF").split(",");
			read=lookStaff.length+"";
			}else{
				read="0";
			}
			json.accumulate("read", read);
			json.accumulate("like", rs.getString("LAUD_NUM"));
			json.accumulate("comment", rs.getString("COMM_COUNT"));
			json.accumulate("time", rs.getString("DIARY_DATETIME"));
			jsonArr.add(json);
		}
		JSONObject listjson=new JSONObject();
		listjson.accumulate("time", new Date().getTime()/1000);
		String havemore="1";
		if((page+1)*15>=this.getUserCount(conn, orgId, accountId,type)){
			havemore="0";
		}
		listjson.accumulate("havemore", havemore);
		listjson.accumulate("page", ""+page);
		listjson.accumulate("list", jsonArr);
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取列表成功");
		datajson.accumulate("data", listjson);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 获取日志总条数
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param orgId
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public int getUserCount(Connection conn,String orgId,String accountId,String type)throws Exception{
		String queryStr="SELECT COUNT(1) AS NUM FROM DIARY WHERE ORG_ID=? AND ACCOUNT_ID =?";
		if(type.equals("2")){
			queryStr+="  AND DIARY_MOLD=0 ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ResultSet rs=ps.executeQuery();
		int num=0;
		if(rs.next()){
			num=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return num;
	}
	/**
	 * 获取他人日志
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String getotherdiaryLogic(Connection conn,Account account,HttpServletRequest request,int page)throws Exception{
		JSONObject datajson=new JSONObject();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountStr="";
		if(manageDept.equals("2"))
		{
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}else
		{
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
				+" AND ( (EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0)  OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ORDER BY DIARY_DATETIME DESC LIMIT "+(page*15)+",15";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM (SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
					+" AND ((EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0 ) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ORDER BY DIARY_DATETIME DESC ) WHERE ROWNUM BETWEEN  "+(page*15)+" AND "+(page+1)*15;
		}
		String accountId=","+account.getAccountId()+",";
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		PreparedStatement ps = conn.prepareStatement(queryStr);
		if(manageDept.equals("2"))
		{
			ps.setString(1, account.getAccountId());
			ps.setString(2, userInfo.getDeptId());
			ps.setString(3, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(4, leaveNoId);
			ps.setString(5, userInfo.getLeadId());
			ps.setString(6, accountId);
			ps.setString(7, account.getOrgId());
		}else
		{
			ps.setString(1, account.getAccountId());
			ps.setString(2, userInfo.getLeadLeave());
			ps.setString(3, userInfo.getLeadId());
			ps.setString(4, accountId);
			ps.setString(5, account.getOrgId());
		}
		JSONArray jsonArr=new JSONArray();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("DIARY_ID"));
			json.accumulate("createId",rs.getString("ACCOUNT_ID"));
			json.accumulate("name", rs.getString("USER_NAME"));
			if(rs.getString("HEAD_IMG")==null||rs.getString("HEAD_IMG").equals("")){
				json.accumulate("avatar", "");
			}else{
				json.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
			}
			json.accumulate("title", rs.getString("DIARY_NAME"));
			String read="";
			if(rs.getString("LOOK_STAFF")!=null){
			String[] lookStaff=rs.getString("LOOK_STAFF").split(",");
			read=lookStaff.length+"";
			}else{
				read="0";
			}
			json.accumulate("read", read);
			json.accumulate("like", rs.getString("LAUD_NUM"));
			json.accumulate("comment", rs.getString("COMM_COUNT"));
			json.accumulate("time", rs.getString("DIARY_DATETIME"));
			jsonArr.add(json);
		}
		JSONObject listjson=new JSONObject();
		listjson.accumulate("time", new Date().getTime()/1000);
		String havemore="1";
		if((page+1)*15>=this.getotherCount(conn, account)){
			havemore="0";
		}
		listjson.accumulate("havemore", havemore);
		listjson.accumulate("page", ""+page);
		listjson.accumulate("list", jsonArr);
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取列表成功");
		datajson.accumulate("data", listjson);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 获取他人日志数量
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int getotherCount(Connection conn,Account account)throws Exception{
		int num=0;
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountStr="";
		if(manageDept.equals("2"))
		{
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}else
		{
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT COUNT(1) AS NUM FROM DIARY T1 WHERE T1.ACCOUNT_ID !=?"
				+" AND ( (EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0)  OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM (SELECT COUNT(1) AS NUM FROM DIARY T1 WHERE T1.ACCOUNT_ID !=?"
					+" AND ((EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0 ) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ";
		}
		String accountId=","+account.getAccountId()+",";
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		PreparedStatement ps = conn.prepareStatement(queryStr);
		if(manageDept.equals("2"))
		{
			ps.setString(1, account.getAccountId());
			ps.setString(2, userInfo.getDeptId());
			ps.setString(3, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(4, leaveNoId);
			ps.setString(5, userInfo.getLeadId());
			ps.setString(6, accountId);
			ps.setString(7, account.getOrgId());
		}else
		{
			ps.setString(1, account.getAccountId());
			ps.setString(2, userInfo.getLeadLeave());
			ps.setString(3, userInfo.getLeadId());
			ps.setString(4, accountId);
			ps.setString(5, account.getOrgId());
		}
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			num=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return num;
	}
	/**
	 * 获取分享给我的日志
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String getsharediaryLogic(Connection conn,String accountId,String orgId,HttpServletRequest request,int page)throws Exception{
		JSONObject datajson=new JSONObject();
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT * FROM ( SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ORG_ID =? AND (LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll') ) DIARY ORDER BY DIARY_DATETIME DESC LIMIT "+(page*15)+",15";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM ( SELECT * FROM ( SELECT DIARY_ID,DIARY_DATETIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,LAUD_NUM,LOOK_STAFF,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.ACCOUNT_ID WHERE T1.ORG_ID =? AND ( INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll') ) DIARY ORDER BY DIARY_DATETIME DESC ) WHERE ROWNUM BETWEEN  "+(page*15)+" AND "+(page+1)*15;
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, ","+accountId+",");
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("DIARY_ID"));
			json.accumulate("createId",rs.getString("ACCOUNT_ID"));
			json.accumulate("name", rs.getString("USER_NAME"));
			if(rs.getString("HEAD_IMG")==null||rs.getString("HEAD_IMG").equals("")){
				json.accumulate("avatar", "");
			}else{
				json.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
			}
			json.accumulate("title", rs.getString("DIARY_NAME"));
			String read="";
			if(rs.getString("LOOK_STAFF")!=null){
			String[] lookStaff=rs.getString("LOOK_STAFF").split(",");
			read=lookStaff.length+"";
			}else{
				read="0";
			}
			json.accumulate("read", read);
			json.accumulate("like", rs.getString("LAUD_NUM"));
			json.accumulate("comment", rs.getString("COMM_COUNT"));
			json.accumulate("time", rs.getString("DIARY_DATETIME"));
			jsonArr.add(json);
		}
		JSONObject listjson=new JSONObject();
		listjson.accumulate("time", new Date().getTime()/1000);
		String havemore="1";
		if((page+1)*15>=this.getshareCount(conn, accountId, orgId)){
			havemore="0";
		}
		listjson.accumulate("havemore", havemore);
		listjson.accumulate("page", ""+page);
		listjson.accumulate("list", jsonArr);
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取列表成功");
		datajson.accumulate("data", listjson);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 获取分享给我的日志的总条数
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int getshareCount(Connection conn,String accountId,String orgId)throws Exception{
		int num=0;
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT COUNT(1) AS NUM FROM DIARY WHERE ORG_ID=? AND (LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll')";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT COUNT(1) AS NUM FROM DIARY WHERE ORG_ID=? AND (INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll')";
		}	
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, ","+accountId+",");
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			num=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return num;
	}
	/**
	 * 日志阅读
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordReadLogic(Connection conn,String diaryId,String accountId,String orgId)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="SELECT LOOK_STAFF FROM DIARY WHERE ORG_ID=? AND DIARY_ID =?";
		PreparedStatement ps=null;
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, diaryId);
		ResultSet rs=null;
		rs=ps.executeQuery();
		String lookStaff="";
		if(rs.next()){
			if(rs.getString("LOOK_STAFF")!=null){
				lookStaff=rs.getString("LOOK_STAFF");
			}
		}
		if(lookStaff!=""){
			lookStaff+=","+accountId;
		}else{
			lookStaff=accountId;
		}
		 queryStr="UPDATE DIARY SET LOOK_STAFF=? WHERE ORG_ID=? AND DIARY_ID =? ";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, lookStaff);
		ps.setString(2, orgId);
		ps.setString(3, diaryId);
		int i=ps.executeUpdate();
		JSONObject data=new JSONObject();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "阅读成功");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "阅读失败");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}
		rs.close();
		ps.close();
		return datajson.toString();		
	}
	/**
	 * 日志点赞
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordLikeLogic(Connection conn,String diaryId,String accountId,String orgId)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="SELECT LAUD ,LAUD_NUM FROM DIARY WHERE ORG_ID=? AND DIARY_ID=? ";
		PreparedStatement ps=null;
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, diaryId);
		ResultSet rs=null;
		rs=ps.executeQuery();
		String laud="";
		int laudnum=0;
		if(rs.next()){
			laud=rs.getString("LAUD");
			laudnum=rs.getInt("LAUD_NUM");
		}
		String laudStr=","+laud+",";
		String accountStr=","+accountId+",";
		JSONObject data=new JSONObject();
		if(laudStr.indexOf(accountStr)!=-1){
			datajson.accumulate("status_code", "404");
			datajson.accumulate("msg", "已经点赞，不可重复");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}else{
		if(laud!=""){
			laud+=","+accountId;
		}else{
			laud=accountId;
		}
		laudnum+=1;
		queryStr="UPDATE DIARY SET LAUD =? ,LAUD_NUM=? WHERE ORG_ID=? AND DIARY_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, laud);
		ps.setInt(2, laudnum);
		ps.setString(3, orgId);
		ps.setString(4, diaryId);
		int i=ps.executeUpdate();
		
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "点赞成功");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "点赞失败");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}
		}
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 日志详情
	 * Time 2015-11-02
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordDetailLogic(Connection conn,String diaryId,String accountId,String orgId,String to_ken,String phone)throws Exception{
		JSONObject datajson=new JSONObject();
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT DIARY_ID,DIARY_TITLETIME,ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD,LOCATE(?,CONCAT(',',LOOK_STAFF,',')) AS READING FROM DIARY WHERE  ORG_ID=? AND DIARY_ID=?";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT DIARY_ID,DIARY_TITLETIME,ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD,INSTR(CONCAT(',',TO_CHAR(LOOK_STAFF))||',',?) AS READING FROM DIARY WHERE  ORG_ID=? AND DIARY_ID=?";
		}	
		PreparedStatement ps=null;
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		ps.setString(4, diaryId);
		ResultSet rs=ps.executeQuery();
		JSONObject data=new JSONObject();
		
		String isread="0";
		String islike="0";
		String commentable="1";
		
		DiaryFitLogic diaryFitLogic=new DiaryFitLogic();
		String fit=diaryFitLogic.lookFitLogic(conn, orgId);
		JSONObject fitjson=JSONObject.fromObject(fit);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createUser="";
		if(rs.next()){
			createUser=rs.getString("ACCOUNT_ID");
			if(rs.getInt("READING")>0)
			{
				isread="1";
			}
			if(rs.getInt("LAUD")>0){
				islike="1";
			}
			if(fitjson!=null){
				Date titletime=sdf.parse(rs.getString("DIARY_TITLETIME"));
				long stime;
				if(fitjson.getString("startTime").equals("")){
					stime=0;
				}else{
				Date stimetime=sdf.parse(fitjson.getString("startTime"));
					stime=stimetime.getTime();
				}
				long etime;
				if(fitjson.getString("endTime").equals("")){
					etime=0;
				}else{
					Date etimetime=sdf.parse(fitjson.getString("endTime"));
					etime=etimetime.getTime();
				}
				String commStatus=fitjson.getString("commStatus");
				Date mydate=sdf.parse(SysTool.getCurDateTimeStr("yyyy-MM-dd"));
				int lockDay=Integer.parseInt(fitjson.getString("lockDay"));
				if(!fitjson.getString("startTime").equals("")&&stime<=titletime.getTime()&&(etime>=titletime.getTime()||fitjson.getString("endTime").equals(""))){					
					if(!commStatus.equals("1")){
						commentable="0";
					}
				}else{
					if(titletime.getTime()>=mydate.getTime()-lockDay*24*60*60*1000 || lockDay==0){
						
					}else{
						if(!commStatus.equals("1")){
							commentable="0";
						}
					}
				}
				}
		}
		data.accumulate("time", new Date().getTime()/1000);
		data.accumulate("content", "/tfd/diary/personaldiary/readdiary.jsp;JSESSIONID="+to_ken+"?diaryId="+diaryId+"&phone="+phone);
		data.accumulate("isRead", isread);
		data.accumulate("commentable", commentable);
		data.accumulate("isLike",islike);
		if(accountId.equals(createUser)){
			data.accumulate("canShare", "1");	
		}else{
			data.accumulate("canShare", "0");	
		}
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取详情成功");
		datajson.accumulate("data", data);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 日志分享
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param share
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordShareLogic(Connection conn,String diaryId,String share,String orgId)throws Exception{
		JSONObject datajson=new JSONObject();
		PreparedStatement ps=null;
		String queryStr="UPDATE DIARY SET SHARE_PRIV=? WHERE ORG_ID=? AND DIARY_ID=? ";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, share);
		ps.setString(2, orgId);
		ps.setString(3, diaryId);
		int i=ps.executeUpdate();
		JSONObject data=new JSONObject();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "分享成功");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "分享失败");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data", data);
		}		
		ps.close();
		return datajson.toString();
	}
	/**
	 * 日志阅读人员
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordReadingLogic(Connection conn,String diaryId,String orgId,HttpServletRequest request)throws Exception
	{
		JSONObject datajson=new JSONObject();
		AccountLogic accountLogic=new AccountLogic();
		String queryStr="SELECT LOOK_STAFF FROM DIARY WHERE ORG_ID=? AND DIARY_ID=?";
		PreparedStatement ps=null;
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, diaryId);
		ResultSet rs=null;
		rs=ps.executeQuery();
		String lookStaff="";
		if(rs.next()){
			lookStaff=rs.getString("LOOK_STAFF");
		}
		JSONArray list=new JSONArray();
		if(lookStaff!=null){
			String listuser=accountLogic.getjsonUserNameStr(conn, lookStaff);
			list=JSONArray.fromObject(listuser);
		}
		JSONArray datalist=new JSONArray();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject data=new JSONObject();
				data.accumulate("id", list.getJSONObject(i).getString("accountId"));
				data.accumulate("name", list.getJSONObject(i).getString("userName"));
				
				if(list.getJSONObject(i).getString("headImg")==null||list.getJSONObject(i).getString("headImg").equals("")){
					data.accumulate("avatar", "");
				}else{
					data.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+list.getJSONObject(i).getString("headImg"));
				}
				datalist.add(data);
			}
		}
		JSONObject dataJson=new JSONObject();
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取阅读人员成功");
		dataJson.accumulate("time", new Date().getTime()/1000);
		dataJson.accumulate("list",datalist);
		datajson.accumulate("data", dataJson);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 获取日志点赞人员
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordLikesLogic(Connection conn,String diaryId,String orgId,HttpServletRequest request)throws Exception{
		JSONObject datajson=new JSONObject();
		AccountLogic accountLogic=new AccountLogic();
		String queryStr="SELECT LAUD FROM DIARY WHERE ORG_ID=? AND DIARY_ID=?";
		PreparedStatement ps=null;
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, diaryId);
		ResultSet rs=null;
		rs=ps.executeQuery();
		String LAUD="";
		if(rs.next()){
			LAUD=rs.getString("LAUD");
		}
		JSONArray list=new JSONArray();
		if(LAUD!=""){
			String listuser=accountLogic.getjsonUserNameStr(conn, LAUD);
			list=JSONArray.fromObject(listuser);
		}
		JSONArray datalist=new JSONArray();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject data=new JSONObject();
				data.accumulate("id", list.getJSONObject(i).getString("accountId"));
				data.accumulate("name", list.getJSONObject(i).getString("userName"));
				
				if(list.getJSONObject(i).getString("headImg")==null||list.getJSONObject(i).getString("headImg").equals("")){
					data.accumulate("avatar", "");
				}else{
					data.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+list.getJSONObject(i).getString("headImg"));
				}
				datalist.add(data);
			}
		}
		JSONObject dataJson=new JSONObject();
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取点赞人员成功");
		dataJson.accumulate("time", new Date().getTime()/1000);
		dataJson.accumulate("list",datalist);
		datajson.accumulate("data", dataJson);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	/**
	 * 日志评论
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param diaryComm
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordCommentLogic(Connection conn,DiaryComments diaryComm)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="INSERT INTO DIARY_COMMENTS (COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,DIARY_ID,ACCOUNT_ID,ORG_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryComm.getCommId());
		ps.setString(2, diaryComm.getCommPid());
		ps.setString(3, diaryComm.getCommContect());
		ps.setString(4, diaryComm.getCommTime());
		ps.setString(5, diaryComm.getDiaryId());
		ps.setString(6, diaryComm.getAccountId());
		ps.setString(7, diaryComm.getOrgId());
		int i=ps.executeUpdate();
		JSONObject data=new JSONObject();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "评论成功");
			data.accumulate("time", new Date().getTime()/1000);
			data.accumulate("id", diaryComm.getCommId());
			datajson.accumulate("data", data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "评论失败");
			data.accumulate("time", new Date().getTime()/1000);			
			datajson.accumulate("data", data);
		}
		ps.close();
		return datajson.toString();
	}
	/**
	 * 添加日志
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public String addDailyRecordLogic(Connection conn,Diary dr)throws Exception{
		JSONObject datajson=new JSONObject();
		DiaryFitLogic diaryFitLogic=new DiaryFitLogic();
		int fit=diaryFitLogic.getlockDayLogic(conn, dr.getOrgId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag=true;
		Date titletime=sdf.parse(dr.getDiaryTitleDatetime());
		Date mydate=sdf.parse(SysTool.getCurDateTimeStr("yyyy-MM-dd"));
		String Prompt="";
		if(titletime.getTime()<=mydate.getTime()){
			if(titletime.getTime()>=mydate.getTime()-fit*24*60*60*1000 || fit==0){
					
				}else{
						flag=false;
						String ptime=SysTool.getDateTimeStr(new Date(mydate.getTime()-fit*24*60*60*1000));
						Prompt="日期必须大于"+ptime.substring(0,10);
			}
			
		}else{
			flag=false;
			Prompt="日期不可大于当前日期";
		}
		JSONObject data=new JSONObject();
		if(flag){
		String queryStr="SELECT SHARE_STATUS FROM DIARY_FIT WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dr.getOrgId());
		ResultSet rs=ps.executeQuery();
		String shareStatus="";
		if(rs.next()){
			shareStatus=rs.getString("SHARE_STATUS");
		}
		queryStr="INSERT INTO DIARY (ACCOUNT_ID,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,ORG_ID,DIARY_TITLETIME,SHARE_PRIV,SHARE_RANGE,LAUD,LAUD_NUM)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, dr.getAccountId());
		ps.setString(2, dr.getDiaryName());
		ps.setString(3, dr.getDiaryContent());
		ps.setString(4, dr.getDiaryAnnex());
		ps.setString(5, dr.getDiaryDatetime());
		ps.setInt(6, dr.getDiaryMold());
		ps.setString(7, dr.getDiaryId());
		ps.setString(8,dr.getOrgId());
		ps.setString(9, dr.getDiaryTitleDatetime());
		ps.setString(10, dr.getSharePriv());
		ps.setString(11, shareStatus);
		ps.setString(12, dr.getLaud());
		ps.setInt(13, dr.getLaudNum());
		int i=ps.executeUpdate();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "添加成功");
			data.accumulate("time", new Date().getTime()/1000);
			data.accumulate("id", dr.getDiaryId());
			datajson.accumulate("data", data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "添加失败");
			data.accumulate("time", new Date().getTime()/1000);			
			datajson.accumulate("data", data);
		}
		rs.close();
		ps.close();
		}else{
			datajson.accumulate("status_code", "520");
			datajson.accumulate("msg", Prompt);
			data.accumulate("time", new Date().getTime()/1000);			
			datajson.accumulate("data", data);
		}
		return datajson.toString();
	}
	/**
	 * 日志评论列表
	 * Time 2015-11-04
	 * Author Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String dailyRecordCommentsLogic(Connection conn,String diaryId,String orgId,HttpServletRequest request)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.USER_NAME AS USER_NAME ,T2.HEAD_IMG AS HEAD_IMG FROM DIARY_COMMENTS T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T1.ORG_ID=? AND DIARY_ID=? ORDER BY T1.ID ASC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, diaryId);
		ResultSet rs=ps.executeQuery();
		JSONArray listArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("commId", rs.getString("COMM_ID"));
			if(rs.getString("COMM_PID")==null||rs.getString("COMM_PID").equals("")){
				json.accumulate("commPid", "0");	
			}else{
				json.accumulate("commPid", rs.getString("COMM_PID"));
			}
			json.accumulate("commContect", rs.getString("COMM_CONTECT"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			if(rs.getString("HEAD_IMG")==null||rs.getString("HEAD_IMG").equals("")){
				json.accumulate("headImg", "");
			}else{
				json.accumulate("headImg", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
			}
			json.accumulate("time", rs.getString("COMM_TIME"));
			listArr.add(json);
		}
		JSONArray listjson=new JSONArray();
		for (int i = 0; i < listArr.size(); i++) {
			if(listArr.getJSONObject(i).getString("commPid").equals("0")){
				JSONArray sonArr=new JSONArray();
				JSONObject sonjson=new JSONObject();
				sonjson.accumulate("commentId", listArr.getJSONObject(i).getString("commId"));
				if(listArr.getJSONObject(i).getString("commPid")==null||listArr.getJSONObject(i).getString("commPid").equals("")){
					sonjson.accumulate("commentPid", "0");
				}else{
					sonjson.accumulate("commentPid", listArr.getJSONObject(i).getString("commPid"));
				}
				sonjson.accumulate("content",listArr.getJSONObject(i).getString("commContect"));
				sonjson.accumulate("conmentatorId", listArr.getJSONObject(i).getString("accountId"));
				sonjson.accumulate("commentator", listArr.getJSONObject(i).getString("userName"));
				sonjson.accumulate("avatar", listArr.getJSONObject(i).getString("headImg"));
				sonjson.accumulate("time", listArr.getJSONObject(i).getString("time"));
				sonArr.add(sonjson);
				sonArr=this.commLogic(listArr.getJSONObject(i).getString("commId"), listArr, sonArr);
				listjson.add(sonArr);
			}
		}
		datajson.accumulate("status_code", "200");
		datajson.accumulate("msg", "获取评论列表成功");
		JSONObject data=new JSONObject();
		data.accumulate("time",new Date().getTime()/1000 );
		data.accumulate("list", listjson);
		datajson.accumulate("data",data);
		rs.close();
		ps.close();
		return datajson.toString();
	}
	public JSONArray commLogic(String commid,JSONArray listArr,JSONArray sonArr)throws Exception{
		for (int i = 0; i < listArr.size(); i++) {
			if(commid.equals(listArr.getJSONObject(i).getString("commPid"))&& !listArr.getJSONObject(i).getString("commPid").equals("0")){
				JSONObject sonjson=new JSONObject();
				sonjson.accumulate("commentId", listArr.getJSONObject(i).getString("commId"));
				if(listArr.getJSONObject(i).getString("commPid")==null||listArr.getJSONObject(i).getString("commPid").equals("")){
					sonjson.accumulate("commentPid", "0");
				}else{
					sonjson.accumulate("commentPid", listArr.getJSONObject(i).getString("commPid"));
				}
				sonjson.accumulate("content",listArr.getJSONObject(i).getString("commContect"));
				sonjson.accumulate("conmentatorId", listArr.getJSONObject(i).getString("accountId"));
				sonjson.accumulate("commentator", listArr.getJSONObject(i).getString("userName"));
				sonjson.accumulate("avatar", listArr.getJSONObject(i).getString("headImg"));
				sonjson.accumulate("time", listArr.getJSONObject(i).getString("time"));
				sonArr.add(sonjson);
				sonArr=this.commLogic(listArr.getJSONObject(i).getString("commId"), listArr, sonArr);
			}
		}
		return sonArr;
	}
}
