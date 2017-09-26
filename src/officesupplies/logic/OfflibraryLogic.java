package officesupplies.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.db.DbPoolConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import officesupplies.data.Offlibrary;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;

public class OfflibraryLogic {

	/**
	 * 添加办公用品库
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public int addlibrarylogic(Connection conn,Offlibrary library) throws SQLException{
		String queryStr="INSERT INTO OFF_LIBRARY( LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF,DEL_STATUS,ORG_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getLibraryId());
		ps.setString(2, library.getLibraryName());
		ps.setString(3, library.getBelongDept());
		ps.setString(4, library.getLibraryStaff());
		ps.setString(5, library.getDispatchStaff());
		ps.setString(6, library.getDelStatus());
		ps.setString(7, library.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id 修改办公用品库
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public int updatelibrarylogic(Connection conn,Offlibrary library) throws SQLException{
		String queryStr="UPDATE OFF_LIBRARY SET LIBRARY_NAME=? ,BELONG_DEPT =?,LIBRARY_STAFF =?,DISPATCH_STAFF =?,SORT_ID=? WHERE LIBRARY_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getLibraryName());
		ps.setString(2, library.getBelongDept());
		ps.setString(3, library.getLibraryStaff());
		ps.setString(4, library.getDispatchStaff());
		ps.setString(5, library.getSortId());
		ps.setString(6, library.getLibraryId());
		ps.setString(7, library.getOrgId());
		int i=ps.executeUpdate();
		if(i>0){
			this.updatetopIdlogic(conn, library);
			Offreslogic offreslogic=new Offreslogic();
			offreslogic.updateStaffLogic(conn, library.getLibraryId(), library.getOrgId(), library.getLibraryStaff());
			Offresapplylogic offresapplylogic=new Offresapplylogic();
			offresapplylogic.updateStaffLogic(conn, library.getLibraryId(), library.getOrgId(), library.getLibraryStaff());
		}
		ps.close();
		return i;
	}
	/**
	 * 根据Id 改变删除状态
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public int dellibrarylogic(Connection conn,Offlibrary library) throws SQLException{
		int i=0;
		if(this.checklibrary(conn, library)){
		String queryStr="UPDATE OFF_LIBRARY SET DEL_STATUS =? WHERE  LIBRARY_ID=? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getDelStatus());
		ps.setString(2, library.getLibraryId());
		ps.setString(3, library.getOrgId());
		i=ps.executeUpdate();
		ps.close();
		}
		else{
			i=2;
		}
		return i;
	}
	/**
	 * 根据Id 查看是否该库中拥有分类库
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public boolean checklibrary(Connection conn,Offlibrary library)throws SQLException{
		String queryStr="SELECT COUNT(1) AS NUM FROM OFF_RES WHERE (LIBRARY_ID=? OR CLASSIFY_ID=?) AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getLibraryId());
		ps.setString(2, library.getLibraryId());
		ps.setString(3, library.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		boolean falg=true;
		if(rs.next()){
			int num=rs.getInt("NUM");
			if(num>0){
				falg=false;
			}
		}
		rs.close();
		ps.close();
		return falg;
	}
	/**
	 * 根据Id 查看办公用品库的信息
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public String getIdlibrarylogic(Connection conn,Offlibrary library) throws SQLException{
		String queryStr="SELECT LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF, (SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.TOP_ID) AS TOP_NAME,TOP_ID ,SORT_ID FROM OFF_LIBRARY T1 WHERE LIBRARY_ID =? AND DEL_STATUS =0 AND ORG_ID =?  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getLibraryId());
		ps.setString(2, library.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		JSONObject json=new JSONObject();
		if(rs.next()){
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("belongDept", rs.getString("BELONG_DEPT"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("BELONG_DEPT"));
			json.accumulate("deptName", deptName);
			json.accumulate("libraryStaff", rs.getString("LIBRARY_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("LIBRARY_STAFF"));
			json.accumulate("userName", userName);
			if(rs.getString("TOP_ID")==null){
				json.accumulate("topId", "");
			}else{
			json.accumulate("topId", rs.getString("TOP_ID"));
			}
			if(rs.getString("TOP_NAME")==null){
				json.accumulate("topName", "");
			}else{
			json.accumulate("topName", rs.getString("TOP_NAME"));
			}
			json.accumulate("sortId", rs.getString("SORT_ID"));
			json.accumulate("dispatchStaff",rs.getString("DISPATCH_STAFF") );
			String dispatchName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
			json.accumulate("dispatchName", dispatchName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 获取办公用品库列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String looklibrarylogic(Connection conn,Account account) throws SQLException{
		String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("className",getNamelogic(conn, rs.getString("LIBRARY_ID"), account.getOrgId()));
			json.accumulate("belongDept", rs.getString("BELONG_DEPT"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("BELONG_DEPT"));
			json.accumulate("deptName", deptName);
			json.accumulate("libraryStaff", rs.getString("LIBRARY_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("LIBRARY_STAFF"));
			json.accumulate("userName", userName);
			json.accumulate("dispatchStaff",rs.getString("DISPATCH_STAFF") );
			String dispatchName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
			json.accumulate("dispatchName", dispatchName);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据管理员获取办公用品列表
	 * Author:Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
		public String getlibrarylogic(Connection conn,Account account) throws SQLException{
			String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF FROM OFF_LIBRARY WHERE LIBRARY_STAFF =? AND TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?  ";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, account.getAccountId());
			ps.setString(2, account.getOrgId());
			ResultSet rs=null;
			rs=ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				JSONObject json=new JSONObject();
				json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
				json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 根据 dispatch_staff 获取办公用品库列表
		 * Author:Wp
		 * @param conn
		 * @param account
		 * @return
		 * @throws SQLException
		 */
				public String getdislibrarylogic(Connection conn,Account account) throws SQLException{
					String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF FROM OFF_LIBRARY WHERE DISPATCH_STAFF =? AND TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?  ";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, account.getAccountId());
					ps.setString(2, account.getOrgId());
					ResultSet rs=null;
					rs=ps.executeQuery();
					JSONArray jsonArr=new JSONArray();
					while(rs.next()){
						JSONObject json=new JSONObject();
						json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
						json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
						jsonArr.add(json);
					}
					rs.close();
					ps.close();
					return jsonArr.toString();
				}
				/**
				 * 获取所有办公用品库
				 * Author:Wp
				 * @param conn
				 * @param account
				 * @return
				 * @throws SQLException
				 */
		public String getlibraryNamelogic(Connection conn,Account account) throws SQLException{
			String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?  ";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, account.getOrgId());
			ResultSet rs=null;
			rs=ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				JSONObject json=new JSONObject();
				json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
				json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 获取办公用品库和办公用品信息
		 * Author:Wp
		 * @param conn
		 * @param account
		 * @param deptId
		 * @return
		 * @throws SQLException
		 */
				public String alllibrarylogic(Connection conn,Account account,String deptId) throws SQLException{
					String queryStr="SELECT LIBRARY_ID,LIBRARY_NAME FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?  ";
					PreparedStatement ps = conn.prepareStatement(queryStr);
					ps.setString(1, account.getOrgId());
					ResultSet rs=null;
					rs=ps.executeQuery();
					JSONObject jsonall=new JSONObject();
					JSONArray jsonlib=new JSONArray();
					while(rs.next()){
						JSONObject json=new JSONObject();
						json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
						json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
						jsonlib.add(json);
					}
					jsonall.accumulate("library", jsonlib);
					String queryStrclassify="SELECT LIBRARY_NAME,TOP_ID,LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IN (SELECT LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?)";
					ps = conn.prepareStatement(queryStrclassify);
					ps.setString(1, account.getOrgId());
					rs=ps.executeQuery();
					JSONArray jsonclassify=new JSONArray();
					while(rs.next()){
						JSONObject json=new JSONObject();
						json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
						json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
						if(rs.getString("TOP_ID")==null){
						json.accumulate("topId", "");
						}else{
							json.accumulate("topId", rs.getString("TOP_ID"));	
						}
						jsonclassify.add(json);
					}
					jsonall.accumulate("classify", jsonclassify);
					String dbType =DbPoolConnection.getInstance().getDbType();
					String queryStrres="";
					if(dbType.equals("sqlserver")){
						
					}else if(dbType.equals("mysql")){
						queryStrres="SELECT RES_ID,RES_NAME ,CLASSIFY_ID,BEFORESTOCK,RES_PRICE FROM OFF_RES T1 WHERE CLASSIFY_ID IN (SELECT LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IN (SELECT LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?)) AND (LOCATE(',userAll,',CONCAT(',',USER_PRIV,','))>0 OR LOCATE(?,CONCAT(',',USER_PRIV,','))>0 OR LOCATE(',deptAll,',CONCAT(',',DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',DEPT_PRIV,','))>0) AND ORG_ID =?";
					}else if(dbType.equals("oracle")){
						queryStrres="SELECT RES_ID,RES_NAME ,CLASSIFY_ID,BEFORESTOCK,RES_PRICE FROM OFF_RES T1 WHERE CLASSIFY_ID IN (SELECT LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IN (SELECT LIBRARY_ID FROM OFF_LIBRARY WHERE TOP_ID IS NULL AND DEL_STATUS =0  AND ORG_ID =?)) AND (INSTR(CONCAT(',',USER_PRIV)||',',',userAll,')>0 OR INSTR(CONCAT(',',USER_PRIV)||',',?)>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',',deptAll,')>0 OR INSTR(CONCAT(',',DEPT_PRIV)||',',?)>0) AND ORG_ID =?";
					}
					ps = conn.prepareStatement(queryStrres);
					ps.setString(1, account.getOrgId());
					ps.setString(2, ","+account.getAccountId()+",");
					ps.setString(3, ","+deptId+",");
					ps.setString(4, account.getOrgId());
					rs=ps.executeQuery();
					JSONArray jsonArrres=new JSONArray();
					while(rs.next()){
						JSONObject json =new JSONObject();
						json.accumulate("resId", rs.getString("RES_ID"));
						json.accumulate("resName", rs.getString("RES_NAME"));
						json.accumulate("classifyId", rs.getString("CLASSIFY_ID"));
						json.accumulate("beforeStock", rs.getString("BEFORESTOCK"));
						json.accumulate("resPrice", rs.getString("RES_PRICE"));
						jsonArrres.add(json);
					}
					jsonall.accumulate("res", jsonArrres);
					rs.close();
					ps.close();
					return jsonall.toString();
					
				}
				/**
				 * 添加分类库
				 * Author:Wp
				 * @param conn
				 * @param library
				 * @return
				 * @throws SQLException
				 */
	public int addlibclassifylogic(Connection conn,Offlibrary library) throws SQLException{
		String queryStr="INSERT INTO OFF_LIBRARY( LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF,TOP_ID,SORT_ID,DEL_STATUS,ORG_ID) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getLibraryId());
		ps.setString(2, library.getLibraryName());
		ps.setString(3, library.getBelongDept());
		ps.setString(4, library.getLibraryStaff());
		ps.setString(5, library.getDispatchStaff());
		ps.setString(6, library.getTopId());
		ps.setString(7, library.getSortId());
		ps.setString(8, library.getDelStatus());
		ps.setString(9, library.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据topId 获取分类库列表
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
	public String gettopIdlogic(Connection conn,Offlibrary library) throws SQLException{
		String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.TOP_ID) AS TOP_NAME,TOP_ID,SORT_ID FROM OFF_LIBRARY T1 WHERE TOP_ID=? AND DEL_STATUS =0  AND ORG_ID =?  ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, library.getTopId());
		ps.setString(2, library.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		JSONArray jsonArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("Id", rs.getString("ID"));
			json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
			json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
			json.accumulate("belongDept", rs.getString("BELONG_DEPT"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("BELONG_DEPT"));
			json.accumulate("deptName", deptName);
			if(rs.getString("TOP_ID")==null){
				json.accumulate("topId", "");
			}else{
				json.accumulate("topId", rs.getString("TOP_ID"));	
			}
			if(rs.getString("TOP_NAME")==null){
				json.accumulate("topName", "");
			}else{
			json.accumulate("topName", rs.getString("TOP_NAME"));
			}
			json.accumulate("sortId", rs.getString("SORT_ID"));
			json.accumulate("libraryStaff", rs.getString("LIBRARY_STAFF"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("LIBRARY_STAFF"));
			json.accumulate("userName", userName);
			json.accumulate("dispatchStaff",rs.getString("DISPATCH_STAFF") );
			String dispatchName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
			json.accumulate("dispatchName", dispatchName);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据topId获取分类库名称
	 * Author:Wp
	 * @param conn
	 * @param library
	 * @return
	 * @throws SQLException
	 */
		public String gettopIdNamelogic(Connection conn,Offlibrary library) throws SQLException{
			String queryStr="SELECT ID, LIBRARY_ID,LIBRARY_NAME,BELONG_DEPT,LIBRARY_STAFF,DISPATCH_STAFF,(SELECT LIBRARY_NAME FROM OFF_LIBRARY T2 WHERE T2.LIBRARY_ID =T1.TOP_ID) AS TOP_NAME,TOP_ID,SORT_ID FROM OFF_LIBRARY T1 WHERE TOP_ID=? AND DEL_STATUS =0  AND ORG_ID =?  ";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, library.getTopId());
			ps.setString(2, library.getOrgId());
			AccountLogic acclogic=new AccountLogic();
			ResultSet rs=null;
			rs=ps.executeQuery();
			JSONArray jsonArr=new JSONArray();
			while(rs.next()){
				JSONObject json=new JSONObject();
				json.accumulate("libraryId", rs.getString("LIBRARY_ID"));
				json.accumulate("libraryName", rs.getString("LIBRARY_NAME"));
				json.accumulate("libraryStaff", rs.getString("LIBRARY_STAFF"));
				String disName=acclogic.getUserNameStr(conn, rs.getString("DISPATCH_STAFF"));
				json.accumulate("disName", disName);
				json.accumulate("dispatchStaff", rs.getString("DISPATCH_STAFF"));
				String userName=acclogic.getUserNameStr(conn, rs.getString("LIBRARY_STAFF"));
				json.accumulate("userName", userName);
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 根据topId 修改分类库信息
		 * Author:Wp
		 * @param conn
		 * @param library
		 * @return
		 * @throws SQLException
		 */
		public int updatetopIdlogic(Connection conn,Offlibrary library) throws SQLException{
			String queryStr="UPDATE OFF_LIBRARY SET BELONG_DEPT =?,LIBRARY_STAFF=?,DISPATCH_STAFF=? WHERE TOP_ID =? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, library.getBelongDept());
			ps.setString(2, library.getLibraryStaff());
			ps.setString(3, library.getDispatchStaff());
			ps.setString(4, library.getLibraryId());
			ps.setString(5, library.getOrgId());
			ps.executeUpdate();
			ps.close();
			return 0;
		}
		/**
		 * 根据topId获取分类库的名称字符串
		 * Author:Wp
		 * @param conn
		 * @param topId
		 * @param orgId
		 * @return
		 * @throws SQLException
		 */
		public String getNamelogic(Connection conn,String topId,String orgId)throws SQLException{
			String queryStr="SELECT LIBRARY_NAME FROM OFF_LIBRARY WHERE TOP_ID=? AND ORG_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, topId);
			ps.setString(2, orgId);
			ResultSet rs=null;
			rs=ps.executeQuery();
			String libraryName="";
			while(rs.next()){
				if(rs.getString("LIBRARY_NAME")==null){
					
				}else{
				libraryName+=rs.getString("LIBRARY_NAME")+",";
				}
			}
			if(libraryName.length()!=0){
			libraryName=libraryName.substring(0,libraryName.length()-1);
			}
			rs.close();
			ps.close();
			return libraryName;
		}
}
