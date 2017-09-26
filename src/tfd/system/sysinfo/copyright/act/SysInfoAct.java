package tfd.system.sysinfo.copyright.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.sysinfo.copyright.data.SysConfig;
import tfd.system.sysinfo.copyright.logic.SysInfoLogic;



import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class SysInfoAct {
	//获取系统信息
	public void getSysInfoAct(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			SysInfoLogic logic = new SysInfoLogic();
			returnData=logic.getSysInfoLogic(dbConn,account.getOrgId());
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public void getSysInfoList(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Connection dbConn =null;
		String returnData="";
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		SysInfoLogic logic = new SysInfoLogic();
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		returnData=logic.getSysInfoList(dbConn,account.getOrgId());
		dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	public void getSysInfoListByLogin(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String returnData="";
		try
		{
		dbConn=DbPoolConnection.getInstance().getConnection();
		String userName = request.getParameter("userName");
		SysInfoLogic logic = new SysInfoLogic();
		String orgId = logic.getOrgIdByaccountId(dbConn,userName);
		returnData=logic.getSysInfoList(dbConn,orgId);
		dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public void updateSysConfig(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		int returnData = 0;
		try{
			dbConn=DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			SysConfig sysConfig = new SysConfig();
			sysConfig.setSysConfigId(request.getParameter("sysConfigId"));
			sysConfig.setUpdateName(request.getParameter("updateName"));
			sysConfig.setInitPwd(request.getParameter("initPwd"));
			sysConfig.setPwdCycle(request.getParameter("pwdCycle"));
			sysConfig.setOutPwd(request.getParameter("outPwd"));
			String pwdWidth = request.getParameter("pwdWidth1")+"-"+request.getParameter("pwdWidth2");
			sysConfig.setPwdWidth(pwdWidth);
			sysConfig.setIsAbc(request.getParameter("isAbc"));
			sysConfig.setMissNum(request.getParameter("missNum"));
			sysConfig.setMissTime(request.getParameter("missTime"));
			sysConfig.setMissPwd(request.getParameter("missPwd"));
			sysConfig.setFindPwd(request.getParameter("findPwd"));
			sysConfig.setRemberUser(request.getParameter("remberUser"));
			sysConfig.setMoreLogin(request.getParameter("moreLogin"));
			sysConfig.setComWithPhone(request.getParameter("comWithPhone"));
			sysConfig.setUseKey(request.getParameter("useKey"));
			sysConfig.setDomainLogin(request.getParameter("domainLogin"));
			sysConfig.setUserIp(request.getParameter("userIp"));
			sysConfig.setRemberStatus(request.getParameter("remberStatus"));
			sysConfig.setOrgId(account.getOrgId());
			SysInfoLogic logic = new SysInfoLogic();
			returnData = logic.updateSysConfig(dbConn, sysConfig);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
