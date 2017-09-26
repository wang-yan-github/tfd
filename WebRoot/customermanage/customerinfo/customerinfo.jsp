<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>客户列表</title>
<%String customerId=request.getParameter("customerId"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/tabs/style.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/tabs/tabs.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jqueryui/jquery.layout-latest.js"></script>
<script>
var customerId="<%=customerId%>";
$(function (){
	doInit();
});
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
	$.addTab("tabs","tabs-content",[{title:"我的客户",url:contextPath+"/customermanage/customerinfo/customerTable.jsp",active:true},
	                                {title:"下属的客户",url:contextPath+"/customermanage/customerinfo/branchcustomer.jsp",active:false},
	                                {title:"参与的客户",url:contextPath+"/customermanage/customerinfo/sharecustomer.jsp",active:false}
	                                ]);
}
</script>
</head>
<body>
<div id="tabs" class="pane tfd_tab_header ui-layout-north"></div>
<div id="tabs-content" class="pane ui-layout-center"></div>
</body>
</html>