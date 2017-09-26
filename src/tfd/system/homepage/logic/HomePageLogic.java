package tfd.system.homepage.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.system.tool.GuId;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

public class HomePageLogic {

	/**
	 * 获取首页数据
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getHomePage(Connection conn, Account account)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT HOME_PAGE_ID,MODULE,MODULE_NAME,ADDRESS_TD,ADDRESS_TR,IS_OPEN,ACCOUNT_ID,ORG_ID FROM HOME_PAGE WHERE ACCOUNT_ID = ? AND ORG_ID = ? ORDER BY ADDRESS_TR ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		boolean flag = false;
		while(rs.next()){
			flag = true;
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("HOME_PAGE_ID"));
			json.accumulate("module", rs.getString("MODULE"));
			json.accumulate("moduleName", rs.getString("MODULE_NAME"));
			json.accumulate("addressTd", rs.getString("ADDRESS_TD"));
			json.accumulate("addressTr", rs.getString("ADDRESS_TR"));
			json.accumulate("isOpen", rs.getString("IS_OPEN"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			jsonArr.add(json);
		}
		if(!flag){
			jsonArr = this.createHomePage(conn, account);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 创建首页默认数据
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public JSONArray createHomePage(Connection conn, Account account)throws Exception{
		String[] modules = new String[]{"news","notify","calendar","email"};
		String[] moduleNames = new String[]{"新闻","通知公告","个人日程","内部邮件"};
		String[] addresssTd = new String[]{"1","1","2","2"};
		String[] addresssTr = new String[]{"1","2","1","2"};
		JSONArray jsonArr = new JSONArray();
		PreparedStatement ps = null;
		for (int i = 0; i < modules.length; i++) {
			JSONObject json = new JSONObject();
			String guid = GuId.getGuid();
			String sql = "INSERT INTO HOME_PAGE(HOME_PAGE_ID,MODULE,MODULE_NAME,ADDRESS_TD,ADDRESS_TR,IS_OPEN,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, guid);
			ps.setString(2, modules[i]);
			ps.setString(3, moduleNames[i]);
			ps.setString(4, addresssTd[i]);
			ps.setString(5, addresssTr[i]);
			ps.setString(6, "1");
			ps.setString(7, account.getAccountId());
			ps.setString(8, account.getOrgId());
			ps.executeUpdate();
			json.accumulate("id", guid);
			json.accumulate("module", modules[i]);
			json.accumulate("moduleName", moduleNames[i]);
			json.accumulate("addressTd", addresssTd[i]);
			json.accumulate("addressTr", addresssTr[i]);
			json.accumulate("isOpen", "1");
			json.accumulate("accountId", account.getAccountId());
			json.accumulate("orgId", account.getOrgId());
			jsonArr.add(json);
		}
		ps.close();
		
		return jsonArr;
	}

	/**
	 * 修改首页数据
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param id
	 * @param addressTd
	 * @param addressTr
	 * @throws Exception
	 */
	public void updateHomePage(Connection conn, Account account, String id,String addressTd, String addressTr)throws Exception{
		String sql = "UPDATE HOME_PAGE SET ADDRESS_TD = ?,ADDRESS_TR = ? WHERE HOME_PAGE_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, addressTd);
		ps.setString(2, addressTr);
		ps.setString(3, id);
		ps.setString(4, account.getOrgId());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 开关首页模块
	 * Time:2016-01-21
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param id
	 * @param isOpen
	 * @throws Exception
	 */
	public int closeModule(Connection conn, Account account, String id,String isOpen)throws Exception{
		String sql = "UPDATE HOME_PAGE SET IS_OPEN = ? WHERE HOME_PAGE_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, isOpen);
		ps.setString(2, id);
		ps.setString(3, account.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	public String getModuleById(Connection conn, Account account, String id)throws Exception{
		JSONObject json = new JSONObject();
		String queryStr = "SELECT HOME_PAGE_ID,MODULE_NAME,IS_OPEN FROM HOME_PAGE WHERE HOME_PAGE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, id);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("id", rs.getString("HOME_PAGE_ID"));
			json.accumulate("moduleName", rs.getString("MODULE_NAME"));
			json.accumulate("isOpen", rs.getString("IS_OPEN"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	public int updateModuleById(Connection conn, Account account, String id,String isOpen, String moduleName)throws Exception{
		String sql = "UPDATE HOME_PAGE SET IS_OPEN = ?, MODULE_NAME = ? WHERE HOME_PAGE_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, isOpen);
		ps.setString(2, moduleName);
		ps.setString(3, id);
		ps.setString(4, account.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

}
