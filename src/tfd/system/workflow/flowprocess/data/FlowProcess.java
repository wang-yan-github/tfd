package tfd.system.workflow.flowprocess.data;

public class FlowProcess {
private int Id=0;
private String flowId="";
private String formId="";
private int prcsId=0;
private String prcsName="";
private String nextPrcs="";
private String leadPrcsLeave="";
private String prcsType="";
private String forceAggregation="";
private String forceParallel="";
private String printX="";
private String printY="";
private String path="";
private String fileName="";
private String userPriv="";
private String deptPriv="";
private String privId="";
private String writerField="";
private String childTableWriterField="";
private String prcsCondition="";
private String memo="";
private String autoUserModle="";
private String sPrcsAuto="";
private String childFlow="";
private String parnetWait="0";
private String waitPrcsId="";
private String goBack="";
private String copyToChild="";
private String copyToParent="";
private String shareFlowDoc="";
private String publicFile="";
private String follow="";
private int passTime=0;
private String mustField="";
private String hiddenField="";
public String getSmsConfig() {
	return smsConfig;
}
public void setSmsConfig(String smsConfig) {
	this.smsConfig = smsConfig;
}
private String smsConfig="";

public String getMustField() {
	return mustField;
}
public void setMustField(String mustField) {
	this.mustField = mustField;
}
public String getHiddenField() {
	return hiddenField;
}
public void setHiddenField(String hiddenField) {
	this.hiddenField = hiddenField;
}
public String getFollow() {
	return follow;
}
public int getPassTime() {
	return passTime;
}
public void setPassTime(int passTime) {
	this.passTime = passTime;
}
public void setFollow(String follow) {
	this.follow = follow;
}
public String getGoBack() {
	return goBack;
}
public void setGoBack(String goBack) {
	this.goBack = goBack;
}
public String getWaitPrcsId() {
	return waitPrcsId;
}
public void setWaitPrcsId(String waitPrcsId) {
	this.waitPrcsId = waitPrcsId;
}
public String getPublicFile() {
	return publicFile;
}
public void setPublicFile(String publicFile) {
	this.publicFile = publicFile;
}
public String getLeadPrcsLeave() {
	return leadPrcsLeave;
}
public void setLeadPrcsLeave(String leadPrcsLeave) {
	this.leadPrcsLeave = leadPrcsLeave;
}
public String getParnetWait() {
	return parnetWait;
}
public void setParnetWait(String parnetWait) {
	this.parnetWait = parnetWait;
}
public String getCopyToChild() {
	return copyToChild;
}
public void setCopyToChild(String copyToChild) {
	this.copyToChild = copyToChild;
}
public String getCopyToParent() {
	return copyToParent;
}
public void setCopyToParent(String copyToParent) {
	this.copyToParent = copyToParent;
}
public String getShareFlowDoc() {
	return shareFlowDoc;
}
public String getForceAggregation() {
	return forceAggregation;
}
public void setForceAggregation(String forceAggregation) {
	this.forceAggregation = forceAggregation;
}
public String getForceParallel() {
	return forceParallel;
}
public void setForceParallel(String forceParallel) {
	this.forceParallel = forceParallel;
}
public void setShareFlowDoc(String shareFlowDoc) {
	this.shareFlowDoc = shareFlowDoc;
}
public String getChildTableWriterField() {
	return childTableWriterField;
}
public void setChildTableWriterField(String childTableWriterField) {
	this.childTableWriterField = childTableWriterField;
}
public String getPrivId() {
	return privId;
}
public void setPrivId(String privId) {
	this.privId = privId;
}
public String getDeptPriv() {
	return deptPriv;
}
public void setDeptPriv(String deptPriv) {
	this.deptPriv = deptPriv;
}
public String getAutoUserModle() {
	return autoUserModle;
}
public void setAutoUserModle(String autoUserModle) {
	this.autoUserModle = autoUserModle;
}
public String getsPrcsAuto() {
	return sPrcsAuto;
}
public void setsPrcsAuto(String sPrcsAuto) {
	this.sPrcsAuto = sPrcsAuto;
}
public String getChildFlow() {
	return childFlow;
}
public void setChildFlow(String childFlow) {
	this.childFlow = childFlow;
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getFlowId() {
	return flowId;
}
public void setFlowId(String flowId) {
	this.flowId = flowId;
}
public String getFormId() {
	return formId;
}
public void setFormId(String formId) {
	this.formId = formId;
}
public int getPrcsId() {
	return prcsId;
}
public void setPrcsId(int prcsId) {
	this.prcsId = prcsId;
}
public String getPrcsName() {
	return prcsName;
}
public void setPrcsName(String prcsName) {
	this.prcsName = prcsName;
}
public String getNextPrcs() {
	return nextPrcs;
}
public void setNextPrcs(String nextPrcs) {
	this.nextPrcs = nextPrcs;
}
public String getPrcsType() {
	return prcsType;
}
public void setPrcsType(String prcsType) {
	this.prcsType = prcsType;
}
public String getPrintX() {
	return printX;
}
public void setPrintX(String printX) {
	this.printX = printX;
}
public String getPrintY() {
	return printY;
}
public void setPrintY(String printY) {
	this.printY = printY;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getUserPriv() {
	return userPriv;
}
public void setUserPriv(String userPriv) {
	this.userPriv = userPriv;
}
public String getWriterField() {
	return writerField;
}
public void setWriterField(String writerField) {
	this.writerField = writerField;
}
public String getPrcsCondition() {
	return prcsCondition;
}
public void setPrcsCondition(String prcsCondition) {
	this.prcsCondition = prcsCondition;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
}
