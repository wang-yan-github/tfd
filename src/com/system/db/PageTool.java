package com.system.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.component.page.Page;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class PageTool {
	BaseDao dao=new BaseDaoImpl();
	/**
	 * 获取总记计数
	 * 
	 * @param conn
	 * @param SqlStr
	 * @param pramList
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Connection conn, String SqlStr, List<String> pramList)throws Exception {
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			String queryStr = "SELECT COUNT(*) FROM (" + SqlStr + ") SELECTTABLE";
			ps = conn.prepareStatement(queryStr);
			if (pramList != null && pramList.size() > 0) {
				for (int i = 1; pramList.size() >= i; i++) {
					ps.setString(i, pramList.get(i - 1));
				}
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return 0;
	}

	/**
	 * 获取当前页码全部内容
	 * @param conn
	 * @param SqlStr
	 * @param pramList
	 * @param total 每页显示记数
	 * @param page 当前页码
	 * @param optArrJson
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPageData(Connection conn, String SqlStr,
							List<String> pramList, int total, 
							int page, JSONArray optArrJson,
							String sortOrder, String sortName) throws Exception {
		JSONObject data = new JSONObject();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String name = "";
			String fuc = "";
			String parm = "";
			String agrStr = "";
			String optStr = "";
			String orderByStr = "";
			
			if (!SysTool.isNullorEmpty(sortOrder)&& !SysTool.isNullorEmpty(sortName)) {
				orderByStr = " ORDER BY " + sortName + " " + sortOrder;
			}
			String dbType = DatabaseUtil.getDatabaseType(conn);
			String sql = "";
			int totalRecord = getCount(conn, SqlStr, pramList);
			if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_SQLSERVER)) {
				sql = "SELECT TOP " + total + " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY ";
				if (!SysTool.isNullorEmpty(sortOrder)&& !SysTool.isNullorEmpty(sortName)) {
					sql += sortName +(SysTool.isNullorEmpty(sortOrder)?"":" desc") +") AS ROW_NUMBER,* FROM (" + SqlStr + ") A ) B WHERE ROW_NUMBER > " + total + "*(" + page+ "-1)";
				} else {
					sql += "ID) AS ROW_NUMBER,* FROM (" + SqlStr+ ") A ) B WHERE ROW_NUMBER > " + total + "*(" + page+ "-1)";
				}
			} else if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_MYSQL)) {
				if (page <= 0) {
					page = 1;
				}
				sql = SqlStr + orderByStr + " LIMIT " + (page - 1) * total+ "," + total + "";
			} else if (dbType.equals(ConnConst.VALUE_SOURCE_TYPE_ORACLE)) {
				if (page <= 0) {
					page = 1;
				}
				sql = "SELECT * FROM (SELECT TMP.*,ROWNUM AS RN FROM ("+ SqlStr + orderByStr + " ) TMP) WHERE RN BETWEEN  "+ (page - 1) * total + " AND " + page * total;
			}
			ps = conn.prepareStatement(sql);
			if (pramList != null && pramList.size() > 0) {
				for (int i = 1; pramList.size() >= i; i++) {
					ps.setString(i, pramList.get(i - 1));
				}
			}
			
			rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			int count = rs.getMetaData().getColumnCount();
			for (int i = 0; count > i; i++) {
				list.add(rs.getMetaData().getColumnLabel(i + 1));
			}
			JSONArray rows = new JSONArray();
			while (rs.next()) {
				JSONObject row = new JSONObject();
				for (int k = 0; list.size() > k; k++) {
					row.accumulate(list.get(k), rs.getString(list.get(k)));
				}
				if (optArrJson != null && optArrJson.size() > 0) {
					for (int i = 0; optArrJson.size() > i; i++) {
						agrStr = "";
						JSONObject optjson = new JSONObject();
						optjson = optArrJson.getJSONObject(i);
						parm = optjson.get("parm").toString();
						name = optjson.get("name").toString();
						fuc = optjson.get("function").toString();
						String[] parmStr = parm.split(",");
						for (int s = 0; parmStr.length > s; s++) {
							agrStr += "'" + rs.getString(parmStr[s]) + "',";
						}
						if (!SysTool.isNullorEmpty(agrStr)) {
							agrStr = agrStr.substring(0, agrStr.length() - 1);
						}
						optStr += "<a href=javascript:" + fuc + "(" + agrStr+ ");>" + name + "</a> ";
					}
				}
				row.accumulate("OPT", optStr);
				optStr = "";
				rows.add(row);
			}
			
			data.accumulate("rows", rows);
			data.accumulate("total", totalRecord);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return data;
	}

	public JSONObject getPageData(Connection conn, String sql, Page page)
			throws Exception {
		JSONObject data = null;
		data = this.getPageData(conn, sql, null, page.getPageSize(),
				page.getPageIndex(), null, page.getSortType(),
				page.getSortField());
		return data;
	}
}
