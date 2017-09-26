package tfd.system.unit.account.data;

import java.util.Date;


public class Account {
private int ID=0;
private String accountId="";
private String passWord="";
private String passwordType="";
private String theme="";
private String userId="";
private String userPriv="";
private String pagePriv="";
private String notLogin="";
private String updatePasswordTime = "";
private String loginNum;
private String lastLoginTime = "";
private Date lastVisitTime;
private String appIcon;

public String getAppIcon() {
	return appIcon;
}
public void setAppIcon(String appIcon) {
	this.appIcon = appIcon;
}
public Date getLastVisitTime() {
	return lastVisitTime;
}
public void setLastVisitTime(Date lastVisitTime) {
	this.lastVisitTime = lastVisitTime;
}
private long onLine;
public long getOnLine() {
	return onLine;
}
public void setOnLine(long onLine) {
	this.onLine = onLine;
}
private String language="";
private String byName="";
private String orgId="";
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}

public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getTheme() {
	return theme;
}
public String getPasswordType() {
	return passwordType;
}
public void setPasswordType(String passwordType) {
	this.passwordType = passwordType;
}
public void setTheme(String theme) {
	this.theme = theme;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserPriv() {
	return userPriv;
}
public void setUserPriv(String userPriv) {
	this.userPriv = userPriv;
}
public String getPagePriv() {
	return pagePriv;
}
public void setPagePriv(String pagePriv) {
	this.pagePriv = pagePriv;
}
public String getNotLogin() {
	return notLogin;
}
public void setNotLogin(String notLogin) {
	this.notLogin = notLogin;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getByName() {
	return byName;
}
public void setByName(String byName) {
	this.byName = byName;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public String getUpdatePasswordTime() {
	return updatePasswordTime;
}
public void setUpdatePasswordTime(String updatePasswordTime) {
	this.updatePasswordTime = updatePasswordTime;
}
public String getLastLoginTime() {
	return lastLoginTime;
}
public void setLastLoginTime(String lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
}
public String getLoginNum() {
	return loginNum;
}
public void setLoginNum(String loginNum) {
	this.loginNum = loginNum;
}

}
