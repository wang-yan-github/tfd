package com.system.filetool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.org.logic.UnitLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.tool.GuId;
import com.system.tool.HttpUtil;
import com.system.tool.SysFileTool;
import com.system.tool.SysTool;

public class UpFileTool {
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	// 文件下载
	public void doDownFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		BufferedInputStream bis = null;
		OutputStream ops = null;
		try {
			String attachId = request.getParameter("attachId");
			String fileName = this.getAttachName(attachId);
			
			String filePath = this.getAttachPath(attachId);
			File file = new File(filePath);
			
			HashMap<String, String> contentTypeMap = (HashMap<String, String>) this.getAttachHeard(fileName, "1");
			String contentType=contentTypeMap.get("contentTypeDesc");
			contentType=contentType==null?"application/octet-stream;charset=UTF-8":contentType;
			
			String contentDisposition=HttpUtil.getReponseHeader_ContentDisposition(fileName, request.getHeader("user-agent"));
			
			
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Cache-control", "private");
			response.setContentType(contentType);
			response.setContentType("application/msword");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition",contentDisposition);
			
			// 读出文件到i/o流
			bis = new BufferedInputStream(new FileInputStream(file));
			// 从response对象中得到输出流,准备下载
			ops = response.getOutputStream();
			//相当于我们的缓存
			byte[] b = new byte[1024];
			// 该值用于计算当前实际下载了多少字节
			long k = 0;

			// 开始循环下载
			while (k < file.length()) {
				int j = bis.read(b, 0, 1024);
				ops.write(b, 0, j);
				k += j;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			SysTool.closeOutputStream(ops);
			SysTool.closeInputStream(bis);
		}
	}

	public void doDiskDownFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BufferedInputStream bis=null;
		OutputStream ops =null;
		try {
			
			String path = request.getParameter("path");
			String fileName = path.substring(path.lastIndexOf("\\"),path.length());
			
			File file = new File(path);
			
			Map<String, String> contentTypeMap = getAttachHeard(fileName, "1");
			String contentType = contentTypeMap.get("contentTypeDesc");
			contentType=contentType==null?"application/octet-stream":contentType;
			
			String contentDisposition=HttpUtil.getReponseHeader_ContentDisposition(fileName, HttpUtil.getUserAgent(request));
			
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Cache-control", "private");
			response.setContentType(contentType);
			response.setContentType("application/msword");
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition",contentDisposition);
			
			
			bis = new BufferedInputStream(new FileInputStream(file));
			ops = response.getOutputStream();
			
			byte[] b = new byte[1024];
			long k = 0;
			
			while (k < file.length()) {
				int j = bis.read(b, 0, 1024);
				ops.write(b, 0, j);
				k += j;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			SysTool.closeOutputStream(ops);
			SysTool.closeInputStream(bis);
		}
	}

	public String downloadFiles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = request.getParameter("path");
		byte[] buffer = new byte[1024];
		// 生成的ZIP文件名为Demo.zip
		String strZipName = SysTool.getDateTimeStr(new Date()).substring(0, 10)
				+ ".zip";
		String filePath = SysProps.getAttachPath()+"/" + strZipName;
		ZipOutputStream out = new ZipOutputStream(
				new FileOutputStream(filePath));
		File[] file1;
		// 需要同时下载的两个文件result.txt ，source.txt
		if (path.indexOf(",") > -1) {
			String[] paths = path.split(",");
			file1 = new File[paths.length];
			for (int i = 0; i < paths.length; i++) {
				file1[i] = new File(paths[i]);
			}
		} else {
			file1 = new File[1];
			file1[0] = new File(path);
		}
		for (int i = 0; i < file1.length; i++) {
			FileInputStream fis = new FileInputStream(file1[i]);
			out.putNextEntry(new ZipEntry(file1[i].getName()));
			int len;
			// 读入需要下载的文件的内容，打包到zip文件
			while ((len = fis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.closeEntry();
			fis.close();
		}
		out.close();
		String fileName = URLEncoder.encode(strZipName, "UTF-8");
		fileName = fileName.replaceAll("\\+", "%20");
		HashMap<String, String> contentTypeMap = (HashMap<String, String>) this
				.getAttachHeard(strZipName, "1");
		response.setHeader("Cache-control", "private");
		String contentTypeDesc = contentTypeMap.get("contentTypeDesc");
		if (contentTypeDesc != null) {
			response.setContentType(contentTypeDesc);
		} else {
			response.setContentType("application/octet-stream");
		}
		response.setContentType("application/msword");
		File file = new File(filePath);
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + "\"");
		// 读出文件到i/o流
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];// 相当于我们的缓存
		long k = 0;// 该值用于计算当前实际下载了多少字节
		// 从response对象中得到输出流,准备下载
		OutputStream outFile = response.getOutputStream();

		// 开始循环下载
		while (k < file.length()) {
			int j = buff.read(b, 0, 1024);
			k += j;
			// 将b中的数据写到客户端的内存
			outFile.write(b, 0, j);
		}
		// 将写入到客户端的内存的数据,刷新到磁盘
		fis.close();
		outFile.flush();
		outFile.close();
		file.delete();
		return null;
	}
	
	public int getAllFIleSize(Connection conn,Account account) throws SQLException
	{
		int returnData=0;
		String queryStr="SELECT SUM(FILE_SIZE) AS SIZE FROM ATTACHMENT WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData= rs.getInt("SIZE");
		}
		rs.close();
		ps.close();
		return returnData;
		}
	

	// 文件记录上传
	public void doFileUploadLogic(Connection conn, Attachment attachment)
			throws SQLException {
		String queryStr = "INSERT INTO ATTACHMENT (ATTACHMENT_ID,ATTACHMENT_NAME,UP_TIME,CREATE_ACCOUNT_ID,USE_COUNT,USE_USER,PATH,MODULES,PRIV,CONFIG_NO,DEL_FLAG,FILE_SIZE,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, attachment.getAttachmentId());
		ps.setString(2, attachment.getAttachmentName());
		ps.setString(3, attachment.getUpTime());
		ps.setString(4, attachment.getCreateAccountId());
		ps.setInt(5, attachment.getUseCount());
		ps.setString(6, attachment.getUseUser());
		ps.setString(7, attachment.getPath());
		ps.setString(8, attachment.getModules());
		ps.setString(9, attachment.getPriv());
		ps.setString(10, attachment.getConfigNo());
		ps.setString(11, attachment.getDelFlag());
		ps.setLong(12, attachment.getFileSize());
		ps.setString(13, attachment.getOrgId());
		ps.executeUpdate();
		ps.close();
	}

	// 文件目录生成规则
	public String filePath(String modules) {
		String attachPath = SysProps.getAttachPath();
		Date date = new Date();
		String ym = SysTool.getDateTimeStrYMCn1(date);
		String path = attachPath + "\\" + ym;
		path = path + "\\" + modules;
		File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
		return path + "\\";
	}

	// 文件随机码
	public String getFileRandomCode() {
		Date date = new Date();
		String fileRandomCode = SysTool.getDateTimeStrSR(date);
		return fileRandomCode;
	}

	// 文件上传
	public void doFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String module = request.getParameter("module");
		String divId = request.getParameter("divId");
		JSONArray jsonArr = new JSONArray();
		Connection conn = null;
		long fileSize=0;
		UnitLogic unitLogic = new UnitLogic();
		int attachMax=0;
		int allFileSize=0;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(	"ACCOUNT_ID");
			attachMax = unitLogic.getOrgAttachMaxLogic(conn, account);
			allFileSize=this.getAllFIleSize(conn, account);
			if(attachMax*1024*1024<(allFileSize/1024))
			{
				JSONObject  json = new JSONObject();
				json.accumulate("msg", "OK");
				jsonArr.add(json);
			}else
			{
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart) {
					String fileRandomCode = this.getFileRandomCode();
					String attachPath = this.filePath(module);
					int maxSize = SysProps.getInt(SysPropKey.MAX_UPLOAD_FILE_SIZE);
					String tmpPath = SysProps.getUploadCatchPath();
					DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,new File(tmpPath));
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setHeaderEncoding("UTF-8");
					upload.setSizeMax(1024 * 1024 * 1024 * maxSize);
					try {
						List<FileItem> fileItems = upload.parseRequest(request);
						Iterator<FileItem> iter = fileItems.iterator();
	
						while (iter.hasNext()) {
							FileItem item = (FileItem) iter.next();
							if (!item.isFormField()) {
								// 获得文件名及路径
								String fileName = item.getName();
								if (fileName != null) {
									// 如果文件存在则上传
									File fullFile = new File(attachPath+ item.getName());
									fileSize=item.getSize();
									String attachFilePath = attachPath
											+ fileRandomCode + "_"
											+ fullFile.getName();
									if (!fullFile.exists()) {
										File fileOnServer = new File(attachFilePath);
										try {
											item.write(fileOnServer);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										String attachmentId = "";
										try {
											attachmentId = GuId.getGuid();
										} catch (NoSuchAlgorithmException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										Attachment attachment = new Attachment();
										attachment.setAttachmentId(attachmentId);
										attachment.setAttachmentName(fileRandomCode
												+ "_" + fullFile.getName());
										attachment.setModules(module);
										attachment.setPath(attachFilePath);
										attachment.setCreateAccountId(account
												.getAccountId());
										attachment.setUpTime(SysTool
												.getCurDateTimeStr());
										attachment.setDelFlag("0");
										attachment.setFileSize(fileSize);
										attachment.setOrgId(account.getOrgId());
										this.doFileUploadLogic(conn, attachment);
										JSONObject json = new JSONObject();
										json.accumulate("attachmentId",
												attachmentId);
										json.accumulate("attachmentName",
												fullFile.getName());
										json.accumulate("divId", divId);
										json.accumulate("module", module);
										json.accumulate("path", attachFilePath);
										json.accumulate("msg", "OK");
										jsonArr.add(json);
									}
								}
							}
						}
					} catch (FileUploadException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					conn.commit();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbOp dbOp = new DbOp();
			dbOp.connClose(conn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonArr.toString());
			response.getWriter().flush();
		}

	}

	// 文件上传
	public void doFileUploadByFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String module = request.getParameter("module");
		String divId = request.getParameter("divId");
		String pathStr = request.getParameter("pathStr");
		pathStr = URLDecoder.decode(pathStr, "utf-8");
		JSONObject pathJson = JSONObject.fromObject(pathStr);
		JSONArray jsonArr = new JSONArray();
		Connection conn = null;
		UnitLogic unitLogic = new UnitLogic();
		int attachMax=0;
		int allFileSize=0;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account) request.getSession().getAttribute(
					"ACCOUNT_ID");
			if (account == null) {
				AccountLogic accountLogic = new AccountLogic();
				account = accountLogic.getAccountByAccountId(conn, "admin");
			}
			attachMax = unitLogic.getOrgAttachMaxLogic(conn, account);
			allFileSize=this.getAllFIleSize(conn, account);
			if(attachMax*1024*1024<(allFileSize/1024))
			{
				JSONObject  json = new JSONObject();
				json.accumulate("msg", "OK");
				jsonArr.add(json);
			}else{
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// if (isMultipart) {
			String fileRandomCode = this.getFileRandomCode();
			String attachPath = this.filePath(module);
			int maxSize = SysProps.getInt(SysPropKey.MAX_UPLOAD_FILE_SIZE);
			String tmpPath = SysProps.getUploadCatchPath();
			DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,
					new File(tmpPath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setSizeMax(1024 * 1024 * 1024 * maxSize);
			JSONArray pathArr = pathJson.getJSONArray("data");
			for (int i = 0; i < pathArr.size(); i++) {
				String pathObj = pathArr.getJSONObject(i).getString("path");
				String fileName = pathArr.getJSONObject(i).getString("name");
				String type = pathArr.getJSONObject(i).getString("type");
				try {
					SysFileTool.copyFile(pathObj, attachPath + fileRandomCode
							+ "_" + fileName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String attachmentId = "";
				try {
					attachmentId = GuId.getGuid();
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File readfile = new File(pathObj);
				int fileSize = (int)readfile.length();
				Attachment attachment = new Attachment();
				attachment.setAttachmentId(attachmentId);
				attachment.setAttachmentName(fileRandomCode + "_" + fileName);
				attachment.setModules(module);
				attachment
						.setPath(attachPath + fileRandomCode + "_" + fileName);
				attachment.setDelFlag("0");
				attachment.setCreateAccountId(account.getAccountId());
				attachment.setUpTime(SysTool.getCurDateTimeStr());
				attachment.setFileSize(fileSize);
				attachment.setOrgId(account.getOrgId());
				this.doFileUploadLogic(conn, attachment);
				JSONObject json = new JSONObject();
				json.accumulate("attachmentId", attachmentId);
				json.accumulate("attachmentName", fileName);
				json.accumulate("divId", divId);
				json.accumulate("module", module);
				json.accumulate("path", attachPath + fileRandomCode + "_"
						+ fileName);
				jsonArr.add(json);
			}
			conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbOp dbOp = new DbOp();
			dbOp.connClose(conn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonArr.toString());
			response.getWriter().flush();
		}
	}

	// 获取文件名列表
	public void getAttachNameByIdAct(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		JSONArray jsonArr = new JSONArray();
		
		String attachIds = request.getParameter("attachIds");
		if (!SysTool.isNullorEmpty(attachIds)) {
			attachIds = "'" + attachIds.replace(",", "','") + "'";
			Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
			Connection dbConn = DbPoolConnection.getInstance().getConnection();
			String queryStr = "SELECT ATTACHMENT_ID,ATTACHMENT_NAME FROM ATTACHMENT WHERE DEL_FLAG='0' AND ORG_ID=? AND ATTACHMENT_ID IN("
					+ attachIds + ")";
			PreparedStatement ps = dbConn.prepareStatement(queryStr);
			ps.setString(1, account.getOrgId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.accumulate("attachId", rs.getString("ATTACHMENT_ID"));
				json.accumulate("attachName", rs.getString("ATTACHMENT_NAME"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			dbConn.close();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonArr.toString());
			response.getWriter().flush();
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonArr.toString());
			response.getWriter().flush();
		}
	}

	// 获取相关的文档权限
	public String getAttachPriv(String attachId, Account account) {

		String returnData = "";
		return returnData;
	}

	// 通过attachId获取文件物理路经
	public String getAttachPath(String attachId) throws SQLException {
		Connection conn = null;
		String returnData = "";
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String queryStr = "SELECT PATH FROM ATTACHMENT WHERE DEL_FLAG='0' AND ATTACHMENT_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, attachId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				returnData = rs.getString("PATH");
			}
			rs.close();
			ps.close();
			DbOp dbop = new DbOp();
			dbop.connClose(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnData;
	}

	// 删除附件
	public void delAttachByIdAct(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		boolean flag = false;
		Account account = (Account) request.getSession().getAttribute(	"ACCOUNT_ID");
		String attachId = request.getParameter("attachId");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String path = this.getAttachPath(attachId);
			if (!path.equals("")) {
				flag = this.deleteFile(path);
			}
			if (flag == true) {
				String queryStr = "UPDATE ATTACHMENT SET DEL_FLAG='1' WHERE ATTACHMENT_ID=? AND ORG_ID=?";
				PreparedStatement ps = dbConn.prepareStatement(queryStr);
				ps.setString(1, attachId);
				ps.setString(2, account.getOrgId());
				ps.executeUpdate();
				ps.close();
			}
			dbConn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("OK");
			response.getWriter().flush();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	// 获取byattachId获取原始文件名
	public String getAttachName(String attachId) throws SQLException {
		Connection conn = DbPoolConnection.getInstance().getConnection();
		String returnData = "";
		String queryStr = "SELECT ATTACHMENT_NAME FROM ATTACHMENT WHERE DEL_FLAG='0' AND  ATTACHMENT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, attachId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("ATTACHMENT_NAME");
			returnData = returnData.substring(18, returnData.length());
		}
		rs.close();
		ps.close();
		conn.close();
		return returnData;
	}

	public Map<String, String> getAttachHeard(String fileName, String directView) {
		HashMap<String, String> result = new HashMap<String, String>();
		String contentTypeDesc = "";
		Integer contentType = 0;
		String extName = "";
		int extNameIndex = fileName.lastIndexOf(".");
		if (extNameIndex > 0) {
			extName = fileName.substring(extNameIndex).toLowerCase();
		}
		int arrayIndex = -1;
		String[] extFileArray = { ".jpg", ".jpeg", ".bmp", ".gif", ".png",
				".html", ".htm", ".wmv", ".wav", ".mid", ".mht", ".pdf", ".swf" };
		String[] extDocArray = { ".doc", ".dot", ".xls", ".xlc", ".xll",
				".xlm", ".xlw", ".csv", ".ppt", ".pot", ".pps", ".ppz",
				".docx", ".dotx", ".xlsx", ".xltx", ".pptx", ".potx", ".ppsx",
				".rm", ".rmvb" };

		if (directView != null && "1".equals(directView.trim())) {
			arrayIndex = getIndex(extFileArray, extName);
			switch (arrayIndex) {
			case 0:
			case 1:
				contentType = 1;
				contentTypeDesc = "image/jpeg";
				break;
			case 2:
				contentType = 1;
				contentTypeDesc = "image/bmp";
				break;
			case 3:
				contentType = 1;
				contentTypeDesc = "image/gif";
				break;
			case 4:
				contentType = 1;
				contentTypeDesc = "image/png";
				break;
			case 5:
			case 6:
				contentType = 1;
				contentTypeDesc = "text/html";
				break;
			case 7:
			case 8:
			case 9:
			case 10:
				contentType = 1;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 11:
				contentType = 1;
				contentTypeDesc = "application/pdf";
				break;
			case 12:
				contentType = 1;
				contentTypeDesc = "application/x-shockwave-flash";
				break;
			case 13:
				contentType = 1;
				contentTypeDesc = "application/msword";
				break;
			default:
				contentType = 0;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			}
		} else {
			arrayIndex = getIndex(extDocArray, extName);
			switch (arrayIndex) {
			case 0:
			case 1:
				contentType = 1;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 2:
				contentType = 0;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				contentType = 1;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				contentType = 0;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 12:
			case 13:
				contentType = 1;
				contentTypeDesc = "application/msword";
				break;
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
				contentType = 1;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			case 19:
			case 20:
				contentType = 1;
				contentTypeDesc = "audio/x-pn-realaudio";
				break;
			default:
				contentType = 0;
				contentTypeDesc = "application/octet-stream;charset=UTF-8";
				break;
			}
		}
		result.put("contentTypeDesc", contentTypeDesc);
		result.put("contentType", contentType.toString());
		return result;
	}

	private int getIndex(String[] array, String indexStr) {
		int result = -1;
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].equals(indexStr.trim())) {
					result = i;
				}
			}
		}
		return result;
	}

	public String getAllAttachNameById(String attachId) throws Exception {

		Connection dbConn = null;
		String returnData = "";
		dbConn = DbPoolConnection.getInstance().getConnection();
		String queryStr = "SELECT ATTACHMENT_ID,ATTACHMENT_NAME FROM ATTACHMENT WHERE DEL_FLAG='0'  AND ATTACHMENT_ID = '"
				+ attachId + "'";
		PreparedStatement ps = dbConn.prepareStatement(queryStr);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnData = rs.getString("ATTACHMENT_NAME");
		}
		rs.close();
		ps.close();
		dbConn.close();

		return returnData;
	}

	public Attachment getAttachmentAct(Connection conn, String attachId)
			throws SQLException {
		Attachment attachment = new Attachment();
		String queryStr = "SELECT ID,ATTACHMENT_ID,ATTACHMENT_NAME,UP_TIME,CREATE_ACCOUNT_ID,USE_COUNT,USE_USER,PATH,MODULES,PRIV,CONFIG_NO,DEL_FLAG,ORG_ID FROM ATTACHMENT WHERE ATTACHMENT_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, attachId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			attachment.setId(rs.getInt("ID"));
			attachment.setAttachmentId(rs.getString("ATTACHMENT_ID"));
			attachment.setAttachmentName(rs.getString("ATTACHMENT_NAME"));
			attachment.setUpTime(rs.getString("UP_TIME"));
			attachment.setCreateAccountId(rs.getString("CREATE_ACCOUNT_ID"));
			attachment.setUseCount(rs.getInt("USE_COUNT"));
			attachment.setUseUser(rs.getString("USE_USER"));
			attachment.setPath(rs.getString("PATH"));
			attachment.setModules(rs.getString("MODULES"));
			attachment.setPriv(rs.getString("PRIV"));
			attachment.setConfigNo(rs.getString("CONFIG_NO"));
			attachment.setDelFlag(rs.getString("DEL_FLAG"));
			attachment.setOrgId(rs.getString("ORG_ID"));
		}
		rs.close();
		ps.close();
		return attachment;
	}

	public void getTextConentAct(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		JSONObject json = new JSONObject();
		String attachId = request.getParameter("attachId");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Attachment attachment = new Attachment();
			attachment = this.getAttachmentAct(dbConn, attachId);
			String path = attachment.getPath();
			String fileName = attachment.getAttachmentName().substring(18,
					attachment.getAttachmentName().length());
			json.accumulate("fileName", fileName);
			json.accumulate("conent", this.readTxtFile(path));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
	}

	public void getTextConentByDisk(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		JSONObject json = new JSONObject();
		String path = request.getParameter("path");
		path = URLDecoder.decode(path, "utf-8");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			String fileName = path.substring(path.lastIndexOf("."),
					path.length());
			json.accumulate("fileName", fileName);
			json.accumulate("conent", this.readTxtFile(path));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
	}

	public String readTxtFile(String filePath) {
		String returnData = "";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					returnData += "<p>" + lineTxt + "</p>";
				}
				read.close();
			} else {
				returnData = "找不到指定的文件";
			}
		} catch (Exception e) {
			returnData = "读取文件内容出错";
			e.printStackTrace();
		}
		return returnData;
	}
}
