package tfd.system.mobile.workflow.logic;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkFlowDataTool {
	public Map<String,String> getDataMap(String formData)
	{
		Map <String,String> returnMap = new HashMap<String,String>();
		JSONArray formjson = new JSONArray().fromObject(formData);
		for(int i=0;formjson.size()>i;i++)
		{
			JSONObject tmpjson = new JSONObject();
			tmpjson =JSONObject.fromObject(formjson.get(i));
			//处理XLIST控件请取消注释
//			if(tmpjson.get("type").equals("xlist"))
//			{
//				
//			}else
//			{
			returnMap.put(tmpjson.getString("id"), tmpjson.getString("data"));
//			}
		}
		return returnMap;
	}
}
