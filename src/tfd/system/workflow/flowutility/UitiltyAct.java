package tfd.system.workflow.flowutility;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class UitiltyAct {
	public void getHistoryGraphAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData="";
		String runId = request.getParameter("runId");
		Connection dbConn = null;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UitilityGraph  uitilityGraph = new UitilityGraph();
			returnData = uitilityGraph.getHistoryGraphLogic(dbConn, runId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
		}
	}
}
