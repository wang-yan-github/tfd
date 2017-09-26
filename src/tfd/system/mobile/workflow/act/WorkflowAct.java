package tfd.system.mobile.workflow.act;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.messageunit.MessageApi;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.mobile.workflow.logic.WorkFlowAutoUserTool;
import tfd.system.mobile.workflow.logic.WorkFlowDataTool;
import tfd.system.mobile.workflow.logic.WorkflowLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowpassleave.logic.FlowPassLeaveLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityFlowPriv;
import tfd.system.workflow.flowutility.UitilityTool;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.workflow.getflowdata.logic.WorkFlowDataLogic;
import tfd.system.workflow.newwork.logic.NewWorkFlowLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;
import tfd.system.workflow.workflownext.logic.WorkFlowNextLogic;
import tfd.system.workflow.workflownext.tool.FlowProcessWriterItemTool;
import tfd.system.workflow.workflownext.tool.WorkFlowNextCheckTool;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.ResponseConst;
import com.system.tool.SysTool;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkflowAct {
	JSONObject data=JSONObject.fromObject("{'status_code':'500','msg':'请求失败','data':{'time':"+new Date().getTime()/1000+"}}");
	WorkflowLogic logic=new WorkflowLogic();
	/**
	 * 我可以发起的工作流
	 */
	public void myWorkflow(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			
			String platform=request.getParameter("platform");
			String version=request.getParameter("version");
			String phone=request.getParameter("phone");
			String accessToken=request.getParameter("access_token");
			
			sysUtil.checkParam(response,"phone",phone);
			String accountId = SystemUtil.getAccountIdByPhone(dbConn, phone);
			
			Account account = new AccountLogic().getAccountByAccountId(dbConn, accountId);
			String token = sysUtil.checkParam(response,"access_token",accessToken);
			sysUtil.checkAccessToken(request, response, token, accountId);
			JSONObject dataTemp=logic.myWorkflowLogic(dbConn,account);
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}		
	}
	/**
	 * 我的待办工作
	 */
	public void myWork(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform=request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			JSONObject dataTemp=logic.myWorkLogic(dbConn,account);
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	
	/**
	 * 发起流程
	 */
	public void createWork(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform=request.getParameter("platform");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			String version=request.getParameter("version");
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String title="";
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			WorkFlow workFlow = new WorkFlow();
			workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			UitilityTool  uitilityTool = new UitilityTool();
			if(SysTool.isNullorEmpty(title))
			{
				title=uitilityTool.createCode(dbConn,workFlow.getAutoCode(), flowId, "", account);
			}
			String runId =logic.createWorkLogic(dbConn,flowId,account,title);
			dbConn.commit();
			
			JSONObject dataTemp=logic.getFirstPrcsDataLogic(dbConn,runId,account);
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
		
	}
	
	/**
	 * 打开我的工作
	 */
	public void openMyWork(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform=request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			String runPrcsId =sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId"));
			String prcsId =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			JSONObject dataTemp=logic.openMyWorkLogic(dbConn,account,runId,Integer.parseInt(runPrcsId),Integer.parseInt(prcsId));
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
		
	}
	/**
	 * 流程审批，步骤选择，提交
	 */
	public void turnnext(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		JSONObject returnjson = new JSONObject();
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			List<String> toAccountList = new ArrayList<String>();
			SystemUtil sysUtil = new SystemUtil();
			String platform=request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			int runPrcsId =Integer.parseInt(sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId")))+1;
			String prcsIdStr =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			int prcsId = Integer.parseInt(prcsIdStr);
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String prcs = request.getParameter("prcs");
			JSONArray prcsArr = new JSONArray().fromObject(prcs);
			WorkFlowNextLogic workFlowNextLogic = new WorkFlowNextLogic();
			int prcsRunId=workFlowNextLogic.getPrcsRunId(dbConn, runId);
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			FlowRun flowRun = flowRunLogic.getFlowRunLogic(dbConn, runId);
			String parentWait = flowRun.getParentWait();
			if(SysTool.isNullorEmpty(parentWait))
			{
				parentWait="0";
			}
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(dbConn, flowId);
			String tableName=workFlowFormLogic.getFormTableNameByFormIdLogic(dbConn, formId);
			WorkFlowDataLogic workFlowData = new WorkFlowDataLogic();
			String childRunId=workFlowData.getChildRunIdByRunIdLogic(dbConn,tableName,runId);
			UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
			FlowProcessLogic flowProcessLogic =new FlowProcessLogic();
			FlowProcess flowProcess = new FlowProcess();
			UserInfoLogic userInfoLogic = new UserInfoLogic(); 
			if(parentWait.equals("1"))
			{
				boolean childFlowEnd = workFlowLogic.getWorkFlowEndLogic(dbConn, childRunId);
				if(childFlowEnd)
				{
					//已结束//更新待办记录状态
					flowRunLogic.updateFlowWaitLogic(dbConn, runId,"","");
					WorkListLogic workListLogic = new WorkListLogic();
					workListLogic.updateStatusLogic(dbConn, runId, prcsRunId, account.getAccountId());
					FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
					flowRunPrcsLogic.updateStatusLogic(dbConn, runId, prcsId, account.getAccountId(), prcsRunId);
					if(prcsId==0)
					{
						flowRunLogic.endFlowRunLogic(dbConn, runId);
					}
					boolean nextFlag = uitilityFlowPriv.getForceAggregationPriv(dbConn, runId, prcsId);
					if(nextFlag)
					{
						for(int i=0;prcsArr.size()>i;i++)
						{
							JSONObject json=JSONObject.fromObject(prcsArr.get(i));
							flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId, Integer.parseInt(json.getString("prcsId")));
							//处理子流程节点
							//获取子流程的发起人，也就是下一步的经办人员
							//--------------------------------------------------------------------要处理------------------------
							String nextOptUser=account.getAccountId();
							if(flowProcess.getPrcsType().equals("6"))
							{
								String childFlow = flowProcess.getChildFlow();
								AccountLogic accountLogic = new AccountLogic();
								Account childAccount = accountLogic.getAccountByAccountId(dbConn, nextOptUser);
								//新子流程的相关数据
								NewWorkFlowLogic newWorkFlowLogic = new NewWorkFlowLogic();
								newWorkFlowLogic.createChildWorkLogic(dbConn, childAccount, "子流程", childFlow,flowId,runId,flowProcess);
								flowRunLogic.updateFlowWaitLogic(dbConn, runId,flowProcess.getWaitPrcsId(),flowProcess.getParnetWait());
							}
							//处理非子流程节点
							else
							{
								UserInfo userInfo = userInfoLogic.getUserInfoByAccount(dbConn, account);
								FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
								flowRunPrcs.setRunId(runId);
								flowRunPrcs.setFlowId(flowId);
								flowRunPrcs.setTableName(tableName);			
								flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
								flowRunPrcs.setAccountId(nextOptUser);
								flowRunPrcs.setUserName(userInfo.getUserName());
								flowRunPrcs.setDeptId(userInfo.getDeptId());
								flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
								flowRunPrcs.setLeadId(userInfo.getLeadId());
								flowRunPrcs.setTransferUser(account.getAccountId());
								flowRunPrcs.setOrgId(account.getOrgId());
								workFlowNextLogic.goNextLogic(dbConn,flowRunPrcs,prcsRunId);
								//生成下一步的待办列表
								WorkList workList = new WorkList();
								String path=tableName.toLowerCase()+"/"+json.getString("prcsId")+"/index.jsp";;
								workList.setTitle(workListLogic.getTitle(dbConn, runId));
								workList.setPrcsName(flowProcess.getPrcsName());
								workList.setRunId(runId);
								workList.setModule("workflow");
								workList.setAccountId(nextOptUser);
								workList.setUrl("/system/workflow/dowork/"+path+"?runId="+runId+"&runPrcsId="+runPrcsId);
								workList.setReadUrl("/system/workflow/dowork/"+tableName+"/print/index.jsp?runId="+runId);
								workList.setStatus("0");
								workList.setCreateTime(SysTool.getCurDateTimeStr());
								workList.setOrgId(account.getOrgId());
								workList.setDelFlag("0");
								workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								workList.setRunPrcsId(runPrcsId);
								workList.setOrgId(account.getOrgId());
								workListLogic.createDoRecordLogic(dbConn, workList);
								String opUserStr=flowRunLogic.getOpUserStrLogic(dbConn, runId);
								flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr+","+nextOptUser);
							}
						}
					}
					dbConn.commit();
					returnjson.accumulate("status_code", "200");
					returnjson.accumulate("msg", "请求成功");
					JSONObject jsondata = new JSONObject();
					jsondata.accumulate("time", System.currentTimeMillis());
					JSONObject data = new JSONObject();
					data.accumulate("msg", "子流程已发起！步骤操作完成！");
					jsondata.accumulate("data", data);
					returnjson.accumulate("data", jsondata);
				}else
				{
					dbConn.commit();
					returnjson.accumulate("status_code", "200");
					returnjson.accumulate("msg", "请求成功");
					JSONObject jsondata = new JSONObject();
					jsondata.accumulate("time", System.currentTimeMillis());
					JSONObject data = new JSONObject();
					data.accumulate("msg", "子流程尚未完成，请稍候办理！");
					jsondata.accumulate("data", data);
					returnjson.accumulate("data", jsondata);
				}
			}else if(parentWait.equals("0"))
			{
				//不等待
				boolean nextFlag = uitilityFlowPriv.getForceAggregationPriv(dbConn, runId, prcsId);
				//更新待办记录状态
				WorkListLogic workListLogic = new WorkListLogic();
				workListLogic.updateStatusLogic(dbConn, runId, runPrcsId-1, account.getAccountId());
				//
				UserInfo userInfo = new UserInfo();
				//更新流程步骤状态
				FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
				WorkList workList = new WorkList();
				flowRunPrcsLogic.updateStatusLogic(dbConn, runId, runPrcsId-1, account.getAccountId(), prcsRunId);
				//更淅主表数据
				//判断步骤是不是结束结点
				if(prcsId==0)
				{
					flowRunLogic.endFlowRunLogic(dbConn, runId);
				}
				if(nextFlag)
				{
					for(int i=0;prcsArr.size()>i;i++)
					{
						JSONObject json=JSONObject.fromObject(prcsArr.get(i));
						flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId, Integer.parseInt(json.getString("prcsId")));
						//处理子流程节点
						if(flowProcess.getPrcsType().equals("6"))
						{
							//获取子流程的发起人，也就是下一步的经办人员
							String opUserId = json.getString("opAccount");
							String childFlow = flowProcess.getChildFlow();
							AccountLogic accountLogic = new AccountLogic();
							Account childAccount = accountLogic.getAccountByAccountId(dbConn, opUserId);
							userInfo = userInfoLogic.getUserInfoByAccountId(dbConn,opUserId);
							//新子流程的相关数据
							NewWorkFlowLogic newWorkFlowLogic = new NewWorkFlowLogic();
							newWorkFlowLogic.createChildWorkLogic(dbConn, childAccount, "子流程", childFlow,flowId,runId,flowProcess);
							flowRunLogic.updateFlowWaitLogic(dbConn, runId,flowProcess.getWaitPrcsId(),flowProcess.getParnetWait());
								//nextOptUser=account.getAccountId();
								FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
								flowRunPrcs.setRunId(runId);
								flowRunPrcs.setFlowId(flowId);
								flowRunPrcs.setTableName(tableName);			
								flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
								flowRunPrcs.setAccountId(opUserId);
								flowRunPrcs.setUserName(userInfo.getUserName());
								flowRunPrcs.setDeptId(userInfo.getDeptId());
								flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
								flowRunPrcs.setLeadId(userInfo.getLeadId());
								flowRunPrcs.setTransferUser(account.getAccountId());
								flowRunPrcs.setOrgId(account.getOrgId());
								workFlowNextLogic.goNextLogic(dbConn,flowRunPrcs,prcsRunId);
								//生成下一步的待办列表
								String path=tableName.toLowerCase()+"/"+Integer.parseInt(json.getString("prcsId"))+"/index.jsp";
								workList.setTitle(workListLogic.getTitle(dbConn, runId));
								workList.setRunId(runId);
								workList.setModule("workflow");
								workList.setPrcsName(flowProcess.getPrcsName());
								workList.setAccountId(opUserId);
								workList.setUrl("/system/workflow/dowork/"+path+"?runId="+runId+"&runPrcsId="+runPrcsId);
								workList.setReadUrl("/system/workflow/dowork/"+tableName+"/print/index.jsp?runId="+runId);
								workList.setRunPrcsId(runPrcsId);
								workList.setStatus("0");
								workList.setDelFlag("0");
								workList.setCreateTime(SysTool.getCurDateTimeStr());
								workList.setOrgId(account.getOrgId());
								workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								workListLogic.createDoRecordLogic(dbConn, workList);
								String opUserStr=flowRunLogic.getOpUserStrLogic(dbConn, runId);
								flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr+","+opUserId);
						}
						//处理非子流程节点
						else
						{
							//主办人员处理
							JSONObject nprcsjson = new JSONObject();
							JSONArray prcsUser=new JSONArray();
							JSONObject tmpj = new JSONObject().fromObject(prcsArr.get(i));
							prcsUser=tmpj.getJSONArray("prcsUser");
							String opUserId="";
							if(!prcsUser.isEmpty())
							{
								opUserId=prcsUser.getString(0);
							}
								toAccountList.add(opUserId);
								userInfo = userInfoLogic.getUserInfoByAccountId(dbConn,opUserId);
								FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
								flowRunPrcs.setRunId(runId);
								flowRunPrcs.setFlowId(flowId);
								flowRunPrcs.setStatus("0");
								flowRunPrcs.setOpFlag("0");
								flowRunPrcs.setTableName(tableName);			
								flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
								flowRunPrcs.setAccountId(opUserId);
								flowRunPrcs.setUserName(userInfo.getUserName());
								flowRunPrcs.setDeptId(userInfo.getDeptId());
								flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
								flowRunPrcs.setLeadId(userInfo.getLeadId());
								flowRunPrcs.setTransferUser(account.getAccountId());
								flowRunPrcs.setOrgId(account.getOrgId());;
								workFlowNextLogic.goNextLogic(dbConn,flowRunPrcs,prcsRunId);
								String path=tableName.toLowerCase()+"/"+json.getString("prcsId")+"/index.jsp";;
								workList.setTitle(workListLogic.getTitle(dbConn, runId));
								workList.setRunId(runId);
								workList.setModule("workflow");
								workList.setPrcsName(flowProcess.getPrcsName());
								workList.setAccountId(opUserId);
								workList.setUrl("/system/workflow/dowork/"+path+"?runId="+runId+"&opFlag=0&runPrcsId="+runPrcsId);
								workList.setReadUrl("/system/workflow/dowork/"+tableName+"/print/index.jsp?runId="+runId);
								workList.setStatus("0");
								workList.setDelFlag("0");
								workList.setCreateTime(SysTool.getCurDateTimeStr());
								workList.setOrgId(account.getOrgId());
								workList.setRunPrcsId(runPrcsId);
								workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
								workListLogic.createDoRecordLogic(dbConn, workList);
								String opUserStr=flowRunLogic.getOpUserStrLogic(dbConn, runId);
								flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr+","+opUserId);
								//主办人员处理结束
								//知会人员处理开始
								for(int k=1;prcsUser.size()>k;k++)
								{
									toAccountList.add(prcsUser.getString(k));
									userInfo = userInfoLogic.getUserInfoByAccountId(dbConn,prcsUser.getString(k));
									flowRunPrcs.setRunId(runId);
									flowRunPrcs.setFlowId(flowId);
									flowRunPrcs.setStatus("0");
									flowRunPrcs.setOpFlag("1");
									flowRunPrcs.setTableName(tableName);			
									flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
									flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
									flowRunPrcs.setAccountId(prcsUser.getString(k));
									flowRunPrcs.setUserName(userInfo.getUserName());
									flowRunPrcs.setDeptId(userInfo.getDeptId());
									flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
									flowRunPrcs.setLeadId(userInfo.getLeadId());
									flowRunPrcs.setTransferUser(account.getAccountId());
									flowRunPrcs.setOrgId(account.getOrgId());
									workFlowNextLogic.goNextLogic(dbConn,flowRunPrcs,prcsRunId);
									
									workList.setTitle(workListLogic.getTitle(dbConn, runId));
									workList.setRunId(runId);
									workList.setModule("workflow");
									workList.setPrcsName(flowProcess.getPrcsName());
									workList.setAccountId(prcsUser.getString(k));
									workList.setUrl("/system/workflow/dowork/"+path+"?runId="+runId+"&opFlag=1&runPrcsId="+runPrcsId);
									workList.setReadUrl("/system/workflow/dowork/"+tableName.toLowerCase()+"/print/index.jsp?runId="+runId);
									workList.setStatus("0");
									workList.setDelFlag("0");
									workList.setCreateTime(SysTool.getCurDateTimeStr());
									workList.setOrgId(account.getOrgId());
									workList.setRunPrcsId(runPrcsId);
									workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
									workListLogic.createDoRecordLogic(dbConn, workList);
									opUserStr=flowRunLogic.getOpUserStrLogic(dbConn, runId);
									flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr+","+prcsUser.getString(k));
								}
								//知会人员处理结束					
								}
						//需发送信息提醒的人员的ID
						String flowName=workFlowLogic.getFlowName(dbConn,flowId);
						MessageApi messageApi = new MessageApi();
						//messageApi.sendMessage(dbConn, "workflow", smsRemindJson, "你有一个"+flowName+"需要你处理！", account.getAccountId(), toAccountList,account.getOrgId());
					}
					dbConn.commit();
					returnjson.accumulate("status_code", "200");
					returnjson.accumulate("msg", "请求成功");
					JSONObject jsondata = new JSONObject();
					jsondata.accumulate("time", System.currentTimeMillis());
					JSONObject data = new JSONObject();
					data.accumulate("msg", "步骤操作完成！");
					jsondata.accumulate("data", data);
					returnjson.accumulate("data", jsondata);
				}
			}else if(parentWait.equals("2"))
			{
				returnjson.accumulate("status_code", "200");
				returnjson.accumulate("msg", "请求成功");
				JSONObject jsondata = new JSONObject();
				jsondata.accumulate("time", System.currentTimeMillis());
				JSONObject data = new JSONObject();
				data.accumulate("msg", "parentWait is 2!");
				jsondata.accumulate("data", data);
				returnjson.accumulate("data", jsondata);
			}
			if (returnjson!=null) {
				data=returnjson;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 流程查询
	 */
	public void search(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("search");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 查看流程详情
	 */
	public void lookWorkDetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("lookWorkDetail");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 保存表单数据，获取可选步骤、提醒方式
	 */
	public void getTurnnextPrcsMessage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONObject returnjson = new JSONObject();
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform = request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			String workTitle =sysUtil.checkParam(response,"workTitle",request.getParameter("workTitle"));
			String runPrcsId =sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId"));
			String prcsId =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String formDataStr = request.getParameter("formData");
			String idea =sysUtil.checkParam(response,"idea",request.getParameter("idea"));
			String ideaText = request.getParameter("ideaText");
			String flowLeave = request.getParameter("flowLeave");
			if(SysTool.isNullorEmpty("flowLeave"))
			{
				flowLeave="0";
			}
			String flowData = URLDecoder.decode(formDataStr, "utf-8");
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			WorkFlow workFlow = new WorkFlow();
			workFlow=workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(dbConn, workFlow.getFormId());
			
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRunLogic.updateLeaveAndTitleLogic(dbConn, runId, workTitle, flowLeave);
			
			WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
			Map <String,String> map = new HashMap<String,String>();
			map=workFlowDataTool.getDataMap(flowData);
			FlowProcessWriterItemTool writerFieldTool = new  FlowProcessWriterItemTool();
			String[] writerField= writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, Integer.parseInt(prcsId));
			//保存表单数据
			JSONObject dataTemp=logic.saveFlowFormDataLogic(dbConn, map, writerField,runId,tableName);	
			
			//保存会签意见
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, Integer.parseInt(runPrcsId), idea, ideaText,account.getAccountId());
			
			//判断流程是不是启用行政级别审批
			String leavePass = workFlow.getLeavePass();
			FlowProcess  flowProcess = new FlowProcess();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId, Integer.parseInt(prcsId));
			String nextPrcsList="";
			if(leavePass.equals("1"))
			{
				String leadPrcsLeave = flowProcess.getLeadPrcsLeave();
				FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
				nextPrcsList=flowPassLeaveLogic.getNextPrcsByPassLeave(dbConn, flowId, leadPrcsLeave);
			}else
				{
					//判断下一步结点
					WorkFlowNextCheckTool  workFlowNextCheckTool = new WorkFlowNextCheckTool();
					nextPrcsList =workFlowNextCheckTool.workFlowNextCheckLogic(dbConn, runId, Integer.parseInt(prcsId), flowId,tableName);
				}
			if(nextPrcsList.equals("0"))
			{
				flowRunPrcsLogic.endWorkFlow(dbConn, runId);
				WorkListLogic workListLogic = new WorkListLogic();
				workListLogic.updateStatusLogic(dbConn, runId, Integer.parseInt(runPrcsId), account.getAccountId());
				flowRunLogic.endFlowRunLogic(dbConn, runId);
			}else{
				WorkFlowAutoUserTool wfaut = new WorkFlowAutoUserTool();
			JSONArray temp =wfaut.getAutoUserM(dbConn,flowId,runId,nextPrcsList,account,Integer.parseInt(prcsId),Integer.parseInt(runPrcsId));
			
			
			returnjson.accumulate("status_code", "200");
			returnjson.accumulate("msg", "请求成功");
			JSONObject jsondata = new JSONObject();
			jsondata.accumulate("time", System.currentTimeMillis());
			JSONObject data = new JSONObject();
			if(flowProcess.getForceParallel().equals("1"))
			{
			data.accumulate("optional", 0);
			}else if(flowProcess.getForceParallel().equals("0"))
			{
				data.accumulate("optional", 2);
			}else if(flowProcess.getForceParallel().equals("2"))
			{
				data.accumulate("optional", 1);
			}
			data.accumulate("prcs", temp);
			jsondata.accumulate("data", data);
			returnjson.accumulate("data", jsondata);
			}
			if (dataTemp!=null) {
				data=returnjson;
			}
			dbConn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 步骤人员列表，加载方式，整个人员列表
	 */
	public void getPrcsUserList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform = request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String flowId = sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String prcsId = sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			
			
			JSONObject dataTemp=logic.getTestData("getPrcsUserList");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	
	/**
	 * 步骤人员列表，加载方式，部门-人员列表
	 */
	public void getPrcsDeptUser(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("getPrcsDeptUser");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	public void choseWorkflow(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("choseWorkflow");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 流程审批，保存 
	 */
	public void save(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform = request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			String workTitle =sysUtil.checkParam(response,"workTitle",request.getParameter("workTitle"));
			String runPrcsId =sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId"));
			String prcsId =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String formDataStr = request.getParameter("formData");
			String idea =sysUtil.checkParam(response,"idea",request.getParameter("idea"));
			String ideaText = request.getParameter("ideaText");
			String flowLeave = request.getParameter("flowLeave");
			if(SysTool.isNullorEmpty("flowLeave"))
			{
				flowLeave="0";
			}
			String flowData = URLDecoder.decode(formDataStr, "utf-8");
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			WorkFlow workFlow = new WorkFlow();
			workFlow=workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(dbConn, workFlow.getFormId());
			
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRunLogic.updateLeaveAndTitleLogic(dbConn, runId, workTitle, flowLeave);
			
			WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
			Map <String,String> map = new HashMap<String,String>();
			map=workFlowDataTool.getDataMap(flowData);
			FlowProcessWriterItemTool writerFieldTool = new  FlowProcessWriterItemTool();
			String[] writerField= writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, Integer.parseInt(prcsId));
			//保存表单数据
			JSONObject dataTemp=logic.saveFlowFormDataLogic(dbConn, map, writerField,runId,tableName);	
			
			
			if (dataTemp!=null) {
				data=dataTemp;
			}
			dbConn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 流程审批，办理结束
	 */
	public void dealWithEnd(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform = request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			String runPrcsId =sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId"));
			String prcsId =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String formDataStr = request.getParameter("formData");
			String idea =sysUtil.checkParam(response,"idea",request.getParameter("idea"));
			String ideaText = request.getParameter("ideaText");
			//String flowData = URLDecoder.decode(formDataStr, "utf-8");
			//WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			//WorkFlow workFlow = new WorkFlow();
			//workFlow=workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			//WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			//String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(dbConn, workFlow.getFormId());
			//WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
			//Map <String,String> map = new HashMap<String,String>();
			//map=workFlowDataTool.getDataMap(flowData);
			//FlowProcessWriterItemTool writerFieldTool = new  FlowProcessWriterItemTool();
			//String[] writerField= writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, Integer.parseInt(prcsId));
			//保存表单数据
			//logic.saveFlowFormDataLogic(dbConn, map, writerField,runId,tableName);	
			//保存会签意见
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, Integer.parseInt(runPrcsId), idea, ideaText,account.getAccountId());
			//更新待办记录状态
			WorkListLogic workListLogic = new WorkListLogic();
			workListLogic.updateStatusLogic(dbConn, runId, Integer.parseInt(runPrcsId)-1, account.getAccountId());
			
			JSONObject returnjson = new JSONObject();
			returnjson.accumulate("status_code", "200");
			returnjson.accumulate("msg", "请求成功");
			JSONObject jsondata = new JSONObject();
			jsondata.accumulate("time", System.currentTimeMillis());
			JSONObject data = new JSONObject();
			data.accumulate("msg", "办理完成！");
			jsondata.accumulate("data", data);
			returnjson.accumulate("data", jsondata);
			if (returnjson!=null) {
				data=returnjson;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	
	
	/**
	 * 流程审批，结束流程
	 */
	public void dealWithEndWorkflow(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String platform = request.getParameter("platform");
			String version=request.getParameter("version");
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			String runId =sysUtil.checkParam(response,"runId",request.getParameter("runId"));
			String runPrcsId =sysUtil.checkParam(response,"runPrcsId",request.getParameter("runPrcsId"));
			String prcsId =sysUtil.checkParam(response,"prcsId",request.getParameter("prcsId"));
			String flowId =sysUtil.checkParam(response,"flowId",request.getParameter("flowId"));
			String formDataStr = request.getParameter("formData");
			String idea =sysUtil.checkParam(response,"idea",request.getParameter("idea"));
			String ideaText = request.getParameter("ideaText");
			String flowData = URLDecoder.decode(formDataStr, "utf-8");
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			WorkFlow workFlow = new WorkFlow();
			workFlow=workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(dbConn, workFlow.getFormId());
			WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
			Map <String,String> map = new HashMap<String,String>();
			map=workFlowDataTool.getDataMap(flowData);
			FlowProcessWriterItemTool writerFieldTool = new  FlowProcessWriterItemTool();
			String[] writerField= writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, Integer.parseInt(prcsId));
			//保存表单数据
			logic.saveFlowFormDataLogic(dbConn, map, writerField,runId,tableName);	
			//保存会签意见
			FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
			flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, Integer.parseInt(runPrcsId), idea, ideaText,account.getAccountId());
			//更新待办记录状态
			WorkListLogic workListLogic = new WorkListLogic();
			workListLogic.updateStatusLogic(dbConn, runId, Integer.parseInt(runPrcsId)-1, account.getAccountId());
			//更新流程步骤状态
			flowRunPrcsLogic.updateStatusLogic(dbConn, runId,Integer.parseInt(runPrcsId)-1, account.getAccountId(), Integer.parseInt(runPrcsId));
			//更淅主表数据
			//判断步骤是不是结束结点
			JSONObject returnjson = new JSONObject();
			if(Integer.parseInt(prcsId)==0)
			{
				FlowRunLogic flowRunLogic = new FlowRunLogic();
				flowRunLogic.endFlowRunLogic(dbConn, runId);
				returnjson.accumulate("status_code", "200");
				returnjson.accumulate("msg", "请求成功");
				JSONObject jsondata = new JSONObject();
				jsondata.accumulate("time", System.currentTimeMillis());
				JSONObject data = new JSONObject();
				data.accumulate("msg", "流程已结束！");
				jsondata.accumulate("data", data);
				returnjson.accumulate("data", jsondata);
			}
			if (returnjson!=null) {
				data=returnjson;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	
	/**
	 * 流程审批，回退
	 */
	public void getTurnnextPrcsMessageOfRollback(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("getTurnnextPrcsMessageOfRollback");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
	
	
	/**
	 * 流程审批，回退，提交
	 */
	public void turnnextOfRollback(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			JSONObject dataTemp=logic.getTestData("turnnextOfRollback");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
}
