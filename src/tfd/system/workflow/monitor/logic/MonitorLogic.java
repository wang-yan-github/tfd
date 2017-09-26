package tfd.system.workflow.monitor.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.monitor.data.MonitorField;
import tfd.system.workflow.worklist.logic.WorkListLogic;

public class MonitorLogic {
	public String getMonitorFlowLogic(Connection conn,MonitorField monitorField,Account account,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getOrgId());
		String whereStr="";
		if(!SysTool.isNullorEmpty(monitorField.getRunId()))
		{
			whereStr+=" AND F.RUN_ID='"+monitorField.getRunId()+"'";
		}
		if(!SysTool.isNullorEmpty(monitorField.getFlowTitle()))
		{
			whereStr+=" AND F.TITLE LIKE '%"+monitorField.getFlowTitle()+"%' ";
		}
		if(!SysTool.isNullorEmpty(monitorField.getFlowName()))
		{
			whereStr+=" AND W.FLOW_NAME LIKE '%"+monitorField.getFlowName()+"%'";
		}
		if(!SysTool.isNullorEmpty(monitorField.getBeginUserId()))
		{
			whereStr+=" AND F.RUN_ID IN (SELECT RUN_ID FROM FLOW_RUN_PRCS WHERE RUN_PRCS_ID='1' AND ACCOUNT_ID='"+monitorField.getBeginUserId()+"') ";
		}
		if(!SysTool.isNullorEmpty(monitorField.getStatus()))
		{
			whereStr+=" AND F.STATUS='"+monitorField.getStatus()+"'";
		}
		String queryStr="SELECT F.ID AS ID,F.RUN_ID AS RUN_ID,F.TITLE AS TITLE,F.STATUS AS STATUS,F.BEGIN_TIME AS BEGIN_TIME,F.PATH AS PATH FROM FLOW_RUN F,WORK_FLOW W WHERE W.ORG_ID=? AND F.FLOW_ID=W.FLOW_TYPE_ID "
				+whereStr;
		String optStr= "[{'function':'read','name':'查看','parm':'RUN_ID,PATH'},{'function':'doprocess','name':'处理','parm':'RUN_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	
	public void doForceLogic (Connection conn,FlowRunPrcs flowRunPrcs,int runPrcsId,int prcsId,Account account) throws SQLException
	{
		FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
		flowRunPrcsLogic.addFlowRunPrcsLogic(conn, flowRunPrcs);
		flowRunPrcsLogic.updateStatusLogic(conn, flowRunPrcs.getRunId(), prcsId, account.getAccountId(), runPrcsId);
		WorkListLogic workListLogic= new WorkListLogic();
		workListLogic.updateStatusForceLogic(conn, flowRunPrcs.getRunId(), prcsId,runPrcsId);
		
	}
}
