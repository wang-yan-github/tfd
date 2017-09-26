<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</meta>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/im/index.css"></link> 
	<script type="text/javascript" src="<%=contextPath%>/system/im/js/index.js"></script>
	<script language="JavaScript">
	if (window.Event)
	document.captureEvents(Event.MOUSEUP);
	function nocontextmenu()
	{
	event.cancelBubble = true
	event.returnValue = false;
	return false;
	}
	function norightclick(e)
	{
	if (window.Event)
	{
	if (e.which == 2 || e.which == 3)
	return false;
	}
	else
	if (event.button == 2 || event.button == 3)
	{
	event.cancelBubble = true
	event.returnValue = false;
	return false;
	}
	}
	document.oncontextmenu = nocontextmenu; // for IE5+
	document.onmousedown = norightclick; // for all others	
	</script>
</head>
<body>
	<div class="body">
		<div class="tab">
			<div class="menu-left" id="menu_left">主菜单</div>
			<div class="menu-right" id="menu_right" onclick="sendmsg();">快捷菜单</div>
		</div>
		<div class="menu-body">
			<div class="content" id="menu_content"></div>
		</div>
		<div class="short-menu">
			<div class="content" id="shortMenu_content"></div>
		</div>
	</div>
</body>
</html>
