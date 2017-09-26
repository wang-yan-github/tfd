package api.adduser;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.apppower.logic.Appiconlogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.userinfo.data.UserInfo;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

public class AddTestUser {
	public static int USER_ID = 1;
	public static int ORG_ID = 1;
	
	public void addUserAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("人员添加开始");
		Connection dbConn = null;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			for (int i = 1; i < 100; i++) {
				String orgId = addOrg(dbConn);
				for (int j = 1; j <= 100; j++) {
					String deptId = addDept(dbConn,j,orgId);
					for (int k = 0; k < 100; k++) {
						addUser(dbConn,deptId,orgId,j);
					}
					dbConn.commit();
				}
			}
			System.out.println("**********************************************");
			System.out.println("**                                          **");
			System.out.println("**                   人员全部创建完成                                             **");
			System.out.println("**                                          **");
			System.out.println("**********************************************");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
	}
	
	
	public static void main(String[] args)throws Exception {
		Connection conn = DbPoolConnection.getInstance().getConnection();
		for (int i = 102; i < 1000; i++) {
			String orgId = addOrg(conn);
			for (int j = 1; j <= 100; j++) {
				String deptId = addDept(conn,j,orgId);
				for (int k = 0; k < 100; k++) {
					addUser(conn,deptId,orgId,j);
				}
				conn.commit();
			}
		}
		System.out.println("**********************************************");
		System.out.println("**                                          **");
		System.out.println("**                   全部创建完成                                             **");
		System.out.println("**                                          **");
		System.out.println("**********************************************");
	}

	private static String addOrg(Connection conn)throws Exception {
		String guId = GuId.getGuid();
		String orgAdmin = ORG_ID + "";
		String orgName = "公司"+ORG_ID;
		Account account = new Account();
		account.setAccountId(ORG_ID+"");
		account.setPassWord(MD5Util.getMD5("123456"));
		account.setPasswordType("1");
		account.setTheme("1");
		account.setUserPriv("3D159430-CD93-9CEF-15A8-C5573DAA080F");
		account.setNotLogin("0");
		account.setByName("");
		account.setLanguage("1");
		account.setOrgId(guId);
		AccountLogic accountLogic = new AccountLogic();
		accountLogic.addAccountLogic(conn, account);
		String queryStr = "INSERT INTO ORG_CONFIG (ORG_ID,ORG_NAME,ORG_ADMIN,CREATE_TIME,TYPE) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, guId);
		ps.setString(2, orgName);
		ps.setString(3, orgAdmin);
		ps.setString(4, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(5, "PC");
		ps.executeUpdate();
		ps.close();
		ORG_ID++;
		return guId;
	}

	private static String addDept(Connection conn,int j,String orgId)throws Exception {
		Department dept = new Department();
		String deptId = GuId.getGuid();
		String deptName = "部门" + j;
		dept.setOrgId(orgId);
		dept.setDeptId(deptId);
		dept.setDeptName(deptName);
		dept.setOrgleaveId("0");
		dept.setDeptLongId("0/"+deptId);
		dept.setDeptLongName("公司"+ORG_ID+"/部门"+j);
		String queryStr = "INSERT INTO DEPARTMENT ("
				+ "			DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_NAME,"
				+ "			ORG_ID,DEPT_LONG_ID)"
				+ "		VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dept.getDeptId());
		ps.setString(2, dept.getDeptName());
		ps.setString(3, dept.getOrgLeaveId());
		ps.setString(4, dept.getDeptLongName());
		ps.setString(5, dept.getOrgId());
		ps.setString(6, dept.getDeptLongId());
		ps.executeUpdate();
		ps.close();
		return deptId;
	}

	private static void addUser(Connection conn,String deptId,String orgId,int j)throws Exception {
		addAccount(conn,deptId,orgId);
		UserInfo userInfo = new UserInfo();
		String userId = GuId.getGuid();
		userInfo.setUserId(userId);
	    userInfo.setAccountId("用户"+USER_ID);
	    userInfo.setUserName(USER_ID+"");
	    userInfo.setSex("女");
	    userInfo.setDeptId(deptId);
	    int num = (ORG_ID-2)*10000+(j-1)*100+1;
	    userInfo.setLeadId("用户"+num);
	    userInfo.setPostPriv("3D159430-CD93-9CEF-15A8-C5573DAA080F");
	    userInfo.setManageDept("1");
	    userInfo.setLeadLeave("6C771064-C41C-8F6F-9823-C70B1F2A9348");
	    userInfo.setOrgId(orgId);
	    
	    String queryStr = "INSERT INTO USER_INFO (USER_ID,ACCOUNT_ID,USER_NAME,SEX,DEPT_ID,LEAD_ID,POST_PRIV,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,E_MAILE,WORK_ID,MANAGE_DEPT,OTHER_DEPT,LEAD_LEAVE,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userInfo.getUserId());
		ps.setString(2, userInfo.getAccountId());
		ps.setString(3, userInfo.getUserName());
		ps.setString(4, userInfo.getSex());
		ps.setString(5, userInfo.getDeptId());
		ps.setString(6, userInfo.getLeadId());
		ps.setString(7, userInfo.getPostPriv());
		ps.setString(8, userInfo.getOtherPriv());
		ps.setString(9, userInfo.getHomeAdd());
		ps.setString(10, userInfo.getHomeTel());
		ps.setString(11, userInfo.getMobileNo());
		ps.setString(12, userInfo.getQq());
		ps.setString(13, userInfo.geteMail());
		ps.setString(14, userInfo.getWorkId());
		ps.setString(15, userInfo.getManageDept());
		ps.setString(16, userInfo.getOtherDept());
		ps.setString(17, userInfo.getLeadLeave());
		ps.setString(18, userInfo.getOrgId());
		ps.executeUpdate();
		ps.close();
		USER_ID++;
	}

	private static void addAccount(Connection conn, String deptId,String orgId)throws Exception{
		String accountId = "用户"+USER_ID;
		Account account = new Account();
		account.setAccountId(accountId);
		account.setPassWord(MD5Util.getMD5("123456"));
		account.setPasswordType("1");
		account.setTheme("1");
		account.setUserPriv("3D159430-CD93-9CEF-15A8-C5573DAA080F");
		account.setNotLogin("0");
		account.setByName("");
		account.setLanguage("1");
		account.setOrgId(orgId);
		String queryStr = "INSERT INTO ACCOUNT(ACCOUNT_ID,PASSWORD_TYPE,PASS_WORD,THMEM,USER_ID,USER_PRIV,PAGE_PRIV,NOT_LOGIN,"
				+ "LANGUAGE,BY_NAME,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getPasswordType());
		ps.setString(3, account.getPassWord());
		ps.setString(4, account.getTheme());
		ps.setString(5, account.getUserId());
		ps.setString(6, account.getUserPriv());
		ps.setString(7, account.getPagePriv());
		ps.setString(8, account.getNotLogin());
		ps.setString(9, account.getLanguage());
		ps.setString(10, account.getByName());
		ps.setString(11, account.getOrgId());
		ps.executeUpdate();
		ps.close();
	}
	
}
