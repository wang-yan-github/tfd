<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String noticeId=request.getParameter("noticeId"); %>
<%String status=request.getParameter("status"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/notice/notice.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/notice/readnotice/js/readnotice.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>阅读公告</title>
<script type="text/javascript">
var noticeId="<%=noticeId%>";
var status="<%=status%>";
</script>
</head>
<body style="overflow-y: auto;background-color: white;">
<div style="position: fixed;width: 100%;background-color: white;">
<div class="top_info" style="height: 40px;">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="font-size: 16px;line-height: 40px;" >查看公告
</span>
</div>
</div>
</div>
<div class="panel-body">
   <div style="position: fixed;width: 100%;background-color: white;margin-top: 40px;">
   <div align="center">
   <div id="title" class="container">
   </div>
   </div>
   <div id="foot" style="font-size: 14px;background-color: #F2F2F2;line-height: 30px;height: 30px;padding-right: 50px;" align="right" >
   </div>
   </div>
   <div>
   <div id="noticeContent" style="padding-left: 50px;padding-right: 50px;margin-top: 130px;"></div>
   </div>
   <div><div style="float: left;" id="attachDiv" name="attachDiv"></div>
   </div>
   </div>
   
   <div style="position:fixed; bottom: 0px;text-align: center;width: 100%;background-color: white;">
   <button type="button" class="btn btn-default" onclick="upNotice();">上一篇</button>
   <button type="button" class="btn btn-default" onclick="downNotice();">下一篇</button>
   <button type="button" class="btn btn-default histbtn">返回</button>
   </div>
</body>
</html>