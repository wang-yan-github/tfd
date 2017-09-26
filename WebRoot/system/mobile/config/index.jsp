<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<title>移动应用配置</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#layout").layout({auto:true});
});
</script>
<head>
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
		<div layout="west" id="west" min=20 width=500 style="width:500px;">
					<div class="panel panel-primary" style="float:left;width: 170px;margin-left: 30px;">
							   <div class="panel-heading">
							      <h3 class="panel-title">面板标题</h3>
							   </div>
							   <div class="panel-body">
							      这是一个基本的面板
						 	 </div>
					</div>
				<div style="float: right;"><img src="<%=imgPath%>/mobile/iphone.png" /></div>
		</div>
		<div layout="center">
				
		</div>
</div>
</body>
</html>