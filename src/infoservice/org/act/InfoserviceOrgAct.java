package infoservice.org.act;

import infoservice.org.data.InfoserviceOrg;
import infoservice.org.logic.InfoserviceOrgLogic;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.org.data.Unit;
import tfd.system.unit.org.logic.UnitLogic;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.AutoConvertUtil;
import com.system.tool.GuId;
import com.system.tool.JsonConfigDefault;
import com.system.tool.SysTool;

public class InfoserviceOrgAct {
	InfoserviceOrgLogic logic=new InfoserviceOrgLogic();
	BaseDao dao=new BaseDaoImpl();
	AutoConvertUtil autoConvertUtil=new AutoConvertUtil(); 
	public void add(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			Unit unit=(Unit) autoConvertUtil.formToEntity(Unit.class, request.getParameterMap());
			unit.setOrgId(GuId.getGuid());
			
			int unitAdd=new UnitLogic().addUnitLogic(conn, unit);
			
			int serviceOrgAdd=0;
			if (unitAdd>0) {
				InfoserviceOrg infoserviceOrg=new InfoserviceOrg();
				infoserviceOrg.setOrgId(unit.getOrgId());
				serviceOrgAdd=logic.add(infoserviceOrg, conn);
			}
			
			if (serviceOrgAdd>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			writer=response.getWriter();
			writer.print(serviceOrgAdd>0);
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
			
			Unit unit=(Unit) autoConvertUtil.formToEntity(Unit.class, request.getParameterMap());
			
			int unitUpdate=new UnitLogic().saveUnitInfo(conn, unit);
			
			int infoserviceOrgUpdate=0;
			if (unitUpdate>0) {
				InfoserviceOrg infoserviceOrg=new InfoserviceOrg();
				infoserviceOrg.setOrgId(unit.getOrgId());
				infoserviceOrg.setBusinessLicense(request.getParameter("infoserviceOrg_businessLicense"));
				infoserviceOrg.setOrgCodeCertificate(request.getParameter("infoserviceOrg_orgCodeCertificate"));
				infoserviceOrg.setOrgIntroduce(request.getParameter("infoserviceOrg_orgIntroduce"));
				infoserviceOrg.setServiceArea(request.getParameter("infoserviceOrg_serviceArea"));
				infoserviceOrg.setTaxRegistrationCertificate(request.getParameter("infoserviceOrg_taxRegistrationCertificate"));
				
				infoserviceOrgUpdate=logic.update(infoserviceOrg, conn);
			}
			
			if (infoserviceOrgUpdate>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			writer=response.getWriter();
			writer.print(infoserviceOrgUpdate>0);
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
			String orgId=request.getParameter("orgId");
			if (orgId.equals("")) {
				add(request, response);
			}else{
				update(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void getOrgInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			Account account=(Account) request.getSession().getAttribute("ACCOUNT_ID");
			InfoserviceOrg org=logic.getOrgInfo(account.getOrgId(), conn);
			
			writer=response.getWriter();
			writer.print(JSONObject.fromObject(org,JsonConfigDefault.getJsonConfig()));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
	public void searchOfPaging(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			JSONObject data=logic.searchOfPaging(request.getParameterMap(), conn);
			if (data==null) {
				data=new JSONObject();
			}
			writer=response.getWriter();
			writer.print(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
}
