package diary.data;

public class DiaryFit {

	private int Id;
	private String fitId;
	private String startTime;
	private String endTime;
	private int lockDay;
	private int commStatus;
	private int shareStatus;
	private String orgId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFitId() {
		return fitId;
	}
	public void setFitId(String fitId) {
		this.fitId = fitId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getLockDay() {
		return lockDay;
	}
	public void setLockDay(int lockDay) {
		this.lockDay = lockDay;
	}
	public int getCommStatus() {
		return commStatus;
	}
	public void setCommStatus(int commStatus) {
		this.commStatus = commStatus;
	}
	public int getShareStatus() {
		return shareStatus;
	}
	public void setShareStatus(int shareStatus) {
		this.shareStatus = shareStatus;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
