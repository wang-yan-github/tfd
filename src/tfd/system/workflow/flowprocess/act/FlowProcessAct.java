package tfd.system.workflow.flowprocess.act;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowprocess.tool.FlowProcessTool;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;

public class FlowProcessAct {
	// 获取图形JSON
	public void getFlowProcessList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getFlowProcessListLogic(dbConn,flowId);
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

	// 保存图形布局
	public void updateFlowProcessLayoutAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String layoutArr = request.getParameter("layoutArr");
			String[] layoutStr = layoutArr.split("#");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.updateFlowProcessLayoutLogic(dbConn, layoutStr, flowId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("true");
			response.getWriter().flush();
		}
	}

	// 添加普通节点
	public void addSimpleNodeAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String prcsType = request.getParameter("prcsType");
			String parentId = request.getParameter("parentId");
			String printx = request.getParameter("x");
			String printy = request.getParameter("y");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.addSimpleNodelogic(dbConn, flowId,prcsType, parentId, printx, printy);
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

	// 删除节点
	public void removeNodeAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String prcsId = request.getParameter("prcsId");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.removeNodeLoic(dbConn, prcsId, flowId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("true");
			response.getWriter().flush();
		}
	}
//获取步骤的公共附件权限
	public void getPublicFilePrivAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		String returnData="";
		try{
			String flowId = request.getParameter("flowId");
			String prcsId = request.getParameter("prcsId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData=flowProcessLogic.getPublicFilePrivLogic(dbConn, flowId, prcsId);
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
	// 获取步骤可写字段
	public void getWriterByFlowId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String prcsId = request.getParameter("prcsId");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getWriterByFlowIdLogic(dbConn,prcsId, flowId);
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

	// 更新可写字段
	public void updateWriter(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn =null;
		try{
			String writerFieldStr = request.getParameter("writerFieldStr");
			String publicFile = request.getParameter("publicFile");
			if (writerFieldStr.length() > 1) {
				writerFieldStr = writerFieldStr.substring(0,
						writerFieldStr.length() - 1);
			}
			String flowId = request.getParameter("flowId");
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.updateWriterFieldLogic(dbConn, flowId, prcsId,writerFieldStr,publicFile);
			FlowProcessTool flowProcessTool = new FlowProcessTool();
			flowProcessTool.createFlowPrcsFile(dbConn, prcsId, flowId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("true");
			response.getWriter().flush();
		}
	}

	// 获取下一节点信息列表
	public void getAllNextPrcsJson(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String returnData = "";
		Connection dbConn=null;
		try{
			String flowId = request.getParameter("flowId");
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			String nextPrcsList = "";
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			nextPrcsList = flowProcessLogic.getFlorProcessNextLogic(dbConn, flowId,prcsId);
			returnData = flowProcessLogic.getConditionJson(dbConn, flowId, prcsId,	nextPrcsList);
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

	// 更新步骤条件
	public void updatePrcsConditionAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		try{
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			String flowId = request.getParameter("flowId");
			String condition = request.getParameter("requestDataModel");
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.updatePrcsConditionLoigc(dbConn, flowId, prcsId,condition);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("{\"STATUS\":\"OK\"}");
			response.getWriter().flush();
		}
	}

	// 获取步骤条件
	public void getPrcsConditionAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn =null;
		String returnData="";
			try{
				int prcsId = Integer.parseInt(request.getParameter("prcsId"));
				String flowId = request.getParameter("flowId");
				dbConn = DbPoolConnection.getInstance().getConnection();
				FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
				returnData = flowProcessLogic.getPrcsConditionLoigc(dbConn,flowId, prcsId);
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

	// 更新步骤条件
	public void updatePrcsBasicAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String flowId = request.getParameter("flowId");
		String prcsName = request.getParameter("prcsName");
		String leadPrcsLeave = request.getParameter("leadPrcsLeave");
		String follow =request.getParameter("follow");
		try{
		String[] nextPrcses = request.getParameterValues("allPrcs");
		String nextPrcs = "";
		if (nextPrcses != null) {
			nextPrcs = Arrays.toString(nextPrcses).substring(1, Arrays.toString(nextPrcses).length() - 1).replaceAll(" ", "");
		}
		String prcsType = request.getParameter("prcsType");
		String forceParallel = request.getParameter("forceParallel");
		String forceAggregation = request.getParameter("forceAggregation");
		String memo = request.getParameter("memo");
		String goBack = request.getParameter("goBack");
		String passTimeStre="0";
		if(!SysTool.isNullorEmpty(request.getParameter("passTime")))
		{
			passTimeStre=request.getParameter("passTime");
		}
		int passTime = Integer.parseInt(passTimeStre);
		dbConn = DbPoolConnection.getInstance().getConnection();
		FlowProcess flowProcess = new FlowProcess();
		flowProcess.setFollow(follow);
		flowProcess.setPrcsName(prcsName);
		flowProcess.setForceParallel(forceParallel);
		flowProcess.setForceAggregation(forceAggregation);
		flowProcess.setLeadPrcsLeave(leadPrcsLeave);
		flowProcess.setNextPrcs(nextPrcs);
		flowProcess.setPrcsType(prcsType);
		flowProcess.setMemo(memo);
		flowProcess.setGoBack(goBack);
		flowProcess.setPassTime(passTime);
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		flowProcessLogic.updatePrcsBasicLogic(dbConn, flowId, prcsId,flowProcess);
		dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
		request.getRequestDispatcher("/system/workflow/flowprocess/designflow/setprops/basic.jsp?flowId="+ flowId + "&prcsId=" + prcsId + "&type=1").forward(request, response);
	}

	// 获取子流程步骤基信息
	public void getPrcsChildAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		String returnData="";
			try{
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			String flowId = request.getParameter("flowId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getPrcsChildLogic(dbConn, flowId,	prcsId);
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

	// 获取步骤基本信息
	public void getPrcsBasicAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		String returnData="";
		try{
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			String flowId = request.getParameter("flowId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getPrcsBasicLogic(dbConn, flowId,prcsId);
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

	// 指向结束结点
	public void nextToEndPrcsAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		Connection dbConn=null;
		try{
		String flowId = request.getParameter("flowId");
		dbConn = DbPoolConnection.getInstance().getConnection();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		flowProcessLogic.nextToEndPrcsLogic(dbConn, flowId, prcsId);
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
			response.getWriter().flush();
		}
	}

	// 添加并发结点
	public void addParalleFlowAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String returnData = "";
		Connection dbConn = null;
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String flowId = request.getParameter("flowId");
		String prcsType = request.getParameter("prcsType");
		String parentId = request.getParameter("parentId");
		String printx = request.getParameter("x");
		String printy = request.getParameter("y");
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		returnData = flowProcessLogic.addParalleNodelogic(dbConn, flowId,prcsType, parentId, printx, printy);
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

	// 添加子流程节点
	public void addChildFlowAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String returnData = "";
		Connection dbConn =null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String prcsType = request.getParameter("prcsType");
			String parentId = request.getParameter("parentId");
			String printx = request.getParameter("x");
			String printy = request.getParameter("y");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.addSimpleNodelogic(dbConn, flowId,prcsType, parentId, printx, printy);
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

	// 设置流程知能选人规则
	public void setAutoUserTypeAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			String sPrcsAuto = request.getParameter("sPrcsAuto");
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			JSONObject json = new JSONObject();
			json.accumulate("autoUserType", request.getParameter("autoUserType"));
			json.accumulate("autoUserRule", request.getParameter("autoUserRule"));
			json.accumulate("autoFormField", request.getParameter("autoFormField"));
			json.accumulate("autoDeptId", request.getParameter("autoDeptId"));
			json.accumulate("defUserId", request.getParameter("defUserId"));
			String autoUserModle = json.toString();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.setAutoUserTypeLogic(dbConn, flowId, prcsId,autoUserModle, sPrcsAuto);
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
			response.getWriter().flush();
		}
	}

	// 初始化智能选人页面
	public void getAutoUserTypeJsonAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String returnData ="";
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getAutoUserTypeJsonLogic(dbConn,flowId, prcsId);
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

	// 获取流程步骤列表SELECT
	public void getFlowProcessSelsectAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String flowId = request.getParameter("flowId");
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getFlowProcessSelsect(dbConn,flowId);
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
	
	//通过runid获取步骤SELECT
	public void getFlowProcessSelsectByRunIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String runId = request.getParameter("runId");
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			String flowId= flowRunLogic.getFlowIdByRunIdLogic(dbConn, runId);
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getFlowProcessSelsect(dbConn,flowId);
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

	// 获取列表XLIST控件相关信息
	public void getXlistAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		String table = request.getParameter("table");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String returnData="";
		Connection dbConn =null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getOneChildTableModelpublic(dbConn, flowId, prcsId, table);
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

	// 保存Xlist步骤权限
	public void saveXlistSetAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		String model = request.getParameter("model");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.updateChildTableModelLogic(dbConn, flowId, prcsId,model);
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
			response.getWriter().flush();
		}	
	}

	// 设置经办权限
	public void setUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		String userId = request.getParameter("userId");
		String deptId = request.getParameter("deptId");
		String userPrivId = request.getParameter("userPrivId");
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			flowProcessLogic.updateUserPriv(dbConn, flowId, prcsId, userId, deptId,userPrivId);
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
			response.getWriter().flush();
		}
	}

	// 更新子流程设置信息
	public String updateForChildFlowAct(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String flowId = request.getParameter("flowId");
		int prcsId = Integer.parseInt(request.getParameter("prcsId"));
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowProcess flowProcess = new FlowProcess();
		flowProcess.setFlowId(flowId);
		flowProcess.setPrcsId(prcsId);
		flowProcess.setPrcsName(request.getParameter("prcsName"));
		flowProcess.setPrcsType(request.getParameter("prcsType"));
		flowProcess.setParnetWait(request.getParameter("parnetWait"));
		flowProcess.setCopyToChild(request.getParameter("copyToChild"));
		flowProcess.setShareFlowDoc(request.getParameter("shareFlowDoc"));
		String childFlowId =request.getParameter("childFlow");
		flowProcess.setChildFlow(childFlowId);
		FlowProcess tmpfp  = new FlowProcess();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		tmpfp=flowProcessLogic.getFlowProcessLogic(dbConn, childFlowId, 1);
		flowProcess.setUserPriv(tmpfp.getUserPriv());
		flowProcess.setDeptPriv(tmpfp.getDeptPriv());
		flowProcess.setPrivId(tmpfp.getPrivId());
		flowProcessLogic.updateForChildFlowLogic(dbConn, flowProcess);
		dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
		return "/system/workflow/flowprocess/designflow/setprops/childnodeset.jsp?flowId="
				+ flowId + "&prcsId=" + prcsId;
	}
	//获取经办权限的人员设置
	public void getUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flowId = request.getParameter("flowId");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			returnData = flowProcessLogic.getUserPrivLogic(dbConn, flowId, prcsId);
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
//更新数据更验证
	public void updateValidatorAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String mustfield = request.getParameter("mustfield");
		String hiddenfield = request.getParameter("hiddenfield");
		String flowId = request.getParameter("flowId");
		int prcsId =  Integer.parseInt(request.getParameter("prcsId"));
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		int returnData = flowProcessLogic.updateValidatorLogic(dbConn, flowId, prcsId, mustfield, hiddenfield);
		dbConn.commit();
		dbConn.commit();
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
	
	//设置提醒方式
	public void setSmsConfigAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String smsConfig=request.getParameter("smsConfig");
		String flowId = request.getParameter("flowId");
		int returnData=0;
		int prcsId =  Integer.parseInt(request.getParameter("prcsId"));
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic  = new FlowProcessLogic();
			returnData = flowProcessLogic.setSmsConfigLogic(dbConn, flowId, prcsId, smsConfig);
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
	
	//获取SMSCONFIG设置
	public void getSmsConfigAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		String returnData="";
		Connection dbConn=null;
		try{
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			dbConn = DbPoolConnection.getInstance().getConnection();
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			returnData = flowProcessLogic.getSmsConfigLogic(dbConn, flowId, prcsId);
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
