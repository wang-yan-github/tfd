<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<%
	String result=request.getAttribute("result")==null?null:request.getAttribute("result").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产类别导入</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/fixedasset/type/js/import-index.logic.js"></script>
<script type="text/javascript">
    result=<%=result%>;
</script>
<style>
	html,body{
		height:100%;margin:0px;padding:0px;
	}
	html{overflow:hidden;}
	iframe{height:100%;width:100%;}
</style>

</head>
<body>
	<iframe src="<%=contextPath %>/fixedasset/type/import.jsp" frameborder="0" scrolling="auto"></iframe>
</body>
</html>