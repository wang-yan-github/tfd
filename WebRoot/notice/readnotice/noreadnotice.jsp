<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/notice/notice.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<script type="text/javascript" src="<%=contextPath%>/notice/readnotice/js/noreadnotice.js"></script>
<title>未读公告</title>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >未读公告
</span>
<div class="col-xs-12 form-group" style="float: left;margin-top:8px; width: 150px;">
<div id="noticeType"></div>
</div>
</div>
</div>
<div id="myTable" name="myTable"></div>
</body>
</html>