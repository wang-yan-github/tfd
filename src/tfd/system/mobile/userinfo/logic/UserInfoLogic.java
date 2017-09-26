package tfd.system.mobile.userinfo.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.mobile.userinfo.data.Mark;
import tfd.system.mobile.userinfo.data.Target;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

public class UserInfoLogic {
	public BaseDao dao=new BaseDaoImpl();
	/**
	 * 获取公司全部人员
	 * Time:2015-10-14
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platfrom
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String companystaffLogic(Connection conn,Account account,String token,String version,String platfrom,HttpServletRequest request)throws Exception{
		JSONObject returnJson = new JSONObject();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String queryStr="SELECT DISTINCT T1.USER_NAME AS USER_NAME,"
					+ "			T1.ACCOUNT_ID AS ACCOUNT_ID,"	
					+ "			(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
					+ "			T1.MOBILE_NO AS MOBILE_NO,"
					+ "			T1.HOME_TEL AS HOME_TEL,T1.SIGN AS SIGN,"
					+ "			T1.E_MAILE AS E_MAILE,T1.QQ AS QQ ,T1.BIRTHDAY AS BIRTHDAY,"
					+ "			T1.HEAD_IMG AS HEAD_IMG,T1.LEAD_LEAVE AS LEAD_LEAVE,"
					+ "			T4.LEAVE_NAME AS LEAD_LEAVENAME,T1.DEPT_ID AS DEPT_ID ,"
					+ "		    T3.DEPT_NAME AS DEPT_NAME"
					+ "		FROM USER_INFO T1 LEFT JOIN  ACCOUNT T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID  "
					+ "						  LEFT JOIN USER_LEAVE T4 ON T4.LEAVE_ID=T1.LEAD_LEAVE "
					+ "						  LEFT JOIN DEPARTMENT T3 ON T3.DEPT_ID =T1.DEPT_ID "
					+ "		WHERE T2.NOT_LOGIN='0' AND T1.ORG_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, account.getOrgId());
			rs = ps.executeQuery();
			
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				JSONObject json=new JSONObject();
				String sign=rs.getString("SIGN");
				sign=sign==null?"":sign;
				String birthday=rs.getString("BIRTHDAY");
				birthday=birthday==null?"":birthday;
				String headImg=rs.getString("HEAD_IMG");
				headImg=headImg==null||headImg.trim().equals("")?"":request.getContextPath()+"/attachment/userinfo/"+headImg;
				String homeTel=rs.getString("HOME_TEL");
				homeTel=homeTel==null?"":homeTel;
				String mobileNo=rs.getString("MOBILE_NO");
				mobileNo=mobileNo==null?"":mobileNo;
				String email=rs.getString("E_MAILE");
				email=email==null?"":email;
				String qq= rs.getString("QQ");
				qq=qq==null?"":qq;
				String role=rs.getString("LEAD_LEAVENAME");
				role=role==null?"":role;
				
				
				json.accumulate("name", rs.getString("USER_NAME"));
				json.accumulate("id", rs.getString("ACCOUNT_ID"));
				json.accumulate("seqId", rs.getInt("USER_INFO_SEQ_ID"));
				json.accumulate("mobile_phone", mobileNo);
				json.accumulate("work_phone",homeTel );
				json.accumulate("sign", sign);
				json.accumulate("email", email);
				json.accumulate("qq",qq);
				json.accumulate("birthday",birthday );
				json.accumulate("avatar_url", headImg);
				json.accumulate("role", role);
				json.accumulate("department", rs.getString("DEPT_NAME"));
				jsonArr.add(json);
			}
			JSONObject orgjson=this.orgIdinfo(conn, account.getOrgId());
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "请求公司成员状态成功");
			JSONObject dataJson=new JSONObject();
			dataJson.accumulate("list", jsonArr);
			returnJson.accumulate("data", dataJson);
			returnJson.accumulate("companyname", orgjson.getString("name"));
			returnJson.accumulate("companyid", orgjson.getString("id"));
			returnJson.accumulate("deptCount", orgjson.getInt("deptCount"));
			returnJson.accumulate("time", new Date().getTime()/1000);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return returnJson.toString();
	}
	/**
	 * 获取权限内的管理人员
	 * Time 2015-11-04
	 * Author Wp
	 * @param conn
	 * @param account
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String authorityStaffLogic(Connection conn,Account account,HttpServletRequest request)throws Exception{
		JSONObject returnJson=new JSONObject();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			UserInfo userInfo = new UserInfo();
			tfd.system.unit.userinfo.logic.UserInfoLogic userInfoLogic = new tfd.system.unit.userinfo.logic.UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			String manageDept = userInfo.getManageDept();
			String queryStr="";
			if(manageDept.equals("2")){
				queryStr="SELECT DISTINCT "
						+ "		T1.ACCOUNT_ID AS ACCOUNT_ID ,T1.USER_NAME AS USER_NAME,"
						+ "		(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
						+ "		T1.MOBILE_NO AS MOBILE_NO,T1.HOME_TEL AS HOME_TEL,T1.SIGN AS SIGN,"
						+ "		T1.E_MAILE AS E_MAILE,T1.QQ AS QQ ,T1.BIRTHDAY AS BIRTHDAY,"
						+ "		T1.HEAD_IMG AS HEAD_IMG,T1.LEAD_LEAVE AS LEAD_LEAVE,"
						+ "		T4.LEAVE_NAME AS LEAD_LEAVENAME,T1.DEPT_ID AS DEPT_ID , "
						+ "		T3.DEPT_NAME AS DEPT_NAME "
						+ "	FROM  USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID  "
						+ "					   LEFT JOIN DEPARTMENT T3 ON T3.DEPT_ID =T1.DEPT_ID  "
						+ "					   LEFT JOIN USER_LEAVE T4 ON T1.LEAD_LEAVE = T4.LEAVE_ID "
						+ " WHERE T1.ACCOUNT_ID = T2.ACCOUNT_ID "
						+ "       AND T2.NOT_LOGIN='0' "
						+ "		  AND (T1.DEPT_ID=? OR T1.OTHER_DEPT LIKE ?) "
						+ "       AND T1.LEAD_LEAVE=T4.LEAVE_ID "
						+ "       AND T4.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) "
						+ "		  OR T1.LEAD_ID=?";
			}else{
				queryStr="SELECT DISTINCT "
						+ "		T1.ACCOUNT_ID AS ACCOUNT_ID ,T1.USER_NAME AS USER_NAME,"
						+ "		(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
						+ "		T1.MOBILE_NO AS MOBILE_NO,T1.HOME_TEL AS HOME_TEL,"
						+ "		T1.SIGN AS SIGN,T1.E_MAILE AS E_MAILE,T1.QQ AS QQ ,"
						+ "		T1.BIRTHDAY AS BIRTHDAY,T1.HEAD_IMG AS HEAD_IMG,"
						+ "		T1.LEAD_LEAVE AS LEAD_LEAVE,T4.LEAVE_NAME AS LEAD_LEAVENAME,"
						+ "		T1.DEPT_ID AS DEPT_ID , T3.DEPT_NAME AS DEPT_NAME "
						+ " FROM  USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID  "
						+ "					   LEFT JOIN DEPARTMENT T3 ON T3.DEPT_ID =T1.DEPT_ID  "
						+ "					   LEFT JOIN USER_LEAVE T4 ON T1.LEAD_LEAVE = T4.LEAVE_ID  "
						+ " WHERE T1.ACCOUNT_ID = T2.ACCOUNT_ID "
						+ "			AND T2.NOT_LOGIN='0' "
						+ "			AND T1.LEAD_LEAVE=T4.LEAVE_ID "
						+ "			AND T4.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) "
						+ "			OR T1.LEAD_ID=?";
			}
			ps =conn.prepareStatement(queryStr);
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2")){
				ps.setString(1, userInfo.getDeptId());
				ps.setString(2, "%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(3, leaveNoId);
				ps.setString(4, userInfo.getLeadId());
			}else{
				ps.setString(1, userInfo.getLeadLeave());
				ps.setString(2, userInfo.getLeadId());
			}
			rs = ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				String sign=rs.getString("SIGN");
				sign=sign==null?"":sign;
				String birthday=rs.getString("BIRTHDAY");
				birthday=birthday==null?"":birthday;
				String headImg=rs.getString("HEAD_IMG");
				headImg=headImg==null||headImg.trim().equals("")?"":request.getContextPath()+"/attachment/userinfo/"+headImg;
				String homeTel=rs.getString("HOME_TEL");
				homeTel=homeTel==null?"":homeTel;
				String mobileNo=rs.getString("MOBILE_NO");
				mobileNo=mobileNo==null?"":mobileNo;
				String email=rs.getString("E_MAILE");
				email=email==null?"":email;
				String qq= rs.getString("QQ");
				qq=qq==null?"":qq;
				String role=rs.getString("LEAD_LEAVENAME");
				role=role==null?"":role;
				
				JSONObject json=new JSONObject();
				json.accumulate("name", rs.getString("USER_NAME"));
				json.accumulate("id", rs.getString("ACCOUNT_ID"));
				json.accumulate("seqId", rs.getInt("USER_INFO_SEQ_ID"));
				json.accumulate("mobile_phone", mobileNo);
				json.accumulate("work_phone", homeTel);
				json.accumulate("sign", sign);
				json.accumulate("email", email);
				json.accumulate("qq", qq);
				json.accumulate("birthday", birthday);
				json.accumulate("avatar_url", headImg);
				json.accumulate("role", role);
				json.accumulate("department", rs.getString("DEPT_NAME"));
				jsonArr.add(json);
			}
			JSONObject orgjson=this.orgIdinfo(conn, account.getOrgId());
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "请求公司成员状态成功");
			JSONObject dataJson=new JSONObject();
			dataJson.accumulate("list", jsonArr);
			returnJson.accumulate("data", dataJson);
			returnJson.accumulate("companyname", orgjson.getString("name"));
			returnJson.accumulate("companyid", orgjson.getString("id"));
			returnJson.accumulate("deptCount", orgjson.getInt("deptCount"));
			returnJson.accumulate("time", new Date().getTime()/1000);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return returnJson.toString();
	}

	/**
	 * 根据orgId 获取公司信息
	 * Time:2015-10-14
	 * Author:Wp 
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public JSONObject orgIdinfo(Connection conn,String orgId)throws Exception{
		String queryStr="SELECT ORG_ID,ORG_NAME,"
				+ "			("
				+ "				SELECT COUNT(1) FROM USER_INFO LEFT JOIN ACCOUNT ON USER_INFO.ACCOUNT_ID=ACCOUNT.ACCOUNT_ID  "
				+ "					WHERE USER_INFO.ORG_ID=U.ORG_ID AND ACCOUNT.NOT_LOGIN='0'"
				+ "			) DEPT_COUNT "
				+ "			 FROM ORG_CONFIG U WHERE ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		JSONObject json=new JSONObject();
		if(rs.next()){
			json.accumulate("name", rs.getString("ORG_NAME"));
			json.accumulate("id", rs.getString("ORG_ID"));
			json.accumulate("deptCount", rs.getInt("DEPT_COUNT"));
		}
		rs.close();
		ps.close();
		return json;
	}
	/**
	 * 获取公司组织架构 type=1
	 * Time:2015-10-14
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platfrom
	 * @return
	 * @throws Exception
	 */
	public String companystructureLogic(Connection conn,Account account,String token,String version,String platfrom,HttpServletRequest request)throws Exception{
		JSONObject returnJson = new JSONObject();
		String queryStr="SELECT "
				+ "			ID,DEPT_NAME,DEPT_ID,"
				+ "			("
				+ "				SELECT COUNT(1) FROM USER_INFO "
				+ "					LEFT JOIN ACCOUNT ON USER_INFO.ACCOUNT_ID=ACCOUNT.ACCOUNT_ID  "
				+ "					LEFT JOIN DEPARTMENT ON USER_INFO.DEPT_ID=DEPARTMENT.DEPT_ID"
				+ "					WHERE ACCOUNT.NOT_LOGIN='0' AND"
				+ "						DEPARTMENT.DEPT_LONG_ID LIKE CONCAT('%',D.DEPT_ID,'%')"
				+ "			) DEPT_COUNT "
				+ "			FROM DEPARTMENT D WHERE ORG_LEAVE_ID='0'AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			JSONArray personjson=this.getdeptStaffLogic(conn, rs.getString("DEPT_ID"),account.getOrgId(),request);
			json.accumulate("subdept", this.getsondept(conn, rs.getString("DEPT_ID"), account.getOrgId(), request));
			json.accumulate("person", personjson);
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("seqId", rs.getInt("ID"));
			json.accumulate("deptCount", rs.getInt("DEPT_COUNT"));
			jsonArr.add(json);
		}
		JSONObject dataJson=new JSONObject();
		dataJson.accumulate("list",jsonArr);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求公司组织架构状态成功");
		returnJson.accumulate("data", dataJson);
		JSONObject orgjson=this.orgIdinfo(conn, account.getOrgId());
		returnJson.accumulate("companyname", orgjson.getString("name"));
		returnJson.accumulate("companyid", orgjson.getString("id"));
		returnJson.accumulate("deptCount", orgjson.getInt("deptCount"));
		returnJson.accumulate("time", new Date().getTime()/1000);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	/**
	 * 获取权限内人员的信息
	 * @param conn
	 * @param account
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONArray authorityperson(Connection conn,Account account,HttpServletRequest request)throws Exception{
		PreparedStatement ps=null;
		ResultSet rs=null;
			UserInfo userInfo = new UserInfo();
			tfd.system.unit.userinfo.logic.UserInfoLogic userInfoLogic = new tfd.system.unit.userinfo.logic.UserInfoLogic();
			userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
			String manageDept = userInfo.getManageDept();
			String queryStr="";
			if(manageDept.equals("2")){
				queryStr="SELECT DISTINCT "
						+ "		T1.ACCOUNT_ID AS ACCOUNT_ID ,T1.USER_NAME AS USER_NAME,T1.DEPT_ID AS DEPT_ID,"
						+ "		(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
						+ "		T1.MOBILE_NO AS MOBILE_NO,T1.HOME_TEL AS HOME_TEL,T1.SIGN AS SIGN,"
						+ "		T1.E_MAILE AS E_MAILE,T1.QQ AS QQ ,T1.BIRTHDAY AS BIRTHDAY,"
						+ "		T1.HEAD_IMG AS HEAD_IMG,T1.LEAD_LEAVE AS LEAD_LEAVE,"
						+ "		T4.LEAVE_NAME AS LEAD_LEAVENAME,T1.DEPT_ID AS DEPT_ID , "
						+ "		T3.DEPT_NAME AS DEPT_NAME "
						+ "	FROM  USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID  "
						+ "					   LEFT JOIN DEPARTMENT T3 ON T3.DEPT_ID =T1.DEPT_ID  "
						+ "					   LEFT JOIN USER_LEAVE T4 ON T1.LEAD_LEAVE = T4.LEAVE_ID "
						+ " WHERE T1.ACCOUNT_ID = T2.ACCOUNT_ID "
						+ "       AND T2.NOT_LOGIN='0' "
						+ "		  AND (T1.DEPT_ID=? OR T1.OTHER_DEPT LIKE ?) "
						+ "       AND T1.LEAD_LEAVE=T4.LEAVE_ID "
						+ "       AND T4.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) "
						+ "		  OR T1.LEAD_ID=?";
			}else{
				queryStr="SELECT DISTINCT "
						+ "		T1.ACCOUNT_ID AS ACCOUNT_ID ,T1.USER_NAME AS USER_NAME,T1.DEPT_ID AS DEPT_ID,"
						+ "		(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
						+ "		T1.MOBILE_NO AS MOBILE_NO,T1.HOME_TEL AS HOME_TEL,"
						+ "		T1.SIGN AS SIGN,T1.E_MAILE AS E_MAILE,T1.QQ AS QQ ,"
						+ "		T1.BIRTHDAY AS BIRTHDAY,T1.HEAD_IMG AS HEAD_IMG,"
						+ "		T1.LEAD_LEAVE AS LEAD_LEAVE,T4.LEAVE_NAME AS LEAD_LEAVENAME,"
						+ "		T1.DEPT_ID AS DEPT_ID , T3.DEPT_NAME AS DEPT_NAME "
						+ " FROM  USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID = T2.ACCOUNT_ID  "
						+ "					   LEFT JOIN DEPARTMENT T3 ON T3.DEPT_ID =T1.DEPT_ID  "
						+ "					   LEFT JOIN USER_LEAVE T4 ON T1.LEAD_LEAVE = T4.LEAVE_ID  "
						+ " WHERE T1.ACCOUNT_ID = T2.ACCOUNT_ID "
						+ "			AND T2.NOT_LOGIN='0' "
						+ "			AND T1.LEAD_LEAVE=T4.LEAVE_ID "
						+ "			AND T4.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) "
						+ "			OR T1.LEAD_ID=?";
			}
			ps =conn.prepareStatement(queryStr);
			UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
			if(manageDept.equals("2")){
				ps.setString(1, userInfo.getDeptId());
				ps.setString(2, "%"+userInfo.getDeptId()+"%");
				String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
				ps.setString(3, leaveNoId);
				ps.setString(4, userInfo.getLeadId());
			}else{
				ps.setString(1, userInfo.getLeadLeave());
				ps.setString(2, userInfo.getLeadId());
			}
			rs = ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				String sign=rs.getString("SIGN");
				sign=sign==null?"":sign;
				String birthday=rs.getString("BIRTHDAY");
				birthday=birthday==null?"":birthday;
				String headImg=rs.getString("HEAD_IMG");
				headImg=headImg==null||headImg.trim().equals("")?"":request.getContextPath()+"/attachment/userinfo/"+headImg;
				String homeTel=rs.getString("HOME_TEL");
				homeTel=homeTel==null?"":homeTel;
				String mobileNo=rs.getString("MOBILE_NO");
				mobileNo=mobileNo==null?"":mobileNo;
				String email=rs.getString("E_MAILE");
				email=email==null?"":email;
				String qq= rs.getString("QQ");
				qq=qq==null?"":qq;
				String role=rs.getString("LEAD_LEAVENAME");
				role=role==null?"":role;
				
				JSONObject json=new JSONObject();
				json.accumulate("name", rs.getString("USER_NAME"));
				json.accumulate("id", rs.getString("ACCOUNT_ID"));
				json.accumulate("seqId", rs.getInt("USER_INFO_SEQ_ID"));
				json.accumulate("mobile_phone", mobileNo);
				json.accumulate("work_phone", homeTel);
				json.accumulate("sign", sign);
				json.accumulate("email", email);
				json.accumulate("qq", qq);
				json.accumulate("birthday", birthday);
				json.accumulate("avatar_url", headImg);
				json.accumulate("role", role);
				json.accumulate("deptId", rs.getString("DEPT_ID"));
				jsonArr.add(json);
			}
		return jsonArr;
	}
	/**
	 * 获取公司组织架构 type=2
	 * Time:2015-10-14
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platfrom
	 * @return
	 * @throws Exception
	 */
	public String authoritystructureLogic(Connection conn,Account account,String token,String version,String platfrom,HttpServletRequest request)throws Exception{
		JSONObject returnJson = new JSONObject();
		String queryStr="SELECT "
				+ "			ID,DEPT_NAME,DEPT_ID,"
				+ "			("
				+ "				SELECT COUNT(1) FROM USER_INFO "
				+ "					LEFT JOIN ACCOUNT ON USER_INFO.ACCOUNT_ID=ACCOUNT.ACCOUNT_ID  "
				+ "					LEFT JOIN DEPARTMENT ON USER_INFO.DEPT_ID=DEPARTMENT.DEPT_ID"
				+ "					WHERE ACCOUNT.NOT_LOGIN='0' AND"
				+ "						DEPARTMENT.DEPT_LONG_ID LIKE CONCAT('%',D.DEPT_ID,'%')"
				+ "			) DEPT_COUNT "
				+ "			FROM DEPARTMENT D WHERE ORG_LEAVE_ID='0'AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		JSONArray jsonperson=this.authorityperson(conn, account, request);
		while(rs.next()){
			JSONObject json=new JSONObject();
			JSONArray personjson=new JSONArray();
			json.accumulate("subdept", this.getauthoritysondept(conn, rs.getString("DEPT_ID"), account, request));
			for (int i = 0; i < jsonperson.size(); i++) {
				if(rs.getString("DEPT_ID").equals(jsonperson.getJSONObject(i).getString("deptId"))){
					JSONObject jsonstaff=new JSONObject();
					jsonstaff.accumulate("name",jsonperson.getJSONObject(i).getString("name") );
					jsonstaff.accumulate("id", jsonperson.getJSONObject(i).getString("id"));
					jsonstaff.accumulate("seqId", jsonperson.getJSONObject(i).getString("seqId"));
					jsonstaff.accumulate("mobile_phone", jsonperson.getJSONObject(i).getString("mobile_phone"));
					jsonstaff.accumulate("work_phone", jsonperson.getJSONObject(i).getString("work_phone"));
					jsonstaff.accumulate("sign", jsonperson.getJSONObject(i).getString("sign"));
					jsonstaff.accumulate("email", jsonperson.getJSONObject(i).getString("email"));
					jsonstaff.accumulate("qq", jsonperson.getJSONObject(i).getString("qq"));
					jsonstaff.accumulate("birthday", jsonperson.getJSONObject(i).getString("birthday"));
					jsonstaff.accumulate("avatar_url", jsonperson.getJSONObject(i).getString("avatar_url"));
					jsonstaff.accumulate("role", jsonperson.getJSONObject(i).getString("role"));
					personjson.add(jsonstaff);
				}	
			}
			json.accumulate("person", personjson);
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("seqId", rs.getInt("ID"));
			json.accumulate("deptCount", personjson.size());
			jsonArr.add(json);
		}
		JSONObject dataJson=new JSONObject();
		dataJson.accumulate("list",jsonArr);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求公司组织架构状态成功");
		returnJson.accumulate("data", dataJson);
		JSONObject orgjson=this.orgIdinfo(conn, account.getOrgId());
		returnJson.accumulate("companyname", orgjson.getString("name"));
		returnJson.accumulate("companyid", orgjson.getString("id"));
		returnJson.accumulate("deptCount", jsonperson.size());
		returnJson.accumulate("time", new Date().getTime()/1000);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	/**
	 * 根据部门id 获取人员信息
	 * Time:2015-10-14
	 * Author:Wp
	 * @param conn
	 * @param deptId
	 * @param orgId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONArray getdeptStaffLogic(Connection conn,String deptId,String orgId,HttpServletRequest request)throws Exception{
		String queryStr="SELECT DISTINCT T1.USER_NAME AS USER_NAME,"
				+ "			T1.ACCOUNT_ID AS ACCOUNT_ID,"
				+ "			(SELECT MAX(ID) FROM DEPARTMENT)+T1.ID AS USER_INFO_SEQ_ID,"
				+ "			T1.MOBILE_NO AS MOBILE_NO,"
				+ "			T1.HOME_TEL AS HOME_TEL,T1.SIGN AS SIGN,T1.E_MAILE AS E_MAILE,"
				+ "			T1.QQ AS QQ ,T1.BIRTHDAY AS BIRTHDAY,T1.HEAD_IMG AS HEAD_IMG,"
				+ "			T1.LEAD_LEAVE AS LEAD_LEAVE,(SELECT LEAVE_NAME FROM  USER_LEAVE T4 WHERE T4.LEAVE_ID=T1.LEAD_LEAVE)AS LEAD_LEAVENAME "
				+ "		FROM USER_INFO T1,ACCOUNT T2 "
				+ "		WHERE T1.DEPT_ID=? AND T1.ACCOUNT_ID=T2.ACCOUNT_ID AND T2.NOT_LOGIN='0' AND T1.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			String sign=rs.getString("SIGN");
			sign=sign==null?"":sign;
			String birthday=rs.getString("BIRTHDAY");
			birthday=birthday==null?"":birthday;
			String headImg=rs.getString("HEAD_IMG");
			headImg=headImg==null||headImg.trim().equals("")?"":request.getContextPath()+"/attachment/userinfo/"+headImg;
			String homeTel=rs.getString("HOME_TEL");
			homeTel=homeTel==null?"":homeTel;
			String mobileNo=rs.getString("MOBILE_NO");
			mobileNo=mobileNo==null?"":mobileNo;
			String email=rs.getString("E_MAILE");
			email=email==null?"":email;
			String qq= rs.getString("QQ");
			qq=qq==null?"":qq;
			String role=rs.getString("LEAD_LEAVENAME");
			role=role==null?"":role;
			
			json.accumulate("name", rs.getString("USER_NAME"));
			json.accumulate("id", rs.getString("ACCOUNT_ID"));
			json.accumulate("seqId", rs.getInt("USER_INFO_SEQ_ID"));
			json.accumulate("mobile_phone", mobileNo);
			json.accumulate("work_phone", homeTel);
			json.accumulate("sign", sign);
			json.accumulate("email", email);
			json.accumulate("qq", qq);
			json.accumulate("birthday", birthday);
			json.accumulate("avatar_url", headImg);
			json.accumulate("role", role);
			jsonArr.add(json);
		}
		return jsonArr;
	}
	
	public JSONArray getsondept(Connection conn,String deptId,String orgId,HttpServletRequest request)throws Exception{
		JSONArray jsonArr=new JSONArray();
		String queryStr="SELECT ID,DEPT_NAME,DEPT_ID, "
				+ "			("
				+ "				SELECT COUNT(1) FROM USER_INFO "
				+ "					LEFT JOIN ACCOUNT ON USER_INFO.ACCOUNT_ID=ACCOUNT.ACCOUNT_ID  "
				+ "					LEFT JOIN DEPARTMENT ON USER_INFO.DEPT_ID=DEPARTMENT.DEPT_ID"
				+ "					WHERE ACCOUNT.NOT_LOGIN='0' AND"
				+ "						DEPARTMENT.DEPT_LONG_ID LIKE CONCAT('%',D.DEPT_ID,'%')"
				+ "			) DEPT_COUNT "
				+ "			FROM DEPARTMENT D WHERE ORG_LEAVE_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			JSONArray personjson=this.getdeptStaffLogic(conn, rs.getString("DEPT_ID"),orgId,request);
			json.accumulate("subdept", this.getsondept(conn, rs.getString("DEPT_ID"), orgId, request));
			json.accumulate("person", personjson);
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("seqId", rs.getInt("ID"));
			json.accumulate("deptCount", rs.getInt("DEPT_COUNT"));
			jsonArr.add(json);
		}
		return jsonArr;
	}
	public JSONArray getauthoritysondept(Connection conn,String deptId,Account account,HttpServletRequest request)throws Exception{
		JSONArray jsonArr=new JSONArray();
		String queryStr="SELECT ID,DEPT_NAME,DEPT_ID, "
				+ "			("
				+ "				SELECT COUNT(1) FROM USER_INFO "
				+ "					LEFT JOIN ACCOUNT ON USER_INFO.ACCOUNT_ID=ACCOUNT.ACCOUNT_ID  "
				+ "					LEFT JOIN DEPARTMENT ON USER_INFO.DEPT_ID=DEPARTMENT.DEPT_ID"
				+ "					WHERE ACCOUNT.NOT_LOGIN='0' AND"
				+ "						DEPARTMENT.DEPT_LONG_ID LIKE CONCAT('%',D.DEPT_ID,'%')"
				+ "			) DEPT_COUNT "
				+ "			FROM DEPARTMENT D WHERE ORG_LEAVE_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, account.getOrgId());
		JSONArray jsonperson=this.authorityperson(conn, account, request);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json=new JSONObject();
			JSONArray personjson=new JSONArray();
			for (int i = 0; i < jsonperson.size(); i++) {
				if(rs.getString("DEPT_ID").equals(jsonperson.getJSONObject(i).getString("deptId"))){
					JSONObject jsonstaff=new JSONObject();
					jsonstaff.accumulate("name",jsonperson.getJSONObject(i).getString("name") );
					jsonstaff.accumulate("id", jsonperson.getJSONObject(i).getString("id"));
					jsonstaff.accumulate("seqId", jsonperson.getJSONObject(i).getString("seqId"));
					jsonstaff.accumulate("mobile_phone", jsonperson.getJSONObject(i).getString("mobile_phone"));
					jsonstaff.accumulate("work_phone", jsonperson.getJSONObject(i).getString("work_phone"));
					jsonstaff.accumulate("sign", jsonperson.getJSONObject(i).getString("sign"));
					jsonstaff.accumulate("email", jsonperson.getJSONObject(i).getString("email"));
					jsonstaff.accumulate("qq", jsonperson.getJSONObject(i).getString("qq"));
					jsonstaff.accumulate("birthday", jsonperson.getJSONObject(i).getString("birthday"));
					jsonstaff.accumulate("avatar_url", jsonperson.getJSONObject(i).getString("avatar_url"));
					jsonstaff.accumulate("role", jsonperson.getJSONObject(i).getString("role"));
					personjson.add(jsonstaff);
				}	
			}
			json.accumulate("subdept", this.getauthoritysondept(conn, rs.getString("DEPT_ID"), account, request));
			json.accumulate("person", personjson);
			json.accumulate("name", rs.getString("DEPT_NAME"));
			json.accumulate("id", rs.getString("DEPT_ID"));
			json.accumulate("seqId", rs.getInt("ID"));
			json.accumulate("deptCount",personjson.size());
			jsonArr.add(json);
		}
		return jsonArr;
	}
	/**
	 * 查看用户其他信息
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String userOtherInfo(Connection conn, Account account,String token, String version, String platform, String userId)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		String targetData = this.getTarget(conn, userId, account.getOrgId());
		String markData = this.getMark(conn, userId, account.getOrgId(), account.getAccountId());
		String secretaryData = this.getSecretary(conn, userId, account.getOrgId());
		jsonObj.accumulate("time",new Date().getTime()/1000 );
		jsonObj.accumulate("targets", targetData);
		jsonObj.accumulate("marks", markData);
		jsonObj.accumulate("smallsecretary", secretaryData);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		returnJson.accumulate("data", jsonObj);
		return returnJson.toString();
	}
	
	/**
	 * 获取目标墙
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getTarget(Connection conn,String userId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String sql = "";
		if(dbType.equals("mysql")){
			sql = "SELECT TARGET_ID,CONTENT FROM TARGET WHERE ACCOUNT_ID = ? AND ORG_ID = ? ORDER BY CREATE_TIME DESC LIMIT 3 ";
		}else if(dbType.equals("oracle")){
			sql = "SELECT t1.* FROM (SELECT TARGET_ID,CONTENT,ROWNUM AS RN FROM TARGET WHERE ACCOUNT_ID = ? AND ORG_ID = ? ORDER BY CREATE_TIME DESC ) t1 WHERE RN < 4 ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("target_id", rs.getString("TARGET_ID"));
			json.accumulate("content", rs.getString("CONTENT"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 获取标签
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param userId
	 * @param orgId
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public String getMark(Connection conn,String userId,String orgId,String accountId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String sql = "";
		if(dbType.equals("mysql")){
			sql = "SELECT MARK_ID,CONTENT,GOOD FROM MARK WHERE ACCOUNT_ID = ? AND ORG_ID = ?  LIMIT 10 ";
		}else if(dbType.equals("oracle")){
			sql = "SELECT t1.* FROM (SELECT MARK_ID,CONTENT,GOOD,ROWNUM AS RN FROM MARK WHERE ACCOUNT_ID = ? AND ORG_ID = ?  ) t1 WHERE RN < 11 ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, orgId);
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
							if(goods[i].equals(accountId)){
								flag = "1";
							}
						}
					}else{
						if(good.equals(accountId)){
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
	 * 获取小秘书
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getSecretary(Connection conn,String userId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT t1.SECRETARY_ID,t1.CONTENT FROM SECRETARY t1 LEFT JOIN USER_INFO t2 ON t1.SECRETARY_ID = t2.SECRETARY_ID WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("id",rs.getString("SECRETARY_ID") );
			json.accumulate("content",rs.getString("CONTENT") );
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 查看自己的目标墙
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @return
	 * @throws Exception
	 */
	public String targetWall(Connection conn, Account account, String token,String version, String platform)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		String unfinish = this.getTargets(conn, account.getAccountId(), account.getOrgId(), "0");
		String finish = this.getTargets(conn, account.getAccountId(), account.getOrgId(), "1");
		jsonObj.accumulate("time",new Date().getTime()/1000 );
		jsonObj.accumulate("unfinish_targets", unfinish);
		jsonObj.accumulate("finish_targets", finish);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		returnJson.accumulate("data", jsonObj);
		return returnJson.toString();
	}
	
	/**
	 * 获取目标墙列表
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public String getTargets(Connection conn,String accountId,String orgId,String status)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT TARGET_ID,CONTENT,CREATE_TIME,IS_PUBLIC,STATUS,GOOD FROM TARGET WHERE ACCOUNT_ID = ? AND ORG_ID = ? AND STATUS = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ps.setString(3, status);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("TARGET_ID"));
			json.accumulate("content", rs.getString("CONTENT"));
			json.accumulate("createTime", rs.getString("CREATE_TIME"));
			json.accumulate("isPublic", rs.getString("IS_PUBLIC"));
			json.accumulate("status", rs.getString("STATUS"));
			String good = rs.getString("GOOD");
			JSONArray userArr = new JSONArray();
			if(good!=null){
				if(!good.equals("")){
					if(good.indexOf(",")>-1){
						String[] goods = good.split(",");
						for (int i = 0; i < goods.length; i++) {
							JSONObject jsonUser = new JSONObject();
							jsonUser.accumulate("userid", goods[i]);
							String userName = new AccountLogic().getUserNameStr(conn, goods[i]);
							jsonUser.accumulate("username", userName);
							userArr.add(jsonUser);
						}
					}else{
						JSONObject jsonUser = new JSONObject();
						jsonUser.accumulate("userid", good);
						String userName = new AccountLogic().getUserNameStr(conn, good);
						jsonUser.accumulate("username", userName);
						userArr.add(jsonUser);
					}
				}
			}
			json.accumulate("likes", userArr);
			jsonArr.add(json);
		}
		ps.close();
		rs.close();
		return jsonArr.toString();
	}
	
	/**
	 * 查看其他人的目标墙
	 * Time:2016-11-16
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
	public String targetWallOther(Connection conn, Account account,String token, String version, String platform, String id)throws Exception {
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT TARGET_ID,CONTENT,CREATE_TIME,IS_PUBLIC,STATUS,GOOD FROM TARGET WHERE ACCOUNT_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("TARGET_ID"));
			json.accumulate("content", rs.getString("CONTENT"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("createTime", rs.getString("CREATE_TIME"));
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
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		jsonObj.accumulate("time",new Date().getTime()/1000 );
		jsonObj.accumulate("targets", jsonArr);
		returnJson.accumulate("data", jsonObj);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 创建/编辑目标
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param target
	 * @param isAdd
	 * @return
	 * @throws Exception
	 */
	public String createTarget(Connection conn, Account account,String token, String version, String platform, Target target,String isAdd)throws Exception{
		String returnData = "";
		if(isAdd.equals("1")){
			returnData = this.addTarget(conn, target);
		}else{
			returnData = this.editTarget(conn, target);
		}
		return returnData;
	}
	
	/**
	 * 添加目标
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public String addTarget(Connection conn,Target target)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "INSERT INTO TARGET(TARGET_ID,CREATE_TIME,CONTENT,IS_PUBLIC,STATUS,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, target.getTargetId());
		ps.setString(2, target.getCreateTime());
		ps.setString(3, target.getContent());
		ps.setString(4, target.getIsPublic());
		ps.setString(5, target.getStatus());
		ps.setString(6, target.getAccountId());
		ps.setString(7, target.getOrgId());
		int i = ps.executeUpdate();
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "创建成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "创建失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		json.accumulate("id", target.getTargetId());
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 编辑目标
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public String editTarget(Connection conn,Target target)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "UPDATE TARGET SET CREATE_TIME = ?,CONTENT = ?,IS_PUBLIC = ?,STATUS = ? WHERE TARGET_ID = ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, target.getCreateTime());
		ps.setString(2, target.getContent());
		ps.setString(3, target.getIsPublic());
		ps.setString(4, target.getStatus());
		ps.setString(5, target.getTargetId());
		ps.setString(6, target.getAccountId());
		ps.setString(7, target.getOrgId());
		int i = ps.executeUpdate();
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "修改成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "修改失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		json.accumulate("id", target.getTargetId());
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 目标点赞/取消赞
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param id
	 * @param ispraise
	 * @return
	 * @throws Exception
	 */
	public String praisetarget(Connection conn, Account account, String token,String version, String platform, String id, String ispraise)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "SELECT GOOD FROM TARGET WHERE TARGET_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		String good = "";
		if(rs.next()){
			good = rs.getString("GOOD");
			if(good == null){
				good = "";
			}
		}
		if(ispraise.equals("1")){
			good = good + account.getAccountId() + ",";
		}else{
			good = good.replaceAll(account.getAccountId()+",", "");
		}
		sql =  "UPDATE TARGET SET GOOD = ? WHERE TARGET_ID = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, good);
		ps.setString(2, id);
		int i = ps.executeUpdate();
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 操作目标
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param id
	 * @param operatetype
	 * @return
	 * @throws Exception
	 */
	public String operatetarget(Connection conn, Account account,String token, String version, String platform, String id,String operatetype)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		int i = 0;
		if(operatetype.equals("2")){
			String sql = "DELETE FROM TARGET WHERE TARGET_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			i = ps.executeUpdate();
			ps.close();
		}else{
			String sql = "UPDATE TARGET SET STATUS = ? WHERE TARGET_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, operatetype);
			ps.setString(2,id);
			i = ps.executeUpdate();
			ps.close();
		}
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		return returnJson.toString();
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
	public String createmark(Connection conn, Account account, String token,String version, String platform, Mark mark)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "INSERT INTO MARK(MARK_ID,CONTENT,CREATE_USER,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, mark.getMarkId());
		ps.setString(2, mark.getContent());
		ps.setString(3, mark.getCreateUser());
		ps.setString(4, mark.getAccountId());
		ps.setString(5, mark.getOrgId());
		int i = ps.executeUpdate();
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "创建成功");
			json.accumulate("id", mark.getMarkId());
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "创建失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 查看用户标签列表
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public String marks(Connection conn, Account account, String token,String version, String platform, String userid)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT MARK_ID,CONTENT,CREATE_USER,GOOD FROM MARK WHERE ACCOUNT_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userid);
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("markid", rs.getString("MARK_ID"));
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
			json.accumulate("islike", flag);
			String createUser = rs.getString("CREATE_USER");
			json.accumulate("createrid", createUser);
			String userName = new AccountLogic().getUserNameStr(conn, createUser);
			json.accumulate("creater", userName);
			json.accumulate("likes", num);
			
			jsonArr.add(json);
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("marks", jsonArr);
		returnJson.accumulate("data", jsonObj);
		rs.close();
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 标签点赞
	 * Time:2016-11-16
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
	public String praisemark(Connection conn, Account account, String token,String version, String platform, String id,String islike)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "SELECT GOOD FROM MARK WHERE MARK_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		String good = "";
		if(rs.next()){
			good = rs.getString("GOOD");
			if(good == null){
				good = "";
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
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	
	/**
	 * 获取小秘书列表
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @return
	 * @throws Exception
	 */
	public String smallsecretarys(Connection conn, Account account,String token, String version, String platform)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT SECRETARY_ID,CONTENT FROM SECRETARY WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("SECRETARY_ID"));
			json.accumulate("content", rs.getString("CONTENT"));
			jsonArr.add(json);
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("list", jsonArr);
		returnJson.accumulate("data", jsonObj);
		ps.close();
		rs.close();
		return returnJson.toString();
	}
	
	/**
	 * 选择小秘书
	 * Time:2016-11-16
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param token
	 * @param version
	 * @param platform
	 * @param smallsecretary
	 * @return
	 * @throws Exception
	 */
	public String selectsmallsecretary(Connection conn, Account account,String token, String version, String platform, String smallsecretary)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject json = new JSONObject();
		String sql = "UPDATE USER_INFO SET SECRETARY_ID = ? WHERE ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, smallsecretary);
		ps.setString(2, account.getAccountId());
		ps.setString(3, account.getOrgId());
		int i = ps.executeUpdate();
		if(i>0){
			returnJson.accumulate("status_code", "200");
			returnJson.accumulate("msg", "更换成功");
		}else{
			returnJson.accumulate("status_code", "500");
			returnJson.accumulate("msg", "更换失败");
		}
		json.accumulate("time", new Date().getTime()/1000);
		returnJson.accumulate("data", json);
		ps.close();
		return returnJson.toString();
	}
	public String markLikes(Connection conn, Account account, String token,String version, String platform, String id,HttpServletRequest request)throws Exception{
		JSONArray userArr = new JSONArray();
		JSONObject returnJson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		String sql = "SELECT GOOD FROM MARK WHERE MARK_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			String good = rs.getString("GOOD");
			if(good!=null){
				if(!good.equals("")){
					if(good.indexOf(",")>-1){
						String[] goods = good.split(",");
						for (int i = 0; i < goods.length; i++) {
							JSONObject jsonUser = new JSONObject();
							jsonUser.accumulate("userId", goods[i]);
							String userName = new AccountLogic().getUserNameStr(conn, goods[i]);
							jsonUser.accumulate("name", userName);
							String imgUrl = SystemUtil.getHeadImgByAccountId(conn, goods[i], request);
							jsonUser.accumulate("avatar", imgUrl);
							userArr.add(jsonUser);
						}
					}else{
						JSONObject jsonUser = new JSONObject();
						jsonUser.accumulate("userId", good);
						String userName = new AccountLogic().getUserNameStr(conn, good);
						jsonUser.accumulate("name", userName);
						String imgUrl = SystemUtil.getHeadImgByAccountId(conn, good, request);
						jsonUser.accumulate("avatar", imgUrl);
						userArr.add(jsonUser);
					}
				}
			}
		}
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg", "请求列表成功");
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("list", userArr);
		returnJson.accumulate("data", jsonObj);
		return returnJson.toString();
	}
}
