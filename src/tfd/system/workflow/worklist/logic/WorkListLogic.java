package tfd.system.workflow.worklist.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.worklist.data.WorkList;

public class WorkListLogic {
	//
	public String getFlowWorkCountLogic(Connection conn,Account account) throws SQLException
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT COUNT(*) AS ZS FROM WORK_LIST  WHERE ACCOUNT_ID=? AND MODULE=? AND STATUS=? AND DEL_FLAG=? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1,"admin");
		ps.setString(2, "workflow");
		ps.setString(3, "0");
		ps.setString(4, "0");
		//ps.setString(5, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("zs", rs.getString("ZS"));
		}else
		{
			json.accumulate("zs", "0");
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	//获取待办记录实例
	public WorkList getWorkListLogic(Connection conn,String runId,int prcsId,int runPrcsId) throws SQLException
	{
		WorkList workList = new WorkList();
		String queryStr="SELECT TITLE,RUN_ID,MODULE,ACCOUNT_ID,READ_URL,URL,STATUS,CREATE_TIME,END_TIME,PRCS_ID,RUN_PRCS_ID,DEL_FLAG,PRCS_NAME,ORG_ID "
				+ "FROM WORK_LIST "
				+ "WHERE RUN_ID=? AND PRCS_ID=? AND RUN_PRCS_ID=? AND MODULE=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.setInt(2, prcsId);
		ps.setInt(3, runPrcsId);
		ps.setString(4, "workflow");
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			workList.setTitle(rs.getString("TITLE"));
			workList.setRunId(rs.getString("RUN_ID"));
			workList.setModule(rs.getString("MODULE"));
			workList.setAccountId(rs.getString("ACCOUNT_ID"));
			workList.setReadUrl(rs.getString("READ_URL"));
			workList.setUrl(rs.getString("URL"));
			workList.setStatus(rs.getString("STATUS"));
			workList.setCreateTime(rs.getString("CREATE_TIME"));
			workList.setEndTime(rs.getString("END_TIME"));
			workList.setPrcsId(rs.getInt("PRCS_ID"));
			workList.setRunPrcsId(rs.getInt("RUN_PRCS_ID"));
			workList.setDelFlag(rs.getString("DEL_FLAG"));
			workList.setPrcsName(rs.getString("PRCS_NAME"));
			workList.setOrgId(rs.getString("ORG_ID"));
		}
		return workList;
	}
	//获取待办
	public String getDoWorkListLogic(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName,Map<String,String> map) throws Exception
	{
		String whereStr="";
		if(!SysTool.isNullorEmpty(map.get("flowName")))
		{
			whereStr+=" AND F.FLOW_ID IN(SELECT FLOW_TYPE_ID FROM WORK_FLOW WHERE FLOW_NAME LIKE '%"+map.get("flowName")+"%')";
		}
		if(!SysTool.isNullorEmpty(map.get("runId")))
		{
			whereStr+=" AND W.RUN_ID='"+map.get("runId")+"'";
		}
		if(!SysTool.isNullorEmpty(map.get("beginUser")))
		{
			whereStr+=" AND F.ACCOUNT_ID='"+map.get("beginUser")+"'";
		}
		if(!SysTool.isNullorEmpty(map.get("flowTitle")))
		{
			whereStr+=" AND W.TITLE LIKE '%"+map.get("flowTitle")+"%'";
		}
		String queryStr="SELECT W.ID AS ID,W.TITLE AS TITLE,W.MODULE AS MODULE,W.RUN_ID AS RUN_ID,W.ACCOUNT_ID AS ACCOUNT_ID,W.RUN_PRCS_ID AS RUN_PRCS_ID,"
				+ "W.URL AS URL,W.STATUS AS STATUS,W.CREATE_TIME AS CREATE_TIME,W.END_TIME AS END_TIME,W.READ_URL AS READ_URL,F.PRCS_NAME AS PRCS_NAME,"
				+ " (SELECT USER_NAME FROM USER_INFO U WHERE U.ACCOUNT_ID=F.TRANSFER_USER) AS TRANSFER_USER_NAME"
				+ " FROM WORK_LIST W,FLOW_RUN_PRCS F  "
				+	" WHERE W.RUN_ID=F.RUN_ID "
				+ whereStr
				+ " AND W.PRCS_ID=F.PRCS_ID AND W.ACCOUNT_ID=F.ACCOUNT_ID AND W.ACCOUNT_ID=? AND F.STATUS=0"
				+ " AND W.MODULE=? AND W.ORG_ID=? AND W.STATUS=0 AND W.DEL_FLAG='0' ";
		String optStr= "[{'function':'dowork','name':'办理','parm':'RUN_ID,URL'},{'function':'read','name':'查看','parm':'RUN_ID,READ_URL'},{'function':'flowView','name':'流程图','parm':'RUN_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	//创建新待办记录
	public int createDoRecordLogic(Connection conn,WorkList workList) throws SQLException{
		String queryStr="INSERT INTO WORK_LIST (" +
				"				TITLE,RUN_ID,MODULE,ACCOUNT_ID,URL," +
				"				STATUS,CREATE_TIME,ORG_ID,PRCS_ID," +
				"				READ_URL,RUN_PRCS_ID," +
				"				DEL_FLAG,PRCS_NAME" +
				"		) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, workList.getTitle());
		ps.setString(2, workList.getRunId());
		ps.setString(3, workList.getModule());
		ps.setString(4, workList.getAccountId());
		ps.setString(5,	workList.getUrl());
		ps.setString(6, workList.getStatus());
		ps.setString(7,	workList.getCreateTime());
		ps.setString(8, workList.getOrgId());
		ps.setInt(9, workList.getPrcsId());
		ps.setString(10, workList.getReadUrl());
		ps.setInt(11, workList.getRunPrcsId());
		ps.setString(12, workList.getDelFlag());
		ps.setString(13, workList.getPrcsName());
		int returnData = ps.executeUpdate();
		ps.close();
		return returnData;
	}
	
	public void updateStatusForceLogic(Connection conn,String runId,int prcsId,int runPrcsId) throws SQLException
	{
		String queryStr="UPDATE WORK_LIST SET STATUS='1',END_TIME=? WHERE RUN_ID=? AND PRCS_ID=? AND RUN_PRCS_ID=? AND MODULE='workflow' ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(2, runId);
		ps.setInt(3, prcsId);
		ps.setInt(4, runPrcsId);
		ps.executeUpdate();
		ps.close();
	}
	
	
//修改流程步骤状态
	public void updateStatusLogic(Connection conn,String runId,int runPrcsId,String accountId) throws SQLException
	{
		String queryStr="UPDATE WORK_LIST SET STATUS='1',END_TIME=? WHERE RUN_ID=? AND RUN_PRCS_ID=? AND ACCOUNT_ID=? AND MODULE='workflow' ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(2, runId);
		ps.setInt(3, runPrcsId);
		ps.setString(4, accountId);
		ps.executeUpdate();
		ps.close();
	}
//获取标题
	public String getTitle(Connection conn,String runId) throws SQLException
	{
		String returnData="";
		ResultSet rs=null;
		String queryStr="SELECT TITLE FROM WORK_LIST WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("TITLE");
		}
		ps.close();
		return returnData;
	}
//获取过程中流程
	public String getProcessLogic(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName, Map<String,String> map) throws Exception
	{
		String whereStr="";
		if(!SysTool.isNullorEmpty(map.get("flowName")))
		{
			whereStr+=" AND FLOW_ID IN(SELECT FLOW_TYPE_ID FROM WORK_FLOW WHERE FLOW_NAME LIKE '%"+map.get("flowName")+"%')";
		}
		if(!SysTool.isNullorEmpty(map.get("runId")))
		{
			whereStr+=" AND RUN_ID='"+map.get("runId")+"'";
		}
		if(!SysTool.isNullorEmpty(map.get("beginUser")))
		{
			whereStr+=" AND FLOW_ID IN(SELECT FLOW_ID FROM FLOW_RUN_PRCS WHERE ACCOUNT_ID ='"+map.get("beginUser")+"' AND RUN_PRCS_ID=1)";
		}
		if(!SysTool.isNullorEmpty(map.get("flowTitle")))
		{
			whereStr+=" AND TITLE LIKE '%"+map.get("flowTitle")+"%'";
		}
		String queryStr="";
		String dbType= DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT ID,RUN_ID,TITLE,OP_USER_STR,BEGIN_TIME,PATH FROM FLOW_RUN "
					+ "WHERE STATUS='0' "+whereStr+" "
					+ "AND DEL_FLAG='0' "
					+ "AND ORG_ID=? "
					+ "AND MODULE=? "
					+ "AND CONCAT(',',OP_USER_STR,',') LIKE ?";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT ID,RUN_ID,TITLE,OP_USER_STR,BEGIN_TIME,PATH FROM FLOW_RUN "
					+ "WHERE STATUS='0' "+whereStr+" "
					+ "AND DEL_FLAG='0' "
					+ "AND ORG_ID=? "
					+ "AND MODULE=? "
					+ "AND CONCAT(',',OP_USER_STR)||',' LIKE ?";
		}
		String optStr= "[{'function':'read','name':'查看','parm':'RUN_ID,PATH'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
		}
	//获取结束流程
	public String getEndWorkLogic(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName,Map<String,String> map) throws Exception
	{
		String whereStr="";
		if(!SysTool.isNullorEmpty(map.get("flowName")))
		{
			whereStr+=" AND FLOW_ID IN(SELECT FLOW_TYPE_ID FROM WORK_FLOW WHERE FLOW_NAME LIKE '%"+map.get("flowName")+"%')";
		}
		if(!SysTool.isNullorEmpty(map.get("runId")))
		{
			whereStr+=" AND RUN_ID='"+map.get("runId")+"'";
		}
		if(!SysTool.isNullorEmpty(map.get("beginUser")))
		{
			whereStr+=" AND FLOW_ID IN(SELECT FLOW_ID FROM FLOW_RUN_PRCS WHERE ACCOUNT_ID ='"+map.get("beginUser")+"' AND RUN_PRCS_ID=1)";
		}
		if(!SysTool.isNullorEmpty(map.get("flowTitle")))
		{
			whereStr+=" AND TITLE LIKE '%"+map.get("flowTitle")+"%'";
		}
		String queryStr="";
		String dbType= DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql"))
		{
			queryStr="SELECT ID,RUN_ID,TITLE,OP_USER_STR,BEGIN_TIME,PATH "
					+ "FROM FLOW_RUN "
					+ "WHERE STATUS='1' "+whereStr+" "
					+ "AND DEL_FLAG='0' "
					+ "AND ORG_ID=? "
					+ "AND MODULE=? "
					+ "AND CONCAT(',',OP_USER_STR,',') LIKE ?";
		}else if(dbType.equals("sqlserver"))
		{
			
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT ID,RUN_ID,TITLE,OP_USER_STR,BEGIN_TIME,PATH "
					+ "FROM FLOW_RUN "
					+ "WHERE STATUS='1' "+whereStr+" "
					+ "AND DEL_FLAG='0' "
					+ "AND ORG_ID=? "
					+ "AND MODULE=? "
					+ "AND CONCAT(',',OP_USER_STR)||',' LIKE ?";
		}
		String optStr= "[{'function':'read','name':'查看','parm':'RUN_ID,PATH'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
		}
	//获取前5条待办
	public String getFristWorkFlowLogic(Connection conn,Account account) throws SQLException
	{
		String dbType =DbPoolConnection.getInstance().getDbType();
		JSONArray jsonArr = new JSONArray();
		String queryStr="";
		if(dbType.equals("sqlserver"))
		{
			queryStr="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY A.CREATE_TIME ASC) AS RB, A.RUN_ID AS RUN_ID,A.TITLE AS TITLE,A.URL AS URL,A.CREATE_TIME AS CREATE_TIME,B.PRCS_NAME AS PRCS_NAME FROM WORK_LIST A,FLOW_RUN_PRCS B "
					+ "WHERE "
					+ "A.ACCOUNT_ID=? "
					+ "AND A.RUN_ID=B.RUN_ID "
					+ "AND A.PRCS_ID=B.PRCS_ID "
			        + "AND A.STATUS = B.STATUS "
			        + "AND A.STATUS=? "
			        + "AND A.MODULE=? "
					+ "AND A.DEL_FLAG=? "
					+ "AND  A.ORG_ID=? "
					+ ") T ";
		}else if(dbType.equals("mysql"))
		{
		queryStr="SELECT A.RUN_ID AS RUN_ID,A.TITLE AS TITLE,A.URL AS URL,A.CREATE_TIME AS CREATE_TIME,B.PRCS_NAME AS PRCS_NAME FROM WORK_LIST A,FLOW_RUN_PRCS B "
				+ "WHERE "
				+ "A.ACCOUNT_ID=? "
				+ "AND A.RUN_ID=B.RUN_ID "
				+ "AND A.PRCS_ID=B.PRCS_ID "
		        + "AND A.STATUS = B.STATUS "
		        + "AND A.STATUS=? "
		        + "AND A.MODULE=? "
				+ "AND A.DEL_FLAG=? "
				+ "AND  A.ORG_ID=? "
				+ "LIMIT 0,5";
		}else if(dbType.equals("oracle"))
		{
			queryStr="SELECT * FROM(SELECT A.RUN_ID AS RUN_ID,A.TITLE AS TITLE,A.URL AS URL,A.CREATE_TIME AS CREATE_TIME,B.PRCS_NAME AS PRCS_NAME FROM WORK_LIST A,FLOW_RUN_PRCS B "
					+ "WHERE "
					+ "A.ACCOUNT_ID=? "
					+ "AND A.RUN_ID=B.RUN_ID "
					+ "AND A.PRCS_ID=B.PRCS_ID "
			        + "AND A.STATUS = B.STATUS "
			        + "AND A.STATUS=? "
			        + "AND A.MODULE=? "
					+ "AND A.DEL_FLAG=? "
					+ "AND  A.ORG_ID=? "
					+ "ORDER BY A.ID DESC ) TMP WHERE ROWNUM<=5";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, "0");
		ps.setString(3, "workflow");
		ps.setString(4, "0");
		ps.setString(5, account.getOrgId());
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("RUN_ID", rs.getString("RUN_ID"));
			json.accumulate("TITLE", rs.getString("TITLE"));
			json.accumulate("URL", rs.getString("URL"));
			json.accumulate("CREATE_TIME", rs.getString("CREATE_TIME"));
			json.accumulate("PRCS_NAME", rs.getString("PRCS_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
