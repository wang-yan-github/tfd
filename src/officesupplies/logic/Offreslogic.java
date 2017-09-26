package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import officesupplies.data.Offres;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;

public class Offreslogic {

	/**
	 * 添加办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public int addreslogic(Connection conn ,Offres res) throws SQLException{
		String queryStr="INSERT INTO OFF_RES (RES_ID,RES_TYPE,LIBRARY_ID,CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,APPROVE_STAFF,RES_FORMAT,ATTACH_ID,RES_UNIT,RES_PRICE,RES_SUPPLIER,DEPT_PRIV,USER_PRIV,ORG_ID,RES_NAME,DISPATCH_STAFF) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, res.getResId());
		ps.setString(2, res.getResType());
		ps.setString(3, res.getLibraryId());
		ps.setString(4, res.getClassifyId());
		ps.setString(5, res.getBeforStock());
		ps.setString(6, res.getMinStock());
		ps.setString(7, res.getMaxStock());
		ps.setString(8, res.getApproveStaff());
		ps.setString(9, res.getResFormat());
		ps.setString(10, res.getAttachId());
		ps.setString(11, res.getResUnit());
		ps.setString(12, res.getResPrice());
		ps.setString(13, res.getResSupplier());
		ps.setString(14, res.getDeptPriv());
		ps.setString(15, res.getUserPriv());
		ps.setString(16, res.getOrgId());
		ps.setString(17, res.getResName());
		ps.setString(18, res.getDispatchStaff());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id查询办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public String getIdreslogic(Connection conn ,Offres res) throws SQLException{
		String queryStr="SELECT RES_ID,RES_NAME,RES_TYPE,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID ) AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID ) AS CLASSIFY_NAME ,CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,APPROVE_STAFF,RES_FORMAT,ATTACH_ID,RES_UNIT,RES_PRICE,RES_SUPPLIER,DEPT_PRIV,USER_PRIV,DISPATCH_STAFF FROM OFF_RES T1 WHERE RES_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, res.getResId());
		ps.setString(2, res.getOrgId());
		ResultSet rs=null;
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		rs=ps.executeQuery();
		JSONObject json =new JSONObject();
		if(rs.next()){
			json.accumulate("resId", rs.getString("RES_ID"));
			json.accumulate("resName", rs.getString("RES_NAME"));
			json.accumulate("resType", rs.getString("RES_TYPE"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
			json.accumulate("classifyName", rs.getString("CLASSIFY_NAME"));
			json.accumulate("beforeStock", rs.getString("BEFORESTOCK"));
			json.accumulate("minStock", rs.getString("MINSTOCK"));
			json.accumulate("maxStock", rs.getString("MAXSTOCK"));
			json.accumulate("approveStaff", rs.getString("APPROVE_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("APPROVE_STAFF"));
			json.accumulate("userName", userName);
			String disName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
			json.accumulate("disName", disName);
			json.accumulate("dispatchStaff", rs.getString("DISPATCH_STAFF"));
			json.accumulate("resFormat", rs.getString("RES_FORMAT"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("resUnit", rs.getString("RES_UNIT"));
			json.accumulate("resPrice", rs.getString("RES_PRICE"));
			json.accumulate("resSupplier", rs.getString("RES_SUPPLIER"));
			json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
			json.accumulate("deptName", deptName);
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			String staffName=acclogic.getUserNameStr(conn, rs.getString("USER_PRIV"));
			json.accumulate("staffName", staffName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据Id删除办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public int delIdreslogic(Connection conn ,Offres res) throws SQLException{
		String queryStr="DELETE FROM OFF_RES WHERE RES_ID =? AND ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, res.getResId());
		ps.setString(2, res.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id修改办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public int updatereslogic(Connection conn ,Offres res) throws SQLException{
		String queryStr="UPDATE OFF_RES SET RES_NAME=?, RES_TYPE =?,LIBRARY_ID =?,CLASSIFY_ID=?,BEFORESTOCK=?,MINSTOCK =?,MAXSTOCK=?,APPROVE_STAFF=?,RES_FORMAT=?,ATTACH_ID=?,RES_UNIT=?,RES_PRICE=?,RES_SUPPLIER=?,DEPT_PRIV=?,USER_PRIV =? WHERE RES_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, res.getResName());
		ps.setString(2, res.getResType());
		ps.setString(3, res.getLibraryId());
		ps.setString(4, res.getClassifyId());
		ps.setString(5, res.getBeforStock());
		ps.setString(6, res.getMinStock());
		ps.setString(7, res.getMaxStock());
		ps.setString(8, res.getApproveStaff());
		ps.setString(9, res.getResFormat());
		ps.setString(10, res.getAttachId());
		ps.setString(11, res.getResUnit());
		ps.setString(12, res.getResPrice());
		ps.setString(13, res.getResSupplier());
		ps.setString(14, res.getDeptPriv());
		ps.setString(15, res.getUserPriv());
		ps.setString(16, res.getResId());
		ps.setString(17, res.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据libId 修改审批人员
	 * @param conn
	 * @param libId
	 * @param orgId
	 * @param approvalStaff
	 * @return
	 * @throws SQLException
	 */
	public int updateStaffLogic(Connection conn,String libId,String orgId,String approvalStaff)throws SQLException{
		String queryStr="UPDATE OFF_RES  SET  APPROVE_STAFF =? WHERE LIBRARY_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalStaff);
		ps.setString(2, libId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据分类库classlibId获取办公用品的列表
	 * Author:Wp
	 * @param conn
	 * @param classifyId
	 * @param orgId
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */

	public String getlibIdlogic(Connection conn,String classifyId,String orgId,int pageSize,int page,String sortOrder,String sortName,String accountId) throws Exception{
		String queryStr="SELECT RES_ID,RES_NAME,RES_TYPE,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID=T1.CLASSIFY_ID) AS CLASSIFY_NAME,CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID = T1.APPROVE_STAFF)AS USER_NAME,APPROVE_STAFF,RES_UNIT,RES_SUPPLIER FROM OFF_RES T1 WHERE CLASSIFY_ID =? AND T1.APPROVE_STAFF=? AND ORG_ID =?";
		String optStr= "[{'function':'updateres','name':'修改','parm':'RES_ID'},{'function':'delres','name':'删除','parm':'RES_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(classifyId);
		pramList.add(accountId);
		pramList.add(orgId);
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据条件查询办公用品
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String tremqueryreslogic(Connection conn,Offres res,int pageSize,int page,String sortOrder,String sortName) throws Exception{
		String queryStr="SELECT T1.ID AS ID ,RES_ID,RES_NAME,RES_TYPE,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,APPROVE_STAFF,T3.USER_NAME AS USER_NAME,RES_UNIT,RES_SUPPLIER FROM OFF_RES T1 LEFT JOIN USER_INFO T3 ON T3.ACCOUNT_ID=T1.APPROVE_STAFF WHERE APPROVE_STAFF =? AND T1.ORG_ID =?";
		List<String> pramList = new ArrayList<String>();
		pramList.add(res.getApproveStaff());
		pramList.add(res.getOrgId());
		if(!res.getResId().equals("")){
			queryStr+="AND RES_ID =? ";
			pramList.add(res.getResId());
		}
		if(!res.getLibraryId().equals(""))
		{
			queryStr+="AND LIBRARY_ID =? ";
			pramList.add(res.getLibraryId());
		}
		if(!res.getClassifyId().equals(""))
		{
			queryStr+="AND CLASSIFY_ID =? ";
			pramList.add(res.getClassifyId());
		}
		if(!res.getResName().equals(""))
		{
			queryStr+="AND RES_NAME LIKE ? ";
			pramList.add("%"+res.getResName()+"%");
		}
		String optStr= "[{'function':'updateres','name':'修改','parm':'RES_ID'},{'function':'delres','name':'删除','parm':'RES_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject Json=new JSONObject();
		Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
		return Json.toString();
	}
	/**
	 * 根据classifyId获取办公用品名称
	 * Author:Wp
	 * @param conn
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public String getresNamelogic(Connection conn ,Offres res) throws SQLException{
		String queryStr="SELECT RES_ID,RES_NAME,BEFORESTOCK FROM OFF_RES WHERE CLASSIFY_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, res.getClassifyId());
		ps.setString(2, res.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json =new JSONObject();
			json.accumulate("resId", rs.getString("RES_ID"));
			json.accumulate("resName", rs.getString("RES_NAME"));
			json.accumulate("beforeStock", rs.getString("BEFORESTOCK"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 当记录采购入库情况时根据resId 修改办公用品数量 
	 * Author:Wp
	 * @param conn
	 * @param resId
	 * @param orgId
	 * @param maintNum
	 * @return
	 * @throws SQLException
	 */
	public int updateresNumlogic(Connection conn,String resId,String orgId,int maintNum)throws SQLException{
		String queryStr="UPDATE OFF_RES SET BEFORESTOCK=BEFORESTOCK+? WHERE RES_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setInt(1, maintNum);
		ps.setString(2, resId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 报废情况下修改当前办公用品数量信息
	 * Author:Wp
	 * @param conn
	 * @param resId
	 * @param orgId
	 * @param maintNum
	 * @return
	 * @throws SQLException
	 */
	public int prrupdateresNumlogic(Connection conn,String resId,String orgId,int maintNum)throws SQLException{
		String queryStr="UPDATE OFF_RES SET BEFORESTOCK=BEFORESTOCK-? WHERE RES_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setInt(1, maintNum);
		ps.setString(2, resId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据申领人权限获取办公用品
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param deptId
	 * @param classifyId
	 * @return
	 * @throws SQLException
	 */
	public String getapplyreslogic(Connection conn,Account account,String deptId,String classifyId)throws SQLException{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT RES_ID,RES_NAME,RES_TYPE,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID ) AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID ) AS CLASSIFY_NAME ,CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,APPROVE_STAFF,RES_FORMAT,ATTACH_ID,RES_UNIT,RES_PRICE,RES_SUPPLIER,DEPT_PRIV,USER_PRIV,DISPATCH_STAFF FROM OFF_RES T1 WHERE CLASSIFY_ID=? AND (LOCATE(',userAll,',CONCAT(',',USER_PRIV,','))>0 OR LOCATE(?,CONCAT(',',USER_PRIV,','))>0 OR LOCATE(',deptAll,',CONCAT(',',DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',DEPT_PRIV,','))>0) AND ORG_ID =?";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT RES_ID,RES_NAME,RES_TYPE,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID ) AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID ) AS CLASSIFY_NAME ,CLASSIFY_ID,BEFORESTOCK,MINSTOCK,MAXSTOCK,APPROVE_STAFF,RES_FORMAT,ATTACH_ID,RES_UNIT,RES_PRICE,RES_SUPPLIER,DEPT_PRIV,USER_PRIV,DISPATCH_STAFF FROM OFF_RES T1 WHERE CLASSIFY_ID=? AND (INSTR(CONCAT(',',USER_PRIV)||',',',userAll,')>0 OR INSTR(CONCAT(',',USER_PRIV)||',',?)>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',',deptAll,')>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',?)>0) AND ORG_ID =?";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, classifyId);
		ps.setString(2, ","+account.getAccountId()+",");
		ps.setString(3, ","+deptId+",");
		ps.setString(4, account.getOrgId());
		ResultSet rs=null;
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json =new JSONObject();
			json.accumulate("resId", rs.getString("RES_ID"));
			json.accumulate("resName", rs.getString("RES_NAME"));
			json.accumulate("resType", rs.getString("RES_TYPE"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
			json.accumulate("classifyName", rs.getString("CLASSIFY_NAME"));
			json.accumulate("beforeStock", rs.getString("BEFORESTOCK"));
			json.accumulate("minStock", rs.getString("MINSTOCK"));
			json.accumulate("maxStock", rs.getString("MAXSTOCK"));
			json.accumulate("approveStaff", rs.getString("APPROVE_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("APPROVE_STAFF"));
			json.accumulate("userName", userName);
			String disName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
			json.accumulate("disName", disName);
			json.accumulate("dispatchStaff", rs.getString("DISPATCH_STAFF"));
			json.accumulate("resFormat", rs.getString("RES_FORMAT"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("resUnit", rs.getString("RES_UNIT"));
			json.accumulate("resPrice", rs.getString("RES_PRICE"));
			json.accumulate("resSupplier", rs.getString("RES_SUPPLIER"));
			json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
			json.accumulate("deptName", deptName);
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			String staffName=acclogic.getUserNameStr(conn, rs.getString("USER_PRIV"));
			json.accumulate("staffName", staffName);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
