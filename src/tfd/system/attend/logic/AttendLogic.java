package tfd.system.attend.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.component.datamodel.Record;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.attend.data.Attend;
import tfd.system.attend.data.AttendConfig;
import tfd.system.attend.data.AttendRegist;
import tfd.system.attend.data.AttendTime;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

public class AttendLogic {
	
	JSONArray jsonArrCount = new JSONArray();
	List<Record> records=new ArrayList<Record>();
	
	/**
	 * 添加排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfig
	 * @return
	 * @throws Exception
	 */
	public int addAttendConfig(Connection conn,AttendConfig attendConfig)throws Exception{
		String sql = "INSERT INTO ATTEND_CONFIG(ATTEND_CONFIG_ID,CONFIG_NAME,CONFIG_TYPE,"
				+ "ATTEND_TIME_ID,ATTEND_TIME_ID2,ATTEND_TIME_ID3,ORG_ID) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfig.getAttendConfigId());
		ps.setString(2, attendConfig.getConfigName());
		ps.setString(3, attendConfig.getConfigType());
		ps.setString(4, attendConfig.getAttendTimeId());
		ps.setString(5, attendConfig.getAttendTimeId2());
		ps.setString(6, attendConfig.getAttendTimeId3());
		ps.setString(7, attendConfig.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 添加排版类型时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendTime
	 * @return
	 * @throws Exception
	 */
	public String addAttendTime(Connection conn,AttendTime attendTime)throws Exception{
		String sql = "INSERT INTO ATTEND_TIME(ATTEND_TIME_ID,ATTEND_CONFIG_ID,TIME1,TIME2,"
				+ "TIME_TYPE1,TIME_TYPE2,ORG_ID) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendTime.getAttendTimeId());
		ps.setString(2, attendTime.getAttendConfigId());
		ps.setString(3, attendTime.getTime1());
		ps.setString(4, attendTime.getTime2());
		ps.setString(5, attendTime.getTimeType1());
		ps.setString(6, attendTime.getTimeType2());
		ps.setString(7, attendTime.getOrgId());
		ps.executeUpdate();
		ps.close();
		return attendTime.getAttendTimeId();
	}
	
	/**
	 * 添加排版类型列表
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendConfigList(Connection conn,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT ATTEND_CONFIG_ID,CONFIG_NAME,CONFIG_TYPE,ATTEND_TIME_ID,ATTEND_TIME_ID2,"
				+ "ATTEND_TIME_ID3,ORG_ID FROM ATTEND_CONFIG WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			AttendTime attendTime = new AttendTime();
			json.accumulate("attendConfigId", rs.getString("ATTEND_CONFIG_ID"));
			json.accumulate("configName", rs.getString("CONFIG_NAME"));
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID"), orgId);
			json.accumulate("time1", attendTime.getTime1());
			json.accumulate("time2", attendTime.getTime2());
			json.accumulate("timeType1", attendTime.getTimeType1());
			json.accumulate("timeType2", attendTime.getTimeType2());
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID2"), orgId);
			json.accumulate("time3", attendTime.getTime1());
			json.accumulate("time4", attendTime.getTime2());
			json.accumulate("timeType3", attendTime.getTimeType1());
			json.accumulate("timeType4", attendTime.getTimeType2());
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID3"), orgId);
			json.accumulate("time5", attendTime.getTime1());
			json.accumulate("time6", attendTime.getTime2());
			json.accumulate("timeType5", attendTime.getTimeType1());
			json.accumulate("timeType6", attendTime.getTimeType2());
			json.accumulate("orgId", rs.getString("ORG_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据Id获取排版类型时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendTimeId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public AttendTime getAttendTimeById(Connection conn,String attendTimeId,String orgId)throws Exception{
		AttendTime attendTime = new AttendTime();
		String sql  = "SELECT ATTEND_TIME_ID,TIME1,TIME2,TIME_TYPE1,TIME_TYPE2,ORG_ID FROM"
				+ " ATTEND_TIME WHERE ATTEND_TIME_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendTimeId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			attendTime.setAttendTimeId(rs.getString("ATTEND_TIME_ID"));
			attendTime.setTime1(rs.getString("TIME1"));
			attendTime.setTime2(rs.getString("TIME2"));
			String type = rs.getString("TIME_TYPE1");
			if(type.equals("1")){
				type = "上班";
			}else{
				type = "下班";
			}
			attendTime.setTimeType1(type);
			type = rs.getString("TIME_TYPE2");
			if(type.equals("1")){
				type = "上班";
			}else{
				type = "下班";
			}
			attendTime.setTimeType2(type);
			attendTime.setOrgId(rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return attendTime;
	}
	
	/**
	 * 根据Id获取排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfigId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendConfigById(Connection conn,String attendConfigId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT ATTEND_CONFIG_ID,CONFIG_NAME,CONFIG_TYPE,ATTEND_TIME_ID,ATTEND_TIME_ID2,"
				+ "ATTEND_TIME_ID3,PUBLIC_DAY,ORG_ID FROM ATTEND_CONFIG WHERE ATTEND_CONFIG_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfigId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			AttendTime attendTime = new AttendTime();
			json.accumulate("attendConfigId", rs.getString("ATTEND_CONFIG_ID"));
			json.accumulate("configName", rs.getString("CONFIG_NAME"));
			json.accumulate("configType", rs.getString("CONFIG_TYPE"));
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID"), orgId);
			json.accumulate("time1", attendTime.getTime1());
			json.accumulate("time2", attendTime.getTime2());
			json.accumulate("timeType1", attendTime.getTimeType1());
			json.accumulate("timeType2", attendTime.getTimeType2());
			json.accumulate("attendTimeId2", rs.getString("ATTEND_TIME_ID2"));
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID2"), orgId);
			json.accumulate("time3", attendTime.getTime1());
			json.accumulate("time4", attendTime.getTime2());
			json.accumulate("timeType3", attendTime.getTimeType1());
			json.accumulate("timeType4", attendTime.getTimeType2());
			json.accumulate("attendTimeId3", rs.getString("ATTEND_TIME_ID3"));
			attendTime = this.getAttendTimeById(conn, rs.getString("ATTEND_TIME_ID3"), orgId);
			json.accumulate("time5", attendTime.getTime1());
			json.accumulate("time6", attendTime.getTime2());
			json.accumulate("timeType5", attendTime.getTimeType1());
			json.accumulate("timeType6", attendTime.getTimeType2());
			json.accumulate("publicDay", rs.getString("PUBLIC_DAY"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 修改排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfig
	 * @return
	 * @throws Exception
	 */
	public int updateAttendConfig(Connection conn,AttendConfig attendConfig)throws Exception{
		String sql = "UPDATE ATTEND_CONFIG SET CONFIG_NAME = ?,CONFIG_TYPE = ?,ATTEND_TIME_ID = ?"
				+ ",ATTEND_TIME_ID2 = ?,ATTEND_TIME_ID3 = ? WHERE ATTEND_CONFIG_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfig.getConfigName());
		ps.setString(2, attendConfig.getConfigType());
		ps.setString(3, attendConfig.getAttendTimeId());
		ps.setString(4, attendConfig.getAttendTimeId2());
		ps.setString(5, attendConfig.getAttendTimeId3());
		ps.setString(6, attendConfig.getAttendConfigId());
		ps.setString(7, attendConfig.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 修改排版类型时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendTime
	 * @return
	 * @throws Exception
	 */
	public String updateAttendTime(Connection conn,AttendTime attendTime)throws Exception{
		AttendTime attendTime1 = this.getAttendTimeById(conn, attendTime.getAttendTimeId(), attendTime.getOrgId());
		if(attendTime1.getAttendTimeId().equals("")){
			attendTime.setAttendTimeId(GuId.getGuid());
			this.addAttendTime(conn, attendTime);
		}else{
			String sql = "UPDATE ATTEND_TIME SET TIME1 = ?,TIME2 = ?,TIME_TYPE1 = ?, TIME_TYPE2 = ? WHERE ATTEND_TIME_ID = ? AND ORG_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, attendTime.getTime1());
			ps.setString(2, attendTime.getTime2());
			ps.setString(3, attendTime.getTimeType1());
			ps.setString(4, attendTime.getTimeType2());
			ps.setString(5, attendTime.getAttendTimeId());
			ps.setString(6, attendTime.getOrgId());
			ps.executeUpdate();
			ps.close();
		}
		return attendTime.getAttendTimeId();
	}
	
	/**
	 * 删除排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfigId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int delAttendConfigById(Connection conn,String attendConfigId,String orgId)throws Exception{
		String sql = "DELETE FROM ATTEND_CONFIG WHERE ATTEND_CONFIG_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfigId);
		ps.setString(2, orgId);
		int i = ps.executeUpdate();
		this.delAttendTimeById(conn, attendConfigId, orgId);
		ps.close();
		return i;
	}
	
	/**
	 * 根据排版类型Id删除排版类型时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfigId
	 * @param orgId
	 * @throws Exception
	 */
	public void delAttendTimeById(Connection conn,String attendConfigId,String orgId)throws Exception{
		String sql = "DELETE FROM ATTEND_TIME WHERE ATTEND_CONFIG_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfigId);
		ps.setString(2, orgId);
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * 根据Id获取登记规则
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendRegistById(Connection conn,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		String sql  = "SELECT ATTEND_REGIST_ID,BEFORE_WORK,AFTER_WORK,BEFORE_BACK,AFTER_BACK,ORG_ID FROM"
				+ " ATTEND_REGIST WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("attendRegistId", rs.getString("ATTEND_REGIST_ID"));
			json.accumulate("beforeWork", rs.getString("BEFORE_WORK"));
			json.accumulate("afterWork", rs.getString("AFTER_WORK"));
			json.accumulate("beforeBack", rs.getString("BEFORE_BACK"));
			json.accumulate("afterBack", rs.getString("AFTER_BACK"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 修改登记规则
	 * @param conn
	 * @param attendRegist
	 * @return
	 * @throws Exception
	 */
	public int editAttendRegist(Connection conn,AttendRegist attendRegist)throws Exception{
		String sql = "UPDATE ATTEND_REGIST SET BEFORE_WORK = ?,AFTER_WORK = ?,BEFORE_BACK = ?,AFTER_BACK = ? WHERE ATTEND_REGIST_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,attendRegist.getBeforeWork());
		ps.setString(2,attendRegist.getAfterWork());
		ps.setString(3,attendRegist.getBeforeBack());
		ps.setString(4,attendRegist.getAfterBack());
		ps.setString(5, attendRegist.getAttendRegistId());
		ps.setString(6, attendRegist.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 添加登记规则
	 * @param conn
	 * @param attendRegist
	 * @return
	 * @throws Exception
	 */
	public int addAttendRegist(Connection conn,AttendRegist attendRegist)throws Exception{
		String sql = "INSERT INTO ATTEND_REGIST(ATTEND_REGIST_ID,BEFORE_WORK,AFTER_WORK,BEFORE_BACK,AFTER_BACK,ORG_ID)VALUES(?,?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendRegist.getAttendRegistId());
		ps.setString(2,attendRegist.getBeforeWork());
		ps.setString(3,attendRegist.getAfterWork());
		ps.setString(4,attendRegist.getBeforeBack());
		ps.setString(5,attendRegist.getAfterBack());
		ps.setString(6, attendRegist.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 更改用户的排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param accountId
	 * @param attendConfigId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int changeUserAttendConfig(Connection conn,String accountId,String attendConfigId,String orgId)throws Exception{
		String sql = "UPDATE USER_INFO SET ATTEND_CONFIG_ID = ? WHERE ACCOUNT_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfigId);
		ps.setString(2, accountId);
		ps.setString(3, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 修改部门内人员的排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param DeptId
	 * @param attendConfigId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int changeDeptAttendConfig(Connection conn,String DeptId,String attendConfigId,String orgId)throws Exception{
		String sql = "UPDATE USER_INFO SET ATTEND_CONFIG_ID = ? WHERE DEPT_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendConfigId);
		ps.setString(2, DeptId);
		ps.setString(3, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 获取今天的考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getTodayRegistById(Connection conn,Account account)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.ATTEND_TIME_ID,t1.TIME1,t1.TIME2,t1.TIME_TYPE1,t1.TIME_TYPE2 "
				+ "FROM ATTEND_TIME t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID "
				+ "WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ORDER BY t1.TIME1 ASC ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			String time = rs.getString("TIME1");
			String type = rs.getString("TIME_TYPE1");
			if(this.isTimeRegist(conn, time, type, account.getOrgId())){
				json.accumulate("editable","true");
			}else{
				json.accumulate("editable","false");
			}
			json.accumulate("time", time);
			json.accumulate("type", type);
			Attend attend = this.getAttendById(conn,rs.getString("ATTEND_TIME_ID"), type, account.getAccountId(),SysTool.getDateTimeStr(new Date()).substring(0, 10));
			json.accumulate("status", attend.getStatus());
			if(!attend.getStatus().equals("4")){
				json.accumulate("registTime", attend.getRegistTime());
				json.accumulate("attendId", attend.getAttendId());
				json.accumulate("address", attend.getAddress());
			}else{
				json.accumulate("address", "");
			}
			jsonArr.add(json);
			jsonArr = getTimeJsonArr(conn,jsonArr,rs.getString("ATTEND_TIME_ID"),rs.getString("TIME2"),rs.getString("TIME_TYPE2"),account.getAccountId(),account.getOrgId(),SysTool.getDateTimeStr(new Date()).substring(0, 10));
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 获取下班考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param jsonArr
	 * @param attendTimeId
	 * @param time
	 * @param type
	 * @param accountId
	 * @param orgId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public JSONArray getTimeJsonArr(Connection conn,JSONArray jsonArr,String attendTimeId,String time,String type,String accountId,String orgId,String date)throws Exception{
		JSONObject json = new JSONObject();
		json.accumulate("attendTimeId", attendTimeId);
		if(this.isTimeRegist(conn, time, type, orgId)){
			json.accumulate("editable","true");
		}else{
			json.accumulate("editable","false");
		}
		json.accumulate("time", time);
		json.accumulate("type", type);
		Attend attend = this.getAttendById(conn,attendTimeId, type, accountId,date);
		json.accumulate("status", attend.getStatus());
		if(!attend.getStatus().equals("4")){
			json.accumulate("registTime", attend.getRegistTime());
			json.accumulate("attendId", attend.getAttendId());
			json.accumulate("address", attend.getAddress());
			json.accumulate("remark", attend.getRemark());
			json.accumulate("pictrue", attend.getPictrue());
		}else{
			json.accumulate("address", "");
		}
		jsonArr.add(json);
		return jsonArr;
	}
	
	/**
	 * 判断现在是否是可登记时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param time
	 * @param type
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public boolean isTimeRegist(Connection conn,String time,String type,String orgId)throws Exception{
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String jsonStr = this.getAttendRegistById(conn, orgId);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		long now = new Date().getTime();
		if(type.equals("1")){
			long temp1 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+time).getTime()+Integer.parseInt(jsonObj.getString("afterWork"))*60000;
			long temp2 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+time).getTime()-Integer.parseInt(jsonObj.getString("beforeWork"))*60000;
			if(now<=temp1&&now>=temp2){
				flag = true;
			}
		}else{
			long temp1 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+time).getTime()+Integer.parseInt(jsonObj.getString("afterBack"))*60000;
			long temp2 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+time).getTime()-Integer.parseInt(jsonObj.getString("beforeBack"))*60000;
			if(now<=temp1&&now>=temp2){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 考勤登记
	 * Time:2015-10-16
	 * Author:Yzz
	 * @param conn
	 * @param attend 
	 * @return
	 * @throws Exception
	 */
	public int addAttend(Connection conn,Attend attend)throws Exception{
		String sql = "INSERT INTO ATTEND(ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,PICTRUE,STATUS,FROM_TYPE,IS_VALID,ATTEND_TIME_ID,ACCOUNT_ID,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attend.getAttendId());
		ps.setString(2, SysTool.getDateTimeStr(new Date()));
		ps.setString(3, attend.getRegistType());
		ps.setString(4, attend.getRemark());
		ps.setString(5, attend.getPictrue());
		String status = getAttendStatus(conn,attend.getRegistType(),attend.getAttendTimeId(),attend.getOrgId());
		ps.setString(6, status);
		ps.setString(7, "1");
		ps.setString(8, "1");
		ps.setString(9, attend.getAttendTimeId());
		ps.setString(10, attend.getAccountId());
		ps.setString(11, attend.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 获取考勤状态
	 * Time:2015-10-16
	 * Author:Yzz
	 * @param conn
	 * @param type
	 * @param attendTimeId
	 * @return Status:1-正常考勤,2-迟到,3-早退
	 * @throws Exception
	 */
	public String getAttendStatus(Connection conn,String type,String attendTimeId,String orgId)throws Exception{
		String status = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AttendTime attendTime = this.getAttendTimeById(conn, attendTimeId, orgId);
		String jsonStr = this.getAttendRegistById(conn, orgId);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		long now = new Date().getTime();
		if(type.equals("1")){
			long temp = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+attendTime.getTime1()).getTime();
			long temp2 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+attendTime.getTime1()).getTime()-Integer.parseInt(jsonObj.getString("beforeWork"))*60000;
			if(now<=temp&&now>=temp2){
				status = "1";
			}else if(now>temp){
				status = "2";
			}
		}else{
			long temp = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+attendTime.getTime2()).getTime();
			long temp1 = sdf.parse(SysTool.getDateTimeStr(new Date()).substring(0,10)+" "+attendTime.getTime2()).getTime()+Integer.parseInt(jsonObj.getString("afterBack"))*60000;
			if(now>=temp&&now<=temp1){
				status = "1";
			}else if(now<temp){
				status = "3";
			}
		}
		return status;
	}
	
	/**
	 * 根据Id获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendTimeId
	 * @param type
	 * @param accountId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Attend getAttendById(Connection conn,String attendTimeId,String type,String accountId,String date)throws Exception{
		Attend attend = new Attend();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date+" 00:00:00"));
		c.add(Calendar.DATE, 1);
		String date1 = SysTool.getDateTimeStr(c.getTime());
		String sql = "SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,STATUS,ADDRESS,REMARK,PICTRUE FROM ATTEND WHERE ATTEND_TIME_ID = ? AND REGIST_TIME > ? AND REGIST_TIME < ? AND REGIST_TYPE = ? AND ACCOUNT_ID = ? AND IS_VALID = '1' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendTimeId);
		ps.setString(2, date);
		ps.setString(3, date1);
		ps.setString(4, type);
		ps.setString(5, accountId);
		ResultSet rs = ps.executeQuery();
		int i = 0;
		while(rs.next()){
			i = 1;
			attend.setAttendId(rs.getString("ATTEND_ID"));
			attend.setRegistTime(rs.getString("REGIST_TIME"));
			attend.setStatus(rs.getString("STATUS"));
			attend.setAddress(rs.getString("ADDRESS"));
			attend.setRemark(rs.getString("REMARK"));
			attend.setPictrue(rs.getString("PICTRUE"));
		}
		if(i==0){
			attend.setStatus("4");
		}
		rs.close();
		ps.close();
		return attend;
	}
	
	/**
	 * 根据Id获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendById(Connection conn,String attendId,String orgId)throws Exception{
		JSONObject json = new JSONObject();
		
		String sql = "SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,PICTRUE,REMARK,ADDRESS,DETAIL,STATUS FROM ATTEND WHERE ATTEND_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attendId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("attendId", rs.getString("ATTEND_ID"));
			json.accumulate("registTime", rs.getString("REGIST_TIME"));
			json.accumulate("registType", rs.getString("REGIST_Type"));
			json.accumulate("pictrue", rs.getString("PICTRUE"));
			json.accumulate("remark", rs.getString("REMARK"));
			json.accumulate("address", rs.getString("ADDRESS"));
			json.accumulate("detail", rs.getString("DETAIL"));
			json.accumulate("status", rs.getString("STATUS"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 修改考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attend
	 * @return
	 * @throws Exception
	 */
	public int updateAttend(Connection conn,Attend attend)throws Exception{
		String sql = "UPDATE ATTEND SET REMARK = ?,PICTRUE = ? WHERE ATTEND_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, attend.getRemark());
		ps.setString(2, attend.getPictrue());
		ps.setString(3, attend.getAttendId());
		ps.setString(4, attend.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 修改登记时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendId
	 * @param type
	 * @param attendTimeId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int updateRegistTime(Connection conn,String attendId,String type,String attendTimeId,String orgId)throws Exception{
		String sql = "UPDATE ATTEND SET REGIST_TIME = ?,STATUS = ? WHERE ATTEND_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, SysTool.getDateTimeStr(new Date()));
		String status = this.getAttendStatus(conn, type, attendTimeId, orgId);
		ps.setString(2, status);
		ps.setString(3, attendId);
		ps.setString(4, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 修改公共休息日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param attendConfigId
	 * @param publicDay
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int editPublicDay(Connection conn,String attendConfigId,String publicDay,String orgId)throws Exception{
		String sql = "UPDATE ATTEND_CONFIG SET PUBLIC_DAY = ? WHERE ATTEND_CONFIG_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, publicDay);
		ps.setString(2, attendConfigId);
		ps.setString(3, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i ;
	}
	
	/**
	 * 根据月份获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getMonthRegistById(Connection conn,Account account,String dayNum)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String startDate = SysTool.getDateTimeStr(new Date()).substring(0,7)+"-01 00:00:00";
		String endDate = SysTool.getDateTimeStr(new Date()).substring(0,7)+"-"+dayNum+" 23:59:59";
		String sql = "SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,STATUS,ADDRESS,ACCOUNT_ID,ATTEND_TIME_ID FROM ATTEND WHERE REGIST_TIME > ? AND REGIST_TIME < ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, startDate);
		ps.setString(2, endDate);
		ps.setString(3, account.getAccountId());
		ps.setString(4, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("attendId", rs.getString("ATTEND_ID"));
			json.accumulate("registTime", rs.getString("REGIST_TIME"));
			json.accumulate("registType", rs.getString("REGIST_TYPE"));
			json.accumulate("remark", rs.getString("REMARK"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("address", rs.getString("ADDRESS"));
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 获取节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendHoliday(Connection conn,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT t1.HOLIDAY_ID,t1.HOLIDAY_NAME,t1.START_DATE,t1.END_DATE "
				+ " FROM ATTEND_HOLIDAY t1 WHERE t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("holidayId", rs.getString("HOLIDAY_ID"));
			json.accumulate("holidayName", rs.getString("HOLIDAY_NAME"));
			json.accumulate("startDate", rs.getString("START_DATE"));
			json.accumulate("endDate", rs.getString("END_DATE"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	/**
	 * 添加节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param holidayId
	 * @param holidayName
	 * @param startDate
	 * @param endDate
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int addAttendHoliday(Connection conn, String holidayId,String holidayName, String startDate, String endDate, String orgId)throws Exception {
		String sql = "INSERT INTO ATTEND_HOLIDAY(HOLIDAY_ID,HOLIDAY_NAME,START_DATE,END_DATE,ORG_ID) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, holidayId);
		ps.setString(2, holidayName);
		ps.setString(3, startDate);
		ps.setString(4, endDate);
		ps.setString(5, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 更改节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param holidayId
	 * @param holidayName
	 * @param startDate
	 * @param endDate
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int editAttendHoliday(Connection conn, String holidayId,String holidayName, String startDate, String endDate, String orgId)throws Exception {
		String sql = "UPDATE ATTEND_HOLIDAY SET HOLIDAY_NAME = ?,START_DATE = ?,END_DATE = ? WHERE HOLIDAY_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, holidayName);
		ps.setString(2, startDate);
		ps.setString(3, endDate);
		ps.setString(4, holidayId);
		ps.setString(5, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 删除节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param holidayId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int delAttendHoliday(Connection conn,String holidayId,String orgId)throws Exception{
		String sql = "DELETE FROM ATTEND_HOLIDAY WHERE HOLIDAY_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, holidayId);
		ps.setString(2, orgId);
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}

	/**
	 * 根据Id获取节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param holidayId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getAttendHolidayById(Connection conn, String holidayId,String orgId) throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT  t1.HOLIDAY_ID,t1.HOLIDAY_NAME,t1.START_DATE,t1.END_DATE "
				+ " FROM ATTEND_HOLIDAY t1 WHERE t1.HOLIDAY_ID = ? AND t1.ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, holidayId);
		ps.setString(2, orgId);
		ResultSet rs= ps.executeQuery();
		if(rs.next()){
			json.accumulate("holidayId", rs.getString("HOLIDAY_ID"));
			json.accumulate("holidayName", rs.getString("HOLIDAY_NAME"));
			json.accumulate("startDate", rs.getString("START_DATE"));
			json.accumulate("endDate", rs.getString("END_DATE"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 根据日期获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getRegistByDay(Connection conn,String date,Account account)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.ATTEND_TIME_ID,t1.TIME1,t1.TIME2,t1.TIME_TYPE1,t1.TIME_TYPE2 "
				+ "FROM ATTEND_TIME t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID "
				+ "WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ORDER BY t1.TIME1 ASC ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			String time = rs.getString("TIME1");
			String type = rs.getString("TIME_TYPE1");
			json.accumulate("time", time);
			json.accumulate("type", type);
			Attend attend = this.getAttendById(conn,rs.getString("ATTEND_TIME_ID"), type, account.getAccountId(),date);
			json.accumulate("status", attend.getStatus());
			if(!attend.getStatus().equals("4")){
				json.accumulate("registTime", attend.getRegistTime());
				json.accumulate("attendId", attend.getAttendId());
				json.accumulate("address", attend.getAddress());
				json.accumulate("remark", attend.getRemark());
				json.accumulate("pictrue", attend.getPictrue());
			}
			jsonArr.add(json);
			jsonArr = getTimeJsonArr(conn,jsonArr,rs.getString("ATTEND_TIME_ID"),rs.getString("TIME2"),rs.getString("TIME_TYPE2"),account.getAccountId(),account.getOrgId(),date);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	/**
	 * 获取排版类型的公共休息日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getAttendConfig(Connection conn, Account account)throws Exception {
		JSONObject json = new JSONObject();
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.PUBLIC_DAY FROM ATTEND_CONFIG t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			json.accumulate("attendConfigId", rs.getString("ATTEND_CONFIG_ID"));
			json.accumulate("publicDay", rs.getString("PUBLIC_DAY"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	/**
	 * 判断是否是节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String IsHoliday(Connection conn, String date, Account account)throws Exception{
		JSONObject json = new JSONObject();
		String sql = "SELECT HOLIDAY_ID,HOLIDAY_NAME FROM ATTEND_HOLIDAY WHERE  START_DATE <=  ? AND END_DATE >= ?  AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, date);
		ps.setString(2, date);
		ps.setString(3, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			String holidayName = rs.getString("HOLIDAY_NAME");
			if(holidayName==null){
				holidayName = "";
			}
			json.accumulate("holidayName", holidayName);
		}else{
			json.accumulate("holidayName", "");
		}
		rs.close();
		ps.close();
		return json.toString();
	}

	/**
	 * 统计考勤
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param deptId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String countAttend(Connection conn,String date,String deptId,Account account)throws Exception{
		if(deptId.equals("deptAll")){
			doCount(conn, date, "0", account);
		}else{
			if(deptId.indexOf(",")>-1){
				String[] deptIds = deptId.split(",");
				deptIds = checkDeptId(conn,deptIds,account.getOrgId());
				for (int i = 0; i < deptIds.length; i++) {
					doCount(conn, date, deptIds[i], account);
				}
			}else{
				doCount(conn, date, deptId, account);
			}
		}
		return jsonArrCount.toString();
	}
	
	/**
	 * 所属部门去重
	 * Time:2015-12-03
	 * Author:Yzz
	 * @param conn
	 * @param deptIds
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String[] checkDeptId(Connection conn,String[] deptIds,String orgId)throws Exception{
		String[] deptLongIds = new String[deptIds.length];
		String[] returnIds = new String[deptIds.length];
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < deptIds.length; i++) {
			String sql = "SELECT DEPT_ID,DEPT_LONG_ID FROM DEPARTMENT WHERE DEPT_ID = ? AND ORG_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptIds[i]);
			ps.setString(2, orgId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				list.add(rs.getString("DEPT_LONG_ID"));
				deptLongIds[i] = rs.getString("DEPT_LONG_ID");
			}
		}
		for (int i = 0; i < deptLongIds.length; i++) {
			for (int j = 0; j < deptLongIds.length; j++) {
				if(deptLongIds[j].indexOf(deptLongIds[i])>-1&&!deptLongIds[j].equals(deptLongIds[i])){
					if(list.contains(deptLongIds[j])){
						list.remove(deptLongIds[j]);
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
				String id = list.get(i).substring(list.get(i).lastIndexOf("/")+1,list.get(i).length());
				returnIds[i] = id;
			}
		}
		return returnIds;
	}
	
	/**
	 * 执行统计
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param deptId
	 * @param account
	 * @throws Exception
	 */
	public void doCount(Connection conn,String date,String deptId,Account account)throws Exception{
		if(this.deptIsParent(conn, deptId,account.getOrgId())){
			getAttendByTimeAndDept(conn, date, deptId, account);
			String sql = "SELECT DEPT_ID FROM DEPARTMENT WHERE ORG_LEAVE_ID = ? AND ORG_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptId);
			ps.setString(2, account.getOrgId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				doCount(conn, date, rs.getString("DEPT_ID"), account);
			}
			rs.close();
			ps.close();
		}else{
			getAttendByTimeAndDept(conn, date, deptId, account);
		}
	}
	
	/**
	 * 判断部门是否还有子部门
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param deptId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public boolean deptIsParent(Connection conn,String deptId,String orgId)throws Exception{
		boolean flag = false;
		String sql = "SELECT COUNT(ID) AS CHILD_COUNT FROM DEPARTMENT WHERE ORG_LEAVE_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, deptId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if(rs.getInt("CHILD_COUNT")>0){
				flag = true;
			}
		}
		rs.close();
		ps.close();
		return flag;
	}
	
//	/**
//	 * 根据时间和部门获取考勤
//	 * Time:2015-10-29
//	 * Author:Yzz
//	 * @param conn
//	 * @param date
//	 * @param deptId
//	 * @param account
//	 * @return
//	 * @throws Exception
//	 */
//	public void getAttendByTimeAndDept(Connection conn, String date,String deptId, Account account)throws Exception{
//		ArrayList<Map<String,String>> userList = new ArrayList<Map<String,String>>();
//		String queryStr="SELECT T1.ACCOUNT_ID AS ACCOUNT_ID,T1.USER_NAME AS USER_NAME FROM USER_INFO T1 LEFT JOIN ACCOUNT T2 ON T1.ACCOUNT_ID=T2.ACCOUNT_ID WHERE T2.NOT_LOGIN='0' AND T1.DEPT_ID=?";
//		PreparedStatement ps = conn.prepareStatement(queryStr);
//		ps.setString(1, deptId);
//		ResultSet rs =ps.executeQuery();
//		while(rs.next())
//		{
//			Map<String,String> userMap = new HashMap<String,String>();
//			userMap.put("accountId", rs.getString("ACCOUNT_ID"));
//			userMap.put("userName",rs.getString("USER_NAME"));
//			userList.add(userMap);
//		}
//		UserPrivLogic userPrivLogic=new UserPrivLogic();
//		List<Map<String, String>> userLists = new ArrayList<Map<String,String>>();
//		userLists = userPrivLogic.getRangeListByCalendarLogic(conn, account);		
//		if(userList.size()>0){
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			Calendar c = Calendar.getInstance();
//			c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
//			String startDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
//			c.add(Calendar.MONTH, 1);
//			String endDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
//			for(int i=0;userList.size()>i;i++){
//				Map<String,String> tmpMap = userList.get(i);
//				if(userLists.contains(tmpMap)||tmpMap.get("accountId").equals(account.getAccountId())){
//					JSONObject tmpJson = new JSONObject();
//					String sql = "SELECT (SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE "
//							+ "ACCOUNT_ID = ? AND IS_VALID = '1' AND REGIST_TIME >=  ?"
//							+ "AND REGIST_TIME <= ?) AS Actual,(SELECT COUNT(ATTEND_ID) FROM"
//							+ " ATTEND WHERE ACCOUNT_ID = ? AND IS_VALID = '1' AND STATUS = '1' "
//							+ "AND REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Normal,"
//							+ "(SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE ACCOUNT_ID = ? AND "
//							+ "IS_VALID = '1' AND STATUS = '2' AND REGIST_TIME >=  ? AND "
//							+ "REGIST_TIME <= ?) AS Later,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
//							+ "WHERE ACCOUNT_ID = ? AND IS_VALID = '1' AND STATUS = '3' AND "
//							+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Leavess,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
//							+ "WHERE ACCOUNT_ID = ? AND IS_VALID = '0' AND "
//							+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Address FROM DUAL";
//					ps = conn.prepareStatement(sql);
//					ps.setString(1, tmpMap.get("accountId"));
//					ps.setString(2, startDate);
//					ps.setString(3, endDate);
//					ps.setString(4, tmpMap.get("accountId"));
//					ps.setString(5, startDate);
//					ps.setString(6, endDate);
//					ps.setString(7, tmpMap.get("accountId"));
//					ps.setString(8, startDate);
//					ps.setString(9, endDate);
//					ps.setString(10, tmpMap.get("accountId"));
//					ps.setString(11, startDate);
//					ps.setString(12, endDate);
//					ps.setString(13, tmpMap.get("accountId"));
//					ps.setString(14, startDate);
//					ps.setString(15, endDate);
//					rs = ps.executeQuery();
//					Account acc = new AccountLogic().getAccountByAccountId(conn, tmpMap.get("accountId"));
//					int shouldNum = this.getAttendNumByDate(conn, date, acc);
//					String deptName = new DeptLogic().getDeptNameStr(conn, deptId);
//					String userName = new AccountLogic().getUserNameStr(conn, tmpMap.get("accountId"));
//					tmpJson.accumulate("accountId",tmpMap.get("accountId") );
//					tmpJson.accumulate("userName", userName);
//					tmpJson.accumulate("deptName", deptName);
//					tmpJson.accumulate("shouldNum", shouldNum);
//					rs.next();
//					tmpJson.accumulate("Actual",rs.getString("Actual"));
//					tmpJson.accumulate("Normal",rs.getString("Normal"));
//					tmpJson.accumulate("Later",rs.getString("Later"));
//					tmpJson.accumulate("Leave",rs.getString("Leavess"));
//					tmpJson.accumulate("Address", rs.getString("Address"));
//					
//					jsonArrCount.add(tmpJson);
//					
//				}
//			}
//		}
//		rs.close();
//		ps.close();
//	}
	
	/**
	 * 根据时间和部门获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param deptId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public void getAttendByTimeAndDept(Connection conn, String date,String deptId, Account account)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
		String startDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		c.add(Calendar.MONTH, 1);
		String endDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String sql =  "SELECT ACCOUNT_ID,USER_NAME,(SELECT DEPT_NAME FROM DEPARTMENT T2 WHERE T2.DEPT_ID = T1.DEPT_ID ) AS DEPT_NAME ,"
				+ "(SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE "
				+ "ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND REGIST_TIME >=  ?"
				+ "AND REGIST_TIME <= ?) AS Actual,(SELECT COUNT(ATTEND_ID) FROM"
				+ " ATTEND WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND STATUS = '1' "
				+ "AND REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Normal,"
				+ "(SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND "
				+ "IS_VALID = '1' AND STATUS = '2' AND REGIST_TIME >=  ? AND "
				+ "REGIST_TIME <= ?) AS Later,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
				+ "WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND STATUS = '3' AND "
				+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Leavess,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
				+ "WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '0' AND "
				+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Address FROM USER_INFO T1 WHERE "
				+ "EXISTS(SELECT * FROM (SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM "
				+ "USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L "
				+ "ON U.LEAD_LEAVE = L.LEAVE_ID WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN = '0' "
				+ "AND U.DEPT_ID = ?  AND U.LEAD_LEAVE=L.LEAVE_ID AND ( L.LEAVE_NO_ID<="
				+ "(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)) T4 "
				+ "WHERE T4.ACCOUNT_ID = T1.ACCOUNT_ID)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, startDate);
		ps.setString(2, endDate);
		ps.setString(3, startDate);
		ps.setString(4, endDate);
		ps.setString(5, startDate);
		ps.setString(6, endDate);
		ps.setString(7, startDate);
		ps.setString(8, endDate);
		ps.setString(9, startDate);
		ps.setString(10, endDate);
		ps.setString(11, deptId);
		ps.setString(12, userInfo.getLeadLeave());
		ps.setString(13, userInfo.getLeadId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject tmpJson = new JSONObject();
			Account acc = new AccountLogic().getAccountByAccountId(conn, rs.getString("ACCOUNT_ID"));
			//获取应该考勤数
			int shouldNum = this.getAttendNumByDate(conn, date, acc);
			tmpJson.accumulate("accountId",rs.getString("ACCOUNT_ID") );
			tmpJson.accumulate("userName", rs.getString("USER_NAME"));
			tmpJson.accumulate("deptName", rs.getString("DEPT_NAME"));
			tmpJson.accumulate("shouldNum", shouldNum);
			tmpJson.accumulate("Actual",rs.getString("Actual"));
			tmpJson.accumulate("Normal",rs.getString("Normal"));
			tmpJson.accumulate("Later",rs.getString("Later"));
			tmpJson.accumulate("Leave",rs.getString("Leavess"));
			tmpJson.accumulate("Address", rs.getString("Address"));
			jsonArrCount.add(tmpJson);
		}
		rs.close();
		ps.close();
	}
	
	/**
	 * 根据月份获取当月应有考勤数
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int getAttendNumByDate(Connection conn,String date,Account account)throws Exception{
		int returnData = 0;
		String sql = "SELECT T2.CONFIG_TYPE,T2.PUBLIC_DAY FROM USER_INFO T1 LEFT JOIN ATTEND_CONFIG T2 ON T1.ATTEND_CONFIG_ID = T2.ATTEND_CONFIG_ID  WHERE T1.ACCOUNT_ID = ? AND T1.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		String type = "";
		String publicDay = "";
		if(rs.next()){
			type = rs.getString("CONFIG_TYPE");
			publicDay = rs.getString("PUBLIC_DAY");
			if(publicDay == null){
				publicDay = "";
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
		int dayNum = c.getActualMaximum(Calendar.DATE);
		int num = 0;
		for (int i = 0; i <= dayNum; i++) {
			JSONObject holiDay = JSONObject.fromObject(IsHoliday(conn, SysTool.getDateTimeStr(c.getTime()).substring(0,10), account));
			String week = c.get(Calendar.DAY_OF_WEEK)+"";
			if(publicDay.indexOf(week)<0&&holiDay.getString("holidayName").equals("")){
				num++;
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		returnData = num * Integer.parseInt(type) * 2;
		rs.close();
		ps.close();
		return returnData;
	}

	/**
	 * 统计考勤导出
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param deptId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Record> countAttendExpart(Connection conn,String date,String deptId,Account account)throws Exception{
		if(deptId.equals("deptAll")){
			doCount(conn, date, "0", account);
		}else{
			if(deptId.indexOf(",")>-1){
				String[] deptIds = deptId.split(",");
				deptIds = checkDeptId(conn,deptIds,account.getOrgId());
				for (int i = 0; i < deptIds.length; i++) {
					doCount(conn, date, deptIds[i], account);
				}
			}else{
				doCountExpart(conn, date, deptId, account);
			}
		}
		return records;
	}
	
	/**
	 * 执行统计
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param date
	 * @param deptId
	 * @param account
	 * @throws Exception
	 */
	public void doCountExpart(Connection conn,String date,String deptId,Account account)throws Exception{
		if(this.deptIsParent(conn, deptId,account.getOrgId())){
			AttendListExport(conn, account, date, deptId);
			String sql = "SELECT DEPT_ID FROM DEPARTMENT WHERE ORG_LEAVE_ID = ? AND ORG_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptId);
			ps.setString(2, account.getOrgId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				doCountExpart(conn, date, rs.getString("DEPT_ID"), account);
			}
			rs.close();
			ps.close();
		}else{
			AttendListExport(conn, account, date, deptId);
		}
	}
	
	/**
	 * 添加内容到导出List
	 * Time:2015-12-04
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @param date
	 * @param deptId
	 * @throws Exception
	 */
	public void AttendListExport(Connection conn, Account account,String date, String deptId)throws Exception {
		Record record=new Record();
		record.addField("部门名称", "部门名称");
		record.addField("人员", "人员");
		record.addField("应有考勤", "应有考勤");
		record.addField("实有考勤", "实有考勤");
		record.addField("正常考勤", "正常考勤");
		record.addField("迟到", "迟到");
		record.addField("早退", "早退");
		record.addField("定位", "定位");
		records.add(record);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
		String startDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		c.add(Calendar.MONTH, 1);
		String endDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String sql =  "SELECT ACCOUNT_ID,USER_NAME,(SELECT DEPT_NAME FROM DEPARTMENT T2 WHERE T2.DEPT_ID = T1.DEPT_ID ) AS DEPT_NAME ,"
				+ "(SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE "
				+ "ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND REGIST_TIME >=  ?"
				+ "AND REGIST_TIME <= ?) AS Actual,(SELECT COUNT(ATTEND_ID) FROM"
				+ " ATTEND WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND STATUS = '1' "
				+ "AND REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Normal,"
				+ "(SELECT COUNT(ATTEND_ID) FROM ATTEND WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND "
				+ "IS_VALID = '1' AND STATUS = '2' AND REGIST_TIME >=  ? AND "
				+ "REGIST_TIME <= ?) AS Later,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
				+ "WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '1' AND STATUS = '3' AND "
				+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Leavess,(SELECT COUNT(ATTEND_ID) FROM ATTEND "
				+ "WHERE ACCOUNT_ID = T1.ACCOUNT_ID AND IS_VALID = '0' AND "
				+ "REGIST_TIME >=  ? AND REGIST_TIME <= ?) AS Address FROM USER_INFO T1 WHERE "
				+ "EXISTS(SELECT * FROM (SELECT DISTINCT U.ACCOUNT_ID AS ACCOUNT_ID FROM "
				+ "USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L "
				+ "ON U.LEAD_LEAVE = L.LEAVE_ID WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN = '0' "
				+ "AND U.DEPT_ID = ?  AND U.LEAD_LEAVE=L.LEAVE_ID AND ( L.LEAVE_NO_ID<="
				+ "(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=?)) T4 "
				+ "WHERE T4.ACCOUNT_ID = T1.ACCOUNT_ID)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, startDate);
		ps.setString(2, endDate);
		ps.setString(3, startDate);
		ps.setString(4, endDate);
		ps.setString(5, startDate);
		ps.setString(6, endDate);
		ps.setString(7, startDate);
		ps.setString(8, endDate);
		ps.setString(9, startDate);
		ps.setString(10, endDate);
		ps.setString(11, deptId);
		ps.setString(12, userInfo.getLeadLeave());
		ps.setString(13, userInfo.getLeadId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject tmpJson = new JSONObject();
			Account acc = new AccountLogic().getAccountByAccountId(conn, rs.getString("ACCOUNT_ID"));
			//获取应该考勤数
			int shouldNum = this.getAttendNumByDate(conn, date, acc);
			record.addField("部门名称", rs.getString("DEPT_NAME"));
			record.addField("人员", rs.getString("USER_NAME"));
			record.addField("应有考勤", shouldNum);
			record.addField("实有考勤", rs.getString("Actual"));
			record.addField("正常考勤", rs.getString("Normal"));
			record.addField("迟到", rs.getString("Later"));
			record.addField("早退", rs.getString("Leavess"));
			record.addField("定位", rs.getString("Address"));
			jsonArrCount.add(tmpJson);
		}
		rs.close();
		ps.close();
		
	}

	public String getAddressByTimeAndDept(Connection conn, String date,String accountId, String orgId)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date.substring(0,4)+"-"+date.substring(5,7)+"-01 00:00"));
		String startDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		c.add(Calendar.MONTH, 1);
		String endDate = SysTool.getDateTimeStr(c.getTime()).substring(0,10);
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT ATTEND_ID,REGIST_TIME,REMARK,PICTRUE,LON,LAT,ADDRESS,ACCOUNT_ID FROM ATTEND WHERE IS_VALID = '0' AND ACCOUNT_ID = ? AND REGIST_TIME >=  ? AND REGIST_TIME <= ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, startDate);
		ps.setString(3, endDate);
		ps.setString(4, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			
			json.accumulate("attendId", rs.getString("ATTEND_ID"));
			json.accumulate("registTime", rs.getString("REGIST_TIME"));
			json.accumulate("remark", rs.getString("REMARK"));
			json.accumulate("pictrue", rs.getString("PICTRUE"));
			json.accumulate("lon", rs.getString("LON"));
			json.accumulate("lat", rs.getString("LAT"));
			json.accumulate("address", rs.getString("ADDRESS"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	public String getAttendRegistTime(Connection conn, String userId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT t1.ATTEND_CONFIG_ID,t1.ATTEND_TIME_ID,t1.TIME1,t1.TIME2,t1.TIME_TYPE1,t1.TIME_TYPE2 "
				+ "FROM ATTEND_TIME t1 LEFT JOIN USER_INFO t2 ON t1.ATTEND_CONFIG_ID = t2.ATTEND_CONFIG_ID "
				+ "WHERE t2.ACCOUNT_ID = ? AND t2.ORG_ID = ? ORDER BY t1.TIME1 ASC ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			json.accumulate("time", rs.getString("TIME1"));
			json.accumulate("type", rs.getString("TIME_TYPE1"));
			jsonArr.add(json);
			JSONObject json1 = new JSONObject();
			json1.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			json1.accumulate("time", rs.getString("TIME2"));
			json1.accumulate("type", rs.getString("TIME_TYPE2"));
			jsonArr.add(json1);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据月份获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getMonthRegistByDate(Connection conn,String orgId,String date,String userId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		int dayNum = c.getActualMaximum(java.util.Calendar.DATE);
		String startDate = date+"-01 00:00:00";
		String endDate = date+"-"+dayNum+" 23:59:59";
		String sql = "SELECT ATTEND_ID,REGIST_TIME,REGIST_TYPE,REMARK,STATUS,ADDRESS,ACCOUNT_ID,ATTEND_TIME_ID FROM ATTEND WHERE REGIST_TIME > ? AND REGIST_TIME < ? AND ACCOUNT_ID = ? AND ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, startDate);
		ps.setString(2, endDate);
		ps.setString(3, userId);
		ps.setString(4, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("attendId", rs.getString("ATTEND_ID"));
			json.accumulate("registTime", rs.getString("REGIST_TIME"));
			json.accumulate("registType", rs.getString("REGIST_TYPE"));
			json.accumulate("remark", rs.getString("REMARK"));
			json.accumulate("status", rs.getString("STATUS"));
			json.accumulate("address", rs.getString("ADDRESS"));
			json.accumulate("attendTimeId", rs.getString("ATTEND_TIME_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
