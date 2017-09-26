<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script>
	(function($){
		
		$.fn.optAlert(function(){
			
		});
	})(jQuery);
</script>
<style>
	.alert-opt{
		width:250px;text-align:center;position:fixed;
		top:50%;margin-top:-50px;
		left:50%;margin-left:-125px;
		z-index:20000;
	}
	.alert-opt-screen{
		width:100%;height:100%;position:fixed;
		top:0px;left:0px;background-color:#ccc;
		
		filter: alpha(opacity =   10);
		-moz-opacity: 0.1;
		-khtml-opacity: 0.1;
		opacity: 0.1;
	}
</style>
</head>

<body>
	<div class="alert alert-success alert-opt">
		<span class="alert-icon glyphicon glyphicon-ok-sign"></span>
		<span class="alert-text">操作成功</span>
	</div>
	<div class="alert-opt-screen"></div>
	
	<div class="alert alert-danger alert-opt">
		<span class="alert-icon glyphicon glyphicon-ok-sign"></span>
		<span class="alert-text">操作成功</span>
	</div>
</body>
</html>