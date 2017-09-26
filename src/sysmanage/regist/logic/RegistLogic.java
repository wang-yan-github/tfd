package sysmanage.regist.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sysmanage.regist.data.Regist;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.SysTool;

public class RegistLogic {
	public BaseDao dao=new BaseDaoImpl();
	public int regist(Regist regist,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="insert into sysm_regist("
					+ "		id,regist_unit,product_sn,regist_disk_sn,"
					+ "		regist_time,regist_deadline,regist_user_count,"
					+ "		regist_im_user_count"
					+ "	)"
					+ "	values(?,?,?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, regist.getId());
			stmt.setString(2, regist.getRegistUnit());
			stmt.setString(3, regist.getProductSn());
			stmt.setString(4, regist.getRegistDiskSn());
			stmt.setDate(5, new java.sql.Date(regist.getRegistTime().getTime()));
			stmt.setDate(6, new java.sql.Date(regist.getRegistDeadline().getTime()));
			stmt.setInt(7, regist.getRegistUserCount());
			stmt.setInt(8, regist.getRegistImUserCount());
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	public JSONArray registeredList(Connection conn) throws Exception{
		JSONArray list=new JSONArray();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select p.sn as product_sn,p.product_name,"
					+ "			p.version as product_version,"
					+ "		r.id,r.regist_unit,r.regist_time,"
					+ "		r.regist_deadline,r.regist_user_count,"
					+ "		r.regist_im_user_count,r.regist_disk_sn"
					+ "	from sysm_regist r left join sysm_product p on r.product_sn=p.sn";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				JSONObject o=new JSONObject();
				o.put("productSn",rs.getString("product_sn"));
				o.put("productName",rs.getString("product_name"));
				o.put("productVersion",rs.getString("product_version"));
				o.put("id",rs.getString("id"));
				o.put("registUnit",rs.getString("regist_unit"));
				o.put("registTime",SysTool.getDateTimeStr(rs.getDate("regist_time"), DateConst.VALUE_SHORT_DATE));
				o.put("registDeadline",SysTool.getDateTimeStr(rs.getDate("regist_deadline"), DateConst.VALUE_SHORT_DATE));
				o.put("registUserCount",rs.getString("regist_user_count"));
				o.put("registImUserCount",rs.getString("regist_im_user_count"));
				o.put("registDiskSn",rs.getString("regist_disk_sn"));
				list.add(o);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return list;
	}
	public JSONObject readRegisteredDetail(String id,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select p.sn as product_sn,"
					+ "			p.product_name,"
					+ "			p.version as product_version,"
					+"			p.product_team,"
					+"			p.product_site,"
					+ "			r.id,"
					+ "			r.regist_unit,"
					+ "			r.regist_time,"
					+ "			r.regist_deadline,"
					+ "			r.regist_user_count,"
					+ "			r.regist_im_user_count,"
					+ "			r.regist_disk_sn"
					+ "	from sysm_regist r left join sysm_product p on r.product_sn=p.sn where r.id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			
			if(rs.next()){
				JSONObject o=new JSONObject();
				o.put("productSn",rs.getString("product_sn"));
				o.put("productName",rs.getString("product_name"));
				o.put("productVersion",rs.getString("product_version"));
				o.put("productTeam",rs.getString("product_team"));
				o.put("productSite",rs.getString("product_site"));
				o.put("id",rs.getString("id"));
				o.put("registUnit",rs.getString("regist_unit"));
				o.put("registTime",SysTool.getDateTimeStr(rs.getDate("regist_time"), DateConst.VALUE_SHORT_DATE));
				o.put("registDeadline",SysTool.getDateTimeStr(rs.getDate("regist_deadline"), DateConst.VALUE_SHORT_DATE));
				o.put("registUserCount",rs.getString("regist_user_count"));
				o.put("registImUserCount",rs.getString("regist_im_user_count"));
				o.put("registDiskSn",rs.getString("regist_disk_sn"));
				return o;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
}
