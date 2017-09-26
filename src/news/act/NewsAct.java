package news.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import news.data.News;
import news.logic.NewsLogic;
import tfd.system.messageunit.MessageApi;
import tfd.system.mobile.jpush.JpushLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

public class NewsAct {
	/**
	 * 添加新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			News news = new News();
			news.setNewsId(GuId.getGuid());
			news.setTitle(request.getParameter("title"));
			news.setType(request.getParameter("newstype"));
			news.setUserPriv(request.getParameter("userPriv"));
			news.setDeptPriv(request.getParameter("deptPriv"));
			news.setAccountId(request.getParameter("accountId"));
			news.setCreateTime(request.getParameter("createTime"));
			news.setAttachId(request.getParameter("attachId"));
			news.setContect(request.getParameter("contect"));
			news.setTop(request.getParameter("top"));
			if(!request.getParameter("endTime").equals("")){
			news.setEndTime(request.getParameter("endTime"));
			}else{
			news.setEndTime("0");
			}
			news.setCommentStatus(request.getParameter("commentStatus"));
			news.setOnclickCount(0);
			news.setOrgId(account.getOrgId());
			news.setIsSms(request.getParameter("smsReminds"));
			news.setCreateUser(account.getAccountId());
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.addNewsLogic(dbConn, news);
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
	 * 修改新闻终止时间
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void endNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newsId=request.getParameter("newsId");
			String endTime=request.getParameter("endTime");
			if(endTime.equals("")){
				endTime=SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
			}		
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.endNewsLogic(dbConn,newsId,endTime, account);
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
	 * 根据newsId 修改新闻信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			News news = new News();
			news.setNewsId(request.getParameter("newsId"));
			news.setTitle(request.getParameter("title"));
			news.setType(request.getParameter("newstype"));
			news.setUserPriv(request.getParameter("userPriv"));
			news.setDeptPriv(request.getParameter("deptPriv"));
			news.setAccountId(request.getParameter("accountId"));
			news.setCreateTime(request.getParameter("createTime"));
			news.setAttachId(request.getParameter("attachId"));
			news.setContect(request.getParameter("contect"));
			news.setTop(request.getParameter("top"));
			if(!request.getParameter("endTime").equals("")){
				news.setEndTime(request.getParameter("endTime"));
				}else{
				news.setEndTime("0");
				}
			news.setCommentStatus(request.getParameter("commentStatus"));
			news.setCreateUser(account.getAccountId());
			news.setIsSms(request.getParameter("smsReminds"));
			news.setOrgId(account.getOrgId());
			NewsLogic newsLogic = new NewsLogic();
			data = newsLogic.updateNewsLogic(dbConn, news);
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
	 * 批量删除新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		 try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String [] newsId = request.getParameterValues("newsId");
			String orgId=account.getOrgId();
			String createUser=account.getAccountId();
			NewsLogic newsLogic = new NewsLogic();
			for(int i=0;newsId.length>i;i++)
			{
				data=newsLogic.delNewsLogic(dbConn,newsId[i],orgId,createUser);
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
	 * 批量修改新闻的终止时间
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void endsNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn=null;
		int data=0;
		 try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String [] newsId = request.getParameterValues("newsId");
			NewsLogic newsLogic = new NewsLogic();
			String endTime=request.getParameter("endTime");
			if(endTime.equals("")){
				endTime=SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
			}
			for(int i=0;newsId.length>i;i++)
			{
				data=newsLogic.endNewsLogic(dbConn,newsId[i],endTime, account);
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
	 * 获取新闻列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNewsListAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData=null;
		Connection dbConn=null;
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
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newstype=request.getParameter("newstype");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getAccountId());
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			NewsLogic newsLogic = new NewsLogic();
			returnData=newsLogic.getNewsListLogic(dbConn,newstype,pramList,pageSize,page,sortOrder,sortName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
		}
	}
	/**
	 * 通过newsId 获取新闻详情
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNewsByNewsIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData=null;
		Connection dbConn=null;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			String newsId=request.getParameter("newsId");
			NewsLogic newsLogic = new NewsLogic();
			returnData=newsLogic.getNewsByNewsIdLogic(dbConn,newsId,account,pramList);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.getWriter().print(returnData);
		response.getWriter().flush();
		}
	}
	/**
	 * 获取最新的三条新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNoreadNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String returnData=null;
		Connection dbConn=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			pramList.add(","+account.getAccountId()+",");
			NewsLogic newsLogic = new NewsLogic();
			returnData=newsLogic.getNoreadNewsLogic(dbConn, pramList);
			dbConn.commit();
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
	/**
	 * 获取未读的新闻列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNoReadNewsList(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String returnData=null;
		Connection dbConn=null;
		
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String storName=request.getParameter("sortname");
			String storOrder=request.getParameter("sortorder");
			int page=1;
			int pageSize=30;
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
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			String newstype=request.getParameter("newstype");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(","+account.getAccountId()+",");
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(account.getOrgId());
			NewsLogic newsLogic = new NewsLogic();
			returnData = newsLogic.getNoReadNewsListLogic(dbConn,newstype,pramList,pageSize,page,storOrder,storName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
		}
	}
	/**
	 * 查看新闻之后进行已读人员的添加
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setReadNews(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		int data=0;
		Connection dbConn=null;
		try {
			String newsId=request.getParameter("newsId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.setReadNewsLogic(dbConn, newsId, account);
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
	 * 获取已读新闻列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPassReadNewsList(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn =null;
		String returnDate=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String storName=request.getParameter("sortname");
			String storOrder=request.getParameter("sortorder");
			int page=1;
			int pageSize=30;
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
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(account.getOrgId());
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			NewsLogic newsLogic = new NewsLogic();
			String newstype=request.getParameter("newstype");
			String createTime=request.getParameter("createTime");
			returnDate = newsLogic.getPassReadNewsListLogic(dbConn,newstype,createTime,pramList,pageSize,page,storOrder,storName);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnDate);
		response.getWriter().flush();
		}
	}
	
	/**
	 * 获取历史新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void gethistoryNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn =null;
		String returnDate=null;
		try {
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String storName=request.getParameter("sortname");
			String storOrder=request.getParameter("sortorder");
			int page=1;
			int pageSize=30;
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
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(account.getOrgId());
			pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			NewsLogic newsLogic = new NewsLogic();
			String newstype=request.getParameter("newstype");
			String createTime=request.getParameter("createTime");
			returnDate = newsLogic.gethistoryNewsLogic(dbConn, newstype, pramList, pageSize, page, storOrder, storName,createTime);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnDate);
		response.getWriter().flush();
		}
	}
	/**
	 * 添加新闻并直接发布
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void publishNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int data=0;
		Connection dbConn=null;
		
		 try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			News news = new News();
			news.setNewsId(GuId.getGuid());
			news.setTitle(request.getParameter("title"));
			news.setType(request.getParameter("newstype"));
			news.setUserPriv(request.getParameter("userPriv"));
			news.setDeptPriv(request.getParameter("deptPriv"));
			news.setAccountId(request.getParameter("accountId"));
			news.setCreateTime(request.getParameter("createTime"));
			news.setAttachId(request.getParameter("attachId"));
			news.setContect(request.getParameter("contect"));
			if(!request.getParameter("endTime").equals("")){
				news.setEndTime(request.getParameter("endTime"));
				}else{
				news.setEndTime("0");
				}
			news.setTop(request.getParameter("top"));
			news.setCommentStatus(request.getParameter("commentStatus"));
			news.setOrgId(account.getOrgId());
			news.setCreateUser(account.getAccountId());
			news.setStatus("1");
			news.setApprovalStaff(request.getParameter("approvalStaff"));
			news.setApprovalStatus(request.getParameter("approvalStatus"));
			news.setIsSms(request.getParameter("smsReminds"));
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.publishNewsLogic(dbConn, news);
			
			//极光推送
			Map<String, String> extras = new HashMap<String, String>();
			String alertString="您有一条标题："+news.getTitle()+"  新闻未读";
			JSONArray aliases=new JSONArray();
			aliases.add("123");
			aliases.add("1234");
			Integer resultNum=0;
			JpushLogic jp=new JpushLogic();
			resultNum= jp.JpushALogic(aliases, extras, alertString, news.getCreateTime());
			
			
			if(request.getParameter("approvalStatus").equals("1")){
			String smsRemindStr = request.getParameter("smsReminds");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			List<String> toAccountList = new ArrayList<String>();
			String accountId="";
			if(news.getAccountId().equals("userAll")||news.getDeptPriv().equals("deptAll")||news.getUserPriv().equals("privAll")){
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
			messageApi.addMessage(dbConn, "news", smsRemindJson, "未读新闻", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
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
	 * 当前新闻已存在的情况下发布新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void publishUpdateNewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		int data=0;
		Connection dbConn=null;
		
		 try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			News news = new News();
			news.setNewsId(request.getParameter("newsId"));
			news.setTitle(request.getParameter("title"));
			news.setType(request.getParameter("newstype"));
			news.setUserPriv(request.getParameter("userPriv"));
			news.setDeptPriv(request.getParameter("deptPriv"));
			news.setAccountId(request.getParameter("accountId"));
			news.setCreateTime(request.getParameter("createTime"));
			news.setAttachId(request.getParameter("attachId"));
			news.setContect(request.getParameter("contect"));
			if(!request.getParameter("endTime").equals("")){
				news.setEndTime(request.getParameter("endTime"));
				}else{
				news.setEndTime("0");
				}
			news.setTop(request.getParameter("top"));
			news.setCommentStatus(request.getParameter("commentStatus"));
			news.setCreateUser(account.getAccountId());
			news.setStatus("1");
			news.setApprovalStaff(request.getParameter("approvalStaff"));
			news.setApprovalStatus(request.getParameter("approvalStatus"));
			news.setOrgId(account.getOrgId());
			news.setIsSms(request.getParameter("smsReminds"));
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.publishupdateNewsLogic(dbConn, news);
			if(request.getParameter("approvalStatus").equals("1")){
				String smsRemindStr = request.getParameter("smsReminds");
				JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
				List<String> toAccountList = new ArrayList<String>();
				String accountId="";
				if(news.getAccountId().equals("userAll")||news.getDeptPriv().equals("deptAll")||news.getUserPriv().equals("privAll")){
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
				messageApi.addMessage(dbConn, "news", smsRemindJson, "未读新闻", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
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
	 * 多条件的新闻查询
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void QuerynewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String data=null;
		Connection dbConn=null;
		
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
			News news = new News();
			news.setCreateUser(account.getAccountId());
			news.setOrgId(account.getOrgId());
			news.setType(request.getParameter("newstype"));
			news.setStatus(request.getParameter("status"));
			news.setTop(request.getParameter("top"));
			news.setTitle(request.getParameter("title"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			news.setContect(request.getParameter("contect"));
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.querynewsLogic(dbConn, news, starttime, endtime,pageSize,page,sortOrder,sortName);
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
	 * 根据多条件进行删除新闻
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void DelnewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			News news = new News();
			news.setCreateUser(account.getAccountId());
			news.setOrgId(account.getOrgId());
			news.setType(request.getParameter("newstype"));
			news.setStatus(request.getParameter("status"));
			news.setTop(request.getParameter("top"));
			news.setTitle(request.getParameter("title"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			news.setContect(request.getParameter("contect"));
			NewsLogic newsLogic = new NewsLogic();
			data=newsLogic.delnewsLogic(dbConn, news, starttime, endtime);
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
	 * 获取待审批的新闻列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void noapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String accountId=account.getAccountId();
			String orgId=account.getOrgId();
			NewsLogic newsLogic = new NewsLogic();
			data = newsLogic.noapprovallogic(dbConn, accountId, orgId, pageSize, page, sortOrder, sortName);
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
	 * 获取已审批的新闻列表
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void alreadyapprovalAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			String accountId=account.getAccountId();
			String orgId=account.getOrgId();
			NewsLogic newsLogic = new NewsLogic();
			data = newsLogic.alreadyapprovallogic(dbConn, accountId, orgId, pageSize, page, sortOrder, sortName);
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
	 * 审批新闻处理
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void managenewsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newsId=request.getParameter("newsId");
			String orgId=account.getOrgId();
			String approvalStatus=request.getParameter("approvalStatus");
			NewsLogic newsLogic = new NewsLogic();
			data = newsLogic.managenewsnewslogic(dbConn, newsId, orgId,approvalStatus);
			
			if(request.getParameter("approvalStatus").equals("1")){
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
				messageApi.addMessage(dbConn, "news", smsRemindJson, "未读新闻", account.getAccountId(), toAccountList,account.getOrgId(),request.getParameter("createTime"));
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
	 * 查看新闻 多条件模糊查询
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void readtremAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			News news = new News();
			news.setCreateUser(request.getParameter("accountId"));
			news.setType(request.getParameter("newstype"));
			news.setTitle(request.getParameter("title"));
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			news.setContect(request.getParameter("contect"));
			NewsLogic newsLogic = new NewsLogic();
			String deptId=(String) request.getSession().getAttribute("DEPT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(","+account.getAccountId()+",");
			pramList.add(","+deptId+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",");
			pramList.add(","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getPostPriv()+",");
			pramList.add(account.getOrgId());
			data = newsLogic.readtremlogic(dbConn, pramList, news, starttime, endtime, pageSize, page, sortOrder, sortName);
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
	 * 通过新闻ID 获取所有可读该新闻的人员信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdreadstatusAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		
		try {
			NewsLogic newsLogic = new NewsLogic();
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newsId=request.getParameter("newsId");
			data=newsLogic.getIdreadstatusLogic(dbConn, newsId, account);
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
	 * 通过newsId 获取已读人员的信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getreadUserAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		
		try {
			NewsLogic newsLogic = new NewsLogic();
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newsId=request.getParameter("newsId");
			data=newsLogic.getreadUserLogic(dbConn, newsId, account);
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
