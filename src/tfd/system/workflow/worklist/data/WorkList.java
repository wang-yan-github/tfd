package tfd.system.workflow.worklist.data;

public class WorkList {
	private int Id;
	private String title;
	private String runId;
	private String module;
	private String accountId;
	private String url;
	private String readUrl;
	private String status;
	private String createTime;
	private String endTime;
	private String orgId;
	private int prcsId;
	private int runPrcsId;
	private String prcsName;
	public String getPrcsName() {
		return prcsName;
	}
	public void setPrcsName(String prcsName) {
		this.prcsName = prcsName;
	}
	private String delFlag;
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public int getRunPrcsId() {
		return runPrcsId;
	}
	public void setRunPrcsId(int runPrcsId) {
		this.runPrcsId = runPrcsId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getReadUrl() {
		return readUrl;
	}
	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}
	public int getPrcsId() {
		return prcsId;
	}
	public void setPrcsId(int prcsId) {
		this.prcsId = prcsId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRunId() {
		return runId;
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
