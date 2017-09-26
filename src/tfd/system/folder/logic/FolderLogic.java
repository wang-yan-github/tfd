package tfd.system.folder.logic;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.filetool.UpFileTool;
import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.tool.GuId;
import com.system.tool.SysFileTool;
import com.system.tool.SysTool;


public class FolderLogic {
	/**
	 * 添加文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param folderPid 上级文件夹Id
	 * @param folderNo 排序号
	 * @param folderName 文件夹名称
	 * @param isPublic 是否是公共的
	 * @param orgId
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int addFolder(Connection conn,String folderId,String folderPid,String folderNo,String folderName, String isPublic,String orgId)throws Exception{
		String sql = "";
		if(folderPid.equals("0")){
			sql = "INSERT INTO FOLDER(FOLDER_ID,FOLDER_NO,FOLDER_PID,FOLDER_NAME,IS_PUBLIC,ORG_ID "
				+ ") VALUES (?, ?, ?, ?, ?, ? ) ";
		}else{
			sql = "INSERT INTO FOLDER(FOLDER_ID,FOLDER_NO,FOLDER_PID,FOLDER_NAME,IS_PUBLIC,ORG_ID "
					+ ",NEW_USER ,NEW_DEPT ,NEW_PRIV"
					+ ",EDIT_USER ,EDIT_DEPT ,EDIT_PRIV"
					+ ",ACCESS_USER ,ACCESS_DEPT ,ACCESS_PRIV"
					+ ",DOWN_USER ,DOWN_DEPT ,DOWN_PRIV"
					+ ",DEL_USER ,DEL_DEPT ,DEL_PRIV"
					+ ",COMM_USER ,COMM_DEPT ,COMM_PRIV"
					+ ") SELECT ?, ?, ?, ?, ?, ?  "
					+ ",NEW_USER ,NEW_DEPT ,NEW_PRIV"
					+ ",EDIT_USER ,EDIT_DEPT ,EDIT_PRIV"
					+ ",ACCESS_USER ,ACCESS_DEPT ,ACCESS_PRIV"
					+ ",DOWN_USER ,DOWN_DEPT ,DOWN_PRIV"
					+ ",DEL_USER ,DEL_DEPT ,DEL_PRIV"
					+ ",COMM_USER ,COMM_DEPT ,COMM_PRIV FROM FOLDER WHERE FOLDER_ID = '"+folderPid+"' ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ps.setString(2, folderNo);
		ps.setString(3, folderPid);
		int j = 2;
		String name = folderName;
		while(!checkName(conn, name,folderPid, "1")){
			name = folderName + "(" + j + ")";
			j++;
		}
		folderName = name;
		ps.setString(4, folderName);
		ps.setString(5, isPublic);
		ps.setString(6, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 得到文件夹列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId
	 * @return String类型的Json数据
	 * @throws Exception
	 */
	public String getFolderList(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName,String accountId)throws Exception{
		String queryStr = null;
		queryStr= "SELECT t1.ID, t1.FOLDER_ID,t1.FOLDER_NO,t1.FOLDER_NAME"
					+ " FROM FOLDER t1 WHERE t1.FOLDER_PID = '0' AND "
					+ " t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		String optStr= "[{'function':'editFolder','name':'编辑','parm':'FOLDER_ID'},{'function':'deleteFolder','name':'删除','parm':'FOLDER_ID'},{'function':'setPermission','name':'设置权限','parm':'FOLDER_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	/**
	 * 根据Id得到文件夹的详细信息
	 * @param conn jbdc连接
	 * @param orgId 企业Id
	 * @param folderId 文件夹Id
	 * @param isPublic 是否是公共的
	 * @return String类型的Json数据
	 * @throws Exception
	 */
	public String getFolderById(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("folderNo", rs.getString("FOLDER_NO"));
			json.accumulate("id", rs.getString("FOLDER_ID"));
			json.accumulate("name", rs.getString("FOLDER_NAME"));
			json.accumulate("pId", rs.getString("FOLDER_PID"));
			json.accumulate("isParent", "true");
			json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 根据Id修改文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int updateFolder(Connection conn,String orgId,String folderId,String folderNo,String folderName,String isPublic)throws Exception{
		String sql = "UPDATE FOLDER SET FOLDER_NO = ?, FOLDER_NAME = ? WHERE FOLDER_ID = ? AND ORG_ID = ? AND IS_PUBLIC = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderNo);
		ps.setString(2, folderName);
		ps.setString(3, folderId);
		ps.setString(4, orgId);
		ps.setString(5, isPublic);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据Id删除文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param folderId 文件夹Id
	 * @return int类型的受影响的行数
	 * @throws Exception
	 */
	public int deleteFolder(Connection conn,String orgId,String folderId)throws Exception{
		String sql = "DELETE FROM FOLDER  WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		if(isParent(conn, folderId)){
			findChild(conn,folderId,orgId);
		}
		return i;
	}
	
	/**
	 * 得到文件夹的下级并删除
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderPid 上级Id
	 * @param orgId 企业Id
	 * @throws Exception
	 */
	public void findChild(Connection conn,String folderPid,String orgId)throws Exception{
		ResultSet rs =null;
		String sql = "SELECT FOLDER_ID FROM FOLDER WHERE FOLDER_PID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderPid);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		while(rs.next()){
			findFolderFile(conn, rs.getString("FOLDER_ID"), orgId);
			deleteFolder(conn,orgId,rs.getString("FOLDER_ID"));
		}
		rs.close();
		ps.close();
	}
	/**
	 * 得到文件夹的文件并删除
	 * Time:2015-4-21
	 * Author:Yzz 
	 * @param conn jdbc连接
	 * @param folderPid 上级Id
	 * @param orgId 企业Id
	 * @throws Exception
	 */
	public void findFolderFile(Connection conn,String folderId,String orgId)throws Exception{
		ResultSet rs =null;
		String sql = "SELECT FILE_ID FROM FOLDER_FILE WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		while(rs.next()){
			deleteFile(conn,orgId,rs.getString("FILE_ID"));
		}
		rs.close();
		ps.close();
	}
	
	/**
	 * 根据Id得到文件夹列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param folderPid 上级Id
	 * @param isPublic 是否是公共的
	 * @return String类型的Json数据
	 * @throws Exception
	 */
	public String getFolderListById(Connection conn,String orgId,String folderPid,String isPublic)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
			String queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID FROM FOLDER t1 WHERE t1.FOLDER_PID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ORDER BY FOLDER_NO ASC";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, folderPid);
			ps.setString(2, orgId);
			ps.setString(3, isPublic);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				
				json.accumulate("folderNo", rs.getString("FOLDER_NO"));
				json.accumulate("id", rs.getString("FOLDER_ID"));
				json.accumulate("name", rs.getString("FOLDER_NAME"));
				json.accumulate("pId", rs.getString("FOLDER_PID"));
				if(isParent(conn, rs.getString("FOLDER_ID"))){
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
					json.accumulate("isParent", "true");
					
				}else{
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder_active.gif");
					json.accumulate("isParent", "false");
				}
				
				jsonArr.add(json);
			}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 判断是否是父级
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderPid 上级Id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isParent(Connection conn, String folderPid)throws Exception{
		boolean flag = false;
		ResultSet rs =null;
		String sql = "SELECT COUNT(*) FROM FOLDER WHERE FOLDER_PID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderPid);
		rs = ps.executeQuery();
		if(rs.next()){
			int i = rs.getInt(1);
			if(i > 0){
				flag = true;
			}else{
				flag = false;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	
	/**
	 * 得到访问权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getAccessPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.ACCESS_USER,t1.ACCESS_DEPT,t1.ACCESS_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("accessUser", rs.getString("ACCESS_USER"));
			String accessUserName = acclogic.getUserNameStr(conn, rs.getString("ACCESS_USER"));
			json.accumulate("accessUserName", accessUserName);
			json.accumulate("accessDept", rs.getString("ACCESS_DEPT"));
			String accessDpetName = deptlogic.getDeptNameStr(conn, rs.getString("ACCESS_DEPT"));
			json.accumulate("accessDeptName",accessDpetName);
			json.accumulate("accessPriv", rs.getString("ACCESS_PRIV"));
			String accessPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("ACCESS_PRIV"));
			json.accumulate("accessPrivName", accessPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到编辑权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getEditPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.EDIT_USER,t1.EDIT_DEPT,t1.EDIT_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("editUser", rs.getString("EDIT_USER"));
			String editUserName = acclogic.getUserNameStr(conn, rs.getString("EDIT_USER"));
			json.accumulate("editUserName", editUserName);
			json.accumulate("editDept", rs.getString("EDIT_DEPT"));
			String editDeptName = deptlogic.getDeptNameStr(conn, rs.getString("EDIT_DEPT"));
			json.accumulate("editDeptName",editDeptName);
			json.accumulate("editPriv", rs.getString("EDIT_PRIV"));
			String editPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("EDIT_PRIV"));
			json.accumulate("editPrivName", editPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到新建权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getNewPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.NEW_USER,t1.NEW_DEPT,t1.NEW_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("newUser", rs.getString("NEW_USER"));
			String newUserName = acclogic.getUserNameStr(conn, rs.getString("NEW_USER"));
			json.accumulate("newUserName", newUserName);
			json.accumulate("newDept", rs.getString("NEW_DEPT"));
			String newDpetName = deptlogic.getDeptNameStr(conn, rs.getString("NEW_DEPT"));
			json.accumulate("newDeptName",newDpetName);
			json.accumulate("newPriv", rs.getString("NEW_PRIV"));
			String newPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("NEW_PRIV"));
			json.accumulate("newPrivName", newPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到下载/打印权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getDownPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.DOWN_USER,t1.DOWN_DEPT,t1.DOWN_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("downUser", rs.getString("DOWN_USER"));
			String downUserName = acclogic.getUserNameStr(conn, rs.getString("DOWN_USER"));
			json.accumulate("downUserName", downUserName);
			json.accumulate("downDept", rs.getString("DOWN_DEPT"));
			String downDpetName = deptlogic.getDeptNameStr(conn, rs.getString("DOWN_DEPT"));
			json.accumulate("downDeptName",downDpetName);
			json.accumulate("downPriv", rs.getString("DOWN_PRIV"));
			String downPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("DOWN_PRIV"));
			json.accumulate("downPrivName", downPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到删除权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getDelPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.DEL_USER,t1.DEL_DEPT,t1.DEL_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("delUser", rs.getString("DEL_USER"));
			String delUserName = acclogic.getUserNameStr(conn, rs.getString("DEL_USER"));
			json.accumulate("delUserName", delUserName);
			json.accumulate("delDept", rs.getString("DEL_DEPT"));
			String delDeptName = deptlogic.getDeptNameStr(conn, rs.getString("DEL_DEPT"));
			json.accumulate("delDeptName",delDeptName);
			json.accumulate("delPriv", rs.getString("DEL_PRIV"));
			String delPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("DEL_PRIV"));
			json.accumulate("delPrivName", delPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到评论权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getCommPriv(Connection conn,String orgId,String folderId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.FOLDER_ID,t1.COMM_USER,t1.COMM_DEPT,t1.COMM_PRIV FROM FOLDER t1 WHERE t1.FOLDER_ID = ? AND t1.ORG_ID = ? AND t1.IS_PUBLIC = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2, orgId);
		ps.setString(3, isPublic);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
			json.accumulate("commUser", rs.getString("COMM_USER"));
			String commUserName = acclogic.getUserNameStr(conn, rs.getString("COMM_USER"));
			json.accumulate("commUserName", commUserName);
			json.accumulate("commDept", rs.getString("COMM_DEPT"));
			String commDeptName = deptlogic.getDeptNameStr(conn, rs.getString("COMM_DEPT"));
			json.accumulate("commDeptName",commDeptName);
			json.accumulate("commPriv", rs.getString("COMM_PRIV"));
			String commPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("COMM_PRIV"));
			json.accumulate("commPrivName", commPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 修改权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id 
	 * @param folderId 文件夹Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public int updatePriv(Connection conn,String User,String Dept,String Priv,String folderId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			sql = "UPDATE FOLDER SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			sql = "UPDATE FOLDER SET EDIT_USER = ?, EDIT_DEPT = ?,EDIT_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("3")){
			sql = "UPDATE FOLDER SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("4")){
			sql = "UPDATE FOLDER SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("5")){
			sql = "UPDATE FOLDER SET DEL_USER = ?, DEL_DEPT = ?,DEL_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("6")){
			sql = "UPDATE FOLDER SET COMM_USER = ?, COMM_DEPT = ?,COMM_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}
		updateChildPriv(conn, sql, User, Dept, privId, folderId, orgId);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, folderId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	public void updateChildPriv(Connection conn,String sql,String User,String Dept,String Priv,String folderId,String orgId)throws Exception{
		if (isParent(conn, folderId)){
			String queryStr = "SELECT FOLDER_ID FROM FOLDER WHERE FOLDER_PID = ? AND ORG_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, folderId);
			ps.setString(2, orgId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ps = conn.prepareStatement(sql);
				ps.setString(1, User);
				ps.setString(2, Dept);
				ps.setString(3, Priv);
				ps.setString(4, rs.getString("FOLDER_ID"));
				ps.setString(5, orgId);
				ps.executeUpdate();
				updateChildPriv(conn, sql, User, Dept, Priv, rs.getString("FOLDER_ID"), orgId);
			}
			ps.close();
			rs.close();
		}
	}
	/**
	 * 批量添加权限
	 * Time:2015-4-21
	 * Author : Yzz
	 * @param conn
	 * @param User
	 * @param Dept
	 * @param Priv
	 * @param folderId
	 * @param orgId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int addPriv(Connection conn,String User,String Dept,String Priv,String folderId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			JSONObject json = JSONObject.fromObject(getAccessPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("accessDept"));
			Priv = checkJson(Priv,json.getString("accessPriv"));
			User = checkJson(User,json.getString("accessUser"));
			sql = "UPDATE FOLDER SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			JSONObject json = JSONObject.fromObject(getEditPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("editDept"));
			Priv = checkJson(Priv,json.getString("editPriv"));
			User = checkJson(User,json.getString("editUser"));
			sql = "UPDATE FOLDER SET EDIT_USER = ?, EDIT_DEPT = ?,EDIT_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("3")){
			JSONObject json = JSONObject.fromObject(getNewPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("newDept"));
			Priv = checkJson(Priv,json.getString("newPriv"));
			User = checkJson(User,json.getString("newUser"));
			sql = "UPDATE FOLDER SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("4")){
			JSONObject json = JSONObject.fromObject(getDownPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("downDept"));
			Priv = checkJson(Priv,json.getString("downPriv"));
			User = checkJson(User,json.getString("downUser"));
			sql = "UPDATE FOLDER SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("5")){
			JSONObject json = JSONObject.fromObject(getDelPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("delDept"));
			Priv = checkJson(Priv,json.getString("delPriv"));
			User = checkJson(User,json.getString("delUser"));
			sql = "UPDATE FOLDER SET DEL_USER = ?, DEL_DEPT = ?,DEL_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("6")){
			JSONObject json = JSONObject.fromObject(getCommPriv(conn, orgId, folderId,"1"));
			Dept = checkJson(Dept,json.getString("commDept"));
			Priv = checkJson(Priv,json.getString("commPriv"));
			User = checkJson(User,json.getString("commUser"));
			sql = "UPDATE FOLDER SET COMM_USER = ?, COMM_DEPT = ?,COMM_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, folderId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 检查两个String中是否有相同的数据
	 * Time:2015-4-21
	 * Author : Yzz
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
	 * 批量删除权限
	 * Time:2015-4-21
	 * Author : Yzz
	 * @param conn
	 * @param User
	 * @param Dept
	 * @param Priv
	 * @param folderId
	 * @param orgId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int delPriv(Connection conn,String User,String Dept,String Priv,String folderId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			JSONObject json = JSONObject.fromObject(getAccessPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("accessDept"));
			Priv = checkJson2(Priv,json.getString("accessPriv"));
			User = checkJson2(User,json.getString("accessUser"));
			sql = "UPDATE FOLDER SET ACCESS_USER = ?, ACCESS_DEPT = ?,ACCESS_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			JSONObject json = JSONObject.fromObject(getEditPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("editDept"));
			Priv = checkJson2(Priv,json.getString("editPriv"));
			User = checkJson2(User,json.getString("editUser"));
			sql = "UPDATE FOLDER SET EDIT_USER = ?, EDIT_DEPT = ?,EDIT_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("3")){
			JSONObject json = JSONObject.fromObject(getNewPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("newDept"));
			Priv = checkJson2(Priv,json.getString("newPriv"));
			User = checkJson2(User,json.getString("newUser"));
			sql = "UPDATE FOLDER SET NEW_USER = ?, NEW_DEPT = ?,NEW_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("4")){
			JSONObject json = JSONObject.fromObject(getDownPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("downDept"));
			Priv = checkJson2(Priv,json.getString("downPriv"));
			User = checkJson2(User,json.getString("downUser"));
			sql = "UPDATE FOLDER SET DOWN_USER = ?, DOWN_DEPT = ?,DOWN_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("5")){
			JSONObject json = JSONObject.fromObject(getDelPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("delDept"));
			Priv = checkJson2(Priv,json.getString("delPriv"));
			User = checkJson2(User,json.getString("delUser"));
			sql = "UPDATE FOLDER SET DEL_USER = ?, DEL_DEPT = ?,DEL_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("6")){
			JSONObject json = JSONObject.fromObject(getCommPriv(conn, orgId, folderId,"1"));
			Dept = checkJson2(Dept,json.getString("commDept"));
			Priv = checkJson2(Priv,json.getString("commPriv"));
			User = checkJson2(User,json.getString("commUser"));
			sql = "UPDATE FOLDER SET COMM_USER = ?, COMM_DEPT = ?,COMM_PRIV = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, folderId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 检查两个String中是否有相同的数据
	 * Time:2015-4-21
	 * Author : Yzz
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
	 * 根据权限得到文件列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param accountId 用户Id
	 * @param isPublic 是否公共的
	 * @return String类型的json数据
	 * @throws Exception
	 */
	public String getFolderListByPriv(Connection conn,String orgId,String accountId,String isPublic)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType=DbPoolConnection.getInstance().getDbType();
	if(dbType.equals("mysql"))
	{
		 if(isPublic.equals("1")){
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0' AND "
						+ " ( LOCATE('"+accountId+"',CONCAT(',',t1.ACCESS_USER,',')) > 0 "
						+ " OR LOCATE('"+deptId+"',CONCAT(',',t1.ACCESS_DEPT,',')) > 0 "
						+ " OR LOCATE('"+privId+"',CONCAT(',',t1.ACCESS_PRIV,',')) > 0 "
						+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll') ";
			}else{
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0'  AND t1.ACCOUNT_ID = '"+accountId+"'";
			}
	}else if(dbType.equals("sqlserver"))
	{
		if(isPublic.equals("1")){
			queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
					+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0' AND "
					+ " ( CHARINDEX('"+accountId+"',','+t1.ACCESS_USER+',') > 0 "
					+ " OR CHARINDEX('"+deptId+"',','+t1.ACCESS_DEPT+',') > 0"
					+ " OR CHARINDEX('"+privId+"',','+t1.ACCESS_PRIV+',') > 0 "
					+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll') ";
		}else{
			queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
					+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0'  AND t1.ACCOUNT_ID = '"+accountId+"'";
		}
	}else if(dbType.equals("oracle"))
	{
		 if(isPublic.equals("1")){
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0' AND "
						+ " ( INSTR(CONCAT(',',t1.ACCESS_USER)||',','"+accountId+"') > 0 "
						+ " OR INSTR(CONCAT(',',t1.ACCESS_DEPT)||',','"+deptId+"') > 0 "
						+ " OR INSTR(CONCAT(',',t1.ACCESS_PRIV)||',','"+privId+"') > 0 "
						+ " OR TO_CHAR(ACCESS_USER) = 'userAll' OR TO_CHAR(ACCESS_DEPT) = 'deptAll' OR TO_CHAR(ACCESS_PRIV) = 'privAll') ";
			}else{
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = '0'  AND t1.ACCOUNT_ID = '"+accountId+"'";
			}
	}
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, orgId);
			ps.setString(2, isPublic);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				
				json.accumulate("folderNo", rs.getString("FOLDER_NO"));
				json.accumulate("id", rs.getString("FOLDER_ID"));
				json.accumulate("name", rs.getString("FOLDER_NAME"));
				json.accumulate("pId", rs.getString("FOLDER_PID"));
				if(isParent(conn, rs.getString("FOLDER_ID"))){
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
					json.accumulate("isParent", "true");
					
				}else{
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder_active.gif");
					json.accumulate("isParent", "false");
				}
				
				jsonArr.add(json);
			}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 添加文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 文件Id
	 * @param fileNo 文件排序号
	 * @param fileName 文件名
	 * @param folderId 文件夹Id
	 * @param fileContent 文件内容
	 * @param attachId 附件Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int addFile(Connection conn,String fileId,String fileNo,String fileName,String folderId,String fileContent, String attachId,String orgId)throws Exception{
		String sql = "INSERT INTO FOLDER_FILE(FILE_ID,FILE_NO,FOLDER_ID,FILE_NAME,FILE_CONTENT,ATTACH_ID,ORG_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, fileId);
		ps.setString(2, fileNo);
		ps.setString(3, folderId);
		int j = 2;
		String name = fileName;
		String typeStr = SysFileTool.getFileExtName(name);
		if(!typeStr.equals("")){
			String nameStr = name.substring(0,name.indexOf(typeStr)-1);
			while(!checkName(conn, name,folderId, "2")){
				name = name.substring(0,name.indexOf(typeStr)-1);
				name = nameStr + "(" + j + ")."+typeStr;
				j++;
			}
			fileName = name;
		}else{
			while(!checkName(conn, name,folderId, "1")){
				name = fileName + "(" + j + ")";
				j++;
			}
			fileName = name;
		}
		ps.setString(4, fileName);
		ps.setString(5, fileContent);
		ps.setString(6, attachId);
		ps.setString(7, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据权限得到文件夹及文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param orgId 企业Id
	 * @param accountId 用户Id
	 * @param isPublic 是否公共的
	 * @return String类型的受影响的行数
	 * @throws Exception
	 */
	public String getFolderFileListByPriv(Connection conn,String folderId,String orgId,String accountId,String isPublic)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType = DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql"))
		{
			if(isPublic.equals("1")){
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND "
						+ " ( LOCATE('"+accountId+"',CONCAT(',',t1.ACCESS_USER,',')) > 0 "
						+ " OR LOCATE('"+deptId+"',CONCAT(',',t1.ACCESS_DEPT,',')) > 0 "
						+ " OR LOCATE('"+privId+"',CONCAT(',',t1.ACCESS_PRIV,',')) > 0 "
						+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' ) AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
			}else{
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
				+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
			}
		}else if(dbType.equals("sqlserver"))
		{
			if(isPublic.equals("1")){
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND "
						+ " ( CHARINDEX('"+accountId+"',','+t1.ACCESS_USER+',') > 0 "
						+ " CHARINDEX('"+deptId+"',','+t1.ACCESS_DEPT+',') > 0"
						+ " CHARINDEX('"+privId+"',','+t1.ACCESS_PRIV+',') > 0 "
						+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ORDER BY t1.FOLDER_NO ASC ";
			}else{
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
				+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
			}
		}else if(dbType.equals("oracle"))
		{
			if(isPublic.equals("1")){
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
						+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND "
						+ " ( INSTR(CONCAT(',',t1.ACCESS_USER)||',','"+accountId+"') > 0 "
						+ " OR INSTR(CONCAT(',',t1.ACCESS_DEPT)||',','"+deptId+"') > 0 "
						+ " OR INSTR(CONCAT(',',t1.ACCESS_PRIV)||',','"+privId+"') > 0 "
						+ " OR TO_CHAR(ACCESS_USER) = 'userAll' OR TO_CHAR(ACCESS_DEPT) = 'deptAll' OR TO_CHAR(ACCESS_PRIV) = 'privAll' ) AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
			}else{
				queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
				+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
			}
		}
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, orgId);
			ps.setString(2, isPublic);
			ps.setString(3, folderId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				
				json.accumulate("No", rs.getString("FOLDER_NO"));
				json.accumulate("id", rs.getString("FOLDER_ID"));
				json.accumulate("name", rs.getString("FOLDER_NAME"));
				json.accumulate("pId", rs.getString("FOLDER_PID"));
				json.accumulate("isParent", "true");
				json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
				
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
		ResultSet fileRs =null;
		String sql = "SELECT FILE_NO,FILE_ID,FOLDER_ID,FILE_NAME FROM FOLDER_FILE WHERE FOLDER_ID = ? AND ORG_ID = ?  ";
		PreparedStatement filePs = conn.prepareStatement(sql);
		filePs.setString(1, folderId);
		filePs.setString(2, orgId);
		fileRs = filePs.executeQuery();
		while(fileRs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("id", fileRs.getString("FILE_ID"));
			json.accumulate("name", fileRs.getString("FILE_NAME"));
			json.accumulate("pId", fileRs.getString("FOLDER_ID"));
			json.accumulate("isParent", "false");
			
			jsonArr.add(json);
		}
		filePs.close();
		fileRs.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据权限得到文件夹及有附件的文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param orgId 企业Id
	 * @param accountId 用户Id
	 * @param isPublic 是否公共的
	 * @return String类型的受影响的行数
	 * @throws Exception
	 */
	public String getFolderFileListByPrivInAttach(Connection conn,String folderId,String orgId,String accountId,String isPublic)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbms = SysProps.getString(SysPropKey.DBCONN_DBMS);
		if(dbms.equals("sqlserver")){
			queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
					+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND "
					+ " ( CHARINDEX('"+accountId+"',','+t1.ACCESS_USER+',') > 0 "
					+ " CHARINDEX('"+deptId+"',','+t1.ACCESS_DEPT+',') > 0"
					+ " CHARINDEX('"+privId+"',','+t1.ACCESS_PRIV+',') > 0 "
					+ "OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ORDER BY t1.FOLDER_NO ASC ";
		}else if(isPublic.equals("1")){
			queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
					+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND "
					+ " ( LOCATE('"+accountId+"',CONCAT(',',t1.ACCESS_USER,',')) > 0 "
					+ " OR LOCATE('"+deptId+"',CONCAT(',',t1.ACCESS_DEPT,',')) > 0 "
					+ " OR LOCATE('"+privId+"',CONCAT(',',t1.ACCESS_PRIV,',')) > 0 "
					+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' ) AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
		}else{
			queryStr="SELECT t1.FOLDER_NO,t1.FOLDER_ID,t1.FOLDER_NAME,t1.FOLDER_PID "
			+ "FROM FOLDER t1 WHERE t1.ORG_ID = ? AND t1.IS_PUBLIC = ? AND t1.FOLDER_PID = ? ORDER BY t1.FOLDER_NO ASC ";
		}
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, orgId);
			ps.setString(2, isPublic);
			ps.setString(3, folderId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				
				json.accumulate("No", rs.getString("FOLDER_NO"));
				json.accumulate("id", rs.getString("FOLDER_ID"));
				json.accumulate("name", rs.getString("FOLDER_NAME"));
				json.accumulate("pId", rs.getString("FOLDER_PID"));
				json.accumulate("isParent", "true");
				json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
				
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
		ResultSet fileRs =null;
		String sql = "SELECT t1.ATTACHMENT_ID,t1.ATTACHMENT_NAME,t1.PATH FROM attachment t1 LEFT JOIN folder_file t2 ON LOCATE(t1.ATTACHMENT_ID,CONCAT(',',t2.ATTACH_ID,',')) > 0 WHERE t2.FOLDER_ID = ? AND t2.ORG_ID = ?  AND t2.ATTACH_ID !=''";
		PreparedStatement filePs = conn.prepareStatement(sql);
		filePs.setString(1, folderId);
		filePs.setString(2, orgId);
		fileRs = filePs.executeQuery();
		while(fileRs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("id", fileRs.getString("ATTACHMENT_ID"));
			String name = fileRs.getString("ATTACHMENT_NAME");
			name = name.substring(18,name.length());
			json.accumulate("name", name);
			json.accumulate("path", fileRs.getString("PATH"));
			json.accumulate("isParent", "false");
			
			jsonArr.add(json);
		}
		filePs.close();
		fileRs.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据Id得到文件详细内容
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param fileId 文件Id
	 * @return String类型的Json数据
	 * @throws Exception
	 */
	public String getFileById(Connection conn,String orgId,String fileId,String accountId,String isPublic)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr = "";
		String folderId = getFolderIdByFileId(conn, fileId);
		if(isPublic.equals("1")){
			if(checkUserFile2(conn, folderId, accountId)){
				queryStr="SELECT t1.FILE_ID,t1.FILE_NO,t1.FOLDER_ID,t1.FILE_NAME,t1.FILE_CONTENT,t1.ATTACH_ID FROM FOLDER_FILE t1 WHERE t1.FILE_ID = ? AND t1.ORG_ID = ? ";
			}else{
				queryStr="SELECT t1.FILE_ID,t1.FILE_NO,t1.FOLDER_ID,t1.FILE_NAME,t1.FILE_CONTENT,t1.ATTACH_ID FROM FOLDER_FILE t1 WHERE t1.FILE_ID = ? AND t1.ORG_ID = ? AND 1=2";
			}
		}else{
			if(checkUserFile(conn,folderId,accountId)){
				queryStr="SELECT t1.FILE_ID,t1.FILE_NO,t1.FOLDER_ID,t1.FILE_NAME,t1.FILE_CONTENT,t1.ATTACH_ID FROM FOLDER_FILE t1 WHERE t1.FILE_ID = ? AND t1.ORG_ID = ? ";
			}else{
				queryStr="SELECT t1.FILE_ID,t1.FILE_NO,t1.FOLDER_ID,t1.FILE_NAME,t1.FILE_CONTENT,t1.ATTACH_ID FROM FOLDER_FILE t1 WHERE t1.FILE_ID = ? AND t1.ORG_ID = ? AND 1=2";
			}
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, fileId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("fileNo", rs.getString("FILE_NO"));
			json.accumulate("fileId", rs.getString("FILE_ID"));
			json.accumulate("fileName", rs.getString("FILE_NAME"));
			json.accumulate("fileContent", rs.getString("FILE_CONTENT"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("folderId", rs.getString("FOLDER_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据Id得到文件夹是否属于当前用户
	 * Time:2015-9-8
	 * Author:Yzz
	 * @param conn
	 * @param folderId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkUserFile2(Connection conn,String folderId,String accountId)throws Exception{
		boolean flag = false;
		String dbType=DbPoolConnection.getInstance().getDbType();
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String queryStr="";
		if(dbType.equals("mysql"))
		{
		queryStr= "SELECT FOLDER_PID,ACCOUNT_ID FROM FOLDER WHERE FOLDER_ID = ?  AND"
				+ " ( LOCATE(?,CONCAT(',',ACCESS_USER,',')) > 0 "
					+ " OR LOCATE(?,CONCAT(',',ACCESS_DEPT,',')) > 0 "
					+ " OR LOCATE(?,CONCAT(',',ACCESS_PRIV,',')) > 0 "
					+ " OR ACCESS_USER = 'userAll' OR ACCESS_DEPT = 'deptAll' OR ACCESS_PRIV = 'privAll' )";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr= "SELECT FOLDER_PID,ACCOUNT_ID FROM FOLDER WHERE FOLDER_ID = ?  AND"
					+ " ( INSTR(CONCAT(',',ACCESS_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',ACCESS_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',ACCESS_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(ACCESS_USER) = 'userAll' OR TO_CHAR(ACCESS_DEPT) = 'deptAll' OR TO_CHAR(ACCESS_PRIV) = 'privAll' )";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, folderId);
		ps.setString(2,accountId);
		ps.setString(3, deptId);
		ps.setString(4, privId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}else{
			flag = false;
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * 根据Id得到文件夹是否属于当前用户
	 * Time:2015-9-8
	 * Author:Yzz
	 * @param conn
	 * @param folderId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkUserFile(Connection conn,String folderId,String accountId)throws Exception{
		boolean flag = false;
		String sql = "SELECT FOLDER_PID,ACCOUNT_ID FROM FOLDER WHERE FOLDER_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if(rs.getString("FOLDER_PID").equals("0")){
				if(rs.getString("ACCOUNT_ID").equals(accountId)){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				flag = checkUserFile(conn, rs.getString("FOLDER_PID"),accountId);
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * 根据Id得到父级文件夹Id
	 * Time:2015-9-8
	 * Author:Yzz
	 * @param conn
	 * @param folderId
	 * @return
	 * @throws Exception
	 */
	public String getFolderIdByFileId(Connection conn,String fileId)throws Exception{
		String sql = "SELECT FOLDER_ID FROM FOLDER_FILE WHERE FILE_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, fileId);
		String returnData = "";
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = rs.getString("FOLDER_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	/**
	 * 修改文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 文件Id
	 * @param fileNo 文件排序号
	 * @param fileName 文件名
	 * @param folderId 文件夹Id
	 * @param fileContent 文件内容
	 * @param attachId 附件Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int updateFile(Connection conn,String fileId,String fileNo,String fileName,String folderId,String fileContent, String attachId,String orgId)throws Exception{
		String sql = "UPDATE FOLDER_FILE SET FILE_NO = ?,FOLDER_ID = ?,FILE_NAME = ?,FILE_CONTENT = ?,ATTACH_ID = ? WHERE FILE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, fileNo);
		ps.setString(2, folderId);
		ps.setString(3, fileName);
		ps.setString(4, fileContent);
		ps.setString(5, attachId);
		ps.setString(6, fileId);
		ps.setString(7, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 删除文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param orgId 企业Id
	 * @param fileId 文件Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int deleteFile(Connection conn,String orgId,String fileId)throws Exception{
		String sql = "DELETE FROM FOLDER_FILE  WHERE FILE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, fileId);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 检验名称是否存在
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param name 名称
	 * @param pId 上级文件夹
	 * @param isFolder 是否是文件夹
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkName(Connection conn,String name,String pId, String isFolder)throws Exception{
		boolean flag=true;
		ResultSet rs = null;
		String queryStr = null;
		if(isFolder.equals("1")){
			queryStr = "SELECT FOLDER_NAME FROM FOLDER WHERE FOLDER_NAME = ? AND FOLDER_PID = ?";
		}else{
			queryStr = "SELECT FILE_NAME FROM FOLDER_FILE WHERE FILE_NAME = ? AND FOLDER_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, name);
		ps.setString(2, pId);
		rs = ps.executeQuery();
		if(rs.next()){
			flag= false;
		}
		rs.close();
		ps.close();
		return flag;
	}
	
	/**
	 * 剪切文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 文件Id
	 * @param folderId 文件夹Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int sheraFile(Connection conn,String fileId,String folderId,String orgId)throws Exception{
		String sql = "UPDATE FOLDER_FILE SET FOLDER_ID = ? WHERE FILE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ps.setString(2, fileId);
		ps.setString(3, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 复制文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 原文件Id
	 * @param newId 新文件Id
	 * @param folderId 文件夹Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int copyFile(Connection conn,String fileId,String newId,String folderId,String orgId)throws Exception{
		String sql = "INSERT INTO FOLDER_FILE(FILE_ID,FILE_NO,FOLDER_ID,FILE_NAME,"
				+ "FILE_CONTENT,ATTACH_ID,ORG_ID) SELECT ?,FILE_NO,?,"
				+ "FILE_NAME,FILE_CONTENT,ATTACH_ID,ORG_ID FROM FOLDER_FILE WHERE FILE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, newId);
		ps.setString(2, folderId);
		ps.setString(3, fileId);
		ps.setString(4, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}

	/**
	 * 复制文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param newId 新文件夹Id
	 * @param folderId 原文件夹Id
	 * @param folderPid 上级Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int copyFolder(Connection conn,String folderId,String newId,String folderPid,String orgId)throws Exception{
		String sql = "INSERT INTO FOLDER(FOLDER_ID,FOLDER_NO,FOLDER_PID,FOLDER_NAME "
				+ ",NEW_USER ,NEW_DEPT ,NEW_PRIV"
				+ ",EDIT_USER ,EDIT_DEPT ,EDIT_PRIV"
				+ ",ACCESS_USER ,ACCESS_DEPT ,ACCESS_PRIV"
				+ ",DOWN_USER ,DOWN_DEPT ,DOWN_PRIV"
				+ ",DEL_USER ,DEL_DEPT ,DEL_PRIV"
				+ ",COMM_USER ,COMM_DEPT ,COMM_PRIV,IS_PUBLIC,ORG_ID ) "
				+ "SELECT ?,FOLDER_NO,?,FOLDER_NAME "
				+ ",NEW_USER ,NEW_DEPT ,NEW_PRIV"
				+ ",EDIT_USER ,EDIT_DEPT ,EDIT_PRIV"
				+ ",ACCESS_USER ,ACCESS_DEPT ,ACCESS_PRIV"
				+ ",DOWN_USER ,DOWN_DEPT ,DOWN_PRIV"
				+ ",DEL_USER ,DEL_DEPT ,DEL_PRIV"
				+ ",COMM_USER ,COMM_DEPT ,COMM_PRIV,IS_PUBLIC,ORG_ID FROM FOLDER "
				+ " WHERE FOLDER_ID = ? AND ORG_ID = ?  ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, newId);
		ps.setString(2, folderPid);
		ps.setString(3, folderId);
		ps.setString(4, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 剪切文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param folderPid 上级Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int sheraFolder(Connection conn,String folderId,String folderPid,String orgId)throws Exception{
		String sql = "UPDATE FOLDER SET FOLDER_PID = ? WHERE FOLDER_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderPid);
		ps.setString(2, folderId);
		ps.setString(3, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 创建个人文件柜
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param folderPid 上级Id
	 * @param folderNo 排序号
	 * @param folderName 文件夹名称
	 * @param isPublic 是否公共的
	 * @param accountId 用户Id
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int createFolder(Connection conn,String folderId,String folderPid,String folderNo,String folderName, String isPublic,String accountId,String orgId)throws Exception{
		if(checkPersonFolder(conn,folderName,accountId)){
			String sql = "INSERT INTO FOLDER(FOLDER_ID,FOLDER_NO,FOLDER_PID,FOLDER_NAME,IS_PUBLIC,ACCOUNT_ID,ORG_ID) VALUES(?, ?, ?, ?, ?, ?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, folderId);
			ps.setString(2, folderNo);
			ps.setString(3, folderPid);
			ps.setString(4, folderName);
			ps.setString(5, isPublic);
			ps.setString(6, accountId);
			ps.setString(7, orgId);
			int i =  ps.executeUpdate();
			ps.close();
			return i;
		}else{
			return 0;
		}
		
	}
	
	/**
	 * 检验个人文件柜是否已经创建
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param name 名称
	 * @param accountId 用户Id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkPersonFolder(Connection conn,String name,String accountId)throws Exception{
		boolean flag=true;
		ResultSet rs = null;
		String queryStr = null;
		queryStr = "SELECT FOLDER_NAME FROM FOLDER WHERE FOLDER_NAME = ? AND ACCOUNT_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, name);
		ps.setString(2, accountId);
		rs = ps.executeQuery();
		if(rs.next()){
			flag= false;
		}
		rs.close();
		ps.close();
		return flag;
	}
	
	/**
	 * 验证权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param folderId 文件夹Id
	 * @param accountId 用户Id
	 * @param orgId 企业Id
	 * @param privType 权限类型
	 * @return int类型的1和0用于判断
	 * @throws Exception
	 */
	public int checkPriv(Connection conn,String folderId,String accountId,String orgId,String privType)throws Exception{
		int flag=0;
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType=DbPoolConnection.getInstance().getDbType();
		if(privType.equals("2")){
			if(dbType.equals("mysql"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.EDIT_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.EDIT_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.EDIT_PRIV,',')) > 0 "
						+ " OR EDIT_USER = 'userAll' OR EDIT_DEPT = 'deptAll' OR EDIT_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("sqlserver"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.EDIT_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.EDIT_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.EDIT_PRIV+',') > 0 "
						+ " OR EDIT_USER = 'userAll' OR EDIT_DEPT = 'deptAll' OR EDIT_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( INSTR(CONCAT(',',t1.EDIT_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.EDIT_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.EDIT_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(EDIT_USER) = 'userAll' OR TO_CHAR(EDIT_DEPT) = 'deptAll' OR TO_CHAR(EDIT_PRIV) = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}
		}else if(privType.equals("3")){
			if(dbType.equals("mysql"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.NEW_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.NEW_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.NEW_PRIV,',')) > 0 "
						+ " OR NEW_USER = 'userAll' OR NEW_DEPT = 'deptAll' OR NEW_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("sqlserver"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.NEW_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.NEW_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.NEW_PRIV+',') > 0 "
						+ " OR NEW_USER = 'userAll' OR NEW_DEPT = 'deptAll' OR NEW_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( INSTR(CONCAT(',',t1.NEW_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.NEW_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.NEW_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(NEW_USER) = 'userAll' OR TO_CHAR(NEW_DEPT) = 'deptAll' OR TO_CHAR(NEW_PRIV) = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}
		}else if(privType.equals("4")){
			if(dbType.equals("mysql"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.DOWN_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DOWN_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DOWN_PRIV,',')) > 0 "
						+ " OR DOWN_USER = 'userAll' OR DOWN_DEPT = 'deptAll' OR DOWN_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("sqlserver"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.DOWN_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.DOWN_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.DOWN_PRIV+',') > 0 "
						+ " OR DOWN_USER = 'userAll' OR DOWN_DEPT = 'deptAll' OR DOWN_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( INSTR(CONCAT(',',t1.DOWN_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.DOWN_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.DOWN_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(DOWN_USER) = 'userAll' OR TO_CHAR(DOWN_DEPT) = 'deptAll' OR TO_CHAR(DOWN_PRIV) = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}
		}else if(privType.equals("5")){
			if(dbType.equals("mysql"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.DEL_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DEL_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.DEL_PRIV,',')) > 0 "
						+ " OR DEL_USER = 'userAll' OR DEL_DEPT = 'deptAll' OR DEL_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("sqlserver"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.DEL_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.DEL_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.DEL_PRIV+',') > 0 "
						+ " OR DEL_USER = 'userAll' OR DEL_DEPT = 'deptAll' OR DEL_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( INSTR(CONCAT(',',t1.DEL_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.DEL_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.DEL_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(DEL_USER) = 'userAll' OR TO_CHAR(DEL_DEPT) = 'deptAll' OR TO_CHAR(DEL_PRIV） = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}
		}else if(privType.equals("6")){
			if(dbType.equals("mysql"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( LOCATE(?,CONCAT(',',t1.COMM_USER,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.COMM_DEPT,',')) > 0 "
						+ " OR LOCATE(?,CONCAT(',',t1.COMM_PRIV,',')) > 0 "
						+ " OR COMM_USER = 'userAll' OR COMM_DEPT = 'deptAll' OR COMM_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("sqlserver"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( CHARINDEX(?,','+t1.COMM_USER+',') > 0 "
						+ " OR CHARINDEX(?,','+t1.COMM_DEPT+',') > 0"
						+ " OR CHARINDEX(?,','+t1.COMM_PRIV+',') > 0 "
						+ " OR COMM_USER = 'userAll' OR COMM_DEPT = 'deptAll' OR COMM_PRIV = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}else if(dbType.equals("oracle"))
			{
				queryStr="SELECT t1.FOLDER_ID FROM FOLDER t1 WHERE t1.ORG_ID = ? AND "
						+ " ( INSTR(CONCAT(',',t1.COMM_USER)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.COMM_DEPT)||',',?) > 0 "
						+ " OR INSTR(CONCAT(',',t1.COMM_PRIV)||',',?) > 0 "
						+ " OR TO_CHAR(COMM_USER) = 'userAll' OR TO_CHAR(COMM_DEPT) = 'deptAll' OR TO_CHAR(COMM_PRIV) = 'privAll' ) AND t1.FOLDER_ID = ? ";
			}
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, accountId);
		ps.setString(3, deptId);
		ps.setString(4, privId);
		ps.setString(5, folderId);
		rs=ps.executeQuery();
		if(rs.next()){
			flag= 1;
		}
		rs.close();
		ps.close();
		return flag;
	}
	
	/**
	 * 复制附件记录
	 * Time:2015-4-23
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param attachId 附件Id
	 * @param fileName 附件名
	 * @param srcFilePath 附件路径
	 * @throws Exception
	 */
	public String copyAttach(Connection conn,String attachId,String fileName,String srcFilePath)throws Exception{
		String newId = GuId.getGuid();
		String queryStr = "INSERT INTO ATTACHMENT(ATTACHMENT_ID,ATTACHMENT_NAME,UP_TIME,CREATE_ACCOUNT_ID"
				+ ",USE_COUNT,USE_USER,PATH,MODULES,PRIV,CONFIG_NO,ORG_ID) SELECT ?"
				+ ",?,UP_TIME,CREATE_ACCOUNT_ID"
				+ ",USE_COUNT,USE_USER,?,MODULES,PRIV,CONFIG_NO,ORG_ID FROM ATTACHMENT WHERE ATTACHMENT_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, newId);
		ps.setString(2, fileName);
		String folder = srcFilePath.substring(0,srcFilePath.lastIndexOf("\\")+1);
		String destFile =folder+fileName;
		ps.setString(3, destFile);
		ps.setString(4, attachId);
		ps.executeUpdate();
		ps.close();
		return newId;
	}
	
	/**
	 * 添加修订记录
	 * Time:2015-4-23
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 文件Id
	 * @param accountId 用户Id
	 * @param attachId 附件Id
	 * @param fileName 附件名称
	 * @param orgId 企业Id
	 * @return int类型受影响的行数
	 * @throws Exception
	 */
	public int addRecord(Connection conn,String fileId,String accountId,String attachId,String fileName,String orgId)throws Exception{
		String newId = "";
		if(attachId != null ){
			UpFileTool upFileTool = new UpFileTool();
			String srcFilePath = upFileTool.getAttachPath(attachId);
			newId = copyAttach(conn,attachId,fileName,srcFilePath);
		}
		String recordId = GuId.getGuid();
		int recordNo = getRecordNo(conn, fileId);
		String queryStr = "INSERT INTO FILE_RECORD(RECORD_ID,RECORD_NO,FILE_ID,ATTACH_ID,RECORD_TIME,ACCOUNT_ID,ORG_ID) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, recordId);
		ps.setInt(2, recordNo);
		ps.setString(3, fileId);
		ps.setString(4, newId);
		ps.setString(5, SysTool.getDateTimeStr(new Date()));
		ps.setString(6, accountId);
		ps.setString(7, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 得到版本号
	 * Time:2015-4-23
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param fileId 文件id
	 * @return int的类型判断依据
	 * @throws Exception
	 */
	public int getRecordNo(Connection conn,String fileId)throws Exception{
		int recordNo = 0;
		ResultSet rs = null;
		String queryStr = "SELECT RECORD_NO FROM FILE_RECORD WHERE FILE_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, fileId);
		rs = ps.executeQuery();
		if(rs.next()){
			recordNo = rs.getInt("RECORD_NO");
			recordNo++;
		}else{
			recordNo = 0;
		}
		rs.close();
		ps.close();
		return recordNo;
	}
	/**
	 * 复制附件
	 * Time:2015-4-23
	 * Author:Yzz
	 * @param attachId 附件Id
	 * @return String类型的附件名称
	 * @throws Exception
	 */
	public String createHistoryFileLogic(String attachId) throws Exception
	{
		UpFileTool upFileTool = new UpFileTool();
		String srcFilePath = upFileTool.getAttachPath(attachId);
		String extName = srcFilePath.substring(srcFilePath.lastIndexOf("."),srcFilePath.length());
		String fileName = upFileTool.getAllAttachNameById(attachId);
		String folder = srcFilePath.substring(0,srcFilePath.lastIndexOf("\\")+1);
		int j = 2;
		String name = fileName;
		String startName = name.substring(0,name.lastIndexOf(extName));
		while(checkFileName(folder,name)){
			name = startName + "(" + j + ")" + extName;
			j++;
		}
		fileName = name;
		String destFile =folder+fileName;
		SysFileTool.copyFile(srcFilePath, destFile);
		
		return fileName;
	}
	
	/**
	 * 验证附件名是否存在
	 * Time:2015-4-23
	 * Author:Yzz
	 * @param folder 文件夹名称
	 * @param fileName 文件名
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkFileName(String folder,String fileName)throws Exception{
		boolean flag = false;
		File files = new File(folder);
		String[] filelist = files.list();
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(folder + "\\" + filelist[i]);
			if (!readfile.isDirectory()) {
				if(readfile.getName().toString().equals(fileName)){
					flag = true;
					break;
				}else{
					flag = false;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 根据Id得到版本记录
	 * @param conn jdbc连接
	 * @param fileId 文件Id
	 * @param orgId 企业Id
	 * @return json数据
	 * @throws Exception
	 */
	public String getRecordListById(Connection conn,String fileId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		String queryStr = "SELECT RECORD_ID,RECORD_NO,FILE_ID,ATTACH_ID,"
				+ "RECORD_TIME,F.ACCOUNT_ID,(SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID = F.ACCOUNT_ID AND U.ORG_ID = F.ORG_ID ) AS USER_NAME,F.ORG_ID FROM FILE_RECORD F WHERE FILE_ID "
				+ "= ? AND ORG_ID = ? ORDER BY RECORD_NO ASC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, fileId);
		ps.setString(2, orgId);
		rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("recordId", rs.getString("RECORD_ID"));
			json.accumulate("recordNo", rs.getString("RECORD_NO"));
			json.accumulate("fileId", rs.getString("FILE_ID"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			json.accumulate("recordTime", rs.getString("RECORD_TIME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	public int checkFolderPid(Connection conn, String folderId)throws Exception{
		int returnData = 0;
		String sql = "SELECT FOLDER_PID FROM FOLDER WHERE FOLDER_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, folderId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if(rs.getString("FOLDER_PID").equals("0")){
				returnData = 1;
			}
		}
		return returnData;
	}
}
