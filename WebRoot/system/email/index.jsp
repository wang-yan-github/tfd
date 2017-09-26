<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String emailId = request.getParameter("emailId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<title>内部邮件</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
html{
overflow: hidden;
}

</style>
<script type="text/javascript">
var emailId = "<%=emailId%>";
$(document).ready(function(){
	$("#layout").layout({auto:true});
	if(emailId != "null"){
		$("#right").prop("src","<%=contextPath%>/system/email/inbox/readEmail.jsp?emailId="+emailId);
	}else{
		$("#right").prop("src","<%=contextPath%>/system/email/inbox/index.jsp");
	}
});


</script>
</head>
<body>
<div id="layout">
	<div layout="west" id="west" min=20 width=200 style="width:200px;border-right:1px #CCC solid;">
		<iframe name="left" width="100%" height="100%" frameborder="0" scrolling="auto" id="left" src="left.jsp" ></iframe>
	</div>
	<div layout="center">
		<iframe name="edit" width="100%" height="100%" frameborder="0" scrolling="auto" id="right"  src=""></iframe>
	</div>
</div>
</body>
</html>