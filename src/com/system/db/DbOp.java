package com.system.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbOp {
	public int delTable(Connection conn,String tableName) throws SQLException
	{
		String queryStr="DROP TABLE "+tableName;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		int i = ps.executeUpdate();
		ps.close();
		return i;
		
	}
	
	public void connClose (Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				conn=null;
			}
			
		}
	}
	
	public void cretateSeqTg(String tableName,Connection dbConn)throws Exception{
			CallableStatement callableStmt=null;
			callableStmt=dbConn.prepareCall("{call CREATESEQANDTRI(?)}");
			callableStmt.setString(1, tableName);
			callableStmt.execute();
			callableStmt.close();
		}
}