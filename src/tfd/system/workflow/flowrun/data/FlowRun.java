package tfd.system.workflow.flowrun.data;


public class FlowRun {
	private int Id;
	private String runId;
	private String title;
	private String flowId;
	private String beginTime;
	private String endTime;
	private String delFlag;
	private String opUserStr;
	private String status;
	private String module;
	private String orgId;
	private String path;
	private String attachId;
	private String waitPrcsId;
	private String parentWait;
	private String flowLeave;
	private String createUser;
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getFlowLeave() {
		return flowLeave;
	}
	public void setFlowLeave(String flowLeave) {
		this.flowLeave = flowLeave;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getWaitPrcsId() {
		return waitPrcsId;
	}
	public void setWaitPrcsId(String waitPrcsId) {
		this.waitPrcsId = waitPrcsId;
	}
	public String getParentWait() {
		return parentWait;
	}
	public void setParentWait(String parentWait) {
		this.parentWait = parentWait;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getRunId() {
		return runId;
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpUserStr() {
		return opUserStr;
	}
	public void setOpUserStr(String opUserStr) {
		this.opUserStr = opUserStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
