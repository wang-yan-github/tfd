package notice.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import notice.data.Notice;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.SysTool;


public class NoticeLogic {
	/**
	 * 添加通知公告
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
	public int addnoticeLogic(Connection conn,Notice nt) throws SQLException{
		String queryStr="INSERT INTO NOTICE ( NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,ACCOUNT_ID,DEPT_PRIV,USER_PRIV,CREATE_TIME,ATTACH_ID,ATTACH_PRIV,NOTICE_CONTENT,TOP,CREATE_USER,ORG_ID,ONCLICK_COUNT,END_TIME,ATTACH_POWER,IS_SMS,READER,NOTICE_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticeId());
		ps.setString(2, nt.getNoticetitle());
		ps.setString(3, nt.getNoticeType());
		ps.setString(4, nt.getAccountId());
		ps.setString(5, nt.getDeptPriv());
		ps.setString(6, nt.getUserPriv());
		ps.setString(7, nt.getCreatetime());
		ps.setString(8, nt.getAttachId());
		ps.setString(9, nt.getAttachPriv());
		ps.setString(10, nt.getNoticeContent());
		ps.setString(11, nt.getTop());
		ps.setString(12, nt.getCreateUser());
		ps.setString(13, nt.getOrgId());
		ps.setInt(14, nt.getOnclickCount());
		ps.setString(15, nt.getEndTime());
		ps.setString(16, nt.getAttachPower());
		ps.setString(17, nt.getIsSms());
		ps.setString(18, nt.getReader());
		ps.setString(19, nt.getNoticeStatus());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 直接发布通知公告
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
	public int publishnoticeLogic(Connection conn,Notice nt) throws SQLException{
		String queryStr="INSERT INTO NOTICE ( NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,ACCOUNT_ID,DEPT_PRIV,USER_PRIV,CREATE_TIME,ATTACH_ID,ATTACH_PRIV,NOTICE_CONTENT,TOP,CREATE_USER,NOTICE_STATUS,APPROVAL_STAFF,ORG_ID,APPROVAL_STATUS,END_TIME,ATTACH_POWER,IS_SMS,ONCLICK_COUNT,READER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticeId());
		ps.setString(2, nt.getNoticetitle());
		ps.setString(3, nt.getNoticeType());
		ps.setString(4, nt.getAccountId());
		ps.setString(5, nt.getDeptPriv());
		ps.setString(6, nt.getUserPriv());
		ps.setString(7, nt.getCreatetime());
		ps.setString(8, nt.getAttachId());
		ps.setString(9, nt.getAttachPriv());
		ps.setString(10, nt.getNoticeContent());
		ps.setString(11, nt.getTop());
		ps.setString(12, nt.getCreateUser());
		ps.setString(13, nt.getNoticeStatus());
		ps.setString(14, nt.getApprovalStaff());
		ps.setString(15, nt.getOrgId());
		ps.setString(16, nt.getApprovalStatus());
		ps.setString(17, nt.getEndTime());
		ps.setString(18, nt.getAttachPower());
		ps.setString(19, nt.getIsSms());
		ps.setString(20, "0");
		ps.setString(21, nt.getReader());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据通知公告Id 修改公告信息
	 * Author: Wp
	 * @param conn
	 * @param notice
	 * @return
	 * @throws SQLException
	 */
	public int updatenoticeLogic(Connection conn,Notice notice) throws SQLException{
		String queryStr="UPDATE NOTICE SET NOTICE_TITLE =? ,NOTICE_TYPE =? ,ACCOUNT_ID =? ,DEPT_PRIV =?, USER_PRIV =? ,CREATE_TIME =?, ATTACH_ID =? ,ATTACH_PRIV =?, NOTICE_CONTENT =?, TOP =?,END_TIME=?,ATTACH_POWER=? ,IS_SMS=? WHERE CREATE_USER =? AND NOTICE_ID =? AND ORG_ID =?   ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, notice.getNoticetitle());
		ps.setString(2, notice.getNoticeType());
		ps.setString(3, notice.getAccountId());
		ps.setString(4, notice.getDeptPriv());
		ps.setString(5, notice.getUserPriv());
		ps.setString(6, notice.getCreatetime());
		ps.setString(7, notice.getAttachId());
		ps.setString(8, notice.getAttachPriv());
		ps.setString(9, notice.getNoticeContent());
		ps.setString(10, notice.getTop());
		ps.setString(11, notice.getEndTime());
		ps.setString(12, notice.getAttachPower());
		ps.setString(13, notice.getIsSms());
		ps.setString(14, notice.getCreateUser());
		ps.setString(15, notice.getNoticeId());
		ps.setString(16, notice.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改通知公告终止时间
	 * Author: Wp
	 * @param conn
	 * @param noticeId
	 * @param endTime
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public int endNoticeLogic(Connection conn,String noticeId,String endTime,Account account)throws SQLException{
		String queryStr=" UPDATE NOTICE SET END_TIME =? WHERE NOTICE_ID =? AND CREATE_USER =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, endTime);
		ps.setString(2, noticeId);
		ps.setString(3, account.getAccountId());
		ps.setString(4, account.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 修改通知公告内容并发布该公告
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
	public int publishupdatenoticeLogic(Connection conn,Notice nt) throws SQLException{
		String queryStr="UPDATE NOTICE SET NOTICE_TITLE =?, NOTICE_TYPE =?, ACCOUNT_ID =?, DEPT_PRIV =? ,USER_PRIV =?, CREATE_TIME =?, ATTACH_ID =?, ATTACH_PRIV =?, NOTICE_CONTENT =?,TOP =?, END_TIME=?,NOTICE_STATUS =?,APPROVAL_STAFF=? ,APPROVAL_STATUS =?,ATTACH_POWER=?,IS_SMS=? WHERE CREATE_USER =? AND NOTICE_ID =? AND ORG_ID =?  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticetitle());
		ps.setString(2, nt.getNoticeType());
		ps.setString(3, nt.getAccountId());
		ps.setString(4, nt.getDeptPriv());
		ps.setString(5, nt.getUserPriv());
		ps.setString(6, nt.getCreatetime());
		ps.setString(7, nt.getAttachId());
		ps.setString(8, nt.getAttachPriv());
		ps.setString(9, nt.getNoticeContent());
		ps.setString(10, nt.getTop());
		ps.setString(11, nt.getEndTime());
		ps.setString(12, nt.getNoticeStatus());
		ps.setString(13, nt.getApprovalStaff());
		ps.setString(14, nt.getApprovalStatus());
		ps.setString(15, nt.getAttachPower());
		ps.setString(16, nt.getIsSms());
		ps.setString(17, nt.getCreateUser());
		ps.setString(18, nt.getNoticeId());
		ps.setString(19, nt.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 获取通知公告列表
	 * Author: Wp
	 * @param conn
	 * @param noticeType
	 * @param account
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getnoticelistLogic(Connection conn,String noticeType,Account account,int pageSize,int page,String sortOrder,String sortName) throws Exception{
		String queryStr="SELECT T1.ID AS ID, NOTICE_ID,NOTICE_TITLE,T2.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,NOTICE_STATUS,APPROVAL_STATUS,END_TIME FROM NOTICE T1 LEFT JOIN CODE_CLASS T2 ON T2.CODE_VALUE =T1.NOTICE_TYPE WHERE CREATE_USER =? AND T1.ORG_ID =? ";
		String optStr= "[{'function':'updatenotice','name':'修改','parm':'NOTICE_ID'},{'function':'readnotice','name':'查看','parm':'NOTICE_ID'},{'function':'endNotice','name':'终止','parm':'NOTICE_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		if(!noticeType.equals("")){
			queryStr+=" AND T1.NOTICE_TYPE =?";
			pramList.add(noticeType);
		}
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据noticeId 删除通知公告信息
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
	public int delnoticeLogic(Connection conn,Notice nt) throws SQLException{
		String queryStr="DELETE FROM NOTICE WHERE NOTICE_ID =? AND CREATE_USER =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticeId());
		ps.setString(2, nt.getCreateUser());
		ps.setString(3, nt.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 获取通知公告最前面的三条数据
	 * Author: Wp
	 * @param conn
	 * @param pramList
	 * @return
	 * @throws SQLException
	 */
	public String getreadNoticeLogic(Connection conn,List<String> pramList)throws SQLException{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
				queryStr="SELECT  DISTINCT *FROM ( SELECT NOTICE_ID, T1.ID AS ID,TOP,READER,NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,CREATE_USER,"
				+ "T2.USER_NAME AS CREATE_NAME "
				+ "FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
				+ " WHERE  "+
				"( T1.ACCOUNT_ID='userAll' OR LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 "
				+ "OR T1.DEPT_PRIV='deptAll' OR LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 "
				+ "OR LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR T1.USER_PRIV='privAll' "
				+ "OR LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0) "
				+ "AND CREATE_TIME <? "
				+ "AND (END_TIME>? "
				+ "OR END_TIME='0') "
				+ "AND APPROVAL_STATUS =1 AND T1.ORG_ID=? )  NOTICE "
				+ "ORDER BY (LOCATE(?,CONCAT(',',READER,','))=0) DESC,TOP DESC,ID DESC LIMIT 6";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT DISTINCT * FROM ( SELECT * FROM(   "
			 +"SELECT  T1.ID AS ID,NOTICE_ID,NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,CREATE_USER,TOP,READER, "
					               +"T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 "
					         +" LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID = T1.CREATE_USER LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					         +" WHERE "
					           +" ( "
					               +" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
					               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
					               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
					               +" INSTR(CONCAT(',', TO_CHAR(T1.ACCOUNT_ID)) || ',', ?) > 0 OR  "           
					               +" INSTR(CONCAT(',', TO_CHAR(T1.DEPT_PRIV)) || ',', ?) > 0 OR "
					               +" INSTR(CONCAT(',', TO_CHAR(T1.DEPT_PRIV)) || ',', ?)> 0 OR "
					               +" INSTR(CONCAT(',', TO_CHAR(T1.USER_PRIV)) || ',', ?) > 0 ) "
					          +" AND CREATE_TIME < ? "
					           +" AND (END_TIME > ? OR END_TIME = '0') "
					           + " AND APPROVAL_STATUS = 1 AND T1.ORG_ID=? ) "       
					         +" ORDER BY CASE "
					+" WHEN (INSTR(CONCAT(',', TO_CHAR(READER)) || ',', ?) = 0) THEN  1 ELSE 0 END DESC, TOP DESC, ID DESC  ) TMP " 
					+" WHERE ROWNUM<=6 ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, pramList.get(0));
		ps.setString(2, pramList.get(1));
		ps.setString(3, pramList.get(2));
		ps.setString(4, pramList.get(3));
		ps.setString(5, pramList.get(4));
		ps.setString(6, pramList.get(5));
		ps.setString(7, pramList.get(6));
		ps.setString(8, pramList.get(7));
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("noticeId", rs.getString("NOTICE_ID"));
			json.accumulate("noticeTitle", rs.getString("NOTICE_TITLE"));
			json.accumulate("noticeType", rs.getString("NOTICE_TYPE"));
			json.accumulate("createUser", rs.getString("CREATE_USER"));
			json.accumulate("createName", rs.getString("CREATE_NAME"));
			json.accumulate("createTime", rs.getString("CREATE_TIME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 获取未看的通知公告列表
	 * Author: Wp
	 * @param conn
	 * @param noticeType
	 * @param account
	 * @param pramList
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param deptId
	 * @return
	 * @throws Exception 
	 */
	public String getNoreadnoticeLogic(Connection conn,String noticeType,Account account,List<String> pramList,int pageSize,int page,String sortOrder,String sortName,String deptId) throws Exception{
		String dbType=DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT DISTINCT *FROM (SELECT T1.ID AS ID,TOP, NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS "
					+ " CREATE_NAME "
					+ " FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					+ " WHERE ("
					+ "T1.ACCOUNT_ID='userAll' OR "
					+ "T1.DEPT_PRIV ='deptAll' OR "
					+ "T1.USER_PRIV ='userPriv' OR "
					+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 ) AND "
					+ " ( LOCATE(?,CONCAT(',',T1.READER,','))=0 OR T1.READER IS NULL)  AND T1.CREATE_TIME <=? AND (T1.END_TIME>=? OR T1.END_TIME='0') AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT DISTINCT * FROM ( SELECT T1.ID AS ID, NOTICE_ID,TOP, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME "
					+ " FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					+ " WHERE ("
					+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
		               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
		               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "					
					+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 ) AND "
					+ " INSTR(CONCAT(',',TO_CHAR(T1.READER))||',',?)=0  AND T1.CREATE_TIME <=? AND (T1.END_TIME>=? OR T1.END_TIME='0')  AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}
		
		String optStr= "[{'function':'readnotice','name':'查看','parm':'NOTICE_ID'}]";
		if(!noticeType.equals("")){
			queryStr+=" AND T1.NOTICE_TYPE =? ) NOTICE ORDER BY TOP DESC,CREATE_TIME DESC,ID DESC";
			pramList.add(noticeType);
		}else{
			queryStr+=" ) NOTICE ORDER BY TOP DESC,CREATE_TIME DESC,ID DESC";
		}
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 获取未读通知公告的数量
	 * Time 2015-12-02
	 * Author Wp
	 * @param conn
	 * @param pramList
	 * @return
	 * @throws SQLException
	 */
	public int nonoticeNumberLogic(Connection conn,List<String> pramList)throws SQLException{
		String dbType=DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT COUNT(NOTICE_ID) AS COUNT_NUM "
					+ " FROM NOTICE T1 "
					+ " WHERE ("
					+ "T1.ACCOUNT_ID='userAll' OR "
					+ "T1.DEPT_PRIV ='deptAll' OR "
					+ "T1.USER_PRIV ='userPriv' OR "
					+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 ) AND "
					+ " LOCATE(?,CONCAT(',',T1.READER,','))=0  AND T1.CREATE_TIME <=? AND (T1.END_TIME>=? OR T1.END_TIME='0') AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr=" SELECT COUNT(NOTICE_ID) AS COUNT_NUM "
					+ " FROM NOTICE T1"
					+ " WHERE ("
					+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
		               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
		               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "					
					+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 ) AND "
					+ " INSTR(CONCAT(',',TO_CHAR(T1.READER))||',',?)=0  AND T1.CREATE_TIME <=? AND (T1.END_TIME>=? OR T1.END_TIME='0')  AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}
		PreparedStatement ps=conn.prepareStatement(queryStr);
		if (pramList!=null&&pramList.size()>0) {
			for(int i=1;pramList.size()>=i;i++)
			{
				ps.setString(i, pramList.get(i-1));
			}
		}
		ResultSet rs=ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("COUNT_NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 获取已读的通知公告列表
	 * Author: Wp
	 * @param conn
	 * @param noticeType
	 * @param createTime
	 * @param account
	 * @param pramList
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param deptId
	 * @return
	 * @throws Exception 
	 */
	public String getreadnoticeLogic(Connection conn,String noticeType,String createTime,Account account,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception{
		String dbType=DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT DISTINCT *FROM ( SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE  "
					+ "WHERE "
					+ "LOCATE(?,CONCAT(',',T1.READER,','))>0 AND CREATE_TIME <=? AND (END_TIME>=? OR END_TIME='0') AND APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT DISTINCT *FROM ( SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					+ "WHERE "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.READER))||',',?)>0 AND T1.CREATE_TIME <=? AND (T1.END_TIME>=? OR T1.END_TIME='0') AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}
		String optStr= "[{'function':'readnotice','name':'查看','parm':'NOTICE_ID'}]";
		if(!noticeType.equals("")){
			queryStr+=" AND T1.NOTICE_TYPE =? ";
			pramList.add(noticeType);
		}
		if(!createTime.equals("")){
			queryStr+=" AND CREATE_TIME >? AND CREATE_TIME <?";
			pramList.add(createTime+" 00:00:00");
			pramList.add(createTime+" 23:59:59");
		}
		queryStr+=" ) NOTICE ORDER BY CREATE_TIME DESC";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 获取历史通知公告
	 * Author: Wp
	 * @param conn
	 * @param noticeType
	 * @param createTime
	 * @param account
	 * @param pramList
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param deptId
	 * @return
	 * @throws Exception 
	 */
	public String gethistorynoticeAct(Connection conn,String noticeType,String createTime,Account account,List<String> pramList,int pageSize,int page,String sortOrder,String sortName,String deptId) throws Exception{
		String dbType=DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT DISTINCT *FROM ( SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					+ "WHERE ("
					+ "T1.ACCOUNT_ID='userAll' OR "
					+ "T1.DEPT_PRIV ='deptAll' OR "
					+ "T1.USER_PRIV ='userPriv' OR "
					+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
					+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0  )"
					+" AND T1.END_TIME<=? AND END_TIME!='0' AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr=" SELECT DISTINCT *FROM ( SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 "
					+ "LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
					+ "WHERE ("
					+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
		               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
		               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 )"
					+" AND T1.END_TIME<=? AND END_TIME!='0' AND T1.APPROVAL_STATUS =1 AND T1.ORG_ID=? ";
		}
		String optStr= "[{'function':'readnotice','name':'查看','parm':'NOTICE_ID'}]";
		if(!noticeType.equals("")){
			queryStr+=" AND T1.NOTICE_TYPE =? ";
			pramList.add(noticeType);
		}
		if(!createTime.equals("")){
			queryStr+=" AND CREATE_TIME >? AND CREATE_TIME <?";
			pramList.add(createTime+" 00:00:00");
			pramList.add(createTime+" 23:59:59");
		}
		queryStr+=" ) NOTICE ORDER BY CREATE_TIME DESC ";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据noticeId 查询通知公告信息
	 * Author: Wp
	 * @param conn
	 * @param noticeId
	 * @param account
	 * @param pramList
	 * @return
	 * @throws SQLException
	 */
	public String getIdnoticLogic(Connection conn,String noticeId,Account account,List<String> pramList)throws SQLException{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT T1.ID AS ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
					+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.NOTICE_TYPE "
				+ "WHERE "
				+ "NOTICE_ID =? AND ( "
				+ "T1.ACCOUNT_ID='userAll' OR "
				+ "T1.DEPT_PRIV ='deptAll' OR "
				+ "T1.USER_PRIV ='userPriv' OR "
				+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
				+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
				+ " LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 OR "
				+ "CREATE_USER=? OR APPROVAL_STAFF=?) AND T1.ORG_ID =?";
		} else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT T1.ID AS ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
					+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.NOTICE_TYPE "
					+ " WHERE "
					+ "NOTICE_ID =? AND "
					+" ( TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
		               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
		               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
					+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 OR " 
					+ "T1.CREATE_USER=? OR T1.APPROVAL_STAFF=? ) AND T1.ORG_ID =? ";
		}		
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, noticeId);
		ps.setString(2, pramList.get(0));
		ps.setString(3, pramList.get(1));
		ps.setString(4, pramList.get(2));
		ps.setString(5, pramList.get(3));
		ps.setString(6, account.getAccountId());
		ps.setString(7, account.getAccountId());
		ps.setString(8, account.getOrgId());
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("noticeTitle", rs.getString("NOTICE_TITLE"));
			json.accumulate("noticeType", rs.getString("NOTICE_TYPE"));
			json.accumulate("typeName", rs.getString("TYPE_NAME"));
			json.accumulate("createUser", rs.getString("CREATE_USER"));
			json.accumulate("createName", rs.getString("CREATE_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", userName);
			json.accumulate("deptPriv",rs.getString("DEPT_PRIV"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
			json.accumulate("deptName",deptName);
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			String userPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("USER_PRIV"));
			json.accumulate("userPrivName", userPrivName);
			json.accumulate("createTime", rs.getString("CREATE_TIME"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("approvalStatus", rs.getString("APPROVAL_STATUS"));
			json.accumulate("noticeContent", rs.getString("NOTICE_CONTENT"));
			json.accumulate("oncount", rs.getString("ONCLICK_COUNT"));
			json.accumulate("top", rs.getString("TOP"));
			if(rs.getString("END_TIME")==null){
				json.accumulate("endTime", "");
			}else{
			json.accumulate("endTime", rs.getString("END_TIME"));
			}
			json.accumulate("attachPower", rs.getString("ATTACH_POWER"));
			json.accumulate("isSms", rs.getString("IS_SMS"));
		}
		rs.close();
		ps.close();
		return json.toString();	
	}
	/**
	 * 查看通知公告之后的处理
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public int setreadnoticeLogic(Connection conn,Notice nt,Account account)throws SQLException{
		ResultSet rs=null;
		PreparedStatement ps=null;
		String reader="";
		int onclickCount=0;
		String queryStr="SELECT READER,ONCLICK_COUNT FROM NOTICE WHERE NOTICE_ID=? AND ORG_ID =?";
		ps= conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticeId());
		ps.setString(2, nt.getOrgId());
		rs = ps.executeQuery();
		if(rs.next())
		{
			reader=rs.getString("READER");
			onclickCount=rs.getInt("ONCLICK_COUNT");
		}
		queryStr="UPDATE NOTICE SET READER=?,ONCLICK_COUNT=? WHERE NOTICE_ID=? AND ORG_ID=?";
		ps = conn.prepareStatement(queryStr);
		if(SysTool.isNullorEmpty(reader))
		{
			reader=account.getAccountId();
		}else
		{
			String readers=","+reader+",";
			if(readers.indexOf(","+account.getAccountId()+",")<0)
			{
			reader=reader+","+account.getAccountId();
			}
		}
		ps.setString(1, reader);
		ps.setInt(2, onclickCount+1);
		ps.setString(3, nt.getNoticeId());
		ps.setString(4, nt.getOrgId());
		int i=ps.executeUpdate();
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 查看未审批的通知公告
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String lookNoapprovalnoticelogic(Connection conn,Notice nt,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT NOTICE_ID,NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_USER,T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,NOTICE_CONTENT,ONCLICK_COUNT FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE WHERE APPROVAL_STAFF=? AND T1.ORG_ID=? AND APPROVAL_STATUS =0 AND NOTICE_STATUS =1";
		String optStr= "[{'function':'approvalnotice','name':'审批','parm':'NOTICE_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(nt.getApprovalStaff());
		pramList.add(nt.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 查看已审批的通知公告
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String lookapprovalnoticelogic(Connection conn,Notice nt,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT NOTICE_ID,NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_USER,T2.USER_NAME AS CREATE_NAME, CREATE_TIME,ATTACH_ID,NOTICE_CONTENT,ONCLICK_COUNT,APPROVAL_STATUS FROM "
				+ "NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID "
				+ "LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
				+ "WHERE APPROVAL_STAFF=? AND T1.ORG_ID=? AND APPROVAL_STATUS !=0";
		String optStr= "[{'function':'approvalnotice','name':'审批','parm':'NOTICE_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(nt.getApprovalStaff());
		pramList.add(nt.getOrgId());
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 审批通过之后的处理
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
	public int approvalpassnoticelogic(Connection conn,Notice nt)throws SQLException{
		String queryStr="UPDATE NOTICE SET APPROVAL_STATUS =1 WHERE NOTICE_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, nt.getNoticeId());
		ps.setString(2, nt.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 审批不通过的处理
	 * Author: Wp
	 * @param conn
	 * @param nt
	 * @return
	 * @throws SQLException
	 */
		public int approvalnopassnoticelogic(Connection conn,Notice nt)throws SQLException{
			String queryStr="UPDATE NOTICE SET APPROVAL_STATUS =2 WHERE NOTICE_ID=? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, nt.getNoticeId());
			ps.setString(2, nt.getOrgId());
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
		/**
		 * 多条件查询公告信息
		 * Author: Wp
		 * @param conn
		 * @param nt
		 * @param starttime
		 * @param endtime
		 * @param pageSize
		 * @param page
		 * @param sortOrder
		 * @param sortName
		 * @return
		 * @throws Exception 
		 */
		public String querynoticelogic(Connection conn,Notice nt,String starttime,String endtime,int pageSize,int page,String sortOrder,String sortName)throws Exception{
			String optStr= "[{'function':'updatenotice','name':'修改','parm':'NOTICE_ID'},{'function':'readnotice','name':'查看','parm':'NOTICE_ID'},{'function':'delnotice','name':'删除','parm':'NOTICE_ID'}]";
			JSONArray optArrJson = JSONArray.fromObject(optStr);
			String queryStr="SELECT DISTINCT *FROM ( SELECT T1.ID AS ID, NOTICE_ID, NOTICE_TITLE,T2.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,NOTICE_STATUS,APPROVAL_STATUS,END_TIME FROM NOTICE T1 LEFT JOIN CODE_CLASS T2 ON T2.CODE_VALUE =T1.NOTICE_TYPE WHERE CREATE_USER =? AND T1.ORG_ID =?";
			List<String> pramList = new ArrayList<String>();
			pramList.add(nt.getCreateUser());
			pramList.add(nt.getOrgId());
			if(!nt.getNoticeType().equals("")){
				queryStr+=" AND T1.NOTICE_TYPE =?";
				pramList.add(nt.getNoticeType());
			}
			if(!nt.getApprovalStatus().equals("")){
				queryStr +=" AND APPROVAL_STATUS =?";
				pramList.add(nt.getApprovalStatus());
			}
			if(!starttime.equals("")){
				queryStr +=" AND CREATE_TIME >=?";
				pramList.add(starttime);
			}
			if(!endtime.equals("")){
				queryStr +=" AND CREATE_TIME <=?";
				pramList.add(endtime);
			}
			if(!nt.getNoticeContent().equals("")){
				queryStr +=" AND NOTICE_CONTENT =?";
				pramList.add("%"+nt.getNoticeContent()+"%");
			}
			if(!nt.getNoticetitle().equals(""))
			{
				queryStr +=" AND NOTICE_TITLE =?";
				pramList.add("%"+nt.getNoticetitle()+"%");
			}
			if(!nt.getNoticeStatus().equals("")){
				queryStr +=" AND NOTICE_STATUS =?";
				pramList.add(nt.getNoticeStatus());
			}
			queryStr+=" ) NOTICE  ";
			PageTool pageTool = new PageTool();
			JSONObject Json=new JSONObject();
			Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
			return Json.toString();
		}
		/**
		 * 根据多条件去删除公告
		 * Author: Wp
		 * @param conn
		 * @param nt
		 * @param starttime
		 * @param endtime
		 * @return
		 * @throws SQLException
		 */
		public int termdelnoticelogic(Connection conn,Notice nt,String starttime,String endtime)throws SQLException{
			String queryStr="DELETE FROM NOTICE WHERE CREATE_USER =? AND ORG_ID =? ";
			List<String> pramList = new ArrayList<String>();
			pramList.add(nt.getCreateUser());
			pramList.add(nt.getOrgId());
			if(!nt.getNoticeType().equals("")){
				queryStr+=" AND NOTICE_TYPE =?";
				pramList.add(nt.getNoticeType());
			}
			if(!nt.getApprovalStatus().equals("")){
				queryStr +=" AND APPROVAL_STATUS =?";
				pramList.add(nt.getApprovalStatus());
			}
			if(!starttime.equals("")){
				queryStr +=" AND CREATE_TIME >=?";
				pramList.add(starttime);
			}
			if(!endtime.equals("")){
				queryStr +=" AND CREATE_TIME <=?";
				pramList.add(endtime);
			}
			if(!nt.getNoticeContent().equals("")){
				queryStr +=" AND NOTICE_CONTENT =?";
				pramList.add("%"+nt.getNoticeContent()+"%");
			}
			if(!nt.getNoticetitle().equals(""))
			{
				queryStr +=" AND NOTICE_TITLE =?";
				pramList.add("%"+nt.getNoticetitle()+"%");
			}
			if(!nt.getNoticeStatus().equals("")){
				queryStr +=" AND NOTICE_STATUS =?";
				pramList.add(nt.getNoticeStatus());
			}
			PreparedStatement ps = conn.prepareStatement(queryStr);
			for(int i=1;pramList.size()>=i;i++)
			{
				ps.setString(i, pramList.get(i-1));
			}
			int data=ps.executeUpdate();
			ps.close();
			return data;
		}
		/**
		 * 多条件查看公告
		 * Author: Wp
		 * @param conn
		 * @param pramList
		 * @param nt
		 * @param starttime
		 * @param endtime
		 * @param pageSize
		 * @param page
		 * @param storOrder
		 * @param storName
		 * @return
		 * @throws Exception 
		 */
		public String termquerylogic( Connection conn,List<String> pramList,Notice nt,String starttime,String endtime,int pageSize,int page,String storOrder,String storName)throws Exception{
			String optStr= "[{'function':'readnotice','name':'查看','parm':'NOTICE_ID'}]";
			String dbType =DbPoolConnection.getInstance().getDbType();
			JSONArray optArrJson =JSONArray.fromObject(optStr);
			JSONObject returnJson=new JSONObject();
			String queryStr="";
			if(dbType.equals("mysql"))
			{
				queryStr=" SELECT DISTINCT *FROM ( SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER, "
						+ " T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID=T1.CREATE_USER "
						+ " LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
						+ " WHERE APPROVAL_STATUS=1 AND ( "
						+ "T1.ACCOUNT_ID='userAll' OR "
						+ "T1.DEPT_PRIV ='deptAll' OR "
						+ "T1.USER_PRIV ='userPriv' OR "
						+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
						+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
						+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
						+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 ) AND T1.ORG_ID=? ";
			}else if(dbType.equals("sqlserver"))
			{
				
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT DISTINCT *FROM (SELECT NOTICE_ID, NOTICE_TITLE,T3.CODE_NAME AS NOTICE_TYPE,CREATE_TIME,ONCLICK_COUNT,CREATE_USER,T2.USER_NAME AS CREATE_NAME FROM NOTICE T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
						+ " WHERE APPROVAL_STATUS=1 AND ( "
						+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
			               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
			               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
						+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
						+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
						+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
						+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 ) AND T1.ORG_ID=? ";
			}
			if(!nt.getCreateUser().equals("")){
				queryStr+=" AND CREATE_USER =? ";
				pramList.add(nt.getCreateUser());
			}
			if(!nt.getNoticeType().equals("")){
				queryStr+=" AND T1.NOTICE_TYPE =?";
				pramList.add(nt.getNoticeType());
			}
			if(!starttime.equals("")){
				queryStr +=" AND CREATE_TIME >=?";
				pramList.add(starttime);
			}
			if(!endtime.equals("")){
				queryStr +=" AND CREATE_TIME <=?";
				pramList.add(endtime);
			}
			if(!nt.getNoticeContent().equals("")){
				queryStr +=" AND NOTICE_CONTENT =? ";
				pramList.add("%"+nt.getNoticeContent()+"%");
			}
			if(!nt.getNoticetitle().equals(""))
			{
				queryStr +=" AND NOTICE_TITLE =?";
				pramList.add("%"+nt.getNoticetitle()+"%");
			}
			queryStr+=" ) NOTICE  ";
			PageTool pageTool = new PageTool();
			returnJson=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, storOrder, storName);
			return returnJson.toString();
		}
		/**
		 * 根据通知公告noticeId 获取全部可读人员的信息
		 * Author: Wp
		 * @param conn
		 * @param noticeId
		 * @param account
		 * @return
		 * @throws SQLException
		 */
		public String getIdreadstatusLogic(Connection conn,String noticeId,Account account)throws SQLException{
			String queryStr="SELECT ACCOUNT_ID,DEPT_PRIV,USER_PRIV FROM NOTICE WHERE NOTICE_ID=? AND CREATE_USER=? AND ORG_ID=?";
			PreparedStatement ps=null;
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, noticeId);
			ps.setString(2, account.getAccountId());
			ps.setString(3, account.getOrgId());
			ResultSet rs=ps.executeQuery();
			String deptId=null;
			String accountId=null;
			String userPriv=null;
			if(rs.next()){
				accountId=rs.getString("ACCOUNT_ID");
				deptId=rs.getString("DEPT_PRIV");
				userPriv=rs.getString("USER_PRIV");
			}
			JSONArray jsonArr=new JSONArray();
			UserInfoLogic infoLogic=new UserInfoLogic();
			if(accountId.equals("userAll")||deptId.equals("deptAll")||userPriv.equals("privAll")){
				jsonArr=infoLogic.getOrgIdLogic(conn, account.getOrgId());
			}else{
			if(accountId!=""){
				AccountLogic accountLogic=new AccountLogic();
				jsonArr=accountLogic.getjsonArrUserNameStr(conn, accountId);
			}
			if(deptId!=""){
				String []deptIds=null;
				if(deptId.indexOf(",")>-1){
					deptIds = deptId.split(",");
				}else
				{
					deptIds=new String[]{deptId};
				}
				for (int i = 0; i < deptIds.length; i++) {
					queryStr="SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE (DEPT_ID=? OR OTHER_DEPT=?) AND ORG_ID=?";
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, deptIds[i]);
					ps.setString(2, deptIds[i]);
					ps.setString(3, account.getOrgId());
					rs=ps.executeQuery();
					while(rs.next()){
						JSONObject json=new JSONObject();
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("deptId", rs.getString("DEPT_ID"));
						jsonArr.add(json);
					}
				}
			}
			if(userPriv!=""){
				String []userPrivs=null;
				if(userPriv.indexOf(",")>-1){
					userPrivs = userPriv.split(",");
				}else
				{
					userPrivs=new String[]{userPriv};
				}
				for (int i = 0; i < userPrivs.length; i++) {
					queryStr="SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE POST_PRIV=? AND ORG_ID=?";
					ps.setString(1, userPrivs[i]);
					ps.setString(2, account.getOrgId());
					rs=ps.executeQuery();
					while(rs.next()){
						JSONObject json=new JSONObject();
						json.accumulate("userName", rs.getString("USER_NAME"));
						json.accumulate("deptId", rs.getString("DEPT_ID"));
						jsonArr.add(json);
					}
				}
			}
			}
			HashSet<Object> hs=new HashSet<Object>();
			for (int i = 0; i < jsonArr.size(); i++) {
				hs.add(jsonArr.get(i));
			}
			rs.close();
			ps.close();
			return hs.toString();
		}
		/**
		 * 通知noticeId 获取已读的人员的信息
		 * Author: Wp
		 * @param conn
		 * @param noticeId
		 * @param account
		 * @return
		 * @throws SQLException
		 */
				public String getreadUserLogic(Connection conn,String noticeId,Account account)throws SQLException{
					String queryStr="SELECT READER FROM NOTICE WHERE NOTICE_ID =? AND CREATE_USER =? AND ORG_ID=?";
					PreparedStatement ps=null;
					ps = conn.prepareStatement(queryStr);
					ps.setString(1, noticeId);
					ps.setString(2, account.getAccountId());
					ps.setString(3, account.getOrgId());
					ResultSet rs=ps.executeQuery();
					String readUserName="";
					AccountLogic acclogic=new AccountLogic();
					while(rs.next()){
						readUserName=acclogic.getUserNameStr(conn, rs.getString("READER"));
					}
					return readUserName;
				}
				/**
				 * 查询下一篇的通知公告信息
				 * Author: Wp
				 * @param conn
				 * @param Id
				 * @param account
				 * @param pramList
				 * @return
				 * @throws SQLException
				 */
				public String downNoticeLogic(Connection conn,String Id,Account account,List<String> pramList)throws SQLException{
					String dbType=DbPoolConnection.getInstance().getDbType();
					String queryStr="";
					if(dbType.equals("mysql"))
					{
					queryStr="SELECT *FROM ( SELECT T1.ID AS ID,NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,"
							+ "T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
							+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID "
							+ " LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
							+ "WHERE T1.ID<? AND ((("
							+ "T1.ACCOUNT_ID='userAll' OR "
							+ "T1.DEPT_PRIV ='deptAll' OR "
							+ "T1.USER_PRIV ='userPriv' OR "
							+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0  "
							+ " )AND T1.APPROVAL_STATUS =1 ) OR CREATE_USER=?) AND T1.ORG_ID =? ) NOTICE  ORDER BY ID DESC LIMIT 1";
					}else if(dbType.equals("sqlserver"))
					{
						
					}else if(dbType.equals("oracle"))
					{
						queryStr="SELECT * FROM (SELECT * FROM (SELECT T1.ID AS ID,NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,"
								+ "T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
								+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID "
								+ " LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
								+ " WHERE T1.ID<? AND ((( "
								+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
					               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
					               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
								+ "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
								+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
								+ "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
								+ "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0  "
								+ ") AND T1.APPROVAL_STATUS =1 ) OR CREATE_USER=?) AND T1.ORG_ID =?)  ORDER BY ID DESC) TMP WHERE ROWNUM=1";
					}
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, Id);
					ps.setString(2, pramList.get(0));
					ps.setString(3, pramList.get(1));
					ps.setString(4, pramList.get(2));
					ps.setString(5, pramList.get(3));
					ps.setString(6, account.getAccountId());
					ps.setString(7, account.getOrgId());
					AccountLogic acclogic=new AccountLogic();
					DeptLogic deptlogic=new DeptLogic();
					UserPrivLogic ulogic=new UserPrivLogic();
					ResultSet rs =null;
					rs=ps.executeQuery();
					JSONObject json = new JSONObject();
					while(rs.next()){
						json.accumulate("Id", rs.getString("ID"));
						json.accumulate("noticeId", rs.getString("NOTICE_ID"));
						json.accumulate("noticeTitle", rs.getString("NOTICE_TITLE"));
						json.accumulate("noticeType", rs.getString("NOTICE_TYPE"));
						json.accumulate("typeName", rs.getString("TYPE_NAME"));
						json.accumulate("createUser", rs.getString("CREATE_USER"));
						json.accumulate("createName", rs.getString("CREATE_NAME"));
						json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
						String userName=acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
						json.accumulate("userName", userName);
						json.accumulate("deptPriv",rs.getString("DEPT_PRIV"));
						String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
						json.accumulate("deptName",deptName);
						json.accumulate("userPriv", rs.getString("USER_PRIV"));
						String userPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("USER_PRIV"));
						json.accumulate("userPrivName", userPrivName);
						json.accumulate("createTime", rs.getString("CREATE_TIME"));
						json.accumulate("attachId", rs.getString("ATTACH_ID"));
						json.accumulate("approvalStatus", rs.getString("APPROVAL_STATUS"));
						json.accumulate("noticeContent", rs.getString("NOTICE_CONTENT"));
						json.accumulate("oncount", rs.getString("ONCLICK_COUNT"));
						json.accumulate("top", rs.getString("TOP"));
						json.accumulate("endTime", rs.getString("END_TIME"));
						json.accumulate("attachPower", rs.getString("ATTACH_POWER"));
						json.accumulate("isSms", rs.getString("IS_SMS"));
					}
					rs.close();
					ps.close();
					return json.toString();	
				}
				/**
				 * 查询上一篇通知公告
				 * Author: Wp
				 * @param conn
				 * @param Id
				 * @param account
				 * @param pramList
				 * @return
				 * @throws SQLException
				 */
				public String upNoticeLogic(Connection conn,String Id,Account account,List<String> pramList)throws SQLException{
					String dbType=DbPoolConnection.getInstance().getDbType();
					String queryStr="";
					if(dbType.equals("mysql"))
					{
					queryStr="SELECT *FROM (SELECT T1.ID AS ID,NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,"
							+ "T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID ,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
							+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID "
							+ " LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
							+ " WHERE "
							+ "T1.ID>? AND ((("
							+ "T1.ACCOUNT_ID='userAll' OR "
							+ "T1.DEPT_PRIV ='deptAll' OR "
							+ "T1.USER_PRIV ='userPriv' OR "
							+ "LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
							+ "LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0  "
							+ " )AND T1.APPROVAL_STATUS =1) OR CREATE_USER=? )  AND "
							+ "T1.ORG_ID =? ) NOTICE ORDER BY ID ASC LIMIT 1";
					}else if(dbType.equals("sqlserver"))
					{
						
					}else if(dbType.equals("oracle"))
					{
						queryStr="SELECT * FROM (SELECT * FROM (SELECT T1.ID AS ID,NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,T3.CODE_NAME AS TYPE_NAME,CREATE_USER,ONCLICK_COUNT,"
								+ "T2.USER_NAME AS CREATE_NAME,CREATE_TIME,ATTACH_ID,TOP,NOTICE_CONTENT,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,APPROVAL_STATUS,END_TIME,ATTACH_POWER,T1.IS_SMS AS IS_SMS FROM NOTICE T1 "
								+ " LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID "
								+ " LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE =T1.NOTICE_TYPE "
								+ " WHERE "
								+ "T1.ID>? AND ((( "
								+" TO_CHAR(T1.ACCOUNT_ID)= 'userAll' OR "
					               +" TO_CHAR(T1.DEPT_PRIV)='deptAll' OR "
					               +" TO_CHAR(T1.USER_PRIV)='privAll' OR "
								+ "INSTR(CONCAT(',',T1.ACCOUNT_ID)||',',?)>0 OR "
								+ "INSTR(CONCAT(',',T1.DEPT_PRIV)||',',?)>0 OR "
								+ "INSTR(CONCAT(',',T1.DEPT_PRIV)||',',?)>0 OR "
								+ "INSTR(CONCAT(',',T1.USER_PRIV)||',',?)>0  "
								+ ")AND T1.APPROVAL_STATUS =1 ) OR CREATE_USER=?) AND "
								+ " T1.ORG_ID =?)  ORDER BY ID ASC) TMP WHERE ROWNUM=1";
					}
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, Id);
					ps.setString(2, pramList.get(0));
					ps.setString(3, pramList.get(1));
					ps.setString(4, pramList.get(2));
					ps.setString(5, pramList.get(3));
					ps.setString(6, account.getAccountId());
					ps.setString(7, account.getOrgId());
					AccountLogic acclogic=new AccountLogic();
					DeptLogic deptlogic=new DeptLogic();
					UserPrivLogic ulogic=new UserPrivLogic();
					ResultSet rs =null;
					rs=ps.executeQuery();
					JSONObject json = new JSONObject();
					while(rs.next()){
						json.accumulate("Id", rs.getString("ID"));
						json.accumulate("noticeId", rs.getString("NOTICE_ID"));
						json.accumulate("noticeTitle", rs.getString("NOTICE_TITLE"));
						json.accumulate("noticeType", rs.getString("NOTICE_TYPE"));
						json.accumulate("typeName", rs.getString("TYPE_NAME"));
						json.accumulate("createUser", rs.getString("CREATE_USER"));
						json.accumulate("createName", rs.getString("CREATE_NAME"));
						json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
						String userName=acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
						json.accumulate("userName", userName);
						json.accumulate("deptPriv",rs.getString("DEPT_PRIV"));
						String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
						json.accumulate("deptName",deptName);
						json.accumulate("userPriv", rs.getString("USER_PRIV"));
						String userPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("USER_PRIV"));
						json.accumulate("userPrivName", userPrivName);
						json.accumulate("createTime", rs.getString("CREATE_TIME"));
						json.accumulate("attachId", rs.getString("ATTACH_ID"));
						json.accumulate("approvalStatus", rs.getString("APPROVAL_STATUS"));
						json.accumulate("noticeContent", rs.getString("NOTICE_CONTENT"));
						json.accumulate("oncount", rs.getString("ONCLICK_COUNT"));
						json.accumulate("top", rs.getString("TOP"));
						json.accumulate("endTime", rs.getString("END_TIME"));
						json.accumulate("attachPower", rs.getString("ATTACH_POWER"));
						json.accumulate("isSms", rs.getString("IS_SMS"));
					}
					rs.close();
					ps.close();
					return json.toString();	
				}
}
