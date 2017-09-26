package tfd.system.workflow.worklist.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class WorkListAct {
	//获取当前待办记灵数
	public void getFlowWorkCountAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData="";
		Connection dbConn=null;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			WorkListLogic workListLogic = new WorkListLogic();
			returnData =workListLogic.getFlowWorkCountLogic(dbConn, account);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	//获取前5条待办工作
	public void getFristWorkListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData="";
		Connection dbConn=null;
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		WorkListLogic workListLogic = new WorkListLogic();
		returnData = workListLogic.getFristWorkFlowLogic(dbConn, account);
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
	//获取待办工作
	public void getDoWorkListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
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
		String module=request.getParameter("module");
		String flowName=request.getParameter("flowName");
		String runId = request.getParameter("runId");
		String flowTitle = request.getParameter("flowTitle");
		String beginUser=request.getParameter("beginUser");
		Connection dbConn = null;
		String returnData="";
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		WorkListLogic doWorkListLogic = new WorkListLogic();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(module);
		pramList.add(account.getOrgId());
		Map<String,String> map = new HashMap<String,String>();
		map.put("flowName", flowName);
		map.put("runId",runId);
		map.put("flowTitle", flowTitle);
		map.put("beginUser", beginUser);
		returnData=doWorkListLogic.getDoWorkListLogic(dbConn,pramList,pageSize,page,sortOrder,sortName,map);
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
	//获取过程中流程
	public void getProcessAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		try{
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
		String module=request.getParameter("module");
		String flowName=request.getParameter("flowName");
		String runId = request.getParameter("runId");
		String flowTitle = request.getParameter("flowTitle");
		String beginUser=request.getParameter("beginUser");
		Map<String,String> map = new HashMap<String,String>();
		map.put("flowName", flowName);
		map.put("runId",runId);
		map.put("flowTitle", flowTitle);
		map.put("beginUser", beginUser);
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getOrgId());
		pramList.add(module);
		pramList.add("%,"+account.getAccountId()+",%");
		WorkListLogic doWorkListLogic = new WorkListLogic();
		returnData=doWorkListLogic.getProcessLogic(dbConn,pramList,pageSize,page,sortOrder,sortName,map);
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
	//获取结束流程
	public void getEndWorkAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String returnData="";
		try{
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
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String module=request.getParameter("module");
		String flowName=request.getParameter("flowName");
		String runId = request.getParameter("runId");
		String flowTitle = request.getParameter("flowTitle");
		String beginUser=request.getParameter("beginUser");
		Map<String,String> map = new HashMap<String,String>();
		map.put("flowName", flowName);
		map.put("runId",runId);
		map.put("flowTitle", flowTitle);
		map.put("beginUser", beginUser);
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getOrgId());
		pramList.add(module);
		pramList.add("%,"+account.getAccountId()+",%");
		dbConn = DbPoolConnection.getInstance().getConnection();
		WorkListLogic doWorkListLogic = new WorkListLogic();
		returnData=doWorkListLogic.getEndWorkLogic(dbConn,pramList,pageSize,page,sortOrder,sortName,map);
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
