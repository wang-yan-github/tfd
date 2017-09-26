package tfd.system.regist.act;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.regist.data.Reg;
import tfd.system.regist.logic.RegistLogic;

import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.SysTool;

public class RegistAct {
	RegistLogic logic=new RegistLogic();
	public String regist(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		Connection conn=null;
		OutputStream ops=null;
		//上传的文件流
		InputStream is=null;
		try {
			is=SysTool.getFileInputStreamOfRequestForm(request);
			
			//注册文件校验
			byte[] regBytes=logic.readRegFile(is);
			String decodeRegFileContent=logic.decodeRegFile(regBytes);
			Reg reg=logic.decodedRegFileToObject(decodeRegFileContent);
			conn=DbPoolConnection.getInstance().getConnection();
			boolean validateResult=logic.validate(reg, conn);
			
			//检测通过的注册文件上传到系统内
			if (validateResult) {
				request.getSession().setAttribute(SysConst.SYS_IS_REGISTERED, true);
				
				ops=new FileOutputStream(new File(SysTool.getProjectWebInfAbsolutePath()+"config/reg.lsq"));
				ops.write(regBytes);
			}
			
			request.setAttribute("registred", validateResult);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closeOutputStream(ops);
			SysTool.closeInputStream(is);
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, conn);
		}
		return "/system/regist/regist.jsp";
		
	}
	public void readReg(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		String regContent="";
		Connection conn=null;
		try {
			byte[] regBytes=logic.readRegFile();
			if (regBytes==null) {
				String disk=this.getClass().getClassLoader().getResource(File.separator).getPath().substring(1,2);
				String diskSn=logic.getHardDiskSN(disk);
				regContent="{'diskSn':'"+diskSn+"','validateResult':'false'}";
			}else{
				conn=DbPoolConnection.getInstance().getConnection();
				regContent=logic.decodeRegFile(regBytes);
				regContent=regContent.trim();
				regContent=regContent.substring(0,regContent.length()-1)+",\"validateResult\":\""+logic.validate(conn)+"\"}";
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			writer=response.getWriter();
			writer.print(regContent);
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, conn);
		}
		
	}
}
