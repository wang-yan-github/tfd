package tfd.system.apppower.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.apppower.logic.Appiconlogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class AppiconAct {

	//查询各个模块的icon
	public void lookiconAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String data="";
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId=account.getOrgId();
			Appiconlogic iconlogic=new Appiconlogic();
			 data=iconlogic.lookiconlogic(dbConn, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
}
