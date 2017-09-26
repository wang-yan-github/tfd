package tfd.system.workflow.newwork.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.workflow.getflowdata.logic.WorkFlowDataLogic;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

public class NewWorkFlowLogic {
	public String getAllWorkLogic(Connection conn,Account account) throws SQLException
	{
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		JSONArray jsonArr = new JSONArray();
		PreparedStatement ps=null;
		ResultSet rs = null;
		String queryStr="SELECT W.FLOW_TYPE_ID AS FLOW_TYPE_ID,W.FLOW_NAME AS FLOW_NAME "
	  		+ " FROM WORK_FLOW W,FLOW_PROCESS F"
	  		+ " WHERE F.PRCS_ID=1 AND W.ORG_ID=? AND W.MODULE_ID=? "
	  		+ " AND F.FLOW_ID=W.FLOW_TYPE_ID AND(CONCAT(',',F.USER_PRIV,',') LIKE '%,"+userInfo.getAccountId()+",%'  OR CONCAT(',',F.DEPT_PRIV,',') LIKE '%,"+userInfo.getDeptId()+",%' OR CONCAT(',',F.PRIV_ID,',') LIKE '%,"+userInfo.getPostPriv()+",%')";
		ps=conn.prepareStatement(queryStr);
	  ps.setString(1, account.getOrgId());
	  ps.setString(2, "workflow");
	  rs=ps.executeQuery();
	  while(rs.next())
	  {
			  JSONObject json = new JSONObject();
			  json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
			  json.accumulate("flowName", rs.getString("FLOW_NAME"));
			  jsonArr.add(json);
	  }
	  rs.close();
	  ps.close();
	  return jsonArr.toString();
	}
	public String getNewWorkJson(Connection conn,Account account) throws SQLException{
		String dbType=DbPoolConnection.getInstance().getDbType();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		JSONArray jsonArr = new JSONArray();
		PreparedStatement ps=null;
		ResultSet rs = null;
		String queryStr="";
        queryStr="SELECT FLOW_TYPE_ID,LEAVE_ID,FLOW_TYPE_NAME FROM WORK_FLOW_TYPE WHERE MODULE_ID=? AND ORG_ID=?";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, "workflow");
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
			  if(dbType.equals("mysql"))
			  {
			  queryStr ="SELECT W.FLOW_TYPE_ID AS FLOW_TYPE_ID,W.FLOW_NAME AS FLOW_NAME,W.WORK_FLOW_TYPE_ID AS WORK_FLOW_TYPE_ID,F.USER_PRIV AS USER_PRIV,F.DEPT_PRIV AS DEPT_PRIV,F.PRIV_ID AS PRIV_ID "
			  		+ " FROM WORK_FLOW W,FLOW_PROCESS F"
			  		+ " WHERE F.PRCS_ID=1 AND W.ORG_ID='"+account.getOrgId()+"' AND W.MODULE_ID='workflow' "
			  		+ " AND F.FLOW_ID=W.FLOW_TYPE_ID AND(CONCAT(',',F.USER_PRIV,',') LIKE '%,"+userInfo.getAccountId()+",%' OR F.USER_PRIV='userAll' "
			  		+ "OR CONCAT(',',F.DEPT_PRIV,',') LIKE '%,"+userInfo.getDeptId()+",%' OR F.DEPT_PRIV='deptAll' "
			  		+ "OR CONCAT(',',F.PRIV_ID,',') LIKE '%,"+userInfo.getPostPriv()+",%' OR F.PRIV_ID='privAll')";
			  }else if(dbType.equals("oracle"))
			  {
				  queryStr ="SELECT W.FLOW_TYPE_ID AS FLOW_TYPE_ID,W.FLOW_NAME AS FLOW_NAME,W.WORK_FLOW_TYPE_ID AS WORK_FLOW_TYPE_ID,F.USER_PRIV AS USER_PRIV,F.DEPT_PRIV AS DEPT_PRIV,F.PRIV_ID AS PRIV_ID "
					  		+ " FROM WORK_FLOW W,FLOW_PROCESS F"
					  		+ " WHERE F.PRCS_ID=1 AND W.ORG_ID='"+account.getOrgId()+"' AND W.MODULE_ID='workflow' "
					  		+ " AND F.FLOW_ID=W.FLOW_TYPE_ID AND(CONCAT(CONCAT(',',F.USER_PRIV),',') LIKE '%,"+userInfo.getAccountId()+",%' OR TO_CHAR(F.USER_PRIV)='userAll' "
					  		+ "OR CONCAT(CONCAT(',',F.DEPT_PRIV),',') LIKE '%,"+userInfo.getDeptId()+",%' OR TO_CHAR(F.DEPT_PRIV)='deptAll' "
					  		+ "OR CONCAT(CONCAT(',',F.PRIV_ID),',') LIKE '%,"+userInfo.getPostPriv()+",%' OR TO_CHAR(F.PRIV_ID)='privAll')";
			  }else if(dbType.equals("sqlserver"))
			  {
				  
			  }
	  ps=conn.prepareStatement(queryStr);
	  rs=ps.executeQuery(queryStr);
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

	public String creatNewWork(Connection conn,FlowRunPrcs flowRunPrcs) throws Exception
	{
		//新建第一步
		String query="INSERT INTO FLOW_RUN_PRCS (RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,PASS,STATUS)" +
				" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setString(1, flowRunPrcs.getRunId());
		ps.setString(2, flowRunPrcs.getFlowId());
		ps.setInt(3, flowRunPrcs.getRunPrcsId());
		ps.setInt(4, flowRunPrcs.getPrcsId());
		ps.setString(5, flowRunPrcs.getTableName());
		ps.setString(6, flowRunPrcs.getPrcsName());
		ps.setString(7, flowRunPrcs.getAccountId());
		ps.setString(8, flowRunPrcs.getUserName());
		ps.setString(9, flowRunPrcs.getDeptId());
		ps.setString(10, flowRunPrcs.getUserPrivId());
		ps.setString(11, flowRunPrcs.getLeadId());
		ps.setString(12,SysTool.getCurDateTimeStr());
		ps.setString(13, flowRunPrcs.getPass());
		ps.setString(14, flowRunPrcs.getStatus());
		ps.executeUpdate();
		ps.close();
		return flowRunPrcs.getTableName().toLowerCase()+"/"+flowRunPrcs.getRunPrcsId()+"/index.jsp";
	}
	//初始化工作流表数据
		public void intrFormData(Connection conn,String flowId,String tableName,String runId) throws SQLException
		{
			String queryStr="INSERT INTO "+tableName+"(RUN_ID) VALUES(?)";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, runId);
			ps.executeUpdate();
			ps.close();
		}
	//初始化子工作流表数据
		public void intrChildFormData(Connection conn,String flowId,String tableName,String runId,String parentRunId) throws SQLException
		{
			String queryStr="INSERT INTO "+tableName+"(RUN_ID,PARENT_RUN_ID) VALUES(?,?)";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, runId);
			ps.setString(2, parentRunId);
			ps.executeUpdate();
			ps.close();
		}
	//新建子流程方法
		public String createChildWorkLogic(Connection conn,Account account,String title,String flowId,String parentFlowId,String parentRunId,FlowProcess flowProcess) throws Exception
		{
			String returnData="";
			String path="";
			String runId=GuId.getGuid();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			String parentformId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, parentFlowId);
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			String tableName=workFlowFormLogic.getFormTableNameByFormIdLogic(conn, formId);
			String parentTableName = workFlowFormLogic.getFormTableNameByFormIdLogic(conn, parentformId);
			//为父流程添加子流程关联
			updateChildRunId(conn,parentTableName,runId,parentRunId);
			//流程步骤
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			//获取第一步的名称
			String prcsName=flowProcessLogic.getFlowProcessPrcsNameLogic(conn, flowId, 1);
			
			FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
			flowRunPrcs.setRunId(runId);
			flowRunPrcs.setFlowId(flowId);
			flowRunPrcs.setTableName(tableName);
			flowRunPrcs.setAccountId(account.getAccountId());
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			UserInfo userInfo=userInfoLogic.getUserInfoByAccount(conn, account);
			flowRunPrcs.setUserName(userInfo.getUserName());
			flowRunPrcs.setPrcsName(prcsName);
			flowRunPrcs.setPrcsId(1);
			flowRunPrcs.setRunPrcsId(1);
			flowRunPrcs.setDeptId(userInfo.getDeptId());
			flowRunPrcs.setLeadId(userInfo.getLeadId());
			flowRunPrcs.setPass("1");
			flowRunPrcs.setStatus("0");
			flowRunPrcs.setUserPrivId(account.getUserPriv());
			NewWorkFlowLogic logic = new NewWorkFlowLogic();
			path=logic.creatNewWork(conn,flowRunPrcs);
			//事务中心
			returnData="/system/workflow/dowork/"+path+"?runId="+runId;
			String readUrl="/system/workflow/dowork/"+tableName.toLowerCase()+"/print/index.jsp?runId="+runId;
			WorkList  workList = new WorkList();
			workList.setTitle(title);
			workList.setRunId(runId);
			workList.setModule("workflow");
			workList.setAccountId(account.getAccountId());
			workList.setUrl(returnData);
			workList.setReadUrl(readUrl);
			workList.setStatus("0");
			workList.setCreateTime(SysTool.getCurDateTimeStr());
			workList.setOrgId("1");
			workList.setPrcsId(1);
			WorkListLogic  workListLogic = new WorkListLogic();
			workListLogic.createDoRecordLogic(conn, workList);
			//初始化子流程数据
			logic.intrChildFormData(conn, flowId, tableName, runId,parentRunId);
			//处理父流程向子流程字段内容怎复制
			WorkFlowDataLogic workFlowData = new WorkFlowDataLogic();
			String coypToChild=flowProcess.getCopyToChild();
			String shareFlowDoc = flowProcess.getShareFlowDoc();
			if(!SysTool.isNullorEmpty(coypToChild))
			{
				copyToChildLogic(conn,parentTableName,parentRunId,tableName,runId,coypToChild);
			}
			//下一步的实例
			String copyToChildStr = flowProcess.getCopyToChild();
			if(!SysTool.isNullorEmpty(copyToChildStr))
			{
				//从子流程向父流程复制数据
			}
			//流程主表
			FlowRun flowRun = new FlowRun();
			flowRun.setFlowId(flowId);
			flowRun.setRunId(runId);
			flowRun.setTitle(title);
			flowRun.setDelFlag("0");
			flowRun.setOpUserStr(account.getAccountId());
			flowRun.setStatus("0");
			flowRun.setOrgId(account.getOrgId());
			flowRun.setModule("workflow");
			flowRun.setPath(readUrl);
			if(!SysTool.isNullorEmpty(shareFlowDoc))
			{
				if(shareFlowDoc=="1")
				{
					FlowRunLogic flowRunLogic = new FlowRunLogic();
					flowRun.setAttachId(flowRunLogic.getAtttchIdLogic(conn, parentRunId));
				}
			}
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRunLogic.createFlowRunLogic(conn, flowRun);
			return returnData;
		}	
		//父子流程父流程向子流程拷贝数据字段对应关系处理
		public void copyToChildLogic(Connection conn,String parentTableName,String parentRunId,String childTableName,String childRunId,String copyToChildStr) throws SQLException
		{
			ArrayList<String>  parentList = new ArrayList<String>();
			ArrayList<String> childList = new ArrayList<String>();
			String[] copyToChildOpt = copyToChildStr.split(",");
			for(int i=0;copyToChildOpt.length>i;i++)
				{
					String[] tmp = copyToChildOpt[i].split(":");
					if((!SysTool.isNullorEmpty(tmp[0]))&&(!SysTool.isNullorEmpty(tmp[1])))
							{
								parentList.add(tmp[0]);
								childList.add(tmp[1]);
							}
				}
			ArrayList<String> rsList = new ArrayList<String>();
			String queryParentStr = "SELECT ";
			for(int j=0;parentList.size()>j;j++)
			{
				queryParentStr+=parentList.get(j)+",";
			}
				queryParentStr=queryParentStr.substring(0,queryParentStr.length()-1);
				queryParentStr+=" FROM "+parentTableName+" WHERE RUN_ID=?";
				PreparedStatement ps = conn.prepareStatement(queryParentStr);
				ps.setString(1, parentRunId);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					for(int j=0;parentList.size()>j;j++)
					{
						rsList.add(rs.getString(parentList.get(j)));
					}
				}
				rs.close();
			String queryChilStr = "UPDATE "+childTableName+" SET ";
			for(int j =0;childList.size()>j;j++)
				{
					queryChilStr+=childList.get(j)+"=?,";
				}
					queryChilStr=queryChilStr.substring(0,queryChilStr.length()-1);
					queryChilStr+=" WHERE RUN_ID=?";
				ps = conn.prepareStatement(queryChilStr);
				int k=0;
				for(;rsList.size()>k;k++)
				{
					ps.setString(k+1, rsList.get(k));
				}
					ps.setString(k+1, childRunId);
					ps.executeUpdate();
					ps.close();
		}
	//父子流程数据拷贝处理
	//新建普通工作过程方法
		public String createWorkLogic(Connection conn,Account account,String title,String flowId) throws Exception
		{
			String path="";
			String runId=GuId.getGuid();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
			String tableName=workFlowFormLogic.getFormTableNameByFormIdLogic(conn, formId);
			//流程步骤
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			String prcsName=flowProcessLogic.getFlowProcessPrcsNameLogic(conn, flowId, 1);
			FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
			flowRunPrcs.setRunId(runId);
			flowRunPrcs.setFlowId(flowId);
			flowRunPrcs.setTableName(tableName);
			flowRunPrcs.setAccountId(account.getAccountId());
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			UserInfo userInfo=userInfoLogic.getUserInfoByAccount(conn, account);
			flowRunPrcs.setUserName(userInfo.getUserName());
			flowRunPrcs.setPrcsName(prcsName);
			flowRunPrcs.setPrcsId(1);
			flowRunPrcs.setRunPrcsId(1);
			flowRunPrcs.setDeptId(userInfo.getDeptId());
			flowRunPrcs.setLeadId(userInfo.getLeadId());
			flowRunPrcs.setPass("1");
			flowRunPrcs.setStatus("0");
			flowRunPrcs.setUserPrivId(account.getUserPriv());
			NewWorkFlowLogic logic = new NewWorkFlowLogic();
			path=logic.creatNewWork(conn,flowRunPrcs);
			//事务中心
			String url="/system/workflow/dowork/"+path+"?runId="+runId+"&runPrcsId=1";
			String readUrl="/system/workflow/dowork/"+tableName.toLowerCase()+"/print/index.jsp?runId="+runId;
			WorkList  workList = new WorkList();
			workList.setTitle(title);
			workList.setRunId(runId);
			workList.setModule("workflow");
			workList.setAccountId(account.getAccountId());
			workList.setUrl(url);
			workList.setReadUrl(readUrl);
			workList.setStatus("0");
			workList.setCreateTime(SysTool.getCurDateTimeStr());
			workList.setPrcsId(1);
			workList.setRunPrcsId(1);
			workList.setDelFlag("0");
			workList.setOrgId(account.getOrgId());
			WorkListLogic  workListLogic = new WorkListLogic();
			workListLogic.createDoRecordLogic(conn, workList);
			logic.intrFormData(conn, flowId, tableName, runId);
			//流程主表
			FlowRun flowRun = new FlowRun();
			flowRun.setFlowId(flowId);
			flowRun.setRunId(runId);
			flowRun.setTitle(title);
			flowRun.setDelFlag("0");
			flowRun.setOpUserStr(account.getAccountId());
			flowRun.setStatus("0");
			flowRun.setOrgId(account.getOrgId());
			flowRun.setModule("workflow");
			flowRun.setCreateUser(account.getAccountId());
			flowRun.setPath(readUrl);
			FlowRunLogic flowRunLogic = new FlowRunLogic();
			flowRunLogic.createFlowRunLogic(conn, flowRun);
			JSONObject json = new JSONObject();
			json.accumulate("title", title);
			json.accumulate("url", url);
			json.accumulate("runId",runId);
			return json.toString();
		}
		//更新流程的CHILDRUNID
		public void updateChildRunId(Connection conn,String tableName,String childRunId,String runId) throws SQLException
		{
			String childRunIdTmp = "";
			String queryStr="SELECT CHILD_RUN_ID FROM "+tableName+" WHERE RUN_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, runId);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				childRunIdTmp = rs.getString("CHILD_RUN_ID");
			}
			rs.close();
			childRunIdTmp=childRunId+","+childRunIdTmp;
			queryStr="UPDATE "+tableName+" SET CHILD_RUN_ID=? WHERE RUN_ID=?";
			ps=conn.prepareStatement(queryStr);
			ps.setString(1, childRunId);
			ps.setString(2, runId);
			ps.executeUpdate();
			ps.close();
		}
}
