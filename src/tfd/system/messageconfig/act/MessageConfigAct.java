package tfd.system.messageconfig.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.messageconfig.logic.MessageConfigLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class MessageConfigAct {
	public void updateMessageConfigAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			String guId = GuId.getGuid();
			String value = request.getParameter("value");
			MessageConfigLogic logic = new MessageConfigLogic();
			returnData=logic.updateMessageConfigLogic(dbConn,guId,value,account);
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
	public void getAllMessageConfigAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn=null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			MessageConfigLogic logic = new MessageConfigLogic();
			returnData = logic.getMessageConfigLogic(dbConn, account);
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
	public void getMConfigJsonAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String module=request.getParameter("module");
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn=null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			MessageConfigLogic logic = new MessageConfigLogic();
			returnData = logic.getMConfigJsonLogic(dbConn, module, account);
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
