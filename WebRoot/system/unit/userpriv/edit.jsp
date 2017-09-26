<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String userPrivId=request.getParameter("userPrivId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统权限设置</title>
<script type="text/javascript">
var userPrivId='<%=userPrivId%>';
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/getUserPrivByUserPrivIdAct.act";
	var paramData={userPrivId:userPrivId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			
			for(var name in data){
				$("#"+name).val(data[name]);
			}
		}
	});
});
function save()
{
	var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/updateUserPrivAct.act";
	var paramData={userPrivId:userPrivId,userPrivName:$("#userPrivName").val(),userPrivLeave:$("#userPrivLeave").val()};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=="1")
				{
				alert("更新成功！");
				history.back();
				}
		}
	});
	}
</script>
</head>
<body>
<div  style="width: 100%;margin-top: 5%;" align="center">
<form id="updatepriv" name="updatepriv" class="form-horizontal">
 <div class="list-group"  style="width:60%;">
  		<a class="list-group-item active"> 修改权限组</a>
	  <div  class="list-group-item" style="height: 55px;">
	  			<div style="float: left;width: 120px;padding-top:8px;">权限组编号:</div>
	  			<div class="col-xs-6 form-group"><input type="text" id="userPrivLeave" name="userPrivLeave" class="form-control"/></div>
	 </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">权限组名称：</div>
	 	 	<div class="col-xs-6 form-group"><input type="text" id="userPrivName" name="userPrivName" class="form-control"/></div>
	  </div>
</div>
<div align="center"><button type="submit" class="btn btn-primary">保存</button>
<button type="button" onclick="javascript :history.back();" class="btn btn-default" >返回</button></div>
   </form>
   </div>
   <script type="text/javascript">
$(document).ready(function() {
$("#updatepriv").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 userPrivLeave: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },
	     userPrivName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
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
	     save();
		}); 
});
</script>
</body>
</html>