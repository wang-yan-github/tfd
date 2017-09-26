package diary.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.DiaryComments;
import diary.logic.DiaryCommentsLogic;

public class DiaryCommentsAct {

	/**
	 * 添加日志评论
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addCommentsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String userName=(String) request.getSession().getAttribute("USER_NAME");
			DiaryCommentsLogic dcomlogicCommentsLogic=new DiaryCommentsLogic();
			DiaryComments diaryComm=new DiaryComments();
			diaryComm.setCommId(GuId.getGuid());
			diaryComm.setCommPid(request.getParameter("commPid"));
			diaryComm.setAccountId(account.getAccountId());
			diaryComm.setCommContect(request.getParameter("commContect"));
			diaryComm.setCommTime(SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			diaryComm.setDiaryId(request.getParameter("diaryId"));
			diaryComm.setOrgId(account.getOrgId());
			data = dcomlogicCommentsLogic.addCommentsLogic(dbConn, diaryComm);
			String smsRemindStr = request.getParameter("smsReminds");
			JSONObject smsRemindJson =JSONObject.fromObject(smsRemindStr);
			if(request.getParameter("commPid").equals("")){
			String accountId=request.getParameter("accountId");
			List<String> toAccountList = new ArrayList<String>();
			toAccountList.add(accountId);
			MessageApi messageApi = new MessageApi();
			messageApi.addMessage(dbConn, "diary", smsRemindJson, ""+userName+"评论了你的日志", account.getAccountId(), toAccountList,account.getOrgId(),SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
			}else{
				List<String> toAccountList = new ArrayList<String>();
				toAccountList.add(dcomlogicCommentsLogic.getcommIdNameLogic(dbConn, request.getParameter("commPid"), account.getOrgId()));
				MessageApi messageApi = new MessageApi();
				messageApi.addMessage(dbConn, "diary", smsRemindJson, ""+userName+"回复了你的评论", account.getAccountId(), toAccountList,account.getOrgId(),SysTool.getCurDateTimeStr( "yyyy-MM-dd HH:mm:ss"));
				
			}
			
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据日志diaryId 获取评论内容
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookCommentsAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn =null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			DiaryCommentsLogic dcomlogicCommentsLogic=new DiaryCommentsLogic();
			String diaryId=request.getParameter("diaryId");
			String orgId=account.getOrgId();
			data=dcomlogicCommentsLogic.lookCommentsLogic(dbConn, diaryId, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据Id 删除日志评论信息
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delCommAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			DiaryCommentsLogic dcomlogicCommentsLogic=new DiaryCommentsLogic();
			String commId=request.getParameter("commId");
			String orgId=account.getOrgId();
			data=dcomlogicCommentsLogic.delCommIdlogic(dbConn, commId, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
	/**
	 * 根据日志diaryId 获取评论条数
	 * Author: Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdcountAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			DiaryCommentsLogic dcomlogicCommentsLogic=new DiaryCommentsLogic();
			String diaryId=request.getParameter("diaryId");
			String orgId=account.getOrgId();
			data=dcomlogicCommentsLogic.getIdcountLogic(dbConn, diaryId, orgId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		DbOp dbop = new DbOp();
		dbop.connClose(dbConn);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		response.getWriter().flush();
		}
	}
}
