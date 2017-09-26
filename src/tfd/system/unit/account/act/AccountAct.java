package tfd.system.unit.account.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

public class AccountAct {
	AccountLogic logic=new AccountLogic();
public void addAccountAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	JSONObject json = new JSONObject();
	int returnData=0;
	try{
	Account account = new Account();
	Account tmpAccount =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	String appIcon="[{\"id\":\"1\",\"name\":\"日程安排\",\"selected\":\"1\",\"sort\":1},{\"id\":\"2\",\"name\":\"工作日志\",\"selected\":\"1\",\"sort\":2},{\"id\":\"4\",\"name\":\"定位考勤\",\"selected\":\"1\",\"sort\":3},{\"id\":\"5\",\"name\":\"工作审批\",\"selected\":\"1\",\"sort\":4},{\"id\":\"6\",\"name\":\"客户管理\",\"selected\":\"1\",\"sort\":5}]";
	account.setAccountId(request.getParameter("accountId"));
	account.setPassWord(MD5Util.getMD5(request.getParameter("passWord")));
	account.setPasswordType(request.getParameter("passwordType"));
	account.setTheme(request.getParameter("thmem"));
	account.setUserPriv(request.getParameter("userPriv"));
	account.setNotLogin(request.getParameter("notLogin"));
	account.setByName(request.getParameter("byName"));
	account.setLanguage(request.getParameter("language"));
	account.setOrgId(tmpAccount.getOrgId());
	account.setAppIcon(appIcon);
	AccountLogic accountLogic = new AccountLogic();
	dbConn=DbPoolConnection.getInstance().getConnection();
	returnData=accountLogic.addAccountLogic(dbConn, account);
	if(returnData==1)
	{
		json.accumulate("accountId",request.getParameter("accountId"));
	}
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
	}
}
//更新账号信息
public void updateAccountAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	int returnData=0;
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	Account account = new Account();
	Account tmpAccount =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	account.setAccountId(request.getParameter("accountId"));
	account.setPassWord(MD5Util.getMD5(request.getParameter("passWord")));
	account.setPasswordType(request.getParameter("passwordType"));
	account.setTheme(request.getParameter("thmem"));
	account.setUserPriv(request.getParameter("userPriv"));
	account.setNotLogin(request.getParameter("notLogin"));
	account.setByName(request.getParameter("byName"));
	account.setLanguage(request.getParameter("language"));
	account.setOrgId(tmpAccount.getOrgId());
	AccountLogic accountLogic = new AccountLogic();
	returnData=accountLogic.uptateAccountLogic(dbConn, account);
	dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
}
//账号维护列表
public void getMenageAccountListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	String returnData="";
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	String pageStr=request.getParameter("page");
	String pageSizeStr=request.getParameter("rows");
	String sortName=request.getParameter("sort");
	String sortOrder=request.getParameter("order");
	int page=1;
	int pageSize=30;
	if(!SysTool.isNullorEmpty(pageStr))
	{
		page=Integer.parseInt(pageStr);
	}
	if(!SysTool.isNullorEmpty(pageSizeStr))
	{
		pageSize=Integer.parseInt(pageSizeStr);
	}
	Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
	List<String> pramList = new ArrayList<String>();
	pramList.add(account.getOrgId());
	pramList.add(request.getParameter("accountId"));
	pramList.add(request.getParameter("notLogin"));
	pramList.add(request.getParameter("userName"));
	pramList.add(request.getParameter("deptId"));
	AccountLogic accountLogic = new AccountLogic();	
	returnData=accountLogic.getMenageAccountListLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
//通过ACCOUNTID获取相关账号信息
public void getAccountJsonByIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String accountId=request.getParameter("accountId");
		AccountLogic accountLogic = new AccountLogic();	
		returnData=accountLogic.getAccountJsonByIdLogic(dbConn, accountId);
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
//删除账号
public void delAccountIdByIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	int returnData=0;
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String accountId = request.getParameter("accountId");
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		AccountLogic accountLogic = new AccountLogic();	
		returnData=accountLogic.delAccountIdByIdLogic(dbConn, accountId,account.getOrgId());
		dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
//通过ACCOUNTStr获取用户信息
public void getUserNameStrAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String accountIdStr=request.getParameter("accountIdStr");
	Connection dbConn = null;
	String returnData="";
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	AccountLogic accountLogic = new AccountLogic();
	returnData = accountLogic.getUserNameStr(dbConn, accountIdStr);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
//通过字符串得到用户姓名的json数组
public void getjsonUserNameStrAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Connection dbConn = null;
	String returnData="";
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	AccountLogic accountLogic = new AccountLogic();
	String userNameStr=request.getParameter("userNameStr");
	returnData=accountLogic.getjsonUserNameStr(dbConn, userNameStr);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
public void getUserListOfUserIdStr(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Connection dbConn = null;
	String returnData="";
	try{
	dbConn=DbPoolConnection.getInstance().getConnection();
	AccountLogic accountLogic = new AccountLogic();
	String userNameStr=request.getParameter("userIdStr");
	returnData=accountLogic.getjsonUserNameStr(dbConn, userNameStr);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
}
	/**
	 * 根据用户名设置用户app权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdpowerAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		int returnData = 0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			AccountLogic accountLogic = new AccountLogic();
			String accountId = request.getParameter("accountId");
			String orgId = account.getOrgId();
			String appIcon = request.getParameter("appIcon");
			
			String[] accountIdArr = accountId.split(",");
			for (int i = 0; i < accountIdArr.length; i++) {
				returnData = accountLogic.getIdpowerlogic(dbConn,accountIdArr[i], appIcon, orgId);
				if (returnData<1) {
					break;
				}
			}
			
			if (returnData>0) {
				dbConn.commit();
			}else{
				dbConn.rollback();
			}
		} catch (Exception e) {
			logic.dao.rollback(dbConn);
			throw e;
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer=response.getWriter();
			writer.print(returnData>0?"0":"-1");
			SysTool.closePrintWriter(writer);
		}
	}
		/**
		 * 根据部门Id 设置用户的app权限
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getdeptpowerAct(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Connection dbConn = null;
			int returnData = 0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
				AccountLogic accountLogic = new AccountLogic();
				String orgId = account.getOrgId();
				String appIcon = request.getParameter("appIcon");
				
				String deptId=request.getParameter("deptId");
				String[] deptIdArr=deptId.split(",");
				for (int i = 0; i < deptIdArr.length; i++) {
					returnData=accountLogic.getdeptpowerlogic(dbConn, deptIdArr[i], appIcon, orgId);
					if (returnData<1) {
						break;
					}
				}
				
				if (returnData>0) {
					dbConn.commit();
				}else{
					dbConn.rollback();
				}
			} catch (Exception e) {
				logic.dao.rollback(dbConn);
				throw e;
			} finally {
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer=response.getWriter();
				writer.print(returnData>0?"0":"-1");
				SysTool.closePrintWriter(writer);
			}
		}
		/**
		 * 根据角色设置用户的app权限
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getprivIdpowerAct(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Connection dbConn = null;
			int returnData=0;
			try
			{
				dbConn=DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				AccountLogic accountLogic=new AccountLogic();
				String postpriv=request.getParameter("userPriv");
				String appIcon=request.getParameter("appIcon");
				String orgId=account.getOrgId();
				String []postprivArr=postpriv.split(",");
				for (int i = 0; i < postprivArr.length; i++) {
					returnData=accountLogic.getprivIdpowerlogic(dbConn, postprivArr[i], appIcon, orgId);	
				}
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer=response.getWriter();
				writer.print(returnData>0?"0":"-1");
				SysTool.closePrintWriter(writer);
			}
		}
		//根据用户id获取icon
		public void getIconAct(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Connection dbConn = null;
			String returnData="";
			try
			{
				dbConn=DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				AccountLogic accountLogic=new AccountLogic();
				String accountId=account.getAccountId();
				String orgId=account.getOrgId();
				returnData=accountLogic.getIconAct(dbConn, accountId, orgId);
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
		}
		/**
		 * 检查accountId 是否存在
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void checkAccountIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Connection dbConn = null;
			int returnData=0;
			try {
				AccountLogic accountLogic=new AccountLogic();
				dbConn = DbPoolConnection.getInstance().getConnection();
				String accountId=request.getParameter("accountId");
				returnData =accountLogic.checkAccountIdLogic(dbConn, accountId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
		}
}
