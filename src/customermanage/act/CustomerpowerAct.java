package customermanage.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import customermanage.data.CustomerPower;
import customermanage.logic.CustomerpowerLogic;

public class CustomerpowerAct {

	/**
	 * 添加权限人员
	 * Author:Wp
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
			CustomerPower customerpower=new CustomerPower();
			CustomerpowerLogic customerpowerLogic=new CustomerpowerLogic();
			String accountId=request.getParameter("accountId");
			String[] accountIds =null;
			if(accountId.indexOf(",")>-1){
				accountIds = accountId.split(",");
			}else
			{
				accountIds=new String[]{accountId};
			}
			data = 0;
			for (int i = 0; i < accountIds.length; i++) {
				customerpower.setPowerId(GuId.getGuid());
				customerpower.setAccountId(accountIds[i]);
				customerpower.setOrgId(account.getOrgId());
				data=customerpowerLogic.addpowerLogic(dbConn, customerpower);	
			}
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
	/**
	 * 查看权限人员
	 * Author:Wp
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
			CustomerpowerLogic customerpowerLogic=new CustomerpowerLogic();
			String orgId=account.getOrgId();
			data = customerpowerLogic.lookpowerLogic(dbConn, orgId);
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
	/**
	 * 根据powerId删除人员信息
	 * Author:Wp
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
			CustomerpowerLogic customerpowerLogic=new CustomerpowerLogic();
			String powerId=request.getParameter("powerId");
			String orgId=account.getOrgId();
			data = customerpowerLogic.delpowerLogic(dbConn, powerId, orgId);
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
	/**
	 * 判断当前 用户是否有权限
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkpowerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerpowerLogic customerpowerLogic=new CustomerpowerLogic();
			data = customerpowerLogic.checkpowerLogic(dbConn, account);
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
