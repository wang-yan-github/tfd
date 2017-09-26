package tfd.system.mobile.attend.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.attend.data.Attend;
import tfd.system.attend.data.AttendRegist;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;

public class AttendLogic {

	/**
	 * 查看我的考勤
	 * Time 2015-10-21
	 * Author:Wp
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String attendanceLogic(Connection conn,String userId,String orgId,int page,HttpServletRequest request)throws Exception{
		JSONObject returnjson=new JSONObject();
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT ATTEND_ID,REGIST_TIME,REMARK,PICTRUE,ADDRESS,DETAIL,LON,LAT FROM ATTEND WHERE ACCOUNT_ID =? AND ORG_ID=? ORDER BY REGIST_TIME DESC LIMIT "+(page*15)+",16";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.* FROM (SELECT ATTEND_ID,REGIST_TIME,REMARK,PICTRUE,ADDRESS,DETAIL,LON,LAT,ROWNUM AS RN FROM ATTEND WHERE ACCOUNT_ID =? AND ORG_ID=? ORDER BY REGIST_TIME DESC) t1 WHERE RN > "+(page*15)+" AND RN <= "+((page+1)*15+1);
		} 
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		JSONArray dataArr=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("date", rs.getString("REGIST_TIME"));
			JSONArray imgs = new JSONArray();
			if(rs.getString("PICTRUE")!=null){
				if(!rs.getString("PICTRUE").equals("")){
					String url = rs.getString("PICTRUE");
					if(url.indexOf(",")>-1){
						String[] urls = url.split(",");
						for (int j = 0; j < urls.length; j++) {
							imgs.add(urls[j]);
						}
					}else{
						imgs.add(url);
					}
				}
			}
			json.accumulate("image_urls", imgs);
			if(rs.getString("REMARK")==null){
				json.accumulate("content", "");
			}else{
				json.accumulate("content", rs.getString("REMARK"));
			}
			if(rs.getString("LON")==null){
				json.accumulate("lon", "");
			}else{
				json.accumulate("lon", rs.getString("LON"));
			}
			if(rs.getString("LAT")==null){
				json.accumulate("lat", "");
			}else{
				json.accumulate("lat", rs.getString("LAT"));
			}
			if(rs.getString("ADDRESS")==null){
				json.accumulate("address", "");
			}else{
				json.accumulate("address", rs.getString("ADDRESS"));
			}
			if(rs.getString("DETAIL")==null){
				json.accumulate("detail", "");
			}else{
				json.accumulate("detail", rs.getString("DETAIL"));
			}
			json.accumulate("id", rs.getString("ATTEND_ID"));
			dataArr.add(json);
		}
		rs.close();
		ps.close();
		JSONObject datajson=new JSONObject();
		datajson.accumulate("time", new Date().getTime()/1000);
		String havemore="1";
		if((page+1)*15>=this.getcount(conn, userId, orgId)){
			havemore="0";
		}
		datajson.accumulate("havemore", havemore);
		datajson.accumulate("page", ""+page+"");
		datajson.accumulate("data", dataArr);
		returnjson.accumulate("status_code", "200");
		returnjson.accumulate("msg", "获取列表成功");
		returnjson.accumulate("data", datajson);
		return returnjson.toString();
	}
	/**
	 * 根据用户查看有多少条考勤记录
	 * @param conn
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int getcount(Connection conn,String accountId,String orgId)throws Exception{
		int i=0;
		String queryStr="SELECT COUNT(ID) AS NUM FROM ATTEND WHERE ACCOUNT_ID=? AND ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			i=rs.getInt("NUM");
		}
		rs.close();
		ps.close();
		return i;
	}
	/**
	 * 提交考勤
	 * Time 2015-10-21
	 * Author :Wp
	 * @param conn
	 * @param attend
	 * @return
	 * @throws Exception
	 */
	public String checkattendanceLogic(Connection conn,Attend attend,String kind)throws Exception{
		JSONObject json=new JSONObject();
		String queryStr="INSERT INTO ATTEND (ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,ACCOUNT_ID,ORG_ID,ADDRESS,DETAIL,LON,LAT,FROM_TYPE,IS_VALID,ATTEND_TIME_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, attend.getAttendId());
		ps.setString(2, attend.getRegistTime());
		ps.setString(3, kind);
		ps.setString(4, attend.getRemark());
		ps.setString(5, attend.getPictrue());
		String statusStr = this.getStatus(conn, attend.getAccountId(), attend.getOrgId(), kind, attend.getRegistTime().substring(11,19));
		JSONObject statusJson = JSONObject.fromObject(statusStr);
		String status = "";
		String attendTimeId = "";
		if(attend.getIsValid().equals("1")){
			status = statusJson.getString("status");
			attendTimeId = statusJson.getString("attendTimeId");
		}
		ps.setString(6, status);
		ps.setString(7, attend.getAccountId());
		ps.setString(8, attend.getOrgId());
		ps.setString(9, attend.getAddress());
		ps.setString(10, attend.getDetail());
		ps.setString(11, attend.getLon());
		ps.setString(12, attend.getLat());
		ps.setString(13, "2");
		ps.setString(14, attend.getIsValid());
		ps.setString(15, attendTimeId);
		int i=ps.executeUpdate();
		JSONObject dataJson=new JSONObject();
		if(i>0){
			json.accumulate("status_code", "200");
			json.accumulate("msg", "添加成功");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			json.accumulate("data", dataJson);
		}else{
			json.accumulate("status_code", "500");
			json.accumulate("msg", "添加失败");
			dataJson.accumulate("time",new Date().getTime()/1000 );
			json.accumulate("data", dataJson);
		}
		ps.close();
		return json.toString();
	}
	/**
	 * 同事考勤
	 * Time 2015-10-26
	 * Author Wp
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String colleagueattendanceLogic(Connection conn,Account account,String time,HttpServletRequest request,int page)throws Exception{
		JSONObject returnJson=new JSONObject();
		JSONArray dataArr=new JSONArray();
		JSONObject jsonObj = new JSONObject();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountStr="";
		if(manageDept.equals("2")){
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}else{
			accountStr="SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?";
		}
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr="SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,LON,LAT,ADDRESS,DETAIL,ACCOUNT_ID FROM ATTEND WHERE EXISTS(SELECT * FROM ("+accountStr+") T4  WHERE T4.ACCOUNT_ID=ACCOUNT_ID ) AND ACCOUNT_ID != ? AND ORG_ID = ? ORDER BY REGIST_TIME DESC LIMIT "+(page*15)+",16";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT t1.* FROM (SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,LON,LAT,ADDRESS,DETAIL,ACCOUNT_ID FROM ATTEND WHERE ACCOUNT_ID IN ("+accountStr+") AND ACCOUNT_ID != ? AND ORG_ID = ? ORDER BY REGIST_TIME DESC) t1 WHERE RN > "+(page*15)+" AND RN <= "+((page+1)*15+1);
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		if(manageDept.equals("2")){
			ps.setString(1, userInfo.getDeptId());
			ps.setString(2, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = new UserLeaveLogic().getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(3, leaveNoId);
			ps.setString(4, userInfo.getLeadId());
			ps.setString(5, account.getAccountId());
			ps.setString(6, account.getOrgId());
		}else{
			String leaveNoId = new UserLeaveLogic().getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(1, leaveNoId);
			ps.setString(2, userInfo.getLeadId());
			ps.setString(3, account.getAccountId());
			ps.setString(4, account.getOrgId());
		}
		ResultSet rs = ps.executeQuery();
		int i = 0;
		while(rs.next()){
			i++;
			JSONObject json=new JSONObject();
			json.accumulate("userId", rs.getString("ACCOUNT_ID"));
			String name = new AccountLogic().getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
			json.accumulate("name", name);
			String avatar_url = SystemUtil.getHeadImgByAccountId(conn, rs.getString("ACCOUNT_ID"), request);
			json.accumulate("avatar_url",avatar_url );
			json.accumulate("date", rs.getString("REGIST_TIME"));
			if(rs.getString("REMARK")==null){
				json.accumulate("content", "");
			}else{
				json.accumulate("content", rs.getString("REMARK"));
			}
			if(rs.getString("LON")==null){
				json.accumulate("lon", "");
			}else{
				json.accumulate("lon", rs.getString("LON"));
			}
			if(rs.getString("LAT")==null){
				json.accumulate("lat", "");
			}else{
				json.accumulate("lat", rs.getString("LAT"));
			}
			if(rs.getString("ADDRESS")==null){
				json.accumulate("address", "");
			}else{
				json.accumulate("address", rs.getString("ADDRESS"));
			}
			if(rs.getString("DETAIL")==null){
				json.accumulate("detail", "");
			}else{
				json.accumulate("detail", rs.getString("DETAIL"));
			}
			json.accumulate("id", rs.getString("ATTEND_ID"));
			dataArr.add(json);
		}
		rs.close();
		ps.close();
		String havemore = "0";
		if(i == 16){
			havemore = "1";
		}
		jsonObj.accumulate("page",page );
		jsonObj.accumulate("havemore",havemore );
		jsonObj.accumulate("time", new Date().getTime()/1000);
		jsonObj.accumulate("data", dataArr);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg","获取同事考勤成功");
		returnJson.accumulate("data", jsonObj);
		
		return returnJson.toString();
	}
	public String checkClockingTime(Connection conn, Account account)throws Exception{
		JSONObject returnJson = new JSONObject();
		JSONObject dataJson = new JSONObject();
		JSONArray inJsonArr = new JSONArray();
		JSONArray outJsonArr = new JSONArray();
		AttendRegist attendRegist = this.getAttendRegist(conn, account.getOrgId());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.ATTEND_TIME_ID,t1.TIME1,t1.TIME2,t1.TIME_TYPE1,t1.TIME_TYPE2 "
				+ "FROM ATTEND_TIME t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID "
				+ "WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ORDER BY t1.TIME1 ASC ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject workJson = new JSONObject();
			String starttime = SysTool.getDateTimeStr(new Date(sdf.parse(rs.getString("TIME1")).getTime()-Integer.parseInt(attendRegist.getBeforeWork())*60000)).substring(11,19);
			workJson.accumulate("starttime", starttime);
			String endtime = SysTool.getDateTimeStr(new Date(sdf.parse(rs.getString("TIME1")).getTime()+Integer.parseInt(attendRegist.getAfterWork())*60000)).substring(11,19);
			workJson.accumulate("endtime", endtime);
			inJsonArr.add(workJson);
			JSONObject backJson = new JSONObject();
			starttime = SysTool.getDateTimeStr(new Date(sdf.parse(rs.getString("TIME2")).getTime()-Integer.parseInt(attendRegist.getBeforeBack())*60000)).substring(11,19);
			backJson.accumulate("starttime", starttime);
			endtime = SysTool.getDateTimeStr(new Date(sdf.parse(rs.getString("TIME2")).getTime()+Integer.parseInt(attendRegist.getAfterBack())*60000)).substring(11,19);
			backJson.accumulate("endtime", endtime);
			outJsonArr.add(backJson);
		}
		dataJson.accumulate("time", new Date().getTime()/1000);
		dataJson.accumulate("clocking-in", inJsonArr);
		dataJson.accumulate("clocking-out", outJsonArr);
		returnJson.accumulate("status_code", "200");
		returnJson.accumulate("msg","获取签到时间成功");
		returnJson.accumulate("data", dataJson);
		return returnJson.toString();
	}
	
	public AttendRegist getAttendRegist(Connection conn,String orgId)throws Exception{
		AttendRegist attendRegist = new AttendRegist();
		String sql  = "SELECT ATTEND_REGIST_ID,BEFORE_WORK,AFTER_WORK,BEFORE_BACK,AFTER_BACK,ORG_ID FROM"
				+ " ATTEND_REGIST WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			attendRegist.setAttendRegistId(rs.getString("ATTEND_REGIST_ID"));
			attendRegist.setBeforeWork(rs.getString("BEFORE_WORK"));
			attendRegist.setAfterWork(rs.getString("AFTER_WORK"));
			attendRegist.setBeforeBack(rs.getString("BEFORE_BACK"));
			attendRegist.setAfterBack(rs.getString("AFTER_BACK"));
			attendRegist.setOrgId(rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return attendRegist;
		
	}
	
	public String getStatus(Connection conn,String accountId,String orgId,String kind,String registTime)throws Exception{
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		AttendRegist attendRegist = this.getAttendRegist(conn, orgId);
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.ATTEND_TIME_ID,t1.TIME1,t1.TIME2,t1.TIME_TYPE1,t1.TIME_TYPE2 "
				+ "FROM ATTEND_TIME t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID "
				+ "WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ORDER BY t1.TIME1 ASC ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Date now = sdf.parse(registTime);
			if(kind.equals("1")){
				long starttime = sdf.parse(rs.getString("TIME1")).getTime()-Integer.parseInt(attendRegist.getBeforeWork())*60000;
				long time = sdf.parse(rs.getString("TIME1")).getTime();
				long endtime = sdf.parse(rs.getString("TIME1")).getTime()+Integer.parseInt(attendRegist.getAfterWork())*60000;
				if(now.getTime()>=starttime&&now.getTime()<=endtime){
					json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
					if(now.getTime()<=time&&now.getTime()<=endtime){
						json.accumulate("status", "1");
					}else if(now.getTime()>time){
						json.accumulate("status", "2");
					}
				}
			}else{
				long starttime = sdf.parse(rs.getString("TIME2")).getTime()-Integer.parseInt(attendRegist.getBeforeBack())*60000;
				long time = sdf.parse(rs.getString("TIME2")).getTime();
				long endtime = sdf.parse(rs.getString("TIME2")).getTime()+Integer.parseInt(attendRegist.getAfterBack())*60000;
				if(now.getTime()>=starttime&&now.getTime()<=endtime){
					json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
					if(now.getTime()>=time&&now.getTime()<=starttime){
						json.accumulate("status", "1");
					}else if(now.getTime()>time){
						json.accumulate("status", "3");
					}
				}
			}
		}
		return json.toString();
	}
}
