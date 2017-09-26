package checkin.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import net.sf.json.JSONObject;
import checkin.logic.CheckinLogic;

import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysProps;
import com.system.tool.SysTool;

public class CheckinAct {
	CheckinLogic logic=new CheckinLogic();
	public void list(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=logic.dao.getConn(SysProps.getWebInfoPath()+"/config/db/conn_checkin.properties");
			
			
			JSONObject list=logic.list(request.getParameterMap(), conn);
			writer=response.getWriter();
			writer.print(list.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void checkinSetting(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String url=request.getParameter("url");
			
			boolean priv=logic.checkinPriv(url, account.getAccountId(), account.getOrgId(), conn);
			String userName=new UserInfoLogic().getUserNameByAccountIdLogic(conn, account.getAccountId());
			
			JSONObject returnData=new JSONObject();
			returnData.put("userName", userName);
			returnData.put("priv", priv);
			
			writer=response.getWriter();
			writer.print(returnData.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
}
