package com.system.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

public class JsonConfigDefault{
	public static JsonConfig getJsonConfig(){
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(Class arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(Class arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		jsonConfig.registerDefaultValueProcessor(Long.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(Class arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String name, Object value, JsonConfig arg2) {
				// TODO Auto-generated method stub
				if(value!=null){
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
				}
				return null;
			}
			
			@Override
			public Object processArrayValue(Object value, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return value;
			}
		});
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String name, Object value, JsonConfig arg2) {
				// TODO Auto-generated method stub
				if(value!=null){
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
				}
				return null;
			}
			
			@Override
			public Object processArrayValue(Object value, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return value;
			}
		});
		return jsonConfig;
	}
}
