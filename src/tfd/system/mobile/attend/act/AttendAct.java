package tfd.system.mobile.attend.act;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tfd.system.attend.data.Attend;
import tfd.system.mobile.attend.logic.AttendLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.filetool.UpImgTool;
import com.system.global.SysProps;
import com.system.tool.GuId;

public class AttendAct {

	/**
	 * 查看我的考勤
	 * Time 2015-10-21
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void attendance(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String userId = sysUtil.checkParam(response,"userId",request.getParameter("userId"));
			String platform=request.getParameter("platform");
			int page=Integer.parseInt(sysUtil.checkParam(response,"page",request.getParameter("page")));
			AttendLogic attendLogic=new AttendLogic();
			returnData=attendLogic.attendanceLogic(dbConn, userId, account.getOrgId(), page, request);
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 提交考勤
	 * Time 2015-10-21
	 * Author：Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkattendance(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			Attend attend=new Attend();
			String date=sysUtil.checkParam(response,"date",request.getParameter("date"));
			attend.setRegistTime(date);
			String content=sysUtil.checkParam(response,"content",request.getParameter("content"));
			attend.setRemark(content);
			String lon=sysUtil.checkParam(response,"lon",request.getParameter("lon"));
			attend.setLon(lon);
			String lat=sysUtil.checkParam(response,"lat",request.getParameter("lat"));
			attend.setLat(lat);
			String address=sysUtil.checkParam(response,"address",request.getParameter("address"));
			attend.setAddress(address);
			String attachId = sysUtil.checkParam(response,"attachIds",request.getParameter("attachIds"));
			String locationDate = sysUtil.checkParam(response,"loactionDate",request.getParameter("locationDate"));
			String type = sysUtil.checkParam(response,"type",request.getParameter("type"));
			String kind = sysUtil.checkParam(response,"kind",request.getParameter("kind"));
			attend.setDetail(sysUtil.checkParam(response,"detail",request.getParameter("detail")));
			attend.setPictrue(attachId);
			attend.setAccountId(account.getAccountId());
			attend.setOrgId(account.getOrgId());
			attend.setAttendId(GuId.getGuid());
			AttendLogic attendLogic=new AttendLogic();
			if(type.equals("1")){
				attend.setIsValid("1");
			}else{
				attend.setIsValid("0");
			}
			returnData=attendLogic.checkattendanceLogic(dbConn, attend,kind);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 同事考勤
	 * Time 2015-10-26
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void colleagueattendance(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			String time = request.getParameter("time");
			int page = Integer.parseInt(sysUtil.checkParam(response,"page",request.getParameter("page")));
			
			AttendLogic attendLogic=new AttendLogic();
			returnData=attendLogic.colleagueattendanceLogic(dbConn, account,time,request,page);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取签到时间
	 * Time 2015-11-04
	 * Author Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkClockingTime(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			
			AttendLogic attendLogic=new AttendLogic();
			returnData=attendLogic.checkClockingTime(dbConn, account);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
