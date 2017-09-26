package tfd.system.im.userinfo.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.im.userinfo.logic.UserInfoLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.SysTool;

public class UserInfoAct {
	UserInfoLogic logic = new UserInfoLogic();
	public void getLoginUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
			
			JSONObject userInfo=logic.getUserInfo(conn, account.getAccountId());
			String returnData = userInfo==null?"":userInfo.toString();
			System.out.println("im.userinfo.getLoginUserInfo:"+returnData);
			writer=response.getWriter();
			writer.print(returnData);
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String accountId=request.getParameter("accountId");
			
			JSONObject userInfo=logic.getUserInfo(conn, accountId);
			String returnData = userInfo==null?"":userInfo.toString();
			
			writer=response.getWriter();
			writer.print(returnData);
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
	public void getUserInfoAct(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String returnData="";
		Connection dbConn=null;
		try
		{
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			dbConn=DbPoolConnection.getInstance().getConnection();
			returnData=userInfoLogic.getUserInfoLogic(dbConn, account);
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
