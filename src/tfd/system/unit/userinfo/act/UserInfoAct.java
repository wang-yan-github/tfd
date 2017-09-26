package tfd.system.unit.userinfo.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class UserInfoAct {
	
	//获取人员权限的树
	public void getUserPowerTreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserInfoLogic  userInfoLogic = new UserInfoLogic();
			returnData=userInfoLogic.getUserPowerTreeLogic(dbConn, account);
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
	//获取部门树
public void getUserInfoTreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    returnData= userInfoLogic.getUserInfoTreeLogic(dbConn, account);
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
//获取部门下的人员 加载到树中
public void getZtreedeptAlluserAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    String deptId=request.getParameter("id");
	    returnData= userInfoLogic.getZtreedeptAlluserLogic(dbConn, deptId);
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
//获取人员信息
public void getUserInfoAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String userId=request.getParameter("userId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    returnData= userInfoLogic.getUserInfoLogic(dbConn, userId);
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
public void getCurrentUserInfoListByAccountId(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Connection dbConn = null;
	PrintWriter writer=null;
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
	    Account account=(Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
	    
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    JSONArray userInfoList= userInfoLogic.getUserInfoListByAccountId(dbConn, account.getAccountId());
	    
	    writer=response.getWriter();
	    writer.print(userInfoList.toString());
	    
	}catch(Exception e){
		throw e;
	}finally{
		new DbOp().connClose(dbConn);
		SysTool.closePrintWriter(writer);
	}
}
//获取用户部门人员列表
public void getUserInfoListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
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
	String deptId=request.getParameter("deptId");
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		List<String> pramList = new ArrayList<String>();
		pramList.add(deptId);
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    returnData= userInfoLogic.getUserInfoListLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
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
//通过ACCOUNT_ID获取用户信息JSON格式
public void getUserInfoByAccountIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	String accountId=request.getParameter("accountId");
	Connection dbConn = null;
	String returnData="";
	try{
		dbConn=DbPoolConnection.getInstance().getConnection();
	    UserInfoLogic  userInfoLogic = new UserInfoLogic();
	    returnData= userInfoLogic.getUserInfoByAccountIdLogic(dbConn, accountId);
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

//更新人员信息
public String updateUserInfoAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	UserInfo userInfo = new UserInfo();
	String accountId=request.getParameter("accountId");
	Connection dbConn = null;
	try
	{
	dbConn=DbPoolConnection.getInstance().getConnection();
    userInfo.setAccountId(accountId);
    userInfo.setUserName(request.getParameter("userName"));
    userInfo.setSex(request.getParameter("sex"));
    userInfo.setDeptId(request.getParameter("deptId"));
    userInfo.setLeadId(request.getParameter("leadId"));
    userInfo.setPostPriv(request.getParameter("postPriv"));
    userInfo.setOtherPriv(request.getParameter("otherPriv"));
    userInfo.setHomeAdd(request.getParameter("homeAdd"));
    userInfo.setHomeTel(request.getParameter("homeTel"));
    userInfo.setMobileNo(request.getParameter("mobileNo"));
    userInfo.setQq(request.getParameter("qQ"));
    userInfo.seteMail(request.getParameter("eMaile"));
    userInfo.setWorkId(request.getParameter("workId"));
    userInfo.setManageDept(request.getParameter("manageDept"));
    userInfo.setOtherDept(request.getParameter("otherDept"));
    userInfo.setLeadLeave(request.getParameter("leadLeave"));
	UserInfoLogic userInfoLogic = new UserInfoLogic();
	userInfoLogic.updateUserInfoLogic(dbConn, userInfo);
	String notLogin=request.getParameter("notLogin");
	if(!notLogin.equals("null")){
		AccountLogic accountLogic=new AccountLogic();
		accountLogic.updateNotLoginLogic(dbConn, accountId);
	}
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
	}
	return "/system/unit/userinfo/edituserinfo.jsp?type=1&accountId="+accountId;
}
//根据accountId 查询是否已关联到userinfo 表
public void checkUserinfoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Connection dbConn = null;
	int data=0;
	try
	{
	dbConn=DbPoolConnection.getInstance().getConnection();
	String accountId=request.getParameter("accountId");
	Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	UserInfoLogic userInfoLogic = new UserInfoLogic();
	data=userInfoLogic.checkUserinfoLogic(dbConn, accountId, account.getOrgId());
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
	}
}
public String addUserInfoAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	UserInfo userInfo = new UserInfo();
	Connection dbConn = null;
	String accountId=request.getParameter("accountId");
	try
	{
	dbConn=DbPoolConnection.getInstance().getConnection();
    String userId = GuId.getGuid();
    userInfo.setUserId(userId);
    userInfo.setAccountId(accountId);
    userInfo.setUserName(request.getParameter("userName"));
    userInfo.setSex(request.getParameter("sex"));
    userInfo.setDeptId(request.getParameter("deptId"));
    userInfo.setLeadId(request.getParameter("leadId"));
    if(SysTool.isNullorEmpty(request.getParameter("postPriv")))
    {
    	 Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
    	 userInfo.setPostPriv(account.getUserPriv());
    }else
    {
    userInfo.setPostPriv(request.getParameter("postPriv"));
    }
    userInfo.setOtherPriv(request.getParameter("otherPriv"));
    userInfo.setHomeAdd(request.getParameter("homeAdd"));
    userInfo.setHomeTel(request.getParameter("homeTel"));
    userInfo.setMobileNo(request.getParameter("mobileNo"));
    userInfo.setQq(request.getParameter("qQ"));
    userInfo.seteMail(request.getParameter("eMaile"));
    userInfo.setWorkId(request.getParameter("workId"));
    userInfo.setManageDept(request.getParameter("manageDept"));
    userInfo.setOtherDept(request.getParameter("otherDpet"));
    userInfo.setLeadLeave(request.getParameter("leadLeave"));
    Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
    userInfo.setOrgId(account.getOrgId());
	UserInfoLogic userInfoLogic = new UserInfoLogic();
	userInfoLogic.addUserInfoLogic(dbConn, userInfo);
	dbConn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally
	{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
	}
	return "/system/unit/userinfo/edituserinfo.jsp?accountId="+accountId;
}
}
