package com.system.tool;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import com.system.global.SysLogConst;
import tfd.system.unit.account.data.Account;
import tfd.system.syslog.logic.SysLogLogic;

public class PasswordValidator implements LoginValidator {
	String isSecureCard = "0";
	  String returnValue = "0";
	  String password  =  "";
	  public PasswordValidator(String password) {
	    this.password = password;
	  }
	  
	  public void addSysLog(HttpServletRequest request, Account account,
	      Connection conn) throws Exception {
	    //系统日志-登陆密码错误
	    SysLogLogic.addSysLog(conn, SysLogConst.LOGIN_PASSWORD_ERROR, "登录密码错误",
	    	account.getAccountId(), request.getRemoteAddr());
	  }


	  
	  public String getValidatorMsg() {
	    // TODO Auto-generated method stub
	    return null;
	  }
	@Override
	public boolean isValid(HttpServletRequest request, Account account,
			Connection conn) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValidatorType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getValidatorCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
