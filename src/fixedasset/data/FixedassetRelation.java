package fixedasset.data;

import java.util.Date;

public class FixedassetRelation {
	private int id;
	private String seqId;
	private String assetId;
	private String assetType;
	private String assetSource;
	private String useDept;
	private String useSituation;
	private String storageLocation;
	private String useUser;
	private int number;
	private Date postingDate;
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
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetSource() {
		return assetSource;
	}
	public void setAssetSource(String assetSource) {
		this.assetSource = assetSource;
	}
	public String getUseDept() {
		return useDept;
	}
	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}
	public String getUseSituation() {
		return useSituation;
	}
	public void setUseSituation(String useSituation) {
		this.useSituation = useSituation;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getUseUser() {
		return useUser;
	}
	public void setUseUser(String useUser) {
		this.useUser = useUser;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
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
