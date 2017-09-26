<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.system.global.SysConst"%>
<%
String rtState = (String)request.getAttribute(SysConst.RET_STATE);
String rtMsrg = (String)request.getAttribute(SysConst.RET_MSRG);
String rtData = (String)request.getAttribute(SysConst.RET_DATA);
if (rtState == null) {
  rtState = "0";
}
if (rtMsrg == null) {
  rtMsrg = "";
}else {
  rtMsrg = rtMsrg.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
}
if (rtData == null) {
  rtData = "\"\"";
}
%>
{"rtState":"<%=rtState %>", "rtMsrg":"<%=rtMsrg %>", "rtData":<%=rtData %>}