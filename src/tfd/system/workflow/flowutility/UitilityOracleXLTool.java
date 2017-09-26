package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbOp;
import com.system.tool.SysTool;

public class UitilityOracleXLTool {
	//列表控件处理
		public void XListTool(Connection conn,String childTable,String model) throws SQLException
		{
			JSONArray jsonArr = JSONArray.fromObject(model);
			ArrayList<String> oldChildFieldList = new ArrayList<String>();
			String createTableSql="ID NUMBER NOT NULL ,"+
					" RUN_ID VARCHAR2(50),"+
					" TAG VARCHAR2(50),";
			ResultSet rs=null;
			PreparedStatement ps =null;
			childTable=childTable.toUpperCase();
			ps=conn.prepareStatement("SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME='"+childTable+"'");
			rs=ps.executeQuery();
			while(rs.next())
			{
				oldChildFieldList.add(rs.getString("COLUMN_NAME").toUpperCase());
			}
			rs.close();
			if (oldChildFieldList.size()>0)
			{
					for(int i=0;jsonArr.size()>i;i++)
					{
						JSONObject json = JSONObject.fromObject(jsonArr.get(i));
						String filedName=json.getString("fieldname").toUpperCase();
						String maxLength=json.getString("width");
						String alterSql="";
						if(SysTool.isNullorEmpty(maxLength))
						{
							alterSql="ALTER TABLE "+childTable+" ADD ("+filedName+" VARCHAR2(200))";
						}else
						{
							alterSql="ALTER TABLE "+childTable+" ADD ("+filedName+" VARCHAR2 ("+maxLength+"))";
						}
						ArrayList<String> tmpList = new ArrayList<String>();
						tmpList.add(filedName);
						if(Collections.disjoint(tmpList, oldChildFieldList))
						{
							ps= conn.prepareStatement(alterSql);
							ps.executeUpdate();
						}
					}
			}else
			{
				for(int i=0;jsonArr.size()>i;i++)
				{
					JSONObject json = JSONObject.fromObject(jsonArr.get(i));
					String filedName=json.getString("fieldname").toUpperCase();
					String maxLength=json.getString("width");
					if(SysTool.isNullorEmpty(maxLength))
					{
						createTableSql=createTableSql+filedName+" VARCHAR2 (200),";	
					}else
					{
						createTableSql=createTableSql+filedName+" VARCHAR2 ("+maxLength+"),";	
					}
				}
				createTableSql="CREATE TABLE "+childTable+"("+createTableSql+" PRIMARY KEY (ID))";
				String indexSql = "CREATE  INDEX "+childTable+"_INDEX ON "+childTable+" (RUN_ID)";
				ps=conn.prepareStatement(createTableSql);
				ps.execute();
				ps=conn.prepareStatement(indexSql);
				ps.execute();
				ps.close();	
				DbOp dbop = new DbOp();
				try {
					dbop.cretateSeqTg(childTable, conn);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
}
