package notice.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.data.ApprovalNotice;
import notice.logic.ApprovalNoticeLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class ApprovalNoticeAct {

	/**
	 * 添加通知公告审批意见
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNotice approvalnotice=new ApprovalNotice();
			ApprovalNoticeLogic approvalNoticeLogic=new ApprovalNoticeLogic();
			approvalnotice.setApprovalId(GuId.getGuid());
			approvalnotice.setAccountId(account.getAccountId());
			approvalnotice.setNoticeId(request.getParameter("noticeId"));
			approvalnotice.setApprovalContent(request.getParameter("approvalContent"));
			approvalnotice.setApprovalTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			approvalnotice.setOrgId(account.getOrgId());
			data = approvalNoticeLogic.addapprovalLogic(dbConn, approvalnotice);
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
	 * 查看审批意见
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNotice approvalnotice=new ApprovalNotice();
			ApprovalNoticeLogic approvalNoticeLogic=new ApprovalNoticeLogic();
			approvalnotice.setNoticeId(request.getParameter("noticeId"));
			approvalnotice.setOrgId(account.getOrgId());
			data = approvalNoticeLogic.lookapprovalLogic(dbConn, approvalnotice);
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
	 * 根据通知公告Id 修改公告审批意见
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNotice approvalnotice=new ApprovalNotice();
			ApprovalNoticeLogic approvalNoticeLogic=new ApprovalNoticeLogic();
			approvalnotice.setAccountId(account.getAccountId());
			approvalnotice.setNoticeId(request.getParameter("noticeId"));
			approvalnotice.setApprovalContent(request.getParameter("approvalContent"));
			approvalnotice.setApprovalTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			data = approvalNoticeLogic.updateapprovalLogic(dbConn, approvalnotice);
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
