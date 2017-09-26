package com.system.login.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tfd.system.syslog.logic.SysLogLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysLogConst;
import com.system.global.SysProps;
import com.system.login.data.UserOnline;
import com.system.login.logic.ChangeOrgLogic;
import com.system.login.logic.DoLoginLogic;
import com.system.servlet.ListenerSession;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

public class DologinAct {

    public void CheckLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            dbConn.setAutoCommit(false);

            Account account = null;
            boolean isLogin = false;
            AccountLogic accountLogic = new AccountLogic();
            DoLoginLogic loginLogic = new DoLoginLogic();
            String accountId = request.getParameter("username");
            ChangeOrgLogic changeOrgLogic = new ChangeOrgLogic();
            String password = changeOrgLogic.getPasswordLogic(dbConn, accountId);
            isLogin = loginLogic.queryAccount(dbConn, accountId, password);

            if (isLogin) {
                account = accountLogic.getAccountByAccountId(dbConn, accountId);
                account.setLastVisitTime(new Date());
                String loginType = request.getParameter("CLIENT");
                this.loginSuccess(dbConn, account, request, response, loginType);
                request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
                request.getSession().setAttribute(SysConst.LOGIN_USER, account);
                loginLogic.setInitNum(dbConn, accountId);
                returnData = "pass";
            } else {
                returnData = "notpass";
            }
            dbConn.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(returnData);
            SysTool.closePrintWriter(writer);
        }

    }

    // PC im 登陆
    public void ImLoginInTo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            Account account = null;
            boolean isLogin = false;
            AccountLogic accountLogic = new AccountLogic();
            DoLoginLogic loginLogic = new DoLoginLogic();
            dbConn = DbPoolConnection.getInstance().getConnection();
            String accountId = request.getParameter("username");
            String password = request.getParameter("password");
            isLogin = loginLogic.queryAccount(dbConn, accountId, MD5Util.getMD5(password));
            if (isLogin) {
                account = accountLogic.getAccountByAccountId(dbConn, accountId);
                account.setLastVisitTime(new Date());
                String loginType = request.getParameter("CLIENT");
                this.loginSuccess(dbConn, account, request, response, loginType);
                request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
                request.getSession().setAttribute("ACCOUNT_ID", account);
                loginLogic.setInitNum(dbConn, accountId);
                returnData = request.getSession().getId();
            }
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            // ServletTool.forward("/system/im/index.jsp", request, response);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }

    }

    public void LoginInTo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            dbConn.setAutoCommit(false);

            // Object
            // sysIsRegistered=request.getSession().getAttribute(SysConst.SYS_IS_REGISTERED);
            // if (sysIsRegistered==null) {
            // sysIsRegistered=new RegistLogic().validate(dbConn);
            // request.getSession().setAttribute(SysConst.SYS_IS_REGISTERED,
            // sysIsRegistered);
            // }
            // if(!(Boolean)sysIsRegistered){
            // returnData="isNotRegistered";
            // return ;
            // }

            Account account = null;
            boolean isLogin = false;
            AccountLogic accountLogic = new AccountLogic();
            DoLoginLogic loginLogic = new DoLoginLogic();
            String accountId = request.getParameter("username");
            String password = request.getParameter("password");
            isLogin = loginLogic.queryAccount(dbConn, accountId, MD5Util.getMD5(password));
            if (isLogin) {
                account = accountLogic.getAccountByAccountId(dbConn, accountId);
                account.setLastVisitTime(new Date());
                String loginType = request.getParameter("CLIENT");
                this.loginSuccess(dbConn, account, request, response, loginType);
                request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
                request.getSession().setAttribute("ACCOUNT_ID", account);
                loginLogic.setInitNum(dbConn, accountId);
                returnData = "pass";
            } else {
                returnData = "notpass";
            }
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }

    }

    /**
     * 在session中设置用户信息
     * 
     * @param person
     * @param session
     */
    public void setUserInfoInSession(Account account, HttpSession session, String ip, Connection conn)
            throws Exception {
        String sessionToken = session.getId();
        session.setAttribute("ACCOUNT_ID", account);
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        UserInfo userInfo = userInfoLogic.getUserInfoByAccountId(conn, account.getAccountId());
        String orgId = account.getOrgId();
        DeptLogic deptLogic = new DeptLogic();
        session.setAttribute("USER_ID", account.getAccountId());
        session.setAttribute("USER_NAME", userInfo.getUserName());
        session.setAttribute("DEPT_ID", userInfo.getDeptId());
        session.setAttribute("DEPT_NAME_LONG", deptLogic.getDeptNameLong(conn, userInfo.getDeptId()));
        session.setAttribute("DEPT_NAME_SHORT", deptLogic.getDeptNameShort(conn, userInfo.getDeptId()));
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
     * 登录成功的处理
     * 
     * @param conn
     * @param person
     * @param request
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

    // 用户注销
    public String doLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            ListenerSession.removeSessaionContextMap(session.getId());
            session.invalidate();
        }
        return "/system/returnapi/logout.jsp";
    }

    public void getLastLoginTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String accountId = request.getParameter("username");
            returnData = new DoLoginLogic().getLastLoginTime(dbConn, accountId);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void getLoginNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String accountId = request.getParameter("username");
            returnData = new DoLoginLogic().getLoginNum(dbConn, accountId);
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }
}
