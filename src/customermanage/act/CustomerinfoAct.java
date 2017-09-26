package customermanage.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import customermanage.data.Customerinfo;
import customermanage.logic.CustomerinfoLogic;

public class CustomerinfoAct {
	/**
	 * 添加客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addcustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			Customerinfo customerinfo=new Customerinfo();
			customerinfo.setCustomerId(GuId.getGuid());
			customerinfo.setAccountId(account.getAccountId());
			customerinfo.setJoinStaff(request.getParameter("joinStaff"));
			customerinfo.setCustomerName(request.getParameter("customerName"));
			customerinfo.setTelNumber(request.getParameter("telNumber"));
			customerinfo.setFaxNumber(request.getParameter("faxNumber"));
			customerinfo.setUrlName(request.getParameter("urlName"));
			customerinfo.seteMail(request.getParameter("eMail"));
			customerinfo.setAreaName(request.getParameter("areaName"));
			customerinfo.setAddName(request.getParameter("addName"));
			customerinfo.setCustomerType(request.getParameter("customerType"));
			customerinfo.setCustomerOrigin(request.getParameter("customerOrigin"));
			customerinfo.setTradeType(request.getParameter("tradeType"));
			customerinfo.setFirmNature(request.getParameter("firmNature"));
			customerinfo.setFirmSummary(request.getParameter("firmSummary"));
			customerinfo.setRemark(request.getParameter("remark"));
			customerinfo.setTop("0");
			customerinfo.setOrgId(account.getOrgId());
			customerinfo.setKeeper(request.getParameter("keeper"));
			customerinfo.setCustomerStatus(request.getParameter("customerStatus"));
			customerinfo.setCustomerTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			customerinfo.setDelStatus(0);
			data = customerinfoLogic.addcustomerLoigc(dbConn, customerinfo);
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
	 * customerId删除客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delcustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			String[] customerId = request.getParameterValues("customerId");
			data = 0;
			for(int i=0;customerId.length>i;i++)
			{
			 String orgId=account.getOrgId();
			 data=customerinfoLogic.delcustomerLogic(dbConn, customerId[i], orgId);
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
	 * 根据custoemrId修改客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatecustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			Customerinfo customerinfo=new Customerinfo();
			customerinfo.setCustomerId(request.getParameter("customerId"));
			customerinfo.setJoinStaff(request.getParameter("joinStaff"));
			customerinfo.setCustomerName(request.getParameter("customerName"));
			customerinfo.setTelNumber(request.getParameter("telNumber"));
			customerinfo.setFaxNumber(request.getParameter("faxNumber"));
			customerinfo.setUrlName(request.getParameter("urlName"));
			customerinfo.seteMail(request.getParameter("eMail"));
			customerinfo.setAreaName(request.getParameter("areaName"));
			customerinfo.setAddName(request.getParameter("addName"));
			customerinfo.setCustomerType(request.getParameter("customerType"));
			customerinfo.setCustomerOrigin(request.getParameter("customerOrigin"));
			customerinfo.setTradeType(request.getParameter("tradeType"));
			customerinfo.setFirmNature(request.getParameter("firmNature"));
			customerinfo.setFirmSummary(request.getParameter("firmSummary"));
			customerinfo.setRemark(request.getParameter("remark"));
			customerinfo.setOrgId(account.getOrgId());
			customerinfo.setKeeper(request.getParameter("keeper"));
			customerinfo.setCustomerStatus(request.getParameter("customerStatus"));
			data = customerinfoLogic.updatecustomerLogic(dbConn, customerinfo);
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
	 * 根据custoemrId 获取客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdcustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			String customerId=request.getParameter("customerId");
			String orgId=account.getOrgId();
			data = customerinfoLogic.getIdcustomerLogic(dbConn, customerId, orgId);
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
	 * 根据keeper获取我的客户
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNamecustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String status=request.getParameter("status");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			String accountId=account.getAccountId();
			String orgId=account.getOrgId();
			data = customerinfoLogic.getNamecustomerAct(dbConn, accountId, orgId, pageSize, page, sortOrder, sortName,status);
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
	 * 获取下属的客户列表信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void branchcustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String status=request.getParameter("status");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			data = customerinfoLogic.branchcustomerLogic(dbConn, account,pageSize, page, sortOrder, sortName,status);
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
	 * 根据accountId获取共享给自己的客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sharecustomerAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String status=request.getParameter("status");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			data = customerinfoLogic.sharecustomerLogic(dbConn, account, pageSize, page, sortOrder, sortName,status);
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
	 * 根据条件 查询获取客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void gettermcusAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String termtype=request.getParameter("termtype");
			String customerName=request.getParameter("customerName");
			String linkmanName=request.getParameter("linkmanName");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			if(termtype.equals("1")){
				data=customerinfoLogic.gettermcusLogic(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
			}else{
				if(termtype.equals("2")){
					data=customerinfoLogic.termbranchcusLoigc(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
				}else{
					data=customerinfoLogic.termsharecusLogic(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
				}
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
	 * 根据条件查询类型为合作伙伴的客户信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void gettermTypecusAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String termtype=request.getParameter("termtype");
			String customerName=request.getParameter("customerName");
			String linkmanName=request.getParameter("linkmanName");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			if(termtype.equals("1")){
				data=customerinfoLogic.typetermLogic(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
			}else{
				if(termtype.equals("2")){
				data=customerinfoLogic.typebranchLogic(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
				}else{
					data=customerinfoLogic.typeshareLogic(dbConn, account, pageSize, page, sortOrder, sortName, customerName, linkmanName);
				}
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
	 * 根据customerId修改置顶状态
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatetopAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			CustomerinfoLogic customerinfoLogic=new CustomerinfoLogic();
			String customerId=request.getParameter("customerId");
			String top=request.getParameter("top");
			String orgId=account.getOrgId();
			data = customerinfoLogic.updatetopLogic(dbConn, customerId, orgId, top);
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
