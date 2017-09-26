package customermanage.act;

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
import calendar.data.Calendar;
import calendar.logic.CalendarLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import customermanage.data.CustomerRecord;
import customermanage.logic.CustomerrecordLogic;

public class CustomerrecordAct {

	/**
	 * 添加联系记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addrecordAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			CustomerRecord customerRecord=new CustomerRecord();
			customerRecord.setRecordId(GuId.getGuid());
			customerRecord.setCustomerId(request.getParameter("customerId"));
			customerRecord.setLinkmanId(request.getParameter("linkmanId"));
			customerRecord.setRecordContent(request.getParameter("recordContent"));
			customerRecord.setRecordWarn(request.getParameter("recordWarn"));
			customerRecord.setRecordlinkType(request.getParameter("recordlinkType"));
			customerRecord.setRecordTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd"));
			customerRecord.setOrgId(account.getOrgId());
			customerRecord.setAccountId(account.getAccountId());
			data = customerrecordLogic.addrecordLogic(dbConn, customerRecord);
			//添加日程安排
			if(!request.getParameter("recordWarn").equals("")){
			Calendar cl = new Calendar();
			cl.setCalendarId(GuId.getGuid());
			cl.setCalendarPid(cl.getCalendarId());
			cl.setAccountId(account.getAccountId());
			String startDate = request.getParameter("recordWarn")+" 09:00";
			String endDate=request.getParameter("recordWarn")+" 23:59";
			cl.setStartDate(startDate);
			cl.setEndDate(endDate);	
			cl.setContent(request.getParameter("WarnContent"));
			cl.setFromType("customer");
			cl.setFromTypeId(customerRecord.getRecordId());
			String smsRemindStr = request.getParameter("smsReminds");			
			cl.setIsSms(smsRemindStr);
			cl.setBeforeTime("0");
			cl.setFromId(account.getAccountId());
			cl.setOrgId(account.getOrgId());
			cl.setStatus("0");
			cl.setCalType("1");
			cl.setCalLevel("0");
			cl.setAffairId("");
			cl.setUserId(account.getAccountId());
			cl.setFromId(account.getAccountId());
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			CalendarLogic cllogic = new CalendarLogic();
			cllogic.addOrUpdateLogic(dbConn, cl,"1");
			List<String> toAccountList = new ArrayList<String>();
			toAccountList.add(account.getAccountId());
			MessageApi messageApi = new MessageApi();
			messageApi.addMessage(dbConn, "customer", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList,account.getOrgId(),startDate);
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
	 * 根据custoemrId获取 联系记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getcIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
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
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			String customerId=request.getParameter("customerId");
			String orgId=account.getOrgId();
			data = customerrecordLogic.getcIdLogic(dbConn, customerId, orgId, pageSize, page, sortOrder, sortName);
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
	 * 根据recordId查询联系记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getrecordIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			String recordId=request.getParameter("recordId");
			String orgId=account.getOrgId();
			data = customerrecordLogic.getrecordIdLogic(dbConn, recordId, orgId);
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
	 * 根据recordId删除联系记录信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delrecordAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			String recordId=request.getParameter("recordId");
			String orgId=account.getOrgId();
			data = customerrecordLogic.delrecordLogic(dbConn, recordId, orgId);
			if(data==1){
				CalendarLogic cllogic = new CalendarLogic();
				cllogic.settypeIddelClLogic(dbConn, recordId, orgId);
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
	 * 根据recordId修改联系记录信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updaterecordAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			CustomerRecord customerRecord=new CustomerRecord();
			customerRecord.setRecordId(request.getParameter("recordId"));
			customerRecord.setLinkmanId(request.getParameter("linkmanId"));
			customerRecord.setRecordContent(request.getParameter("recordContent"));
			customerRecord.setRecordWarn(request.getParameter("recordWarn"));
			customerRecord.setRecordlinkType(request.getParameter("recordlinkType"));
			customerRecord.setOrgId(account.getOrgId());
			SimpleDateFormat   formatter   = new   SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Date beforetime=formatter.parse(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			Date retime=formatter.parse(request.getParameter("recordWarn")+" 09:00:00");
			data = customerrecordLogic.updaterecordLogic(dbConn, customerRecord);
			if((!request.getParameter("time").equals(request.getParameter("recordWarn"))&& beforetime.getTime()<retime.getTime())|| !request.getParameter("staff").equals(request.getParameter("linkmanId"))){
				//修改日程安排
				Calendar cl = new Calendar();
				cl.setCalendarId(GuId.getGuid());
				cl.setAccountId(account.getAccountId());
				String startDate = request.getParameter("recordWarn")+" 09:00";
				String endDate=request.getParameter("recordWarn")+" 23:59";
				cl.setStartDate(startDate);
				cl.setEndDate(endDate);	
				cl.setContent(request.getParameter("WarnContent"));
				cl.setFromType("customer");
				cl.setFromTypeId(request.getParameter("recordId"));
				String smsRemindStr = request.getParameter("smsReminds");
				cl.setIsSms(smsRemindStr);
				cl.setOrgId(account.getOrgId());
				JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
				CalendarLogic cllogic = new CalendarLogic();
				cllogic.settypeupdateClLogic(dbConn, cl);
				List<String> toAccountList = new ArrayList<String>();
				toAccountList.add(account.getAccountId());
				MessageApi messageApi = new MessageApi();
				messageApi.addMessage(dbConn, "customer", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList,account.getOrgId(),startDate);
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
	 * 获取该客户最近的联系记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getrecordAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			String customerId=request.getParameter("customerId");
			data = customerrecordLogic.getrecordLogic(dbConn, customerId, account);
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
	 * 条件查询联系记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void termQueryAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
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
			CustomerrecordLogic customerrecordLogic=new CustomerrecordLogic();
			String recordTime=request.getParameter("recordTime");
			String linkmanId=request.getParameter("linkmanId");
			String customerId=request.getParameter("customerId");
			data = customerrecordLogic.termQueryLogic(dbConn, customerId, account, pageSize, page, sortOrder, sortName, linkmanId, recordTime);
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
