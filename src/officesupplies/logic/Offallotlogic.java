package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;

import officesupplies.data.Offallot;
import tfd.system.unit.account.data.Account;

public class Offallotlogic {
	/**
	 * 添加调拨记录
	 * Author:Wp
	 * @param conn
	 * @param allot
	 * @return
	 * @throws SQLException
	 */
	public int addallotlogic(Connection conn,Offallot allot)throws SQLException{
		String queryStr="INSERT INTO OFF_ALLOT (ALLOT_ID,RES_ID,LIBRARY_ID,CLASSIFY_ID,ALLOT_NUM,ALLOT_STATUS,APPROVAL_STAFF,ALLOT_STAFF,ORG_ID,ALLOT_TIME,DEPOT_STAFF) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, allot.getAllotId());
		ps.setString(2, allot.getResId());
		ps.setString(3, allot.getLibraryId());
		ps.setString(4, allot.getClassifyId());
		ps.setString(5, allot.getAllotNum());
		ps.setString(6, allot.getAllotStatus());
		ps.setString(7, allot.getApprovalStaff());
		ps.setString(8, allot.getAllotStaff());
		ps.setString(9, allot.getOrgId());
		ps.setString(10, allot.getAllotTime());
		ps.setString(11, allot.getDeptoStaff());
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
		public String getnotallotlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
			String queryStr="SELECT ALLOT_ID,(SELECT RES_NAME FROM OFF_RES T2 WHERE T2.RES_ID =T1.RES_ID ) AS RES_NAME ,RES_ID,LIBRARY_ID,CLASSIFY_ID,ALLOT_NUM,ALLOT_STATUS,APPROVAL_STAFF,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.ALLOT_STAFF) AS USER_NAME,ALLOT_STAFF,ALLOT_TIME FROM OFF_ALLOT T1 WHERE APPROVAL_STAFF =? AND ALLOT_STATUS=0 AND ORG_ID =?";
			String optStr= "[{'function':'approvalallot','name':'批准','parm':'ALLOT_ID,RES_ID'},{'function':'notapprovalallot','name':'不批准','parm':'ALLOT_ID,RES_ID'},{'function':'delapply','name':'删除','parm':'ALLOT_ID'}]";
			JSONArray optArrJson = JSONArray.fromObject(optStr);
			PageTool pageTool = new PageTool();
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
			return json.toString();
		}
		/**
		 * 根据审批人查看待审批的办公用品你信息表
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
				public String getallotlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
					String queryStr="SELECT ALLOT_ID,(SELECT RES_NAME FROM OFF_RES T2 WHERE T2.RES_ID =T1.RES_ID ) AS RES_NAME ,RES_ID,LIBRARY_ID,CLASSIFY_ID,ALLOT_NUM,ALLOT_STATUS,APPROVAL_STAFF,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.ALLOT_STAFF) AS USER_NAME,ALLOT_STAFF,ALLOT_TIME FROM OFF_ALLOT T1 WHERE APPROVAL_STAFF =? AND ALLOT_STATUS !=0 AND ORG_ID =?";
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
				 * 根据allotId 修改审批状态
				 * Author:Wp
				 * @param conn
				 * @param allot
				 * @return
				 * @throws SQLException
				 */
		public int updateStatuslogic(Connection conn,Offallot allot)throws SQLException{
			String queryStr="UPDATE OFF_ALLOT SET ALLOT_STATUS=? WHERE ALLOT_ID =? AND ORG_ID =?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, allot.getAllotStatus());
			ps.setString(2, allot.getAllotId());
			ps.setString(3, allot.getOrgId());
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
		/**
		 * 根据allotId 获取调拨信息
		 * Author:Wp
		 * @param conn
		 * @param allotId
		 * @param orgId
		 * @return
		 * @throws SQLException
		 */
		public String getIdallotlogic(Connection conn,String allotId,String orgId)throws SQLException{
			String queryStr="SELECT ALLOT_ID,(SELECT RES_NAME FROM OFF_RES T2 WHERE T2.RES_ID =T1.RES_ID ) AS RES_NAME ,RES_ID,LIBRARY_ID,CLASSIFY_ID,ALLOT_NUM,ALLOT_STATUS,APPROVAL_STAFF,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.ALLOT_STAFF) AS USER_NAME,ALLOT_STAFF,ALLOT_TIME,DEPOT_STAFF FROM OFF_ALLOT T1  WHERE ALLOT_ID =? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, allotId);
			ps.setString(2, orgId);
			ResultSet rs=null;
			rs=ps.executeQuery();
			JSONObject json=new JSONObject();
			if(rs.next()){
				json.accumulate("allotId", rs.getString("ALLOT_ID"));
				json.accumulate("resName", rs.getString("RES_NAME"));
				json.accumulate("depotStaff", rs.getString("DEPOT_STAFF"));
				json.accumulate("allotNum", rs.getString("ALLOT_NUM"));
				json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
				json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
				json.accumulate("allotStaff", rs.getString("ALLOT_STAFF"));
			}
			rs.close();
			ps.close();
			return json.toString();
		}
}
