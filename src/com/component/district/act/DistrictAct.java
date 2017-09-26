package com.component.district.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class DistrictAct {
	public String search(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		PrintWriter writer=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			String searchField=request.getParameter("searchField");
			String parentId=request.getParameter("parentid");
			
			String sql="select "+searchField+",(select count(1) from district where parentid=d.id) children from district d where parentId=? order by `order`";
			
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(parentId));
			rs=stmt.executeQuery();
			
			String[] fields=searchField.split(",");
			JSONArray district=new JSONArray();
			JSONObject aDistrict=null;
			while(rs.next()){
				aDistrict=new JSONObject();
				for(String field:fields){
					String value=rs.getString(field);
					aDistrict.accumulate(field, value);
				}
				aDistrict.accumulate("children", rs.getInt("children"));
				district.add(aDistrict);
			}
			
			writer=response.getWriter();
			writer.print(district.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
}
