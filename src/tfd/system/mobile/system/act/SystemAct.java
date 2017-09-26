package tfd.system.mobile.system.act;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tfd.system.mobile.customer.logic.CustomerInfoLogic;
import tfd.system.mobile.system.logic.SystemLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.mobilesms.logic.MobileSmsLogic;
import tfd.system.syslog.logic.SysLogLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.org.logic.UnitLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.filetool.Attachment;
import com.system.filetool.UpFileTool;
import com.system.filetool.UpImgTool;
import com.system.global.SysConst;
import com.system.global.SysLogConst;
import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.login.data.UserOnline;
import com.system.login.logic.DoLoginLogic;
import com.system.servlet.ListenerSession;
import com.system.tool.GuId;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

public class SystemAct {
    /**
     * 登陆验证 Time:2015-9-28 Author:Yzz
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            Account account = null;
            boolean isLogin = false;
            AccountLogic accountLogic = new AccountLogic();
            SystemLogic systemLogic = new SystemLogic();
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            String accountId = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String password = sysUtil.checkParam(response, "password", request.getParameter("password"));
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            String deviceId = request.getParameter("deviceId");
            isLogin = systemLogic.queryAccount(dbConn, accountId, password);
            if (isLogin) {
                account = accountLogic.getAccountByAccountId(dbConn, accountId);
                account.setLastVisitTime(new Date());
                String loginType = request.getParameter("CLIENT");
                this.loginSuccess(dbConn, account, request, response, loginType);
                request.setAttribute("DEVICE_ID", deviceId);
                request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
                request.getSession().setAttribute(SysConst.LOGIN_USER, account);
                // systemLogic.setInitNum(dbConn, phone);
                JSONObject returnJson = new JSONObject();
                returnJson.accumulate("status_code", "200");
                returnJson.accumulate("msg", "登陆成功");
                String data = systemLogic.getUserInfoLogic(dbConn, accountId, request);
                returnJson.accumulate("data", data);
                returnData = returnJson.toString();
            } else {
                JSONObject returnJson = new JSONObject();
                returnJson.accumulate("status_code", "500");
                returnJson.accumulate("msg", "登陆失败");
                JSONObject json = new JSONObject();
                json.accumulate("infomation", "");
                returnJson.accumulate("data", json);
                returnData = returnJson.toString();
            }
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
        // return returnData;
    }

    /**
     * 登陆成功后的相关操作 Time:2015-9-28 Author:Yzz
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void loginSuccess(Connection conn, Account account, HttpServletRequest request,
            HttpServletResponse response, String loginType) throws Exception {

        // 获取用户当前的session,如果不存在就生成一个新的session
        HttpSession session = request.getSession(true);
        account.setLastVisitTime(new Date());
        if (session.getAttribute("ACCOUNT_ID") == null) {
            SysLogLogic.addSysLog(conn, SysLogConst.LOGIN, "登录成功", account.getAccountId(),
                    request.getRemoteAddr());
            setUserInfoInSession(account, session, request.getRemoteAddr(), conn);
            String ip = getRemortIP(request);
            this.addOnline(conn, account, String.valueOf(session.getAttribute("sessionToken")), ip);
        } else {
            Account loginAccount = (Account) session.getAttribute("ACCOUNT_ID");
            if (loginAccount.getAccountId() != account.getAccountId()) {
                session.invalidate();
                loginSuccess(conn, account, request, response, loginType);
            }
        }
        if (SysTool.isNullorEmpty(loginType)) {
            loginType = "0";
        }
    }

    /**
     * 添加Ip地址 Time:2015-9-28 Author:Yzz
     * 
     * @param request
     * @return
     */
    public String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 添加到在线 Time:2015-9-28 Author:Yzz
     * 
     * @param conn
     * @param account
     * @param sessionToken
     * @param ip
     * @throws Exception
     */
    public void addOnline(Connection conn, Account account, String sessionToken, String ip) throws Exception {
        UserOnline online = new UserOnline();
        online.setSessionToken(sessionToken);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        online.setLoginTime(df.format(new Date()));
        online.setAccountId(account.getAccountId());
        online.setState("1");
        online.setOrgId(account.getOrgId());
        online.setIp(ip);
        DoLoginLogic logic = new DoLoginLogic();
        int state = logic.queryUserOnline(conn, account.getAccountId());
        if (state == 0) {
            logic.addOnline(conn, online);
        } else {
            logic.updateLastVisitInfo(conn, online);
        }
    }

    /**
     * 设置用户信息 Time:2015-9-28 Author:Yzz
     * 
     * @param account
     * @param session
     * @param ip
     * @param conn
     * @throws Exception
     */
    public void setUserInfoInSession(Account account, HttpSession session, String ip, Connection conn)
            throws Exception {
        String sessionToken = session.getId();
        session.setAttribute("ACCOUNT_ID", account);
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        String userName = userInfoLogic.getUserInfoByAccountId(conn, account.getAccountId()).getUserName();
        String deptId = userInfoLogic.getUserInfoByAccountId(conn, account.getAccountId()).getDeptId();
        String orgId = account.getOrgId();
        DeptLogic deptLogic = new DeptLogic();
        session.setAttribute("USER_ID", account.getAccountId());
        session.setAttribute("USER_NAME", userName);
        session.setAttribute("DEPT_ID", deptId);
        session.setAttribute("DEPT_NAME_LONG", deptLogic.getDeptNameLong(conn, deptId));
        session.setAttribute("DEPT_NAME_SHORT", deptLogic.getDeptNameShort(conn, deptId));
        session.setAttribute("ORG_ID", orgId);
        session.setAttribute("sessionToken", sessionToken);
        session.setAttribute("LOGIN_IP", ip);
        ListenerSession.addSession(session);
        String lockSecStr = SysProps.getString("$OFFLINE_TIME_MIN");
        Long lockSec = null;
        try {
            lockSec = Long.valueOf(Integer.parseInt(lockSecStr) * 60 * 1000);
        } catch (Exception e) {
            lockSec = Long.valueOf(0);
        }
        session.setAttribute("OFFLINE_TIME_MIN", lockSec);
    }

    /**
     * 获取验证码 Time:2015-9-28 Author:Yzz
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void getcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        JSONObject returnData = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            String accountId = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            SystemLogic systemLogic = new SystemLogic();
            String code = systemLogic.getCodeLogic(dbConn, accountId);

            if (code != null) {
                // 发送短信
                String returnString = new MobileSmsLogic().send(phone, "【OA】您的验证码是" + code);
                returnString = returnString.split("\n")[0].split(",")[1];
                System.out.println("code:" + code);
                System.out.println(returnString);
                if (returnString.equals("0")) {
                    returnData = JSONObject
                            .fromObject("{'status_code':'200','msg':'获取验证码成功','data':{'time':'"
                                    + new Date().getTime() / 1000 + "'}}");
                    dbConn.commit();
                } else {
                    dbConn.rollback();
                }
            } else {
                dbConn.rollback();
            }

        } catch (Exception e) {
            new BaseDaoImpl().rollback(dbConn);
            throw e;
        } finally {
            if (returnData == null) {
                returnData = JSONObject.fromObject("{'status_code':'500','msg':'获取验证码失败','data':{'time':'"
                        + new Date().getTime() / 1000 + "'}}");
            }

            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData.toString());
            response.getWriter().flush();
        }
    }

    /**
     * 修改密码 Time:2015-9-28 Author:Yzz
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void retrieve(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String code = sysUtil.checkParam(response, "code", request.getParameter("code"));
            String password = sysUtil.checkParam(response, "password", request.getParameter("password"));
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.retrieveLogic(dbConn, phone, code, MD5Util.getMD5(password));
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void checkupdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = sysUtil.checkParam(response, "version", request.getParameter("version"));
            String platform = sysUtil.checkParam(response, "platform", request.getParameter("platform"));
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.checkupdateLogic(dbConn, phone, token, version, platform);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        HttpSession session = request.getSession(false);
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.logoutLogic(dbConn, request, phone, token, version, platform);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            // response.getWriter().flush();
            session.invalidate();
        }
    }

    public void changepassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            String password = sysUtil.checkParam(response, "password", request.getParameter("password"));
            String old_password = sysUtil.checkParam(response, "old_password",
                    request.getParameter("old_password"));
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.changepasswordLogic(dbConn, phone, token, version, platform,
                    MD5Util.getMD5(password), MD5Util.getMD5(old_password));
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void personalupdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            UserInfo info = new UserInfo();
            info.setHomeTel(request.getParameter("work_phone"));
            info.setUserName(request.getParameter("name"));
            info.setSign(request.getParameter("sign"));
            info.setMobileNo(request.getParameter("mobile_phone"));
            info.seteMail(request.getParameter("email"));
            info.setQq(request.getParameter("qq"));
            info.setBirthday(request.getParameter("birthday"));
            String HeadImg = request.getParameter("attachIds");
            info.setHeadImg(HeadImg);
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.personalupdate(dbConn, phone, token, version, platform, info);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void about(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.aboutLogic(dbConn, account, token, version, platform);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    /**
     * 登陆成功后获取用户的菜单权限(内部管理接口) Time:2015-10-08 Author:Wp
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";

        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.getUserPowerLogic(dbConn, account, token, version, platform);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    /**
     * 内部管理列表状态更新接口 Time:2015-10-08 Author:Wp
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void liststate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            String list = request.getParameter("list");
            list = URLDecoder.decode(list, "utf-8");
            SystemLogic systemLogic = new SystemLogic();
            returnData = systemLogic.liststateLogic(dbConn, account, list, token, version, platform);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    /**
     * 检查权限
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void checkpermission(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            String phone = sysUtil.checkParam(response, "phone", request.getParameter("phone"));
            phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
            Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
            String token = sysUtil.checkParam(response, "access_token", request.getParameter("access_token"));
            sysUtil.checkAccessToken(request, response, token, phone);
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            String type = request.getParameter("type");

            if (type.equals("1")) {
                CustomerInfoLogic customerInfoLogic = new CustomerInfoLogic();
                returnData = customerInfoLogic.checkpowerLogic(dbConn, account);
            } else if (type.equals("2")) {

            }
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        long fileSize = 0;
        UnitLogic unitLogic = new UnitLogic();
        int attachMax = 0;
        int allFileSize = 0;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            SystemUtil sysUtil = new SystemUtil();
            UpFileTool upFileTool = new UpFileTool();
            Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
            String token = request.getParameter("access_token");
            String version = request.getParameter("version");
            String platform = request.getParameter("platform");
            String module = sysUtil.checkParam(response, "module", request.getParameter("module"));
            if (!module.equals("personal") && !module.equals("feedback")) {
                if (module.equals("dailyRecord")) {
                    module = "diary";
                }
                attachMax = unitLogic.getOrgAttachMaxLogic(dbConn, account);
                allFileSize = upFileTool.getAllFIleSize(dbConn, account);
                long maxSize = attachMax * 1024 * 1024;
                if (maxSize < (allFileSize / 1024)) {
                } else {
                    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                    if (isMultipart) {
                        String fileRandomCode = upFileTool.getFileRandomCode();
                        String attachPath = upFileTool.filePath(module);
                        int maxSize1 = SysProps.getInt(SysPropKey.MAX_UPLOAD_FILE_SIZE);
                        String tmpPath = SysProps.getUploadCatchPath();
                        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath));
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        upload.setHeaderEncoding("UTF-8");
                        upload.setSizeMax(1024 * 1024 * maxSize1);
                        try {
                            List<FileItem> fileItems = upload.parseRequest(request);
                            Iterator<FileItem> iter = fileItems.iterator();
                            String diaryAnnex = "";
                            while (iter.hasNext()) {
                                FileItem item = (FileItem) iter.next();
                                if (!item.isFormField()) {
                                    // 获得文件名及路径
                                    String fileName = item.getName();
                                    if (fileName != null) {
                                        // 如果文件存在则上传
                                        File fullFile = new File(attachPath + item.getName());
                                        fileSize = item.getSize();
                                        String attachFilePath = attachPath + fileRandomCode + "_"
                                                + fullFile.getName();
                                        if (!fullFile.exists()) {
                                            File fileOnServer = new File(attachFilePath);
                                            try {
                                                item.write(fileOnServer);
                                            } catch (Exception e) {
                                                // TODO Auto-generated catch
                                                // block
                                                e.printStackTrace();
                                            }
                                            String attachmentId = "";
                                            try {
                                                attachmentId = GuId.getGuid();
                                            } catch (NoSuchAlgorithmException e) {
                                                // TODO Auto-generated catch
                                                // block
                                                e.printStackTrace();
                                            }
                                            Attachment attachment = new Attachment();
                                            attachment.setAttachmentId(attachmentId);
                                            diaryAnnex += attachmentId + ",";
                                            attachment.setAttachmentName(fileRandomCode + "_"
                                                    + fullFile.getName());
                                            attachment.setModules(module);
                                            attachment.setPath(attachFilePath);
                                            attachment.setCreateAccountId(account.getAccountId());
                                            attachment.setUpTime(SysTool.getCurDateTimeStr());
                                            attachment.setDelFlag("0");
                                            attachment.setFileSize(fileSize);
                                            attachment.setOrgId(account.getOrgId());
                                            upFileTool.doFileUploadLogic(dbConn, attachment);

                                        }
                                    }
                                }
                            }
                            if (diaryAnnex != "") {
                                diaryAnnex = diaryAnnex.substring(0, diaryAnnex.length() - 1);
                                JSONObject json = new JSONObject();
                                json.accumulate("status_code", "200");
                                json.accumulate("msg", "图片上传成功");
                                JSONObject datajson = new JSONObject();
                                datajson.accumulate("attachId", diaryAnnex);
                                json.accumulate("data", datajson);
                                returnData = json.toString();
                            } else {
                                JSONObject json = new JSONObject();
                                json.accumulate("status_code", "500");
                                json.accumulate("msg", "图片上传失败");
                                JSONObject datajson = new JSONObject();
                                datajson.accumulate("attachId", "");
                                json.accumulate("data", datajson);
                                returnData = json.toString();
                            }
                            dbConn.commit();
                        } catch (FileUploadException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                    }
                }
            } else {
                String imgName = new UpImgTool().imgUploadBymobile(request, response, module);
                if (imgName != "") {
                    imgName = request.getContextPath() + "/attachment/userinfo/" + imgName;
                    JSONObject json = new JSONObject();
                    json.accumulate("status_code", "200");
                    json.accumulate("msg", "图片上传成功");
                    JSONObject datajson = new JSONObject();
                    datajson.accumulate("attachId", imgName);
                    json.accumulate("data", datajson);
                    returnData = json.toString();
                } else {
                    JSONObject json = new JSONObject();
                    json.accumulate("status_code", "500");
                    json.accumulate("msg", "图片上传失败");
                    JSONObject datajson = new JSONObject();
                    datajson.accumulate("attachId", "");
                    json.accumulate("data", datajson);
                    returnData = json.toString();
                }
                dbConn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SystemUtil sysUtil = new SystemUtil();
        String attachId = sysUtil.checkParam(response, "attachId", request.getParameter("attachId"));
        String returnData = "";
        UpFileTool upFileTool = new UpFileTool();
        String path = upFileTool.getAttachPath(attachId);
        String fileName = upFileTool.getAllAttachNameById(attachId);
        if (SysTool.isNullorEmpty(path)) {
            JSONObject json = new JSONObject();
            json.accumulate("status_code", "500");
            json.accumulate("msg", "图片下载失败");
            returnData = json.toString();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        } else {
            File readfile = new File(path);
            int fileSize = (int) readfile.length();
            if (fileName.indexOf(".") > -1) {
                String expandedName = fileName.substring(fileName.lastIndexOf(".", fileName.length())); // 文件扩展名
                expandedName = expandedName.toLowerCase();
                if (!expandedName.equals(".jpg") && !expandedName.equals(".jpeg")
                        && !expandedName.equals(".png") && !expandedName.equals(".gif")
                        && !expandedName.equals(".bmp")) {
                    response.setContentType("application/octet-stream; charset=UTF-8");
                    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                } else {
                    response.setContentType("image/jpeg; charset=UTF-8");
                }
            } else {
                response.setContentType("application/octet-stream; charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            }
            if (fileSize > 0) {
                response.setContentLength(fileSize);
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
            } else {
                JSONObject json = new JSONObject();
                json.accumulate("status_code", "500");
                json.accumulate("msg", "下载失败");
                returnData = json.toString();
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(returnData);
                response.getWriter().flush();
            }
        }
    }
}
