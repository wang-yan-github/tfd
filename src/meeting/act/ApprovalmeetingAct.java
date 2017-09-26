package meeting.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.data.Approvalmeeting;
import meeting.logic.Approvalmeetinglogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class ApprovalmeetingAct {

	/**
	 * 添加会议审批意见
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addappmeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Approvalmeeting approvalmeeting=new Approvalmeeting();
			Approvalmeetinglogic approvalmeetinglogic=new Approvalmeetinglogic(); 
			approvalmeeting.setApprovalId(GuId.getGuid());
			approvalmeeting.setApprovalContent(request.getParameter("approvalContent"));
			approvalmeeting.setApprovalTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			approvalmeeting.setMeetingId(request.getParameter("meetingId"));
			approvalmeeting.setAccountId(account.getAccountId());
			approvalmeeting.setOrgId(account.getOrgId());
			data = approvalmeetinglogic.addappmeetinglogic(dbConn, approvalmeeting);
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
	 * 根据会议ID 进行修改审批意见
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatemeetingAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Approvalmeeting approvalmeeting=new Approvalmeeting();
			Approvalmeetinglogic approvalmeetinglogic=new Approvalmeetinglogic(); 
			approvalmeeting.setMeetingId(request.getParameter("meetingId"));
			approvalmeeting.setApprovalContent(request.getParameter("approvalContent"));
			approvalmeeting.setOrgId(account.getOrgId());
			approvalmeeting.setApprovalTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			data = approvalmeetinglogic.updatemeetinglogic(dbConn, approvalmeeting);
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
	 * 根据会议ID 查询审批意见
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getmeetingIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Approvalmeeting approvalmeeting=new Approvalmeeting();
			Approvalmeetinglogic approvalmeetinglogic=new Approvalmeetinglogic(); 
			approvalmeeting.setMeetingId(request.getParameter("meetingId"));
			approvalmeeting.setOrgId(account.getOrgId());
			data = approvalmeetinglogic.getmeetingIdlogic(dbConn, approvalmeeting);
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
