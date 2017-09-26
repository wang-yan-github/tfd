<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<style type="text/css">
	html,body{height: 100%;margin:0px;padding:0px;}
html{overflow: hidden;}
.floder{width:40%;height:300px;position:absolute;top:50px;left:30%;}
.beLeft{line-height:30px;float:left;}
</style>
<script type="text/javascript">	
$(function(){
	//doinit();
	//setAttendRegist();
	$("#btn-back").click(function(){
		history.go(-1);
		return false;
	})
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	if(month<10){
		month = "0"+month;
	}
	$("#startDate").val(year+"-"+month);
})
</script>
</head>
<body>
<div class="floder" >
<form id="form1" name="form1" class="form-horizontal" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
        考勤统计查询
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
   <td>月份:</td>
   <td><div class="col-xs-12 form-group" ><input type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})" id="startDate" name="startDate" class="form-control" /></div></td>
   </tr>
   <tr>
   <td>部门:</td>
   <td><div class="col-xs-12 form-group" ><input type="hidden" name="deptPriv" id="deptPriv" /><input type="text" readonly="readonly" id="deptName" name="deptName" class="form-control" onclick="deptinit(['deptPriv','deptName']);" /></div></td>
   </tr>
</table>
</div>
</div>
<div align="center" >
	<input type="button"  value="查询" onclick="javascript:queryAttend();" class="btn btn-primary" id="btn_save" />
</div>
</form>
</div>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
function queryAttend(){
	var startDate = $("#startDate").val();
	var deptId = $("#deptPriv").val();
	if(deptId == ""){
		top.layer.msg("请选择部门",function(){});
		return;
	}
	window.location = contextPath + "/system/attend/count/list.jsp?startDate="+startDate+"&deptId="+deptId;
}
</script>
</html>