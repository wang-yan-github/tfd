package fixedasset.logic;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.PageTool;
import com.system.tool.AutoConvertUtil;
import com.system.tool.GuId;

import fixedasset.data.FixedassetAttr;
import fixedasset.data.FixedassetRelation;

public class FixedassetLogic {
	private FixedassetAttrLogic fixedassetAttrLogic=new FixedassetAttrLogic();
	private FixedassetRelationLogic fixedassetRelationLogic=new FixedassetRelationLogic();
	private AutoConvertUtil autoConvertUtil=new AutoConvertUtil();
	public void add(Map<String,String[]> requestMap,Connection conn) throws Exception{
		Map<String,Map<String,String[]>> form=new HashMap<String, Map<String,String[]>>();
		form.put(FixedassetAttr.class.getSimpleName(), new HashMap<String, String[]>());
		form.put(FixedassetRelation.class.getSimpleName(), new HashMap<String, String[]>());
		
		for(String fieldName:requestMap.keySet()){
			String entity=fieldName.split("_")[0];
			String inputName=fieldName.split("_")[1];
			if (form.containsKey(entity)) {
				form.get(entity).put(inputName, requestMap.get(fieldName));
			}
		}
		
		FixedassetAttr fixedassetAttr=(FixedassetAttr) autoConvertUtil.formToEntity(FixedassetAttr.class, form.get(FixedassetAttr.class.getSimpleName()));
		fixedassetAttr.setSeqId(GuId.getGuid());
		fixedassetAttrLogic.add(fixedassetAttr, conn);
		
		FixedassetRelation fixedassetRelation=(FixedassetRelation) autoConvertUtil.formToEntity(FixedassetRelation.class, form.get(FixedassetRelation.class.getSimpleName()));
		fixedassetRelation.setSeqId(GuId.getGuid());
		fixedassetRelation.setAssetId(fixedassetAttr.getSeqId());
		fixedassetRelationLogic.add(fixedassetRelation, conn);
	}
	public JSONObject assetList(Map<String,String[]> requestMap,Connection conn) throws Exception{
		JSONObject assetList=null;
		
		String sql="select asset_no,asset_name,unit_price from fixedasset_attr";
		
		Page page=PageUtil.toEasyuiPage(requestMap);
		PageTool pageTool=new PageTool();
		assetList= pageTool.getPageData(conn, sql, page);
		
		return assetList;
	}
}
