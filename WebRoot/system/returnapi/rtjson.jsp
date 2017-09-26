<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.system.global.SysConst"%>
<%
String status_code = (String)request.getAttribute(SysConst.RET_STATE);
String msg = (String)request.getAttribute(SysConst.RET_MSRG);
String data = (String)request.getAttribute(SysConst.RET_DATA);
if (status_code == null) {
	status_code = "500";
}
if (msg == null) {
	msg = "";
}else {
	msg = msg.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
}
if (data == null) {
	data = "\"\"";
}

%>
{"status_code":"<%=status_code %>", "msg":"<%=msg %>", "data":<%=data %>}