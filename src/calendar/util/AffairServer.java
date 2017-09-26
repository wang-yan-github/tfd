package calendar.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;

import com.system.servers.AutoServer;
import com.system.servers.AutoThreadTool;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class AffairServer extends AutoServer{
	public static byte[] onlineSync = new byte[1];
	@Override
	public void doTask() throws Exception {
		synchronized(AffairServer.onlineSync) {
			Connection dbConn =AutoThreadTool.getDbConn();
		    try {
		        this.doAffairCal(dbConn);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
		    	dbConn.commit();
		    }
		}
	}
	
	/**
	 * 查询周期性事务
	 * Time:2015-9-21
	 * Author:Yzz
	 * @param conn
	 * @throws Exception
	 */
	public void doAffairCal(Connection conn) throws Exception {
		String sql = "SELECT t1.CALENDAR_ID,t1.START_DATE,t1.END_DATE,t2.REMIND_TYPE,t2.REMIND_DATE,t2.REMIND_TIME,t2.END_WHILE,t2.IS_WEEK,t2.FREQUENCY,t1.IS_SMS,t1.ACCOUNT_ID,t1.USER_ID,t1.ORG_ID FROM CALENDAR t1,AFFAIR t2 WHERE t1.CALENDAR_PID = t2.CALENDAR_ID AND t1.CAL_AFF_TYPE = '1' AND t2.END_WHILE > sysdate() ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			String calendarId = rs.getString("CALENDAR_ID");
			String startDate = rs.getString("START_DATE");
			String endDate = rs.getString("END_DATE");
			String remindType = rs.getString("REMIND_TYPE");
			String remindDate = rs.getString("REMIND_DATE");
			String remindTime = rs.getString("REMIND_TIME");
			String endwhile = rs.getString("END_WHILE");
			String isWeek = rs.getString("IS_WEEK");
			String frequency = rs.getString("FREQUENCY");
			String smsRemindStr = rs.getString("IS_SMS");
			String accountId = rs.getString("ACCOUNT_ID");
			String orgId = rs.getString("ORG_ID");
			String userId = rs.getString("USER_ID");
			List<String> toAccountList = new ArrayList<String>();
			String[] userIds = null;
			if(userId!=null){
				if(userId.indexOf(",")>-1){
					userIds = userId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						toAccountList.add(userIds[i]);
					}
				}else{
					toAccountList.add(userId);
				}
			} 
			toAccountList.add(accountId);
			
			Account account = new Account();
			account.setAccountId(accountId);
			account.setOrgId(orgId);
			
			addSms(conn,calendarId,startDate,endDate,remindType,remindDate,remindTime,endwhile,isWeek,frequency,smsRemindStr,account,toAccountList);
		}
	}
	
	/**
	 * 添加到消息中心
	 * Time:2015-9-21
	 * Author:Yzz
	 * @param conn
	 * @param calendarId
	 * @param startDate
	 * @param remindType
	 * @param remindDate
	 * @param remindTime
	 * @param endwhile
	 * @param isWeek
	 * @param frequency
	 * @param smsRemindStr
	 * @param account
	 * @param toAccountList
	 * @throws Exception
	 */
	public void addSms(Connection conn,String calendarId,String startDate,String endDate,String remindType,String remindDate,String remindTime,String endwhile,String isWeek,String frequency,String smsRemindStr,Account account,List<String> toAccountList) throws Exception {
		JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
		MessageApi messageApi = new MessageApi();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String startDate1 = SysTool.getDateTimeStr(sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+startDate.substring(11,startDate.length())));
		Date start = sdf.parse(startDate.substring(0,10)+" 00:00");
	    Date now = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" 00:00");
	    int fNum = Integer.parseInt(frequency);
	    long spanNum = (now.getTime()-start.getTime())/(24*60*60*1000);
	    endDate = SysTool.getDateTimeStr(new Date(sdf.parse(startDate1).getTime() + (sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime())));
	    String sendTime = "";
		if(remindType.equals("2")){
			if(isWeek!=null){
				int num = getWorkDays(start, now);
				if(num%fNum==0){
					this.addCalendar(conn, startDate1, endDate,calendarId);
					sendTime = SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+remindTime;
					messageApi.addMessage(conn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
				}
			}else{
				if(spanNum%Integer.parseInt(frequency)==0){
					this.addCalendar(conn, startDate1, endDate,calendarId);
					sendTime = SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+remindTime;
					messageApi.addMessage(conn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
				}
			}
		}else if(remindType.equals("3")){
			int startNum = 7*fNum;
			int endNum = 7*(fNum+1);
			int weekNum = (int)spanNum/startNum;
			if(spanNum >= startNum*weekNum&&spanNum<=endNum*weekNum){
				String nowWeek = sdf2.format(new Date());
				String[] weeks = remindDate.split(",");
				boolean flag = false;
				for (int i = 0; i < weeks.length; i++) {
					if(nowWeek.equals(weeks[i])){
						flag = true;
						break;
					}
				}
				if(flag){
					this.addCalendar(conn, startDate1, endDate,calendarId);
					sendTime = SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+remindTime;
					messageApi.addMessage(conn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
				}
			}
		}else if(remindType.equals("4")){
			String today = SysTool.getDateTimeStr(new Date()).substring(8,10)+"日";
			if(today.startsWith("0")){
				today = today.substring(1,3);
			}
			String[] days = remindDate.split(",");
			boolean flag = false;
			for (int i = 0; i < days.length; i++) {
				if(today.equals(days[i])){
					flag = true;
					break;
				}
			}
			if(flag){
				this.addCalendar(conn, startDate1, endDate,calendarId);
				sendTime = SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+remindTime;
				messageApi.addMessage(conn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
			}
		}else if(remindType.equals("5")){
			String today = SysTool.getDateTimeStr(new Date()).substring(5,10);
			if(today.startsWith("0")){
				today = today.substring(1,5);
			}
			if(today.equals(remindDate)){
				this.addCalendar(conn, startDate1, endDate,calendarId);
				sendTime = SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+remindTime;
				messageApi.addMessage(conn, "calendar", smsRemindJson, "您有新的日程，请注意查看。", account.getAccountId(), toAccountList, account.getOrgId(), sendTime);
			}
		}
	}
	
	public static int getWorkDays(Date dateFrom,Date dateTo) throws Exception {
        //工作天数
        int workdays = 0;
 
        while(dateFrom.before(dateTo)){
             Calendar cal = Calendar.getInstance();
             //设置日期
            cal.setTime(dateFrom);
            if((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
                    &&(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)){
                //进行比较
                workdays++;
            }
            //日期加1
            cal.add(Calendar.DAY_OF_MONTH,1);
            dateFrom = cal.getTime();
            cal=null;
        }
        return workdays;
    }
	
	public static int getMonthSpace(String date1, String date2)
			throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result);

	}
	
	public void addCalendar(Connection conn, String startDate,String endDate,String calendarPid)throws Exception{
		System.out.println(startDate);
		System.out.println(endDate);
		String sql = "INSERT INTO CALENDAR(CALENDAR_ID,CALENDAR_PID,START_DATE,END_DATE,"
				+ "CONTENT,CAL_TYPE,CAL_LEVEL,CAL_AFF_TYPE,BEFORE_TIME,ACCOUNT_ID,USER_ID,"
				+ "FROM_ID,IS_SMS,STATUS,FROM_TYPE,FROM_TYPE_ID,AFFAIR_ID,ORG_ID) SELECT "
				+ "?,CALENDAR_PID,?,?,CONTENT,CAL_TYPE,CAL_LEVEL,"
				+ "?,BEFORE_TIME,ACCOUNT_ID,USER_ID,FROM_ID,IS_SMS,STATUS,FROM_TYPE,"
				+ "FROM_TYPE_ID,?,ORG_ID FROM CALENDAR WHERE CALENDAR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, GuId.getGuid());
		ps.setString(2, startDate);
		ps.setString(3, endDate);
		ps.setString(4, "");
		ps.setString(5, "");
		ps.setString(6, calendarPid);
		ps.executeUpdate();
		ps.close();
	} 
	
}
