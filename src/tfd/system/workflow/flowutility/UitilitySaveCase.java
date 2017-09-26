package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.SQLException;

import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

public class UitilitySaveCase {
	public String getWorkFlowPageHtml(Connection conn, String runId)
	{
		String pageHtml ="";
		
		return pageHtml;
	}
	public void doSaveToNews(Connection conn,String runId) throws SQLException
	{
		String pageHtml=this.getWorkFlowPageHtml(conn, runId);
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		String flowId = flowRunLogic.getFlowIdByRunIdLogic(conn, runId);
		WorkFlowLogic WorkFlowLogic = new WorkFlowLogic();
		WorkFlow WorkFlow = new WorkFlow();
		WorkFlow=WorkFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowId);
		
	}

}
