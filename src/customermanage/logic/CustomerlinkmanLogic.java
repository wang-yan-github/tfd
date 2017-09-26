package customermanage.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import customermanage.data.CustomerLinkman;

public class CustomerlinkmanLogic {

	/**
	 * 添加客户联系人信息
	 * Author:Wp
	 * @param conn
	 * @param customerLinkman
	 * @return
	 * @throws SQLException
	 */
	public int addlinkmanLogic(Connection conn,CustomerLinkman customerLinkman)throws SQLException{
		String queryStr="INSERT INTO CUSTOMER_LINKMAN (LINKMAN_ID,CUSTOMER_ID,LINKMAN_NAME,LINKMAN_JOB,LINKMAN_SEX,LINKMAN_CALL,HOME_PHONE,CELL_PHONE,QQ_NUMBER,EMAIL,ADD_NAME,REMARK,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerLinkman.getLinkmanId());
		ps.setString(2, customerLinkman.getCustomerId());
		ps.setString(3, customerLinkman.getLinkmanName());
		ps.setString(4, customerLinkman.getLinkmanJob());
		ps.setString(5, customerLinkman.getLinkmanSex());
		ps.setString(6, customerLinkman.getLinkmanCall());
		ps.setString(7, customerLinkman.getHomePhone());
		ps.setString(8, customerLinkman.getCellPhone());
		ps.setString(9, customerLinkman.getQqNumber());
		ps.setString(10, customerLinkman.geteMail());
		ps.setString(11, customerLinkman.getAddName());
		ps.setString(12, customerLinkman.getRemark());
		ps.setString(13, customerLinkman.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据linkmanId删除联系人
	 * Author:Wp
	 * @param conn
	 * @param linkmanId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delLinkmanLogic(Connection conn,String linkmanId,String orgId)throws SQLException{
		String queryStr="DELETE FROM CUSTOMER_LINKMAN WHERE LINKMAN_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, linkmanId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据linkmanId修改联系人信息
	 * Author:Wp
	 * @param conn
	 * @param customerLinkman
	 * @return
	 * @throws SQLException
	 */
	public int updatelinkmanLogic(Connection conn,CustomerLinkman customerLinkman)throws SQLException{
		String queryStr="UPDATE CUSTOMER_LINKMAN SET LINKMAN_NAME=?,LINKMAN_JOB=?,LINKMAN_SEX=?,LINKMAN_CALL=?,HOME_PHONE=?,CELL_PHONE=?,QQ_NUMBER=?,EMAIL=?,ADD_NAME=?,REMARK=? WHERE LINKMAN_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerLinkman.getLinkmanName());
		ps.setString(2, customerLinkman.getLinkmanJob());
		ps.setString(3, customerLinkman.getLinkmanSex());
		ps.setString(4, customerLinkman.getLinkmanCall());
		ps.setString(5, customerLinkman.getHomePhone());
		ps.setString(6, customerLinkman.getCellPhone());
		ps.setString(7, customerLinkman.getQqNumber());
		ps.setString(8, customerLinkman.geteMail());
		ps.setString(9, customerLinkman.getAddName());
		ps.setString(10, customerLinkman.getRemark());
		ps.setString(11, customerLinkman.getLinkmanId());
		ps.setString(12, customerLinkman.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据linkmanId获取联系人信息
	 * Author:Wp
	 * @param conn
	 * @param linkmanId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getIdlinkmanLogic(Connection conn,String linkmanId,String orgId)throws SQLException{
		String queryStr="SELECT T1.LINKMAN_ID AS LINKMAN_ID,T1.CUSTOMER_ID AS CUSTOMER_ID,T2.CUSTOMER_NAME AS CUSTOMER_NAME,T1.LINKMAN_NAME AS LINKMAN_NAME,T1.LINKMAN_JOB AS LINKMAN_JOB,T1.LINKMAN_SEX AS LINKMAN_SEX,T1.LINKMAN_CALL AS LINKMAN_CALL,T1.HOME_PHONE AS HOME_PHONE,T1.CELL_PHONE AS CELL_PHONE,T1.QQ_NUMBER AS QQ_NUMBER,T1.EMAIL AS EMAIL,T1.ADD_NAME AS ADD_NAME,T1.REMARK AS REMARK FROM CUSTOMER_LINKMAN T1 LEFT JOIN CUSTOMER_INFO T2 ON T2.CUSTOMER_ID=T1.CUSTOMER_ID WHERE T1.LINKMAN_ID=? AND T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, linkmanId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		if(rs.next()){
			json.accumulate("linkmanId", rs.getString("LINKMAN_ID"));
			json.accumulate("customerId", rs.getString("CUSTOMER_ID"));
			json.accumulate("customerName", rs.getString("CUSTOMER_NAME"));
			json.accumulate("linkmanName", rs.getString("LINKMAN_NAME"));
			if(rs.getString("LINKMAN_JOB")==null){
				json.accumulate("linkmanJob", "");
			}else{
				json.accumulate("linkmanJob", rs.getString("LINKMAN_JOB"));	
			}
			if(rs.getString("LINKMAN_SEX")==null){
				json.accumulate("linkmanSex", "");
			}else{
			json.accumulate("linkmanSex", rs.getString("LINKMAN_SEX"));
			}
			if(rs.getString("LINKMAN_CALL")==null){
				json.accumulate("linkmanCall", "");
			}else{
			json.accumulate("linkmanCall", rs.getString("LINKMAN_CALL"));
			}
			if(rs.getString("HOME_PHONE")==null){
				json.accumulate("homePhone", "");
			}else{
			json.accumulate("homePhone", rs.getString("HOME_PHONE"));
			}
			if(rs.getString("CELL_PHONE")==null){
				json.accumulate("cellPhone", "");
			}else{
			json.accumulate("cellPhone", rs.getString("CELL_PHONE"));
			}
			if(rs.getString("QQ_NUMBER")==null){
				json.accumulate("qqNumber", "");
			}else{
				json.accumulate("qqNumber", rs.getString("QQ_NUMBER"));	
			}
			if(rs.getString("EMAIL")==null){
				json.accumulate("eMail", "");
			}else{
			json.accumulate("eMail", rs.getString("EMAIL"));
			}
			if(rs.getString("ADD_NAME")==null){
				json.accumulate("addName", "");
			}else{
			json.accumulate("addName", rs.getString("ADD_NAME"));
			}
			if(rs.getString("REMARK")==null){
				json.accumulate("remark", "");	
			}else{
			json.accumulate("remark", rs.getString("REMARK"));
			}
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据customerId获取联系人列表
	 * Author:Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getcIdlinkmanLogic(Connection conn,String customerId,String orgId)throws SQLException{
		String queryStr="SELECT T1.LINKMAN_ID AS LINKMAN_ID,T1.CUSTOMER_ID AS CUSTOMER_ID,T2.CUSTOMER_NAME AS CUSTOMER_NAME,T1.LINKMAN_NAME AS LINKMAN_NAME,T1.LINKMAN_JOB AS LINKMAN_JOB,T1.LINKMAN_SEX AS LINKMAN_SEX,T1.LINKMAN_CALL AS LINKMAN_CALL,T1.HOME_PHONE AS HOME_PHONE,T1.CELL_PHONE AS CELL_PHONE,T1.QQ_NUMBER AS QQ_NUMBER,T1.EMAIL AS EMAIL,T1.ADD_NAME AS ADD_NAME,T1.REMARK AS REMARK FROM CUSTOMER_LINKMAN T1 LEFT JOIN CUSTOMER_INFO T2 ON T2.CUSTOMER_ID=T1.CUSTOMER_ID WHERE T1.CUSTOMER_ID=? AND T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json  = new JSONObject();
			json.accumulate("linkmanId", rs.getString("LINKMAN_ID"));
			json.accumulate("customerId", rs.getString("CUSTOMER_ID"));
			json.accumulate("customerName", rs.getString("CUSTOMER_NAME"));
			json.accumulate("linkmanName", rs.getString("LINKMAN_NAME"));
			if(rs.getString("LINKMAN_JOB")==null){
				json.accumulate("linkmanJob", "");	
			}else{
			json.accumulate("linkmanJob", rs.getString("LINKMAN_JOB"));
			}
			if(rs.getString("LINKMAN_SEX")==null){
				json.accumulate("linkmanSex", "");
			}else{
			json.accumulate("linkmanSex", rs.getString("LINKMAN_SEX"));
			}
			if(rs.getString("LINKMAN_CALL")==null){
				json.accumulate("linkmanCall", "");
			}else{
			json.accumulate("linkmanCall", rs.getString("LINKMAN_CALL"));
			}
			if(rs.getString("HOME_PHONE")==null){
				json.accumulate("homePhone", "");
			}else{
			json.accumulate("homePhone", rs.getString("HOME_PHONE"));
			}
			if(rs.getString("CELL_PHONE")==null){
				json.accumulate("cellPhone", "");
			}else{
			json.accumulate("cellPhone", rs.getString("CELL_PHONE"));
			}
			if(rs.getString("QQ_NUMBER")==null){
				json.accumulate("qqNumber", "");
			}else{
			json.accumulate("qqNumber", rs.getString("QQ_NUMBER"));
			}
			if(rs.getString("EMAIL")==null){
				json.accumulate("eMail", "");
			}else{
			json.accumulate("eMail", rs.getString("EMAIL"));
			}
			if(rs.getString("ADD_NAME")==null){
				json.accumulate("addName", "");
			}else{
			json.accumulate("addName", rs.getString("ADD_NAME"));
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
		return jsonArr.toString();
	}
	/**
	 * 根据custeomrId获取联系人姓名
	 * Author:Wp
	 * @param conn
	 * @param customerId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getlinkmanNameLogic(Connection conn,String customerId,String orgId)throws SQLException{
		String queryStr="SELECT LINKMAN_ID,LINKMAN_NAME FROM CUSTOMER_LINKMAN T1 WHERE CUSTOMER_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, customerId);
		ps.setString(2, orgId);
		ResultSet rs=ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json  = new JSONObject();
			json.accumulate("linkmanId", rs.getString("LINKMAN_ID"));
			json.accumulate("linkmanName", rs.getString("LINKMAN_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
