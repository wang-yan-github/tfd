package tfd.system.workflow.workflowdatasource.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.workflowdatasource.data.WorkFlowDataSource;
import tfd.system.workflow.workflowdatasource.logic.WorkFlowDataSourceLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class WorkFlowDataSourceAct {
	// 获取下拉数据源列表
	public void getDataSourceListAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			WorkFlowDataSourceLogic workFlowDataSourcelogic = new WorkFlowDataSourceLogic();
			returnData = workFlowDataSourcelogic.getDataSourceListLogic(dbConn,
					account.getOrgId());
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}

	// 添加
	public void addDataSourceAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			WorkFlowDataSource workFlowDataSource = new WorkFlowDataSource();
			workFlowDataSource.setDbSourceId(GuId.getGuid());
			workFlowDataSource.setDbSourceName(request
					.getParameter("dbSourceName"));
			workFlowDataSource.setDbSourceType(request
					.getParameter("dbSourceType"));
			workFlowDataSource.setDbLink(request.getParameter("dbLink"));
			workFlowDataSource.setDbName(request.getParameter("dbName"));
			workFlowDataSource.setDbUserName(request
					.getParameter("dbUserName1"));
			workFlowDataSource.setDbUserPasswd(request
					.getParameter("dbUserPasswd1"));
			workFlowDataSource.setCreateAccountId(account.getAccountId());
			workFlowDataSource.setOrgId(account.getOrgId());
			WorkFlowDataSourceLogic workFlowDataSourcelogic = new WorkFlowDataSourceLogic();
			workFlowDataSourcelogic.addDataSourceLogic(dbConn,
					workFlowDataSource);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("OK");
			response.getWriter().flush();
		}
	}

	// 删除
	public void delDataSourceAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String dbSourceId = request.getParameter("dbSourceId");
			WorkFlowDataSourceLogic workFlowDataSourcelogic = new WorkFlowDataSourceLogic();
			workFlowDataSourcelogic.delDataSourceLogic(dbConn, dbSourceId);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("OK");
			response.getWriter().flush();
		}
	}

	// 更新
	public void uptateDataSourceAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			WorkFlowDataSource workFlowDataSource = new WorkFlowDataSource();
			workFlowDataSource.setDbSourceId(GuId.getGuid());
			workFlowDataSource.setDbSourceName(request
					.getParameter("dbSourceName"));
			workFlowDataSource.setDbSourceType(request
					.getParameter("dbSourceType"));
			workFlowDataSource.setDbLink(request.getParameter("dbLink"));
			workFlowDataSource.setDbName(request.getParameter("dbName"));
			workFlowDataSource
					.setDbUserName(request.getParameter("dbUserName"));
			workFlowDataSource.setDbUserPasswd(request
					.getParameter("dbUesrPasswd"));
			workFlowDataSource.setCreateAccountId(account.getAccountId());
			workFlowDataSource.setOrgId(account.getOrgId());
			WorkFlowDataSourceLogic workFlowDataSourcelogic = new WorkFlowDataSourceLogic();
			workFlowDataSourcelogic.updateDataSourceLogic(dbConn,
					workFlowDataSource);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("Ok");
			response.getWriter().flush();
		}
	}

	public void getDataSourceListJsonAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		int page = 1;
		int pageSize = 30;
		if (!SysTool.isNullorEmpty(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		if (!SysTool.isNullorEmpty(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			WorkFlowDataSourceLogic workFlowDataSourcelogic = new WorkFlowDataSourceLogic();
			returnData = workFlowDataSourcelogic.getDataSourceListJsonLogic(
					dbConn, pramList, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}

	}
}
