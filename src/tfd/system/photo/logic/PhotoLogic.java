package tfd.system.photo.logic;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysFileTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.photo.data.Photo;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

public class PhotoLogic {
	/**
	 * 添加相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param photo
	 * @return
	 * @throws SQLException
	 */
	public int addPhotoLogic(Connection conn,Photo photo) throws SQLException{
		String queryStr="INSERT INTO PHOTO (PHOTO_ID,SORT_ID,PHOTO_NAME,PHOTO_PATH,CREATE_ACCOUNT_ID,CREATE_DATE,ORG_ID)VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photo.getPhotoId());
		ps.setInt(2, photo.getSortId());
		ps.setString(3, photo.getPhotoName());
		ps.setString(4, photo.getPhotoPath());
		ps.setString(5, photo.getCreateAccountId());
		ps.setString(6, photo.getCreateDate());
		ps.setString(7, photo.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 得到Json格式的相册数据
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getPhotoJsonLogic(Connection conn,Account account) throws SQLException{
		JSONArray jsonArr = new JSONArray();
		String accountId = account.getAccountId();
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT PHOTO_ID,PHOTO_NAME,PHOTO_COVER,PHOTO_PATH FROM PHOTO WHERE ORG_ID=?  AND "
				 	+ " ( LOCATE(?,CONCAT(',',READ_ACCOUNT_ID,',')) > 0 "
				 	+ " OR LOCATE(?,CONCAT(',',READ_DEPT_ID,',')) > 0 "
				 	+ " OR LOCATE(?,CONCAT(',',READ_USER_PRIV_ID,',')) > 0 OR CREATE_ACCOUNT_ID = ? "
				 	+ " OR READ_ACCOUNT_ID = 'userAll' OR READ_DEPT_ID = 'deptAll' OR READ_USER_PRIV_ID = 'privAll' )";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT PHOTO_ID,PHOTO_NAME,PHOTO_COVER,PHOTO_PATH FROM PHOTO WHERE ORG_ID=?  AND "
				 	+ " ( INSTR(CONCAT(',',READ_ACCOUNT_ID)||',',?) > 0 "
				 	+ " OR INSTR(CONCAT(',',READ_DEPT_ID)||',',?) > 0 "
				 	+ " OR INSTR(CONCAT(',',READ_USER_PRIV_ID)||',',?) > 0 OR CREATE_ACCOUNT_ID = ? "
				 	+ " OR TO_CHAR(READ_ACCOUNT_ID) = 'userAll' OR TO_CHAR(READ_DEPT_ID) = 'deptAll' OR TO_CHAR(READ_USER_PRIV_ID) = 'privAll' )";
		}
		
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, accountId);
		ps.setString(3, deptId);
		ps.setString(4, privId);
		ps.setString(5, accountId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("PHOTO_ID"));
			json.accumulate("pId", "0");
			json.accumulate("name", rs.getString("PHOTO_NAME"));
			json.accumulate("photoCover", rs.getString("PHOTO_COVER"));
			json.accumulate("photoPath", rs.getString("PHOTO_PATH"));
			json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
			json.accumulate("isParent", true);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 验证权限
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param account
	 * @param photoId
	 * @return
	 * @throws SQLException
	 */
	public String checkPrivLogic(Connection conn,Account account,String photoId) throws SQLException{
		String returnData = "";
		String accountId = account.getAccountId();
		String deptId = new UserInfoLogic().getDeptId(conn, accountId);
		String privId = new AccountLogic().getAccountByAccountId(conn, accountId).getUserPriv();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT PHOTO_ID,PHOTO_NAME,PHOTO_COVER,PHOTO_PATH FROM PHOTO WHERE ORG_ID=? AND PHOTO_ID = ?  AND "
				 	+ " ( LOCATE(?,CONCAT(',',MANAGE_ACCOUNT_ID,',')) > 0 "
				 	+ " OR LOCATE(?,CONCAT(',',MANAGE_DEPT_ID,',')) > 0 "
				 	+ " OR LOCATE(?,CONCAT(',',MANAGE_USER_PRIV_ID,',')) > 0 OR CREATE_ACCOUNT_ID = ? "
				 	+ " OR READ_ACCOUNT_ID = 'userAll' OR READ_DEPT_ID = 'deptAll' OR READ_USER_PRIV_ID = 'privAll' )";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT PHOTO_ID,PHOTO_NAME,PHOTO_COVER,PHOTO_PATH FROM PHOTO WHERE ORG_ID=? AND PHOTO_ID = ? AND "
				 	+ " ( INSTR(CONCAT(',',MANAGE_ACCOUNT_ID)||',',?) > 0 "
				 	+ " OR INSTR(CONCAT(',',MANAGE_DEPT_ID)||',',?) > 0 "
				 	+ " OR INSTR(CONCAT(',',MANAGE_USER_PRIV_ID)||',',?) > 0 OR CREATE_ACCOUNT_ID = ? "
				 	+ " OR TO_CHAR(MANAGE_ACCOUNT_ID) = 'userAll' OR TO_CHAR(MANAGE_DEPT_ID) = 'deptAll' OR TO_CHAR(MANAGE_USER_PRIV_ID) = 'privAll' )";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, photoId);
		ps.setString(3, accountId);
		ps.setString(4, deptId);
		ps.setString(5, privId);
		ps.setString(6, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			returnData = "1";
		}else{
			returnData = "0";
		}
		rs.close();
		ps.close();
		return returnData;
	}
	/**
	 * 得到全部相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String getAllPhotoAct(Connection conn,Account account) throws SQLException{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT PHOTO_ID,PHOTO_NAME,PHOTO_PATH,(SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID = P.CREATE_ACCOUNT_ID AND U.ORG_ID = P.ORG_ID ) AS USER_NAME,CREATE_ACCOUNT_ID,CREATE_DATE FROM PHOTO P WHERE P.ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("photoId", rs.getString("PHOTO_ID"));
			json.accumulate("photoName", rs.getString("PHOTO_NAME"));
			json.accumulate("photoPath", rs.getString("PHOTO_PATH"));
			json.accumulate("createUserName", rs.getString("USER_NAME"));
			json.accumulate("createAccountId", rs.getString("CREATE_ACCOUNT_ID"));
			json.accumulate("createDate", rs.getString("CREATE_DATE"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 得到全部文件夹和文件
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param account
	 * @param path
	 * @return
	 * @throws SQLException
	 */
	public String getPhotoFileListLogic(Connection conn,Account account,String path) throws SQLException{
		JSONArray jsonArr = new JSONArray();
		try {
			File f = new File(path);
			String[] filelist = f.list();
			File[] files=f.listFiles();
			for(int i=0;i<filelist.length;i++){
			File readfile = new File(path + "\\" + filelist[i]);
					JSONObject json = new JSONObject();
					json.accumulate("pId",path);
					json.accumulate("path",files[i].toString() );
					if (readfile.isDirectory()) {
						json.accumulate("pathtype", "folder");
						json.accumulate("name",filelist[i]); 
					}else{
						String refile = readfile.toString().replaceAll("\\\\", "/");
						JSONObject infoJson = getPhotoInfoById(conn,refile,account.getAccountId() );
						if(infoJson.has("photoInfoId")){
							json.accumulate("id",infoJson.getString("photoInfoId"));
							json.accumulate("time",infoJson.getString("createTime"));
							json.accumulate("accountId",infoJson.getString("createAccountId"));
							json.accumulate("good",infoJson.getString("good"));
							json.accumulate("goodNum",infoJson.getString("goodNum"));
						}else{
							json.accumulate("id","");
							json.accumulate("time","暂无记录");
							json.accumulate("accountId","暂无记录");
							json.accumulate("good","false");
							json.accumulate("goodNum","暂无记录");
						}
						String name = readfile.getName().toString();
						json.accumulate("pathtype", "file");
						String typeStr = SysFileTool.getFileExtName(readfile.getName());
						json.accumulate("type",typeStr);
						name = name.substring(0,name.indexOf(typeStr)-1);
						json.accumulate("name",name);
					}
					jsonArr.add(json);
			}
		} catch (Exception e) {
		}
		return jsonArr.toString();
	}
	/**
	 * 删除相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param photoId
	 * @return
	 * @throws Exception
	 */
	public int delPhotoLogic(Connection conn,String photoId) throws Exception{
		String queryStr="DELETE FROM PHOTO WHERE PHOTO_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photoId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 修改相册信息
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param sortId
	 * @param photoName
	 * @param photoPath
	 * @param photoId
	 * @return
	 * @throws SQLException
	 */
	public int updatePhotoLogic(Connection conn,int sortId,String photoName,String photoPath,String photoId) throws SQLException{
		String queryStr="UPDATE PHOTO SET SORT_ID = ?,PHOTO_NAME = ?,PHOTO_PATH = ? WHERE PHOTO_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setInt(1, sortId);
		ps.setString(2, photoName);
		ps.setString(3, photoPath);
		ps.setString(4, photoId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id得到相册的详细信息
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param conn
	 * @param photoId
	 * @return
	 * @throws Exception
	 */
	public String getPhotoByIdLogic(Connection conn,String photoId) throws Exception{
		JSONObject json = new JSONObject();
		String queryStr="SELECT SORT_ID,PHOTO_PATH,PHOTO_NAME FROM PHOTO WHERE PHOTO_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photoId);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			json.accumulate("photoId", photoId);
			json.accumulate("sortId", rs.getString("SORT_ID"));
			json.accumulate("photoName", rs.getString("PHOTO_NAME"));
			json.accumulate("photoPath", rs.getString("PHOTO_PATH"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 得到访问权限
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param photoId
	 * @return
	 * @throws Exception
	 */
	public String getReadPrivLogic(Connection conn,String orgId,String photoId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.PHOTO_ID,t1.READ_ACCOUNT_ID,t1.READ_DEPT_ID,t1.READ_USER_PRIV_ID FROM PHOTO t1 WHERE t1.PHOTO_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photoId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("photoId", rs.getString("PHOTO_ID"));
			json.accumulate("readUser", rs.getString("READ_ACCOUNT_ID"));
			String accessUserName = acclogic.getUserNameStr(conn, rs.getString("READ_ACCOUNT_ID"));
			json.accumulate("readUserName", accessUserName);
			json.accumulate("readDept", rs.getString("READ_DEPT_ID"));
			String accessDpetName = deptlogic.getDeptNameStr(conn, rs.getString("READ_DEPT_ID"));
			json.accumulate("readDpetName",accessDpetName);
			json.accumulate("readPriv", rs.getString("READ_USER_PRIV_ID"));
			String accessPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("READ_USER_PRIV_ID"));
			json.accumulate("readPrivName", accessPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 得到管理权限
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param photoId
	 * @return
	 * @throws Exception
	 */
	public String getManagePrivLogic(Connection conn,String orgId,String photoId)throws Exception{
		JSONObject json = new JSONObject();
		ResultSet rs =null;
		String queryStr="SELECT t1.PHOTO_ID,t1.MANAGE_ACCOUNT_ID,t1.MANAGE_DEPT_ID,t1.MANAGE_USER_PRIV_ID FROM PHOTO t1 WHERE t1.PHOTO_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photoId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next())
		{
			json.accumulate("photoId", rs.getString("PHOTO_ID"));
			json.accumulate("manageUser", rs.getString("MANAGE_ACCOUNT_ID"));
			String editUserName = acclogic.getUserNameStr(conn, rs.getString("MANAGE_ACCOUNT_ID"));
			json.accumulate("manageUserName", editUserName);
			json.accumulate("manageDept", rs.getString("MANAGE_DEPT_ID"));
			String editDeptName = deptlogic.getDeptNameStr(conn, rs.getString("MANAGE_DEPT_ID"));
			json.accumulate("manageDpetName",editDeptName);
			json.accumulate("managePriv", rs.getString("MANAGE_USER_PRIV_ID"));
			String editPrivName=ulogic.getUserPrivNameStr(conn, rs.getString("MANAGE_USER_PRIV_ID"));
			json.accumulate("managePrivName", editPrivName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 修改权限
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param User
	 * @param Dept
	 * @param Priv
	 * @param photoId
	 * @param orgId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int updatePhotoPrivLogic(Connection conn,String User,String Dept,String Priv,String photoId,String orgId,String privId)throws Exception{
		String sql = "";
		if(privId.equals("1")){
			sql = "UPDATE PHOTO SET READ_ACCOUNT_ID = ?, READ_DEPT_ID = ?,READ_USER_PRIV_ID = ? WHERE PHOTO_ID = ? AND ORG_ID = ? ";
		}else if(privId.equals("2")){
			sql = "UPDATE PHOTO SET MANAGE_ACCOUNT_ID = ?, MANAGE_DEPT_ID = ?,MANAGE_USER_PRIV_ID = ? WHERE PHOTO_ID = ? AND ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, User);
		ps.setString(2, Dept);
		ps.setString(3, Priv);
		ps.setString(4, photoId);
		ps.setString(5, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 设置相册封面
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param path
	 * @param photoId
	 * @return
	 * @throws SQLException
	 */
	public int setPhotoCoverLogic(Connection conn,String path,String photoId) throws SQLException{
		String queryStr="UPDATE PHOTO SET PHOTO_COVER = ? WHERE PHOTO_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, path);
		ps.setString(2, photoId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 添加照片信息
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param photoName
	 * @param accountId
	 * @param photoPath
	 * @param orgId
	 * @throws Exception
	 */
	public void addPhotoInfoLogic(Connection conn,String photoName,String accountId,String photoPath,String orgId)throws Exception{
		String queryStr = "";
		String dbType = DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("oracle")){
			queryStr = "INSERT INTO PHOTO_INFO(PHOTO_INFO_ID,PHOTO_INFO_NAME,CREATE_TIME,CREATE_ACCOUNT_ID,GOOD,PHOTO_PATH,ORG_ID) VALUES(?,?,sysdate,?,?,?,?)";
		}else if(dbType.equals("mysql")){
			queryStr = "INSERT INTO PHOTO_INFO(PHOTO_INFO_ID,PHOTO_INFO_NAME,CREATE_TIME,CREATE_ACCOUNT_ID,GOOD,PHOTO_PATH,ORG_ID) VALUES(?,?,sysdate(),?,?,?,?)";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, photoName);
		ps.setString(3, accountId);
		ps.setString(4, "");
		ps.setString(5, photoPath);
		ps.setString(6, orgId);
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * 点赞与取消赞
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param photoInfoId
	 * @return
	 * @throws SQLException
	 */
	public int updatePhotoInfoGoodLogic(Connection conn,String accountId,String photoInfoId) throws SQLException{
		String good = "";
		String sql = "SELECT GOOD FROM PHOTO_INFO WHERE PHOTO_INFO_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, photoInfoId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			good = rs.getString("GOOD");
		}
		if(good.indexOf(accountId) > -1){
			good = good.replaceAll(accountId+",", "");
		}else{
			good = good + accountId + ",";
		}
		String queryStr="UPDATE PHOTO_INFO SET GOOD = ? WHERE PHOTO_INFO_ID = ? ";
		PreparedStatement ps1 = conn.prepareStatement(queryStr);
		ps1.setString(1, good);
		ps1.setString(2, photoInfoId);
		int i = ps1.executeUpdate();
		rs.close();
		ps1.close();
		ps.close();
		return i;
	}
	
	/**
	 * 根据照片Id获得照片详细信息
	 * @param conn
	 * @param photoPath
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPhotoInfoById(Connection conn,String photoPath,String accountId) throws Exception{
		JSONObject json = new JSONObject();
		String queryStr="SELECT PHOTO_INFO_ID,CREATE_TIME,CREATE_ACCOUNT_ID,GOOD FROM PHOTO_INFO WHERE PHOTO_PATH=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, photoPath);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			json.accumulate("photoInfoId", rs.getString("PHOTO_INFO_ID"));
			json.accumulate("createTime", rs.getString("CREATE_TIME"));
			json.accumulate("createAccountId", rs.getString("CREATE_ACCOUNT_ID"));
			String good = rs.getString("GOOD");
			if(!good.equals("")){
				if(good.indexOf(accountId) > -1){
					json.accumulate("good", "true");
				}else{
					json.accumulate("good", "false");
				}
				String goods[] = good.split(",");
				json.accumulate("goodNum", goods.length);
			}else{
				json.accumulate("good", "false");
				json.accumulate("goodNum", "0");
			}
			
		}
		rs.close();
		ps.close();
		return json;
	}

}
