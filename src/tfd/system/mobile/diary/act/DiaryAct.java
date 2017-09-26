package tfd.system.mobile.diary.act;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;














import tfd.system.mobile.diary.logic.DiaryLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.Diary;
import diary.data.DiaryComments;


public class DiaryAct {

	/**
	 * 日志列表
	 * Time 2015-11-2
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordList(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String type = sysUtil.checkParam(response,"type",request.getParameter("type"));
			String page=sysUtil.checkParam(response,"page",request.getParameter("page"));
			int pagediary=Integer.parseInt(page);
			String id=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			if(type.equals("1")){
				returnData=diaryLogic.getAccountIdLogic(dbConn, account.getAccountId(), account.getOrgId(), request, pagediary,type);
			}else if(type.equals("2")){
				if(account.getAccountId().equals(id)){
					returnData=diaryLogic.getotherdiaryLogic(dbConn, account, request, pagediary);
				}else{
					returnData=diaryLogic.getAccountIdLogic(dbConn, id, account.getOrgId(), request, pagediary,type);
				}
			}else if(type.equals("3")){
				returnData=diaryLogic.getsharediaryLogic(dbConn, account.getAccountId(), account.getOrgId(), request, pagediary);
			}
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
	 * 日志阅读
	 * Time 2015-11-02
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordRead(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String id=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordReadLogic(dbConn, id, account.getAccountId(), account.getOrgId());
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
	 * 日志点赞
	 * Time 2015-11-02
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordLike(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordLikeLogic(dbConn, diaryId, account.getAccountId(), account.getOrgId());
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
	 * 日志详情
	 * Time 2015-11-02
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordDetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordDetailLogic(dbConn, diaryId, account.getAccountId(), account.getOrgId(),token,phone);
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
	 * 日志分享
	 * Time 2015-11-02
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordShare(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			String share=sysUtil.checkParam(response,"share",request.getParameter("share"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordShareLogic(dbConn, diaryId, share, account.getOrgId());
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
	 * 日志阅读人员 
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordReadings(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordReadingLogic(dbConn, diaryId, account.getOrgId(), request);
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
	 * 日志点赞人员
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordLikes(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordLikesLogic(dbConn, diaryId, account.getOrgId(), request);
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
	 * 日志评论
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordComment(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			DiaryComments diaryComm=new DiaryComments();
			diaryComm.setCommId(GuId.getGuid());
			diaryComm.setCommPid(sysUtil.checkParam(response,"commentPid",request.getParameter("commentPid")));
			diaryComm.setAccountId(account.getAccountId());
			diaryComm.setCommContect(sysUtil.checkParam(response,"content",URLDecoder.decode(request.getParameter("content"),"utf-8")));
			diaryComm.setCommTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			diaryComm.setDiaryId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			diaryComm.setOrgId(account.getOrgId());
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordCommentLogic(dbConn, diaryComm);
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
	 * 添加日志
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addDailyRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
			Diary diary=new Diary();
			diary.setDiaryName(sysUtil.checkParam(response,"title",request.getParameter("title")));
			diary.setDiaryContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
			diary.setDiaryTitleDatetime(sysUtil.checkParam(response,"time",request.getParameter("time")));
			diary.setDiaryMold(Integer.parseInt(sysUtil.checkParam(response,"type",request.getParameter("type"))));
			diary.setSharePriv(sysUtil.checkParam(response,"share",request.getParameter("share")));
			diary.setDiaryDatetime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			diary.setAccountId(account.getAccountId());
			diary.setOrgId(account.getOrgId());
			diary.setDiaryId(GuId.getGuid());
			diary.setLaud("");
			diary.setLaudNum(0);
			diary.setDiaryAnnex(sysUtil.checkParam(response,"attachIds",request.getParameter("attachIds")));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.addDailyRecordLogic(dbConn, diary);
			dbConn.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 日志评论列表
	 * Time 2015-11-04
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dailyRecordComments(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String diaryId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			DiaryLogic diaryLogic=new DiaryLogic();
			returnData=diaryLogic.dailyRecordCommentsLogic(dbConn, diaryId, account.getOrgId(), request);
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
	 * 日志详情
	 * Time 2015-12-17
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String diaryId=request.getParameter("diaryId");
			diary.logic.DiaryLogic dl=new diary.logic.DiaryLogic();
			returnData=dl.getIdDiaryLogic(dbConn, diaryId,account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
		}
	}
}
