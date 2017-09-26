package tfd.system.workflow.monitor.data;

public class MonitorField {
	private String runId;
	private String flowTitle;
	private String flowName;
	private String beginUserId;
	private String status;
	public String getRunId() {
		return runId;
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	public String getFlowTitle() {
		return flowTitle;
	}
	public void setFlowTitle(String flowTitle) {
		this.flowTitle = flowTitle;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getBeginUserId() {
		return beginUserId;
	}
	public void setBeginUserId(String beginUserId) {
		this.beginUserId = beginUserId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
