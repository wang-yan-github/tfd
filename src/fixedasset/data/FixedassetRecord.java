package fixedasset.data;

import java.util.Date;

public class FixedassetRecord {
	private int id;
	private String seqId;
	private String assetId;
	private String flowMsg;
	private Date registrationDate;
	private String registrationUser;
	private String recordType;
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
	public String getFlowMsg() {
		return flowMsg;
	}
	public void setFlowMsg(String flowMsg) {
		this.flowMsg = flowMsg;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationUser() {
		return registrationUser;
	}
	public void setRegistrationUser(String registrationUser) {
		this.registrationUser = registrationUser;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

}
