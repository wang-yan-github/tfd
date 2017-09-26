package tfd.system.unit.userleave.data;

public class UserLeave {
	private int id=0; 
	private String leaveId="";
	private String leaveNoId="";
	private String midding="";
	public String getMidding() {
		return midding;
	}
	public void setMidding(String midding) {
		this.midding = midding;
	}
	private String leaveName="";
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	private String orgId="";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeaveNoId() {
		return leaveNoId;
	}
	public void setLeaveNoId(String leaveNoId) {
		this.leaveNoId = leaveNoId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
