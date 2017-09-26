<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>部门管理</title>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/dept.css"></link>
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
<body style="style="overflow-y: hidden;margin:0px">
<div id="layout">
<div layout="north" id="north" min=20  height=50 style="height:50px;" class="top_info" >
	<div class="top_info_left icontop-basic_hover">
		<span class="title_name">部门管理</span>
	</div>
	<div style="margin-right: 20px;float:right;margin-top:6px;">
		<div style="float:left;"><input type="text" class="form-control" id="form_select" name="form_select" placeholder="请输入流程名称"></div>
		<div style="float:right;"><button type="submit" class="btn btn-primary" id="searchbtn" onclick="form_select()">查询</button></div>
	</div>
</div>


	<div layout="west" id="west" min=20 width=250 style="width:250px;">
	<iframe name="left" height="100%" width="100%" frameborder="0" scrolling="auto" src="<%=contextPath%>/system/unit/dept/dept-tree.jsp" /></iframe>
	</div>
<div layout="center" style="margin-top: 10px;">
	<iframe name="edit" height="100%" width="100%" style="float:right;"  frameborder="0" id="edit" src="<%=contextPath%>/system/unit/dept/add.jsp"/></iframe>
	</div>
</div>
</body>
</html>