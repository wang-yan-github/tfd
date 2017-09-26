package tfd.system.workflow.flowrunprcs.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class FlowRunPrcsAct {
	//获取会签意见
	public void getIdeaAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData="";
		String runId = request.getParameter("runId");
		Connection dbConn =null;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			returnData=flowRunPrcsLogic.getIdeaLogic(dbConn,runId);
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
	//获取流程发起人姓名
public void flowBeginUserAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String returnData="";
	Connection dbConn = null;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String runId = request.getParameter("runId");
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		returnData=flowRunPrcsLogic.flowBeginUserName(dbConn, runId);
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
//获取当前人员以住发起的流程
public void getHistoryWorkFlowAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		returnData=flowRunPrcsLogic.getHistoryWorkFlowLogic(dbConn, account);
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
//加签
public void addOpUserAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	int returnData=0;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String runId = request.getParameter("runId");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String addOpUser = request.getParameter("addOpUser");
		int runPrcsId =  Integer.parseInt(request.getParameter("runPrcsId"));
		String flowId = request.getParameter("flowId");
		FlowRunPrcsLogic  flowRunPrcsLogic = new FlowRunPrcsLogic();
		returnData = flowRunPrcsLogic.addOpUsersLogic(dbConn, flowId,runId, prcsId,runPrcsId, addOpUser,account);
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
//设置关注人员
public void setFollowAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String runId = request.getParameter("runId");
	String runPrcsId = request.getParameter("runPrcsId");
	String prcsId = request.getParameter("prcsId");
	String flag=request.getParameter("flag");
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	int returnData=0;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	FlowRunPrcsLogic flowRunLogic = new FlowRunPrcsLogic();
	returnData = flowRunLogic.setFollowLogic(dbConn, runId, runPrcsId, prcsId, flag, account);
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
//获取以前办理的全部步骤
public void getHistoryPrcsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String runId = request.getParameter("runId");
	String runPrcsId = request.getParameter("runPrcsId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowRunPrcsLogic flowRunLogic = new FlowRunPrcsLogic();
		returnData = flowRunLogic.getHistoryPrcsLogic(dbConn, runId,runPrcsId);
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
//获取以前办理的全部步骤
public void getAStepAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String runId = request.getParameter("runId");
	String runPrcsId = request.getParameter("runPrcsId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowRunPrcsLogic flowRunLogic = new FlowRunPrcsLogic();
		returnData = flowRunLogic.getgetAStepLogic(dbConn, runId,runPrcsId);
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
//回退操作
public void goBackSeptAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	String runId = request.getParameter("runId");
	String ideaText = request.getParameter("ideaText");
	int Id =Integer.parseInt(request.getParameter("Id"));
	int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId"));
	int prcsId = Integer.parseInt(request.getParameter("prcsId"));
	Connection dbConn = null;
	int returnData = 0;
	try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, runPrcsId, "0", ideaText, account.getAccountId());
			returnData=flowRunPrcsLogic.goBackSeptLogic(dbConn,account,runId,Id, runPrcsId,prcsId);
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
public void getAllRunPrcsByRunIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String runId = request.getParameter("runId");
	Connection dbConn = null;
	String returnData="";
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
	returnData = flowRunPrcsLogic.getAllRunPrcsByRunIdLogic(dbConn, runId);
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
//工作流较交
public void deliverWorkAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	int returnData=0;
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	String flowId = request.getParameter("flowTypeId");
	String changeA = request.getParameter("changeA");
	String toChangeB =request.getParameter("toChangeB");
	Connection dbConn =null;
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		returnData=flowRunPrcsLogic.deliverWorkLogic(dbConn, flowId, account, changeA, toChangeB);
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
