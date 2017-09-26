package customermanage.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.SysTool;

import customermanage.data.Customerinfo;

public class CustomerinfoLogic {

	/**
	 * 添加 客户信息
	 * Author:Wp
	 * @param conn
	 * @param customerinfo
	 * @return
	 * @throws SQLException
	 */
	public String addcustomerLoigc(Connection conn ,Customerinfo customerinfo)throws SQLException{
		String queryStr="INSERT INTO CUSTOMER_INFO (CUSTOMER_ID,ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,ORG_ID,TOP,KEEP_ER,CUSTOMER_STATUS,CUSTOMER_TIME,DEL_STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerinfo.getCustomerId());
		ps.setString(2, customerinfo.getAccountId());
		ps.setString(3, customerinfo.getJoinStaff());
		ps.setString(4, customerinfo.getCustomerName());
		ps.setString(5, customerinfo.getTelNumber());
		ps.setString(6, customerinfo.getFaxNumber());
		ps.setString(7, customerinfo.getUrlName());
		ps.setString(8, customerinfo.geteMail());
		ps.setString(9, customerinfo.getAreaName());
		ps.setString(10, customerinfo.getAddName());
		ps.setString(11, customerinfo.getCustomerType());
		ps.setString(12, customerinfo.getCustomerOrigin());
		ps.setString(13, customerinfo.getTradeType());
		ps.setString(14, customerinfo.getFirmNature());
		ps.setString(15, customerinfo.getFirmSummary());
		ps.setString(16, customerinfo.getRemark());
		ps.setString(17, customerinfo.getOrgId());
		ps.setString(18, customerinfo.getTop());
		ps.setString(19, customerinfo.getKeeper());
		ps.setString(20, customerinfo.getCustomerStatus());
		ps.setString(21, customerinfo.getCustomerTime());
		ps.setInt(22, customerinfo.getDelStatus());
		JSONObject json=new JSONObject();
		json.accumulate("customerId", customerinfo.getCustomerId());
		int i=ps.executeUpdate();
		json.accumulate("count", i);
		ps.close();
		return json.toString();
	}
	/**
	 * 根据customerId删除客户信息
	 * Author:Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delcustomerLogic(Connection conn,String customerId,String orgId)throws SQLException{
		String queryStr="UPDATE CUSTOMER_INFO SET DEL_STATUS=1 WHERE CUSTOMER_ID =? AND ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据customerId修改 客户信息
	 * Author:Wp
	 * @param conn
	 * @param customerinfo
	 * @return
	 * @throws SQLException
	 */
	public int updatecustomerLogic(Connection conn ,Customerinfo customerinfo)throws SQLException{
		String queryStr="UPDATE CUSTOMER_INFO SET JOIN_STAFF =?,CUSTOMER_NAME=?,TEL_NUMBER=?,FAX_NUMBER=?,URL_NAME=?,E_MAIL=?,AREA_NAME=?,ADD_NAME=?,CUSTOMER_TYPE=?,CUSTOMER_ORIGIN=?,TRADE_TYPE=?,FIRM_NATURE=?,FIRM_SUMMARY=?,REMARK=?,KEEP_ER=?,CUSTOMER_STATUS=? WHERE CUSTOMER_ID=? AND ORG_ID =? ";
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
		ps.setString(10, customerinfo.getCustomerOrigin());
		ps.setString(11, customerinfo.getTradeType());
		ps.setString(12, customerinfo.getFirmNature());
		ps.setString(13, customerinfo.getFirmSummary());
		ps.setString(14, customerinfo.getRemark());
		ps.setString(15, customerinfo.getKeeper());
		ps.setString(16, customerinfo.getCustomerStatus());
		ps.setString(17, customerinfo.getCustomerId());
		ps.setString(18, customerinfo.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * customerId 获取客户信息
	 * Author:Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getIdcustomerLogic(Connection conn,String customerId,String orgId)throws SQLException{
		String queryStr="SELECT CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID, T2.USER_NAME AS USER_NAME,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,T1.E_MAIL AS E_MAIL,AREA_NAME,T1.ADD_NAME AS ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,KEEP_ER,CUSTOMER_STATUS FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T1.ACCOUNT_ID =T2.ACCOUNT_ID WHERE T1.CUSTOMER_ID =? AND T1.ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		AccountLogic acclogic=new AccountLogic();
		if(rs.next()){
			json.accumulate("customerId", rs.getString("CUSTOMER_ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			if(rs.getString("JOIN_STAFF")==null){
				json.accumulate("joinStaff", "");
			}else{
			json.accumulate("joinStaff", rs.getString("JOIN_STAFF"));
			}
			json.accumulate("joinStaffName",acclogic.getUserNameStr(conn, rs.getString("JOIN_STAFF")));
			json.accumulate("customerName", rs.getString("CUSTOMER_NAME"));
			if(rs.getString("TEL_NUMBER")==null){
				json.accumulate("telNumber", "");
			}else{
				json.accumulate("telNumber", rs.getString("TEL_NUMBER"));	
			}
			if(rs.getString("FAX_NUMBER")==null){
				json.accumulate("faxNumber", "");
			}else{
				json.accumulate("faxNumber", rs.getString("FAX_NUMBER"));	
			}
			if(rs.getString("URL_NAME")==null){
				json.accumulate("urlName","");
			}else{
				json.accumulate("urlName",rs.getString("URL_NAME"));	
			}
			if(rs.getString("E_MAIL")==null){
				json.accumulate("eMail", "");	
			}else{
			json.accumulate("eMail", rs.getString("E_MAIL"));
			}
			if(rs.getString("AREA_NAME")==null){
				json.accumulate("areaName", "");	
			}else{
			json.accumulate("areaName", rs.getString("AREA_NAME"));
			}
			if(rs.getString("ADD_NAME")==null){
				json.accumulate("addName", "");
			}else{
			json.accumulate("addName", rs.getString("ADD_NAME"));
			}
			if(rs.getString("CUSTOMER_TYPE")==null){
				json.accumulate("customerType", "");	
			}else{
			json.accumulate("customerType", rs.getString("CUSTOMER_TYPE"));
			}
			if(rs.getString("CUSTOMER_ORIGIN")==null){
				json.accumulate("customerOrigin", "");	
			}else{
			json.accumulate("customerOrigin", rs.getString("CUSTOMER_ORIGIN"));
			}
			if(rs.getString("TRADE_TYPE")==null){
				json.accumulate("tradeType", "");	
			}else{
			json.accumulate("tradeType", rs.getString("TRADE_TYPE"));
			}
			if(rs.getString("FIRM_NATURE")==null){
				json.accumulate("firmNature", "");
			}else{
				json.accumulate("firmNature", rs.getString("FIRM_NATURE"));	
			}
			if(rs.getString("FIRM_SUMMARY")==null){
				json.accumulate("firmSummary", "");
			}else{
				json.accumulate("firmSummary", rs.getString("FIRM_SUMMARY"));	
			}
			if(rs.getString("REMARK")==null){
				json.accumulate("remark", "");
			}else{
				json.accumulate("remark", rs.getString("REMARK"));	
			}
			json.accumulate("keeper", rs.getString("KEEP_ER"));
			json.accumulate("keeperName", acclogic.getUserNameStr(conn, rs.getString("KEEP_ER")));
			if(rs.getString("CUSTOMER_STATUS")==null){
				json.accumulate("customerStatus", "");
			}else{
				json.accumulate("customerStatus", rs.getString("CUSTOMER_STATUS"));	
			}
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据keeper获取我的客户
	 * Author:Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public String getNamecustomerAct(Connection conn,String accountId,String orgId,int pagesize,int page,String sortOrder,String sortName,String status)throws Exception{
		String queryStr="";
		if(status.equals("1")){
			 queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_STATUS,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND CUSTOMER_TYPE !='合作伙伴' AND T1.KEEP_ER=? AND T1.ORG_ID =? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";	
		}
		else{
			 queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_STATUS,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND CUSTOMER_TYPE ='合作伙伴' AND KEEP_ER=? AND T1.ORG_ID =? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(accountId);
		pramList.add(orgId);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 获取下属的客户列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public String branchcustomerLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String status)throws Exception{
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
		if(status.equals("1")){
			queryStr="SELECT * FROM ( "
					+ "SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,"
					+ "JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,"
					+ "ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,"
					+ "FIRM_SUMMARY,REMARK,TOP,KEEP_ER,"
					+ "T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME  "
					+ "FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER "
					+ " WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴'"
					+ " AND EXISTS(SELECT *FROM ("+accountId+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER ) "
					+ "AND KEEP_ER !=?  AND T1.ORG_ID=? "
					+ ") "
					+ " CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC ";	
		}else{
			queryStr="SELECT * FROM ( "
					+ "SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,"
					+ "JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL"
					+ ",AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE"
					+ ",FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,"
					+ "CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1"
					+ " LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER "
					+ "WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴' AND"
					+ " EXISTS(SELECT *FROM ("+accountId+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER ) "
					+ " AND KEEP_ER !=?  AND T1.ORG_ID=? "
					+ ") CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			pramList.add(userInfo.getDeptId());
			pramList.add("%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			pramList.add( leaveNoId);
			pramList.add( userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());
		}else
		{
			pramList.add(userInfo.getLeadLeave());
			pramList.add(userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());
		}
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 获取自己参与的客户
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public String sharecustomerLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String status)throws Exception{
		String queryStr="";
		String dbType =DbPoolConnection.getInstance().getDbType();
		if(status.equals("1")){
			if(dbType.equals("sqlserver")){
				
			}else if(dbType.equals("mysql")){
				queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,CUSTOMER_TIME,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴' AND (LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 OR JOIN_STAFF='userAll') AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";	
			}else if(dbType.equals("oracle")){
				queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,CUSTOMER_TIME,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴' AND (INSTR(CONCAT(',',TO_CHAR(JOIN_STAFF))||',',?)>0 OR TO_CHAR(JOIN_STAFF)='userAll') AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
			}
			}else{
				if(dbType.equals("sqlserver")){
					
				}else if(dbType.equals("mysql")){
				queryStr="SELECT * FROM (SELECT T1.ID AS ID,CUSTOMER_TIME,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴'  AND (LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 OR JOIN_STAFF='userAll') AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
				}
				else if(dbType.equals("oracle")){
					queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_TIME,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴'  AND (INSTR(CONCAT(',',TO_CHAR(JOIN_STAFF))||',',?)>0 OR TO_CHAR(JOIN_STAFF)='userAll') AND T1.ORG_ID=? ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
				}
				}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(","+account.getAccountId()+",");
		pramList.add(account.getOrgId());
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据条件查询获取客户信息列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String gettermcusLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴' AND KEEP_ER=? AND T1.ORG_ID =?";
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ?";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=" ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 条件查询获取下属的客户列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String termbranchcusLoigc(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{
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
		String queryStr="SELECT * FROM ( SELECT DISTINCT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND CUSTOMER_TYPE !='合作伙伴' AND EXISTS(SELECT *FROM ("+accountId+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER )  AND KEEP_ER !=?  AND T1.ORG_ID=? ) CUSTOMER_INFO";	
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			pramList.add(userInfo.getDeptId());
			pramList.add("%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			pramList.add( leaveNoId);
			pramList.add( userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());

		}else 
		{
			pramList.add(userInfo.getLeadLeave());
			pramList.add(userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());
		}
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ?";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=" ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC ";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据条件获取自己参与的客户信息表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String termsharecusLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴' AND (LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 OR JOIN_STAFF='userAll') AND T1.ORG_ID=?";	
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE !='合作伙伴' AND (INSTR(CONCAT(',',TO_CHAR(JOIN_STAFF))||',',?)>0 OR TO_CHAR(JOIN_STAFF)='userAll') AND T1.ORG_ID=?";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(","+account.getAccountId()+",");
		pramList.add(account.getOrgId());
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ? ";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=" ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC ";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据条件获取类型为合作伙伴的客户信息表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String typetermLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T1.KEEP_ER=T2.ACCOUNT_ID WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴' AND KEEP_ER =?  AND T1.ORG_ID =? ";
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ?";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=" ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据条件获取下属的类型为合作伙伴的客户信息表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String typebranchLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{
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
		String queryStr="SELECT * FROM ( SELECT DISTINCT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.KEEP_ER WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴' AND EXISTS(SELECT *FROM ("+accountId+")T4 WHERE T4.ACCOUNT_ID=T1.KEEP_ER )  AND KEEP_ER !=?  AND T1.ORG_ID=?";	
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			pramList.add(userInfo.getDeptId());
			pramList.add("%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			pramList.add( leaveNoId);
			pramList.add( userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());
		}else
		{
			pramList.add(userInfo.getLeadLeave());
			pramList.add(userInfo.getLeadId());
			pramList.add(account.getAccountId());
			pramList.add( account.getOrgId());
		}
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ?";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=") CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 获取类型为合作伙伴并参与的客户信息列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param customerName
	 * @param linkmanName
	 * @return
	 * @throws Exception 
	 */
	public String typeshareLogic(Connection conn,Account account,int pagesize,int page,String sortOrder,String sortName,String customerName,String linkmanName)throws Exception{ 
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
		
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T1.KEEP_ER=T2.ACCOUNT_ID WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴' AND (LOCATE(?,CONCAT(',',JOIN_STAFF,','))>0 OR JOIN_STAFF='userAll') AND T1.ORG_ID=? ";	
		}else if(dbType.equals("oracle")){
			queryStr="SELECT * FROM ( SELECT T1.ID AS ID,CUSTOMER_ID,T1.ACCOUNT_ID AS ACCOUNT_ID,JOIN_STAFF,CUSTOMER_NAME,TEL_NUMBER,FAX_NUMBER,URL_NAME,E_MAIL,AREA_NAME,ADD_NAME,CUSTOMER_TYPE,CUSTOMER_ORIGIN,TRADE_TYPE,FIRM_NATURE,FIRM_SUMMARY,REMARK,TOP,KEEP_ER,T2.USER_NAME AS KEEPER_NAME,CUSTOMER_STATUS,CUSTOMER_TIME FROM CUSTOMER_INFO T1 LEFT JOIN USER_INFO T2 ON T1.KEEP_ER=T2.ACCOUNT_ID WHERE T1.DEL_STATUS=0 AND  CUSTOMER_TYPE ='合作伙伴' AND (INSTR(CONCAT(',',TO_CHAR(JOIN_STAFF))||',',?)>0 OR TO_CHAR(JOIN_STAFF)='userALl') AND T1.ORG_ID=? ";
		}
			String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(account.getAccountId());
		pramList.add(account.getOrgId());
		if(!customerName.equals("")){
			queryStr+=" AND CUSTOMER_NAME LIKE ?";
			pramList.add("%"+customerName+"%");
		}
		if(!linkmanName.equals("")){
			queryStr+=" AND CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_LINKMAN WHERE LINKMAN_NAME LIKE ?)";
			pramList.add("%"+linkmanName+"%");
		}
		queryStr+=" ) CUSTOMER_INFO ORDER BY TOP DESC,CUSTOMER_TIME DESC";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 修改客户信息置顶状态
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @param top
	 * @return
	 * @throws SQLException
	 */
	public int updatetopLogic(Connection conn,String customerId,String orgId,String top)throws SQLException{
		String queryStr="UPDATE CUSTOMER_INFO SET TOP =?,CUSTOMER_TIME=? WHERE CUSTOMER_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, top);
		ps.setString(2, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(3, customerId);
		ps.setString(4, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
}
