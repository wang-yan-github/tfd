package tfd.system.module.selectuser.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import tfd.system.module.PrivConst;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SelectUserLogic {
	public BaseDao dao = new BaseDaoImpl();
	
	public String getSelectUserJsonLogic(Connection conn, String deptId,
			Account account) throws SQLException {
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME,T1.SEX AS SEX FROM USER_INFO T1,ACCOUNT T2 WHERE T1.ACCOUNT_ID =T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND T1.DEPT_ID=? AND T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("ACCOUNT_ID"));
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

	public String searchNameUserLogic(Connection conn, String searchName,Account account) throws Exception {
		JSONArray userList = new JSONArray();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql = "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME,T1.SEX AS SEX FROM USER_INFO T1,ACCOUNT T2 WHERE T1.ACCOUNT_ID =T1.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND  T1.USER_NAME LIKE ? AND T1.ORG_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchName+"%");
			stmt.setString(2, account.getOrgId());
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				JSONObject user = new JSONObject();
				user.accumulate("id", rs.getString("ACCOUNT_ID"));
				user.accumulate("name", rs.getString("USER_NAME"));
				if (rs.getString("SEX").equals("男")) {
					user.accumulate("icon", "/tfd/system/styles/images/org/U00.png");
				} else {
					user.accumulate("icon", "/tfd/system/styles/images/org/U10.png");
				}
				
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return userList.toString();
	}

	public JSONArray getWorkFlowUserJsonLogic(Connection conn, String deptId,Account account, String flowId, int prcsId,String userName) throws Exception {
		JSONArray userList = new JSONArray();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			
			FlowProcess flowProcess = flowProcessLogic.getFlowProcessLogic(conn, flowId, prcsId);
			String userPriv = flowProcess.getUserPriv()==null?"":flowProcess.getUserPriv();
			String deptPriv = flowProcess.getDeptPriv()==null?"":flowProcess.getDeptPriv();
			String rolePriv = flowProcess.getPrivId()==null?"":flowProcess.getPrivId();
			
			String sql=null;
			if (
					userPriv.equals(PrivConst.USER_ALL)||
					deptPriv.equals(PrivConst.DEPT_ALL)||
					rolePriv.equals(PrivConst.PRIV_ALL)
			) {
				sql = "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME,T1.SEX AS SEX FROM USER_INFO T1,ACCOUNT T2"
						+ "				WHERE T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0'  AND T1.ORG_ID=?";
			}else{
				
				sql = "SELECT DISTINCT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME,T1.SEX AS SEX FROM USER_INFO T1,ACCOUNT T2"
						+ "				WHERE T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0'  AND T1.ORG_ID=?"
						+ "					 AND (T1.ACCOUNT_ID IN ('"+SysTool.join(userPriv.split(","), "','")+"') "
						+ "						  OR T1.DEPT_ID IN ('"+SysTool.join(deptPriv.split(","), "','")+"') "
						+ "			              OR T1.POST_PRIV IN ('"+SysTool.join(rolePriv.split(","), "','")+"')"
						+ "						 )";	
			}
			
			if (deptId!=null&&!deptId.equals("")) {
				sql+=" AND DEPT_ID = ?";
			}
			if (userName!=null&&!userName.equals("")) {
				sql+=" AND USER_NAME LIKE ?";
			}
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getOrgId());
			int stmtIndex=1;
			
			if (deptId!=null&&!deptId.equals("")) {
				stmt.setString(++stmtIndex, deptId);
			}
			
			if (userName!=null&&!userName.equals("")) {
				stmt.setString(++stmtIndex, "%"+userName+"%");
			}
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				JSONObject user = new JSONObject();
				user.accumulate("id", rs.getString("ACCOUNT_ID"));
				user.accumulate("name", rs.getString("USER_NAME"));
				if (rs.getString("SEX").equals("男")) {
					user.accumulate("icon", "/tfd/system/styles/images/org/U00.png");
				} else {
					user.accumulate("icon", "/tfd/system/styles/images/org/U10.png");
				}
				
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return userList;
	}
}
