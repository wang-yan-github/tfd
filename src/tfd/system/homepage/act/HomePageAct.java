package tfd.system.homepage.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.homepage.logic.HomePageLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class HomePageAct {
	/**
	 * 获取首页数据
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getHomePage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			returnData = new HomePageLogic().getHomePage(dbConn,account);
			dbConn.commit();
		}catch(Exception e){
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
	 * 修改首页信息
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateHomePage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String id = request.getParameter("id");
			String addressTd = request.getParameter("addressTd");
			String addressTr = request.getParameter("addressTr");
			new HomePageLogic().updateHomePage(dbConn,account,id,addressTd,addressTr);
			dbConn.commit();
		}catch(Exception e){
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
	 * 开关首页模块
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void closeModule(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData= 0 ;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String id = request.getParameter("id");
			String isOpen = request.getParameter("isOpen");
			returnData = new HomePageLogic().closeModule(dbConn,account,id,isOpen);
			dbConn.commit();
		}catch(Exception e){
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
	 * 获取首页数据
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getModuleById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String id = request.getParameter("id");
			returnData = new HomePageLogic().getModuleById(dbConn,account,id);
			dbConn.commit();
		}catch(Exception e){
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
	 * 开关首页模块
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateModuleById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData= 0 ;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String id = request.getParameter("id");
			String isOpen = request.getParameter("isOpen");
			String moduleName = request.getParameter("moduleName");
			returnData = new HomePageLogic().updateModuleById(dbConn,account,id,isOpen,moduleName);
			dbConn.commit();
		}catch(Exception e){
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
