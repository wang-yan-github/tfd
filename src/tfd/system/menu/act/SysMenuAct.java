package tfd.system.menu.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.menu.data.SysMenu;
import tfd.system.menu.logic.SysMenuLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.SysTool;

public class SysMenuAct {
	SysMenuLogic logic = new SysMenuLogic();

	public void getSysMeunJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysMenuLogic logic = new SysMenuLogic();
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		String returnData = "";
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = logic.getMeunTreeLogicJson(dbConn, account);
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

	public void getSysShortMeunJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysMenuLogic logic = new SysMenuLogic();
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		String returnData = "";
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = logic.getSysShortMeunJson(dbConn,
					account.getAccountId(), account.getOrgId());
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

	public void updateShortMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sysMenuId = request.getParameter("sysMenuId");
		SysMenuLogic logic = new SysMenuLogic();
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		String accountId = account.getAccountId();
		int returnData = 0;
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = logic.updateShortMenu(dbConn, sysMenuId, accountId);
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

	// 系统所有菜单
	public void getAllMenuInfoAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = null;
			account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			SysMenuLogic logic = new SysMenuLogic();
			returnData = logic.getAllMenuInfoLogic(dbConn, account);
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

	//
	public void getMenuInfoByMenuId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sysMenuId = request.getParameter("sysMenuId");
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SysMenuLogic logic = new SysMenuLogic();
			returnData = logic.getMenuInfoByMenuIdLogicJson(dbConn, sysMenuId);
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

	// 删除菜单
	public void delMenuByMenuIdAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sysMenuId = request.getParameter("sysMenuId");
		Connection dbConn = null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			JSONObject returnData=new JSONObject();
			int childCount=logic.getChildCount(sysMenuId, dbConn);
			returnData.put("childCount", childCount);
			
			returnData.put("result", false);
			if (childCount==0) {
				returnData.put("result", logic.delMenuByMenuLogic(dbConn, sysMenuId)>0);
			}
			
			writer=response.getWriter();
			writer.print(returnData.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 更新菜单信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateMenuByMenuIdAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			SysMenu sysMenu = new SysMenu();
			sysMenu.setSysMenuId(Integer.parseInt(request.getParameter("sysMenuId")));
			sysMenu.setSysMenuName(request.getParameter("sysMenuName"));
			if (!SysTool.isNullorEmpty(request.getParameter("sysMenuLeave"))) {
				sysMenu.setSysMenuLeave(request.getParameter("sysMenuLeave"));
			} else {
				sysMenu.setSysMenuLeave("0");
			}
			sysMenu.setSysMenuCode(request.getParameter("sysMenuCode"));
			sysMenu.setSysMenuPic(request.getParameter("sysMenuPic"));
			sysMenu.setSysMenuUrl(request.getParameter("sysMenuUrl"));
			sysMenu.setSysMenuParm(request.getParameter("sysMenuParm"));
			sysMenu.setSysMenuOpen(request.getParameter("sysMenuOpen"));
			sysMenu.setOrgId(request.getParameter("orgId"));
			
			SysMenuLogic logic = new SysMenuLogic();
			int result=logic.updateMenuByMenuLogic(dbConn, sysMenu);
			
			writer=response.getWriter();
			writer.print(result>0?"OK":"NO");
		} catch (Exception e) {
			throw e;
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 添加菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void addMenuAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
			
			SysMenu sysMenu = new SysMenu();
			sysMenu.setSysMenuCode(request.getParameter("sysMenuCode"));
			sysMenu.setSysMenuPic(request.getParameter("sysMenuPic"));
			sysMenu.setSysMenuUrl(request.getParameter("sysMenuUrl"));
			sysMenu.setSysMenuParm(request.getParameter("sysMenuParm"));
			sysMenu.setSysMenuOpen(request.getParameter("sysMenuOpen"));
			sysMenu.setSysMenuId(logic.maxSysMenuId(dbConn) + 1);
			sysMenu.setSysMenuName(request.getParameter("sysMenuName"));
			sysMenu.setSysMenuLeave(request.getParameter("sysMenuLeave"));
			sysMenu.setOrgId(account.getOrgId());
			
			int result=logic.addMenuLogic(dbConn, sysMenu);
			writer=response.getWriter();
			writer.print(result>0?"OK":"NO");
		} catch (Exception e) {
			throw e;
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			SysTool.closePrintWriter(writer);
		}
	}
}
