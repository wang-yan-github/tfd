package tfd.system.module.selectuser.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.unit.account.data.Account;
import tfd.system.module.selectuser.logic.SelectUserLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class SelectUserAct {
	SelectUserLogic logic = new SelectUserLogic();
	public void getDeptUserJsonAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 按部门获取人员
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		String deptId = request.getParameter("deptId");
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		SelectUserLogic selectUserLogic = new SelectUserLogic();
		String returnData = selectUserLogic.getSelectUserJsonLogic(dbConn,
				deptId, account);
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}

	public void searchNameUserAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String searchName = request.getParameter("searchName");
			
			String data = logic.searchNameUserLogic(dbConn, searchName,account);
			
			writer=response.getWriter();
			writer.print(data);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, dbConn);
		}
	}
	public void searchUserOfWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String searchStr = request.getParameter("searchStr");
			String flowId = request.getParameter("flowId");
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			
			JSONArray userList = logic.getWorkFlowUserJsonLogic(dbConn,null, account, flowId, prcsId,searchStr);
			
			writer=response.getWriter();
			writer.print(userList.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, dbConn);
		}
	}

	public void getWorkFlowUserJsonAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String deptId = request.getParameter("deptId");
			String flowId = request.getParameter("flowId");
			int prcsId = Integer.parseInt(request.getParameter("prcsId"));
			
			JSONArray userList = logic.getWorkFlowUserJsonLogic(dbConn,deptId, account, flowId, prcsId,null);
			
			writer=response.getWriter();
			writer.print(userList.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, dbConn);
		}
		
	}

}
