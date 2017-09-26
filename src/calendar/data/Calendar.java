package calendar.data;

public class Calendar {
	private String calendarId;
	private String calendarPid;
	private String startDate;
	private String endDate;
	private String content;
	private String calType;
	private String calLevel;
	private String calAffType;
	private String BeforeTime;
	private String accountId;
	private String userId;
	private String fromId;
	private String isSms;
	private String status;
	private String diaryId;
	private String fromType;
	private String fromTypeId;
	private String affairId;
	private String orgId;
	



	public Calendar(String calendarId, String startDate, String endDate,
			String content, String calType, String calLevel, String calAffType,
			String beforeTime, String accountId, String userId,
			String isSms, String status, String orgId) {
		super();
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.calType = calType;
		this.calLevel = calLevel;
		this.calAffType = calAffType;
		this.BeforeTime = beforeTime;
		this.accountId = accountId;
		this.userId = userId;
		this.isSms = isSms;
		this.status = status;
		this.orgId = orgId;
	}

	public String getAffairId() {
		return affairId;
	}

	public void setAffairId(String affairId) {
		this.affairId = affairId;
	}

	public String getCalendarPid() {
		return calendarPid;
	}

	public void setCalendarPid(String calendarPid) {
		this.calendarPid = calendarPid;
	}

	public String getBeforeTime() {
		return BeforeTime;
	}

	public void setBeforeTime(String beforeTime) {
		BeforeTime = beforeTime;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCalType() {
		return calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public String getCalLevel() {
		return calLevel;
	}

	public void setCalLevel(String calLevel) {
		this.calLevel = calLevel;
	}

	public String getCalAffType() {
		return calAffType;
	}

	public void setCalAffType(String calAffType) {
		this.calAffType = calAffType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getFromTypeId() {
		return fromTypeId;
	}

	public void setFromTypeId(String fromTypeId) {
		this.fromTypeId = fromTypeId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}




	public Calendar() {
		super();
	}
}
