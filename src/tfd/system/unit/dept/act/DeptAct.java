package tfd.system.unit.dept.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.dept.logic.DeptLogic;

public class DeptAct {
	private DeptLogic logic = new DeptLogic();
	public void getDeptTreeAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			writer=response.getWriter();
			writer.print(logic.getDeptTreeOfAll(conn,account));
		} catch (Exception e) {
			throw e;
		} finally {
			new DbOp().connClose(conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getDeptTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String deptParent=request.getParameter("id")==null?"0":request.getParameter("id");
			
			JSONArray treeList= logic.getDeptTree(account, deptParent, conn);
			
			writer=response.getWriter();
			writer.print(treeList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new DbOp().connClose(conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getDeptTreeOfChose(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String deptParent=request.getParameter("id")==null?"0":request.getParameter("id");
			
			JSONArray treeList= logic.getDeptTree(account, deptParent, conn);
			if (deptParent.equals("0")) {
				JSONObject treeLeaf=new JSONObject();
				treeLeaf.accumulate("id","-1");
				treeLeaf.accumulate("pid", "");
				treeLeaf.accumulate("text", "无");
				treeLeaf.accumulate("icon", "");
				treeLeaf.accumulate("isParent", false);
				treeLeaf.accumulate("deptLongId","");
				treeLeaf.accumulate("deptLongName","");
				treeList.add(0, treeLeaf);
			}
			
			writer=response.getWriter();
			writer.print(treeList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new DbOp().connClose(conn);
			SysTool.closePrintWriter(writer);
		}
	}

	// 按管理权限获取部门
	public void getDeptTreeByUserPrivAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute(
				"ACCOUNT_ID");
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			DeptLogic deptLogic = new DeptLogic();
			returnData = deptLogic.getDeptTreeByUserPrivJson(dbConn, account);
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

	/**
	 * 获取部门信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDeptInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			String deptId = request.getParameter("deptId");
			JSONObject deptInfo = logic.getDeptInfoLogic(conn, deptId);
			
			writer=response.getWriter();
			writer.print(deptInfo.toString());
		} catch (Exception e) {
			throw e;
		}finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getDeptUserCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			String deptId = request.getParameter("deptId");
			JSONObject deptInfo = logic.getDeptInfoLogic(conn, deptId);
			
			writer=response.getWriter();
			writer.print(deptInfo.toString());
		} catch (Exception e) {
			throw e;
		}finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}

	public void updateDept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter writer=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			String deptId=request.getParameter("deptId");
			String deptName=request.getParameter("deptName");
			String orgLeaveId=request.getParameter("orgLeaveId");
			String parentDeptLongId=request.getParameter("parentDeptLongId");
			String parentDeptLongName=request.getParameter("parentDeptLongName");
			
			String deptLongId=parentDeptLongId+"/"+deptId;
			String deptLongName=deptName;
			if (!orgLeaveId.equals("0")) {
				deptLongName=parentDeptLongName+"/"+deptName;
			}
			
			Department dept = new Department();
			dept.setOrgId(account.getOrgId());
			dept.setDeptId(deptId);
			dept.setDeptName(deptName);
			dept.setOrgleaveId(orgLeaveId);
			dept.setDeptLongId(deptLongId);
			dept.setDeptLongName(deptLongName);
			dept.setDeptLead(request.getParameter("deptLead"));
			dept.setDeptTel(request.getParameter("deptTel"));
			dept.setDeptFax(request.getParameter("deptFax"));
			dept.setDeptEmail(request.getParameter("deptEmail"));
			dept.setDeptFunction(request.getParameter("deptFunction"));
			
			
			int result = logic.updateDeptInfo(conn, dept);
			if (result>0) {
				conn.commit();
			}else{
				logic.dao.rollback(conn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			logic.dao.rollback(conn);
			e.printStackTrace();
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}

	// 添加部门
	public void addDept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			String deptId=GuId.getGuid();
			String deptName=request.getParameter("deptName");
			String orgLeaveId=request.getParameter("orgLeaveId");
			String parentDeptLongId=request.getParameter("parentDeptLongId");
			String parentDeptLongName=request.getParameter("parentDeptLongName");
			
			String deptLongId=parentDeptLongId+"/"+deptId;
			String deptLongName=deptName;
			if (!orgLeaveId.equals("0")) {
				deptLongName=parentDeptLongName+"/"+deptName;
			}
			
			Department dept = new Department();
			dept.setOrgId(account.getOrgId());
			dept.setDeptId(deptId);
			dept.setDeptName(deptName);
			dept.setOrgleaveId(orgLeaveId);
			dept.setDeptLongId(deptLongId);
			dept.setDeptLongName(deptLongName);
			dept.setDeptLead(request.getParameter("deptLead"));
			dept.setDeptTel(request.getParameter("deptTel"));
			dept.setDeptFax(request.getParameter("deptFax"));
			dept.setDeptEmail(request.getParameter("deptEmail"));
			dept.setDeptFunction(request.getParameter("deptFunction"));
			
			
			int result = logic.addDeptInfoLogic(conn, dept);
			if (result>0) {
				conn.commit();
			}else{
				logic.dao.rollback(conn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
			
		} catch (Exception e) {
			logic.dao.rollback(conn);
			e.printStackTrace();
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}

	// 删除部门
	public void deleteDept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter writer=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=request.getParameter("deptId");
			
			int result = logic.deleteDept(account, deptId, conn);
			if (result>0) {
				conn.commit();
			}else{
				logic.dao.rollback(conn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			logic.dao.rollback(conn);
			e.printStackTrace();
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}

	// 通过字符串得到部门名称的json数组
	public void getjsondeptNameStrAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		String returnData = "";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			DeptLogic deptLogic = new DeptLogic();
			String deptNameStr = request.getParameter("deptNameStr");
			returnData = deptLogic.getjsondeptNameStr(dbConn, deptNameStr);
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

	public void getDeptAllUserInfoCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=request.getParameter("deptId");
			
			int count= logic.getDeptAllUserInfoCount(account, deptId, conn);
			
			writer=response.getWriter();
			writer.print(count);
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getDeptTreeOfOnlineUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			String deptParent=request.getParameter("id");
			int deptChildCount=0;
			if (deptParent==null) {
				deptParent="0";
				deptChildCount=1;
			}else{
				deptChildCount=Integer.parseInt(request.getParameter("deptChildCount"));
			}

			JSONArray tree=new JSONArray();
			if (deptChildCount>0) {
				tree.addAll(logic.getDeptTreeOfOnlineUser(account,deptParent,conn));
			}
			tree.addAll(logic.getOnlineUserOfTree(account, deptParent, conn));
			writer=response.getWriter();
			writer.print(tree.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void getDeptTreeOfAllUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn = null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			String deptParent=request.getParameter("id");
			int deptChildCount=0;
			int userCount=0;
			if (deptParent==null) {
				deptParent="0";
				deptChildCount=1;
				userCount=1;
			}else{
				deptChildCount=Integer.parseInt(request.getParameter("deptChildCount"));
				userCount=Integer.parseInt(request.getParameter("userCount"));
			}

			JSONArray tree=new JSONArray();
			if (deptChildCount>0) {
				tree.addAll(logic.getDeptTreeOfAllUser(account,deptParent,conn));
			}
			if (userCount>0) {
				tree.addAll(logic.getAllUserOfTree(account, deptParent, conn));
			}
			
			writer=response.getWriter();
			writer.print(tree.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			logic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
}
