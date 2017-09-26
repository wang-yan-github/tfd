package com.component.datamodel;

import java.util.HashMap;
import java.util.Map;

public class Record {
  //键是字段的名称，值是字段的值
  private Map<String, Object> queryFields = new HashMap<String, Object>();
  //字段计数
  private int fieldCnt = 0;
  //字段索引
  private Map<String, String> fieldIndexMap = new HashMap<String, String>();

  /**
   * 连接另外一个记录集
   */
  public void join(Record other) {
    if (other == null) {
      return;
    }
    int fieldCnt = other.getFieldCnt();
    for (int i = 0; i < fieldCnt; i++) {
      String fieldName = other.getNameByIndex(i);
      Object fieldValue = other.getValueByIndex(i);
      this.addField(fieldName, fieldValue);
    }
  }
  
  /**
   * 取得字段的个数
   * @return
   */
  public int getFieldCnt() {
    return fieldCnt;
  }
  
  
  
  /**
   * 增加字段
   * @param fieldName       字段名称
   * @param fieldValue      字段值
   */
  public void addField(String fieldName, Object fieldValue) {
    queryFields.put(fieldName, fieldValue);
    fieldIndexMap.put(String.valueOf(fieldCnt++), fieldName);
  }
  
  /**
   * 增加字段
   * @param fieldName       字段名称
   * @param fieldValue      字段值
   */
  public void updateField(String fieldName, Object fieldValue) {
    queryFields.put(fieldName, fieldValue);
    if (!fieldIndexMap.containsValue(fieldName)) {
      fieldIndexMap.put(String.valueOf(fieldCnt++), fieldName);
    }
  }
  
  /**
   * 由字段名称取得字段的值
   * @param fieldName    字段名称
   * @return
   */
  public Object getValueByName(String fieldName) {
    return queryFields.get(fieldName);
  }
  
  /**
   * 由字段的索引取得字段的值
   * @param fieldIndex  字段的索引
   * @return
   */
  public Object getValueByIndex(int fieldIndex) {
    String fieldName = (String)fieldIndexMap.get(String.valueOf(fieldIndex));
    if (fieldName != null) {
      return queryFields.get(fieldName);
    }
    return null;
  }
  
  /**
   * 由字段的索引取得字段的值
   * @param fieldIndex  字段的索引
   * @return
   */
  public String getNameByIndex(int fieldIndex) {
    String fieldName = (String)fieldIndexMap.get(String.valueOf(fieldIndex));
    return fieldName;
  }
  public boolean nameMatch(String fieldName) {
	  return queryFields.containsKey(fieldName);
  }
  public boolean nameTrimMatch(String fieldName) {
	  for(String k:queryFields.keySet()){
		  if (fieldName.equals(k.trim())) {
			return true;
		  }
	  }
	  return false;
  }
}
