package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import officesupplies.data.Offres;
import officesupplies.logic.Offreslogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class OffresAct {
	/**
	 * 添加办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setResId(GuId.getGuid());
			res.setResName(request.getParameter("resName"));
			res.setResType(request.getParameter("resType"));
			res.setLibraryId(request.getParameter("libraryId"));
			res.setClassifyId(request.getParameter("classifyId"));
			res.setBeforStock(request.getParameter("beforeStock"));
			res.setMinStock(request.getParameter("minStock"));
			res.setMaxStock(request.getParameter("maxStock"));
			res.setApproveStaff(request.getParameter("approveStaff"));
			res.setDispatchStaff(request.getParameter("dispatchStaff"));
			res.setResFormat(request.getParameter("resFormat"));
			res.setAttachId(request.getParameter("attachId"));
			res.setResUnit(request.getParameter("resUnit"));
			res.setResPrice(request.getParameter("resPrice"));
			res.setResSupplier(request.getParameter("resSupplier"));
			res.setDeptPriv(request.getParameter("deptPriv"));
			res.setUserPriv(request.getParameter("userPriv"));
			res.setOrgId(account.getOrgId());
			data = reslogic.addreslogic(dbConn, res);
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
	 * 根据Id查询办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setResId(request.getParameter("resId"));
			res.setOrgId(account.getOrgId());
			data = reslogic.getIdreslogic(dbConn, res);
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
	 * 根据Id删除办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delIdresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setResId(request.getParameter("resId"));
			res.setOrgId(account.getOrgId());
			data = reslogic.delIdreslogic(dbConn, res);
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
	 * 根据Id修改办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setResId(request.getParameter("resId"));
			res.setResName(request.getParameter("resName"));
			res.setResType(request.getParameter("resType"));
			res.setLibraryId(request.getParameter("libraryId"));
			res.setClassifyId(request.getParameter("classifyId"));
			res.setBeforStock(request.getParameter("beforeStock"));
			res.setMinStock(request.getParameter("minStock"));
			res.setMaxStock(request.getParameter("maxStock"));
			res.setApproveStaff(request.getParameter("approveStaff"));
			res.setResFormat(request.getParameter("resFormat"));
			res.setAttachId(request.getParameter("attachId"));
			res.setResUnit(request.getParameter("resUnit"));
			res.setResPrice(request.getParameter("resPrice"));
			res.setResSupplier(request.getParameter("resSupplier"));
			res.setDeptPriv(request.getParameter("deptPriv"));
			res.setUserPriv(request.getParameter("userPriv"));
			res.setOrgId(account.getOrgId());
			data = reslogic.updatereslogic(dbConn, res);
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
	 * 根据classifyId（分类库Id）获取办公用品的列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getlibIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offreslogic reslogic=new Offreslogic();
			String classifyId=request.getParameter("classifyId");
			String orgId=account.getOrgId();
			data = reslogic.getlibIdlogic(dbConn, classifyId, orgId, pageSize, page, sortOrder, sortName,account.getAccountId());
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
	 * 根据条件查询办公用品信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void tremqueryresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setLibraryId(request.getParameter("libraryId"));
			res.setClassifyId(request.getParameter("classifyId"));
			res.setResId(request.getParameter("resId"));
			res.setResName(request.getParameter("resName"));
			res.setApproveStaff(account.getAccountId());
			res.setOrgId(account.getOrgId());
			data = reslogic.tremqueryreslogic(dbConn, res, pageSize, page, sortOrder, sortName);
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
	 * 根据classifyId获取办公用品名称
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getresNameAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offres res=new Offres();
			Offreslogic reslogic=new Offreslogic();
			res.setClassifyId(request.getParameter("classifyId"));
			res.setOrgId(account.getOrgId());
			data = reslogic.getresNamelogic(dbConn, res);
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
	 * 采购入库情况根据resId 修改办公用品(+)
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateresNumAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offreslogic reslogic=new Offreslogic();
			int maintNum=Integer.parseInt(request.getParameter("maintNum"));
			String resId=request.getParameter("resId");
			String orgId=account.getOrgId();
			data = reslogic.updateresNumlogic(dbConn, resId, orgId, maintNum);
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
	 * 根据resId 修改办公用品数量 （-）
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void prrupdateresNumAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offreslogic reslogic=new Offreslogic();
				int maintNum=Integer.parseInt(request.getParameter("maintNum"));
				String resId=request.getParameter("resId");
				String orgId=account.getOrgId();
				data = reslogic.prrupdateresNumlogic(dbConn, resId, orgId, maintNum);
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
		 * 根据申领人权限获取办公用品
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getapplyresAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				String deptId=","+(String) request.getSession().getAttribute("DEPT_ID")+",";
				String classifyId=request.getParameter("classifyId");
				Offreslogic reslogic=new Offreslogic();
				data = reslogic.getapplyreslogic(dbConn, account, deptId, classifyId);
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
