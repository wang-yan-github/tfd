package tfd.system.portal.act;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbPoolConnection;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
public class PortalAct {
	public void getPortalInfoJson(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		String returnData=userInfoLogic.getUserInfoByAccountIdLogic(dbConn,account.getAccountId());
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}

}
