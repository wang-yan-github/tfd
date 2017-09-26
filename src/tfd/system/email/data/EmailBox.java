package tfd.system.email.data;

public class EmailBox {
	private int id;
	private String boxId;
	private String sortId;
	private String boxPid;
	private String boxName;
	private String accountId;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getSortId() {
		return sortId;
	}
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	public String getBoxPid() {
		return boxPid;
	}
	public void setBoxPid(String boxPid) {
		this.boxPid = boxPid;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
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
	public EmailBox(int id, String boxId, String boxPid, String boxName,
			String accountId, String orgId) {
		super();
		this.id = id;
		this.boxId = boxId;
		this.boxPid = boxPid;
		this.boxName = boxName;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	public EmailBox() {
		super();
	}
}
