package tfd.system.unit.userinfo.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

public class UserInfoLogic {
	public BaseDao dao=new BaseDaoImpl();
	public UserInfo getUserInfoByAccountId(Connection conn, String accountId)
			throws Exception {
		String queryStr = "SELECT ID,ACCOUNT_ID,USER_NAME,DEPT_ID,"
				+ "LEAD_ID,POST_PRIV,OTHER_PRIV,LEAD_LEAVE,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,E_MAILE,WORK_ID,"
				+ "ORG_ID,MANAGE_DEPT,OTHER_DEPT FROM USER_INFO WHERE ACCOUNT_ID='"
				+ accountId + "'";
		UserInfo userInfo = new UserInfo();
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		if (rs.next()) {
			userInfo.setId(rs.getInt("ID"));
			userInfo.setAccountId(rs.getString("ACCOUNT_ID"));
			userInfo.setUserName(rs.getString("USER_NAME"));
			userInfo.setDeptId(rs.getString("DEPT_ID"));
			userInfo.setLeadId(rs.getString("LEAD_ID"));
			userInfo.setLeadLeave(rs.getString("LEAD_LEAVE"));
			userInfo.setPostPriv(rs.getString("POST_PRIV"));
			userInfo.setHomeAdd(rs.getString("HOME_ADD"));
			userInfo.setHomeTel(rs.getString("HOME_TEL"));
			userInfo.setMobileNo(rs.getString("MOBILE_NO"));
			userInfo.setMobileNo(rs.getString("E_MAILE"));
			userInfo.setQq(rs.getString("QQ"));
			userInfo.setWorkId(rs.getString("WORK_ID"));
			userInfo.setOrgId(rs.getString("ORG_ID"));
			userInfo.setOtherPriv(rs.getString("OTHER_PRIV"));
			userInfo.setManageDept(rs.getString("MANAGE_DEPT"));
			userInfo.setOtherDept(rs.getString("OTHER_DEPT"));
		}
		rs.close();
		ps.close();
		return userInfo;
	}

	public String getUserPowerTreeLogic(Connection conn, Account account)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String queryStr = "";
		queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID FROM DEPARTMENT WHERE ORG_ID='"
				+ account.getOrgId() + "'";
		JSONArray jsonArr = new JSONArray();
		ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("pId", rs.getString("ORG_LEAVE_ID"));
			json.accumulate("name", "[" + rs.getString("DEPT_NAME") + "]");
			json.accumulate("isParent", "true");
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			jsonArr.add(json);
		}
		UserPrivLogic userPrivLogic = new UserPrivLogic();
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		userList = userPrivLogic.getRangeListLogic(conn, account);
		for (int i = 0; i < userList.size(); i++) {
			JSONObject json = new JSONObject();
			json.accumulate("id", userList.get(i).get("accountId"));
			json.accumulate("pId", userList.get(i).get("deptId"));
			json.accumulate("name", userList.get(i).get("userName"));
			if (userList.get(i).get("sex").equals("男")) {
				json.accumulate("icon", "/tfd/system/styles/images/org/U00.png");
			} else {
				json.accumulate("icon", "/tfd/system/styles/images/org/U10.png");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	public String getUserInfoTreeLogic(Connection conn, Account account)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String queryStr = "";
		queryStr = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID FROM DEPARTMENT WHERE ORG_ID='"
				+ account.getOrgId() + "'";
		JSONArray jsonArr = new JSONArray();
		ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("pId", rs.getString("ORG_LEAVE_ID"));
			json.accumulate("name", "[" + rs.getString("DEPT_NAME") + "]");
			json.accumulate("isParent", "true");
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			jsonArr.add(json);
		}
		/*queryStr = "SELECT DISTINCT T1.USER_ID AS USER_ID,USER_NAME,DEPT_ID,SEX FROM USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T2.NOT_LOGIN='0' AND T1.ORG_ID='"
				+ account.getOrgId() + "'";
		ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("USER_ID"));
			json.accumulate("pId", rs.getString("DEPT_ID"));
			json.accumulate("name", rs.getString("USER_NAME"));
			if (rs.getString("SEX").equals("男")) {
				json.accumulate("icon", "/tfd/system/styles/images/org/U00.png");
			} else {
				json.accumulate("icon", "/tfd/system/styles/images/org/U10.png");
			}
			jsonArr.add(json);
		}*/
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	//根据部门获取人员信息加载到树中去
	public String getZtreedeptAlluserLogic(Connection conn,String deptId)throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		JSONArray jsonArr = new JSONArray();
		String	queryStr = "SELECT DISTINCT T1.USER_ID AS USER_ID,USER_NAME,DEPT_ID,SEX FROM USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T2.NOT_LOGIN='0' AND T1.DEPT_ID='"
				+ deptId + "'";
		ps = conn.prepareStatement(queryStr);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("USER_ID"));
			json.accumulate("pId", rs.getString("DEPT_ID"));
			json.accumulate("name", rs.getString("USER_NAME"));
			if (rs.getString("SEX").equals("男")) {
				json.accumulate("icon", "/tfd/system/styles/images/org/U00.png");
			} else {
				json.accumulate("icon", "/tfd/system/styles/images/org/U10.png");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	// 获取用户信息
	public String getUserInfoLogic(Connection conn, String userId)
			throws SQLException {
		DeptLogic deptLogic = new DeptLogic();
		JSONObject json = new JSONObject();
		PreparedStatement ps = null;
		
		String queryStr="SELECT A.ID AS ID,USER_ID,ACCOUNT_ID,LEAD_LEAVE,USER_NAME,SEX,A.DEPT_ID,"
				+" LEAD_ID,POST_PRIV,C.USER_PRIV_NAME AS USER_PRIV_NAME,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO, "
				+" QQ,E_MAILE,WORK_ID,A.ORG_ID AS ORG_ID,MANAGE_DEPT,OTHER_DEPT,B.DEPT_LONG_NAME AS DEPT_LONG_NAME, "
				+" A.ATTEND_CONFIG_ID FROM USER_INFO A LEFT JOIN DEPARTMENT B ON A.DEPT_ID = B.DEPT_ID "
				+" LEFT JOIN USER_PRIV C  ON C.USER_PRIV_ID = A.POST_PRIV WHERE USER_ID = ?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("sex", rs.getString("SEX"));
			json.accumulate("deptId", rs.getString("DEPT_LONG_NAME"));
			json.accumulate("leadId", rs.getString("LEAD_ID"));
			json.accumulate("leadUserName",
					getUserNameByAccountIdLogic(conn, rs.getString("LEAD_ID")));
			json.accumulate("postPriv", rs.getString("USER_PRIV_NAME"));
			json.accumulate("homeAdd", rs.getString("HOME_ADD"));
			json.accumulate("homeTel", rs.getString("HOME_TEL"));
			json.accumulate("mobileNo", rs.getString("MOBILE_NO"));
			json.accumulate("qQ", rs.getString("QQ"));
			json.accumulate("eMaile", rs.getString("E_MAILE"));
			json.accumulate("workId", rs.getString("WORK_ID"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			json.accumulate(
					"otherPriv",
					userLeaveLogic.getLeaveNameLogic(conn,
							rs.getString("OTHER_PRIV"), rs.getString("ORG_ID")));
			json.accumulate("manageDept",
					rs.getString("MANAGE_DEPT").equals("1") ? "全体" : "本部门");
			json.accumulate("otherDept",
					deptLogic.getDeptNameStr(conn, rs.getString("OTHER_DEPT")));
			json.accumulate("leadLeave", rs.getString("LEAD_LEAVE"));
			json.accumulate(
					"leadLeaveName",
					userLeaveLogic.getLeaveNameLogic(conn,
							rs.getString("LEAD_LEAVE"), rs.getString("ORG_ID")));
			json.accumulate("attendConfigId", rs.getString("ATTEND_CONFIG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	public JSONArray getUserInfoListByAccountId(Connection conn, String accountId)throws Exception {
		JSONArray userInfoList = new JSONArray();
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try{
			DeptLogic deptLogic = new DeptLogic();
			
			String queryStr="SELECT A.ID AS ID,USER_ID,ACCOUNT_ID,LEAD_LEAVE,USER_NAME,SEX,A.DEPT_ID,"
					+" 				LEAD_ID,POST_PRIV,C.USER_PRIV_NAME AS USER_PRIV_NAME,OTHER_PRIV,"
					+ "				HOME_ADD,HOME_TEL,MOBILE_NO, "
					+" 				QQ,E_MAILE,WORK_ID,A.ORG_ID AS ORG_ID,MANAGE_DEPT,"
					+ "				OTHER_DEPT,B.DEPT_LONG_NAME AS DEPT_LONG_NAME, "
					+" 				A.ATTEND_CONFIG_ID "
					+ "		FROM USER_INFO A LEFT JOIN DEPARTMENT B ON A.DEPT_ID = B.DEPT_ID "
					+" 						 LEFT JOIN USER_PRIV C  ON C.USER_PRIV_ID = A.POST_PRIV "
					+ "		WHERE ACCOUNT_ID = ?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, accountId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
				JSONObject json=new JSONObject();
				json.accumulate("id", rs.getString("ID"));
				json.accumulate("userId", rs.getString("USER_ID"));
				json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
				json.accumulate("userName", rs.getString("USER_NAME"));
				json.accumulate("sex", rs.getString("SEX"));
				json.accumulate("deptId", rs.getString("DEPT_LONG_NAME"));
				json.accumulate("leadId", rs.getString("LEAD_ID"));
				json.accumulate("leadUserName",getUserNameByAccountIdLogic(conn, rs.getString("LEAD_ID")));
				json.accumulate("postPriv", rs.getString("USER_PRIV_NAME"));
				json.accumulate("homeAdd", rs.getString("HOME_ADD"));
				json.accumulate("homeTel", rs.getString("HOME_TEL"));
				json.accumulate("mobileNo", rs.getString("MOBILE_NO"));
				json.accumulate("qQ", rs.getString("QQ"));
				json.accumulate("eMaile", rs.getString("E_MAILE"));
				json.accumulate("workId", rs.getString("WORK_ID"));
				json.accumulate("orgId", rs.getString("ORG_ID"));
				json.accumulate("otherPriv",
						userLeaveLogic.getLeaveNameLogic(conn,
								rs.getString("OTHER_PRIV"), rs.getString("ORG_ID")));
				json.accumulate("manageDept",
						rs.getString("MANAGE_DEPT").equals("1") ? "全体" : "本部门");
				json.accumulate("otherDept",
						deptLogic.getDeptNameStr(conn, rs.getString("OTHER_DEPT")));
				json.accumulate("leadLeave", rs.getString("LEAD_LEAVE"));
				json.accumulate(
						"leadLeaveName",
						userLeaveLogic.getLeaveNameLogic(conn,
								rs.getString("LEAD_LEAVE"), rs.getString("ORG_ID")));
				json.accumulate("attendConfigId", rs.getString("ATTEND_CONFIG_ID"));
				userInfoList.add(json);
			}
		}catch(Exception e){
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return userInfoList;
	}
	// 通达ACCOUNTID获取用户信息
	public String getUserInfoByAccountIdLogic(Connection conn, String accountId)
			throws SQLException {
		DeptLogic deptLogic = new DeptLogic();
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String queryStr = "SELECT A.ID AS ID,USER_ID,ACCOUNT_ID,LEAD_LEAVE,USER_NAME,SEX,A.DEPT_ID,LEAD_ID,POST_PRIV,C.USER_PRIV_NAME AS USER_PRIV_NAME,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,"
				+ "E_MAILE,WORK_ID,A.ORG_ID AS ORG_ID,MANAGE_DEPT,OTHER_DEPT,B.DEPT_LONG_NAME AS DEPT_LONG_NAME  "
				+ "FROM USER_INFO A,DEPARTMENT B,USER_PRIV C "
				+ "WHERE ACCOUNT_ID=? AND A.DEPT_ID=B.DEPT_ID AND C.USER_PRIV_ID=A.POST_PRIV";

		JSONArray jsonArr = new JSONArray();
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("sex", rs.getString("SEX"));
			json.accumulate("deptId", rs.getString("DEPT_ID"));
			json.accumulate("deptName",
					deptLogic.getDeptNameShort(conn, rs.getString("DEPT_ID")));
			json.accumulate("leadId", rs.getString("LEAD_ID"));
			json.accumulate("leadUserName",
					getUserNameByAccountIdLogic(conn, rs.getString("LEAD_ID")));
			json.accumulate("postPriv", rs.getString("POST_PRIV"));
			json.accumulate("postPrivName", rs.getString("USER_PRIV_NAME"));
			json.accumulate("homeAdd", rs.getString("HOME_ADD"));
			json.accumulate("homeTel", rs.getString("HOME_TEL"));
			json.accumulate("mobileNo", rs.getString("MOBILE_NO"));
			json.accumulate("qQ", rs.getString("QQ"));
			json.accumulate("eMaile", rs.getString("E_MAILE"));
			json.accumulate("workId", rs.getString("WORK_ID"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			json.accumulate("otherPriv", rs.getString("OTHER_PRIV"));
			json.accumulate(
					"otherPrivName",
					userLeaveLogic.getLeaveNameLogic(conn,
							rs.getString("OTHER_PRIV"), rs.getString("ORG_ID")));
			json.accumulate("manageDept", rs.getString("MANAGE_DEPT"));
			json.accumulate("otherDept", rs.getString("OTHER_DEPT"));
			json.accumulate("otherDeptName",
					deptLogic.getDeptNameStr(conn, rs.getString("OTHER_DEPT")));
			json.accumulate("leadLeave", rs.getString("LEAD_LEAVE"));
			json.accumulate(
					"leadLeaveName",
					userLeaveLogic.getLeaveNameLogic(conn,
							rs.getString("LEAD_LEAVE"), rs.getString("ORG_ID")));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	// 获取部门列表
	public String getUserInfoListLogic(Connection conn, List<String> pramList,
			int pageSize, int page, String sortOrder, String sortName)
			throws Exception {
		String queryStr="SELECT DISTINCT A.ID AS ID, A.USER_ID AS USER_ID, A.ACCOUNT_ID AS ACCOUNT_ID, USER_NAME, SEX, A.DEPT_ID AS DEPT_ID, DEPT_NAME, LEAD_ID, POST_PRIV, USER_PRIV_NAME, OTHER_PRIV, HOME_ADD, HOME_TEL, MOBILE_NO, QQ, E_MAILE, WORK_ID, A.ORG_ID AS ORG_ID, MANAGE_DEPT, OTHER_DEPT, LEAD_LEAVE, (SELECT CONFIG_NAME FROM ATTEND_CONFIG t1 WHERE t1.ATTEND_CONFIG_ID = A.ATTEND_CONFIG_ID) AS ATTEND_CONFIG_NAME FROM USER_INFO A LEFT JOIN DEPARTMENT B ON A.DEPT_ID = B.DEPT_ID LEFT JOIN USER_PRIV C ON A.POST_PRIV = C.USER_PRIV_ID LEFT JOIN ACCOUNT D ON A.ACCOUNT_ID = D.ACCOUNT_ID WHERE D.NOT_LOGIN = '0' AND A.DEPT_ID=?";
		String optStr = "[{'function':'edit','name':'修改','parm':'ACCOUNT_ID'},{'function':'del','name':'注销','parm':'ACCOUNT_ID'}]";
		new JSONArray();
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList1 = new ArrayList<String>();
		pramList1.add(pramList.get(0));
		JSONObject json = pageTool.getPageData(conn, queryStr, pramList1,
				pageSize, page, optArrJson, sortOrder, sortName);
		return json.toString();
	}

	// 获取用户部门ID
	public String getDeptId(Connection conn, String accountId)
			throws SQLException {
		String deptId = "";
		ResultSet rs = null;
		String querySrt = "SELECT DEPT_ID FROM USER_INFO WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(querySrt);
		ps.setString(1, accountId);
		rs = ps.executeQuery();
		if (rs.next()) {
			deptId = rs.getString("DEPT_ID");
		}
		rs.close();
		ps.close();
		return deptId;
	}

	// 获取用户姓名通过accountId
	public String getUserNameByAccountIdLogic(Connection conn, String accountId)
			throws SQLException {
		String returnData = "";
		String queryStr = "SELECT USER_NAME FROM USER_INFO WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("USER_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	// 获取userInfo实例
	public UserInfo getUserInfoByAccount(Connection conn, Account account)
			throws SQLException {
		UserInfo userInfo = new UserInfo();
		ResultSet rs = null;
		String queryStr = "SELECT ID,USER_ID,ACCOUNT_ID,USER_NAME,DEPT_ID,LEAD_ID,LEAD_LEAVE,POST_PRIV,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,"
				+ "E_MAILE,WORK_ID,ORG_ID,SEX,SORT_ID,MANAGE_DEPT,OTHER_DEPT FROM USER_INFO WHERE ACCOUNT_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		rs = ps.executeQuery();
		if (rs.next()) {
			userInfo.setId(rs.getInt("ID"));
			userInfo.setUserId(rs.getString("USER_ID"));
			userInfo.setAccountId(rs.getString("ACCOUNT_ID"));
			userInfo.setUserName(rs.getString("USER_NAME"));
			userInfo.setDeptId(rs.getString("DEPT_ID"));
			userInfo.setLeadId(rs.getString("LEAD_ID"));
			userInfo.setPostPriv(rs.getString("POST_PRIV"));
			userInfo.setHomeAdd(rs.getString("HOME_ADD"));
			userInfo.setHomeTel(rs.getString("HOME_TEL"));
			userInfo.setMobileNo(rs.getString("MOBILE_NO"));
			userInfo.setQq(rs.getString("QQ"));
			userInfo.seteMail(rs.getString("E_MAILE"));
			userInfo.setWorkId(rs.getString("WORK_ID"));
			userInfo.setOrgId(rs.getString("ORG_ID"));
			userInfo.setSex(rs.getString("SEX"));
			userInfo.setSortId(rs.getString("SORT_ID"));
			userInfo.setOtherPriv(rs.getString("OTHER_PRIV"));
			userInfo.setManageDept(rs.getString("MANAGE_DEPT"));
			userInfo.setOtherDept(rs.getString("OTHER_DEPT"));
			userInfo.setLeadLeave(rs.getString("LEAD_LEAVE"));
		}
		rs.close();
		ps.close();
		return userInfo;
	}

	// 按用户姓名获取AccountId
	public String getAccountIdByUserName(Connection conn, String userName)
			throws SQLException {
		String returnData = "";
		String queryStr = "SELECT ACCOUNT_ID FROM USER_INFO WHERE USER_NAME=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("ACCOUNT_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	// 更新用户信息
	public void updateUserInfoLogic(Connection conn, UserInfo userInfo)
			throws SQLException {
		String queryStr = "UPDATE USER_INFO SET USER_NAME=?,SEX=?,DEPT_ID=?,LEAD_ID=?,POST_PRIV=?,OTHER_PRIV=?,HOME_ADD=?,HOME_TEL=?,MOBILE_NO=?,QQ=?,E_MAILE=?,WORK_ID=?,MANAGE_DEPT=?,OTHER_DEPT=?,LEAD_LEAVE=? "
				+ "WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userInfo.getUserName());
		ps.setString(2, userInfo.getSex());
		ps.setString(3, userInfo.getDeptId());
		ps.setString(4, userInfo.getLeadId());
		ps.setString(5, userInfo.getPostPriv());
		ps.setString(6, userInfo.getOtherPriv());
		ps.setString(7, userInfo.getHomeAdd());
		ps.setString(8, userInfo.getHomeTel());
		ps.setString(9, userInfo.getMobileNo());
		ps.setString(10, userInfo.getQq());
		ps.setString(11, userInfo.geteMail());
		ps.setString(12, userInfo.getWorkId());
		ps.setString(13, userInfo.getManageDept());
		ps.setString(14, userInfo.getOtherDept());
		ps.setString(15, userInfo.getLeadLeave());
		ps.setString(16, userInfo.getAccountId());
		ps.executeUpdate();
		ps.close();
		queryStr = "UPDATE  ACCOUNT SET USER_PRIV=? WHERE ACCOUNT_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, userInfo.getPostPriv());
		ps.setString(2, userInfo.getAccountId());
		ps.executeUpdate();
		ps.close();
	}

	// 添加用户信息
	public int addUserInfoLogic(Connection conn, UserInfo userInfo)
			throws Exception {
		PreparedStatement stmt=null;
		try {
			
			String queryStr = "INSERT INTO USER_INFO (USER_ID,ACCOUNT_ID,USER_NAME,SEX,DEPT_ID,LEAD_ID,POST_PRIV,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,E_MAILE,WORK_ID,MANAGE_DEPT,OTHER_DEPT,LEAD_LEAVE,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, userInfo.getUserId());
			stmt.setString(2, userInfo.getAccountId());
			stmt.setString(3, userInfo.getUserName());
			stmt.setString(4, userInfo.getSex());
			stmt.setString(5, userInfo.getDeptId());
			stmt.setString(6, userInfo.getLeadId());
			stmt.setString(7, userInfo.getPostPriv());
			stmt.setString(8, userInfo.getOtherPriv());
			stmt.setString(9, userInfo.getHomeAdd());
			stmt.setString(10, userInfo.getHomeTel());
			stmt.setString(11, userInfo.getMobileNo());
			stmt.setString(12, userInfo.getQq());
			stmt.setString(13, userInfo.geteMail());
			stmt.setString(14, userInfo.getWorkId());
			stmt.setString(15, userInfo.getManageDept());
			stmt.setString(16, userInfo.getOtherDept());
			stmt.setString(17, userInfo.getLeadLeave());
			stmt.setString(18, userInfo.getOrgId());
			int result=stmt.executeUpdate();
			
			if (result>0) {
				AccountLogic accountLogic = new AccountLogic();
				accountLogic.updateNotLoginLogic(conn, userInfo.getAccountId());
				return result;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
		return 0;
	}

	// 获取用户的手机号吗
	public String getUserMobileNoLogic(Connection conn, String accountId)
			throws SQLException {
		String returnData = "";
		String queryStr = "SELECT MOBILE_NO FROM USER_INFO WHERE ACCOUNT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("MOBILE_NO");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	// 根据角色 Id 获取所有用户的accountId的字符串
	public String getprivIdLogic(Connection conn, String orgId, String privId)
			throws SQLException {
		String queryStr = "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID  FROM  USER_INFO T1 ,ACCOUNT T2 WHERE T1.ACCOUNT_ID =T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND  T1.POST_PRIV=? AND T1.ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, privId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		String accountStr = "";
		while (rs.next()) {
			accountStr += rs.getString("ACCOUNT_ID");
		}
		if (accountStr.length() > 0) {
			accountStr = accountStr.substring(0, accountStr.length() - 1);
		}
		return accountStr;
	}

	// 根据deptId获取所有员工accountStr 字符串
	public String getdeptIdLogic(Connection conn, String deptId, String orgId)
			throws SQLException {
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr ="";
		if(dbType.equals("mysql"))
		{
		queryStr= "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO T1,ACCOUNT T2  WHERE T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND (DEPT_ID =? OR LOCATE(?,CONCAT(',',OTHER_DEPT,','))>0) AND T1.ORG_ID=?";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr= "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO T1,ACCOUNT T2  "
					+ "WHERE T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND (DEPT_ID =? OR INSTR(CONCAT(',',OTHER_DEPT)||',',?)>0) AND T1.ORG_ID=?";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, "," + deptId + ",");
		ps.setString(3, orgId);
		ResultSet rs = ps.executeQuery();
		String accountStr = "";
		while (rs.next()) {
			accountStr += rs.getString("ACCOUNT_ID") + ",";
		}
		if (accountStr.length() > 0) {
			accountStr = accountStr.substring(0, accountStr.length() - 1);
		}
		return accountStr;
	}
	//根据orgId 获取用户信息的json数组
	public JSONArray getOrgIdLogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT T1.DEPT_ID ,T1.USER_NAME FROM USER_INFO T1,ACCOUNT T2  WHERE T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND T1.ORG_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("deptId", rs.getString("DEPT_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr;
	}
	//判断accountId 是否关联了userinfo
	public int checkUserinfoLogic(Connection conn,String accountId,String orgId)throws SQLException{
		String queryStr="SELECT COUNT(*) AS NUM FROM USER_INFO WHERE ACCOUNT_ID=? AND ORG_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		int i=0;
		while(rs.next()){
			i=rs.getInt("NUM");
		}
		return i;
	}
}
