package tfd.system.workflow.workflow.act;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class WorkFlowAct{
	//获取工作流树
	public void getWorkFlowTreeAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String returnData="";
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowLogic logic = new WorkFlowLogic();
			returnData=logic.getWorkFlowJson(dbConn, account);
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
	
	//获取流程相关信息
	public void getWorkFlowByFlowIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowTypeId=request.getParameter("flowTypeId");
		String returnData="";
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn =null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowLogic logic = new WorkFlowLogic();
			returnData=logic.getWorkFLowByFlowIdLogic(dbConn, flowTypeId,account);
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
	//删除流程
	public void delWorkFlowByFlowTypeIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowTypeId=request.getParameter("flowTypeId");
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowLogic logic = new WorkFlowLogic();
			returnData=logic.delFLowByFlowTypeIdLogic(dbConn, flowTypeId);
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
	//添加新流程
	public String createWorkFlowAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowTypeId=GuId.getGuid();
		String flowId=GuId.getGuid();
		Connection dbConn = null;
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String flowCreateUser=account.getAccountId();
		String sortId=request.getParameter("sortId");
		if(SysTool.isNullorEmpty(sortId))
		{
			sortId="0";
		}
		WorkFlow workFlow = new WorkFlow();
		workFlow.setFlowName(request.getParameter("flowName"));
		workFlow.setFlowTypeId(flowTypeId);
		workFlow.setFlowId(flowId);
		workFlow.setSortId(sortId);
		workFlow.setFormId(request.getParameter("formId"));
		workFlow.setOrgId(account.getOrgId());
		workFlow.setModuleId(request.getParameter("moduleId"));
		workFlow.setFlowType(request.getParameter("flowType"));
		workFlow.setWorkFlowTypeId(request.getParameter("workFlowTypeId"));
		workFlow.setFlowCreateUser(flowCreateUser);
		workFlow.setLeavePass(request.getParameter("leavePass"));
		workFlow.setFlowLock(request.getParameter("flowLock"));
		workFlow.setFreeToOther(request.getParameter("freeToOther"));
		workFlow.setFlowDoc(request.getParameter("flowDoc"));
		workFlow.setAutoCode(request.getParameter("autoCode"));
		workFlow.setAutoNum(request.getParameter("autoNum"));
		workFlow.setSendToUser(request.getParameter("sendToUser"));
		workFlow.setSendToModule(request.getParameter("sendToModule"));
		workFlow.setSavePath(request.getParameter("savePath"));
		workFlow.setSaveFile(request.getParameter("saveFile"));
		workFlow.setFlowWait(request.getParameter("flowWait"));
		workFlow.setFlowFunction(request.getParameter("editor"));
		WorkFlowLogic logic = new WorkFlowLogic();
		logic.createWorkFlowLogic(dbConn, workFlow);
		dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
		return "/system/workflow/workflow/manage.jsp?flowTypeId="+flowTypeId;
	}
	
	public String updateWorkFlowAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String[] printField = request.getParameterValues("p_printfield");
		String printFieldStr="";
		if(printField!=null)
		{
			for(int i=0;printField.length>i;i++)
			{
				printFieldStr+=printField[i]+",";
			}
		}
		if(printFieldStr.endsWith(","))
		{
			printFieldStr=printFieldStr.substring(0,printFieldStr.length()-1);
		}
		String flowTypeId=request.getParameter("flowTypeId");
		Connection dbConn = null;
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String flowCreateUser=account.getAccountId();
		String sortId=request.getParameter("sortId");
		if(SysTool.isNullorEmpty(sortId))
		{
			sortId="0";
		}
		WorkFlow workFlow = new WorkFlow();
		workFlow.setFlowName(request.getParameter("flowName"));
		workFlow.setFlowTypeId(request.getParameter("flowTypeId"));
		workFlow.setFlowId(request.getParameter("flowId"));
		workFlow.setSortId(sortId);
		workFlow.setFormId(request.getParameter("formId"));
		workFlow.setOrgId(account.getOrgId());
		workFlow.setModuleId(request.getParameter("moduleId"));
		workFlow.setFlowType(request.getParameter("flowType"));
		workFlow.setWorkFlowTypeId(request.getParameter("workFlowTypeId"));
		workFlow.setFlowCreateUser(flowCreateUser);
		workFlow.setLeavePass(request.getParameter("leavePass"));
		workFlow.setFlowLock(request.getParameter("flowLock"));
		workFlow.setFreeToOther(request.getParameter("freeToOther"));
		workFlow.setFlowDoc(request.getParameter("flowDoc"));
		workFlow.setAutoCode(request.getParameter("autoCode"));
		workFlow.setAutoNum(request.getParameter("autoNum"));
		workFlow.setSendToUser(request.getParameter("sendToUser"));
		workFlow.setSendToModule(request.getParameter("sendToModule"));
		workFlow.setSavePath(request.getParameter("savePath"));
		workFlow.setSaveFile(request.getParameter("saveFile"));
		workFlow.setFlowFunction(request.getParameter("editor"));
		workFlow.setPrintField(printFieldStr);
		WorkFlowLogic logic = new WorkFlowLogic();
		logic.updateWorkFLowLogic(dbConn, workFlow);
		dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
		return "/system/workflow/workflow/manage.jsp?flowTypeId="+flowTypeId;
	}
	
//更新工作流所有表单缓存
public void	updateFlowCahceAct(HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId=request.getParameter("flowTypeId");
	Connection dbConn = null;
	try
	{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowLogic workFlowLogic = new WorkFlowLogic();
	WorkFlow workFlow = new WorkFlow();
	workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowTypeId);
	String flowLock = workFlow.getFlowLock();
	response.setContentType("text/html;charset=utf-8");
	if(flowLock.equals("0"))
	{
		workFlowLogic.updateFlowCahceLogic(dbConn, flowTypeId);
		response.getWriter().print("OK");
	}else if(flowLock.equals("1"))
	{
		response.getWriter().print("LOCK");
	}
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.getWriter().flush();
	}
}

//获取分类工作流列表
public void getListByWorkFlowTypeIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String pageStr=request.getParameter("page");
	String pageSizeStr=request.getParameter("rows");
	String sortName=request.getParameter("sortname");
	String sortOrder=request.getParameter("sortorder");
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
	List<String> pramList = new ArrayList<String>();
	pramList.add(request.getParameter("workFlowTypeId"));
	pramList.add(account.getAccountId());
	pramList.add(account.getOrgId());
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		returnData=workFlowLogic.getListByWorkFlowTypeIdLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
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
//设置相关管理权限
public void setWorkFlowManagePrivAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId = request.getParameter("flowTypeId");
	String privType=request.getParameter("privType");
	String accountId=request.getParameter("accountId");
	Connection dbConn = null;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	WorkFlowLogic workFlowLogic = new WorkFlowLogic();
	workFlowLogic.setWorkFlowManagePrivLogic(dbConn, accountId, privType, flowTypeId);
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
//获取相关权限信息
public void getWorkFlowManagePrivAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId = request.getParameter("flowTypeId");
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		returnData = workFlowLogic.getWorkFlowManagePrivLogic(dbConn, flowTypeId);
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
//获取相关的原始表单
public void getPreviewPathAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId = request.getParameter("flowTypeId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		returnData = workFlowLogic.getPreviewPathLogic(dbConn, flowTypeId);
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
//获取流程说明
public void getFlowFunctionAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId = request.getParameter("flowTypeId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		returnData = workFlowLogic.getFlowFunctionLogic(dbConn, flowTypeId);
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
//获取流程说明
public void clearFlowDataAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowTypeId = request.getParameter("flowTypeId");
	Connection dbConn = null;
	int returnData=0;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		returnData = workFlowLogic.clearWorkFlowDataByFlowIdLogic(dbConn, flowTypeId);
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

public void getWorkFlowByFlowNameAct (HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String flowName = request.getParameter("workselect");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		returnData = workFlowLogic.getWorkFlowByFlowNameLogic(dbConn, flowName,account);
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
