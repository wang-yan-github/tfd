package notice.data;

public class Notice {

	private int Id;				//id
	private String noticeId;	//公告Id
	private String noticetitle;	//公告标题
	private String noticeType;	//公告类型
	private String accountId;	//人员权限
	private String deptPriv;	//部门权限
	private String userPriv;	//角色权限
	private String createtime;	//创建时间
	private String endTime;		//结束时间
	private String attachId;	//附件Id
	private String attachPriv;	//附件权限
	private String noticeContent;	//公告内容
	private int onclickCount;		//点击数
	private String delFlag;			//删除状态
	private String top;				//置顶
	private String reader;			//阅读人员
	private String createUser;		//创建人员
	private String approvalStatus;	//审批状态
	private String noticeStatus;	//发布状态
	private String approvalStaff;  //审批人员
	private String attachPower;		//附件权限
	private String orgId;
	private String isSms;
	
	public String getIsSms() {
		return isSms;
	}
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}
	public String getAttachPower() {
		return attachPower;
	}
	public void setAttachPower(String attachPower) {
		this.attachPower = attachPower;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getDeptPriv() {
		return deptPriv;
	}
	public void setDeptPriv(String deptPriv) {
		this.deptPriv = deptPriv;
	}
	public String getUserPriv() {
		return userPriv;
	}
	public void setUserPriv(String userPriv) {
		this.userPriv = userPriv;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getAttachPriv() {
		return attachPriv;
	}
	public void setAttachPriv(String attachPriv) {
		this.attachPriv = attachPriv;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getOnclickCount() {
		return onclickCount;
	}
	public void setOnclickCount(int onclickCount) {
		this.onclickCount = onclickCount;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
}
