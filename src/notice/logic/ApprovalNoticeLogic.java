package notice.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tfd.system.unit.account.logic.AccountLogic;
import net.sf.json.JSONObject;
import notice.data.ApprovalNotice;



public class ApprovalNoticeLogic {
	/**
	 * 添加通知公告审批意见
	 * Author: Wp
	 * @param conn
	 * @param approvalnotice
	 * @return
	 * @throws SQLException
	 */
	public int addapprovalLogic(Connection conn,ApprovalNotice approvalnotice) throws SQLException{
		String queryStr="INSERT INTO APPROVAL_NOTICE (APPROVAL_ID,NOTICE_ID,APPROVAL_CONTENT,ACCOUNT_ID,APPROVAL_TIME,ORG_ID) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnotice.getApprovalId());
		ps.setString(2, approvalnotice.getNoticeId());
		ps.setString(3, approvalnotice.getApprovalContent());
		ps.setString(4, approvalnotice.getAccountId());
		ps.setString(5, approvalnotice.getApprovalTime());
		ps.setString(6, approvalnotice.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 查看审批意见
	 * Author: Wp
	 * @param conn
	 * @param approvalnotice
	 * @return
	 * @throws SQLException
	 */
	public String lookapprovalLogic(Connection conn,ApprovalNotice approvalnotice) throws SQLException{
		String queryStr="SELECT APPROVAL_ID,NOTICE_ID,APPROVAL_CONTENT,ACCOUNT_ID,APPROVAL_TIME,ORG_ID FROM APPROVAL_NOTICE WHERE NOTICE_ID=? AND ORG_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnotice.getNoticeId());
		ps.setString(2, approvalnotice.getOrgId());
		ResultSet rs =null;
		AccountLogic acclogic=new AccountLogic();
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			String username=acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", username);
			if(rs.getString("APPROVAL_CONTENT")==null){
				json.accumulate("approvalContent", "");	
			}else{
			json.accumulate("approvalContent", rs.getString("APPROVAL_CONTENT"));
			}
			json.accumulate("approvalTime", rs.getString("APPROVAL_TIME"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 根据通知公告Id 修改公告审批意见
	 * Author: Wp
	 * @param conn
	 * @param approvalnotice
	 * @return
	 * @throws SQLException
	 */
	public int updateapprovalLogic(Connection conn,ApprovalNotice approvalnotice) throws SQLException{
		String queryStr="UPDATE APPROVAL_NOTICE SET APPROVAL_CONTENT=? ,ACCOUNT_ID=?,APPROVAL_TIME=? WHERE NOTICE_ID=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnotice.getApprovalContent());
		ps.setString(2, approvalnotice.getAccountId());
		ps.setString(3, approvalnotice.getApprovalTime());
		ps.setString(4, approvalnotice.getNoticeId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
}
