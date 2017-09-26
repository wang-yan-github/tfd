package tfd.system.sysinfo.copyright.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import tfd.system.sysinfo.copyright.data.SysConfig;

import com.system.db.DbPoolConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SysInfoLogic {
	public String getSysInfoLogic(Connection conn,String orgId) throws SQLException
	{	
		String dbms = DbPoolConnection.getInstance().getDbType();
		Properties props = System.getProperties();  
		JSONObject json = new JSONObject();
		String queryStr="SELECT UNIT,MACHINE_CODE,SN,USER_COUNT,COPY_RIGHT FROM SYS_INFO WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs =ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("UNIT", rs.getString("UNIT"));
			json.accumulate("MACHINE_CODE", rs.getString("MACHINE_CODE"));
			json.accumulate("SN", rs.getString("SN"));
			json.accumulate("USER_COUNT", rs.getString("USER_COUNT"));
			json.accumulate("COPY_RIGHT", rs.getString("COPY_RIGHT"));	
		}
		json.accumulate("DB_NAME",dbms);
		json.accumulate("JDK", props.getProperty("java.version")+"/"+props.getProperty("java.vm.version"));
		json.accumulate("OS_NAME", props.getProperty("os.name")+"  "+props.getProperty("os.arch")+"X bit  "+props.getProperty("os.version"));
		//Sysreg sysreg = new Sysreg();
//		if(sysreg.regist())
//		{
//			json.accumulate("REG_FLAG", "已注册");
//		}else
//		{
//			json.accumulate("REG_FLAG", "未注册");
//		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	
	public String getSysInfoList(Connection conn,String orgId) throws SQLException{	
		JSONObject json = new JSONObject();
		//String queryStr="SELECT ID,SYS_PARAM_NAME,SYS_PARAM_VALUE,ORG_ID,MEMO FROM SYS_CONFIG WHERE ORG_ID = ? ORDER BY ID ASC";
		String queryStr = "SELECT SYS_CONFIG_ID,UPDATE_NAME,INIT_PWD,PWD_CYCLE,OUT_PWD,PWD_WIDTH"
				+ ",IS_ABC,MISS_NUM,MISS_TIME,MISS_PWD,FIND_PWD,REMBER_USER,MORE_LOGIN,COM_WITH_PHONE,"
				+ "USE_KEY,DOMAIN_LOGIN,USER_IP,REMBER_STATUS FROM SYS_CONFIG WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs =ps.executeQuery();
		if(rs.next())
		{
			
			
			json.accumulate("sysConfigId", rs.getString("SYS_CONFIG_ID"));
			json.accumulate("updateName", rs.getString("UPDATE_NAME"));
			json.accumulate("initPwd", rs.getString("INIT_PWD"));
			json.accumulate("pwdCycle", rs.getString("PWD_CYCLE"));
			json.accumulate("outPwd", rs.getString("OUT_PWD"));
			json.accumulate("pwdWidth", rs.getString("PWD_WIDTH"));
			json.accumulate("isAbc", rs.getString("IS_ABC"));
			json.accumulate("missNum", rs.getString("MISS_NUM"));
			json.accumulate("missTime", rs.getString("MISS_TIME"));
			json.accumulate("missPwd", rs.getString("MISS_PWD"));
			json.accumulate("findPwd", rs.getString("FIND_PWD"));
			json.accumulate("remberUser", rs.getString("REMBER_USER"));
			json.accumulate("moreLogin", rs.getString("MORE_LOGIN"));
			json.accumulate("comWithPhone", rs.getString("COM_WITH_PHONE"));
			json.accumulate("useKey", rs.getString("USE_KEY"));
			json.accumulate("domainLogin", rs.getString("DOMAIN_LOGIN"));
			json.accumulate("userIp", rs.getString("USER_IP"));
			json.accumulate("remberStatus", rs.getString("REMBER_STATUS"));
			
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	
	public String getOrgIdByaccountId(Connection conn,String userName) throws SQLException{	
		String queryStr="SELECT ORG_ID FROM ACCOUNT WHERE ACCOUNT_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userName);
		ResultSet rs =ps.executeQuery();
		String returnData = "";
		if(rs.next())
		{
			returnData = rs.getString("ORG_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	public int updateSysConfig(Connection conn,SysConfig sysConfig )throws Exception{
		String sql = "UPDATE SYS_CONFIG SET UPDATE_NAME = ?,INIT_PWD = ?,PWD_CYCLE = ?,"
				+ "OUT_PWD = ?,PWD_WIDTH = ?,IS_ABC = ?,MISS_NUM = ?,MISS_TIME = ?,MISS_PWD = ?,"
				+ "FIND_PWD = ?,REMBER_USER = ?,MORE_LOGIN = ?,COM_WITH_PHONE = ?,USE_KEY = ?,"
				+ "DOMAIN_LOGIN = ?,USER_IP = ?,REMBER_STATUS = ? WHERE SYS_CONFIG_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sysConfig.getUpdateName());
		ps.setString(2, sysConfig.getInitPwd());
		ps.setString(3, sysConfig.getPwdCycle());
		ps.setString(4, sysConfig.getOutPwd());
		ps.setString(5, sysConfig.getPwdWidth());
		ps.setString(6, sysConfig.getIsAbc());
		ps.setString(7, sysConfig.getMissNum());
		ps.setString(8, sysConfig.getMissTime());
		ps.setString(9, sysConfig.getMissPwd());
		ps.setString(10, sysConfig.getFindPwd());
		ps.setString(11, sysConfig.getRemberUser());
		ps.setString(12, sysConfig.getMoreLogin());
		ps.setString(13, sysConfig.getComWithPhone());
		ps.setString(14, sysConfig.getUseKey());
		ps.setString(15, sysConfig.getDomainLogin());
		ps.setString(16, sysConfig.getUserIp());
		ps.setString(17, sysConfig.getRemberStatus());
		ps.setString(18, sysConfig.getSysConfigId());
		ps.setString(19, sysConfig.getOrgId());
		
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

}
