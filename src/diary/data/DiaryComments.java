package diary.data;

public class DiaryComments {

	private int Id;
	private String commId;
	private String commPid;
	private String commContect;
	private String commTime;
	private String diaryId;
	private String accountId;
	private String orgId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getCommPid() {
		return commPid;
	}
	public void setCommPid(String commPid) {
		this.commPid = commPid;
	}
	public String getCommContect() {
		return commContect;
	}
	public void setCommContect(String commContect) {
		this.commContect = commContect;
	}
	public String getCommTime() {
		return commTime;
	}
	public void setCommTime(String commTime) {
		this.commTime = commTime;
	}
	public String getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
