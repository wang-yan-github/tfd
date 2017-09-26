package fixedasset.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.system.db.DbPoolConnection;

import fixedasset.logic.FixedassetSourceLogic;

public class FixedassetSourceAct {
	private FixedassetSourceLogic logic=new FixedassetSourceLogic();
	public String sourceList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			JSONObject data=logic.sourceList(request.getParameterMap(), conn);
			
			writer=response.getWriter();
			writer.print(data.toString());
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {}
		}
		return null;
	}
}
