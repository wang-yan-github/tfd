package calendar.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.component.datamodel.Record;
import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;
import calendar.data.Affair;
import calendar.data.Calendar;
import calendar.util.AffairServer;

public class CalendarLogic {
	/**
	 * 添加或修改日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param cl
	 * @param isAdd
	 * @return
	 * @throws SQLException
	 */
	public String addOrUpdateLogic(Connection conn,Calendar cl,String isAdd) throws Exception
	{
		PreparedStatement ps = null;
		String queryStr = null;
		if(isAdd.equals("1")){
			queryStr="INSERT INTO CALENDAR (CALENDAR_ID,CALENDAR_PID,START_DATE,END_DATE,CONTENT,CAL_TYPE,CAL_LEVEL,CAL_AFF_TYPE,BEFORE_TIME,ACCOUNT_ID,USER_ID,FROM_ID,IS_SMS,STATUS,FROM_TYPE,FROM_TYPE_ID,AFFAIR_ID,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, cl.getCalendarId());
			ps.setString(2, cl.getCalendarPid());
			ps.setString(3, cl.getStartDate());
			ps.setString(4,cl.getEndDate());
			ps.setString(5, cl.getContent());
			ps.setString(6, cl.getCalType());
			ps.setString(7, cl.getCalLevel());
			ps.setString(8, cl.getCalAffType());
			ps.setString(9, cl.getBeforeTime());
			ps.setString(10, cl.getAccountId());
			ps.setString(11, cl.getUserId());
			ps.setString(12, cl.getFromId());
			ps.setString(13, cl.getIsSms());
			ps.setString(14, cl.getStatus());
			ps.setString(15, cl.getFromType());
			ps.setString(16, cl.getFromTypeId());
			ps.setString(17,cl.getAffairId());
			ps.setString(18, cl.getOrgId());
			ps.executeUpdate();
			ps.close();	
		}else{
			String pId = this.isFrom(conn, cl.getCalendarPid(), cl.getAccountId());
			queryStr="UPDATE CALENDAR SET START_DATE = ?,END_DATE = ?,CONTENT = ?,CAL_TYPE = ?,CAL_LEVEL = ?,CAL_AFF_TYPE = ?,BEFORE_TIME = ?,USER_ID = ?,FROM_ID = ?,IS_SMS = ?,STATUS = ?,AFFAIR_ID = ? WHERE CALENDAR_PID = ? AND ORG_ID = ?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, cl.getStartDate());
			ps.setString(2,cl.getEndDate());
			ps.setString(3, cl.getContent());
			ps.setString(4, cl.getCalType());
			ps.setString(5, cl.getCalLevel());
			ps.setString(6, cl.getCalAffType());
			ps.setString(7, cl.getBeforeTime());
			ps.setString(8, cl.getUserId());
			ps.setString(9, cl.getFromId());
			ps.setString(10, cl.getIsSms());
			ps.setString(11, cl.getStatus());
			ps.setString(12, cl.getAffairId());
			ps.setString(13, pId);
			ps.setString(14, cl.getOrgId());
			ps.executeUpdate();
			ps.close();	
		}
		JSONObject json = new JSONObject();
		DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
		DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
		String endStr="";
		String startStr="";
		try {
			endStr = endd.parse(cl.getEndDate()).toString();
			startStr= startd.parse(cl.getStartDate()).toString();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(endStr.equals(startStr))
		{
			json.accumulate("allDay", false);
		}else
		{
			json.accumulate("allDay", true);	
		}
		json.accumulate("id",cl.getCalendarId());
		json.accumulate("start",cl.getStartDate());
		json.accumulate("startTime", cl.getStartDate());
		json.accumulate("end", cl.getEndDate());
		json.accumulate("endTime", "");
		json.accumulate("title", cl.getContent());
		json.accumulate("status", "0");
		json.accumulate("type", "calendar");
		json.accumulate("deleteable", true);
		json.accumulate("editable", true);
		json.accumulate("className", "fc-event-color1");
		if(cl.getUserId()!=null){
			if(cl.getUserId().indexOf(cl.getAccountId())>-1){
				String fromName = new AccountLogic().getUserNameStr(conn, cl.getFromId());
				json.accumulate("fromName", "安排人："+fromName);
			}else{

				json.accumulate("fromName", "    ");
			}
		}else{
			json.accumulate("fromName", "    ");
		}
		JSONArray jsonArr = new JSONArray();
		jsonArr.add(json);
		ps.close();
		return jsonArr.toString();
	}
	
	public void addAffair(Connection conn,Affair affair)throws Exception{
		String sql = "INSERT INTO AFFAIR(AFFAIR_ID,CALENDAR_ID,CAL_AFF_TYPE,REMIND_TYPE,REMIND_DATE,REMIND_TIME,END_WHILE,FREQUENCY,IS_WEEK,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, affair.getAffairId());
		ps.setString(2, affair.getCalendarId());
		ps.setString(3, affair.getCalAffType());
		ps.setString(4, affair.getRemindType());
		ps.setString(5, affair.getRemindDate());
		ps.setString(6, affair.getRemindTime());
		ps.setString(7, affair.getEndWhile());
		ps.setString(8, affair.getFrequency());
		ps.setString(9, affair.getIsWeek());
		ps.setString(10, affair.getOrgId());
		
		ps.executeUpdate();
		ps.close();
	}
	
	public void updateAffair(Connection conn,Affair affair)throws Exception{
		String sql = "UPDATE AFFAIR SET REMIND_TYPE = ?,REMIND_DATE = ?,REMIND_TIME = ?,END_WHILE = ?,FREQUENCY = ?,IS_WEEK = ? WHERE AFFAIR_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, affair.getRemindType());
		ps.setString(2, affair.getRemindDate());
		ps.setString(3, affair.getRemindTime());
		ps.setString(4, affair.getEndWhile());
		ps.setString(5, affair.getFrequency());
		ps.setString(6, affair.getIsWeek());
		ps.setString(7, affair.getAffairId());
		ps.setString(8, affair.getOrgId());
		
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 查询时间段内的自己的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param account
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public String selectByTimeLogic(Connection conn,Account account,String startTime,String endTime) throws Exception
	{
		updateStatus(conn,startTime,endTime);
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID,AFFAIR_ID FROM CALENDAR WHERE ORG_ID = ? AND ACCOUNT_ID=? AND START_DATE > ? AND END_DATE <= ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, account.getAccountId());
		ps.setString(3, startTime);
		ps.setString(4, endTime);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json  = new JSONObject();
			DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
			String endStr="";
			String startStr="";
			try {
				endStr = endd.parse(rs.getString("END_DATE")).toString();
				startStr= startd.parse(rs.getString("START_DATE")).toString();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String str1 = rs.getString("END_DATE").toString().substring(11,16);
			String str2 = rs.getString("START_DATE").toString().substring(11,16);
			boolean allDay = false;
			if(!endStr.equals(startStr)||(str1.equals("23:59")&&str2.equals("00:00")))
			{
				allDay = true;
			}else
			{
				allDay = false;	
			}
			json.accumulate("allDay", allDay);
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("className", "fc-event-color1");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("className", "fc-event-color");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("className", "fc-event-color3");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("className", "fc-event-color4");
			}
			String endDate = rs.getString("END_DATE");
			String startDate = rs.getString("START_DATE");
			String calendarId = rs.getString("CALENDAR_ID");
			String content = rs.getString("CONTENT");
			json.accumulate("end", endDate);
			json.accumulate("id",calendarId);
			json.accumulate("start",startDate);
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("title", content);
			json.accumulate("type", "calendar");
			String fromName = "";
			if(!rs.getString("FROM_ID").equals(account.getAccountId())){
				json.accumulate("deleteable", false);
				json.accumulate("editable", false);
				fromName = "安排人："+new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
			}else{
				if(rs.getString("USER_ID")!=null){
					if(rs.getString("USER_ID").indexOf(account.getAccountId())>-1&&!account.getAccountId().equals(rs.getString("ACCOUNT_ID"))){
						json.accumulate("deleteable", false);
						json.accumulate("editable", false);
						fromName = "安排人："+new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
					}else{
						json.accumulate("deleteable", true);
						json.accumulate("editable", true);
						fromName = "    ";
					}
				}else{
					json.accumulate("deleteable", true);
					json.accumulate("editable", true);
					fromName = "    ";
				}
			}
			json.accumulate("fromName", fromName);
			json.accumulate("finishable", true);
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		jsonArr = this.getAffairCalendar(conn, jsonArr, account.getAccountId(), account.getOrgId(),"1");
		return jsonArr.toString();
	}
	/**
	 * 更新日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param startDate
	 * @param endDate
	 * @param sid
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String updateChangeCalLogic(Connection conn, String startDate,String endDate, String sid,String orgId) throws Exception{
		String queryStr = "";
		PreparedStatement ps = null;
		//if(CheckStatus(conn, sid)){
			queryStr="UPDATE CALENDAR SET START_DATE=?,END_DATE=? WHERE CALENDAR_PID=? AND ORG_ID = ?";
			ps =conn.prepareStatement(queryStr);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setString(3, sid);
			ps.setString(4, orgId);
			ps.executeUpdate();
		//}
		JSONArray jsonArr = new JSONArray();
		queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS FROM CALENDAR WHERE CALENDAR_ID=? AND ORG_ID = ? ";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, sid);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json  = new JSONObject();
			DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
			String endStr="";
			String startStr="";
			try {
				endStr = endd.parse(rs.getString("END_DATE")).toString();
				startStr= startd.parse(rs.getString("START_DATE")).toString();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String str1 = rs.getString("END_DATE").toString().substring(11,16);
			String str2 = rs.getString("START_DATE").toString().substring(11,16);
			if(!endStr.equals(startStr)||(str1.equals("23:59")&&str2.equals("00:00")))
			{
				json.accumulate("allDay", true);
			}else
			{
				json.accumulate("allDay", false);	
			}
			json.accumulate("id",rs.getString("CALENDAR_ID"));
			json.accumulate("start",rs.getString("START_DATE"));
			json.accumulate("startTime", rs.getString("START_DATE"));
			json.accumulate("end", rs.getString("END_DATE"));
			json.accumulate("endTime", "");
			json.accumulate("title", rs.getString("CONTENT"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("type", "calendar");
			json.accumulate("deleteable", true);
			json.accumulate("editable", true);
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("className", "fc-event-color1");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("className", "fc-event-color");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("className", "fc-event-color3");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("className", "fc-event-color4");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 检查日程状态
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param calId
	 * @return
	 * @throws Exception
	 */
	public boolean CheckStatus(Connection conn,String calId)throws Exception{
		boolean flag = false;
		ResultSet rs = null;
		String queryStr = "SELECT STATUS FROM CALENDAR WHERE CALENDAR_ID = ? AND STATUS = '0'";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, calId);
		rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}else{
			flag = false;
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * 自动日程状态
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @throws Exception
	 */
	public void updateStatus(Connection conn,String startDate,String endDate)throws Exception{
		deleteExcess(conn);
		String nd = SysTool.getDateTimeStr(new Date());
		String queryStr = "UPDATE CALENDAR SET STATUS = '3' WHERE START_DATE < ? AND END_DATE > ? AND STATUS != '1'  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nd);
		ps.setString(2, nd);
		ps.executeUpdate();
		queryStr = "UPDATE CALENDAR SET STATUS = '4' WHERE END_DATE < ? AND START_DATE >= ? AND STATUS != '1' ";
		PreparedStatement ps1 = conn.prepareStatement(queryStr);
		ps1.setString(1, nd);
		ps1.setString(2, startDate);
		ps1.executeUpdate();
		queryStr = "UPDATE CALENDAR SET STATUS = '0' WHERE START_DATE > ? AND END_DATE <= ? AND STATUS != '1' ";
		PreparedStatement ps2 = conn.prepareStatement(queryStr);
		ps2.setString(1, nd);
		ps2.setString(2, endDate);
		ps2.executeUpdate();
		ps.close();
		ps1.close();
		ps2.close();
	}
	
	/**
	 * 自动日程状态
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @throws Exception
	 */
	public void deleteExcess(Connection conn)throws Exception{
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
		queryStr = "DELETE FROM CALENDAR WHERE ( LOCATE(ACCOUNT_ID,CONCAT(',',USER_ID,',')) <= 0 )";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr = "DELETE FROM CALENDAR WHERE ( INSTR(CONCAT(',',USER_ID)||',',ACCOUNT_ID) <= 0 )";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 删除日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param calendarId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int deleteCalLogic(Connection conn,String calendarId,String orgId,String accountId) throws Exception
	{
		String pId = this.isFrom(conn, calendarId, accountId);
		String queryStr="DELETE FROM CALENDAR WHERE CALENDAR_PID= ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, pId);
		ps.setString(2, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 更新日程状态
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param calendarId
	 * @param status
	 * @param orgId
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String updateOverStatusLogic(Connection conn,String calendarId,String status,String orgId,Account account) throws SQLException
	{
		String queryStr="UPDATE CALENDAR SET STATUS=? WHERE CALENDAR_ID=? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, status);
		ps.setString(2, calendarId);
		ps.setString(3, orgId);
		ps.executeUpdate();
		queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID FROM CALENDAR WHERE CALENDAR_ID=? AND ORG_ID = ?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, calendarId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		JSONObject json  = new JSONObject();
		while(rs.next())
		{
			DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
			String endStr="";
			String startStr="";
			try {
				endStr = endd.parse(rs.getString("END_DATE")).toString();
				startStr= startd.parse(rs.getString("START_DATE")).toString();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String str1 = rs.getString("END_DATE").toString().substring(11,16);
			String str2 = rs.getString("START_DATE").toString().substring(11,16);
			if(!endStr.equals(startStr)||(str1.equals("23:59")&&str2.equals("00:00")))
			{
				json.accumulate("allDay", true);
			}else
			{
				json.accumulate("allDay", false);	
			}
			json.accumulate("id",rs.getString("CALENDAR_ID"));
			json.accumulate("start",rs.getString("START_DATE"));
			json.accumulate("startTime", rs.getString("START_DATE"));
			json.accumulate("end", rs.getString("END_DATE"));
			json.accumulate("endTime", "");
			json.accumulate("title", rs.getString("CONTENT"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("type", "calendar");
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("className", "fc-event-color1");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("className", "fc-event-color");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("className", "fc-event-color3");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("className", "fc-event-color4");
			}
			if(!rs.getString("FROM_ID").equals(account.getAccountId())){
				json.accumulate("deleteable", false);
				json.accumulate("editable", false);
				String fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
				json.accumulate("fromName", "安排人："+fromName);
			}else{
				if(rs.getString("USER_ID")!=null){
					if(rs.getString("USER_ID").indexOf(account.getAccountId())>-1&&!account.getAccountId().equals(rs.getString("ACCOUNT_ID"))){
						json.accumulate("deleteable", false);
						json.accumulate("editable", false);
						String fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
						json.accumulate("fromName", "安排人："+fromName);
					}else{
						json.accumulate("deleteable", true);
						json.accumulate("editable", true);
						json.accumulate("fromName", "    ");
					}
				}else{
					json.accumulate("deleteable", true);
					json.accumulate("editable", true);
					json.accumulate("fromName", "    ");
				}
			}
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据Id查询日程的详细信息
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param calendarId
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String selectByIdLogic(Connection conn,String calendarId,Account account) throws Exception
	{
		String queryStr="SELECT t1.CALENDAR_ID,t1.START_DATE,t1.END_DATE,t1.CONTENT,t1.CAL_LEVEL,"
				+ "t1.CAL_TYPE,t1.CAL_AFF_TYPE,t1.BEFORE_TIME,t1.ACCOUNT_ID,t1.USER_ID,t1.FROM_ID,"
				+ "t1.IS_SMS,t1.DIARY_ID,t1.STATUS FROM CALENDAR t1 "
				+ "WHERE t1.CALENDAR_ID=? AND t1.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, calendarId);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		JSONObject json  = new JSONObject();
		AccountLogic acclogic=new AccountLogic();
		if(rs.next())
		{
			DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
			String endStr="";
			String startStr="";
			try {
				endStr = endd.parse(rs.getString("END_DATE")).toString();
				startStr= startd.parse(rs.getString("START_DATE")).toString();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String str1 = rs.getString("END_DATE").toString().substring(11,16);
			String str2 = rs.getString("START_DATE").toString().substring(11,16);
			if(!endStr.equals(startStr)||(str1.equals("23:59")&&str2.equals("00:00")))
			{
				json.accumulate("allDay", true);
			}else
			{
				json.accumulate("allDay", false);	
			}
			json.accumulate("id",rs.getString("CALENDAR_ID"));
			json.accumulate("startTimeStr",rs.getString("START_DATE"));
			json.accumulate("startTime", rs.getString("START_DATE"));
			json.accumulate("endTimeStr", rs.getString("END_DATE"));
			json.accumulate("endTime", rs.getString("END_DATE"));
			json.accumulate("title", rs.getString("CONTENT"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("calLevel", rs.getString("CAL_LEVEL"));
			json.accumulate("calType", rs.getString("CAL_TYPE"));
			json.accumulate("calAffType", rs.getString("CAL_AFF_TYPE"));
			json.accumulate("beforeTime", rs.getString("BEFORE_TIME"));
			String UserName = this.getAllName(conn, rs.getString("USER_ID"));
			json.accumulate("userName", UserName);
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("fromId", rs.getString("FROM_ID"));
			String accountName = acclogic.getUserNameStr(conn, rs.getString("FROM_ID"));
			json.accumulate("accountName", accountName);
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("deleteable", true);
			json.accumulate("editable", true);
			
			json.accumulate("isSms", rs.getString("IS_SMS"));
			json.accumulate("editable", true);
			json.accumulate("diaryId", rs.getString("DIARY_ID"));
			if(rs.getString("USER_ID")!=null){
				if(rs.getString("USER_ID").indexOf(account.getAccountId())>-1&&!account.getAccountId().equals(rs.getString("ACCOUNT_ID"))){
					json.accumulate("isAll", "false");
				}else{
					json.accumulate("isAll", "true");
				}
			}else{
				json.accumulate("isAll", "true");
			}
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("className", "fc-event-color1");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("className", "fc-event-color");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("className", "fc-event-color3");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("className", "fc-event-color4");
			}
		}
		queryStr = "SELECT t2.AFFAIR_ID,t2.REMIND_TYPE,t2.REMIND_DATE,t2.REMIND_TIME,"
				+ "t2.END_WHILE,t2.FREQUENCY,t2.IS_WEEK,t2.AFFAIR_ID FROM AFFAIR t2 WHERE t2.CALENDAR_ID = ?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, calendarId);
		rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("remindType", rs.getString("REMIND_TYPE"));
			json.accumulate("frequency", rs.getString("FREQUENCY"));
			json.accumulate("remindDate", rs.getString("REMIND_DATE"));
			json.accumulate("endWhile", rs.getString("END_WHILE"));
			json.accumulate("remindTime", rs.getString("REMIND_TIME"));
			json.accumulate("isWeekend", rs.getString("IS_WEEK"));
			json.accumulate("affairId", rs.getString("AFFAIR_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	public String getAllName(Connection conn,String userId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String [] ids = userId.split(",");
		for (int i = 0; i < ids.length; i++) {
			JSONObject json = new JSONObject();
			
			AccountLogic acclogic=new AccountLogic();
			String UserName = acclogic.getUserNameStr(conn, ids[i]);
			json.accumulate("userId", ids[i]);
			json.accumulate("name", UserName);
			
			
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
	/**
	 * 获取个人日程
	 * Author:Yzz
	 * Time:2015-6-1	
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getPerCalendarJsonLogic(Connection conn,String accountId,String orgId) throws SQLException
	{
		JSONObject returnJson = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS FROM CALENDAR WHERE ACCOUNT_ID=? AND ORG_ID = ?";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("CALENDAR_ID"));
			json.accumulate("startDate", rs.getString("START_DATE"));
			json.accumulate("endDate", rs.getString("END_DATE"));
			json.accumulate("content",rs.getString("CONTENT"));
			json.accumulate("status", rs.getString("STATUS"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		returnJson.accumulate("Rows", jsonArr);
		returnJson.accumulate("Total",jsonArr.size());
		return returnJson.toString();
	}
	/**
	 * 查询别人给我安排的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param pramList
	 * @param searchContent
	 * @param pagesize
	 * @param page
	 * @param storOrder
	 * @param storName
	 * @return
	 * @throws Exception 
	 */
	public String getCalendarFromMeLogic(Connection conn,List<String> pramList,String searchContent,int pagesize,int page,String storOrder,String storName) throws Exception
	{
		String queryStr= "SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.ACCOUNT_ID AND t3.ORG_ID = t2.ORG_ID ) AS USER_NAME,t2.ACCOUNT_ID,USER_ID,IS_SMS,STATUS FROM CALENDAR t2 "
				+ "WHERE FROM_ID = ? AND FROM_ID != ACCOUNT_ID";
		if(!searchContent.equals("")){
			queryStr += " AND ( CONTENT LIKE '%" + searchContent + "%'";
			queryStr += " OR START_DATE LIKE '%" + searchContent + "%'";
			queryStr += " OR END_DATE LIKE '%" + searchContent + "%')";
		}
		queryStr += " AND ORG_ID = ? ";
		String optStr= "[{'function':'updateCal','name':'修改','parm':'CALENDAR_ID'},{'function':'deleteCal','name':'删除','parm':'CALENDAR_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	/**
	 * 查询我给别人安排的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param pramList
	 * @param searchContent
	 * @param pagesize
	 * @param page
	 * @param storOrder
	 * @param storName
	 * @return
	 * @throws Exception 
	 */
	public String getCalendarForMeLogic(Connection conn,List<String> pramList,String searchContent,int pagesize,int page,String storOrder,String storName) throws Exception
	{
		String queryStr= "SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID  ) AS USER_NAME,t2.FROM_ID,USER_ID,IS_SMS,STATUS FROM CALENDAR t2 "
				+ "WHERE t2.ACCOUNT_ID = ?  AND t2.ACCOUNT_ID != t2.FROM_ID   ";
		if(!searchContent.equals("")){
			queryStr += " AND ( CONTENT LIKE '%" + searchContent + "%'";
			queryStr += " OR START_DATE LIKE '%" + searchContent + "%'";
			queryStr += " OR END_DATE LIKE '%" + searchContent + "%')";
		}
		queryStr += " AND ORG_ID = ? ";
		String optStr= "[{'function':'showCal','name':'查看','parm':'CALENDAR_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	/**
	 * 获取管理范围内人员的日期程列表
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param deptId
	 * @param weekStartDate
	 * @param weekEndDate
	 * @param status
	 * @param orgId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getManageRangeCalendarLogic(Connection conn,String deptId,String weekStartDate,String weekEndDate,String status,String orgId,Account account) throws Exception
	{
		updateStatus(conn,weekStartDate,weekEndDate);
		JSONArray jsonArr= new  JSONArray();
		ArrayList<Map<String,String>> userList = new ArrayList<Map<String,String>>();
		String queryStr="SELECT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME FROM USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T2.NOT_LOGIN='0' AND T1.DEPT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			Map<String,String> userMap = new HashMap<String,String>();
			userMap.put("accountId", rs.getString("ACCOUNT_ID"));
			userMap.put("userName",rs.getString("USER_NAME"));
			userList.add(userMap);
		}
		UserPrivLogic userPrivLogic=new UserPrivLogic();
		List<Map<String, String>> userLists = new ArrayList<Map<String,String>>();
		userLists = userPrivLogic.getRangeListByCalendarLogic(conn, account,deptId);		
		if(userList.size()>0)
		{
			
			for(int i=0;userList.size()>i;i++)
			{
				Map<String,String> tmpMap = userList.get(i);
				JSONObject tmpJson = new JSONObject();
				JSONArray clJsonArr= new  JSONArray();
				String accountId=tmpMap.get("accountId");
				String userName=tmpMap.get("userName");
				tmpJson.accumulate("accountId", accountId);
				tmpJson.accumulate("userName", userName);
				if(userLists.contains(tmpMap)||accountId.equals(account.getAccountId())){
					queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,(SELECT USER_NAME FROM USER_INFO t2 WHERE t2.ACCOUNT_ID = FROM_ID ) AS USER_NAME,AFFAIR_ID FROM CALENDAR WHERE ACCOUNT_ID=?  "
							+ "  AND ORG_ID = ? AND (CAL_TYPE = '3' OR CAL_TYPE = '1'  )  AND START_DATE > ? AND END_DATE <= ?  ";
					tmpJson.accumulate("isManager", "true");
				}else{
					queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,(SELECT USER_NAME FROM USER_INFO t2 WHERE t2.ACCOUNT_ID = FROM_ID ) AS USER_NAME,AFFAIR_ID FROM CALENDAR WHERE ACCOUNT_ID=?  "
							+ "  AND ORG_ID = ? AND (CAL_TYPE = '3')  AND START_DATE > ? AND END_DATE <= ?  ";
					tmpJson.accumulate("isManager", "false");
				}
				if(!status.equals("-1")){
					queryStr += " AND STATUS = '"+status+"'";
				}
				ps=conn.prepareStatement(queryStr);
				ps.setString(1, accountId);
				ps.setString(2, orgId);
				ps.setString(3, weekStartDate);
				ps.setString(4, weekEndDate);
				rs=ps.executeQuery();
				while(rs.next())
				{
					JSONObject clJson = new JSONObject();
					String endDate = rs.getString("END_DATE");
					String startDate = rs.getString("START_DATE");
					String calendarId = rs.getString("CALENDAR_ID");
					String content = rs.getString("CONTENT");
					clJson.accumulate("end", endDate);
					clJson.accumulate("id",calendarId);
					clJson.accumulate("start",startDate);
					clJson.accumulate("title", content);
					if(rs.getString("STATUS").equals("0")){
						clJson.accumulate("className", "fc-event-color1");	
					}else if(rs.getString("STATUS").equals("1")){
						clJson.accumulate("className", "fc-event-color");
					}else if(rs.getString("STATUS").equals("3")){
						clJson.accumulate("className", "fc-event-color3");
					}else if(rs.getString("STATUS").equals("4")){
						clJson.accumulate("className", "fc-event-color4");
					}
					if(rs.getString("STATUS").equals("1")&&!accountId.equals(account.getAccountId())){
						clJson.accumulate("editable", "false");
					}else{
						clJson.accumulate("editable", "true");
					}
					//String fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
					clJson.accumulate("fromName", rs.getString("USER_NAME"));
//					if(rs.getString("AFFAIR_ID")!=null){
//						if(!rs.getString("AFFAIR_ID").equals("")){
//							clJsonArr = getReal(conn, clJsonArr, rs.getString("AFFAIR_ID"), startDate, endDate, false, content, calendarId,fromName);
//						}
//					}
					clJsonArr.add(clJson);
				}
				if(status.equals("-1")||status.equals("0")){
					clJsonArr = this.getAffairCalendar(conn, clJsonArr, accountId, orgId,"2");
				}
				tmpJson.accumulate("calendarData", clJsonArr);
				jsonArr.add(tmpJson);
				rs.close();
				ps.close();
			}
		}
		return jsonArr.toString();
	}
	
	/**
	 * 获取管理范围内的人员
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param deptId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getManageUser(Connection conn,String deptId,Account account) throws Exception
	{
		
		JSONArray jsonArr= new  JSONArray();
		String queryStr="SELECT ACCOUNT_ID,USER_NAME FROM USER_INFO WHERE DEPT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("userId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
	/**
	 * 根据参与人Id查询日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param userId
	 * @param weekStartDate
	 * @param weekEndDate
	 * @param status
	 * @param orgId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getCalendarByUserId(Connection conn,String userId,String weekStartDate,String weekEndDate,String status,String orgId,Account account)throws Exception{
		String queryStr = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject returnjson = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String accountId=userId;
		UserInfo  u = new UserInfoLogic().getUserInfoByAccountId(conn, accountId);
		UserPrivLogic userPrivLogic=new UserPrivLogic();
		List<Map<String, String>> userLists = new ArrayList<Map<String,String>>();
		userLists = userPrivLogic.getRangeListByCalendarLogic(conn, account, u.getDeptId());
		Map<String, String>usermap=new HashMap<String, String>();
		usermap.put("accountId",userId);
		usermap.put("userName",new AccountLogic().getUserNameStr(conn, userId));
		if(userLists.contains(usermap)||accountId.equals(account.getAccountId())){
			queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,(SELECT USER_NAME FROM USER_INFO t2 WHERE t2.ACCOUNT_ID = FROM_ID ) AS USER_NAME,AFFAIR_ID FROM CALENDAR WHERE ACCOUNT_ID=?  "
					+ "  AND ORG_ID = ? AND (CAL_TYPE = '3' OR CAL_TYPE = '1' ) AND START_DATE > ? AND END_DATE <= ? ";
			returnjson.accumulate("isManager", "true");
		}else{
			queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,(SELECT USER_NAME FROM USER_INFO t2 WHERE t2.ACCOUNT_ID = FROM_ID ) AS USER_NAME,AFFAIR_ID FROM CALENDAR WHERE ACCOUNT_ID=?  "
					+ "  AND ORG_ID = ? AND (CAL_TYPE = '3' ) AND START_DATE > ? AND END_DATE <= ? ";
			returnjson.accumulate("isManager", "false");
		}
		if(!status.equals("-1")){
			queryStr += " AND STATUS = '"+status+"'";
		}
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ps.setString(3, weekStartDate);
		ps.setString(4, weekEndDate);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			String endDate = rs.getString("END_DATE");
			String startDate = rs.getString("START_DATE");
			String calendarId = rs.getString("CALENDAR_ID");
			String content = rs.getString("CONTENT");
			json.accumulate("end", endDate);
			json.accumulate("id",calendarId);
			json.accumulate("start",startDate);
			json.accumulate("title", content);
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("className", "fc-event-color1");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("className", "fc-event-color");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("className", "fc-event-color3");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("className", "fc-event-color4");
			}
			if(rs.getString("STATUS").equals("1")&&!accountId.equals(account.getAccountId())){
				json.accumulate("editable", "false");
			}else{
				json.accumulate("editable", "true");
			}
			//String fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
			json.accumulate("fromName", rs.getString("USER_NAME"));
			jsonArr.add(json);
		}
		if(status.equals("-1")||status.equals("0")){
			jsonArr = this.getAffairCalendar(conn, jsonArr, accountId, orgId,"2");
		}
		returnjson.accumulate("rows", jsonArr);
		rs.close();
		ps.close();
		return returnjson.toString();
	}
	/**
	 * 搜索日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param pramList
	 * @param content
	 * @param calType
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param pagesize
	 * @param page
	 * @param storOrder
	 * @param storName
	 * @return
	 * @throws Exception
	 */
	public String searchCalendar(Connection conn,List<String> pramList,String content,String calType,String status,String startDate,String endDate,int pagesize,int page,String storOrder,String storName)throws Exception{
		String queryStr= "SELECT ID,CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,USER_ID,IS_SMS,STATUS,ACCOUNT_ID,FROM_ID FROM CALENDAR "
				+ "WHERE ACCOUNT_ID=? ";
		if(!content.equals("")){
			queryStr += " AND CONTENT LIKE '%" + content + "%'";
		}
		if(!calType.equals("")){
			queryStr += " AND CAL_TYPE = '" + calType + "'";
		}
		if(!status.equals("")){
			queryStr += " AND STATUS = '" + status + "'";
		}
		if(!startDate.equals("")){
			queryStr += " AND START_DATE > '" + startDate + "'";
		}
		if(!endDate.equals("")){
			queryStr += " AND START_DATE < '" + endDate + "'";
		}
		queryStr += " AND ORG_ID = ? ";
		String optStr= "[{'function':'showCal','name':'查看','parm':'CALENDAR_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	/**
	 * 搜索周期事务
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param pramList
	 * @param searchContent
	 * @param pagesize
	 * @param page
	 * @param storOrder
	 * @param storName
	 * @return
	 * @throws Exception
	 */
	public String searchCycle(Connection conn,List<String> pramList,String searchContent,int pagesize,int page,String storOrder,String storName)throws Exception{
		String queryStr= "SELECT t1.CALENDAR_ID,t1.START_DATE,t1.END_DATE,t1.CONTENT,t1.CAL_LEVEL,t1.CAL_TYPE,t1.CAL_AFF_TYPE,"
				+ "t1.BEFORE_TIME,t1.USER_ID,t1.IS_SMS,t1.STATUS,t2.REMIND_TYPE,t2.REMIND_DATE,t2.REMIND_TIME,t2.IS_WEEK FROM CALENDAR t1 LEFT JOIN AFFAIR t2 "
				+ "ON t1.CALENDAR_ID = t2.CALENDAR_ID WHERE t1.CAL_AFF_TYPE = '1' ";
		if(!searchContent.equals("")){
			queryStr += " AND ( t1.CONTENT LIKE '%" + searchContent + "%'";
			queryStr += " OR t1.START_DATE LIKE '%" + searchContent + "%'";
			queryStr += " OR t1.END_DATE LIKE '%" + searchContent + "%')";
		}
		queryStr += " AND t1.ACCOUNT_ID = ? AND t1.ORG_ID = ? ";
		String optStr= "[{'function':'updateCal','name':'修改','parm':'CALENDAR_ID'},{'function':'deleteCal','name':'删除','parm':'CALENDAR_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	/**
	 * 添加完成情况(日志)
	 * Author:Yzz
	 * Time:2015-6-1
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
	 * 获取今天往后的3条未完成的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param start
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getTodayCalendar(Connection conn,String start,String accountId,String orgId)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		JSONArray jsonArr = new JSONArray();
		String queryStr="";
		if(dbType.equals("sqlserver"))
		{
			queryStr="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY START_DATE ASC) AS RB,CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID FROM CALENDAR WHERE ORG_ID = ? AND ACCOUNT_ID=?  AND END_DATE > '"+start+"' AND STATUS <>1) t1 WHERE  RB BETWEEN 0 AND 6";
		}else if(dbType.equals("mysql"))
		{
			queryStr="SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID FROM CALENDAR WHERE ORG_ID = ? AND ACCOUNT_ID=?  AND END_DATE > '"+start+"' AND STATUS != '1' ORDER BY START_DATE ASC LIMIT 6 ";
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM (SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID FROM CALENDAR WHERE ORG_ID = ? AND ACCOUNT_ID=?  AND END_DATE > '"+start+"' AND STATUS != '1' ORDER BY START_DATE ASC) TMP WHERE ROWNUM<=6";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("startTime", rs.getString("START_DATE"));
			json.accumulate("content", rs.getString("CONTENT"));
			if(rs.getString("STATUS").equals("0")){
				json.accumulate("status", "未开始");	
			}else if(rs.getString("STATUS").equals("1")){
				json.accumulate("status", "已完成");
			}else if(rs.getString("STATUS").equals("3")){
				json.accumulate("status", "进行中");
			}else if(rs.getString("STATUS").equals("4")){
				json.accumulate("status", "已超时");
			}
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据From_Type_Id修改日程
	 * Author:Wp
	 * Time:2015-9-6
	 * @param conn
	 * @param cl
	 * @return
	 * @throws SQLException
	 */
	public int settypeupdateClLogic(Connection conn,Calendar cl)throws Exception{
		String queryStr="UPDATE CALENDAR SET START_DATE=? ,END_DATE=? ,CONTENT=?,FROM_TYPE=?,IS_SMS=? WHERE FROM_TYPE_ID=? AND FROM_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, cl.getStartDate());
		ps.setString(2, cl.getEndDate());
		ps.setString(3, cl.getContent());
		ps.setString(4, cl.getFromType());
		ps.setString(5, cl.getIsSms());
		ps.setString(6, cl.getFromTypeId());
		ps.setString(7, cl.getFromId());
		ps.setString(8, cl.getOrgId());
		int i=ps.executeUpdate();
		if(i==0){
			this.addOrUpdateLogic(conn, cl, "1");
		}
		return i;
	}
	/*
	 * 根据From_Type_Id删除日程
	 * 
	 */
	public int settypeIddelClLogic(Connection conn,String fromTypeId,String orgId)throws SQLException{
		String queryStr="DELETE FROM CALENDAR WHERE FROM_TYPE_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, fromTypeId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		return i;
	}
	
	/**
	 * 日程完成后给安排人添加回执消息
	 * Time:2015-9-21
	 * Author:Yzz
	 * @param conn
	 * @param cId
	 * @param account
	 * @throws Exception
	 */
	public void addBackSms(Connection conn,String cId,Account account)throws Exception{
		String jsonStr = this.selectByIdLogic(conn, cId, account);
		JSONObject json = JSONObject.fromObject(jsonStr);
		String uId = json.getString("fromId");
		if(!uId.equals(account.getAccountId())){
			String smsStr = json.getString("isSms");
			JSONObject smsRemindJson = JSONObject.fromObject(smsStr);
			List<String> toAccountList = new ArrayList<String>();
			toAccountList.add(uId);
			MessageApi messageApi = new MessageApi();
			messageApi.sendMessage(conn, "calendar", smsRemindJson, "您安排的日程已完成，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId());
		}
	}
	
	
	public List<Record> CalendarExport(Connection conn,Account account,String content,String calType,String status,String startDate,String endDate)throws Exception{
		List<Record> records=new ArrayList<Record>();
		
		//添加列名
		Record record=new Record();
		record.addField("开始时间", "开始时间");
		record.addField("结束时间", "结束时间");
		record.addField("日程内容", "日程内容");
		record.addField("日程类型", "日程类型");
		record.addField("日程状态", "日程状态");
		records.add(record);
		
		String queryStr = "SELECT ID,CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,USER_ID,IS_SMS,STATUS FROM CALENDAR WHERE ACCOUNT_ID=? ";
		if(!content.equals("")){
			queryStr += " AND CONTENT LIKE '%" + content + "%'";
		}
		if(!calType.equals("")){
			queryStr += " AND CAL_TYPE = '" + calType + "'";
		}
		if(!status.equals("")){
			queryStr += " AND STATUS = '" + status + "'";
		}
		if(!startDate.equals("")){
			queryStr += " AND START_DATE > '" + startDate + "'";
		}
		if(!endDate.equals("")){
			queryStr += " AND START_DATE < '" + endDate + "'";
		}
		queryStr += " AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			record=new Record();
			
			record.addField("开始时间", rs.getString("START_DATE"));
			record.addField("结束时间", rs.getString("END_DATE"));
			record.addField("日程内容", rs.getString("CONTENT"));
			if(rs.getString("CAL_TYPE").equals("1")){
				record.addField("日程类型", "工作日程");	
			}else if(rs.getString("CAL_TYPE").equals("2")){
				record.addField("日程类型", "个人日程");
			}else if(rs.getString("CAL_TYPE").equals("3")){
				record.addField("日程类型", "完全公开");
			}
			if(rs.getString("STATUS").equals("0")){
				record.addField("日程状态", "未开始");	
			}else if(rs.getString("STATUS").equals("1")){
				record.addField("日程状态", "已完成");
			}else if(rs.getString("STATUS").equals("3")){
				record.addField("日程状态", "进行中");
			}else if(rs.getString("STATUS").equals("4")){
				record.addField("日程状态", "已超时");
			}
			
			records.add(record);
		}
		ps.close();
		rs.close();
		return records;
	}
	
	public String queryCalendar(Connection conn,List<String> pramList,String content,String status,String startDate,String endDate,String calLevel,String deptPriv,Account account,int pagesize,int page,String storOrder,String storName)throws Exception{
		String queryStr= "SELECT ID,CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,USER_ID,IS_SMS,STATUS FROM CALENDAR "
				+ "WHERE 1=1 ";
		if(!content.equals("")){
			queryStr += " AND CONTENT LIKE '%" + content + "%'";
		}
		if(!status.equals("")){
			queryStr += " AND STATUS = '" + status + "'";
		}
		if(!startDate.equals("")){
			queryStr += " AND START_DATE > '" + startDate + "'";
		}
		if(!endDate.equals("")){
			queryStr += " AND START_DATE < '" + endDate + "'";
		}
		if(!calLevel.equals("")){
			queryStr += " AND CAL_LEVEL = '" + calLevel + "'";
		}
		UserPrivLogic userPrivLogic=new UserPrivLogic();
		List<Map<String, String>> userLists = new ArrayList<Map<String,String>>();
		userLists = userPrivLogic.getRangeListLogic(conn, account);
		String users = "";
		if(!deptPriv.equals("")&&!deptPriv.equals("deptAll")){
			String[] depts = deptPriv.split(",");
			for (int i = 0; i < depts.length; i++) {
				String userJson = this.getManageUser(conn, depts[i], account);
				JSONArray jsonArr = JSONArray.fromObject(userJson);
				for (int j = 0; j < jsonArr.size(); j++) {
					Map<String, String>usermap=new HashMap<String, String>();
					JSONObject json = jsonArr.getJSONObject(j);
					usermap.put("accountId",json.getString("userId"));
					usermap.put("userName",new AccountLogic().getUserNameStr(conn, json.getString("userId")));
					if(userLists.contains(usermap)){
						users += json.getString("userId")+",";
					}
					if(json.getString("userId").equals(account.getAccountId())){
						users += json.getString("userId")+",";
						//queryStr += " AND CAL_TYPE = '2' ";
					}
				}
			}
		}else{
			for (int i = 0; i < userLists.size(); i++) {
				Map<String, String> temMap = userLists.get(i);
				users += temMap.get("accountId")+",";
			}
			users += account.getAccountId()+",";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql")){ 
			queryStr += " AND LOCATE(ACCOUNT_ID,CONCAT(',','"+users+"',',')) > 0 AND CAL_TYPE != '2'  ";
		}else if(dbType.equals("oracle")){
			queryStr += " AND INSTR(CONCAT(',','"+users+"')||',',ACCOUNT_ID) > 0 AND CAL_TYPE != '2'  ";
		}
		queryStr += " AND ORG_ID = ? ";
		String optStr= "[{'function':'showCal','name':'查看','parm':'CALENDAR_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	public List<Record> CalendarExport2(Connection conn,Account account,String content,String status,String startDate,String endDate,String calLevel,String deptPriv)throws Exception{
		List<Record> records=new ArrayList<Record>();
		
		//添加列名
		Record record=new Record();
		record.addField("开始时间", "开始时间");
		record.addField("结束时间", "结束时间");
		record.addField("日程内容", "日程内容");
		record.addField("日程类型", "日程类型");
		record.addField("日程状态", "日程状态");
		records.add(record);
		
		String queryStr= "SELECT ID,CALENDAR_ID,START_DATE,END_DATE,CONTENT,CAL_LEVEL,CAL_TYPE,CAL_AFF_TYPE,"
				+ "BEFORE_TIME,USER_ID,IS_SMS,STATUS FROM CALENDAR "
				+ "WHERE 1=1 ";
		if(!content.equals("")){
			queryStr += " AND CONTENT LIKE '%" + content + "%'";
		}
		if(!status.equals("")){
			queryStr += " AND STATUS = '" + status + "'";
		}
		if(!startDate.equals("")){
			queryStr += " AND START_DATE > '" + startDate + "'";
		}
		if(!endDate.equals("")){
			queryStr += " AND START_DATE < '" + endDate + "'";
		}
		if(!calLevel.equals("")){
			queryStr += " AND CAL_LEVEL = '" + calLevel + "'";
		}
		UserPrivLogic userPrivLogic=new UserPrivLogic();
		List<Map<String, String>> userLists = new ArrayList<Map<String,String>>();
		userLists = userPrivLogic.getRangeListLogic(conn, account);
		String users = "";
		if(!deptPriv.equals("")&&!deptPriv.equals("deptAll")){
			String[] depts = deptPriv.split(",");
			for (int i = 0; i < depts.length; i++) {
				String userJson = this.getManageUser(conn, depts[i], account);
				JSONArray jsonArr = JSONArray.fromObject(userJson);
				for (int j = 0; j < jsonArr.size(); j++) {
					Map<String, String>usermap=new HashMap<String, String>();
					JSONObject json = jsonArr.getJSONObject(j);
					usermap.put("accountId",json.getString("userId"));
					usermap.put("userName",new AccountLogic().getUserNameStr(conn, json.getString("userId")));
					if(userLists.contains(usermap)){
						users += json.getString("userId")+",";
					}
					if(json.getString("userId").equals(account.getAccountId())){
						users += json.getString("userId")+",";
						//queryStr += " AND CAL_TYPE = '2' ";
					}
				}
			}
		}else{
			for (int i = 0; i < userLists.size(); i++) {
				Map<String, String> temMap = userLists.get(i);
				users += temMap.get("accountId")+",";
			}
			users += account.getAccountId()+",";
		}
		queryStr += " AND LOCATE(ACCOUNT_ID,CONCAT(',','"+users+"',',')) > 0 AND CAL_TYPE != '2'  ";
		queryStr += " AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			record=new Record();
			
			record.addField("开始时间", rs.getString("START_DATE"));
			record.addField("结束时间", rs.getString("END_DATE"));
			record.addField("日程内容", rs.getString("CONTENT"));
			if(rs.getString("CAL_TYPE").equals("1")){
				record.addField("日程类型", "工作日程");	
			}else if(rs.getString("CAL_TYPE").equals("2")){
				record.addField("日程类型", "个人日程");
			}else if(rs.getString("CAL_TYPE").equals("3")){
				record.addField("日程类型", "完全公开");
			}
			if(rs.getString("STATUS").equals("0")){
				record.addField("日程状态", "未开始");	
			}else if(rs.getString("STATUS").equals("1")){
				record.addField("日程状态", "已完成");
			}else if(rs.getString("STATUS").equals("3")){
				record.addField("日程状态", "进行中");
			}else if(rs.getString("STATUS").equals("4")){
				record.addField("日程状态", "已超时");
			}
			
			records.add(record);
		}
		ps.close();
		rs.close();
		return records;
	}
	
	public boolean isExist(Connection conn,String accountId,String calendarPid)throws Exception{
		boolean flag = false;
		String sql = "SELECT CALENDAR_ID FROM CALENDAR WHERE CALENDAR_PID = ? AND ACCOUNT_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, calendarPid);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}
		ps.close();
		rs.close();
		return flag;
	}
	
	public JSONArray getReal(Connection conn, JSONArray jsonArr,String affairId,String startDate,String endDate,boolean allDay,String content,String calendarId,String fromName)throws Exception{
		String queryStr = "SELECT t2.AFFAIR_ID,t2.REMIND_TYPE,t2.REMIND_DATE,t2.REMIND_TIME,"
				+ "t2.END_WHILE,t2.FREQUENCY,t2.IS_WEEK,t2.AFFAIR_ID FROM AFFAIR t2 WHERE t2.AFFAIR_ID = ? AND t2.END_WHILE > ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, affairId);
		ps.setString(2, SysTool.getDateTimeStr(new Date()));
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			String remindType = rs.getString("REMIND_TYPE");
			String remindDate = rs.getString("REMIND_DATE");
			String endwhile = rs.getString("END_WHILE");
			String isWeek = rs.getString("IS_WEEK");
			String frequency = rs.getString("FREQUENCY");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
			Date startDate1 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+startDate.substring(11,startDate.length()));
			int fNum = Integer.parseInt(frequency);
			Date end = sdf.parse(endwhile+" 00:00");
			Date start = sdf.parse(startDate.substring(0,10)+" 00:00");
		    Date now = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" 00:00");
		    long spanNum = (now.getTime()-start.getTime())/(24*60*60*1000);
		    long span = (sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime());
			if(remindType.equals("2")){
				int i = 0;
				for (i = 0; i < fNum; i++) {
					if(isWeek!=null){
						int num = AffairServer.getWorkDays(start, now);
						num = num + i;
						if(num%fNum==0){
							break;
						}
					}else{
						spanNum = spanNum + i;
						if(spanNum%Integer.parseInt(frequency)==0){
							break;
						}
					}
				}
				if(i <= 60){
					startDate1 = new Date(startDate1.getTime()+i*(24*60*60*1000));
					for (int j = 0; j < 60/fNum; j++) {
						startDate1 = new Date(startDate1.getTime()+fNum*(24*60*60*1000));
						boolean flag = true;
						if(isWeek!=null){
							String week = sdf2.format(startDate1);
							if(week.equals("星期六")||week.equals("星期日")){
								flag = false;
							}
						}
						if(startDate1.getTime()<end.getTime()){
							String endDate1 = SysTool.getDateTimeStr(new Date(startDate1.getTime() + span));
							JSONObject json = new JSONObject();
							json = this.getjson(json, allDay, calendarId, SysTool.getDateTimeStr(startDate1), endDate1, content,fromName);
							if(flag){
								jsonArr.add(json);
							}
						}else{
							break;
						}
					}
				}
			}else if(remindType.equals("3")){
				int startNum = 7*fNum; //周开始
				int endNum = 7*(fNum+1); //周结束
				int weekNum = (int)spanNum/startNum; //周数
				int i = 0;
				for (i = 0; i < fNum; i++) {
					if(spanNum >= startNum*weekNum+i*7&&spanNum<=endNum*weekNum+i*7){
						break;
					}
				}
				if(i*7<60){
					startDate1 = new Date(startDate1.getTime()+i*7*(24*60*60*1000));
					for (int j = 0; j < 60/(fNum*7); j++) {
						for(int k = 0 ; k < 7 ; k++){
							startDate1 = new Date(startDate1.getTime()+(24*60*60*1000));
							String week = sdf2.format(startDate1);
							String[] weeks = remindDate.split(",");
							boolean flag = false;
							for (int l = 0; l < weeks.length; l++) {
								if(week.equals(weeks[l])){
									flag = true;
									break;
								}
							}
							if(flag){
								if(startDate1.getTime()<end.getTime()){
									String endDate1 = SysTool.getDateTimeStr(new Date(startDate1.getTime() + span));
									JSONObject json = new JSONObject();
									json = this.getjson(json, allDay, calendarId, SysTool.getDateTimeStr(startDate1), endDate1, content,fromName);
										
									jsonArr.add(json);
								}else{
									break;
								}
							}
						}
					}
				}
			}else if(remindType.equals("4")){
				java.util.Calendar c = java.util.Calendar.getInstance();
				int MonNum = AffairServer.getMonthSpace(startDate, SysTool.getDateTimeStr(startDate1));
				c.setTime(startDate1);
				for (int i = 0; i < 3; i++) {
					c.add(java.util.Calendar.MONTH, 1);
					MonNum = AffairServer.getMonthSpace(startDate, sdf.format(c.getTime()));
					if(MonNum !=0){
						if(MonNum%fNum==0){
							int dayNum = c.getActualMaximum(java.util.Calendar.DATE);
							c.set(java.util.Calendar.DAY_OF_MONTH, 1);
							String[] days = remindDate.split(",");
							for (int j = 0; j < dayNum; j++) {
								String day = c.get(java.util.Calendar.DAY_OF_MONTH)+"日";
								boolean flag = false;
								for (int l = 0; l < days.length; l++) {
									if(day.equals(days[l])){
										flag = true;
										break;
									}
								}
								if(flag){
									String endDate1 = sdf.format(c.getTime().getTime()+ span);
									JSONObject json = new JSONObject();
									json = this.getjson(json, allDay, calendarId, sdf.format(c.getTime()), endDate1, content,fromName);
									jsonArr.add(json);
								}
								c.add(java.util.Calendar.DAY_OF_MONTH, 1);
							}
						}
					}
				}
			}else if(remindType.equals("5")){
				int year = Integer.parseInt(SysTool.getDateTimeStr(new Date()).substring(0,4));
				int startYear = Integer.parseInt(startDate.substring(0,4));
				if(year - startYear != 0){
					if((year-startYear)%fNum==0){
						String startDate2 = year + "-" + remindDate +" "+startDate.substring(11,startDate.length());
						String endDate1 = SysTool.getDateTimeStr(new Date(startDate1.getTime() + span));
						JSONObject json = new JSONObject();
						
						json = this.getjson(json, allDay, calendarId, startDate2, endDate1, content,fromName);	
						
						jsonArr.add(json);
					}
				}
			}
		}
		return jsonArr;
	}
	
	public JSONObject getjson(JSONObject json,boolean allDay,String calendarId,String startDate,String endDate,String content,String fromName)throws Exception{
		json.accumulate("allDay", allDay);
		json.accumulate("id", calendarId);
		json.accumulate("start", startDate);
		json.accumulate("end", endDate);
		json.accumulate("title", content);
		json.accumulate("className", "fc-event-color1");
		json.accumulate("status", "0");
		json.accumulate("deleteable", false);
		json.accumulate("editable", false);
		json.accumulate("fromName",fromName);
		json.accumulate("finishable", false);
		return json;
	}
	
	public JSONArray getAffairCalendar(Connection conn,JSONArray jsonArr,String accountId,String orgId,String type)throws Exception{
		String sql = "SELECT CALENDAR_ID,START_DATE,END_DATE,CONTENT,STATUS,USER_ID,ACCOUNT_ID,FROM_ID,AFFAIR_ID FROM CALENDAR WHERE CAL_AFF_TYPE = '1' AND ORG_ID = ? AND ACCOUNT_ID=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			DateFormat endd=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat startd=new SimpleDateFormat("yyyy-MM-dd");
			String endStr="";
			String startStr="";
			try {
				endStr = endd.parse(rs.getString("END_DATE")).toString();
				startStr= startd.parse(rs.getString("START_DATE")).toString();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String str1 = rs.getString("END_DATE").toString().substring(11,16);
			String str2 = rs.getString("START_DATE").toString().substring(11,16);
			boolean allDay = false;
			if(!endStr.equals(startStr)||(str1.equals("23:59")&&str2.equals("00:00")))
			{
				allDay = true;
			}else
			{
				allDay = false;	
			}
			String endDate = rs.getString("END_DATE");
			String startDate = rs.getString("START_DATE");
			String calendarId = rs.getString("CALENDAR_ID");
			String content = rs.getString("CONTENT");
			String fromName = "";
			if(type.equals("2")){
				fromName = new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
			}else{
				if(!rs.getString("FROM_ID").equals(accountId)){
					fromName = "安排人："+new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
				}else{
					if(rs.getString("USER_ID")!=null){
						if(rs.getString("USER_ID").indexOf(accountId)>-1&&!accountId.equals(rs.getString("ACCOUNT_ID"))){
							fromName = "安排人："+new AccountLogic().getUserNameStr(conn, rs.getString("FROM_ID"));
						}else{
							fromName = "    ";
						}
					}else{
						fromName = "    ";
					}
				}
			}
			if(rs.getString("AFFAIR_ID")!=null){
				if(!rs.getString("AFFAIR_ID").equals("")){
					jsonArr = getReal(conn, jsonArr, rs.getString("AFFAIR_ID"), startDate, endDate, allDay, content, calendarId,fromName);
				}
			}
		}
		rs.close();
		ps.close();
		return jsonArr;
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
	
	/**
	 * 获取未完成日程数量
	 * Author:Yzz
	 * Time:2016-1-7
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int getCalendarNum(Connection conn,String accountId,String orgId)throws Exception{
		int i = 0;
		String sql = "SELECT COUNT(CALENDAR_ID) AS CALENDAR_NUM FROM CALENDAR WHERE ACCOUNT_ID = ? AND ORG_ID = ? AND STATUS != '1' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			i = rs.getInt("CALENDAR_NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
}
