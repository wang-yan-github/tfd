<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>内部邮件</title>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/menu/menu.css"></link>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#layout").layout({
				auto : true
			});
		});
	</script>
	<style>
		html,body {
			height: 100%;
		}
		html {
			overflow: hidden;
		}
		body{overflow-y: hidden; margin: 0px}
	</style>
</head>
<body>
	<div id="layout">
		<div layout="north" id="north" min=20 height=50 style="height: 50px;">
			<div class="top_info">
				<div class="top_info_left icontop-basic_hover">
					<span class="title_name">菜单管理</span>
				</div>
			</div>
		</div>
		<div layout="west" id="west" min=20 width=250 style="width: 250px;">
			<iframe name="left" width="100%" height="100%" frameborder="0"
				scrolling="auto" src="<%=contextPath%>/system/menu/menu-tree.jsp"></iframe>
		</div>
		<div layout="center">
			<iframe name="edit" width="100%" height="100%" frameborder="0"
				scrolling="auto" id="mainFrame" name="mainFrame"
				src="<%=contextPath%>/system/menu/readme.jsp"></iframe>
		</div>
	</div>
</body>
</html>