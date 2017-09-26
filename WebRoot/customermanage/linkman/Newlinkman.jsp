<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>新建联系人</title>
<%String customerId=request.getParameter("customerId"); %>
<%String status=request.getParameter("status"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script>
var customerId="<%=customerId%>";
var cusstatus="<%=status%>";
$(function (){
	$("#customerId").val(customerId);
	getcustomerName();
});
function getcustomerName(){
	var url=contextPath+"/customermanage/act/CustomerinfoAct/getIdcustomerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{customerId:customerId},
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#customerName").text(data.customerName);
	}
	});
}
function addlinkman(){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/addlinkmanAct.act";
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
				parent.layer.msg('保存成功');
				if(cusstatus==1){
					var url=contextPath+"/customermanage/customerinfo/Newcustomer.jsp";
					window.location=url;
				}else{
					if(cusstatus==2){
						var url=contextPath+"/customermanage/customerinfo/Newcustomer.jsp?status="+1;
						window.location=url;
					}else{
				history.back();
					}
				}
			}
	}
	});
}
</script>
</head>
<body>
<form id="linkmanfrom" name="linkmanfrom" class="form-horizontal">
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >新建联系人</span>
</div>
</div>
   <table class="table table-striped table-condensed">
   <tr>
   <td>
  	客户单位：
   </td>
   <td colspan="3">
   <input type="hidden" id="customerId" name="customerId"/>
   <div class="col-xs-4 form-group" id="customerName">
   
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
   <div class="col-xs-12 form-group">
   <textarea rows="2" cols="40" name="addName" id="addName" class="form-control"></textarea>
   </div>
   </td>
   </tr>
   <tr>
   <td>备注：</td>
   <td colspan="3">
   <div class="col-xs-12 form-group">
   <textarea rows="2" cols="40" name="remark" id="remark" class="form-control"></textarea>
   </div>
   </td>
   </tr>
   </table>
   </div>
   <div align="center">
 	<button type="submit" name="send" class="btn btn-primary" >保存</button>
	<button type="button"  class="btn btn-default" onclick="history.back();">返回</button>
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
	     addlinkman();
		}); 
});
   </script>
</body>
</html>