package tfd.system.interfaces.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONObject;
import tfd.system.interfaces.data.Interface;
import tfd.system.unit.account.data.Account;

public class InterfaceLogic {
	/**
	 * 添加界面设置信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param inter
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int addInterface(Connection conn,Interface inter,Account account)throws Exception{
		String sql = "INSERT INTO INTERFACE(INTERFACE_ID,TOP_TITLE,TOP_IMG,BOTTOM_TITLE,BROWSER_TITLE,ORG_ID) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, inter.getInterfaceId());
		ps.setString(2, inter.getTopTitle());
		ps.setString(3, inter.getTopImg());
		ps.setString(4, inter.getBottomTitle());
		ps.setString(5, inter.getBrowserTitle());
		ps.setString(6, account.getOrgId());
		int i = ps.executeUpdate();
		
		return i;
	}
	
	/**
	 * 修改界面设置信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param inter
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int updateInterface(Connection conn,Interface inter,Account account)throws Exception{
		String sql = "UPDATE INTERFACE SET TOP_TITLE = ?,TOP_IMG = ?,BOTTOM_TITLE = ?,BROWSER_TITLE = ? WHERE INTERFACE_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, inter.getTopTitle());
		ps.setString(2, inter.getTopImg());
		ps.setString(3, inter.getBottomTitle());
		ps.setString(4, inter.getBrowserTitle());
		ps.setString(5, inter.getInterfaceId());
		ps.setString(6, account.getOrgId());
		int i = ps.executeUpdate();
		
		return i;
	}
	
	/**
	 * 获取界面设置信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getInterface(Connection conn,Account account)throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT INTERFACE_ID,TOP_TITLE,TOP_IMG,BOTTOM_TITLE,BROWSER_TITLE,ORG_ID FROM INTERFACE WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("interfaceId", rs.getString("INTERFACE_ID"));
			json.accumulate("topTitle", rs.getString("TOP_TITLE"));
			json.accumulate("topImg", rs.getString("TOP_IMG"));
			json.accumulate("bottomTitle", rs.getString("BOTTOM_TITLE"));
			json.accumulate("browserTitle", rs.getString("BROWSER_TITLE"));
		}
		
		return json.toString();
	}
}
