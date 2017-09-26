package api.paging;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class PagingAct {
	private BaseDao dao=new BaseDaoImpl();

	public String studentList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			/*********/
			JSONObject datalist=null;
			
			String sql="select name,age from api_student";
			
			Page page=PageUtil.toEasyuiPage(request.getParameterMap());
			PageTool pageTool=new PageTool();
			datalist= pageTool.getPageData(conn, sql, page);
			/***********/
			writer=response.getWriter();
			writer.print(datalist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
	
	public String studentAdd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			int result=0;
			
			String sql="select count(1) student_count from api_student";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("student_count")<1000) {
					for (int i = 0; i < 100; i++) {
						sql="insert into api_student(name,age) values(?,?)";
						stmt=conn.prepareStatement(sql);
						stmt.setString(1, "学生"+(int)Math.floor(Math.random()*1000000));
						stmt.setInt(2, i+1);
						result=stmt.executeUpdate();
						if (result<1) {
							break;
						}
					}
					
				}
			}
			
			if (result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
			
			writer=response.getWriter();
			writer.print(result>0);
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
			dao.close(rs, stmt, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
}
