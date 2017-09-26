package institution.logic;

import institution.data.InstComment;
import institution.data.Institution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InstitutionLogic {
	/**
	 * 得到所有的制度列表
	 */
	public String getInstitutionList(Connection conn,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT INST_ID,INST_NAME,DIR_ID FROM INSTITUTION WHERE ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("INST_ID"));
			json.accumulate("pId", rs.getString("DIR_ID"));
			json.accumulate("name", rs.getString("INST_NAME"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据Id查询具体制度
	 */
	public String getInstitutionById(Connection conn,String id,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT INST_ID,INST_NAME,DIR_ID,INST_CONTENT,CREATE_TIME,(SELECT t2.USER_NAME FROM user_info t2 WHERE t2.ACCOUNT_ID = t1.ACCOUNT_ID AND t2.ORG_ID = t1.ORG_ID) AS USER_NAME,t1.ACCOUNT_ID,(SELECT t3.DIR_NAME FROM DIRECTORY t3 WHERE t3.DIR_ID = t1.DIR_ID) AS DIR_NAME,t1.ATTACH_ID FROM INSTITUTION t1 WHERE t1.INST_ID = ? AND t1.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, id);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("INST_ID"));
			json.accumulate("pId", rs.getString("DIR_ID"));
			json.accumulate("name", rs.getString("INST_NAME"));
			json.accumulate("content", rs.getString("INST_CONTENT"));
			json.accumulate("dirId", rs.getString("DIR_ID"));
			json.accumulate("dirName", rs.getString("DIR_NAME"));
			json.accumulate("time", rs.getString("CREATE_TIME"));
			json.accumulate("username", rs.getString("USER_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			json.accumulate("attachId", rs.getString("ATTACH_ID"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据Id删除制度
	 */
	public int delInstitutionById(Connection conn,String id,String orgId)throws Exception{
		String sql = "DELETE FROM INSTITUTION WHERE INST_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据Id修改制度内容
	 */
	public int updateInstitutionById(Connection conn,Institution inst,String orgId)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String sql = "";
		if(dbType.equals("mysql")){
			sql = "UPDATE INSTITUTION SET INST_NAME = ?,INST_CONTENT = ?,CREATE_TIME = sysdate(),DIR_ID = ?,ATTACH_ID = ?,ACCOUNT_ID = ? WHERE INST_ID = ?  AND ORG_ID = ?";
		}else if(dbType.equals("oracle")){
			sql = "UPDATE INSTITUTION SET INST_NAME = ?,INST_CONTENT = ?,CREATE_TIME = sysdate,DIR_ID = ?,ATTACH_ID = ?,ACCOUNT_ID = ? WHERE INST_ID = ?  AND ORG_ID = ?";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,inst.getInstName() );
		ps.setString(2,inst.getInstContent() );
		ps.setString(3,inst.getDirId() );
		ps.setString(4, inst.getAttachId());
		ps.setString(5,inst.getAccountId() );
		ps.setString(6,inst.getInstId() );
		ps.setString(7, orgId);
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 添加制度
	 */
	public int addInstitution(Connection conn,Institution inst)throws Exception{
		String dbType =DbPoolConnection.getInstance().getDbType();
		String queryStr = "";
		if(dbType.equals("mysql")){
			queryStr = "INSERT INTO INSTITUTION(INST_ID,INST_NAME,INST_CONTENT,CREATE_TIME,DIR_ID,ATTACH_ID,ACCOUNT_ID,ORG_ID) VALUES(?, ?, ?, sysdate(),? , ?,  ? ,?)";
		}else if(dbType.equals("oracle")){
			queryStr = "INSERT INTO INSTITUTION(INST_ID,INST_NAME,INST_CONTENT,CREATE_TIME,DIR_ID,ATTACH_ID,ACCOUNT_ID,ORG_ID) VALUES(?, ?, ?, sysdate,? , ?,  ? ,?)";
		}
		
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, inst.getInstId());
		int j = 2;
		String instName = inst.getInstName();
		String name = instName;
		while(checkInstitution(conn, name,inst.getDirId())){
			name = instName + "(" + j + ")";
			j++;
		}
		instName = name;
		ps.setString(2,instName );
		ps.setString(3,inst.getInstContent() );
		ps.setString(4,inst.getDirId() );
		ps.setString(5, inst.getAttachId());
		ps.setString(6,inst.getAccountId() );
		ps.setString(7, inst.getOrgId());
		int i = ps.executeUpdate();
		ps.close();
		return i;
		
	}
	
	/**
	 * 检查制度
	 */
	public boolean checkInstitution(Connection conn,String name,String dirId)throws Exception{
		boolean flag = false;
		String sql = "SELECT INST_NAME FROM INSTITUTION WHERE INST_NAME = ? AND DIR_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2,dirId );
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			flag = true;
		}else{
			flag = false;
		}
		rs.close();
		ps.close();
		return flag;
	}
	/**
	 * 普通搜索方法
	 */
	public String searchInstitution(Connection conn,String searchName,List<String> pramList,int pagesize,int page,String storOrder,String storName)throws Exception{
		String queryStr= null;
		if(searchName.equals("")){
			queryStr = "SELECT t1.INST_ID,t1.INST_NAME,t1.CREATE_TIME,(SELECT t2.USER_NAME FROM user_info t2 WHERE t2.ACCOUNT_ID = t1.ACCOUNT_ID AND t2.ORG_ID = t1.ORG_ID ) AS USER_NAME,t1.ACCOUNT_ID," +
					"(SELECT t3.ALL_DIR FROM DIRECTORY t3 WHERE t3.DIR_ID = t1.DIR_ID) AS ALL_DIR " +
					"FROM INSTITUTION t1 WHERE t1.ORG_ID = ? ";
		}else{
			queryStr = "SELECT t1.INST_ID,t1.INST_NAME,t1.CREATE_TIME,(SELECT t2.USER_NAME FROM user_info t2 WHERE t2.ACCOUNT_ID = t1.ACCOUNT_ID AND t2.ORG_ID = t1.ORG_ID ) AS USER_NAME,t1.ACCOUNT_ID," +
					"(SELECT t3.ALL_DIR  FROM DIRECTORY t3 WHERE t3.DIR_ID = t1.DIR_ID) AS ALL_DIR " +
					"FROM INSTITUTION t1 WHERE t1.INST_ID like '%"+searchName+"%' or t1.INST_NAME like '%"+searchName+"%' AND t1.ORG_ID = ?  ";
		}
		String optStr= "[{'function':'showInst','name':'查看','parm':'INST_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	
	/**
	 * 高级搜索方法
	 */
	public String searchInstitution2(Connection conn,List<String> pramList,String dirName,String userName,String beginTime,String endTime,int pagesize,int page,String storOrder,String storName)throws Exception{
		String queryStr= "SELECT t1.INST_ID,t1.INST_NAME,t1.CREATE_TIME,(SELECT t2.USER_NAME FROM user_info t2 WHERE t2.ACCOUNT_ID = t1.ACCOUNT_ID AND t2.ORG_ID = t1.ORG_ID ) AS USER_NAME,t1.ACCOUNT_ID," +
				"(SELECT t3.ALL_DIR  FROM DIRECTORY t3 WHERE t3.DIR_ID = t1.DIR_ID) AS ALL_DIR " +
				"FROM INSTITUTION t1 WHERE 1 = 1 ";
		if(!dirName.equals("")){
			queryStr += " AND t5.ALL_DIR LIKE '%" + dirName + "%'";
		}
		if(!userName.equals("")){
			queryStr += " AND t4.USER_NAME LIKE '%" + userName + "%'";
		}
		if(!beginTime.equals("")){
			queryStr += " AND t1.CREATE_TIME > '" + beginTime + "'";
		}
		if(!endTime.equals("")){
			queryStr += " AND t1.CREATE_TIME < '" + endTime + "'";
		}
		queryStr += " AND t1.ORG_ID = ? ";
		String optStr= "[{'function':'showInst','name':'查看','parm':'INST_ID'}]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	/**
	 * 根据制度Id得到修订建议
	 */
	public String getInstCommentByInstId(Connection conn,String instId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray(); 
		ResultSet rs =null;
		String queryStr="SELECT t1.COM_ID,t1.COM_PID,t1.COM_CONTENT,t1.COM_TIME,"
				+ "(SELECT t2.USER_NAME FROM user_info t2 WHERE ACCOUNT_ID = t1.ACCOUNT_ID AND t2.ORG_ID = t1.ORG_ID  ) AS USER_NAME "
				+ ",t1.ACCOUNT_ID FROM inst_comment t1 WHERE t1.INST_ID = ? AND t1.ORG_ID = ?";
		queryStr += " ORDER BY t1.COM_TIME DESC ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, instId);
		ps.setString(2, orgId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("COM_ID"));
			json.accumulate("pId", rs.getString("COM_PID"));
			json.accumulate("content", rs.getString("COM_CONTENT"));
			json.accumulate("username", rs.getString("USER_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			String allTime = rs.getString("COM_TIME");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date time1 = formatter.parse(allTime);
			Date nowTime = new Date();
			long temp = nowTime.getTime() - time1.getTime();
			String time = "";
			if(temp < 60000){
				long sounds = temp / 1000;
				time = sounds+"秒前";
			}else if(temp >= 60000 && temp < 3600000){
				long mins = temp / 60 / 1000;
				time = mins+"分钟前";
			}else if(temp >= 3600000 && temp < 86400000){
				long hours = temp / 3600 / 1000;
				time = hours+"小时前";
			}else if(temp >= 86400000 && temp < 172800000){
				time = "昨天";
			}else if(temp >= 172800000){
				time = allTime.substring(0,10);
			}
			json.accumulate("time", time);
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 添加修订建议
	 */
	public int addInstComment(Connection conn,InstComment com)throws Exception{
		String sql = "INSERT INTO INST_COMMENT(COM_ID,COM_PID,COM_CONTENT,COM_TIME,INST_ID,ACCOUNT_ID,ORG_ID) VALUES(?, ?, ?, sysdate(), ?, ? ,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, com.getComId());
		ps.setString(2,com.getComPid());
		ps.setString(3,com.getComContent());
		ps.setString(4,com.getInstId());
		ps.setString(5,com.getAccountId());
		ps.setString(6, com.getOrgId());
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
}
