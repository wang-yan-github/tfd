package com.system.db;

import java.lang.reflect.Field;
import java.util.Properties;

import com.system.loadconfig.LoadConfig;

public class ConnMessageClient {
	public static ConnMessage getConnMessage(String connMessageConfig)
			throws Exception {
		// TODO Auto-generated method stub
		ConnMessage connMessage=null;
		if (connMessage==null) {
			connMessage=new ConnMessage();
			
			Properties prop=LoadConfig.loadSysProps(connMessageConfig);
			Field[] fields=connMessage.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				field.set(connMessage, prop.getProperty(field.getName()));
			}
		}
		return connMessage;
	}
}
