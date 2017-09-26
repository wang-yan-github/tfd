package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import officesupplies.data.Offmaint;
import officesupplies.logic.Offmaintlogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class OffmaintAct {
	/**
	 * 添加办公用品 维护信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addmaintAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offmaint maint=new Offmaint();
			Offmaintlogic maintlogic=new Offmaintlogic();
			maint.setMaintId(GuId.getGuid());
			maint.setMaintType(request.getParameter("maintType"));
			maint.setLibraryId(request.getParameter("libraryId"));
			maint.setClassifyId(request.getParameter("classifyId"));
			maint.setResId(request.getParameter("resId"));
			maint.setResPrice(request.getParameter("resPrice"));
			maint.setMaintNum(request.getParameter("maintNum"));
			maint.setMaintRemary(request.getParameter("maintRemary"));
			maint.setMaintTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			maint.setCreateUser(account.getAccountId());
			maint.setOrgId(account.getOrgId());
			data = maintlogic.addmaintlogic(dbConn, maint);
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
	 * 根据创建人createuser获取库存维护记录信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUsermaintAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Offmaintlogic maintlogic=new Offmaintlogic();
			data = maintlogic.getUsermaintlogic(dbConn, account, pageSize, page, sortOrder, sortName);
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
	 * 根据maintId 获取维护信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offmaintlogic maintlogic=new Offmaintlogic();
			String maintId=request.getParameter("maintId");
			String orgId=account.getOrgId();
			data = maintlogic.getIdlogic(dbConn, maintId, orgId);
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
	 * 根据maintId 修改维护信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatemaintAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offmaint maint=new Offmaint();
			Offmaintlogic maintlogic=new Offmaintlogic();
			maint.setMaintId(request.getParameter("maintId"));
			maint.setResPrice(request.getParameter("resPrice"));
			maint.setMaintNum(request.getParameter("maintNum"));
			maint.setMaintRemary(request.getParameter("maintRemary"));
			maint.setMaintTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			maint.setOrgId(account.getOrgId());
			data = maintlogic.updatemaintlogic(dbConn, maint);
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
	 * 根据maintId删除库存维护信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delmaintAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offmaintlogic maintlogic=new Offmaintlogic();
			String maintId=request.getParameter("maintId");
			String orgId=account.getOrgId();
			data = maintlogic.delmaintlogic(dbConn, maintId, orgId);
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
