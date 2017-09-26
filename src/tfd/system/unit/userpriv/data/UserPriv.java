package tfd.system.unit.userpriv.data;

public class UserPriv {
	private int Id;
	private String userPrivId;
	private String userPrivLeave;
	public String getUserPrivLeave() {
		return userPrivLeave;
	}
	public void setUserPrivLeave(String userPrivLeave) {
		this.userPrivLeave = userPrivLeave;
	}
	private String userPrivName;
	private String privStr;
	private String orgId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserPrivId() {
		return userPrivId;
	}
	public void setUserPrivId(String userPrivId) {
		this.userPrivId = userPrivId;
	}
	public String getUserPrivName() {
		return userPrivName;
	}
	public void setUserPrivName(String userPrivName) {
		this.userPrivName = userPrivName;
	}
	public String getPrivStr() {
		return privStr;
	}
	public void setPrivStr(String privStr) {
		this.privStr = privStr;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
