package tfd.system.publicdisk.logic;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.global.SysPropKey;
import com.system.global.SysProps;

import tfd.system.publicdisk.data.PublicDisk;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

public class PublicDiskLogic {
	
	/**
	 * 新建资源共享
	 * Time:2015-4-10
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param p 共享资源实体
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int addPublicDisk(Connection conn,PublicDisk p)throws Exception{
		String sql = "INSERT INTO PUBLIC_DISK(DISK_ID,DISK_NAME,DISK_PATH,DISK_NO,SPACE_LIMIT,ORDER_BY,ASC_DESC,ORG_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getDiskId());
		ps.setString(2, p.getDiskName());
		ps.setString(3, p.getDiskPath());
		ps.setString(4, p.getDiskNo());
		ps.setString(5, p.getSpaceLimit());
		ps.setString(6, p.getOrderBy());
		ps.setString(7, p.getAscDesc());
		ps.setString(8, p.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据权限查看共享目录设置
	 * Time:2015-4-10
	 * Author:Yzz
	 * @param conn
	 * @param pramList
	 * @param pagesize
	 * @param page
	 * @param storOrder
	 * @param storName
	 * @param accountId
	 * @return String类型的Json数据
	 * @throws Exception
	 */
	public String getPublicDiskList(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName,String accountId)throws Exception{
		String queryStr = null;
		queryStr= "SELECT t1.ID,t1.DISK_ID,t1.DISK_NO,t1.DISK_NAME,t1.DISK_PATH,t1.SPACE_LIMIT"
					+ ",t1.ORDER_BY,t1.ASC_DESC FROM PUBLIC_DISK t1 WHERE "
					+ " t1.ORG_ID = ?";
		String optStr= "[{'function':'editDisk','name':'编辑','parm':'DISK_ID'},{'function':'deleteDisk','name':'删除','parm':'DISK_ID'},{'function':'setPermission','name':'设置权限','parm':'DISK_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	/**
	 * 根据Id查看共享的详细内容
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param diskId 共享资源Id
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getPublicDiskById(Connection conn,String orgId,String diskId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.ID,t1.DISK_NO,t1.DISK_ID,t1.DISK_NAME,t1.DISK_PATH,t1.SPACE_LIMIT,t1.ORDER_BY,t1.ASC_DESC FROM PUBLIC_DISK t1 WHERE t1.DISK_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("diskNo", rs.getString("DISK_NO"));
			json.accumulate("diskId", rs.getString("DISK_ID"));
			json.accumulate("diskName", rs.getString("DISK_NAME"));
			json.accumulate("diskPath", rs.getString("DISK_PATH"));
			json.accumulate("spaceLimit", rs.getString("SPACE_LIMIT"));
			json.accumulate("orderBy", rs.getString("ORDER_BY"));
			json.accumulate("ascDesc", rs.getString("ASC_DESC"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 根据Id修改共享内容
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param p 共享资源实体
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int updatePublicDisk(Connection conn,PublicDisk p)throws Exception{
		String sql = "UPDATE PUBLIC_DISK SET DISK_NO = ?, DISK_NAME = ?,DISK_PATH = ?,SPACE_LIMIT = ?,ORDER_BY = ?,ASC_DESC = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getDiskNo());
		ps.setString(2, p.getDiskName());
		ps.setString(3, p.getDiskPath());
		ps.setString(4, p.getSpaceLimit());
		ps.setString(5, p.getOrderBy());
		ps.setString(6, p.getAscDesc());
		ps.setString(7, p.getDiskId());
		ps.setString(8, p.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据Id删除共享资源
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param diskId 共享资源Id
	 * @return int类型的受影响的行数
	 * @throws Exception
	 */
	public int deletePublicDisk(Connection conn,String orgId,String diskId)throws Exception{
		String sql = "DELETE FROM PUBLIC_DISK  WHERE DISK_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 得到访问权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param diskId 共享目录Id
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getAccessPriv(Connection conn,String orgId,String diskId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.DISK_ID,t1.ACCESS_USER,t1.ACCESS_DEPT,t1.ACCESS_PRIV FROM PUBLIC_DISK t1 WHERE t1.DISK_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("diskId", rs.getString("DISK_ID"));
			json.accumulate("accessUser", rs.getString("ACCESS_USER"));
			String accessUserName = acclogic.getUserNameStr(conn, rs.getString("ACCESS_USER"));
			json.accumulate("accessUserName", accessUserName);
			json.accumulate("accessDept", rs.getString("ACCESS_DEPT"));
			String accessDpetName = deptlogic.getDeptNameStr(conn, rs.getString("ACCESS_DEPT"));
			json.accumulate("accessDpetName",accessDpetName);
			json.accumulate("accessPriv", rs.getString("ACCESS_PRIV"));
			String accessPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("ACCESS_PRIV"));
			json.accumulate("accessPrivName", accessPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到管理权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param diskId 共享目录Id
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getManagePriv(Connection conn,String orgId,String diskId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.DISK_ID,t1.MANAGE_USER,t1.MANAGE_DEPT,t1.MANAGE_PRIV FROM PUBLIC_DISK t1 WHERE t1.DISK_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("diskId", rs.getString("DISK_ID"));
			json.accumulate("manageUser", rs.getString("MANAGE_USER"));
			String manageUserName = acclogic.getUserNameStr(conn, rs.getString("MANAGE_USER"));
			json.accumulate("manageUserName", manageUserName);
			json.accumulate("manageDept", rs.getString("MANAGE_DEPT"));
			String manageDpetName = deptlogic.getDeptNameStr(conn, rs.getString("MANAGE_DEPT"));
			json.accumulate("manageDpetName",manageDpetName);
			json.accumulate("managePriv", rs.getString("MANAGE_PRIV"));
			String managePrivName=ulogic.getUserPrivNameStr(conn, rs.getString("MANAGE_PRIV"));
			json.accumulate("managePrivName", managePrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到新建权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param diskId 共享目录Id
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getNewPriv(Connection conn,String orgId,String diskId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.DISK_ID,t1.NEW_USER,t1.NEW_DEPT,t1.NEW_PRIV FROM PUBLIC_DISK t1 WHERE t1.DISK_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("diskId", rs.getString("DISK_ID"));
			json.accumulate("newUser", rs.getString("NEW_USER"));
			String newUserName = acclogic.getUserNameStr(conn, rs.getString("NEW_USER"));
			json.accumulate("newUserName", newUserName);
			json.accumulate("newDept", rs.getString("NEW_DEPT"));
			String newDpetName = deptlogic.getDeptNameStr(conn, rs.getString("NEW_DEPT"));
			json.accumulate("newDpetName",newDpetName);
			json.accumulate("newPriv", rs.getString("NEW_PRIV"));
			String newPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("NEW_PRIV"));
			json.accumulate("newPrivName", newPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到打印/下载权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param diskId 共享目录Id
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getDownPriv(Connection conn,String orgId,String diskId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.DISK_ID,t1.DOWN_USER,t1.DOWN_DEPT,t1.DOWN_PRIV FROM PUBLIC_DISK t1 WHERE t1.DISK_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diskId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("diskId", rs.getString("DISK_ID"));
			json.accumulate("downUser", rs.getString("DOWN_USER"));
			String downUserName = acclogic.getUserNameStr(conn, rs.getString("DOWN_USER"));
			json.accumulate("downUserName", downUserName);
			json.accumulate("downDept", rs.getString("DOWN_DEPT"));
			String downDpetName = deptlogic.getDeptNameStr(conn, rs.getString("DOWN_DEPT"));
			json.accumulate("downDpetName",downDpetName);
			json.accumulate("downPriv", rs.getString("DOWN_PRIV"));
			String downPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("DOWN_PRIV"));
			json.accumulate("downPrivName", downPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 修改权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param diskId 共享目录Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int updatePriv(Connection conn,String User,String Dept,String Priv,String diskId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			sql = "UPDATE PUBLIC_DISK SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			sql = "UPDATE PUBLIC_DISK SET MANAGE_USER = ?, MANAGE_DEPT = ?,MANAGE_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("3")){
			sql = "UPDATE PUBLIC_DISK SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("4")){
			sql = "UPDATE PUBLIC_DISK SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, diskId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}

	/**
	 * 批量添加权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn
	 * @param User
	 * @param Dept
	 * @param Priv
	 * @param diskId
	 * @param orgId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int addPriv(Connection conn,String User,String Dept,String Priv,String diskId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			JSONObject json = JSONObject.fromObject(getAccessPriv(conn, orgId, diskId));
			Dept = checkJson(Dept,json.getString("accessDept"));
			Priv = checkJson(Priv,json.getString("accessPriv"));
			User = checkJson(User,json.getString("accessUser"));
			sql = "UPDATE PUBLIC_DISK SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			JSONObject json = JSONObject.fromObject(getManagePriv(conn, orgId, diskId));
			Dept = checkJson(Dept,json.getString("manageDept"));
			Priv = checkJson(Priv,json.getString("managePriv"));
			User = checkJson(User,json.getString("manageUser"));
			sql = "UPDATE PUBLIC_DISK SET MANAGE_USER = ?, MANAGE_DEPT = ?,MANAGE_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("3")){
			JSONObject json = JSONObject.fromObject(getNewPriv(conn, orgId, diskId));
			Dept = checkJson(Dept,json.getString("newDept"));
			Priv = checkJson(Priv,json.getString("newPriv"));
			User = checkJson(User,json.getString("newUser"));
			sql = "UPDATE PUBLIC_DISK SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("4")){
			JSONObject json = JSONObject.fromObject(getDownPriv(conn, orgId, diskId));
			Dept = checkJson(Dept,json.getString("downDept"));
			Priv = checkJson(Priv,json.getString("downPriv"));
			User = checkJson(User,json.getString("downUser"));
			sql = "UPDATE PUBLIC_DISK SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, diskId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 检查两个String是否有相同的数据
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String checkJson(String str,String obj)throws Exception{
		String returnData = "";
		if(str.equals("userAll")||str.equals("deptAll")||str.equals("privAll")){
			returnData = str;
		}else{
			List<String> list = new ArrayList<String>();
			String [] strs = str.split(",");
			String [] objs = obj.split(",");
			for (int i = 0; i < strs.length; i++) {
				list.add(strs[i]);
			}
			for (int i = 0; i < objs.length; i++) {
				boolean flag = false;
				for (int j = 0; j < strs.length; j++) {
					if(!list.get(j).equals(objs[i])){
						flag = true;
					}else{
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(objs[i]);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				returnData += list.get(i)+",";
			}
		}
		return returnData;
	}
	/**
	 * 批量移除权限
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn
	 * @param User
	 * @param Dept
	 * @param Priv
	 * @param diskId
	 * @param orgId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int delPriv(Connection conn,String User,String Dept,String Priv,String diskId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			JSONObject json = JSONObject.fromObject(getAccessPriv(conn, orgId, diskId));
			Dept = checkJson2(Dept,json.getString("accessDept"));
			Priv = checkJson2(Priv,json.getString("accessPriv"));
			User = checkJson2(User,json.getString("accessUser"));
			sql = "UPDATE PUBLIC_DISK SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			JSONObject json = JSONObject.fromObject(getManagePriv(conn, orgId, diskId));
			Dept = checkJson2(Dept,json.getString("manageDept"));
			Priv = checkJson2(Priv,json.getString("managePriv"));
			User = checkJson2(User,json.getString("manageUser"));
			sql = "UPDATE PUBLIC_DISK SET MANAGE_USER = ?, MANAGE_DEPT = ?,MANAGE_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("3")){
			JSONObject json = JSONObject.fromObject(getNewPriv(conn, orgId, diskId));
			Dept = checkJson2(Dept,json.getString("newDept"));
			Priv = checkJson2(Priv,json.getString("newPriv"));
			User = checkJson2(User,json.getString("newUser"));
			sql = "UPDATE PUBLIC_DISK SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}if(privId.equals("4")){
			JSONObject json = JSONObject.fromObject(getDownPriv(conn, orgId, diskId));
			Dept = checkJson2(Dept,json.getString("downDept"));
			Priv = checkJson2(Priv,json.getString("downPriv"));
			User = checkJson2(User,json.getString("downUser"));
			sql = "UPDATE PUBLIC_DISK SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE DISK_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, diskId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 检查两个String是否有相同的数据
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String checkJson2(String str,String obj)throws Exception{
		String returnData = "";
		if(str.equals("userAll")||str.equals("deptAll")||str.equals("privAll")){
			returnData = "";
		}else{
			List<String> list = new ArrayList<String>();
			String [] strs = str.split(",");
			String [] objs = obj.split(",");
			for (int i = 0; i < objs.length; i++) {
				list.add(objs[i]);
			}
			for (int i = 0; i < list.size(); i++) {
				boolean flag = false;
				for (int j = 0; j < strs.length; j++) {
					if(list.get(i).equals(strs[j])){
						flag = true;
						break;
					}else{
						flag = false;
					}
				}
				if(flag){
					list.remove(i);
					i = -1;
				}
			}
			for (int i = 0; i < list.size(); i++) {
				returnData += list.get(i)+",";
			}
		}
		return returnData;
	}
	/**
	 * 得到共享目录
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getDiskList(Connection conn,String orgId,String accountId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType =DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql"))
		{
			queryStr = "SELECT t1.DISK_ID,t1.DISK_NAME,t1.DISK_PATH,t1.ORDER_BY,t1.ASC_DESC FROM PUBLIC_DISK t1 WHERE"
					+ " ( LOCATE(?,CONCAT(',',t1.ACCESS_USER,',')) > 0 "
					+ " OR LOCATE(?,CONCAT(',',t1.ACCESS_DEPT,',')) > 0 "
					+ " OR LOCATE(?,CONCAT(',',t1.ACCESS_PRIV,',')) > 0 "
					+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' )"
					+ " AND t1.ORG_ID = ? ORDER BY t1.DISK_NO ";
		}else	if(dbType.equals("sqlserver")){
			queryStr = "SELECT t1.DISK_ID,t1.DISK_NAME,t1.DISK_PATH,t1.ORDER_BY,t1.ASC_DESC FROM PUBLIC_DISK t1 WHERE"
					+ " ( CHARINDEX(?,','+t1.ACCESS_USER+',') > 0 "
					+ " OR CHARINDEX(?,','+t1.ACCESS_DEPT+',') > 0"
					+ " OR CHARINDEX(?,','+t1.ACCESS_PRIV+',') > 0 "
					+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll') "
					+ " AND t1.ORG_ID = ? ORDER BY t1.DISK_NO ";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.DISK_ID,t1.DISK_NAME,t1.DISK_PATH,t1.ORDER_BY,t1.ASC_DESC FROM PUBLIC_DISK t1 WHERE"
					+ " ( INSTR(CONCAT(',',t1.ACCESS_USER)||',',?) > 0 "
					+ " OR INSTR(CONCAT(',',t1.ACCESS_DEPT)||',',?) > 0 "
					+ " OR INSTR(CONCAT(',',t1.ACCESS_PRIV)||',',?) > 0 "
					+ " OR TO_CHAR(ACCESS_USER) = 'userAll' OR TO_CHAR(ACCESS_DEPT) = 'deptAll' OR TO_CHAR(ACCESS_PRIV) = 'privAll' )"
					+ " AND t1.ORG_ID = ? ORDER BY t1.DISK_NO ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, deptId);
		ps.setString(3, privId);
		ps.setString(4, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("DISK_ID"));
			json.accumulate("name", rs.getString("DISK_NAME"));
			json.accumulate("path", rs.getString("DISK_PATH"));
			json.accumulate("isParent", "true");
			json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
			json.accumulate("orderBy", rs.getString("ORDER_BY"));
			json.accumulate("ascDesc", rs.getString("ASC_DESC"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 判断目录下是否有文件夹
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param path
	 * @return boolean类型
	 * @throws Exception
	 */
	public boolean isParent(String path)throws Exception{
		File f = new File(path);
		String[] filelist = f.list();
		if(filelist.length>0){
			for(int i=0;i<filelist.length;i++){
				File readfile = new File(path + "\\" + filelist[i]);
				if (readfile.isDirectory()) {
					return true;
				}
			}
			
		}else{
			return false;
		}
		return false;
	}
	/**
	 * 验证权限
	 * @param conn
	 * @param diskId
	 * @param privType
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int checkPriv(Connection conn,String diskId,String privType,String accountId,String orgId)throws Exception{
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbms = SysProps.getString(SysPropKey.DBCONN_DBMS);
		if(privType.equals("2")){
			if(dbms.equals("sqlserver")){
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.MANAGE_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.MANAGE_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.MANAGE_PRIV+',') > 0 "
						+ " OR MANAGE_USER = 'userAll' OR MANAGE_DEPT = 'deptAll' OR MANAGE_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}else{
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.MANAGE_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.MANAGE_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.MANAGE_PRIV,',')) > 0 "
						+ " OR MANAGE_USER = 'userAll' OR MANAGE_DEPT = 'deptAll' OR MANAGE_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}
		}else if(privType.equals("3")){
			if(dbms.equals("sqlserver")){
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.NEW_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.NEW_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.NEW_PRIV+',') > 0 "
						+ " OR NEW_USER = 'userAll' OR NEW_DEPT = 'deptAll' OR NEW_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}else{
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.NEW_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.NEW_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.NEW_PRIV,',')) > 0 "
						+ " OR NEW_USER = 'userAll' OR NEW_DEPT = 'deptAll' OR NEW_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}
		}else if(privType.equals("4")){
			if(dbms.equals("sqlserver")){
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.DOWN_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.DOWN_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.DOWN_PRIV+',') > 0 "
						+ " OR DOWN_USER = 'userAll' OR DOWN_DEPT = 'deptAll' OR DOWN_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}else{
				queryStr="SELECT t1.DISK_ID FROM PUBLIC_DISK t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.DOWN_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DOWN_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DOWN_PRIV,',')) > 0 "
						+ " OR DOWN_USER = 'userAll' OR DOWN_DEPT = 'deptAll' OR DOWN_PRIV = 'privAll' ) AND t1.DISK_ID = ? ";
			}
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ps.setString(3, deptId);
		ps.setString(4, privId);
		ps.setString(5, diskId);
		rs = ps.executeQuery();
		if(rs.next()){
			rs.close();
			ps.close();
			return 1;
		}else{
			rs.close();
			ps.close();
			return 0;
		}
		
	}
}
