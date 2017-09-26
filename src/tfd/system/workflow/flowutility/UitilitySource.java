package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;
import tfd.system.workflow.workflowdatasource.data.WorkFlowDataSource;
import tfd.system.workflow.workflowdatasource.logic.WorkFlowDataSourceLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UitilitySource {
	//执行SQL返回查询结果
	public void getSqlQueryDataAct(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String returnData="";
		Connection dbConn = DbPoolConnection.getInstance().getConnection();
		Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
		String runId=request.getParameter("runId");
		String flowId=request.getParameter("flowId");
		String fieldName=request.getParameter("fieldname");
		String prcsId=request.getParameter("prcsId");
		FLowFormItemLogic  flowFormItemLogic = new FLowFormItemLogic();
		String xsqlModel=flowFormItemLogic.getXsqlLogic(dbConn, fieldName, flowId);
		JSONObject xsqlModelJson = new JSONObject();
		if(!SysTool.isNullorEmpty(xsqlModel))
		{
			xsqlModelJson=JSONObject.fromObject(xsqlModel);
		}
		if(!xsqlModelJson.equals(null))
		{
			String dbSourceId =xsqlModelJson.getString("dbsourceid");
			WorkFlowDataSourceLogic workFlowDataSourceLogic = new WorkFlowDataSourceLogic();
			WorkFlowDataSource workFlowDataSource = new WorkFlowDataSource();
			workFlowDataSource=workFlowDataSourceLogic.getWorkFlowDataSourceLogic(dbConn, dbSourceId);
			Connection sqlConn = this.getSqlConn(
					workFlowDataSource.getDbSourceType(), 
					workFlowDataSource.getDbLink(), 
					workFlowDataSource.getDbName(), 
					workFlowDataSource.getDbUserName(),
					workFlowDataSource.getDbUserPasswd()
					);
			String sql=xsqlModelJson.getString("sql");
			sql=this.getSqlHandle(dbConn, runId, flowId,prcsId,account, sql);
			returnData=this.getDbSourceLogic(sqlConn, sql);
			dbConn.commit();
		}
		dbConn.close();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnData);
		response.getWriter().flush();
	}
	
	
	//获取数据源
	public Connection getSqlConn(String dbSourceType,String dbLink, String dbName,String dbUserName, String dbUserPasswd) throws Exception 
	{
		Connection conn=null;
		String classforname = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		if(dbSourceType.equals("MSSQL"))
		{
			try
		    {
		      String url = "jdbc:microsoft:sqlserver://" + dbLink + ";DatabaseName=" + dbName;
		      Class.forName(classforname);
		      if ((conn == null) || (conn.isClosed()))
		        conn = DriverManager.getConnection(url, dbUserName, dbUserPasswd);
		    }
		    catch (ClassNotFoundException ex)
		    {
		    }
		    catch (SQLException ex)
		    {
		    }
		}
		return conn;
	}
	//处理来自工作流的SQL
	public String getDbSourceLogic(Connection conn,String sqlStr) throws SQLException
	{
		String tagflag="";
		JSONObject returnJson = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		ArrayList<String> filedList = new ArrayList<String>();
		if(conn!=null)
		{
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData data = rs.getMetaData();
		if(data.getColumnCount()>=1)
		{
			for(int i=1; data.getColumnCount()>=i;i++)
			{
				filedList.add(data.getColumnName(i));
			}
		}
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			for(int j=0;filedList.size()>j;j++)
			{
				json.accumulate(filedList.get(j), rs.getString(filedList.get(j)));
			}
			jsonArr.add(json);
		}
		if(filedList.size()>1)
		{
			tagflag="table";
		}else if (filedList.size()==1&&jsonArr.size()>1)
		{
			tagflag="select";
		}else if(filedList.size()==1&&jsonArr.size()==1)
		{
			tagflag="input";
		}
		returnJson.accumulate("tagflag", tagflag);
		returnJson.accumulate("tablefield", filedList);
		returnJson.accumulate("data", jsonArr);
		}
		return returnJson.toString();
	}
	//工作流系统常量定义
	public String getSqlHandle(Connection conn,String runId,String flowId,String prcsId,Account account,String sqlStr) throws SQLException
	{
		String returnData=sqlStr;
		if(!SysTool.isNullorEmpty(returnData))
		{
			returnData=returnData.replaceAll("`PRCS_ID`", prcsId);
			returnData=returnData.replaceAll("`RUN_ID`", runId);
			returnData=returnData.replaceAll("`FLOW_ID`", flowId);
			WorkFlowLogic workFlowLogic = new WorkFlowLogic();
			workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
			returnData=returnData.replaceAll("`FORM_ID`", workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId));
			returnData=returnData.replaceAll("`ACCOUNT_ID`", account.getAccountId());
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			userInfoLogic.getDeptId(conn, account.getAccountId());
			returnData=returnData.replaceAll("`DEPT_ID`", userInfoLogic.getDeptId(conn, account.getAccountId()));
			returnData=returnData.replaceAll("`USER_NAME`", userInfoLogic.getUserNameByAccountIdLogic(conn, account.getAccountId()));
			DeptLogic deptLogic = new DeptLogic();
			returnData=returnData.replaceAll("`DEPT_NAME`", deptLogic.getDeptNameShort(conn,userInfoLogic.getDeptId(conn, account.getAccountId())));
		}
		return returnData;
	}
}
