package diary.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;







import java.util.ArrayList;
import java.util.List;

















import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import diary.data.Diary;

public class DiaryLogic {

	/**
	 * 添加日志
	 * Author: Wp
	 * @param conn
	 * @param dr（日志的对象）
	 * @return
	 * @throws SQLException
	 */
	public int addDiaryLogic(Connection conn,Diary dr)throws SQLException{
		String queryStr="INSERT INTO DIARY (ACCOUNT_ID,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,ORG_ID,DIARY_TITLETIME,SHARE_PRIV,SHARE_RANGE,LAUD,LAUD_NUM)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dr.getAccountId());
		ps.setString(2, dr.getDiaryName());
		ps.setString(3, dr.getDiaryContent());
		ps.setString(4, dr.getDiaryAnnex());
		ps.setString(5, dr.getDiaryDatetime());
		ps.setInt(6, dr.getDiaryMold());
		ps.setString(7, dr.getDiaryId());
		ps.setString(8,dr.getOrgId());
		ps.setString(9, dr.getDiaryTitleDatetime());
		ps.setString(10, dr.getSharePriv());
		ps.setInt(11, dr.getShareRange());
		ps.setString(12, dr.getLaud());
		ps.setInt(13, dr.getLaudNum());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 分页查询日志记录信息
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String selectDiaryLogic(Connection conn,Diary dr,int pagesize,int page,String sortOrder,String sortName)throws Exception{
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT *FROM (SELECT (SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID  AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,T1.ORG_ID,DIARY_TITLETIME,LOOK_STAFF,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? ) DIARY ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT *FROM (SELECT (SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0' )AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,T1.ORG_ID,DIARY_TITLETIME,LOOK_STAFF,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? ) DIARY ";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(","+dr.getAccountId()+",");
		pramList.add(dr.getAccountId());
		pramList.add(dr.getOrgId());
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 删除日志
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @return
	 * @throws SQLException
	 */
	public int deleteDiaryIdLogic(Connection conn,Diary dr)throws SQLException{
		String queryStr="DELETE FROM DIARY WHERE ACCOUNT_ID=? AND DIARY_ID=? AND ORG_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, dr.getAccountId());
		ps.setString(2, dr.getDiaryId());
		ps.setString(3, dr.getOrgId());
		int i=ps.executeUpdate();
		DiaryCommentsLogic diarycomm=new DiaryCommentsLogic();
		if(i!=0){
			diarycomm.deldiaryIdlogic(conn, dr.getDiaryId(), dr.getOrgId());
		}
		ps.close();
		return i;
	}
	/**
	 * 根据日志Id修改日志信息
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @return
	 * @throws SQLException
	 */
	public int updateDiaryLogic(Connection conn,Diary dr)throws SQLException{
		String queryStr="UPDATE DIARY SET DIARY_NAME=?,DIARY_CONTENT=?,DIARY_DATETIME=?,DIARY_ANNEX=?,SHARE_PRIV=?,DIARY_TITLETIME=? WHERE ACCOUNT_ID=? AND DIARY_ID=? AND ORG_ID=? ";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, dr.getDiaryName());
		ps.setString(2, dr.getDiaryContent());
		ps.setString(3, dr.getDiaryDatetime());
		ps.setString(4, dr.getDiaryAnnex());
		ps.setString(5, dr.getSharePriv());
		ps.setString(6, dr.getDiaryTitleDatetime());
		ps.setString(7,dr.getAccountId());
		ps.setString(8, dr.getDiaryId());
		ps.setString(9,dr.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据日志Id查询日志信息
	 * Author: Wp
	 * @param conn
	 * @param diaryId（日志Id）
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getIdDiaryLogic(Connection conn,String diaryId,Account account)throws SQLException{		
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountId="";
		if(manageDept.equals("2"))
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}else
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
		queryStr="SELECT T1.ID,T2.USER_NAME AS USER_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD"
				+ ",DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,SHARE_PRIV"
				+ " FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID  "
				+ "WHERE DIARY_ID=?"
				+ " AND ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0)"
						+ " OR T1.ACCOUNT_ID =? OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0"
						+ " OR SHARE_PRIV='userAll' OR SHARE_RANGE=1) AND T1.ORG_ID=? ";
		}else if(dbType.equals("oracle")){
			
			queryStr="SELECT T1.ID,T2.USER_NAME AS USER_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,"+
			" DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,SHARE_PRIV"
			+ " FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID "+
					" WHERE DIARY_ID=? AND ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID)AND DIARY_MOLD=0 ) "+
			" OR T1.ACCOUNT_ID =? "+
			" OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1) AND T1.ORG_ID=? ";
		}
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1,diaryId);
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			ps.setString(2, userInfo.getDeptId());
			ps.setString(3, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(4, leaveNoId);
			ps.setString(5, userInfo.getLeadId());
			ps.setString(6, account.getAccountId());
			ps.setString(7, ","+account.getAccountId()+",");
			ps.setString(8, account.getOrgId());
			
		}else 
		{
			ps.setString(2, userInfo.getLeadLeave());
			ps.setString(3, userInfo.getLeadId());
			ps.setString(4, account.getAccountId());
			ps.setString(5, ","+account.getAccountId()+",");
			ps.setString(6, account.getOrgId());
		}
		ResultSet rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		JSONObject json  = new JSONObject();
		if(rs.next()){
			json.accumulate("ID", rs.getInt("ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("diaryName", rs.getString("DIARY_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("diaryContent", rs.getString("DIARY_CONTENT"));
			json.accumulate("diaryMold", rs.getInt("DIARY_MOLD"));
			json.accumulate("diaryDatetime", rs.getString("DIARY_DATETIME"));
			json.accumulate("diaryId", rs.getString("DIARY_ID"));
			json.accumulate("attachId", rs.getString("DIARY_ANNEX"));
			json.accumulate("diaryTitletime", rs.getString("DIARY_TITLETIME"));
			if(rs.getString("LOOK_STAFF")==null){
				json.accumulate("lookStaff", "");	
			}else{
			json.accumulate("lookStaff", rs.getString("LOOK_STAFF"));
			}
			json.accumulate("sharePriv", rs.getString("SHARE_PRIV"));
			json.accumulate("sharePrivName", acclogic.getUserNameStr(conn, rs.getString("SHARE_PRIV")));
			String lookStaffName=acclogic.getUserNameStr(conn, rs.getString("LOOK_STAFF"));
			json.accumulate("lookStaffName", lookStaffName);
		}
		rs.close();
		ps.close();
		return json.toString(); 
	}
	/**
	 * 通过时间查询日志信息
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param endtime
	 * @param germtime
	 * @return
	 * @throws Exception 
	 */
	public String timeQueryDiaryLogic(Connection conn,Diary dr,int pagesize,int page,String sortOrder,String sortName,String endtime,String germtime)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT T1.ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1  LEFT JOIN USER_INFO ON T2.ACCOUNT_ID =T1.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_DATETIME >=? AND DIARY_DATETIME<=? ";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT T1.ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1  LEFT JOIN USER_INFO ON T2.ACCOUNT_ID =T1.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_DATETIME >=? AND DIARY_DATETIME<=? ";
		}
		String optStr= "[{'function':'selectdiaryId','name':'修改','parm':'DIARY_ID'},{'function':'deleteDiary','name':'删除','parm':'DIARY_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(","+dr.getAccountId()+",");
		pramList.add(dr.getAccountId());
		pramList.add(dr.getOrgId());
		pramList.add(germtime);
		pramList.add(endtime);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 判断是否有下一篇日志
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @return
	 * @throws SQLException
	 */
	public String judgedowndiaryLogic(Connection conn,Diary dr,Account account)throws SQLException{
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountId="";
		if(manageDept.equals("2"))
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}else
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			
			queryStr="SELECT ID,DIARY_DATETIME,DIARY_ID FROM DIARY T1  "
					+ "WHERE ID <? AND ACCOUNT_ID = ? "
					+" AND ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0)"
					+ " OR T1.ACCOUNT_ID =? OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0"
					+ " OR SHARE_PRIV='userAll' OR SHARE_RANGE=1) AND T1.ORG_ID=? "
					+ " ORDER BY DIARY_DATETIME DESC,ID DESC LIMIT 1";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT *FROM (SELECT ID,DIARY_DATETIME,DIARY_ID FROM DIARY T1 WHERE ID <? AND ACCOUNT_ID = ?"
					+ " ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0) "+
			" OR T1.ACCOUNT_ID =? "+
			" OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1) AND ORG_ID=? ORDER BY DIARY_DATETIME DESC,ID DESC)TMP WHERE ROWNUM=1";
		}
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setInt(1,dr.getId());
		ps.setString(2, dr.getAccountId());
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			ps.setString(3, userInfo.getDeptId());
			ps.setString(4, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(5, leaveNoId);
			ps.setString(6, userInfo.getLeadId());
			ps.setString(7, account.getAccountId());
			ps.setString(8, ","+account.getAccountId()+",");
			ps.setString(9, account.getOrgId());
			
		}else 
		{
			ps.setString(3, userInfo.getLeadLeave());
			ps.setString(4, userInfo.getLeadId());
			ps.setString(5, account.getAccountId());
			ps.setString(6, ","+account.getAccountId()+",");
			ps.setString(7, account.getOrgId());
		}
		ResultSet rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		while(rs.next()){
			json.accumulate("ID", rs.getInt("ID"));
			json.accumulate("diaryId", rs.getString("DIARY_ID"));
		}
		rs.close();
		ps.close();
		return json.toString(); 
	}
	/**
	 * 判断是否拥有上一篇日志
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @return
	 * @throws SQLException
	 */
		public String judgetonediaryLogic(Connection conn,Diary dr,Account account)throws SQLException{
			UserInfo userInfo = new UserInfo();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			String manageDept = userInfo.getManageDept();
			String accountId="";
			if(manageDept.equals("2"))
			{
				accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
			}else
			{
				accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
			}
			String dbType =DbPoolConnection.getInstance().getDbType();
			String queryStr="";
			if(dbType.equals("sqlserver")){
				
			}else if(dbType.equals("mysql")){
				queryStr="SELECT ID,DIARY_DATETIME,DIARY_ID FROM DIARY T1 WHERE ID >? AND ACCOUNT_ID = ?"
						+" AND ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0)"
						+ " OR T1.ACCOUNT_ID =? OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0"
						+ " OR SHARE_PRIV='userAll' OR SHARE_RANGE=1)  AND T1.ORG_ID=? ORDER BY DIARY_DATETIME ASC,ID ASC  LIMIT 1";
			}else if(dbType.equals("oracle")){
				
				queryStr="SELECT*FROM( SELECT ID,DIARY_DATETIME,DIARY_ID FROM DIARY T1 WHERE ID >? AND ACCOUNT_ID = ? "
						+ " ((EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0) "+
						" OR T1.ACCOUNT_ID =? "+
						" OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR "
						+ "TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1) AND ORG_ID=? ORDER BY DIARY_DATETIME ASC,ID ASC) TMP WHERE ROWNUM=1";
			}
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setInt(1,dr.getId());
			ps.setString(2, dr.getAccountId());
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2"))
			{
				ps.setString(3, userInfo.getDeptId());
				ps.setString(4, "%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(5, leaveNoId);
				ps.setString(6, userInfo.getLeadId());
				ps.setString(7, account.getAccountId());
				ps.setString(8, ","+account.getAccountId()+",");
				ps.setString(9, account.getOrgId());
				
			}else 
			{
				ps.setString(3, userInfo.getLeadLeave());
				ps.setString(4, userInfo.getLeadId());
				ps.setString(5, account.getAccountId());
				ps.setString(6, ","+account.getAccountId()+",");
				ps.setString(7, account.getOrgId());
			}
			ResultSet rs=ps.executeQuery();
			JSONObject json  = new JSONObject();
			while(rs.next()){
				json.accumulate("ID", rs.getInt("ID"));
				json.accumulate("diaryId", rs.getString("DIARY_ID"));
			}
			rs.close();
			ps.close();
			return json.toString(); 
		}
	
	/**
	 * 通过日志标题时间查询日志信息
	 * Author: Wp
	 * @param conn
	 * @param dr
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String titletimeQueryDiaryLogic(Connection conn,Diary dr,int pagesize,int page,String sortOrder,String sortName)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID  AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID  AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD,LAUD_NUM,T1.ACCOUNT_ID AS ACCOUNT_ID FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(","+dr.getAccountId()+",");
		pramList.add(dr.getAccountId());
		pramList.add(dr.getOrgId());
		pramList.add(dr.getDiaryTitleDatetime());
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 查询日志条数
	 * Author: Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getCountLogic(Connection conn,Account account)throws SQLException{
		
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountId="";
		if(manageDept.equals("2"))
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}else
		{
			accountId="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}
		String queryStr="";
		String dbType = DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql"))
		{
			 queryStr="SELECT COUNT(1) AS OTHERCOUNT FROM DIARY T1 WHERE ( (EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0 ) OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1 OR ACCOUNT_ID =?) AND ORG_ID = ? ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			 queryStr="SELECT COUNT(1) AS OTHERCOUNT FROM DIARY T1 WHERE ( (EXISTS(SELECT *FROM ("+accountId+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0 ) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1 OR ACCOUNT_ID=?) AND ORG_ID = ? ";
		}
		PreparedStatement ps=null;
		 ResultSet rs=null;
		 ps=conn.prepareStatement(queryStr);
		 UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		 if(manageDept.equals("2")){
				ps.setString(1, userInfo.getDeptId());
				ps.setString(2, "%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(3, leaveNoId);
				ps.setString(4, userInfo.getLeadId());
				ps.setString(5, ","+account.getAccountId()+",");
				ps.setString(6, account.getAccountId());
				ps.setString(7, account.getOrgId());
		 }else{
			 ps.setString(1, userInfo.getLeadLeave());
			ps.setString(2, userInfo.getLeadId());
			ps.setString(3, ","+account.getAccountId()+",");
			ps.setString(4, account.getAccountId());
			ps.setString(5, account.getOrgId());
		 }
		 rs=ps.executeQuery();
		 JSONObject json  = new JSONObject();
		 int allCount = 0;
		 if(rs.next()){
			 allCount=rs.getInt("OTHERCOUNT");
			 json.accumulate("allCount", rs.getInt("OTHERCOUNT"));
		 }
		 int myCount=0;
		 queryStr="SELECT COUNT(1)AS MYCOUNT FROM DIARY WHERE ACCOUNT_ID = ? AND ORG_ID = ?";
		 ps=conn.prepareStatement(queryStr);
		 ps.setString(1, account.getAccountId());
		 ps.setString(2, account.getOrgId());
		 rs=ps.executeQuery();
		 if(rs.next()){
			 myCount=rs.getInt("MYCOUNT");
			 json.accumulate("myCount", rs.getInt("MYCOUNT"));
		 }
		 int otherCount=allCount - myCount;
		 json.accumulate("otherCount", otherCount);
		rs.close();
		ps.close();
		return json.toString();
	}
	public String getuserCountLogic(Connection conn,String accountId,String orgId)throws SQLException{		
		String queryStr="SELECT COUNT(1) AS COUNTNUM FROM DIARY T1 WHERE DIARY_MOLD=0 AND T1.ACCOUNT_ID =? AND T1.ORG_ID=?";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		if(rs.next()){
			json.accumulate("countnum", rs.getString("COUNTNUM"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	
	
	
	/**
	 * 通过权限查看其他人日志
	 * 
	 */
	
		/**
		 * 
		 * 分页查询日志信息（所有日志）
		 * Author: Wp
		 * @param conn
		 * @param account
		 * @param pagesize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @return
		 * @throws Exception 
		 */
		public String selectotherDiaryLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName)throws Exception{
			UserInfo userInfo = new UserInfo();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			String manageDept = userInfo.getManageDept();
			String accountStr="";
			if(manageDept.equals("2"))
			{
				accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
			}else
			{
				accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
			}
			String dbType =DbPoolConnection.getInstance().getDbType();
			String queryStr="";
			if(dbType.equals("sqlserver")){
				
			}else if(dbType.equals("mysql")){
				queryStr="SELECT *FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE "
			+" (( "
			+ "EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0 "
					+ ")  OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0"
					+ " OR SHARE_PRIV='userAll' OR SHARE_RANGE=1"
					+ " OR T1.ACCOUNT_ID=?) AND T1.ORG_ID=? ) DIARY ";
			}else if(dbType.equals("oracle")){
				queryStr="SELECT *FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE "
						+" ( "
						+ "( EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID)AND DIARY_MOLD=0 "
								+ ") OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1 "
								+ " OR T1.ACCOUNT_ID=? ) AND T1.ORG_ID=? ) DIARY ";
			}
			String accountId=","+account.getAccountId()+",";
			JSONArray optArrJson = new JSONArray();
			String optStr= "[]";
			optArrJson=JSONArray.fromObject(optStr);
			List<String> pramList = new ArrayList<String>();
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2"))
			{
				pramList.add(accountId);
				pramList.add(userInfo.getDeptId());
				pramList.add("%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				pramList.add(leaveNoId);
				pramList.add(userInfo.getLeadId());
				pramList.add(accountId);
				pramList.add(account.getAccountId());
				pramList.add(account.getOrgId());
			}else
			{
				pramList.add(accountId);
				pramList.add(userInfo.getLeadLeave());
				pramList.add(userInfo.getLeadId());
				pramList.add(accountId);
				pramList.add(account.getAccountId());
				pramList.add(account.getOrgId());
			}
			PageTool pageTool = new PageTool();
			JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
			return json.toString();
		}
		/**
		 * 通过日志标题时间查询日志信息
		 * Author: Wp
		 * @param conn
		 * @param account
		 * @param pagesize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @param titletime
		 * @return
		 * @throws Exception 
		 */
		public String othertitletimeQueryDiaryLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String titletime)throws Exception{
			UserInfo userInfo = new UserInfo();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			String manageDept = userInfo.getManageDept();
			String accountStr="";
			if(manageDept.equals("2"))
			{
				accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
			}else
			{
				accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
			}
			String dbType =DbPoolConnection.getInstance().getDbType();
			String queryStr="";
			if(dbType.equals("sqlserver")){
				
			}else if(dbType.equals("mysql")){
				queryStr="SELECT *FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE "
							+" ( ( EXISTS(SELECT *FROM ("+accountStr+") T4 WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID )AND DIARY_MOLD=0 ) OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1  OR T1.ACCOUNT_ID=? ) AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
			}else if(dbType.equals("oracle"))
			{				
				queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE  "
						+" (( EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0 ) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1 OR T1.ACCOUNT_ID=? ) AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
			}
			JSONArray optArrJson = new JSONArray();
			String optStr= "[]";
			optArrJson=JSONArray.fromObject(optStr);
			List<String> pramList = new ArrayList<String>();
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2"))
			{
				pramList.add(","+account.getAccountId()+",");
				pramList.add(userInfo.getDeptId());
				pramList.add("%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				pramList.add(leaveNoId);
				pramList.add(userInfo.getLeadId());
				pramList.add(","+account.getAccountId()+",");
				pramList.add(account.getAccountId());
				pramList.add(account.getOrgId());
				pramList.add(titletime);
			}else
			{
				pramList.add(","+account.getAccountId()+",");
				pramList.add(userInfo.getLeadLeave());
				pramList.add(userInfo.getLeadId());
				pramList.add(","+account.getAccountId()+",");
				pramList.add(account.getAccountId());
				pramList.add(account.getOrgId());
				pramList.add(titletime);
			}
			PageTool pageTool = new PageTool();
			JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
			return json.toString();
		}
		/**
		 * 分页查询 权限内的所有他人日志信息
		 * Author: Wp
		 * @param conn
		 * @param account
		 * @param pagesize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @return
		 * @throws Exception 
		 */
				public String otheruserDiaryLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName)throws Exception{
					UserInfo userInfo = new UserInfo();
					UserInfoLogic userInfoLogic = new UserInfoLogic();
					userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
					String manageDept = userInfo.getManageDept();
					String accountStr="";
					if(manageDept.equals("2"))
					{
						accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
					}else
					{
						accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
					}
					String dbType =DbPoolConnection.getInstance().getDbType();
					String queryStr="";
					if(dbType.equals("sqlserver")){
						
					}else if(dbType.equals("mysql")){
						queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF ,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
							+" AND ( (EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0)  OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ";
					}else if(dbType.equals("oracle")){
						queryStr="SELECT * FROM (SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF ,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
								+" AND ((EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0 ) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? ) DIARY ";
					}
					String accountId=","+account.getAccountId()+",";
					JSONArray optArrJson = new JSONArray();
					String optStr= "[]";
					optArrJson=JSONArray.fromObject(optStr);
					List<String> pramList = new ArrayList<String>();
					UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
					if(manageDept.equals("2"))
					{
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getAccountId());
						pramList.add(userInfo.getDeptId());
						pramList.add("%"+userInfo.getDeptId()+"%");
						String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
						pramList.add(leaveNoId);
						pramList.add(userInfo.getLeadId());
						pramList.add(accountId);
						pramList.add(account.getOrgId());
					}else
					{
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getAccountId());
						pramList.add(userInfo.getLeadLeave());
						pramList.add(userInfo.getLeadId());
						pramList.add(accountId);
						pramList.add(account.getOrgId());
					}
					PageTool pageTool = new PageTool();
					JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
					return json.toString();
				}
				/**
				 * otherusertimeQueryDiaryAct
				 * 根据日志标题时间查询他人的日志信息
				 * Author: Wp
				 * @param conn
				 * @param account
				 * @param pagesize
				 * @param page
				 * @param sortOrder
				 * @param sortName
				 * @param titletime
				 * @return
				 * @throws Exception 
				 */
				public String otherusertimeQueryDiaryLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String titletime)throws Exception{
					UserInfo userInfo = new UserInfo();
					UserInfoLogic userInfoLogic = new UserInfoLogic();
					userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
					String manageDept = userInfo.getManageDept();
					String accountStr="";
					if(manageDept.equals("2"))
					{
						accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
					}else
					{
						accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
					}
					String dbType =DbPoolConnection.getInstance().getDbType();
					String queryStr="";
					if(dbType.equals("sqlserver")){
						
					}else if(dbType.equals("mysql")){
						queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
						+" AND ( (EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0) OR LOCATE(?,CONCAT(',',SHARE_PRIV,','))>0 OR SHARE_PRIV='userAll' OR SHARE_RANGE=1 ) AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
					}else if(dbType.equals("oracle")){
						queryStr="SELECT * FROM (SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF ,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.ACCOUNT_ID !=?"
								+" AND (( EXISTS(SELECT *FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=T1.ACCOUNT_ID ) AND DIARY_MOLD=0) OR INSTR(CONCAT(',',TO_CHAR(SHARE_PRIV))||',',?)>0 OR TO_CHAR(SHARE_PRIV)='userAll' OR SHARE_RANGE=1)AND T1.ORG_ID=? AND DIARY_TITLETIME=? ) DIARY ";
					}
					JSONArray optArrJson = new JSONArray();
					String optStr= "[]";
					optArrJson=JSONArray.fromObject(optStr);
					List<String> pramList = new ArrayList<String>();
					UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
					if(manageDept.equals("2"))
					{
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getAccountId());
						pramList.add(userInfo.getDeptId());
						pramList.add("%"+userInfo.getDeptId()+"%");
						String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
						pramList.add(leaveNoId);
						pramList.add(userInfo.getLeadId());
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getOrgId());
						pramList.add(titletime);
					}else
					{
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getAccountId());
						pramList.add(userInfo.getLeadLeave());
						pramList.add(userInfo.getLeadId());
						pramList.add(","+account.getAccountId()+",");
						pramList.add(account.getOrgId());
						pramList.add(titletime);
					}
					PageTool pageTool = new PageTool();
					JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
					return json.toString();
				}
		/**
		 * 添加日志浏览人员
		 * Author: Wp
		 * @param conn
		 * @param orgId
		 * @param diaryId
		 * @param lookStaff
		 * @return
		 * @throws SQLException
		 */
		public int addlookStaffLogic(Connection conn,String orgId,String diaryId,String lookStaff)throws SQLException{
			String queryStr="UPDATE DIARY SET LOOK_STAFF=? WHERE DIARY_ID =? AND ORG_ID=?";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, lookStaff);
			ps.setString(2, diaryId);
			ps.setString(3, orgId);
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
		/**
		 * 根据accountId 查询日志信息
		 * Author: Wp
		 * @param conn
		 * @param accountId
		 * @param orgId
		 * @param pagesize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @return
		 * @throws Exception 
		 */
		public String getaccountIdLogic(Connection conn,String accountId,String orgId,int pagesize,int page,String sortOrder,String sortName)throws Exception{
			String dbType =DbPoolConnection.getInstance().getDbType();
			String queryStr="";
			if(dbType.equals("sqlserver")){
				
			}else if(dbType.equals("mysql")){
				queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.ACCOUNT_ID WHERE DIARY_MOLD=0 AND T1.ACCOUNT_ID=? AND T1.ORG_ID=? ) DIARY ";
			}else if(dbType.equals("oracle")){
				queryStr="SELECT * FROM ( SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD ,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.ACCOUNT_ID WHERE DIARY_MOLD=0 AND T1.ACCOUNT_ID=? AND T1.ORG_ID=? ) DIARY ";
			}
				String optStr= "[]";
			JSONArray optArrJson = new JSONArray();
			optArrJson=JSONArray.fromObject(optStr);
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+accountId+",");
			pramList.add(accountId);
			pramList.add(orgId);
			PageTool pageTool = new PageTool();
			JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
			return json.toString();
		}
		/**
		 * 通过日志标题时间和人员的accoutnId 查询其他人日志
		 * Author: Wp
		 * @param conn
		 * @param accountId
		 * @param dr
		 * @param pagesize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @return
		 * @throws Exception 
		 */
				public String gettimeuserQueryDiaryLogic(Connection conn,String accountId,Diary dr,int pagesize,int page,String sortOrder,String sortName)throws Exception{
					String dbType =DbPoolConnection.getInstance().getDbType();
					String queryStr="";
					if(dbType.equals("sqlserver")){
						
					}else if(dbType.equals("mysql")){
						queryStr="SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,LOCATE(?,CONCAT(',',LAUD,',')) AS LAUD,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE DIARY_MOLD=0 AND T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_TITLETIME=?";
					}else if(dbType.equals("oracle"))
					{
						queryStr="SELECT T1.ID AS ID,(SELECT COUNT(1) FROM DIARY_COMMENTS T3 WHERE T3.DIARY_ID =T1.DIARY_ID AND COMM_PID ='0')AS COMM_COUNT,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME ,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,DIARY_TITLETIME,LOOK_STAFF,T1.ACCOUNT_ID AS ACCOUNT_ID,INSTR(CONCAT(',',TO_CHAR(LAUD))||',',?) AS LAUD,LAUD_NUM FROM DIARY T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE DIARY_MOLD=0 AND T1.ACCOUNT_ID=? AND T1.ORG_ID=? AND DIARY_TITLETIME=?";
					}
						JSONArray optArrJson = new JSONArray();
					String optStr= "[]";
					optArrJson=JSONArray.fromObject(optStr);
					List<String> pramList = new ArrayList<String>();
					pramList.add(","+accountId+",");
					pramList.add(accountId);
					pramList.add(dr.getOrgId());
					pramList.add(dr.getDiaryTitleDatetime());
					PageTool pageTool = new PageTool();
					JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
					return json.toString();
				}
				/**
				 * 点赞处理
				 * Time 2015-10-28
				 * Author Wp
				 * @param conn
				 * @param diaryId
				 * @param accountId
				 * @param orgId
				 * @return
				 * @throws SQLException
				 */
				public int getlaudLogic(Connection conn,String diaryId,String accountId,String orgId)throws SQLException{
					String queryStr="SELECT LAUD,LAUD_NUM FROM DIARY WHERE ORG_ID=? AND DIARY_ID=?";
					PreparedStatement ps=null;
					ResultSet rs=null;
					ps=conn.prepareStatement(queryStr);
					ps.setString(1, orgId);
					ps.setString(2, diaryId);
					rs=ps.executeQuery();
					String laudStaff="";
					int laudNum=0;
					if(rs.next()){
						laudStaff=rs.getString("LAUD");
						laudNum=rs.getInt("LAUD_NUM");
					}
					if(laudStaff!=null){
						if(!laudStaff.equals("")){
						laudStaff+=","+accountId;
						}else{
							laudStaff=accountId;
						}
					}else{
						laudStaff=accountId;
					}
					queryStr="UPDATE DIARY SET LAUD =?,LAUD_NUM=? WHERE ORG_ID=? AND DIARY_ID=?";
					ps=conn.prepareStatement(queryStr);
					ps.setString(1, laudStaff);
					ps.setInt(2, laudNum+1);
					ps.setString(3, orgId);
					ps.setString(4, diaryId);
					int i=ps.executeUpdate();
					rs.close();
					ps.close();
					return i;
				}
}
