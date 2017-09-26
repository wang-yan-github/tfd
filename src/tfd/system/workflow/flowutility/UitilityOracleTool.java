package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tfd.system.workflow.flowformitem.data.FlowFormItem;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;

import com.system.db.DbOp;
import com.system.tool.SysTool;

public class UitilityOracleTool {
	public void createOracleTableLogic(Connection conn,String formId,List<FlowFormItem> itemList) throws SQLException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<String> oldFIeldList = new ArrayList<String>();
		WorkFlowFormLogic workFLowFormLogic = new WorkFlowFormLogic();
		String tableName=workFLowFormLogic.getFormTableNameByFormIdLogic(conn, formId).toUpperCase();
		String createTableSql="ID NUMBER NOT NULL ,"
								+ "RUN_ID VARCHAR2(50), "
							    + "CHILD_RUN_ID VARCHAR2(200),"
							    + "PARENT_RUN_ID VARCHAR2(50),";
		ps=conn.prepareStatement("SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME='"+tableName.toUpperCase()+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			oldFIeldList.add(rs.getString("COLUMN_NAME").toUpperCase());
		}
		rs.close();
		if (oldFIeldList.size()>0)
		{
			for(int i=0;itemList.size()>i;i++)
			{
				//处理列表控件
				if(itemList.get(i).getXtype().equals("xlist"))
				{
					UitilityOracleXLTool UOXLT = new UitilityOracleXLTool();
					UOXLT.XListTool(conn, itemList.get(i).getChildTable(), itemList.get(i).getModel());
					ArrayList<String> tmpList = new ArrayList<String>();
					tmpList.add(itemList.get(i).getChildTable().toUpperCase());
					if(Collections.disjoint(tmpList, oldFIeldList))
					{
						ps= conn.prepareStatement("ALTER TABLE "+tableName+" ADD ("+itemList.get(i).getChildTable().toUpperCase()+" CLOB)");
						ps.execute();
						oldFIeldList.add(itemList.get(i).getFieldName().toUpperCase());	
					}	
				}else {
					ArrayList<String> tmpList = new ArrayList<String>();
					tmpList.add(itemList.get(i).getFieldName().toUpperCase());
					if(Collections.disjoint(tmpList, oldFIeldList))
					{
							if(itemList.get(i).getDataType().equals("text")||itemList.get(i).getDataType().equals("")||itemList.get(i).getDataType().equals(null))
							{
									ps= conn.prepareStatement("ALTER TABLE "+tableName+" ADD "+itemList.get(i).getFieldName().toUpperCase()+" CLOB ");
									ps.execute();
							}else	{
									if(Integer.parseInt(itemList.get(i).getMaxLength())>0 && SysTool.isInteger(itemList.get(i).getMaxLength()))
									{		
										ps= conn.prepareStatement("ALTER TABLE "+tableName+" ADD "+itemList.get(i).getFieldName().toUpperCase()+" VARCHAR2"+
												"("+itemList.get(i).getMaxLength()+")");
										ps.execute();
									}
					}
							oldFIeldList.add(itemList.get(i).getFieldName().toUpperCase());	
				}
				}
			}
		}else  {
			for(int i=0;itemList.size()>i;i++)
			{
				//处理列表控件
				if(itemList.get(i).getXtype().equals("xlist"))
				{
					UitilityOracleXLTool UOXLT = new UitilityOracleXLTool();
					UOXLT.XListTool(conn, itemList.get(i).getChildTable(), itemList.get(i).getModel());
					createTableSql=createTableSql+itemList.get(i).getChildTable().toUpperCase()+" VARCHAR2("+50+"),";
				}else
				{
				String filedName=itemList.get(i).getFieldName().toUpperCase();
				String dataType=itemList.get(i).getDataType();
				String maxLength=itemList.get(i).getMaxLength();
				if(dataType.equals("text"))
				{
					createTableSql=createTableSql+filedName+" CLOB,";
				}else{	
					if(SysTool.isNullorEmpty(maxLength))
					{
						maxLength="10";
					}
					if(Integer.parseInt(maxLength)>0 && SysTool.isInteger(maxLength))
					{
					createTableSql=createTableSql+filedName+" VARCHAR2("+maxLength+"),";
					}
				}
			}
			}
			createTableSql="CREATE TABLE "+tableName+"("+createTableSql
					+" PRIMARY KEY (ID)"
					+ ")";
			String indexSql = "CREATE  INDEX "+tableName+"_INDEX ON "+tableName+" (RUN_ID)";
			ps= conn.prepareStatement(createTableSql);
			ps.execute();
			ps= conn.prepareStatement(indexSql);
			ps.execute();
			ps.close();
			DbOp dbop = new DbOp();
			try {
				dbop.cretateSeqTg(tableName, conn);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
