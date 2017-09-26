package tfd.system.code.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tfd.system.code.data.CodeClass;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;

public class CodeLogic {
	/**
	 *  获取代码分类列表
	 * Time : 2015-4-2
	 * Author : Yzz
	 * @param conn ：jdbc链接
	 * @param orgId : 企业Id
	 * @return : 
	 * @throws Exception
	 */
	public String getCodeList(Connection conn,List<String> pramList,int pagesize,int page,String storOrder,String storName,String search)throws Exception{
		String queryStr = null;
		if(search.equals("")){
			queryStr = "SELECT t1.CODE_ID,t1.CODE_PID,t1.CODE_NAME,t1.PAGE_CODE,t1.CODE_VALUE FROM code_class t1 WHERE t1.CODE_PID = '0' AND t1.ORG_ID = ? ";
		}else{
			queryStr = "SELECT t1.CODE_ID,t1.CODE_PID,t1.CODE_NAME,t1.PAGE_CODE,t1.CODE_VALUE FROM code_class t1 WHERE t1.CODE_PID = '0' AND t1.ORG_ID = ? AND (t1.CODE_NAME LIKE '%"+search+"%') ";
		}
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pagesize,page,optArrJson,storOrder,storName);
		return json.toString();
	}
	
	/**
	 * 添加代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param c 代码分类实体类
	 * @return
	 * @throws Exception
	 */
	public int addCode(Connection conn,CodeClass c)throws Exception{
		String sql = "INSERT INTO CODE_CLASS(CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,c.getCodeId() );
		ps.setString(2,c.getCodePid() );
		ps.setString(3,c.getCodeName() );
		ps.setString(4,c.getPageCode() );
		ps.setString(5,c.getCodeValue() );
		ps.setString(6,c.getOrgId() );
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	
	/**
	 * 添加代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param id 代码分类Id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public String getCodeById(Connection conn,String id,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		String queryStr = "SELECT t1.CODE_ID,t1.CODE_PID,t1.CODE_NAME,t1.PAGE_CODE,t1.CODE_VALUE FROM code_class t1 WHERE t1.ORG_ID = ? AND t1.CODE_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, id);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("CODE_ID"));
			json.accumulate("pId", rs.getString("CODE_PID"));
			json.accumulate("name", rs.getString("CODE_NAME"));
			json.accumulate("page", rs.getString("PAGE_CODE"));
			json.accumulate("value", rs.getString("CODE_VALUE"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 修改代码主分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param c 代码分类实体类
	 * @return
	 * @throws Exception
	 */
	public int updateCode(Connection conn,CodeClass c)throws Exception{
		String sql = "UPDATE CODE_CLASS SET CODE_PID = ?,CODE_NAME = ?,PAGE_CODE = ?,CODE_VALUE = ? WHERE CODE_ID = ? AND  ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,c.getCodePid() );
		ps.setString(2,c.getCodeName() );
		ps.setString(3,c.getPageCode() );
		ps.setString(4,c.getCodeValue() );
		ps.setString(5,c.getCodeId() );
		ps.setString(6,c.getOrgId() );
		int i =  ps.executeUpdate();
		ps.close();
		return i;
	}
	
	/**
	 * 修改代码主分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param c 代码分类实体类
	 * @return
	 * @throws Exception
	 */
	public int delCode(Connection conn,String id,String orgId)throws Exception{
		String sql = "DELETE FROM CODE_CLASS  WHERE CODE_ID = ? AND ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,id);
		ps.setString(2,orgId );
		int j =  ps.executeUpdate();
		ps.close();
		//在删除子集的内容
		String optStr= findChild(conn, id, orgId);
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		if(optArrJson.size() == 0){
			
		}else{
			for(int i = 0;optArrJson.size()>i;i++)
			{
				JSONObject optjson = optArrJson.getJSONObject(i);
				delCode(conn, optjson.getString("CODE_ID"), optjson.getString("ORG_ID"));
			}
		}
		return j;
	}
	
	/**
	 * 找到子集
	 * Time:2015-4-3
	 * Author:Yzz
	 * @param conn jdbc连接
	 * @param pId 父级Id
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public String findChild(Connection conn,String pId,String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = null;
		String queryStr = "SELECT t1.CODE_ID,t1.CODE_PID,t1.CODE_NAME,t1.PAGE_CODE,t1.CODE_VALUE FROM code_class t1 WHERE t1.ORG_ID = ? AND t1.CODE_PID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, pId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json = new JSONObject();
			
			json.accumulate("id", rs.getString("CODE_ID"));
			json.accumulate("pId", rs.getString("CODE_PID"));
			json.accumulate("name", rs.getString("CODE_NAME"));
			json.accumulate("page", rs.getString("PAGE_CODE"));
			json.accumulate("value", rs.getString("CODE_VALUE"));
			
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	/**
	 * 根据值得到主分类
	 * Time : 2015-4-6
	 * Author : Yzz
	 * @param conn jdbc连接
	 * @param value 值
	 * @param orgId 企业Id
	 * @return
	 * @throws Exception
	 */
	public List<String> getCodeIdByValue(Connection conn,String value,String orgId)throws Exception{
		List<String> returnData = new ArrayList<String>();
		ResultSet rs = null;
		String queryStr = "SELECT t1.CODE_ID,t1.CODE_PID,t1.CODE_NAME,t1.PAGE_CODE,t1.CODE_VALUE FROM code_class t1 WHERE t1.ORG_ID = ? AND t1.CODE_VALUE = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ps.setString(2, value);
		rs=ps.executeQuery();
		if(rs.next())
		{
			returnData.add(rs.getString("CODE_ID")) ;
			returnData.add(rs.getString("CODE_VALUE")) ;
		}
		rs.close();
		ps.close();
		return returnData;
	}
}
