package diary.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

import diary.data.DiaryFit;
import diary.logic.DiaryFitLogic;

public class DiaryFitAct {

	/**
	 * 保存工作日志 设置信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveFitAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			DiaryFit diaryFit=new DiaryFit();
			diaryFit.setStartTime(request.getParameter("startTime"));
			diaryFit.setEndTime(request.getParameter("endTime"));
			if(!request.getParameter("lockDay").equals("")){
			diaryFit.setLockDay(Integer.parseInt(request.getParameter("lockDay")));
			}else{
				diaryFit.setLockDay(0);	
			}
			diaryFit.setShareStatus(Integer.parseInt(request.getParameter("shareStatus")));
			diaryFit.setCommStatus(Integer.parseInt(request.getParameter("commStatus")));
			diaryFit.setOrgId(account.getOrgId());
			DiaryFitLogic diaryFitLogic=new DiaryFitLogic();
			if(request.getParameter("fitId")!=""){
				diaryFit.setFitId(request.getParameter("fitId"));
				data=diaryFitLogic.updateFitLogic(dbConn, diaryFit);
			}else{
				diaryFit.setFitId(GuId.getGuid());
				data=diaryFitLogic.addFitLogic(dbConn, diaryFit);
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
	}
	
	/**
	 * 根据orgId  查询工作日志设置信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookFitAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		String data="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryFitLogic diaryFitLogic=new DiaryFitLogic();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data=diaryFitLogic.lookFitLogic(dbConn, account.getOrgId());
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
	}
	/**
	 * 根据orgId 获取锁定天数
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getlockDayAct(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Connection dbConn = null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			DiaryFitLogic diaryFitLogic=new DiaryFitLogic();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			data=diaryFitLogic.getlockDayLogic(dbConn, account.getOrgId());
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(data);
			response.getWriter().flush();	
		}
		
	}
}
