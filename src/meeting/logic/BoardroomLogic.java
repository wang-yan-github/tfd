package meeting.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import meeting.data.Boardroom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;

import com.system.db.DbPoolConnection;

public class BoardroomLogic {

	/**
	 * 添加会议室信息
	 * Author Wp
	 * @param conn
	 * @param boardroom
	 * @return
	 * @throws SQLException
	 */
	public int addboardroomlogic(Connection conn,Boardroom boardroom)throws SQLException{
		String queryStr="INSERT INTO BOARDROOM (BOARDROOM_ID,BOARDROOM_NAME,BOARDROOM_DEPICT,BOARDROOM_STAFF,DEPT_PRIV,USER_PRIV,BOARDROOM_NUM,ALLOW_TIME,EQUIPMENT,BOARDROOM_ADDRESS,BOARDROOM_SYSTEM,ORG_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroom.getBoardroomId());
		ps.setString(2, boardroom.getBoardroomName());
		ps.setString(3, boardroom.getBoardroomDepict());
		ps.setString(4, boardroom.getBoardroomStaff());
		ps.setString(5, boardroom.getDeptPriv());
		ps.setString(6, boardroom.getUserPriv());
		ps.setString(7, boardroom.getBoardroomNum());
		ps.setString(8, boardroom.getAllowTime());
		ps.setString(9, boardroom.getEquipment());
		ps.setString(10, boardroom.getBoardroomAddress());
		ps.setString(11, boardroom.getBoardroomSystem());
		ps.setString(12, boardroom.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 删除会议室信息
	 * Author Wp
	 * @param conn
	 * @param boardroomId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delboardroomlogic(Connection conn,String boardroomId,String orgId)throws SQLException{
		String queryStr="DELETE FROM BOARDROOM WHERE BOARDROOM_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改会议室信息
	 * Author Wp
	 * @param conn
	 * @param boardroom
	 * @return
	 * @throws SQLException
	 */
	public int updateboardroomlogic(Connection conn,Boardroom boardroom)throws SQLException{
		String queryStr="UPDATE BOARDROOM SET BOARDROOM_NAME =?,BOARDROOM_DEPICT =?,BOARDROOM_STAFF =?,DEPT_PRIV =?,USER_PRIV =?,BOARDROOM_NUM =?,ALLOW_TIME =?,EQUIPMENT =?,BOARDROOM_ADDRESS =?,BOARDROOM_SYSTEM =? WHERE BOARDROOM_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroom.getBoardroomName());
		ps.setString(2, boardroom.getBoardroomDepict());
		ps.setString(3, boardroom.getBoardroomStaff());
		ps.setString(4, boardroom.getDeptPriv());
		ps.setString(5, boardroom.getUserPriv());
		ps.setString(6, boardroom.getBoardroomNum());
		ps.setString(7, boardroom.getAllowTime());
		ps.setString(8, boardroom.getEquipment());
		ps.setString(9, boardroom.getBoardroomAddress());
		ps.setString(10, boardroom.getBoardroomSystem());
		ps.setString(11, boardroom.getBoardroomId());
		ps.setString(12, boardroom.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据id查询会议室信息
	 * Author Wp
	 * @param conn
	 * @param boardroomId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getIdboardroomAct(Connection conn,String boardroomId,String orgId)throws SQLException{
		String queryStr="SELECT BOARDROOM_ID,BOARDROOM_NAME,BOARDROOM_DEPICT,BOARDROOM_STAFF,DEPT_PRIV,USER_PRIV,BOARDROOM_NUM,ALLOW_TIME,EQUIPMENT,BOARDROOM_ADDRESS,BOARDROOM_SYSTEM,ORG_ID FROM BOARDROOM WHERE BOARDROOM_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomId);
		ps.setString(2, orgId);
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("boardroomId", rs.getString("BOARDROOM_ID"));
			json.accumulate("boardroomName", rs.getString("BOARDROOM_NAME"));
			json.accumulate("boardroomDepict", rs.getString("BOARDROOM_DEPICT"));
			json.accumulate("boardroomStaff", rs.getString("BOARDROOM_STAFF"));
			String boardroomuserName=acclogic.getUserNameStr(conn, rs.getString("BOARDROOM_STAFF"));
			json.accumulate("boardroomuserName", boardroomuserName);
			json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
			String deptName=deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
			json.accumulate("deptName",deptName);
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			String userName=acclogic.getUserNameStr(conn, rs.getString("USER_PRIV"));
			json.accumulate("userName", userName);
			json.accumulate("boardroomNum", rs.getString("BOARDROOM_NUM"));
			json.accumulate("allowTime", rs.getString("ALLOW_TIME"));
			json.accumulate("equipment", rs.getString("EQUIPMENT"));
			json.accumulate("boardroomAddress", rs.getString("BOARDROOM_ADDRESS"));
			json.accumulate("boardroomSystem", rs.getString("BOARDROOM_SYSTEM"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 查询会议室列表
	 * Author Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String selectboardroomlogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT BOARDROOM_ID,BOARDROOM_NAME,BOARDROOM_DEPICT,T2.USER_NAME AS USER_NAME,BOARDROOM_STAFF,DEPT_PRIV,USER_PRIV,BOARDROOM_NUM,ALLOW_TIME,EQUIPMENT,BOARDROOM_ADDRESS,BOARDROOM_SYSTEM FROM BOARDROOM T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID = T1.BOARDROOM_STAFF WHERE T1.ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("boardroomId", rs.getString("BOARDROOM_ID"));
			json.accumulate("boardroomName", rs.getString("BOARDROOM_NAME"));
			json.accumulate("boardroomDepict", rs.getString("BOARDROOM_DEPICT"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("boardroomStaff", rs.getString("BOARDROOM_STAFF"));
			json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			json.accumulate("boardroomNum", rs.getString("BOARDROOM_NUM"));
			json.accumulate("allowTime", rs.getString("ALLOW_TIME"));
			json.accumulate("equipment", rs.getString("EQUIPMENT"));
			json.accumulate("boardroomAddress", rs.getString("BOARDROOM_ADDRESS"));
			json.accumulate("boardroomSystem", rs.getString("BOARDROOM_SYSTEM"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据用户的权限去查询会议室列表
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @param deptId
	 * @param otherdept
	 * @return
	 * @throws SQLException
	 */
	public String getaccountlogic(Connection conn,String accountId,String orgId,String deptId,String otherdept)throws SQLException{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr="";
		if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			queryStr="SELECT BOARDROOM_ID,BOARDROOM_NAME,BOARDROOM_DEPICT, T2.USER_NAME AS USER_NAME,BOARDROOM_STAFF,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,BOARDROOM_NUM,ALLOW_TIME,EQUIPMENT,BOARDROOM_ADDRESS,BOARDROOM_SYSTEM FROM BOARDROOM T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID = T1.BOARDROOM_STAFF WHERE ( T1.USER_PRIV='userAll' OR LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 OR  DEPT_PRIV='deptAll' OR  LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 ) AND T1.ORG_ID =? ";
		}else if(dbType.equals("oracle")){
			queryStr="SELECT BOARDROOM_ID,BOARDROOM_NAME,BOARDROOM_DEPICT, T2.USER_NAME AS USER_NAME,BOARDROOM_STAFF,T1.DEPT_PRIV AS DEPT_PRIV,T1.USER_PRIV AS USER_PRIV,BOARDROOM_NUM,ALLOW_TIME,EQUIPMENT,BOARDROOM_ADDRESS,BOARDROOM_SYSTEM FROM BOARDROOM T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID = T1.BOARDROOM_STAFF WHERE (TO_CHAR(T1.USER_PRIV)='userAll' OR INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 OR TO_CHAR(T1.DEPT_PRIV)='deptAll' OR INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 ) AND T1.ORG_ID =? ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, deptId);
		ps.setString(3, otherdept);
		ps.setString(4, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("boardroomId", rs.getString("BOARDROOM_ID"));
			json.accumulate("boardroomName", rs.getString("BOARDROOM_NAME"));
			json.accumulate("boardroomDepict", rs.getString("BOARDROOM_DEPICT"));
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("boardroomStaff", rs.getString("BOARDROOM_STAFF"));
			json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
			json.accumulate("userPriv", rs.getString("USER_PRIV"));
			json.accumulate("boardroomNum", rs.getString("BOARDROOM_NUM"));
			json.accumulate("allowTime", rs.getString("ALLOW_TIME"));
			json.accumulate("equipment", rs.getString("EQUIPMENT"));
			json.accumulate("boardroomAddress", rs.getString("BOARDROOM_ADDRESS"));
			json.accumulate("boardroomSystem", rs.getString("BOARDROOM_SYSTEM"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
