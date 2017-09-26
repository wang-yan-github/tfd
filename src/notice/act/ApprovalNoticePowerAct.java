package notice.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.data.ApprovalNoticePower;
import notice.logic.ApprovalNoticePowerLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class ApprovalNoticePowerAct {

	/**
	 * 设置公告审批人员
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addnoticepowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNoticePower approvalnoticepower=new ApprovalNoticePower();
			ApprovalNoticePowerLogic approvalnoticepowerlogic=new ApprovalNoticePowerLogic();
			approvalnoticepower.setPowerId(GuId.getGuid());
			approvalnoticepower.setNoticeType(request.getParameter("noticeType"));
			approvalnoticepower.setApprovalStaff(request.getParameter("approvalStaff"));
			approvalnoticepower.setOrgId(account.getOrgId());
			data = approvalnoticepowerlogic.addnoticepowerlogic(dbConn, approvalnoticepower);
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
	 * 查看已设置的公告审批人员表信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookpowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			ApprovalNoticePowerLogic approvalnoticepowerlogic=new ApprovalNoticePowerLogic();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data = approvalnoticepowerlogic.lookpowerlogic(dbConn, account);
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
	 * 修改通知公告审批人员
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatepowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNoticePower approvalnoticepower=new ApprovalNoticePower();
			ApprovalNoticePowerLogic approvalnoticepowerlogic=new ApprovalNoticePowerLogic();
			approvalnoticepower.setPowerId(request.getParameter("powerId"));
			approvalnoticepower.setNoticeType(request.getParameter("noticeType"));
			approvalnoticepower.setApprovalStaff(request.getParameter("approvalStaff"));
			approvalnoticepower.setOrgId(account.getOrgId());
			data = approvalnoticepowerlogic.updatepowerlogic(dbConn, approvalnoticepower);
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
	 * 根据公告类型查询审批人员
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void looktypeapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			ApprovalNoticePower approvalnoticepower=new ApprovalNoticePower();
			ApprovalNoticePowerLogic approvalnoticepowerlogic=new ApprovalNoticePowerLogic();
			approvalnoticepower.setNoticeType(request.getParameter("noticeType"));
			approvalnoticepower.setOrgId(account.getOrgId());
			data = approvalnoticepowerlogic.looktypeapprovallogic(dbConn, approvalnoticepower);
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
}
