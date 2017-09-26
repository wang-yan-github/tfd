<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<title>公共资源</title>
<style type="text/css">
	html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}

</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#layout").layout({auto:true});
});
</script>
</head>
<body style="overflow-y:hidden;">
<input type="hidden" id="copyJson" />
<div id="layout">
	<div layout="west" id="west" min=20 width=250 style="width:250px;">
		<iframe name="left" width="100%" height="100%" frameborder="0" scrolling="auto" id="left" src="diskDir.jsp" ></iframe>
	</div>
	<div layout="center">
		<iframe name="right" width="100%" height="100%" frameborder="0" scrolling="auto" id="right" src="manage.jsp" ></iframe>
	</div>
</div>
</body>
</html>