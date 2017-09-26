package news.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.data.NewsPower;
import news.logic.NewsPowerLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class NewsPowerAct {

	/**
	 * 添加新闻权限数据
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addpowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsPower newspower=new NewsPower();
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			newspower.setPowerId(GuId.getGuid());
			newspower.setAccountId(request.getParameter("accountId"));
			newspower.setPowerStatus(request.getParameter("powerStatus"));
			newspower.setOrgId(account.getOrgId());
			data = newsPowerLogic.addpowerlogic(dbConn, newspower);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据权限Id 删除数据
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delpowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			String powerId=request.getParameter("powerId");
			String orgId=account.getOrgId();
			data = newsPowerLogic.delpowerlogic(dbConn, powerId, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据权限Id 进行权限信息修改
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatepowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsPower newspower=new NewsPower();
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			newspower.setPowerId(request.getParameter("powerId"));
			newspower.setAccountId(request.getParameter("accountId"));
			newspower.setPowerStatus(request.getParameter("powerStatus"));
			newspower.setOrgId(account.getOrgId());
			data = newsPowerLogic.updatepowerlogic(dbConn, newspower);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 获取权限人员列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookpowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			String orgId=account.getOrgId();
			data = newsPowerLogic.lookpowerlogic(dbConn, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	} 
	/**
	 * 根据accountId 查询数据
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getaccountIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=account.getAccountId();
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			data = newsPowerLogic.getaccountIdlogic(dbConn, accountId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 获取拥有审批权限的审批人员
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId=account.getOrgId();
			NewsPowerLogic newsPowerLogic=new NewsPowerLogic();
			data = newsPowerLogic.getapprovallogic(dbConn, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
	}
}
