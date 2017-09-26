package tfd.system.mobile.userinfo.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.mobile.userinfo.data.Mark;
import tfd.system.mobile.userinfo.data.Target;
import tfd.system.mobile.userinfo.logic.UserInfoLogic;
import tfd.system.mobile.workflow.logic.WorkflowLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.ResponseConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class UserInfoAct {
	public void companystructureOfDept(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONObject data=JSONObject.fromObject("{'status_code':'500','msg':'请求失败','data':{'time':"+new Date().getTime()/1000+"}}");
		try {
			JSONObject dataTemp=new WorkflowLogic().getTestData("companystructureOfDept");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 获取人员
	 * Time:2015-10-14
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void companystaff(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String type=sysUtil.checkParam(response,"type",request.getParameter("type"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			if(type.equals("1")){
				returnData=userInfoLogic.companystaffLogic(dbConn, account, token, version, platform, request);
			}else if(type.equals("2")){
				returnData=userInfoLogic.authorityStaffLogic(dbConn, account, request);
			}
			dbConn.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 查看公司组织机构
	 * Time:2015-10-14
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void companystructure(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String type=sysUtil.checkParam(response,"type",request.getParameter("type"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			if(type.equals("1")){
				returnData=userInfoLogic.companystructureLogic(dbConn, account, token, version, platform,request);
			}else if(type.equals("2")){
				returnData=userInfoLogic.authoritystructureLogic(dbConn, account, token, version, platform, request);
			}
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
	 * 查看用户其他信息
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void userOtherInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String userId=sysUtil.checkParam(response,"target_user_id",request.getParameter("target_user_id"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.userOtherInfo(dbConn, account, token, version, platform,userId);
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
	 * 查看自己的目标墙
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void targetWall(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.targetWall(dbConn, account, token, version, platform);
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
	 * 查看其他人的目标墙
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void targetWallOther(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.targetWallOther(dbConn, account, token, version, platform,id);
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
	 * 创建/编辑目标
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createTarget(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Target target = new Target();
			String id = request.getParameter("id");
			String isAdd = "0";
			if(id==null){
				id = GuId.getGuid();
				isAdd = "1";
			}
			target.setTargetId(id);
			target.setContent(sysUtil.checkParam(response,"phone",request.getParameter("content")));
			target.setCreateTime(sysUtil.checkParam(response,"date",request.getParameter("date")));
			target.setIsPublic(sysUtil.checkParam(response,"isPublic",request.getParameter("isPublic")));
			target.setStatus(sysUtil.checkParam(response,"status",request.getParameter("status")));
			target.setAccountId(account.getAccountId());
			target.setOrgId(account.getOrgId());
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.createTarget(dbConn, account, token, version, platform,target,isAdd);
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
	 * 目标点赞/取消赞
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void praisetarget(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
			String ispraise = sysUtil.checkParam(response,"ispraise",request.getParameter("ispraise"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.praisetarget(dbConn, account, token, version, platform,id,ispraise);
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
	 * 操作目标
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void operatetarget(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
			String operatetype = sysUtil.checkParam(response,"type",request.getParameter("type"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.operatetarget(dbConn, account, token, version, platform,id,operatetype);
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
	 * 创建标签
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createmark(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Mark mark = new Mark();
			mark.setMarkId(GuId.getGuid());
			mark.setContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
			mark.setCreateUser(phone);
			mark.setAccountId(sysUtil.checkParam(response,"userid",request.getParameter("userid")));
			mark.setOrgId(account.getOrgId());
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.createmark(dbConn, account, token, version, platform,mark);
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
	 * 查看标签列表
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void marks(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String userid = sysUtil.checkParam(response,"userid",request.getParameter("userid"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.marks(dbConn, account, token, version, platform,userid);
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
	 * 点赞标签
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void praisemark(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id = sysUtil.checkParam(response,"markid",request.getParameter("markid"));
			String islike = sysUtil.checkParam(response,"islike",request.getParameter("islike"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.praisemark(dbConn, account, token, version, platform,id,islike);
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
	 * 查询小秘书列表
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void smallsecretarys(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.smallsecretarys(dbConn, account, token, version, platform);
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
	 * 选择小秘书
	 * Time:2015-11-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectsmallsecretary(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String smallsecretary = sysUtil.checkParam(response,"smallsecretary",request.getParameter("smallsecretary"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.selectsmallsecretary(dbConn, account, token, version, platform,smallsecretary);
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
	
	public void markLikes(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id = sysUtil.checkParam(response,"id",request.getParameter("id"));
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			returnData=userInfoLogic.markLikes(dbConn, account, token, version, platform,id,request);
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
}
