package tfd.system.workflow.workflow.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.db.PageTool;
import com.system.tool.SysTool;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowprocess.tool.FlowProcessTool;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflowtype.logic.WorkFlowTypeLogic;

public class WorkFlowLogic {
	public String getWorkFlowJson(Connection conn,Account account) throws SQLException{
		JSONArray jsonArr = new JSONArray();
		PreparedStatement ps=null;
		ResultSet rs = null;
		String queryStr="";
        queryStr="SELECT FLOW_TYPE_ID,LEAVE_ID,FLOW_TYPE_NAME FROM WORK_FLOW_TYPE WHERE MANAGE_ACCOUNT_ID =? AND ORG_ID=?";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
	    rs=ps.executeQuery();
			  while(rs.next())
			  {
				  JSONObject json = new JSONObject();
				  json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
				  json.accumulate("pId", rs.getString("LEAVE_ID"));
				  json.accumulate("name", rs.getString("FLOW_TYPE_NAME"));
				  json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
				  json.accumulate("isParent", "true");
				  jsonArr.add(json);
			  }
	  queryStr ="SELECT FLOW_TYPE_ID,FLOW_NAME,WORK_FLOW_TYPE_ID FROM WORK_FLOW WHERE FLOW_CREATE_USER=? AND MODULE_ID=? AND ORG_ID=?";
	  ps=conn.prepareStatement(queryStr);
	  ps.setString(1, account.getAccountId());
	  ps.setString(2, "workflow");
	  ps.setString(3, account.getOrgId());
	  rs=ps.executeQuery();
	  while(rs.next())
	  {
		  JSONObject json = new JSONObject();
		  json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
		  json.accumulate("pId", rs.getString("WORK_FLOW_TYPE_ID"));
		  json.accumulate("name", rs.getString("FLOW_NAME"));
		  json.accumulate("icon", "/tfd/system/styles/images/file_tree/workflow.gif");
		  jsonArr.add(json);
	  }
	  rs.close();
	  ps.close();
	  return jsonArr.toString();
	}
//获取流程分类中流程列表
	public String getListByWorkFlowTypeIdLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		String queryStr="SELECT W.ID AS ID,W.FLOW_TYPE_ID AS FLOW_TYPE_ID, W.FLOW_NAME AS FLOW_NAME,W.FLOW_TYPE AS FLOW_TYPE,W.FLOW_CREATE_USER AS FLOW_CREATE_USER,W.MODULE_ID AS MODULE_ID,U.USER_NAME AS USER_NAME,W.ORG_ID AS ORG_ID "+
									"FROM WORK_FLOW W , USER_INFO U "+
									"WHERE W.FLOW_CREATE_USER=U.ACCOUNT_ID AND WORK_FLOW_TYPE_ID=? AND W.FLOW_CREATE_USER=? AND W.ORG_ID=?";
		String optStr= "[{'function':'edit','name':'基本属性','parm':'FLOW_TYPE_ID'},{'function':'designFlow','name':'设计流程','parm':'FLOW_TYPE_ID'},"
								+"{'function':'delFlow','name':'删除','parm':'FLOW_TYPE_ID'},{'function':'clearData','name':'初始化','parm':'FLOW_TYPE_ID'},"
								+"{'function':'moreOpt','name':'更多..','parm':'FLOW_TYPE_ID'}]";
		JSONArray optArrJson = new JSONArray().fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	
	public String getFormIdByFlowTypeIdLogic(Connection conn,String flowId) throws SQLException
	{
		ResultSet rs = null;
		String returndata="";
		String queryStr="SELECT FORM_ID FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returndata=rs.getString("FORM_ID");
		}
		rs.close();
		ps.close();
		return returndata;
	}
	
	public String getWorkFLowByFlowIdLogic(Connection conn,String flowTypeId,Account account) throws SQLException
	{
		String queryStr="";
		PreparedStatement ps=null;
		ResultSet rs = null;
		String printField="";
		String formId="";
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
		WorkFlowTypeLogic workFlowTypeLogic = new WorkFlowTypeLogic();
		JSONObject json = new JSONObject();
		queryStr="SELECT ID,FLOW_ID,SORT_ID,FLOW_NAME,FORM_ID,FLOW_TYPE,WORK_FLOW_TYPE_ID,FLOW_CREATE_USER" +
				",FLOW_CREATE_TIME,MODULE_ID,LEAVE_PASS,FLOW_LOCK,FREE_TO_OTHER,FLOW_DOC,AUTO_CODE,AUTO_NUM,SEND_TO_USER,SEND_TO_MODULE,SAVE_PATH,SAVE_FILE,FLOW_WAIT,"
				+ "FLOW_FUNCTION,PRINT_FIELD FROM WORK_FLOW WHERE FLOW_TYPE_ID=? AND FLOW_CREATE_USER=?";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ps.setString(2, account.getAccountId());
		rs=ps.executeQuery();
		while(rs.next())
			  {
				  json.accumulate("id", rs.getString("ID"));
				  json.accumulate("flowId", rs.getString("FLOW_ID"));
				  json.accumulate("sortId", rs.getString("SORT_ID"));
				  json.accumulate("flowName", rs.getString("FLOW_NAME"));
				  json.accumulate("formId", rs.getString("FORM_ID"));
				  formId=rs.getString("FORM_ID");
				  json.accumulate("formName", workFlowFormLogic.getFormNameByFormId(conn, rs.getString("FORM_ID")));
				  json.accumulate("flowType", rs.getString("FLOW_TYPE"));
				  json.accumulate("workFlowTypeId", rs.getString("WORK_FLOW_TYPE_ID"));
				  json.accumulate("workFlowTypeName", workFlowTypeLogic.getWorkFlowTypeNameLogic(conn,rs.getString("WORK_FLOW_TYPE_ID")));
				  json.accumulate("flowCreatUser", rs.getString("FLOW_CREATE_USER"));
				  json.accumulate("flowCreateUserName",userInfoLogic.getUserNameByAccountIdLogic(conn, rs.getString("FLOW_CREATE_USER")));
				  json.accumulate("flowCreateTime", rs.getString("FLOW_CREATE_TIME"));
				  json.accumulate("moduleId", rs.getString("MODULE_ID"));
				  json.accumulate("leavePass", rs.getString("LEAVE_PASS"));
				  json.accumulate("flowLock", rs.getString("FLOW_LOCK"));
				  json.accumulate("freeToOther", rs.getString("FREE_TO_OTHER"));
				  json.accumulate("flowDoc", rs.getString("FLOW_DOC"));
				  json.accumulate("autoCode", rs.getString("AUTO_CODE"));
				  json.accumulate("autoNum", rs.getString("AUTO_NUM"));
				  json.accumulate("sendToUser", rs.getString("SEND_TO_USER"));
				  json.accumulate("sendToModule", rs.getString("SEND_TO_MODULE"));
				  json.accumulate("savePath", rs.getString("SAVE_PATH"));
				  json.accumulate("saveFile", rs.getString("SAVE_FILE"));
				  json.accumulate("flowWait", rs.getString("FLOW_WAIT"));
				  printField=","+rs.getString("PRINT_FIELD")+",";
				  if(SysTool.isNullorEmpty(rs.getString("FLOW_FUNCTION")))
				  {
					  json.accumulate("editor", "");  
				  }else
				  {
					  json.accumulate("editor", rs.getString("FLOW_FUNCTION"));
				  }
			  }
		queryStr="SELECT FIELD_NAME,TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND XTYPE<>'xlist'";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, formId);
		rs=ps.executeQuery();
		JSONArray jsontmpArr = new JSONArray();
		while(rs.next())
		{
			JSONObject jsontmp = new JSONObject();
			if(printField.indexOf(","+rs.getString("FIELD_NAME")+",")>=0)
			{
				jsontmp.accumulate("printfield", "1");
			}else
			{
				jsontmp.accumulate("printfield", "0");
			}
			jsontmp.accumulate("title", rs.getString("TITLE"));
			jsontmp.accumulate("field", rs.getString("FIELD_NAME"));
			jsontmpArr.add(jsontmp);
		}
	  json.accumulate("printField", jsontmpArr.toString());
	  rs.close();
	  ps.close();
	return json.toString();
	}
	
	//新建流程
	public int createWorkFlowLogic(Connection conn,WorkFlow workFlow) throws SQLException
	{
		int i=0;
		String queryStr="INSERT INTO WORK_FLOW (FLOW_TYPE_ID,SORT_ID,FLOW_NAME,FORM_ID,FLOW_TYPE,WORK_FLOW_TYPE_ID,FLOW_CREATE_USER,MODULE_ID,ORG_ID,FLOW_CREATE_TIME,FLOW_ID,"
				+ "LEAVE_PASS,FLOW_LOCK,FREE_TO_OTHER,FLOW_DOC,AUTO_CODE,AUTO_NUM,SEND_TO_USER,SEND_TO_MODULE,SAVE_PATH,SAVE_FILE,FLOW_WAIT,FLOW_FUNCTION) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, workFlow.getFlowTypeId());
		ps.setInt(2,  Integer.parseInt(workFlow.getSortId()));
		ps.setString(3, workFlow.getFlowName());
		ps.setString(4, workFlow.getFormId());
		ps.setString(5, workFlow.getFlowType());
		ps.setString(6, workFlow.getWorkFlowTypeId());
		ps.setString(7, workFlow.getFlowCreateUser());
		ps.setString(8, workFlow.getModuleId());
		ps.setString(9, workFlow.getOrgId());
		ps.setString(10, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(11, workFlow.getFlowId());
		ps.setString(12, workFlow.getLeavePass());
		ps.setString(13, workFlow.getFlowLock());
		ps.setString(14, workFlow.getFreeToOther());
		ps.setString(15, workFlow.getFlowDoc());
		ps.setString(16, workFlow.getAutoCode());
		ps.setString(17, workFlow.getAutoNum());
		ps.setString(18, workFlow.getSendToUser());
		ps.setString(19, workFlow.getSendToModule());
		ps.setString(20, workFlow.getSavePath());
		ps.setString(21, workFlow.getSaveFile());
		ps.setString(22, workFlow.getFlowWait());
		ps.setString(23, workFlow.getFlowFunction());
		i=ps.executeUpdate();
		ps.close();
		return i;
	}
	//更新
	public void updateWorkFLowLogic(Connection conn,WorkFlow workFlow) throws SQLException
	{
		String queryStr="UPDATE WORK_FLOW SET SORT_ID=?,FORM_ID=?,FLOW_TYPE=?,WORK_FLOW_TYPE_ID=?,MODULE_ID=?,ORG_ID=?,"
				+ "LEAVE_PASS=?,FLOW_LOCK=?,FREE_TO_OTHER=?,FLOW_DOC=?,AUTO_CODE=?,AUTO_NUM=?,SEND_TO_USER=?,SEND_TO_MODULE=?,SAVE_PATH=?,SAVE_FILE=?,FLOW_WAIT=?,"
				+ "FLOW_NAME=?,FLOW_FUNCTION=?,PRINT_FIELD=? WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, workFlow.getSortId());
		ps.setString(2, workFlow.getFormId());
		ps.setString(3, workFlow.getFlowType());
		ps.setString(4, workFlow.getWorkFlowTypeId());
		ps.setString(5, workFlow.getModuleId());
		ps.setString(6, workFlow.getOrgId());
		ps.setString(7, workFlow.getLeavePass());
		ps.setString(8, workFlow.getFlowLock());
		ps.setString(9, workFlow.getFreeToOther());
		ps.setString(10, workFlow.getFlowDoc());
		ps.setString(11, workFlow.getAutoCode());
		ps.setString(12, workFlow.getAutoNum());
		ps.setString(13, workFlow.getSendToUser());
		ps.setString(14, workFlow.getSendToModule());
		ps.setString(15, workFlow.getSavePath());
		ps.setString(16, workFlow.getSaveFile());
		ps.setString(17, workFlow.getFlowWait());
		ps.setString(18,workFlow.getFlowName());
		ps.setString(19, workFlow.getFlowFunction());
		ps.setString(20, workFlow.getPrintField());
		ps.setString(21, workFlow.getFlowTypeId());
		ps.executeUpdate();
		ps.close();
	}
	//更新工作流程表单缓存
	public void updateFlowCahceLogic(Connection conn,String flowTypeId) throws Exception
	{
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		ArrayList<Integer> list = flowProcessLogic.getAllPrcsIdByFlowIdLogic(conn, flowTypeId);
		FlowProcessTool flowProcessTool = new FlowProcessTool();
		for(int i=0;list.size()>i;i++)
		{
			flowProcessTool.createFlowPrcsFile(conn, list.get(i), flowTypeId);
		}
	}
	//通过FLOW_TYPE_ID获取流程名称
	public String getFlowName(Connection conn,String flowTypeId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT FLOW_NAME FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData = rs.getString("FLOW_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//通过FLOW_TYPE_ID获取流程实例
	public WorkFlow getWorkFlowByFlowTypeIdLogic(Connection conn,String flowTypeId) throws SQLException
	{
		WorkFlow workFlow = new WorkFlow();
		String queryStr="SELECT FLOW_TYPE_ID,SORT_ID,FLOW_NAME,FORM_ID,FLOW_TYPE,WORK_FLOW_TYPE_ID,"
				+ "FLOW_CREATE_USER,MODULE_ID,ORG_ID,FLOW_CREATE_TIME,FLOW_ID,LEAVE_PASS,FLOW_LOCK,FREE_TO_OTHER,"
				+ "FLOW_DOC,AUTO_CODE,AUTO_NUM,SEND_TO_USER,SEND_TO_MODULE,SAVE_PATH,SAVE_FILE,FLOW_WAIT,ALL_QUERY_USER,DEPT_QUERY_USER,"
				+ "OTHER_DEPT_QUERY_USER,LEAVE_QUERY_USER,ALL_MANAGE_USER,DEPT_MANAGE_USER,OTHER_DEPT_MANAGE_USER,FLOW_FUNCTION,PRINT_FIELD FROM WORK_FLOW  WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			workFlow.setFlowTypeId(rs.getString("FLOW_TYPE_ID"));
			workFlow.setSortId(rs.getString("SORT_ID"));
			workFlow.setFlowName(rs.getString("FLOW_NAME"));
			workFlow.setFormId(rs.getString("FORM_ID"));
			workFlow.setFlowType(rs.getString("FLOW_TYPE"));
			workFlow.setWorkFlowTypeId(rs.getString("WORK_FLOW_TYPE_ID"));
			workFlow.setFlowCreateUser(rs.getString("FLOW_CREATE_USER"));
			workFlow.setModuleId(rs.getString("MODULE_ID"));
			workFlow.setOrgId(rs.getString("ORG_ID"));
			workFlow.setFlowCreateTime(rs.getString("FLOW_CREATE_TIME"));
			workFlow.setFlowId(rs.getString("FLOW_ID"));
			workFlow.setLeavePass(rs.getString("LEAVE_PASS"));
			workFlow.setFlowLock(rs.getString("FLOW_LOCK"));
			workFlow.setFreeToOther(rs.getString("FREE_TO_OTHER"));
			workFlow.setFlowDoc(rs.getString("FLOW_DOC"));
			workFlow.setAutoCode(rs.getString("AUTO_CODE"));
			workFlow.setAutoNum(rs.getString("AUTO_NUM"));
			workFlow.setSendToUser(rs.getString("SEND_TO_USER"));
			workFlow.setSendToModule(rs.getString("SEND_TO_MODULE"));
			workFlow.setSavePath(rs.getString("SAVE_PATH"));
			workFlow.setSaveFile(rs.getString("SAVE_FILE"));
			workFlow.setFlowWait(rs.getString("FLOW_WAIT"));
			workFlow.setAllQueryUser(rs.getString("ALL_QUERY_USER"));
			workFlow.setDeptQueryUser(rs.getString("DEPT_QUERY_USER"));
			workFlow.setOtherDeptQueryUser(rs.getString("OTHER_DEPT_QUERY_USER"));
			workFlow.setLeaveQueryUser(rs.getString("LEAVE_QUERY_USER"));
			workFlow.setAllManageUser(rs.getString("ALL_MANAGE_USER"));
			workFlow.setDeptManageUser(rs.getString("DEPT_MANAGE_USER"));
			workFlow.setOtherDeptManageUser(rs.getString("OTHER_DEPT_MANAGE_USER"));
			workFlow.setFlowFunction(rs.getString("FLOW_FUNCTION"));
			workFlow.setPrintField(rs.getString("PRINT_FIELD"));
		}
		rs.close();
		ps.close();
		return workFlow;
	}
	//判断流程否结束
	public boolean getWorkFlowEndLogic(Connection conn,String runId) throws SQLException
	{
		boolean returnData=false;
		String flag="";
		String queryStr="SELECT STATUS FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			flag=rs.getString("STATUS");
		}
		if(flag.equals("1"))
		{
			returnData=true;
		}
		return returnData;
	}
	//按照FlowTypeId删除流程
	public int delFLowByFlowTypeIdLogic(Connection conn,String flowTypeId) throws SQLException
	{
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		boolean flag=flowRunLogic.getFlowRunExistLogic(conn, flowTypeId);
		int returnData=0;
		//这里没有判断流程是否能被删除,日后补上
		if(!flag)
		{
			String queryStr="DELETE FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, flowTypeId);
			returnData=ps.executeUpdate();
			ps.close();
		}
		
		return returnData;
	}
	
	//设置管理权限
	public void setWorkFlowManagePrivLogic(Connection conn, String accountId, String privType,String flowTypeId) throws SQLException
	{
		String queryStr="UPDATE WORK_FLOW SET";
		if(privType.equals("1"))
		{
			queryStr+=" ALL_QUERY_USER=? ";
		}
		if(privType.equals("2"))
		{
			queryStr+=" DEPT_QUERY_USER=? ";
		}
		if(privType.equals("3"))
		{
			queryStr+=" OTHER_DEPT_QUERY_USER=? ";
		}
		if(privType.equals("4"))
		{
			queryStr+=" LEAVE_QUERY_USER=? ";
		}
		if(privType.equals("5"))
		{
			queryStr+=" ALL_MANAGE_USER=? ";
		}
		if(privType.equals("6"))
		{
			queryStr+=" DEPT_MANAGE_USER=? ";
		}
		if(privType.equals("7"))
		{
			queryStr+=" OTHER_DEPT_MANAGE_USER=? ";
		}
		queryStr+="WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, flowTypeId);
		ps.executeUpdate();
		ps.close();
		
	}
	//获取相关管理权限(返回的是人员的姓名)
	public String getWorkFlowManagePrivLogic(Connection conn,String flowTypeId) throws SQLException
	{
		AccountLogic accountLogic = new AccountLogic();
		JSONObject json = new JSONObject();
		String queryStr="SELECT ALL_QUERY_USER,DEPT_QUERY_USER,OTHER_DEPT_QUERY_USER, LEAVE_QUERY_USER,ALL_MANAGE_USER,DEPT_MANAGE_USER, OTHER_DEPT_MANAGE_USER FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			json.accumulate("allQueryUser", accountLogic.getUserNameStr(conn,rs.getString("ALL_QUERY_USER")));
			json.accumulate("allQueryAccountId", rs.getString("ALL_QUERY_USER"));
			json.accumulate("deptQueryUser", accountLogic.getUserNameStr(conn,rs.getString("DEPT_QUERY_USER")));
			json.accumulate("deptQueryAccountId",rs.getString("DEPT_QUERY_USER"));
			json.accumulate("otherDeptQueryUser", accountLogic.getUserNameStr(conn,rs.getString("OTHER_DEPT_QUERY_USER")));
			json.accumulate("otherDeptQueryAccountId",rs.getString("OTHER_DEPT_QUERY_USER"));
			json.accumulate("leaveQueryUser", accountLogic.getUserNameStr(conn,rs.getString("LEAVE_QUERY_USER")));
			json.accumulate("leaveQueryAccountId",rs.getString("LEAVE_QUERY_USER"));
			json.accumulate("allManageUser", accountLogic.getUserNameStr(conn,rs.getString("ALL_MANAGE_USER")));
			json.accumulate("allManageAccountId",rs.getString("ALL_MANAGE_USER"));
			json.accumulate("deptManageUser", accountLogic.getUserNameStr(conn,rs.getString("DEPT_MANAGE_USER")));
			json.accumulate("deptManageAccountId",rs.getString("DEPT_MANAGE_USER"));
			json.accumulate("otherDeptManageUser", accountLogic.getUserNameStr(conn,rs.getString("OTHER_DEPT_MANAGE_USER")));
			json.accumulate("otherDeptManageAccountId",rs.getString("OTHER_DEPT_MANAGE_USER"));
		}
		rs.next();
		ps.close();
		return json.toString();
	}
	
	//获取流程相关的数据库表名
	public String getPreviewPathLogic(Connection conn,String flowId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT F.FORM_TABLE_NAME AS FORM_TABLE_NAME FROM WORK_FLOW W, WORK_FLOW_FORM F WHERE W.FORM_ID=F.FORM_ID AND W.FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("FORM_TABLE_NAME");
		}
		if(!SysTool.isNullorEmpty(returnData))
		{
			returnData = returnData.toLowerCase()+"/preview/index.jsp";
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//获取流程相关的数据库表名
	public String getFlowFunctionLogic(Connection conn,String flowId) throws SQLException
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT FLOW_FUNCTION FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			if(!SysTool.isNullorEmpty(rs.getString("FLOW_FUNCTION")))
			{
				json.accumulate("flowFunction", rs.getString("FLOW_FUNCTION"));
			}else
			{
				json.accumulate("flowFunction", "无！");
			}
		}
		JSONArray jsonArr = new JSONArray();
		queryStr="SELECT PRCS_ID,PRCS_NAME,NEXT_PRCS FROM FLOW_PROCESS WHERE FLOW_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		rs = ps.executeQuery();
		while(rs.next())
		{
			if(rs.getString("PRCS_ID").equals("1"))
			{
				JSONObject jsona = new JSONObject();
				jsona.accumulate("prcsId", rs.getString("PRCS_ID"));
				jsona.accumulate("prcsName", rs.getString("PRCS_NAME"));
				jsona.accumulate("nextPrcs", rs.getString("NEXT_PRCS"));
				json.accumulate("beginPrcs", jsona);
			}else if(rs.getString("PRCS_ID").equals("0"))
			{
				JSONObject jsonb = new JSONObject();
				jsonb.accumulate("prcsId", rs.getString("PRCS_ID"));
				jsonb.accumulate("prcsName", rs.getString("PRCS_NAME"));
				jsonb.accumulate("nextPrcs", rs.getString("NEXT_PRCS"));
				json.accumulate("endPrcs", jsonb);
			}else
			{
				JSONObject jsonc = new JSONObject();
				jsonc.accumulate("prcsId", rs.getString("PRCS_ID"));
				jsonc.accumulate("prcsName", rs.getString("PRCS_NAME"));
				jsonc.accumulate("nextPrcs", rs.getString("NEXT_PRCS"));
				jsonArr.add(jsonc);
			}
		}
		json.accumulate("proPrcs", jsonArr);
		rs.close();
		ps.close();
		return json.toString();
	}
	//初始化工作流，清空历史记录
	public int clearWorkFlowDataByFlowIdLogic(Connection conn,String flowTypeId) throws SQLException
	{
		FlowRunPrcsLogic flowRunPrcLogic= new FlowRunPrcsLogic();
		flowRunPrcLogic.delFlowRunPrcsByFlowIdLogci(conn, flowTypeId);
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		int i = flowRunLogic.delFlowRunByFlowIdLogic(conn, flowTypeId);
		return i;
	}
	//按工作流名称查询
		public String getWorkFlowByFlowNameLogic(Connection conn,String workselect,Account account) throws SQLException
		{
			String queryStr="SELECT FLOW_TYPE_ID,FLOW_NAME FROM WORK_FLOW WHERE ORG_ID=? AND FLOW_NAME LIKE '%"+workselect+"%'";
			JSONArray jsonArr = new JSONArray();
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, account.getOrgId());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.accumulate("flowName", rs.getString("FLOW_NAME"));
				json.accumulate("flowTypeId", rs.getString("FLOW_TYPE_ID"));
				jsonArr.add(json);
			}
			return jsonArr.toString();
		}
}
