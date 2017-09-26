package tfd.system.workflow.workflowtype.act;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.workflowtype.data.WorkFlowType;
import tfd.system.workflow.workflowtype.logic.WorkFlowTypeLogic;

public class WorkFlowTypeAct {
//新建流程分类
public String addWorkFlowTypeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	WorkFlowType workFlowType = new WorkFlowType();
	workFlowType.setFlowTypeId(GuId.getGuid());
	workFlowType.setFlowTypeName(request.getParameter("flowTypeName"));
	workFlowType.setModuleId(request.getParameter("moduleId"));
	workFlowType.setManageAccountId(account.getAccountId());
	workFlowType.setTopFlag(request.getParameter("topFlag"));
	workFlowType.setLeaveId(request.getParameter("leaveId"));
	workFlowType.setSortId(request.getParameter("sortId"));
	workFlowType.setOrgId(account.getOrgId());
	Connection dbConn = null;
	try
	{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
	logic.addWorkFlowTypeLogic(dbConn, workFlowType);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
	}
	return "/system/workflow/flowsort/add.jsp";
}

//更新流程分类
public String updateWorkFlowTypeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String flowTypeId=request.getParameter("flowTypeId");
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	WorkFlowType workFlowType = new WorkFlowType();
	workFlowType.setFlowTypeId(flowTypeId);
	workFlowType.setFlowTypeName(request.getParameter("flowTypeName"));
	workFlowType.setModuleId(request.getParameter("moduleId"));
	workFlowType.setManageAccountId(account.getAccountId());
	workFlowType.setTopFlag(request.getParameter("topFlag"));
	workFlowType.setLeaveId(request.getParameter("leaveId"));
	workFlowType.setSortId(request.getParameter("sortId"));
	workFlowType.setOrgId(account.getOrgId());
	Connection dbConn = null;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
		logic.updateWorkFlowTypeLogic(dbConn, workFlowType);
		dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
	}
	return "/system/workflow/flowsort/right.jsp?flowTypeId="+flowTypeId;
}
	
//获取流程分类列表
public void getWorkFlowTypeListJson(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String returnData="";
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
	returnData=logic.getWorkFlowTypeListJson(dbConn, account);
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
//获取流程分类的SELECT列表
//public void getWorkFlowTypeSelectAct(HttpServletRequest request,HttpServletResponse response)throws Exception
//{
//	Connection dbConn = null;
//	String returnDate="";
//	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
//	RequestConnDb requestConnDb = (RequestConnDb) request.getAttribute(SysConst.REQUEST_DB_CONN_MGR);
//	dbConn = requestConnDb.getSysDbConn();
//	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
//	returnDate=logic.getWorkFlowTypeSelectLogic(dbConn, account);
//	dbConn.close();
//	response.getWriter().print(returnDate);
//	response.getWriter().flush();
//
//}
//获取流程分类树
public void getWorkFlowTypeTreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String returnData="";
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
	returnData=logic.getWorkFlowTypeTreeLogic(dbConn, account);
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
//FLOWTYPEID获取类信息
public void getWorkFlowTypeinfoAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String flowTypeId=request.getParameter("flowTypeId");
	String returnData="";
	Connection dbConn = null;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
	returnData=logic.getWorkFlowTypeInfoLogic(dbConn, flowTypeId);
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
//删除流程分类
public void deleteWorkFLowTypeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String flowTypeId=request.getParameter("flowTypeId");
	Connection dbConn = null;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowTypeLogic logic = new WorkFlowTypeLogic();
	logic.deleteWorkFlowTypeLoigc(dbConn, flowTypeId);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("OK");
		response.getWriter().flush();
	}
}
}
