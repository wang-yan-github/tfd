package diary.act;

import java.net.URLDecoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.Diary;
import diary.logic.DiaryLogic;

public class DiaryAct {
	/**
	 * 添加日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			String content = URLDecoder.decode(request.getParameter("diaryContent"), "utf-8");
			dr.setAccountId(account.getAccountId());
			dr.setDiaryDatetime(formatter.format(new Date()));
			dr.setDiaryMold(Integer.parseInt(request.getParameter("diaryMold")));
			dr.setDiaryName(request.getParameter("diaryName"));
			dr.setDiaryContent(content);
			dr.setSharePriv(request.getParameter("sharePriv"));
			dr.setOrgId(account.getOrgId());
			dr.setDiaryId(GuId.getGuid());
			dr.setDiaryAnnex(request.getParameter("diaryAnnex"));
			dr.setDiaryTitleDatetime(request.getParameter("diarytitleDatetime"));
			dr.setShareRange(Integer.parseInt(request.getParameter("shareRange")));
			dr.setLaud("");
			dr.setLaudNum(0);
			data=dl.addDiaryLogic(dbConn,dr);
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
	 * 分页查询自己的日志信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String data="";
		
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
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dr.setAccountId(account.getAccountId());
			dr.setOrgId(account.getOrgId());
			data=dl.selectDiaryLogic(dbConn, dr,pageSize,page,sortOrder,sortName);
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
	 * 根据 日志Id 查询日志信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data="";
		try {
			String diaryId=request.getParameter("diaryId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			DiaryLogic dl=new DiaryLogic();
			 data=dl.getIdDiaryLogic(dbConn, diaryId,account);
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
	 * 根据日志Id 删除日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteDiaryIDAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String diaryId=request.getParameter("id");
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			dr.setAccountId(account.getAccountId());
			dr.setDiaryId(diaryId);
			dr.setOrgId(account.getOrgId());
			data=dl.deleteDiaryIdLogic(dbConn, dr);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据日志Id 修改日志的信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Connection dbConn = null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			dr.setAccountId(account.getAccountId());
			dr.setDiaryName(request.getParameter("diaryName"));
			dr.setOrgId(account.getOrgId());
			dr.setDiaryTitleDatetime(request.getParameter("diarytitleDatetime"));
			String content = URLDecoder.decode(request.getParameter("diaryContent"), "utf-8");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dr.setDiaryDatetime(formatter.format(new Date()));
			dr.setDiaryContent(content);
			dr.setDiaryId(request.getParameter("diaryId"));
			dr.setDiaryAnnex(request.getParameter("diaryAnnex"));
			dr.setSharePriv(request.getParameter("sharePriv"));
			data=dl.updateDiaryLogic(dbConn, dr);
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
	//批量删除
	public void batchDelDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Connection dbConn = null;
		int data=0;
		
		try {
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			dr.setAccountId(account.getAccountId());
			dr.setOrgId(account.getOrgId());
			String diaryid=request.getParameter("id");
			String [] diaryidArray=diaryid.split(",");
			data=0;
			for (int i = 0; i < diaryidArray.length; i++) {
				dr.setDiaryId(diaryidArray[i]);
				data=dl.deleteDiaryIdLogic(dbConn, dr);
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
	 * 通过查询个人日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void timeQueryDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String data=null;
		Connection dbConn=null;
		try {
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			String germtime=request.getParameter("germtime");
			String endtime=request.getParameter("endtime");
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
			if(germtime==""&&endtime==""){
				dr.setAccountId(account.getAccountId());
				dr.setOrgId(account.getOrgId());
				data=dl.selectDiaryLogic(dbConn, dr,pageSize,page,sortOrder,sortName);
			}else{
				dr.setAccountId(account.getAccountId());
				dr.setOrgId(account.getOrgId());
				dbConn = DbPoolConnection.getInstance().getConnection();
				data=dl.timeQueryDiaryLogic(dbConn, dr, pageSize, page, sortOrder, sortName, endtime, germtime);	
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 判断当前日志 是否拥有下一篇
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void judgedowndiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn =null;
		String data=null;
		try {
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Diary dr=new Diary();
			DiaryLogic dl=new DiaryLogic();
			int id=Integer.parseInt(request.getParameter("id"));
			dr.setId(id);
			dr.setAccountId(request.getParameter("accountId"));
			dr.setOrgId(account.getOrgId());
			data=dl.judgedowndiaryLogic(dbConn, dr,account);
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
	 * 判断是否有上一篇
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void judgetonediaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn = null;
			String data=null;
			try {
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Diary dr=new Diary();
				dbConn = DbPoolConnection.getInstance().getConnection();
				DiaryLogic dl=new DiaryLogic();
				int id=Integer.parseInt(request.getParameter("id"));
				dr.setId(id);
				dr.setAccountId(request.getParameter("accountId"));
				dr.setOrgId(account.getOrgId());
				data=dl.judgetonediaryLogic(dbConn, dr,account);
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
	 * 根据标题日期进行查询当天日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void titletimeQueryDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String data=null;
		
		try {
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Diary dr=new Diary();
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			String titletime=request.getParameter("titletime");
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
			dr.setAccountId(account.getAccountId());
			dr.setDiaryTitleDatetime(titletime);
			dr.setOrgId(account.getOrgId());
			data=dl.titletimeQueryDiaryLogic(dbConn, dr,  pageSize, page, sortOrder, sortName);
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
	 * 查询日志的条数信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCountAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		
		try {
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			data=dl.getCountLogic(dbConn, account);
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
	 * 查询拥有权限的日志方法
	 */
	/**
	 * 查询拥有权限查看的日志信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectotherDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
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
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			data=dl.selectotherDiaryLogic(dbConn, account,pageSize,page,sortOrder,sortName);
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
	 * 通过日志标题时间查询 权限内可以查看的日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void othertitletimeQueryDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			
			try {
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				dbConn = DbPoolConnection.getInstance().getConnection();
				DiaryLogic dl=new DiaryLogic();
				String titletime=request.getParameter("titletime");
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
				data=dl.othertitletimeQueryDiaryLogic(dbConn, account,  pageSize, page, sortOrder, sortName,titletime);
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
		 * 查询权限下的其他人的日志信息
		 * Author: Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
	public void otheruserDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
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
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			data = dl.otheruserDiaryLogic(dbConn, account,pageSize,page,sortOrder,sortName);
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
	 * 通过日志标题时间查询其他人的日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
			public void otherusertimeQueryDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
				Connection dbConn=null;
				String data=null;
				try {
					Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
					dbConn = DbPoolConnection.getInstance().getConnection();
					DiaryLogic dl=new DiaryLogic();
					String titletime=request.getParameter("titletime");
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
					data=dl.otherusertimeQueryDiaryLogic(dbConn, account,  pageSize, page, sortOrder, sortName,titletime);
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
			 * 给当前日志添加浏览人员
			 * Author: Wp
			 * @param request
			 * @param response
			 * @throws Exception
			 */
	public void addlookStaffAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Connection dbConn=null;
		int data=0;
		try {
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			 dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			String diaryId=request.getParameter("diaryId");
			String orgId=account.getOrgId();
			String lookStaff=request.getParameter("lookStaff");
			data=dl.addlookStaffLogic(dbConn, orgId, diaryId, lookStaff);
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
	 * 根据accoungId 查询日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getaccountIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryLogic dl=new DiaryLogic();
			String accountId=request.getParameter("accountId");
			String orgId=account.getOrgId();
			data=dl.getaccountIdLogic(dbConn, accountId, orgId, pageSize, page, sortOrder, sortName);
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
	 * 通过标题时间和人员的accountId 查询权限下可查看人员的日志
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void gettimeuserQueryDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			
			try {
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Diary dr=new Diary();
				dbConn = DbPoolConnection.getInstance().getConnection();
				DiaryLogic dl=new DiaryLogic();
				String accountId=request.getParameter("accountId");
				String titletime=request.getParameter("titletime");
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
				dr.setDiaryTitleDatetime(titletime);
				dr.setOrgId(account.getOrgId());
				data=dl.gettimeuserQueryDiaryLogic(dbConn, accountId, dr, pageSize, page, sortOrder, sortName);
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
		 * 根据accoungId 查询日志条数
		 * Author: Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getuserCountAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			String data=null;
			Connection dbConn=null;
			
			try {
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				DiaryLogic dl=new DiaryLogic();
				dbConn = DbPoolConnection.getInstance().getConnection();
				String accountId=request.getParameter("accountId");
				String orgId=account.getOrgId();
				data=dl.getuserCountLogic(dbConn, accountId, orgId);
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
		 * 点赞
		 * Time 2015-10-28
		 * Author Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getlaudAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			int data=0;
			Connection dbConn=null;
			
			try {
				Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
				DiaryLogic dl=new DiaryLogic();
				dbConn = DbPoolConnection.getInstance().getConnection();
				String accountId=account.getAccountId();
				String orgId=account.getOrgId();
				String diaryId=request.getParameter("diaryId");
				data=dl.getlaudLogic(dbConn, diaryId, accountId, orgId);
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
}
