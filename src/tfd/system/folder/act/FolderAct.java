package tfd.system.folder.act;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.folder.logic.FolderLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class FolderAct {
	/**
	 * 新建文件夹
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = GuId.getGuid();
			String folderPid = request.getParameter("folderPid");
			if(folderPid == null){
				folderPid = "0";
			}
			String folderNo = request.getParameter("folderNo");
			if(folderNo == null||folderNo.equals("")){
				folderNo = "0";
			}
			String folderName = request.getParameter("folderName");
			if(folderName == null||folderName.equals("")){
				folderName = "新建文件夹";
			}
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().addFolder(dbConn, folderId,folderPid,folderNo,folderName,isPublic,orgId);
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
	 * 得到文件夹列表
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			String isPublic=request.getParameter("isPublic");
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
			pramList.add(isPublic);
			returnData = new FolderLogic().getFolderList(dbConn,pramList,pageSize,page,sortOrder,sortName,account.getAccountId());
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
	 * 根据Id查看共享的详细内容
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String folderId = request.getParameter("folderId");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFolderById(dbConn,orgId ,folderId,isPublic);
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
	 * 根据Id修改文件夹
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String folderNo = request.getParameter("folderNo");
			String folderName = request.getParameter("folderName");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().updateFolder(dbConn,orgId,folderId,folderNo,folderName,isPublic);
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
	 * 根据Id删除文件夹
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().deleteFolder(dbConn,orgId,folderId);
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
	 * 根据Id得到文件夹列表
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderListById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String folderPid = request.getParameter("id");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFolderListById(dbConn,orgId ,folderPid,isPublic);
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
	 * 得到权限
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String folderId = request.getParameter("folderId");
			String privId = request.getParameter("privId");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = null;
			if(privId.equals("1")){
				returnData = new FolderLogic().getAccessPriv(dbConn,orgId ,folderId,isPublic);
			}else if(privId.equals("2")){
				returnData = new FolderLogic().getEditPriv(dbConn,orgId ,folderId,isPublic);
			}else if(privId.equals("3")){
				returnData = new FolderLogic().getNewPriv(dbConn,orgId ,folderId,isPublic);
			}else if(privId.equals("4")){
				returnData = new FolderLogic().getDownPriv(dbConn,orgId ,folderId,isPublic);
			}else if(privId.equals("5")){
				returnData = new FolderLogic().getDelPriv(dbConn,orgId ,folderId,isPublic);
			}else if(privId.equals("6")){
				returnData = new FolderLogic().getCommPriv(dbConn,orgId ,folderId,isPublic);
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
	 * 修改权限
	 * Time:2015-4-15
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().updatePriv(dbConn,User,Dept,Priv,folderId,orgId,privId);
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
	 * 根据权限得到文件夹列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderListByPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFolderListByPriv(dbConn,orgId,account.getAccountId(),isPublic);
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
	 * 添加文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String fileId = GuId.getGuid();
			String folderId = request.getParameter("folderId");
			String fileNo = request.getParameter("fileNo");
			String fileName = request.getParameter("fileName");
			String fileContent = request.getParameter("fileContent");
			if(fileContent!=null){
				fileContent = URLDecoder.decode(fileContent, "utf-8");
			}
			String attachId = request.getParameter("attachId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			FolderLogic folderLogic = new FolderLogic();
			returnData = folderLogic.addFile(dbConn, fileId,fileNo,fileName,folderId,fileContent,attachId,orgId);
			folderLogic.addRecord(dbConn, fileId, account.getAccountId(), attachId, fileName, orgId);
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
	 * 根据权限得到文件夹和文件列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderFileListByPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String folderId = request.getParameter("folderId");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFolderFileListByPriv(dbConn,folderId,orgId,account.getAccountId(),isPublic);
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
	 * 根据权限得到文件夹和有附件的文件列表
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolderFileListByPrivInAttach(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String folderId = request.getParameter("folderId");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFolderFileListByPrivInAttach(dbConn,folderId,orgId,account.getAccountId(),isPublic);
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
	 * 根据Id得到文件详细内容
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFileById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String fileId = request.getParameter("fileId");
			String isPublic = request.getParameter("isPublic");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().getFileById(dbConn,orgId ,fileId,account.getAccountId(),isPublic);
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
	 * 根据Id修改文件详细内容
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String fileId = request.getParameter("fileId");
			String folderId = request.getParameter("folderId");
			String fileNo = request.getParameter("fileNo");
			String fileName = request.getParameter("fileName");
			String fileContent = request.getParameter("fileContent");
			fileContent = URLDecoder.decode(fileContent, "utf-8");
			String attachId = request.getParameter("attachId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().updateFile(dbConn, fileId,fileNo,fileName,folderId,fileContent,attachId,orgId);
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
	 * 根据Id删除文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String fileId = request.getParameter("fileId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().deleteFile(dbConn,orgId,fileId);
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
	 * 复制文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void copyFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String newId = GuId.getGuid();
			String folderId = request.getParameter("folderId");
			String folderPid = request.getParameter("folderPid");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().copyFolder(dbConn,folderId,newId,folderPid,orgId);
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
	 * 复制文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void copyFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String newId = GuId.getGuid();
			String fileId = request.getParameter("fileId");
			String folderId = request.getParameter("folderId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().copyFile(dbConn,fileId,newId,folderId,orgId);
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
	 * 剪切文件
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sheraFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String fileId = request.getParameter("fileId");
			String folderId = request.getParameter("folderId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().sheraFile(dbConn,fileId,folderId,orgId);
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
	 * 剪切文件夹
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sheraFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String folderPid = request.getParameter("folderPid");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().sheraFolder(dbConn,folderId,folderPid,orgId);
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
	 * 创建个人文件柜
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = GuId.getGuid();
			String folderPid = "0";
			String folderNo = "0";
			String folderName = "我的文件柜";
			String isPublic = "2";
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().createFolder(dbConn, folderId,folderPid,folderNo,folderName,isPublic,account.getAccountId(),orgId);
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
	 * 检验权限
	 * Time:2015-4-21
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String privType = request.getParameter("privType");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().checkPriv(dbConn,folderId,account.getAccountId(),orgId,privType);
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
	 * 添加版本记录
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addRecord(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String fileId = request.getParameter("fileId");
			String attachId = request.getParameter("attachId");
			String fileName = request.getParameter("fileName");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			FolderLogic folderLogic =new FolderLogic();
			returnData = folderLogic.addRecord(dbConn,fileId,account.getAccountId(),attachId,fileName,orgId);
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
	 * 复制附件
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createHistoryFileLogic(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String returnData="";
		try{
			String attachId = request.getParameter("attachId");
			FolderLogic folderLogic =new FolderLogic();
			returnData = folderLogic.createHistoryFileLogic(attachId);
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 得到版本历史记录
	 * Time:2015-5-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getRecordListById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String fileId = request.getParameter("fileId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			FolderLogic folderLogic =new FolderLogic();
			returnData = folderLogic.getRecordListById(dbConn,fileId,orgId);
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
	 * 批量添加权限
	 * Time:2015-7-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().addPriv(dbConn,User,Dept,Priv,folderId,orgId,privId);
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
	 * 批量移除权限
	 * Time:2015-7-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new FolderLogic().delPriv(dbConn,User,Dept,Priv,folderId,orgId,privId);
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
	 * 检查文件是否为最上级
	 * Time:2015-11-20
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkFolderPid(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String folderId = request.getParameter("folderId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			returnData = new FolderLogic().checkFolderPid(dbConn,folderId);
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
