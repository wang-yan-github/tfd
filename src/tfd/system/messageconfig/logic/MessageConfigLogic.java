package tfd.system.messageconfig.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import com.system.tool.GuId;

import tfd.system.unit.account.data.Account;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageConfigLogic {
public int updateMessageConfigLogic(Connection conn,String mConfigId,String value,Account account) throws SQLException
{
	int returnData=0;
	JSONArray jsonValue = JSONArray.fromObject(value);
	JSONObject json = new JSONObject();
	json = jsonValue.getJSONObject(0);
	Statement ps =conn.createStatement();
	if(getFlag(conn,account.getOrgId()))
	{
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"', VALUE='"+json.getString("workNextValue")+"' WHERE MODULE='workNext' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("passTimeValue")+"' WHERE MODULE='passTime' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("workEndValue")+"' WHERE MODULE='workEnd' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("workFollowValue")+"' WHERE MODULE='workFollow' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("noticeValue")+"' WHERE MODULE='notice' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("newsValue")+"' WHERE MODULE='news' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("calendarValue")+"' WHERE MODULE='calendar' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("diaryValue")+"' WHERE MODULE='diary' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("meetValue")+"' WHERE MODULE='meeting' AND ORG_ID='"+account.getOrgId()+"'");
	ps.addBatch("UPDATE MESSAGE_CONFIG SET M_CONFIG_ID='"+mConfigId+"',  VALUE='"+json.getString("emailValue")+"' WHERE MODULE='email' AND ORG_ID='"+account.getOrgId()+"'");
	ps.executeBatch();
	returnData=1;
	ps.close();
	}else
	{
		returnData=addMessageConfigLogic(conn,mConfigId,value,account);
	}
	return returnData;
}

public int addMessageConfigLogic(Connection conn,String mConfigId,String value,Account account) throws SQLException
{
	JSONArray jsonValue = JSONArray.fromObject(value);
	JSONObject json = new JSONObject();
	json = jsonValue.getJSONObject(0);
	Statement ps =conn.createStatement();
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','workNext','"+json.getString("workNextValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','passTime','"+json.getString("passTimeValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','workEnd','"+json.getString("workEndValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','workFollow','"+json.getString("workNextValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','notice','"+json.getString("noticeValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','news','"+json.getString("newsValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','calendar','"+json.getString("calendarValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','diary','"+json.getString("diaryValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','meet','"+json.getString("meetValue")+"','"+account.getOrgId()+"')");
	ps.addBatch("INSERT MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES ('','email','"+json.getString("emailValue")+"','"+account.getOrgId()+"')");
	ps.executeBatch();
	ps.close();
	return 1;
}


public boolean getFlag(Connection conn,String orgId) throws SQLException
{
	boolean flag=false;
	String queryStr="SELECT COUNT(*) AS ZS FROM MESSAGE_CONFIG WHERE ORG_ID=?";
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, orgId);
	ResultSet rs =ps.executeQuery();
	if(rs.next())
	{
		if(rs.getInt("ZS")>0)
		{
			flag=true;
		}
	}
	rs.close();
	ps.close();
	return flag;
}

public String getMessageConfigLogic(Connection conn,Account account) throws SQLException
{
	JSONObject json = new JSONObject();
	String queryStr="SELECT MODULE,VALUE FROM MESSAGE_CONFIG WHERE ORG_ID=?";
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, account.getOrgId());
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		json.accumulate(rs.getString("MODULE"), rs.getString("VALUE"));
	}
	rs.close();
	ps.close();
	return json.toString();
}

public String getMConfigJsonLogic(Connection conn,String module,Account account) throws SQLException
{
	String returnData="{}";
	String queryStr="SELECT VALUE FROM MESSAGE_CONFIG WHERE MODULE=? AND ORG_ID=?";
	PreparedStatement ps =conn.prepareStatement(queryStr);
	ps.setString(1, module);
	ps.setString(2, account.getOrgId());
	ResultSet rs =ps.executeQuery();
	if(rs.next())
	{
		returnData = rs.getString("VALUE");
	}
	rs.close();
	ps.close();
	return returnData;
}



}
