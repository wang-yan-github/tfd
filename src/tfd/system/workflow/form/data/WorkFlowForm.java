package tfd.system.workflow.form.data;

import java.util.Date;

public class WorkFlowForm {
private int Id;
private String formId;
private String formName;
private String formTableName;
private String formCreateUser;
private String formDeptId;
private Date formCreatTime;
private String workFlowTypeId;
private String formCode;
private String formType;
private String orgId;
public int getId() {
	return Id;
}
public String getFormType() {
	return formType;
}
public void setFormType(String formType) {
	this.formType = formType;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public void setId(int id) {
	Id = id;
}
public String getFormId() {
	return formId;
}
public void setFormId(String formId) {
	this.formId = formId;
}
public String getFormName() {
	return formName;
}
public void setFormName(String formName) {
	this.formName = formName;
}
public String getFormTableName() {
	return formTableName;
}
public void setFormTableName(String formTableName) {
	this.formTableName = formTableName;
}
public String getFormCode() {
	return formCode;
}
public void setFormCode(String formCode) {
	this.formCode = formCode;
}
public String getFormCreateUser() {
	return formCreateUser;
}
public void setFormCreateUser(String formCreateUser) {
	this.formCreateUser = formCreateUser;
}
public String getFormDeptId() {
	return formDeptId;
}
public void setFormDeptId(String formDeptId) {
	this.formDeptId = formDeptId;
}
public Date getFormCreatTime() {
	return formCreatTime;
}
public void setFormCreatTime(Date formCreatTime) {
	this.formCreatTime = formCreatTime;
}
public String getWorkFlowTypeId() {
	return workFlowTypeId;
}
public void setWorkFlowTypeId(String workFlowTypeId) {
	this.workFlowTypeId = workFlowTypeId;
}

}
