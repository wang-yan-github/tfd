package com.system.tool;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysLoginConst;

import tfd.system.unit.account.data.Account;

public class LoginAdapter {
	private HttpServletRequest request;
	  private Connection dbConn;
	  private Account account;
	  
	  public LoginAdapter(HttpServletRequest request, Account account) throws Exception{
	    this.request = request;
	    
	    
	    this.dbConn = DbPoolConnection.getInstance().getConnection();
	    
	    this.account = account;
	  }
	  
	  public boolean isValid(LoginValidator lv) throws Exception{
	    return lv.isValid(this.request, this.account, this.dbConn);
	  }
	  
	  public boolean validate(LoginValidator lv) throws Exception{
	    if (lv.isValid(this.request, this.account, this.dbConn)){
	      return true;
	    }
	    else{
	      
	      //写系统日志

	      lv.addSysLog(this.request, this.account, this.dbConn);
	      String msg = lv.getValidatorMsg();
	      if (msg == null || "".equals(msg.trim())){
	        msg = "{}";
	      }
	      //返回到页面上的信息
	      String retData = "{\"code\":" + lv.getValidatorCode() + ",\"msg\":" + msg + "}";
	      if (lv.getValidatorCode() == SysLoginConst.LOGIN_PW_EXPIRED_CODE || lv.getValidatorCode() == SysLoginConst.LOGIN_INITIAL_PW_CODE) {
	        String sessionToken = (String)request.getSession().getAttribute("sessionToken");
	        retData = "{\"code\":" + lv.getValidatorCode() + ",\"msg\":" + msg + ",\"seqId\":\""+ account.getID() +"\",\"sessionToken\":\""+ sessionToken +"\"}";
	      }
	      request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_ERROR);
	      request.setAttribute(SysConst.RET_MSRG, lv.getValidatorType());
	      request.setAttribute(SysConst.RET_DATA, retData);
	      
	      return false;
	    }
	  }
}
