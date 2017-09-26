package fixedasset.data;

import net.sf.json.JSONObject;

public enum FixedassetsRecordType {
	MANUAL_ADD("0","手动修改"),
	WORKFLOW_ADD("1","走流程入库"),
	MANUAL_MODIFY("2","手动修改"),
	WORKFLOW_REPAIR("3","走流程维修"),
	WORKFLOW_TRANSFER("4","走流程调拨"),
	WORKFLOW_SCRAP("5","走流程报废");
	
	private String value;
	private String desc;
	private FixedassetsRecordType(String value,String desc){
		this.value=value;
		this.desc=desc;
	}
	public static JSONObject toJson(){
		JSONObject type=new JSONObject();
		for (FixedassetsRecordType t : FixedassetsRecordType.values()) {
			type.accumulate(t.value, t.desc);
		}
		return type;
	}
}
