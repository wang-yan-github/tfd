<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String newsId=request.getParameter("newsId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<% String userName = session.getAttribute("USER_NAME").toString(); %> 
<%String status=request.getParameter("status"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/news/news.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/news/read/js/readnews.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阅读新闻</title>
<script type="text/javascript">
var newsId="<%=newsId%>";
var userName="<%=userName%>";
var status="<%=status%>";
</script>
<style >
.top{
line-height: 50px;
font-size: 14px;
background-color: #F2F2F2; 
}
.topName{
float:left;
margin-left: 10px;
width: 80px;
}
.topTime{
font-size:12px;
float: left;
margin-left: 50px;
}
.boderdiv{
width:90%;
border:solid 1px #9A9A9A;
}
.commPcon{
font-weight: bold;
}
</style>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >新闻查看
</span>
</div>
</div>
   <div class="panel-body" style="width: 98%;margin-left: 1%;margin-top: 10px;">
   <div>
   <div align="center"><div id="title" style="font-weight:550;font-size: 16px;line-height: 40px;"></div></div>
   <div id="foot" style="font-size: 14px;background-color: #F2F2F2;line-height: 30px;height: 30px;"align="right"></div></div>
   <div><div id="contect"></div></div>
   <div><div style="float: left;" id="attachDiv" name="attachDiv"></div>
   </div>
   </div>
   </div>
   
   <div  class="panel panel-info" style="width: 98%;margin-left: 1%;text-align: center;margin-top: 20px;" id="commentsdiv">
   <div  class="panel-body">
   <table class="table">
   <tr>
   <td align="center" style="font-weight: bold;font-size: 14px;">最新的五条评论</td>
   </tr>
   <tbody id="CommentsCon">
   </tbody>
   </table>
   </div>
   </div>
   
   <div id="newsComments" name="newsComments" class="panel panel-info" style="width:98%;margin-left: 1%;text-align: center;" >
   <div  class="panel-body">
   <table class="table table-striped table-condensed">
   <tr>
   <td colspan="2" align="center" style="font-weight: bold;font-size: 14px;" id="titleName"> 发表评论</td>
   </tr>
   <tr id="commPname"></tr>
   <tr>
   <td width="100px;">署名：</td>
   <td>
   <input id="commPid" name="commPid" type="hidden" />
   <div class="col-xs-3 form-group">
   <input id="commName" name="commName" type="text" class="form-control" maxlength="15"/>
   </div>
   </td>
   </tr>
   <tr>
   <td> 内容：</td>
   <td>
   <div class="col-xs-6 form-group">
   <textarea rows="5" cols="50" class="form-control" id="commContect" name="commContect"></textarea>
   </div>
   </td>
   </tr>
   </table>
   <div align="center">
   <button type="button" class="btn btn-primary"  onclick="saveComments();" id="savebtn">发表</button>
   <button type="button" class="btn btn-default" onclick="lookComments();" id="look">查看所有评论</button>
   <button type="button" class="btn btn-default histbtn">返回</button>
   </div>
      </div>
      </div>
     <div align="center"  id="backbtn" style="display: none;margin-top: 20px;">
    <button type="button" class="btn btn-default histbtn">返回</button>
   	</div>
</body>
</html>