package api.adduser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;


public class AddEmail{
	
	
	
	public static int EMAIL_NUM = 1;
	public static long START_TIME;
	public static int num = 0;
	
	public void AddEmailAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("邮件添加开始");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			START_TIME = new Date().getTime();
			Connection conn = DbPoolConnection.getInstance().getConnection();
			for (int i = 0; i < 4; i++) {
				String boxId = (i+1)+"";
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 25000; k++) {
						addEmail(conn,boxId);
					}
					conn.commit();
				}
			}
			System.out.println("**********************************************");
			System.out.println("**                                          **");
			System.out.println("**                  邮件全部创建完成                                             **");
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
	
	
	public static void main(String[] args)throws Exception {
		try {
			START_TIME = new Date().getTime();
			Connection conn = DbPoolConnection.getInstance().getConnection();
			for (int i = 0; i < 4; i++) {
				String boxId = (i+1)+"";
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 25000; k++) {
						addEmail(conn,boxId);
					}
					conn.commit();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
	
	public static void addEmail(Connection conn,String boxId)throws Exception{
		String bodyId = GuId.getGuid();
		String sql = "INSERT INTO EMAIL_BODY(BODY_ID,FROM_ID,TO_ID,SUBJECT,CONTENT,SEND_TIME,SEND_FLAG,ORG_ID) VALUES(?,?,?,?,?,sysdate,1,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bodyId);
		ps.setString(2, "admin");
		ps.setString(3, "admin");
		ps.setString(4, "邮件"+EMAIL_NUM);
		ps.setString(5, "这是测试邮件"+EMAIL_NUM);
		ps.setString(6, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();
		String emailId =  GuId.getGuid();
		sql = "INSERT INTO EMAIL(EMAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG,BOX_ID,BODY_ID,ORG_ID) VALUES(?,?,'1',?,?,?,?)";
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setString(1, emailId);
		ps1.setString(2, "admin");
		
		String deleteFlag = "1";
		if(boxId.equals("4")){
			deleteFlag = "2";
		}
		ps1.setString(3, deleteFlag);
		ps1.setString(4, boxId);
		ps1.setString(5, bodyId);
		ps1.setString(6, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps1.executeUpdate();
		ps1.close();
		EMAIL_NUM++;
		num++;
	}
	
}
