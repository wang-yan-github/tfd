package institution.act;

import institution.data.Directory;
import institution.logic.DirectoryLogic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class DirectoryAct {
	/**
	 * 得到目录与制度
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDirectoryList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new DirectoryLogic().getDirectoryList(dbConn,orgId);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 得到目录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDirectoryList2(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				String orgId = account.getOrgId();
				returnData = new DirectoryLogic().getDirectoryList2(dbConn,orgId);
				dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 添加目录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addDirectory(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try {String name = request.getParameter("name");
			String topId = request.getParameter("topId");
			String dirId = GuId.getGuid();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			String all_Dir = null;
			if(!topId.equals("")){
				all_Dir = new DirectoryLogic().getAllDirById(dbConn, topId);
				all_Dir = all_Dir +"/"+name;
			}else{
				all_Dir = name;
			}
			
			Directory dir = new Directory(dirId,name,topId,all_Dir,account.getOrgId());
			
			returnData = new DirectoryLogic().addDirectory(dbConn, dir);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id得到单个的目录信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDirectoryById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			String dirId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String orgId = account.getOrgId();
		 	returnData = new DirectoryLogic().getDirectoryById(dbConn,dirId,orgId);
		 	dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id删除目录信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delDirectoryById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try {
			String id = request.getParameter("id");
			
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new DirectoryLogic().delDirectoryById(dbConn,id,orgId);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id修改目录信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateDirectoryById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String topId = request.getParameter("topId");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			String all_Dir = new DirectoryLogic().getAllDirById(dbConn, topId);
			all_Dir = all_Dir +"/"+name;
			Directory dir = new Directory(id,name,topId,all_Dir,account.getOrgId());
			returnData = new DirectoryLogic().updateDirectoryById(dbConn,dir);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
