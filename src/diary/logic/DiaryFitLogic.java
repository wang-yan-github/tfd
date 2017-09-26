package diary.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;
import diary.data.DiaryFit;

public class DiaryFitLogic {

	/**
	 * 保存工作日志设置信息
	 * Author: Wp
	 * @param conn
	 * @param diaryFit
	 * @return
	 * @throws SQLException
	 */
	public int addFitLogic(Connection conn,DiaryFit diaryFit)throws SQLException{
		String queryStr="INSERT INTO DIARY_FIT (FIT_ID,START_TIME,END_TIME,LOCK_DAY,COMM_STATUS,SHARE_STATUS,ORG_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryFit.getFitId());
		ps.setString(2, diaryFit.getStartTime());
		ps.setString(3, diaryFit.getEndTime());
		ps.setInt(4, diaryFit.getLockDay());
		ps.setInt(5, diaryFit.getCommStatus());
		ps.setInt(6, diaryFit.getShareStatus());
		ps.setString(7, diaryFit.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改工作日志设置信息
	 * Author: Wp
	 * @param conn
	 * @param diaryFit
	 * @return
	 * @throws SQLException
	 */
	public int updateFitLogic(Connection conn,DiaryFit diaryFit)throws SQLException{
		String queryStr="UPDATE DIARY_FIT SET  START_TIME=?,END_TIME=?,LOCK_DAY=?,COMM_STATUS=?,SHARE_STATUS=? WHERE FIT_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, diaryFit.getStartTime());
		ps.setString(2, diaryFit.getEndTime());
		ps.setInt(3, diaryFit.getLockDay());
		ps.setInt(4, diaryFit.getCommStatus());
		ps.setInt(5, diaryFit.getShareStatus());
		ps.setString(6, diaryFit.getFitId());
		ps.setString(7, diaryFit.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据orgId 查询工作日志设置信息
	 * Author: Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String lookFitLogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT START_TIME,FIT_ID,END_TIME,LOCK_DAY,COMM_STATUS,SHARE_STATUS FROM DIARY_FIT WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		JSONObject json  = new JSONObject();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(rs.getString("START_TIME")!=null){
				json.accumulate("startTime", rs.getString("START_TIME"));	
			}else{
				json.accumulate("startTime", "");
			}
			json.accumulate("fitId", rs.getString("FIT_ID"));
			if(rs.getString("END_TIME")!=null){
			json.accumulate("endTime", rs.getString("END_TIME"));
			}else{
				json.accumulate("endTime", "");
			}
			json.accumulate("lockDay", rs.getString("LOCK_DAY"));
			json.accumulate("commStatus", rs.getString("COMM_STATUS"));
			json.accumulate("shareStatus", rs.getString("SHARE_STATUS"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据orgId 获取锁定天数
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public int getlockDayLogic(Connection conn,String orgId)throws SQLException{
		String queryStr="SELECT LOCK_DAY FROM DIARY_FIT WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		int data=0;
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			data=rs.getInt("LOCK_DAY");
		}
		rs.close();
		ps.close();
		return data;
	}
}
