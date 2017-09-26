<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/system/attend/js/index.logic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>
<style type="text/css">
	.content-body{width:80%;margin-left:10%;margin-top:30px;}
	.content-body table tr td{text-align:center;}
</style>
<script type="text/javascript">	
$(function(){
	doinit();
	setAttendRegist();
	$("#btn-back").click(function(){
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
<span class="title_name" style="float:left;" >今日上下班记录</span>
</div>
<div style=" float:right; width:300px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-info" onclick="goAddress();" >本月定位考勤</button>
	<button style="float:right;" class="btn btn-info" onclick="goMonth();" >本月考勤</button>
	<button style="float:right;" class="btn btn-primary" onclick="goToday();" >今日考勤</button>
</div>
</div>
<table class="MessageBox" style="margin-top:20px;" align="center" width="500" cellpadding="0" cellspacing="0">
	   <tbody><tr class="head-no-title">
	      <td class="left"></td>
	      <td class="center">
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="msg">
	      <td class="left"></td>
	      <td class="center info">
	         <div class="msg-content">规定时间之前<span id="beforeWork" ></span>分钟到之后 <span id="afterWork" ></span> 分钟这段时间可进行上班登记，规定时间之前 <span id="beforeBack" ></span> 分钟到之后 <span id="afterBack" ></span> 分钟这段时间可进行下班登记</div>
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
<div class="content-body" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         今日上下班登记
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped">
<tr>
<td>登记次序</td>
<td>登记类型</td>
<td>规定时间</td>
<td>登记时间</td>
<td>操作</td>
</tr>
<tbody id="content" name="content"></tbody>
</table>
</div>
</div>
</div>

<div class="modal fade" id="myModal_remark" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">请说明有关情况（如迟到或早退原因，加班情况等）：</h4>
         </div>
         <div class="modal-body">
         	<input type="hidden" id="attendId" />
         	<textarea rows="5" id="remark" cols="75"></textarea>
         </div>
         <div class="modal-footer">
          <button type="button" id="btn_update" onclick="javascript:updateRemark();" class="btn btn-primary">确认</button>
            <button type="button" class="btn btn-default btn_close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>

</body>
</html>