package checkin.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.json.JSONObject;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;

public class CheckinLogic {
	public BaseDao dao=new BaseDaoImpl();
	public JSONObject list(Map<String,String[]> requestMap,Connection conn) throws Exception{
		
		String sql="select 0 as id,d.deptname,u.name as username,"
				+ "			(case c.checktype when 'I' then '签到' when 'O' then '签退' else '' end) as checktype,"
				+ "			convert(varchar(100), c.checktime, 20) as checktime"
				+ "		from checkinout c"
				+ "			left join userinfo u on c.userid=u.userid"
				+ "			left join departments d on u.defaultdeptid=d.deptid"
				+ "		where 1=1";
		
		String deptName=requestMap.get("deptName")==null?null:requestMap.get("deptName")[0];
		String userName=requestMap.get("userName")==null?null:requestMap.get("userName")[0];
		String checkinType=requestMap.get("checkinType")==null?null:requestMap.get("checkinType")[0];
		String checkinTimeStart=requestMap.get("checkinTimeStart")==null?null:requestMap.get("checkinTimeStart")[0];
		String checkinTimeEnd=requestMap.get("checkinTimeEnd")==null?null:requestMap.get("checkinTimeEnd")[0];
		
		if (deptName!=null&&!deptName.equals("")) {
			sql+=" and d.deptname like '%"+deptName+"%'";
		}
		if (userName!=null&&!userName.equals("")) {
			sql+=" and u.name like '%"+userName+"%'";
		}
		if (checkinType!=null&&!checkinType.equals("")) {
			sql+=" and c.checktype='"+checkinType+"'";
		}
		if (checkinTimeStart!=null&&!checkinTimeStart.equals("")) {
			sql+=" and c.checktime > '"+checkinTimeStart+"'";
		}
		if (checkinTimeEnd!=null&&!checkinTimeEnd.equals("")) {
			sql+=" and c.checktime < '"+checkinTimeEnd+"'";
		}
		
		Page page=PageUtil.toEasyuiPage(requestMap);
		PageTool pageTool=new PageTool();
		
		return pageTool.getPageData(conn, sql, page);
		
	}
	public boolean checkinPriv(String url,String accountId,String orgId,Connection conn){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			
			String sql="select 1 from user_priv"
					+ "		where concat(',',user_priv_str,',') like "
					+ "			concat('%,',(select sys_menu_id from sys_menu where sys_menu_url=?),',%')"
					+ "		  and user_priv_id=(select user_priv from account where account_id=? and org_id=?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, url);
			stmt.setString(2, accountId);
			stmt.setString(3, orgId);
			rs=stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		Connection conn=new BaseDaoImpl().getConn("D:\\project\\workspace\\TFDSYS\\webroot\\tfd\\WEB-INF\\config\\db\\conn_checkin.properties");
//		PreparedStatement stmt=conn.prepareStatement("select 0 as id,d.deptname,u.name as username,			(case c.checktype when 'I' then '签到' when 'O' then '签退' else '' end) as checktype,			convert(varchar(100), c.checktime, 20) as checktime		from checkinout c			left join userinfo u on c.userid=u.userid			left join departments d on u.defaultdeptid=d.deptid		where 1=1 order by c.checktime desc");
//		PreparedStatement stmt=conn.prepareStatement("select u.name as username,c.checktime,c.checktype  from CHECKINOUT c left join userinfo u on c.userid=u.userid where 1=1 order by c.checktime desc");
//		PreparedStatement stmt=conn.prepareStatement("select userid as username,checktime,checktype from checkinout where 1=1 order by checktime desc");
		PreparedStatement stmt=conn.prepareStatement("SELECT TOP 10 * FROM (SELECT ROW_NUMBER() OVER (ORDER BY checktime) AS ROW_NUMBER,* FROM (select 0 as id,d.deptname,u.name as username,			(case c.checktype when 'I' then '签到' when 'O' then '签退' else '' end) as checktype,			convert(varchar(100), c.checktime, 20) as checktime		from checkinout c			left join userinfo u on c.userid=u.userid			left join departments d on u.defaultdeptid=d.deptid		where 1=1) A ) B WHERE ROW_NUMBER > 10*(2-1) ORDER BY checktime desc");
		ResultSet rs=stmt.executeQuery();
		int i=0;
		while (rs.next()) {
			String name=rs.getString("username");
			String checktime=rs.getString("checktime");
			String checktype=rs.getString("checktype");
			System.out.println(i+"\t"+name+"\t"+checktime+"\t"+checktype);
			i++;
		}
		System.out.println("共："+i);
	}
}
