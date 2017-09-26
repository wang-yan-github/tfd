package api.adduser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.data.Notice;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class addNotice {

	
	public void addNoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("公告添加开始");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			for (int i = 0; i < 1000; i++) {
				String accountId="用户"+i;;
			for (int j = 0; j < 500; j++) {			
				addNoticeAct(dbConn,accountId,j);
			}
			dbConn.commit();
			}
			System.out.println("**********************************************");
			System.out.println("**                                          **");
			System.out.println("**                公告全部创建完成                                             **");
			System.out.println("**                                          **");
			System.out.println("**********************************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
	}
	
	private static int jnum=0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = DbPoolConnection.getInstance().getConnection();
		for (int i = 0; i < 1000; i++) {
			String accountId="用户"+i;
		for (int j = 0; j < 500; j++) {			
			addNoticeAct(conn,accountId,j);
		}
		conn.commit();
		}
		System.out.println("创建完成！");
	}
	public static void addNoticeAct(Connection conn,String accountId,int j)throws Exception{
		Notice notice=new Notice();
		notice.setNoticeId(GuId.getGuid());
		notice.setNoticetitle("通知公告 "+j);
		notice.setNoticeType("通知");
		if(jnum<1000){
			notice.setAccountId("userAll");	
		}else if(jnum>=1000&&jnum<10000){
		notice.setAccountId("admin");
		}else if(jnum>=10000&&jnum<100000){
			notice.setAccountId("用户1");
		}else if(jnum>=10000&&jnum<100000){
			
		}else{
			notice.setAccountId(accountId);
		}
		
		notice.setDeptPriv("");
		notice.setUserPriv("");
		notice.setCreatetime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		notice.setEndTime("2016-11-10 17:46:00");
		notice.setAttachId("");
		notice.setAttachPriv("0");
		notice.setNoticeContent("第"+j+"篇公告");
		notice.setOnclickCount(0);
		notice.setTop("1");
		notice.setCreateUser(accountId);
		notice.setAttachPower("1");
		notice.setOrgId("8EADB678-A646-1E51-3E87-75A547B8AF19");
		notice.setIsSms("{\"wxsms\":\"0\",\"sitesms\":\"0\",\"mobilesms\":\"0\",\"emailsms\":\"0\",\"webemilesms\":\"0\"}");
		notice.setOnclickCount(0);
		notice.setApprovalStatus("1");
		notice.setNoticeStatus("1");
		String queryStr="INSERT INTO NOTICE ( NOTICE_ID,NOTICE_TITLE,NOTICE_TYPE,ACCOUNT_ID,DEPT_PRIV,USER_PRIV,CREATE_TIME,ATTACH_ID,ATTACH_PRIV,NOTICE_CONTENT,TOP,CREATE_USER,NOTICE_STATUS,APPROVAL_STAFF,ORG_ID,APPROVAL_STATUS,END_TIME,ATTACH_POWER,IS_SMS,ONCLICK_COUNT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, notice.getNoticeId());
		ps.setString(2, notice.getNoticetitle());
		ps.setString(3, notice.getNoticeType());
		ps.setString(4, notice.getAccountId());
		ps.setString(5, notice.getDeptPriv());
		ps.setString(6, notice.getUserPriv());
		ps.setString(7, notice.getCreatetime());
		ps.setString(8, notice.getAttachId());
		ps.setString(9, notice.getAttachPriv());
		ps.setString(10, notice.getNoticeContent());
		ps.setString(11, notice.getTop());
		ps.setString(12, notice.getCreateUser());
		ps.setString(13, notice.getNoticeStatus());
		ps.setString(14, notice.getApprovalStaff());
		ps.setString(15, notice.getOrgId());
		ps.setString(16, notice.getApprovalStatus());
		ps.setString(17, notice.getEndTime());
		ps.setString(18, notice.getAttachPower());
		ps.setString(19, notice.getIsSms());
		ps.setInt(20, notice.getOnclickCount());
		ps.executeUpdate();
		ps.close();
		jnum++;
	}
}
