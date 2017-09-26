package tfd.system.unit.userleave.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userleave.data.UserLeave;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

public class UserLeaveAct {
	public void addUserLeaveAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserLeave userLeave = new UserLeave();
			userLeave.setLeaveId(GuId.getGuid());
			userLeave.setLeaveNoId(request.getParameter("leaveNoId"));
			userLeave.setLeaveName(request.getParameter("leaveName"));
			userLeave.setMidding(request.getParameter("midding"));
			userLeave.setOrgId(account.getOrgId());
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			returnData =userLeaveLogic.addUserLeaveLogic(dbConn, userLeave);
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
	
	public void updateUserLeaveAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		int returnData=0;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserLeave userLeave = new UserLeave();
			userLeave.setLeaveId(request.getParameter("leaveId"));
			userLeave.setLeaveNoId(request.getParameter("leaveNoId"));
			userLeave.setLeaveName(request.getParameter("leaveName"));
			userLeave.setMidding(request.getParameter("midding"));
			userLeave.setOrgId(account.getOrgId());
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			returnData =userLeaveLogic.updateUserLeaveLogic(dbConn, userLeave);
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
	
	//获取下拉列表
	public void getUserLeaveSelectAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			returnData = userLeaveLogic.getUserLeaveSelectLogic(dbConn, account.getOrgId());
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
	
	public void getUserLeaveListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String pageStr=request.getParameter("page");
		String pageSizeStr=request.getParameter("pagesize");
		String sortName=request.getParameter("sortname");
		String sortOrder=request.getParameter("sortorder");
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
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			returnData = userLeaveLogic.getUserLeaveListLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
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
	
	//
public void getUserLeaveJosnAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String leaveId = request.getParameter("leaveId");
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		returnData = userLeaveLogic.getUserLeaveJsonLogic(dbConn, leaveId, account.getOrgId());
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
public void getLeaveNameByLeaveIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
	Connection dbConn = null;
	String returnData="";
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String leaveId = request.getParameter("leaveId");
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		returnData = userLeaveLogic.getLeaveNameLogic(dbConn, leaveId, account.getOrgId());
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

public void delByLeaveIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	int returnData=0;
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String leaveId = request.getParameter("leaveId");
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		returnData = userLeaveLogic.delByLeaveIdLogic(dbConn, leaveId);
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
//通过兼职的字符串 获取信息的json数组
public void getjsonleaveAct(HttpServletRequest request,HttpServletResponse response)throws Exception
{
	Connection dbConn = null;
	String returnData=null;
	try
	{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String leaveIdStr = request.getParameter("leaveIdStr");
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		returnData =userLeaveLogic.getjsonleaveLogic(dbConn, leaveIdStr);
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
