<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/district/jquery.fn.district.css">

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/district/jquery.fn.district.js"></script>
<script>
	$(function(){
		$("#district").district();
		$("#district2").district();
	});
</script>
</head>

<body>
	<input id="district"/>
	<input id="district2"/>
</body>
</html>