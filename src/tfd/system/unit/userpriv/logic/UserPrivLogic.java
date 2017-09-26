package tfd.system.unit.userpriv.logic;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import tfd.system.unit.userpriv.data.UserPriv;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class UserPrivLogic {
	public String getAllUserPirvLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		String queryStr="SELECT ID,USER_PRIV_ID,USER_PRIV_LEAVE,USER_PRIV_NAME FROM USER_PRIV WHERE ORG_ID=?";
		String optStr= "[{'function':'setPriv','name':'设置权限','parm':'USER_PRIV_ID'},{'function':'edit','name':'编辑','parm':'USER_PRIV_ID'},{'function':'copy','name':'复制','parm':'USER_PRIV_ID'},{'function':'readPriv','name':'查看','parm':'USER_PRIV_ID'},{'function':'delPriv','name':'删除','parm':'USER_PRIV_ID'}]";
		JSONArray optArrJson =JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	public String getUserPrivStr(Connection conn,String userPirvIdList) throws SQLException
	{
		ResultSet rs = null;
		String userPrivStr="";
		String query="SELECT USER_PRIV_STR FROM USER_PRIV WHERE USER_PRIV_ID in ('"+userPirvIdList+"')";
		PreparedStatement ps=conn.prepareStatement(query);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	    	userPrivStr=userPrivStr+rs.getString("USER_PRIV_STR")+",";
	    }
	    rs.close();
	    ps.close();
	    if(userPrivStr.length()>1){
	    	userPrivStr=userPrivStr.substring(0,userPrivStr.length()-1);
	    }
	    return userPrivStr;
	}
//获取角色名称
	public String getUserPrivNameById(Connection conn,String USER_PRIV_ID) throws SQLException
	{
		String returnData="";
		ResultSet rs =null;
		String queryStr="SELECT USER_PRIV_NAME FROM USER_PRIV WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, USER_PRIV_ID);
		rs = ps.executeQuery();
		if(rs.next())
		{
			 returnData = rs.getString("USER_PRIV_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//根据USERPIRVID获取USERPRIVSTR
	public String getPrivStrByUserPrivId(Connection conn,String userPrivId) throws SQLException
	{
		String returnData="";
		ResultSet rs =null;
		String queryStr="SELECT USER_PRIV_STR FORM USER_PRIV WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userPrivId);
		rs = ps.executeQuery();
		if(rs.next())
		{
			 returnData = rs.getString("USER_PRIV_STR");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//处理兼职角色字符串
	public String getUserPrivNameStr(Connection conn,String userPrivIdStr) throws SQLException
	{
		String returnData="";
		String queryStr="";
		if(SysTool.isNullorEmpty(userPrivIdStr))
		{
			return returnData;
		}
			if(userPrivIdStr.contains("privAll")){
				returnData+="全部角色,";
			}
			else
			{
			userPrivIdStr = userPrivIdStr.replaceAll(",", "','");
			queryStr="SELECT USER_PRIV_NAME FROM USER_PRIV WHERE USER_PRIV_ID IN ('"+userPrivIdStr+"')";
		
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			returnData+=rs.getString("USER_PRIV_NAME")+",";
		}
		rs.close();
		ps.close();
			}
		if(returnData.length()>0)
		{
		returnData=returnData.substring(0,returnData.length()-1);
		}
		return returnData;
	}
	//添加用户权限组
	public int addUserPrivLogic(Connection conn,UserPriv userPriv) throws SQLException
	{
		String queryStr="INSERT INTO USER_PRIV (USER_PRIV_ID,USER_PRIV_LEAVE,USER_PRIV_NAME,ORG_ID) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userPriv.getUserPrivId());
		ps.setString(2, userPriv.getUserPrivLeave());
		ps.setString(3, userPriv.getUserPrivName());
		ps.setString(4, userPriv.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	
	//获取用户权限组信息通过userPrivId
	public String getUserPrivByUserPrivIdLogic(Connection conn,String userPrivId) throws SQLException
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT ID,USER_PRIV_ID,USER_PRIV_LEAVE,USER_PRIV_NAME,ORG_ID FROM USER_PRIV WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userPrivId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			json.accumulate("id", rs.getString("ID"));
			json.accumulate("userPrivId", rs.getString("USER_PRIV_ID"));
			json.accumulate("userPrivLeave", rs.getString("USER_PRIV_LEAVE"));
			json.accumulate("userPrivName", rs.getString("USER_PRIV_NAME"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	//删除
	public void delByUserPrivIdLogic(Connection conn,String userPrivId) throws SQLException
	{
		String queryStr="DELETE FROM USER_PRIV WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,userPrivId);
		ps.executeUpdate();
		ps.close();
	}
	
	//更新权限
	public int updateUserPriByIdLogic(Connection conn,String userPrivId,String userPrivStr) throws SQLException
	{
		String queryStr="UPDATE USER_PRIV SET USER_PRIV_STR=? WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userPrivStr);
		ps.setString(2, userPrivId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	//复制权限组
	public int copyUserPrivLogic(Connection conn,String userPrivId) throws SQLException, NoSuchAlgorithmException
	{
		String userPrivIdNext = GuId.getGuid();
		String queryStr="INSERT INTO USER_PRIV(USER_PRIV_ID,USER_PRIV_LEAVE,USER_PRIV_NAME,USER_PRIV_STR,ORG_ID) SELECT '"+userPrivIdNext+"',USER_PRIV_LEAVE,USER_PRIV_NAME,USER_PRIV_STR,ORG_ID FROM USER_PRIV WHERE USER_PRIV_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userPrivId);
		int returnData=ps.executeUpdate();
		ps.close();
		return returnData;
		}
	//更新
	public int updateUserPrivLogic(Connection conn,UserPriv userPriv) throws SQLException
	{
		String queryStr="UPDATE USER_PRIV SET USER_PRIV_LEAVE=?,USER_PRIV_NAME=? WHERE USER_PRIV_ID=? AND ORG_ID=?";
		PreparedStatement ps  = conn.prepareStatement(queryStr);
		ps.setString(1, userPriv.getUserPrivLeave());
		ps.setString(2, userPriv.getUserPrivName());
		ps.setString(3, userPriv.getUserPrivId());
		ps.setString(4, userPriv.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	//按人员获取管理范围
	
	public List<Map<String,String>> getRangeListLogic(Connection conn,Account account) throws SQLException
	{
		List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String queryStr="";
		if(manageDept.equals("2"))
		{
			queryStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID ,U.USER_NAME AS USER_NAME,U.DEPT_ID AS DEPT_ID,U.SEX AS SEX FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}else
		{
			queryStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID,U.USER_NAME AS USER_NAME,U.DEPT_ID AS DEPT_ID,U.SEX AS SEX FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}
		PreparedStatement ps =conn.prepareStatement(queryStr);
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			ps.setString(1, userInfo.getDeptId());
			ps.setString(2, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(3, leaveNoId);
			ps.setString(4, userInfo.getLeadId());
		}else
		{
			ps.setString(1, userInfo.getLeadLeave());
			ps.setString(2, userInfo.getLeadId());
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Map<String, String>usermap=new HashMap<String, String>();
			usermap.put("accountId",rs.getString("ACCOUNT_ID"));
			usermap.put("userName",rs.getString("USER_NAME"));
			usermap.put("deptId", rs.getString("DEPT_ID"));
			usermap.put("sex", rs.getString("SEX"));
			userList.add(usermap);
		}
		return userList;
	}
	
	//日程获得管理权限人员
	public List<Map<String,String>> getRangeListByCalendarLogic(Connection conn,Account account,String deptId) throws SQLException
	{
		List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String queryStr = "SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID,U.USER_NAME AS USER_NAME,U.DEPT_ID AS DEPT_ID,U.SEX AS SEX FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND U.DEPT_ID = ?  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, deptId);
		ps.setString(2, userInfo.getLeadLeave());
		ps.setString(3, userInfo.getLeadId());
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Map<String, String>usermap=new HashMap<String, String>();
			usermap.put("accountId",rs.getString("ACCOUNT_ID"));
			usermap.put("userName",rs.getString("USER_NAME"));

			userList.add(usermap);
		}
		return userList;
	}
	
	
	
	//判断人员在模块中的权限
	//flag 判断的模式 ACCOUNT_ID,DEPT,PRIV
	//listStr 需要判断的字符串
	public boolean getRangeLogic(String flag,UserInfo userInfo,String listStr)
	{
		boolean retrunFlag =true;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		if(!SysTool.isNullorEmpty(listStr))
		{
			if(listStr.endsWith(","))
			{
				listStr = listStr.substring(0,listStr.length()-1);
			}
			if(listStr.indexOf(",")<0)
			{
				list2.add(listStr);
			}else
			{
				String[] tmp = listStr.split(",");
				for(int i = 0; tmp.length>i;i++)
				{
					list2.add(tmp[i]);
				}
			}
		}
		if(flag.equals("ACCOUNT_ID"))
		{
			list1.add(userInfo.getAccountId());
			if(Collections.disjoint(list1, list2))
			{
				retrunFlag=false;
			}
		}else if(flag.equals("PRIV"))
		{
			list1.add(userInfo.getPostPriv());
			String userOtherPriv=userInfo.getOtherPriv();
			if(!SysTool.isNullorEmpty(userOtherPriv))
			{
				if(userOtherPriv.endsWith(","))
				{
					userOtherPriv = userOtherPriv.substring(0,userOtherPriv.length()-1);
				}
				if(userOtherPriv.indexOf(",")>0)
				{
					String[] tmp = userOtherPriv.split(",");
					for(int i=0; tmp.length>i;i++)
					{
						list1.add(tmp[i]);
					}
				}else
				{
					list1.add(userOtherPriv);
				}
				if(Collections.disjoint(list1, list2))
				{
					retrunFlag=false;
				}
			}
		}else if(flag.equals("DEPT"))
		{
			list1.add(userInfo.getDeptId());
			String otherDept = userInfo.getOtherDept();
			if(otherDept.endsWith(","))
			{
				otherDept = otherDept.substring(0,otherDept.length()-1);
			}
			if(otherDept.indexOf(",")>0)
			{
				String[] tmp = otherDept.split(",");
				for(int i=0;tmp.length>i;i++)
				{
					list1.add(tmp[i]);
				}
			}else
			{
				list1.add(otherDept);
			}
			if(Collections.disjoint(list1, list2))
			{
				retrunFlag=false;
			}
		}
		return retrunFlag;
	}
	//根据角色字符串获取json 数组
	public String getjsonuserPrivLogic(Connection conn,String userPrivIdStr)throws SQLException{
		String queryStr="";
		JSONArray jsonArr = new JSONArray();
		if(SysTool.isNullorEmpty(userPrivIdStr))
		{
			return jsonArr.toString();
		}
			if(userPrivIdStr.contains("privAll")){
				JSONObject json=new JSONObject();
				json.accumulate("userprivId", "privAll");
				json.accumulate("userprivName", "全部角色");
				jsonArr.add(json);
			}else
			{
			userPrivIdStr = userPrivIdStr.replaceAll(",", "','");
			queryStr="SELECT USER_PRIV_NAME,USER_PRIV_ID FROM USER_PRIV WHERE USER_PRIV_ID IN ('"+userPrivIdStr+"')";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			JSONObject json=new JSONObject();
			json.accumulate("userprivId", rs.getString("USER_PRIV_ID"));
			json.accumulate("userprivName",rs.getString("USER_PRIV_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
			}
		return jsonArr.toString();
	}
	public String getUserPrivIdByUserPrivName(String userPrivName,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="SELECT USER_PRIV_ID FROM USER_PRIV WHERE USER_PRIV_NAME =? AND ORG_ID=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, userPrivName);
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("USER_PRIV_ID");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public String getUserPrivIdStrByUserPrivNameStr(String userPrivNameStr,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select user_priv_id from user_priv where user_priv_name in (?) and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, "'"+userPrivNameStr.replaceAll(",","','")+"'");
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			List<String> privIds=new ArrayList<String>();
			if (rs.next()) {
				privIds.add(rs.getString("user_priv_id"));
			}
			if (privIds.size()>0) {
				return SysTool.join(privIds.toArray(), ",");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public String getUserPrivNameStrByUserPrivIdStr(String userPrivIdStr,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select user_priv_name from user_priv where user_priv_id in (?) and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, userPrivIdStr);
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			List<String> userPrivNames=new ArrayList<String>();
			while (rs.next()) {
				userPrivNames.add(rs.getString("user_priv_name"));
			}
			if (userPrivNames.size()>0) {
				return SysTool.join(userPrivNames.toArray(), ",");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
}

