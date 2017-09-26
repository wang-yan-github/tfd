package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import officesupplies.data.Offresapply;
import officesupplies.logic.Offresapplylogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class OffresapplyAct {

	/**
	 * 添加申领办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapply apply=new Offresapply();
			Offresapplylogic applylogic=new Offresapplylogic();
			apply.setApplyId(GuId.getGuid());
			apply.setLibraryId(request.getParameter("libraryId"));
			apply.setClassifyId(request.getParameter("classifyId"));
			apply.setResId(request.getParameter("resId"));
			apply.setResType(request.getParameter("resType"));
			apply.setApplyNum(request.getParameter("applyNum"));
			apply.setApprovalStaff(request.getParameter("approvalStaff"));
			apply.setApplyRemary(request.getParameter("applyRemary"));
			apply.setApplyTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			apply.setDispatchStaff(request.getParameter("dispatchStaff"));
			apply.setOrgId(account.getOrgId());
			apply.setCreateUser(account.getAccountId());
			data = applylogic.addapplylogic(dbConn, apply);
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
	 * 根据Id查询申领办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getapplyIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapply apply=new Offresapply();
			Offresapplylogic applylogic=new Offresapplylogic();
			apply.setApplyId(request.getParameter("applyId"));
			apply.setOrgId(account.getOrgId());
			data = applylogic.getapplyIdAct(dbConn, apply);
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
	 * 修改申领办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapply apply=new Offresapply();
			Offresapplylogic applylogic=new Offresapplylogic();
			apply.setApplyId(request.getParameter("applyId"));
			apply.setLibraryId(request.getParameter("libraryId"));
			apply.setClassifyId(request.getParameter("classifyId"));
			apply.setResId(request.getParameter("resId"));
			apply.setResType(request.getParameter("resType"));
			apply.setApplyNum(request.getParameter("applyNum"));
			apply.setApprovalStaff(request.getParameter("approvalStaff"));
			apply.setApplyRemary(request.getParameter("applyRemary"));
			apply.setApplyTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			apply.setDispatchStaff(request.getParameter("dispatchStaff"));
			apply.setOrgId(account.getOrgId());
			apply.setCreateUser(account.getAccountId());
			data = applylogic.updateapplylogic(dbConn, apply);
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
	 * 根据审批人查看待审批的办公用品信息表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getnotapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offresapplylogic applylogic=new Offresapplylogic();
			data = applylogic.getnotapplylogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据createUser获取办公用品申领信息的列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getuserapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offresapplylogic applylogic=new Offresapplylogic();
			data = applylogic.getuserapplylogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据applyId 删除申领记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapply apply=new Offresapply();
			Offresapplylogic applylogic=new Offresapplylogic();
			apply.setApplyId(request.getParameter("applyId"));
			apply.setOrgId(account.getOrgId());
			data = applylogic.delapplylogic(dbConn, apply);
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
	 * 代登记的情况下添加申领记录
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void agentapplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapply apply=new Offresapply();
			Offresapplylogic applylogic=new Offresapplylogic();
			apply.setApplyId(GuId.getGuid());
			apply.setLibraryId(request.getParameter("libraryId"));
			apply.setClassifyId(request.getParameter("classifyId"));
			apply.setResId(request.getParameter("resId"));
			apply.setResType(request.getParameter("resType"));
			apply.setApplyNum(request.getParameter("applyNum"));
			apply.setApprovalStaff(account.getAccountId());
			apply.setApplyRemary(request.getParameter("applyRemary"));
			apply.setApplyTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			apply.setDispatchStaff(request.getParameter("dispatchStaff"));
			apply.setOrgId(account.getOrgId());
			apply.setCreateUser(request.getParameter("createUser"));
			apply.setApplyStatus("1");
			data = applylogic.agentapplylogic(dbConn, apply);
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
	 * 根据发放人获取未发放办公用品的申领信息列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getdisStaffAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offresapplylogic applylogic=new Offresapplylogic();
			data = applylogic.getdisStafflogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据发放人获取已发放办公用品的申领信息列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getalreadyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offresapplylogic applylogic=new Offresapplylogic();
			data = applylogic.getalreadylogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据applyId 改变发放状态
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updategiveAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapplylogic applylogic=new Offresapplylogic();
			Offresapply apply=new Offresapply();
			apply.setApplyId(request.getParameter("applyId"));
			apply.setOrgId(account.getOrgId());
			data = applylogic.updategivelogic(dbConn, apply);
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
	 * 根据applyId 改变审批状态
	 * Author:Wp
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
			Offresapplylogic applylogic=new Offresapplylogic();
			Offresapply apply=new Offresapply();
			apply.setApplyId(request.getParameter("applyId"));
			apply.setApplyStatus(request.getParameter("applyStatus"));
			apply.setOrgId(account.getOrgId());
			data = applylogic.updateapprovallogic(dbConn, apply);
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
	 * 根据applyId修改审批人员
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateStaffAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offresapplylogic applylogic=new Offresapplylogic();
			String applyId=request.getParameter("applyId");
			String approvalStaff=request.getParameter("approvalStaff");
			String orgId=account.getOrgId();
			data = applylogic.updateStafflogic(dbConn, applyId, approvalStaff, orgId);
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
	 * 根据审批人员获取已审批的申领信息列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void alreadlyaplyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offresapplylogic applylogic=new Offresapplylogic();
			Offresapply apply=new Offresapply();
			apply.setApprovalStaff(account.getAccountId());
			apply.setOrgId(account.getOrgId());
			data = applylogic.alreadlyaplylogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
