package tfd.system.email.act;


import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.email.data.Email;
import tfd.system.email.data.EmailBody;
import tfd.system.email.data.EmailBox;
import tfd.system.email.data.EmailConfig;
import tfd.system.email.logic.EmailLogic;
import tfd.system.email.util.WebMail;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class EmailAct {
	
	/**
	 * 得到邮件
	 * Time : 2015-3-25
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			String boxId = request.getParameter("boxId");
			String search = request.getParameter("searchContent");
			if(search == null){
				search = "";
			}
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			List<String> pramList = new ArrayList<String>();
			pramList.add(orgId);
			pramList.add(account.getAccountId());
			pramList.add(boxId);
			returnData = new EmailLogic().getEmailList(dbConn,pramList,boxId,pageSize,page,sortOrder,sortName,search);
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
	 * 发送邮件
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String emailId = GuId.getGuid();
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String subject = request.getParameter("subject");
			String accountId = request.getParameter("accountId");
			String sendOther = request.getParameter("sendOther");
			String attachmentId = request.getParameter("attachId");
			String attachmentName = request.getParameter("attachmentName");
			String smsRemindStr = request.getParameter("smsRemind");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			String important = request.getParameter("important");
			String fromWebmailId = request.getParameter("fromWebmailId");
			String toWebmail = request.getParameter("toWebmail");
			String toWebmailCopy = request.getParameter("toWebmailCopy");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			EmailBody body = new EmailBody();
			body.setToId(accountId);
			body.setFromId(account.getAccountId());
			body.setSubject(subject);
			body.setContent(content);
			body.setCopyToId(sendOther);
			body.setAttachmentId(attachmentId);
			body.setAttachmentName(attachmentName);
			body.setSmsRemind(smsRemindStr);
			body.setImportant(important);
			body.setFromWebmailId(fromWebmailId);
			body.setToWebmail(toWebmail);
			body.setToWebmailCopy(toWebmailCopy);
			body.setOrgId(orgId);
			//先保存邮件
			String bodyId = emailL.saveEmail(dbConn, body);
			Email e = new Email();
			e.setEmailId(GuId.getGuid());
			e.setBodyId(bodyId);
			e.setToId(accountId);
			e.setOrgId(orgId);
			emailL.addEmailtoSend(dbConn,e);
			String[] userIds =null;
			if(accountId.indexOf(",")>-1){
				userIds = accountId.split(",");
			}else
			{
				userIds=new String[]{accountId};
			}
			if(!body.getToWebmail().equals("")){
				try {
					EmailConfig emailConfig = emailL.getEmailConfigById2(dbConn,body.getFromId(),body.getOrgId());
					WebMail.SendWebMail(body, emailConfig);	
				} catch (Exception e2) {
					returnData = -1;
					throw new Exception("外部邮件错误");
				}
			}
			List<String> toAccountList = new ArrayList<String>();
			for (int i = 0; i < userIds.length; i++) {
				Email email = new Email();
				toAccountList.add(userIds[i]);
				email.setEmailId(GuId.getGuid());
				email.setBodyId(bodyId);
				email.setToId(userIds[i]);
				email.setOrgId(orgId);
				returnData = emailL.sendEmail(dbConn, email);
			}
			if(sendOther!=""){
				if(sendOther.indexOf(",")>-1){
					String[] userIdss = sendOther.split(",");
					for (int i = 0; i < userIdss.length; i++) {
						if(!emailL.CheckCopyMail(dbConn, userIdss[i], bodyId)){
							Email email = new Email();
							email.setEmailId(GuId.getGuid());
							email.setBodyId(bodyId);
							email.setToId(userIdss[i]);
							email.setOrgId(orgId);
							returnData = emailL.sendEmail(dbConn, email);
						}
					}
				}else{
					if(!emailL.CheckCopyMail(dbConn, sendOther, bodyId)){
						Email email = new Email();
						email.setEmailId(emailId);
						email.setBodyId(bodyId);
						email.setToId(sendOther);
						email.setOrgId(orgId);
						returnData = emailL.sendEmail(dbConn, email);
					}
				}
			}
			//后成提醒方式
			MessageApi messageApi = new MessageApi();
			messageApi.sendMessage(dbConn, "email", smsRemindJson, "您有一封新邮件，请注意查收。", account.getAccountId(), toAccountList,account.getOrgId());
			
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
	 * 保存邮件到草稿箱
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String emailId="";
		try{
			emailId = GuId.getGuid();
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String subject = request.getParameter("subject");
			String accountId = request.getParameter("accountId");
			String sendOther = request.getParameter("sendOther");
			String attachmentId = request.getParameter("attachId");
			String attachmentName = request.getParameter("attachmentName");
			String smsRemind = request.getParameter("smsRemind");
			String important = request.getParameter("important");
			String fromWebmailId = request.getParameter("fromWebmailId");
			String toWebmail = request.getParameter("toWebmail");
			String toWebmailCopy = request.getParameter("toWebmailCopy");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			EmailBody body = new EmailBody();
			body.setToId(accountId);
			body.setFromId(account.getAccountId());
			body.setSubject(subject);
			body.setContent(content);
			body.setCopyToId(sendOther);
			body.setAttachmentId(attachmentId);
			body.setAttachmentName(attachmentName);
			body.setSmsRemind(smsRemind);
			body.setImportant(important);
			body.setFromWebmailId(fromWebmailId);
			body.setToWebmail(toWebmail);
			body.setToWebmailCopy(toWebmailCopy);
			body.setOrgId(orgId);
			//先保存邮件
			String bodyId = emailL.saveEmail(dbConn, body);
			Email email = new Email();
			email.setEmailId(emailId);
			email.setBodyId(bodyId);
			email.setToId(accountId);
			email.setOrgId(orgId);
			emailL.saveEmailToDraft(dbConn, email);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(emailId);
			response.getWriter().flush();
		}
	}
	/**
	 * 得到未读邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailBoxCount(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String boxId = request.getParameter("boxId");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailBoxCount(dbConn,boxId,account.getAccountId(),orgId);
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
	 * 得到邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailCount(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailCount(dbConn,account.getAccountId(),orgId);
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
	 * 得到未读邮件数量
	 * Time : 2015-3-30
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailInCount(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailInCount(dbConn,account.getAccountId(),orgId);
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
	 * 根据邮件Id查看具体邮件内容
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String emailId = request.getParameter("id");
			String boxId = request.getParameter("boxId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailById(dbConn,emailId,orgId,account.getAccountId(),boxId);
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
	 * 修改邮件未读状态
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void changeEmailFlag(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			String emailId = request.getParameter("id");
			
			if(emailId.length() > 36){
				String[] id = emailId.split("%2C");
				for (int i = 0; i < id.length; i++) {
					returnData = new EmailLogic().changeEmailFlag(dbConn,id[i],orgId);
				}
			}else{
				returnData = new EmailLogic().changeEmailFlag(dbConn,emailId,orgId);
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
	 * 删除邮件到草稿箱
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			String emailId = request.getParameter("id");
			
			if(emailId.length() > 36){
				String[] id = emailId.split("%2C");
				for (int i = 0; i < id.length; i++) {
					returnData = new EmailLogic().delEmail(dbConn,id[i],orgId);
				}
			}else{
				returnData = new EmailLogic().delEmail(dbConn,emailId,orgId);
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
	 * 彻底删除邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			String emailId = request.getParameter("id");
			if(emailId.length() > 36){
				String[] id = emailId.split("%2C");
				for (int i = 0; i < id.length; i++) {
					returnData = new EmailLogic().deleteEmail(dbConn,id[i],orgId);
				}
			}else{
				returnData = new EmailLogic().deleteEmail(dbConn,emailId,orgId);
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
	 * 从草稿箱发送邮件
	 * Time:2015-3-31
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendEmailByDraft(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String emailId = request.getParameter("emailId");
			String bodyId = request.getParameter("bodyId");
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String subject = request.getParameter("subject");
			String accountId = request.getParameter("accountId");
			String sendOther = request.getParameter("sendOther");
			String attachmentId = request.getParameter("attachmentId");
			String attachmentName = request.getParameter("attachmentName");
			String smsRemindStr = request.getParameter("smsRemind");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			String important = request.getParameter("important");
			String fromWebmailId = request.getParameter("fromWebmailId");
			String toWebmail = request.getParameter("toWebmail");
			String toWebmailCopy = request.getParameter("toWebmailCopy");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			//先删除草稿箱中的邮件
			emailL.deleteEmail(dbConn, emailId, orgId);
			EmailBody body = new EmailBody();
			body.setBodyId(bodyId);
			body.setToId(accountId);
			body.setFromId(account.getAccountId());
			body.setSubject(subject);
			body.setContent(content);
			body.setCopyToId(sendOther);
			body.setAttachmentId(attachmentId);
			body.setAttachmentName(attachmentName);
			body.setSmsRemind(smsRemindStr);
			body.setImportant(important);
			body.setFromWebmailId(fromWebmailId);
			body.setToWebmail(toWebmail);
			body.setToWebmailCopy(toWebmailCopy);
			body.setOrgId(account.getOrgId());
			//修改邮件
			emailL.updateEmailBody(dbConn, body);
			Email e = new Email();
			e.setEmailId(emailId);
			e.setBodyId(bodyId);
			e.setToId(accountId);
			e.setOrgId(orgId);
			emailL.addEmailtoSend(dbConn, e);
			//returnData = emailL.sendEmail(dbConn, email);
			String[] userIds =null;
			if(accountId.indexOf(",")>-1){
				userIds = accountId.split(",");
			}else
			{
				userIds=new String[]{accountId};
			}
			if(!body.getToWebmail().equals("")){
				try {
					EmailConfig emailConfig = emailL.getEmailConfigById2(dbConn,body.getFromId(),body.getOrgId());
					WebMail.SendWebMail(body, emailConfig);	
				} catch (Exception e2) {
					returnData = -1;
					throw new Exception("外部邮件错误");
				}
			}
			List<String> toAccountList = new ArrayList<String>();
			for (int i = 0; i < userIds.length; i++) {
				Email email = new Email();
				toAccountList.add(userIds[i]);
				email.setEmailId(GuId.getGuid());
				email.setBodyId(bodyId);
				email.setToId(userIds[i]);
				email.setOrgId(orgId);
				returnData = emailL.sendEmail(dbConn, email);
			}
			if(sendOther!=""){
				if(sendOther.indexOf(",")>-1){
					String[] userIdss = sendOther.split(",");
					for (int i = 0; i < userIdss.length; i++) {
						if(!emailL.CheckCopyMail(dbConn, userIdss[i], bodyId)){
							Email email = new Email();
							email.setEmailId(GuId.getGuid());
							email.setBodyId(bodyId);
							email.setToId(userIdss[i]);
							email.setOrgId(orgId);
							returnData = emailL.sendEmail(dbConn, email);
						}
					}
				}else{
					if(!emailL.CheckCopyMail(dbConn, sendOther, bodyId)){
						Email email = new Email();
						email.setEmailId(emailId);
						email.setBodyId(bodyId);
						email.setToId(sendOther);
						email.setOrgId(orgId);
						returnData = emailL.sendEmail(dbConn, email);
					}
				}
			}
			//后成提醒方式
			MessageApi messageApi = new MessageApi();
			messageApi.sendMessage(dbConn, "email", smsRemindJson, "您有一封新邮件，请注意查收。", account.getAccountId(), toAccountList,account.getOrgId());
			
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
	 * 从草稿箱保存
	 * Time:2015-3-31
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateEmailByDraft(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String emailId = request.getParameter("emailId");
			String bodyId = request.getParameter("bodyId");
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String subject = request.getParameter("subject");
			String accountId = request.getParameter("accountId");
			String sendOther = request.getParameter("sendOther");
			String attachmentId = request.getParameter("attachId");
			String attachmentName = request.getParameter("attachmentName");
			String smsRemind = request.getParameter("smsRemind");
			String important = request.getParameter("important");
			String fromWebmailId = request.getParameter("fromWebmailId");
			String toWebmail = request.getParameter("toWebmail");
			String toWebmailCopy = request.getParameter("toWebmailCopy");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			EmailBody body = new EmailBody();
			body.setBodyId(bodyId);
			body.setToId(accountId);
			body.setFromId(account.getAccountId());
			body.setSubject(subject);
			body.setContent(content);
			body.setCopyToId(sendOther);
			body.setAttachmentId(attachmentId);
			body.setAttachmentName(attachmentName);
			body.setSmsRemind(smsRemind);
			body.setImportant(important);
			body.setFromWebmailId(fromWebmailId);
			body.setToWebmail(toWebmail);
			body.setToWebmailCopy(toWebmailCopy);
			body.setOrgId(account.getOrgId());
			//修改邮件内容
			emailL.updateEmailBody(dbConn, body);
			Email email = new Email();
			email.setEmailId(emailId);
			email.setBodyId(bodyId);
			email.setToId(accountId);
			email.setOrgId(orgId);
			//修改邮件
			returnData = emailL.updateEmail(dbConn, email);
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
	 * 批量发送邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendEmails(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String emailId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			String[] id = emailId.split("%2C");
			EmailLogic emailL = new EmailLogic();
			for (int i = 0; i < id.length; i++) {
				returnData = new EmailLogic().updateEmailBox(dbConn,id[i],"2",orgId);
				String toId = emailL.getToIdByEmailId(dbConn, id[i]);
				String bodyId = emailL.copyEmailBody(dbConn, id[i], "1", orgId);
				if(toId.indexOf(",") > -1){
					String[] toIds = toId.split(",");
					for (int j = 0; j < toIds.length; j++) {
						emailL.copyEmail(dbConn, id[i], "1", orgId,toIds[j],bodyId);
					}
				}else{
					emailL.copyEmail(dbConn, id[i], "1", orgId,toId,bodyId);
				}
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
	 * 批量再次发送邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendEmailAgain(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String emailId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			if(emailId.length() > 36){
				String[] id = emailId.split("%2C");
				for (int i = 0; i < id.length; i++) {
					String toId = emailL.getToIdByEmailId(dbConn, id[i]);
					String bodyId = emailL.copyEmailBody(dbConn, id[i], "1", orgId);
					if(toId.indexOf(",") > -1){
						String[] toIds = toId.split(",");
						for (int j = 0; j < toIds.length; j++) {
							returnData = emailL.copyEmail(dbConn, id[i], "1", orgId,toIds[j],bodyId);
						}
					}else{
						returnData = emailL.copyEmail(dbConn, id[i], "1", orgId,toId,bodyId);
					}
				}
			}else{
				String toId = emailL.getToIdByEmailId(dbConn, emailId);
				String bodyId = emailL.copyEmailBody(dbConn, emailId, "1", orgId);
				if(toId.indexOf(",") > -1){
					String[] toIds = toId.split(",");
					for (int i = 0; i < toIds.length; i++) {
						emailL.copyEmail(dbConn, emailId, "1", orgId,toIds[i],bodyId);
					}
				}else{
					emailL.copyEmail(dbConn, emailId, "1", orgId,toId,bodyId);
				}
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
	 * 批量再次发送邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void restoreEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String emailId = request.getParameter("id");
			String boxId = request.getParameter("boxId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			EmailLogic emailL = new EmailLogic();
			if(emailId.length() > 36){
				String[] id = emailId.split("%2C");
				for (int i = 0; i < id.length; i++) {
					returnData = new EmailLogic().updateEmailBox(dbConn, id[i], boxId, orgId);
				}
			}else{
				String toId = emailL.getToIdByEmailId(dbConn, emailId);
				String bodyId = emailL.copyEmailBody(dbConn, emailId, "1", orgId);
				if(toId.indexOf(",") > -1){
					String[] toIds = toId.split(",");
					for (int i = 0; i < toIds.length; i++) {
						emailL.copyEmail(dbConn, emailId, "1", orgId,toIds[i],bodyId);
					}
				}else{
					emailL.copyEmail(dbConn, emailId, "1", orgId,toId,bodyId);
				}
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
	 * 下一封邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNextEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			int id = Integer.parseInt( request.getParameter("id"));
			String boxId = request.getParameter("boxId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getNextEmail(dbConn, id, orgId,account.getAccountId(),boxId);
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
	 * 上一封邮件
	 * Time : 2015-3-31
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getLastEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			int id = Integer.parseInt( request.getParameter("id"));
			String boxId = request.getParameter("boxId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getLastEmail(dbConn, id, orgId,account.getAccountId(),boxId);
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
	 * 得到邮件文件夹
	 * Time:2015-5-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailBoxList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailBoxList(dbConn,account.getAccountId(),orgId);
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
	 * 添加邮件文件夹
	 * Time:2015-5-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addEmailBox(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			EmailBox  emailBox = new EmailBox();
			emailBox.setBoxId(GuId.getGuid());
			emailBox.setSortId(request.getParameter("sortId"));
			emailBox.setBoxPid("0");
			emailBox.setBoxName(request.getParameter("boxName"));
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			emailBox.setAccountId(account.getAccountId());
			emailBox.setOrgId(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new EmailLogic().addEmailBox(dbConn,emailBox);
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
	 * 根据Id查找邮件文件夹
	 * Time:2015-5-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailBoxById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String boxId = request.getParameter("boxId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailBoxById(dbConn,boxId,account.getAccountId(),orgId);
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
	 * 修改邮件文件夹
	 * Time:2015-5-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateEmailBox(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			EmailBox  emailBox = new EmailBox();
			emailBox.setBoxId(request.getParameter("boxId"));
			emailBox.setSortId(request.getParameter("sortId"));
			emailBox.setBoxPid("0");
			emailBox.setBoxName(request.getParameter("boxName"));
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			emailBox.setAccountId(account.getAccountId());
			emailBox.setOrgId(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new EmailLogic().updateEmailBox(dbConn,emailBox);
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
	 * 删除邮件文件夹
	 * Time:2015-5-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteEmailBox(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String boxId = request.getParameter("boxId");
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId = account.getAccountId();
			String orgId = account.getOrgId();
			dbConn = DbPoolConnection.getInstance().getConnection();
			int i = new EmailLogic().checkEmailBox(dbConn, boxId);
			
			if(i == 1){
				returnData = 0;
			}else{
				returnData = new EmailLogic().deleteEmailBox(dbConn,boxId,accountId,orgId);
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
	 * 根据id得到用户邮箱配置
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getEmailConfigById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new EmailLogic().getEmailConfigById(dbConn,account.getAccountId(),orgId);
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
	 * 根据id修改用户邮箱配置
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateEmailConfig(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			EmailConfig  emailConfig = new EmailConfig();
			emailConfig.setConfigId(request.getParameter("configId"));
			emailConfig.setEmailServer(request.getParameter("emailServer"));
			emailConfig.setServerPort(request.getParameter("serverPort"));
			emailConfig.setEmailUser(request.getParameter("emailUser"));
			emailConfig.setEmailPwd(request.getParameter("emailPwd"));
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			emailConfig.setAccountId(account.getAccountId());
			emailConfig.setOrgId(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			
			if(request.getParameter("configId")!=""){
				returnData = new EmailLogic().updateEmailConfig(dbConn,emailConfig);
			}else{
				emailConfig.setConfigId(GuId.getGuid());
				returnData = new EmailLogic().addEmailConfig(dbConn,emailConfig);
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
	 * 得到未读邮件
	 * Author:Yzz
	 * Time:2015-5-22
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNoReadEmail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId = account.getAccountId();
			String orgId = account.getOrgId();
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new EmailLogic().getNoReadEmail(dbConn,accountId,orgId);
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
