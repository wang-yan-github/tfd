package api.im;

import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.tool.RequestUtil;
import com.system.tool.SysTool;

public class IMTest {
	public void getKey(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		try {
			String sessionid=request.getSession().getId();
			String terminalId=request.getParameter("terminalId");
			String deviceId=request.getParameter("deviceId");
			String deviceType=request.getParameter("deviceType");
			String clientVersion=request.getParameter("clientVersion");
			
			String param="terminal_id="+terminalId+
						 "&device_id="+deviceId+
						 "&access_token="+sessionid+
						 "&device_type="+deviceType+
						 "&client_version="+clientVersion;
			String key=RequestUtil.sendPost("http://115.29.170.136:8080/user/hand_shake",param);
			writer=response.getWriter();
			writer.print(key);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
		}
	}
	public void sendPrivate(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		try {
			String sessionid=request.getSession().getId();
			String content=request.getParameter("content");
			String terminalId=request.getParameter("terminalId");
			String toTerminalId=request.getParameter("toTerminalId");
			
			String param="terminal_id="+terminalId+
						 "&access_token="+sessionid+
						 "&to_terminal_id="+toTerminalId+
						 "&content="+content+
						 "&msg_type=msg";
			String rv=RequestUtil.sendPost("http://115.29.170.136:8080/chat/send_private",param);
			
			writer=response.getWriter();
			writer.print(rv);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
		}
	}
	public void getMsg(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		try {
			String param="k=5JTZWelnfzeHl9EduRDNkrAerok8sJPv&m=7";
			String rv=RequestUtil.sendPost("http://115.29.170.136:8090/1/msg/get",param);
			writer=response.getWriter();
			writer.print(rv);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
		}
	}
}
