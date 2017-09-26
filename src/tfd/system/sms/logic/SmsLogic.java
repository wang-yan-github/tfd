package tfd.system.sms.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.sms.data.Sms;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.SysTool;

public class SmsLogic {
	public BaseDao dao=new BaseDaoImpl();
	/**
	 * 发起内部短信
	 * @param conn
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public int sendSmsLogic(Connection conn, Sms sms) throws Exception {
		PreparedStatement ps = null;
		try {

			String toUsers = sms.getSmsTo();
			String[] smsTo = toUsers.split(",");
			for (int i = 0; smsTo.length > i; i++) {
				if (smsTo[i].trim().equals("")) {
					continue;
				}
				String queryStr = "INSERT INTO SMS ("
						+ "				SMS_ID,SMS_FROM,SMS_TO,SMS_SEND_TIME,SMS_CONTENT,"
						+ "				SMS_FLAG,SMS_TYPE,SMS_URL,SMS_ATTACH_ID,SMS_ATTACH_PRIV,SMS_STATUS,ORG_ID"
						+ "			)"
						+ "			VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(queryStr);
				ps.setString(1, sms.getSmsId());
				ps.setString(2, sms.getSmsFrom());
				ps.setString(3, smsTo[i]);
				ps.setTimestamp(4,new Timestamp(sms.getSmsSendTime().getTime()));
				ps.setString(5, sms.getSmsContent());
				ps.setString(6, sms.getSmsFlag());
				ps.setString(7, sms.getSmsType());
				ps.setString(8, sms.getSmsUrl());
				ps.setString(9, sms.getSmsAttachId());
				ps.setString(10, sms.getSmsAttachPriv());
				ps.setString(11, sms.getSmsStatus());
				ps.setString(12, sms.getOrgId());
				return ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
		return 0;
	}

	/**
	 * 获取未读内部短信息
	 * @param conn
	 * @param account
	 * @param nowTime
	 * @return
	 * @throws Exception
	 */
	public JSONArray getNoReadSmsLogic(Connection conn, Account account) throws Exception {
		JSONArray smsList = new JSONArray();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			
			String queryStr = "SELECT "
					+ "			SMS_ID,SMS_FROM,SMS_SEND_TIME,SMS_CONTENT,"
					+ "			SMS_TYPE,SMS_URL,SMS_ATTACH_ID,SMS_ATTACH_PRIV,"
					+ "			SMS_STATUS,ORG_ID "
					+ "			FROM SMS "
					+ "			WHERE SMS_FLAG=? AND SMS_TO=? AND SMS_SEND_TIME<?";
			
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, Sms.SMS_FLAG_NOT_DELETE);
			ps.setString(2, account.getAccountId());
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));
			
			rs = ps.executeQuery();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			while (rs.next()) {
				JSONObject sms = new JSONObject();
				sms.accumulate("SMS_ID", rs.getString("SMS_ID"));
				sms.accumulate("SMS_FROM", rs.getString("SMS_FROM"));
				sms.accumulate("SMS_FROM_NAME",
						userInfoLogic.getUserNameByAccountIdLogic(conn,rs.getString("SMS_FROM"))
						);
				sms.accumulate("SMS_SEND_TIME",SysTool.getDateTimeStr(rs.getTimestamp("SMS_SEND_TIME"),DateConst.VALUE_LONG_DATE24));
				sms.accumulate("SMS_CONTENT", rs.getString("SMS_CONTENT"));
				sms.accumulate("SMS_TYPE", rs.getString("SMS_TYPE"));
				
				sms.accumulate("SMS_URL", rs.getString("SMS_URL"));
				sms.accumulate("SMS_ATTACH_ID", rs.getString("SMS_ATTACH_ID"));
				sms.accumulate("SMS_ATTACH_PRIV", rs.getString("SMS_ATTACH_PRIV"));
				sms.accumulate("SMS_STATUS", rs.getString("SMS_STATUS"));
				sms.accumulate("ORG_ID", rs.getString("ORG_ID"));
				smsList.add(sms);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return smsList;
	}

	/**
	 * 更改SMS已读状态
	 * @param conn
	 * @param smsId
	 * @return
	 * @throws Exception
	 */
	public int updateSmsReadLogic(Connection conn, String smsId) throws Exception {
		PreparedStatement ps=null;
		try {
			String queryStr = "UPDATE SMS SET SMS_FLAY=? WHERE SMS_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, Sms.SMS_STATUS_HAVE_READ);
			ps.setString(1, smsId);
			
			return ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
	}

	/**
	 * 删除SMS短信
	 * @param conn
	 * @param smsId
	 * @return
	 * @throws Exception
	 */
	public int deleteSmsLogic(Connection conn, String smsId) throws Exception {
		PreparedStatement ps=null;
		try {
			String queryStr = "DELETE FROM SMS WHERE SMS_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, smsId);
			
			return ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, ps, null);
		}
	}
}
