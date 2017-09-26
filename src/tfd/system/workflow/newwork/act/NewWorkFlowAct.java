package tfd.system.workflow.newwork.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowutility.UitilityTool;
import tfd.system.workflow.newwork.logic.NewWorkFlowLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import net.sf.json.JSONObject;
public class NewWorkFlowAct {
	public void getAllWorkAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			NewWorkFlowLogic logic = new NewWorkFlowLogic();
			returnData=logic.getAllWorkLogic(dbConn, account);
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
	
	public void getNewTreeAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			NewWorkFlowLogic logic = new NewWorkFlowLogic();
			returnData=logic.getNewWorkJson(dbConn, account);
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
	//创建工作流
	public void createWorkAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String returnData="";
		Connection dbConn =null;
		try{
			String flowId=request.getParameter("flowId");
			String title=request.getParameter("title");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn=DbPoolConnection.getInstance().getConnection();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			WorkFlow workFlow = new WorkFlow();
			workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
			UitilityTool  uitilityTool = new UitilityTool();
			if(SysTool.isNullorEmpty(title))
			{
				title=uitilityTool.createCode(dbConn,workFlow.getAutoCode(), flowId, "", account);
			}
			NewWorkFlowLogic newWorkFlowLogic = new NewWorkFlowLogic();
			returnData=newWorkFlowLogic.createWorkLogic(dbConn, account, title, flowId);
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
//生成工作流名称
	public void createWorkFlowTitleAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String flowTypeId=request.getParameter("flowTypeId");
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = null;
		String returnData="";
		JSONObject json = new JSONObject();
		try{
		dbConn=DbPoolConnection.getInstance().getConnection();
		UitilityTool  uitilityTool = new UitilityTool();
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		WorkFlow workFlow = new WorkFlow();
		workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowTypeId);
		json.accumulate("flowName", workFlow.getFlowName());
		returnData=uitilityTool.createCode(dbConn,workFlow.getAutoCode(), flowTypeId, "", account);
		json.accumulate("flowCode", returnData);
		dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json);
			response.getWriter().flush();
		}
		
	}
}
