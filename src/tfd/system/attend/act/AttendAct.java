package tfd.system.attend.act;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.attend.data.Attend;
import tfd.system.attend.data.AttendConfig;
import tfd.system.attend.data.AttendRegist;
import tfd.system.attend.data.AttendTime;
import tfd.system.attend.logic.AttendLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class AttendAct {
	/**
	 * 添加排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addAttendConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			AttendConfig attendConfig = new AttendConfig();
			String attendConfigId = GuId.getGuid();
			attendConfig.setAttendConfigId(attendConfigId);
			attendConfig.setConfigName(request.getParameter("configName"));
			String configType = request.getParameter("configType");
			String attendTimeId2 = "";
			String attendTimeId3 = "";
			AttendTime attendTime = new AttendTime();
			attendTime.setAttendTimeId(GuId.getGuid());
			attendTime.setAttendConfigId(attendConfigId);
			attendTime.setTime1(request.getParameter("time1"));
			attendTime.setTime2(request.getParameter("time2"));
			attendTime.setTimeType1(request.getParameter("timeType1"));
			attendTime.setTimeType2(request.getParameter("timeType2"));
			attendTime.setOrgId(account.getOrgId());
			if(configType.equals("2")){
				AttendTime attendTime2 = new AttendTime();
				attendTime2.setAttendTimeId(GuId.getGuid());
				attendTime2.setAttendConfigId(attendConfigId);
				attendTime2.setTime1(request.getParameter("time3"));
				attendTime2.setTime2(request.getParameter("time4"));
				attendTime2.setTimeType1(request.getParameter("timeType3"));
				attendTime2.setTimeType2(request.getParameter("timeType4"));
				attendTime2.setOrgId(account.getOrgId());
				attendTimeId2 = attendLogic.addAttendTime(dbConn,attendTime2);
			}else if(configType.equals("3")){
				AttendTime attendTime2 = new AttendTime();
				attendTime2.setAttendTimeId(GuId.getGuid());
				attendTime2.setAttendConfigId(attendConfigId);
				attendTime2.setTime1(request.getParameter("time3"));
				attendTime2.setTime2(request.getParameter("time4"));
				attendTime2.setTimeType1(request.getParameter("timeType3"));
				attendTime2.setTimeType2(request.getParameter("timeType4"));
				attendTime2.setOrgId(account.getOrgId());
				attendTimeId2 = attendLogic.addAttendTime(dbConn,attendTime2);
				AttendTime attendTime3 = new AttendTime();
				attendTime3.setAttendTimeId(GuId.getGuid());
				attendTime3.setAttendConfigId(attendConfigId);
				attendTime3.setTime1(request.getParameter("time5"));
				attendTime3.setTime2(request.getParameter("time6"));
				attendTime3.setTimeType1(request.getParameter("timeType5"));
				attendTime3.setTimeType2(request.getParameter("timeType6"));
				attendTime3.setOrgId(account.getOrgId());
				attendTimeId3 = attendLogic.addAttendTime(dbConn,attendTime3);
			}
			String attendTimeId = attendLogic.addAttendTime(dbConn,attendTime);
			attendConfig.setConfigType(configType);
			attendConfig.setAttendTimeId(attendTimeId);
			attendConfig.setAttendTimeId2(attendTimeId2);
			attendConfig.setAttendTimeId3(attendTimeId3);
			attendConfig.setOrgId(account.getOrgId());
			returnData = attendLogic.addAttendConfig(dbConn,attendConfig);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取排版类型列表
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendConfigList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendConfigList(dbConn,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id获取排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendConfigById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String attendConfigId = request.getParameter("attendConfigId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendConfigById(dbConn,attendConfigId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String updateAttendConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			AttendConfig attendConfig = new AttendConfig();
			String attendConfigId = request.getParameter("attendConfigId");
			attendLogic.delAttendTimeById(dbConn, attendConfigId, account.getOrgId());
			attendConfig.setAttendConfigId(attendConfigId);
			attendConfig.setConfigName(request.getParameter("configName"));
			String configType = request.getParameter("configType");
			String attendTimeId2 = "";
			String attendTimeId3 = "";
			AttendTime attendTime = new AttendTime();
			attendTime.setAttendTimeId(request.getParameter("attendTimeId"));
			attendTime.setAttendConfigId(attendConfigId);
			attendTime.setTime1(request.getParameter("time1"));
			attendTime.setTime2(request.getParameter("time2"));
			attendTime.setTimeType1(request.getParameter("timeType1"));
			attendTime.setTimeType2(request.getParameter("timeType2"));
			attendTime.setOrgId(account.getOrgId());
			String attendTimeId = attendLogic.addAttendTime(dbConn,attendTime);
			if(configType.equals("2")){
				AttendTime attendTime2 = new AttendTime();
				attendTimeId2 = request.getParameter("attendTimeId2");
				if(attendTimeId2.equals("")){
					attendTimeId2 = GuId.getGuid();
				}
				attendTime2.setAttendTimeId(attendTimeId2);
				attendTime2.setAttendConfigId(attendConfigId);
				attendTime2.setTime1(request.getParameter("time3"));
				attendTime2.setTime2(request.getParameter("time4"));
				attendTime2.setTimeType1(request.getParameter("timeType3"));
				attendTime2.setTimeType2(request.getParameter("timeType4"));
				attendTime2.setOrgId(account.getOrgId());
				attendTimeId2 = attendLogic.addAttendTime(dbConn,attendTime2);
			}else if(configType.equals("3")){
				AttendTime attendTime2 = new AttendTime();
				attendTimeId2 = request.getParameter("attendTimeId2");
				if(attendTime2.equals("")){
					attendTimeId2 = GuId.getGuid();
				}
				attendTime2.setAttendTimeId(attendTimeId2);
				attendTime2.setAttendConfigId(attendConfigId);
				attendTime2.setTime1(request.getParameter("time3"));
				attendTime2.setTime2(request.getParameter("time4"));
				attendTime2.setTimeType1(request.getParameter("timeType3"));
				attendTime2.setTimeType2(request.getParameter("timeType4"));
				attendTime2.setOrgId(account.getOrgId());
				attendTimeId2 = attendLogic.addAttendTime(dbConn,attendTime2);
				AttendTime attendTime3 = new AttendTime();
				attendTimeId3 = request.getParameter("attendTimeId3");
				if(attendTime3.equals("")){
					attendTimeId3 = GuId.getGuid();
				}
				attendTime3.setAttendTimeId(attendTimeId3);
				attendTime3.setAttendConfigId(attendConfigId);
				attendTime3.setTime1(request.getParameter("time5"));
				attendTime3.setTime2(request.getParameter("time6"));
				attendTime3.setTimeType1(request.getParameter("timeType5"));
				attendTime3.setTimeType2(request.getParameter("timeType6"));
				attendTime3.setOrgId(account.getOrgId());
				attendTimeId3 = attendLogic.addAttendTime(dbConn,attendTime3);
			}
			attendConfig.setConfigType(configType);
			attendConfig.setAttendTimeId(attendTimeId);
			attendConfig.setAttendTimeId2(attendTimeId2);
			attendConfig.setAttendTimeId3(attendTimeId3);
			attendConfig.setOrgId(account.getOrgId());
			attendLogic.updateAttendConfig(dbConn,attendConfig);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			/*response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();*/
		}
		return "/system/attend/setting/config/setAttendConfig.jsp";
	}
	
	/**
	 * 删除排版类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delAttendConfigById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String attendConfigId = request.getParameter("attendConfigId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.delAttendConfigById(dbConn,attendConfigId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 添加登记规则
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendRegistById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendRegistById(dbConn,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改登记规则
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void editAttendRegistById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			AttendRegist attendRegist = new AttendRegist();
			attendRegist.setBeforeWork(request.getParameter("beforeWork"));
			attendRegist.setAfterWork(request.getParameter("afterWork"));
			attendRegist.setBeforeBack(request.getParameter("beforeBack"));
			attendRegist.setAfterBack(request.getParameter("afterBack"));
			attendRegist.setOrgId(account.getOrgId());
			if(request.getParameter("attendRegistId")==""){
				attendRegist.setAttendRegistId(GuId.getGuid());
				returnData = attendLogic.addAttendRegist(dbConn, attendRegist);
			}else{
				attendRegist.setAttendRegistId(request.getParameter("attendRegistId"));
				returnData = attendLogic.editAttendRegist(dbConn, attendRegist);
			}
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 改变用户的排班类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void changeUserAttendConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String accountId = request.getParameter("accountId");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String attendConfigId = request.getParameter("attendConfigId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.changeUserAttendConfig(dbConn,accountId,attendConfigId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据部门修改排班类型
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void changeDeptAttendConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String deptId = request.getParameter("deptId");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String attendConfigId = request.getParameter("attendConfigId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.changeDeptAttendConfig(dbConn,deptId,attendConfigId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取今天的考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getTodayRegistById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getTodayRegistById(dbConn,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 添加考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addAttend(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			Attend attend = new Attend();
			attend.setAttendId(GuId.getGuid());
			attend.setRegistType(request.getParameter("registType"));
			attend.setRemark(request.getParameter("remark"));
			attend.setPictrue(request.getParameter("pictrue"));
			attend.setAttendTimeId(request.getParameter("attendTimeId"));
			attend.setAccountId(account.getAccountId());
			attend.setOrgId(account.getOrgId());
			returnData = attendLogic.addAttend(dbConn,attend);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String attendId = request.getParameter("attendId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendById(dbConn,attendId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateAttend(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			Attend attend = new Attend();
			attend.setAttendId(request.getParameter("attendId"));
			attend.setRemark(request.getParameter("remark"));
			attend.setPictrue(request.getParameter("pictrue"));
			attend.setAccountId(account.getAccountId());
			attend.setOrgId(account.getOrgId());
			returnData = attendLogic.updateAttend(dbConn,attend);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改考勤时间
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateRegistTime(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			String attendId = request.getParameter("attendId");
			String type = request.getParameter("registType");
			String attendTimeId = request.getParameter("attendTimeId");
			String orgId = account.getOrgId();
			returnData = attendLogic.updateRegistTime(dbConn,attendId,type,attendTimeId,orgId);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改公共休假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void editPublicDay(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			String attendConfigId = request.getParameter("attendConfigId");
			String publicDay = request.getParameter("publicDay");
			String orgId = account.getOrgId();
			returnData = attendLogic.editPublicDay(dbConn,attendConfigId,publicDay,orgId);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据月份获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getMonthRegistById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String dayNum = request.getParameter("day");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getMonthRegistById(dbConn,account,dayNum);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendHoliday(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendHoliday(dbConn,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 添加节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String addAttendHoliday(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			String holidayId = GuId.getGuid();
			String holidayName = request.getParameter("holidayName");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			attendLogic.addAttendHoliday(dbConn,holidayId,holidayName,startDate,endDate,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			/*response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();*/
		}
		return "/system/attend/setting/holiday/index.jsp";
	}
	
	/**
	 * 修改节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String editAttendHoliday(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			String holidayId = request.getParameter("holidayId2");
			String holidayName = request.getParameter("holidayName2");
			String startDate = request.getParameter("startDate2");
			String endDate = request.getParameter("endDate2");
			attendLogic.editAttendHoliday(dbConn,holidayId,holidayName,startDate,endDate,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			/*response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();*/
		}
		return "/system/attend/setting/holiday/index.jsp";
	}
	
	/**
	 * 删除节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delAttendHoliday(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			String holidayId = request.getParameter("holidayId");
			returnData = attendLogic.delAttendHoliday(dbConn,holidayId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id获取节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendHolidayById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String holidayId = request.getParameter("holidayId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendHolidayById(dbConn,holidayId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取当天的考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getRegistByDay(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String date = request.getParameter("date");
			String accountId = request.getParameter("accountId");
			Account account = null;
			if(accountId==null){
				account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			}else{
				account = new AccountLogic().getAccountByAccountId(dbConn, accountId);
			}
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getRegistByDay(dbConn,date,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取排版类型的节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendConfig(dbConn,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 判断是否是节假日
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void IsHoliday(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String date = request.getParameter("date");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.IsHoliday(dbConn,date,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据时间和部门获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendByTimeAndDept(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String startDate = request.getParameter("startDate");
			String deptId = request.getParameter("deptId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.countAttend(dbConn,startDate,deptId,account);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 导出统计信息
	 * Time:2015-11-04
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String AttendListExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream ops=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String deptId = request.getParameter("deptId");
			String startDate = request.getParameter("startDate");
			Account account=(Account) request.getSession().getAttribute("ACCOUNT_ID");
			List<Record> records=new AttendLogic().countAttendExpart(conn,startDate,deptId,account);
			
			String exportFileName="上下班统计.xls";
			String fileName = URLEncoder.encode(exportFileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control","maxage=3600");
			response.setHeader("Pragma","public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			conn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(conn);
			SysTool.closeOutputStream(ops);
		}
		return null;
	}
	
	/**
	 * 根据时间和部门获取定位
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAddressByTimeAndDept(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String date = request.getParameter("date");
			String accountId = request.getParameter("accountId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAddressByTimeAndDept(dbConn,date,accountId, account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据人员获取一天考勤的时间
	 * Time:2015-11-19
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAttendTime(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String userId = request.getParameter("userId");
			if(userId==null){
				userId = account.getAccountId();
			}
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getAttendRegistTime(dbConn,userId,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据月份获取考勤
	 * Time:2015-10-29
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getMonthRegistByDate(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String date = request.getParameter("date");
			String userId = request.getParameter("accountId");
			AttendLogic attendLogic = new AttendLogic();
			returnData = attendLogic.getMonthRegistByDate(dbConn,account.getOrgId(),date,userId);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
