package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
public class UitilityFlowPriv {
//判断工作流办理权限
	public boolean getEditFlowPriv(String accountId,FlowRunPrcs flowRunPrcs)
	{
		boolean flag=true;
		if(!accountId.equals(flowRunPrcs.getAccountId()))
		{
			flag=false;
		}
		return flag;
	}
//判断工作流查看时的权限
	public boolean getFlowPrintPriv(String accountId)
	{
		boolean flag=true;
		
		
		return flag;
	}
	
	
	//判断流程是否强制合并，若强制合并都等所有前一步待办是否全部办完
	public boolean getForceAggregationPriv(Connection conn,String runId,int prcsId) throws SQLException
	{
		boolean flag=true;
		String queryStr="SELECT COUNT(*) AS ZS FROM FLOW_RUN_PRCS WHERE STATUS='0' AND RUN_ID=? AND PRCS_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.setInt(2, prcsId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			if(rs.getInt("ZS")>1)
			{
				flag=false;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	//根据流程的相关权限获取查询范围内的流程
	public void getWorkFlowQueryRangeLogic(Connection conn,Account account)
	{
//		ArrayList<String> flowList = new ArrayList<String>();
//		String allQueryUser=workFlow.getAllQueryUser();
//		String deptQueryUser= workFlow.getDeptQueryUser();
//		String otherDeptQueryUser= workFlow.getOtherDeptManageUser();
//		String leaveManageUser = workFlow.getLeaveQueryUser();
//		String allManageUser = workFlow.getAllManageUser();
//		String deptManageUser = workFlow.getDeptManageUser();
//		String otherDeptManageUser = workFlow.getOtherDeptManageUser();
//		if(!SysTool.isNullorEmpty(allQueryUser))
//		{
//			
//		}
//		String queryStr="SELECT * FROM WORK_FLOW WHERE ALL_QUERY_USER LIKE";
	}
	
	//判断人员是存在
	public boolean userIsExist(String str1,String str2)
	{
		boolean flag=false;
		str1=","+str1+",";
		str2=","+str2+",";
		if(str1.indexOf(str2)>=0)
		{
			flag=true;
		}
		return flag;
	}
	}
