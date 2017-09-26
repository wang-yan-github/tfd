<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String accountId =request.getSession().getAttribute("USER_ID").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/system/attend/count/js/address.logic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<style type="text/css">
	.content-body{width:80%;margin-left:10%;margin-top:50px;}
	.content-body table tr td{text-align:center;}
	.title{height:40px;line-height:40px;font-size:18px;padding-left:20px;position:fixed;margin-top:-50px;width:100%;
		background-color:#F5F5F5;}
</style>
<script type="text/javascript">	
var accountId = "<%=accountId%>";
var now = new Date();
var month = now.getMonth()+1;
if(month<10){
	month = "0"+month;
}
var date = now.getFullYear()+"-"+month;
$(function(){
	doinit();
	//setAttendRegist();
	$("#btn_back").click(function(){
		history.go(-1);
		return false;
	})
})
function goMonth(){
	window.location = contextPath + "/system/attend/month.jsp";
}
function goToday(){
	window.location = contextPath + "/system/attend/index.jsp";
}
function goAddress(){
	window.location = contextPath + "/system/attend/address.jsp";
}
</script>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >本月定位考勤记录</span>
</div>
<div style=" float:right; width:300px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-primary" onclick="goAddress();" >本月定位考勤</button>
	<button style="float:right;" class="btn btn-info" onclick="goMonth();" >本月考勤</button>
	<button style="float:right;" class="btn btn-info" onclick="goToday();" >今日考勤</button>
</div>
</div>
<div class="content-body" >
<table class="table table-striped" style="margin-bottom:50px;" >
	<tr>
		<td>序号</td>
		<td>登记时间</td>
		<td>登记地点</td>
		<td>操作</td>
	</tr>
	<tbody id="content" name="content"></tbody>
</table>
</div>
<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
         <div class="msg-content">暂无数据</div>
      </td>
      <td class="right"></td>
   </tr>
   <tr class="foot">
      <td class="left"></td>
      <td class="center"><b></b></td>
      <td class="right"></td>
   </tr>
   </tbody>
</table>

<div class="modal fade" id="myModals" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
      <form   method="post" name="form1" id="form1" class="form-horizontal" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="myModalLabel">详细信息</h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"  ></iframe>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
         </div>
         </form>
      </div>
</div>
</div>
</body>
</html>