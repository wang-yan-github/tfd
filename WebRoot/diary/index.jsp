<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>日志信息</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/tabs/style.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/tabs/tabs.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jqueryui/jquery.layout-latest.js"></script>
<script type="text/javascript">
function init(){
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
	$.addTab("tabs","tabs-content",[{title:"日志信息",url:contextPath+"/diary/personaldiary/personaldiary.jsp",active:true},
	                                {title:"日志检索",url:contextPath+"/diary/otherdiary/searchdiary.jsp",active:false}
	                                ]);
}
</script>
</head>
<body onload="init();">
<div id="tabs" class="pane tfd_tab_header ui-layout-north"></div>
<div id="tabs-content" class="pane ui-layout-center"></div>
</div>
</body>
</html>