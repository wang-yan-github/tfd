<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
<head>
<title>手机短信</title>
<script>
function gotourl(type)
{
	if(type==1)
		{
			$("#sec").removeClass("active");
			$("#frist").addClass("active");
			$("#content").attr("src",contextPath+"/system/mobilesms/send.jsp") ;
		}else if(type==2)
			{
			$("#frist").removeClass("active");
			$("#sec").addClass("active");
			$("#content").attr("src",contextPath+"/system/mobilesms/query.jsp") ;
			}
	}
</script>
<style>
html,body{
height:100%;
}
</style>
</head>
<body>
<div style="position:fixed;width: 100%;margin-top:0px;">
<ul class="nav nav-tabs">
   <li id="frist" class="active"><a href="javascript:void(0);" onclick="gotourl('1');">手机短信发布</a></li>
   <li id="sec"><a href="javascript:void(0);" onclick="gotourl('2');">手机短信查询</a></li>
</ul>
</div>
<div style="width:100%;height:100%;margin-bottom:40px;position:fixed;top:40px;"><iframe id="content"  src="<%=contextPath %>/system/mobilesms/send.jsp" name="content" scrolling="auto" frameborder="0"  style="width:100%;height:100%;"></iframe></div>
</body>
</html>