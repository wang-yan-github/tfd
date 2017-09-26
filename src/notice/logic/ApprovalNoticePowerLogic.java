package notice.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import notice.data.ApprovalNoticePower;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;


public class ApprovalNoticePowerLogic {

	/**
	 * 添加公告审批人员
	 * Author: Wp
	 * @param conn
	 * @param approvalnoticepower
	 * @return
	 * @throws SQLException
	 */
	public int addnoticepowerlogic(Connection conn,ApprovalNoticePower approvalnoticepower) throws SQLException{
		String queryStr="INSERT INTO APPROVAL_NOTICE_POWER (POWER_ID,NOTICE_TYPE,APPROVAL_STAFF,ORG_ID) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnoticepower.getPowerId());
		ps.setString(2, approvalnoticepower.getNoticeType());
		ps.setString(3, approvalnoticepower.getApprovalStaff());
		ps.setString(4, approvalnoticepower.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 查看已设置的公告审批人员信息
	 * @param conn
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public String lookpowerlogic(Connection conn,Account account) throws SQLException{
		String queryStr="SELECT CODE_VALUE,(SELECT APPROVAL_STAFF FROM APPROVAL_NOTICE_POWER T2 WHERE T2.NOTICE_TYPE = T1.CODE_VALUE AND T2.ORG_ID =? ) AS APPROVAL_STAFF,(SELECT POWER_ID FROM APPROVAL_NOTICE_POWER T2 WHERE T2.NOTICE_TYPE = T1.CODE_VALUE AND T2.ORG_ID =?) AS POWER_ID FROM CODE_CLASS T1 WHERE T1.CODE_PID= (SELECT CODE_ID FROM CODE_CLASS T1 WHERE T1.CODE_VALUE ='NOTICE' AND T1.ORG_ID =?) AND T1.ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ps.setString(2, account.getOrgId());
		ps.setString(3, account.getOrgId());
		ps.setString(4, account.getOrgId());
		ResultSet rs =null;
		AccountLogic acclogic=new AccountLogic();
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("codevalue", rs.getString("CODE_VALUE"));
			json.accumulate("approvalStaff", rs.getString("APPROVAL_STAFF"));
			if(rs.getString("APPROVAL_STAFF")!=null){
			String userName=acclogic.getUserNameStr(conn, rs.getString("APPROVAL_STAFF"));
			json.accumulate("staffName",userName );
			}else{
				json.accumulate("staffName","");
			}
			json.accumulate("powerId",rs.getString("POWER_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 修改公告审批人员信息
	 * Author: Wp
	 * @param conn
	 * @param approvalnoticepower
	 * @return
	 * @throws SQLException
	 */
	public int updatepowerlogic(Connection conn,ApprovalNoticePower approvalnoticepower) throws SQLException{
		String queryStr="UPDATE APPROVAL_NOTICE_POWER SET NOTICE_TYPE =? ,APPROVAL_STAFF =? ,ORG_ID =? WHERE POWER_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnoticepower.getNoticeType());
		ps.setString(2, approvalnoticepower.getApprovalStaff());
		ps.setString(3, approvalnoticepower.getOrgId());
		ps.setString(4, approvalnoticepower.getPowerId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据审批类型查询审批人员
	 * Author: Wp
	 * @param conn
	 * @param approvalnoticepower
	 * @return
	 * @throws SQLException
	 */
	public String looktypeapprovallogic(Connection conn,ApprovalNoticePower approvalnoticepower) throws SQLException{
		String queryStr="SELECT APPROVAL_STAFF FROM APPROVAL_NOTICE_POWER WHERE NOTICE_TYPE =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, approvalnoticepower.getNoticeType());
		ps.setString(2, approvalnoticepower.getOrgId());
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject staffjson=new JSONObject();
		if(rs.next()){
			if(rs.getString("APPROVAL_STAFF")!=null&&rs.getString("APPROVAL_STAFF")!=""){
			 staffjson.accumulate("APPROVAL_STAFF", rs.getString("APPROVAL_STAFF"));
			}
		}
		rs.close();
		ps.close();
		return staffjson.toString();
	}
}
