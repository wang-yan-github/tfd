package meeting.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import meeting.data.Meetingrequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;

import com.system.db.PageTool;
import com.system.tool.SysTool;

public class MeetingrequestLogic {

	/**
	 * 添加会议申请信息
	 * Author Wp
	 * @param conn
	 * @param mrequest
	 * @return
	 * @throws SQLException
	 */
	public int addrequestlogic(Connection conn,Meetingrequest mrequest)throws SQLException{
		String queryStr="INSERT INTO MEETINGREQUEST (REQUEST_ID,ATTEND_STAFF,SELECT_DEPT,MEETING_NAME,MEETING_THEME,BOARDROOM_ID,BOARDOOM_STAFF,MEETING_TYPE,MEETING_STARTTIME,MEETING_ENDTIME,MEETING_DEVICE,MEETING_SUMMAN,ATTACH_ID,MEETING_DESCRIPTION,CREATE_USER,MEETING_STATUS,ORG_ID,WARN_TIME,IS_SMS,CYC_TYPE,CYC_ENDTIME)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, mrequest.getRequestId());
		ps.setString(2, mrequest.getAttendStaff());
		ps.setString(3, mrequest.getSelectDept());
		ps.setString(4, mrequest.getMeetingName());
		ps.setString(5, mrequest.getMeetingTheme());
		ps.setString(6, mrequest.getBoardroomId());
		ps.setString(7, mrequest.getBoardroomStaff());
		ps.setString(8, mrequest.getMeetingType());
		ps.setString(9, mrequest.getMeetingStarttime());
		ps.setString(10, mrequest.getMeetingEndtime());
		ps.setString(11, mrequest.getMeetingDevice());
		ps.setString(12, mrequest.getMeetingSumman());
		ps.setString(13, mrequest.getAttachId());
		ps.setString(14, mrequest.getMeetingDescription());
		ps.setString(15, mrequest.getCreateUser());
		ps.setString(16, mrequest.getMeetingStatus());
		ps.setString(17, mrequest.getOrgId());
		ps.setString(18, mrequest.getWarnTime());
		ps.setString(19, mrequest.getIsSms());
		ps.setString(20, mrequest.getCycType());
		ps.setString(21, mrequest.getCycEndtime());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改会议申请信息
	 * Author Wp
	 * @param conn
	 * @param mrequest
	 * @return
	 * @throws SQLException
	 */
	public int updaterequestlogic(Connection conn,Meetingrequest mrequest)throws SQLException{
		String queryStr="UPDATE MEETINGREQUEST SET ATTEND_STAFF=? ,SELECT_DEPT=?,MEETING_NAME=?, MEETING_THEME=?,BOARDROOM_ID=?,BOARDOOM_STAFF=?,MEETING_TYPE=?,MEETING_STARTTIME=?,MEETING_ENDTIME=?,MEETING_DEVICE=?,MEETING_SUMMAN=?,ATTACH_ID=?,MEETING_DESCRIPTION=?,MEETING_STATUS=?,WARN_TIME=?,IS_SMS=?,CYC_TYPE=?,CYC_ENDTIME=? WHERE REQUEST_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, mrequest.getAttendStaff());
		ps.setString(2, mrequest.getSelectDept());
		ps.setString(3, mrequest.getMeetingName());
		ps.setString(4, mrequest.getMeetingTheme());
		ps.setString(5, mrequest.getBoardroomId());
		ps.setString(6, mrequest.getBoardroomStaff());
		ps.setString(7, mrequest.getMeetingType());
		ps.setString(8, mrequest.getMeetingStarttime());
		ps.setString(9, mrequest.getMeetingEndtime());
		ps.setString(10, mrequest.getMeetingDevice());
		ps.setString(11, mrequest.getMeetingSumman());
		ps.setString(12, mrequest.getAttachId());
		ps.setString(13, mrequest.getMeetingDescription());
		ps.setString(14, mrequest.getMeetingStatus());
		ps.setString(15, mrequest.getWarnTime());
		ps.setString(16, mrequest.getIsSms());
		ps.setString(17, mrequest.getCycType());
		ps.setString(18, mrequest.getCycEndtime());
		ps.setString(19, mrequest.getRequestId());
		ps.setString(20, mrequest.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 删除会议申请信息
	 * Author Wp
	 * @param conn
	 * @param requestId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delrequestlogic(Connection conn,String requestId,String orgId)throws SQLException{
		String queryStr="DELETE FROM MEETINGREQUEST WHERE REQUEST_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, requestId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 查询待批的会议申请信息
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
	public String noratifyrequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF, T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.CREATE_USER =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =0 ) MEETINGREQUEST  ";
		String optStr= "[{'function':'selectmeeting','name':'查看','parm':'REQUEST_ID'},{'function':'updatemeeting','name':'修改','parm':'REQUEST_ID'},{'function':'delmeeting','name':'删除','parm':'REQUEST_ID'}]";
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
	 * 查询已批准的会议申请信息
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
	public String ratifyrequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID ,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF, T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.CREATE_USER =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =1 ) MEETINGREQUEST ";
		String optStr= "[{'function':'selectmeeting','name':'查看','parm':'REQUEST_ID'}]";
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
	 * 查询不批准的会议申请信息
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
	public String notratifyrequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT *FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF, T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.CREATE_USER =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =2 ) MEETINGREQUEST ";
		String optStr= "[{'function':'selectmeeting','name':'查看','parm':'REQUEST_ID'},{'function':'lookapproval','name':'审批意见','parm':'REQUEST_ID'},{'function':'updatemeeting','name':'修改','parm':'REQUEST_ID'},{'function':'delmeeting','name':'删除','parm':'REQUEST_ID'}]";
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
	 * 根据会议ID查询会议申请信息
	 * Author Wp
	 * @param conn
	 * @param mrequest
	 * @return
	 * @throws SQLException
	 */
	public String getIdrequestlogic(Connection conn,Meetingrequest mrequest,String accountId)throws SQLException{
		String queryStr="SELECT REQUEST_ID,ATTEND_STAFF,SELECT_DEPT,MEETING_NAME,CREATE_USER,MEETING_THEME,(SELECT BOARDROOM_NAME FROM BOARDROOM T2 WHERE T2.BOARDROOM_ID =T1.BOARDROOM_ID)AS BOARDROOM_NAME,BOARDROOM_ID,BOARDOOM_STAFF,MEETING_TYPE,MEETING_STARTTIME,MEETING_ENDTIME,MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN ,ATTACH_ID,MEETING_DESCRIPTION,CREATE_USER,MEETING_STATUS,WARN_TIME,IS_SMS,CYC_TYPE,CYC_ENDTIME FROM MEETINGREQUEST T1 WHERE (BOARDOOM_STAFF=? OR CREATE_USER=?) AND REQUEST_ID=? AND ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, accountId);
		ps.setString(3, mrequest.getRequestId());
		ps.setString(4, mrequest.getOrgId());
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("requestId", rs.getString("REQUEST_ID"));
			json.accumulate("attendStaff", rs.getString("ATTEND_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("ATTEND_STAFF"));
			json.accumulate("userName", userName);
			json.accumulate("selectDept", rs.getString("SELECT_DEPT"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("SELECT_DEPT"));
			json.accumulate("deptName", deptName);
			json.accumulate("meetingName", rs.getString("MEETING_NAME"));
			json.accumulate("meetingTheme", rs.getString("MEETING_THEME"));
			json.accumulate("boardroomId", rs.getString("BOARDROOM_ID"));
			json.accumulate("boardroomStaff", rs.getString("BOARDOOM_STAFF"));
			json.accumulate("boardroomName", rs.getString("BOARDROOM_NAME"));
			String boardroomStaffname=acclogic.getUserNameStr(conn, rs.getString("BOARDOOM_STAFF"));
			json.accumulate("boardroomStaffname", boardroomStaffname);
			json.accumulate("meetingType", rs.getString("MEETING_TYPE"));
			json.accumulate("meetingStarttime", rs.getString("MEETING_STARTTIME"));
			json.accumulate("meetingEndtime", rs.getString("MEETING_ENDTIME"));
			json.accumulate("meetingDevice", rs.getString("MEETING_DEVICE"));
			json.accumulate("meetingSumman", rs.getString("MEETING_SUMMAN"));
			String SummanName=acclogic.getUserNameStr(conn, rs.getString("MEETING_SUMMAN"));
			json.accumulate("SummanName", SummanName);
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("meetingDescription", rs.getString("MEETING_DESCRIPTION"));
			json.accumulate("warnTime", rs.getString("WARN_TIME"));
			json.accumulate("isSms", rs.getString("IS_SMS"));
			json.accumulate("cycType", rs.getString("CYC_TYPE"));
			json.accumulate("createUser", rs.getString("CREATE_USER"));
			json.accumulate("cycEndtime", rs.getString("CYC_ENDTIME"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 查询需要审批的会议
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
	public String waitrequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID, T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.BOARDOOM_STAFF =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =0 ) MEETINGREQUEST ";
		String optStr= "[{'function':'approvalmeeting','name':'审批','parm':'REQUEST_ID'},{'function':'updatemeeting','name':'修改','parm':'REQUEST_ID'},{'function':'passmeeting','name':'批准','parm':'REQUEST_ID'},{'function':'notpassmeeting','name':'不批准','parm':'REQUEST_ID'}]";
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
	 * 已经批准了的会议申请信息
	 * @param conn
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String viarequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.BOARDOOM_STAFF =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =1 ) MEETINGREQUEST  ";
		String optStr= "[{'function':'approvalmeeting','name':'审批','parm':'REQUEST_ID'},{'function':'notpassmeeting','name':'不批准','parm':'REQUEST_ID'}]";
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
	 * 根据审批人查询审批未通过的信息
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
	public String notviarequestlogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.BOARDOOM_STAFF =? AND T1.ORG_ID =? AND T1.MEETING_STATUS =2 ) MEETINGREQUEST ";
		String optStr= "[{'function':'approvalmeeting','name':'审批','parm':'REQUEST_ID'},{'function':'updatemeeting','name':'修改','parm':'REQUEST_ID'},{'function':'passmeeting','name':'批准','parm':'REQUEST_ID'}]";
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
	 * 审批通过处理
	 * Author Wp
	 * @param conn
	 * @param mrequest
	 * @return
	 * @throws SQLException
	 */
	public int adoptmeetinglogic(Connection conn,Meetingrequest mrequest)throws SQLException{
		String queryStr="UPDATE MEETINGREQUEST SET MEETING_STATUS =? WHERE REQUEST_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, mrequest.getMeetingStatus());
		ps.setString(2, mrequest.getRequestId());
		ps.setString(3, mrequest.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 审批不通过处理
	 * Author Wp
	 * @param conn
	 * @param mrequest
	 * @return
	 * @throws SQLException
	 */
		public int notadoptmeetinglogic(Connection conn,Meetingrequest mrequest)throws SQLException{
			String queryStr="UPDATE MEETINGREQUEST SET MEETING_STATUS =? WHERE REQUEST_ID =? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, mrequest.getMeetingStatus());
			ps.setString(2, mrequest.getRequestId());
			ps.setString(3, mrequest.getOrgId());
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
		/**
		 * 根据纪要员获取已结束的会议并且没有添加会议纪要信息的列表
		 * Author Wp
		 * @param conn
		 * @param account
		 * @param pageSize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @param beforetime
		 * @return
		 * @throws Exception 
		 */
		public String getStaffmeetinglogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName,String beforetime)throws Exception{
			String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE  T1.MEETING_SUMMAN =? AND T1.ORG_ID =? AND T1.MEETING_ENDTIME<? AND T1.MEETING_STATUS =1 AND T1.SUMMARY_STATUS =0 ) MEETINGREQUEST ";
			String optStr= "[{'function':'addsummary','name':'会议纪要','parm':'REQUEST_ID,MEETING_NAME,ATTEND_STAFF,CREATE_USER'}]";
			JSONArray optArrJson = JSONArray.fromObject(optStr);
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			pramList.add(beforetime);
			PageTool pageTool = new PageTool();
			JSONObject Json=new JSONObject();
			Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
			return Json.toString();
		}
		/**
		 * 根据纪要员获取已结束的会议并且添加了会议纪要信息的列表
		 * Author Wp
		 * @param conn
		 * @param account
		 * @param pageSize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @param beforetime
		 * @return
		 * @throws Exception 
		 */
				public String getalreadymeetinglogic(Connection conn,Account account,int pageSize,int page,String sortOrder,String sortName,String beforetime)throws Exception{
					String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE  T1.MEETING_SUMMAN =? AND T1.ORG_ID =? AND T1.MEETING_ENDTIME<=? AND T1.MEETING_STATUS =1 AND T1.SUMMARY_STATUS =1 ) MEETINGREQUEST ";
					String optStr= "[{'function':'looksummary','name':'查看','parm':'REQUEST_ID'},{'function':'updatesummary','name':'修改','parm':'REQUEST_ID'}]";
					JSONArray optArrJson = JSONArray.fromObject(optStr);
					List<String> pramList = new ArrayList<String>();
					pramList.add(account.getAccountId());
					pramList.add(account.getOrgId());
					pramList.add(beforetime);	
					PageTool pageTool = new PageTool();
					JSONObject Json=new JSONObject();
					Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
					return Json.toString();
				}
				/**
				 * 根据出席人员 获取出席人名称的字符串
				 * Author Wp
				 * @param conn
				 * @param attendStaff
				 * @return
				 * @throws SQLException
				 */
		public String getattendstafflogic(Connection conn,String attendStaff)throws SQLException{
			AccountLogic acclogic=new AccountLogic();
			String attendName=acclogic.getUserNameStr(conn,attendStaff);
			return attendName;
		}
		/**
		 * 当纪要添加之后对会议信息中的纪要状态进行修改
		 * Author Wp
		 * @param conn
		 * @param mrequest
		 * @return
		 * @throws SQLException
		 */
		public int getIdsummarylogic(Connection conn,Meetingrequest mrequest)throws SQLException{
					String queryStr="UPDATE MEETINGREQUEST SET SUMMARY_STATUS =? WHERE REQUEST_ID =? AND ORG_ID=?";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, mrequest.getSummaryStatus());
					ps.setString(2, mrequest.getRequestId());
					ps.setString(3, mrequest.getOrgId());
					int i=ps.executeUpdate();
					ps.close();
					return i;
				}
		
		public String termquertmeetingAct(Connection conn,Meetingrequest mrequest,int pageSize,int page,String sortOrder,String sortName,String starttime,String endtime,String attendStaff)throws Exception{
			String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,T1.REQUEST_ID AS REQUEST_ID,T1.ATTEND_STAFF AS ATTEND_STAFF,T1.SELECT_DEPT AS SELECT_DEPT,T1.MEETING_NAME AS MEETING_NAME,T1.MEETING_THEME AS MEETING_THEME,T3.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID AS BOARDROOM_ID,T1.BOARDOOM_STAFF AS BOARDOOM_STAFF,T1.MEETING_TYPE AS MEETING_TYPE,T1.MEETING_STARTTIME AS MEETING_STARTTIME,T1.MEETING_ENDTIME AS MEETING_ENDTIME,T1.MEETING_DEVICE AS MEETING_DEVICE,T1.MEETING_SUMMAN AS MEETING_SUMMAN,T1.ATTACH_ID AS ATTACH_ID,T1.MEETING_DESCRIPTION AS MEETING_DESCRIPTION,T2.USER_NAME AS USER_NAME,T1.CREATE_USER AS CREATE_USER,T1.MEETING_STATUS AS MEETING_STATUS FROM MEETINGREQUEST T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER LEFT JOIN BOARDROOM T3 ON T3.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.ORG_ID=? ) MEETINGREQUEST ";
			String optStr= "[{'function':'selectmeeting','name':'查看','parm':'REQUEST_ID'}]";
			JSONObject returnJson=new JSONObject();
			JSONArray optArrJson = JSONArray.fromObject(optStr);
			List<String> pramList = new ArrayList<String>();
			pramList.add(mrequest.getOrgId());
			if(!mrequest.getMeetingName().equals("")){
				queryStr+="AND T1.MEETING_NAME =? ";
				pramList.add(mrequest.getMeetingName());
			}
			if(!mrequest.getCreateUser().equals("")){
				queryStr+=" AND T1.CREATE_USER =?";
				pramList.add(mrequest.getCreateUser());
			}
			if(!starttime.equals("")){
				queryStr+=" AND T1.MEETING_STARTTIME >= ?";
				pramList.add(starttime);
			}
			if(!endtime.equals("")){
				queryStr+=" AND T1.MEETING_STARTTIME <= ?";
				pramList.add(endtime);
			}
			if(!mrequest.getBoardroomId().equals("")){
				queryStr+=" AND T1.BOARDROOM_ID =? ";
				pramList.add(mrequest.getBoardroomId());
			}
			if(!mrequest.getMeetingStatus().equals("")){
				queryStr+=" AND T1.MEETING_STATUS =? ";
				pramList.add(mrequest.getMeetingStatus());
			}
			if(!attendStaff.equals("")){
				queryStr+=" AND T1.ATTEND_STAFF LIKE ?";
				pramList.add("%"+attendStaff);
			}
			PageTool pageTool = new PageTool();
			returnJson=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
			return returnJson.toString();
		}
		/**
		 * 根据会议室id获取会议室信息
		 * Author Wp
		 * @param conn
		 * @param boardroomId
		 * @param orgId
		 * @param stime
		 * @param etime
		 * @return
		 * @throws Exception
		 */
		public String getboardroomlogic(Connection conn,String accountId,String boardroomId,String orgId,String stime,String etime)throws Exception{
			String queryStr="SELECT BOARDROOM_ID,BOARDROOM_NAME FROM BOARDROOM WHERE BOARDROOM_ID=? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, boardroomId);
			ps.setString(2, orgId);
			ResultSet rs =null;
			rs=ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			JSONObject json=new JSONObject();
			if(rs.next()){
				json.accumulate("name", rs.getString("BOARDROOM_NAME"));
				json.accumulate("id", rs.getString("BOARDROOM_ID"));
				json.accumulate("blackList", 1);
				json.accumulate("meeting", this.getboardIdlogic(conn,accountId, boardroomId, orgId, stime, etime));
			}
			jsonArr.add(json);
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 根据会议室id 查询会议申请记录
		 * Author Wp
		 * @param conn
		 * @param boardroomId
		 * @param orgId
		 * @param stime
		 * @param etime
		 * @return
		 * @throws Exception
		 */
		public String getboardIdlogic(Connection conn,String accountId,String boardroomId,String orgId,String stime,String etime)throws Exception{
			String queryStr="SELECT REQUEST_ID,MEETING_NAME,MEETING_STARTTIME,MEETING_ENDTIME,MEETING_STATUS,CREATE_USER FROM MEETINGREQUEST WHERE BOARDROOM_ID=? AND MEETING_STATUS !=2 AND ORG_ID=? AND MEETING_STARTTIME>? AND MEETING_STARTTIME<?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, boardroomId);
			ps.setString(2, orgId);
			ps.setString(3, stime);
			ps.setString(4, etime);
			ResultSet rs =null;
			rs=ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bdate=sdf.parse(stime);
			while(rs.next()){
				JSONObject json=new JSONObject();
				Date starttime=sdf.parse(rs.getString("MEETING_STARTTIME"));
				long day=(starttime.getTime()-bdate.getTime())/(1000*24*60*60);
				json.accumulate("day", day);
				json.accumulate("content", rs.getString("MEETING_NAME"));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar c = Calendar.getInstance();
				c.setTime(df.parse(rs.getString("MEETING_STARTTIME")));
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int start=hour*60+c.get(Calendar.MINUTE);
				json.accumulate("start", start);
				c.setTime(df.parse(rs.getString("MEETING_ENDTIME")));
				
				int limit=c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE)-start;
				json.accumulate("limit",limit);
				Date endt=df.parse(rs.getString("MEETING_ENDTIME"));
				Date btime=df.parse(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
				if(endt.getTime()<btime.getTime()){
					json.accumulate("status", 3);
				}else{
					if(endt.getTime()>btime.getTime() && starttime.getTime()<btime.getTime()){
						json.accumulate("status", 2);
					}else{
					json.accumulate("status", rs.getString("MEETING_STATUS"));
					}
				}
				json.accumulate("id", rs.getString("REQUEST_ID"));
				if(accountId.equals(rs.getString("CREATE_USER"))){
					json.accumulate("s_priv", 1);	
				}else{
					json.accumulate("s_priv", 0);
				}
			jsonArr.add(json);	
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 根据会议室id修改会议申请信息中的审批人员
		 * Author Wp
		 * @param conn
		 * @param boardroomId
		 * @param orgId
		 * @param boardroomStaff
		 * @return
		 * @throws Exception
		 */
		public int getlibIdupdatelogic(Connection conn,String boardroomId,String orgId,String boardroomStaff)throws Exception{
			String queryStr="UPDATE MEETINGREQUEST SET BOARDOOM_STAFF=? WHERE BOARDROOM_ID=? AND ORG_ID =?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, boardroomStaff);
			ps.setString(2, boardroomId);
			ps.setString(3, orgId);
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
}
