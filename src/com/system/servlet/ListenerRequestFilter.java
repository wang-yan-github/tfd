package com.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.SysTool;

public class ListenerRequestFilter implements Filter {
	// 编码
	protected String encoding = null;
	// 过滤器配置

	protected FilterConfig filterConfig = null;

	/**
	 * 释放资源
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * 执行过滤器
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		PrintWriter writer=null;
		try {
			String userAgent=((HttpServletRequest) request).getHeader("user-agent");
			HttpSession session = ((HttpServletRequest) request).getSession();
			String qUri = ((HttpServletRequest) request).getRequestURI();
			if (qUri.endsWith("/")) {
				qUri += "index.jsp";
			}
			if ((qUri.endsWith(".jsp") || qUri.endsWith(".act"))
					&& !ServletTool.isLoginAction((HttpServletRequest) request)) {
				String sessionId = session.getId();
				if (!SysTool.isNullorEmpty(sessionId)) {
					session = (HttpSession) ListenerSession.getSessaionContextMap().get(sessionId);
					if (session != null) {
						Account account = (Account) session.getAttribute(SysConst.LOGIN_USER);
						if (account != null) {
							((HttpServletRequest) request).getSession().setAttribute(SysConst.LOGIN_USER, account);
						}
					}
				}
				if (!ServletTool.isValidSession(session, SysConst.LOGIN_USER)) {
					if (SysTool.isMobilePhoneDevice(userAgent)||SysTool.isTabletDevice(userAgent)) {
						response.setContentType("application/json;charset=utf-8");
						writer=((HttpServletResponse) response).getWriter();
						writer.print("{\"status_code\":\"10004\",\"data\":{\"time\":"+new Date().getTime()/1000+"}}");
					}else{
						ServletTool.forward("/system/returnapi/sessionerror.jsp",
								(HttpServletRequest) request,
								(HttpServletResponse) response);
					}
					return;
				}
			}
			else if(qUri.startsWith("/tfd/com/system/filetool/UpFileTool/doFileUpload.act"))
		    {
		    	String	sessionId = qUri.split("=")[1];
		    	 if (!SysTool.isNullorEmpty(sessionId) ) {
				        session = (HttpSession) ListenerSession.getSessaionContextMap().get(sessionId);
				        if (session != null) {
					          Account account = (Account)session.getAttribute(SysConst.LOGIN_USER);
					          if (account != null) {
					        	  	((HttpServletRequest)request).getSession().setAttribute(SysConst.LOGIN_USER, account);
					          }
				        }
		    	 }
		    }else if(qUri.startsWith("/tfd/system/im/index.jsp"))
		    	 {
		    		 String	sessionId2 = qUri.split("=")[1];
			    	 if (!SysTool.isNullorEmpty(sessionId2) ) {
					        session = (HttpSession) ListenerSession.getSessaionContextMap().get(sessionId2);
					        if (session != null) {
						          Account account = (Account) ListenerSession.getSessaionContextMap().get(sessionId2).getAttribute("ACCOUNT_ID");
						        	  	((HttpServletRequest)request).getSession().setAttribute("ACCOUNT_ID",account);
						        	  	Connection dbConn = null;
						        	  	try
						        	  	{
						        	  	dbConn=DbPoolConnection.getInstance().getConnection();
						        	     UserInfoLogic userInfoLogic = new UserInfoLogic();
						        	    UserInfo userInfo=userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId());
						        	    String orgId =account.getOrgId();
						        	    DeptLogic deptLogic = new DeptLogic();
						        	    ((HttpServletRequest)request).getSession().setAttribute("USER_ID", account.getAccountId());
						        	    ((HttpServletRequest)request).getSession().setAttribute("USER_NAME", userInfo.getUserName());
						        	    ((HttpServletRequest)request).getSession().setAttribute("DEPT_ID", userInfo.getDeptId());
						        	    ((HttpServletRequest)request).getSession().setAttribute("DEPT_NAME_LONG", deptLogic.getDeptNameLong(dbConn, userInfo.getDeptId()));
						        	    ((HttpServletRequest)request).getSession().setAttribute("DEPT_NAME_SHORT", deptLogic.getDeptNameShort(dbConn, userInfo.getDeptId()));
						        	    ((HttpServletRequest)request).getSession().setAttribute("ORG_ID", orgId);
						        	    ((HttpServletRequest)request).getSession().setAttribute("sessionToken", sessionId2);
						        	  	}catch(Exception e){
						        			e.printStackTrace();
						        		}finally
						        		{
						        			DbOp dbop = new DbOp();
						        			dbop.connClose(dbConn);
						        		}
					        }
		    	 }
		    	 if (!ServletTool.isValidSession(session, "ACCOUNT_ID")) {
			    	  ServletTool.forward("/system/returnapi/sessionerror.jsp", (HttpServletRequest)request, (HttpServletResponse)response);
			        return;
			      }
		    }
			
			
			
			
			
			
			session.setAttribute("LAST_OPT_TIME", new Date().getTime());
			response.setCharacterEncoding(encoding);
			chain.doFilter(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 过滤器初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	/**
	 * 取得编码
	 * 
	 * @return
	 */
	protected String getEncoding() {
		return (this.encoding);
	}
}
