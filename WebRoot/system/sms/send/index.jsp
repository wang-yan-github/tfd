<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部短信</title>
<style>
	html,body{width:100%;height:100%;margin:0px;padding:0px;}
	html{overflow:hidden;}
	#body{position:absolute;top:10px;left:10px;right:0px;bottom:0px;}
	#west{position:absolute;height:100%;width:250px;}
	#center{position:absolute;height:100%;left:255px;top:0px;right:0px;}
		
	iframe{width:100%;height:100%;}
</style>
</head>
<body>
	<div id="body">
		<div id="west">
			<iframe name="sort" id="sort" frameborder="0" 
				scrolling="auto" src="<%=contextPath%>/system/sms/send/left.jsp"></iframe>
		</div>
		<div id="center">
			<iframe name="edit" id="edit" frameborder="0"
				 src="<%=contextPath%>/system/sms/send/right.jsp"></iframe>
		</div>
	</div>
</body>
</html>