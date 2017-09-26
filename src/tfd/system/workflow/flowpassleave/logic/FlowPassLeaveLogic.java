package tfd.system.workflow.flowpassleave.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;
import com.system.tool.SysTool;

import tfd.system.workflow.flowpassleave.data.FlowPassLeave;

public class FlowPassLeaveLogic {
	//添加
	public int addFlowPassLeaveLogic(Connection conn,FlowPassLeave flowPassLeave) throws SQLException
	{
		int i=0;
		String queryStr="INSERT INTO FLOW_PASS_LEAVE(FLOW_PASS_LEAVE_ID,PASS_LEAVE_ID,PASS_LEAVE_NAME,PASS_LEAVE_NEXT,FLOW_ID,ORG_ID)VALUE(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowPassLeave.getFlowPassLeaveId());
		ps.setString(2, flowPassLeave.getPassLeaveId());
		ps.setString(3, flowPassLeave.getPassLeaveName());
		ps.setString(4, flowPassLeave.getPassLeaveNext());
		ps.setString(5, flowPassLeave.getFlowId());
		ps.setString(6, flowPassLeave.getOrgId());
		i=ps.executeUpdate();
		ps.close();
		return i;
	}
	public int updateFlowPassLeaveLogic(Connection conn,FlowPassLeave flowPassLeave) throws SQLException
	{
		int i=0;
		String queryStr="UPDATE FLOW_PASS_LEAVE SET PASS_LEAVE_ID=?,PASS_LEAVE_NAME=?,PASS_LEAVE_NEXT=? WHERE FLOW_PASS_LEAVE_ID=? AND FLOW_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowPassLeave.getPassLeaveId());
		ps.setString(2, flowPassLeave.getPassLeaveName());
		ps.setString(3, flowPassLeave.getPassLeaveNext());
		ps.setString(4, flowPassLeave.getFlowPassLeaveId());
		ps.setString(5, flowPassLeave.getFlowId());
		ps.setString(6, flowPassLeave.getOrgId());
		i = ps.executeUpdate();
		return i;
	}
	
	
	//获取行政审批下一结点
	public FlowPassLeave getNextPassLeaveLogic(Connection conn,String flowPassLeaveId) throws SQLException
	{
		FlowPassLeave flowPassLeave = new FlowPassLeave();
		String queryStr = "SELECT ID,FLOW_PASS_LEAVE_ID,PASS_LEAVE_ID,PASS_LEAVE_NAME,PASS_LEAVE_NEXT,FLOW_ID,ORG_ID FROM FLOW_PASS_LEAVE WHERE FLOW_PASS_LEAVE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowPassLeaveId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			flowPassLeave.setId(rs.getInt("ID"));
			flowPassLeave.setFlowPassLeaveId("FLOW_PASS_LEAVE_ID");
			flowPassLeave.setPassLeaveId(rs.getString("PASS_LEAVE_ID"));
			flowPassLeave.setPassLeaveName(rs.getString("PASS_LEAVE_NAME"));
			flowPassLeave.setPassLeaveNext(rs.getString("PASS_LEAVE_NEXT"));
			flowPassLeave.setFlowId(rs.getString("FLOW_ID"));
			flowPassLeave.setOrgId(rs.getString("ORG_ID"));
		}
		return flowPassLeave;
	}

	//按步骤取得一下行政审批
	public String getNextPrcsByPassLeave(Connection conn,String flowId,String flowPassLeaveId) throws SQLException
	{
		String returnData="";
		String queryStr = "SELECT PRCS_ID FROM FLOW_PROCESS WHERE FLOW_ID=? AND LEAD_PRCS_LEAVE IN(SELECT PASS_LEAVE_NEXT FROM FLOW_PASS_LEAVE WHERE FLOW_PASS_LEAVE_ID=?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ps.setString(2, flowPassLeaveId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			returnData+=rs.getString("PRCS_ID")+",";
		}
		rs.close();
		ps.close();
		if(!SysTool.isNullorEmpty(returnData))
		{
			returnData=returnData.substring(0,returnData.length()-1);
		}
		return returnData;
	}
	//
	public String getFlowPassLeaveLogic(Connection conn,String flowPassLeaveId) throws SQLException
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT PASS_LEAVE_ID,FLOW_PASS_LEAVE_ID,PASS_LEAVE_NAME,PASS_LEAVE_NEXT,A.FLOW_ID AS FLOW_ID,B.FLOW_NAME AS FLOW_NAME FROM FLOW_PASS_LEAVE A,WORK_FLOW B WHERE A.FLOW_PASS_LEAVE_ID=? AND"
				+ " A.FLOW_ID=B.FLOW_TYPE_ID";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowPassLeaveId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			json.accumulate("flowPassLeaveId",rs.getString("FLOW_PASS_LEAVE_ID"));
			json.accumulate("passLeaveId", rs.getString("PASS_LEAVE_ID"));
			json.accumulate("passLeaveName", rs.getString("PASS_LEAVE_NAME"));
			json.accumulate("passLeaveNext", rs.getString("PASS_LEAVE_NEXT"));
			json.accumulate("flowId", rs.getString("FLOW_ID"));
			json.accumulate("flowName", rs.getString("FLOW_NAME"));
		}
		return json.toString();
	}
	//获取行政级别列表
	public String getFlowPassLeaveListLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		String queryStr="SELECT A.ID AS ID,FLOW_PASS_LEAVE_ID AS FLOW_PASS_LEAVE_ID,PASS_LEAVE_ID,PASS_LEAVE_NAME,PASS_LEAVE_NEXT,A.FLOW_ID AS FLOW_ID,B.FLOW_NAME AS FLOW_NAME FROM FLOW_PASS_LEAVE A,WORK_FLOW B WHERE A.FLOW_ID=? AND A.ORG_ID=? AND"
				+ " A.FLOW_ID=B.FLOW_TYPE_ID";
		String optStr= "[{'function':'edit','name':'修改','parm':'FLOW_PASS_LEAVE_ID'},{'function':'del','name':'删除','parm':'FLOW_PASS_LEAVE_ID'}]";
		JSONArray optArrJson = new JSONArray().fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	//获取行政级别的SELECT列表
	public String getFlowPassLeaveSelectLogic(Connection conn,String flowId,String orgId) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT PASS_LEAVE_ID,PASS_LEAVE_NAME FROM FLOW_PASS_LEAVE WHERE FLOW_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("PASS_LEAVE_ID"));
			json.accumulate("text", rs.getString("PASS_LEAVE_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	public String getFlowPassLeaveSelectByFlowIdLogic(Connection conn,String orgId,String flowId) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT PASS_LEAVE_ID,PASS_LEAVE_NAME FROM FLOW_PASS_LEAVE WHERE ORG_ID=? AND FLOW_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2,flowId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("PASS_LEAVE_ID"));
			json.accumulate("text", rs.getString("PASS_LEAVE_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	public int delFlowPassLeaveLogic(Connection conn,String flowPassleaveId) throws SQLException
	{
		int i =0;
		String queryStr="DELETE FROM FLOW_PASS_LEAVE WHERE FLOW_PASS_LEAVE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowPassleaveId);
		i=ps.executeUpdate();
		ps.close();
		return i;
	}
}
