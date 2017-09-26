package tfd.system.workflow.flowformitem.data;

public class FlowFormItem {
private int Id;
private String formId;
private String formItemId;
private String title;
private String xtype;
private String fieldName;
private String dataType;
private String maxLength;
private String model;
private String childTable;
public String getChildTable() {
	return childTable;
}
public void setChildTable(String childTable) {
	this.childTable = childTable;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getMaxLength() {
	return maxLength;
}
public void setMaxLength(String maxLength) {
	this.maxLength = maxLength;
}
public String getXtype() {
	return xtype;
}
public void setXtype(String xtype) {
	this.xtype = xtype;
}
public String getDataType() {
	return dataType;
}
public void setDataType(String dataType) {
	this.dataType = dataType;
}
public int getId() {
	return Id;
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
public String getFormItemId() {
	return formItemId;
}
public void setFormItemId(String formItemId) {
	this.formItemId = formItemId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getFieldName() {
	return fieldName;
}
public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
}
}
