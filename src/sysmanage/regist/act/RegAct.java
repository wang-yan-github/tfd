package sysmanage.regist.act;

import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sysmanage.regist.data.Reg;
import sysmanage.regist.logic.RegLogic;
import sysmanage.regist.logic.RegistLogic;

import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.HttpUtil;
import com.system.tool.SysTool;

public class RegAct {
	RegLogic logic=new RegLogic();
	public void toRegFile(HttpServletRequest request,HttpServletResponse response)throws Exception{
		OutputStream ops=null;
		Connection conn=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			JSONObject detail=new RegistLogic().readRegisteredDetail(request.getParameter("id"), conn);
			
			Reg reg=new Reg(
				detail.getString("registUnit"), 
				detail.getString("productVersion"), 
				detail.getString("productSn"), 
				SysTool.parseDate(DateConst.VALUE_SHORT_DATE,detail.getString("registTime")), 
				SysTool.parseDate(DateConst.VALUE_SHORT_DATE,detail.getString("registDeadline")), 
				Integer.parseInt(detail.getString("registUserCount")),
				Integer.parseInt(detail.getString("registImUserCount")), 
				detail.getString("registDiskSn"), 
				detail.getString("productName"), 
				detail.getString("productTeam"), 
				detail.getString("productSite")
			);
			byte[] encodeBytes=logic.encodeReg(reg);
			
			response.setContentType("application/octet-stream");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control", "maxage=3600");
			response.setHeader("Pragma", "public");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition",
					HttpUtil.getReponseHeader_ContentDisposition("reg.lsq", request.getHeader("user-agent")));
			
			ops=response.getOutputStream();
			ops.write(encodeBytes, 0, encodeBytes.length);
		} catch (Exception e) {
			throw e;
		}finally{
			SysTool.closeOutputStream(ops);
			new BaseDaoImpl().close(null, null, conn);
		}
		
	}
}
