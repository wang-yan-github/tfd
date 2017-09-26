package tfd.system.workflow.workflowdatasource.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.workflow.workflowdatasource.data.WorkFlowDataSource;

public class WorkFlowDataSourceLogic {
	//添加
	public void addDataSourceLogic(Connection conn,WorkFlowDataSource workFlowDataSource) throws SQLException
	{
		String queryStr="INSERT INTO WORK_FLOW_DATA_SOURCE(DB_SOURCE_ID,DB_SOURCE_NAME,DB_SOURCE_TYPE,DB_LINK,DB_USER_NAME,DB_USER_PASSWD,DB_NAME,CREATE_ACCOUNT_ID,CREATE_TIME,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, workFlowDataSource.getDbSourceId());
		ps.setString(2, workFlowDataSource.getDbSourceName());
		ps.setString(3, workFlowDataSource.getDbSourceType());
		ps.setString(4, workFlowDataSource.getDbLink());
		ps.setString(5, workFlowDataSource.getDbUserName());
		ps.setString(6, workFlowDataSource.getDbUserPasswd());
		ps.setString(7, workFlowDataSource.getDbName());
		ps.setString(8, workFlowDataSource.getCreateAccountId());
		ps.setString(9, workFlowDataSource.getCreateTime());
		ps.setString(10, workFlowDataSource.getOrgId());
		ps.executeUpdate();
		ps.close();
		
	}
	//删除
	public void delDataSourceLogic(Connection conn,String dbSourceId) throws SQLException
	{
		String queryStr="DELETE FROM WORK_FLOW_DATA_SOURCE WHERE DB_SOURCE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dbSourceId);
		ps.executeUpdate();
		ps.close();
	}
	//获取下拉数据源列表
	public String getDataSourceListLogic(Connection conn,String orgId) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT DB_SOURCE_ID,DB_SOURCE_NAME FROM WORK_FLOW_DATA_SOURCE WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("DB_SOURCE_ID"));
			json.accumulate("text", rs.getString("DB_SOURCE_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	//更新
	public void updateDataSourceLogic(Connection conn,WorkFlowDataSource workFlowDataSource) throws SQLException
	{
		String queryStr="UPDATE WORK_FLOW_DATA_SOURCE SET DB_SOURCE_NAME=?,DB_SOURCE_TYPE=?,DB_LINK=?,DB_USER_NAME=?,DB_USER_PASSWD=?,DB_NAME=?,CREATE_ACCOUNT_ID=?,CREATE_TIME=?,ORG_ID=? WHERE DB_SOURCE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, workFlowDataSource.getDbSourceName());
		ps.setString(2, workFlowDataSource.getDbSourceType());
		ps.setString(3, workFlowDataSource.getDbLink());
		ps.setString(4, workFlowDataSource.getDbUserName());
		ps.setString(5, workFlowDataSource.getDbUserPasswd());
		ps.setString(6, workFlowDataSource.getDbName());
		ps.setString(7, workFlowDataSource.getCreateAccountId());
		ps.setString(8, workFlowDataSource.getCreateTime());
		ps.setString(9, workFlowDataSource.getOrgId());
		ps.setString(10, workFlowDataSource.getDbSourceId());
		ps.executeUpdate();
		ps.close();
	}
public WorkFlowDataSource getWorkFlowDataSourceLogic(Connection conn,String dbSourceId ) throws SQLException
{
	WorkFlowDataSource workFlowDataSource = new WorkFlowDataSource();
	String queryStr="SELECT ID,DB_SOURCE_ID,DB_SOURCE_NAME,DB_SOURCE_TYPE,DB_LINK,DB_USER_NAME,"
			+ "DB_USER_PASSWD,DB_NAME,CREATE_ACCOUNT_ID,CREATE_TIME,ORG_ID FROM WORK_FLOW_DATA_SOURCE WHERE DB_SOURCE_ID=?";
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, dbSourceId);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		workFlowDataSource.setID(rs.getInt("ID"));
		workFlowDataSource.setDbSourceId(rs.getString("DB_SOURCE_ID"));
		workFlowDataSource.setDbSourceName(rs.getString("DB_SOURCE_NAME"));
		workFlowDataSource.setDbSourceType(rs.getString("DB_SOURCE_TYPE"));
		workFlowDataSource.setDbLink(rs.getString("DB_LINK"));
		workFlowDataSource.setDbUserName(rs.getString("DB_USER_NAME"));
		workFlowDataSource.setDbUserPasswd(rs.getString("DB_USER_PASSWD"));
		workFlowDataSource.setDbName(rs.getString("DB_NAME"));
		workFlowDataSource.setCreateAccountId(rs.getString("CREATE_ACCOUNT_ID"));
		workFlowDataSource.setCreateTime(rs.getString("CREATE_TIME"));
		workFlowDataSource.setOrgId(rs.getString("ORG_ID"));
	}
	rs.close();
	ps.close();
	return workFlowDataSource;
}
//获取列表
public String getDataSourceListJsonLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
{
	String queryStr="SELECT ID,DB_SOURCE_ID,DB_SOURCE_NAME,DB_SOURCE_TYPE,DB_LINK,DB_USER_NAME,DB_USER_PASSWD,DB_NAME,CREATE_ACCOUNT_ID,CREATE_TIME,ORG_ID FROM WORK_FLOW_DATA_SOURCE  WHERE ORG_ID=?";
	String optStr= "[{'function':'edit','name':'修改','parm':'DB_SOURCE_ID'},{'function':'read','name':'查看','parm':'DB_SOURCE_ID'},{'function':'del','name':'删除','parm':'DB_SOURCE_ID'}]";
	JSONArray optArrJson = JSONArray.fromObject(optStr);
	PageTool pageTool = new PageTool();
	JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
	return json.toString();
}

}
