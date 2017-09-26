package meeting.data;

public class Meetingsummary {

	private int Id;
	private String summaryId;
	private String requestId;
	private String meetingName;
	private String askStaff;
	private String lookStaff;
	private String realityStaff;
	private String summaryContent;
	private String summaryStaff;
	private String attachId;
	private String orgId;
	public String getSummaryStaff() {
		return summaryStaff;
	}
	public void setSummaryStaff(String summaryStaff) {
		this.summaryStaff = summaryStaff;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getAskStaff() {
		return askStaff;
	}
	public void setAskStaff(String askStaff) {
		this.askStaff = askStaff;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getLookStaff() {
		return lookStaff;
	}
	public void setLookStaff(String lookStaff) {
		this.lookStaff = lookStaff;
	}
	public String getRealityStaff() {
		return realityStaff;
	}
	public void setRealityStaff(String realityStaff) {
		this.realityStaff = realityStaff;
	}
	public String getSummaryContent() {
		return summaryContent;
	}
	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
