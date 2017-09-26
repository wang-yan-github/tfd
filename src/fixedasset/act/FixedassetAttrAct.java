package fixedasset.act;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

import fixedasset.logic.FixedassetAttrLogic;

public class FixedassetAttrAct {
	private FixedassetAttrLogic logic=new FixedassetAttrLogic();
	private BaseDao dao=new BaseDaoImpl();
	public String getSeqIdByAssetNo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=response.getWriter();
		boolean optResult=false;
		JSONObject result=new JSONObject();
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String assetNo=request.getParameter("assetNo");
			String seqId=logic.getSeqIdByAssetNo(assetNo, conn);
			optResult=true;
			result.accumulate("optResult", optResult);
			result.accumulate("data", seqId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			writer.print(result.toString());
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
}
