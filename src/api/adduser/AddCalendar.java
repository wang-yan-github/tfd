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

public class AddCalendar {
	
	public static int CALENDAR_ID = 1;
	
	public void addCalendarAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("开始创建日程数据");
		long time = new Date().getTime()/1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse("2015-10-01 00:00"));
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sdf.parse("2015-10-01 00:30"));
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10000; j++) {
				addData(dbConn,SysTool.getDateTimeStr(c.getTime()),SysTool.getDateTimeStr(c1.getTime()));
				c.add(Calendar.HOUR, 1);
				c1.add(Calendar.HOUR, 1);
			}
			dbConn.commit();
		}
		dbConn.close();
		System.out.println("*********************日程创建完成**********************");
		System.out.println("用时:"+(new Date().getTime()/1000-time)+"秒");
	}
	
	public void addData(Connection conn,String startDate,String endDate)throws Exception{
		PreparedStatement ps = null;
		String queryStr = null;
		queryStr="INSERT INTO CALENDAR (CALENDAR_ID,CALENDAR_PID,START_DATE,END_DATE,CONTENT,CAL_TYPE,CAL_LEVEL,CAL_AFF_TYPE,BEFORE_TIME,ACCOUNT_ID,USER_ID,FROM_ID,IS_SMS,STATUS,FROM_TYPE,FROM_TYPE_ID,AFFAIR_ID,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		String id = GuId.getGuid();
		ps.setString(1, id);
		ps.setString(2, id);
		ps.setString(3, startDate);
		ps.setString(4, endDate);
		ps.setString(5, "日程"+CALENDAR_ID);
		ps.setString(6, "1");
		ps.setString(7, "1");
		ps.setString(8, "");
		ps.setString(9, "");
		ps.setString(10, "admin");
		ps.setString(11, "admin");
		ps.setString(12, "admin");
		ps.setString(13, "");
		ps.setString(14, "0");
		ps.setString(15, "");
		ps.setString(16, "");
		ps.setString(17,"");
		ps.setString(18, "8EADB678-A646-1E51-3E87-75A547B8AF19");
		ps.executeUpdate();
		ps.close();	
		CALENDAR_ID++;
	}
}
