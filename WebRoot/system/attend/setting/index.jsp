<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>
<title>考勤管理</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
html{
overflow-x: hidden;
}
.title{width:150px;height:40px;line-height:40px;font-size:18px;text-align:center;}
.btn-margin{margin-left:15px;}
</style>
</head>
<body>
	<div class="title" ><img alt="" src="/tfd/system/styles/images/code/system.png" width="24" height="24" /><span style="position:relative;top:4px;left:10px;">考勤管理</span></div>
	<div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">上下班考勤登记设置</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" onclick="javascript:ToSetAttendConfig();" >排版类型</button>
	<button type="button" class="btn btn-default btn-margin" onclick="javascript:ToSetAttendRegist();" >登记时间段</button>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >免签人员</button>
	<button type="button" class="btn btn-default btn-margin" onclick="javascript:ToSetHoliday();" >免签节假日</button>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >考勤方式</button>
</td>
</tr>
</table>
</div>
</div>
    <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">考勤数据管理</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >删除考勤数据</button>
</td>
</tr>
</table>
</div>
</div>
      <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">设置考勤审批规则</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >设置考勤审批规则</button>
</td>
</tr>
</table>
</div>
</div>
      <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">外出原因填写要求</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >外出原因填写要求</button>
</td>
</tr>
</table>
</div>
</div>
      <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">考勤机设置</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >考勤机设置</button>
</td>
</tr>
</table>
</div>
</div>
      <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">设置代考勤人员</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" disabled="disabled" >设置代考勤人员</button>
</td>
</tr>
</table>
</div>
</div>
      <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary"> 设置考勤排班类型</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <table class="table table-striped"  >
<tr>
<td>
	<button type="button" class="btn btn-default btn-margin" onclick="javascript:ToSetAttendUser();" >设置考勤排班类型</button>
</td>
</tr>
</table>
</div>
</div>
</body>
<script type="text/javascript">
function ToSetAttendConfig(){
	window.location = contextPath + "/system/attend/setting/config/setAttendConfig.jsp";
}
function ToSetAttendRegist(){
	window.location = contextPath + "/system/attend/setting/regist/index.jsp";
}
function ToSetAttendUser(){
	window.location = contextPath + "/system/attend/setting/user/index.jsp";
}
function ToSetHoliday(){
	window.location = contextPath + "/system/attend/setting/holiday/index.jsp";
}
</script>
</html>