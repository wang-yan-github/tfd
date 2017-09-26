package tfd.system.sms.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.sms.data.Sms;
import tfd.system.sms.logic.SmsLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;
import com.system.global.DateConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class SmsAct {
	SmsLogic logic = new SmsLogic();
	/**
	 * 发送内部短信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendSmsAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			dbConn.setAutoCommit(false);
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			String smsTo = request.getParameter("smsTo");
			String smsContent = request.getParameter("smsContent");
			Date smsSendTime = request.getParameter("smsSendTime").equals("")
								?SysTool.parseDate(request.getParameter("smsSendTime"),DateConst.VALUE_LONG_DATE24)
								:new Date();
								
			Sms sms = new Sms();
			sms.setSmsId(GuId.getGuid());
			sms.setSmsTo(smsTo);
			sms.setSmsContent(smsContent);
			sms.setSmsFrom(account.getAccountId());
			sms.setSmsFlag(Sms.SMS_FLAG_NOT_DELETE);
			sms.setSmsStatus(Sms.SMS_STATUS_NOT_RECEIVE);
			sms.setSmsSendTime(smsSendTime);
			sms.setSmsType("sms");
			
			int result=logic.sendSmsLogic(dbConn, sms);
			if (result>0) {
				dbConn.commit();
			}else{
				logic.dao.rollback(dbConn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			logic.dao.rollback(dbConn);
			// TODO: handle exception
			throw e;
		}finally{
			logic.dao.close(null, null, dbConn);
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 获取未读内部短信信
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNoReadSmsAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			
			JSONArray smsList = logic.getNoReadSmsLogic(dbConn, account);
			
			writer=response.getWriter();
			writer.print(smsList.toString());
		} catch (Exception e) {
			throw e;
		}finally{
			logic.dao.close(null, null, dbConn);
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 修改短信的读取状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateSmsReadAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			dbConn.setAutoCommit(false);
			
			String smsId = request.getParameter("smsId");
			
			int result=logic.updateSmsReadLogic(dbConn, smsId);
			if (result>0) {
				dbConn.commit();
			}else{
				logic.dao.rollback(dbConn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			logic.dao.rollback(dbConn);
			throw e;
		} finally {
			logic.dao.close(null, null, dbConn);
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 删除短信
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteSmsAct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection dbConn = null;
		PrintWriter writer=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			dbConn.setAutoCommit(false);
			
			String smsId = request.getParameter("smsId");
			
			int result=logic.deleteSmsLogic(dbConn, smsId);
			if (result>0) {
				dbConn.commit();
			}else{
				logic.dao.rollback(dbConn);
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			logic.dao.rollback(dbConn);
			throw e;
		} finally {
			logic.dao.close(null, null, dbConn);
			SysTool.closePrintWriter(writer);
		}
	}
}
