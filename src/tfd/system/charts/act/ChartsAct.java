package tfd.system.charts.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.charts.logic.ChartsLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class ChartsAct {
	
	/**
	 * 获取图表数据
	 * Time:2016-01-08
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getChartsData(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String priv = request.getParameter("priv");
			String type = request.getParameter("type");
			returnData = new ChartsLogic().getChartsData(dbConn,account,priv,type);
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
	
	public void getChartsModules(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			returnData = new ChartsLogic().getChartsModules(dbConn,account.getOrgId());
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
	
	public void getPrivByModule(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String module = request.getParameter("module");
			returnData = new ChartsLogic().getPrivByModule(dbConn,account.getOrgId(),module);
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
	
	public void setPrivByModule(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData= 0 ;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String chartsConfigId = request.getParameter("chartsConfigId");
			String accountId = request.getParameter("User");
			String deptId = request.getParameter("Dept");
			String privId = request.getParameter("Priv");
			returnData = new ChartsLogic().setPrivByModule(dbConn,chartsConfigId,accountId,deptId,privId);
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
	
	public void getChartsModulesByPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			returnData = new ChartsLogic().getChartsModulesByPriv(dbConn,account);
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
