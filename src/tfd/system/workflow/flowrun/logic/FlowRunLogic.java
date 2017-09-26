package tfd.system.workflow.flowrun.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.tool.SysTool;

import tfd.system.workflow.flowrun.data.FlowRun;

public class FlowRunLogic {
	//创建流程主表信息
	public void createFlowRunLogic(Connection conn,FlowRun flowRun) throws SQLException
	{
		String queryStr="INSERT INTO FLOW_RUN(RUN_ID,TITLE,BEGIN_TIME,DEL_FLAG,OP_USER_STR,STATUS,FLOW_ID,MODULE,ORG_ID,PATH,ATTACH_ID,CREATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, flowRun.getRunId());
		ps.setString(2,flowRun.getTitle());
		ps.setString(3, SysTool.getCurDateTimeStr());
		ps.setString(4, "0");
		ps.setString(5, flowRun.getOpUserStr());
		ps.setString(6, flowRun.getStatus());
		ps.setString(7, flowRun.getFlowId());
		ps.setString(8, flowRun.getModule());
		ps.setString(9, flowRun.getOrgId());
		ps.setString(10, flowRun.getPath());
		ps.setString(11, flowRun.getAttachId());
		ps.setString(12, flowRun.getCreateUser());
		ps.executeUpdate();
		ps.close();
	}
	//获取实例
	public FlowRun getFlowRunLogic(Connection conn,String runId) throws SQLException
	{
		FlowRun flowRun = new FlowRun();
		String queryStr="SELECT ID,RUN_ID,TITLE,BEGIN_TIME,DEL_FLAG,OP_USER_STR,STATUS,FLOW_ID,MODULE,ORG_ID,PATH,ATTACH_ID,WAIT_PRCS_ID,PARENT_WAIT,FLOW_LEAVE,CREATE_USER FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			flowRun.setId(rs.getInt("ID"));
			flowRun.setRunId(rs.getString("RUN_ID"));
			flowRun.setTitle(rs.getString("TITLE"));
			flowRun.setBeginTime(rs.getString("BEGIN_TIME"));
			flowRun.setDelFlag(rs.getString("DEL_FLAG"));
			flowRun.setOpUserStr(rs.getString("OP_USER_STR"));
			flowRun.setStatus(rs.getString("STATUS"));
			flowRun.setFlowId(rs.getString("FLOW_ID"));
			flowRun.setModule(rs.getString("MODULE"));
			flowRun.setPath(rs.getString("PATH"));
			flowRun.setOrgId(rs.getString("ORG_ID"));
			flowRun.setAttachId(rs.getString("ATTACH_ID"));
			flowRun.setWaitPrcsId(rs.getString("WAIT_PRCS_ID"));
			flowRun.setParentWait(rs.getString("PARENT_WAIT"));
			flowRun.setFlowLeave(rs.getString("FLOW_LEAVE"));
			flowRun.setCreateUser(rs.getString("CREATE_USER"));
		}
		rs.close();
		ps.close();
		return flowRun;
	}
	
	//获取以住参与人员
	public String getOpUserStrLogic(Connection conn,String runId) throws SQLException
	{
		String returnData="";
		ResultSet rs =null;
		String queryStr="SELECT OP_USER_STR FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("OP_USER_STR");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	//更新参与人员
	public void updateOpUserStrLogic(Connection conn,String runId,String opUserStr) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET OP_USER_STR=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, opUserStr);
		ps.setString(2, runId);
		ps.executeUpdate();
		ps.close();
	}
	//更新流程等待情况
	public void updateFlowWaitLogic(Connection conn,String runId,String waitPrcsId,String parentWait) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET WAIT_PRCS_ID=?,PARENT_WAIT=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, waitPrcsId);
		ps.setString(2, parentWait);
		ps.setString(3, runId);
		ps.executeUpdate();
		ps.close();
	}
	//结束流程主表
	public void endFlowRunLogic(Connection conn,String runId) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET STATUS=?,END_TIME=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, "1");
		ps.setString(2, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(3, runId);
		ps.executeUpdate();
		ps.close();
	}
	//通过RUN_ID获取FLOW_ID
	public String getFlowIdByRunIdLogic(Connection conn,String runId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT FLOW_ID FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			returnData=rs.getString("FLOW_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//保存公共附件
	public void updataAttachIdLogic(Connection conn,String runId,String attactIds) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET ATTACH_ID=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, attactIds);
		ps.setString(2, runId);
		ps.executeUpdate();
		ps.close();
	}
	//通过runId获取ATTACHID
	public String getAtttchIdLogic(Connection conn,String RunId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT ATTACH_ID FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, RunId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			returnData=rs.getString("ATTACH_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//逻辑删除
	public int delFlowRunLogic(Connection conn,String runId) throws SQLException
	{
		String queryStr="UPDATE  FLOW_RUN SET DEL_FLAG='1' WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		int i=ps.executeUpdate();
		queryStr="UPDATE WORK_LIST SET DEL_FLAG='1' WHERE RUN_ID=?";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		i = ps.executeUpdate();
		return i;
	}
	//判断工作流是否有流程记录
	public boolean getFlowRunExistLogic(Connection conn,String flowId) throws SQLException
	{
		boolean flag = false;
		String queryStr="SELECT COUNT(*) AS NUM FROM FLOW_RUN WHERE FLOW_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			if(rs.getInt("NUM")>0)
			{
				flag = true;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	//删除
	public int delFlowRunByFlowIdLogic(Connection conn,String flowId) throws SQLException
	{
		String queryStr="";
		PreparedStatement ps=null;
		queryStr="DELETE FROM WORK_LIST WHERE RUN_ID IN (SELECT RUN_ID FROM FLOW_RUN WHERE FLOW_ID=?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		ps.executeUpdate();
		queryStr="DELETE FROM FLOW_RUN WHERE FLOW_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	public String getFlowRunByIdsLogic(Connection conn,String runIds) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		if(!SysTool.isNullorEmpty(runIds))
		{
			String queryStr="SELECT TITLE,PATH,RUN_ID FROM FLOW_RUN WHERE '"+runIds+"' LIKE CONCAT('%',RUN_ID,'%')";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.accumulate("runId", rs.getString("RUN_ID"));
				json.accumulate("title", rs.getString("TITLE"));
				json.accumulate("path", rs.getString("PATH"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
		}
		return jsonArr.toString();
	}
	
	public int updateLeaveAndTitleLogic(Connection conn,String runId,String title,String flowLeave) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET TITLE=?, FLOW_LEAVE=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, title);
		ps.setString(2, flowLeave);
		ps.setString(3, runId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
}
