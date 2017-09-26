package com.system.db.injection;

public class Parameter {
	//数据库字段名
	private String field;
	//参数值 Object
	private Object value;
	//数据库操作符 =、>=、<、like...
	private String operator;

	public Parameter(){
		
	}
	public Parameter(String field, String operator, Object value) {
		this.field = field;
		this.value = value;
		this.operator = operator;
	}

	
	
	public void setField(String field) {
		this.field = field;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getField() {
		return field;
	}
	public Object getValue() {
		return value;
	}
	public String getOperator() {
		return operator;
	}
}
