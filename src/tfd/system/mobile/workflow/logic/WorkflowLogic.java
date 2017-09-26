package tfd.system.mobile.workflow.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.db.ConnConst;
import com.system.db.DbPoolConnection;
import com.system.global.SysProps;
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
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityFlowPriv;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.mobile.workflow.logic.WorkFlowDataLogic;
import tfd.system.workflow.newwork.logic.NewWorkFlowLogic;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;
import tfd.system.workflow.workflownext.tool.AutoUserTool;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

public class WorkflowLogic {
	//发起工作流
	public JSONObject myWorkflowLogic(Connection conn, Account account)throws Exception {
		JSONObject returnData = new JSONObject();
		
		
		try {
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			UserInfo userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			
			JSONObject data = new JSONObject();
			String dbType = DbPoolConnection.getInstance().getDbType();
			String sql = "";
			if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_MYSQL)) {
				sql = "SELECT DISTINCT F.FLOW_ID AS FLOW_ID,W.FLOW_NAME AS FLOW_NAME FROM"
						+ " FLOW_RUN F,WORK_FLOW W "
						+ " WHERE"
						+ " F.FLOW_ID=W.FLOW_TYPE_ID"
						+ " AND F.CREATE_USER = ?"
						+ " AND F.ORG_ID = ?"
						+ " ORDER BY F.BEGIN_TIME DESC LIMIT 0,5";
			} else if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_ORACLE)) {
				sql = "SELECT * FROM"
						+ "("
						+ "SELECT DISTINCT F.FLOW_ID AS FLOW_ID,W.FLOW_NAME AS FLOW_NAME FROM"
						+ " FLOW_RUN F,WORK_FLOW W " + " WHERE"
						+ " F.FLOW_ID=W.FLOW_TYPE_ID" + " AND F.CREATE_USER = ?"
						+ " AND F.ORG_ID = ?" + " ORDER BY F.BEGIN_TIME DESC"
						+ ") TMP WHERE ROWNUM<=5";
			} else if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_SQLSERVER)) {

			}
			
			JSONArray commonjsonArr = new JSONArray();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getAccountId());
			ps.setString(2, account.getOrgId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				JSONObject json = new JSONObject();
				json.accumulate("flowId", rs.getString("FLOW_ID"));
				json.accumulate("flowName", rs.getString("FLOW_NAME"));
				commonjsonArr.add(json);
			}
			rs.close();
			ps.close();
			data.accumulate("common", commonjsonArr);
			// 获取全部工作

			if (dbType.equals("mysql")) {
				sql = "SELECT W.FLOW_TYPE_ID AS FLOW_TYPE_ID,W.FLOW_NAME AS FLOW_NAME "
						+ " FROM WORK_FLOW W,FLOW_PROCESS F"
						+ " WHERE F.PRCS_ID=1 AND W.ORG_ID='"
						+ account.getOrgId()
						+ "' AND W.MODULE_ID='workflow' "
						+ " AND F.FLOW_ID=W.FLOW_TYPE_ID AND(CONCAT(',',F.USER_PRIV,',') LIKE '%,"
						+ userInfo.getAccountId()
						+ ",%' OR F.USER_PRIV='userAll' "
						+ "OR CONCAT(',',F.DEPT_PRIV,',') LIKE '%,"
						+ userInfo.getDeptId()
						+ ",%' OR F.DEPT_PRIV='deptAll' "
						+ "OR CONCAT(',',F.PRIV_ID,',') LIKE '%,"
						+ userInfo.getPostPriv() + ",%' OR F.PRIV_ID='privAll')";
			} else if (dbType.equals("oracle")) {
				sql = "SELECT W.FLOW_TYPE_ID AS FLOW_TYPE_ID,W.FLOW_NAME AS FLOW_NAME "
						+ " FROM WORK_FLOW W,FLOW_PROCESS F"
						+ " WHERE F.PRCS_ID=1 AND W.ORG_ID='"
						+ account.getOrgId()
						+ "' AND W.MODULE_ID='workflow' "
						+ " AND F.FLOW_ID=W.FLOW_TYPE_ID AND(CONCAT(CONCAT(',',F.USER_PRIV),',') LIKE '%,"
						+ userInfo.getAccountId()
						+ ",%' OR TO_CHAR(F.USER_PRIV)='userAll' "
						+ "OR CONCAT(CONCAT(',',F.DEPT_PRIV),',') LIKE '%,"
						+ userInfo.getDeptId()
						+ ",%' OR TO_CHAR(F.DEPT_PRIV)='deptAll' "
						+ "OR CONCAT(CONCAT(',',F.PRIV_ID),',') LIKE '%,"
						+ userInfo.getPostPriv()
						+ ",%' OR TO_CHAR(F.PRIV_ID)='privAll')";
			} else if (dbType.equals("sqlserver")) {

			}
			JSONArray alljsonArr = new JSONArray();
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				JSONObject json = new JSONObject();
				json.accumulate("flowId", rs1.getString("FLOW_TYPE_ID"));
				json.accumulate("flowName", rs1.getString("FLOW_NAME"));
				alljsonArr.add(json);
			}
			rs1.close();
			ps1.close();
			data.accumulate("all", alljsonArr);
			returnData.accumulate("status_code", "200");
			returnData.accumulate("msg", "请求成功");
			JSONObject jsondata = new JSONObject();
			
			jsondata.accumulate("time", System.currentTimeMillis());
			jsondata.accumulate("data", data);
			returnData.accumulate("data", jsondata);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return returnData;
	}
	
	//获取待办流程
	public JSONObject myWorkLogic(Connection conn,Account account) throws SQLException{
	JSONObject returnjson = new JSONObject();
	String queryStr="SELECT "
			+ "W.ID AS ID,"
			+ "W.TITLE AS TITLE,"
			+ "W.RUN_ID AS RUN_ID,"
			+ "W.RUN_PRCS_ID AS RUN_PRCS_ID,"
			+ "W.URL AS URL,"
			+ "W.CREATE_TIME AS CREATE_TIME,"
			+ "W.PRCS_ID AS PRCS_ID,"
			+ "F.TRANSFER_USER AS TRANSFER_USER,"
			+ " (SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID=F.TRANSFER_USER) AS TRANSFER_USER_NAME, "
			+ " (SELECT HEAD_IMG FROM USER_INFO U WHERE U.ACCOUNT_ID=F.TRANSFER_USER) AS HEAD_IMG, "
			+ " (SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID=(SELECT CREATE_USER FROM FLOW_RUN FR WHERE FR.RUN_ID= F.RUN_ID)) AS CREATE_USER "
			+ " FROM WORK_LIST W,FLOW_RUN_PRCS F  "
			+ " WHERE W.RUN_ID=F.RUN_ID "
			+ " AND W.PRCS_ID=F.PRCS_ID AND W.ACCOUNT_ID=F.ACCOUNT_ID AND W.ACCOUNT_ID=? AND F.STATUS=0"
			+ " AND W.MODULE=? AND W.ORG_ID=? AND W.STATUS=0 AND W.DEL_FLAG='0' ORDER BY CREATE_TIME DESC";
	JSONArray data= new JSONArray();
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, account.getAccountId());
	ps.setString(2, "workflow");
	ps.setString(3, account.getOrgId());
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		JSONObject json = new JSONObject();
		if(SysTool.isNullorEmpty(rs.getString("TITLE")))
		{
			json.accumulate("workTitle", "");	
		}else
		{
		json.accumulate("workTitle", rs.getString("TITLE"));
		}
		json.accumulate("runId", rs.getString("RUN_ID"));
		json.accumulate("runPrcsId", rs.getString("RUN_PRCS_ID"));
		json.accumulate("prcsId", rs.getString("PRCS_ID"));
		if(SysTool.isNullorEmpty(rs.getString("TRANSFER_USER")))
		{
			json.accumulate("deliverUserId", "");	
		}else
		{
			json.accumulate("deliverUserId", rs.getString("TRANSFER_USER"));
		}
		if(SysTool.isNullorEmpty(rs.getString("TRANSFER_USER_NAME")))
		{
			json.accumulate("deliverUserName", "");
		}else
		{
			json.accumulate("deliverUserName", rs.getString("TRANSFER_USER_NAME"));
		}
		if(SysTool.isNullorEmpty(rs.getString("HEAD_IMG")))
		{
			json.accumulate("deliverUserHeadImg","");
		}else
		{
			//json.accumulate("deliverUserHeadImg","");
			json.accumulate("deliverUserHeadImg", "/tfd/attachment/userinfo/"+rs.getString("HEAD_IMG"));
		}
		json.accumulate("beginUserName", rs.getString("CREATE_USER"));
		json.accumulate("arriveTime", rs.getString("CREATE_TIME"));
		data.add(json);
	}
	returnjson.accumulate("status_code", "200");
	returnjson.accumulate("msg", "请求成功");
	JSONObject jsondata = new JSONObject();
	jsondata.accumulate("time", System.currentTimeMillis());
	jsondata.accumulate("data", data);
	returnjson.accumulate("data", jsondata);
	return returnjson;
	}
	
//新建工作
	public String  createWorkLogic(Connection conn,String flowId,Account account,String title)throws SQLException{
		String path="";
		String runId="";
		try {
			runId = GuId.getGuid();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			path=logic.creatNewWork(conn,flowRunPrcs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return runId;
	}
	
	public JSONObject getFirstPrcsDataLogic(Connection conn,String runId,Account account) throws Exception
	{
		String flowId="";
		JSONObject returnjson = new JSONObject();
		JSONObject jsondata = new JSONObject();
		jsondata.accumulate("time", System.currentTimeMillis());
		JSONObject basic = new JSONObject();
		JSONObject other = new JSONObject();
		JSONArray option = new JSONArray();
		JSONArray formItem = new JSONArray();
		JSONObject opjson = new JSONObject();
		JSONObject data = new JSONObject();
		opjson.accumulate("type", "save");
		opjson.accumulate("text", "保存");
		option.add(opjson);
		opjson = new JSONObject();
		opjson.accumulate("type", "submitToNext");
		opjson.accumulate("text", "提交");
		option.add(opjson);		
		String queryStr="SELECT "
						+ " F.ID AS ID,"
						+ " F.RUN_ID AS RUN_ID,"
						+ " F.TITLE AS TITLE,"
						+ " F.FLOW_ID AS FLOW_ID,"
						+ " F.BEGIN_TIME AS BEGIN_TIME,"
						+ " (SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID=F.CREATE_USER) AS CREATE_USER_NAME "
						+ " FROM "
						+ " FLOW_RUN F "
						+ " WHERE F.MODULE='workflow'"
						+ " AND F.RUN_ID=?"
						+ " AND F.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			basic.accumulate("intRunId", rs.getString("ID"));
			basic.accumulate("workTitle", rs.getString("TITLE"));
			basic.accumulate("beginTime", rs.getString("BEGIN_TIME"));
			basic.accumulate("beginUserName", rs.getString("CREATE_USER_NAME"));
			other.accumulate("runId", rs.getString("RUN_ID"));
			other.accumulate("flowId",rs.getString("FLOW_ID"));
			other.accumulate("runPrcsId", "1");
			other.accumulate("prcsId", "1");
			flowId=rs.getString("FLOW_ID");
		}
		rs.close();
		ps.close();
		FlowRunPrcsLogic flowRunPrcsLogic= new FlowRunPrcsLogic();
		FlowRunPrcs flowRunPrcs=flowRunPrcsLogic.getFlowRunPrcs(conn, runId,1,1,account); 
		FlowProcess flowProcess = new FlowProcess();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		flowProcess = flowProcessLogic.getFlowProcessLogic(conn, flowId, 1);
		UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
		boolean flag =uitilityFlowPriv.getEditFlowPriv(account.getAccountId(), flowRunPrcs);
	if(flag)
	{
		WorkFlowDataLogic workFlowDataLogic = new WorkFlowDataLogic();
		formItem=workFlowDataLogic.getWorkFlowDataLogic(flowRunPrcs,flowProcess,account,conn);
	}
		data.accumulate("basic", basic);
		data.accumulate("formItem", formItem);
		data.accumulate("option", option);
		data.accumulate("other", other);
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "请求成功");
		jsondata.accumulate("data", data);
		returnjson.accumulate("data", jsondata);
		return returnjson;
		
	}
	
	/**
	 * 进入待办界面
	 * @param conn
	 * @param account
	 * @param runId
	 * @param runPrcsId
	 * @param prcsId
	 * @return
	 * @throws SQLException
	 */
	public JSONObject openMyWorkLogic(Connection conn,Account account,String runId,int runPrcsId,int prcsId) throws Exception
	{
		String flowId="";
		JSONObject returnjson = new JSONObject();
		JSONObject jsondata = new JSONObject();
		JSONObject basic = new JSONObject();
		JSONObject other = new JSONObject();
		JSONArray option = new JSONArray();
		JSONArray formItem = new JSONArray();
		JSONObject opjson = new JSONObject();
		JSONObject idea = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray approveProcess = new JSONArray();
		opjson.accumulate("type", "save");
		opjson.accumulate("text", "保存");
		option.add(opjson);
		opjson = new JSONObject();
		opjson.accumulate("type", "submitToNext");
		opjson.accumulate("text", "提交");
		option.add(opjson);		
		String queryStr="SELECT "
						+ " F.ID AS ID,"
						+ " F.RUN_ID AS RUN_ID,"
						+ " F.TITLE AS TITLE,"
						+ " F.FLOW_ID AS FLOW_ID,"
						+ " F.BEGIN_TIME AS BEGIN_TIME,"
						+ " (SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID=F.CREATE_USER) AS CREATE_USER_NAME "
						+ " FROM "
						+ " FLOW_RUN F "
						+ " WHERE F.MODULE='workflow'"
						+ " AND F.RUN_ID=?"
						+ " AND F.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			basic.accumulate("intRunId", rs.getString("ID"));
			basic.accumulate("workTitle", rs.getString("TITLE"));
			basic.accumulate("beginTime", rs.getString("BEGIN_TIME"));
			basic.accumulate("beginUserName", rs.getString("CREATE_USER_NAME"));
			other.accumulate("runId", rs.getString("RUN_ID"));
			other.accumulate("flowId",rs.getString("FLOW_ID"));
			other.accumulate("runPrcsId", "1");
			other.accumulate("prcsId", "1");
			flowId=rs.getString("FLOW_ID");
		}
		rs.close();
		ps.close();
		FlowRunPrcsLogic flowRunPrcsLogic= new FlowRunPrcsLogic();
		FlowRunPrcs flowRunPrcs=flowRunPrcsLogic.getFlowRunPrcs(conn, runId,prcsId,runPrcsId,account); 
		String ideaText=flowRunPrcs.getIdeaText();
		ideaText=ideaText==null?"":ideaText;
		idea.accumulate("idea", flowRunPrcs.getIdea());
		idea.accumulate("ideaText",ideaText );
		FlowProcess flowProcess = new FlowProcess();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		flowProcess = flowProcessLogic.getFlowProcessLogic(conn, flowId, prcsId);
		UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
		boolean flag =uitilityFlowPriv.getEditFlowPriv(account.getAccountId(), flowRunPrcs);
		if(flag)
		{
			WorkFlowDataLogic workFlowDataLogic = new WorkFlowDataLogic();
			formItem=workFlowDataLogic.getWorkFlowDataLogic(flowRunPrcs,flowProcess,account,conn);
		}
	
		ResultSet rs1 = null;
		String queryStr1="SELECT "
				+ "F.RUN_PRCS_ID AS RUN_PRCS_ID,"
				+ "F.PRCS_ID AS PRCS_ID,"
				+ "F.PRCS_NAME AS PRCS_NAME,"
				+ "F.CREATE_TIME AS CREATE_TIME,"
				+ "F.END_TIME AS END_TIME,"
				+ "F.ACCOUNT_ID AS ACCOUNT_ID,"
				+ "F.USER_NAME AS USER_NAME,"
				+ "F.IDEA AS IDEA,"
				+ "F.OP_FLAG AS OP_FLAG,"
				+ "F.IDEA_TEXT AS IDEA_TEXT,"
				+ "U.HEAD_IMG AS HEAD_IMG "
				+ "FROM FLOW_RUN_PRCS F,USER_INFO U WHERE F.ACCOUNT_ID=U.ACCOUNT_ID AND RUN_ID=?";
		PreparedStatement ps1 = conn.prepareStatement(queryStr1);
		ps1.setString(1, runId);
		rs1 = ps1.executeQuery();
		while(rs1.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("runPrcsId", rs1.getString("RUN_PRCS_ID"));
			json.accumulate("prcsName", rs1.getString("PRCS_NAME"));
			json.accumulate("opUser", rs1.getString("USER_NAME"));
			json.accumulate("opUserId", rs1.getString("ACCOUNT_ID"));
			json.accumulate("opUserHeadImg",rs1.getString("HEAD_IMG"));
			json.accumulate("arriveTime", rs1.getString("CREATE_TIME"));
			if(SysTool.isNullorEmpty(rs1.getString("END_TIME")))
			{
				json.accumulate("endTime", "");
	
			}else
			{
				json.accumulate("endTime", rs1.getString("END_TIME"));
			}
			
			if(SysTool.isNullorEmpty(rs1.getString("IDEA")))
			{
				json.accumulate("idea", "0");
			}else
			{
				json.accumulate("idea", rs1.getString("IDEA"));
			}
			json.accumulate("ideaText", rs1.getString("IDEA_TEXT")==null?"":rs1.getString("IDEA_TEXT"));
			json.accumulate("opType", rs1.getString("OP_FLAG"));
			approveProcess.add(json);
		}
		rs1.close();
		ps1.close();
		
		data.accumulate("basic", basic);
		data.accumulate("formItem", formItem);
		data.accumulate("idea", idea);
		data.accumulate("approveProcess", approveProcess);
		data.accumulate("option", option);
		data.accumulate("other", other);
		
		jsondata.accumulate("time", System.currentTimeMillis());
		jsondata.accumulate("data", data);
		
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "请求成功");
		returnjson.accumulate("data", jsondata);
		return returnjson;
	}
	
//保作表单数据
	
	public JSONObject saveFlowFormDataLogic(Connection conn,Map<String,String> map,String[] writerField,String runId,String tableName) throws SQLException
	{
		JSONObject returnjson = new JSONObject();
		if(writerField!=null){
		Map<String,String> fieldTypeMap = new HashMap<String,String>();
		PreparedStatement ps =null;
		String queryStr="SELECT * FROM "+tableName;
		ps=conn.prepareStatement(queryStr);
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData md=rs.getMetaData(); 
		if(rs.next()){
			for(int i = 1 ; i<= md.getColumnCount() ; i++){ 
				String columnName = md.getColumnName(i); 
				String columnTypeName=md.getColumnTypeName(i); 
				fieldTypeMap.put(columnName, columnTypeName);
			}
		}
		
		String query="UPDATE "+tableName+" SET ";
		List<String> valueList = new ArrayList<String>();
		List<String> fieldList = new ArrayList<String>();
		for(int i=0;writerField.length>i;i++)
		{
			if(map.containsKey(writerField[i])){
				query=query+writerField[i]+"=?,";
			fieldList.add(writerField[i]);
			valueList.add(map.get(writerField[i]));
			}
		}
		query=query.substring(0, query.length()-1);
		query=query+" WHERE RUN_ID='"+runId+"'";
		ps=conn.prepareStatement(query);
		for(int j=0;valueList.size()>j;j++)
		{
			String fieldType=fieldTypeMap.get(fieldList.get(j).toUpperCase());
			 if(fieldType.equals("INT")||fieldType.equals("NUMBER"))
			{
				if(valueList.get(j).equals(""))
				{
					ps.setInt(j+1,0);					
				}else
				{
					ps.setInt(j+1,Integer.parseInt(valueList.get(j)));
				}
			}else{
				ps.setString(j+1,valueList.get(j));
			}
		}
		ps.executeUpdate();
		rs.close();
		ps.close();
		}
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "请求成功");
		JSONObject jsondata = new JSONObject();
		jsondata.accumulate("time", System.currentTimeMillis());
		JSONObject data = new JSONObject();
		data.accumulate("msg", "保存成功");
		jsondata.accumulate("data", data);
		returnjson.accumulate("data", jsondata);
		return returnjson;
	}
	
	
	public JSONObject getTestData(String method) throws Exception{
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(SysProps.getWebPath()+"/api/app/workflow/data/"+method+".json")), "utf-8"));
			
			StringBuffer data=new StringBuffer();
			String str=null;
			while((str=reader.readLine())!=null){
				data.append(str);
			}
			if (data.length()>0) {
				return JSONObject.fromObject(data.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			if (reader!=null) {
				reader.close();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(new Date().getTime()/1000);
	}

}
