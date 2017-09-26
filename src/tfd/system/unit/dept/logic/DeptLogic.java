package tfd.system.unit.dept.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

public class DeptLogic {
	public BaseDao dao=new BaseDaoImpl();
	public JSONArray getDeptTree(Account account,String deptParent,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_ID,DEPT_LONG_NAME, "
					+ "(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT "
					+ " FROM DEPARTMENT D WHERE ORG_ID=? AND ORG_LEAVE_ID=?";
			
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1,account.getOrgId());
			stmt.setString(2, deptParent);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("id", rs.getString("DEPT_ID"));
				treeLeaf.accumulate("pid", rs.getString("ORG_LEAVE_ID"));
				treeLeaf.accumulate("text", rs.getString("DEPT_NAME"));
				treeLeaf.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
				treeLeaf.accumulate("isParent", rs.getInt("CHILD_COUNT")>0);
				
				treeLeaf.accumulate("deptLongId", rs.getString("DEPT_LONG_ID"));
				treeLeaf.accumulate("deptLongName", rs.getString("DEPT_LONG_NAME"));
				
				treeList.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return treeList;
	}

	public String getDeptTreeOfAll(Connection conn, Account account)throws Exception {
		JSONArray tree=new JSONArray();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String sql = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,"
					+ "(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT "
					+ " FROM DEPARTMENT D WHERE ORG_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getOrgId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("id", rs.getString("DEPT_ID"));
				treeLeaf.accumulate("pId", rs.getString("ORG_LEAVE_ID"));
				treeLeaf.accumulate("name", rs.getString("DEPT_NAME"));
				treeLeaf.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
				treeLeaf.accumulate("isParent", rs.getInt("CHILD_COUNT")>0);
				tree.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return tree.toString();
	}

	// 获取部门信息
	public JSONObject getDeptInfoLogic(Connection conn, String deptId) throws Exception {
		JSONObject deptInfo = new JSONObject();
		ResultSet rs = null;
		PreparedStatement stmt=null;
		try {
			String queryStr = "SELECT ID,DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,"
					+ "				DEPT_LONG_ID,DEPT_LONG_NAME,"
					+ "				DEPT_TEL,DEPT_LEAD,"
					+ "				(SELECT USER_NAME FROM USER_INFO WHERE ACCOUNT_ID=D.DEPT_LEAD) AS DEPT_LEAD_USER_NAME,"
					+ "				DEPT_FAX,DEPT_EMAIL,DEPT_FUNCTION,ORG_ID"
					+ " FROM DEPARTMENT D WHERE DEPT_ID=?";
			
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, deptId);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String deptLongName=rs.getString("DEPT_LONG_NAME");
				String orgLeaveId=rs.getString("ORG_LEAVE_ID");
				String orgLeaveName="无";
				if (!orgLeaveId.equals("0")) {
					orgLeaveName=deptLongName.substring(0,deptLongName.lastIndexOf("/"));
					orgLeaveName=orgLeaveName.substring(orgLeaveName.lastIndexOf("/")+1);
				}
				
				
				deptInfo.accumulate("Id", rs.getString("ID"));
				deptInfo.accumulate("deptId", rs.getString("DEPT_ID"));
				deptInfo.accumulate("deptName", rs.getString("DEPT_NAME"));
				deptInfo.accumulate("orgLeaveId", orgLeaveId);
				deptInfo.accumulate("orgLeaveName", orgLeaveName);
				deptInfo.accumulate("deptLongId", rs.getString("DEPT_LONG_ID"));
				deptInfo.accumulate("deptLongName", deptLongName);
				deptInfo.accumulate("deptLead", rs.getString("DEPT_LEAD"));
				deptInfo.accumulate("deptLeadUserName",rs.getString("DEPT_LEAD_USER_NAME"));
				deptInfo.accumulate("deptTel", rs.getString("DEPT_TEL"));
				deptInfo.accumulate("deptFax", rs.getString("DEPT_FAX"));
				deptInfo.accumulate("deptEmail", rs.getString("DEPT_EMAIL"));
				deptInfo.accumulate("deptFunction", rs.getString("DEPT_FUNCTION"));
				deptInfo.accumulate("orgId", rs.getString("ORG_ID"));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return deptInfo;
	}

	// 判断是否有子部门
	public Boolean deptIsParent(Connection conn, String deptId)
			throws SQLException {
		Boolean flag = false;
		String queryStr = "SELECT COUNT(*) FROM DEPARTMENT WHERE ORG_LEAVE_ID=?";
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		rs = ps.executeQuery();
		if (rs.next()) {
			if (rs.getInt(1) > 0) {
				flag = true;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}

	// 通过DEPT_ID获取短部门名称
	public String getDeptNameShort(Connection conn, String deptId)
			throws SQLException {
		String queryStr = "SELECT DEPT_NAME FROM DEPARTMENT WHERE DEPT_ID=?";
		ResultSet rs = null;
		String returnData = "";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("DEPT_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	// 通过DEPT_ID获取长部门名称
	public String getDeptNameLong(Connection conn, String deptId)
			throws SQLException {
		String queryStr = "SELECT DEPT_LONG_NAME FROM DEPARTMENT WHERE DEPT_ID=?";
		ResultSet rs = null;
		String returnData = "";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("DEPT_LONG_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	// 处理部门字符串
	public String getDeptNameStr(Connection conn, String deptIdStr)
			throws SQLException {

		String returnData = "";
		String queryStr = "";
		if (SysTool.isNullorEmpty(deptIdStr)) {
			return returnData;
		}
		if (deptIdStr.equals("deptAll")) {
			returnData += "全部部门,";
		} else {
			deptIdStr = deptIdStr.replaceAll(",", "','");
			queryStr = "SELECT DEPT_NAME FROM DEPARTMENT WHERE DEPT_ID IN ('"
					+ deptIdStr + "')";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				returnData += rs.getString("DEPT_NAME") + ",";
			}
			rs.close();
			ps.close();
		}
		if (returnData.length() > 0) {
			returnData = returnData.substring(0, returnData.length() - 1);
		}
		return returnData;
	}

	// 按管理权限查询部门
	public String getDeptTreeByUserPrivJson(Connection conn, Account account)
			throws SQLException {
		String accountId = account.getAccountId();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		UserInfo userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String otherDept = userInfo.getOtherDept();
		if (SysTool.isNullorEmpty(otherDept)) {
			otherDept = "";
		} else {
			otherDept = "'" + otherDept.replace(",", "','") + "'";
		}
		ResultSet rs = null;
		String queryStr = "";
		if (manageDept.equals("2")) {
			queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID FROM DEPARTMENT WHERE ORG_ID='"
					+ account.getOrgId() + "'";
		} else {
			if (SysTool.isNullorEmpty(otherDept)) {
				queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID FROM DEPARTMENT WHERE ORG_ID='"
						+ account.getOrgId()
						+ "'"
						+ " AND DEPT_ID='"
						+ userInfo.getDeptId() + "'";
			} else {
				queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID FROM DEPARTMENT WHERE ORG_ID='"
						+ account.getOrgId()
						+ "'"
						+ " AND (DEPT_ID IN ("
						+ otherDept
						+ ") OR DEPT_ID='"
						+ userInfo.getDeptId()
						+ "')";
			}
		}
		JSONArray jsonArr = new JSONArray();
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("pId", rs.getString("ORG_LEAVE_ID"));
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			if (rs.getString("ORG_LEAVE_ID").equals("0")) {
				json.accumulate("isParent", true);
			} else {
				json.accumulate("isParent", false);
			}
			jsonArr.add(json);
		}

		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	// 更新部门信息
	public int updateDeptInfo(Connection conn, Department dept)
			throws SQLException {
		String queryStr = "UPDATE DEPARTMENT SET DEPT_NAME=?,ORG_LEAVE_ID=?,DEPT_LONG_NAME=?,DEPT_TEL=?,DEPT_EMAIL=?,DEPT_FAX=?,DEPT_LEAD=?,DEPT_FUNCTION=?,ORG_ID=? WHERE DEPT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dept.getDeptName());
		ps.setString(2, dept.getOrgLeaveId());
		ps.setString(3, dept.getDeptLongName());
		ps.setString(4, dept.getDeptTel());
		ps.setString(5, dept.getDeptEmail());
		ps.setString(6, dept.getDeptFax());
		ps.setString(7, dept.getDeptLead());
		ps.setString(8, dept.getDeptFunction());
		ps.setString(9, dept.getOrgId());
		ps.setString(10, dept.getDeptId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	// 添加部门信息
	public int addDeptInfoLogic(Connection conn, Department dept)
			throws SQLException {
		String queryStr = "INSERT INTO DEPARTMENT ("
				+ "			DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_NAME,"
				+ "			DEPT_TEL,DEPT_EMAIL,DEPT_FAX,DEPT_LEAD,DEPT_FUNCTION,ORG_ID,DEPT_LONG_ID)"
				+ "		VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dept.getDeptId());
		ps.setString(2, dept.getDeptName());
		ps.setString(3, dept.getOrgLeaveId());
		ps.setString(4, dept.getDeptLongName());
		ps.setString(5, dept.getDeptTel());
		ps.setString(6, dept.getDeptEmail());
		ps.setString(7, dept.getDeptFax());
		ps.setString(8, dept.getDeptLead());
		ps.setString(9, dept.getDeptFunction());
		ps.setString(10, dept.getOrgId());
		ps.setString(11, dept.getDeptLongId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	// 通过DEPTID获取部门实例
	public Department getDepartmentLogic(Connection conn, String detpId)
			throws SQLException {
		Department department = new Department();
		String queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_NAME,DEPT_TEL,DEPT_EMAIL,DEPT_FAX,DEPT_LEAD,DEPT_FUNCTION,ORG_ID FROM DEPARTMENT WHERE DEPT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, detpId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			department.setDeptId(rs.getString("DEPT_ID"));
			department.setDeptName(rs.getString("DEPT_NAME"));
			department.setOrgleaveId(rs.getString("ORG_LEAVE_ID"));
			department.setDeptLongName(rs.getString("DEPT_LONG_NAME"));
			department.setDeptTel(rs.getString("DEPT_TEL"));
			department.setDeptEmail(rs.getString("DEPT_EMAIL"));
			department.setDeptFax(rs.getString("DEPT_FAX"));
			department.setDeptLead(rs.getString("DEPT_LEAD"));
			department.setDeptFunction(rs.getString("DEPT_FUNCTION"));
			department.setOrgId(rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return department;
	}

	public int deleteDept(Account account,String deptId,Connection conn)throws Exception {
		PreparedStatement stmt=null;
		try {
			
			String sql = "DELETE FROM DEPARTMENT WHERE ORG_ID =? AND DEPT_LONG_ID LIKE ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getOrgId());
			stmt.setString(2, "%"+deptId+"%");
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}

	// 判断部门中有无人员
	public boolean checkUserInDeptLogic(Connection conn, String deptId)
			throws SQLException {
		boolean flag = false;
		String queryStr = "SELECT COUNT(*) AS COUNT FROM USER_INFO WHERE DEPT_ID IN (SELECT DEPT_ID FROM DEPARTMENT WHERE DEPT_ID=? OR ORG_LEAVE_ID=?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, deptId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt("COUNT") > 0) {
				flag = true;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * @author fzd
	 * 获取部门及子部门下用户信息数量
	 * @param account
	 * @param deptId
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getDeptAllUserInfoCount(Account account,String deptId,Connection conn)throws Exception {
		PreparedStatement stmt=null;
		ResultSet rs =null;
		try {
			
			String sql = "SELECT COUNT(1) AS USER_INFO_COUNT "
					+ "	FROM USER_INFO AS U LEFT JOIN DEPARTMENT AS D ON U.DEPT_ID=D.DEPT_ID "
					+ " WHERE D.ORG_ID =? AND D.DEPT_LONG_ID LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getOrgId());
			stmt.setString(2, "%"+deptId+"%");
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("user_info_count");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		
		return 0;
	}

	// 通过字符串得到部门名称的json数组
	public String getjsondeptNameStr(Connection conn, String accountIdStr)
			throws SQLException {
		String inAccountIdStr = "";
		JSONArray jsonArr = new JSONArray();
		if (!SysTool.isNullorEmpty(accountIdStr)) {
			if (accountIdStr.indexOf(",") > 0) {
				String[] accounts = accountIdStr.split(",");
				for (int i = 0; accounts.length > i; i++) {
					String queryStr = "SELECT DEPT_NAME FROM DEPARTMENT WHERE DEPT_ID ='"
							+ accounts[i] + "'";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("deptId", accounts[i]);
						json.accumulate("deptName", rs.getString("DEPT_NAME"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			} else {
				inAccountIdStr = accountIdStr;
				if (inAccountIdStr.equals("deptAll")) {
					JSONObject json = new JSONObject();
					json.accumulate("deptId", "deptAll");
					json.accumulate("deptName", "全部部门");
					jsonArr.add(json);
				} else {
					String queryStr = "SELECT DEPT_NAME FROM DEPARTMENT WHERE DEPT_ID ='"
							+ inAccountIdStr + "'";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						JSONObject json = new JSONObject();
						json.accumulate("deptId", inAccountIdStr);
						json.accumulate("deptName", rs.getString("DEPT_NAME"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
				}
			}
		}
		return jsonArr.toString();
	}
	public String getDeptIdByDeptNameAndOrgLevelId(String deptName,String orgLevelId,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select dept_id from department where dept_name=? and org_leave_id=? and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, deptName);
			stmt.setString(2, orgLevelId);
			stmt.setString(3, orgId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("dept_id");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public String getDeptIdByDeptLongName(String deptLongName,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select dept_id from department where dept_long_name=? and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, deptLongName);
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("dept_id");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public String getDeptLongNameStrByDeptIdStr(String deptIdStr,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select dept_long_name from department where dept_id in (?) and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, deptIdStr);
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			List<String> deptLongNames=new ArrayList<String>();
			while (rs.next()) {
				deptLongNames.add(rs.getString("dept_long_name"));
			}
			if (deptLongNames.size()>0) {
				return SysTool.join(deptLongNames.toArray(), ",");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public JSONArray getDeptTreeOfOnlineUser(Account account,String deptParent,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String sql ="";
				sql= "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_ID,DEPT_LONG_NAME,"
						+ "		(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT "
						+ " FROM DEPARTMENT D"
						+ "	WHERE ORG_ID=? AND ORG_LEAVE_ID=?"
						+ "		AND (SELECT COUNT(1) "
						+ "				FROM USER_ONLINE  UO LEFT JOIN USER_INFO UI "
						+ "					ON UO.ACCOUNT_ID=UI.ACCOUNT_ID "
						+ "				WHERE (SELECT DEPT_LONG_ID FROM DEPARTMENT WHERE DEPT_ID=UI.DEPT_ID) LIKE CONCAT(DEPT_LONG_ID,'%')"
						+ "					AND UO.STATE='1'"
						+ "			) >0";
				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,account.getOrgId());
			stmt.setString(2, deptParent);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("id", rs.getString("DEPT_ID"));
				treeLeaf.accumulate("pid", rs.getString("ORG_LEAVE_ID"));
				treeLeaf.accumulate("text", rs.getString("DEPT_NAME"));
				treeLeaf.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
				treeLeaf.accumulate("isParent", true);

				treeLeaf.accumulate("deptChildCount", rs.getInt("CHILD_COUNT"));
				treeLeaf.accumulate("deptLongId", rs.getString("DEPT_LONG_ID"));
				treeLeaf.accumulate("deptLongName", rs.getString("DEPT_LONG_NAME"));
				
				treeList.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		
		return treeList;
	}
	public JSONArray getOnlineUserOfTree(Account account,String deptId,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String sql="SELECT T1.USER_ID AS USER_ID,T1.USER_NAME AS USER_NAME,T1.SEX AS SEX " 
					+" FROM USER_INFO T1 LEFT JOIN USER_ONLINE T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID "
					+" LEFT JOIN ACCOUNT T3 ON T1.ACCOUNT_ID = T3.ACCOUNT_ID "
					+" WHERE T1.ORG_ID = ? AND T1.DEPT_ID = ? AND T3.NOT_LOGIN = '0' AND T2.STATE = '1' ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, deptId);
			stmt.setString(2,account.getOrgId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				String sex=rs.getString("sex");
				String userIcon=sex.equals("男")?"/tfd/system/styles/images/org/U01.png":"/tfd/system/styles/images/org/U11.png";
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("isUser", true);
				treeLeaf.accumulate("id", rs.getString("user_id"));
				treeLeaf.accumulate("pid", deptId);
				treeLeaf.accumulate("text", rs.getString("user_name"));
				treeLeaf.accumulate("icon", userIcon);
				treeLeaf.accumulate("isParent", false);
				treeList.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return treeList;
	}
	public JSONArray getDeptTreeOfAllUser(Account account,String deptParent,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String sql = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_ID,DEPT_LONG_NAME,"
					+ "		(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT, "
					+ "		(SELECT COUNT(1) FROM USER_INFO WHERE DEPT_ID=D.DEPT_ID) AS USER_COUNT "
					+ " FROM DEPARTMENT D"
					+ "	WHERE ORG_ID=? AND ORG_LEAVE_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,account.getOrgId());
			stmt.setString(2, deptParent);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int deptChildCount=rs.getInt("CHILD_COUNT");
				int userCount=rs.getInt("USER_COUNT");
				
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("id", rs.getString("DEPT_ID"));
				treeLeaf.accumulate("pid", rs.getString("ORG_LEAVE_ID"));
				treeLeaf.accumulate("text", rs.getString("DEPT_NAME"));
				treeLeaf.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
				treeLeaf.accumulate("isParent",deptChildCount>0||userCount>0);

				treeLeaf.accumulate("deptChildCount", deptChildCount);
				treeLeaf.accumulate("userCount", userCount);
				treeLeaf.accumulate("deptLongId", rs.getString("DEPT_LONG_ID"));
				treeLeaf.accumulate("deptLongName", rs.getString("DEPT_LONG_NAME"));
				
				treeList.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		
		return treeList;
	}
	public JSONArray getAllUserOfTree(Account account,String deptId,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			String sql = "SELECT DISTINCT UI.USER_ID,UI.USER_NAME,UI.SEX,UO.STATE "
					+ "	 FROM USER_INFO UI LEFT JOIN USER_ONLINE UO ON UO.ACCOUNT_ID=UI.ACCOUNT_ID "
					+" LEFT JOIN ACCOUNT T1 ON ui.ACCOUNT_ID=T1.ACCOUNT_ID "
					+ "WHERE UI.ORG_ID=? AND UI.DEPT_ID=? AND T1.NOT_LOGIN='0' ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,account.getOrgId());
			stmt.setString(2,deptId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String sex=rs.getString("sex");
				String state=rs.getString("state");
				String userIcon=null;
				if (state!=null&&state.equals("1")) {
					userIcon=sex.equals("男")?"/tfd/system/styles/images/org/U01.png":"/tfd/system/styles/images/org/U11.png";
				}else{
					userIcon=sex.equals("男")?"/tfd/system/styles/images/org/U00.png":"/tfd/system/styles/images/org/U10.png";
				}
				
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("isUser", true);
				treeLeaf.accumulate("id", rs.getString("user_id"));
				treeLeaf.accumulate("pid", deptId);
				treeLeaf.accumulate("text", rs.getString("user_name"));
				treeLeaf.accumulate("icon", userIcon);
				treeLeaf.accumulate("isParent", false);
				
				treeList.add(treeLeaf);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return treeList;
	}
}
