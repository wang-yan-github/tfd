<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改集团单位信息</title>
<script type="text/javascript">
function doinit(){
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/getUnitInfoAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		async:false,
		success:function(data){
			var org=data;
			for(var name in org){
				$("#"+name).val(org[name]);
			}
		}
	});
}
function checkorg(){
	var status;
	var url = "<%=contextPath%>/tfd/system/unit/org/act/UnitAct/checkorgAct.act";
	$.ajax({
		url:url,
		dataType:"text",
		async:false,
		data:{orgName:$("#orgName").val()},
		success:function(data){
			if(data!=0){
				status=false;
			}else{
				status=true;
			}
		}
	});
	return status;
}
</script>
</head>
<body onload="doinit()">
<div align="center" style="margin-top:20px;">
<form name="form1" id="form1"  class="form-horizontal" action="<%=contextPath%>/tfd/system/unit/org/act/UnitAct/saveUnitInfoAct.act">
  <div class="list-group"  style="width:60%;">
  		<a class="list-group-item active">组织机构信息</a>
	  <div  class="list-group-item" style="height: 55px;">
	  			<div style="float: left;width: 120px;padding-top:8px;">单位名称:</div>
	  			<div class="col-xs-6 form-group"><input id="orgName" name="orgName" type="text" class="form-control" ></div><span style="color:red;line-height: 30px;">*软件注册后不要轻易修改！</span>
	 </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">单位电话:</div>
	 	 	<div class="col-xs-6 form-group"><input id="orgTel" name="orgTel" type="text" class="form-control"></div>
	  </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">单位传真:</div>
	  		<div class="col-xs-6 form-group"><input id="orgFax" name="orgFax" type="text" class="form-control"></div>
	  </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">单位地址:</div>
	  		<div class="col-xs-6 form-group"><input id="orgAdd" name="orgAdd" type="text" class="form-control"></div>
	  </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">单位邮编:</div>
	  		<div class="col-xs-6 form-group"><input id="orgPost" name="orgPost" type="text" class="form-control"></div>
	  </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">电子邮箱:</div>
	  		<div class="col-xs-6 form-group">
			<div class="input-group">
			<span class="input-group-addon">@</span>
			         <input type="text" class="form-control " placeholder="xxxx@163.com" id="orgEmail" name="orgEmail">
			         </div>
			</div>
	</div>
	<br>
	<div align="center"><button type="submit" class="btn btn-primary">确 定</button> </div>
</div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		container: 'tooltip',
		message: '这不是一个有效的值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			orgName: {
                validators: {
                    notEmpty: {
                        message: '单位名称不能为空！'
                    },
                    callback: {
                        message: '该单位已存在，请改名',
                        callback: function(value, validator) {
                        	if($("#orgName").val()!=""){
                        		return checkorg();	
                        	}else{
                        		return true;
                        	}
                        }
                    }
                }
            },
		orgEmail: {
               validators: {
                   emailAddress: {
                       message: '电子邮地址有错误！'
                   }
               }
           }
		}
	});
})
</script>
</body>
</html>