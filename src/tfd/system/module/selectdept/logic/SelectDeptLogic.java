package tfd.system.module.selectdept.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.module.PrivConst;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;

public class SelectDeptLogic {
	public BaseDao dao = new BaseDaoImpl();


	public JSONArray getDeptTreeOfWorkflowPriv(Account account,String deptParent,String flowId, int prcsId,Connection conn) throws Exception {
		JSONArray treeList = new JSONArray();
		
		PreparedStatement stmt =null;
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
				sql = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_ID,DEPT_LONG_NAME,"
						+ "		(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT,"
						+ "		("
						+ "			SELECT COUNT(1) FROM USER_INFO WHERE DEPT_ID=D.DEPT_ID "
						+ "		) AS USER_COUNT "
						+ " FROM DEPARTMENT D"
						+ "	WHERE ORG_ID=? AND ORG_LEAVE_ID=?"
						+ "		AND("
						+ "			SELECT COUNT(1) FROM USER_INFO UI "
						+ "					WHERE (SELECT DEPT_LONG_ID FROM DEPARTMENT WHERE DEPT_ID=UI.DEPT_ID) LIKE CONCAT(D.DEPT_LONG_ID,'%')"
						+ "		)>0 ";
				
			}else{
				
				sql = "SELECT DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_ID,DEPT_LONG_NAME,"
						+ "		(SELECT COUNT(1) FROM DEPARTMENT WHERE ORG_LEAVE_ID=D.DEPT_ID) AS CHILD_COUNT,"
						+ "		("
						+ "			SELECT COUNT(1) FROM USER_INFO WHERE DEPT_ID=D.DEPT_ID "
						+ "						AND (ACCOUNT_ID IN ('"+SysTool.join(userPriv.split(","), "','")+"') "
						+ "								OR POST_PRIV IN ('"+SysTool.join(rolePriv.split(","), "','")+"')"
						+ "								OR DEPT_ID IN ('"+SysTool.join(deptPriv.split(","), "','")+"')"
						+ "							)"
						+ "		) AS USER_COUNT "
						+ " FROM DEPARTMENT D"
						+ "	WHERE ORG_ID=? AND ORG_LEAVE_ID=?"
						+ "		AND("
						+ "			SELECT COUNT(1) FROM USER_INFO UI "
						+ "					WHERE (SELECT DEPT_LONG_ID FROM DEPARTMENT WHERE DEPT_ID=UI.DEPT_ID) LIKE CONCAT(D.DEPT_LONG_ID,'%')"
						+ "						AND (ACCOUNT_ID IN ('"+SysTool.join(userPriv.split(","), "','")+"') "
						+ "								OR POST_PRIV IN ('"+SysTool.join(rolePriv.split(","), "','")+"')"
						+ "								OR D.DEPT_ID IN ('"+SysTool.join(deptPriv.split(","), "','")+"')"
						+ "							)"
						+ "		)>0 ";
			}
			
			
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, account.getOrgId());
			stmt.setString(2, deptParent);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int deptChildCount=rs.getInt("CHILD_COUNT");
				int userCount=rs.getInt("USER_COUNT");
				
				JSONObject treeLeaf = new JSONObject();
				treeLeaf.accumulate("id", rs.getString("DEPT_ID"));
				treeLeaf.accumulate("pid", rs.getString("ORG_LEAVE_ID"));
				treeLeaf.accumulate("name", rs.getString("DEPT_NAME"));
				treeLeaf.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
				treeLeaf.accumulate("isParent",deptChildCount>0);

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
	
		
	// 获取顶级部门
	public String getDeptTopJsonLogic(Connection conn, Account account)
			throws SQLException {
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT DEPT_ID,DEPT_NAME FROM DEPARTMENT WHERE ORG_LEAVE_ID='0' AND ORG_ID=?";
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			json.accumulate("isParent", "true");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	// 获取子部门
	public String getDeptChildJsonLogic(Connection conn, Account account,
			String deptId) throws SQLException {
		DeptLogic deptLogic = new DeptLogic();
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT DEPT_ID,DEPT_NAME FROM DEPARTMENT WHERE ORG_ID=? AND ORG_LEAVE_ID=?";
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, deptId);
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("name", rs.getString("DEPT_NAME"));
			if (deptLogic.deptIsParent(conn, rs.getString("DEPT_ID"))) {
				json.accumulate("isParent", "true");
			} else {
				json.accumulate("isParent", "false");
			}
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();

	}

	public String serachdeptLogic(Connection conn, Account account,
			String deptName) throws SQLException {
		DeptLogic deptLogic = new DeptLogic();
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT DEPT_ID,DEPT_NAME FROM DEPARTMENT WHERE ORG_ID=? AND DEPT_NAME LIKE ? ";
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, "%"+deptName+"%");
		rs = ps.executeQuery();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("name", rs.getString("DEPT_NAME"));
			if (deptLogic.deptIsParent(conn, rs.getString("DEPT_ID"))) {
				json.accumulate("isParent", "true");
			} else {
				json.accumulate("isParent", "false");
			}
			json.accumulate("icon", "/tfd/system/styles/images/org/dept.png");
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();

	}
}
