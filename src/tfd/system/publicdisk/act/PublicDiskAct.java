package tfd.system.publicdisk.act;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.publicdisk.data.PublicDisk;
import tfd.system.publicdisk.logic.PublicDiskLogic;
import tfd.system.publicdisk.util.IoOption;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysFileTool;
import com.system.tool.SysTool;

public class PublicDiskAct {
	
	/**
	 * 新建资源共享
	 * Time:2015-4-10
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addPublicDisk(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			PublicDisk pd = new PublicDisk();
			pd.setDiskId(GuId.getGuid());
			pd.setDiskNo( request.getParameter("diskNo"));
			pd.setDiskName( request.getParameter("diskName"));
			pd.setDiskPath( request.getParameter("diskPath"));
			pd.setSpaceLimit( request.getParameter("spaceLimit"));
			pd.setOrderBy( request.getParameter("orderBy"));
			pd.setAscDesc( request.getParameter("ascDesc"));
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			pd.setOrgId(account.getOrgId());
			returnData = new PublicDiskLogic().addPublicDisk(dbConn, pd);
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
	 * 得到代码分类
	 * Time : 2015-4-10
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPublicDiskList(HttpServletRequest request,HttpServletResponse response)throws Exception{
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			List<String> pramList = new ArrayList<String>();
			pramList.add(orgId);
			returnData = new PublicDiskLogic().getPublicDiskList(dbConn,pramList,pageSize,page,sortOrder,sortName,account.getAccountId());
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
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPublicDiskById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String diskId = request.getParameter("diskId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().getPublicDiskById(dbConn,orgId ,diskId);
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
	 * 根据Id修改共享内容
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePublicDisk(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			PublicDisk pd = new PublicDisk();
			pd.setDiskId(request.getParameter("diskId"));
			pd.setDiskNo( request.getParameter("diskNo"));
			pd.setDiskName( request.getParameter("diskName"));
			pd.setDiskPath( request.getParameter("diskPath"));
			pd.setSpaceLimit( request.getParameter("spaceLimit"));
			pd.setOrderBy( request.getParameter("orderBy"));
			pd.setAscDesc( request.getParameter("ascDesc"));
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			pd.setOrgId(account.getOrgId());
			returnData = new PublicDiskLogic().updatePublicDisk(dbConn,pd);
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
	 * 根据Id删除共享内容
	 * Time:2015-4-13
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deletePublicDisk(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int  returnData=0;
		try{
			String diskId = request.getParameter("diskId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().deletePublicDisk(dbConn,orgId,diskId);
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
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String diskId = request.getParameter("diskId");
			String privId = request.getParameter("privId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			if(privId.equals("1")){
				returnData = new PublicDiskLogic().getAccessPriv(dbConn,orgId ,diskId);
			}else if(privId.equals("2")){
				returnData = new PublicDiskLogic().getManagePriv(dbConn,orgId ,diskId);
			}else if(privId.equals("3")){
				returnData = new PublicDiskLogic().getNewPriv(dbConn,orgId ,diskId);
			}else if(privId.equals("4")){
				returnData = new PublicDiskLogic().getDownPriv(dbConn,orgId ,diskId);
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
	 * 验证权限
	 * Author:Yzz
	 * Time:2015-5-28
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String diskId = request.getParameter("diskId");
			String privId = request.getParameter("privId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().checkPriv(dbConn, diskId, privId, account.getAccountId(), orgId);
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
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String diskId = request.getParameter("diskId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().updatePriv(dbConn,User,Dept,Priv,diskId,orgId,privId);
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
	 * 得到共享目录
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDiskList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().getDiskList(dbConn,orgId,account.getAccountId());
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
	 * 得到目录下的文件
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String path = request.getParameter("path");
		String searchContent = request.getParameter("searchContent");
		JSONArray jsonArr = new JSONArray();
		String returnData = null;
		try {
			File f = new File(path);
			String[] filelist = f.list();
			File[] files=f.listFiles();
			for(int i=0;i<filelist.length;i++){
				File readfile = new File(path + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					if(readfile.getName().toString().indexOf(searchContent)>-1){
						JSONObject json = new JSONObject();
						
						json.accumulate("id",readfile.toString() );
						json.accumulate("path",files[i].toString() );
						json.accumulate("pId",path.toString() );
						json.accumulate("name",readfile.getName().toString()); 
						double size = readfile.length();
						String sizeStr = "";
						if(size>=1024&&1048576>size){
							 size = size/1024;
							 BigDecimal b = new BigDecimal(size);
							 size = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); 
							 sizeStr = size+"KB";
						}else if(size>=1048576&&1073741824>size){
							 size = size/1024/1024;
							 BigDecimal b = new BigDecimal(size);
							 size = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); 
							 sizeStr = size+"MB";
						}else if(size >= 1073741824){
							 size = size/1024/1024/1024;
							 BigDecimal b = new BigDecimal(size);
							 size = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
							 sizeStr = size+"GB";
						}else{
							BigDecimal b = new BigDecimal(size);
							size = b.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue(); 
							sizeStr = size+"B";
						}
						json.accumulate("size",sizeStr);
						Calendar cal = Calendar.getInstance();   
						long time = readfile.lastModified();
						cal.setTimeInMillis(time);
						String strTime = SysTool.getDateTimeStr(cal.getTime());
						json.accumulate("time",strTime);
						String typeStr = SysFileTool.getFileExtName(readfile.getName());
						json.accumulate("type",typeStr);

						jsonArr.add(json);
					}
				}
			}
		} catch (Exception e) {
		}
		
		returnData = jsonArr.toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
	
	/**
	 * 得到目录下的文件夹
	 * Time:2015-4-14
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String path = request.getParameter("path");
		JSONArray jsonArr = new JSONArray();
		String returnData = null;
		try {
			File f = new File(path);
			String[] filelist = f.list();
			File[] files=f.listFiles();
			for(int i=0;i<filelist.length;i++){
				File readfile = new File(path + "\\" + filelist[i]);
				if (readfile.isDirectory()) {
					JSONObject json = new JSONObject();
					json.accumulate("id",readfile.toString() );
					json.accumulate("path",files[i].toString() );
					json.accumulate("pId",path.toString() );
					json.accumulate("name",readfile.getName().toString());
					if(new PublicDiskLogic().isParent(readfile.toString())){
						json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
						json.accumulate("isParent","true");
					}else{
						json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder_active.gif");
						json.accumulate("isParent","false");
					}
					jsonArr.add(json);
				}
			}
		} catch (Exception e) {
		}
		returnData = jsonArr.toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
	/**
	 * 删除文件夹
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteFloder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData = 0;
		try{
			String path = request.getParameter("path");
			IoOption io = new IoOption();
			boolean flag = io.DeleteFolder(path);
			if(flag){
				returnData = 1;
			}
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
	 * 删除文件
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData = 0;
		try{
			String path = request.getParameter("path");
			IoOption io = new IoOption();
			boolean flag = io.deleteFile(path);
			if(flag){
				returnData = 1;
			}
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
	 * 新建文件夹
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createFolder(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData=0;
		try{
			String path = request.getParameter("path");
			IoOption io = new IoOption();
			 returnData = io.createFolder(path);
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
	 * 新建文件夹
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void changeFolderName(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData=0;
		try{
			String path = request.getParameter("path");
			String folderName = request.getParameter("folderName");
			String newName = request.getParameter("newName");
			IoOption io = new IoOption();
			returnData = io.changeFolderName(path,folderName,newName);
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
	 * 附件上传
	 * Author:Yzz
	 * Time:2015-6-1
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void uploadfiles(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData = 0;
		try{
			String path = request.getParameter("path");
			String attachId = request.getParameter("attachId");
			IoOption io = new IoOption();
			String [] attachIds = attachId.split(",");
			for (int i = 0; i < attachIds.length; i++) {
				returnData = io.uploadfiles(path, attachIds[i],"disk");
			}
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
	 * 批量添加权限
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String diskId = request.getParameter("diskId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().addPriv(dbConn,User,Dept,Priv,diskId,orgId,privId);
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
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delPriv(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String diskId = request.getParameter("diskId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			String orgId = account.getOrgId();
			returnData = new PublicDiskLogic().delPriv(dbConn,User,Dept,Priv,diskId,orgId,privId);
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
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void copyFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData=1;
		try{
			String path = request.getParameter("path");
			String diskId = request.getParameter("diskId");
			
			new IoOption().copyFile(diskId, path);
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
	 * 剪切文件
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void cutFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int returnData=1;
		try{
			String path = request.getParameter("path");
			String diskId = request.getParameter("diskId");
			
			new IoOption().cutFile(diskId, path);
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
