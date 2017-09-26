package com.system.filetool;

public class Attachment {
private int id=0;
private String attachmentId="";
private String attachmentName="";
private String upTime="";
private String createAccountId="";
private int useCount=0;
private String useUser="";
private String path="";
private String modules="";
private String priv="";
private String configNo="";
private String delFlag="";
private long fileSize;
public long getFileSize() {
	return fileSize;
}
public void setFileSize(long fileSize) {
	this.fileSize = fileSize;
}
public String getDelFlag() {
	return delFlag;
}
public void setDelFlag(String delFlag) {
	this.delFlag = delFlag;
}
private String orgId="";
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public void setUseCount(int useCount) {
	this.useCount = useCount;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getUpTime() {
	return upTime;
}
public void setUpTime(String upTime) {
	this.upTime = upTime;
}
public String getCreateAccountId() {
	return createAccountId;
}
public void setCreateAccountId(String createAccountId) {
	this.createAccountId = createAccountId;
}
public int getUseCount() {
	return useCount;
}
public void setUserCount(int useCount) {
	this.useCount = useCount;
}
public String getUseUser() {
	return useUser;
}
public void setUseUser(String useUser) {
	this.useUser = useUser;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getModules() {
	return modules;
}
public void setModules(String modules) {
	this.modules = modules;
}
public String getPriv() {
	return priv;
}
public void setPriv(String priv) {
	this.priv = priv;
}
public String getConfigNo() {
	return configNo;
}
public void setConfigNo(String configNo) {
	this.configNo = configNo;
}

}
