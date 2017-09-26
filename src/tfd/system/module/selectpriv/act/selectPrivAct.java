package tfd.system.module.selectpriv.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.module.selectpriv.logic.selectPrivLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;

public class selectPrivAct {

	//查询角色
	public void getPrivAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
	    Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
	    selectPrivLogic privLogic=new selectPrivLogic();
	    String data=privLogic.getPrivLogic(dbConn, account);
	    dbConn.close();
	    response.setContentType("text/html;charset=utf-8");
	    response.getWriter().print(data);
		response.getWriter().flush();
	}
}
