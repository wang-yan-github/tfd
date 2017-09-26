package tfd.system.im.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import tfd.system.im.data.ImUser;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

public class ImUserLogic {
	public BaseDao dao=new BaseDaoImpl();
	public int add(ImUser imKey,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="insert into im_key(account_id,mid) values(?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, imKey.getAccountId());
			stmt.setString(2, imKey.getMid());
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	public int update(ImUser imUser,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="update im_key set mid=?, where account_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, imUser.getMid());
			stmt.setString(2, imUser.getAccountId());
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	public String getKey(String accountId,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select `key` from im_key where account_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, accountId);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("key");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return null;
	}
	public String handshake(String sessionId,String accountId)throws Exception{
		String deviceId="deviceId";
		String deviceType="deviceType";
		String clientVersion="clientVersion";
		
		//和服务器握手
		URI uri=new URIBuilder()
		.setScheme("http")
		.setHost("115.29.170.136:8080")
		.setPath("/user/hand_shake")
		.setParameter("terminal_id",accountId)
		.setParameter("device_id",deviceId)
		.setParameter("access_token", sessionId)
		.setParameter("device_type", deviceType)
		.setParameter("client_version", clientVersion)
		.setCharset(Charset.forName("utf-8"))
		.build();

		HttpPost httpPost=new HttpPost(uri);
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		HttpEntity entity=httpResponse.getEntity();
		BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
		String line=null;
		String data="";
		while((line=reader.readLine())!=null){
			data+=line;
		}
		//-----------------------------------
		
		System.out.println(sessionId);
		System.out.println("im获取key:"+data);
		
		JSONObject dataObject=null;
		try {
			dataObject=JSONObject.fromObject(data);
		} catch (Exception e) {}
		
		if (dataObject!=null&&dataObject.getString("ret").equals("0")) {
			return dataObject.getString("channel_key");
		}
		
		return null;
	}
	
	public int updateMid(String accountId,String mid,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=conn.prepareStatement("update im_user set mid=? where account_id=?");
			stmt.setString(1, mid);
			stmt.setString(2, accountId);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	public int updateDefined(String accountId,String defined,Connection conn)throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="update im_user set defined=? where account_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, defined);
			stmt.setString(2, accountId);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	public boolean init(String accountId,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="select 1 from im_user where account_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, accountId);
			
			if (stmt.executeQuery().next()) {
				return true;
			}else{
				sql="insert into im_user(account_id) values(?)";
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, accountId);
				return stmt.executeUpdate()>0;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
