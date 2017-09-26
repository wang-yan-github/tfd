package tfd.system.workflow.flowformitem.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;

public class FlowFormItemAct {
	public void getAllFlowFormItemByFlowId(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		int prcsId=Integer.parseInt(request.getParameter("prcsId"));
		Connection dbConn=null;
		String returnData ="";
		try{
			dbConn= DbPoolConnection.getInstance().getConnection();
			FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
			returnData = flowFormItemLogic.getAllFlowProcessFieldLogic(dbConn, flowId,prcsId);
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
	public void getTitleAndFieldNameByFlowIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
			returnData = flowFormItemLogic.getTitleAndFieldNameByFlowIdJson(dbConn, flowId);
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
	//获取所有XLIST控件
	public void getAllXlistAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		Connection dbConn =null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
			returnData = flowFormItemLogic.getAllXlistJsonLogic(dbConn, flowId);
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
	//获取指定XLIST控件
	public void getXlistAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		String childTable=request.getParameter("childTable");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
			returnData = flowFormItemLogic.getXlistJsonLogic(dbConn, flowId,childTable);
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
	//获取流程表单所有字段
	public void getAllFieldAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		String returnData="";
		Connection dbConn = null;
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
		returnData = flowFormItemLogic.getAllFieldByFlowIdLogic(dbConn, flowId);
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
	
	//获取所有的字段
	public void getFieldByFormIdListAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowId=request.getParameter("flowId");
		String prcsId = request.getParameter("prcsId");
		Connection dbConn = null;
		String returnData="";
			try{
				dbConn=DbPoolConnection.getInstance().getConnection();
				FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
				returnData = flowFormItemLogic.getFieldByFormIdJsonLogic(dbConn, flowId,prcsId);
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
	
	public void getValidateByFormIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String formId =request.getParameter("formId");
		Connection dbConn = null;
		String returnData="";
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
			returnData = flowFormItemLogic.getValidateByFormIdLogic(dbConn, formId);
			dbConn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
