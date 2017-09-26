package com.system.login.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import com.system.db.DbPoolConnection;
import com.system.login.data.ChangeOrg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

public class ChangeOrgLogic {

public String ChanageOrgListLogic(Connection conn,Account account) throws SQLException
{
	JSONArray jsonArr = new JSONArray();
	String dbType =DbPoolConnection.getInstance().getDbType();
	String queryStr="";
	if(dbType.equals("mysql")){
		queryStr="SELECT A.CHANGE_ORG_ID AS CHANGE_ORG_ID,A.ORG_ID AS ORG_ID,B.ORG_NAME AS ORG_NAME,BY_NAME AS BY_NAME FROM CHANGE_ORG A, ORG_CONFIG B WHERE A.ORG_ID=B.ORG_ID AND LOCATE(?,CONCAT(',',A.ACCOUNT_FLAG,','))>0 AND A.STATUS ='1'";
	}else if(dbType.equals("oracle")){
		queryStr="SELECT A.CHANGE_ORG_ID AS CHANGE_ORG_ID,A.ORG_ID AS ORG_ID,B.ORG_NAME AS ORG_NAME,BY_NAME AS BY_NAME FROM CHANGE_ORG A, ORG_CONFIG B WHERE A.ORG_ID=B.ORG_ID AND INSTR(CONCAT(',',TO_CHAR(A.ACCOUNT_FLAG))||',',? )>0  AND A.STATUS ='1'";
	}
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, account.getAccountId());
	ResultSet  rs = ps.executeQuery();
	while (rs.next())
	{
		JSONObject json = new JSONObject();
		json.accumulate("changeOrgId", rs.getString("CHANGE_ORG_ID"));
		json.accumulate("orgName", rs.getString("ORG_NAME"));
		json.accumulate("byName", rs.getString("BY_NAME"));
		if(account.getOrgId().equals(rs.getString("ORG_ID")))
		{
			json.accumulate("flag", true);
		}else
		{
			json.accumulate("flag",false);
		}
		jsonArr.add(json);
	}
	rs.close();
	ps.close();
	return jsonArr.toString();
}

public String getPasswordLogic(Connection conn,String accountId) throws SQLException
{
	String returnData="";
	String queryStr="SELECT PASS_WORD FROM CHANGE_ORG WHERE BY_NAME=?";
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, accountId);
	ResultSet rs = ps.executeQuery();
	if(rs.next())
	{
		returnData = rs.getString("PASS_WORD");
	}
	return returnData;
}

public ChangeOrg getChangeOrg(Connection conn,String changeOrgId) throws SQLException
{
	ChangeOrg changeOrg = new ChangeOrg();
	String queryStr="SELECT CHANGE_ORG_ID,ORG_ID,ACCOUNT_FLAG,PASS_WORD,BY_NAME,STATUS FROM CHANGE_ORG WHERE CHANGE_ORG_ID=?";
	PreparedStatement ps =conn.prepareStatement(queryStr);
	ps.setString(1, changeOrgId);
	ResultSet rs = ps.executeQuery();
	if(rs.next())
	{
		changeOrg.setChangOrgId(rs.getString("CHANGE_ORG_ID"));
		changeOrg.setOrgId(rs.getString("ORG_ID"));
		changeOrg.setAccountFlag(rs.getString("ACCOUNT_FLAG"));
		changeOrg.setPassWord(rs.getString("PASS_WORD"));
		changeOrg.setByName(rs.getString("BY_NAME"));
		changeOrg.setStatus(rs.getString("STATUS"));
	}
	rs.close();
	ps.close();
	return changeOrg;
}

}
