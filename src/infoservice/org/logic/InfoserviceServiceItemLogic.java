package infoservice.org.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

import infoservice.InfoserviceConst;
import infoservice.org.data.InfoserviceServiceItem;

public class InfoserviceServiceItemLogic {
	BaseDao dao=new BaseDaoImpl();
	public int add(InfoserviceServiceItem item,Connection conn)throws Exception{
		int result=0;
		PreparedStatement stmt=null;
		try {
			String sql="insert into infoservice_service_item"
					+ "(service_id,org_id,service_name,service_desc,service_price,service_price_highest,submit_date)"
					+ " values(?,?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, item.getServiceId());
			stmt.setString(2, item.getOrgId());
			stmt.setString(3, item.getServiceName());
			stmt.setString(4, item.getServiceDesc());
			stmt.setDouble(5, item.getServicePrice());
			stmt.setDouble(6, item.getServicePriceHighest());
			stmt.setTimestamp(7, new Timestamp(new Date().getTime()));
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null,stmt,null);
		}
		return result;
	}
	public int update(InfoserviceServiceItem item,Connection conn)throws Exception{
		int result=0;
		PreparedStatement stmt=null;
		try {
			String sql="update infoservice_service_item set "
					+ "service_name=?,service_desc=?,"
					+ "service_price=?,service_price_highest=?,"
					+ "submit_date=?,audit_status=?,audit_user=?,audit_date=?,audit_remark=? "
					+ "where service_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, item.getServiceName());
			stmt.setString(2, item.getServiceDesc());
			stmt.setDouble(3, item.getServicePrice());
			stmt.setDouble(4, item.getServicePriceHighest());
			stmt.setTimestamp(5, new Timestamp(new Date().getTime()));
			stmt.setString(6, InfoserviceConst.ORG_AUDIT_STATUS_START);
			stmt.setString(7, null);
			stmt.setTimestamp(8, null);
			stmt.setString(9, null);
			stmt.setString(10, item.getServiceId());
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null,stmt,null);
		}
		return result;
	}
	public int delete(String serviceId,Connection conn)throws Exception{
		int result=0;
		PreparedStatement stmt=null;
		try {
			String sql="delete from infoservice_service_item where service_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, serviceId);
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null,stmt,null);
		}
		return result;
	}
	public List<InfoserviceServiceItem> search(String orgId,Connection conn)throws Exception{
		List<InfoserviceServiceItem> items=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from infoservice_service_item where org_id=? order by id";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, orgId);
			
			rs=stmt.executeQuery();
			items=new ArrayList<InfoserviceServiceItem>();
			InfoserviceServiceItem item=null;
			while(rs.next()){
				item=new InfoserviceServiceItem(
						rs.getInt("id"),
						rs.getString("service_id"),
						orgId,
						rs.getString("service_name"),
						rs.getString("service_desc"),
						rs.getInt("service_price"),
						rs.getInt("service_price_highest"),
						new Date(rs.getTimestamp("submit_date").getTime()),
						rs.getString("audit_status"),
						rs.getString("audit_user"),
						rs.getTimestamp("audit_date")==null?null:new Date(rs.getTimestamp("audit_date").getTime()),
						rs.getString("audit_remark")
				);
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return items;
	}
}
