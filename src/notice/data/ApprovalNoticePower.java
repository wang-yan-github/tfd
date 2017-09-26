package notice.data;

public class ApprovalNoticePower {

	private int Id;
	private String powerId;
	private String noticeType;
	private String approvalStaff;
	private String orgId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPowerId() {
		return powerId;
	}
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getApprovalStaff() {
		return approvalStaff;
	}
	public void setApprovalStaff(String approvalStaff) {
		this.approvalStaff = approvalStaff;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
