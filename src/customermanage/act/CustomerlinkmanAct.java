package customermanage.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import customermanage.data.CustomerLinkman;
import customermanage.logic.CustomerlinkmanLogic;

public class CustomerlinkmanAct {

	/**
	 * 添加 客户联系人 信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addlinkmanAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			CustomerLinkman customerLinkman=new CustomerLinkman();
			customerLinkman.setLinkmanId(GuId.getGuid());
			customerLinkman.setCustomerId(request.getParameter("customerId"));
			customerLinkman.setLinkmanName(request.getParameter("linkmanName"));
			customerLinkman.setLinkmanJob(request.getParameter("linkmanJob"));
			customerLinkman.setLinkmanSex(request.getParameter("linkmanSex"));
			customerLinkman.setLinkmanCall(request.getParameter("linkmanCall"));
			customerLinkman.setHomePhone(request.getParameter("homePhone"));
			customerLinkman.setCellPhone(request.getParameter("cellPhone"));
			customerLinkman.setQqNumber(request.getParameter("qqNumber"));
			customerLinkman.seteMail(request.getParameter("eMail"));
			customerLinkman.setAddName(request.getParameter("addName"));
			customerLinkman.setRemark(request.getParameter("remark"));
			customerLinkman.setOrgId(account.getOrgId());
			data = customerlinkmanLogic.addlinkmanLogic(dbConn, customerLinkman);
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
	 * 根据Id删除联系人
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delLinkmanAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			String linkmanId=request.getParameter("linkmanId");
			String orgId=account.getOrgId();
			data = customerlinkmanLogic.delLinkmanLogic(dbConn, linkmanId, orgId);
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
	 * 根据linkmanId修改联系人信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatelinkmanAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			CustomerLinkman customerLinkman=new CustomerLinkman();
			customerLinkman.setLinkmanId(request.getParameter("linkmanId"));;
			customerLinkman.setCustomerId(request.getParameter("customerId"));
			customerLinkman.setLinkmanName(request.getParameter("linkmanName"));
			customerLinkman.setLinkmanJob(request.getParameter("linkmanJob"));
			customerLinkman.setLinkmanSex(request.getParameter("linkmanSex"));
			customerLinkman.setLinkmanCall(request.getParameter("linkmanCall"));
			customerLinkman.setHomePhone(request.getParameter("homePhone"));
			customerLinkman.setCellPhone(request.getParameter("cellPhone"));
			customerLinkman.setQqNumber(request.getParameter("qqNumber"));
			customerLinkman.seteMail(request.getParameter("eMail"));
			customerLinkman.setAddName(request.getParameter("addName"));
			customerLinkman.setRemark(request.getParameter("remark"));
			customerLinkman.setOrgId(account.getOrgId());
			data = customerlinkmanLogic.updatelinkmanLogic(dbConn, customerLinkman);
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
	 * 根据linkmanId获取 联系人信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdlinkmanAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			String linkmanId=request.getParameter("linkmanId");
			String orgId=account.getOrgId();
			data = customerlinkmanLogic.getIdlinkmanLogic(dbConn, linkmanId, orgId);
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
	 * 根据custoemrId获取联系人列表
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getcIdlinkmanAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			String customerId=request.getParameter("customerId");
			String orgId=account.getOrgId();
			data = customerlinkmanLogic.getcIdlinkmanLogic(dbConn, customerId, orgId);
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
	 * 根据customerId获取联系人姓名
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getlinkmanNameAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerlinkmanLogic customerlinkmanLogic=new CustomerlinkmanLogic();
			String customerId=request.getParameter("customerId");
			String orgId=account.getOrgId();
			data = customerlinkmanLogic.getlinkmanNameLogic(dbConn, customerId, orgId);
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
