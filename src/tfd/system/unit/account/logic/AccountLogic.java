package tfd.system.unit.account.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import tfd.system.module.PrivConst;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

public class AccountLogic {
	public BaseDao dao=new BaseDaoImpl();
	public Account getAccountByAccountId(Connection conn, String accountId)
			throws SQLException {
		Account account = new Account();
		String querStr = "SELECT ACCOUNT_ID,PASSWORD_TYPE,THMEM,USER_ID,USER_PRIV,PAGE_PRIV,NOT_LOGIN,"
				+ " ON_LINE,LAST_VISIT_TIME,LANGUAGE,BY_NAME,ORG_ID FROM ACCOUNT WHERE ACCOUNT_ID='"
				+ accountId + "'";
		PreparedStatement ps = conn.prepareStatement(querStr);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			account.setAccountId(rs.getString("ACCOUNT_ID"));
			account.setPasswordType(rs.getString("PASSWORD_TYPE"));
			account.setTheme(rs.getString("THMEM"));
			account.setUserId(rs.getString("USER_ID"));
			account.setUserPriv(rs.getString("USER_PRIV"));
			account.setPagePriv(rs.getString("PAGE_PRIV"));
			account.setNotLogin(rs.getString("NOT_LOGIN"));
			account.setOnLine(rs.getLong("ON_LINE"));
			account.setLastVisitTime(rs.getDate("LAST_VISIT_TIME"));
			account.setLanguage(rs.getString("LANGUAGE"));
			account.setByName(rs.getString("BY_NAME"));
			account.setOrgId(rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return account;
	}

	// 添加账号
	public int addAccountLogic(Connection conn, Account account)
			throws Exception {
		PreparedStatement stmt=null;
		try {
			String queryStr = "INSERT INTO ACCOUNT(ACCOUNT_ID,PASSWORD_TYPE,PASS_WORD,THMEM,USER_ID,USER_PRIV,PAGE_PRIV,NOT_LOGIN,"
					+ "LANGUAGE,BY_NAME,ORG_ID,APP_ICON)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, account.getAccountId());
			stmt.setString(2, account.getPasswordType());
			stmt.setString(3, account.getPassWord());
			stmt.setString(4, account.getTheme());
			stmt.setString(5, account.getUserId());
			stmt.setString(6, account.getUserPriv());
			stmt.setString(7, account.getPagePriv());
			stmt.setString(8, account.getNotLogin());
			stmt.setString(9, account.getLanguage());
			stmt.setString(10, account.getByName());
			stmt.setString(11, account.getOrgId());
			stmt.setString(12, account.getAppIcon());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}

	// 获取维护账号列表
	public String getMenageAccountListLogic(Connection conn,
			List<String> pramList, int pageSize, int page, String sortOrder,
			String sortName) throws Exception {
		String selectStr = "SELECT DISTINCT A.ID AS ID,A.ACCOUNT_ID AS ACCOUNT_ID,A.PASSWORD_TYPE AS PASSWORD_TYPE,B.USER_PRIV_NAME AS USER_PRIV_NAME,A.LANGUAGE AS LANGUAGE FROM ACCOUNT A, USER_PRIV B ";
		String whereStr = " WHERE A.USER_PRIV=B.USER_PRIV_ID AND A.ORG_ID=? ";
		if (!SysTool.isNullorEmpty(pramList.get(1))) {
			whereStr += " AND A.ACCOUNT_ID='" + pramList.get(1) + "' ";
		}
		if (!SysTool.isNullorEmpty(pramList.get(2))) {
			whereStr += " AND A.NOT_LOGIN='" + pramList.get(2) + "' ";
		}
		if (!SysTool.isNullorEmpty(pramList.get(3))) {
			whereStr += " AND A.ACCOUNT_ID IN ( SELECT ACCOUNT_ID FROM USER_INFO WHERE USER_NAME='"
					+ pramList.get(3) + "')";
		}
		if (!SysTool.isNullorEmpty(pramList.get(4))) {
			whereStr += " AND A.ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM USER_INFO WHERE DEPT_ID='"
					+ pramList.get(4) + "') ";
		}
		String queryStr = selectStr + whereStr;
		String optStr = "[{'function':'edit','name':'修改','parm':'ACCOUNT_ID'},{'function':'read','name':'查看','parm':'ACCOUNT_ID'},{'function':'delAccount','name':'注销','parm':'ACCOUNT_ID'}]";
		if (pramList.get(2).equals("1")) {
			optStr = "[{'function':'edit','name':'修改','parm':'ACCOUNT_ID'},{'function':'read','name':'查看','parm':'ACCOUNT_ID'},{'function':'setUser','name':'绑定用户','parm':'ACCOUNT_ID'}]";
		}
		new JSONArray();
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList1 = new ArrayList<String>();
		pramList1.add(pramList.get(0));
		JSONObject json = pageTool.getPageData(conn, queryStr, pramList1,
				pageSize, page, optArrJson, sortOrder, sortName);
		return json.toString();
	}

	public String getAccountJsonByIdLogic(Connection conn, String accountId)
			throws SQLException {
		JSONObject json = new JSONObject();
		UserPrivLogic userPrivLogic = new UserPrivLogic();
		String queryStr = "SELECT ID,ACCOUNT_ID,PASS_WORD,PASSWORD_TYPE,THMEM,USER_ID,USER_PRIV,PAGE_PRIV,NOT_LOGIN,ON_LINE,LAST_VISIT_TIME,LANGUAGE,BY_NAME,ORG_ID FROM ACCOUNT WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("passWord", rs.getString("PASS_WORD"));
			json.accumulate("passwordType", rs.getString("PASSWORD_TYPE"));
			json.accumulate("thmem", rs.getString("THMEM"));
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			json.accumulate(
					"userPrivName",
					userPrivLogic.getUserPrivNameById(conn,
							rs.getString("USER_PRIV")));
			json.accumulate("pagePriv", rs.getString("PAGE_PRIV"));
			json.accumulate("notLogin", rs.getString("NOT_LOGIN"));
			if (rs.getString("NOT_LOGIN").equals("0")) {
				json.accumulate("notLoginName", "允许");
			} else {
				json.accumulate("notLoginName", "禁止");
			}
			json.accumulate("onLine", rs.getString("ON_LINE"));
			json.accumulate("lastVisitTime", rs.getString("LAST_VISIT_TIME"));
			json.accumulate("language", rs.getString("LANGUAGE"));
			json.accumulate("byName", rs.getString("BY_NAME"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	// 更新账号相关信息
	public int uptateAccountLogic(Connection conn, Account account)
			throws SQLException {
		String queryStr = "UPDATE ACCOUNT SET PASS_WORD=?,PASSWORD_TYPE=?,THMEM=?,USER_PRIV=?,PAGE_PRIV=?,NOT_LOGIN=?,LANGUAGE=?,BY_NAME=?,ORG_ID=? WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getPassWord());
		ps.setString(2, account.getPasswordType());
		ps.setString(3, account.getTheme());
		ps.setString(4, account.getUserPriv());
		ps.setString(5, account.getPagePriv());
		ps.setString(6, account.getNotLogin());
		ps.setString(7, account.getLanguage());
		ps.setString(8, account.getByName());
		ps.setString(9, account.getOrgId());
		ps.setString(10, account.getAccountId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	// 删除账号
	public int delAccountIdByIdLogic(Connection conn, String accountId,String orgId)
			throws SQLException {
		int returnData = 0;
			String queryStr = "UPDATE ACCOUNT SET NOT_LOGIN =1  WHERE ACCOUNT_ID=? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, accountId);
			ps.setString(2, orgId);
			returnData = ps.executeUpdate();
			ps.close();
		return returnData;
	}

	// 判断账号是否与用户关联
	public boolean checkUserByAccountIdLogic(Connection conn, String accountId)
			throws SQLException {
		boolean flag = false;
		String queryStr = "SELECT COUNT(*) AS COUNT FROM USER_INFO WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt("COUNT") > 0) {
				flag = true;
			}
		}
		return flag;
	}

	// 更改在职状态
	public int updateNotLoginLogic(Connection conn, String accountId)
			throws Exception {
		PreparedStatement stmt =null;
		try {
			String queryStr = "UPDATE ACCOUNT SET NOT_LOGIN='0' WHERE ACCOUNT_ID=?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, accountId);
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}

	// 通过accountId字符串获取人员姓名
	public String getUserNameStr(Connection conn, String accountIdStr)throws SQLException {
		String dbType = DbPoolConnection.getInstance().getDbType();
		String returnData = "";
		String inAccountIdStr = "";
		if (!SysTool.isNullorEmpty(accountIdStr)) {
			if (accountIdStr.indexOf(",") > 0) {
				String[] accounts = accountIdStr.split(",");
				for (int i = 0; accounts.length > i; i++) {
					inAccountIdStr += "'" + accounts[i] + "',";
				}
				if (inAccountIdStr.length() > 0) {
					inAccountIdStr = inAccountIdStr.substring(0,
							inAccountIdStr.length() - 1);
				}
				String queryStr ="";
				if(dbType.equals("mysql"))
				{
					queryStr="SELECT USER_NAME FROM USER_INFO WHERE ACCOUNT_ID IN("	+ inAccountIdStr + ")";
				}else if(dbType.equals("sqlserver")){
					queryStr="SELECT USER_NAME FROM USER_INFO WHERE ACCOUNT_ID IN("	+ inAccountIdStr + ")";
				}else if(dbType.equals("oracle")){
							queryStr="SELECT /*+ INDEX(USER_INFO USER_INFO_ACCOUNT_ID_PK)*/ USER_NAME FROM USER_INFO WHERE ACCOUNT_ID IN("	+ inAccountIdStr + ")";
						}
				PreparedStatement ps = conn.prepareStatement(queryStr);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					returnData += rs.getString("USER_NAME") + ",";
				}
				rs.close();
				ps.close();
				if (returnData.length() > 0) {
					returnData = returnData.substring(0,
							returnData.length() - 1);
				}
			} else {
				if (accountIdStr.equals("userAll")) {
					returnData += "全部人员,";
				} else {
					inAccountIdStr = accountIdStr;
					String queryStr = "SELECT USER_NAME FROM USER_INFO WHERE ACCOUNT_ID IN(?)";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, inAccountIdStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						returnData += rs.getString("USER_NAME") + ",";
					}
					rs.close();
					ps.close();
				}
				if (returnData.length() > 0) {
					returnData = returnData.substring(0,
							returnData.length() - 1);
				}
			}
		}
		return returnData;
	}

	// 通过字符串得到用户姓名的json数组
	public String getjsonUserNameStr(Connection conn, String accountIdStr)
			throws SQLException {
		String inAccountIdStr = "";
		JSONArray jsonArr = new JSONArray();
		if (!SysTool.isNullorEmpty(accountIdStr)) {
			if (accountIdStr.indexOf(",") > 0) {

				String[] accounts = accountIdStr.split(",");
				for (int i = 0; accounts.length > i; i++) {

					String queryStr = "SELECT USER_NAME,HEAD_IMG FROM USER_INFO WHERE ACCOUNT_ID ='"
							+ accounts[i] + "'";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("accountId", accounts[i]);
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("headImg", rs.getString("HEAD_IMG"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			} else {
				if (accountIdStr.equals("userAll")) {
					JSONObject json = new JSONObject();
					json.accumulate("accountId", "userAll");
					json.accumulate("userName", "全部人员");
					jsonArr.add(json);
				} else {
					inAccountIdStr = accountIdStr;
					String queryStr = "SELECT USER_NAME,HEAD_IMG FROM USER_INFO WHERE ACCOUNT_ID =?";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, inAccountIdStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("accountId", inAccountIdStr);
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("headImg", rs.getString("HEAD_IMG"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			}
		}
		return jsonArr.toString();
	}
	//根据字符串得到用户信息的json 数据（包含用户的名称和部门ID）
	public JSONArray getjsonArrUserNameStr(Connection conn, String accountIdStr)
			throws SQLException {
		String inAccountIdStr = "";
		JSONArray jsonArr = new JSONArray();
		if (!SysTool.isNullorEmpty(accountIdStr)) {
			if (accountIdStr.indexOf(",") > 0) {

				String[] accounts = accountIdStr.split(",");
				for (int i = 0; accounts.length > i; i++) {

					String queryStr = "SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE ACCOUNT_ID ='"
							+ accounts[i] + "'";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("deptId", rs.getString("DEPT_ID"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			} else {
				if (accountIdStr.equals("userAll")) {
					
				} else {
					inAccountIdStr = accountIdStr;
					String queryStr = "SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE ACCOUNT_ID =?";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, inAccountIdStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("deptId", rs.getString("DEPT_ID"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			}
		}
		return jsonArr;
	}
	
	//通过orgId 获取全部用户的accountId
	public String getorgIdAllLogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT ACCOUNT_ID FROM ACCOUNT WHERE ORG_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		String data="";
		while(rs.next()){
			data+=rs.getString("ACCOUNT_ID")+",";
		}
		if(data.length()>0){
			data=data.substring(0, data.length()-1);
		}
		return data;
	}
	public String getEmptyPassword(){
		return "";
	}
	/**
	 * 用户导入初始密码
	 * @return
	 */
	public String getImportInitPassword(){
		return "E10ADC3949BA59ABBE56E057F20F883E";
	}
	/**
	 * 用户导入初始明文密码
	 * @return
	 */
	public String getImportInitPasswordClear(){
		return "123456";
	}
	public String getPasswordTypeDefault(){
		return "1";
	}
	public String getThemeDefault(){
		return "1";
	}
	public String getNotLoginDefault(){
		return "0";
	}
	public String getLanguageDefault(){
		return "1";
	}
	
		/**
		 * 根据用户Id设置用户app权限
		 * @param conn
		 * @param accountId
		 * @param appIcon
		 * @param orgId
		 * @return
		 * @throws Exception
		 */
		public int getIdpowerlogic(Connection conn, String accountId,String appIcon, String orgId) throws Exception {
			PreparedStatement ps=null;
			try {
				String queryStr="";
				if(accountId.equals(PrivConst.USER_ALL)){
					queryStr="UPDATE ACCOUNT SET APP_ICON =? WHERE ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, orgId);
				}else{
					queryStr = "UPDATE ACCOUNT SET APP_ICON =? WHERE ACCOUNT_ID=? AND ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, accountId);
					ps.setString(3, orgId);
				}
				return ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}finally{
				dao.close(null, ps, null);
			}
		}

		/**
		 * 根据deptId 设置用户的app权限
		 * @param conn
		 * @param deptId
		 * @param appIcon
		 * @param orgId
		 * @return
		 * @throws SQLException
		 */
		public int getdeptpowerlogic(Connection conn, String deptId,String appIcon, String orgId) throws Exception {
			PreparedStatement ps=null;
			try {
				String queryStr="";
				if(deptId.equals(PrivConst.DEPT_ALL)){
					queryStr="UPDATE ACCOUNT SET APP_ICON =? WHERE ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, orgId);
				}else{
					queryStr = "UPDATE ACCOUNT SET APP_ICON =? WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM USER_INFO WHERE DEPT_ID=?) AND ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, deptId);
					ps.setString(3, orgId);
				}
				return ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}finally{
				dao.close(null, ps, null);
			}
		}
		
		/**
		 * 根据角色设置用户的app权限
		 * @param conn
		 * @param postpriv
		 * @param appIcon
		 * @param orgId
		 * @return
		 * @throws Exception
		 */
		public int getprivIdpowerlogic(Connection conn, String postpriv,String appIcon, String orgId) throws Exception {
			PreparedStatement ps =null;
			try {
				String queryStr="";
				if(postpriv.equals(PrivConst.PRIV_ALL)){
					queryStr="UPDATE ACCOUNT SET APP_ICON =? WHERE ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, orgId);
				}else{
					queryStr = "UPDATE ACCOUNT SET APP_ICON =? WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM USER_INFO WHERE POST_PRIV=?) AND ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, appIcon);
					ps.setString(2, postpriv);
					ps.setString(3, orgId);
				}
				return ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}finally{
				dao.close(null, ps, null);
			}
		}

		// 根据用户id获取icon
		public String getIconAct(Connection conn, String accountId, String orgId)
				throws SQLException {
			String queryStr = "SELECT APP_ICON FROM ACCOUNT WHERE ACCOUNT_ID=? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, accountId);
			ps.setString(2, orgId);
			ResultSet rs = ps.executeQuery();
			JSONArray jsonArr = new JSONArray();
			if (rs.next()) {
				jsonArr = JSONArray.fromObject(rs.getString("APP_ICON"));
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 系统账户总数
		 * @param conn
		 * @return
		 * @throws Exception
		 */
		public Integer getCountOfAllAccount(Connection conn) throws Exception{
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				String sql="select count(1) from account";
				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}finally{
				dao.close(rs, stmt, null);
			}
			return null;
		}
		/**
		 * 检查accountId 是否存在
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public int checkAccountIdLogic(Connection conn,String accountId)throws Exception{
			String queryStr="SELECT COUNT(1) AS NUM FROM ACCOUNT WHERE ACCOUNT_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, accountId);
			ResultSet rs = ps.executeQuery();
			int i=0;
			if(rs.next()){
				i=rs.getInt("NUM");
			}
			rs.close();
			ps.close();
			return i;
		}
}