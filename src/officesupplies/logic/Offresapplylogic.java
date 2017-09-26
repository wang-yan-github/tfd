package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import officesupplies.data.Offresapply;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.PageTool;

public class Offresapplylogic {
	/**
	 * 添加申领办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int addapplylogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="INSERT INTO OFF_RESAPPLY (APPLY_ID,LIBRARY_ID,CLASSIFY_ID,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,CREATE_USER,APPLY_TIME,ORG_ID,DISPATCH_STAFF)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyId());
		ps.setString(2, apply.getLibraryId());
		ps.setString(3, apply.getClassifyId());
		ps.setString(4, apply.getResId());
		ps.setString(5, apply.getResType());
		ps.setString(6, apply.getApplyNum());
		ps.setString(7, apply.getApprovalStaff());
		ps.setString(8, apply.getApplyRemary());
		ps.setString(9, apply.getCreateUser());
		ps.setString(10, apply.getApplyTime());
		ps.setString(11, apply.getOrgId());
		ps.setString(12, apply.getDispatchStaff());
		int i=ps.executeUpdate();
		ps.close();
		return i;	
	}
	/**
	 * 根据Id查询申领办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public String getapplyIdAct(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME ,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,CREATE_USER,APPLY_TIME FROM OFF_RESAPPLY T1 WHERE APPLY_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyId());
		ps.setString(2, apply.getOrgId());
		ResultSet rs=null;
		AccountLogic acclogic=new AccountLogic();
		rs=ps.executeQuery();
		JSONObject json =new JSONObject();
		if(rs.next()){
			json.accumulate("applyId", rs.getString("APPLY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("classifyName", rs.getString("CLASSIFY_NAME"));
			json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
			json.accumulate("resName", rs.getString("RES_NAME"));
			json.accumulate("resId", rs.getString("RES_ID"));
			json.accumulate("resType", rs.getString("RES_TYPE"));
			json.accumulate("applyNum", rs.getString("APPLY_NUM"));
			json.accumulate("approvalStaff", rs.getString("APPROVAL_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("APPROVAL_STAFF"));
			json.accumulate("userName", userName);
			json.accumulate("applyRemary", rs.getString("APPLY_REMARY"));
			json.accumulate("createUser", rs.getString("CREATE_USER"));
			json.accumulate("applyTime", rs.getString("APPLY_TIME"));
		}
		rs.close();
		ps.close();
		return json.toString();
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
		String queryStr="UPDATE OFF_RESAPPLY  SET  APPROVAL_STAFF =? WHERE LIBRARY_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalStaff);
		ps.setString(2, libId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改申领办公用品信息
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int updateapplylogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="UPDATE OFF_RESAPPLY SET LIBRARY_ID =?,CLASSIFY_ID=?,RES_ID=?,RES_TYPE=?,APPLY_NUM=?,APPROVAL_STAFF=?,APPLY_REMARY=?,CREATE_USER=?,APPLY_TIME=?,DISPATCH_STAFF=? WHERE APPLY_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getLibraryId());
		ps.setString(2, apply.getClassifyId());
		ps.setString(3, apply.getResId());
		ps.setString(4, apply.getResType());
		ps.setString(5, apply.getApplyNum());
		ps.setString(6, apply.getApprovalStaff());
		ps.setString(7, apply.getApplyRemary());
		ps.setString(8, apply.getCreateUser());
		ps.setString(9, apply.getApplyTime());
		ps.setString(10, apply.getDispatchStaff());
		ps.setString(11, apply.getApplyId());
		ps.setString(12, apply.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;	
	}
	/**
	 * 根据审批人查看待审批的办公用品信息表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getnotapplylogic (Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME ,(SELECT BEFORESTOCK FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID )AS BEFORESTOCK,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.CREATE_USER) AS USER_NAME,CREATE_USER,APPLY_TIME FROM OFF_RESAPPLY T1 WHERE APPROVAL_STAFF =? AND APPLY_STATUS =0 AND ORG_ID =?";
		String optStr= "[{'function':'approvalapply','name':'批准','parm':'APPLY_ID,RES_ID,APPROVAL_STAFF,APPLY_NUM,BEFORESTOCK'},{'function':'notapprovalapply','name':'不批准','parm':'APPLY_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据createUser获取办公用品申领信息的列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getuserapplylogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME ,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.APPROVAL_STAFF) AS USER_NAME , CREATE_USER,APPLY_TIME,APPLY_STATUS FROM OFF_RESAPPLY T1 WHERE CREATE_USER =? AND ORG_ID =?";
		String optStr= "[{'function':'updateapply','name':'修改','parm':'APPLY_ID'},{'function':'delapply','name':'删除','parm':'APPLY_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据applyId删除办公用品申领信息
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int delapplylogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="DELETE FROM OFF_RESAPPLY WHERE APPLY_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyId());
		ps.setString(2, apply.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;	
	}
	/**代登记的情况下添加 申领信息
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int agentapplylogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="INSERT INTO OFF_RESAPPLY (APPLY_ID,LIBRARY_ID,CLASSIFY_ID,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,CREATE_USER,APPLY_TIME,APPLY_STATUS,ORG_ID,DISPATCH_STAFF)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyId());
		ps.setString(2, apply.getLibraryId());
		ps.setString(3, apply.getClassifyId());
		ps.setString(4, apply.getResId());
		ps.setString(5, apply.getResType());
		ps.setString(6, apply.getApplyNum());
		ps.setString(7, apply.getApprovalStaff());
		ps.setString(8, apply.getApplyRemary());
		ps.setString(9, apply.getCreateUser());
		ps.setString(10, apply.getApplyTime());
		ps.setString(11, apply.getApplyStatus());
		ps.setString(12, apply.getOrgId());
		ps.setString(13, apply.getDispatchStaff());
		int i=ps.executeUpdate();
		ps.close();
		return i;
		
	}
	/**
	 * 根据发放人获取申领记录
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getdisStafflogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME,(SELECT BEFORESTOCK FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS BEFORESTOCK,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.APPROVAL_STAFF) AS USER_NAME , CREATE_USER,APPLY_TIME,APPLY_STATUS ,GIVE_STATUS FROM OFF_RESAPPLY T1 WHERE DISPATCH_STAFF =? AND APPLY_STATUS =1 AND GIVE_STATUS=0 AND ORG_ID =?";
		String optStr= "[{'function':'resgive','name':'发放','parm':'APPLY_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据发放人员获取已发放办公用品的申领列表信息
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getalreadylogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME,(SELECT BEFORESTOCK FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS BEFORESTOCK,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.APPROVAL_STAFF) AS USER_NAME , CREATE_USER,APPLY_TIME,APPLY_STATUS ,GIVE_STATUS FROM OFF_RESAPPLY T1 WHERE DISPATCH_STAFF =? AND APPLY_STATUS =1 AND GIVE_STATUS=1 AND ORG_ID =?";
		String optStr= "[]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据applyId修改发放状态
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int updategivelogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="UPDATE OFF_RESAPPLY SET GIVE_STATUS =1 WHERE APPLY_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyId());
		ps.setString(2, apply.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据applyId修改审批状态
	 * Author:Wp
	 * @param conn
	 * @param apply
	 * @return
	 * @throws SQLException
	 */
	public int updateapprovallogic(Connection conn,Offresapply apply)throws SQLException{
		String queryStr="UPDATE OFF_RESAPPLY SET APPLY_STATUS =? WHERE APPLY_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, apply.getApplyStatus());
		ps.setString(2, apply.getApplyId());
		ps.setString(3, apply.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据applyId修改审批人员
	 * Author:Wp
	 * @param conn
	 * @param applyId
	 * @param approvalStaff
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int updateStafflogic(Connection conn,String applyId,String approvalStaff,String orgId)throws SQLException{
		String queryStr="UPDATE OFF_RESAPPLY SET APPROVAL_STAFF =? WHERE APPLY_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalStaff);
		ps.setString(2, applyId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据审批人查看已审批的办公用品信息表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
		public String alreadlyaplylogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
			String queryStr="SELECT APPLY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.LIBRARY_ID )AS LIBRARY_NAME ,LIBRARY_ID,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.CLASSIFY_ID )AS CLASSIFY_NAME, CLASSIFY_ID,(SELECT RES_NAME FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID ) AS RES_NAME,(SELECT BEFORESTOCK FROM OFF_RES T3 WHERE T3.RES_ID =T1.RES_ID )AS BEFORESTOCK,RES_ID,RES_TYPE,APPLY_NUM,APPROVAL_STAFF,APPLY_REMARY,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.CREATE_USER) AS USER_NAME,CREATE_USER,APPLY_TIME,GIVE_STATUS,APPLY_STATUS FROM OFF_RESAPPLY T1 WHERE APPROVAL_STAFF =? AND APPLY_STATUS !=0 AND ORG_ID =?";
			String optStr= "[]";
			JSONArray optArrJson = JSONArray.fromObject(optStr);
			PageTool pageTool = new PageTool();
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
			return json.toString();
		}
}
