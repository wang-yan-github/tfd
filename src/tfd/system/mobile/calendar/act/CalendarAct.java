package tfd.system.mobile.calendar.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.mobile.calendar.logic.CalendarLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import calendar.data.Calendar;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class CalendarAct {
	/**
	 * 月日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void montharrangement(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String date = sysUtil.checkParam(response,"date",request.getParameter("date"));
				String userId = sysUtil.checkParam(response,"userId",request.getParameter("userId"));
				CalendarLogic cllogic = new CalendarLogic();
				returnData = cllogic.getDayByMonth(dbConn,token,version,platform,date,userId,account);
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 日日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void dailyarrangement(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String date = sysUtil.checkParam(response,"date",request.getParameter("date"));
				String userId = sysUtil.checkParam(response,"userId",request.getParameter("userId"));
				CalendarLogic cllogic = new CalendarLogic();
				returnData = cllogic.getCalendarByDay(dbConn,token,version,platform,date,userId,account,request);
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 日程列表
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void arrangementlist(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String userId = sysUtil.checkParam(response,"userId",request.getParameter("userId"));
				String type = sysUtil.checkParam(response,"type",request.getParameter("type"));
				String page = sysUtil.checkParam(response,"page",request.getParameter("page"));
				CalendarLogic cllogic = new CalendarLogic();
				returnData = cllogic.getCalendarByType(dbConn,token,version,platform,type,userId,account,page,request);
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 日程详情
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void arrangementdetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
				CalendarLogic cllogic = new CalendarLogic();
				returnData = cllogic.getCalendarById(dbConn,token,version,platform,id,account);
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 修改日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void arrangementedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				CalendarLogic cllogic = new CalendarLogic();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String accountId = account.getAccountId();
				String editType = sysUtil.checkParam(response,"edittype",request.getParameter("edittype"));
				Calendar cl = new Calendar();
				String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
				if(editType.equals("2")){
					returnData = cllogic.updateState(dbConn, id, sysUtil.checkParam(response,"state",request.getParameter("state")));
					String completion = request.getParameter("completion");
					String diaryId = cllogic.getDirayId(dbConn, id);
					cllogic.addCalendarDiary(dbConn, account, diaryId, completion,id);
				}else if(editType.equals("1")){
					cl.setCalendarId(id);
					cl.setCalendarPid(id);
					cl.setContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
					cl.setStartDate(sysUtil.checkParam(response,"begindate",request.getParameter("begindate")));
					cl.setEndDate(sysUtil.checkParam(response,"enddate",request.getParameter("enddate")));
					cl.setCalType(sysUtil.checkParam(response,"type",request.getParameter("type")));
					cl.setCalLevel(sysUtil.checkParam(response,"priority",request.getParameter("priority")));
					String remindmins = sysUtil.checkParam(response,"remindmins",request.getParameter("remindmins"));
					cl.setBeforeTime(remindmins);
					cl.setAccountId(accountId);
					cl.setFromId(accountId);
					cl.setOrgId(account.getOrgId());
					cl.setStatus("0");
					String userId = sysUtil.checkParam(response,"partner",request.getParameter("partner"));
					if(!userId.equals("")){
						if(userId.indexOf(",")>-1){
							cl.setUserId(userId);
							String [] userIds = userId.split(",");
							for (int i = 0; i < userIds.length; i++) {
								if(!userIds[i].equals(accountId)){
									cl.setCalendarId(GuId.getGuid());
									cl.setAccountId(userIds[i]);
									if(!cllogic.isExist(dbConn, userIds[i], id,accountId)){
										cllogic.addCalendar(dbConn,token,version,platform,cl);
									}
								}
							}
						}else{
							cl.setAccountId(userId);
							cl.setCalendarId(GuId.getGuid());
							if(!cllogic.isExist(dbConn, userId, id,accountId)){
								cl.setUserId(userId);
								cllogic.addCalendar(dbConn,token,version,platform,cl);
							}
						}
					} 
					cl.setCalendarId(id);
					cl.setAccountId(accountId);
					returnData = cllogic.editCalendar(dbConn,token,version,platform,cl);
				}else if(editType.equals("3")){
					cllogic.updateState(dbConn, id, sysUtil.checkParam(response,"state",request.getParameter("state")));
					String completion = request.getParameter("completion");
					String diaryId = cllogic.getDirayId(dbConn, id);
					cllogic.addCalendarDiary(dbConn, account, diaryId, completion,id);
					cl.setCalendarId(id);
					cl.setCalendarPid(id);
					cl.setContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
					cl.setStartDate(sysUtil.checkParam(response,"begindate",request.getParameter("begindate")));
					cl.setEndDate(sysUtil.checkParam(response,"enddate",request.getParameter("enddate")));
					cl.setCalType(sysUtil.checkParam(response,"type",request.getParameter("type")));
					cl.setCalLevel(sysUtil.checkParam(response,"priority",request.getParameter("priority")));
					String remindmins = sysUtil.checkParam(response,"remindmins",request.getParameter("remindmins"));
					cl.setBeforeTime(remindmins);
					cl.setAccountId(accountId);
					cl.setFromId(accountId);
					cl.setOrgId(account.getOrgId());
					cl.setStatus("0");
					String userId = sysUtil.checkParam(response,"partner",request.getParameter("partner"));
					if(!userId.equals("")){
						if(userId.indexOf(",")>-1){
							cl.setUserId(userId);
							String [] userIds = userId.split(",");
							for (int i = 0; i < userIds.length; i++) {
								if(!userIds[i].equals(accountId)){
									cl.setCalendarId(GuId.getGuid());
									cl.setAccountId(userIds[i]);
									if(!cllogic.isExist(dbConn, userIds[i], id,accountId)){
										cllogic.addCalendar(dbConn,token,version,platform,cl);
									}
								}
							}
						}else{
							cl.setAccountId(userId);
							cl.setUserId(userId);
							cl.setCalendarId(GuId.getGuid());
							if(!cllogic.isExist(dbConn, userId, id,accountId)){
								cllogic.addCalendar(dbConn,token,version,platform,cl);
							}
						}
					} 
					cl.setCalendarId(id);
					cl.setAccountId(accountId);
					returnData = cllogic.editCalendar(dbConn,token,version,platform,cl);
				}
				
				
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 添加日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void addarrangement(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				CalendarLogic cllogic = new CalendarLogic();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String accountId = account.getAccountId();
				String remindmins = sysUtil.checkParam(response,"remindmins",request.getParameter("remindmins"));
				Calendar cl = new Calendar();
				String id = GuId.getGuid();
				cl.setCalendarId(id);
				cl.setCalendarPid(id);
				cl.setContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
				cl.setStartDate(sysUtil.checkParam(response,"begindate",request.getParameter("begindate")));
				cl.setEndDate(sysUtil.checkParam(response,"enddate",request.getParameter("enddate")));
				cl.setCalType(sysUtil.checkParam(response,"type",request.getParameter("type")));
				cl.setCalLevel(sysUtil.checkParam(response,"priority",request.getParameter("priority")));
				cl.setBeforeTime(remindmins);
				cl.setAccountId(accountId);
				cl.setFromId(accountId);
				cl.setStatus("0");
				cl.setOrgId(account.getOrgId());
				String userId = sysUtil.checkParam(response,"partner",request.getParameter("partner"));
				if(userId!=null){
					if(!userId.equals("")){
						if(userId.indexOf(",")>-1){
							cl.setUserId(userId);
							String [] userIds = userId.split(",");
							for (int i = 0; i < userIds.length; i++) {
								cl.setCalendarId(GuId.getGuid());
								cl.setAccountId(userIds[i]);
								returnData = cllogic.addCalendar(dbConn,token,version,platform,cl);
							}
						}else{
							cl.setAccountId(userId);
							cl.setUserId(userId);
							cl.setCalendarId(GuId.getGuid());
							returnData = cllogic.addCalendar(dbConn,token,version,platform,cl);
						}
					}
				}
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
	 
	/**
	 * 删除日程
	 * Time:2015-10-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 public void deleteArrangement(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Connection dbConn = null;
			String returnData="";
			try{
				dbConn = DbPoolConnection.getInstance().getConnection();
				SystemUtil sysUtil = new SystemUtil();
				String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
				phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
				Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
				String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
				sysUtil.checkAccessToken(request, response, token, phone);
				String version=request.getParameter("version");
				String platform=request.getParameter("platform");
				String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
				CalendarLogic cllogic = new CalendarLogic();
				returnData = cllogic.delCalendarById(dbConn,token,version,platform,id,account.getOrgId(),account.getAccountId());
				dbConn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally
			{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(returnData);
				response.getWriter().flush();
			}
	 }
}
