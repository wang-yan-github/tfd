package tfd.system.unit.org.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.BaseDao;
import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.org.data.Unit;
import tfd.system.unit.org.logic.UnitLogic;

public class UnitAct {
	public String saveUnitInfoAct(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		Connection dbConn = null;
		try {
			Unit unit = new Unit();
			UnitLogic unitLogic = new UnitLogic();
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			unit.setOrgId(account.getOrgId());
			unit.setOrgName(request.getParameter("orgName"));
			unit.setOrgTel(request.getParameter("orgTel"));
			unit.setOrgFax(request.getParameter("orgFax"));
			unit.setOrgAdd(request.getParameter("orgAdd"));
			unit.setOrgPost(request.getParameter("orgPost"));
			unit.setOrgEmail(request.getParameter("orgEmail"));
			unitLogic.saveUnitInfo(dbConn, unit);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
		return "/system/unit/org/index.jsp";
	}

	public void getUnitInfoAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			UnitLogic unitLogic = new UnitLogic();
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = unitLogic.getUnitInfo(dbConn, orgId);
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

	// BY orgconfigid 获取
	public void getOrgConfigByIdAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			String orgId = request.getParameter("orgId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			UnitLogic unitLogic = new UnitLogic();
			returnData = unitLogic.getOrgConfigByIdLogic(dbConn, orgId);
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

	public void addOrgConfigAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		int returnData = 0;
		Connection dbConn = null;
		try {
			if (account.getAccountId().equals("admin")) {
				String guId = GuId.getGuid();
				String orgName = request.getParameter("orgName");
				String orgAdmin = request.getParameter("orgAdmin");
				String userPriv = request.getParameter("userPriv");
				dbConn = DbPoolConnection.getInstance().getConnection();
				UnitLogic unitLogic = new UnitLogic();
				returnData = unitLogic.addOrgConfigLogic(dbConn, guId, orgName,
						orgAdmin, userPriv);
			}
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

	public void getOrgConfigJsonAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			UnitLogic unitLogic = new UnitLogic();
			if (account.getAccountId().equals("admin")) {
				returnData = unitLogic.getOrgConfigJsonLogic(dbConn);
			} else {
				returnData = "[]";
			}
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

	public void delOrgConfigAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int returnData = 0;
		Connection dbConn = null;
		try {
			String orgId = request.getParameter("orgId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			UnitLogic unitLogic = new UnitLogic();
			returnData = unitLogic.delOrgConfigLogic(dbConn, orgId);
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

	public void updateOrgConfigAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String orgAdmin = request.getParameter("orgAdmin");
		Connection dbConn = null;
		int returnData = 0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			UnitLogic unitLogic = new UnitLogic();
			returnData = unitLogic.updateOrgConfigLogic(dbConn, orgId, orgName,
					orgAdmin);
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
	
	public void unitExistOfOrgName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		BaseDao dao=new BaseDaoImpl();
		UnitLogic logic=new UnitLogic();
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String orgName = request.getParameter("orgName");
			Unit unit=logic.getUnitByOrgName(orgName, conn);
			writer=response.getWriter();
			writer.print(unit!=null);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	/**
	 * 验证orgName 是否已存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkorgAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		int data=0;
		UnitLogic logic=new UnitLogic();
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String orgName=request.getParameter("orgName");
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data=logic.checkorgLogic(dbConn, orgName,account.getOrgId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
	}
	/**
	 * 注册的情况下使用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkorgNameAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		int data=0;
		UnitLogic logic=new UnitLogic();
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String orgName=request.getParameter("orgName");
			data=logic.checkorgNameLogic(dbConn, orgName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
	}
	/**
	 * 检查org_config 中orgName是否已存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkorgConfigAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		int data=0;
		UnitLogic logic=new UnitLogic();
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String orgName=request.getParameter("orgName");
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data=logic.checkorgConfigLogic(dbConn, orgName,account.getOrgId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
	}
}
