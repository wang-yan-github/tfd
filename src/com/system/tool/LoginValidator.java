package com.system.tool;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import com.system.logic.SysLogic;
import tfd.system.unit.account.data.Account;
public interface LoginValidator {
	  public static final SysLogic logic = new SysLogic();
	  public boolean isValid(HttpServletRequest request, Account account, Connection conn) throws Exception;
	  public void addSysLog(HttpServletRequest request, Account account, Connection conn) throws Exception;
	  public String getValidatorType();
	  public int getValidatorCode();
	  public String getValidatorMsg();
}
