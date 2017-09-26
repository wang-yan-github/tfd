package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import tfd.system.workflow.flowformitem.data.FlowFormItem;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class UitilityMysqlTool {
	BaseDao dao=new BaseDaoImpl();
	public void createMysqlTableLogic(Connection conn,String formId,List<FlowFormItem> itemList) throws Exception{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ArrayList<String> oldFIeldList = new ArrayList<String>();
			WorkFlowFormLogic workFLowFormLogic = new WorkFlowFormLogic();
			UitilityMysqlXLTool umxlTool = new UitilityMysqlXLTool();
			
			String tableName=workFLowFormLogic.getFormTableNameByFormIdLogic(conn, formId).toUpperCase();
			String createTableSql="ID int(11) NOT NULL auto_increment,"
								  + "RUN_ID VARCHAR(50), "
								  + "CHILD_RUN_ID VARCHAR(200),"
								  + "PARENT_RUN_ID VARCHAR(50),";
			rs=conn.getMetaData().getTables(null, null, tableName, null);
			//表单对应的表是否存在
			if (rs.next()){
				//收集已有的字段
				ps=conn.prepareStatement("SELECT * FROM "+tableName + " LIMIT 1");
				rs=ps.executeQuery();
				ResultSetMetaData data = rs.getMetaData();
				if(data.getColumnCount()>0){
					for(int i=1; data.getColumnCount()>=i;i++){
						oldFIeldList.add(data.getColumnName(i).toUpperCase());
					}
				}
				
				
				for(int i=0;itemList.size()>i;i++){
					String fieldName=itemList.get(i).getFieldName().toUpperCase();
					String dataType=itemList.get(i).getDataType();
					String maxLength=itemList.get(i).getMaxLength();
					String xtype=itemList.get(i).getXtype();
					
					if (oldFIeldList.contains(fieldName)) {
						continue;
					}
					
					String sql="";
					//列表控件
					if(xtype.equals("xlist")){
						umxlTool.XListTool(conn, itemList.get(i).getFieldName(), itemList.get(i).getModel());
						sql="ALTER TABLE "+tableName+" ADD "+fieldName+" text (50)";
					}else {
						if(dataType.equals("text")){
							sql="ALTER TABLE "+tableName+" ADD "+fieldName+" "+dataType;
						}else{
							if(
									SysTool.isNullorEmpty(maxLength)
									||!SysTool.isInteger(maxLength)
									||Integer.parseInt(maxLength)<1
							 ){
								maxLength="10";
							}
							
							sql="ALTER TABLE "+tableName+" ADD "+fieldName+" "+dataType+"("+maxLength+")";
						}
					}
					
					ps= conn.prepareStatement(sql);
					ps.execute();
					oldFIeldList.add(fieldName);	
				}
			}else  {
				for(int i=0;itemList.size()>i;i++){
					String filedName=itemList.get(i).getFieldName().toUpperCase();
					String dataType=itemList.get(i).getDataType();
					String maxLength=itemList.get(i).getMaxLength();
					String xtype=itemList.get(i).getXtype();
					
					//列表控件
					if(xtype.equals("xlist")){
						umxlTool.XListTool(conn, itemList.get(i).getFieldName(), itemList.get(i).getModel());
						createTableSql+=filedName+" text(50),";
					}else{
						if(dataType.equals("text")){
							createTableSql=createTableSql+filedName+" "+dataType+",";
						}else{	
							if(
									SysTool.isNullorEmpty(maxLength)
									||!SysTool.isInteger(maxLength)
									||Integer.parseInt(maxLength)<1
							 ){
								maxLength="10";
							}
							createTableSql=createTableSql+filedName+" "+dataType+"("+maxLength+"),";
						}
					}
				}
				
				createTableSql="CREATE TABLE "+tableName+"("+createTableSql+" KEY ID (ID))";
				ps= conn.prepareStatement(createTableSql);
				ps.execute();
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
	}
}
