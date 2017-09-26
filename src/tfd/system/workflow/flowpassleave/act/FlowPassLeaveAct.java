package tfd.system.workflow.flowpassleave.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowpassleave.data.FlowPassLeave;
import tfd.system.workflow.flowpassleave.logic.FlowPassLeaveLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class FlowPassLeaveAct {
	//添加
	public void addFlowPassLeaveAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Connection dbConn = null;
		int returnData=0;
		String passLeaveId=request.getParameter("passLeaveId");
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		FlowPassLeave flowPassLeave = new FlowPassLeave();
		String flowPassLeaveId = GuId.getGuid();
		flowPassLeave.setFlowPassLeaveId(flowPassLeaveId);
		flowPassLeave.setPassLeaveId(passLeaveId);
		flowPassLeave.setPassLeaveName(request.getParameter("passLeaveName"));
		flowPassLeave.setPassLeaveNext(request.getParameter("passLeaveNext"));
		flowPassLeave.setFlowId(request.getParameter("flowId"));
		flowPassLeave.setOrgId(account.getOrgId());
		FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
		returnData=flowPassLeaveLogic.addFlowPassLeaveLogic(dbConn, flowPassLeave);
		dbConn.commit();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	//获取行政级别信息
	public void getFlowPassLeaveAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		String flowPassLeaveId=request.getParameter("flowPassLeaveId");
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
			returnData=flowPassLeaveLogic.getFlowPassLeaveLogic(dbConn, flowPassLeaveId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	public void getFlowPassLeaveListAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId = request.getParameter("flowId");
		String returnData="";
		Connection dbConn = null;
		try{
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
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(flowId);
			pramList.add(account.getOrgId());
			FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
			returnData = flowPassLeaveLogic.getFlowPassLeaveListLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	//获取下拉列表JSON
	public void getFlowPassLeaveSeltctAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId =request.getParameter("flowId");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
			returnData = flowPassLeaveLogic.getFlowPassLeaveSelectLogic(dbConn, flowId,account.getOrgId());
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	public void getPassLeaveSeltctByFlowIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId = request.getParameter("flowId");
		Connection dbConn = null;
		String returnData ="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
			returnData = flowPassLeaveLogic.getFlowPassLeaveSelectByFlowIdLogic(dbConn, account.getOrgId(),flowId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public void updatePassLeaveAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Connection dbConn = null;
		int returnData=0;
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		FlowPassLeave flowPassLeave = new FlowPassLeave();
		String passLeaveId=request.getParameter("passLeaveId");
		String flowPassLeaveId = request.getParameter("flowPassLeaveId");
		flowPassLeave.setFlowPassLeaveId(flowPassLeaveId);
		flowPassLeave.setPassLeaveId(passLeaveId);
		flowPassLeave.setPassLeaveName(request.getParameter("passLeaveName"));
		flowPassLeave.setPassLeaveNext(request.getParameter("passLeaveNext"));
		flowPassLeave.setFlowId(request.getParameter("flowId"));
		flowPassLeave.setOrgId(account.getOrgId());
		FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
		returnData=flowPassLeaveLogic.updateFlowPassLeaveLogic(dbConn, flowPassLeave);
		dbConn.commit();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public void delPassLeaveAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowPassleaveId= request.getParameter("flowPassleaveId");
		Connection dbConn = null;
		int returnData=0;
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
		returnData=flowPassLeaveLogic.delFlowPassLeaveLogic(dbConn, flowPassleaveId);
		dbConn.commit();
		}catch (Exception ex)
		{
			ex.printStackTrace();
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
