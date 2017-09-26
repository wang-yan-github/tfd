package tfd.system.mobile.customer.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import customermanage.data.CustomerLinkman;
import customermanage.data.CustomerRecord;
import customermanage.data.Customerinfo;

public class CustomerInfoLogic {

	/**
	 * 获取自己负责的客户以及参与的客户信息列表
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param page
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getcustomerLogic(Connection conn,int page,String accountId,String orgId)throws Exception{
		JSONObject json=new JSONObject();
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT * FROM ( SELECT ID,CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER,"
					+ "(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER )AS KEEP_ERNAME"
					+ ",CUSTOMER_TIME,TOP FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND "
					+ " (KEEP_ER=? OR LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 "
					+ " OR JOIN_STAFF='userAll')"
					+ " AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC LIMIT "+(page*15)+",15";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM ( SELECT * FROM ( SELECT ID,CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER,"
					+ "(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER )AS KEEP_ERNAME"
					+ ",CUSTOMER_TIME,TOP FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND "
					+ " (KEEP_ER=? OR INSTR(CONCAT(',',TO_CHAR(T1.JOIN_STAFF))||',',?)>0 "
					+ " OR TO_CHAR(T1.JOIN_STAFF)='userAll')"
					+ " AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC ) WHERE ROWNUM BETWEEN "+(page*15)+" AND "+(page+1)*15;
		}
		
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		ResultSet rs = ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject cusjson=new JSONObject();
			cusjson.accumulate("id", rs.getString("CUSTOMER_ID"));
			cusjson.accumulate("name", rs.getString("CUSTOMER_NAME"));
			cusjson.accumulate("state", rs.getString("CUSTOMER_STATUS"));
			cusjson.accumulate("manager", rs.getString("KEEP_ERNAME"));
			cusjson.accumulate("createtime", rs.getString("CUSTOMER_TIME"));
			cusjson.accumulate("top", rs.getString("TOP"));
			jsonArr.add(cusjson);
		}
		JSONObject datajson=new JSONObject();
		datajson.accumulate("time", new Date().getTime()/1000);
		String havemore="1";
		if((page+1)*15>=this.getcount(conn, accountId, orgId)){
			havemore="0";
		}
		datajson.accumulate("havemore", havemore);
		datajson.accumulate("page", ""+page+"");
		datajson.accumulate("list", jsonArr);
		json.accumulate("status_code", "200");
		json.accumulate("msg", "获取列表成功");
		json.accumulate("data", datajson);
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 查询用户有多少条客户信息
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int getcount(Connection conn,String accountId,String orgId)throws Exception{
		String queryStr="SELECT COUNT(*)AS NUM FROM CUSTOMER_INFO WHERE DEL_STATUS=0 AND KEEP_ER=? AND ORG_ID=?";
		int i=0;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 获取用户的下属负责的客户资料列表
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param account
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String getbranchcustomerLogic(Connection conn,Account account,int page)throws Exception{
		JSONObject json=new JSONObject();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountIdStr="";
		if(manageDept.equals("2"))
		{
			accountIdStr="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}else
		{
			accountIdStr="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}
		String dbType = DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
			 queryStr="SELECT * FROM ( SELECT ID,CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER"
			 		+ ",(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER )AS KEEP_ERNAME"
			 		+ ",CUSTOMER_TIME,TOP FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND  "
			 		+ "EXISTS(SELECT *FROM ("+accountIdStr+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER )"
			 				+ " AND KEEP_ER !=?  AND ORG_ID=? ) CUSTOMER_INFO  "
			 				+ " ORDER BY TOP DESC,CUSTOMER_TIME DESC LIMIT "+(page*15)+",15";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM (SELECT * FROM ( SELECT ID,CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER"
			 		+ ",(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER )AS KEEP_ERNAME"
			 		+ ",CUSTOMER_TIME,TOP FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND  "
			 		+ "EXISTS(SELECT *FROM ("+accountIdStr+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER )"
			 				+ " AND KEEP_ER !=?  AND ORG_ID=? ) CUSTOMER_INFO  "
			 				+ " ORDER BY TOP DESC,CUSTOMER_TIME DESC) WHERE ROWNUM BETWEEN "+(page*15)+" AND "+(page+1)*15;
		}		
			PreparedStatement ps = conn.prepareStatement(queryStr);
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2"))
			{				
				ps.setString(1, userInfo.getDeptId());
				ps.setString(2,"%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(3, leaveNoId);				
				ps.setString(4, userInfo.getLeadId());
				ps.setString(5,account.getAccountId() );
				ps.setString(6,account.getOrgId());
			}else
			{
				ps.setString(1, userInfo.getLeadLeave());
				ps.setString(2, userInfo.getLeadId());
				ps.setString(3, account.getAccountId());
				ps.setString(4, account.getOrgId());
			}
			ResultSet rs = ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				JSONObject cusjson=new JSONObject();
				cusjson.accumulate("id", rs.getString("CUSTOMER_ID"));
				cusjson.accumulate("name", rs.getString("CUSTOMER_NAME"));
				cusjson.accumulate("state", rs.getString("CUSTOMER_STATUS"));
				cusjson.accumulate("manager", rs.getString("KEEP_ERNAME"));
				cusjson.accumulate("createtime", rs.getString("CUSTOMER_TIME"));
				cusjson.accumulate("top", rs.getString("TOP"));
				jsonArr.add(cusjson);
			}
			JSONObject datajson=new JSONObject();
			datajson.accumulate("time", new Date().getTime()/1000);
			String havemore="1";
			if((page+1)*15>=this.getbranchcountLogic(conn, account)){
				havemore="0";
			}
			datajson.accumulate("havemore", havemore);
			datajson.accumulate("page", ""+page+"");
			datajson.accumulate("list", jsonArr);
			json.accumulate("status_code", "200");
			json.accumulate("msg", "获取列表成功");
			json.accumulate("data", datajson);
			rs.close();
			ps.close();
			return json.toString();
	}
	/**
	 * 获取下属的客户有多少条记录
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int getbranchcountLogic(Connection conn,Account account)throws Exception{
		int i=0;
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountIdStr="";
		if(manageDept.equals("2"))
		{
			accountIdStr="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}else
		{
			accountIdStr="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}
		String queryStr="SELECT COUNT(*) AS NUM FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND EXISTS(SELECT *FROM ("+accountIdStr+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER ) AND KEEP_ER !=?  AND T1.ORG_ID=?";	
			PreparedStatement ps = conn.prepareStatement(queryStr);
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2"))
			{				
				ps.setString(1, userInfo.getDeptId());
				ps.setString(2,"%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(3, leaveNoId);				
				ps.setString(4, userInfo.getLeadId());
				ps.setString(5,account.getAccountId() );
				ps.setString(6,account.getOrgId());
			}else
			{
				ps.setString(1, userInfo.getLeadLeave());
				ps.setString(2, userInfo.getLeadId());
				ps.setString(3, account.getAccountId());
				ps.setString(4, account.getOrgId());
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				i=rs.getInt("NUM");
			}
			rs.close();
			ps.close();
			return i;
	}
	/**
	 * 搜索
	 * Time 2015-10-15
	 * Author Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public String customersearchLogic(Connection conn,String accountId,String orgId,String keyword)throws Exception{
		JSONObject returnjson =new JSONObject();
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "搜索成功");
		JSONObject data=new JSONObject();
		data.accumulate("time",new Date().getTime()/1000 );
		JSONArray jsoncus=new JSONArray();
		jsoncus=this.setNamesearch(conn, accountId, orgId, keyword);
		JSONArray jsonlink=this.setlinksearch(conn, accountId, orgId, keyword);
		data.accumulate("customers", jsoncus);
		data.accumulate("contacts", jsonlink);
		returnjson.accumulate("data", data);
		return returnjson.toString();
	}
	/**
	 * 根据关键字 模糊查询客户资料
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public JSONArray setNamesearch(Connection conn,String accountId,String orgId,String keyword)throws Exception{
		JSONArray jsonName=new JSONArray();
		String queryStr="SELECT CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER )AS KEEP_ERNAME,CUSTOMER_TIME,TOP FROM CUSTOMER_INFO T1 WHERE DEL_STATUS=0 AND " +
				" (KEEP_ER=? OR LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 "
				+ " OR JOIN_STAFF='userAll')"
				+" AND ORG_ID=? AND (CUSTOMER_NAME LIKE UPPER(?) OR CUSTOMER_NAME LIKE LOWER(?))ORDER BY TOP DESC,CUSTOMER_TIME DESC ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, ","+accountId+",");
		ps.setString(3, orgId);
		ps.setString(4, "%"+keyword+"%");
		ps.setString(5, "%"+keyword+"%");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("CUSTOMER_ID"));
			json.accumulate("name", rs.getString("CUSTOMER_NAME"));
			json.accumulate("manager", rs.getString("KEEP_ERNAME"));
			json.accumulate("state", rs.getString("CUSTOMER_STATUS"));
			json.accumulate("top", rs.getString("TOP"));
			json.accumulate("createtime", rs.getString("CUSTOMER_TIME"));
			jsonName.add(json);
		}
		rs.close();
		ps.close();
		return jsonName;
	}
	/**
	 * 根据关键字 模糊查询联系人获取信息
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public JSONArray setlinksearch(Connection conn,String accountId,String orgId,String keyword)throws Exception{
		JSONArray jsonlink=new JSONArray();
		String queryStr="SELECT T2.CUSTOMER_ID AS CUSTOMER_ID,T2.CUSTOMER_NAME AS CUSTOMER_NAME,T1.LINKMAN_NAME AS LINKMAN_NAME,T1.CELL_PHONE AS CELL_PHONE,T2.TOP AS TOP "
				+ "FROM CUSTOMER_INFO T2 LEFT JOIN CUSTOMER_LINKMAN T1 ON T1.CUSTOMER_ID=T2.CUSTOMER_ID LEFT JOIN ACCOUNT T3 ON T3.ACCOUNT_ID=T2.KEEP_ER "
				+ "WHERE T2.DEL_STATUS=0 " +" AND ( T2.KEEP_ER=? OR LOCATE(?,CONCAT(',',T2.JOIN_STAFF,','))>0 "
				+ " OR JOIN_STAFF='userAll')"+
				"AND T1.ORG_ID= ? AND "
				+ "(T1.LINKMAN_NAME LIKE UPPER(?) OR T1.LINKMAN_NAME LIKE LOWER(?))";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, ","+accountId+",");
		ps.setString(3, orgId);
		ps.setString(4, "%"+keyword+"%");
		ps.setString(5, "%"+keyword+"%");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("CUSTOMER_ID"));
			json.accumulate("name", rs.getString("CUSTOMER_NAME"));
			json.accumulate("contact", rs.getString("LINKMAN_NAME"));
			if(rs.getString("CELL_PHONE")==null){
				json.accumulate("phone", "");
			}else{
				json.accumulate("phone", rs.getString("CELL_PHONE"));
			}
			json.accumulate("top", rs.getString("TOP"));
			json.accumulate("searchBy", "1");
			jsonlink.add(json);
		}
		queryStr="SELECT T2.CUSTOMER_ID AS CUSTOMER_ID,T2.CUSTOMER_NAME AS CUSTOMER_NAME,T1.LINKMAN_NAME AS LINKMAN_NAME,T1.CELL_PHONE AS CELL_PHONE,T2.TOP AS TOP "
				+ "FROM CUSTOMER_INFO T2 LEFT JOIN CUSTOMER_LINKMAN T1 ON T1.CUSTOMER_ID=T2.CUSTOMER_ID LEFT JOIN ACCOUNT T3 ON T3.ACCOUNT_ID=T2.KEEP_ER "
				+ "WHERE T2.DEL_STATUS=0 AND "
				+"( T2.KEEP_ER=? OR LOCATE(?,CONCAT(',',T2.JOIN_STAFF,','))>0 "
				+ " OR JOIN_STAFF='userAll') "
				+ " AND T1.ORG_ID= ? AND T1.CELL_PHONE LIKE ?";
		 ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, ","+accountId+",");
		ps.setString(3, orgId);
		ps.setString(4, "%"+keyword+"%");
		 rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("id", rs.getString("CUSTOMER_ID"));
			json.accumulate("name", rs.getString("CUSTOMER_NAME"));
			json.accumulate("contact", rs.getString("LINKMAN_NAME"));
			if(rs.getString("CELL_PHONE")==null){
				json.accumulate("phone", "");
			}else{
				json.accumulate("phone", rs.getString("CELL_PHONE"));
			}
			json.accumulate("top", rs.getString("TOP"));
			json.accumulate("searchBy", "2");
			jsonlink.add(json);
		}
		rs.close();
		ps.close();
		return jsonlink;
	}
	/**
	 * 客户置顶
	 * Time:2015-10-15
	 * Author: Wp
	 * @param conn
	 * @param account
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public String customertopLogic(Connection conn,String orgId,String customerId)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr=" UPDATE CUSTOMER_INFO SET TOP =1,CUSTOMER_TIME=? WHERE CUSTOMER_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(2, customerId);
		ps.setString(3, orgId);
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
		json.accumulate("status_code", "200");
		json.accumulate("msg", "置顶成功");
		dataJson.accumulate("time", new Date().getTime()/1000);
		json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "置顶失败");
			dataJson.accumulate("time", new Date().getTime()/1000);
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 客户详情
	 * Time 2015-10-15
	 * Author Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String customerdetailLogic(Connection conn,String customerId,String orgId,HttpServletRequest request)throws Exception{
		JSONObject returnjson=new JSONObject();
		String queryStr="SELECT CUSTOMER_NAME,CUSTOMER_STATUS,KEEP_ER,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER) AS KEEP_ERNAME,(SELECT HEAD_IMG FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.KEEP_ER) AS KEEP_IMG,ADD_NAME,URL_NAME,TEL_NUMBER,FAX_NUMBER,E_MAIL,TRADE_TYPE,AREA_NAME,CUSTOMER_TYPE FROM CUSTOMER_INFO T1 WHERE CUSTOMER_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONObject json=new JSONObject();
		json.accumulate("time", new Date().getTime()/1000);
		if(rs.next()){
			json.accumulate("name", rs.getString("CUSTOMER_NAME"));
			json.accumulate("contacts", this.getcustomerIdlinkLogic(conn, customerId, orgId));
			json.accumulate("state", rs.getString("CUSTOMER_STATUS"));
			json.accumulate("manager", rs.getString("KEEP_ERNAME"));
			json.accumulate("managerid", rs.getString("KEEP_ER"));
			if(rs.getString("KEEP_IMG")==null||rs.getString("KEEP_IMG").equals("")){
				json.accumulate("managerAvatar", "");
			}else{
				json.accumulate("managerAvatar", request.getContextPath()+"/attachment/userinfo/"+rs.getString("KEEP_IMG"));
			}
			json.accumulate("partner", ""+this.getcustomerjoinStaff(conn, customerId, orgId,request)+"");
			if(rs.getString("ADD_NAME")==null){
				json.accumulate("address","");
			}else{
			json.accumulate("address", rs.getString("ADD_NAME"));
			}
			if(rs.getString("URL_NAME")==null){
				json.accumulate("website", "");	
			}else{
			json.accumulate("website", rs.getString("URL_NAME"));
			}
			if(rs.getString("TEL_NUMBER")==null){
				json.accumulate("mobile", "");
			}else{
			json.accumulate("mobile", rs.getString("TEL_NUMBER"));
			}
			if(rs.getString("FAX_NUMBER")==null){
				json.accumulate("fax", "");
			}else{
			json.accumulate("fax", rs.getString("FAX_NUMBER"));
			}
			if(rs.getString("E_MAIL")==null){
				json.accumulate("email","");
			}else{
			json.accumulate("email", rs.getString("E_MAIL"));
			}
			if(rs.getString("TRADE_TYPE")==null){
			json.accumulate("profession", "");
			}else{
				json.accumulate("profession", rs.getString("TRADE_TYPE"));
			}
			if(rs.getString("AREA_NAME")==null){
			json.accumulate("area", "");
			}else{
				json.accumulate("area", rs.getString("AREA_NAME"));
			}
			json.accumulate("type",rs.getString("CUSTOMER_TYPE"));
			json.accumulate("contactrecord", this.getcusrecordinfo(conn, customerId, orgId,request));
		}
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "请求成功");
		returnjson.accumulate("data", json);
		rs.close();
		ps.close();
		return returnjson.toString();
	}
	/**
	 * 根据客户ID获取联系人信息
	 * Time 2015-10-16
	 * Author Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public JSONArray getcustomerIdlinkLogic(Connection conn,String customerId,String orgId)throws Exception{
		JSONArray jsonArr=new JSONArray();
		String queryStr="SELECT LINKMAN_ID,LINKMAN_NAME,LINKMAN_JOB,LINKMAN_CALL,CELL_PHONE,HOME_PHONE,QQ_NUMBER,EMAIL,REMARK FROM CUSTOMER_LINKMAN WHERE CUSTOMER_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("contactid", rs.getString("LINKMAN_ID"));
			json.accumulate("name", rs.getString("LINKMAN_NAME"));
			if(rs.getString("CELL_PHONE")==null){
				json.accumulate("mobile", "");
			}else{
			json.accumulate("mobile", rs.getString("CELL_PHONE"));
			}
			if(rs.getString("LINKMAN_JOB")==null){
				json.accumulate("role", "");
			}else{
				json.accumulate("role", rs.getString("LINKMAN_JOB"));	
			}
			if(rs.getString("LINKMAN_CALL")==null){
			json.accumulate("appellation","");
			}else{
				json.accumulate("appellation", rs.getString("LINKMAN_CALL"));
			}
			if(rs.getString("HOME_PHONE")==null){
			json.accumulate("tel", "");
			}else{
				json.accumulate("tel", rs.getString("HOME_PHONE"));
			}
			if(rs.getString("QQ_NUMBER")==null){
				json.accumulate("qq", "");
			}else{
			json.accumulate("qq", rs.getString("QQ_NUMBER"));
			}
			if(rs.getString("EMAIL")==null){
			json.accumulate("email", "");
			}else{
				json.accumulate("email", rs.getString("EMAIL"));
			}
			if(rs.getString("REMARK")==null){
			json.accumulate("remark", "");
			}else{
				json.accumulate("remark", rs.getString("REMARK"));
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr;
	}
	/**
	 * 根据customerId 获取参与人信息
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getcustomerjoinStaff(Connection conn,String customerId,String orgId,HttpServletRequest request)throws Exception{
		String queryStr="SELECT JOIN_STAFF FROM CUSTOMER_INFO WHERE CUSTOMER_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		AccountLogic accountLogic=new AccountLogic();
		String accountIdStr="";
		if(rs.next()){
			accountIdStr=rs.getString("JOIN_STAFF");
		}
		rs.close();
		ps.close();
		String userJsonArr=accountLogic.getjsonUserNameStr(conn, accountIdStr);
		JSONArray jsonArr=JSONArray.fromObject(userJsonArr);
		JSONArray userArr=new JSONArray();
		if(jsonArr.size()>0){
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject json=new JSONObject();
			json.accumulate("id", jsonArr.getJSONObject(i).getString("accountId"));
			json.accumulate("name", jsonArr.getJSONObject(i).getString("userName"));
			if(jsonArr.getJSONObject(i).getString("headImg")==null||jsonArr.getJSONObject(i).getString("headImg").equals("")){
				json.accumulate("avatar", "");
			}else{
				json.accumulate("avatar", request.getContextPath()+"/attachment/userinfo/"+jsonArr.getJSONObject(i).getString("headImg"));
			}
			userArr.add(json);
		}
		}
		return userArr.toString();
	}
	/**
	 * 根据客户Id 获取聊天记录的内容
	 * Time 2015-10-16
	 * Author Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONArray getcusrecordinfo(Connection conn,String customerId,String orgId,HttpServletRequest request)throws Exception{
		JSONArray jsonArr=new JSONArray();
		String queryStr="SELECT RECORD_ID,LINKMAN_ID,RECORD_TIME,DETAIL,RECORD_CONTENT,RECORD_WARN,ADDRESS,LON,LAT,T1.ACCOUNT_ID AS ACCOUNT_ID,T2.HEAD_IMG AS HEAD_IMG,T2.USER_NAME AS USER_NAME FROM CUSTOMER_RECORD T1 ,USER_INFO T2 WHERE CUSTOMER_ID=? AND T1.ORG_ID=? AND T1.ACCOUNT_ID=T2.ACCOUNT_ID ORDER BY RECORD_TIME DESC,T1.ID DESC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			if(rs.getString("LINKMAN_ID")==null){
			json.accumulate("id", "");
			}else{
				json.accumulate("id", rs.getString("LINKMAN_ID"));
			}
			json.accumulate("contactId", rs.getString("RECORD_ID"));
			if(rs.getString("USER_NAME") ==null){
				json.accumulate("name","" );
			}else{
			json.accumulate("name",rs.getString("USER_NAME") );
			}
			if(rs.getString("HEAD_IMG")==null||rs.getString("HEAD_IMG").equals("")){
				json.accumulate("avatar_url", "");
			}else{
				json.accumulate("avatar_url", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
			}
			json.accumulate("createtime", rs.getString("RECORD_TIME"));
			json.accumulate("content", rs.getString("RECORD_CONTENT"));
			json.accumulate("remindtime", rs.getString("RECORD_WARN"));
			if(rs.getString("ADDRESS") ==null){
				json.accumulate("address","" );
			}else{
			json.accumulate("address", rs.getString("ADDRESS"));
			}
			if(rs.getString("DETAIL") ==null){
				json.accumulate("detail","" );
			}else{
			json.accumulate("detail", rs.getString("DETAIL"));
			}
			if(rs.getString("LON") ==null){
				json.accumulate("lon","" );
			}else{
			json.accumulate("lon", rs.getString("LON"));
			}
			if(rs.getString("LAT") ==null){
				json.accumulate("lat","" );
			}else{
			json.accumulate("lat", rs.getString("LAT"));
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr;
	}
	/**
	 * 添加客户资料
	 * Time 2015-10-16
	 * Author Wp
	 * @param conn
	 * @param customerinfo
	 * @return
	 * @throws Exception
	 */
	public String addcustomerLogic(Connection conn,Customerinfo customerinfo)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr="INSERT INTO CUSTOMER_INFO (CUSTOMER_ID,CUSTOMER_NAME,JOIN_STAFF,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,TRADE_TYPE,ORG_ID,TOP,KEEP_ER,CUSTOMER_STATUS,CUSTOMER_TIME,ACCOUNT_ID,DEL_STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerinfo.getCustomerId());
		ps.setString(2, customerinfo.getCustomerName());
		ps.setString(3, customerinfo.getJoinStaff());
		ps.setString(4, customerinfo.getTelNumber());
		ps.setString(5, customerinfo.getFaxNumber());
		ps.setString(6, customerinfo.getUrlName());
		ps.setString(7, customerinfo.geteMail());
		ps.setString(8, customerinfo.getAreaName());
		ps.setString(9, customerinfo.getAddName());
		ps.setString(10, customerinfo.getCustomerType());
		ps.setString(11, customerinfo.getTradeType());
		ps.setString(12, customerinfo.getOrgId());
		ps.setString(13, customerinfo.getTop());
		ps.setString(14, customerinfo.getKeeper());
		ps.setString(15, customerinfo.getCustomerStatus());
		ps.setString(16, customerinfo.getCustomerTime());
		ps.setString(17, customerinfo.getAccountId());
		ps.setInt(18, customerinfo.getDelStatus());
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
			json.accumulate("status_code", "200");
			json.accumulate("msg", "添加客户成功");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			dataJson.accumulate("id", customerinfo.getCustomerId());
			json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "添加客户失败");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 新建联系人
	 * Time 2015-10-16
	 * Author Wp
	 * @param conn
	 * @param customerLinkman
	 * @return
	 * @throws Exception
	 */
	public String addcontactLogic(Connection conn,CustomerLinkman customerLinkman)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr="INSERT INTO CUSTOMER_LINKMAN (LINKMAN_ID,CUSTOMER_ID,LINKMAN_NAME,LINKMAN_JOB,LINKMAN_CALL,HOME_PHONE,CELL_PHONE,QQ_NUMBER,EMAIL,REMARK,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerLinkman.getLinkmanId());
		ps.setString(2, customerLinkman.getCustomerId());
		ps.setString(3, customerLinkman.getLinkmanName());
		ps.setString(4, customerLinkman.getLinkmanJob());
		ps.setString(5, customerLinkman.getLinkmanCall());
		ps.setString(6, customerLinkman.getHomePhone());
		ps.setString(7, customerLinkman.getCellPhone());
		ps.setString(8, customerLinkman.getQqNumber());
		ps.setString(9, customerLinkman.geteMail());
		ps.setString(10, customerLinkman.getRemark());
		ps.setString(11, customerLinkman.getOrgId());
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
			json.accumulate("status_code", "200");
			json.accumulate("msg", "添加成功");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			dataJson.accumulate("id", customerLinkman.getLinkmanId());
			json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "添加失败");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 添加联系记录
	 * Time 2015-10-19
	 * Author Wp
	 * @param conn
	 * @param customerRecord
	 * @return
	 * @throws Exception
	 */
	public String addcontactrecordLogic(Connection conn,CustomerRecord customerRecord)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr="INSERT INTO CUSTOMER_RECORD(RECORD_ID,CUSTOMER_ID,LINKMAN_ID,RECORD_CONTENT,RECORD_WARN,RECORD_LINKTYPE,RECORD_TIME,ACCOUNT_ID,ADDRESS,LON,LAT,ORG_ID,DETAIL)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,customerRecord.getRecordId());
		ps.setString(2,customerRecord.getCustomerId());
		ps.setString(3,customerRecord.getLinkmanId());
		ps.setString(4,customerRecord.getRecordContent());
		ps.setString(5,customerRecord.getRecordWarn());
		ps.setString(6,customerRecord.getRecordlinkType());
		ps.setString(7,customerRecord.getRecordTime());
		ps.setString(8,customerRecord.getAccountId());
		ps.setString(9,customerRecord.getAddress());
		ps.setString(10,customerRecord.getLon());
		ps.setString(11,customerRecord.getLat());
		ps.setString(12,customerRecord.getOrgId());
		ps.setString(13, customerRecord.getDetail());
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
			json.accumulate("status_code", "200");
			json.accumulate("msg", "添加成功");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			dataJson.accumulate("id", customerRecord.getRecordId());
			json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "添加失败");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 编辑客户信息
	 * Time 2015-10-19
	 * Author Wp
	 * @param conn
	 * @param customerinfo
	 * @return
	 * @throws Exception
	 */
	public String customereditLogic(Connection conn,Customerinfo customerinfo)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr="UPDATE CUSTOMER_INFO SET JOIN_STAFF =?,CUSTOMER_NAME=?,TEL_NUMBER=?,FAX_NUMBER=?,URL_NAME=?,E_MAIL=?,AREA_NAME=?,ADD_NAME=?,CUSTOMER_TYPE=?,TRADE_TYPE=?,KEEP_ER=?,CUSTOMER_STATUS=? WHERE CUSTOMER_ID=? AND ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerinfo.getJoinStaff());
		ps.setString(2, customerinfo.getCustomerName());
		ps.setString(3, customerinfo.getTelNumber());
		ps.setString(4, customerinfo.getFaxNumber());
		ps.setString(5, customerinfo.getUrlName());
		ps.setString(6, customerinfo.geteMail());
		ps.setString(7, customerinfo.getAreaName());
		ps.setString(8, customerinfo.getAddName());
		ps.setString(9, customerinfo.getCustomerType());
		ps.setString(10, customerinfo.getTradeType());
		ps.setString(11, customerinfo.getKeeper());
		ps.setString(12, customerinfo.getCustomerStatus());
		ps.setString(13, customerinfo.getCustomerId());
		ps.setString(14, customerinfo.getOrgId());
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
		json.accumulate("status_code", "200");
		json.accumulate("msg", "编辑成功");
		dataJson.accumulate("time", new Date().getTime()/1000);
		json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "编辑失败");
			dataJson.accumulate("time", new Date().getTime()/1000);
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 获取行业信息
	 * Time 2015 10-19
	 * Author Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getProfessionLogic(Connection conn,String orgId)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr = "SELECT CODE_NAME,CODE_VALUE FROM CODE_CLASS WHERE ORG_ID = ? AND CODE_PID=(SELECT CODE_ID FROM CODE_CLASS WHERE CODE_VALUE = 'customer' AND ORG_ID=?) ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONArray list=new JSONArray();
		while(rs.next()){
			JSONObject listjson=new JSONObject();
			listjson.accumulate("id", rs.getString("CODE_VALUE"));
			listjson.accumulate("name", rs.getString("CODE_NAME"));
			list.add(listjson);
		}
		JSONObject data=new JSONObject();
		data.accumulate("time", new Date().getTime()/1000);
		data.accumulate("list", list);
		json.accumulate("status_code", "200");
		json.accumulate("msg", "获取行业成功");
		json.accumulate("data", data);
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 判断下属是否拥有客户
	 * Time 2012-10-22
	 * Author Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String checkpowerLogic(Connection conn,Account account)throws Exception{
		JSONObject json=new JSONObject();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountId="";
		if(manageDept.equals("2"))
		{
			accountId="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}else
		{
			accountId="(SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM USER_INFO U,USER_LEAVE L,ACCOUNT A WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)";
		}
		String queryStr="";
		queryStr="SELECT  COUNT(*) AS NUM FROM CUSTOMER_INFO T1 WHERE T1.DEL_STATUS=0 AND EXISTS(SELECT *FROM ("+accountId+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER ) AND KEEP_ER !=?  AND ORG_ID=?";	
		
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		PreparedStatement ps = conn.prepareStatement(queryStr);
		if(manageDept.equals("2"))
		{				
			ps.setString(1, userInfo.getDeptId());
			ps.setString(2,"%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(3, leaveNoId);				
			ps.setString(4, userInfo.getLeadId());
			ps.setString(5,account.getAccountId() );
			ps.setString(6,account.getOrgId());
		}else
		{
			ps.setString(1, userInfo.getLeadLeave());
			ps.setString(2, userInfo.getLeadId());
			ps.setString(3, account.getAccountId());
			ps.setString(4, account.getOrgId());
		}
		ResultSet rs = ps.executeQuery();
		int i=0;
		if(rs.next()){
			i=rs.getInt("NUM");
		}
		JSONObject data=new JSONObject();
		if(i>0){
			data.accumulate("time", new Date().getTime()/1000 );
			data.accumulate("permission", 1);
			json.accumulate("data", data);
			json.accumulate("msg", "检查权限成功");
			json.accumulate("status_code", "200");
		}else{
			data.accumulate("time", new Date().getTime()/1000 );
			data.accumulate("permission", 0);
			json.accumulate("data", data);
			json.accumulate("msg", "检查权限成功");
			json.accumulate("status_code", "200");
		}
		return json.toString(); 
	}
	/**
	 * 编辑联系人信息
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param customerLinkman
	 * @return
	 * @throws Exception
	 */
	public String  contacteditLogic(Connection conn,CustomerLinkman customerLinkman)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="UPDATE CUSTOMER_LINKMAN SET LINKMAN_NAME=?,LINKMAN_JOB=?,LINKMAN_CALL=?,CELL_PHONE=?,HOME_PHONE=?,QQ_NUMBER=?,EMAIL=?,REMARK=? WHERE ORG_ID=? AND LINKMAN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerLinkman.getLinkmanName());
		ps.setString(2, customerLinkman.getLinkmanJob());
		ps.setString(3, customerLinkman.getLinkmanCall());
		ps.setString(4, customerLinkman.getCellPhone());
		ps.setString(5, customerLinkman.getHomePhone());
		ps.setString(6, customerLinkman.getQqNumber());
		ps.setString(7, customerLinkman.geteMail());
		ps.setString(8, customerLinkman.getRemark());
		ps.setString(9, customerLinkman.getOrgId());
		ps.setString(10, customerLinkman.getLinkmanId());
		int i=ps.executeUpdate();
		JSONObject data=new JSONObject();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "编辑成功");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data",data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "编辑失败");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data",data);
		}
		ps.close();
		return datajson.toString();
	}
	/**
	 * 编辑联系记录
	 * Time 2015-11-03
	 * Author Wp
	 * @param conn
	 * @param customerRecord
	 * @throws Exception
	 */
	public String contactrecordeditLogic(Connection conn,CustomerRecord customerRecord)throws Exception{
		JSONObject datajson=new JSONObject();
		String queryStr="UPDATE CUSTOMER_RECORD SET LINKMAN_ID=? ,RECORD_CONTENT=?,RECORD_WARN=?,RECORD_LINKTYPE=?,ADDRESS=?,LON=?,LAT=?,DETAIL=? WHERE ORG_ID=? AND RECORD_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerRecord.getLinkmanId());
		ps.setString(2, customerRecord.getRecordContent());
		ps.setString(3, customerRecord.getRecordWarn());
		ps.setString(4, customerRecord.getRecordlinkType());
		ps.setString(5, customerRecord.getAddress());
		ps.setString(6, customerRecord.getLon());
		ps.setString(7, customerRecord.getLat());
		ps.setString(8, customerRecord.getDetail());
		ps.setString(9, customerRecord.getOrgId());
		ps.setString(10, customerRecord.getRecordId());
		int i=ps.executeUpdate();
		JSONObject data=new JSONObject();
		if(i>0){
			datajson.accumulate("status_code", "200");
			datajson.accumulate("msg", "编辑成功");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data",data);
		}else{
			datajson.accumulate("status_code", "500");
			datajson.accumulate("msg", "编辑失败");
			data.accumulate("time", new Date().getTime()/1000);
			datajson.accumulate("data",data);
		}
		ps.close();
		return datajson.toString();
	}
}
