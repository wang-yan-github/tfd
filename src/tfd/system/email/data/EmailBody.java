package tfd.system.email.data;

import java.util.Date;

public class EmailBody {
	private int id;
	private String bodyId;
	private String fromId;
	private String toId;
	private String copyToId;
	private String subject;
	private String content;
	private Date sendTime;
	private String module;
    private String attachmentId;
    private String attachmentName;
    private String sendFlag;
    private String smsRemind;
    private String important;
    private String fromWebmail;
    private String toWebmail;
    private String toWebmailCopy;
    private String compressContent;
    private String fromWebmailId;
    private String webmailContent;
    private String webmailFlag;
    private String isWebmail;
    private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBodyId() {
		return bodyId;
	}
	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getCopyToId() {
		return copyToId;
	}
	public void setCopyToId(String copyToId) {
		this.copyToId = copyToId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}
	public String getSmsRemind() {
		return smsRemind;
	}
	public void setSmsRemind(String smsRemind) {
		this.smsRemind = smsRemind;
	}
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getFromWebmail() {
		return fromWebmail;
	}
	public void setFromWebmail(String fromWebmail) {
		this.fromWebmail = fromWebmail;
	}
	public String getToWebmail() {
		return toWebmail;
	}
	public void setToWebmail(String toWebmail) {
		this.toWebmail = toWebmail;
	}
	public String getToWebmailCopy() {
		return toWebmailCopy;
	}
	public void setToWebmailCopy(String toWebmailCopy) {
		this.toWebmailCopy = toWebmailCopy;
	}
	public String getCompressContent() {
		return compressContent;
	}
	public void setCompressContent(String compressContent) {
		this.compressContent = compressContent;
	}
	public String getFromWebmailId() {
		return fromWebmailId;
	}
	public void setFromWebmailId(String fromWebmailId) {
		this.fromWebmailId = fromWebmailId;
	}
	public String getWebmailContent() {
		return webmailContent;
	}
	public void setWebmailContent(String webmailContent) {
		this.webmailContent = webmailContent;
	}
	public String getWebmailFlag() {
		return webmailFlag;
	}
	public void setWebmailFlag(String webmailFlag) {
		this.webmailFlag = webmailFlag;
	}
	public String getIsWebmail() {
		return isWebmail;
	}
	public void setIsWebmail(String isWebmail) {
		this.isWebmail = isWebmail;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public EmailBody() {
		super();
	}
	public EmailBody(String bodyId, String fromId, String toId,
			String copyToId, String subject, String content, Date sendTime,
			String attachmentId, String attachmentName, String sendFlag,
			String smsRemind, String important, String fromWebmail,
			String toWebmail, String toWebmailCopy, String compressContent,
			String fromWebmailId, String webmailContent, String webmailFlag,
			String isWebmail, String orgId) {
		super();
		this.bodyId = bodyId;
		this.fromId = fromId;
		this.toId = toId;
		this.copyToId = copyToId;
		this.subject = subject;
		this.content = content;
		this.sendTime = sendTime;
		this.attachmentId = attachmentId;
		this.attachmentName = attachmentName;
		this.sendFlag = sendFlag;
		this.smsRemind = smsRemind;
		this.important = important;
		this.fromWebmail = fromWebmail;
		this.toWebmail = toWebmail;
		this.toWebmailCopy = toWebmailCopy;
		this.compressContent = compressContent;
		this.fromWebmailId = fromWebmailId;
		this.webmailContent = webmailContent;
		this.webmailFlag = webmailFlag;
		this.isWebmail = isWebmail;
		this.orgId = orgId;
	}
	public EmailBody(int id, String bodyId, String fromId, String toId,
			String copyToId, String subject, String content, Date sendTime,
			String attachmentId, String attachmentName, String sendFlag,
			String smsRemind, String important, String fromWebmail,
			String toWebmail, String toWebmailCopy, String compressContent,
			String fromWebmailId, String webmailContent, String webmailFlag,
			String isWebmail, String orgId) {
		super();
		this.id = id;
		this.bodyId = bodyId;
		this.fromId = fromId;
		this.toId = toId;
		this.copyToId = copyToId;
		this.subject = subject;
		this.content = content;
		this.sendTime = sendTime;
		this.attachmentId = attachmentId;
		this.attachmentName = attachmentName;
		this.sendFlag = sendFlag;
		this.smsRemind = smsRemind;
		this.important = important;
		this.fromWebmail = fromWebmail;
		this.toWebmail = toWebmail;
		this.toWebmailCopy = toWebmailCopy;
		this.compressContent = compressContent;
		this.fromWebmailId = fromWebmailId;
		this.webmailContent = webmailContent;
		this.webmailFlag = webmailFlag;
		this.isWebmail = isWebmail;
		this.orgId = orgId;
	}
	
}
