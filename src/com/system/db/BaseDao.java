package com.system.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public interface BaseDao {
	public List getListAll(Connection conn,String sql,Object[] obj)throws Exception;
	public List getListAll(Connection conn,String sql)throws Exception;
	public void excuteSQL(Connection conn,String sql,Object[] obj)throws Exception;
	public void excuteSQL(Connection conn,String sql)throws Exception;
	public void excuteBatchSQL(Connection conn,String sql,Object[][] obj)throws Exception;
	public Map getMapById(Connection conn,String sql,Object[] obj)throws Exception;
	public Map getMapById(Connection conn,String sql)throws Exception;
	public void close(ResultSet rs, Statement stmt, Connection dbConn);
	public void rollback(Connection conn);
	public Connection getConn(String connMessageConfig)throws Exception;
}
