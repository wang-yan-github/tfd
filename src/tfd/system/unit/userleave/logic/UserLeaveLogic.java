package tfd.system.unit.userleave.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.userleave.data.UserLeave;

import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class UserLeaveLogic {
	public String getLeaveNameLogic(Connection conn,String leaveId,String orgId) throws SQLException
	{
		String returnData="";
		/*PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, leaveId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("LEAVE_NAME");
		}*/
		String inAccountIdStr="";
		if(!SysTool.isNullorEmpty(leaveId)){
			if(leaveId.indexOf(",")>0)
			{
				String[] accounts=leaveId.split(",");
				for(int i=0;accounts.length>i;i++)
				{
					inAccountIdStr+="'"+accounts[i]+"',";
				}
				if(inAccountIdStr.length()>0)
				{
					inAccountIdStr=inAccountIdStr.substring(0, inAccountIdStr.length()-1);
				}
				String queryStr="SELECT LEAVE_NAME FROM USER_LEAVE WHERE LEAVE_ID in ("+inAccountIdStr+") AND ORG_ID=?";
				PreparedStatement ps  = conn.prepareStatement(queryStr);
				ps.setString(1, orgId);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					returnData+=rs.getString("LEAVE_NAME")+",";
				}
				rs.close();
				ps.close();
				if(returnData.length()>0)
				{
					returnData=returnData.substring(0, returnData.length()-1);
				}
			}else
			{
				inAccountIdStr=leaveId;
				String queryStr="SELECT LEAVE_NAME FROM USER_LEAVE WHERE LEAVE_ID in (?) AND ORG_ID=?";
				PreparedStatement ps  = conn.prepareStatement(queryStr);
				ps.setString(1, leaveId);
				ps.setString(2, orgId);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					returnData+=rs.getString("LEAVE_NAME")+",";
				}
				rs.close();
				ps.close();
				if(returnData.length()>0)
				{
					returnData=returnData.substring(0, returnData.length()-1);
				}
			}
		}
		return returnData;
	}
	
	public int addUserLeaveLogic(Connection conn,UserLeave userLeave) throws SQLException
	{
		String queryStr="INSERT INTO USER_LEAVE (LEAVE_ID,LEAVE_NO_ID,LEAVE_NAME,MIDDING,ORG_ID)VALUES(?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(queryStr);
		ps.setString(1, userLeave.getLeaveId());
		ps.setString(2, userLeave.getLeaveNoId());
		ps.setString(3, userLeave.getLeaveName());
		ps.setString(4, userLeave.getMidding());
		ps.setString(5, userLeave.getOrgId());
		int i =ps.executeUpdate();
		ps.close();
		return i;
	}
	
	public int updateUserLeaveLogic(Connection conn,UserLeave userLeave) throws SQLException
	{
		String queryStr="UPDATE USER_LEAVE SET LEAVE_NO_ID=?,LEAVE_NAME=?,MIDDING=? WHERE LEAVE_ID=? AND ORG_ID=?";
		PreparedStatement ps  = conn.prepareStatement(queryStr);
		ps.setString(1, userLeave.getLeaveNoId());
		ps.setString(2, userLeave.getLeaveName());
		ps.setString(3, userLeave.getMidding());
		ps.setString(4, userLeave.getLeaveId());
		ps.setString(5, userLeave.getOrgId());
		int i =ps.executeUpdate();
		ps.close();
		return i;
	}
	public String getUserLeaveSelectLogic(Connection conn,String orgId) throws SQLException
	{
		JSONArray jsonArr = new JSONArray();
		String queryStr="SELECT LEAVE_ID,LEAVE_NAME FROM USER_LEAVE WHERE ORG_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			json.accumulate("id", rs.getString("LEAVE_ID"));
			json.accumulate("text", rs.getString("LEAVE_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	public String getUserLeaveListLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		String queryStr="SELECT ID,LEAVE_ID,LEAVE_NO_ID,LEAVE_NAME,MIDDING FROM USER_LEAVE WHERE ORG_ID=?";
		String optStr= "[{'function':'setleave','name':'修改','parm':'LEAVE_ID'}]";
		JSONArray optArrJson = new JSONArray().fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	
	public String getUserLeaveJsonLogic(Connection conn,String leaveId,String orgId) throws SQLException
	{
		String queryStr="SELECT ID,LEAVE_ID,LEAVE_NO_ID,LEAVE_NAME,MIDDING FROM USER_LEAVE WHERE LEAVE_ID=? AND ORG_ID=?";
		JSONObject json = new JSONObject();
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, leaveId);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			json.accumulate("leaveId", rs.getString("LEAVE_ID"));
			json.accumulate("leaveNoId", rs.getString("LEAVE_NO_ID"));
			json.accumulate("leaveName", rs.getString("LEAVE_NAME"));
			json.accumulate("midding", rs.getString("MIDDING"));
		}
		return json.toString();
	}
	public int delByLeaveIdLogic(Connection conn,String leaveId) throws SQLException
	{
		String queryStr="DELETE FROM USER_LEAVE WHERE LEAVE_ID=?";
		int i=0;
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, leaveId);
		i=ps.executeUpdate();
		return i;
	}
	
	public String getLeaveNoIdLogic(Connection conn,String leaveId) throws SQLException
	{
		String returnData="";
		String queryStr="SELECT LEAVE_NO_ID FROM USER_LEAVE WHERE LEAVE_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, leaveId);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			returnData=rs.getString("LEAVE_NO_ID");
		}
		rs.close();
		ps.close();
		return returnData;
	}
	//获取json数组类型的 兼职信息
	public String getjsonleaveLogic(Connection conn,String leaveIdStr)throws SQLException{
		String queryStr="";
		JSONArray jsonArr = new JSONArray();
		if(SysTool.isNullorEmpty(leaveIdStr))
		{
			return jsonArr.toString();
		}else
		{
			leaveIdStr = leaveIdStr.replaceAll(",", "','");
			queryStr="SELECT LEAVE_ID,LEAVE_NAME FROM USER_LEAVE WHERE LEAVE_ID IN ('"+leaveIdStr+"')";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			JSONObject json=new JSONObject();
			json.accumulate("leaveId", rs.getString("LEAVE_ID"));
			json.accumulate("leaveName",rs.getString("LEAVE_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	public String getLevelIdByLevelName(String levelName,String orgId,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		BaseDao dao=new BaseDaoImpl();
		try {
			String sql="select leave_id from user_leave where leave_name=? and org_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, levelName);
			stmt.setString(2, orgId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("leave_id");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
}
