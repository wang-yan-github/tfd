package infoservice.org.logic;

import infoservice.InfoserviceConst;
import infoservice.org.data.InfoserviceOrg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;

public class InfoserviceOrgLogic {
	private BaseDao dao=new BaseDaoImpl();
	public int add(InfoserviceOrg infoserviceOrg,Connection conn) throws Exception{
		int result=0;
		PreparedStatement stmt=null;
		try {
			String sql="insert into infoservice_org"
					+ "(org_id,org_introduce,service_area,business_license,org_code_certificate,tax_registration_certificate,submit_date) "
					+ "values(?,?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, infoserviceOrg.getOrgId());
			stmt.setString(2, infoserviceOrg.getOrgIntroduce());
			stmt.setString(3, infoserviceOrg.getServiceArea());
			stmt.setString(4, infoserviceOrg.getBusinessLicense());
			stmt.setString(5, infoserviceOrg.getOrgCodeCertificate());
			stmt.setString(6, infoserviceOrg.getTaxRegistrationCertificate());
			stmt.setTimestamp(7, new Timestamp(new Date().getTime()));
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
		return result;
	}
	public int update(InfoserviceOrg infoserviceOrg,Connection conn) throws Exception{
		int result=0;
		PreparedStatement stmt=null;
		try {
			String sql="update infoservice_org set "
					+ "org_introduce=?,service_area=?,business_license=?,"
					+ "org_code_certificate=?,tax_registration_certificate=?,"
					+ "submit_date=?,audit_status=?,audit_user=?,audit_date=?,audit_remark=? "
					+ "where org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, infoserviceOrg.getOrgIntroduce());
			stmt.setString(2, infoserviceOrg.getServiceArea());
			stmt.setString(3, infoserviceOrg.getBusinessLicense());
			stmt.setString(4, infoserviceOrg.getOrgCodeCertificate());
			stmt.setString(5, infoserviceOrg.getTaxRegistrationCertificate());
			stmt.setTimestamp(6, new Timestamp(new Date().getTime()));
			stmt.setString(7, InfoserviceConst.ORG_AUDIT_STATUS_START);
			stmt.setString(8, null);
			stmt.setTimestamp(9, null);
			stmt.setString(10, null);
			stmt.setString(11, infoserviceOrg.getOrgId());
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
		return result;
	}
	
	public InfoserviceOrg getOrgInfo(String orgId,Connection conn)throws Exception{
		InfoserviceOrg infoserviceOrg=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from infoservice_org where org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, orgId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				infoserviceOrg=new InfoserviceOrg(
						rs.getInt("id"),
						orgId,
						rs.getInt("visits"),
						rs.getString("org_introduce"),
						rs.getString("service_area"),
						rs.getString("business_license"),
						rs.getString("org_code_certificate"),
						rs.getString("tax_registration_certificate"),
						new Date(rs.getTimestamp("submit_date").getTime()),
						rs.getString("audit_status"),
						rs.getString("audit_user"),
						rs.getTimestamp("audit_date")==null?null:new Date(rs.getTimestamp("audit_date").getTime()),
						rs.getString("audit_remark")
				);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return infoserviceOrg;
	}
	
	public JSONObject searchOfPaging(Map<String,String[]> requestMap,Connection conn) throws Exception{
		JSONObject data=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select u.org_name,"
					+"(select count(1) from infoservice_service_item where audit_status='0') not_audit_service_item_count,"
					+"io.submit_date submit_date,"
					+"'<div class=\\'row-opt-bar\\'><span class=\\'row-opt opt-audit\\'>审核</span></div>' as optbar"
					+ " from unit u left join infoservice_org io on u.org_id=io.org_id where 1=1 ";
			
			Page page=PageUtil.toEasyuiPage(requestMap);
			PageTool pageTool=new PageTool();
			data= pageTool.getPageData(conn, sql, page);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return data;
	}
}
