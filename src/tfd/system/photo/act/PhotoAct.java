package tfd.system.photo.act;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.photo.data.Photo;
import tfd.system.photo.logic.PhotoLogic;
import tfd.system.publicdisk.util.IoOption;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.filetool.UpFileTool;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class PhotoAct {
	/**
	 * 添加相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addPhotoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			int sortId = Integer.parseInt(request.getParameter("sortId"));
			String photoName = request.getParameter("photoName");
			String photoPath = request.getParameter("photoPath");
			String photoId = GuId.getGuid();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Photo photo = new Photo();
			photo.setPhotoId(photoId);
			photo.setSortId(sortId);
			photo.setPhotoName(photoName);
			photo.setPhotoPath(photoPath);
			photo.setCreateAccountId(account.getAccountId());
			photo.setCreateDate(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			photo.setOrgId(account.getOrgId());
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.addPhotoLogic(dbConn, photo);
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
	 * 得到全部相册Json数据
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPhotoJsonAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData = photoLogic.getPhotoJsonLogic(dbConn, account);
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
	 * 得到全部相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAllPhotoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData = photoLogic.getAllPhotoAct(dbConn, account);
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
	 * 得到相册下所有的照片文件
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPhotoFileListAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String path = request.getParameter("path");
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.getPhotoFileListLogic(dbConn,account, path);
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
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkPrivAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String photoId = request.getParameter("photoId");
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.checkPrivLogic(dbConn,account, photoId);
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
	 * 得到相片
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPhotoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String photoPath=request.getParameter("photoPath");
		response.setContentType("text/html; charset=UTF-8");
	    response.setContentType("image/jpeg"); 
	    FileInputStream fis = new FileInputStream(photoPath);
	    OutputStream os = response.getOutputStream();
	    try {
	        int count = 0;
	        byte[] buffer = new byte[1024*1024];
	        while ( (count = fis.read(buffer)) != -1 )
	            os.write(buffer, 0, count);
	    } catch (IOException e){  
	       e.printStackTrace();  
	    }finally {
	    	if(os!=null)
	    		 os.close();
	        if(fis != null)
	        	fis.close();
	    }
	}
	/**
	 * 根据相册Id得到相册信息
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPhotoByIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String photoId = request.getParameter("photoId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.getPhotoByIdLogic(dbConn, photoId);
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
	 * 修改相册信息
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePhotoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			int sortId = Integer.parseInt(request.getParameter("sortId"));
			String photoName = request.getParameter("photoName");
			String photoPath = request.getParameter("photoPath");
			String photoId = request.getParameter("photoId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.updatePhotoLogic(dbConn,sortId,photoName,photoPath,photoId);
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
	 * 删除相册
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delPhotoAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String photoId = request.getParameter("photoId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.delPhotoLogic(dbConn, photoId);
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
	 * 得到相册的权限
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getPhotoPrivAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String photoId = request.getParameter("photoId");
			String privId = request.getParameter("privId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			if(privId.equals("1")){
				returnData = new PhotoLogic().getReadPrivLogic(dbConn,orgId ,photoId);
			}else if(privId.equals("2")){
				returnData = new PhotoLogic().getManagePrivLogic(dbConn,orgId ,photoId);
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
	 * Time:2015-6-16
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePhotoPrivAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String photoId = request.getParameter("photoId");
			String privId = request.getParameter("privId");
			String User = request.getParameter("User");
			String Dept = request.getParameter("Dept");
			String Priv = request.getParameter("Priv");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			returnData = new PhotoLogic().updatePhotoPrivLogic(dbConn,User,Dept,Priv,photoId,orgId,privId);
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
	 * 新建文件夹
	 * Author:Yzz
	 * Time:2015-6-18
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
	 * 上传照片
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void uploadfiles(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData = 0;
		try{
			String path = request.getParameter("path");
			String attachId = request.getParameter("attachId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			IoOption io = new IoOption();
			PhotoLogic photoLogic = new PhotoLogic();
			String [] attachIds = attachId.split(",");
			for (int i = 0; i < attachIds.length; i++) {
				String fileName = new UpFileTool().getAttachName(attachIds[i]);
				returnData = io.uploadfiles(path, attachIds[i],"photo");
				photoLogic.addPhotoInfoLogic(dbConn, fileName, account.getAccountId(), path+"/"+fileName, account.getOrgId());
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
	 * 设置封面
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setPhotoCoverAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String coverPath = request.getParameter("coverPath");
			String photoId = request.getParameter("photoId");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.setPhotoCoverLogic(dbConn,coverPath,photoId);
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
	 * 点赞与取消赞
	 * Author:Yzz
	 * Time:2015-6-20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatePhotoInfoGoodAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String photoInfoId = request.getParameter("photoInfoId");
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			dbConn = DbPoolConnection.getInstance().getConnection();
			PhotoLogic photoLogic = new PhotoLogic();
			returnData=photoLogic.updatePhotoInfoGoodLogic(dbConn,account.getAccountId(),photoInfoId);
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
