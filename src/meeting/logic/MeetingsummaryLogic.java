package meeting.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import meeting.data.Meetingsummary;

public class MeetingsummaryLogic {

	/**
	 * 添加会议纪要
	 * Author Wp
	 * @param conn
	 * @param summary
	 * @return
	 * @throws SQLException
	 */
	public int addsummarylogic(Connection conn,Meetingsummary summary)throws SQLException{
		String queryStr="INSERT INTO MEETINGSUMMARY (SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID,SUMMARY_STAFF)VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, summary.getSummaryId());
		ps.setString(2, summary.getRequestId());
		ps.setString(3, summary.getMeetingName());
		ps.setString(4, summary.getLookStaff());
		ps.setString(5, summary.getAskStaff());
		ps.setString(6, summary.getRealityStaff());
		ps.setString(7, summary.getSummaryContent());
		ps.setString(8, summary.getAttachId());
		ps.setString(9, summary.getOrgId());
		ps.setString(10, summary.getSummaryStaff());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改会议纪要
	 * Author Wp
	 * @param conn
	 * @param summary
	 * @return
	 * @throws SQLException
	 */
	public int updatesummarylogic(Connection conn,Meetingsummary summary)throws SQLException{
		String queryStr="UPDATE MEETINGSUMMARY SET LOOK_STAFF=?, REALITY_STAFF=? , SUMMARY_CONTENT=?,ATTACH_ID=? WHERE REQUEST_ID =? AND ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, summary.getLookStaff());
		ps.setString(2, summary.getRealityStaff());
		ps.setString(3, summary.getSummaryContent());
		ps.setString(4, summary.getAttachId());
		ps.setString(5, summary.getRequestId());
		ps.setString(6, summary.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据会议ID 获取纪要信息
	 * Author Wp
	 * @param conn
	 * @param summary
	 * @return
	 * @throws SQLException
	 */
	public String getIdsummarylogic(Connection conn,Meetingsummary summary)throws SQLException{
		String queryStr="SELECT SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE REQUEST_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, summary.getRequestId());
		ps.setString(2, summary.getOrgId());
		AccountLogic acclogic=new AccountLogic();
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("summaryId", rs.getString("SUMMARY_ID"));
			json.accumulate("requestId", rs.getString("REQUEST_ID"));
			json.accumulate("meetingName", rs.getString("MEETING_NAME"));
			json.accumulate("lookStaff", rs.getString("LOOK_STAFF"));
			String lookName=acclogic.getUserNameStr(conn, rs.getString("LOOK_STAFF"));
			json.accumulate("lookName", lookName);
			json.accumulate("askStaff", rs.getString("ASK_STAFF"));
			json.accumulate("askName", rs.getString("ASK_NAME"));
			json.accumulate("summaryStaff", rs.getString("SUMMARY_STAFF"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("realityStaff", rs.getString("REALITY_STAFF"));
			String realityName=acclogic.getUserNameStr(conn, rs.getString("REALITY_STAFF"));
			json.accumulate("realityName", realityName);
			json.accumulate("summaryContent", rs.getString("SUMMARY_CONTENT"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 多条件 模糊查询
	 * Author Wp
	 * @param conn
	 * @param summary
	 * @param lookstaff
	 * @param contentone
	 * @param contenttwo
	 * @param contentthree
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String tremsummarylogic(Connection conn,Meetingsummary summary,String lookstaff,String contentone,String contenttwo,String contentthree,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String optStr= "[{'function':'minutesummary','name':'详细信息','parm':'SUMMARY_ID'}]";
		JSONArray optArrJson =JSONArray.fromObject(optStr);
		JSONObject returnJson=new JSONObject();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID ,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE (LOOK_STAFF='userAll' OR LOCATE(?,CONCAT(',',LOOK_STAFF,','))>0) AND ORG_ID=?";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM ( SELECT SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID ,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE (TO_CHAR(LOOK_STAFF)='userAll' OR INSTR(CONCAT(',',TO_CHAR(LOOK_STAFF))||',',?)>0) AND ORG_ID=?";
		}
		List<String> pramList = new ArrayList<String>();
		pramList.add(lookstaff);
		pramList.add(summary.getOrgId());
		if(!summary.getMeetingName().equals("")){
			queryStr+=" AND MEETING_NAME =?";
			pramList.add(summary.getMeetingName());
		}
		if(!summary.getAskStaff().equals("")){
			queryStr+=" AND ASK_STAFF =?";
			pramList.add(summary.getAskStaff());
		}
		if(!contentone.equals("")){
			queryStr+=" AND SUMMARY_CONTENT LIKE ?";
			pramList.add("%"+contentone+"%");
		}
		if(!contenttwo.equals("")){
			queryStr+=" AND SUMMARY_CONTENT LIKE ?";
			pramList.add("%"+contenttwo+"%");
		}
		if(!contentthree.equals("")){
			queryStr+=" AND SUMMARY_CONTENT LIKE ?";
			pramList.add("%"+contentthree+"%");
		}
		queryStr+=" ) MEETINGSUMMARY  ";
		PageTool pageTool = new PageTool();
		returnJson=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
		return returnJson.toString();
	}
	/**
	 * 获取会议纪要列表
	 * Author Wp
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String looksummarylogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID ,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE (LOCATE(?,CONCAT(',',LOOK_STAFF,','))>0 OR LOOK_STAFF='userAll') AND ORG_ID=? ) MEETINGSUMMARY ";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID ,SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID ,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE (INSTR(CONCAT(',',LOOK_STAFF)||',',?)>0 OR TO_CHAR(LOOK_STAFF)='userAll') AND ORG_ID=? ) MEETINGSUMMARY ";
		}
		String optStr= "[{'function':'looksummary','name':'详细信息','parm':'SUMMARY_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		PageTool pageTool = new PageTool();
		JSONObject Json=new JSONObject();
		Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
		return Json.toString();
	}
	/**
	 * 根据summaryID获取纪要详细信息
	 * Author Wp
	 * @param conn
	 * @param summary
	 * @return
	 * @throws SQLException
	 */
	public String getsummaryIdlogic(Connection conn,Meetingsummary summary)throws SQLException{
		String queryStr="SELECT SUMMARY_ID,REQUEST_ID,MEETING_NAME,LOOK_STAFF,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.ASK_STAFF )AS ASK_NAME,ASK_STAFF,REALITY_STAFF,SUMMARY_CONTENT,ATTACH_ID,ORG_ID,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID =T1.SUMMARY_STAFF )AS USER_NAME ,SUMMARY_STAFF FROM MEETINGSUMMARY T1 WHERE SUMMARY_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, summary.getSummaryId());
		ps.setString(2, summary.getOrgId());
		AccountLogic acclogic=new AccountLogic();
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("summaryId", rs.getString("SUMMARY_ID"));
			json.accumulate("requestId", rs.getString("REQUEST_ID"));
			json.accumulate("meetingName", rs.getString("MEETING_NAME"));
			json.accumulate("lookStaff", rs.getString("LOOK_STAFF"));
			String lookName=acclogic.getUserNameStr(conn, rs.getString("LOOK_STAFF"));
			json.accumulate("lookName", lookName);
			json.accumulate("askStaff", rs.getString("ASK_STAFF"));
			json.accumulate("askName", rs.getString("ASK_NAME"));
			json.accumulate("summaryStaff", rs.getString("SUMMARY_STAFF"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("realityStaff", rs.getString("REALITY_STAFF"));
			String realityName=acclogic.getUserNameStr(conn, rs.getString("REALITY_STAFF"));
			json.accumulate("realityName", realityName);
			json.accumulate("summaryContent", rs.getString("SUMMARY_CONTENT"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
}
