package tfd.system.im.system.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.worklist.logic.WorkListLogic;

public class GetSysParamAct {

	public void getCountAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String returnData="";
		Connection dbConn=null;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			WorkListLogic workListLogic = new WorkListLogic();
			returnData =workListLogic.getFlowWorkCountLogic(dbConn, account);
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
			response.getWriter().flush();
		}
	}
	
	
}
