package com.system.servlet;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.global.SysConst;
import com.system.loadconfig.LoadConfig;
import com.system.servers.AutoThreadTool;
import com.system.sessionpool.SessionPoolList;
import com.system.tool.SysTool;
public class ActionServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


/**
   * 系统初始化

   */
  public void init() throws ServletException {
    super.init();
    try {
      String rootPath = getServletContext().getRealPath("/");
      LoadConfig.loadInit(rootPath);
    }catch (Exception ex) {
     ex.printStackTrace();
    }
  }
  
  /**
   * 释放资源
   */
  public void  destroy() {
    SessionPoolList.stopReleaseThread();
    AutoThreadTool.stopRun();
  }
  public void doGet(HttpServletRequest request,HttpServletResponse response){
	 String returnData=null;
	 PrintWriter writer=null;
    try {
    	response.setHeader("PRagma","No-cache"); 
        response.setHeader("Cache-Control","no-cache"); 
        response.setDateHeader("Expires", 0); 
        request.setAttribute(SysConst.ACT_CTX_PATH, ServletTool.getWebAppDir(this.getServletContext()));
        String className = request.getParameter(SysConst.ACT_CLASS);
        String methodName = request.getParameter(SysConst.ACT_METHOD);
        String qUri = request.getRequestURI();
        if (SysTool.isNullorEmpty(className)) {
          qUri = qUri.substring(request.getContextPath().length() + 1);
          int tmpIndex = qUri.lastIndexOf(".");
          if (tmpIndex > 0) {
            int tmpIndex2 = qUri.lastIndexOf("/");
            if (tmpIndex2 > 0) {
              className = qUri.substring(0, tmpIndex2);
              methodName = qUri.substring(tmpIndex2 + 1, tmpIndex);
            }
          }
        }
        
      Class<?> classObj = Class.forName(className.replace("/", "."));
      Class<?>[] paramTypeArray = new Class[]{HttpServletRequest.class, HttpServletResponse.class};
      Method methodObj = classObj.getMethod(methodName, paramTypeArray);
      String forWardUrl = (String)methodObj.invoke(classObj.newInstance(), new Object[]{request, response});
      if (!SysTool.isNullorEmpty(forWardUrl)) {
        String retMethod = (String)request.getAttribute(SysConst.RET_METHOD);
        if (SysTool.isNullorEmpty(retMethod)) {
          ServletTool.forward(forWardUrl, request, response);
        }else {
          String contextPath = request.getContextPath();
          if (SysTool.isNullorEmpty(contextPath)) {
            contextPath = "/tfd";
          }
          response.sendRedirect(contextPath + forWardUrl);
        }
      }
    }catch(Exception e) {
    	returnData = "{\"status_code\":\"500\",\"msg\":\"服务器内部错误\",\"data\":{\"time\":"+new Date().getTime()/1000+"}}";
    	e.printStackTrace();
    }finally {
    	if (returnData!=null) {
    		try {
    			writer=response.getWriter();
    			response.setContentType("application/json;charset=utf-8");
    			writer.print(returnData);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				SysTool.closePrintWriter(writer);
			}
		}
    	
    }
  }
  

  public void doPost(HttpServletRequest request,
             HttpServletResponse response) {
      doGet(request, response);
  }
}
