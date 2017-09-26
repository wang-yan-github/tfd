package com.system.db.injection;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DynamicQuery {
	private String templet = " AND %s %s ?";
	private String baseSql;
	private List<Parameter> parameters = new ArrayList<Parameter>();

	public DynamicQuery() {

	}

	/**
	 * baseSql需要带有where条件
	 * @param baseSql
	 */
	public void setBaseSql(String baseSql) {
		this.baseSql = baseSql;
	}

	public List<Parameter> getParameters(){
		return this.parameters;
	}
	public void setParameters(List<Parameter> parameters){
		this.parameters=parameters;
	}
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}

	public String generateSql() {
		StringBuffer sb = new StringBuffer(baseSql);
		for (Parameter parameter : parameters) {
			sb.append(String.format(templet, parameter.getField(),parameter.getOperator()));
		}
		return sb.toString();
	}

	public void fillPreparedStatement(PreparedStatement stmt) throws Exception {
		int index = 1;
		for (Parameter p : parameters) {
			Object fieldValue=p.getValue();
			Class fieldType=fieldValue.getClass();
			
			
			List<Class> TYPE_INT=new ArrayList<Class>();
			TYPE_INT.add(int.class);
			TYPE_INT.add(Integer.class);
			
			List<Class> TYPE_LONG=new ArrayList<Class>();
			TYPE_LONG.add(long.class);
			TYPE_LONG.add(Long.class);
			
			List<Class> TYPE_FLOAT=new ArrayList<Class>();
			TYPE_LONG.add(float.class);
			TYPE_LONG.add(Float.class);
			
			List<Class> TYPE_DOUBLE=new ArrayList<Class>();
			TYPE_LONG.add(double.class);
			TYPE_LONG.add(Double.class);
			
			
			
			
			if (fieldType==Date.class) {
				stmt.setTimestamp(index, new Timestamp(((Date)fieldValue).getTime()));
			}else if(TYPE_INT.contains(fieldType)){
				stmt.setInt(index, (Integer) fieldValue);
			}else if(TYPE_LONG.contains(fieldType)){
				stmt.setLong(index, (Long) fieldValue);
			}else if(TYPE_FLOAT.contains(fieldType)){
				stmt.setFloat(index, (Float) fieldValue);
			}else if(TYPE_DOUBLE.contains(fieldType)){
				stmt.setDouble(index, (Double) fieldValue);
			}else{
				stmt.setString(index, fieldValue.toString());
			}
			
			index++;
		}
	}
}
