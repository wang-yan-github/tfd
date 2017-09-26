package fixedasset.logic;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.GuId;

import fixedasset.data.FixedassetType;
import fixedasset.data.TypeTree;

public class FixedassetTypeLogic {
	private BaseDao dao=new BaseDaoImpl();
	public void add(FixedassetType fixedassetType,Connection conn) throws Exception{
		String sql="insert into fixedasset_type(level_id,net_salvage,parent_id,seq_id,type_name,unit) values(?,?,?,?,?,?)";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetType.getLevelId(),
				fixedassetType.getNetSalvage(),
				fixedassetType.getParentId(),
				fixedassetType.getSeqId(),
				fixedassetType.getTypeName(),
				fixedassetType.getUnit()
		});
	}
	public void delete(String levelId,Connection conn) throws Exception{
		String sql="delete from fixedasset_type where level_id like ?";
		dao.excuteSQL(conn, sql, new Object[]{
				levelId+"%"
		});
	}
	public void update(FixedassetType fixedassetType,Connection conn) throws Exception{
		String sql="update fixedasset_type set level_id=?,net_salvage=?,parent_id=?,type_name=?,unit=? where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetType.getLevelId(),
				fixedassetType.getNetSalvage(),
				fixedassetType.getParentId(),
				fixedassetType.getTypeName(),
				fixedassetType.getUnit(),
				fixedassetType.getSeqId()
		});
	}
	
	/**
	 * 
	 * @param requestMap{parentId:,type:"tree|parentTree"}
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public JSONArray typeTree(Map<String,String[]> requestMap,Connection conn) throws Exception{
		JSONArray data=new JSONArray();;
		
		String parentId="0";
		if (requestMap.get("parentId")!=null) {
			parentId=requestMap.get("parentId")[0];
		}
		
		String type="tree";
		if (requestMap.get("type")!=null) {
			type=requestMap.get("type")[0];
		}
		
		String sql="select seq_id,level_id,type_name,unit,net_salvage from fixedasset_type where parent_id=? order by type_name";
		List typeList=dao.getListAll(conn, sql, new Object[]{
				parentId
		});
		
		if (type.equals("parentTree")&&parentId.equals("0")) {
			TypeTree tree=new TypeTree();
			tree.setId(parentTopValue());
			tree.setText(parentTopText());
			tree.setLevelId(parentTopLevel());
			data.add(tree);
		}
		
		if (typeList!=null&&typeList.size()>0) {
			for (Object o : typeList) {
				Map typeMap=(Map) o;
				
				String seqId=typeMap.get("seq_id").toString();
				String levelId=typeMap.get("level_id").toString();
				String typeName=typeMap.get("type_name").toString();
				String unit=typeMap.get("unit")==null?null:typeMap.get("unit").toString();
				double netSalvage=typeMap.get("net_salvage")==null?0:Double.parseDouble(typeMap.get("net_salvage").toString());
				
				TypeTree tree=new TypeTree();
				tree.setId(seqId);
				tree.setPid(parentId);
				tree.setText(typeName);
				tree.setLevelId(levelId);
				tree.setIsParent(haveChildren(seqId, conn));
				tree.setUnit(unit);
				tree.setNetSalvage(netSalvage);
				
				data.add(tree);
			}
		}
		return data;
	}
	public boolean haveChildren(String seqId,Connection conn)throws Exception{
		boolean haveChildren=false;
		String sql="select count(1) child_count from fixedasset_type where parent_id=?";
		Map dataMap=dao.getMapById(conn, sql,new Object[]{
				seqId
		});
		if (dataMap!=null&&dataMap.size()>0) {
			haveChildren=Integer.parseInt(dataMap.get("child_count").toString())>0;
		}
		return haveChildren;
	}
	public String parentTopLevel(){
		return "0";
	}
	public String parentTopText(){
		return "资产类别";
	}
	public String parentTopValue(){
		return "0";
	}
	
	
	
	public Map<String,String> template(){
		Map<String,String> template=new LinkedHashMap<String, String>();
		template.put("序号", "不可为空且须为从1自增的数字");
		template.put("类别名称", "不可为空");
		template.put("父级", "填写相应的类别序号，没有请填0");
		template.put("计量单位", "多个请用‘|’分割");
		template.put("净残值率", "小于1的小数");
		return template;
	}
	public Map<String,String> templateField(){
		Map<String,String> template=new LinkedHashMap<String, String>();
		template.put("id","序号");
		template.put("typeName","类别名称");
		template.put("parent","父级");
		template.put("unit","计量单位");
		template.put("netSalvage","净残值率");
		return template;
	}
	
	public List<Record> getImportRecords(InputStream inputStream) throws Exception{
		List<Record> records=new ArrayList<Record>();
		
		Map<String,List<Record>> recordsMap=ExcelUtil.readExcel(inputStream, true);
		
		List<Record> importRecords=null;
		for(String sheet:recordsMap.keySet()){
			if (recordsMap.get(sheet).size()>0) {
				importRecords=recordsMap.get(sheet);
				break;
			}
		}
		
		for (int i = 0; i < importRecords.size(); i++) {
			Record ir=importRecords.get(i);
			Record r=new Record();
			for (int j = 0; j < importRecords.get(i).getFieldCnt(); j++) {
				r.addField(ir.getNameByIndex(j).trim(), ir.getValueByIndex(j).toString().trim());
			}
			records.add(r);
		}
		
		return records;
	}
	
	public Map<String,List<String>> importRecordsCheck(List<Record> records){
		Map<String,List<String>> checkResult=new LinkedHashMap<String, List<String>>();
		
		Map<String,String> template=this.template();
		
		String CHECK_TYPE_FIELD="模板字段检查";
		String CHECK_TYPE_CONTENT="模板内容检查";
		checkResult.put(CHECK_TYPE_FIELD, new ArrayList<String>());
		checkResult.put(CHECK_TYPE_CONTENT, new ArrayList<String>());
		
		//1.
		Record templateField=records.get(0);
		for(String fieldName:template.keySet()){
			boolean check=templateField.nameMatch(fieldName);
			if (!check) {
				checkResult.get(CHECK_TYPE_FIELD).add("缺失字段："+fieldName);
			}
		}
		
		//2.
		// 2.1 template.put("序号", "不可为空且须为从1自增的数字");
		// 2.2 template.put("类别名称", "不可为空");
		// 2.3 template.put("父级", "填写相应的类别序号，没有请填0");
		// 2.4 template.put("计量单位", "多个请用‘|’分割");
		// 2.5 template.put("净残值率", "小于1的小数");
		
		String FIELD_ID="id";
		String FIELD_TYPE_NAME="typeName";
		String FIELD_PARENT="parent";
		String FIELD_UNIT="unit";
		String FIELD_NET_SALVAGE="netSalvage";
		
		List<String> ids=new ArrayList<String>();
		
		Map<String,String> templateFieldMap=this.templateField();
		if (checkResult.get(CHECK_TYPE_FIELD).size()==0) {
			
			for (int i = 0; i < records.size(); i++) {
				String id=records.get(i).getValueByName(templateFieldMap.get(FIELD_ID)).toString();
				String typeName=records.get(i).getValueByName(templateFieldMap.get(FIELD_TYPE_NAME)).toString();
				String parent=records.get(i).getValueByName(templateFieldMap.get(FIELD_PARENT)).toString();
				String unit=records.get(i).getValueByName(templateFieldMap.get(FIELD_UNIT)).toString();
				String netSalvage=records.get(i).getValueByName(templateFieldMap.get(FIELD_NET_SALVAGE)).toString();
				
				String errorLocation="错误位置：第"+(i+1)+"行";
				String error=null;
				
				//2.1
				if (id.equals("")) {
					error=errorLocation+","+templateFieldMap.get(FIELD_ID)+"不可为空！";
				}else if(ids.contains(id)){
					error=errorLocation+","+templateFieldMap.get(FIELD_ID)+"不可重复！";
				}else{
					try {
						Integer.parseInt(id);
						ids.add(id);
					} catch (Exception e) {
						error=errorLocation+","+templateFieldMap.get(FIELD_ID)+"必须为数字！";
					}
				}
				if (error!=null) {
					checkResult.get(CHECK_TYPE_CONTENT).add(error);
					error=null;
				}
				
				//2.2
				if (typeName.equals("")) {
					error=errorLocation+","+templateFieldMap.get(FIELD_TYPE_NAME)+"不可为空！";
				}
				if (error!=null) {
					checkResult.get(CHECK_TYPE_CONTENT).add(error);
					error=null;
				}
				
				
				
				//2.3
				boolean parentCheck=false;
				if (parent.equals("0")) {
					parentCheck=true;
				}else{
					for (int k = 0; k < records.size(); k++) {
						String idTemp=records.get(k).getValueByName(templateFieldMap.get(FIELD_ID)).toString();
						if (idTemp.equals(parent)) {
							parentCheck=true;
							break;
						}
						if (parentCheck) {
							break;
						}
					}
				}
				if (!parentCheck) {
					error=errorLocation+","+templateFieldMap.get(FIELD_PARENT)+"对应的"+templateFieldMap.get(FIELD_ID)+"不存在！";
				}
				if (error!=null) {
					checkResult.get(CHECK_TYPE_CONTENT).add(error);
					error=null;
				}
				
				//2.5
				if (!netSalvage.equals("")) {
					try {
						Double.parseDouble(netSalvage);
					} catch (Exception e) {
						error=errorLocation+","+templateFieldMap.get(FIELD_NET_SALVAGE)+"必须为小于1的数字！";
					}
				}
				if (error!=null) {
					checkResult.get(CHECK_TYPE_CONTENT).add(error);
					error=null;
				}
			}
		}
		return checkResult;
	}
	public void importRecords(List<Record> records,Connection conn) throws Exception{
		
		Map<String,String> templateFieldMap=this.templateField();
		
		String FIELD_ID="id";
		String FIELD_TYPE_NAME="typeName";
		String FIELD_PARENT="parent";
		String FIELD_UNIT="unit";
		String FIELD_NET_SALVAGE="netSalvage";
		
		FixedassetType aRoot=new FixedassetType();
		aRoot.setLevelId(this.parentTopLevel());
		aRoot.setId(Integer.parseInt(this.parentTopValue())); 
		aRoot.setSeqId(this.parentTopValue()); 
		
		List<FixedassetType> roots=new ArrayList<FixedassetType>();
		roots.add(aRoot);
		//3.
		// 3.0 移除第一行标题栏
		// 3.1 查找所有父级为root的记录
		// 3.2 移除以查找的记录
		// 3.3 查找的记录生成相应的实体
		// 3.4 实体插入数据库
		// 3.5将所有查找的记录作为新的父级 3.1开始循环
		
		//3.0
		for (; ;) {
			List<FixedassetType> newRoots=new ArrayList<FixedassetType>();
			if (roots.size()==0) {
				break;
			}
			for (int h = 0; h < roots.size(); h++) {
				FixedassetType root=roots.get(h);
				
				List<Record> recordsTemp=new ArrayList<Record>();
				List<Integer> removeIndex=new ArrayList<Integer>();
				
				for (int i = 0; i < records.size(); i++) {
					String parent=records.get(i).getValueByName(templateFieldMap.get(FIELD_PARENT)).toString();
					if (parent.equals(root.getId()+"")) {
						recordsTemp.add(records.get(i));
						removeIndex.add(i);
					}
				}
				
				for (int i = removeIndex.size()-1; i >= 0; i--) {
					records.remove(removeIndex.get(i));
				}
				
				for (int i = 0; i < recordsTemp.size(); i++) {
					String id=recordsTemp.get(i).getValueByName(templateFieldMap.get(FIELD_ID)).toString();
					String typeName=recordsTemp.get(i).getValueByName(templateFieldMap.get(FIELD_TYPE_NAME)).toString();
					String parent=recordsTemp.get(i).getValueByName(templateFieldMap.get(FIELD_PARENT)).toString();
					String unit=recordsTemp.get(i).getValueByName(templateFieldMap.get(FIELD_UNIT)).toString();
					String netSalvage=recordsTemp.get(i).getValueByName(templateFieldMap.get(FIELD_NET_SALVAGE)).toString();
					netSalvage=netSalvage.equals("")?"0":netSalvage;
					
					String seqId=GuId.getGuid();
					String levelId=root.getLevelId()+"."+seqId;
					String parentId=root.getSeqId();
					
					FixedassetType type=new FixedassetType();
					
					type.setId(Integer.parseInt(id));
					type.setLevelId(levelId);
					type.setNetSalvage(Double.parseDouble(netSalvage));
					type.setParentId(parentId);
					type.setSeqId(seqId);
					type.setTypeName(typeName);
					type.setUnit(unit);
					
					this.add(type, conn);
					
					newRoots.add(type);
				}
			}
			
			roots=new ArrayList<FixedassetType>();
			for (int i = 0; i < newRoots.size(); i++) {
				roots.add(newRoots.get(i));
			}
		}
	}
	
	public List<Record> getExportRecords(Connection conn) throws Exception{
		List<Record> records=new ArrayList<Record>();

		Map<String,String> templateField=this.templateField();

		String FIELD_ID="id";
		String FIELD_TYPE_NAME="typeName";
		String FIELD_PARENT="parent";
		String FIELD_UNIT="unit";
		String FIELD_NET_SALVAGE="netSalvage";
		
		Record record=new Record();
		record.addField(templateField.get(FIELD_ID), templateField.get(FIELD_ID));
		record.addField(templateField.get(FIELD_TYPE_NAME), templateField.get(FIELD_TYPE_NAME));
		record.addField(templateField.get(FIELD_PARENT), templateField.get(FIELD_PARENT));
		record.addField(templateField.get(FIELD_UNIT), templateField.get(FIELD_UNIT));
		record.addField(templateField.get(FIELD_NET_SALVAGE), templateField.get(FIELD_NET_SALVAGE));
		
		records.add(record);
		
		String sql="select ";
		sql+="			id,";
		sql+="			type_name,";
		sql+="			(SELECT id FROM fixedasset_type WHERE seq_id=ft.parent_id) parent,";
		sql+="			unit,";
		sql+="			net_salvage";
		sql+="			from fixedasset_type ft order by id";
		List dataList=dao.getListAll(conn, sql);
		if (dataList!=null&&dataList.size()>0) {
			for(Object object:dataList){
				Map dataMap=(Map) object;
				String id=dataMap.get("id").toString();
				String typeName=dataMap.get("type_name").toString();
				String parent=dataMap.get("parent")==null?this.parentTopValue():dataMap.get("parent").toString();
				String unit=dataMap.get("unit")==null?null:dataMap.get("unit").toString();
				String netSalvage=dataMap.get("net_salvage")==null?null:dataMap.get("net_salvage").toString();
				
				record=new Record();
				record.addField(templateField.get(FIELD_ID), id);
				record.addField(templateField.get(FIELD_TYPE_NAME), typeName);
				record.addField(templateField.get(FIELD_PARENT), parent);
				record.addField(templateField.get(FIELD_UNIT), unit);
				record.addField(templateField.get(FIELD_NET_SALVAGE), netSalvage);
				
				records.add(record);
			}
		}
		return records;
	}
}
