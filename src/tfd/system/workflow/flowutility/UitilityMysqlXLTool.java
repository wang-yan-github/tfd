package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class UitilityMysqlXLTool {
	BaseDao dao=new BaseDaoImpl();
	public void XListTool(Connection conn, String childTable, String model) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			childTable = childTable.toUpperCase();
			JSONArray models = JSONArray.fromObject(model);
			
			ArrayList<String> oldFields = new ArrayList<String>();
			String createTableSql = "ID int(11) NOT NULL auto_increment,"
								 + " RUN_ID varchar(50),"
								 + " TAG varchar(50),";
			rs = conn.getMetaData().getTables(null, null, childTable, null);
			//childTable是否已存在
			if (rs.next()) {
				//收集已存在的字段
				ps = conn.prepareStatement("SELECT * FROM " + childTable+ " LIMIT 1");
				rs = ps.executeQuery();
				ResultSetMetaData data = rs.getMetaData();
				if (data.getColumnCount() >0) {
					for (int i = 1; data.getColumnCount() >= i; i++) {
						oldFields.add(data.getColumnName(i).toUpperCase());
					}
				}
				
				for (int i = 0; models.size() > i; i++) {
					JSONObject modelObj = JSONObject.fromObject(models.get(i));
					String fieldName = modelObj.getString("fieldname").toUpperCase();
					String maxLength = modelObj.getString("width");
					if (SysTool.isNullorEmpty(maxLength)) maxLength="200";
					
					if (oldFields.contains(fieldName)) {
						continue;
					}
					
					ps = conn.prepareStatement("ALTER TABLE " + childTable + " ADD " + fieldName + " VARCHAR ("+maxLength+")");
					ps.execute();
				}
			} else {
				for (int i = 0; models.size() > i; i++) {
					JSONObject modelObj = JSONObject.fromObject(models.get(i));
					String filedName = modelObj.getString("fieldname").toUpperCase();
					String maxLength = modelObj.getString("width");
					if (SysTool.isNullorEmpty(maxLength)) maxLength="200";
					
					createTableSql = createTableSql + filedName + " VARCHAR ("+ maxLength + "),";
				}
				createTableSql = "CREATE TABLE " + childTable + "("+ createTableSql + " KEY ID (ID))";
				ps = conn.prepareStatement(createTableSql);
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
	}
}
