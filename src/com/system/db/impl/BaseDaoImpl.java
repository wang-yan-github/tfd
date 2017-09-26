package com.system.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.system.db.BaseDao;
import com.system.db.ConnConst;
import com.system.db.ConnMessage;
import com.system.db.ConnMessageClient;

public class BaseDaoImpl implements BaseDao{
	@Override
	public List getListAll(Connection conn, String sql, Object[] obj)throws Exception{
		List results=null;
		QueryRunner qr = new QueryRunner();
		results = qr.query(conn, sql, new MapListHandler(),obj);
		return results;
	}

	@Override
	public List getListAll(Connection conn, String sql) throws Exception {
		List results=null;
		QueryRunner qr = new QueryRunner();
		results = qr.query(conn, sql, new MapListHandler());
		return results;
	}

	@Override
	public void excuteSQL(Connection conn, String sql, Object[] obj) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.update(conn, sql,obj);
	}

	@Override
	public void excuteSQL(Connection conn, String sql) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.update(conn, sql);
	}

	@Override
	public void excuteBatchSQL(Connection conn,String sql, Object[][] obj) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.batch(conn,sql, obj);
	}

	@Override
	public Map getMapById(Connection conn,String sql, Object[] obj) throws Exception {
		Map map=null;
		QueryRunner qr = new QueryRunner();
		map = qr.query(conn,sql, new MapHandler(),obj ); 
		return map;
	}

	@Override
	public Map getMapById(Connection conn,String sql) throws Exception {
		Map map=null;
		QueryRunner qr = new QueryRunner();
		map = qr.query(conn,sql, new MapHandler() ); 
		return map;
	}

	@Override
	public void close(ResultSet rs, Statement stmt, Connection dbConn) {
		// TODO Auto-generated method stub
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(dbConn!=null){
			try {
				dbConn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void rollback(Connection conn) {
		try {
			if (conn!=null) {
				conn.rollback();
			}				
		} catch (Exception e2) {
		}
	}
	
	@Override
	public Connection getConn(String connMessageConfig)throws Exception{
		ConnMessage connMessage=ConnMessageClient.getConnMessage(connMessageConfig);
		String sourceType=connMessage.getSourceType();
		String url=null;
		String userId=connMessage.getUserId();
		String userPass=connMessage.getUserPass();
		String sourceAddress=connMessage.getSourceAddress();
		String sourceName=connMessage.getSourceName();
		if(sourceType.equals(ConnConst.VALUE_SOURCE_TYPE_MYSQL)){
			Class.forName("com.mysql.jdbc.Driver");
			url="jdbc:mysql://"+sourceAddress+"/"+sourceName;
		}else if(sourceType.equals(ConnConst.VALUE_SOURCE_TYPE_SQLSERVER)){
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url="jdbc:sqlserver://"+sourceAddress+"; DatabaseName="+sourceName;
		}else if(sourceType.equals(ConnConst.VALUE_SOURCE_TYPE_ORACLE)){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@"+sourceAddress+":"+sourceName;
		}  
		return DriverManager.getConnection(url, userId, userPass);
	}
}
