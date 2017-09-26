package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UitilityGraph {
	//获取历史审批的相关步骤图形
public String getHistoryGraphLogic(Connection conn,String runId) throws SQLException
{
	JSONArray jsonArr = new JSONArray();
	String queryStr="SELECT PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,CREATE_TIME,END_TIME,PASS,IDEA_TEXT,ATTACH_ID,RUN_PRCS_ID FROM FLOW_RUN_PRCS WHERE RUN_ID=? ORDER BY RUN_PRCS_ID  ASC";
	PreparedStatement ps = conn.prepareStatement(queryStr);
	ps.setString(1, runId);
	ResultSet rs = ps.executeQuery();
	int x =1;
	while(rs.next())
	{
		JSONObject json = new JSONObject();
		JSONObject jsonNode  = new JSONObject();
		JSONArray tmpArr = new JSONArray();
		tmpArr.add(rs.getInt("RUN_PRCS_ID")+1);
		json.accumulate("nextPrcs", tmpArr);
		jsonNode.accumulate("x", x*200+"");
		jsonNode.accumulate("y", 300+"");
		jsonNode.accumulate("sid", rs.getString("RUN_PRCS_ID"));
		jsonNode.accumulate("prcsName", rs.getString("PRCS_NAME"));
		jsonNode.accumulate("prcsId", rs.getString("RUN_PRCS_ID"));
		jsonNode.accumulate("createTime", rs.getString("CREATE_TIME"));
		jsonNode.accumulate("userName", rs.getString("USER_NAME"));
		if(SysTool.isNullorEmpty(rs.getString("IDEA_TEXT")))
		{
			jsonNode.accumulate("ideaText", "<span style=\"color: red;\">空！</span>");
		}else
		{
		jsonNode.accumulate("ideaText", rs.getString("IDEA_TEXT"));
		}
		if(rs.getString("PASS").equals("1"))
		{
			jsonNode.accumulate("pass", "同意！");
		}else if(rs.getString("PASS").equals("0"))
		{
			jsonNode.accumulate("pass", "不同意！");
		}else if(rs.getString("PASS").equals("2"))
		{
			jsonNode.accumulate("pass", "基本同意！");
		}
		if(SysTool.isNullorEmpty(rs.getString("END_TIME")))
		{
			jsonNode.accumulate("endTime", "");
		}else
		{
			jsonNode.accumulate("endTime", rs.getString("END_TIME"));
		}
		if(rs.getString("RUN_PRCS_ID").equals("1"))
		{
		jsonNode.accumulate("prcsType", "1");
		}else
		{
		jsonNode.accumulate("prcsType", "3");
		}
		
		jsonNode.accumulate("params", json);
		jsonArr.add(jsonNode);
		x++;
	}
	rs.close();
	ps.close();
	return jsonArr.toString();
}


}
