package api.adduser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class AddAttend {
	
	public static int ATTEND_ID = 1;
	
	public void addAttendAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("开始创建考勤数据");
		long time = new Date().getTime()/1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse("2015-10-01 09:00:00"));
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sdf.parse("2015-10-01 11:30:00"));
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sdf.parse("2015-10-01 13:30:00"));
		Calendar c3 = Calendar.getInstance();
		c3.setTime(sdf.parse("2015-10-01 17:30:00"));
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 2500; j++) {
				addData1(dbConn,SysTool.getDateTimeStr(c.getTime()));
				addData2(dbConn,SysTool.getDateTimeStr(c1.getTime()));
				addData3(dbConn,SysTool.getDateTimeStr(c2.getTime()));
				addData4(dbConn,SysTool.getDateTimeStr(c3.getTime()));
				c.add(Calendar.DATE, 1);
				c1.add(Calendar.DATE, 1);
				c2.add(Calendar.DATE, 1);
				c3.add(Calendar.DATE, 1);
			}
			dbConn.commit();
		}
		dbConn.close();
		System.out.println("*********************考勤创建完成**********************");
		System.out.println("用时:"+(new Date().getTime()/1000-time)+"秒");
	}
	
	public void addData1(Connection conn,String date1)throws Exception{
		String sql = "INSERT INTO ATTEND(ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,FROM_TYPE,IS_VALID,ATTEND_TIME_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, date1);
		ps.setString(3, "1");
		ps.setString(4, "");
		ps.setString(5, "");
		ps.setString(6, "1");
		ps.setString(7, "1");
		ps.setString(8, "1");
		ps.setString(9, "0D90DAE0-0E0D-E332-ABE5-985908551907");
		ps.setString(10, "admin");
		ps.setString(11, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();
	}
	
	public void addData2(Connection conn,String date)throws Exception{
		String sql = "INSERT INTO ATTEND(ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,FROM_TYPE,IS_VALID,ATTEND_TIME_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, date);
		ps.setString(3, "2");
		ps.setString(4, "");
		ps.setString(5, "");
		ps.setString(6, "1");
		ps.setString(7, "1");
		ps.setString(8, "1");
		ps.setString(9, "0D90DAE0-0E0D-E332-ABE5-985908551907");
		ps.setString(10, "admin");
		ps.setString(11, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();
	}
	
	public void addData3(Connection conn,String date)throws Exception{
		String sql = "INSERT INTO ATTEND(ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,FROM_TYPE,IS_VALID,ATTEND_TIME_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, date);
		ps.setString(3, "1");
		ps.setString(4, "");
		ps.setString(5, "");
		ps.setString(6, "1");
		ps.setString(7, "1");
		ps.setString(8, "1");
		ps.setString(9, "7F282A34-1E56-CFFC-AEDD-E5E8FC6F19F8");
		ps.setString(10, "admin");
		ps.setString(11, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();
	}
	
	public void addData4(Connection conn,String date)throws Exception{
		String sql = "INSERT INTO ATTEND(ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,FROM_TYPE,IS_VALID,ATTEND_TIME_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, date);
		ps.setString(3, "2");
		ps.setString(4, "");
		ps.setString(5, "");
		ps.setString(6, "1");
		ps.setString(7, "1");
		ps.setString(8, "1");
		ps.setString(9, "7F282A34-1E56-CFFC-AEDD-E5E8FC6F19F8");
		ps.setString(10, "admin");
		ps.setString(11, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();
	}
}
