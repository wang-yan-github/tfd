package com.system.tool;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AutoConvertUtil {
	/**
	 * 2014-6-10 fzd 前台表单转换成相应的实体类
	 * @param entityClass
	 * @param requestMap
	 * @return
	 */
	public Object formToEntity(Class entityClass,Map<String,String[]> requestMap)throws Exception{
		Object entity=null;
		try {
			entity=entityClass.newInstance();
			if(requestMap!=null){
				Field[] fields=entityClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					String[] fieldValues=requestMap.get(fields[i].getName());
					String fieldValue=null;
					if (fieldValues!=null) {
						fieldValue=fieldValues[0];
					}
					Class fieldType=fields[i].getType();
					if(fieldValue!=null&&!fieldValue.equals("")){
						if(fieldType==Date.class){
							fields[i].set(entity, SysTool.strToDate(fieldValue));
						}else if(fieldType==int.class){
							fields[i].set(entity, Integer.parseInt(fieldValue));
						}else if(fieldType==Integer.class){
							fields[i].set(entity, Integer.parseInt(fieldValue));
						}else if(fieldType==double.class){
							fields[i].set(entity, Double.parseDouble(fieldValue));
						}else if(fieldType==Double.class){
							fields[i].set(entity, Double.parseDouble(fieldValue));
						}else if(fieldType==float.class){
							fields[i].set(entity, Float.parseFloat(fieldValue));
						}else if(fieldType==Float.class){
							fields[i].set(entity, Float.parseFloat(fieldValue));
						}else{
							fields[i].set(entity,fieldValue.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
	/**
	 * 2014-3-29 fzd 将键值对形式的数据转换成相应的实体类
	 * @param entityClass
	 * @param requestMap
	 * @return
	 */
	public Object mapDataToEntity(Class entityClass,Map<String,Object> mapData){
		Object entity=null;
		try {
			if(mapData!=null){
				entity=entityClass.newInstance();
				Field[] fields=entityClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					Object fieldValue=mapData.get(fields[i].getName());
					Class fieldType=fields[i].getType();
					if(fieldValue!=null&&!fieldValue.equals("")){
						fieldValue=valueConvert(fieldValue);
						if(fieldType==Date.class){
							fields[i].set(entity, SysTool.strToDate(fieldValue.toString()));
						}else if(fieldType==int.class){
							fields[i].set(entity, Integer.parseInt(fieldValue.toString()));
						}else if(fieldType==Integer.class){
							fields[i].set(entity, Integer.parseInt(fieldValue.toString()));
						}else if(fieldType==double.class){
							fields[i].set(entity, Double.parseDouble(fieldValue.toString()));
						}else if(fieldType==Double.class){
							fields[i].set(entity, Double.parseDouble(fieldValue.toString()));
						}else if(fieldType==float.class){
							fields[i].set(entity, Float.parseFloat(fieldValue.toString()));
						}else if(fieldType==Float.class){
							fields[i].set(entity, Float.parseFloat(fieldValue.toString()));
						}else{
							fields[i].set(entity,fieldValue.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return entity;
	}
	/**
	 * 2014-6-10 将字段转换为数据库字段
	 * @param fieldName
	 * @return
	 */
	public String fieldToColumn(String fieldName){
		String columnName="";
		int j=0;
		Pattern pattern=Pattern.compile("[a-zA-Z]");
		for (int i = 0; i < fieldName.length(); i++) {
			String c=new String(new char[]{fieldName.charAt(i)});
			boolean upperCase=false;
			Matcher matcher=pattern.matcher(c);
			if(matcher.matches()){
				upperCase=c==c.toUpperCase();
			}
			if (upperCase) {
				if(i>j){
					columnName+=fieldName.substring(j,i).toLowerCase()+"_";
					j=i;
				}
			}
		}
		columnName=columnName+fieldName.substring(j).toLowerCase();
		return columnName;
	}
	/**
	 * 2014-6-10 fzd 将数据库字段转换为实体类字段
	 * @param columnName
	 * @return
	 */
	public String columnToField(String columnName){
		String fieldName="";
		try {
			String[] columnNames=columnName.split("_");
			fieldName+=columnNames[0].toLowerCase();
			Pattern pattern=Pattern.compile("[a-zA-Z]");
			for (int i = 1; i < columnNames.length; i++) {
				Matcher matcher=pattern.matcher(columnNames[i].charAt(0)+"");
				if(matcher.matches()){
					fieldName+=columnNames[i].substring(0,1).toUpperCase()+columnNames[i].substring(1).toLowerCase();
				}else{
					fieldName+="_"+columnNames[i].toLowerCase();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fieldName;
	}
	/**
	 * 2014-6-10 fzd 将表名转换成实体类名
	 * @param tableName
	 * @return
	 */
	public String tableNameToClassName(String tableName){
		String className="";
		String[] names=tableName.split("_");
		for (String name : names) {
			className+=name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
		}
		return className;
	}
	
	/**
	 * 2014-6-10 fzd 将实体类转换成表名
	 * @param className
	 * @return
	 */
	public String classNameToTableName(String className){
		if(className.indexOf(".")>-1){
			className=className.split("\\.")[className.split("\\.").length-1];
		}
		return this.fieldToColumn(className);
	}
	
	/**
	 * 2014-7-1 fzd 将clob类型转换成字符串
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static String clobToString(Clob cl) throws Exception{
	    String res = null;
	    Reader is = null;
	    if(cl == null ){
	      return null;
	    }
	    try{
	      is = cl.getCharacterStream();// 得到流 
	      BufferedReader br = new BufferedReader(is); 
	      String s = br.readLine(); 
	      StringBuffer sb = new StringBuffer(); 
	      while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING 
	        sb.append(s); 
	        s = br.readLine(); 
	        if (s != null) {
	          sb.append("\r\n");
	        }
	      } 
	      res = sb.toString(); 
	    }catch(Exception e){
	      e.printStackTrace();
	      throw e;
	    }finally{
	      is.close();
	    }
	    return res;
	  }
	/**
	 * 2014-7-1 fzd 将clob类型转换成字符串
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static String clobToString(oracle.sql.CLOB cl) throws Exception{
	    String res = null;
	    Reader is = null;
	    if(cl == null ){
	      return null;
	    }
	    try{
	      is = cl.getCharacterStream();// 得到流 
	      BufferedReader br = new BufferedReader(is); 
	      String s = br.readLine(); 
	      StringBuffer sb = new StringBuffer(); 
	      while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING 
	        sb.append(s); 
	        s = br.readLine(); 
	        if (s != null) {
	          sb.append("\r\n");
	        }
	      } 
	      res = sb.toString(); 
	    }catch(Exception e){
	      e.printStackTrace();
	      throw e;
	    }finally{
	      is.close();
	    }
	    return res;
	  }
	/**
	 * 2014-7-13 fzd 值转换，转换那些不易处理的类型为字符串，如，clob
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Object valueConvert(Object value) throws Exception{
		Object convertValue=null;
		if(value!=null){
			Class valueType=value.getClass();
			if(valueType==Clob.class){
				convertValue=clobToString((Clob) value);
			}else if(valueType==oracle.sql.CLOB.class){
				convertValue=clobToString((oracle.sql.CLOB)value);
			}else{
				convertValue=value;
			}
		}
		return convertValue;
	}
}
