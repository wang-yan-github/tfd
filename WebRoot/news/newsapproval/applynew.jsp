<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String newsId=request.getParameter("newsId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/news/newsapproval/js/applynews.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻审批</title>
<script type="text/javascript">
var newsId="<%=newsId%>";
</script>
</head>
<body>
<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">新闻审批</a>
   <div>
   <div style="dispaly:none;">
   <textarea rows="3" cols="40" name="deptPriv" id="deptPriv" style="display: none;"></textarea>
   <textarea rows="3" cols="40" name="accountId" id="accountId" style="display: none;" ></textarea>
   <input type="text" id="userPriv" name="userPriv" style="display:none;">
   <input type="text" id="createTime" name="createTime" style="display:none;">
  <div id="smsdiv" name="smsdiv" style="display: none;"></div>
   </div>
   <div align="center" style="background-color: white;"><div id="title" style="font-weight:550;font-size: 16px;line-height: 40px;"></div></div>
   <div id="foot" style="font-size: 14px;background-color: #F2F2F2;line-height: 30px;height: 30px;"align="right"></div></div>
   <div style="background-color: white;"><div id="contect"></div></div>
   <div><div style="float: left;" id="attachDiv" name="attachDiv"></div>
   </div>
   </div>
   </div>
   <div align="center">
    <div align="center"><button type="button" class="btn  btn-primary"  onclick="vianews();">通过</button>
   <button type="button" class="btn btn-danger "  onclick="notnews();">不通过</button>
   <button type="button" class="btn btn-default"  onclick="history.back();">返回</button></div>
</body>
</html>