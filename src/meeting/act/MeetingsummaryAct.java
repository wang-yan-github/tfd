package meeting.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.data.Meetingsummary;
import meeting.logic.MeetingsummaryLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class MeetingsummaryAct {

	/**
	 * 添加纪要
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addsummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingsummary summary=new Meetingsummary();
			MeetingsummaryLogic meetingsummaryLogic=new MeetingsummaryLogic();
			summary.setSummaryId(GuId.getGuid());
			summary.setLookStaff(request.getParameter("lookStaff"));
			summary.setRequestId(request.getParameter("requestId"));
			summary.setMeetingName(request.getParameter("meetingName"));
			summary.setRealityStaff(request.getParameter("realityStaff"));
			summary.setAskStaff(request.getParameter("askStaff"));
			summary.setSummaryContent(request.getParameter("summaryContent"));
			summary.setSummaryStaff(account.getAccountId());
			summary.setAttachId(request.getParameter("attachId"));
			summary.setOrgId(account.getOrgId());
			data = meetingsummaryLogic.addsummarylogic(dbConn, summary);
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
	 * 修改会议纪要信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatesummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingsummary summary=new Meetingsummary();
			MeetingsummaryLogic meetingsummaryLogic=new MeetingsummaryLogic();
			summary.setRequestId(request.getParameter("requestId"));
			summary.setLookStaff(request.getParameter("lookStaff"));
			summary.setRealityStaff(request.getParameter("realityStaff"));
			summary.setSummaryContent(request.getParameter("summaryContent"));
			summary.setAttachId(request.getParameter("attachId"));
			summary.setOrgId(account.getOrgId());
			data = meetingsummaryLogic.updatesummarylogic(dbConn, summary);
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
	 * 获取会议纪要列表
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void looksummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			MeetingsummaryLogic meetingsummaryLogic=new MeetingsummaryLogic();
			data = meetingsummaryLogic.looksummarylogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据会议ID 获取纪要信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdsummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingsummary summary=new Meetingsummary();
			MeetingsummaryLogic meetingsummarylogic=new MeetingsummaryLogic();
			summary.setRequestId(request.getParameter("requestId"));
			summary.setOrgId(account.getOrgId());
			data = meetingsummarylogic.getIdsummarylogic(dbConn, summary);
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
	 * 模糊查询 根据多条件查询纪要信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void tremsummaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Meetingsummary summary=new Meetingsummary();
			MeetingsummaryLogic meetingsummarylogic=new MeetingsummaryLogic();
			String lookstaff=account.getAccountId();
			summary.setOrgId(account.getOrgId());
			summary.setMeetingName(request.getParameter("meetingName"));
			summary.setAskStaff(request.getParameter("askStaff"));
			String contentone=request.getParameter("contentone");
			String contenttwo=request.getParameter("contenttwo");
			String contentthree=request.getParameter("contentthree");
			data = meetingsummarylogic.tremsummarylogic(dbConn, summary, lookstaff,contentone, contenttwo, contentthree, pageSize, page, sortOrder, sortName);
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
	 * 根据summaryID获取纪要详细信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getsummaryIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Meetingsummary summary=new Meetingsummary();
			MeetingsummaryLogic meetingsummarylogic=new MeetingsummaryLogic();
			summary.setSummaryId(request.getParameter("summaryId"));
			summary.setOrgId(account.getOrgId());
			data = meetingsummarylogic.getsummaryIdlogic(dbConn, summary);
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
