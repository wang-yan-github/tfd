package notice.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONObject;
import notice.data.Notice;
import notice.logic.NoticeLogic;

public class NoticeAct {
	/**
	 * 添加通知公告信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(GuId.getGuid());
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setAccountId(request.getParameter("accountId"));
			notice.setDeptPriv(request.getParameter("deptPriv"));
			notice.setUserPriv(request.getParameter("userPriv"));
			notice.setCreatetime(request.getParameter("createTime"));
			if(!request.getParameter("endTime").equals("")){
				notice.setEndTime(request.getParameter("endTime"));	
			}else{
				notice.setEndTime("0");
			}
			notice.setAttachId(request.getParameter("attachId"));
			notice.setAttachPriv(request.getParameter("attachPriv"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setOnclickCount(0);
			notice.setNoticeStatus("0");
			notice.setTop(request.getParameter("top"));
			notice.setCreateUser(account.getAccountId());
			notice.setAttachPower(request.getParameter("attachPower"));
			notice.setOrgId(account.getOrgId());
			notice.setIsSms(request.getParameter("smsReminds"));
			notice.setOnclickCount(0);
			notice.setReader("");
			data = noticeLogic.addnoticeLogic(dbConn, notice);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 直接发布通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void publishnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(GuId.getGuid());
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setAccountId(request.getParameter("accountId"));
			notice.setDeptPriv(request.getParameter("deptPriv"));
			notice.setUserPriv(request.getParameter("userPriv"));
			notice.setCreatetime(request.getParameter("createTime"));
			if(!request.getParameter("endTime").equals("")){
				notice.setEndTime(request.getParameter("endTime"));	
			}else{
				notice.setEndTime("0");
			}
			notice.setAttachId(request.getParameter("attachId"));
			notice.setAttachPriv(request.getParameter("attachPriv"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setTop(request.getParameter("top"));
			notice.setApprovalStaff(request.getParameter("approvalStaff"));
			notice.setApprovalStatus(request.getParameter("approvalStatus"));
			notice.setCreateUser(account.getAccountId());
			notice.setIsSms(request.getParameter("smsReminds"));
			notice.setNoticeStatus("1");
			notice.setOrgId(account.getOrgId());
			notice.setAttachPower(request.getParameter("attachPower"));
			notice.setReader("");
			data = noticeLogic.publishnoticeLogic(dbConn, notice);
			if(request.getParameter("approvalStatus").equals("1")){
				
				
				String smsRemindStr = request.getParameter("smsReminds");
				JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
				List<String> toAccountList = new ArrayList<String>();
				String accountId="";
				if(notice.getAccountId().equals("userAll")||notice.getDeptPriv().equals("deptAll")||notice.getUserPriv().equals("privAll")){
					accountId=new AccountLogic().getorgIdAllLogic(dbConn, account.getOrgId());
				}
				else{
				accountId+=request.getParameter("accountId")+",";
				String deptPriv=request.getParameter("deptPriv");
				String userpriv=request.getParameter("userPriv");
				UserInfoLogic userInfoLogic=new UserInfoLogic();
				String[] deptIds =null;
				if(deptPriv.indexOf(",")>-1){
					deptIds = deptPriv.split(",");
				}else
				{
					deptIds=new String[]{deptPriv};
				}
				String deptaccountId="";
				for (int i = 0; i < deptIds.length; i++) {
					deptaccountId=userInfoLogic.getdeptIdLogic(dbConn, deptIds[i], account.getOrgId());
				}
				if(deptaccountId.length()!=0){
					accountId+=deptaccountId;
					accountId+=",";
				}
				String[] privIds =null;
				if(userpriv.indexOf(",")>-1){
					privIds = userpriv.split(",");
				}else
				{
					privIds=new String[]{userpriv};
				}
				String privaccountId="";
				for (int i = 0; i < privIds.length; i++) {
					privaccountId=userInfoLogic.getprivIdLogic (dbConn, privIds[i], account.getOrgId());
				}
				if(privaccountId.length()!=0){
					accountId+=privaccountId;
					accountId+=",";
				}
				if(accountId.length()!=1){
					accountId=accountId.substring(0,accountId.length()-1);
				}
				}
				String[] accountIds=null;
				if(accountId.indexOf(",")>-1){
					accountIds=accountId.split(",");
				}
				Collections.addAll(toAccountList,accountIds );
				HashSet<String> hs = new HashSet<String>(toAccountList);
				toAccountList=new ArrayList<String>(hs);
				MessageApi messageApi = new MessageApi();
				messageApi.addMessage(dbConn, "notice", smsRemindJson, "未读公告", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
				}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 根据公告noticeId 修改通知公告信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatenoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(request.getParameter("noticeId"));
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setAccountId(request.getParameter("accountId"));
			notice.setDeptPriv(request.getParameter("deptPriv"));
			notice.setUserPriv(request.getParameter("userPriv"));
			notice.setCreatetime(request.getParameter("createTime"));
			if(!request.getParameter("endTime").equals("")){
				notice.setEndTime(request.getParameter("endTime"));	
			}else{
				notice.setEndTime("0");
			}
			notice.setAttachId(request.getParameter("attachId"));
			notice.setAttachPriv(request.getParameter("attachPriv"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setTop(request.getParameter("top"));
			notice.setCreateUser(account.getAccountId());
			notice.setIsSms(request.getParameter("smsReminds"));
			notice.setAttachPower(request.getParameter("attachPower"));
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.updatenoticeLogic(dbConn, notice);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 根据noticeId 改变终止时间
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void endNoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String noticeId=request.getParameter("noticeId");
			String endTime=request.getParameter("endTime");
			if(endTime.equals("")){
				endTime=SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
			}		
			NoticeLogic noticeLogic=new NoticeLogic();
			data=noticeLogic.endNoticeLogic(dbConn, noticeId, endTime, account);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
		}
	}
	/**
	 * 批量修改通知公告终止时间
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void endsnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String [] noticeId = request.getParameterValues("noticeId");
			String endTime=request.getParameter("endTime");
			if(endTime.equals("")){
				endTime=SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
			}		
			NoticeLogic noticeLogic=new NoticeLogic();
			for(int i=0;noticeId.length>i;i++)
			{
			data=noticeLogic.endNoticeLogic(dbConn, noticeId[i], endTime, account);
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 对已保存的通知公告进行修改并发布
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void publishupdatenoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(request.getParameter("noticeId"));
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setAccountId(request.getParameter("accountId"));
			notice.setDeptPriv(request.getParameter("deptPriv"));
			notice.setUserPriv(request.getParameter("userPriv"));
			notice.setCreatetime(request.getParameter("createTime"));
			if(!request.getParameter("endTime").equals("")){
				notice.setEndTime(request.getParameter("endTime"));	
			}else{
				notice.setEndTime("0");
			}
			notice.setAttachId(request.getParameter("attachId"));
			notice.setAttachPriv(request.getParameter("attachPriv"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setApprovalStaff(request.getParameter("approvalStaff"));
			notice.setApprovalStatus(request.getParameter("approvalStatus"));
			notice.setTop(request.getParameter("top"));
			notice.setAttachPower(request.getParameter("attachPower"));
			notice.setCreateUser(account.getAccountId());
			notice.setIsSms(request.getParameter("smsReminds"));
			notice.setNoticeStatus("1");
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.publishupdatenoticeLogic(dbConn, notice);
			
			if(request.getParameter("approvalStatus").equals("1")){
				String smsRemindStr = request.getParameter("smsRemind");
				JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
				List<String> toAccountList = new ArrayList<String>();
				String accountId="";
				if(notice.getAccountId().equals("userAll")||notice.getDeptPriv().equals("deptAll")||notice.getUserPriv().equals("privAll")){
					accountId=new AccountLogic().getorgIdAllLogic(dbConn, account.getOrgId());
				}
				else{
					accountId+=request.getParameter("accountId")+",";
					String deptPriv=request.getParameter("deptPriv");
					String userpriv=request.getParameter("userPriv");
					UserInfoLogic userInfoLogic=new UserInfoLogic();
					String[] deptIds =null;
					if(deptPriv.indexOf(",")>-1){
						deptIds = deptPriv.split(",");
					}else
					{
						deptIds=new String[]{deptPriv};
					}
					String deptaccountId="";
					for (int i = 0; i < deptIds.length; i++) {
						deptaccountId=userInfoLogic.getdeptIdLogic(dbConn, deptIds[i], account.getOrgId());
					}
					if(deptaccountId.length()!=0){
						accountId+=deptaccountId;
						accountId+=",";
					}
					String[] privIds =null;
					if(userpriv.indexOf(",")>-1){
						privIds = userpriv.split(",");
					}else
					{
						privIds=new String[]{userpriv};
					}
					String privaccountId="";
					for (int i = 0; i < privIds.length; i++) {
						privaccountId=userInfoLogic.getprivIdLogic (dbConn, privIds[i], account.getOrgId());
					}
					if(privaccountId.length()!=0){
						accountId+=privaccountId;
						accountId+=",";
					}
					if(accountId.length()!=1){
						accountId=accountId.substring(0,accountId.length()-1);
					}
				}
				String[] accountIds=null;
				if(accountId.indexOf(",")>-1){
					accountIds=accountId.split(",");
				}
				Collections.addAll(toAccountList,accountIds );
				HashSet<String> hs = new HashSet<String>(toAccountList);
				toAccountList=new ArrayList<String>(hs);
				MessageApi messageApi = new MessageApi();
				messageApi.addMessage(dbConn, "notice", smsRemindJson, "未读公告", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
				}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 获取通知公告列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getnoticelistAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			String noticeType=request.getParameter("noticeType");
			NoticeLogic noticeLogic=new NoticeLogic();
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data = noticeLogic.getnoticelistLogic(dbConn, noticeType,account, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 删除通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			String [] noticeId = request.getParameterValues("noticeId");
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setOrgId(account.getOrgId());
			notice.setCreateUser(account.getAccountId());
			for(int i=0;noticeId.length>i;i++)
			{
			notice.setNoticeId(noticeId[i]);
			data = noticeLogic.delnoticeLogic(dbConn, notice);
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 获取最前面的三条通知公告信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getreadNoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			NoticeLogic noticeLogic=new NoticeLogic();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			pramList.add(","+account.getAccountId()+",");
			data=noticeLogic.getreadNoticeLogic(dbConn, pramList);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	
	/**
	 * 获取未看的通知公告列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNoreadnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			NoticeLogic noticeLogic=new NoticeLogic();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(","+account.getAccountId()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			String noticeType=request.getParameter("noticeType");
			data = noticeLogic.getNoreadnoticeLogic(dbConn,noticeType, account,pramList, pageSize, page, sortOrder, sortName,deptId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 获取未读通知公告的数量
	 * Time 2015-12-02
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void nonoticeNumberAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		JSONObject json = new JSONObject();
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			NoticeLogic noticeLogic=new NoticeLogic();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(","+account.getAccountId()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			data=noticeLogic.nonoticeNumberLogic(dbConn, pramList);
			json.accumulate("noReadNum", data);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();			
			}
	}
	/**
	 * 获取已读的通知公告列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getreadnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			String noticeType=request.getParameter("noticeType");
			String createTime=request.getParameter("createTime");
			NoticeLogic noticeLogic=new NoticeLogic();
			data = noticeLogic.getreadnoticeLogic(dbConn,noticeType,createTime, account,pramList, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	
	/**
	 * 获取 历史通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void gethistorynoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				String pageStr=request.getParameter("page");
				String pageSizeStr=request.getParameter("rows");
				String sortName=request.getParameter("sort");
				String sortOrder=request.getParameter("order");
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
				String deptId=(String) request.getSession().getAttribute("DEPT_ID");
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				UserInfoLogic userInfoLogic = new UserInfoLogic();
				List<String> pramList = new ArrayList<String>();
				pramList.add(","+account.getAccountId()+",");
				pramList.add(","+deptId+",");
				pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
				pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
				pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
				pramList.add(account.getOrgId());
				String noticeType=request.getParameter("noticeType");
				String createTime=request.getParameter("createTime");
				NoticeLogic noticeLogic=new NoticeLogic();
				data = noticeLogic.gethistorynoticeAct(dbConn, noticeType, createTime, account, pramList, pageSize, page, sortOrder, sortName, deptId);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 根据noticeId 获取通知公告信息
		 * Author: Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
	public void getIdnoticAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			NoticeLogic noticeLogic=new NoticeLogic();
			String noticeId=request.getParameter("noticeId");
			data = noticeLogic.getIdnoticLogic(dbConn, noticeId, account, pramList);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 读通知公告之后的处理
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setreadnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(request.getParameter("noticeId"));
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.setreadnoticeLogic(dbConn, notice, account);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查看未审批的通知公告
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookNoapprovalnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setApprovalStaff(account.getAccountId());
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.lookNoapprovalnoticelogic(dbConn, notice, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 查看已审批的通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void lookapprovalnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				String pageStr=request.getParameter("page");
				String pageSizeStr=request.getParameter("rows");
				String sortName=request.getParameter("sort");
				String sortOrder=request.getParameter("order");
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
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Notice notice=new Notice();
				NoticeLogic noticeLogic=new NoticeLogic();
				notice.setApprovalStaff(account.getAccountId());
				notice.setOrgId(account.getOrgId());
				data = noticeLogic.lookapprovalnoticelogic(dbConn, notice, pageSize, page, sortOrder, sortName);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		/**
		 * 通知公告的审批通过处理
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void approvalpassnoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice=new Notice();
			NoticeLogic noticeLogic=new NoticeLogic();
			notice.setNoticeId(request.getParameter("noticeId"));
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.approvalpassnoticelogic(dbConn, notice);
			String smsRemindStr = request.getParameter("smsReminds");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			List<String> toAccountList = new ArrayList<String>();
			String accountId="";
			if(request.getParameter("accountId").equals("userAll")||request.getParameter("deptPriv").equals("deptAll")||request.getParameter("userPriv").equals("privAll")){
				accountId=new AccountLogic().getorgIdAllLogic(dbConn, account.getOrgId());
			}
			else{
			accountId+=request.getParameter("accountId")+",";
			String deptPriv=request.getParameter("deptPriv");
			String userpriv=request.getParameter("userPriv");
			UserInfoLogic userInfoLogic=new UserInfoLogic();
			String[] deptIds =null;
			if(deptPriv.indexOf(",")>-1){
				deptIds = deptPriv.split(",");
			}else
			{
				deptIds=new String[]{deptPriv};
			}
			String deptaccountId="";
			for (int i = 0; i < deptIds.length; i++) {
				deptaccountId=userInfoLogic.getdeptIdLogic(dbConn, deptIds[i], account.getOrgId());
			}
			if(deptaccountId.length()!=0){
				accountId+=deptaccountId;
				accountId+=",";
			}
			String[] privIds =null;
			if(userpriv.indexOf(",")>-1){
				privIds = userpriv.split(",");
			}else
			{
				privIds=new String[]{userpriv};
			}
			String privaccountId="";
			for (int i = 0; i < privIds.length; i++) {
				privaccountId=userInfoLogic.getprivIdLogic (dbConn, privIds[i], account.getOrgId());
			}
			if(privaccountId.length()!=0){
				accountId+=privaccountId;
				accountId+=",";
			}
			if(accountId.length()!=1){
				accountId=accountId.substring(0,accountId.length()-1);
			}
			}
			String[] accountIds=null;
			if(accountId.indexOf(",")>-1){
				accountIds=accountId.split(",");
			}
			Collections.addAll(toAccountList,accountIds );
			HashSet<String> hs = new HashSet<String>(toAccountList);
			toAccountList=new ArrayList<String>(hs);
			MessageApi messageApi = new MessageApi();
			messageApi.addMessage(dbConn, "notice", smsRemindJson, "未读公告", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
		}
		/**
		 * 审批不通过的处理
		 * Author: Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
	public void approvalnopassnoticeAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			Notice notice = new Notice();
			NoticeLogic noticeLogic = new NoticeLogic();
			notice.setNoticeId(request.getParameter("noticeId"));
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.approvalnopassnoticelogic(dbConn, notice);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}

	/**
	 * 多条件模糊查询通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void querynoticeAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice = new Notice();
			NoticeLogic noticeLogic = new NoticeLogic();
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setNoticeStatus(request.getParameter("noticeStatus"));
			notice.setApprovalStatus(request.getParameter("approvalStatus"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setCreateUser(account.getAccountId());
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.querynoticelogic(dbConn, notice, starttime, endtime, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	/**
	 * 进行多条件的删除公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void termdelnoticeAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice = new Notice();
			NoticeLogic noticeLogic = new NoticeLogic();
			notice.setNoticeType(request.getParameter("noticeType"));
			notice.setNoticeStatus(request.getParameter("noticeStatus"));
			notice.setApprovalStatus(request.getParameter("approvalStatus"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			notice.setCreateUser(account.getAccountId());
			notice.setOrgId(account.getOrgId());
			data = noticeLogic.termdelnoticelogic(dbConn, notice, starttime, endtime);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	
	/**
	 * 多条件的查看通知公告
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void termqueryAct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection dbConn=null;
		String data=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
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
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Notice notice = new Notice();
			NoticeLogic noticeLogic = new NoticeLogic();
			notice.setCreateUser(request.getParameter("accountId"));
			notice.setNoticeType(request.getParameter("noticeType"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			notice.setNoticetitle(request.getParameter("noticeTitle"));
			notice.setNoticeContent(request.getParameter("noticeContent"));
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(account.getOrgId());
			data = noticeLogic.termquerylogic(dbConn, pramList, notice, starttime, endtime, pageSize, page, sortOrder, sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();
			}
	}
	
	/**
	 * 根据公告Id 获取所有可读该公告的人员信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void getIdreadstatusAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			
			try {
				NoticeLogic noticeLogic=new NoticeLogic();
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
				String noticeId=request.getParameter("noticeId");
				data=noticeLogic.getIdreadstatusLogic(dbConn, noticeId, account);
				dbConn.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbOp dbop = new DbOp();
				dbop.connClose(dbConn);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(data);
				response.getWriter().flush();
				}
		}
		
		/**根据公告Id 获取所有已读该公告的人员信息
		 * Author: Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
				public void getreadUserAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						NoticeLogic noticeLogic=new NoticeLogic();
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
						String noticeId=request.getParameter("noticeId");
						data=noticeLogic.getreadUserLogic(dbConn, noticeId, account);
						dbConn.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						DbOp dbop = new DbOp();
						dbop.connClose(dbConn);
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print(data);
						response.getWriter().flush();
						}
				}
				/**
				 * 查询下一篇通知公告信息
				 * Author: Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
				public void downNoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						UserInfoLogic userInfoLogic = new UserInfoLogic();
						String deptId=(String) request.getSession().getAttribute("DEPT_ID");
						List<String> pramList = new ArrayList<String>();
						pramList.add(","+account.getAccountId()+",");
						pramList.add(","+deptId+",");
						pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
						pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
						NoticeLogic noticeLogic=new NoticeLogic();
						String Id=request.getParameter("Id");
						data=noticeLogic.downNoticeLogic(dbConn, Id, account, pramList);
						dbConn.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						DbOp dbop = new DbOp();
						dbop.connClose(dbConn);
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print(data);
						response.getWriter().flush();
						}
				}
				/**
				 * 上一篇通知公告
				 * Author: Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
				public void upNoticeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						UserInfoLogic userInfoLogic = new UserInfoLogic();
						String deptId=(String) request.getSession().getAttribute("DEPT_ID");
						List<String> pramList = new ArrayList<String>();
						pramList.add(","+account.getAccountId()+",");
						pramList.add(","+deptId+",");
						pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
						pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
						NoticeLogic noticeLogic=new NoticeLogic();
						String Id=request.getParameter("Id");
						data=noticeLogic.upNoticeLogic(dbConn, Id, account, pramList);
						dbConn.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						DbOp dbop = new DbOp();
						dbop.connClose(dbConn);
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print(data);
						response.getWriter().flush();
						}
				}
}
