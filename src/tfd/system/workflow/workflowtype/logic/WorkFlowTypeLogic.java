package tfd.system.workflow.workflowtype.logic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.workflow.workflowtype.data.WorkFlowType;
public class WorkFlowTypeLogic {
	//新建流程分类
	public void addWorkFlowTypeLogic(Connection conn,WorkFlowType workFlowType) throws SQLException
	{
		String queryStr="INSERT INTO WORK_FLOW_TYPE(FLOW_TYPE_ID,FLOW_TYPE_NAME,MODULE_ID,MANAGE_ACCOUNT_ID,LEAVE_ID,TOP_FLAG,ORG_ID,SORT_ID)VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, workFlowType.getFlowTypeId());
		ps.setString(2, workFlowType.getFlowTypeName());
		ps.setString(3, workFlowType.getModuleId());
		ps.setString(4, workFlowType.getManageAccountId());
		ps.setString(5, workFlowType.getLeaveId());
		ps.setString(6, workFlowType.getTopFlag());
		ps.setString(7, workFlowType.getOrgId());
		ps.setString(8, workFlowType.getSortId());
		ps.executeUpdate();
		ps.close();
	}
	//更新流程分类
	public void updateWorkFlowTypeLogic(Connection conn,WorkFlowType workFlowType) throws SQLException
	{
		String queryStr="UPDATE WORK_FLOW_TYPE SET FLOW_TYPE_NAME=?,MODULE_ID=?,MANAGE_ACCOUNT_ID=?,LEAVE_ID=?,TOP_FLAG=?,ORG_ID=?,SORT_ID=? WHERE FLOW_TYPE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, workFlowType.getFlowTypeName());
		ps.setString(2, workFlowType.getModuleId());
		ps.setString(3, workFlowType.getManageAccountId());
		ps.setString(4, workFlowType.getLeaveId());
		ps.setString(5, workFlowType.getTopFlag());
		ps.setString(6, workFlowType.getOrgId());
		ps.setString(7, workFlowType.getSortId());
		ps.setString(8, workFlowType.getFlowTypeId());
		ps.executeUpdate();
		ps.close();
		
	}
	//获取流程分类json list
	public String getWorkFlowTypeListJson(Connection conn,Account account) throws SQLException{
			 ResultSet rs = null;
			 String queryStr="";
			 int count=0;
			 String tmpStr="";
			 String optStr="";
			 PreparedStatement ps=null;
			 StringBuffer sb = new StringBuffer();
			queryStr="SELECT COUNT(*) FROM WORK_FLOW_TYPE WHERE MANAGE_ACCOUNT_ID='"+account.getAccountId()+"'";
			ps=conn.prepareStatement(queryStr);
			rs=ps.executeQuery();
			if(rs.next())
			{
				count=rs.getInt(1);
			}
			queryStr="SELECT ID,FLOW_TYPE_ID,FLOW_TYPE_NAME,MODULE_ID,SORT_ID FROM WORK_FLOW_TYPE WHERE MANAGE_ACCOUNT_ID='"+account.getAccountId()+"'";
			ps=conn.prepareStatement(queryStr);
			 try{
				  rs=ps.executeQuery();
				  sb.append("{Rows:[");
				  while(rs.next())
				  {
					  optStr="<a href='javascript:edit("+rs.getString("ID")+")'>编辑</a>  <a href='javascript:del("+rs.getString("ID")+")'>删除</a>";
					  tmpStr=tmpStr+"{\"Id\":\""+rs.getString("ID")+"\","+
							  "\"flowTypeName\":\""+rs.getString("FLOW_TYPE_NAME")+"\","+
							  "\"moduleId\":\""+rs.getString("MODULE_ID")+"\","+
							  "\"sortId\":\""+rs.getString("SORT_ID")+"\","+
							  "\"opt\":\""+optStr+"\""+
							  "},";
				  }
			if(tmpStr.length()>0)
			{
			tmpStr=tmpStr.substring(0, tmpStr.length()-1);
			}
		  sb.append(tmpStr);
		  sb.append("],Total:"+count+"}");
		  rs.close();
		  ps.close();
			 }catch (Exception e) {
				  e.printStackTrace();
			 
		}
			 return sb.toString();
		}
	//获取流程分类树
	public String getWorkFlowTypeTreeLogic(Connection conn,Account account) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT FLOW_TYPE_ID,FLOW_TYPE_NAME,LEAVE_ID FROM WORK_FLOW_TYPE WHERE ORG_ID=?";
		 ResultSet rs = null;
		 PreparedStatement ps=conn.prepareStatement(queryStr);
		 ps.setString(1, account.getOrgId());
		 rs = ps.executeQuery();
		 while(rs.next())
		 {
			 JSONObject json = new JSONObject();
			  json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
			  json.accumulate("pId", rs.getString("LEAVE_ID"));
			  json.accumulate("name", rs.getString("FLOW_TYPE_NAME"));
			  json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
			  json.accumulate("isParent", "true");
			  jsonArr.add(json);
		 }
		 rs.close();
		 ps.close();
		 return jsonArr.toString();
	}
	
	//
//	public String getWorkFlowTypeSelectLogic(Connection conn,Account account) throws SQLException
//	{
//		String queryStr="SELECT FLOW_TYPE_ID,FLOW_TYPE_NAME FROM WORK_FLOW_TYPE WHERE MANAGE_ACCOUNT_ID='"+account.getAccountId()+"' " +
//				" AND MODULE_ID='workflow' AND ORG_ID='"+account.getOrgId()+"'";
//		 ResultSet rs = null;
//		 PreparedStatement ps=conn.prepareStatement(queryStr);
//		 JSONArray jsonArr = new JSONArray();
//		 rs=ps.executeQuery();
//		 while(rs.next())
//		 {
//			 JSONObject json = new JSONObject();
//			 json.accumulate("text", rs.getString("FLOW_TYPE_NAME"));
//			 json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
//			 jsonArr.add(json);
//		 }
//			rs.close();
//			ps.close();
//			return jsonArr.toString();
//	}
	//根据WORKFLOWTYPEID获取分类名称
	public String getWorkFlowTypeNameLogic(Connection conn, String workFlowTypeId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT FLOW_TYPE_NAME FROM WORK_FLOW_TYPE WHERE FLOW_TYPE_ID=?";
		ResultSet rs=null;
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, workFlowTypeId);
		rs = ps.executeQuery();
		while(rs.next())
		{
			returnData=rs.getString("FLOW_TYPE_NAME");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//根据WORKFLOWTYPEID获取分类信息
	public String getWorkFlowTypeInfoLogic(Connection conn, String workFlowTypeId) throws SQLException
	{
		JSONObject json = new JSONObject();
		String queryStr="SELECT FLOW_TYPE_ID,FLOW_TYPE_NAME,MODULE_ID,MANAGE_ACCOUNT_ID,F.USER_NAME AS USER_NAME,LEAVE_ID,TOP_FLAG,W.ORG_ID AS ORG_ID,W.SORT_ID AS SORT_ID FROM WORK_FLOW_TYPE W,USER_INFO F"
				+ " WHERE W.MANAGE_ACCOUNT_ID=F.ACCOUNT_ID AND FLOW_TYPE_ID=?";
		ResultSet rs=null;
		PreparedStatement ps=conn.prepareStatement(queryStr);
		ps.setString(1, workFlowTypeId);
		rs = ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("flowTypeId", rs.getString("FLOW_TYPE_ID"));
			json.accumulate("flowTypeName", rs.getString("FLOW_TYPE_NAME"));
			json.accumulate("moduleId", rs.getString("MODULE_ID"));
			json.accumulate("manageAccountId", rs.getString("MANAGE_ACCOUNT_ID"));
			json.accumulate("manageAccountName", rs.getString("USER_NAME"));
			json.accumulate("leaveId", rs.getString("LEAVE_ID"));
			json.accumulate("topFlag", rs.getString("TOP_FLAG"));
			json.accumulate("orgId", rs.getString("ORG_ID"));
			json.accumulate("sortId", rs.getString("SORT_ID"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	//删除流程分类
	public void deleteWorkFlowTypeLoigc(Connection conn,String flowTypeId) throws SQLException
	{
		boolean delFlag=false;
		String queryStr="SELECT LEAVE_ID FROM WORK_FLOW_TYPE WHERE LEAVE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			delFlag=true;
		}
		rs.close();
		if(!delFlag)
		{
			queryStr="DELETE FROM WORK_FLOW_TYPE WHERE FLOW_TYPE_ID=?";
			ps = conn.prepareStatement(queryStr);
			ps.setString(1, flowTypeId);
			ps.executeUpdate();
		}
		ps.close();
	}
}
