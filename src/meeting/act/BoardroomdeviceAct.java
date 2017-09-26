package meeting.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.data.Boardroomdevice;
import meeting.logic.BoardroomdeviceLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class BoardroomdeviceAct {

	/**
	 * 添加 设备
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void adddeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomdeviceId(GuId.getGuid());
			boardroomdevice.setBoardroomId(request.getParameter("boardroomId"));
			boardroomdevice.setDeviceId(request.getParameter("deviceId"));
			boardroomdevice.setDeviceName(request.getParameter("deviceName"));
			boardroomdevice.setDeviceStatus(request.getParameter("deviceStatus"));
			boardroomdevice.setDeviceSimilar(request.getParameter("deviceSimilar"));
			boardroomdevice.setDeviceDescription(request.getParameter("deviceDescription"));
			boardroomdevice.setDevicetype(request.getParameter("deviceType"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.adddevicelogic(dbConn, boardroomdevice);
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
	 * 修改设备信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updatedeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomdeviceId(request.getParameter("boardroomdeviceId"));
			boardroomdevice.setBoardroomId(request.getParameter("boardroomId"));
			boardroomdevice.setDeviceId(request.getParameter("deviceId"));
			boardroomdevice.setDeviceName(request.getParameter("deviceName"));
			boardroomdevice.setDeviceStatus(request.getParameter("deviceStatus"));
			boardroomdevice.setDeviceSimilar(request.getParameter("deviceSimilar"));
			boardroomdevice.setDeviceDescription(request.getParameter("deviceDescription"));
			boardroomdevice.setDevicetype(request.getParameter("deviceType"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.updatedevicelogic(dbConn, boardroomdevice);
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
	 * 删除设备信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deldeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomdeviceId(request.getParameter("boardroomdeviceId"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.deldevicelogic(dbConn, boardroomdevice);
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
	 * 根据设备ID查询设备信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIddeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomdeviceId(request.getParameter("boardroomdeviceId"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.getIddevicelogic(dbConn, boardroomdevice);
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
	 * 获取设备信息列表
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void lookdeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			String orgId=account.getOrgId();
			data = boardroomdevicelogic.lookdevicelogic(dbConn, orgId, pageSize, page, sortOrder, sortName);
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
	 * 模糊查询会议室设备信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void termdeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
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
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomId(request.getParameter("boardroomId"));
			boardroomdevice.setDeviceId(request.getParameter("deviceId"));
			boardroomdevice.setDeviceName(request.getParameter("deviceName"));
			boardroomdevice.setDeviceStatus(request.getParameter("deviceStatus"));
			boardroomdevice.setDevicetype(request.getParameter("deviceType"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.termdevicelogic(dbConn, boardroomdevice, pageSize, page, sortOrder, sortName);
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
	 * 根据会议室ID获取设备列表
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getboardroomIddeviceAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroomdevice boardroomdevice=new Boardroomdevice();
			BoardroomdeviceLogic boardroomdevicelogic=new BoardroomdeviceLogic();
			boardroomdevice.setBoardroomId(request.getParameter("boardroomId"));
			boardroomdevice.setOrgId(account.getOrgId());
			data = boardroomdevicelogic.getboardroomIddevicelogic(dbConn, boardroomdevice);
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
