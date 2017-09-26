package tfd.system.workflow.flowformitem.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.DbOp;
import com.system.tool.SysTool;

import tfd.system.workflow.flowformitem.data.FlowFormItem;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

public class FLowFormItemLogic {
	//生成字段对应关系
	public void insterFlowFormItemLogic(Connection conn,String formId,List<FlowFormItem> itemList) throws SQLException
	{
		boolean falg;
		PreparedStatement ps=null;
		FlowFormItem tempItem = new FlowFormItem();
		String delquery="DELETE FROM FLOW_FORM_ITEM WHERE FORM_ID='"+formId+"'";
		PreparedStatement delps=conn.prepareStatement(delquery);
		delps.executeUpdate();
		delps.close();
		for(int i=0;itemList.size()>i;i++ )
		{
			tempItem=itemList.get(i);
			falg=this.testFormItemLogic(conn, formId, tempItem.getFormItemId());
			if(falg)
			{
				continue;
			}
			String queryStr="INSERT INTO FLOW_FORM_ITEM (FORM_ID,FORM_ITEM_ID,TITLE,XTYPE,FIELD_NAME,DATA_TYPE,MAX_LENGTH,MODEl)VALUES(?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, tempItem.getFormItemId());
			ps.setString(3, tempItem.getTitle());
			ps.setString(4, tempItem.getXtype());
			ps.setString(5, tempItem.getFieldName());
			ps.setString(6, tempItem.getDataType());
			ps.setString(7, tempItem.getMaxLength());
			ps.setString(8, tempItem.getModel());
			ps.executeUpdate();
		}
		if(ps!=null)
		{
			ps.close();
		}
	}
	//判断FORMITEM是不是存在
	public boolean testFormItemLogic(Connection conn,String formId,String tempItem) throws SQLException
	{
			boolean returnData=false;
			String queryStr="SELECT COUNT(*) FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND FORM_ITEM_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, tempItem);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0)
				{
					returnData=true;
				}
			}
			rs.close();
			ps.close();
			return returnData;
	}
	//获取可写字段备选列表
	public String getAllFlowProcessFieldLogic(Connection conn,String flowId,int prcsId) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement ps=null;
		String query="";
		String writerFieldStr="";
		query="SELECT WRITER_FIELD FROM FLOW_PROCESS WHERE PRCS_ID=? AND FLOW_ID=?";
		ps=conn.prepareStatement(query);
		ps.setInt(1, prcsId);
		ps.setString(2, flowId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			writerFieldStr=rs.getString("WRITER_FIELD");
		}
		query="SELECT FIELD_NAME,TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND XTYPE<>'xlist'";
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		JSONArray jsonArr = new JSONArray();
		ps=conn.prepareStatement(query);
		ps.setString(1,flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowId, prcsId));
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			if(SysTool.isNullorEmpty(writerFieldStr)){
					json.accumulate("text", rs.getString("TITLE"));
					json.accumulate("id", rs.getString("FIELD_NAME"));
					jsonArr.add(json);
			}else{
				writerFieldStr=","+writerFieldStr+",";
				if(writerFieldStr.indexOf(","+rs.getString("FIELD_NAME")+",")<0)
				{
					json.accumulate("text", rs.getString("TITLE"));
					json.accumulate("id", rs.getString("FIELD_NAME"));
					jsonArr.add(json);
				}
			}
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	//获取可写字段名称
	public String getTitleBy(Connection conn,String formId,String fieldName) throws SQLException
	{
		PreparedStatement ps=null;
		ResultSet rs = null;
		String returnData="";
		String query="SELECT TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND FIELD_NAME=?";
		ps=conn.prepareStatement(query);
		ps.setString(1,formId);
		ps.setString(2,fieldName);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("TITLE");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	
	//获取所用的field
	public Map<String,Map<String,String>> getAllFlowFormItemByFormIdLogic(Connection conn,String formId) throws SQLException
	{
		Map<String,Map<String,String>> returnMap = new HashMap<String, Map<String,String>>(); 
		String queryStr="SELECT ID, FIELD_NAME,TITLE,XTYPE,MODEL FROM FLOW_FORM_ITEM WHERE FORM_ID=? ORDER BY ID DESC";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, formId);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			Map<String,String> fmap = new HashMap<String, String>();
			fmap.put("title", rs.getString("TITLE"));
			fmap.put("xtype", rs.getString("XTYPE"));
			fmap.put("model", rs.getString("MODEL"));
			returnMap.put(rs.getString("FIELD_NAME"), fmap);
		}
		rs.close();
		ps.close();
		return returnMap;
	}
	
	
	
	
	//获取查询SELECT项
		public String getAllFlowFormItemLogic(Connection conn,String flowId,int prcsId) throws SQLException
		{
			ResultSet rs = null;
			PreparedStatement ps=null;
			String query="";
			String fieldStr="";
			query="SELECT FIELD_NAME FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			ps=conn.prepareStatement(query);
			ps.setString(1,flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowId, prcsId));
			rs=ps.executeQuery();
			while(rs.next())
			{
				fieldStr=fieldStr+rs.getString("FIELD_NAME")+",";
			}
			if(fieldStr.length()>0)
			{
				fieldStr=fieldStr.substring(0, fieldStr.length()-1);
			}
			rs.close();
			ps.close();
			return fieldStr;
		}
		 //获取字段列表json
		public String getFieldByFormIdJsonLogic(Connection conn,String flowId,String prcsId) throws SQLException
		{
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId = workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
			int intPrcsId = Integer.parseInt(prcsId);
			JSONObject validatorJson = JSONObject.fromObject(flowProcessLogic.getValidatorLogic(conn, flowId, intPrcsId));
			String mustfield = ","+validatorJson.getString("mustfield")+",";
			String hiddenfield =","+validatorJson.getString("hiddenfield")+",";
			JSONArray jsonArr = new JSONArray();
			ResultSet rs = null;
			PreparedStatement ps=null;
			String query="";
			query="SELECT FIELD_NAME,TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND XTYPE<>'xlist'";
			ps=conn.prepareStatement(query);
			ps.setString(1,formId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
					if(mustfield.indexOf(","+rs.getString("FIELD_NAME")+",")>=0)
					{
						json.accumulate("mustfield", "1");
					}else
					{
						json.accumulate("mustfield", "0");
					}
					if(hiddenfield.indexOf(","+rs.getString("FIELD_NAME")+",")>=0)
					{
						json.accumulate("hiddenfield", "1");
					}else
					{
						json.accumulate("hiddenfield", "0");
					}
					json.accumulate("field", rs.getString("FIELD_NAME"));
					json.accumulate("title", rs.getString("TITLE"));
					jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}		
		

 //获取字段列表list
		public List<String> getFieldByFormIdList(Connection conn,String formId) throws SQLException
		{
			ResultSet rs = null;
			PreparedStatement ps=null;
			String query="";
			List<String> fieldList = new ArrayList<String>();
			query="SELECT FIELD_NAME FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
			ps=conn.prepareStatement(query);
			ps.setString(1,formId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(!SysTool.isNullorEmpty(rs.getString("FIELD_NAME")))
				{
					fieldList.add(rs.getString("FIELD_NAME"));
				}
			}
			rs.close();
			ps.close();
			return fieldList;
		}
//获取字段title and field_name 通过flowId
		public String getTitleAndFieldNameByFlowIdJson(Connection conn,String flowId) throws SQLException
		{
			FlowProcessLogic flowProcessLogic =  new FlowProcessLogic();
			String formId=flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowId,1);
			ResultSet rs = null;
			PreparedStatement ps=null;
			String query="";
			JSONArray jsonArr = new JSONArray();
			query="SELECT FIELD_NAME,TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
			ps=conn.prepareStatement(query);
			ps.setString(1,formId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.accumulate("fieldName", rs.getString("FIELD_NAME"));
				json.accumulate("title", rs.getString("TITLE"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
			
		}
//提取宏件
		public String getAutoFieldModelLogic(Connection conn,String fieldName,String flowId) throws SQLException
		{
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			String returnData="";
			String queryStr="SELECT MODEL FROM FLOW_FORM_ITEM WHERE FIELD_NAME=? AND XTYPE=? AND FORM_ID=?";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, fieldName);
			ps.setString(2, "xmacro");
			ps.setString(3, formId);
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				returnData=rs.getString("MODEl");
			}
			rs.close();
			ps.close();
			return returnData;
		}
//提取XSQL控件
		public String getXsqlLogic(Connection conn,String fieldName,String flowId) throws SQLException
		{
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			String returnData = "";
			String queryStr="SELECT MODEL FROM FLOW_FORM_ITEM WHERE FIELD_NAME=? AND XTYPE=? AND FORM_ID=?";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, fieldName);
			ps.setString(2, "xsql");
			ps.setString(3, formId);
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				returnData=rs.getString("MODEL");
			}
			rs.close();
			ps.close();
			return returnData;
		}
//提取文号控件
		public String getXDocNumLogic(Connection conn,String fieldName,String flowId) throws SQLException
		{
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			String returnData = "";
			boolean flag = false;
			String queryStr="SELECT COUNT(*) AS COUNT FROM FLOW_FORM_ITEM WHERE FIELD_NAME=? AND XTYPE=? AND FORM_ID=?";
			PreparedStatement ps=conn.prepareStatement(queryStr);
			ps.setString(1, fieldName);
			ps.setString(2, "xdocnum");
			ps.setString(3, formId);
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				if(!rs.getString("COUNT").equals("0"))
				{
					flag=true;
				}
			}
			if(flag)
			{
				queryStr="SELECT AUTO_CODE FROM WORK_FLOW WHERE FLOW_TYPE_ID=?";
				ps=conn.prepareStatement(queryStr);
				ps.setString(1, flowId);
				rs = ps.executeQuery();
				if(rs.next())
				{
					returnData=rs.getString("AUTO_CODE");
				}
			}
			rs.close();
			ps.close();
			return returnData;
			
		}
		
//获取所有Xlist控件json
		public String getAllXlistJsonLogic(Connection conn,String flowId) throws SQLException
		{
			JSONArray jsonArr = new JSONArray();
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			String queryStr="SELECT TITLE,FIELD_NAME,MODEL FROM FLOW_FORM_ITEM WHERE XTYPE=? AND FORM_ID=?";
			ResultSet rs = null;
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, "xlist");
			ps.setString(2, formId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.accumulate("title", rs.getString("TITLE"));
				json.accumulate("table", rs.getString("FIELD_NAME"));
				json.accumulate("model", rs.getString("MODEL"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
//获取指定XLIST控件
		public String getXlistJsonLogic(Connection conn,String flowId,String childTable) throws SQLException
		{
			String returnData="";
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			ResultSet rs =null;
			String queryStr="SELECT MODEL FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND FIELD_NAME=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, childTable);
			rs = ps.executeQuery();
			if(rs.next())
			{
				returnData=JSONArray.fromObject(rs.getString("MODEL")).toString();
			}
			return returnData;
		}
//获取指定FORM中的指定的FIELDNAME的module
		public String getModeLByFormIdAndFieldLogic(Connection conn,String formId,String fieldName) throws SQLException
		{
			String returnData="";
			String queryStr="SELECT MODEL FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND FIELD_NAME=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, fieldName);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				returnData=rs.getString("MODEL");
			}
			rs.close();
			ps.close();
			return returnData;
		}
//获取所有流程表单字段
		public String getAllFieldByFlowIdLogic (Connection conn,String flowId) throws SQLException
		{
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			String formId = workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			JSONArray jsonArr = new JSONArray();
			String queryStr="SELECT FIELD_NAME,TITLE FROM FLOW_FORM_ITEM WHERE FORM_ID=? AND XTYPE<>?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, "xlist");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.accumulate("id", rs.getString("FIELD_NAME"));
				json.accumulate("text", rs.getString("TITLE"));
				json.accumulate("desc", "");
				jsonArr.add(json);
			}
			return jsonArr.toString();
			
		}
//获取子表名称
		public List<String> getChildTableByFormIdLogic(Connection conn,String formId) throws SQLException
		{
			List<String> returnData = new ArrayList<String>();
			String queryStr="SELECT FIELD_NAME FROM FLOW_FORM_TIME WHERE FLOW_ID=? AND XTYPE=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			ps.setString(2, "xlist");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				returnData.add(rs.getString("FIELD_NAME"));
			}
			rs.close();
			ps.close();
			return returnData;
		}
		
//删除字段信息
		public int clearFormItemByFormIdLogic(Connection conn,String formId) throws SQLException
		{
			List<String> childTableList = this.getChildTableByFormIdLogic(conn, formId);
			if(!childTableList.equals(null))
			{
				DbOp dbOp = new DbOp();
				for(int i=0;childTableList.size()>i;i++)
				{
					dbOp.delTable(conn, childTableList.get(i));
				}
			}
			String queryStr="DELECT FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, formId);
			int i=ps.executeUpdate();
			ps.close();
			return i;
		}
		
		/**
		 * {title:{title1:"title1",title2:"title2"},fieldName:{fieldName1:"fieldName1"}}
		 * @param conn
		 * @param formId
		 * @return
		 * @throws SQLException
		 */
	public String getValidateByFormIdLogic(Connection conn, String formId) throws SQLException {
		JSONObject field = new JSONObject();
		JSONObject fieldTitle = new JSONObject();
		JSONObject fieldName = new JSONObject();
		JSONObject detail=new JSONObject();
		
		String queryStr = "SELECT TITLE,FIELD_NAME,XTYPE FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, formId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			fieldTitle.put(rs.getString("TITLE"), rs.getString("TITLE"));
			fieldName.put(rs.getString("FIELD_NAME"),rs.getString("FIELD_NAME"));
			
			JSONObject detailOne=new JSONObject();
			detailOne.put("title", rs.getString("TITLE"));
			detailOne.put("xtype", rs.getString("XTYPE"));
			detail.put(rs.getString("FIELD_NAME"),detailOne);
			
		}
		field.put("title", fieldTitle);
		field.put("fieldName", fieldName);
		field.put("detail", detail);
		return field.toString();
	}
}
