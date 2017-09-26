package tfd.system.workflow.monitor.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.monitor.data.MonitorField;
import tfd.system.workflow.monitor.logic.MonitorLogic;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class MonitorAct {
//查询所有控制全限的流程
	public void getMonitorFlowAct(HttpServletRequest request,HttpServletResponse response)throws Exception
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
		String flowName = request.getParameter("flowName");
		String flowTitle= request.getParameter("flowTitle");
		String runId = request.getParameter("runId");
		String beginUserId = request.getParameter("beginUserId");
		String status = request.getParameter("status");
		MonitorField monitorField = new MonitorField();
		monitorField.setFlowName(flowName);
		monitorField.setFlowTitle(flowTitle);
		monitorField.setRunId(runId);
		monitorField.setBeginUserId(beginUserId);
		monitorField.setStatus(status);
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		MonitorLogic monitorLogic = new MonitorLogic();
		returnData=monitorLogic.getMonitorFlowLogic(dbConn, monitorField, account, pageSize, page, sortOrder, sortName);
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
	
	//强制转交
	public void doForcedAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		int goprcsId =Integer.parseInt(request.getParameter("goprcsId"));
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String gonextuser = request.getParameter("gonextuser");
		int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId"));
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String runId = request.getParameter("runId");
		int gorunPrcsId=0;
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		Connection dbConn =null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			if(goprcsId==0)
			{
				//结束节点的处理
				flowRunPrcsLogic.endWorkFlow(dbConn, runId);
				WorkListLogic workListLogic = new WorkListLogic();
				workListLogic.updateStatusForceLogic(dbConn, runId, prcsId,runPrcsId);
				FlowRunLogic flowRunLogic = new FlowRunLogic();
				flowRunLogic.endFlowRunLogic(dbConn, runId);
			}else
			{
			String createTime = SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
			UserInfo userInfo = new UserInfo();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			
			FlowRun flowRun = new FlowRun();
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRun = flowRunLogic.getFlowRunLogic(dbConn, runId);
			
			FlowProcess flowProcess = new FlowProcess();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowRun.getFlowId(), goprcsId);
			userInfo = userInfoLogic.getUserInfoByAccountId(dbConn, gonextuser);
			gorunPrcsId = flowRunPrcsLogic.getRunPrcsId(dbConn, runId)+1;
			FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
			flowRunPrcs.setRunId(runId);
			flowRunPrcs.setFlowId(flowRun.getFlowId());
			flowRunPrcs = flowRunPrcsLogic.getFlowRunPrcs(dbConn, runId,runPrcsId);
			flowRunPrcs.setPrcsId(goprcsId);
			flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
			flowRunPrcs.setRunPrcsId(gorunPrcsId);
			flowRunPrcs.setCreateTime(createTime);
			flowRunPrcs.setAccountId(gonextuser);
			flowRunPrcs.setUserName(userInfo.getUserName());
			flowRunPrcs.setDeptId(userInfo.getDeptId());
			flowRunPrcs.setLeadId(userInfo.getLeadId());
			flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
			WorkList workList = new WorkList();
			workList.setTitle(flowRun.getTitle());
			workList.setRunId(flowRun.getRunId());
			workList.setModule("workflow");
			workList.setAccountId(gonextuser);
			workList.setReadUrl("/system/workflow/dowork/"+flowRunPrcs.getTableName()+"/print/index.jsp?runId="+runId);
			workList.setUrl("/system/workflow/dowork/"+flowRunPrcs.getTableName()+"/"+goprcsId+"/index.jsp?runId="+runId+"&runPrcsId="+gorunPrcsId);
			workList.setStatus("0");
			workList.setCreateTime(createTime);
			workList.setEndTime("");
			workList.setPrcsId(gorunPrcsId);
			workList.setRunPrcsId(gorunPrcsId);
			workList.setDelFlag("0");
			workList.setOrgId(account.getOrgId());
			WorkListLogic workListLogic = new WorkListLogic();
			workListLogic.createDoRecordLogic(dbConn, workList);
			MonitorLogic monintorLogic = new MonitorLogic();
			monintorLogic.doForceLogic(dbConn, flowRunPrcs,runPrcsId,prcsId,account);
			returnData="1";
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
			response.getWriter().flush();
		}
	}
}
