package tfd.system.mobile.feedback.act;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;





import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import tfd.system.mobile.feedback.data.Feedback;
import tfd.system.mobile.feedback.logic.FeedbackLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.mobile.tool.MUpImgTool;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

public class FeedbackAct {
	/**
	 * 意见反馈
	 * Time:2015-9-28
	 * Author:Lsq
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void feedback(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData = null;
		dbConn = DbPoolConnection.getInstance().getConnection();
		SystemUtil sysUtil = new SystemUtil();
		String phone= sysUtil.checkParam(response,"phone",request.getParameter("phone"));
		String accountId = SystemUtil.getAccountIdByPhone(dbConn, phone);
		Account account = new AccountLogic().getAccountByAccountId(dbConn, accountId);
		JSONObject json = new JSONObject();
		String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
		sysUtil.checkAccessToken(request, response, token, phone);
		String version  = request.getParameter("version");
		String platform = request.getParameter("platform");
		String content = request.getParameter("content");
		String attachIds = request.getParameter("attachIds");
		try
		{
			Feedback feedback = new Feedback();
			String feedbackId = GuId.getGuid();
			feedback.setFeedbackId(feedbackId);
			feedback.setClientType(platform);
			feedback.setContent(content);
			feedback.setVersion(version);
			feedback.setAttachId(attachIds);
			FeedbackLogic feedbackLogic = new FeedbackLogic();
			int i = feedbackLogic.feedbackLogic(dbConn, feedback);
			JSONObject json1 = new JSONObject();
			if(i > 0){
				//json1.accumulate("phone", phone);
	    		json1.accumulate("time",  new Date().getTime());
	    		json.accumulate("status_code", "200");
	    		json.accumulate("msg", "提交反馈意见成功");
	    		json.accumulate("data",json1);
			}else{
				//json1.accumulate("phone", phone);
		   		json1.accumulate("time", new Date().getTime());
		   		json.accumulate("status_code", "500");
		   		json.accumulate("msg", "提交反馈意见失败");
		   		json.accumulate("data",json1);
			}
			dbConn.commit();
		}catch (Exception ex)
		{
			JSONObject json1 = new JSONObject();
			//json1.accumulate("phone", phone);
	   		json1.accumulate("time", new Date().getTime());
	   		json.accumulate("status_code", "500");
	   		json.accumulate("msg", "提交反馈意见失败");
	   		json.accumulate("data",json1);
	   		response.setContentType("application/json;charset=utf-8");
    		response.getWriter().print(json.toString());
    		response.getWriter().flush();
			ex.printStackTrace();
		}finally
		{
    		DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
    		response.getWriter().print(json.toString());
    		response.getWriter().flush();
		}
	}
	
	
}
