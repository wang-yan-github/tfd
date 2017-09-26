package com.system.tool;

import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	public static String getReponseHeader_ContentDisposition(String fileName, String userAgent)
			throws Exception {
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		String newFileName = URLEncoder.encode(fileName, "UTF-8");
		String convertFileName = "filename=\"" + newFileName + "\"";
		
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") >-1) {
				convertFileName = "filename=\"" + newFileName + "\"";
			}
			
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") >-1) {
				convertFileName = "filename*=UTF-8''" + newFileName;
			}
			
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") >-1) {
				convertFileName = "filename=\""
						+ new String(fileName.getBytes("UTF-8"), "ISO8859-1")
						+ "\"";
			}
			
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("applewebkit") >-1) {
				newFileName = MimeUtility.encodeText(fileName, "UTF-8", "B");
				convertFileName = "filename=\"" + newFileName + "\"";
			}
			
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") >-1) {
				convertFileName = "filename*=UTF-8''" + newFileName;
			}
		}
		convertFileName = convertFileName.replaceAll("\\+", "%20");
		return convertFileName;
	}
	public static String getUserAgent(HttpServletRequest request){
		return request.getHeader("user-agent");
	}
}
