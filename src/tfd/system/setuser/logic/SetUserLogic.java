package tfd.system.setuser.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.system.tool.SysTool;

import tfd.system.mobile.userinfo.data.Mark;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SetUserLogic {
	
	/**
	 * 根据AccountId得到用户信息和账户信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param conn jbdc连接
	 * @param accountId 账户Id
	 * @param orgId 企业Id
	 * @return json格式的用户信息和账户信息综合体
	 * @throws SQLException
	 */
	public String getUserInfoAndAccountById(Connection conn,String accountId,String orgId) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement ps=null;
		String queryStr="SELECT t1.USER_ID,t1.ACCOUNT_ID,t1.USER_NAME,t1.HOME_ADD,t1.HOME_TEL,"
				+ "t1.MOBILE_NO,t1.QQ,t1.E_MAILE,t1.SEX,t2.BY_NAME,t1.HEAD_IMG AS HEAD_IMG FROM"
				+ " USER_INFO t1 LEFT JOIN ACCOUNT t2 ON  t1.ACCOUNT_ID = t2.ACCOUNT_ID WHERE t1.ACCOUNT_ID = ? AND t1.ORG_ID = ?";
		JSONArray jsonArr = new JSONArray();
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("sex", rs.getString("SEX"));
			json.accumulate("homeAdd", rs.getString("HOME_ADD"));
			json.accumulate("homeTel", rs.getString("HOME_TEL"));
			json.accumulate("mobileNo", rs.getString("MOBILE_NO"));
			json.accumulate("qQ", rs.getString("QQ"));
			json.accumulate("eMaile", rs.getString("E_MAILE"));
			json.accumulate("byName", rs.getString("BY_NAME"));
			json.accumulate("headImg", rs.getString("HEAD_IMG"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据AccountId得到用户基本信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getBaseUserInfoById(Connection conn,String accountId,String orgId) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement ps=null;
		String queryStr="SELECT t1.USER_ID,t1.ACCOUNT_ID,t1.USER_NAME,(SELECT t3.DEPT_NAME FROM DEPARTMENT t3 WHERE DEPT_ID = t1.DEPT_ID) AS DEPT_NAME,"
				+ "(SELECT t4.USER_PRIV_NAME FROM USER_PRIV t4 WHERE USER_PRIV_ID = t2.USER_PRIV) AS USER_PRIV_NAME,t1.MOBILE_NO,t1.QQ,t1.E_MAILE,t1.SEX FROM "
				+ "USER_INFO t1 LEFT JOIN ACCOUNT t2 ON t1.ACCOUNT_ID = t2.ACCOUNT_ID WHERE t1.ACCOUNT_ID = ? AND t1.ORG_ID = ?";
		
		JSONObject json = new JSONObject();
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("userId", rs.getString("USER_ID"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("sex", rs.getString("SEX"));
			json.accumulate("mobileNo", rs.getString("MOBILE_NO"));
			json.accumulate("qQ", rs.getString("QQ"));
			json.accumulate("eMaile", rs.getString("E_MAILE"));
			json.accumulate("deptName", rs.getString("DEPT_NAME"));
			json.accumulate("userPriv", rs.getString("USER_PRIV_NAME"));
			
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 更改用户信息和账户信息
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param u 用户信息实体
	 * @param byName 别名
	 * @return int类型的受影响的条数
	 * @throws Exception
	 */
	public int updateUserInfoAndAccount(Connection conn,UserInfo u,String byName)throws Exception{

		String sql = "UPDATE USER_INFO t1 "
				+ "SET t1.USER_NAME = ?,t1.SEX = ?,t1.HOME_ADD = ?,t1.HOME_TEL = ?,t1.MOBILE_NO = ? "
				+ ",t1.QQ = ?,t1.E_MAILE = ? ,t1.HEAD_IMG=? WHERE t1.ACCOUNT_ID = ? AND t1.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,u.getUserName() );
		ps.setString(2,u.getSex() );
		ps.setString(3,u.getHomeAdd() );
		ps.setString(4,u.getHomeTel() );
		ps.setString(5,u.getMobileNo() );
		ps.setString(6,u.getQq() );
		ps.setString(7,u.geteMail());
		ps.setString(8, u.getHeadImg());
		ps.setString(9,u.getAccountId());
		ps.setString(10, u.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		sql = "UPDATE ACCOUNT SET BY_NAME = ? WHERE ACCOUNT_ID = ? AND ORG_ID = ?  ";
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setString(1,byName );
		ps1.setString(2, u.getAccountId());
		ps1.setString(3, u.getOrgId());
		ps1.executeUpdate();
		ps.close();
		ps1.close();
		return i;
	}
	
	/**
	 * 检查密码是否正确
	 * Time : 2015-4-8
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param accountId 账户Id
	 * @param pwd 密码
	 * @return String类型的字符
	 * @throws SQLException
	 */
	public int checkPassword(Connection conn,String accountId,String password) throws SQLException
	{
		int returnData = 0;
		String queryStr="SELECT PASS_WORD FROM ACCOUNT WHERE ACCOUNT_ID=? AND PASS_WORD = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData = 1;
		}else{
			returnData = 0;
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	/**
	 * 得到最后修改时间
	 * Time : 2015-5-6
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param accountId 账户Id
	 * @return String类型的字符
	 * @throws SQLException
	 */
	public String getLastUpdateTime(Connection conn,String accountId) throws SQLException
	{
		String str = null;
		String queryStr="SELECT UPDATE_PASSWORD_TIME FROM ACCOUNT WHERE ACCOUNT_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			str = rs.getString("UPDATE_PASSWORD_TIME");
		}
		rs.close();
		ps.close();
		return str;
	}
	
	/**
	 * 更改密码
	 * Time :2015-4-8
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param accountId 账户Id
	 * @param pwd 密码
	 * @return int类型的受影响的条数
	 * @throws SQLException
	 */
	public int updatePassword(Connection conn,String accountId,String pwd) throws SQLException
	{
		String nd = SysTool.getDateTimeStr(new Date());
		String queryStr="UPDATE ACCOUNT SET PASS_WORD = ?,UPDATE_PASSWORD_TIME = ?  WHERE ACCOUNT_ID=?  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, pwd);
		ps.setString(2, nd);
		ps.setString(3, accountId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	/**
	 * 获取密码
	 * Time:2015-12-02
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public String getPassword(Connection conn, String accountId)throws Exception{
		String returnData = "";
		String sql = "SELECT PASS_WORD FROM ACCOUNT WHERE ACCOUNT_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("PASS_WORD");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	public String getPersonalById(Connection conn,String userId)throws Exception{
		JSONObject json = new JSONObject();
		PreparedStatement ps = null;
		String queryStr = "SELECT A.ID AS ID,USER_ID,ACCOUNT_ID,LEAD_LEAVE,USER_NAME,SEX,A.DEPT_ID,LEAD_ID,POST_PRIV,C.USER_PRIV_NAME AS USER_PRIV_NAME,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,"
				+ "BIRTHDAY,SIGN,E_MAILE,WORK_ID,A.ORG_ID AS ORG_ID,A.LEAD_ID,HEAD_IMG,MANAGE_DEPT,OTHER_DEPT,B.DEPT_LONG_NAME AS DEPT_LONG_NAME,B.DEPT_NAME AS DEPT_NAME  "
				+ "FROM USER_INFO A,DEPARTMENT B,USER_PRIV C "
				+ "WHERE ACCOUNT_ID=? AND A.DEPT_ID=B.DEPT_ID AND C.USER_PRIV_ID=A.POST_PRIV";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			json.accumulate("user_id", rs.getString("ACCOUNT_ID"));
			json.accumulate("sex", rs.getString("SEX"));
			json.accumulate("headImg", rs.getString("HEAD_IMG"));
			json.accumulate("name", rs.getString("USER_NAME"));
			json.accumulate("department", rs.getString("DEPT_NAME"));
			json.accumulate("role", rs.getString("USER_PRIV_NAME"));
			json.accumulate("company", this.getUnitByOrgId(conn, rs.getString("ORG_ID")));
			String sign = rs.getString("SIGN");
			sign=sign==null?"-":sign;
			json.accumulate("sign", sign);
			String homeTel = rs.getString("HOME_TEL");
			homeTel=homeTel==null?"-":homeTel;
			json.accumulate("work_phone", homeTel);
			String homeAdd = rs.getString("HOME_ADD");
			homeAdd=homeAdd==null?"-":homeAdd;
			json.accumulate("homeAdd", homeAdd);
			String mobileNo = rs.getString("MOBILE_NO");
			mobileNo=mobileNo==null?"-":mobileNo;
			json.accumulate("phone", mobileNo);
			String email = rs.getString("E_MAILE");
			email=email==null?"-":email;
			json.accumulate("email", email);
			String qq = rs.getString("QQ");
			qq=qq==null?"-":qq;
			json.accumulate("qq", qq);
			String birthday = rs.getString("BIRTHDAY");
			birthday=birthday==null?"-":birthday;
			json.accumulate("birthday", birthday);
			String leadId = rs.getString("LEAD_ID");
			String leadName = new AccountLogic().getUserNameStr(conn, leadId);
			json.accumulate("lead", leadName);
		}
		return json.toString();
	}
	
	/**
	 * 根据OrgId得到企业信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getUnitByOrgId(Connection conn,String orgId)throws Exception{
		String returnData = "";
		String sql = "SELECT ORG_NAME FROM UNIT WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("ORG_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}

	public String getMarksById(Connection conn, String accountId, Account account)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT MARK_ID,CONTENT,GOOD FROM MARK WHERE ACCOUNT_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("mark_id", rs.getString("MARK_ID"));
			json.accumulate("content", rs.getString("CONTENT"));
			String good = rs.getString("GOOD");
			String flag = "0";
			String num = "0";
			if(good!=null){
				if(!good.equals("")){
					if(good.indexOf(",")>-1){
						String[] goods = good.split(",");
						num = goods.length+"";
						for (int i = 0; i < goods.length; i++) {
							if(goods[i].equals(account.getAccountId())){
								flag = "1";
							}
						}
					}else{
						if(good.equals(account.getAccountId())){
							flag = "1";
						}
					}
				}
			}
			json.accumulate("likes", num);
			json.accumulate("islike", flag);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 标签点赞
	 * Time:2016-12-18
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int praisemark(Connection conn, Account account, String id,String islike)throws Exception{
		String sql = "SELECT GOOD,ACCOUNT_ID FROM MARK WHERE MARK_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		String good = "";
		if(rs.next()){
			good = rs.getString("GOOD");
			if(good == null){
				good = "";
			}
			if(rs.getString("ACCOUNT_ID").equals(account.getAccountId())){
				return -1;
			}
		}
		if(islike.equals("1")){
			good = good + account.getAccountId() + ",";
		}else{
			good = good.replaceAll(account.getAccountId()+",", "");
		}
		sql =  "UPDATE MARK SET GOOD = ? WHERE MARK_ID = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, good);
		ps.setString(2, id);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 创建标签
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	public int createMark(Connection conn, Account account, Mark mark)throws Exception{
		if(mark.getAccountId().equals(mark.getCreateUser())){
			return -1;
		}
		String sql = "INSERT INTO MARK(MARK_ID,CONTENT,CREATE_USER,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, mark.getMarkId());
		ps.setString(2, mark.getContent());
		ps.setString(3, mark.getCreateUser());
		ps.setString(4, mark.getAccountId());
		ps.setString(5, mark.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
}
