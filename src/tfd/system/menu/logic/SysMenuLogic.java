package tfd.system.menu.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.menu.data.SysMenu;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

public class SysMenuLogic {
	public BaseDao dao=new  BaseDaoImpl();
	public String getMeunTreeLogicJson(Connection conn, Account account)
			throws Exception {
		ResultSet rs = null;
		JSONArray jsonArr = new JSONArray();
		UserPrivLogic userPrivLogic = new UserPrivLogic();
		String userPrivStr = userPrivLogic.getUserPrivStr(conn,
				account.getUserPriv());
		String queryStr = "SELECT ID,SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,"
				+ "SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC FROM SYS_MENU WHERE SYS_MENU_ID IN ("
				+ userPrivStr + ") ORDER BY ID ASC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("SYS_MENU_ID"));
			json.accumulate("pId", rs.getString("SYS_MENU_LEAVE"));
			json.accumulate("name", rs.getString("SYS_MENU_NAME"));
			json.accumulate("urls", rs.getString("SYS_MENU_URL"));
			json.accumulate(
					"icon",
					"/tfd/system/styles/images/menu/"
							+ rs.getString("SYS_MENU_PIC"));
			if (SysTool.isNullorEmpty(rs.getString("SYS_MENU_URL"))) {
				json.accumulate("isParent", "true");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	public String getSysShortMeunJson(Connection conn, String accountId,
			String orgId) throws Exception {
		ResultSet rs = null;
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT ID,SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,"
				+ "SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC FROM SYS_SHORT_MENU WHERE ACCOUNT_ID = '"
				+ accountId + "' ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("SYS_MENU_ID"));
			json.accumulate("pId", rs.getString("SYS_MENU_LEAVE"));
			json.accumulate("name", rs.getString("SYS_MENU_NAME"));
			json.accumulate("urls", rs.getString("SYS_MENU_URL"));
			json.accumulate(
					"icon",
					"/tfd/system/styles/images/menu/"
							+ rs.getString("SYS_MENU_PIC"));
			if (SysTool.isNullorEmpty(rs.getString("SYS_MENU_URL"))) {
				json.accumulate("isParent", "true");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	/**
	 * 修改快捷菜单 Author:2015-6-5 Time:Yzz
	 * 
	 * @param conn
	 * @param menuId
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public int updateShortMenu(Connection conn, String menuId, String accountId)
			throws Exception {
		deleteShortMenuById(conn, accountId);
		String[] menuIds = menuId.split(",");
		int returnData = 0;
		for (int i = 0; i < menuIds.length; i++) {
			returnData = setShortMenuLogic(conn, menuIds[i], accountId);
		}
		return returnData;
	}

	/**
	 * 删除用户所有的快捷菜单 Author:Yzz Time:2015-6-5
	 * 
	 * @param conn
	 * @param accountId
	 * @throws Exception
	 */
	public void deleteShortMenuById(Connection conn, String accountId)
			throws Exception {
		String queryStr = "DELETE FROM SYS_SHORT_MENU WHERE ACCOUNT_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 添加快捷菜单 Author:Yzz Time:2015-6-5
	 * 
	 * @param conn
	 * @param menuId
	 * @param accountId
	 * @throws Exception
	 */
	public int setShortMenuLogic(Connection conn, String menuId,
			String accountId) throws Exception {
		String queryStr = "INSERT INTO SYS_SHORT_MENU(SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC,ACCOUNT_ID,ORG_ID) SELECT t1.SYS_MENU_ID,t1.SYS_MENU_NAME,t1.SYS_MENU_URL,t1.SYS_MENU_CODE,t1.SYS_MENU_PARM,t1.SYS_MENU_OPEN,t1.SYS_MENU_LEAVE,t1.SYS_MENU_PIC,'"
				+ accountId
				+ "',t1.ORG_ID FROM SYS_MENU t1 WHERE t1.SYS_MENU_ID = '"
				+ menuId + "' ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	// ADMIN获取所有菜单
	public String getAllMenuInfoLogic(Connection conn, Account account)
			throws SQLException {
		ResultSet rs = null;
		JSONArray jsonArr = new JSONArray();
		String queryStr = "";
		PreparedStatement ps = null;
		if (account.getAccountId().equals("admin")) {
			queryStr = "SELECT ID,SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,"
					+ "SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC FROM SYS_MENU";
			ps = conn.prepareStatement(queryStr);
		} else {
			queryStr = "SELECT ID,SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,"
					+ "SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC FROM SYS_MENU WHERE ORG_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, account.getOrgId());
		}
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("SYS_MENU_ID"));
			json.accumulate("pId", rs.getString("SYS_MENU_LEAVE"));
			json.accumulate("name", rs.getString("SYS_MENU_NAME"));
			json.accumulate("urls", rs.getString("SYS_MENU_URL"));
			json.accumulate(
					"icon",
					"/tfd/system/styles/images/menu/"
							+ rs.getString("SYS_MENU_PIC"));
			if (SysTool.isNullorEmpty(rs.getString("SYS_MENU_URL"))) {
				json.accumulate("isParent", "true");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	// 获取菜单信息
	public String getMenuInfoByMenuIdLogicJson(Connection conn, String sysMenuId)
			throws SQLException {
		ResultSet rs = null;
		JSONObject json = new JSONObject();
		String queryStr = "SELECT SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,SYS_MENU_OPEN,SYS_MENU_PARM,SYS_MENU_LEAVE,SYS_MENU_PIC,ORG_ID FROM SYS_MENU WHERE SYS_MENU_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, sysMenuId);
		rs = ps.executeQuery();
		while (rs.next()) {
			json.accumulate("sysMenuId", rs.getString("SYS_MENU_ID"));
			json.accumulate("sysMenuName", rs.getString("SYS_MENU_NAME"));
			json.accumulate("sysMenuUrl", rs.getString("SYS_MENU_URL"));
			json.accumulate("sysMenuCode", rs.getString("SYS_MENU_CODE"));
			json.accumulate("sysMenuOpen", rs.getString("SYS_MENU_OPEN"));
			json.accumulate("sysMenuLeave", rs.getString("SYS_MENU_LEAVE"));
			json.accumulate("sysMenuParm", rs.getString("SYS_MENU_PARM"));
			json.accumulate(
					"sysMenuLeaveName",
					getSysMenuNameByIdLogic(conn,
							rs.getString("SYS_MENU_LEAVE")));
			json.accumulate("sysMenuPic", rs.getString("SYS_MENU_PIC"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	// 根据UserprivStr获取菜单
	public String getMenuByUserPirvStr(Connection conn, String userPrivStr)
			throws SQLException {
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		String queryStr = "SELECT ID,SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,SYS_MENU_CODE,"
				+ "SYS_MENU_PARM,SYS_MENU_OPEN,SYS_MENU_LEAVE,SYS_MENU_PIC FROM SYS_MENU";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		userPrivStr = "," + userPrivStr + ",";
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("SYS_MENU_ID"));
			json.accumulate("pId", rs.getString("SYS_MENU_LEAVE"));
			json.accumulate("name", rs.getString("SYS_MENU_NAME"));
			json.accumulate("urls", rs.getString("SYS_MENU_URL"));
			json.accumulate(
					"icon",
					"/tfd/system/styles/images/menu/"
							+ rs.getString("SYS_MENU_PIC"));
			if (SysTool.isNullorEmpty(rs.getString("SYS_MENU_URL"))) {
				json.accumulate("isParent", "true");
			}
			if (userPrivStr.indexOf("," + rs.getString("SYS_MENU_ID") + ",") >= 0) {
				json.accumulate("checked", "true");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	// 通过ID获取菜单名称
	public String getSysMenuNameByIdLogic(Connection conn, String sysMenuId)
			throws SQLException {
		String returnData = "";
		String queryStr = "SELECT SYS_MENU_NAME FROM SYS_MENU WHERE SYS_MENU_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, sysMenuId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			returnData = rs.getString("SYS_MENU_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	/**
	 * 删除菜单
	 * @param conn
	 * @param sysMenuId
	 * @return
	 * @throws Exception
	 */
	public int delMenuByMenuLogic(Connection conn, String sysMenuId)
			throws Exception {
		PreparedStatement ps=null;
		try {
			String sql = "DELETE FROM SYS_MENU WHERE SYS_MENU_ID=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sysMenuId);
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
	}
	
	/**
	 * 获取菜单第一层子菜单个数
	 * @param sysMenuId
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getChildCount(String sysMenuId,Connection conn) throws Exception{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql = "SELECT COUNT(*) FROM SYS_MENU WHERE SYS_MENU_LEAVE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sysMenuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return 0;
	}

	/**
	 * 更新菜单
	 * @param conn
	 * @param sysMenu
	 * @throws Exception
	 */
	public int updateMenuByMenuLogic(Connection conn, SysMenu sysMenu) throws Exception {
		PreparedStatement ps =null;
		try {
			
			String queryStr = "UPDATE SYS_MENU SET SYS_MENU_NAME=?,SYS_MENU_LEAVE=?,SYS_MENU_CODE=?,SYS_MENU_OPEN=?,SYS_MENU_PARM=?,SYS_MENU_PIC=?,SYS_MENU_URL=? WHERE SYS_MENU_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, sysMenu.getSysMenuName());
			ps.setString(2, sysMenu.getSysMenuLeave());
			ps.setString(3, sysMenu.getSysMenuCode());
			ps.setString(4, sysMenu.getSysMenuOpen());
			ps.setString(5, sysMenu.getSysMenuParm());
			ps.setString(6, sysMenu.getSysMenuPic());
			ps.setString(7, sysMenu.getSysMenuUrl());
			ps.setInt(8, sysMenu.getSysMenuId());
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
	}

	/**
	 * 添加新菜单
	 * @param conn
	 * @param sysMenu
	 * @throws SQLException
	 */
	public int addMenuLogic(Connection conn, SysMenu sysMenu)throws Exception {
		PreparedStatement ps =null;
		try {
			String queryStr = "INSERT INTO SYS_MENU ("
					+ "							SYS_MENU_ID,SYS_MENU_NAME,SYS_MENU_URL,"
					+ "							SYS_MENU_LEAVE,SYS_MENU_CODE,SYS_MENU_OPEN,"
					+ "							SYS_MENU_PARM,SYS_MENU_PIC,ORG_ID"
					+ "						) VALUES(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(queryStr);
			ps.setInt(1, sysMenu.getSysMenuId());
			ps.setString(2, sysMenu.getSysMenuName());
			ps.setString(3, sysMenu.getSysMenuUrl());
			ps.setString(4, sysMenu.getSysMenuLeave());
			ps.setString(5, sysMenu.getSysMenuCode());
			ps.setString(6, sysMenu.getSysMenuOpen());
			ps.setString(7, sysMenu.getSysMenuParm());
			ps.setString(8, sysMenu.getSysMenuPic());
			ps.setString(9, sysMenu.getOrgId());
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
	}
	public int maxSysMenuId(Connection conn) throws Exception {
		PreparedStatement ps =null;
		ResultSet rs =null;
		try {
			ps = conn.prepareStatement("select max(sys_menu_id) from sys_menu");
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return 0;
	}
}
