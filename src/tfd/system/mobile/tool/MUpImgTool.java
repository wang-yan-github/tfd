package tfd.system.mobile.tool;

import java.io.File;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;
import com.system.filetool.Attachment;
import com.system.filetool.UpFileTool;
import com.system.filetool.UpImgTool;
import com.system.global.SysProps;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class MUpImgTool {

public String MUpImgLogic(HttpServletRequest request,String module,Account account)
{
			String attachId="";
			Connection dbConn = null;
			UpFileTool upFileTool = new UpFileTool();
			try
			{
			dbConn = DbPoolConnection.getInstance().getConnection();
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				UpImgTool upImgTool = new UpImgTool();
				String uploadPath=upImgTool.imgPath(request);
				String tmpPath=SysProps.getUploadCatchPath();
			    DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath)); 
			    ServletFileUpload uploadfile = new ServletFileUpload(factory);  
				List<FileItem> fileItems  = uploadfile.parseRequest(request);
				 Iterator<FileItem> iter = fileItems.iterator();
				 while (iter.hasNext()) { 
					 FileItem item = (FileItem) iter.next();  
				        if (!item.isFormField()) {
				        	   	 File upload = new File(uploadPath+item.getName());
				                  String fileRandomCode = upFileTool.getFileRandomCode();
				                  String attachFilePath = uploadPath+fileRandomCode+"_"+upload.getName();
				                  		if (!upload.exists()) {
										                    			File fileOnServer = new File(attachFilePath);  
										                    			item.write(fileOnServer);
																		String attachmentId = GuId.getGuid();
																		Attachment attachment = new Attachment();
																		attachment.setAttachmentId(attachmentId);
																		attachment.setAttachmentName(fileRandomCode+ "_" + fileOnServer.getName());
																		attachment.setModules(module);
																		attachment.setPath(attachFilePath);
																		attachment.setCreateAccountId(account.getAccountId());
																		attachment.setUpTime(SysTool.getCurDateTimeStr());
																		attachment.setDelFlag("0");
																		attachment.setOrgId(account.getOrgId());
																		upFileTool.doFileUploadLogic(dbConn, attachment);
																		attachId = attachmentId+",";
				                  									}
				        								}
				 							}
				        }
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
	return attachId;
}

}
