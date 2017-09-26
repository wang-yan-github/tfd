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

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import customermanage.data.CustomerRecord;

public class CustomerrecordLogic {
	/**
	 * 添加 联系记录
	 * Author:Wp
	 * @param conn
	 * @param customerRecord
	 * @return
	 * @throws SQLException
	 */
	public int addrecordLogic(Connection conn,CustomerRecord customerRecord)throws SQLException{
		String queryStr="INSERT INTO CUSTOMER_RECORD (RECORD_ID,CUSTOMER_ID,LINKMAN_ID,RECORD_CONTENT,RECORD_WARN,RECORD_LINKTYPE,RECORD_TIME,ORG_ID,ACCOUNT_ID)VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerRecord.getRecordId());
		ps.setString(2, customerRecord.getCustomerId());
		ps.setString(3, customerRecord.getLinkmanId());
		ps.setString(4, customerRecord.getRecordContent());
		ps.setString(5, customerRecord.getRecordWarn());
		ps.setString(6, customerRecord.getRecordlinkType());
		ps.setString(7, customerRecord.getRecordTime());
		ps.setString(8, customerRecord.getOrgId());
		ps.setString(9, customerRecord.getAccountId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据custoerId获取 联系记录信息
	 * Author：Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getcIdLogic(Connection conn,String customerId,String orgId,int pagesize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT * FROM ( SELECT T1.RECORD_ID AS RECORD_ID,T1.CUSTOMER_ID AS CUSTOMER_ID,T3.USER_NAME AS USER_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.LINKMAN_ID AS LINKMAN_ID,T2.LINKMAN_NAME AS LINKMAN_NAME,T1.RECORD_CONTENT AS RECORD_CONTENT,T1.RECORD_WARN AS RECORD_WARN,T1.RECORD_LINKTYPE AS RECORD_LINKTYPE,T1.RECORD_TIME AS RECORD_TIME FROM CUSTOMER_RECORD T1 LEFT JOIN CUSTOMER_LINKMAN T2 ON T2.LINKMAN_ID=T1.LINKMAN_ID LEFT JOIN USER_INFO T3 ON T3.ACCOUNT_ID =T1.ACCOUNT_ID WHERE T1.CUSTOMER_ID=? AND T1.ORG_ID =? ) CUSTOMER_RECORD ";
		//String optStr= "[{'function':'updaterecord','name':'修改','parm':'RECORD_ID'},{'function':'delrecord','name':'删除','parm':'RECORD_ID'}]";
		String optStr= "[{'function':'updaterecord','name':'修改','parm':'RECORD_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(customerId);
		pramList.add(orgId);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 根据recordId获取联系记录
	 * Author：Wp
	 * @param conn
	 * @param recordId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getrecordIdLogic(Connection conn,String recordId,String orgId)throws SQLException{
		String queryStr="SELECT T1.RECORD_ID AS RECORD_ID,T1.CUSTOMER_ID AS CUSTOMER_ID,T1.LINKMAN_ID AS LINKMAN_ID,T2.LINKMAN_NAME AS LINKMAN_NAME,T1.RECORD_CONTENT AS RECORD_CONTENT,T1.RECORD_WARN AS RECORD_WARN,T1.RECORD_LINKTYPE AS RECORD_LINKTYPE,T1.RECORD_TIME AS RECORD_TIME FROM CUSTOMER_RECORD T1 LEFT JOIN CUSTOMER_LINKMAN T2 ON T2.LINKMAN_ID=T1.LINKMAN_ID WHERE T1.RECORD_ID=? AND T1.ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,recordId );
		ps.setString(2,orgId );
		ResultSet rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		if(rs.next()){
			json.accumulate("recordId", rs.getString("RECORD_ID"));
			json.accumulate("customerId", rs.getString("CUSTOMER_ID"));
			json.accumulate("linkmanId",rs.getString("LINKMAN_ID"));
			if(rs.getString("LINKMAN_NAME")==null){
				json.accumulate("linkmanName","");
			}else{
			json.accumulate("linkmanName",rs.getString("LINKMAN_NAME"));
			}
			if(rs.getString("RECORD_CONTENT")==null){
				json.accumulate("recordContent", "");
			}else{
				json.accumulate("recordContent", rs.getString("RECORD_CONTENT"));	
			}
			json.accumulate("recordWarn", rs.getString("RECORD_WARN"));
			json.accumulate("recordlinkType", rs.getString("RECORD_LINKTYPE"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据recordId删除联系记录
	 * Author：Wp
	 * @param conn
	 * @param recordId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delrecordLogic(Connection conn,String recordId,String orgId)throws SQLException{
		String queryStr="DELETE FROM CUSTOMER_RECORD WHERE RECORD_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,recordId);
		ps.setString(2,orgId );
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据recordId 修改联系记录信息
	 * Author：Wp
	 * @param conn
	 * @param customerRecord
	 * @return
	 * @throws SQLException
	 */
	public int updaterecordLogic(Connection conn,CustomerRecord customerRecord)throws SQLException{
		String queryStr="UPDATE CUSTOMER_RECORD SET LINKMAN_ID=?, RECORD_CONTENT=?,RECORD_WARN=?,RECORD_LINKTYPE=? WHERE RECORD_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerRecord.getLinkmanId());
		ps.setString(2, customerRecord.getRecordContent());
		ps.setString(3, customerRecord.getRecordWarn());
		ps.setString(4, customerRecord.getRecordlinkType());
		ps.setString(5, customerRecord.getRecordId());
		ps.setString(6, customerRecord.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据customerId查询最近时间的五天联系记录
	 * Author：Wp
	 * @param conn
	 * @param customerId
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getrecordLogic(Connection conn,String customerId,Account account)throws SQLException{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT * FROM ( SELECT RECORD_TIME,RECORD_CONTENT FROM CUSTOMER_RECORD WHERE CUSTOMER_ID=? AND ORG_ID=? ) CUSTOMER_RECORD ORDER BY RECORD_TIME DESC LIMIT 0,5";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT*FROM(SELECT * FROM (SELECT RECORD_TIME,RECORD_CONTENT FROM CUSTOMER_RECORD WHERE CUSTOMER_ID=? AND ORG_ID=? ) CUSTOMER_RECORD ORDER BY RECORD_TIME DESC)TMP WHERE ROWNUM<=5";
		}
			PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, account.getOrgId());
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("recordTime", rs.getString("RECORD_TIME"));
			if(rs.getString("RECORD_CONTENT")==null){
				json.accumulate("recordContent", "");
			}else{
			json.accumulate("recordContent", rs.getString("RECORD_CONTENT"));
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 条件查询联系记录
	 * Author:Wp
	 * @param conn
	 * @param customerId
	 * @param account
	 * @param pagesize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @param linkmanId
	 * @param recordTime
	 * @return
	 * @throws Exception 
	 */
	public String termQueryLogic(Connection conn,String customerId,Account account,int pagesize,int page,String sortOrder,String sortName,String linkmanId,String recordTime)throws Exception{
		String queryStr="SELECT * FROM ( SELECT RECORD_ID,CUSTOMER_ID,(SELECT USER_NAME FROM USER_INFO T3 WHERE T3.ACCOUNT_ID =T1.ACCOUNT_ID AND T3.ORG_ID =T1.ORG_ID) AS USER_NAME,ACCOUNT_ID,LINKMAN_ID,(SELECT LINKMAN_NAME FROM CUSTOMER_LINKMAN T2 WHERE T2.LINKMAN_ID=T1.LINKMAN_ID) AS LINKMAN_NAME,RECORD_CONTENT,RECORD_WARN,RECORD_LINKTYPE,RECORD_TIME FROM CUSTOMER_RECORD T1 WHERE CUSTOMER_ID=? AND ORG_ID =?";
		String optStr= "[{'function':'updaterecord','name':'修改','parm':'RECORD_ID'},{'function':'delrecord','name':'删除','parm':'RECORD_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		List<String> pramList = new ArrayList<String>();
		pramList.add(customerId);
		pramList.add(account.getOrgId());
		if(!recordTime.equals("")){
			queryStr+=" AND RECORD_TIME =?";
			pramList.add(recordTime);
		}
		if(!linkmanId.equals("")){
			queryStr+="AND LINKMAN_ID =?";
			pramList.add(linkmanId);
		}
		queryStr=" ) CUSTOMER_RECORD ";
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,sortOrder,sortName);
		return json.toString();
		
	}
}
