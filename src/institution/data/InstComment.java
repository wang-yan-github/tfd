package institution.data;

import java.util.Date;

public class InstComment {
	private int id;
	private String comId;
	private String comPid;
	private String comContent;
	private Date comTime;
	private String instId;
	private String accountId;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComPid() {
		return comPid;
	}
	public void setComPid(String comPid) {
		this.comPid = comPid;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
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
	
	public Date getComTime() {
		return comTime;
	}
	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	public InstComment() {
		super();
	}
	public InstComment(String comId, String comPid, String comContent,
			Date comTime, String instId, String accountId, String orgId) {
		super();
		this.comId = comId;
		this.comPid = comPid;
		this.comContent = comContent;
		this.comTime = comTime;
		this.instId = instId;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	public InstComment(int id, String comId, String comPid, String comContent,
			Date comTime, String instId, String accountId, String orgId) {
		super();
		this.id = id;
		this.comId = comId;
		this.comPid = comPid;
		this.comContent = comContent;
		this.comTime = comTime;
		this.instId = instId;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	
}
