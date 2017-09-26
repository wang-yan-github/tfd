<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>新建客户信息</title>
<%String customerId=request.getParameter("customerId"); %>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/district/jquery.fn.district.css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/district/jquery.fn.district.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script>
var customerId="<%=customerId%>";
$(function (){	
	$("#customerId").val(customerId);
	getSelect("customer","tradeType","");
	lookcustomer();
	$("#areaName").district();
});
function lookcustomer(){
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
			var fromdata=data;
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
			}
	}
	});
}
function updatecustomerinfo(){
	var url=contextPath+"/customermanage/act/CustomerinfoAct/updatecustomerAct.act";
	var parm=$("#customerinfofrom").serialize();
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
				parent.layer.msg('修改成功');
				history.back();
			}
	}
	});
}
function showother()
{
	 if($("#showother").css('display')=="none")
		 {
		 $("#showother").show();
		 $("#studybutton").text("返回简要信息")
		 }else
			 {
			 $("#showother").hide();
			 $("#studybutton").text("展开更多信息")
			 }
}
function keeperNameval(){
	$('#customerinfofrom').bootstrapValidator('revalidateField', 'keeperName');
}
</script>
</head>
<body>
<form id="customerinfofrom" name="customerinfofrom" class="form-horizontal">
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >修改客户</span>
</div>
<div style=" float: right;margin-top:6px;margin-right: 20px;">
	<button type="submit" name="send" class="btn btn-primary">保存</button>
	<button type="button" class="btn btn-default" onclick ="history.back();">返回</button>
</div>
</div>
<table class="table table-striped table-condensed">
<tr>
<td width="10%">客户名称：</td>
<td width="40%">
<div class="col-xs-12 form-group" >
<input type="text" id="customerName" name="customerName" class="form-control"  placeholder="请输入客户名称......">
</div>
</td>
<td></td>
<td></td>
</tr>
<tr>
<td width="10%">客户经理：</td>
<td>
<div class="col-xs-12 form-group">
<input id="keeper" name="keeper" type="hidden"></input>
<input id="customerId" name="customerId" type="hidden"></input>
<input id="keeperName" name="keeperName" type="text" class="form-control" readonly="readonly"></input>
</div>
</td>
<td>
<div>
<a href="javascript:void(0);" onclick="userinit(['keeper','keeperName'],'true','keeperNameval');">添加人员</a>
</div>
</td>
<td></td>
</tr>
<tr>
<td>参与人：</td>
<td>
<div class="col-xs-12 form-group">
<textarea rows="3" cols="40" name="joinStaff" id="joinStaff" style="display: none;"></textarea>
<textarea rows="4" cols="40" name="joinStaffName" id="joinStaffName" readonly="readonly" class="form-control"></textarea>
</div>
</td>
<td>
<div style="margin-top: 50px;">
<a href="javascript:void(0);" onclick="userinit(['joinStaff','joinStaffName'],'false');">添加人员</a>
</div>
</td>
<td></td>
</tr>
<tr>
<td width="10%">
客户类型：
</td>
<td width="40%">
<div class="col-xs-12 form-group" >
<select id="customerType" name="customerType" class="form-control">
<option value='客户'>客户</option>
<option value='合作伙伴'>合作伙伴</option>
<option value='供应商'>供应商</option>
<option value='代理商'>代理商</option>
<option value='竞争对手'>竞争对手</option>
</select>
</div>
</td>
<td width="10%">客户状态</td>
<td width="40%">
<div class="col-xs-12 form-group">
<select id="customerStatus" name="customerStatus" class="form-control">
<option value='基础'>基础</option>
<option value='重点'>重点</option>
<option value='潜在'>潜在</option>
<option value='销售机会'>销售机会</option>
<option value='成功'>成功</option>
<option value='失败'>失败</option>
<option value='冻结'>冻结</option>
</select>
</div>
</td>
</tr>
<tr>
<td width="10%">电话：</td>
<td>
<div class="col-xs-12 form-group">
<input type="text" id="telNumber" name="telNumber" class="form-control" >
</div>
</td>
<td>传真：</td>
<td>
<div class="col-xs-12 form-group">
<input type="text" id="faxNumber" name="faxNumber" class="form-control" >
</div>
</td>
</tr>
<tr>
<td>网址：</td>
<td>
<div class="col-xs-12 form-group">
<input type="text" id="urlName" name="urlName" class="form-control" >
</div>
</td>
<td>邮箱：</td>
<td>
<div class="col-xs-12 form-group">
<input type="text" id="eMail" name="eMail" class="form-control" >
</div>
</td>
</tr>
<tr>
<td colspan="4">
<a onclick="showother()" style="cursor: pointer;" id="studybutton">展开更多信息</a>
</td>
</tr>
<tbody style="display:none;" id="showother">
<tr>
<td>
所属行业：
</td>
<td>
<div class="col-xs-12 form-group">
<div id="tradeType"></div>
</div>
</td>
<td>
企业性质：
</td>
<td>
<div class="col-xs-12 form-group" >
<select id="firmNature" name="firmNature" class="form-control">
<option value="">请选择</option>
<option value="国企">国企</option>
<option value="民营">民营</option>
<option value="合资">合资</option>
</select>
</div>
</td>
</tr>
<tr>
<td>
客户来源：
</td>
<td>
<div class="col-xs-12 form-group" >
<select id="customerOrigin" name="customerOrigin" class="form-control">
<option value="">请选择</option>
<option value="新闻">新闻</option>
<option value="百度">百度</option>
<option value="报纸">报纸</option>
<option value="搜狐">搜狐</option>
</select>
</div>
</td>
<td> 地区：</td>
<td >
<div class="col-xs-12 form-group">
<input type="text" id="areaName" name="areaName" class="form-control" >
</div>
</td>
</tr>
<tr>
<td>详细地址：</td>
<td colspan="3">
<div class="col-xs-12 form-group" >
<textarea rows="3" cols="40" name="addName" id="addName" class="form-control"></textarea>
</div>
</td>
</tr>

<tr>
<td>企业简述：</td>
<td colspan="3">
<div class="col-xs-12 form-group" >
<textarea rows="3" cols="40" name="firmSummary" id="firmSummary" class="form-control"></textarea>
</div>
</td>
</tr>
<tr>
<td>备注：</td>
<td colspan="3">
<div class="col-xs-12 form-group" >
<textarea rows="3" cols="40" name="remark" id="remark" class="form-control"></textarea>
</div>
</td>
</tr>
</tbody>
</table>
 </form>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
$("#customerinfofrom").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 customerName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },
	     keeperName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },customerType:{
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
	     updatecustomerinfo();
		}); 
});
</script>
</html>