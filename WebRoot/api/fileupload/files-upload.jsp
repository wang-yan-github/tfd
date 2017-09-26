<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/main/index.css"></link>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script>
	$(function(){
		$.each(["inputA","inputB","inputC"],function(i,data){
			$.ajax({
				url:"template.txt",
				dataType:"text",
				success:function(text){
					$("body").append(text.replace(/#module#/g,"news").replace(/#attach#/g,data));
					filesUpLoad("news",data);
				}
			});
		});
	});
</script>
</head>

<body>
	
</body>
</html>