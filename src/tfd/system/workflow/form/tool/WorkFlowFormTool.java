package tfd.system.workflow.form.tool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.workflow.flowformitem.data.FlowFormItem;
import tfd.system.workflow.flowutility.UitilityMysqlTool;
import tfd.system.workflow.flowutility.UitilityOracleTool;

import com.system.db.DbPoolConnection;


public class WorkFlowFormTool {
	public List<FlowFormItem> getFlowFormItemList(String code){
		String value="";
		FlowFormItem tmpitem = new FlowFormItem();
		List<FlowFormItem> itemList = new ArrayList<FlowFormItem>();
		List<String> inputList = new ArrayList<String>();
		
		List<String> reList = new ArrayList<String>();
		reList.add("<input(.*?)/>+");//input 标签处理正则
		reList.add("<select(.*?)></select>");//>select标签处理正则
		reList.add("<textarea(.*?)</textarea>");//textarea标签处理正则
		reList.add("<img(.*?)/>+");//列表标签处理正则
		
		for (int i=0;i<reList.size();i++){
			Pattern p = Pattern.compile(reList.get(i).toString());
	        Matcher m = p.matcher(code);
	        while(m.find()){
	    	   inputList.add(m.group());
	        }
		}
		

		Map<String, Integer> itemId=new HashMap<String, Integer>();
		int itemIndex=0;
		for(int k=0;inputList.size()>k;k++){
			FlowFormItem flowFormItem = new FlowFormItem();
			
			String fieldnamere ="fieldname=\"(.*?)\"";  
			Pattern pfieldnamep = Pattern.compile(fieldnamere);
	        Matcher pfieldnamem = pfieldnamep.matcher(inputList.get(k).toString());
		    while(pfieldnamem.find()){
		        value=pfieldnamem.group().replace("\"", "").split("=")[1].toString();
		        flowFormItem.setFieldName(value);
			}
		    
			String datatypere ="datatype=\"(.*?)\"";  
			Pattern datatypep = Pattern.compile(datatypere);
		    Matcher datatypem = datatypep.matcher(inputList.get(k).toString());
			while(datatypem.find()){
		        value=datatypem.group().replace("\"", "").split("=")[1].toString();
		        flowFormItem.setDataType(value);
			}
			
			String maxlengthre ="maxlength=\"(.*?)\"";  
			Pattern maxlengthp = Pattern.compile(maxlengthre);
			Matcher maxlengthm = maxlengthp.matcher(inputList.get(k).toString());
			while(maxlengthm.find()){
		        value=maxlengthm.group().replace("\"", "").split("=")[1].toString();
		        flowFormItem.setMaxLength(value);
			}
			
			String xtypere ="xtype=\"(.*?)\"";  
			Pattern xtypep = Pattern.compile(xtypere);
			Matcher xtypem = xtypep.matcher(inputList.get(k).toString());
			 while(xtypem.find()){
			 	value=xtypem.group().replace("\"", "").split("=")[1].toString();
			 	flowFormItem.setXtype(value);
			 	if(value.equals("xlist")){
			 		String childtablere ="childtable=\"(.*?)\"";  
					Pattern childtablep = Pattern.compile(childtablere);
			        Matcher childtablem = childtablep.matcher(inputList.get(k).toString());
				    while(childtablem.find()){
				       value=childtablem.group().replace("\"", "").split("=")[1].toString();
				       flowFormItem.setFieldName(value);
				    }				 		
			 	}else if(value.equals("xsql")){
			 		JSONObject json = new JSONObject();
			 		String sql ="sql=\"(.*?)\"";  
					Pattern sqlep = Pattern.compile(sql);
			        Matcher sqlem = sqlep.matcher(inputList.get(k).toString());
					while (sqlem.find()) {
						value = sqlem.group().replace("\"", "");
						value = value.substring(value.indexOf("=") + 1,value.length());
						json.accumulate("sql", value);
					}
				    String dbsourceid ="dbsourceid=\"(.*?)\"";  
					Pattern dbsourceidep = Pattern.compile(dbsourceid);
				    Matcher dbsourceidem = dbsourceidep.matcher(inputList.get(k).toString());
					while (dbsourceidem.find()) {
						value = dbsourceidem.group().replace("\"", "").split("=")[1].toString();
						json.accumulate("dbsourceid", value);
					}
					flowFormItem.setModel(json.toString());
			 	}
			}
			 
			String titlere ="title=\"(.*?)\"";  
			Pattern titlep = Pattern.compile(titlere);
			Matcher titlem = titlep.matcher(inputList.get(k).toString());
			while(titlem.find()){
				value=titlem.group().replace("\"", "").split("=")[1].toString();
				flowFormItem.setTitle(value);
			}
			
			String model ="model=\"(.*?)\"";  
			Pattern modelp = Pattern.compile(model);
			Matcher modelm = modelp.matcher(inputList.get(k).toString());
			while(modelm.find()){
				value=modelm.group().replace("\"", "").split("=")[1].toString();
				value=value.replaceAll("&quot;", "\"");
				flowFormItem.setModel(value);
			}
			
			String idre ="id=\"(.*?)\"";  
			Pattern idp = Pattern.compile(idre);
			Matcher idm = idp.matcher(inputList.get(k).toString());
			while(idm.find()){
				value=idm.group().replace("\"", "").split("=")[1].toString();
				flowFormItem.setFormItemId(value);
			}
			
			String childTablere ="childtable=\"(.*?)\"";  
			Pattern childTablep = Pattern.compile(childTablere);
			Matcher childTablem = childTablep.matcher(inputList.get(k).toString());
			while(childTablem.find()){
				value=childTablem.group().replace("\"", "").split("=")[1].toString();
				flowFormItem.setChildTable(value);
			}
			
			if(itemId.containsKey(flowFormItem.getFieldName())){
				if (flowFormItem.getXtype().equals("xradio")||flowFormItem.getXtype().equals("xcheckbox")) {
					FlowFormItem singleSelect=itemList.get(itemId.get(flowFormItem.getFieldName()));
					
					JSONArray singleSelectModel=JSONArray.fromObject(singleSelect.getModel());
					singleSelectModel.add(JSONArray.fromObject(flowFormItem.getModel()).getJSONObject(0));
					
					singleSelect.setModel(singleSelectModel.toString());
				}
			}else{
				itemList.add(flowFormItem);
				itemId.put(flowFormItem.getFieldName(), itemIndex++);
			}
			
		}
		return itemList;
	}
	//流程表结构动态生成器
	public void createFormDateLogic(Connection conn,String formId,List<FlowFormItem> itemList) throws Exception{
		String dbType=DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("oracle")){
			UitilityOracleTool UOT= new UitilityOracleTool();
			UOT.createOracleTableLogic(conn, formId, itemList);
		}else if(dbType.equals("sqlserver")){
			
		}else if(dbType.equals("mysql")){
			UitilityMysqlTool UMT = new UitilityMysqlTool();
			UMT.createMysqlTableLogic(conn, formId, itemList);
		}
	}
	
	
}
