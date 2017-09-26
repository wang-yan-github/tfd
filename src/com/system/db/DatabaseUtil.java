package com.system.db;

import java.sql.Connection;

public class DatabaseUtil {
	public static String getDatabaseType(Connection conn) throws Exception{
		String driverName=conn.getMetaData().getDriverName().toLowerCase();
		if (driverName.indexOf("mysql")>-1) {
			return ConnConst.VALUE_SOURCE_TYPE_MYSQL;
		}else if (driverName.indexOf("oracle")>-1) {
			return ConnConst.VALUE_SOURCE_TYPE_ORACLE;
		}else if (driverName.indexOf("sql server")>-1) {
			return ConnConst.VALUE_SOURCE_TYPE_SQLSERVER;
		}
		return null;
	}
}
