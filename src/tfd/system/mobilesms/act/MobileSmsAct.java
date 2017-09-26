package tfd.system.mobilesms.act;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.mobilesms.data.MobileSms;
import tfd.system.mobilesms.logic.MobileSmsLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;
import com.system.tool.RequestUtil;
import com.system.tool.SysTool;

public class MobileSmsAct {
	public void sendMoblieSmsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String accountIds = request.getParameter("accountId");
		String outSideNo= request.getParameter("outSideNo");
		String sendTime = request.getParameter("sendTime");
		String sendContent = request.getParameter("sendContent");
		MobileSms mobileSms = new MobileSms();
		mobileSms.setSendAccountId(account.getAccountId());
		mobileSms.setOrgId(account.getOrgId());
		mobileSms.setSendTime(sendTime);
		mobileSms.setCreateTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		mobileSms.setOutSideNo(outSideNo);
		mobileSms.setSendContent(sendContent);
		mobileSms.setAccountIds(accountIds);
		mobileSms.setOrgId(account.getOrgId());
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		MobileSmsLogic mobileSmsLogic = new MobileSmsLogic();
		mobileSmsLogic.sendMobileSmsLogic(dbConn, mobileSms);
		dbConn.commit();
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("OK");
		response.getWriter().flush();
	}
	public void queryMoblieSmsAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String pageStr=request.getParameter("page");
		String pageSizeStr=request.getParameter("rows");
		String sortName=request.getParameter("sort");
		String sortOrder=request.getParameter("order");
		int page=1;
		int pageSize=10;
		if(!SysTool.isNullorEmpty(pageStr))
		{
			page=Integer.parseInt(pageStr);
		}
		if(!SysTool.isNullorEmpty(pageSizeStr))
		{
			pageSize=Integer.parseInt(pageSizeStr);
		}
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		String accountId = request.getParameter("accountId");
		String outSideNo= request.getParameter("outSideNo");
		String beginCreateTime = request.getParameter("beginCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		String status = request.getParameter("status");
		String delFlag = request.getParameter("delFlag");
		MobileSms mobileSms = new MobileSms();
		mobileSms.setAccountIds(accountId);
		mobileSms.setOutSideNo(outSideNo);
		mobileSms.setBeginCreateTime(beginCreateTime);
		mobileSms.setEndCreateTime(endCreateTime);
		mobileSms.setStatus(status);
		mobileSms.setDelFlag(delFlag);
		MobileSmsLogic mobileSmsLogic = new MobileSmsLogic();
		String returnData=mobileSmsLogic.queryMoblieSmsLogic(dbConn, mobileSms, account, pageSize, page, sortOrder, sortName);
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}

	public void send(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		String returnString="-1";
		try {
			String mobile=request.getParameter("mobile");
			String msg=request.getParameter("msg");
			
			
		} catch (Exception e) {
			throw e;
		}finally{
			writer=response.getWriter();
			writer.print(returnString);
			SysTool.closePrintWriter(writer);
		}
	}
}
