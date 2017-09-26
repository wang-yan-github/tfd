package diary.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import diary.data.DiaryComments;

public class DiaryCommentsLogic {

	/**
	 * 添加日志评论信息
	 * Author: Wp
	 * @param conn
	 * @param dcomm
	 * @return
	 * @throws SQLException
	 */
	public int addCommentsLogic(Connection conn,DiaryComments dcomm)throws SQLException{
		String queryStr="INSERT INTO DIARY_COMMENTS (COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,DIARY_ID,ACCOUNT_ID,ORG_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dcomm.getCommId());
		ps.setString(2, dcomm.getCommPid());
		ps.setString(3, dcomm.getCommContect());
		ps.setString(4, dcomm.getCommTime());
		ps.setString(5, dcomm.getDiaryId());
		ps.setString(6, dcomm.getAccountId());
		ps.setString(7, dcomm.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据日志Id获取评论内容
	 * Author: Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String lookCommentsLogic(Connection conn,String diaryId,String orgId)throws Exception{
		String queryStr="SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,ACCOUNT_ID,(SELECT USER_NAME FROM USER_INFO T2 WHERE T2.ACCOUNT_ID=T1.ACCOUNT_ID)AS USER_NAME,DIARY_ID FROM DIARY_COMMENTS T1 WHERE DIARY_ID=? AND ORG_ID=? ORDER BY ID ASC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryId);
		ps.setString(2, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray(); 
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("commId", rs.getString("COMM_ID"));
			if(rs.getString("COMM_PID")==null||rs.getString("COMM_PID").equals("")){
				json.accumulate("commPid", "0");
			}else{
			json.accumulate("commPid", rs.getString("COMM_PID"));
			}
			if(rs.getString("COMM_CONTECT")==null){
			json.accumulate("commContect", "");
			}else{
				json.accumulate("commContect", rs.getString("COMM_CONTECT"));
			}
			json.accumulate("userName", rs.getString("USER_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("diaryId", rs.getString("DIARY_ID"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time1 = formatter.parse(rs.getString("COMM_TIME"));
			Date nowTime = new Date();
			long temp = nowTime.getTime() - time1.getTime();
			String time = "";
			if(temp < 60000){
				long sounds = temp / 1000;
				time = sounds+"秒前";
			}else if(temp >= 60000 && temp < 3600000){
				long mins = temp / 60 / 1000;
				time = mins+"分钟前";
			}else if(temp >= 3600000 && temp < 86400000){
				long hours = temp / 3600 / 1000;
				time = hours+"小时前";
			}else if(temp >= 86400000 && temp < 172800000){
				time = "昨天";
			}else if(temp >= 172800000){
				time = rs.getString("COMM_TIME").substring(0,10);
			}
			json.accumulate("commTime", time);
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据评论Id 删除评论信息
	 * Author: Wp
	 * @param conn
	 * @param commId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delCommIdlogic(Connection conn,String commId,String orgId)throws SQLException{
		String queryStr="DELETE FROM DIARY_COMMENTS WHERE COMM_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, commId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		this.delCommPidlogic(conn, commId, orgId);
		return i;
	}
	/**
	 * 根据父级的评论Id 删除子集的所有评论信息
	 * Author: Wp
	 * @param conn
	 * @param commPid
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int delCommPidlogic(Connection conn,String commPid,String orgId)throws SQLException{
		String queryStr="DELETE FROM DIARY_COMMENTS WHERE COMM_PID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, commPid);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据diaryId 删除评论信息
	 * Author: Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int deldiaryIdlogic(Connection conn,String diaryId,String orgId)throws SQLException{
		String queryStr="DELETE FROM DIARY_COMMENTS WHERE DIARY_ID =? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryId);
		ps.setString(2, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据日志Id 查询有多少条评论记录
	 * Author: Wp
	 * @param conn
	 * @param diaryId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getIdcountLogic(Connection conn,String diaryId,String orgId)throws SQLException{
		String queryStr="SELECT COUNT(1) AS COMM_COUNT FROM DIARY_COMMENTS WHERE DIARY_ID =? AND ORG_ID =? AND COMM_PID ='0'";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryId);
		ps.setString(2, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json  = new JSONObject();
		if(rs.next()){
			json.accumulate("commCount", rs.getString("COMM_COUNT"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 通过评论Id 获取评论人accoungId
	 * Author: Wp
	 * @param conn
	 * @param commId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getcommIdNameLogic(Connection conn,String commId,String orgId)throws SQLException{
		String queryStr="SELECT ACCOUNT_ID FROM DIARY_COMMENTS WHERE COMM_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, commId);
		ps.setString(2, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		String accountId="";
		if(rs.next()){
			accountId=rs.getString("ACCOUNT_ID");
		}
		rs.close();
		ps.close();
		return accountId;
	}
}
