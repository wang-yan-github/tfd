<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String photoId = request.getParameter("photoId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加相册</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/tabs/style.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/tabs/tabs.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jqueryui/jquery.layout-latest.js"></script>
<script type="text/javascript">
var photoId = "<%=photoId%>";
function doInit(){
	$("body").layout({
		name:"outerLayout",
		north:{
			size:35,
			slidable:false,
			resizable:false,
			spacing_open:0,
			spacing_closed:0
		}
	});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/photo/priv/readPriv.jsp?photoId="+photoId,title:"访问权限",active:true});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/photo/priv/managePriv.jsp?photoId="+photoId,title:"管理权限",active:false});
}
</script>
</head>
<body onload="doInit();" style="padding-top:5px;">
<div id="tabs" class="pane tfd_tab_header ui-layout-north"></div>
<div id="tabs-content" class="pane ui-layout-center"></div>
</body>
</html>