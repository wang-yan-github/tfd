package tfd.system.folder.data;

public class FileRecord {
	private int id;
	private String recordId;
	private String recordNo;
	private String attachId;
	private String recordTime;
	private String accountId;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public FileRecord(int id, String recordId, String recordNo,
			String attachId, String recordTime, String accountId, String orgId) {
		super();
		this.id = id;
		this.recordId = recordId;
		this.recordNo = recordNo;
		this.attachId = attachId;
		this.recordTime = recordTime;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	public FileRecord() {
		super();
	}
	
}
