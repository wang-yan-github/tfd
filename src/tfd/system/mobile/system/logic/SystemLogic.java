package tfd.system.mobile.system.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;

import com.system.db.impl.BaseDaoImpl;
import com.system.tool.RandomUtil;
import com.system.tool.SysTool;

import net.sf.json.JSONObject;

public class SystemLogic {
	
	/**
	 * 登陆验证
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean queryAccount(Connection conn, String accountId,String password) throws Exception{
		//setLastLoginTime(conn,accountId);
	    String querStr="SELECT t1.ACCOUNT_ID,t2.MOBILE_NO,t1.PASS_WORD FROM ACCOUNT t1,USER_INFO t2 WHERE t1.ACCOUNT_ID = t2.ACCOUNT_ID AND t1.ACCOUNT_ID = ? AND t1.NOT_LOGIN='0'";
	    ResultSet rs = null;
	    boolean flag= false;
		PreparedStatement ps=conn.prepareStatement(querStr);
		ps.setString(1, accountId);
		rs=ps.executeQuery();
		if(rs.next()){
			if(rs.getString("PASS_WORD").equals(password)){
				//账号和密码验证成功
				flag = true;
			}else{
				if(this.getCode(conn, accountId).equals(password)){
					//账号和验证码验证成功
					flag = true;
				}else if(this.getCode(conn, rs.getString("MOBILE_NO")).equals(password)){
					//手机号和验证码验证成功
					flag = true;
				}else if(rs.getString("MOBILE_NO").equals(password)){
					//手机号和密码验证成功
					flag = true;
				}
			}
		}
		rs.close();
		ps.close();
		return flag;
	 }
	
	/**
	 * 获取用户信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserInfoLogic(Connection conn, String userId,HttpServletRequest request)throws Exception {
		JSONObject returnjson = new JSONObject();
		JSONObject json = new JSONObject();
		PreparedStatement ps = null;
		String queryStr = "SELECT A.ID AS ID,USER_ID,ACCOUNT_ID,LEAD_LEAVE,USER_NAME,SEX,A.DEPT_ID AS DEPT_ID,LEAD_ID,POST_PRIV,C.USER_PRIV_NAME AS USER_PRIV_NAME,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,"
				+ "BIRTHDAY,SIGN,E_MAILE,WORK_ID,A.ORG_ID AS ORG_ID,HEAD_IMG,MANAGE_DEPT,OTHER_DEPT,B.DEPT_LONG_NAME AS DEPT_LONG_NAME,B.DEPT_NAME AS DEPT_NAME  "
				+ "FROM USER_INFO A,DEPARTMENT B,USER_PRIV C "
				+ "WHERE ACCOUNT_ID=? AND A.DEPT_ID=B.DEPT_ID AND C.USER_PRIV_ID=A.POST_PRIV";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			//json.accumulate("id", rs.getString("ID"));
			json.accumulate("user_id", rs.getString("ACCOUNT_ID"));
			if(rs.getString("HEAD_IMG")==null){
				json.accumulate("avatar_url", "");
			}else{
				if(rs.getString("HEAD_IMG").equals("")){
					json.accumulate("avatar_url", "");
				}else{
					json.accumulate("avatar_url", request.getContextPath()+"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
				}
			}
			json.accumulate("name", rs.getString("USER_NAME"));
			json.accumulate("companyId", rs.getString("ORG_ID"));
			json.accumulate("company", this.getUnitByOrgId(conn, rs.getString("ORG_ID")));
			json.accumulate("department", rs.getString("DEPT_NAME"));
			json.accumulate("deptId", rs.getString("DEPT_ID"));
			json.accumulate("role", rs.getString("USER_PRIV_NAME"));
			String sign = rs.getString("SIGN");
			sign=sign==null?"":sign;
			json.accumulate("sign", sign);
			String homeTel = rs.getString("HOME_TEL");
			homeTel=homeTel==null?"":homeTel;
			json.accumulate("work_phone", homeTel);
			String mobileNo = rs.getString("MOBILE_NO");
			mobileNo=mobileNo==null?"":mobileNo;
			json.accumulate("phone", mobileNo);
			String email = rs.getString("E_MAILE");
			email=email==null?"":email;
			json.accumulate("email", email);
			String qq = rs.getString("QQ");
			qq=qq==null?"":qq;
			json.accumulate("qq", qq);
			String birthday = rs.getString("BIRTHDAY");
			birthday=birthday==null?"":birthday;
			json.accumulate("birthday", birthday);
			JSONObject host = this.getHostByOrgId(conn,rs.getString("ORG_ID"));
			json.accumulate("host",host.getString("chatHost"));
		}
		returnjson.accumulate("infomation", json);
		returnjson.accumulate("access_token", request.getSession().getId());
		returnjson.accumulate("time", new Date().getTime()/1000);
		//returnjson.accumulate("sport",host.getString("chatPort"));
		//returnjson.accumulate("hport",host.getString("listenner"));
		rs.close();
		ps.close();
		return returnjson.toString();
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
		String sql = "SELECT ORG_NAME FROM ORG_CONFIG WHERE ORG_ID = ?";
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
	
	/**
	 * 根据OrgId得到聊天服务器地址
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getHostByOrgId(Connection conn,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT CHAT_HOST,LISTENNER_PORT,CHAT_PORT FROM SYS_CONFIG WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("chatHost", rs.getString("CHAT_HOST"));
			json.accumulate("listenner", rs.getString("LISTENNER_PORT"));
			json.accumulate("chatPort", rs.getString("CHAT_PORT"));
		}
		rs.close();
		ps.close();
		return json;
	}
	
	/**
	 * 添加验证码到用户
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @return 验证码
	 * @throws Exception
	 */
	public String getCodeLogic(Connection conn,String accountId)throws Exception{
		PreparedStatement ps=null;
		try {
			JSONObject codeJson = new JSONObject();
			codeJson.accumulate("time", new Date().getTime()/1000);
			String code=RandomUtil.getRandomNum();
			codeJson.accumulate("code",code );
			
			String sql = "UPDATE USER_INFO SET CODE=? WHERE ACCOUNT_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, codeJson.toString());
			ps.setString(2, accountId);
			int result=ps.executeUpdate();
			if (result>0) {
				return code;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			new BaseDaoImpl().close(null, ps, null);
		}
		return null;
	}
	
	/**
	 * 获取用户验证码
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public String getCode(Connection conn,String phone)throws Exception{
		String returnData = "";
		String sql = "SELECT CODE FROM USER_INFO WHERE ACCOUNT_ID = ? OR MOBILE_NO = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, phone);
		ps.setString(2, phone);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			String code = rs.getString("CODE");
			if(!SysTool.isNullorEmpty(code)){
				JSONObject jsonCode = JSONObject.fromObject(code);
				String codeTime = jsonCode.getString("time");
				if(new Date().getTime()/1000-Long.parseLong(codeTime)<1800){
					returnData = jsonCode.getString("code");
				}
			}
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	/**
	 * 修改密码
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param conn
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String retrieveLogic(Connection conn,String phone,String code,String password)throws Exception{
		JSONObject returnJson = new JSONObject();
		String UserCode = this.getCode(conn, phone);
		if(code.equals(UserCode)){
			String sql = "UPDATE ACCOUNT SET PASS_WORD = ? WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, phone);
			ps.executeUpdate();
			ps.close();
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "找回密码成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "找回密码失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);

		return returnJson.toString();
	}
	
	public String checkupdateLogic(Connection conn,String phone,String token,String version,String platfrom)throws Exception{
		JSONObject returnJson = new JSONObject();
		String sql = "SELECT CLIENT_NO FROM CLIENT WHERE CLIENT_TYPE = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, platfrom);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "请求更新成功");
		    JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
		    json.accumulate("version", rs.getString("CLIENT_NO"));
		    returnJson.accumulate("data", json);
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "请求更新失败");
		    JSONObject json = new JSONObject();
		    json.accumulate("time", new Date().getTime()/1000);
		    json.accumulate("version", "");
		    returnJson.accumulate("data", json);
		}
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	
	public String logoutLogic(Connection conn,HttpServletRequest request,String phone,String token,String version,String platfrom)throws Exception{
		JSONObject returnJson = new JSONObject();
		HttpSession session = request.getSession(false);
		if(session != null){
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "注销成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "注销失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
	    returnJson.accumulate("data", json);
		
		return returnJson.toString();
	}
	
	public String changepasswordLogic(Connection conn,String phone,String token,String version,String platfrom,String password,String old_password)throws Exception{
		JSONObject returnJson = new JSONObject();
		String querStr="SELECT t1.ACCOUNT_ID FROM ACCOUNT t1 WHERE t1.ACCOUNT_ID = ? AND PASS_WORD = ? ";
		PreparedStatement ps = conn.prepareStatement(querStr);
		ps.setString(1, phone);
		ps.setString(2, old_password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){ 
			String sql = "UPDATE ACCOUNT SET PASS_WORD = ? WHERE ACCOUNT_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, phone);
			ps.executeUpdate();
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "修改密码成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "修改密码失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	
	
	public String personalupdate(Connection conn,String phone,String token,String version,String platfrom,UserInfo info)throws Exception{
		JSONObject returnJson = new JSONObject();
		String queryStr="UPDATE USER_INFO SET";
		if(info.getHomeTel()!=null){
			queryStr += " HOME_TEL = '"+info.getHomeTel()+"',";
		}
		if(info.getUserName()!=null){
			queryStr += " USER_NAME = '"+info.getUserName()+"',";
		}
		if(info.getSign()!=null){
			queryStr += " SIGN = '"+info.getSign()+"',";
		}
		if(info.getMobileNo()!=null){
			queryStr += " MOBILE_NO = '"+info.getMobileNo()+"',";
		}
		if(info.geteMail()!=null){
			queryStr += " E_MAILE = '"+info.geteMail()+"',";
		}
		if(info.getQq()!=null){
			queryStr += " QQ = '"+info.getQq()+"',";
		}
		if(info.getBirthday()!=null){
			queryStr += " BIRTHDAY = '"+info.getBirthday()+"',";
		}
		if(info.getHeadImg()!=null){
			queryStr += " HEAD_IMG = '"+info.getHeadImg()+"',";
		}
		queryStr = queryStr.substring(0,queryStr.length()-1);
		queryStr += " WHERE ACCOUNT_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, phone);
		int i = ps.executeUpdate();
		if(i>0){ 
			returnJson.accumulate("status_code", "200");
		    returnJson.accumulate("msg", "请求更新成功");
		}else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "请求更新失败");
		}
		JSONObject json = new JSONObject();
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	public String aboutLogic(Connection conn,Account account,String token,String version,String platfrom)throws Exception{
		JSONObject returnJson = new JSONObject();
		String querStr="SELECT DOWN_URL,SERVICE_PHONE,WEB_SITE,COPYRIGHT FROM SYS_ABOUT WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(querStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		JSONObject json = new JSONObject();
		returnJson.accumulate("status_code", "200");
	    returnJson.accumulate("msg", "请求关于成功");
		if(rs.next()){ 
		    json.accumulate("download_url", rs.getString("DOWN_URL"));
		    json.accumulate("service_phone", rs.getString("SERVICE_PHONE"));
		    json.accumulate("website", rs.getString("WEB_SITE"));
		    json.accumulate("copyright", rs.getString("COPYRIGHT"));
		}else{
			json.accumulate("download_url", "");
		    json.accumulate("service_phone", "");
		    json.accumulate("website", "");
		    json.accumulate("copyright", "");
		}
	    json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	/**
	 * 登陆成功后获取用户的菜单权限
	 * Time:2015-10-08
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String getUserPowerLogic(Connection conn,Account account,String token,String version,String platfrom)throws Exception{
		JSONObject returnJson = new JSONObject();
		String queryStr="SELECT APP_ICON FROM ACCOUNT WHERE ACCOUNT_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		String json = "";
		returnJson.accumulate("status_code", "200");
	    returnJson.accumulate("msg", "请求列表成功");
	    if(rs.next()){
	    	if(rs.getString("APP_ICON")!=null){
	    	json=rs.getString("APP_ICON");
	    	}
	    }
	    if(json==null||json.equals("")){
	    	json="[]";
	    }
	    JSONObject data=new JSONObject();
	    data.accumulate("time", new Date().getTime()/1000);
	    data.accumulate("list", json);
	    returnJson.accumulate("data", data);
	    rs.close();
		ps.close();
		return returnJson.toString();
	}
	/**
	 * 登陆成功后获取用户的菜单权限
	 * Time:2015-10-08
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String liststateLogic(Connection conn,Account account,String list,String token,String version,String platfrom)throws Exception{
		JSONObject returnJson = new JSONObject();
		String queryStr="UPDATE ACCOUNT SET APP_ICON=? WHERE ACCOUNT_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, list);
		ps.setString(2, account.getAccountId());
		ps.setString(3, account.getOrgId());
		int i=ps.executeUpdate();
		if(i>0){
		returnJson.accumulate("status_code", "200");
	    returnJson.accumulate("msg", "请求更新列表状态成功");
		}
		else{
			returnJson.accumulate("status_code", "500");
		    returnJson.accumulate("msg", "请求更新列表状态失败");
		}
	    JSONObject data=new JSONObject();
	    data.accumulate("time", new Date().getTime()/1000);
	    returnJson.accumulate("data", data);
		ps.close();
		return returnJson.toString();
	}
}
