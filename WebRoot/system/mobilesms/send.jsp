<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String userName =request.getSession().getAttribute("USER_NAME").toString(); 
String deptNameLong =request.getSession().getAttribute("DEPT_NAME_LONG").toString(); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<title>手机短信</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script>
var userName="<%=userName%>";
var deptNameLong="<%=deptNameLong%>";
function sendMessage()
{
	var requestUrl=contextPath+"/tfd/system/mobilesms/act/MobileSmsAct/sendMoblieSmsAct.act";
	var param=$("#form1").serialize(); 
	$.ajax({
			url:requestUrl,
			dataType:"text",
			data:param,
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="OK")
					{
					alert("短已发送到后台，服务器正在发送...");
					}
				}
		});
	}
function setname()
{
	var sendContent=$("#sendContent").val();
	$("#sendContent").val(sendContent+"\n"+deptNameLong+" ： "+userName+"");
	}
</script>
<head>
</head>
<body>
<div style="height: 100%;"  align="center">
<div class="panel panel-info" style="width: 800px;">
   <div class="panel-heading" align="left">
      <h3 class="panel-title">
       手机短信发送
      </h3>
   </div>
   <div class="panel-body" >
   <form id ="form1" name ="form1">
<table class="table table-striped " style="width: 800px;" align="center">
<tr>
<td style="width: 100px;">单位内人员:</td>
<td>
	<div class="col-xs-8"   style="float: left;">
		<input type="hidden" id="accountId" name="accountId" />
		<textarea id="userName" name="userName"  rows="5"class="form-control"></textarea>
	</div>
	<div>
		<a style="">添加</a><a>删除</a>
	</div>
</td>
</tr>
<tr>
<td>外部联系人:</td>
<td>
<div class="col-xs-8">
<textarea id="outSideNo" name="outSideNo"  rows="5" class="form-control"></textarea>
<div>请用“，”隔开联系人的手机号码！
</div>
</div>
</td>
</tr>
<tr>
<td colspan="2">
<textarea id="sendContent" name="sendContent"  rows="5" class="form-control"></textarea>
</td>
</tr>
<tr>
<td>
定时发送:
</td>
<td>
<div class="col-xs-4">
<input type="text" name="sendTime" id="sendTime" size="20" style="cursor: pointer;"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择时间">
</div>
</td>
</tr>
<tr>
<td colspan="2">
<div align="center">
	<button type="button" name="send"  id="send" class="btn btn-default" onclick="sendMessage();">发送</button>
	<button type="button" name="reset" id="reset" class="btn btn-default">重置</button>
	<button type="button" name="qm" id="qm" class="btn btn-default" onclick="setname();">签名</button>
 </div>
</td>
</tr>
</table>
</form>
</div>
</div>
</div>
   </body>
   </html>