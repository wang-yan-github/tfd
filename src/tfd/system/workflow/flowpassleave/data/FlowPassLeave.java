package tfd.system.workflow.flowpassleave.data;

public class FlowPassLeave {
	private int Id=0;
	private String flowPassLeaveId="";
	private String passLeaveId="";
	private String passLeaveName="";
	private String passLeaveNext="";
	private String flowId="";
	private String orgId="";
	public String getFlowPassLeaveId() {
		return flowPassLeaveId;
	}
	public void setFlowPassLeaveId(String flowPassLeaveId) {
		this.flowPassLeaveId = flowPassLeaveId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPassLeaveId() {
		return passLeaveId;
	}
	public void setPassLeaveId(String passLeaveId) {
		this.passLeaveId = passLeaveId;
	}
	public String getPassLeaveName() {
		return passLeaveName;
	}
	public void setPassLeaveName(String passLeaveName) {
		this.passLeaveName = passLeaveName;
	}
	public String getPassLeaveNext() {
		return passLeaveNext;
	}
	public void setPassLeaveNext(String passLeaveNext) {
		this.passLeaveNext = passLeaveNext;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
