package tfd.system.workflow.maintenance.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowutility.UitilityOpt;
import tfd.system.workflow.maintenance.data.Maintenance;
import tfd.system.workflow.maintenance.logic.MaintenanceLogic;

public class MaintenanceAct {

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
		Maintenance maintenance = new Maintenance();
		maintenance.setRunId(request.getParameter("runId"));
		maintenance.setFlowName(request.getParameter("flowName"));
		maintenance.setRunName(request.getParameter("runName"));
		maintenance.setFlowStatus(request.getParameter("flowStatus"));
		maintenance.setFlowType(request.getParameter("flowType"));
		maintenance.setBeginTime(request.getParameter("beginTime"));
		maintenance.setEndTime(request.getParameter("endTime"));
		maintenance.setBeginUserId(request.getParameter("beginUserId"));
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			MaintenanceLogic  queryWorkFlowLogic = new MaintenanceLogic();
			returnData=queryWorkFlowLogic.queryWorkFlowLogic(dbConn,maintenance,account,pageSize,page,sortOrder,sortName);
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
	
public void  delWorkFlowAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn =null;
	int returnData=0;
	String[] runIds = request.getParameterValues("runIds");
	try{
			UitilityOpt uop = new UitilityOpt();
			dbConn = DbPoolConnection.getInstance().getConnection();
			for(int i=0;runIds.length>i;i++)
			{
				returnData=uop.delWorkFlowFlase(dbConn, runIds[i]);
				dbConn.commit();
			}
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
