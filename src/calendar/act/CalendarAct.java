package calendar.act;


import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import calendar.data.Affair;
import calendar.data.Calendar;
import calendar.logic.CalendarLogic;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.Diary;
import diary.logic.DiaryLogic;

public class CalendarAct {
	
	/**
	 * 查询时间段内的自己的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectByTimeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData=cllogic.selectByTimeLogic(dbConn, account,startTime,endTime);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 添加或修改日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addOrUpdate(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Calendar cl = new Calendar();
			String calId = request.getParameter("calId");
			String isAdd = "";
			if(calId==""||calId==null){
				calId = GuId.getGuid();
				isAdd = "1";
			}else{
				isAdd = "2";
			}
			cl.setCalendarPid(calId);
			String startDate = request.getParameter("startDate");
			cl.setStartDate(startDate);
			cl.setEndDate(request.getParameter("endDate"));
			cl.setContent(request.getParameter("content"));
			cl.setCalType(request.getParameter("calType"));
			cl.setCalLevel(request.getParameter("calLevel"));
			String calAffType = request.getParameter("calAffType");
			cl.setCalAffType(request.getParameter("calAffType"));
			String userId = request.getParameter("accountId");
			if(request.getParameter("calType").equals("2")){
				userId = "";
			}
			String remindType = request.getParameter("remindType");
			String remindTime = request.getParameter("remindTime");
			String endwhile = request.getParameter("endWhile");
			String frequency = request.getParameter("freDay");
			String fromId = "";
			if(!SysTool.isNullorEmpty(request.getParameter("fromId"))){
				fromId = request.getParameter("fromId");
			}else{
				fromId = account.getAccountId();
			}
			cl.setFromId(fromId);
			String remindDate = "";
			
			if(request.getParameter("calAffType")!=null&&remindType!=null){
				if(remindType.equals("3")){
					remindDate = request.getParameter("remindDate3");
					frequency = request.getParameter("freWeek");
				}else if(remindType.equals("4")){
					remindDate = request.getParameter("remindDate4");
					frequency = request.getParameter("freMon");
				}else if(remindType.equals("5")){
					remindDate = request.getParameter("remindDate5Mon") + "-" + request.getParameter("remindDate5Day");
					frequency = request.getParameter("freYear");
				}
			}
			String beforeTime = request.getParameter("remindmins");
			cl.setBeforeTime(beforeTime);
			String accountId = "";
			if(!SysTool.isNullorEmpty(request.getParameter("accountIds"))){
				accountId = request.getParameter("accountIds");
			}else{
				accountId = account.getAccountId();
			}
			cl.setAccountId(accountId);
			if(!SysTool.isNullorEmpty(request.getParameter("fromType"))){
				cl.setFromType(request.getParameter("fromType"));
				cl.setFromTypeId(request.getParameter("fromTypeId"));
			}
			cl.setOrgId(account.getOrgId());
			String smsRemindStr = request.getParameter("smsReminds");
			cl.setIsSms(smsRemindStr);
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			cl.setStatus("0");
			CalendarLogic cllogic = new CalendarLogic();
			if(calAffType!=null){
				Affair affair = new Affair();
				String affairId = request.getParameter("affairId");
				affair.setAffairId(affairId);
				affair.setCalAffType(request.getParameter("calAffType"));
				affair.setRemindType(remindType);
				affair.setRemindTime(remindTime);
				affair.setFrequency(frequency);
				affair.setRemindDate(remindDate);
				affair.setEndWhile(endwhile);
				affair.setIsWeek(request.getParameter("isWeekend"));
				affair.setOrgId(account.getOrgId());
				affair.setCalendarId(calId);
				if(affairId==""||affairId==null){
					affairId = GuId.getGuid();
					cl.setAffairId(affairId);
					affair.setAffairId(affairId);
					cllogic.addAffair(dbConn, affair);
				}else{
					cl.setAffairId(affairId);
					affair.setAffairId(affairId);
					cllogic.updateAffair(dbConn, affair);
				}
			}else{
				cl.setAffairId("");
				
			}
			List<String> toAccountList = new ArrayList<String>();
			String[] userIds = null;
			if(!userId.equals("")){
				if(userId.indexOf(",")>-1){
					if(userId.indexOf(accountId)<0){
						userId += ","+accountId;
					}
					cl.setUserId(userId);
					userIds = userId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						toAccountList.add(userIds[i]);
						if(!userIds[i].equals(accountId)){
							cl.setCalendarId(GuId.getGuid());
							cl.setAccountId(userIds[i]);
							if(!cllogic.isExist(dbConn, userIds[i], calId)){
								cllogic.addOrUpdateLogic(dbConn, cl,"1");
							}else{
								cllogic.addOrUpdateLogic(dbConn, cl,isAdd);
							}
							
						}
					}
				}else{
					cl.setAccountId(userId);
					toAccountList.add(userId);
					cl.setCalendarId(GuId.getGuid());
					if(!cllogic.isExist(dbConn, userId, calId)){
						if(userId.indexOf(accountId)<0){
							userId += ","+accountId;
						}
						cl.setUserId(userId);
						cllogic.addOrUpdateLogic(dbConn, cl,"1");
					}else{
						if(userId.indexOf(accountId)<0){
							userId += ","+accountId;
						}
						cl.setUserId(userId);
						cllogic.addOrUpdateLogic(dbConn, cl,isAdd);
					}
				}
			}else{
				userId += accountId;
				cl.setUserId(userId);
			} 
			toAccountList.add(accountId);
			if(request.getParameter("calAffType")==null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date start = sdf.parse(startDate);
				beforeTime=beforeTime==null?"0":beforeTime;
				long bfTime = (Integer.parseInt(beforeTime))*60*1000;
				long temp = start.getTime() - bfTime;
				String sendTime = SysTool.getDateTimeStr(new Date(temp));
				MessageApi messageApi = new MessageApi();
				messageApi.addMessage(dbConn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
			}
			cl.setCalendarId(calId);
			cl.setAccountId(accountId);
			returnData=cllogic.addOrUpdateLogic(dbConn, cl,isAdd);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 更新日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateChangeCalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String sid = request.getParameter("sid");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData=cllogic.updateChangeCalLogic(dbConn, startDate, endDate, sid,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 删除日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteCalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int  returnData=0;
		try{
			String calendarId = request.getParameter("sid");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.deleteCalLogic(dbConn, calendarId,account.getOrgId(),account.getAccountId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 更新日程状态
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateOverStatusAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String calendarId = request.getParameter("sid");
			String status = request.getParameter("overStatus");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData=cllogic.updateOverStatusLogic(dbConn, calendarId,status,account.getOrgId(),account);
			if(status.equals("1")){
				cllogic.addBackSms(dbConn,calendarId,account);
			}
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 根据Id查询日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectByIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String calendarId = request.getParameter("sid");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData=cllogic.selectByIdLogic(dbConn, calendarId,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 按个人查询日程安排列表
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPerCalendarJsonAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=account.getAccountId();
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.getPerCalendarJsonLogic(dbConn, accountId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 查询我安排的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCalendarFromMeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr)){
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String searchContent = request.getParameter("searchContent"); 
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new CalendarLogic().getCalendarFromMeLogic(dbConn,pramList,searchContent,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 查询别人安排给我的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCalendarForMeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr)){
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String searchContent = request.getParameter("searchContent"); 
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new CalendarLogic().getCalendarForMeLogic(dbConn,pramList,searchContent,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取管理范围内人员的日期程列表
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getManageRangeCanlenarAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=request.getParameter("deptId");
			if(deptId.equals("null")){
				deptId =  new UserInfoLogic().getDeptId(dbConn,account.getAccountId());
			}
			String weekStartDate=request.getParameter("weekStartDate");
			String weekEndDate=request.getParameter("weekEndDate");
			weekEndDate = weekEndDate +" 23:59";
			String status=request.getParameter("status");
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.getManageRangeCalendarLogic(dbConn,deptId,weekStartDate,weekEndDate,status,account.getOrgId(),account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 获取管理范围内的人员
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getManageUser(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String deptId=request.getParameter("deptId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			if(deptId.equals("null")){
				deptId =  new UserInfoLogic().getDeptId(dbConn,account.getAccountId());
			}
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.getManageUser(dbConn,deptId,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 根据参与人Id查询日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCalendarByUserId(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String userId=request.getParameter("userId");
			String weekStartDate=request.getParameter("weekStartDate");
			String weekEndDate=request.getParameter("weekEndDate");
			String status=request.getParameter("status");
			weekEndDate = weekEndDate + "23:59";
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.getCalendarByUserId(dbConn,userId,weekStartDate,weekEndDate,status,account.getOrgId(),account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 搜索日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void searchCalendar(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			
			String content = request.getParameter("content"); 
			String calType = request.getParameter("calType");
			String status = request.getParameter("status");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new CalendarLogic().searchCalendar(dbConn,pramList,content,calType,status,startDate,endDate,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 搜索周期日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void searchCycle(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			
			String searchContent = request.getParameter("searchContent"); 
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new CalendarLogic().searchCycle(dbConn,pramList,searchContent,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 添加完成情况(日志)
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addDiary(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String sid=request.getParameter("sid");
			String content=request.getParameter("content");
			String diaryId=request.getParameter("diaryId");
		
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			if(!content.equals("")){
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
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
				if(diaryId.equals("")){
					id = GuId.getGuid();
					dr.setDiaryId(id);
					dl.addDiaryLogic(dbConn,dr);
					returnData = new CalendarLogic().addDiary(dbConn,sid,id);
				}else{
					dr.setDiaryId(diaryId);
					returnData = dl.updateDiaryLogic(dbConn, dr);
				}
			}
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 获取今天往后3条未完成的日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getTodayCalendar(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String start = SysTool.getDateTimeStr(new Date());
			start = start.substring(0,10) + " 00:00";
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			returnData = new CalendarLogic().getTodayCalendar(dbConn,start,account.getAccountId(),account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public String CalendarExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream ops=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String content = request.getParameter("content"); 
			String calType = request.getParameter("calType");
			String status = request.getParameter("status");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			Account account=(Account) request.getSession().getAttribute("ACCOUNT_ID");
			List<Record> records=new CalendarLogic().CalendarExport(conn,account,content,calType,status,startDate,endDate);
			
			String exportFileName="日程安排.xls";
			String fileName = URLEncoder.encode(exportFileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control","maxage=3600");
			response.setHeader("Pragma","public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			conn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(conn);
			SysTool.closeOutputStream(ops);
		}
		return null;
	}
	
	/**
	 * 搜索日程
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void queryCalendar(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			
			String content = request.getParameter("content"); 
			String status = request.getParameter("status");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String calLevel = request.getParameter("calLevel");
			String deptPriv = request.getParameter("deptPriv");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new CalendarLogic().queryCalendar(dbConn,pramList,content,status,startDate,endDate,calLevel,deptPriv,account,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public String CalendarExport2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream ops=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String content = request.getParameter("content"); 
			String status = request.getParameter("status");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String calLevel = request.getParameter("calLevel");
			String deptPriv = request.getParameter("deptPriv");
			Account account=(Account) request.getSession().getAttribute("ACCOUNT_ID");
			List<Record> records=new CalendarLogic().CalendarExport2(conn,account,content,status,startDate,endDate,calLevel,deptPriv);
			
			String exportFileName="日程安排.xls";
			String fileName = URLEncoder.encode(exportFileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control","maxage=3600");
			response.setHeader("Pragma","public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			conn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(conn);
			SysTool.closeOutputStream(ops);
		}
		return null;
	}
	public void getCalendarNum(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=account.getAccountId();
			CalendarLogic cllogic = new CalendarLogic();
			returnData = cllogic.getCalendarNum(dbConn, accountId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
