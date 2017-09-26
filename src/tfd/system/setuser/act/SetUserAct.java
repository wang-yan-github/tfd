package tfd.system.setuser.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.mobile.userinfo.data.Mark;
import tfd.system.setuser.logic.SetUserLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.MD5Util;

public class SetUserAct {
	/**
	 * 根据AccountId得到用户信息和账户信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUserInfoAndAccountById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=account.getAccountId();
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().getUserInfoAndAccountById(dbConn, accountId,account.getOrgId());
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
	 * 根据AccountId得到用户基本信息
	 * Time : 2015-6-10
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getBaseUserInfoById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=account.getAccountId();
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().getBaseUserInfoById(dbConn, accountId,account.getOrgId());
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
	 * 更改用户信息和账户信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateUserInfoAndAccount(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			UserInfo u = new UserInfo();
			u.setAccountId(account.getAccountId());
			u.setUserId(request.getParameter("userId"));
			u.setUserName(request.getParameter("userName"));
			u.setSex(request.getParameter("sex"));
			u.setHomeAdd(request.getParameter("homeAdd"));
			u.setHomeTel(request.getParameter("homeTel"));
			u.setMobileNo(request.getParameter("mobile"));
			u.seteMail(request.getParameter("email"));
			u.setQq(request.getParameter("qq"));
			u.setOrgId(account.getOrgId());
			u.setHeadImg(request.getParameter("headImg"));
			String byName = request.getParameter("byName");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().updateUserInfoAndAccount(dbConn,u,byName);
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
	 * 检查密码是否正确
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkPassword(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String password = request.getParameter("pwd");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData = new SetUserLogic().checkPassword(dbConn, account.getAccountId(),MD5Util.getMD5(password));
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
	 * 检查密码是否正确
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getLastUpdateTime(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData = new SetUserLogic().getLastUpdateTime(dbConn, account.getAccountId());
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
	 * 更改密码
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePassword(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String pwd = request.getParameter("pwd");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData = new SetUserLogic().updatePassword(dbConn, account.getAccountId(),MD5Util.getMD5(pwd));
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
	
	public void getPassword(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData = new SetUserLogic().getPassword(dbConn, account.getAccountId());
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
	 * 根据AccountId得到用户信息和账户信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPersonalInfoById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String accountId= request.getParameter("accountId");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().getPersonalById(dbConn, accountId);
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
	
	public void getMarksById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String accountId= request.getParameter("accountId");
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().getMarksById(dbConn, accountId,account);
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
	
	public void doMarkGood(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String markId = request.getParameter("markId");
			String islike = request.getParameter("islike");
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().praisemark(dbConn, account,markId, islike);
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
	
	public void createMark(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String accountId = request.getParameter("accountId");
			String content = request.getParameter("content");
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Mark mark = new Mark();
			mark.setMarkId(GuId.getGuid());
			mark.setAccountId(accountId);
			mark.setContent(content);
			mark.setCreateUser(account.getAccountId());
			mark.setOrgId(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
		    returnData= new SetUserLogic().createMark(dbConn, account,mark);
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
}
