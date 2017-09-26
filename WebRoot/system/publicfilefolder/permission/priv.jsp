<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
	String folderId = request.getParameter("folderId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>公共资源</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/tabs/style.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/tabs/tabs.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jqueryui/jquery.layout-latest.js"></script>
<script type="text/javascript">
var folderId = "<%=folderId%>";
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
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/accessPriv.jsp?folderId="+folderId,title:"访问权限",active:true});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/editPriv.jsp?folderId="+folderId,title:"编辑权限",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/delPriv.jsp?folderId="+folderId,title:"删除权限",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/commPriv.jsp?folderId="+folderId,title:"评论权限",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/newPriv.jsp?folderId="+folderId,title:"新建权限",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/downPriv.jsp?folderId="+folderId,title:"下载/打印权限",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/publicfilefolder/permission/allPriv.jsp?folderId="+folderId,title:"批量设置",active:false});
}
</script>
<style>
.tee_tab {
	border: 0px;
	height: 38px;
	width: 100px;
	font-size: 12px;
	float: left;
	cursor: pointer;
	line-height: 25px;
	text-align: center;
	padding: 0px;
	margin-left: 5px;
}
.tee_tab:hover {
	background: none;
	background-image: url(<%=imgPath%>/tabs_bg_select_1.png);
	background-repeat: repeat-x;
	color: white;
}
.tee_tab_select {
	background: none;
	background-image: url(<%=imgPath%>/tabs_bg_select_1.png);
	background-repeat: repeat-x;
	color: white;
}
</style>
</head>
<body onload="doInit();" style="padding-top:5px;">
<div id="tabs" class="pane tfd_tab_header ui-layout-north"></div>
<div id="tabs-content" class="pane ui-layout-center"></div>
</body>
</html>