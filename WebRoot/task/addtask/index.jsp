<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
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
	<iframe name="sort" width="100%" height="100%" frameborder="0" scrolling="auto" src="<%=contextPath%>/task/addtask/lefttree.jsp" ></iframe>
	</div>
<div layout="center">
	<iframe name="edit" width="100%" height="100%" frameborder="0" scrolling="auto" id="edit"  src="<%=contextPath%>/task/addtask/right.jsp"></iframe>
	</div>
</div>
</body>
  
</html>