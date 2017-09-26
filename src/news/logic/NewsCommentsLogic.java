package news.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import news.data.NewsComments;
import tfd.system.unit.account.data.Account;

public class NewsCommentsLogic {

	/**
	 * 添加 新闻 评论内容
	 * Author: Wp
	 * @param conn
	 * @param newsComments
	 * @return
	 * @throws SQLException
	 */
	public int addCommLogic(Connection conn,NewsComments newsComments)throws SQLException{
		String queryStr="INSERT INTO NEWS_COMMENTS (COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,NEWS_ID,ACCOUNT_ID,ORG_ID,COMM_NAME)VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, newsComments.getCommId());
		ps.setString(2, newsComments.getCommPid());
		ps.setString(3, newsComments.getCommContect());
		ps.setString(4, newsComments.getCommTime());
		ps.setString(5, newsComments.getNewsId());
		ps.setString(6, newsComments.getAccountId());
		ps.setString(7, newsComments.getOrgId());
		ps.setString(8, newsComments.getCommName());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 根据新闻newsId 查看评论信息
	 * Author: Wp
	 * @param conn
	 * @param newsId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
	public String getNewsIdLogic(Connection conn,String newsId,String orgId)throws SQLException{
		String queryStr="SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,NEWS_ID,ACCOUNT_ID,COMM_NAME FROM NEWS_COMMENTS WHERE NEWS_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, newsId);
		ps.setString(2, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		JSONArray jsonArr = new JSONArray(); 
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.accumulate("commId", rs.getString("COMM_ID"));
			if(rs.getString("COMM_PID")==null){
				json.accumulate("commPid", "");
			}else{
			json.accumulate("commPid", rs.getString("COMM_PID"));
			}
			json.accumulate("replyNum", this.getCommPidLogic(conn, rs.getString("COMM_PID"), orgId));
			json.accumulate("commContect", rs.getString("COMM_CONTECT"));
			json.accumulate("commTime", rs.getString("COMM_TIME"));
			json.accumulate("newsId", rs.getString("NEWS_ID"));
			json.accumulate("commName", rs.getString("COMM_NAME"));
			json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	/**
	 * 根据新闻newsId 获取最新的五条记录
	 * Author: Wp
	 * @param conn
	 * @param newsId
	 * @param orgId
	 * @return
	 * @throws SQLException
	 */
		public String getlateCommLogic(Connection conn,String newsId,String orgId)throws SQLException{
			String dbType =DbPoolConnection.getInstance().getDbType();
			String queryStr="";
			if(dbType.equals("sqlserver"))
			{
				
			}else if(dbType.equals("mysql")){
			queryStr="SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,NEWS_ID,ACCOUNT_ID,COMM_NAME FROM NEWS_COMMENTS WHERE NEWS_ID =? AND ORG_ID =? ORDER BY COMM_TIME DESC LIMIT 0,5";
			}else if(dbType.equals("oracle")){
			queryStr="SELECT *FROM ( SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,NEWS_ID,ACCOUNT_ID,COMM_NAME FROM NEWS_COMMENTS WHERE NEWS_ID =? AND ORG_ID =? ORDER BY COMM_TIME DESC)TMP WHERE ROWNUM<=3";
			}
			PreparedStatement ps = conn.prepareStatement(queryStr);
			ps.setString(1, newsId);
			ps.setString(2, orgId);
			ResultSet rs =null;
			rs=ps.executeQuery();
			JSONArray jsonArr = new JSONArray(); 
			while(rs.next()){
				JSONObject json=new JSONObject();
				json.accumulate("commId", rs.getString("COMM_ID"));
				if(rs.getString("COMM_PID")==null){
					json.accumulate("commPid", "");
				}else{
				json.accumulate("commPid", rs.getString("COMM_PID"));
				}
				json.accumulate("replyNum", this.getCommPidLogic(conn, rs.getString("COMM_ID"), orgId));
				if(rs.getString("COMM_CONTECT")==null){
					json.accumulate("commContect", "");	
				}else{
				json.accumulate("commContect", rs.getString("COMM_CONTECT"));
				}
				json.accumulate("commTime", rs.getString("COMM_TIME"));
				json.accumulate("newsId", rs.getString("NEWS_ID"));
				json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
				json.accumulate("commName", rs.getString("COMM_NAME"));
				jsonArr.add(json);
			}
			rs.close();
			ps.close();
			return jsonArr.toString();
		}
		/**
		 * 获取评论回复条数
		 * Author: Wp
		 * @param conn
		 * @param commPid
		 * @param orgId
		 * @return
		 * @throws SQLException
		 */
	public String getCommPidLogic(Connection conn,String commPid,String orgId)throws SQLException{
		String queryStr="SELECT COUNT(1)AS REPLYNUM FROM NEWS_COMMENTS WHERE COMM_PID=? AND ORG_ID =?";
		String replyNum="0";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, commPid);
		ps.setString(2, orgId);
		ResultSet rs =null;
		rs=ps.executeQuery();
		while(rs.next()){
			replyNum=rs.getString("REPLYNUM");
		}
		rs.close();
		ps.close();
		return replyNum;
	}
	/**
	 * 根据评论Id 删除评论信息
	 * Author: Wp
	 * @param conn
	 * @param commId
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public int delCommIdLogic(Connection conn,String commId,Account account)throws SQLException{
		String queryStr="DELETE FROM NEWS_COMMENTS WHERE COMM_ID=? AND ACCOUNT_ID =? AND ORG_ID =?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, commId);
		ps.setString(2, account.getAccountId());
		ps.setString(3, account.getOrgId());
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
	/**
	 * 分页查询新闻的评论内容
	 * Author: Wp
	 * @param conn
	 * @param pramList
	 * @param pageSize
	 * @param page
	 * @param sortOrder
	 * @param sortName
	 * @return
	 * @throws Exception 
	 */
	public String getCommentsLogic(Connection conn,List<String> pramList,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		String queryStr="SELECT COMM_ID,COMM_PID,COMM_CONTECT,COMM_TIME,NEWS_ID,ACCOUNT_ID,COMM_NAME,(SELECT COUNT(1) FROM NEWS_COMMENTS T2 WHERE COMM_PID=T1.COMM_ID)AS REPLYNUM,(SELECT COMM_CONTECT FROM NEWS_COMMENTS T3 WHERE T3.COMM_ID=T1.COMM_PID)AS P_CONTECT FROM NEWS_COMMENTS T1 WHERE NEWS_ID =? AND ORG_ID =?";
		String optStr= "[]";
		JSONArray optArrJson = JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
	}
}
