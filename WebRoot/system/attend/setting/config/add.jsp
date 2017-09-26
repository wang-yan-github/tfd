<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
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
</style>
<script type="text/javascript">
function addAttendConfig()
{
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/addAttendConfig.act";
	$.ajax({
		url:requestUrl,
		data:{
			configName:$("#configName").val(),
			configType:$("#configType").val(),
			time1:$("#time1").val(),
			time2:$("#time2").val(),
			time3:$("#time3").val(),
			time4:$("#time4").val(),
			time5:$("#time5").val(),
			time6:$("#time6").val(),
			timeType1:$("#timeType1").val(),
			timeType2:$("#timeType2").val(),
			timeType3:$("#timeType3").val(),
			timeType4:$("#timeType4").val(),
			timeType5:$("#timeType5").val(),
			timeType6:$("#timeType6").val()
		},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=="1"){
				alert("保存成功！");
			}
		}
	});
}
	$(function(){
		$('#btn_back').click(function(){
			history.go(-1);
			return false;
		});
	})
	
	function setConfigType(){
		var type = $("#configType").val();
		if(type == '1'){
			$(".type2,.type3").hide();
		}
		if(type == '2'){
			$(".type2").show();
			$(".type3").hide();
		}
		if(type == '3'){
			$(".type2,.type3").show();
		}
	}
</script>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal"  >
<div align="center" style="margin-top:30px;" >
<div class="list-group" style="margin-bottom: 0px;width:40%;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         添加排版类型
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped ">
<tr>
<td>排班类型说明:</td>
<td><div class="col-xs-12 form-group"><input type="text" id="configName" name="configName"  class="form-control"/></div></td>
</tr>
<tr>
<td>排版类型:</td>
<td><div class="col-xs-12 form-group"><select  id="configType" name="configType" onchange="javascript:setConfigType();"  class="form-control">
	<option value="1" >一班制</option>
	<option value="2" >两班制</option>
	<option value="3" >三班制</option>
</select></div></td>
</tr>
<tr>
<td>第1次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"  onchange="checkTime('time1')" id="time1" name="time1" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType1" class="form-control" ><option value="1" >上班</option></select></div></td>
</tr>
<tr>
<td>第2次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text"  readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" onchange="checkTime('time2')" id="time2" name="time2" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType2" class="form-control" ><option value="2" >下班</option></select></div></td>
</tr>
<tr class="type2" >
<td>第3次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" onchange="checkTime('time3')" id="time3" name="time3" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType3" class="form-control" ><option value="1" >上班</option></select></div></td>
</tr>
<tr class="type2" >
<td>第4次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" onchange="checkTime('time4')" id="time4" name="time4" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType4" class="form-control" ><option value="2" >下班</option></select></div></td>
</tr>
<tr class="type3" >
<td  >第5次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text" readonly="readonly"  onclick="WdatePicker({dateFmt:'HH:mm:ss'})" onchange="checkTime('time5')" id="time5" name="time5" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType5" class="form-control" ><option value="1" >上班</option></select></div></td>
</tr>
<tr class="type3" >
<td  >第6次登记：</td>
<td><div class="col-xs-8 form-group">
<input type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" onchange="checkTime('time6')" id="time6" name="time6" class="form-control"/>
</div>
<div class="col-xs-4 form-group" style="float:left;" >
<select id="timeType6" class="form-control" ><option value="2" >下班</option></select></div></td>
</tr>
</table>
</div>
</div>
</div>
<div align="center">
<input type="submit" class="btn btn-primary"   value="确定" >
<button type="button" class="btn btn-default"  id="btn_back"  >取消</button>
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			configName: {
                validators: {
                	container: 'popover',
					notEmpty: {
            			message: '不能为空!'
                	}
                }
            },
            time1: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                    	message: '不能为空！'
                    }
                }
            },
            time2: {
                validators: {
                	container: 'popover',
                	callback: {
                    	message: '第二次登记时间不能小于第一次排班时间！',
                    	callback: function(value, validator) {
                    		if(value<$("#time1").val()){
                    			return false;
                    		}else{
                    			return true;
                    		}
                    	}
                    }
                }
            },
            time3: {
                validators: {
                	container: 'popover',
                	callback: {
                    	message: '第三次登记时间不能小于第二次排班时间！',
                    	callback: function(value, validator) {
                    		if($("#configType").val()=='2'||$("#configType").val()=='3'){
	                    		if(value<$("#time2").val()){
	                    			return false;
	                    		}else{
	                    			return true;
	                    		}
                    		}else{
                    			return true;
                    		}
                    	}
                    }
                }
            },
            time4: {
                validators: {
                	container: 'popover',
                	callback: {
                    	message: '第四次登记时间不能小于第三次排班时间！',
                    	callback: function(value, validator) {
                    		if($("#configType").val()=='2'||$("#configType").val()=='3'){
	                    		if(value<$("#time3").val()){
	                    			return false;
	                    		}else{
	                    			return true;
	                    		}
                    		}else{
                    			return true;
                    		}
                    	}
                    }
                }
            },
            time5: {
                validators: {
                	container: 'popover',
                	callback: {
                    	message: '第五次登记时间不能小于第四次排班时间！',
                    	callback: function(value, validator) {
                    		if($("#configType").val()=='3'){
	                    		if(value<$("#time4").val()){
	                    			return false;
	                    		}else{
	                    			return true;
	                    		}
                    		}else{
                    			return true;
                    		}
                    	}
                    }
                }
            },
            time6: {
                validators: {
                	container: 'popover',
                	callback: {
                    	message: '第六次登记时间不能小于第五次排班时间！',
                    	callback: function(value, validator) {
                    		if($("#configType").val()=='3'){
	                    		if(value<$("#time5").val()){
	                    			return false;
	                    		}else{
	                    			return true;
	                    		}
                    		}else{
                    			return true;
                    		}
                    	}
                    }
                }
            }
		}
	}).on('success.form.bv',function(e){
		 e.preventDefault();
			
	     // Get the form instance
	     var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     var bv = $form.data('bootstrapValidator');
	     addAttendConfig();
	}); 
})

function checkTime(time){
	$('#form1').bootstrapValidator('revalidateField', time);
}
</script>
</body>
</html>