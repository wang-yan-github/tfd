package tfd.system.im.userinfo.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class UserInfoLogic {
	public BaseDao dao=new BaseDaoImpl();
	public JSONObject getUserInfo(Connection conn, String accountId)throws Exception {
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			String sql = "SELECT U.ACCOUNT_ID,U.USER_NAME,U.SEX ,"
					+ "				U.HEAD_IMG,I.KEY ,I.MID,I.DEFINED,D.DEPT_NAME, U.SIGN"
					+ "			FROM USER_INFO U LEFT JOIN DEPARTMENT D ON U.DEPT_ID=D.DEPT_ID"
					+ "				  LEFT JOIN IM_USER I ON U.ACCOUNT_ID=I.ACCOUNT_ID"
					+ "			WHERE  U.ACCOUNT_ID=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			if (rs.next()) {
				JSONObject userInfo = new JSONObject();
				userInfo.accumulate("accountId", rs.getString("ACCOUNT_ID")==null?"":rs.getString("ACCOUNT_ID"));
				userInfo.accumulate("userName", rs.getString("USER_NAME")==null?"":rs.getString("USER_NAME"));
				userInfo.accumulate("sex", rs.getString("SEX")==null?"":rs.getString("SEX"));
				userInfo.accumulate("headImg", rs.getString("HEAD_IMG")==null?"":"/attachment/userinfo/"+rs.getString("HEAD_IMG"));
				userInfo.accumulate("key", rs.getString("KEY")==null?"":rs.getString("KEY"));
				userInfo.accumulate("mid", rs.getString("MID")==null?"":rs.getString("MID"));
				userInfo.accumulate("defined", rs.getString("DEFINED")==null?"":rs.getString("DEFINED"));
				userInfo.accumulate("deptName", rs.getString("DEPT_NAME")==null?"":rs.getString("DEPT_NAME"));
				userInfo.accumulate("sign", rs.getString("SIGN")==null?"":rs.getString("SIGN"));
				return userInfo;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		
		return null;
	}
	
	
	public String getUserInfoLogic(Connection conn,Account account) throws SQLException 
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT U.USER_NAME AS USER_NAME, U.SIGN AS SIGN , D.DEPT_NAME AS DEPT_NAME,U.HEAD_IMG AS HEAD_IMG FROM USER_INFO U,DEPARTMENT D WHERE  U.ACCOUNT_ID=? AND U.DEPT_ID=D.DEPT_ID AND U.ORG_ID=?";
		PreparedStatement  ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			if(SysTool.isNullorEmpty(rs.getString("USER_NAME")))
			{
				json.accumulate("USER_NAME", "");	
			}else
			{
				json.accumulate("USER_NAME", rs.getString("USER_NAME"));	
			}
			if(SysTool.isNullorEmpty(rs.getString("DEPT_NAME")))
			{
				json.accumulate("DEPT_NAME", "");
			}else
			{
				json.accumulate("DEPT_NAME", rs.getString("DEPT_NAME"));
			}
			if(SysTool.isNullorEmpty(rs.getString("SIGN")))
			{
				json.accumulate("SIGN", "暂无个性签名！");
			}else
			{
				json.accumulate("SIGN", rs.getString("SIGN"));
			} 
			if(SysTool.isNullorEmpty(rs.getString("HEAD_IMG")))
			{
				json.accumulate("HEAD_IMG", "default.jpg");
			}else
			{
				json.accumulate("HEAD_IMG", rs.getString("HEAD_IMG"));
			}
		}
		return json.toString();
	}
	public static void main(String[] args) {
		JSONObject o=new JSONObject();
		o.put("a", "");
		System.out.println(o.toString());
	}
	
}
