<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日程安排与查询</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#layout").layout({auto:true});
});
</script>
<style>
html,body{
height: 100%;
}
html{
overflow: hidden;
}
</style>
</head>
<body style="margin:0px">
<div id="layout">
	<div layout="west" id="west" min=20 width=250 style="width:250px;">
	<iframe name="sort" height="100%" width="100%" frameborder="0" id="sort" scrolling="auto" src="<%=contextPath%>/calendar/leader/left.jsp"></iframe>
	</div>
<div layout="center">
	<iframe name="edit" height="100%" width="100%" frameborder="0" id="edit"  src="<%=contextPath%>/calendar/leader/right.jsp"></iframe>
	</div>
</div>
</body>
</html>