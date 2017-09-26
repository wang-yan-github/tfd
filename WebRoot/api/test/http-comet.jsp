<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<html>
<head>
  <title>http comet</title>
  <script>
  	$.ajax({
  		url:contextPath+"/api/HttpCometTest/comet1.act",
  		success:function(data){
  			$("body").append("<h1>"+data+"</h1>");
  		}
  	});
  	$.ajax({
  		url:contextPath+"/api/HttpCometTest/comet2.act",
  		success:function(data){
  			$("body").append("<h1>"+data+"</h1>");
  		}
  	});
  	$.ajax({
  		url:contextPath+"/api/HttpCometTest/comet3.act",
  		success:function(data){
  			$("body").append("<h1>"+data+"</h1>");
  		}
  	});
  </script>
</head>
<body>
</body>
</html>
