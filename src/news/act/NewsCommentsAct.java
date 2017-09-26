package news.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.data.NewsComments;
import news.logic.NewsCommentsLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class NewsCommentsAct {

	/**
	 * 添加新闻评论
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addCommAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsComments newsComments=new NewsComments();
			NewsCommentsLogic newsCommentsLogic=new NewsCommentsLogic();
			newsComments.setCommId(GuId.getGuid());
			newsComments.setCommPid(request.getParameter("commPid"));
			newsComments.setCommContect(request.getParameter("commContect"));
			newsComments.setCommTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			newsComments.setNewsId(request.getParameter("newsId"));
			newsComments.setCommName(request.getParameter("commName"));
			newsComments.setAccountId(account.getAccountId());
			newsComments.setOrgId(account.getOrgId());
			data=newsCommentsLogic.addCommLogic(dbConn, newsComments);
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
	 * 根据newsId 获取评论信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNewsIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsCommentsLogic newsCommentsLogic=new NewsCommentsLogic();
			String newsId=request.getParameter("newsId");
			String orgId=account.getOrgId();
			data=newsCommentsLogic.getNewsIdLogic(dbConn, newsId, orgId);
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
	 * 获取新闻评论中最新的五条记录
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getlateCommAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsCommentsLogic newsCommentsLogic=new NewsCommentsLogic();
			String newsId=request.getParameter("newsId");
			String orgId=account.getOrgId();
			data=newsCommentsLogic.getlateCommLogic(dbConn, newsId, orgId);
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
	 * 根据评论Id 删除评论信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delCommIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			NewsCommentsLogic newsCommentsLogic=new NewsCommentsLogic();
			String commId=request.getParameter("commId");
			data=newsCommentsLogic.delCommIdLogic(dbConn, commId, account);
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
	 * 分页获取新闻的评论内容
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCommentsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String newsId=request.getParameter("newsId");
			NewsCommentsLogic newsCommentsLogic=new NewsCommentsLogic();
			List<String> pramList = new ArrayList<String>();
			pramList.add(newsId);
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			data=newsCommentsLogic.getCommentsLogic(dbConn, pramList, pageSize, page, sortOrder, sortName);
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
