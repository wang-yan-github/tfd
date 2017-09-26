package meeting.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;
import meeting.data.Approvalmeeting;

public class Approvalmeetinglogic {

	/**
	 * 添加会议申请审批意见
	 * Author Wp
	 * @param conn
	 * @param approvalmeeting
	 * @return
	 * @throws SQLException
	 */
	public int addappmeetinglogic(Connection conn,Approvalmeeting approvalmeeting)throws SQLException{
		String queryStr="INSERT INTO APPROVAL_MEETING (APPROVAL_ID,MEETING_ID,APPROVAL_CONTENT,ACCOUNT_ID,APPROVAL_TIME,ORG_ID) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalmeeting.getApprovalId());
		ps.setString(2, approvalmeeting.getMeetingId());
		ps.setString(3, approvalmeeting.getApprovalContent());
		ps.setString(4, approvalmeeting.getAccountId());
		ps.setString(5, approvalmeeting.getApprovalTime());
		ps.setString(6, approvalmeeting.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改回忆审批意见
	 * Author Wp
	 * @param conn
	 * @param approvalmeeting
	 * @return
	 * @throws SQLException
	 */
	public int updatemeetinglogic(Connection conn,Approvalmeeting approvalmeeting)throws SQLException{
		String queryStr="UPDATE APPROVAL_MEETING SET APPROVAL_CONTENT=?, APPROVAL_TIME =? WHERE MEETING_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalmeeting.getApprovalContent());
		ps.setString(2, approvalmeeting.getApprovalTime());
		ps.setString(3, approvalmeeting.getMeetingId());
		ps.setString(4, approvalmeeting.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据会议ID查询审批意见
	 * Author Wp
	 * @param conn
	 * @param approvalmeeting
	 * @return
	 * @throws SQLException
	 */
	public String getmeetingIdlogic(Connection conn,Approvalmeeting approvalmeeting)throws SQLException{
		String queryStr="SELECT APPROVAL_CONTENT FROM APPROVAL_MEETING WHERE MEETING_ID=? AND ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalmeeting.getMeetingId());
		ps.setString(2, approvalmeeting.getOrgId());
		ResultSet rs=null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("approvalContent", rs.getString("APPROVAL_CONTENT"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
}
