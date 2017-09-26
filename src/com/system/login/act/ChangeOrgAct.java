package com.system.login.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.login.logic.ChangeOrgLogic;

import tfd.system.unit.account.data.Account;

public class ChangeOrgAct {

public void ChanageOrgListAct(HttpServletRequest request,HttpServletResponse response) throws Exception
{
	Connection dbConn=null;
	String returnData="";
	Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		ChangeOrgLogic changeOrgLogic = new ChangeOrgLogic();
		returnData = changeOrgLogic.ChanageOrgListLogic(dbConn, account);
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
