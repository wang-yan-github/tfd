package tfd.system.workflow.search.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.search.data.Search;

import com.system.db.PageTool;
import com.system.tool.SysTool;

public class SearchLogic {

	public String queryWorkFlowLogic(Connection conn,Search search,Account account,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getOrgId());
		String runId=search.getRunId();
		String runName=search.getRunName();
		String flowType=search.getFlowType();
		String flowName=search.getFlowName();
		String flowStatus=search.getFlowStatus();
		String beginTime=search.getBeginTime();
		String endTime=search.getEndTime();
		String beginUserId = search.getBeginUserId();
		String whereStr="";
		if(!SysTool.isNullorEmpty(runId))
		{
			whereStr+="AND A.RUN_ID='"+runId+"' ";
		}
		if(!SysTool.isNullorEmpty(runName))
		{
			whereStr+="AND A.TITLE LIKE '%"+runName+"%' ";
		}
		if(!SysTool.isNullorEmpty(flowStatus))
		{
			whereStr+="AND A.STATUS='"+flowStatus+"' ";
		}
		if(!SysTool.isNullorEmpty(flowType))
		{
			whereStr+="AND B.FLOW_TYPE='"+flowType+"' ";
		}
		if(!SysTool.isNullorEmpty(flowName))
		{
			whereStr+="AND B.FLOW_NAME LIKE '%"+flowName+"%' ";
		}
		if(!SysTool.isNullorEmpty(beginTime))
		{
			whereStr+="AND A.BEGIN_TIME >'"+beginTime+"' ";
		}
		if(!SysTool.isNullorEmpty(endTime))
		{
			whereStr+="AND A.BEGIN_TIME <='"+endTime+"' ";
		}
		if(!SysTool.isNullorEmpty(beginUserId))
		{
			whereStr+="AND A.RUN_ID IN (SELECT RUN_ID FROM FLOW_RUN_PRCS WHERE RUN_PRCS_ID='1' AND ACCOUNT_ID='"+beginUserId+"') ";
		}
		String queryStr="SELECT A.ID AS ID,A.RUN_ID AS RUN_ID,A.TITLE AS TITLE,A.BEGIN_TIME AS BEGIN_TIME,A.STATUS AS STATUS,A.PATH AS PATH FROM FLOW_RUN A,WORK_FLOW B "
								+ " WHERE A.DEL_FLAG='0' AND A.FLOW_ID=B.FLOW_TYPE_ID AND B.ORG_ID=? "
								+whereStr;
		String optStr= "[{'function':'read','name':'查看','parm':'RUN_ID,PATH'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}

}
