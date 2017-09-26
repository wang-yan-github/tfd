package diary.data;


public class Diary {
	private int Id;
	private String accountId;
	private String diaryName;
	private String diaryContent;
	private String diaryAnnex;
	private String diaryDatetime;
	private int diaryMold;
	private String diaryId;
	private String orgId;
	private String diaryTitleDatetime;
	private String sharePriv;
	private String lookStaff;
	private int shareRange;
	private String laud;
	private int laudNum;
	
	
	public int getLaudNum() {
		return laudNum;
	}
	public void setLaudNum(int laudNum) {
		this.laudNum = laudNum;
	}
	public String getLaud() {
		return laud;
	}
	public void setLaud(String laud) {
		this.laud = laud;
	}
	public int getShareRange() {
		return shareRange;
	}
	public void setShareRange(int shareRange) {
		this.shareRange = shareRange;
	}
	public String getLookStaff() {
		return lookStaff;
	}
	public void setLookStaff(String lookStaff) {
		this.lookStaff = lookStaff;
	}
	public String getSharePriv() {
		return sharePriv;
	}
	public void setSharePriv(String sharePriv) {
		this.sharePriv = sharePriv;
	}
	public String getDiaryTitleDatetime() {
		return diaryTitleDatetime;
	}
	public void setDiaryTitleDatetime(String diaryTitleDatetime) {
		this.diaryTitleDatetime = diaryTitleDatetime;
	}
	public String getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getDiaryMold() {
		return diaryMold;
	}
	public void setDiaryMold(int diaryMold) {
		this.diaryMold = diaryMold;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getDiaryName() {
		return diaryName;
	}
	public void setDiaryName(String diaryName) {
		this.diaryName = diaryName;
	}
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	public String getDiaryAnnex() {
		return diaryAnnex;
	}
	public void setDiaryAnnex(String diaryAnnex) {
		this.diaryAnnex = diaryAnnex;
	}
	public String getDiaryDatetime() {
		return diaryDatetime;
	}
	public void setDiaryDatetime(String diaryDatetime) {
		this.diaryDatetime = diaryDatetime;
	}
	public Diary(int id, String accountId, String diaryName,
			String diaryContent, String diaryAnnex, String diaryDatetime,
			int diaryMold, String diaryId, String orgId,
			String diaryTitleDatetime) {
		super();
		Id = id;
		this.accountId = accountId;
		this.diaryName = diaryName;
		this.diaryContent = diaryContent;
		this.diaryAnnex = diaryAnnex;
		this.diaryDatetime = diaryDatetime;
		this.diaryMold = diaryMold;
		this.diaryId = diaryId;
		this.orgId = orgId;
		this.diaryTitleDatetime = diaryTitleDatetime;
	}
	public Diary() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
