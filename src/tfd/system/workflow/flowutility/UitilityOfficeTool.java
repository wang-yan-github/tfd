package tfd.system.workflow.flowutility;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.HttpUtil;
import com.system.tool.RequestUtil;
import com.system.tool.SysTool;

public class UitilityOfficeTool {
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	public void doDownFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String runId = request.getParameter("runId");
		OutputStream ops =null;
		FlowRun flowRun = new FlowRun();
		FlowRunLogic flowRunLogic = new FlowRunLogic();
		Connection dbConn =null;
		try
		{
			dbConn=DbPoolConnection.getInstance().getConnection();
			flowRun = flowRunLogic.getFlowRunLogic(dbConn, runId);
			String fileName = flowRun.getTitle()+".docx";
			String contentType="application/msword";
			String contentDisposition=HttpUtil.getReponseHeader_ContentDisposition(fileName, request.getHeader("user-agent"));
			String domain = "http://127.0.0.1:8080/tfd/system/workflow/dowork/bpmtest/print/index.jsp?runId=DD3DBF63-BFC5-BBFB-4DE8-AC9CD8F89A49";
			byte [] fileByte = RequestUtil.sendPost(domain,null).getBytes();
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Cache-control", "private");
			response.setContentType(contentType);
			response.setContentType("application/msword");
			response.setContentLength((int) fileByte.length);
			response.setHeader("Content-Disposition",contentDisposition);
			ops = response.getOutputStream();
			ops.write(fileByte);
		}catch (Exception ex )
		{
			ex.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			SysTool.closeOutputStream(ops);
		}
		}
	
	}
