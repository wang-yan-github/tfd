package tfd.system.mobile.calendar.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import calendar.data.Calendar;

import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.Diary;
import diary.logic.DiaryLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

public class CalendarLogic {
	/**
	 * 获取月日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param date
	 * @param userId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getDayByMonth(Connection conn, String token,String version,String platform,String date,String userId,Account account)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String days = "";
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
		int dayNum = c.getActualMaximum(java.util.Calendar.DATE);
		for (int i = 1; i <= dayNum; i++) {
			String time = "";
			if(i<10){
				time = date.substring(0,4)+"-"+date.substring(5,7)+"-0"+i;
			}else{
				time = date.substring(0,4)+"-"+date.substring(5,7)+"-"+i;
			}
			if(this.toDayIsCal(conn, time, userId,account.getOrgId())){
				days += i + ",";
			}
		}
		if(!days.equals("")){
			days = days.substring(0,days.length()-1);
		}
		json.accumulate("time",new Date().getTime()/1000);
		json.accumulate("days", days);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "获取成功");
		returnJson.accumulate("data", json);
		return returnJson.toString();
	}
	
	/**
	 * 判断当天是否有日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public boolean toDayIsCal(Connection conn,String date,String userId,String orgId)throws Exception{
		boolean flag = false;
		String sql = "SELECT CALENDAR_ID FROM CALENDAR WHERE START_DATE LIKE ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, date+"%");
		ps.setString(2, userId);
		ps.setString(3, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取当天的日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param date
	 * @param userId
	 * @param account
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getCalendarByDay(Connection conn, String token,String version,String platform,String date,String userId,Account account,HttpServletRequest request)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT CALENDAR_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS,AFFAIR_ID FROM CALENDAR WHERE START_DATE LIKE ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, date+"%");
		ps.setString(2, userId);
		ps.setString(3, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("id",rs.getString("CALENDAR_ID") );
			json.accumulate("userId", rs.getString("FROM_ID"));
			String fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
			json.accumulate("name",fromName);
			String avatar_url = SystemUtil.getHeadImgByAccountId(conn, rs.getString("FROM_ID"), request);
			json.accumulate("avatar_url",avatar_url );
			json.accumulate("date",rs.getString("START_DATE") );
			json.accumulate("content",rs.getString("CONTENT") );
			json.accumulate("priority",rs.getString("CAL_LEVEL"));
			if(rs.getString("STATUS").equals("1")){
				json.accumulate("isFinish", "1");
			}else{
				json.accumulate("isFinish", "0");
			}
			//json = this.CheckPriv(json, rs.getString("FROM_ID"), userId, account.getAccountId(), rs.getString("STATUS"), rs.getString("AFFAIR_ID"));
			jsonArr.add(json);
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "获取成功");
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("list",jsonArr );
		returnJson.accumulate("data", jsonObj);
		return returnJson.toString();
	}
	
	/**
	 * 根据类型获取日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param type
	 * @param userId
	 * @param account
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getCalendarByType(Connection conn, String token,String version,String platform,String type,String userId,Account account,String page,HttpServletRequest request)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		if(type.equals("2")){
			jsonObj = this.getFromMeCalendar(conn,jsonObj, account.getAccountId(), account.getOrgId(), page,request);
		}else if(type.equals("1")){
			jsonObj = this.getForMeCalendar(conn,jsonObj, account.getAccountId(), account.getOrgId(), page,request);
		}else if(type.equals("3")){
			jsonObj = this.getPublicCalendar(conn,jsonObj, account.getAccountId(), account.getOrgId(), page,request);
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "获取成功");
		returnJson.accumulate("data", jsonObj);
		return returnJson.toString();
	}
	
	/**
	 * 获取我安排的日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param jsonObj
	 * @param userId
	 * @param orgId
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFromMeCalendar(Connection conn,JSONObject jsonObj,String userId,String orgId,String page,HttpServletRequest request)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String limitNum = (Integer.parseInt(page)*15)+"";
		String limitNum2 = ((Integer.parseInt(page)*15)+16)+"";
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT CALENDAR_ID,ACCOUNT_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS,AFFAIR_ID FROM CALENDAR WHERE FROM_ID = ? AND FROM_ID != ACCOUNT_ID AND CAL_TYPE != 2 AND ORG_ID = ? ORDER BY START_DATE DESC LIMIT "+limitNum+", 16";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.* FROM (SELECT CALENDAR_ID,ACCOUNT_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS,AFFAIR_ID FROM CALENDAR WHERE FROM_ID = ? AND FROM_ID != ACCOUNT_ID AND CAL_TYPE != 2 AND ORG_ID = ? ORDER BY START_DATE DESC) t1 WHERE RN > "+limitNum+" AND RN <= "+limitNum2;
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		int i = 1;
		boolean flag = false;
		while(rs.next()){
			if(i!=16){
				JSONObject json = new JSONObject();
				json.accumulate("id",rs.getString("CALENDAR_ID") );
				json.accumulate("userId", rs.getString("ACCOUNT_ID"));
				String name = new AccountLogic().getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
				json.accumulate("name",name);
				String avatar_url = SystemUtil.getHeadImgByAccountId(conn, rs.getString("ACCOUNT_ID"), request);
				json.accumulate("avatar_url",avatar_url );
				json.accumulate("date",rs.getString("START_DATE") );
				json.accumulate("content",rs.getString("CONTENT") );
				json.accumulate("priority",rs.getString("CAL_LEVEL"));
				if(rs.getString("STATUS").equals("1")){
					json.accumulate("isFinish", "1");
				}else{
					json.accumulate("isFinish", "0");
				}
				//json = this.CheckPriv(json, rs.getString("FROM_ID"), userId, userId, rs.getString("STATUS"), rs.getString("AFFAIR_ID"));
				jsonArr.add(json);
			}else{
				flag = true;
			}
			i++;
		}
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("page", page);
		if(flag){
			jsonObj.accumulate("havemore", "1");
		}else{
			jsonObj.accumulate("havemore", "0");
		}
		jsonObj.accumulate("list",jsonArr );
		return jsonObj;
	}
	
	/**
	 * 获取安排给我的日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param jsonObj
	 * @param userId
	 * @param orgId
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getForMeCalendar(Connection conn,JSONObject jsonObj,String userId,String orgId,String page,HttpServletRequest request)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String limitNum = (Integer.parseInt(page)*15)+"";
		String limitNum2 = ((Integer.parseInt(page)*15)+16)+"";
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT CALENDAR_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS FROM CALENDAR WHERE ACCOUNT_ID = ? AND ACCOUNT_ID != FROM_ID  AND CAL_TYPE != 2 AND ORG_ID = ? ORDER BY START_DATE DESC LIMIT "+limitNum+", 16";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.* FROM (SELECT CALENDAR_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS FROM CALENDAR WHERE ACCOUNT_ID = ? AND ACCOUNT_ID != FROM_ID  AND CAL_TYPE != 2 AND ORG_ID = ? ORDER BY START_DATE DESC) t1 WHERE RN > "+limitNum+" AND RN <= "+limitNum2;
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		int i = 1;
		boolean flag = false;
		while(rs.next()){
			if(i!=16){
				JSONObject json = new JSONObject();
				json.accumulate("id",rs.getString("CALENDAR_ID") );
				json.accumulate("userId", rs.getString("FROM_ID"));
				String name = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
				json.accumulate("name",name);
				String avatar_url = SystemUtil.getHeadImgByAccountId(conn, rs.getString("FROM_ID"), request);
				json.accumulate("avatar_url",avatar_url );
				json.accumulate("date",rs.getString("START_DATE") );
				json.accumulate("content",rs.getString("CONTENT") );
				json.accumulate("priority",rs.getString("CAL_LEVEL"));
				if(rs.getString("STATUS").equals("1")){
					json.accumulate("isFinish", "1");
				}else{
					json.accumulate("isFinish", "0");
				}
				//json = this.CheckPriv(json, rs.getString("FROM_ID"), userId, userId, rs.getString("STATUS"), rs.getString("AFFAIR_ID"));
				jsonArr.add(json);
			}else{
				flag = true;
			}
			i++;
		}
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("page", page);
		if(flag){
			jsonObj.accumulate("havemore", "1");
		}else{
			jsonObj.accumulate("havemore", "0");
		}
		jsonObj.accumulate("list",jsonArr );
		return jsonObj;
	}
	
	/**
	 * 获取公共日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param jsonObj
	 * @param userId
	 * @param orgId
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPublicCalendar(Connection conn,JSONObject jsonObj,String userId,String orgId,String page,HttpServletRequest request)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String limitNum = (Integer.parseInt(page)*15)+"";
		String limitNum2 = ((Integer.parseInt(page)*15)+16)+"";
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT CALENDAR_ID,ACCOUNT_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS,AFFAIR_ID FROM CALENDAR WHERE CAL_TYPE = 3  AND ORG_ID = ? ORDER BY START_DATE DESC LIMIT "+limitNum+", 16";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.* FROM (SELECT CALENDAR_ID,ACCOUNT_ID,FROM_ID,START_DATE,CONTENT,CAL_LEVEL,STATUS,AFFAIR_ID FROM CALENDAR WHERE CAL_TYPE = 3  AND ORG_ID = ? ORDER BY START_DATE DESC) t1 WHERE RN > "+limitNum+" AND RN <= "+limitNum2;
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		int i = 1;
		boolean flag = false;
		while(rs.next()){
			if(i!=16){
				JSONObject json = new JSONObject();
				json.accumulate("id",rs.getString("CALENDAR_ID") );
				json.accumulate("userId", rs.getString("ACCOUNT_ID"));
				String name = new AccountLogic().getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
				json.accumulate("name",name);
				String avatar_url = SystemUtil.getHeadImgByAccountId(conn, rs.getString("ACCOUNT_ID"), request);
				json.accumulate("avatar_url",avatar_url );
				json.accumulate("date",rs.getString("START_DATE") );
				json.accumulate("content",rs.getString("CONTENT") );
				json.accumulate("priority",rs.getString("CAL_LEVEL"));
				if(rs.getString("STATUS").equals("1")){
					json.accumulate("isFinish", "1");
				}else{
					json.accumulate("isFinish", "0");
				}
				//json = this.CheckPriv(json, rs.getString("FROM_ID"), userId, userId, rs.getString("STATUS"), rs.getString("AFFAIR_ID"));
				jsonArr.add(json);
			}else{
				flag = true;
			}
			i++;
		}
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("page", page);
		if(flag){
			jsonObj.accumulate("havemore", "1");
		}else{
			jsonObj.accumulate("havemore", "0");
		}
		jsonObj.accumulate("list",jsonArr );
		return jsonObj;
	}
	
	/**
	 * 获取日程详情
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param id
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getCalendarById(Connection conn, String token,String version,String platform,String id,Account account)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "SELECT CALENDAR_ID,CONTENT,STATUS,FROM_ID,START_DATE,END_DATE,BEFORE_TIME,CAL_TYPE,USER_ID,CAL_LEVEL,DIARY_ID,AFFAIR_ID,ACCOUNT_ID FROM CALENDAR WHERE CALENDAR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("time", new Date().getTime()/1000);
			json.accumulate("content", rs.getString("CONTENT"));
			String diaryStr = new DiaryLogic().getIdDiaryLogic(conn, rs.getString("DIARY_ID"), account);
			String completion = "";
			if(!diaryStr.equals("{}")){
				completion = JSONObject.fromObject(diaryStr).getString("diaryContent");
			}
			String state = "";
			if(rs.getString("STATUS").equals("1")){
				state = "1";
			}else{
				state = "0";
			}
			json.accumulate("completion", completion);
			json.accumulate("state", state);
			String name = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
			json.accumulate("creater", name);
			json.accumulate("createrid", rs.getString("FROM_ID"));
			json.accumulate("begindate", rs.getString("START_DATE"));
			json.accumulate("enddate", rs.getString("END_DATE"));
			json.accumulate("remind", rs.getString("BEFORE_TIME"));
			json.accumulate("type", rs.getString("CAL_TYPE"));
			String userid=rs.getString("USER_ID");
			userid=userid==null?"":userid;
			String partner = this.getPartnerList(conn, userid);
			json.accumulate("partner", partner);
			json.accumulate("priority", rs.getString("CAL_LEVEL"));
			json = this.CheckPriv(json, rs.getString("FROM_ID"), rs.getString("USER_ID"), account.getAccountId(),rs.getString("ACCOUNT_ID"), rs.getString("STATUS"), rs.getString("AFFAIR_ID"));
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "获取成功");
		returnJson.accumulate("data", json);
		return returnJson.toString();
	}
	
	/**
	 * 获取参与人
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String getPartnerList(Connection conn,String userId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		if(userId.indexOf(",")>-1){
			String userIds[] = userId.split(",");
			for (int i = 0; i < userIds.length; i++) {
				JSONObject json = new JSONObject();
				String name = new AccountLogic().getUserNameStr(conn, userIds[i]);
				json.accumulate("id", userIds[i]);
				json.accumulate("name", name);
				jsonArr.add(json);
			}
		}else{
			JSONObject json = new JSONObject();
			String name = new AccountLogic().getUserNameStr(conn, userId);
			json.accumulate("id", userId);
			json.accumulate("name", name);
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
	

	/**
	 * 添加日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public String addCalendar(Connection conn, String token, String version,String platform, Calendar cl)throws Exception{
		JSONObject returnJson = new JSONObject();
		String sql = "INSERT INTO CALENDAR(CALENDAR_ID,CALENDAR_PID,START_DATE,END_DATE,CONTENT,CAL_TYPE,CAL_LEVEL,BEFORE_TIME,ACCOUNT_ID,USER_ID,FROM_ID,STATUS,AFFAIR_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cl.getCalendarId());
		ps.setString(2, cl.getCalendarPid());
		ps.setString(3, cl.getStartDate());
		ps.setString(4,cl.getEndDate());
		ps.setString(5, cl.getContent());
		ps.setString(6, cl.getCalType());
		ps.setString(7, cl.getCalLevel());
		ps.setString(8, cl.getBeforeTime());
		ps.setString(9, cl.getAccountId());
		ps.setString(10, cl.getUserId());
		ps.setString(11, cl.getFromId());
		ps.setString(12, cl.getStatus());
		ps.setString(13, "");
		ps.setString(14, cl.getOrgId());
		int i = ps.executeUpdate();
		if(i>0){ 
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "添加成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "添加失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		return returnJson.toString();
	}
	
	/**
	 * 验证权限
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param json
	 * @param fromId
	 * @param userId
	 * @param accountId
	 * @param status
	 * @param affairId
	 * @return
	 * @throws Exception
	 */
	public JSONObject CheckPriv(JSONObject json,String fromId,String userId,String id,String accountId,String status,String affairId)throws Exception{
		if(fromId.equals(id)){
			if(userId.indexOf(id)>-1){
				if(id.equals(accountId)){
					//自己的日程
					json.accumulate("editable", "1");
					json.accumulate("finishable", "1");
				}else{
					//自己的参与人的日程
					json.accumulate("editable", "1");
					json.accumulate("finishable", "0");
				}
			}else{
				//我给别人的日程
				if(status.equals("1")){
					//已完成
					json.accumulate("editable", "0");
					json.accumulate("finishable", "0");
				}else{
					//未完成
					json.accumulate("editable", "1");
					json.accumulate("finishable", "0");
				}
			}
		}else{
			if(userId.indexOf(id)>-1){
				if(id.equals(accountId)){
					//别人给我的日程
					json.accumulate("editable", "0");
					json.accumulate("finishable", "1");
				}else{
					//别人给别人的日程
					json.accumulate("editable", "0");
					json.accumulate("finishable", "0");
				}
			}else{
				//公开日程
				json.accumulate("editable", "0");
				json.accumulate("finishable", "0");
			}
		}
		return json;
	}
	
	/**
	 * 判断该日程是否存在
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param calendarPid
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(Connection conn,String accountId,String id,String fromId)throws Exception{
		boolean flag = false;
		String pId = this.isFrom(conn, id, fromId);
		String sql = "SELECT CALENDAR_ID FROM CALENDAR WHERE CALENDAR_PID = ? AND ACCOUNT_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pId);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}
		ps.close();
		rs.close();
		return flag;
	}

	/**
	 * 修改日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public String editCalendar(Connection conn, String token, String version,String platform, Calendar cl)throws Exception {
		JSONObject returnJson = new JSONObject();
		String calendarPid = this.isFrom(conn, cl.getCalendarId(), cl.getAccountId());
		if(calendarPid.equals("")){
			returnJson.accumulate("status_code", "520");
		    returnJson.accumulate("msg", "权限缺失");
		    JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
			returnJson.accumulate("data", json);
		}else{
			String sql = "UPDATE CALENDAR SET START_DATE = ?,END_DATE = ?,CONTENT = ?,CAL_TYPE = ?,CAL_LEVEL = ?,BEFORE_TIME = ?,USER_ID = ? WHERE CALENDAR_PID = ? AND ORG_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cl.getStartDate());
			ps.setString(2, cl.getEndDate());
			ps.setString(3, cl.getContent());
			ps.setString(4, cl.getCalType());
			ps.setString(5, cl.getCalLevel());
			ps.setString(6, cl.getBeforeTime());
			ps.setString(7, cl.getUserId());
			ps.setString(8, calendarPid);
			ps.setString(9, cl.getOrgId());
			int i = ps.executeUpdate();
			if(i>0){ 
				returnJson.accumulate("status_code", "200");
			    returnJson.accumulate("msg", "修改成功");
			}else{
				returnJson.accumulate("status_code", "500");
			    returnJson.accumulate("msg", "修改失败");
			}
			JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
			returnJson.accumulate("data", json);
		}
		return returnJson.toString();
	}
	
	/**
	 * 获取日程日志Id
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param calendarId
	 * @return
	 * @throws Exception
	 */
	public String getDirayId(Connection conn,String calendarId)throws Exception{
		String returnData = "";
		String sql = "SELECT DIARY_ID FROM CALENDAR WHERE CALENDAR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, calendarId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("DIARY_ID");
		}
		return returnData;
	}
	
	/**
	 * 添加日程日志
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param diaryId
	 * @param content
	 * @param calendarId
	 * @throws Exception
	 */
	public void addCalendarDiary(Connection conn,Account account,String diaryId,String content,String calendarId)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Diary dr=new Diary();
		
		DiaryLogic dl=new DiaryLogic();
		dr.setAccountId(account.getAccountId());
		dr.setDiaryDatetime(formatter.format(new Date()));
		dr.setDiaryMold(0);
		dr.setDiaryName(formatter.format(new Date()).substring(0,10)+"完成的日程日志");
		dr.setDiaryContent(content);
		dr.setOrgId(account.getOrgId());
		dr.setDiaryAnnex("");
		dr.setDiaryTitleDatetime(formatter.format(new Date()).substring(0,10));
		String id;
		if(SysTool.isNullorEmpty(diaryId)){
			id = GuId.getGuid();
			dr.setDiaryId(id);
			dl.addDiaryLogic(conn,dr);
			this.addDiary(conn, calendarId, id);
		}else{
			dr.setDiaryId(diaryId);
			dl.updateDiaryLogic(conn, dr);
		}
	}
	
	/**
	 * 添加日志
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param sid
	 * @param diaryId
	 * @return
	 * @throws Exception
	 */
	public int addDiary(Connection conn,String sid,String diaryId)throws Exception{
		String sql = "UPDATE CALENDAR SET DIARY_ID = ? WHERE CALENDAR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, diaryId);
		ps.setString(2, sid);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 修改日程状态
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	public String updateState(Connection conn,String id,String state)throws Exception{
		JSONObject returnJson = new JSONObject();
		String sql = "UPDATE CALENDAR SET STATUS = ? WHERE CALENDAR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, state);
		ps.setString(2, id);
		int i = ps.executeUpdate();
		if(i>0){ 
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "修改成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "修改失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}

	/**
	 * 根据Id删除日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param conn
	 * @param token
	 * @param version
	 * @param platform
	 * @param id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String delCalendarById(Connection conn, String token,String version, String platform, String id, String orgId,String accountId)throws Exception {
		JSONObject returnJson = new JSONObject();
		String pId = this.isFrom(conn, id, accountId);
		if(pId.equals("")){
			returnJson.accumulate("status_code", "520");
		    returnJson.accumulate("msg", "权限缺失");
		    JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
			returnJson.accumulate("data", json);
		}else{
			String sql = "DELETE FROM CALENDAR WHERE CALENDAR_PID = ? AND ORG_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,pId);
			ps.setString(2,orgId);
			int i = ps.executeUpdate();
			if(i>0){ 
				returnJson.accumulate("status_code", "200");
			    returnJson.accumulate("msg", "删除成功");
			}else{
				returnJson.accumulate("status_code", "500");
			    returnJson.accumulate("msg", "删除失败");
			}
			JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
			returnJson.accumulate("data", json);
		}
		return returnJson.toString();
	}
	
	/**
	 * 验证是否为安排人
	 * Time:2015-12-01
	 * Author:Yzz
	 * @param conn
	 * @param calendarId
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public String isFrom(Connection conn,String calendarId,String accountId)throws Exception{
		String returnData = "";
		String sql = "SELECT CALENDAR_PID FROM CALENDAR WHERE CALENDAR_ID = ? AND FROM_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, calendarId);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("CALENDAR_PID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
}
