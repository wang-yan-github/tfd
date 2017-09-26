package tfd.system.im.act;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import tfd.system.im.data.Im;
import tfd.system.im.logic.ImUserLogic;
import tfd.system.im.userinfo.logic.UserInfoLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbPoolConnection;
import com.system.global.DateConst;
import com.system.global.SysConst;
import com.system.tool.SysTool;

public class ImAct {
	
	ImUserLogic imUserlogic=new ImUserLogic();
	UserInfoLogic userInfoLogic=new UserInfoLogic();
	
	public void handshake(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String accountId=request.getParameter("accountId");
			
			String key=imUserlogic.handshake(request.getSession().getId(), accountId);
			key=key==null?"":key;
			
			writer=response.getWriter();
			writer.print(key);
		} catch (Exception e) {
			throw e;
		}finally{
			imUserlogic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void send(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		try {
			String sessionid=request.getSession().getId();
			String content=request.getParameter("content");
			String terminalId=request.getParameter("terminalId");
			String toTerminalId=request.getParameter("toTerminalId");
			
			//发送消息
			URI uri=new URIBuilder()
			.setScheme("http")
			.setHost(Im.OTTSERVER_HOST)
			.setPath("/chat/send_private")
			.setParameter("terminal_id",terminalId)
			.setParameter("access_token", sessionid)
			.setParameter("to_terminal_id", toTerminalId)
			.setParameter("content", content)
			.setParameter("msg_type", "msg")
			.setCharset(Charset.forName("utf-8"))
			.build();

			HttpPost httpPost=new HttpPost(uri);
			
			CloseableHttpClient httpClient=HttpClients.createDefault();
			CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
			HttpEntity entity=httpResponse.getEntity();
			BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
			String line=null;
			String rv="";
			while((line=reader.readLine())!=null){
				rv+=line;
			}
			//-----------------------------------
			
			System.out.println("---------------------");
			System.out.println(terminalId+" -->  "+toTerminalId);
			System.out.println("发送消息："+content);
			System.out.println("结果："+rv);
			
			writer=response.getWriter();
			writer.print(rv);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
		}
	}

	/**
	 * 检查token有限性（聊天服务器使用）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkaccesstoken(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String returnData="";
		try {
//			int status = 0;
//			SystemUtil sysUtil = new SystemUtil();
//			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
//			HttpSession session=null;
//			HashMap<String,HttpSession> sessionMap = ListenerSession.getSessaionContextMap();
//			Iterator<Entry<String, HttpSession>> it=sessionMap.entrySet().iterator(); 
//		    while(it.hasNext()){
//		          Entry<String, HttpSession> entry = it.next();          
//		          session=(HttpSession) entry.getValue();
//		          if(session.getId().equals(token)){
//		        	  status = 1;
//		          }
//			}
			JSONObject data=new JSONObject();
			data.accumulate("status", 1);
			returnData=data.toString();
		} catch (Exception e) {
			throw e;
		}finally{
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	public void updateUserMid(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			String accountId=request.getParameter("accountId");
			String mid=request.getParameter("mid");
			
			int result=imUserlogic.updateMid(accountId, mid, conn);
			if (result>0) {
				conn.commit();
			}else{
				imUserlogic.dao.rollback(conn);
			}
			JSONObject returnData=new JSONObject();
			returnData.put("mid",mid);
			returnData.put("result", result>0);
			writer=response.getWriter();
			writer.print(returnData.toString());
			
		} catch (Exception e) {
			imUserlogic.dao.rollback(conn);
			throw e;
		}finally{
			imUserlogic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void updateUserDefined(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			String accountId=request.getParameter("accountId");
			String defined=request.getParameter("defined");
			
			int result=imUserlogic.updateDefined(accountId, defined, conn);
			if (result>0) {
				conn.commit();
			}else{
				imUserlogic.dao.rollback(conn);
			}
			JSONObject returnData=new JSONObject();
			returnData.put("result", result);
			writer=response.getWriter();
			writer.print(returnData.toString());
			
		} catch (Exception e) {
			imUserlogic.dao.rollback(conn);
			throw e;
		}finally{
			imUserlogic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	public void init(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);

			Account account = (Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
			
			boolean result=imUserlogic.init(account.getAccountId(), conn);
			JSONObject userInfo=userInfoLogic.getUserInfo(conn, account.getAccountId());
			
			JSONObject returnData=new JSONObject();
			returnData.put("result", result);
			returnData.put("userInfo",userInfo==null?JSONNull.getInstance():userInfo);
			returnData.put("time", new SimpleDateFormat(DateConst.VALUE_LONG_DATE24).format(new Date()));
			writer=response.getWriter();
			writer.print(returnData.toString());
			
		} catch (Exception e) {
			imUserlogic.dao.rollback(conn);
			throw e;
		}finally{
			imUserlogic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
	public void privateHistory(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			URI uri=new URIBuilder()
						.setScheme("http")
						.setHost(Im.OTTSERVER_HOST)
						.setPath(Im.OTTSERVER_INF_PRIVATE_HISTORY)
						.setCharset(Charset.forName("utf-8"))
						.setParameter("terminal_id",request.getParameter("me"))
						.setParameter("access_token",request.getSession().getId())
						.setParameter("to_terminal_id",request.getParameter("him"))
						.setParameter("limit",request.getParameter("limit"))
						.setParameter("pm_id",request.getParameter("msgId"))
						.build();
			
			HttpGet httpGet=new HttpGet(uri);
			
			CloseableHttpClient httpClient=HttpClients.createDefault();
			CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity entity=httpResponse.getEntity();
			BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
			String line=null;
			String rv="";
			while((line=reader.readLine())!=null){
				rv+=line;
			}
			
			System.out.println("获取历史消息："+request.getParameter("me")+"<->"+request.getParameter("him"));
			System.out.println("request:"+uri.toString());
			System.out.println("response:"+rv);
			
			writer=response.getWriter();
			writer.print(rv);
			
		} catch (Exception e) {
			throw e;
		}finally{
			imUserlogic.dao.close(null, null, conn);
			SysTool.closePrintWriter(writer);
		}
	}
	
}
