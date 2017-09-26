package tfd.system.workflow.queryset.data;

public class QuerySet {
private int Id=0;
private String queryId="";
private String queryField="";
private String title="";
private String module="";
private String createTime="";
private String createAccountId="";
private String flowId="";
private String orgId="";
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
public String getQueryId() {
	return queryId;
}
public void setQueryId(String queryId) {
	this.queryId = queryId;
}
public String getQueryField() {
	return queryField;
}
public void setQueryField(String queryField) {
	this.queryField = queryField;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getCreateAccountId() {
	return createAccountId;
}
public void setCreateAccountId(String createAccountId) {
	this.createAccountId = createAccountId;
}
public String getFlowId() {
	return flowId;
}
public void setFlowId(String flowId) {
	this.flowId = flowId;
}

}
