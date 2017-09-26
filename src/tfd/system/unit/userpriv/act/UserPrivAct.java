package tfd.system.unit.userpriv.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.menu.logic.SysMenuLogic;
import tfd.system.unit.userpriv.data.UserPriv;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class UserPrivAct {
	public void getAllUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
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
		UserPrivLogic userPrivLogic = new UserPrivLogic();
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account =  (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			returnData=userPrivLogic.getAllUserPirvLogic(dbConn,pramList,pageSize,page,sortOrder,sortName);
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
	//按用户权限USERPRIVID获取菜单权限
	public void getMenuPrivByIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String userPrivId=request.getParameter("userPrivId");
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			String userPrivStr=userPrivLogic.getUserPrivStr(dbConn, userPrivId);
			SysMenuLogic logic = new SysMenuLogic();
			returnData=logic.getMenuByUserPirvStr(dbConn, userPrivStr);
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
	//添加用户组权限
	public void addUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		UserPriv userPriv = new UserPriv();
		String userPrivId = GuId.getGuid();
		String userPrivLeave = request.getParameter("userPrivLeave");
		userPriv.setUserPrivId(userPrivId);
		userPriv.setUserPrivLeave(userPrivLeave);
		userPriv.setUserPrivName(request.getParameter("userPrivName"));
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		userPriv.setOrgId(account.getOrgId());
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			returnData=userPrivLogic.addUserPrivLogic(dbConn, userPriv);
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
	//获取用户权限通过Id
	public void getUserPrivByUserPrivIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String userPrivId=request.getParameter("userPrivId");
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			returnData = userPrivLogic.getUserPrivByUserPrivIdLogic(dbConn, userPrivId);
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
	//删除
	public void delByUserPrivIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String userPrivId=request.getParameter("userPrivId");
		Connection dbConn = null;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			userPrivLogic.delByUserPrivIdLogic(dbConn, userPrivId);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("OK");
			response.getWriter().flush();
		}
	}
	//复制权限组
	public void copyUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String userPrivId=request.getParameter("userPrivId");
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			returnData = userPrivLogic.copyUserPrivLogic(dbConn, userPrivId);
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
	//更新用户菜单权限
	public void updateMenuPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String userPrivId = request.getParameter("userPrivId");
		String userPrivStr = request.getParameter("userPrivStr");
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			returnData=userPrivLogic.updateUserPriByIdLogic(dbConn,userPrivId,userPrivStr);
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
	//更新
	public void updateUserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		UserPriv userPriv = new UserPriv();
		String userPrivId = request.getParameter("userPrivId");
		String userPrivLeave = request.getParameter("userPrivLeave");
		String userPrivName = request.getParameter("userPrivName");
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String orgId = account.getOrgId();
		userPriv.setUserPrivId(userPrivId);
		userPriv.setUserPrivLeave(userPrivLeave);
		userPriv.setUserPrivName(userPrivName);
		userPriv.setOrgId(orgId);
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			returnData = userPrivLogic.updateUserPrivLogic(dbConn, userPriv);
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
	//根据角色字符串获取角色的json数组
	public void getjsonuserPrivAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserPrivLogic userPrivLogic = new UserPrivLogic();
			String userprivStr=request.getParameter("userprivStr");
			returnData=userPrivLogic.getjsonuserPrivLogic(dbConn, userprivStr);
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
