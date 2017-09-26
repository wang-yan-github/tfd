package com.system.filetool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.system.global.SysProps;
import com.system.tool.SysTool;

public class UpImgTool {

    public void upheadimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        JSONObject json = new JSONObject();
        if (isMultipart) {
            try {
                String uploadPath = this.imgModulPath(request);
                String tmpPath = SysProps.getUploadCatchPath();
                File tempFile = new File(tmpPath);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath));
                ServletFileUpload uploadfile = new ServletFileUpload(factory);
                List<FileItem> fileItems = uploadfile.parseRequest(request);
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        if (!SysTool.isNullorEmpty(fileName)) {
                            String expandedName = fileName.substring(fileName.lastIndexOf(".",
                                    fileName.length())); // 文件扩展名
                            expandedName = expandedName.toLowerCase();
                            if (!expandedName.equals(".jpg") && !expandedName.equals(".png")
                                    && !expandedName.equals(".gif") && !expandedName.equals(".bmp")) {
                                json.accumulate("msg", "1");
                                json.accumulate("conent", "请上传.jpg/.gif/.bmp/.png文件");
                                json.accumulate("imgname", "");
                            } else {
                                File upload = new File(uploadPath + item.getName());
                                UpFileTool upFileTool = new UpFileTool();
                                String fileRandomCode = upFileTool.getFileRandomCode();
                                String attachFilePath = uploadPath + fileRandomCode + "_" + upload.getName();
                                if (!upload.exists()) {
                                    File fileOnServer = new File(attachFilePath);
                                    try {
                                        item.write(fileOnServer);
                                        String returnPath = request.getContextPath() + "/attachment/"
                                                + request.getParameter("module") + "/" + fileRandomCode + "_"
                                                + upload.getName();
                                        json.accumulate("msg", "2");
                                        json.accumulate("webpath", returnPath);
                                        json.accumulate("imgname", fileRandomCode + "_" + upload.getName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }
                    }
                }
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(json.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            try {
                PrintWriter out = response.getWriter();
                String callback = request.getParameter("CKEditorFuncNum");
                String uploadPath = this.imgPath(request);
                String tmpPath = SysProps.getUploadCatchPath();
                DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath));
                ServletFileUpload uploadfile = new ServletFileUpload(factory);
                List<FileItem> fileItems = uploadfile.parseRequest(request);
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        if (fileName != null) {
                            String expandedName = fileName.substring(fileName.lastIndexOf(".",
                                    fileName.length())); // 文件扩展名
                            expandedName = expandedName.toLowerCase();
                            if (!expandedName.equals(".jpg") && !expandedName.equals(".png")
                                    && !expandedName.equals(".gif") && !expandedName.equals(".bmp")) {
                                out.println("<script type=\"text/javascript\">");
                                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'',"
                                        + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
                                out.println("</script>");
                                return;
                            }
                            File upload = new File(uploadPath + item.getName());
                            if (upload.length() > 600 * 1024) {
                                out.println("<script type=\"text/javascript\">");
                                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'',"
                                        + "'文件大小不得大于600k');");
                                out.println("</script>");
                                return;
                            }

                            // InputStream is = new FileInputStream(upload);
                            UpFileTool upFileTool = new UpFileTool();
                            String fileRandomCode = upFileTool.getFileRandomCode();
                            String attachFilePath = uploadPath + fileRandomCode + "_" + upload.getName();
                            if (!upload.exists()) {
                                File fileOnServer = new File(attachFilePath);
                                try {
                                    item.write(fileOnServer);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Date date = new Date();
                                String ym = SysTool.getDateTimeStrYMCn1(date);
                                String returnPath = request.getContextPath() + "/webattach/" + ym
                                        + "/uploadImg/" + fileRandomCode + "_" + upload.getName();
                                out.println("<script type=\"text/javascript\">");
                                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"
                                        + returnPath + "','')");
                                out.println("</script>");
                            }

                        }

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String imgPath(HttpServletRequest request) {
        String attachPath = SysProps.getWebPath();
        Date date = new Date();
        String ym = SysTool.getDateTimeStrYMCn1(date);
        String path = attachPath + "\\webattach\\" + ym + "\\uploadImg";
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + "\\";
    }

    public String imgModulPath(HttpServletRequest request) {
        String module = request.getParameter("module");
        if (module.equals("personal")) {
            module = "userinfo";
        }
        String attachPath = SysProps.getWebPath();
        Date date = new Date();
        String ym = SysTool.getDateTimeStrYMCn1(date);
        String path = attachPath + "\\attachment\\" + module;
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + "\\";
    }

    public void getImgAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String attachId = request.getParameter("attachId");
        UpFileTool upFileTool = new UpFileTool();
        String path = upFileTool.getAttachPath(attachId);
        if (SysTool.isNullorEmpty(path)) {
            return;
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        FileInputStream fis = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

    public void getImgActByDisk(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getParameter("path");
        if (SysTool.isNullorEmpty(path)) {
            return;
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        FileInputStream fis = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

    public String imgUploadBymobile(HttpServletRequest request, HttpServletResponse response, String module)
            throws Exception {
        String returnData = "";

        response.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        JSONObject json = new JSONObject();
        if (isMultipart) {
            try {
                String uploadPath = this.imgModulPath(request);
                String tmpPath = SysProps.getUploadCatchPath();
                DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath));
                ServletFileUpload uploadfile = new ServletFileUpload(factory);
                List<FileItem> fileItems = uploadfile.parseRequest(request);
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        if (!SysTool.isNullorEmpty(fileName)) {
                            if (fileName.indexOf(".") > -1) {
                                String expandedName = fileName.substring(fileName.lastIndexOf(".",
                                        fileName.length())); // 文件扩展名
                                expandedName = expandedName.toLowerCase();
                                if (!expandedName.equals(".jpg") && !expandedName.equals(".png")
                                        && !expandedName.equals(".gif") && !expandedName.equals(".bmp")) {
                                    json.accumulate("msg", "1");
                                    json.accumulate("conent", "请上传.jpg/.gif/.bmp/.png文件");
                                    json.accumulate("imgname", "");
                                } else {
                                    File upload = new File(uploadPath + item.getName());
                                    UpFileTool upFileTool = new UpFileTool();
                                    String fileRandomCode = upFileTool.getFileRandomCode();
                                    String attachFilePath = uploadPath + fileRandomCode + "_"
                                            + upload.getName();
                                    if (!upload.exists()) {
                                        File fileOnServer = new File(attachFilePath);
                                        try {
                                            item.write(fileOnServer);
                                            if (module.equals("attendance")) {
                                                returnData += fileRandomCode + "_" + upload.getName() + ",";
                                            } else {
                                                returnData = fileRandomCode + "_" + upload.getName();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return returnData;
    }

}
