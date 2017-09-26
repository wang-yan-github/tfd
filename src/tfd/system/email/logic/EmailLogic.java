package tfd.system.email.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tfd.system.email.data.Email;
import tfd.system.email.data.EmailBody;
import tfd.system.email.data.EmailBox;
import tfd.system.email.data.EmailConfig;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.GuId;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmailLogic {
	/**
	 *  获取邮件列表
	 * Time : 2015-3-25
	 * Author : Yzz
	 * @param conn ：jdbc链接
	 * @param userId ：用户Id
	 * @param orgId : 企业Id
	 * @param boxId : 文件夹标记   1-收件箱，2-发件箱，3-草稿箱，4-废件箱
	 * @return : 
	 * @throws Exception
	 */
	public String getEmailList(Connection conn,List<String> pramList,String boxId,int pagesize,int page,String storOrder,String storName,String search)throws Exception{
		String queryStr = null;
		if(boxId.equals("3")||boxId.equals("2")||boxId.equals("4")){
			storOrder = "DESC";
			storName = "SEND_TIME";
		}
		if(search.equals("")){
			if(boxId.equals("3")||boxId.equals("2")){
				queryStr = "SELECT t3.* FROM (SELECT t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID  ) AS USER_NAME "
						 + ",t1.READ_FLAG,t1.BOX_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE "
						 + "  t1.ORG_ID = ? AND t2.FROM_ID = ? AND t1.BOX_ID = ? ORDER BY SEND_TIME DESC) t3  ";
			}else{
				queryStr = "SELECT t3.* FROM (SELECT t1.EMAIL_ID AS EMAIL_ID,t2.FROM_ID AS FROM_ID,t2.SUBJECT AS SUBJECT,t2.CONTENT AS CONTENT,t2.SEND_TIME AS SEND_TIME,"
						+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID  ) AS USER_NAME "
						 + ",t1.READ_FLAG AS READ_FLAG,t1.BOX_ID AS BOX_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE "
						 + "  t1.ORG_ID = ? AND t1.TO_ID = ? AND t1.BOX_ID = ? ORDER BY SEND_TIME DESC) t3  ";
			}
		}else{
			if(boxId.equals("3")||boxId.equals("2")){
				queryStr = "SELECT t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS USER_NAME "
						 + ",t1.READ_FLAG,t1.BOX_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE "
						 + " t1.ORG_ID = ? AND t2.FROM_ID = ? AND t1.BOX_ID = ? AND (t2.SUBJECT LIKE  '%"+search+"%' OR t2.FROM_ID in "
						 + "(select ACCOUNT_ID from USER_INFO where USER_NAME like '%"+search+"%' AND t2.ORG_ID = ORG_ID ) OR "
						 		+ "SEND_TIME LIKE binary '%"+search+"%' OR CONTENT LIKE '%"+search+"%')";
			}else{
				queryStr = "SELECT t4.* FROM (SELECT t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID ) AS USER_NAME "
						 + ",t1.READ_FLAG,t1.BOX_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE "
						 + " t1.ORG_ID = ? AND t1.TO_ID = ? AND t1.BOX_ID = ? AND (t2.SUBJECT LIKE  '%"+search+"%' OR t2.FROM_ID in "
						 + "(select ACCOUNT_ID from USER_INFO where USER_NAME like '%"+search+"%' AND t2.ORG_ID = ORG_ID ) OR "
						 + "SEND_TIME LIKE binary '%"+search+"%' OR CONTENT LIKE '%"+search+"%' ) ORDER BY SEND_TIME DESC ) t4  ";
			}
			
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	/**
	 * 发送邮件
	 * Time:2015-3-27
	 * Author:Yzz
	 * @param conn ：jdbc链接
	 * @param email :邮件实体
	 * @return
	 * @throws Exception
	 */
	public int sendEmail(Connection conn,Email email)throws Exception{
		//添加到收件人收件箱
		String emailId =  GuId.getGuid();
		String sql = "INSERT INTO EMAIL(EMAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG,BOX_ID,BODY_ID,ORG_ID) VALUES(?,?,'1','1','1',?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ps.setString(2, email.getToId());
		ps.setString(3, email.getBodyId());
		ps.setString(4, email.getOrgId());
		int s =  ps.executeUpdate();
		ps.close();
		return s;
	}
	/**
	 * 添加邮件到发件箱
	 * Time:2015-3-27
	 * Author:Yzz
	 * @param conn ：jdbc链接
	 * @param email :邮件实体
	 * @return
	 * @throws Exception
	 */
	public int addEmailtoSend(Connection conn,Email email)throws Exception{
		//添加到发件箱
		String sql = "INSERT INTO EMAIL(EMAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG,BOX_ID,BODY_ID,ORG_ID) VALUES(?,?,'1','1','2',?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email.getEmailId());
		ps.setString(2, email.getToId());
		ps.setString(3, email.getBodyId());
		ps.setString(4, email.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 保存到草稿箱
	 * Time:2015-3-31
	 * Author:Yzz
	 * @param conn : jdbc链接
	 * @param email: 邮件实体
	 * @return
	 * @throws Exception
	 */
	public int saveEmailToDraft(Connection conn,Email email)throws Exception{
		//添加到草稿箱
		String sql = "INSERT INTO EMAIL(EMAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG,BOX_ID,BODY_ID,ORG_ID) VALUES(?,?,'1','1','3',?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email.getEmailId());
		ps.setString(2, email.getToId());
		ps.setString(3, email.getBodyId());
		ps.setString(4, email.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 保存邮件
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param conn : jdbc链接
	 * @param body : 邮件内容实体
	 * @return
	 * @throws Exception
	 */
	public String saveEmail(Connection conn,EmailBody body)throws Exception{
		String bodyId = GuId.getGuid();
		String sql = "INSERT INTO EMAIL_BODY(BODY_ID,FROM_ID,TO_ID,SUBJECT,CONTENT,SEND_TIME,SEND_FLAG";
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr = " VALUES(?,?,?,?,?,sysdate(),1";
		}else if(dbType.equals("oracle")){
			queryStr = " VALUES(?,?,?,?,?,sysdate,1";
		}
		//是否有抄送人
		if(body.getCopyToId() != null){
			sql += ",COPY_TO_ID";
			queryStr += ",'"+body.getCopyToId()+"'";
		}
		//是否有附件
		if(body.getAttachmentId()!=null){
			sql += ",ATTACHMENT_ID";
			queryStr += ",'"+body.getAttachmentId()+"'";
		}
		//是否有短信提醒
		if(body.getSmsRemind() != null){
			sql += ",SMS_REMIND";
			queryStr += ",'1'";
		}
		//是否为重要邮件
		if(body.getImportant() != null){
			sql += ",IMPORTANT";
			queryStr += ",'"+body.getImportant()+"'";
		}
		//接收是否为外部邮件
		if(body.getFromWebmailId() != null){
			sql += ",WEBMAIL_FLAG,FROM_WEBMAIL_ID";
			queryStr += ",'2','"+body.getFromWebmailId()+"'";
		}
		//发送是否为外部邮件
		if(body.getToWebmail() != null){
			if(!body.getToWebmail().equals("")){
				sql += ",IS_WEBMAIL,TO_WEBMAIL";
				queryStr += ",'2','"+body.getToWebmail()+"'";
			}
		}
		//是否有外部抄送人
		if(body.getToWebmailCopy() != null){
			sql += ",TO_WEBMAIL_COPY";
			queryStr += ",'"+body.getToWebmailCopy()+"'";
		}
		sql += ",ORG_ID)"+queryStr + ",?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bodyId);
		ps.setString(2, body.getFromId());
		ps.setString(3, body.getToId());
		ps.setString(4, body.getSubject());
		ps.setString(5, body.getContent());
		ps.setString(6, body.getOrgId());
		ps.executeUpdate();
		ps.close();
		return bodyId;
	}
	
	/**
	 *  获取邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param conn ：jdbc链接
	 * @return : 
	 * @throws Exception
	 */
	public String getEmailCount(Connection conn,String accountId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT DISTINCT (SELECT COUNT(t1.EMAIL_ID) FROM EMAIL t1 WHERE t1.BOX_ID = '1' AND t1.READ_FLAG = '1' AND t1.ORG_ID = ? AND t1.TO_ID = ? ) AS INCOUNT,"
				+ "(SELECT COUNT(t6.EMAIL_ID) FROM EMAIL_BODY t2,EMAIL t6 WHERE t2.BODY_ID = t6.BODY_ID AND t6.BOX_ID = '2'  AND t2.FROM_ID = ? AND t6.ORG_ID = ?) AS SENDCOUNT,"
				+ "(SELECT COUNT(t5.EMAIL_ID) FROM EMAIL_BODY t3,EMAIL t5 WHERE t3.BODY_ID = t5.BODY_ID AND t5.BOX_ID = '3'  AND t3.FROM_ID = ? AND t5.ORG_ID = ? ) AS DRAFTCOUNT, "
				+ "(SELECT COUNT(t4.EMAIL_ID) FROM EMAIL t4 WHERE t4.BOX_ID = '4'  AND t4.ORG_ID = ? AND t4.TO_ID = ?) AS OUTCOUNT," 
				+ "(SELECT COUNT(t7.EMAIL_ID) FROM EMAIL t7 WHERE t7.BOX_ID != '1' AND t7.BOX_ID != '2' AND t7.BOX_ID != '3' AND t7.BOX_ID != '4' AND t7.ORG_ID = ? AND t7.TO_ID = ?) AS OTHERCOUNT FROM DUAL";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ps.setString(3, accountId);
		ps.setString(4, orgId);
		ps.setString(5, accountId);
		ps.setString(6, orgId);
		ps.setString(7, orgId);
		ps.setString(8, accountId);
		ps.setString(9, orgId);
		ps.setString(10, accountId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("InCount", rs.getString("INCOUNT"));
			json.accumulate("SendCount", rs.getString("SENDCOUNT"));
			json.accumulate("DraftCount", rs.getString("DRAFTCOUNT"));
			json.accumulate("OutCount", rs.getString("OUTCOUNT"));
			json.accumulate("OtherCount", rs.getString("OTHERCOUNT"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 *  获取未读邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param conn ：jdbc链接
	 * @return : 
	 * @throws Exception
	 */
	public String getEmailInCount(Connection conn,String accountId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT (SELECT COUNT(t1.EMAIL_ID) FROM EMAIL t1 WHERE t1.BOX_ID = '1' AND t1.READ_FLAG = '1' AND t1.ORG_ID = ? AND t1.TO_ID = ?) AS noRead,"
				+ "(SELECT COUNT(t1.EMAIL_ID) FROM EMAIL t1 WHERE t1.BOX_ID != '2' AND t1.BOX_ID != '3' AND t1.BOX_ID != '4' AND t1.ORG_ID = ? AND t1.TO_ID = ?) AS InCount FROM DUAL";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		ps.setString(4, accountId);
		rs=ps.executeQuery();
		if(rs.next()){
			json.accumulate("noReadNum", rs.getString("noRead"));
			json.accumulate("InCount", rs.getString("InCount"));
		}else{
			json.accumulate("noReadNum", "0");
			json.accumulate("InCount", "0");
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 *  获取未读邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param conn ：jdbc链接
	 * @return : 
	 * @throws Exception
	 */
	public String getEmailBoxCount(Connection conn,String boxId,String accountId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT COUNT(EMAIL_ID) AS BOXCOUNT FROM EMAIL  WHERE BOX_ID = ? AND TO_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boxId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		rs=ps.executeQuery();
		if(rs.next()){
			json.accumulate("boxCount", rs.getString("BOXCOUNT"));
		}else{
			json.accumulate("boxCount", "");
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 根据邮件Id查看具体邮件内容
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param conn : jdbc链接
	 * @param emailId : 邮件Id
	 * @param orgId : 企业Id
	 * @return
	 * @throws Exception
	 */
	public String getEmailById(Connection conn,String emailId,String orgId,String accountId,String boxId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr = "";
		if(boxId.equals("3")||boxId.equals("2")){
			queryStr="SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t1.BOX_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS FROM_NAME,(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME "
					 + " , t2.ATTACHMENT_ID,t2.IMPORTANT,t2.COPY_TO_ID,t2.TO_WEBMAIL,t2.SMS_REMIND FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  "
					 + "  t1.ORG_ID = ?  AND t1.EMAIL_ID = ? AND t2.FROM_ID = ? ";
		}else{
			queryStr="SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t1.BOX_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS FROM_NAME,(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME "
					 + " , t2.ATTACHMENT_ID,t2.IMPORTANT,t2.COPY_TO_ID,t2.TO_WEBMAIL,t2.SMS_REMIND FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  "
					 + " t1.ORG_ID = ?  AND t1.EMAIL_ID = ? AND t1.TO_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, emailId);
		ps.setString(3, accountId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("fromId", rs.getString("FROM_ID"));
			json.accumulate("fromName", rs.getString("FROM_NAME"));
			json.accumulate("subject", rs.getString("SUBJECT"));
			json.accumulate("content", rs.getString("CONTENT"));
			String time = rs.getString("SEND_TIME");
			Calendar c=Calendar.getInstance();
			c.set(Calendar.YEAR,Integer.parseInt( time.substring(0,4)));  
		    c.set(Calendar.MONTH, Integer.parseInt(time.substring(5,7)) - 1);  
		    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(8,10)));
			int i = c.get(Calendar.DAY_OF_WEEK);
			String week = "";
			if(i == 1){
				week = "星期天";
			}else if(i == 2){
				week = "星期一";
			}else if(i == 3){
				week = "星期二";
			}else if(i == 4){
				week = "星期三";
			}else if(i == 5){
				week = "星期四";
			}else if(i == 6){
				week = "星期五";
			}else if(i == 7){
				week = "星期六";
			}
			time = time.substring(0,4)+"年"+time.substring(5,7)+"月"+time.substring(8,10)+"日"+"("+week+") "+time.substring(11,16);
			json.accumulate("sendTime", time);
			String toName = "";
			if(boxId.equals("3")||boxId.equals("2")){
				toName = getAllToName2(conn, rs.getString("BODY_ID"),rs.getString("TO_ID"));
			}else{
				toName = getAllToName(conn, rs.getString("BODY_ID"),boxId);
			}
			json.accumulate("toName", toName);
			json.accumulate("emailId", rs.getString("EMAIL_ID"));
			json.accumulate("bodyId", rs.getString("BODY_ID"));
			String toId = getAllToId(conn, rs.getString("BODY_ID"),boxId);
			json.accumulate("toId", toId);
			json.accumulate("attachId", rs.getString("ATTACHMENT_ID"));
			json.accumulate("important", rs.getString("IMPORTANT"));
			String copyToId = rs.getString("COPY_TO_ID");
			if(copyToId==null){
				copyToId = "";
			}
			json.accumulate("copyToId",copyToId);
			String copyToName = getAllToName1(conn, rs.getString("BODY_ID"),copyToId);
			json.accumulate("copyToName",copyToName );
			json.accumulate("toWebMail", rs.getString("TO_WEBMAIL"));
			json.accumulate("isSms", rs.getString("SMS_REMIND"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 改变邮件未读状态
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param emailId 邮件id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public int changeEmailFlag(Connection conn,String emailId,String orgId)throws Exception{
		String sql = "UPDATE EMAIL SET READ_FLAG = 2 WHERE EMAIL_ID = ?  AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 改变邮件未读状态
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param emailId 邮件id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public int updateEmail(Connection conn,Email email)throws Exception{
		String sql = "UPDATE EMAIL SET TO_ID = ? WHERE EMAIL_ID = ?  AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email.getToId());
		ps.setString(2, email.getEmailId());
		ps.setString(3, email.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 删除邮件到废件箱
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param emailId 邮件id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public int delEmail(Connection conn,String emailId,String orgId)throws Exception{
		String sql = "UPDATE EMAIL SET DELETE_FLAG = '2',BOX_ID = '4' WHERE EMAIL_ID = ?  AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 彻底删除邮件
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param emailId 邮件id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public int deleteEmail(Connection conn,String emailId,String orgId)throws Exception{
		String sql = "DELETE FROM EMAIL WHERE EMAIL_ID = ?  AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改邮件主体内容
	 * Time ： 2015-3-31
	 * Author : Yzz 
	 * @param conn
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public int updateEmailBody(Connection conn,EmailBody body)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String sql = "";
		if(dbType.equals("mysql")){
			sql = "UPDATE EMAIL_BODY SET FROM_ID = ?,TO_ID = ?,SUBJECT= ?,CONTENT= ?,SEND_TIME=sysdate(),SEND_FLAG=1";
		}else if(dbType.equals("oracle")){
			sql = "UPDATE EMAIL_BODY SET FROM_ID = ?,TO_ID = ?,SUBJECT= ?,CONTENT= ?,SEND_TIME=sysdate,SEND_FLAG=1";
		}
		//是否有抄送人
		if(body.getCopyToId() != null){
			sql += ",COPY_TO_ID='"+body.getCopyToId()+"'";
		}
		//是否有附件
		if(body.getAttachmentId()!=null){
			sql += ",ATTACHMENT_ID='"+body.getAttachmentId()+"',ATTACHMENT_NAME='"+body.getAttachmentName()+"'";
		}
		//是否有短信提醒
		if(body.getSmsRemind() != null){
			sql += ",SMS_REMIND='"+body.getSmsRemind()+"'";
		}
		//是否为重要邮件
		if(body.getImportant() != null){
			sql += ",IMPORTANT='"+body.getImportant()+"'";
		}
		//接收是否为外部邮件
		if(body.getFromWebmailId() != null){
			sql += ",WEBMAIL_FLAG='2',FROM_WEBMAIL_ID='"+body.getFromWebmailId()+"'";
		}
		//发送是否为外部邮件
		if(body.getToWebmail() != null){
			sql += ",IS_WEBMAIL='2',TO_WEBMAIL='"+body.getToWebmail()+"'";
		}
		//是否有外部抄送人
		if(body.getToWebmailCopy() != null){
			sql += ",TO_WEBMAIL_COPY='"+body.getToWebmailCopy()+"'";
		}
		sql += ",ORG_ID= ? WHERE BODY_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, body.getFromId());
		ps.setString(2, body.getToId());
		ps.setString(3, body.getSubject());
		ps.setString(4, body.getContent());
		ps.setString(5, body.getOrgId());
		ps.setString(6, body.getBodyId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 更改邮件箱
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param conn : jdbc
	 * @param emailId : 邮件Id
	 * @param boxId : 邮箱Id
	 * @param orgId ： 企业Id
	 * @return
	 * @throws Exception
	 */
	public int updateEmailBox(Connection conn,String emailId,String boxId,String orgId)throws Exception{
		String sql = "UPDATE EMAIL SET BOX_ID = ? WHERE EMAIL_ID = ?  AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, boxId);
		ps.setString(2, emailId);
		ps.setString(3, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 复制邮件数据
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param conn : jdbc
	 * @param emailId : 邮件Id
	 * @param boxId : 邮箱Id
	 * @param orgId ： 企业Id
	 * @return
	 * @throws Exception
	 */
	public int copyEmail(Connection conn,String emailId,String boxId,String orgId,String toId,String bodyId)throws Exception{
		String eid = GuId.getGuid();
		String sql = "INSERT INTO EMAIL(EMAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG,BOX_ID,BODY_ID,ORG_ID) SELECT ?,?,'1',DELETE_FLAG,?,?,ORG_ID FROM EMAIL WHERE  EMAIL_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, eid);
		ps.setString(2, toId);
		ps.setString(3, boxId);
		ps.setString(4, bodyId);
		ps.setString(5, emailId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 复制邮件数据
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param conn : jdbc
	 * @param emailId : 邮件Id
	 * @param boxId : 邮箱Id
	 * @param orgId ： 企业Id
	 * @return
	 * @throws Exception
	 */
	public String copyEmailBody(Connection conn,String emailId,String boxId,String orgId)throws Exception{
		String bId = GuId.getGuid();
		String bodyId = getBodyIdbyEmailId(conn, emailId);
		String dbType =DbPoolConnection.getInstance().getDbType();
		String str = "";
		if(dbType.equals("mysql")){
			str = "INSERT INTO EMAIL_BODY(BODY_ID,FROM_ID,TO_ID,COPY_TO_ID,"
					+ "SUBJECT,CONTENT,SEND_TIME,ATTACHMENT_ID,ATTACHMENT_NAME,SEND_FLAG,"
					+ "SMS_REMIND,IMPORTANT,WEBMAIL_FLAG,FROM_WEBMAIL,FROM_WEBMAIL_ID,"
					+ "WEBMAIL_CONTENT,IS_WEBMAIL,TO_WEBMAIL,TO_WEBMAIL_COPY,ORG_ID) SELECT ? "
					+ ",FROM_ID,TO_ID,COPY_TO_ID,SUBJECT,CONTENT,sysdate(),ATTACHMENT_ID,ATTACHMENT_NAME,SEND_FLAG,"
					+ "SMS_REMIND,IMPORTANT,WEBMAIL_FLAG,FROM_WEBMAIL,FROM_WEBMAIL_ID,"
					+ "WEBMAIL_CONTENT,IS_WEBMAIL,TO_WEBMAIL,TO_WEBMAIL_COPY,ORG_ID FROM "
					+ "EMAIL_BODY WHERE BODY_ID = ?";
		}else if(dbType.equals("oracle")){
			str = "INSERT INTO EMAIL_BODY(BODY_ID,FROM_ID,TO_ID,COPY_TO_ID,"
					+ "SUBJECT,CONTENT,SEND_TIME,ATTACHMENT_ID,ATTACHMENT_NAME,SEND_FLAG,"
					+ "SMS_REMIND,IMPORTANT,WEBMAIL_FLAG,FROM_WEBMAIL,FROM_WEBMAIL_ID,"
					+ "WEBMAIL_CONTENT,IS_WEBMAIL,TO_WEBMAIL,TO_WEBMAIL_COPY,ORG_ID) SELECT ? "
					+ ",FROM_ID,TO_ID,COPY_TO_ID,SUBJECT,CONTENT,sysdate,ATTACHMENT_ID,ATTACHMENT_NAME,SEND_FLAG,"
					+ "SMS_REMIND,IMPORTANT,WEBMAIL_FLAG,FROM_WEBMAIL,FROM_WEBMAIL_ID,"
					+ "WEBMAIL_CONTENT,IS_WEBMAIL,TO_WEBMAIL,TO_WEBMAIL_COPY,ORG_ID FROM "
					+ "EMAIL_BODY WHERE BODY_ID = ?";
		}
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setString(1, bId);
		ps.setString(2, bodyId);
		ps.executeUpdate();
		ps.close();
		return bId;
	}
	/**
	 * 根据emailId得到EmailBody的Id
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getBodyIdbyEmailId(Connection conn,String emailId)throws Exception{
		String returnData = "";
		String sql = "SELECT BODY_ID FROM EMAIL WHERE EMAIL_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("BODY_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	/**
	 * 查看下一封邮件
	 * Time : 2015-4-1
	 * Author : Yzz
	 * @param conn : jdbc
	 * @param emailId : 邮件Id
	 * @param boxId : 邮箱Id
	 * @param orgId ： 企业Id
	 * @return
	 * @throws Exception
	 */
	public String getNextEmail(Connection conn,int id,String orgId,String accountId,String boxId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("oracle")){
			queryStr="SELECT tmp.* FROM (SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,"
					+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS FROM_NAME,"
					+ "(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME "
					+ " ,t2.IMPORTANT, t2.ATTACHMENT_ID,t2.COPY_TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE t1.TO_ID = ? AND t1.BOX_ID = ? AND t1.ID < ?  AND t1.ORG_ID=? ORDER BY t1.ID DESC) tmp WHERE ROWNUM = 1";
		}else if(dbType.equals("mysql")){
			queryStr="SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,"
					+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID ) AS FROM_NAME,"
					+ "(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME "
					+ " ,t2.IMPORTANT, t2.ATTACHMENT_ID,t2.COPY_TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE t1.TO_ID = ? AND t1.BOX_ID = ? AND t1.ID < ?  AND t1.ORG_ID=? ORDER BY t1.ID DESC LIMIT 1";
		}
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, boxId);
		ps.setInt(3, id);
		ps.setString(4, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("fromId", rs.getString("FROM_ID"));
			json.accumulate("fromName", rs.getString("FROM_NAME"));
			json.accumulate("subject", rs.getString("SUBJECT"));
			json.accumulate("content", rs.getString("CONTENT"));
			String time = rs.getString("SEND_TIME");
			Calendar c=Calendar.getInstance();
			c.set(Calendar.YEAR,Integer.parseInt( time.substring(0,4)));  
		    c.set(Calendar.MONTH, Integer.parseInt(time.substring(5,7)) - 1);  
		    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(8,10)));
			int i = c.get(Calendar.DAY_OF_WEEK);
			String week = "";
			if(i == 1){
				week = "星期天";
			}else if(i == 2){
				week = "星期一";
			}else if(i == 3){
				week = "星期二";
			}else if(i == 4){
				week = "星期三";
			}else if(i == 5){
				week = "星期四";
			}else if(i == 6){
				week = "星期五";
			}else if(i == 7){
				week = "星期六";
			}
			time = time.substring(0,4)+"年"+time.substring(5,7)+"月"+time.substring(8,10)+"日"+"("+week+") "+time.substring(11,16);
			json.accumulate("sendTime", time);
			String toName = "";
			if(boxId.equals("3")||boxId.equals("2")){
				toName = getAllToName2(conn, rs.getString("BODY_ID"),rs.getString("TO_ID"));
			}else{
				toName = getAllToName(conn, rs.getString("BODY_ID"),boxId);
			}
			json.accumulate("toName", toName);
			json.accumulate("emailId", rs.getString("EMAIL_ID"));
			json.accumulate("bodyId", rs.getString("BODY_ID"));
			String toId = getAllToId(conn, rs.getString("BODY_ID"),boxId);
			json.accumulate("toId", toId);
			json.accumulate("important", rs.getString("IMPORTANT"));
			json.accumulate("attachId", rs.getString("ATTACHMENT_ID"));
			String copyToId = rs.getString("COPY_TO_ID");
			if(copyToId==null){
				copyToId = "";
			}
			json.accumulate("copyToId", copyToId);
			String copyToName = getAllToName1(conn, rs.getString("BODY_ID"),copyToId);
			json.accumulate("copyToName",copyToName );
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 查看上一封邮件
	 * Time : 2015-4-1
	 * Author : Yzz
	 * @param conn : jdbc
	 * @param emailId : 邮件Id
	 * @param boxId : 邮箱Id
	 * @param orgId ： 企业Id
	 * @return
	 * @throws Exception
	 */
	public String getLastEmail(Connection conn,int id,String orgId,String accountId,String boxId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("oracle")){
			queryStr="SELECT tmp.* FROM (SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,"
					+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID ) AS FROM_NAME,"
					+ "(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID ) AS TO_NAME "
					+ " ,t2.IMPORTANT, t2.ATTACHMENT_ID,t2.COPY_TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  t1.TO_ID = ? AND t1.BOX_ID = ? AND t1.ID > ?  AND t1.ORG_ID= ? ORDER BY t1.ID ASC) tmp WHERE ROWNUM = 1";
		}else if(dbType.equals("mysql")){
			queryStr="SELECT t1.ID,t1.TO_ID,t1.EMAIL_ID,t2.BODY_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,"
					+ "(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID ) AS FROM_NAME,"
					+ "(SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID ) AS TO_NAME "
					+ " ,t2.IMPORTANT, t2.ATTACHMENT_ID,t2.COPY_TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  t1.TO_ID = ? AND t1.BOX_ID = ? AND t1.ID > ?  AND t1.ORG_ID= ? ORDER BY t1.ID ASC LIMIT 1";
		}
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, boxId);
		ps.setInt(3, id);
		ps.setString(4, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("fromId", rs.getString("FROM_ID"));
			json.accumulate("fromName", rs.getString("FROM_NAME"));
			json.accumulate("subject", rs.getString("SUBJECT"));
			json.accumulate("content", rs.getString("CONTENT"));
			String time = rs.getString("SEND_TIME");
			Calendar c=Calendar.getInstance();
			c.set(Calendar.YEAR,Integer.parseInt( time.substring(0,4)));  
		    c.set(Calendar.MONTH, Integer.parseInt(time.substring(5,7)) - 1);  
		    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(8,10)));
			int i = c.get(Calendar.DAY_OF_WEEK);
			String week = "";
			if(i == 1){
				week = "星期天";
			}else if(i == 2){
				week = "星期一";
			}else if(i == 3){
				week = "星期二";
			}else if(i == 4){
				week = "星期三";
			}else if(i == 5){
				week = "星期四";
			}else if(i == 6){
				week = "星期五";
			}else if(i == 7){
				week = "星期六";
			}
			time = time.substring(0,4)+"年"+time.substring(5,7)+"月"+time.substring(8,10)+"日"+"("+week+") "+time.substring(11,16);
			json.accumulate("sendTime", time);
			String toName = "";
			if(boxId.equals("3")||boxId.equals("2")){
				toName = getAllToName2(conn, rs.getString("BODY_ID"),rs.getString("TO_ID"));
			}else{
				toName = getAllToName(conn, rs.getString("BODY_ID"),boxId);
			}
			json.accumulate("toName", toName);
			json.accumulate("emailId", rs.getString("EMAIL_ID"));
			json.accumulate("bodyId", rs.getString("BODY_ID"));
			String toId = getAllToId(conn, rs.getString("BODY_ID"),boxId);
			json.accumulate("toId", toId);
			json.accumulate("important", rs.getString("IMPORTANT"));
			json.accumulate("attachId", rs.getString("ATTACHMENT_ID"));
			String copyToId = rs.getString("COPY_TO_ID");
			if(copyToId==null){
				copyToId = "";
			}
			json.accumulate("copyToId", copyToId);
			String copyToName = getAllToName1(conn, rs.getString("BODY_ID"),copyToId);
			json.accumulate("copyToName",copyToName );
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 得到全部收件人
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getAllToName(Connection conn,String bodyId,String boxId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr = "SELECT DISTINCT (SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME,t1.EMAIL_ID,t1.READ_FLAG,t1.TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  t1.BODY_ID = ? AND t1.BOX_ID = ? AND FIND_IN_SET(t1.TO_ID,t2.TO_ID) ";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT DISTINCT (SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME,t1.EMAIL_ID,t1.READ_FLAG,t1.TO_ID FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  t1.BODY_ID = ? AND t1.BOX_ID = ? AND INSTR(CONCAT(',',t2.TO_ID)||',',t1.TO_ID) > 0 ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, bodyId);
		ps.setString(2, boxId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			String toName = rs.getString("TO_NAME");
			if(toName != null){
				json.accumulate("name", rs.getString("TO_NAME"));
				if(rs.getString("READ_FLAG").equals("2")){
					json.accumulate("readFlag", "true");
				}else{
					json.accumulate("readFlag", "false");
				}
				json.accumulate("userId", rs.getString("TO_ID"));
				jsonArr.add(json);
			}
		}
		//str = str.substring(0,str.length()-1);
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 得到全部收件人
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getAllToName2(Connection conn,String bodyId,String toId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String [] ids = toId.split(",");
		for (int i = 0; i < ids.length; i++) {
			JSONObject json = new JSONObject();
			
			AccountLogic acclogic=new AccountLogic();
			String UserName = acclogic.getUserNameStr(conn, ids[i]);
			json.accumulate("userId", ids[i]);
			json.accumulate("name", UserName);
			
			
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
	/**
	 * 得到全部收件人
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getAllToName1(Connection conn,String bodyId,String toId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String [] ids = toId.split(",");
		for (int i = 0; i < ids.length; i++) {
			JSONObject json = new JSONObject();
			String sql = "SELECT DISTINCT (SELECT t4.USER_NAME FROM USER_INFO t4 WHERE t4.ACCOUNT_ID = t1.TO_ID AND t4.ORG_ID = t1.ORG_ID ) AS TO_NAME,t1.TO_ID,t1.EMAIL_ID,t1.READ_FLAG FROM email t1 LEFT JOIN email_body t2 ON t1.BODY_ID = t2.BODY_ID WHERE  t1.BODY_ID = ?  AND t1.TO_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bodyId);
			ps.setString(2, ids[i]);
			rs = ps.executeQuery();
			if(rs.next()){
				String toName = rs.getString("TO_NAME");
				if(toName != null){
					json.accumulate("name", rs.getString("TO_NAME"));
					json.accumulate("userId", rs.getString("TO_ID"));
					if(rs.getString("READ_FLAG").equals("2")){
						json.accumulate("readFlag", "true");
					}else{
						json.accumulate("readFlag", "false");
					}
					
				}
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据邮件Id判断是否已读
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public boolean getReadFlag(Connection conn,String emailId)throws Exception{
		boolean flag = false;
		String sql = "SELECT READ_FLAG FROM EMAIL WHERE EMAIL_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if(rs.getString("READ_FLAG").equals("2")){
				flag = true;
			}else{
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 判断这封邮件是否有抄送
	 * Time ： 2015-3-31
	 * Author : Yzz
	 * @param conn
	 * @param accountId
	 * @param bodyId
	 * @return
	 * @throws Exception
	 */
	public boolean CheckCopyMail(Connection conn,String accountId,String bodyId)throws Exception{
		boolean flag = false;
		String sql = "SELECT EMAIL_ID FROM email WHERE TO_ID = ? AND BODY_ID = ? AND BOX_ID = '1' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, bodyId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
	/**
	 * 得到全部收件人Id
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getAllToId(Connection conn,String bodyId,String boxId)throws Exception{
		String str = "";
		String sql = "SELECT DISTINCT t1.TO_ID FROM EMAIL t1 WHERE t1.BODY_ID = ? AND t1.BOX_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bodyId);
		ps.setString(2, boxId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			str += rs.getString("TO_ID")+",";
		}
		if(str.length()>0){
			str = str.substring(0,str.length()-1);
		}
		rs.close();
		ps.close();
		return str;
	}
	
	/**
	 * 得到所有的邮箱文件夹
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getEmailBoxList(Connection conn,String accountId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		String sql = "SELECT BOX_ID,BOX_PID,BOX_NAME FROM EMAIL_BOX WHERE ACCOUNT_ID = ? AND ORG_ID = ? ORDER BY SORT_ID ASC";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("boxId", rs.getString("BOX_ID"));
			json.accumulate("boxPid", rs.getString("BOX_PID"));
			json.accumulate("boxName", rs.getString("BOX_NAME"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 添加邮箱文件夹
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailBox
	 * @return
	 * @throws Exception
	 */
	public int addEmailBox(Connection conn,EmailBox emailBox)throws Exception{
		String sql = "INSERT INTO EMAIL_BOX(BOX_ID,SORT_ID,BOX_PID,BOX_NAME,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailBox.getBoxId());
		ps.setString(2, emailBox.getSortId());
		ps.setString(3, emailBox.getBoxPid());
		ps.setString(4, emailBox.getBoxName());
		ps.setString(5, emailBox.getAccountId());
		ps.setString(6, emailBox.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据Id查询邮箱文件夹
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param boxId
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getEmailBoxById(Connection conn,String boxId,String accountId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs = null;
		String sql = "SELECT BOX_ID,SORT_ID,BOX_NAME FROM EMAIL_BOX WHERE BOX_ID = ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, boxId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("boxId", rs.getString("BOX_ID"));
			json.accumulate("sortId", rs.getString("SORT_ID"));
			json.accumulate("boxName", rs.getString("BOX_NAME"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 修改邮箱文件夹
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailBox
	 * @return
	 * @throws Exception
	 */
	public int updateEmailBox(Connection conn,EmailBox emailBox)throws Exception{
		String sql = "UPDATE EMAIL_BOX SET SORT_ID = ?, BOX_PID = ?, BOX_NAME = ? WHERE BOX_ID = ?  AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailBox.getSortId());
		ps.setString(2, emailBox.getBoxPid());
		ps.setString(3, emailBox.getBoxName());
		ps.setString(4, emailBox.getBoxId());
		ps.setString(5, emailBox.getAccountId());
		ps.setString(6, emailBox.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 删除邮箱文件夹
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param boxId
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int deleteEmailBox(Connection conn,String boxId,String accountId,String orgId)throws Exception{
		String sql = "DELETE FROM EMAIL_BOX WHERE BOX_ID = ?  AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, boxId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 检查邮箱文件夹下是否有邮箱
	 * Author:Yzz
	 * Time:2015-6-2
	 * @param conn
	 * @param boxId
	 * @return
	 * @throws Exception
	 */
	public int checkEmailBox(Connection conn,String boxId)throws Exception{
		int returnData = 0;
		String sql = "SELECT EMAIL_ID FROM EMAIL WHERE BOX_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, boxId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = 1;
		}
		rs.close();
		ps.close();
		return returnData;
	}
	/**
	 * 根据id得到用户邮箱配置
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getEmailConfigById(Connection conn,String accountId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs = null;
		String sql = "SELECT CONFIG_ID,EMAIL_SERVER,SERVER_PORT,EMAIL_USER,EMAIL_PWD FROM EMAIL_CONFIG WHERE ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("configId", rs.getString("CONFIG_ID"));
			json.accumulate("emailServer", rs.getString("EMAIL_SERVER"));
			json.accumulate("serverPort", rs.getString("SERVER_PORT"));
			json.accumulate("emailUser", rs.getString("EMAIL_USER"));
			json.accumulate("emailPwd", rs.getString("EMAIL_PWD"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 根据Id得到邮箱配置(返回EmailConfig对象)
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public EmailConfig getEmailConfigById2(Connection conn,String accountId,String orgId)throws Exception{
		EmailConfig config = new EmailConfig();
		ResultSet rs = null;
		String sql = "SELECT CONFIG_ID,EMAIL_SERVER,SERVER_PORT,EMAIL_USER,EMAIL_PWD FROM EMAIL_CONFIG WHERE ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		if(rs.next()){
			config.setConfigId(rs.getString("CONFIG_ID"));
			config.setEmailServer(rs.getString("EMAIL_SERVER"));
			config.setServerPort(rs.getString("SERVER_PORT"));
			config.setEmailUser(rs.getString("EMAIL_USER"));
			config.setEmailPwd(rs.getString("EMAIL_PWD"));
			config.setAccountId(accountId);
			config.setOrgId(orgId);
		}
		rs.close();
		ps.close();
		return config;
	}
	
	/**
	 * 修改用户邮箱配置
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailConfig
	 * @return
	 * @throws Exception
	 */
	public int updateEmailConfig(Connection conn,EmailConfig emailConfig)throws Exception{
		String sql = "UPDATE EMAIL_CONFIG SET EMAIL_SERVER = ?, SERVER_PORT = ?, EMAIL_USER = ?, EMAIL_PWD = ? WHERE CONFIG_ID = ? AND  ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailConfig.getEmailServer());
		ps.setString(2, emailConfig.getServerPort());
		ps.setString(3, emailConfig.getEmailUser());
		ps.setString(4, emailConfig.getEmailPwd());
		ps.setString(5, emailConfig.getConfigId());
		ps.setString(6, emailConfig.getAccountId());
		ps.setString(7, emailConfig.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 添加用户邮箱配置
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param conn
	 * @param emailConfig
	 * @return
	 * @throws Exception
	 */
	public int addEmailConfig(Connection conn,EmailConfig emailConfig)throws Exception{
		String sql = "INSERT INTO  EMAIL_CONFIG(CONFIG_ID,EMAIL_SERVER,SERVER_PORT,EMAIL_USER,EMAIL_PWD,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emailConfig.getConfigId());
		ps.setString(2, emailConfig.getEmailServer());
		ps.setString(3, emailConfig.getServerPort());
		ps.setString(4, emailConfig.getEmailUser());
		ps.setString(5, emailConfig.getEmailPwd());
		ps.setString(6, emailConfig.getAccountId());
		ps.setString(7, emailConfig.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 得到未读邮件
	 * Author:Yzz
	 * Time:2015-5-22
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getNoReadEmail(Connection conn,String accountId,String orgId)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		JSONArray jsonArr = new JSONArray();
		String queryStr="";
		if(dbType.equals("sqlserver"))
		{
			queryStr = "SELECT * FROM (SELECT   ROW_NUMBER() OVER(ORDER BY SEND_TIME DESC) AS RB,t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID) AS USER_NAME "
					 + ",t1.READ_FLAG,t1.BOX_ID FROM EMAIL t1,EMAIL_BODY t2 WHERE t1.BODY_ID = t2.BODY_ID "
					 + " AND t1.ORG_ID = ? AND t1.TO_ID = ? AND t1.BOX_ID = '1' AND t1.READ_FLAG = '1') TMP WHERE  RB BETWEEN 0 AND 3 ";
		}else if(dbType.equals("mysql"))
		{
		queryStr = "SELECT t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID) AS USER_NAME "
				 + ",t1.READ_FLAG,t1.BOX_ID FROM EMAIL t1,EMAIL_BODY t2 WHERE t1.BODY_ID = t2.BODY_ID "
				 + " AND t1.ORG_ID = ? AND t1.TO_ID = ? AND t1.BOX_ID = '1' AND t1.READ_FLAG = '1' ORDER BY SEND_TIME DESC LIMIT 3 ";
		}else if(dbType.equals("oracle"))
		{
		queryStr = "SELECT * FROM (SELECT t1.EMAIL_ID,t2.FROM_ID,t2.SUBJECT,t2.CONTENT,t2.SEND_TIME,(SELECT t3.USER_NAME FROM USER_INFO t3 WHERE t3.ACCOUNT_ID = t2.FROM_ID AND t3.ORG_ID = t2.ORG_ID) AS USER_NAME "
				 + ",t1.READ_FLAG,t1.BOX_ID FROM EMAIL t1,EMAIL_BODY t2 WHERE t1.BODY_ID = t2.BODY_ID "
				 + " AND t1.ORG_ID = ? AND t1.TO_ID = ? AND t1.BOX_ID = '1' AND t1.READ_FLAG = '1' ORDER BY SEND_TIME DESC) TMP WHERE ROWNUM<=3 ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("emailId", rs.getString("EMAIL_ID"));
			json.accumulate("subject", rs.getString("SUBJECT"));
			String allTime = rs.getString("SEND_TIME");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date time1 = formatter.parse(allTime);
			Date nowTime = new Date();
			long temp = nowTime.getTime() - time1.getTime();
			String time = "";
			if(temp < 60000){
				long sounds = temp / 1000;
				time = sounds+"秒前";
			}else if(temp >= 60000 && temp < 3600000){
				long mins = temp / 60 / 1000;
				time = mins+"分钟前";
			}else if(temp >= 3600000 && temp < 86400000){
				long hours = temp / 3600 / 1000;
				time = hours+"小时前";
			}else if(temp >= 86400000 && temp < 172800000){
				time = "昨天";
			}else if(temp >= 172800000){
				time = allTime.substring(0,10);
			}
			json.accumulate("sendTime", time);
			json.accumulate("fromName", rs.getString("USER_NAME"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
//	/**
//	 * 添加到消息中心
//	 * Author:Yzz
//	 * Time:2015-5-27
//	 * @param conn
//	 * @param email
//	 * @throws Exception
//	 */
//	public void addSms(Connection conn,Sms sms)throws Exception{
//		String sql = "INSERT INTO SMS(SMS_ID,SMS_FROM,SMS_TO,SMS_SEND_TIME,SMS_CONTECT,SMS_FLAG,SMS_TYPE,SMS_ATTACH_ID,SMS_STATUS) VALUES(?,?,?,sysdate(),?,1,?,?,1)";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setString(1, GuId.getGuid());
//		ps.setString(2, sms.getSmsFrom());
//		ps.setString(3, sms.getSmsTo());
//		ps.setString(4, sms.getSmsContent());
//		ps.setString(5, sms.getSmsType());
//		ps.setString(6, sms.getSmsAttachId());
//		ps.executeUpdate();
//		ps.close();
//		ps.close();
//	}
	
	/**
	 * 根据Id得到收件人
	 * @param conn
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public String getToIdByEmailId(Connection conn,String emailId)throws Exception{
		String returnData = "";
		String queryStr = "SELECT TO_ID FROM EMAIL WHERE EMAIL_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, emailId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("TO_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
}
