package tfd.system.interfaces.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.interfaces.data.Interface;
import tfd.system.interfaces.logic.InterfaceLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class InterfaceAct {
	
	/**
	 * 添加或修改界面设置
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateInterface(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Interface inter = new Interface();
			String interfaceId = "";
			inter.setTopTitle(request.getParameter("topTitle"));
			inter.setTopImg(request.getParameter("topImg"));
			inter.setBottomTitle(request.getParameter("bottomTitle"));
			inter.setBrowserTitle(request.getParameter("browserTitle"));
			InterfaceLogic iLogic = new InterfaceLogic();
			if(request.getParameter("interfaceId").equals("")){
				interfaceId = GuId.getGuid();
				inter.setInterfaceId(interfaceId);
				returnData = iLogic.addInterface(dbConn,inter,account);
			}else{
				interfaceId = request.getParameter("interfaceId");
				inter.setInterfaceId(interfaceId);
				returnData = iLogic.updateInterface(dbConn,inter,account);
			}
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
	
	/**
	 * 获取界面设置信息
	 * Time:2015-9-28
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getInterface(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			InterfaceLogic iLogic = new InterfaceLogic();
			returnData = iLogic.getInterface(dbConn,account);
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
