package institution.data;

import java.util.Date;



public class Institution {
	private int id;
	private String instId;
	private String instName;
	private String instContent;
	private Date createTime;
	private String dirId;
	private String attachId;
	private String accountId;
	private String orgId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getInstContent() {
		return instContent;
	}
	public void setInstContent(String instContent) {
		this.instContent = instContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	public String getDirId() {
		return dirId;
	}
	public void setDirId(String dirId) {
		this.dirId = dirId;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
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
	public Institution() {
		super();
	}
	public Institution(int id, String instId, String instName,
			String instContent, Date createTime, String dirId, String attachId,
			String accountId, String orgId) {
		super();
		this.id = id;
		this.instId = instId;
		this.instName = instName;
		this.instContent = instContent;
		this.createTime = createTime;
		this.dirId = dirId;
		this.attachId = attachId;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	public Institution(String instId, String instName, String instContent,
			Date createTime, String dirId, String attachId, String accountId,
			String orgId) {
		super();
		this.instId = instId;
		this.instName = instName;
		this.instContent = instContent;
		this.createTime = createTime;
		this.dirId = dirId;
		this.attachId = attachId;
		this.accountId = accountId;
		this.orgId = orgId;
	}
}
