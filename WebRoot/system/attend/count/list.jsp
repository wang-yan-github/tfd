<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String startDate = request.getParameter("startDate");
	String deptId = request.getParameter("deptId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>
<style type="text/css">
	.content-body{width:80%;margin-left:10%;margin-top:50px;}
	.content-body table tr td{text-align:center;}
	.title{height:40px;line-height:40px;font-size:18px;padding-left:20px;position:fixed;margin-top:-50px;width:100%;
		background-color:#F5F5F5;}
</style>
<script type="text/javascript">
var startDate = "<%=startDate%>";
var deptId = "<%=deptId%>";
$(function(){
	$("#btn_back").click(function(){
		history.go(-1);
		return false;
	})
});
function doinit(){
	ajaxLoading();
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendByTimeAndDept.act";
	$.ajax({
		url:requestUrl,
		data:{startDate:startDate,deptId:deptId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			$.each(data,function(index,data){
				html += "<tr><td>"+data.deptName+"</td>";
				html += "<td><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data.accountId+"')\" >"+data.userName+"</a></td>";
				html += "<td>"+data.shouldNum+"</td>";
				html += "<td>"+data.Actual+"</td>";
				html += "<td>"+data.Normal+"</td>";
				html += "<td>"+data.Later+"</td>";
				html += "<td>"+data.Leave+"</td>";
				if(data.Address == '0'){
					html += "<td>"+data.Address+"</td>";
				}else{
					html += "<td>"+data.Address+"(<a href=\"javascript:void(0);\" onclick=\"javascript:showAddress('"+data.accountId+"');\" title=\"查看详情\" >查看详情</a>)</td>";
				}
				
				html += "<td><a href=\"javascript:void(0);\" onclick=\"javascript:toShowOne('"+data.accountId+"');\" >详细信息</a></td></tr>";
			});
			$("#content").html(html);
			if(html == ""){
				$(".content-body").hide();
				$(".MessageBox").show();
			}else{
				$(".content-body").show();
				$(".MessageBox").hide();
			}
			ajaxLoadEnd();
		}
	});
}
function showAddress(accountId){
	window.location = contextPath + "/system/attend/count/address.jsp?accountId="+accountId+"&date="+startDate;
}
function toShowOne(accountId){
	window.location = contextPath + "/system/attend/count/detail.jsp?accountId="+accountId+"&date="+startDate;
}
function expartList(){
	window.location.href = contextPath + "/tfd/system/attend/act/AttendAct/AttendListExport.act?deptId="+deptId+"&startDate="+startDate ;
}
</script>
</head>
<body onload="doinit();" >
<div class="title" >
	<img alt="" src="/tfd/system/styles/images/code/system.png" width="24" height="24" />
	<span style="position:relative;top:4px;left:10px;">上下班统计 - [<%=startDate %>]</span>
	<span style="margin-left:20px;" ><input type="button"  value="返回" class="btn btn-default btn-sm" id="btn_back" /></span>
	<span style="margin-left:20px;" ><input type="button"  value="导出" class="btn btn-default btn-sm" onclick="javascript:expartList();" /></span>
</div>
<div class="content-body" >
<table class="table table-striped" style="margin-bottom:50px;" >
	<tr>
		<td>所在部门</td>
		<td>人员</td>
		<td>应有考勤</td>
		<td>实有考勤</td>
		<td>正常考勤</td>
		<td>迟到</td>
		<td>早退</td>
		<td>定位</td>
		<td>操作</td>
	</tr>
	<tbody id="content" name="content"></tbody>
</table>
</div>
<table class="MessageBox" style="display:none;margin-top:50px;" align="center" width="440" cellpadding="0" cellspacing="0">
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
</body>
</html>