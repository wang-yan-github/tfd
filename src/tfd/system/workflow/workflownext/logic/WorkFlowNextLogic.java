package tfd.system.workflow.workflownext.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.workflownext.tool.AutoUserTool;
public class WorkFlowNextLogic {
	//数据流程表单数据
	public void saveFlowFormDataLogic(Connection conn,Map<String,String> map,String[] writerField,String runId,String tableName) throws SQLException
	{
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
	}
	//获取下一步相关信息
	public String getNextPrcsInfoLogic(Connection conn,String flowId,String nextPrcs) throws Exception
	{
		String[] nextPrcsList=null;
		String prcsName="";
		JSONArray jsonArr = new JSONArray();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		if(nextPrcs.indexOf(",")>0){
			nextPrcsList=nextPrcs.split(",");
			for(int i=0;nextPrcsList.length>i;i++)
			{
				prcsName=flowProcessLogic.getFlowProcessPrcsNameLogic(conn, flowId, Integer.parseInt(nextPrcsList[i]));
				JSONObject json =new JSONObject();
				json.accumulate("prcsId",nextPrcsList[i]);
				json.accumulate("prcsName", prcsName);
				jsonArr.add(json);
			}
		}else
		{
			prcsName=flowProcessLogic.getFlowProcessPrcsNameLogic(conn, flowId, Integer.parseInt(nextPrcs));
			JSONObject json =new JSONObject();
			json.accumulate("prcsId",nextPrcs);
			json.accumulate("prcsName", prcsName);
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
	//生成下一步相关待办信息
	public String createNextPrcsLogic(Connection conn,String runId,FlowRunPrcs flowRunPrcs) throws SQLException
	{
		String query="INSERT FLOW_RUN_PRCS (RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,PASS)" +
				" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		ps.executeUpdate();
		ps.close();
		return flowRunPrcs.getTableName()+"/"+flowRunPrcs.getRunPrcsId()+"/index.jsp";
	}
	
	//智能选择处理
	public JSONArray getAutoUser(Connection conn,String flowId,String runId,String nextPrcsList ,Account account,int prcsId,int runPrcsId) throws NumberFormatException, Exception
	{
		FlowProcess flowProcess = new FlowProcess();
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		JSONArray jsonArr = new JSONArray();
		String autoUserJsonStr="";
		AutoUserTool autoUserTool = new AutoUserTool();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		String deptId=userInfoLogic.getDeptId(conn, account.getAccountId());
		if(!SysTool.isNullorEmpty(nextPrcsList))
		{
			if(nextPrcsList.indexOf(",")>0)
			{
				String[] nextPrcsArr = nextPrcsList.split(",");
				for(int i =0;nextPrcsArr.length>i;i++)
				{
					flowProcess=flowProcessLogic.getFlowProcessLogic(conn, flowId, Integer.parseInt(nextPrcsArr[i]));
					//智能处理选人
					JSONObject returnJson=new JSONObject();
					String autoUserModle=flowProcess.getAutoUserModle();
					returnJson.accumulate("prcsId", nextPrcsArr[i]);
					if(SysTool.isNullorEmpty(autoUserModle))
					{
						ArrayList<Map<String,String>> tmpUserList = autoUserTool.getsPrcsAutoUserList(conn, flowProcess, runId, prcsId,runPrcsId,account);
						if(tmpUserList.size()==1)
						{
							JSONObject jsontmp =new  JSONObject();
							jsontmp.accumulate("accountId", tmpUserList.get(0).get("accountId"));
							jsontmp.accumulate("userName", tmpUserList.get(0).get("userName"));
							returnJson.accumulate("autoUser",jsontmp);
						}else
						{
							JSONObject jsontmp =new  JSONObject();
							jsontmp.accumulate("accountId", "");
							jsontmp.accumulate("userName", "");
							returnJson.accumulate("autoUser",jsontmp);
						}
					}else
					{
					autoUserJsonStr=autoUserTool.getAutoUserJson(conn,flowProcess, autoUserModle, account, deptId, runId, Integer.parseInt(nextPrcsArr[i]),runPrcsId);
					returnJson.accumulate("autoUser", autoUserJsonStr);	
					}
					jsonArr.add(returnJson);
				}			
			}else{
				JSONObject returnJson=new JSONObject();
				String autoUserModle =autoUserTool.getAutoUserModleLogic(conn, flowId, Integer.parseInt(nextPrcsList));
				returnJson.accumulate("prcsId", nextPrcsList);
				autoUserJsonStr=autoUserTool.getAutoUserJson(conn, flowProcess,autoUserModle, account, deptId, runId, Integer.parseInt(nextPrcsList),runPrcsId);
				returnJson.accumulate("autoUser", autoUserJsonStr);
				jsonArr.add(returnJson);
			}
			
		}
		return jsonArr;
	}
	//转交下一步
	public void goNextLogic(Connection conn,FlowRunPrcs flowRunPrcs,int prcsRunId) throws SQLException
	{
		String queryStr="INSERT INTO FLOW_RUN_PRCS ("
						+ "RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,TABLE_NAME,"
						+ "PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,"
						+ "LEAD_ID,CREATE_TIME,STATUS,OP_FLAG,TRANSFER_USER,ORG_ID"
				+ ") " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowRunPrcs.getRunId());
		ps.setString(2, flowRunPrcs.getFlowId());
		ps.setInt(3, prcsRunId+1);
		ps.setInt(4, flowRunPrcs.getPrcsId());
		ps.setString(5, flowRunPrcs.getTableName());
		ps.setString(6, flowRunPrcs.getPrcsName());
		ps.setString(7, flowRunPrcs.getAccountId());
		ps.setString(8, flowRunPrcs.getUserName());
		ps.setString(9, flowRunPrcs.getDeptId());
		ps.setString(10, flowRunPrcs.getUserPrivId());
		ps.setString(11, flowRunPrcs.getLeadId());
		ps.setString(12,SysTool.getCurDateTimeStr());
		ps.setString(13, flowRunPrcs.getStatus());
		ps.setString(14, flowRunPrcs.getOpFlag());
		ps.setString(15, flowRunPrcs.getTransferUser());
		ps.setString(16, flowRunPrcs.getOrgId());
		ps.executeUpdate();
		ps.close();
	}
	
	//获取PRCS_RUN_ID
	public int getPrcsRunId(Connection conn,String runId) throws SQLException
	{
		int returnData=0;
		ResultSet rs = null;
		String queryStr="SELECT MAX(RUN_PRCS_ID) FROM FLOW_RUN_PRCS WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,runId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returnData = rs.getInt(1);
		}
		return returnData;
	}
	
	//保存表单子表数据
public void saveChildTableData(Connection conn,String runId,String jsonData) throws Exception
{
	PreparedStatement ps =null;
	JSONArray jsonArr1 = JSONArray.fromObject(jsonData);
	String queryStr="";
	for(int i=0;jsonArr1.size()>i;i++)
	{
		JSONObject json1= JSONObject.fromObject(jsonArr1.get(i));
		String tableName=json1.getString("table");
		String writerField=json1.getString("writerfield");
		String data=json1.getString("data");
		JSONArray jsonArr2 = JSONArray.fromObject(data);
		for(int j=0;jsonArr2.size()>j;j++)
		{
			JSONObject json2 = JSONObject.fromObject(jsonArr2.get(j));
			String tag = json2.getString("tag");
			String op =json2.getString("op");
			if(op.equals("1"))
			{
				//新加
				tag=GuId.getGuid();
				String[] writerArr=null;
				String sqlFieldStr="";
				String valueStr="";
				if(writerField.indexOf(",")>0)
				{
					writerArr = writerField.split(",");
				}else
				{
					writerArr = new String[1];
					writerArr[0]=writerField;
				}
				for(int s=0;writerArr.length>s;s++)
				{
					sqlFieldStr+=writerArr[s]+",";
					valueStr+="'"+json2.getString(writerArr[s])+"',";
				}
				queryStr="INSERT INTO "+tableName+"("+sqlFieldStr+"RUN_ID,TAG) VALUES("+valueStr+"'"+runId+"','"+tag+"')";
				ps=conn.prepareStatement(queryStr);
				ps.executeUpdate();
				ps.close();
			}else if(op.equals("2"))
			{
				queryStr="DELETE FROM " +tableName+" WHERE RUN_ID=? AND TAG=?";
				ps=conn.prepareStatement(queryStr);
				ps.setString(1, runId);
				ps.setString(2, tag);
				ps.executeUpdate();
				ps.close();
			}else
			{
				//已有数据执行更新操作
				String[] writerArr=null;
				String setStr="UPDATE "+tableName+" SET ";
				if(writerField.indexOf(",")>0)
				{
					writerArr = writerField.split(",");
					for(int y=0;writerArr.length>y;y++)
					{
						setStr+=" "+writerArr[y]+"='"+json2.getString(writerArr[y])+"',";
					}
					
				}else
				{
					writerArr = new String[1];
					writerArr[0]=writerField;
					setStr+=" "+writerArr[0]+"='"+json2.getString(writerArr[0])+"',";
				}
				setStr=setStr.substring(0,setStr.length()-1);
				setStr=setStr+" WHERE RUN_ID=? AND TAG=?";
				PreparedStatement ps1=conn.prepareStatement(setStr);
				ps1.setString(1,runId);
				ps1.setString(2, tag);
				ps1.executeUpdate();
				ps1.close();
				
			}
		}
	}
}
}
