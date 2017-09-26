package tfd.system.mobile.changeorg.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.mobile.changeorg.logic.ChangeOrgLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;


public class ChangeOrgAct {

	/**
	 * 根据账号信息 获取公司列表
	 * Author Wp
	 * Time 2015-12-15
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void companyList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			ChangeOrgLogic changeOrgLogic=new ChangeOrgLogic();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			returnData=changeOrgLogic.companyListLogic(dbConn, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
