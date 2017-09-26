package infoservice.org.act;

import infoservice.org.data.InfoserviceServiceItem;
import infoservice.org.logic.InfoserviceServiceItemLogic;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.unit.account.data.Account;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.AutoConvertUtil;
import com.system.tool.GuId;
import com.system.tool.JsonConfigDefault;
import com.system.tool.SysTool;

public class InfoserviceServiceItemAct {
	InfoserviceServiceItemLogic logic=new InfoserviceServiceItemLogic();
	BaseDao dao=new BaseDaoImpl();
	AutoConvertUtil autoConvertUtil=new AutoConvertUtil(); 
	public void add(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			InfoserviceServiceItem item=(InfoserviceServiceItem) autoConvertUtil.formToEntity(InfoserviceServiceItem.class, request.getParameterMap());
			item.setServiceId(GuId.getGuid());
			String orgId=((Account)request.getSession().getAttribute("ACCOUNT_ID")).getOrgId();
			item.setOrgId(orgId);
			
			int result=logic.add(item, conn);
			
			if (result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			JSONObject resultObj=new JSONObject();
			resultObj.accumulate("result",result>0);
			resultObj.accumulate("serviceId", item.getServiceId());
			
			writer=response.getWriter();
			writer.print(resultObj.toString());
		} catch (Exception e) {
			// TODO: handle exception
			dao.rollback(conn);
			throw e;
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void update(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			InfoserviceServiceItem item=(InfoserviceServiceItem) autoConvertUtil.formToEntity(InfoserviceServiceItem.class, request.getParameterMap());
			
			int result=logic.update(item, conn);
			
			if (result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			

			JSONObject resultObj=new JSONObject();
			resultObj.accumulate("result",result>0);
			resultObj.accumulate("serviceId", item.getServiceId());
			
			writer=response.getWriter();
			writer.print(resultObj.toString());
		} catch (Exception e) {
			// TODO: handle exception
			dao.rollback(conn);
			throw e;
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
	public void save(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			String serviceId=request.getParameter("serviceId");
			if (serviceId.equals("")) {
				add(request, response);
			}else{
				update(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void search(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			String orgId=request.getParameter("orgId");
			List<InfoserviceServiceItem> items=logic.search(orgId, conn);
			JSONArray itemsJson=JSONArray.fromObject(items,JsonConfigDefault.getJsonConfig());
			writer=response.getWriter();
			writer.print(itemsJson.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void delete(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String serviceId=request.getParameter("serviceId");
			int result=logic.delete(serviceId, conn);
			
			if (result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			// TODO: handle exception
			dao.rollback(conn);
			throw e;
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
}
