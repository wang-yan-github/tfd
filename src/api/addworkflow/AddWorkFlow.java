package api.addworkflow;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.worklist.data.WorkList;

public class AddWorkFlow {
	
	public void addWorkFlow(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("工作流添加开始");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			for (int i = 34976; i < 1000000; i++) {
			addFlowRunData(dbConn,i);
			if(i%1000==0)
			{
				dbConn.commit();
			}
			}
			System.out.println("**********************************************");
			System.out.println("**                                          **");
			System.out.println("**                   全部创建完成                                             **");
			System.out.println("**                                          **");
			System.out.println("**********************************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
	}
	
	
	public static void main(String[] args)throws Exception {
		Connection conn = DbPoolConnection.getInstance().getConnection();
		for (int i = 34976; i < 1000000; i++) {
			System.out.println("第"+i+"个流程创建成功！");
		addFlowRunData(conn,i);
		if(i%1000==0)
		{
			conn.commit();
		}
		}
		System.out.println("**********************************************");
		System.out.println("**                                          **");
		System.out.println("**                   全部创建完成                                             **");
		System.out.println("**                                          **");
		System.out.println("**********************************************");
	}
	
	
	public static void addFlowRunData(Connection conn,int i) throws NoSuchAlgorithmException, SQLException
	{
		FlowRun flowRun = new FlowRun();
		String runId = GuId.getGuid();
		String orgId="8EADB678-A646-1E51-3E87-75A547B8AF19";
		flowRun.setRunId(runId);
		String title="测试第"+i+"个流程";
		flowRun.setTitle(title);
		String beginTime=SysTool.getCurDateTimeStr("yyyy-MM-dd hh:mm:ss");
		flowRun.setBeginTime(beginTime);
		flowRun.setEndTime("");
		flowRun.setDelFlag("0");
		flowRun.setOpUserStr("admin");
		flowRun.setStatus("0");
		String flowId ="3891FD4D-D54C-DEB4-D72B-0DA04F06D54C";
		flowRun.setFlowId(flowId);
		String readUrl = "/system/workflow/dowork/bpmtest/print/index.jsp?runId="+runId;
		flowRun.setPath(readUrl);
		flowRun.setModule("workflow");
		flowRun.setAttachId("");
		flowRun.setWaitPrcsId("");
		flowRun.setFlowLeave("0");
		flowRun.setOrgId(orgId);
		String queryStr="INSERT INTO FLOW_RUN(RUN_ID,TITLE,BEGIN_TIME,END_TIME,DEL_FLAG,OP_USER_STR,STATUS,"+
									"FLOW_ID,PATH,MODULE,ATTACH_ID,WAIT_PRCS_ID,FLOW_LEAVE,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowRun.getRunId());
		ps.setString(2,flowRun.getTitle());
		ps.setString(3, flowRun.getBeginTime());
		ps.setString(4, flowRun.getEndTime());
		ps.setString(5, flowRun.getDelFlag());
		ps.setString(6, flowRun.getOpUserStr());
		ps.setString(7, flowRun.getStatus());
		ps.setString(8, flowRun.getFlowId());
		ps.setString(9, flowRun.getPath());
		ps.setString(10,flowRun.getModule());
		ps.setString(11,flowRun.getAttachId());
		ps.setString(12,flowRun.getWaitPrcsId());
		ps.setString(13,flowRun.getFlowLeave());
		ps.setString(14, flowRun.getOrgId());
		ps.executeUpdate();
		ps.close();
		
		WorkList workList = new WorkList();
		workList.setTitle(title);
		workList.setRunId(runId);
		workList.setModule("workflow");
		workList.setAccountId("admin");
		workList.setReadUrl(readUrl);
		String url = "/system/workflow/dowork/bpmtest/1/index.jsp?runId="+runId+"&runPrcsId=1";
		workList.setUrl(url);
		workList.setStatus("0");
		workList.setCreateTime(beginTime);
		workList.setEndTime("");;
		workList.setPrcsId(1);
		workList.setRunPrcsId(1);
		workList.setDelFlag("0");
		workList.setOrgId(orgId);
		String sql = "INSERT INTO WORK_LIST(TITLE,RUN_ID,MODULE,ACCOUNT_ID,READ_URL,URL,STATUS,CREATE_TIME,END_TIME,PRCS_ID,RUN_PRCS_ID,DEL_FLAG,ORG_ID)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setString(1, workList.getTitle());
		ps1.setString(2, workList.getRunId());
		ps1.setString(3, workList.getModule());
		ps1.setString(4, workList.getAccountId());
		ps1.setString(5, workList.getReadUrl());
		ps1.setString(6, workList.getUrl());
		ps1.setString(7, workList.getStatus());
		ps1.setString(8, workList.getCreateTime());
		ps1.setString(9, workList.getEndTime());
		ps1.setInt(10, workList.getPrcsId());
		ps1.setInt(11, workList.getRunPrcsId());
		ps1.setString(12, workList.getDelFlag());
		ps1.setString(13, workList.getOrgId());
		ps1.executeUpdate();
		ps1.close();
		
		FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
		flowRunPrcs.setRunId(runId);
		flowRunPrcs.setFlowId(flowId);
		flowRunPrcs.setRunPrcsId(1);
		flowRunPrcs.setPrcsId(1);
		flowRunPrcs.setOpFlag("0");
		flowRunPrcs.setTableName("bpmtest");
		flowRunPrcs.setPrcsName("开始");
		flowRunPrcs.setAccountId("admin");
		flowRunPrcs.setUserName("超级管理员");
		flowRunPrcs.setDeptId("743FD4A8-8FC5-9E51-103C-14F923742A70");
		flowRunPrcs.setUserPrivId("3D159430-CD93-9CEF-15A8-C5573DAA080F");
		flowRunPrcs.setLeadId("");
		flowRunPrcs.setCreateTime(beginTime);
		flowRunPrcs.setEndTime("");
		flowRunPrcs.setPass("1");
		flowRunPrcs.setIdea("1");
		flowRunPrcs.setStatus("0");
		flowRunPrcs.setIdeaText("");
		flowRunPrcs.setFollow("");
		flowRunPrcs.setAttachId("");
		flowRunPrcs.setOrgId(orgId);
		String sql2= "INSERT INTO FLOW_RUN_PRCS "
				+ "("
				+ "RUN_ID,"
				+ "FLOW_ID,"
				+ "RUN_PRCS_ID,"
				+ "PRCS_ID,"
				+ "OP_FLAG,"
				+ "TABLE_NAME,"
				+ "PRCS_NAME,"
				+ "ACCOUNT_ID,"
				+"USER_NAME,"
				+ "DEPT_ID,"
				+ "USER_PRIV_ID,"
				+ "LEAD_ID,"
				+ "CREATE_TIME,"
				+ "END_TIME,"
				+ "PASS,"
				+ "IDEA,"
				+ "STATUS,"
				+ "IDEA_TEXT,"
				+ "FOLLOW,"
				+ "ATTACH_ID,"
				+ "ORG_ID)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setString(1, flowRunPrcs.getRunId());
		ps2.setString(2, flowRunPrcs.getFlowId());
		ps2.setInt(3, flowRunPrcs.getRunPrcsId());
		ps2.setInt(4, flowRunPrcs.getPrcsId());
		ps2.setString(5, flowRunPrcs.getOpFlag());
		ps2.setString(6, flowRunPrcs.getTableName());
		ps2.setString(7, flowRunPrcs.getPrcsName());
		ps2.setString(8, flowRunPrcs.getAccountId());
		ps2.setString(9, flowRunPrcs.getUserName());
		ps2.setString(10, flowRunPrcs.getDeptId());
		ps2.setString(11, flowRunPrcs.getUserPrivId());
		ps2.setString(12, flowRunPrcs.getLeadId());
		ps2.setString(13, flowRunPrcs.getCreateTime());
		ps2.setString(14, flowRunPrcs.getEndTime());
		ps2.setString(15, flowRunPrcs.getPass());
		ps2.setString(16, flowRunPrcs.getIdea());
		ps2.setString(17, flowRunPrcs.getStatus());
		ps2.setString(18, flowRunPrcs.getIdeaText());
		ps2.setString(19, flowRunPrcs.getFollow());
		ps2.setString(20,flowRunPrcs.getAttachId());
		ps2.setString(21, flowRunPrcs.getOrgId());
		ps2.executeUpdate();
		ps2.close();
	}
	
	
	
}
