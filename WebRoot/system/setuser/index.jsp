<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String type = request.getParameter("type");
%> 
<html>
<head>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
</head>
<style>
html,body{
height: 100%;
width:100%;
margin:0px;
padding:0px;
}
html{
overflow: hidden;
}
</style>
<script type="text/javascript">

var type = "<%=type%>";
function doinit(){
	$("#layout").layout({auto:true});
	if(type!="null"){
		$("#mainFrame").attr("src","<%=contextPath%>/system/setuser/updatepwd.jsp");
	}else{
		$("#mainFrame").attr("src","<%=contextPath%>/system/setuser/right.jsp");
	}
}
</script>
<body onload="doinit()" >
<div id="layout">
	<div layout="west" id="west" min=20 width=250 style="width:250px;">
		<iframe name="left" width="100%" height="100%" frameborder="0" scrolling="auto" id="left" src="<%=contextPath%>/system/setuser/left.jsp" ></iframe>
	</div>
	<div layout="center">
		<iframe name="edit" width="100%" height="100%" frameborder="0" scrolling="auto" id="mainFrame"  src="" ></iframe>
	</div>
</div>
</body>
</html>