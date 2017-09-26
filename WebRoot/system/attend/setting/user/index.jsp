<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String userId =request.getSession().getAttribute("USER_ID").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<title>内部邮件</title>
<style>
html,body{height: 100%;margin:0px;padding:0px;}
html{overflow: hidden;}
</style>
<script>
var userId = "<%=userId%>";
$(document).ready(function(){
	$("#layout").layout({auto:true});
	var requestUrl = contextPath + "/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoByAccountIdAct.act";
	$.ajax({
		url:requestUrl,
		data:{accountId:userId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#edit").prop("src","<%=contextPath%>/system/attend/setting/user/manage.jsp?userId="+data[0].userId)
		}
	});
});
</script>
<body>
<div id="layout">
<div layout="west" id="west" min=20 width=250 style="width:250px;">
	<iframe name="left" height="100%" width="100%" frameborder="0" scrolling="auto" src="<%=contextPath%>/system/attend/setting/user/lefttree.jsp"></iframe>
</div>
<div layout="center">
	<iframe name="edit" id="edit" height="100%" width="100%" style="float:right;"  frameborder="0" id="edit" src=""></iframe>
</div>
</div>
</body>
</html>