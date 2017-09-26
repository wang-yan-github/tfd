package sysmanage.regist.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sysmanage.regist.data.Regist;
import sysmanage.regist.logic.RegistLogic;

import com.system.db.DbPoolConnection;
import com.system.global.DateConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class RegistAct {
	RegistLogic logic=new RegistLogic();
	public void regist(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		Connection conn=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			int registResult=logic.regist(new Regist(
					GuId.getGuid(), 
					request.getParameter("registUnit"), 
					request.getParameter("productSn"), 
					SysTool.parseDate(DateConst.VALUE_SHORT_DATE, request.getParameter("registTime")),
					SysTool.parseDate(DateConst.VALUE_SHORT_DATE, request.getParameter("registDeadline")), 
					Integer.parseInt(request.getParameter("registUserCount")), 
					Integer.parseInt(request.getParameter("registImUserCount")), 
					request.getParameter("registDiskSn")
			), conn);
			
			if (registResult>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			writer=response.getWriter();
			writer.print(registResult>0);
		} catch (Exception e) {
			// TODO: handle exception
			if (conn!=null) {
				conn.rollback();
			}
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, conn);
		}
	}
	public void registeredList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		Connection conn=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			writer=response.getWriter();
			writer.print(logic.registeredList(conn).toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, conn);
		}
	}
}
