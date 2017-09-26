package tfd.system.workflow.getflowdata.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityFlowPriv;
import tfd.system.workflow.getflowdata.logic.WorkFlowDataLogic;
import tfd.system.workflow.workflownext.tool.FlowProcessWriterItemTool;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class GetWorkFlowDataAct {
	//获取流程选择控件数据
	public void getSelectWorkFLowAct(HttpServletRequest request,HttpServletResponse response) throws Exception
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
		
		String model=request.getParameter("model");
		String q_runId=request.getParameter("q_runId");
		String q_title = request.getParameter("q_title");
		String returnData="";
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowDataLogic workFlowDataLogic = new WorkFlowDataLogic();
			returnData= workFlowDataLogic.getSelectWorkFlowLogic(dbConn,account, model,q_runId,q_title, pageSize, page, sortOrder, sortName);
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
		}
	}
	
	
	//查看时获取表单数据
	public void getPrintWorkFlowDataAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String returnData="";
		String runId=request.getParameter("runId");
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
			boolean flag =uitilityFlowPriv.getFlowPrintPriv(account.getAccountId());
			if(flag)
			{
				WorkFlowDataLogic workFlowDataLogic = new WorkFlowDataLogic();
				returnData=workFlowDataLogic.getWorkFlowDataLogic(dbConn,runId, account);
				dbConn.commit();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
		}
	}
	//办理时获取表单数据
public void getWorkFlowDataAct(HttpServletRequest request,HttpServletResponse response) throws Exception
{
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	String returnData="";
	String runId=request.getParameter("runId");
	int prcsId =Integer.parseInt(request.getParameter("prcsId"));
	int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId"));
	String flowId = request.getParameter("flowId");
	Connection dbConn = null;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowRunPrcsLogic flowRunPrcsLogic= new FlowRunPrcsLogic();
		FlowRunPrcs flowRunPrcs=flowRunPrcsLogic.getFlowRunPrcs(dbConn, runId,prcsId,runPrcsId,account); 
		FlowProcess flowProcess = new FlowProcess();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId, prcsId);
		UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
		boolean flag =uitilityFlowPriv.getEditFlowPriv(account.getAccountId(), flowRunPrcs);
	if(flag)
	{
		WorkFlowDataLogic workFlowDataLogic = new WorkFlowDataLogic();
		returnData=workFlowDataLogic.getWorkFlowDataLogic(dbConn, flowRunPrcs,flowProcess,account);
		dbConn.commit();
	}
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
	}
}
//获取子表数据
public void getChildTableDataAct(HttpServletRequest request,HttpServletResponse response) throws Exception{
	
	Connection dbConn = null;
	PrintWriter writer=null;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		
		String runId=request.getParameter("runId");
		String flowId=request.getParameter("flowId");
		int prcsId=Integer.parseInt(request.getParameter("prcsId"));
		
		String writerJson =new FlowProcessWriterItemTool().getXlistFieldNameTool(dbConn, flowId, prcsId);
		JSONArray returnData=new WorkFlowDataLogic().getChildTableDataLogic(dbConn, runId, writerJson);
		
		writer=response.getWriter();
		writer.print(returnData.toString());
	}catch(Exception ex){
		throw ex;
	}finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		
		SysTool.closePrintWriter(writer);
	}
	
}
//获取所有子表数据
public void getAllChildTableDataAct(HttpServletRequest request,HttpServletResponse response) throws Exception
{
	String runId=request.getParameter("runId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		WorkFlowDataLogic workFlowData = new WorkFlowDataLogic();
		returnData = workFlowData.getAllChildTableDataLogic(dbConn, runId);
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
	}
	
}
}
