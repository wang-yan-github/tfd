package tfd.system.workflow.workflownext.tool;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class WorkFlowDataTool {
	//解析表单参数
	public Map<String,String> getDataMap(String flowData,String[] ueditArr,String [] xsql) throws Exception
	{
		Map <String,String> returnMap = new HashMap<String,String>();
		String [] tmpFlowData=flowData.split("&");
		String [] tmpMapStr=null;
		for (int i = 0; tmpFlowData.length>i;i++)
		{
			tmpMapStr= tmpFlowData[i].split("=");
			if(tmpMapStr.length==2){
				returnMap.put(tmpMapStr[0], tmpMapStr[1]);
			}else
			{
				returnMap.put(tmpMapStr[0],"");
			}
		}
		if(ueditArr!=null)
		{
			Map <String,String> ueditMap = this.getUeditDataMap(ueditArr);
			if(!ueditMap.equals(null))
			{
				returnMap.putAll(ueditMap);
			}
		}
		if(xsql!=null)
		{
			Map <String,String> xsqlMap = this.getXsqlDataMap(xsql);
			if(!xsqlMap.equals(null))
			{
				returnMap.putAll(xsqlMap);
			}
		}
		return returnMap;
	}
	//解析富文本字段值
	public Map<String,String> getUeditDataMap(String[] ueditArr) throws Exception
	{
		Map <String,String> returnMap = new HashMap<String,String>();
		for(int i=0;ueditArr.length>i;i++)
		{
			new JSONObject();
			JSONObject json = JSONObject.fromObject(ueditArr[i]);
			returnMap.put(json.getString("fieldName"), URLDecoder.decode(json.getString("content"),"utf-8"));
		}
		return returnMap;
		
	}
	//处理XSQL字段
	public Map<String,String> getXsqlDataMap(String[] xsql) throws Exception
	{
		Map <String,String> returnMap = new HashMap<String,String>();
		for(int i=0;xsql.length>i;i++)
		{
			new JSONObject();
			JSONObject json = JSONObject.fromObject(xsql[i]);
			returnMap.put(json.getString("fieldName"), URLDecoder.decode(json.getString("content"),"utf-8"));
		}
		return returnMap;
		
	}
	
}
