<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>行政级别</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/tabs/style.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/tabs/tabs.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jqueryui/jquery.layout-latest.js"></script>
<script type="text/javascript">
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
//  	$("#layout").layout({auto:true});//开启布局器
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/unit/leadleave/add.jsp",title:"添加行政级别",active:true});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/unit/leadleave/set.jsp",title:"设置行政级别",active:false});
	$.addTab("tabs","tabs-content",{url:contextPath+"/system/unit/leadleave/query.jsp",title:"行政级别查询",active:false});
}
</script>
</head>
<body onload="doInit();" style="padding-top:5px;">
<div id="tabs" class="pane tfd_tab_header ui-layout-north"></div>
<div id="tabs-content" class="pane ui-layout-center"></div>
</body>
</html>