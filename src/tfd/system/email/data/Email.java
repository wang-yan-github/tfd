package tfd.system.email.data;

public class Email {
	private int id;
	private String emailId;
	private String toId;
	private char readFlag;
	private char deleteFlag;
	private String boxId;
	private String bodyId;
	private char receipt;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public char getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(char readFlag) {
		this.readFlag = readFlag;
	}
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getBodyId() {
		return bodyId;
	}
	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}
	public char getReceipt() {
		return receipt;
	}
	public void setReceipt(char receipt) {
		this.receipt = receipt;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Email(int id, String emailId, String toId, char readFlag,
			char deleteFlag, String boxId, String bodyId, char receipt,
			String orgId) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.toId = toId;
		this.readFlag = readFlag;
		this.deleteFlag = deleteFlag;
		this.boxId = boxId;
		this.bodyId = bodyId;
		this.receipt = receipt;
		this.orgId = orgId;
	}
	public Email() {
		super();
	}
	public Email(String emailId, String toId, char readFlag, char deleteFlag,
			String boxId, String bodyId, char receipt, String orgId) {
		super();
		this.emailId = emailId;
		this.toId = toId;
		this.readFlag = readFlag;
		this.deleteFlag = deleteFlag;
		this.boxId = boxId;
		this.bodyId = bodyId;
		this.receipt = receipt;
		this.orgId = orgId;
	}
	
	
}