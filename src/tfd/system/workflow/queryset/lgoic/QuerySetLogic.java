package tfd.system.workflow.queryset.lgoic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tfd.system.workflow.queryset.data.QuerySet;

public class QuerySetLogic {
	
//添加查询模版
	public void addQuerySetLogic(Connection conn,QuerySet querySet) throws SQLException
	{
		String queryStr="INSERT INTO WORK_FLOW_QUERY_SET(QUERY_ID,QUERY_FIELD,TITLE,MODULE,CREATE_TIME,CREATE_ACCOUNT_ID,FLOW_ID,ORG_ID)VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, querySet.getQueryId());
		ps.setString(2, querySet.getQueryField());
		ps.setString(3, querySet.getTitle());
		ps.setString(4, querySet.getModule());
		ps.setString(5, querySet.getCreateTime());
		ps.setString(6, querySet.getCreateAccountId());
		ps.setString(7, querySet.getFlowId());
		ps.setString(8, querySet.getOrgId());
		ps.executeUpdate();
		ps.close();
	}

}
