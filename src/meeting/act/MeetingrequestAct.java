package meeting.act;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.data.Meetingrequest;
import meeting.logic.MeetingrequestLogic;
import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import calendar.data.Affair;
import calendar.data.Calendar;
import calendar.logic.CalendarLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class MeetingrequestAct {
	/**
	 * 添加会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingrequest mrequest=new Meetingrequest();
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			mrequest.setRequestId(GuId.getGuid());
			mrequest.setAttendStaff(request.getParameter("attendStaff"));
			mrequest.setSelectDept(request.getParameter("selectDept"));
			mrequest.setMeetingName(request.getParameter("meetingName"));
			mrequest.setMeetingTheme(request.getParameter("meetingTheme"));
			mrequest.setBoardroomId(request.getParameter("boardroomId"));
			mrequest.setBoardroomStaff(request.getParameter("boardroomStaff"));
			mrequest.setMeetingType(request.getParameter("meetingType"));
			mrequest.setMeetingStarttime(request.getParameter("meetingStarttime"));
			mrequest.setMeetingEndtime(request.getParameter("meetingEndtime"));
			mrequest.setMeetingDevice(request.getParameter("meetingDevice"));
			mrequest.setMeetingSumman(request.getParameter("meetingSumman"));
			mrequest.setAttachId(request.getParameter("attachId"));
			mrequest.setMeetingDescription(request.getParameter("meetingDescription"));
			mrequest.setCreateUser(account.getAccountId());
			mrequest.setMeetingStatus("0");
			if(request.getParameter("meetingType").equals("1")){
			mrequest.setCycType(request.getParameter("cycType"));
			}else{
				mrequest.setCycType("");
			}
			mrequest.setOrgId(account.getOrgId());
			mrequest.setCycEndtime(request.getParameter("cycEndtime"));
			String smsRemindStr = request.getParameter("smsReminds");
			mrequest.setWarnTime(request.getParameter("warnTime"));
			mrequest.setIsSms(smsRemindStr);
			data = meetingrequestloigc.addrequestlogic(dbConn, mrequest);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 修改会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updaterequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingrequest mrequest=new Meetingrequest();
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			mrequest.setRequestId(request.getParameter("requestId"));
			mrequest.setAttendStaff(request.getParameter("attendStaff"));
			mrequest.setSelectDept(request.getParameter("selectDept"));
			mrequest.setMeetingName(request.getParameter("meetingName"));
			mrequest.setMeetingTheme(request.getParameter("meetingTheme"));
			mrequest.setBoardroomId(request.getParameter("boardroomId"));
			mrequest.setBoardroomStaff(request.getParameter("boardroomStaff"));
			mrequest.setMeetingType(request.getParameter("meetingType"));
			mrequest.setMeetingStarttime(request.getParameter("meetingStarttime"));
			mrequest.setMeetingEndtime(request.getParameter("meetingEndtime"));
			mrequest.setMeetingDevice(request.getParameter("meetingDevice"));
			mrequest.setMeetingSumman(request.getParameter("meetingSumman"));
			mrequest.setAttachId(request.getParameter("attachId"));
			mrequest.setMeetingDescription(request.getParameter("meetingDescription"));
			mrequest.setCreateUser(account.getAccountId());
			mrequest.setCycEndtime(request.getParameter("cycEndtime"));
			if(request.getParameter("meetingType").equals("1")){
				mrequest.setCycType(request.getParameter("cycType"));
				}else{
					mrequest.setCycType("");
				}
			mrequest.setMeetingStatus("0");
			mrequest.setOrgId(account.getOrgId());
			
			String smsRemindStr = request.getParameter("smsReminds");
			mrequest.setWarnTime(request.getParameter("warnTime"));
			mrequest.setIsSms(smsRemindStr);
			data = meetingrequestloigc.updaterequestlogic(dbConn, mrequest);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 删除会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			String requestId=request.getParameter("requestId");
			String orgId=account.getOrgId();
			data = meetingrequestloigc.delrequestlogic(dbConn, requestId, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查询待通过会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void noratifyrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			data = meetingrequestloigc.noratifyrequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查询已准会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ratifyrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			data = meetingrequestloigc.ratifyrequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查询未批准的会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void notratifyrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			data = meetingrequestloigc.notratifyrequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 根据Id 查询会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingrequest mrequest=new Meetingrequest();
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			mrequest.setRequestId(request.getParameter("requestId"));
			mrequest.setOrgId(account.getOrgId());
			String accountId=account.getAccountId();
			data = meetingrequestloigc.getIdrequestlogic(dbConn, mrequest,accountId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查询待批的会议申请
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void waitrequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestloigc=new MeetingrequestLogic();
			data = meetingrequestloigc.waitrequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 已经批准了得会议申请信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void viarequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
			data = meetingrequestlogic.viarequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			
			
			dbConn.commit();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 根据审批人查询审批未通过的信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void notviarequestAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
			data = meetingrequestlogic.notviarequestlogic(dbConn, account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 审批通过处理
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void adoptmeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
			Meetingrequest mrequest=new Meetingrequest();
			mrequest.setRequestId(request.getParameter("requestId"));
			mrequest.setMeetingStatus("1");
			mrequest.setOrgId(account.getOrgId());
			data = meetingrequestlogic.adoptmeetinglogic(dbConn, mrequest);
			
			//关联日程安排
			CalendarLogic cllogic = new CalendarLogic();
			cllogic.settypeIddelClLogic(dbConn, request.getParameter("requestId"), account.getOrgId());
			Calendar cl = new Calendar();
			cl.setStartDate(request.getParameter("meetingStarttime"));
			cl.setEndDate(request.getParameter("meetingEndtime"));	
			String content="请准时参加会议:"+request.getParameter("meetingName")+"主题："+request.getParameter("meetingTheme");
			cl.setContent(content);
			cl.setFromType("meeting");
			cl.setCalendarPid(GuId.getGuid());
			cl.setFromTypeId(mrequest.getRequestId());
			cl.setFromId(request.getParameter("accountId"));
			cl.setOrgId(account.getOrgId());
			cl.setStatus("0");
			cl.setCalType("1");
			cl.setCalLevel("0");
			cl.setIsSms(request.getParameter("isSms"));
			cl.setBeforeTime(request.getParameter("warnTime"));
			String cycEndtime=request.getParameter("cycEndtime");
			String cycType=request.getParameter("cycType");
			if(request.getParameter("meetingType").equals("1")){
			cl.setCalAffType(request.getParameter("meetingType"));
			}
			Affair affair = new Affair();
			if(request.getParameter("meetingType").equals("1")){
				affair.setAffairId(GuId.getGuid());
				affair.setRemindType(cycType);
				affair.setFrequency("1");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
				Date time=format.parse(request.getParameter("meetingStarttime"));
				java.util.Calendar c = java.util.Calendar.getInstance();
				c.setTime(time);
				c.add(c.HOUR_OF_DAY, -2);
				affair.setRemindTime(SysTool.getDateTimeStr(c.getTime()).substring(11,SysTool.getDateTimeStr(c.getTime()).length()));
				if(cycType.equals("2")){
					
				}else if(cycType.equals("3")){
					String week = sdf2.format(time);
					affair.setRemindDate(week);
				}else if(cycType.equals("4")){
					affair.setRemindDate(c.get(java.util.Calendar.DAY_OF_MONTH)+"日");
				}else{
					affair.setRemindDate((c.get(java.util.Calendar.MONTH)+1)+"-"+c.get(java.util.Calendar.DATE));
				}
				affair.setOrgId(account.getOrgId());
				affair.setCalendarId(cl.getCalendarPid());
				affair.setEndWhile(cycEndtime);
				cllogic.addAffair(dbConn, affair);
				cl.setAffairId(affair.getAffairId());
			}else{
				cl.setAffairId("");
			}
			String[] userIds = null;
			String userId=request.getParameter("attendStaff");
			String userIdArr="";
			if(userId.equals("userAll")){
				userId=new AccountLogic().getorgIdAllLogic(dbConn, account.getOrgId());
				if(userId.indexOf(",")>-1){
					userIds = userId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						userIdArr+=userIds[i]+",";
					}
					cl.setUserId(userIdArr.substring(0,userIdArr.length()-1));
					for (int i = 0; i < userIds.length; i++) {
						cl.setAccountId(userIds[i]);
						cl.setAffairId(affair.getAffairId());
						cl.setCalendarId(GuId.getGuid());
						cllogic.addOrUpdateLogic(dbConn, cl,"1");
					}
				}else{
					cl.setCalendarId(GuId.getGuid());
					cl.setAccountId(userId);
					cl.setUserId(userId);
					cllogic.addOrUpdateLogic(dbConn, cl,"1");
					
				}
			}else{
			if(userId!=null){
				if(userId.indexOf(",")>-1){
					userIds = userId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						userIdArr+=userIds[i]+",";
					}
					cl.setUserId(userIdArr.substring(0,userIdArr.length()-1));
					for (int i = 0; i < userIds.length; i++) {
						cl.setAccountId(userIds[i]);
						cl.setCalendarId(GuId.getGuid());
						cllogic.addOrUpdateLogic(dbConn, cl,"1");
						
					}
				}else{
					cl.setCalendarId(GuId.getGuid());
					cl.setAccountId(userId);
					cl.setUserId(userId);
					cllogic.addOrUpdateLogic(dbConn, cl,"1");
					
				}
			} 
			}
			String smsRemindStr = request.getParameter("isSms");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			List<String> toAccountList = new ArrayList<String>();
			if(userId!=null){
				if(userId.equals("userAll")){
					userId=new AccountLogic().getorgIdAllLogic(dbConn, account.getOrgId());
					if(userId.indexOf(",")>-1){
						userIds = userId.split(",");
						for (int i = 0; i < userIds.length; i++) {
							toAccountList.add(userIds[i]);
						}
					}else{
						toAccountList.add(userId);
					}
				}else{
				if(userId.indexOf(",")>-1){
					userIds = userId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						toAccountList.add(userIds[i]);
					}
				}else{
					toAccountList.add(userId);
				}
				}
			} 
			String warnTimeArr=request.getParameter("warnTime");
			long bfTime = Integer.parseInt(warnTimeArr)*60*60*1000;
			SimpleDateFormat   formatter   = new   SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			long temp=formatter.parse(request.getParameter("meetingStarttime")).getTime()-bfTime;
			String sendTime=SysTool.getDateTimeStr(new Date(temp));
			MessageApi messageApi = new MessageApi();
			messageApi.addMessage(dbConn, "meeting", smsRemindJson, "您有个会议待参加", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
			
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 审批不通过处理
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void notadoptmeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				Meetingrequest mrequest=new Meetingrequest();
				mrequest.setRequestId(request.getParameter("requestId"));
				mrequest.setMeetingStatus("2");
				mrequest.setOrgId(account.getOrgId());
				data = meetingrequestlogic.notadoptmeetinglogic(dbConn, mrequest);
				if(data==1){
					CalendarLogic cllogic = new CalendarLogic();
					cllogic.settypeIddelClLogic(dbConn, request.getParameter("requestId"), account.getOrgId());
				}
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 根据纪要员获取已结束的会议并且没有添加会议纪要信息的列表
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getStaffmeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
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
				String beforetime=SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss");
				data = meetingrequestlogic.getStaffmeetinglogic(dbConn, account, pageSize, page, sortOrder, sortName,beforetime);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 根据纪要员获取已结束的会议并且添加了会议纪要信息的列表
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
				public void getalreadymeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
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
						String beforetime=SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss");
						data = meetingrequestlogic.getalreadymeetinglogic(dbConn, account, pageSize, page, sortOrder, sortName, beforetime);
						dbConn.commit();
						} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						DbOp dbop = new DbOp();
						dbop.connClose(dbConn);
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print(data);
						response.getWriter().flush();
						}
				}
				/**
				 * 根据出席人员获取出席人名称字符串
				 * Author Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
		public void getattendstaffAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				String attendStaff=request.getParameter("attendStaff");
				data = meetingrequestlogic.getattendstafflogic(dbConn, attendStaff);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 当纪要添加之后对会议信息中的纪要状态进行修改
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getIdsummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				Meetingrequest mrequest=new Meetingrequest();
				mrequest.setRequestId(request.getParameter("requestId"));
				mrequest.setSummaryStatus("1");;
				mrequest.setOrgId(account.getOrgId());
				data = meetingrequestlogic.getIdsummarylogic(dbConn, mrequest);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 多条件模糊查询会议记录
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void termquertmeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				Meetingrequest mrequest=new Meetingrequest();
				mrequest.setMeetingName(request.getParameter("meetingName"));
				mrequest.setCreateUser(request.getParameter("createUser"));
				String starttime=request.getParameter("starttime");
				String endtime=request.getParameter("endtime");
				mrequest.setBoardroomId(request.getParameter("boardroomId"));
				mrequest.setMeetingStatus(request.getParameter("meetingStatus"));
				String attendStaff=request.getParameter("attendStaff");
				mrequest.setOrgId(account.getOrgId());
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
				data = meetingrequestlogic.termquertmeetingAct(dbConn, mrequest, pageSize, page, sortOrder, sortName, starttime, endtime, attendStaff);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 根据会议室id 查询申请会议的记录
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getboardIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				String boardroomId=request.getParameter("boardroomId");
				String orgId=account.getOrgId();
				String stime=request.getParameter("stime");
				String etime=request.getParameter("etime");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				data = meetingrequestlogic.getboardroomlogic(dbConn, account.getAccountId(),boardroomId, orgId, stime, etime);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 根据会议室ID 修改会议审批人
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getlibIdupdateAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				String boardroomId=request.getParameter("boardroomId");
				String orgId=account.getOrgId();
				String boardroomStaff=request.getParameter("boardroomStaff");
				MeetingrequestLogic meetingrequestlogic=new MeetingrequestLogic();
				data = meetingrequestlogic.getlibIdupdatelogic(dbConn, boardroomId, orgId, boardroomStaff);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
}
