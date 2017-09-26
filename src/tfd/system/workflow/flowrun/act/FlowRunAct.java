package tfd.system.workflow.flowrun.act;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class FlowRunAct {
	//保存工作流共公附件
	public void updataAttachIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String runId=request.getParameter("runId");
		String attactIds= request.getParameter("attachIds");
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		flowRunLogic.updataAttachIdLogic(dbConn, runId, attactIds);
		dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("OK");
			
		}
	}
	//创建流程主表信息
	public void createFlowRunAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
	}
	//逻辑删除工作流
	public void delFlowRunAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String runId = request.getParameter("runId");
		Connection dbConn =null;
		int returnData=0;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			returnData = flowRunLogic.delFlowRunLogic(dbConn, runId);
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
	
	public void getFlowRunByIdsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String runIds=request.getParameter("runIds");
		Connection dbConn = null;
		String returnData="";
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		returnData =flowRunLogic.getFlowRunByIdsLogic(dbConn, runIds);
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
	
	//结束流程
	public void endFlowRunByIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String runId =request.getParameter("runId");
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			flowRunPrcsLogic.endWorkFlow(dbConn, runId);
			WorkListLogic workListLogic = new WorkListLogic();
			int runPrcsId = flowRunPrcsLogic.getRunPrcsId(dbConn, runId)+1;
			workListLogic.updateStatusLogic(dbConn, runId, runPrcsId, account.getAccountId());
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRunLogic.endFlowRunLogic(dbConn, runId);
			returnData="{\"isEnd\":\"END\"}";
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
	
	public void remindersAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String runId =request.getParameter("runId");
		Connection dbConn = null;
		JSONObject returnData= new JSONObject();
		try
		{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			FlowRun flowRun = new FlowRun();
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRun=flowRunLogic.getFlowRunLogic(dbConn, runId);
			Map<String,List<String>> userMap = flowRunPrcsLogic.getOpUserListLogic(dbConn, runId);
			MessageApi messageApi = new MessageApi();
			JSONObject smsRemindJson = new JSONObject();
			smsRemindJson.accumulate("wxsms", "1");
			smsRemindJson.accumulate("sitesms", "1");
			smsRemindJson.accumulate("mobilesms", "1");
			smsRemindJson.accumulate("emailsms", "1");
			smsRemindJson.accumulate("webemilesms", "1");
			String smsType="workflow";
			String content="请您处理一下标题为："+flowRun.getTitle()+"的流程！";
			messageApi.sendMessage(dbConn, smsType, smsRemindJson, content, account.getAccountId(), userMap.get("userAccountIdList"), account.getOrgId());
			String userStr="";
			for(int i=0;userMap.get("userNameList").size()>i;i++)
			{
				userStr+=userMap.get("userNameList").get(i)+",";
			}
			returnData.accumulate("userName", userStr);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData.toString());
		}
	}
}
