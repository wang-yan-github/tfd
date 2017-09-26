package tfd.system.workflow.workflownext.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;

import com.system.tool.SysTool;

import net.sf.json.JSONObject;

public class AutoUserTool {
//获取人员智能选择规则
	public String getAutoUserModleLogic(Connection conn,String flowId,int prcsId) throws SQLException
	{
		String returnData="";
		ResultSet rs=null;
		String queryStr="SELECT AUTO_USER_MODLE FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,flowId);
		ps.setInt(2, prcsId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			if(!SysTool.isNullorEmpty(rs.getString("AUTO_USER_MODLE")))
			{
			returnData=rs.getString("AUTO_USER_MODLE");
			}
		}
		return returnData;
	}
//处理经办权限的集合人员
	public ArrayList<Map<String,String>> getAllpassUser(Connection conn,FlowProcess flowProcess) throws SQLException
	{
		ArrayList<Map<String,String>> userAllList = new ArrayList<Map<String,String>>();
		Map<String,String> userMap = new HashMap<String,String>();
		String userPriv = flowProcess.getUserPriv();
		String deptPriv=flowProcess.getDeptPriv();
		String privId = flowProcess.getPrivId();
		String userPrivStr="";
		String deptPrivStr="";
		String privIdStr="";
		if(userPriv.indexOf(",")>0)
		{
			String[] userPrivAll=userPriv.split(",");
			for(int i=0;userPrivAll.length>i;i++)
			{
				userPrivStr+="'"+userPrivAll[i]+"',";
			}
			userPrivStr=userPrivStr.substring(0, userPrivStr.length()-1);
		}else if(userPriv!=""&&userPriv!=null)
		{
			userPrivStr="'"+userPriv+"'";
		}else
		{
			userPrivStr="''";
		}
		
		if(deptPriv.indexOf(",")>0)
		{
			String[] deptPrivAll=deptPriv.split(",");
			for(int i=0;deptPrivAll.length>i;i++)
			{
				deptPrivStr+="'"+deptPrivAll[i]+"',";
			}
			deptPrivStr=deptPrivStr.substring(0, deptPrivStr.length()-1);
		}else if(deptPriv!=""&&deptPriv!=null)
		{
			deptPrivStr="'"+deptPriv+"'";
		}else
		{
			deptPrivStr="''";
		}
		if(privId.indexOf(",")>0)
		{
			String[] privIdAll=privId.split(",");
			for(int i=0;privIdAll.length>i;i++)
			{
				privIdStr+="'"+privIdAll[i]+"',";
			}
			privIdStr=privIdStr.substring(0, privIdStr.length()-1);
		}else if(privId!=""&&privId!=null)
		{
			privIdStr="'"+privId+"'";
		}else
		{
			privIdStr="''";
		}
		String queryStr="SELECT ACCOUNT_ID,USER_NAME FROM USER_INFO WHERE ACCOUNT_ID IN("+userPrivStr+") OR DEPT_ID IN("+deptPrivStr+") OR POST_PRIV IN("+privIdStr+")";
		PreparedStatement ps =  conn.prepareStatement(queryStr);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			userMap.put("accountId", rs.getString("ACCOUNT_ID"));
			userMap.put("userName", rs.getString("USER_NAME"));
			userAllList.add(userMap);
		}
		rs.close();
		ps.close();
		return userAllList;
	}
//人员过滤处理
	public ArrayList<Map<String,String>> getsPrcsAutoUserList(Connection conn,FlowProcess flowProcess,String runId,int prcsId,int runPrcsId,Account account) throws SQLException
	{
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
		flowRunPrcs = flowRunPrcsLogic.getFlowRunPrcs(conn, runId, prcsId,runPrcsId,account);
		ArrayList<Map<String,String>> returnMapList = new ArrayList<Map<String,String>>();
		if(flowProcess.getsPrcsAuto()==null||flowProcess.getsPrcsAuto().equals("0"))
		{
			ArrayList<Map<String,String>> userAllList = this.getAllpassUser(conn, flowProcess);
			returnMapList=userAllList;
		}else	if(flowProcess.getsPrcsAuto().equals("1"))
		{
			//选人范围在本部门查找领导
			String beferDeptId = flowRunPrcs.getDeptId();
			ArrayList<Map<String,String>> userAllList = this.getAllpassUser(conn, flowProcess);
			ArrayList<Map<String,String>> tmpUserList = new ArrayList<Map<String,String>>();
			Map<String,String> tmpMap = new HashMap<String,String>();
			String queryStr="SELECT ACCOUNT_ID,USER_NAME,HEAD_IMG FROM USER_INFO WHERE DEPT_ID=? AND LEAD_LEAVE =(SELECT MAX(LEAD_LEAVE) FROM USER_INFO WHERE DEPT_ID=?)";
			PreparedStatement ps =conn.prepareStatement(queryStr);
			ps.setString(1, beferDeptId);
			ps.setString(2, beferDeptId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				tmpMap.put("accountId", rs.getString("ACCOUNT_ID"));
				tmpMap.put("userName", rs.getString("USER_NAME"));
				tmpMap.put("headImg",rs.getString("HEAD_IMG"));
				tmpUserList.add(tmpMap);
			}
			for(int i=0;tmpUserList.size()>i;i++)
			{
				if(userAllList.contains(tmpUserList.get(i)))
				{
					returnMapList.add(tmpUserList.get(i));
				}
			}
		}else if(flowProcess.getsPrcsAuto()=="2")
		{
			//选人范围在上级部门经办人
		}
		return returnMapList;
	}
//根据相关的选择进行自能选择
public String getAutoUserJson(Connection conn,FlowProcess flowProcess,String autoUserModle,Account account,String deptId,String runId,int prcsId,int runPrcsId) throws Exception
	{
		JSONObject returnJosn= new JSONObject();
		if(!SysTool.isNullorEmpty(autoUserModle))
		{
		JSONObject json = JSONObject.fromObject(autoUserModle);
		String autoUserType=json.getString("autoUserType");
		String autoUserRule=json.getString("autoUserRule");
		String autoFormField=json.getString("autoFormField");
		String autoDeptId=json.getString("autoDeptId");
		String defUserId=json.getString("defUserId");
		if(autoUserRule.equals("0")||autoUserRule.equals(""))
		{
			AutoUserTool autoUserTool = new AutoUserTool();
			ArrayList<Map<String,String>> tmpUserList = autoUserTool.getsPrcsAutoUserList(conn, flowProcess, runId, prcsId,runPrcsId,account);
			if(tmpUserList.size()==1)
			{
				returnJosn.accumulate("accountId", tmpUserList.get(0).get("accountId"));
				returnJosn.accumulate("userName", tmpUserList.get(0).get("userName"));
				UserInfoLogic userInfoLogic = new UserInfoLogic();
				String headImg=userInfoLogic.getUserInfoByAccountId(conn, tmpUserList.get(0).get("accountId")).getHeadImg();
				returnJosn.accumulate("headImg", headImg);
			}
		}else if(autoUserRule.equals("1"))
		{
			//本部门经理
			returnJosn = this.getAutoUserByUserManager(conn, account);
		}else if(autoUserRule.equals("2"))
			{
			//上级部门领导
				returnJosn=this.getAutoUserByUserManagerLead(conn, account);
			}else if(autoUserRule.equals("3"))
			{
				//指定部门领导
				returnJosn = this.getAutoUserByDeptId(conn, autoDeptId);
				
			}else if(autoUserRule.equals("4"))
			{
				//上级分管领导
				
			}else if(autoUserRule.equals("5"))
			{
				//表单字段
				FlowRunPrcsLogic frpl= new FlowRunPrcsLogic();
				FlowRunPrcs flowRunPrcs=frpl.getFlowRunPrcs(conn, runId, prcsId,runPrcsId,account);
				String tableName=flowRunPrcs.getTableName();
				returnJosn=this.getAutoUserByFormField(conn, tableName, runId, autoFormField);
				
			}else if(autoUserRule.equals("6"))
			{
				//表单字段ID
				FlowRunPrcsLogic frpl= new FlowRunPrcsLogic();
				FlowRunPrcs flowRunPrcs=frpl.getFlowRunPrcs(conn, runId, prcsId,runPrcsId,account);
				String tableName=flowRunPrcs.getTableName();
				returnJosn=this.getAutoUserByFormFieldId(conn, tableName, runId, autoFormField);

			}else if(autoUserRule.equals("7"))
			{
				//默认人员
				returnJosn.accumulate("accountId",defUserId);
				UserInfoLogic userInfoLogic = new UserInfoLogic();
				String userName = userInfoLogic.getUserNameByAccountIdLogic(conn, defUserId);
				String headImg=userInfoLogic.getUserInfoByAccountId(conn, defUserId).getHeadImg();
				returnJosn.accumulate("headImg", headImg);
				returnJosn.accumulate("userName",userName);
			}else if(autoUserRule.equals("8"))
			{
				//指定步骤经办人
				int autoPrcsId=Integer.parseInt(autoUserType);
					if(SysTool.isNullorEmpty(autoUserType)||autoUserType==""||autoUserType=="0")
					{
						autoPrcsId=prcsId;
					}
				returnJosn=this.getAutoUserBySetPrcsIdUser(conn, runId,autoPrcsId,runPrcsId,account);
				
			}else if(autoUserRule.equals("9"))
			{
				//上级部门领导
			}else if(autoUserRule.equals("10"))
			{
				//上级部门领导
			}else if(autoUserRule.equals("11"))
			{
				//上级部门领导
			}
		}
		return returnJosn.toString();
	}
	
	//获取部门经理
	public JSONObject getDeptManage(Connection conn,String deptId) throws SQLException
	{
		JSONObject json = new JSONObject();
		if(SysTool.isNullorEmpty(deptId))
		{
			return json;
		}
		ResultSet rs =null;
		String queryStr="";
		
		queryStr="SELECT USER_NAME,ACCOUNT_ID FROM USER_INFO WHERE DEPT_ID='"+deptId+"' AND POST_PRIV=(SELECT MIN(POST_PRIV) FROM USER_INFO " +
				" WHERE DEPT_ID='"+deptId+"') AND SORT_ID=(SELECT MIN(SORT_ID) FROM USER_INFO WHERE DEPT_ID='"+deptId+"' AND POST_PRIV=(SELECT MIN(POST_PRIV) FROM " +
				"USER_INFO WHERE DEPT_ID='"+deptId+"'))";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName",rs.getString("USER_NAME"));
		}
		rs.close();
		ps.close();
		return json;
	}
	//获取上级领导
	public JSONObject getLeader(Connection conn,String accountId,String deptId) throws SQLException
	{
		JSONObject json = new JSONObject();
		ResultSet rs = null;
		String queryStr="SELECT ACCOUNT_ID,USER_NAME FROM USER_INFO WHERE ACCOUNT_ID=(SELECT LEAD_ID FROM USER_INFO WHERE ACCOUNT_ID='"+accountId+"' AND " +
				" DEPT_ID='"+deptId+"')";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName",rs.getString("USER_NAME"));	
		}
		rs.close();
		ps.close();
		return json;
	}
	// 按表单字段选人
	public JSONObject getAutoUserByFormField(Connection conn,String tableName,String runId,String fieldName) throws Exception
	{
		JSONObject json = new JSONObject();
		String accountId="";
		String queryStr="SELECT ? FROM ? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,fieldName);
		ps.setString(2,tableName);
		ps.setString(3,runId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			String userName=rs.getString(fieldName);
			UserInfoLogic logic = new UserInfoLogic();
			accountId=logic.getAccountIdByUserName(conn, userName);
			UserInfo userInfo = new UserInfo();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccountId(conn, accountId);
			json.accumulate("accountId", accountId);
			json.accumulate("userName", userName);
			json.accumulate("headImg", userInfo.getHeadImg());
		}
		rs.close();
		ps.close();
		return json;
	}	
	//按表单字段ID选人
	public JSONObject getAutoUserByFormFieldId(Connection conn,String tableName,String runId,String flileNameId) throws Exception
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT ? FROM ? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,flileNameId);
		ps.setString(2,tableName);
		ps.setString(3,runId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			String accountId=rs.getString(flileNameId);
			UserInfoLogic logic = new UserInfoLogic();
			UserInfo userInfo = new UserInfo();
			userInfo=logic.getUserInfoByAccountId(conn, accountId);
			String userName = userInfo.getUserName();
			String headImg = userInfo.getHeadImg();
			json.accumulate("accountId", accountId);
			json.accumulate("userName", userName);
			json.accumulate("headImg", headImg);
		}
		rs.close();
		ps.close();
		return json;
	}
	//根据指定部门领导选择人
	public JSONObject getAutoUserByDeptId(Connection conn,String deptId) throws Exception
	{
		JSONObject json = new JSONObject();
		DeptLogic deptLogic = new DeptLogic();
		Department dept = new Department();
		dept=deptLogic.getDepartmentLogic(conn, deptId);
		String deptLead = dept.getDeptLead();
		UserInfoLogic logic = new UserInfoLogic();
		UserInfo userInfo = new UserInfo();
		userInfo = logic.getUserInfoByAccountId(conn, deptLead);
		String userName=userInfo.getUserName();
		String headImg=userInfo.getHeadImg();
		json.accumulate("headImg", headImg);
		json.accumulate("accountId", deptLead);
		json.accumulate("userName", userName);
	return json;
	}
	//获取本部门经理
	public JSONObject getAutoUserByUserManager(Connection conn,Account account) throws Exception
	{
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String deptId = userInfo.getDeptId();
		JSONObject json = new JSONObject();
		DeptLogic deptLogic = new DeptLogic();
		Department dept = new Department();
		dept=deptLogic.getDepartmentLogic(conn, deptId);
		String deptLead = dept.getDeptLead();
		UserInfoLogic logic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccountId(conn, deptLead);
		String userName=userInfo.getUserName();
		String headImg=userInfo.getHeadImg();
		json.accumulate("headImg", headImg);
		json.accumulate("accountId", deptLead);
		json.accumulate("userName", userName);
		return json;
	}
	//获取上级部门领导
	public JSONObject getAutoUserByUserManagerLead(Connection conn,Account account) throws Exception
	{
		String accountId = account.getAccountId();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String deptId = userInfo.getDeptId();		
		JSONObject json = new JSONObject();
		DeptLogic deptLogic = new DeptLogic();
		Department dept = new Department();
		dept=deptLogic.getDepartmentLogic(conn, deptId);
		String orgLeaveId = dept.getOrgLeaveId();
		dept=deptLogic.getDepartmentLogic(conn, orgLeaveId);
		String deptLead = dept.getDeptLead();
		UserInfoLogic logic = new UserInfoLogic();
		String userName=logic.getUserNameByAccountIdLogic(conn, deptLead);
		String headImg=userInfoLogic.getUserInfoByAccountId(conn, deptLead).getHeadImg();
		json.accumulate("headImg", headImg);
		json.accumulate("accountId", deptLead);
		json.accumulate("userName", userName);
		return json;
	}
	//指定步骤经办人
	public JSONObject getAutoUserBySetPrcsIdUser(Connection conn,String runId,int prcsId,int runPrcsId,Account account) throws Exception
	{
		FlowRunPrcsLogic frpl= new FlowRunPrcsLogic();
		FlowRunPrcs flowRunPrcs=frpl.getFlowRunPrcs(conn, runId, prcsId,runPrcsId,account);
		String accountId = flowRunPrcs.getAccountId();
		UserInfoLogic logic = new UserInfoLogic();
		UserInfo userInfo = new UserInfo();
		userInfo=logic.getUserInfoByAccountId(conn, accountId);
		String userName=userInfo.getUserName();
		String headImg = userInfo.getHeadImg();
		JSONObject json = new JSONObject();
		json.accumulate("accountId", accountId);
		json.accumulate("userName", userName);
		json.accumulate("headImg", headImg);
		return json;
	}
}
