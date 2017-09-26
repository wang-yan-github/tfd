package tfd.system.workflow.search.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.search.data.Search;
import tfd.system.workflow.search.logic.SearchLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class SearchAct {

	public void querywfAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String pageStr=request.getParameter("page");
		String pageSizeStr=request.getParameter("rows");
		String sortName=request.getParameter("sort");
		String sortOrder=request.getParameter("order");
		int page=1;
		int pageSize=10;
		if(!SysTool.isNullorEmpty(pageStr))
		{
			page=Integer.parseInt(pageStr);
		}
		if(!SysTool.isNullorEmpty(pageSizeStr))
		{
			pageSize=Integer.parseInt(pageSizeStr);
		}
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Search search = new Search();
		search.setRunId(request.getParameter("runId"));
		search.setFlowName(request.getParameter("flowName"));
		search.setRunName(request.getParameter("runName"));
		search.setFlowStatus(request.getParameter("flowStatus"));
		search.setFlowType(request.getParameter("flowType"));
		search.setBeginTime(request.getParameter("beginTime"));
		search.setEndTime(request.getParameter("endTime"));
		search.setBeginUserId(request.getParameter("beginUserId"));
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			SearchLogic  searchLogic = new SearchLogic();
			returnData=searchLogic.queryWorkFlowLogic(dbConn,search,account,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
	}
	}

}
