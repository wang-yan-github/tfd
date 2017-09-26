package tfd.system.module.selectdept.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.unit.account.data.Account;
import tfd.system.module.selectdept.logic.SelectDeptLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class SelectDeptAct {
	SelectDeptLogic logic = new SelectDeptLogic();
	
	public void getDeptTreeOfWorkflowPriv(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			String flowId=request.getParameter("flowId");
			int prcsId=Integer.parseInt(request.getParameter("prcsId"));
			String deptParent=request.getParameter("id")==null?"0":request.getParameter("id");

			JSONArray tree=logic.getDeptTreeOfWorkflowPriv(account, deptParent, flowId, prcsId, conn);
			
			writer=response.getWriter();
			writer.print(tree.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
	// 获取顶级部门
	public void getDeptTopJsonAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String returnData = logic.getDeptTopJsonLogic(dbConn, account);
			
			writer=response.getWriter();
			writer.print(returnData);
		} catch (Exception e) {
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, dbConn);
		}
	}

	// 获取子部门
	public void getDeptChildJsonAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String deptId = request.getParameter("id");
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		SelectDeptLogic selectDeptLogic = new SelectDeptLogic();
		String returnData = selectDeptLogic.getDeptChildJsonLogic(dbConn,
				account, deptId);
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}

	public void serachdeptAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String deptName = request.getParameter("deptName");
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		SelectDeptLogic selectDeptLogic = new SelectDeptLogic();
		String returnData = selectDeptLogic.serachdeptLogic(dbConn, account,
				deptName);
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}

}
