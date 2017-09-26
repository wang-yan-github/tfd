package tfd.system.messageconfig.data;

public class MessageConfig {
private int Id;
private String mConfigId;
private String module;
private String value;
private String orgId;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getmConfigId() {
	return mConfigId;
}
public void setmConfigId(String mConfigId) {
	this.mConfigId = mConfigId;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
}
