<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>联系记录表</title>
<%String customerId=request.getParameter("customerId"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<style>
html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
</style>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >联系记录表</span>
</div>
<div style=" float: right;margin-top:6px;margin-right: 20px;">
<button class="btn btn-default btn-large" onclick="history.back();"> 返回</button>
</div>
</div>
<div style="width: 100%;height:90%;">
<iframe id="con" scrolling='auto' frameborder='0'  style="border: none;width: 100%;height: 100%;" src="recordTable.jsp?customerId=<%=customerId%>" ></iframe>
</div>
</body>
</html>