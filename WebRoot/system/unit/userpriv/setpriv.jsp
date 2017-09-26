<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String userPrivId=request.getParameter("userPrivId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统权限设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/unit/userpriv/js/setpriv.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/menu/setpriv.css"></link>
</head>
<script type="text/javascript">
	var userPrivId='<%=userPrivId%>';
</script>
<body>
	<div class="easyui-layout" id="body" data-options="fit:true">
		<div id="north" data-options="region:'north',border:false">
			<div class="widget-header bordered-bottom bordered-sky">
				<span class="widget-caption" style="font-weight:bold !important;">权限设置</span>
			</div>
		</div>
		<div id="center" data-options="region:'center',border:false">
			<div id="menudiv"></div>
		</div>
		<div id="south" data-options="region:'south',height:40,border:false">
			<div style="margin-top:2px;text-align:center;">
				<input type="button" value="保存" onclick="doSvae();" class="btn btn-primary" >
				<input type="button" value="返回" onclick="history.back();" class="btn btn-warning" >
			</div>
		</div>
	</div>
</body>
</html>