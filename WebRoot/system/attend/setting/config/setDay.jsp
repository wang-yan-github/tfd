<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String attendConfigId = request.getParameter("attendConfigId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加相册</title>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<style type="text/css">
	.type2,.type3{display:none;}
	a{text-decoration:none;}
</style>
<script type="text/javascript">
var attendConfigId = "<%=attendConfigId%>";
function editDay(){
	var publicDay = "";
	var  obj = document.getElementsByName('publicDay');
	for(var i=0; i<obj.length; i++){    
		if(obj[i].checked) publicDay+=obj[i].value+',';
	}
	if(publicDay!=""){
		publicDay = publicDay.substr(0,publicDay.length-1);
	}
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/editPublicDay.act";
	$.ajax({
		url:requestUrl,
		data:{
			attendConfigId:$("#attendConfigId").val(),
			publicDay:publicDay
		},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=="1"){
				top.layer.msg("保存成功");
				doint();
			}
		}
	}); 
}
$(function(){
	doint();
})

function doint(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendConfigById.act";
	$.ajax({
		url:requestUrl,
		data:{
			attendConfigId:attendConfigId
		},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#attendConfigId").val(data.attendConfigId);
			$("#configName").html(data.configName);
			var day = data.publicDay;
			if(day!=''&&day!="null"&&day!=null){
				var days = day.split(",");
				for(var i = 0 ; i < days.length ; i++ ){
					$("#day"+days[i])[0].checked = true;
				}
			}
		}
	});
}
function ToSetAttendConfig(){
	window.location = contextPath + "/system/attend/setting/config/setAttendConfig.jsp";
}
</script>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal"  >
<input type="hidden" id="attendConfigId" />
<div align="center" style="margin-top:30px;" >
<div class="list-group" style="margin-bottom: 0px;width:40%;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         设置<span id="configName" ></span>的公休日
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped ">
<tr>
<td>
	<input type="checkbox" value="1" name="publicDay" id="day1" />星期一<br/>
	<input type="checkbox" value="2" name="publicDay" id="day2" />星期二<br/>
	<input type="checkbox" value="3" name="publicDay" id="day3" />星期三<br/>
	<input type="checkbox" value="4" name="publicDay" id="day4" />星期四<br/>
	<input type="checkbox" value="5" name="publicDay" id="day5" />星期五<br/>
	<input type="checkbox" value="6" name="publicDay" id="day6" />星期六<br/>
	<input type="checkbox" value="7" name="publicDay" id="day7" />星期天
</td>
</tr>
</table>
</div>
</div>
</div>
<div align="center">
<input type="button" class="btn btn-primary" onclick="javascript:editDay();"  value="确定" >
<button type="button" class="btn btn-default" onclick="javascript:ToSetAttendConfig();"  id="btn_back"  >取消</button>
</div>
</form>
</body>
</html>