package tfd.system.search.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

public class SearchLogic {

	public String getSearchData(Connection conn, Account account,String content)throws Exception{
		JSONArray jsonArr = new JSONArray();
		JSONObject emailJson = this.getEmailData(conn, account, content);
		jsonArr.add(emailJson);
		return jsonArr.toString();
	}
	
	public JSONObject getEmailData(Connection conn,Account account,String content)throws Exception{
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT t1.EMAIL_ID,t2.BODY_ID,t1.BOX_ID,t1.TO_ID,"
				+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t1.TO_ID AND t3.ORG_ID = t2.ORG_ID ) AS TO_NAME,"
				+ "t2.FROM_ID,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS FROM_NAME,"
				+ "t2.CONTENT,t2.SEND_TIME,t2.SUBJECT FROM email"
				+ " t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE (  t2.FROM_ID IN "
				+ "(SELECT U.ACCOUNT_ID FROM USER_INFO U WHERE USER_NAME LIKE ? AND t2.ORG_ID = U.ORG_ID ) "
				+ "OR t1.TO_ID IN (SELECT U.ACCOUNT_ID FROM USER_INFO U WHERE USER_NAME LIKE ? AND t2.ORG_ID = U.ORG_ID )"
				+ "  OR t2.CONTENT LIKE ? OR t2.SUBJECT LIKE ? ) AND t1.ORG_ID = ? AND (t1.TO_ID = ? OR t2.FROM_ID = ? ) ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, "%"+content+"%");
		ps.setString(2, "%"+content+"%");
		ps.setString(3, "%"+content+"%");
		ps.setString(4, "%"+content+"%");
		ps.setString(5, account.getOrgId());
		ps.setString(6, account.getAccountId());
		ps.setString(7, account.getAccountId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(rs.getString("TO_ID").indexOf(",")<0){
				JSONObject tmp = new JSONObject();
				tmp.accumulate("emailId", rs.getString("EMAIL_ID"));
				tmp.accumulate("bodyId", rs.getString("BODY_ID"));
				String boxName = this.getBoxName(conn, rs.getString("BOX_ID"), account.getOrgId());
				tmp.accumulate("boxId", boxName);
				tmp.accumulate("toId", rs.getString("TO_ID"));
				tmp.accumulate("toName", rs.getString("TO_NAME"));
				tmp.accumulate("fromId", rs.getString("FROM_ID"));
				tmp.accumulate("fromName", rs.getString("FROM_NAME"));
				tmp.accumulate("content", rs.getString("CONTENT"));
				tmp.accumulate("sendTime", rs.getString("SEND_TIME"));
				tmp.accumulate("subject", rs.getString("SUBJECT"));
				jsonArr.add(tmp);
			}
		}
		rs.close();
		ps.close();
		json.accumulate("list", jsonArr);
		json.accumulate("module", "email");
		return json;
	}
	
	public String getBoxName(Connection conn,String boxId,String orgId)throws Exception{
		String returnData = "";
		if(boxId.equals("1")){
			returnData = "收件箱";
		}else if(boxId.equals("2")){
			returnData = "发件箱";
		}else if(boxId.equals("3")){
			returnData = "草稿箱";
		}else if(boxId.equals("4")){
			returnData = "废件箱";
		}else{
			String queryStr = "SELECT BOX_NAME FROM EMAIL_BOX WHERE BOX_ID = ? AND ORG_ID = ?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, boxId);
			ps.setString(2, orgId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				returnData = rs.getString("BOX_NAME");
			}
			rs.close();
			ps.close();
		}
		return returnData;
	}

}
