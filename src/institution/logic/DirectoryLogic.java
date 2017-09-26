package institution.logic;

import institution.data.Directory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DirectoryLogic {
	/**
	 * 得到目录与制度
	 */
	public String getDirectoryList(Connection conn,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT DIR_ID,DIR_NAME,TOP_ID FROM DIRECTORY WHERE ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("DIR_ID"));
			json.accumulate("pId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("DIR_NAME"));
			if(isParent(conn, rs.getString("DIR_ID"))){
				json.accumulate("isParent", "true");
				json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
			}else{
				if(isParent2(conn, rs.getString("DIR_ID"))){
					json.accumulate("isParent", "true");
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
				}else{
					json.accumulate("isParent", "false");
					json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder_active.gif");
				}
			}
			
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		ResultSet rs1 =null;
		String queryStr1="SELECT INST_ID,INST_NAME,DIR_ID FROM INSTITUTION WHERE ORG_ID = ?";
		PreparedStatement ps1 = conn.prepareStatement(queryStr1);
		ps1.setString(1, orgId);
		rs1=ps1.executeQuery();
		while(rs1.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs1.getString("INST_ID"));
			json.accumulate("pId", rs1.getString("DIR_ID"));
			json.accumulate("name", rs1.getString("INST_NAME"));
			json.accumulate("isParent", "false");
			json.accumulate("icon", "/tfd/system/styles/images/file_type/dot.gif");
			
			jsonArr.add(json);
		}
		rs1.close();
		ps1.close();
		return jsonArr.toString();
	}
	
	/**
	 * 判断是否为父级
	 * Author:Yzz
	 * Time:2015-6-4
	 * @param conn
	 * @param topId
	 * @return
	 * @throws Exception
	 */
	public boolean isParent(Connection conn,String dirId)throws Exception{
		boolean flag = false;
		String sql = "SELECT INST_ID FROM INSTITUTION WHERE DIR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dirId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * 判断是否为父级
	 * Author:Yzz
	 * Time:2015-6-4
	 * @param conn
	 * @param topId
	 * @return
	 * @throws Exception
	 */
	public boolean isParent2(Connection conn,String dirId)throws Exception{
		boolean flag = false;
		String sql = "SELECT DIR_ID FROM DIRECTORY WHERE TOP_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dirId);
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
	 * 得到目录
	 */
	public String getDirectoryList2(Connection conn,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT DIR_ID,DIR_NAME,TOP_ID FROM DIRECTORY WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("DIR_ID"));
			json.accumulate("pId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("DIR_NAME"));
			if(isParent2(conn, rs.getString("DIR_ID"))){
				json.accumulate("isParent", "true");
				json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
			}else{
				json.accumulate("isParent", "false");
				json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder_active.gif");
			}
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 添加目录
	 */
	public int addDirectory(Connection conn,Directory dir)throws Exception{
		String sql = "INSERT INTO DIRECTORY(DIR_ID,DIR_NAME,TOP_ID,ALL_DIR,ORG_ID) VALUES( ?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dir.getDirId());
		int j = 2;
		String dirName = dir.getDirName();
		String name = dirName;
		while(checkDir(conn, name,dir.getTopId())){
			name = dirName + "(" + j + ")";
			j++;
		}
		dirName = name;
		ps.setString(2,dirName);
		ps.setString(3,dir.getTopId() );
		ps.setString(4, dir.getAllDir());
		ps.setString(5, dir.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 检查制度
	 */
	public boolean checkDir(Connection conn,String name,String topId)throws Exception{
		boolean flag = false;
		String sql = "SELECT DIR_NAME FROM DIRECTORY WHERE DIR_NAME = ? AND TOP_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2,topId );
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
	 * 根据Id得到单个的目录信息
	 */
	public String getDirectoryById(Connection conn,String dirId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT DIR_ID,DIR_NAME,TOP_ID,(SELECT t2.DIR_NAME FROM DIRECTORY t2 WHERE t2.DIR_ID = t1.TOP_ID) AS TOP_NAME  FROM DIRECTORY t1 WHERE t1.DIR_ID = ? AND t1.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dirId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("dirId", rs.getString("DIR_ID"));
			json.accumulate("topId", rs.getString("TOP_ID"));
			json.accumulate("name", rs.getString("DIR_NAME"));
			json.accumulate("dirName", rs.getString("TOP_NAME"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据Id删除目录信息
	 */
	public int delDirectoryById(Connection conn,String id,String orgId)throws Exception{
		String sql = "DELETE FROM DIRECTORY WHERE DIR_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id修改目录信息
	 */
	public int updateDirectoryById(Connection conn,Directory dir)throws Exception{
		String sql = "UPDATE DIRECTORY SET DIR_NAME = ?,TOP_ID = ?,ALL_DIR = ? WHERE DIR_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,dir.getDirName() );
		ps.setString(2,dir.getTopId() );
		ps.setString(3, dir.getAllDir());
		ps.setString(4,dir.getDirId());
		ps.setString(5, dir.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 根据Id得到完整目录
	 */
	public String getAllDirById(Connection conn,String id)throws Exception{
		String all_Dir = null;
		String sql = "select ALL_DIR from DIRECTORY where DIR_ID = ?";
		ResultSet rs =null;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();
		if(rs.next()){
			all_Dir = rs.getString("ALL_DIR");
		}
		rs.close();
		ps.close();
		return all_Dir;
	}
}
