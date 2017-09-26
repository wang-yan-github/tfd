package tfd.system.register.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import tfd.system.register.logic.RegisterLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.org.data.Unit;

public class RegisterAct {

	/**
	 * 注册单位数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void reisterAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returndata=0;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Unit unit=new Unit();
			unit.setOrgName(request.getParameter("orgName"));
			unit.setOrgAdd(request.getParameter("orgAdd"));
			unit.setOrgTel(request.getParameter("orgTel"));
			unit.setOrgFax(request.getParameter("orgFax"));
			unit.setOrgPost(request.getParameter("orgPost"));
			unit.setOrgEmail(request.getParameter("orgEmail"));
			unit.setOrgId(GuId.getGuid());
			Account account=new Account();
			account.setAccountId(request.getParameter("accountId"));
			account.setPassWord(request.getParameter("password"));
			RegisterLogic registerLogic=new RegisterLogic();
			returndata=registerLogic.addUnitLogic(dbConn, unit, account);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returndata);
			response.getWriter().flush();	
			
		}
		
		
	}
	
}
