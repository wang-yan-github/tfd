package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import officesupplies.data.Offallot;
import officesupplies.logic.Offallotlogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class OffallotAct {
	/**
	 * 添加办公用品调拨记录
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addallotAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offallot allot=new Offallot();
			Offallotlogic allotlogic=new Offallotlogic();
			allot.setAllotId(GuId.getGuid());
			allot.setResId(request.getParameter("resId"));
			allot.setLibraryId(request.getParameter("libraryId"));
			allot.setClassifyId(request.getParameter("classifyId"));
			allot.setAllotNum(request.getParameter("allotNum"));
			allot.setAllotStatus(request.getParameter("allotStatus"));
			allot.setApprovalStaff(request.getParameter("approvalStaff"));
			allot.setAllotTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			allot.setAllotStaff(account.getAccountId());
			allot.setDeptoStaff(request.getParameter("deptoStaff"));
			allot.setOrgId(account.getOrgId());
			data = allotlogic.addallotlogic(dbConn, allot);
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
	 * 根据审批人员approvalStaff获取待审批的调拨申请记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getnotallotAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offallotlogic allotlogic=new Offallotlogic();
			data = allotlogic.getnotallotlogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据approvalStaff获取已审批的调拨申请记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void getallotAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
				Offallotlogic allotlogic=new Offallotlogic();
				data = allotlogic.getallotlogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
		 * 根据allotId 修改调拨状态
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
	public void updateStatusAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn =null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offallot allot=new Offallot();
			Offallotlogic allotlogic=new Offallotlogic();
			allot.setAllotId(request.getParameter("allotId"));
			allot.setAllotStatus(request.getParameter("allotStatus"));
			allot.setOrgId(account.getOrgId());
			data = allotlogic.updateStatuslogic(dbConn, allot);
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
	 * 根据allotId查询调拨信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdallotAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offallotlogic allotlogic=new Offallotlogic();
			String allotId=request.getParameter("allotId");
			String orgId=account.getOrgId();
			data = allotlogic.getIdallotlogic(dbConn, allotId, orgId);
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
