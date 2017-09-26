<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>修改联系人信息</title>
<%String linkmanId=request.getParameter("linkmanId"); %>
<script>
var linkmanId="<%=linkmanId%>";
$(function (){
	$("#linkmanId").val(linkmanId);
	getlinkman();
});
function getlinkman(){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/getIdlinkmanAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{linkmanId:linkmanId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var fromdata=data;
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
			}
	}
	});
}
function updatelinkman(){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/updatelinkmanAct.act";
	var parm=$("#linkmanfrom").serialize();
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:parm,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				parent.layer.msg('修改成功',function(){});
				history.back();
			}
	}
	});
}
</script>
</head>
<body>
<form id="linkmanfrom" name="linkmanfrom" class="form-horizontal">
<div class="panel panel-info" style="width: 80%; margin-left: 10%">
   <div class="panel-heading">
      <h3 class="panel-title">
        修改联系人信息
      </h3>
   </div>
   <table class="table table-striped table-condensed">
   <tr>
   <td>
  	客户单位：
   </td>
   <td colspan="3">
   <input type="hidden" id="linkmanId" name="linkmanId"></input>
   <input type="hidden" id="customerId" name="customerId"/>
   <div class="col-xs-4 form-group">
   <input type="text" id="customerName" name="customerName" readonly="readonly" class="form-control"></input>
   </div>
   </td>
   </tr>
   <tr>
   <td>
  	 姓名：
   </td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="linkmanName" name="linkmanName" class="form-control"></input>
   </div>
   </td>
   <td>
  	 性别：
   </td>
   <td>
   <div class="col-xs-10 form-group">
   <select class="form-control" id="linkmanSex" name="linkmanSex">
   <option value="男">男</option>
   <option value="女">女</option>
   </select>
   </div>
   </td>
   </tr>
   <tr>
   <td>称呼：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="linkmanCall" name="linkmanCall" class="form-control"></input>
   </div>
   </td>
   <td>工作头衔：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="linkmanJob" name="linkmanJob" class="form-control"></input>
   </div>
   </td>
   </tr>
   <tr>
   <td>手机号码：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="cellPhone" name="cellPhone" class="form-control"></input>
   </div>
   </td>
   <td>家庭号码：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="homePhone" name="homePhone" class="form-control"></input>
   </div>
   </td>
   </tr>
   <tr>
   <td>QQ号：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="qqNumber" name="qqNumber" class="form-control"></input>
   </div>
   </td>
   <td>邮箱：</td>
   <td>
   <div class="col-xs-10 form-group">
   <input type="text" id="eMail" name="eMail" class="form-control"></input>
   </div>
   </td>
   </tr>
   <tr>
   <td>详细地址：</td>
   <td colspan="3">
   <div class="col-xs-4 form-group">
   <textarea rows="3" cols="40" name="addName" id="addName" class="form-control"></textarea>
   </div>
   </td>
   </tr>
   <tr>
   <td>备注：</td>
   <td colspan="3">
   <div class="col-xs-4 form-group">
   <textarea rows="2" name="remark" id="remark" class="form-control"></textarea>
   </div>
   </td>
   </tr>
   </table>
   </div>
   <div align="center">
 	<button type="submit" name="send" class="btn btn-primary" >保存</button>
	<button type="reset"  class="btn btn-default">重置</button>
	<button type="button" onclick="history.back();" class="btn btn-default">返回</button>
 		</div>
   </form>
   <script>
   $(document).ready(function() {
$("#linkmanfrom").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 linkmanName: {
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
	     updatelinkman();
		}); 
});
   </script>
</body>
</html>