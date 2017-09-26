package fixedasset.act;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.filetool.UpFileTool;
import com.system.tool.SysTool;

import fixedasset.logic.FixedassetLogic;

public class FixedassetAct {
	private FixedassetLogic logic=new FixedassetLogic(); 
	private BaseDao dao=new BaseDaoImpl();
	public String loadAssetImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream os=null;
		ByteArrayOutputStream bos=null;
		InputStream is=null;
		try {
			
			
			String attachId=request.getParameter("attachId");
			String attachPath=new UpFileTool().getAttachPath(attachId);
			
			os=response.getOutputStream();
			is=new FileInputStream(new File(attachPath));
			bos=new ByteArrayOutputStream();
			
			byte[] b=new byte[1024];
			int l=0;
			while((l=is.read(b))>-1){
				bos.write(b, 0, l);
			}
			os.write(bos.toByteArray());
			os.flush();
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if (bos!=null) {
					bos.close();
				}
			} catch (Exception e2) {}
			try {
				if (os!=null) {
					os.close();
				}
			} catch (Exception e2) {}
			try {
				if (is!=null) {
					is.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	public String add(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=response.getWriter();
		boolean result=false;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			logic.add(request.getParameterMap(), conn);
			conn.commit();
			result=true;
		} catch (Exception e) {
			dao.rollback(conn);
			e.printStackTrace();
		}finally{
			writer.print(result);
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
	public String assetList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		JSONObject assetList=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			assetList=logic.assetList(request.getParameterMap(), conn);
			writer=response.getWriter();
			writer.print(assetList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
}
