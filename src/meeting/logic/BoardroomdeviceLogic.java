package meeting.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import meeting.data.Boardroomdevice;


public class BoardroomdeviceLogic {

	/**
	 * 添加设备信息
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @return
	 * @throws SQLException
	 */
	public int adddevicelogic(Connection conn,Boardroomdevice boardroomdevice)throws SQLException{
		String queryStr="INSERT INTO BOARDROOMDEVICE (BOARDROOMDEVICE_ID,DEVICE_ID,DEVICE_NAME,BOARDROOM_ID,DEVICE_STATUS,DEVICE_SIMILAR,DEVICE_DESCRIPTION,DEVICE_TYPE,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomdevice.getBoardroomdeviceId());
		ps.setString(2, boardroomdevice.getDeviceId());
		ps.setString(3, boardroomdevice.getDeviceName());
		ps.setString(4, boardroomdevice.getBoardroomId());
		ps.setString(5, boardroomdevice.getDeviceStatus());
		ps.setString(6, boardroomdevice.getDeviceSimilar());
		ps.setString(7, boardroomdevice.getDeviceDescription());
		ps.setString(8, boardroomdevice.getDevicetype());
		ps.setString(9, boardroomdevice.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 修改设备信息
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @return
	 * @throws SQLException
	 */
	public int updatedevicelogic(Connection conn,Boardroomdevice boardroomdevice)throws SQLException{
		String queryStr="UPDATE BOARDROOMDEVICE SET DEVICE_ID =?,DEVICE_NAME =?,BOARDROOM_ID =?,DEVICE_STATUS =?,DEVICE_SIMILAR =? ,DEVICE_DESCRIPTION=?,DEVICE_TYPE=? WHERE BOARDROOMDEVICE_ID =? AND ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomdevice.getDeviceId());
		ps.setString(2, boardroomdevice.getDeviceName());
		ps.setString(3, boardroomdevice.getBoardroomId());
		ps.setString(4, boardroomdevice.getDeviceStatus());
		ps.setString(5, boardroomdevice.getDeviceSimilar());
		ps.setString(6, boardroomdevice.getDeviceDescription());
		ps.setString(7, boardroomdevice.getDevicetype());
		ps.setString(8, boardroomdevice.getBoardroomdeviceId());
		ps.setString(9, boardroomdevice.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 删除设备信息
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @return
	 * @throws SQLException
	 */
	public int deldevicelogic(Connection conn,Boardroomdevice boardroomdevice)throws SQLException{
		String queryStr=" DELETE FROM BOARDROOMDEVICE WHERE BOARDROOMDEVICE_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomdevice.getBoardroomdeviceId());
		ps.setString(2, boardroomdevice.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据ID查询设备信息
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @return
	 * @throws SQLException
	 */
	public String getIddevicelogic(Connection conn,Boardroomdevice boardroomdevice)throws SQLException{
		String queryStr="SELECT BOARDROOMDEVICE_ID,DEVICE_ID,DEVICE_NAME,BOARDROOM_ID,DEVICE_STATUS,DEVICE_SIMILAR,DEVICE_DESCRIPTION,DEVICE_TYPE,ORG_ID FROM BOARDROOMDEVICE WHERE BOARDROOMDEVICE_ID =? AND ORG_ID =? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomdevice.getBoardroomdeviceId());
		ps.setString(2, boardroomdevice.getOrgId());
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONObject json = new JSONObject();
		if(rs.next()){
			json.accumulate("boardroomdeviceId", rs.getString("BOARDROOMDEVICE_ID"));
			json.accumulate("deviceId",rs.getString("DEVICE_ID") );
			json.accumulate("deviceName",rs.getString("DEVICE_NAME"));
			json.accumulate("boardroomId",rs.getString("BOARDROOM_ID"));
			json.accumulate("deviceStatus", rs.getString("DEVICE_STATUS"));
			json.accumulate("deviceSimilar", rs.getString("DEVICE_SIMILAR"));
			json.accumulate("deviceDescription", rs.getString("DEVICE_DESCRIPTION"));
			json.accumulate("deviceType", rs.getString("DEVICE_TYPE"));
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	/**
	 * 获取设备信息列表
	 * Author Wp
	 * @param conn
	 * @param orgId
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String lookdevicelogic(Connection conn,String orgId,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String queryStr="SELECT T1.ID AS ID,BOARDROOMDEVICE_ID,DEVICE_ID,DEVICE_NAME,T2.BOARDROOM_NAME  AS BOARDROOM_NAME,T1.BOARDROOM_ID,DEVICE_STATUS,DEVICE_SIMILAR,DEVICE_DESCRIPTION,DEVICE_TYPE FROM BOARDROOMDEVICE T1 LEFT JOIN BOARDROOM T2 ON T2.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.ORG_ID =? ";
		String optStr= "[{'function':'updatedevice','name':'修改','parm':'BOARDROOMDEVICE_ID'},{'function':'deldevice','name':'删除','parm':'BOARDROOMDEVICE_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		List<String> pramList = new ArrayList<String>();
		pramList.add(orgId);
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
	/**
	 * 模糊查询设备信息表
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String termdevicelogic(Connection conn,Boardroomdevice boardroomdevice,int pageSize,int page,String sortOrder,String sortName)throws Exception{
		String optStr= "[{'function':'updatedevice','name':'修改','parm':'BOARDROOMDEVICE_ID'},{'function':'deldevice','name':'删除','parm':'BOARDROOMDEVICE_ID'}]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		String queryStr="SELECT T1.ID AS ID,BOARDROOMDEVICE_ID,DEVICE_ID,DEVICE_NAME,T2.BOARDROOM_NAME AS BOARDROOM_NAME,T1.BOARDROOM_ID,DEVICE_STATUS,DEVICE_SIMILAR,DEVICE_DESCRIPTION,DEVICE_TYPE FROM BOARDROOMDEVICE T1 LEFT JOIN BOARDROOM T2 ON T2.BOARDROOM_ID =T1.BOARDROOM_ID WHERE T1.ORG_ID =? ";
		List<String> pramList = new ArrayList<String>();
		pramList.add(boardroomdevice.getOrgId());
		if(!boardroomdevice.getDeviceId().equals("")){
			queryStr+=" AND DEVICE_ID  LIKE ?";
			pramList.add("%"+boardroomdevice.getDeviceId()+"%");
		}
		if(!boardroomdevice.getDeviceName().equals("")){
			queryStr +=" AND DEVICE_NAME LIKE ?";
			pramList.add("%"+boardroomdevice.getDeviceName()+"%");
		}
		if(!boardroomdevice.getDeviceStatus().equals("")){
			queryStr +=" AND DEVICE_STATUS =?";
			pramList.add(boardroomdevice.getDeviceStatus());
		}
		if(!boardroomdevice.getDevicetype().equals("")){
			queryStr +=" AND DEVICE_TYPE LIKE ?";
			pramList.add("%"+boardroomdevice.getDevicetype()+"%");
		}
		if(!boardroomdevice.getBoardroomId().equals("")){
			queryStr +=" AND BOARDROOM_ID =?";
			pramList.add(boardroomdevice.getBoardroomId());
		}
		PageTool pageTool = new PageTool();
		JSONObject Json=new JSONObject();
		Json=pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, sortOrder, sortName);
		return Json.toString();
	}
	/**
	 * 根据会议室ID获取设备列表
	 * Author Wp
	 * @param conn
	 * @param boardroomdevice
	 * @return
	 * @throws SQLException
	 */
	public String getboardroomIddevicelogic(Connection conn,Boardroomdevice boardroomdevice)throws SQLException{
		String queryStr="SELECT BOARDROOMDEVICE_ID,DEVICE_ID,DEVICE_NAME,BOARDROOM_ID,DEVICE_STATUS,DEVICE_SIMILAR,DEVICE_DESCRIPTION,DEVICE_TYPE,ORG_ID FROM BOARDROOMDEVICE WHERE BOARDROOM_ID=? AND ORG_ID =?"; 
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, boardroomdevice.getBoardroomId());
		ps.setString(2, boardroomdevice.getOrgId());
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("boardroomdeviceId", rs.getString("BOARDROOMDEVICE_ID"));
			json.accumulate("deviceId",rs.getString("DEVICE_ID") );
			json.accumulate("deviceName",rs.getString("DEVICE_NAME"));
			json.accumulate("boardroomId",rs.getString("BOARDROOM_ID"));
			json.accumulate("deviceStatus", rs.getString("DEVICE_STATUS"));
			json.accumulate("deviceSimilar", rs.getString("DEVICE_SIMILAR"));
			json.accumulate("deviceDescription", rs.getString("DEVICE_DESCRIPTION"));
			json.accumulate("deviceType", rs.getString("DEVICE_TYPE"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
}
