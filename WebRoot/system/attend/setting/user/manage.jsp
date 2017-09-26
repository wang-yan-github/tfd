<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%String userId=request.getParameter("userId");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
var userId='<%=userId%>';
var accountId="";
$(function(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendConfigList.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var selectHtml = "";
			$.each(data,function(index,data){
				selectHtml +="<option value="+data.attendConfigId+" >"+data.configName+"</option>";
			});
			$("#attendConfigId").html(selectHtml);
		}
	});
	requestUrl=contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoAct.act";
	var paramData={userId:userId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#userName").html(data.userName);
			$("#attendConfigId").val(data.attendConfigId);
			accountId=data.accountId;
		}
	});
});
function edit(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/changeUserAttendConfig.act";
	var paramData={accountId:accountId,attendConfigId:$("#attendConfigId").val()};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='1'){
				top.layer.msg("保存成功");
			}
		}
	});
}
function backToIndex(){
	parent.window.location = contextPath + "/system/attend/setting/index.jsp";
}
</script>
<style>
	td{text-align:center;}
</style>
<body >
<div align="center" style="margin-top: 10px;" >
<div class="list-group-item"  style="padding: 0px;width: 80%;">
<div class="widget-header bg-palegreen">
<span class="widget-caption">人员信息</span>
</div>
<table class="table table-striped table-condensed" >
<tr>
<td width="50%" >用户姓名:</td>
<td width="50%">排版类型：</td>
</tr>
<tr>
<td>
<div name="userName" id="userName"></div>
</td>
<td>
	<select id="attendConfigId" name="attendConfigId" >
	</select>
</td>
</tr>
</table>
</div>
</div>
<br>
<div align="center"><button onclick="edit();" class="btn btn-info" >保存</button> <button onclick="javascript:backToIndex();" class="btn btn-default">返回</button></div>
</body>
</html>