package com.system.servlet;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import com.system.global.SysConst;
public class Listener implements ServletRequestListener{
	  public void requestInitialized(ServletRequestEvent sre) {
	    HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
	    try {
	      if (ServletTool.isGbkCode(request)) {
	        request.setCharacterEncoding("GBK");
	      }else {
	        request.setCharacterEncoding(SysConst.DEFAULT_CODE);
	      }
	    }catch(Exception ex) {      
	    }
	    request.getSession().setAttribute(SysConst.CURR_REQUEST_FLAG, true);
	    request.getSession().setAttribute(SysConst.CURR_REQUEST_ADDRESS, request.getRemoteAddr());	    
	  }
	  public void requestDestroyed(ServletRequestEvent sre) {    
	    HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
	    request.getSession().removeAttribute(SysConst.CURR_REQUEST_FLAG);
	    request.getSession().removeAttribute(SysConst.CURR_REQUEST_ADDRESS);
	  }
	}
