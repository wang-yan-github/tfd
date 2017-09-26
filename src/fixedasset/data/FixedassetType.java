package fixedasset.data;

public class FixedassetType {
	private int id;
	private String seqId;
	private String parentId;
	private String levelId;
	private String typeName;
	private String unit;
	private double netSalvage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getNetSalvage() {
		return netSalvage;
	}
	public void setNetSalvage(double netSalvage) {
		this.netSalvage = netSalvage;
	}

}
