package com.system.login.data;
public class UserOnline {
private int Id;
private String accountId;
private String loginTime;
private String state;
private String sessionToken;
private String ip;
private String orgId;

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
public String getLoginTime() {
	return loginTime;
}
public void setLoginTime(String loginTime) {
	this.loginTime = loginTime;
}
public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getSessionToken() {
	return sessionToken;
}
public void setSessionToken(String sessionToken) {
	this.sessionToken = sessionToken;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
}
