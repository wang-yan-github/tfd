package institution.act;

import institution.data.InstComment;
import institution.data.Institution;
import institution.logic.InstitutionLogic;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class InstitutionAct {
	/**
	 * 得到所有的制度列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getInstitutionList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new InstitutionLogic().getInstitutionList(dbConn,orgId);
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
	 * 根据Id查询具体制度
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getInstitutionById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String instId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new InstitutionLogic().getInstitutionById(dbConn,instId,orgId);
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
	 * 根据Id删除制度
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delInstitutionById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String instId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new InstitutionLogic().delInstitutionById(dbConn,instId,orgId);
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
	 * 根据Id修改制度内容
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateInstitutionById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String instId =  request.getParameter("id");
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String dirId = request.getParameter("dirId");
			String attachId = request.getParameter("attachId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Institution inst = new Institution();
			inst.setInstId(instId);
			inst.setInstName(name);
			inst.setInstContent(content);
			inst.setDirId(dirId);
			inst.setAccountId(account.getAccountId());
			inst.setAttachId(attachId);
			String orgId = account.getOrgId();
			returnData = new InstitutionLogic().updateInstitutionById(dbConn, inst,orgId);
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
	 * 添加制度
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addInstitution(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String instId = GuId.getGuid();
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			content = URLDecoder.decode(content, "utf-8");
			String dirId =  request.getParameter("dirId");
			String attachId = request.getParameter("attachId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			Institution inst = new Institution();
			inst.setInstId(instId);
			inst.setInstName(name);
			inst.setInstContent(content);
			inst.setDirId(dirId);
			inst.setAccountId(account.getAccountId());
			inst.setAttachId(attachId);
			inst.setOrgId(account.getOrgId());
			returnData =  new InstitutionLogic().addInstitution(dbConn, inst);
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
	 * 普通搜索方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void searchInstitution(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
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
			String searchName = request.getParameter("searchName"); 
			if(searchName == null){
				searchName = "";
			}
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new InstitutionLogic().searchInstitution(dbConn,searchName,pramList,pageSize,page,sortOrder,sortName);
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
	 * 高级搜索方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void searchInstitution2(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
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
			
			String dirName = request.getParameter("dirName"); 
			String userName = request.getParameter("userName");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> pramList = new ArrayList<String>();
			pramList.add(account.getOrgId());
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new InstitutionLogic().searchInstitution2(dbConn,pramList,dirName,userName,beginTime,endTime,pageSize,page,sortOrder,sortName);
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
	 * 根据制度Id得到修订建议
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getInstCommentByInstId(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String instId = request.getParameter("id");
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new InstitutionLogic().getInstCommentByInstId(dbConn,instId,orgId);
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
	 * 添加制度评论
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addInstComment(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String comId = GuId.getGuid();
			String comPid = request.getParameter("comPid");
			String content = request.getParameter("content");
			String instId = request.getParameter("instId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			InstComment com = new InstComment(comId,comPid,content,new Date(),instId,account.getAccountId(),orgId);
			returnData = new InstitutionLogic().addInstComment(dbConn, com);
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
