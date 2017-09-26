<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offmaint/js/maint.js"></script>
<title>库存维护</title>
</head>
<body>
   <form id="maintform" name="maintform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:60%;margin-left: 20%;margin-top: 1%;">
<a style="cursor: auto;" class="list-group-item active">修改维护记录</a>
<table class="table table-striped table-condensed">
<tr>
<td>登记类型:</td>
<td>
<div class="col-xs-8 form-group">
<select id="maintType" name="maintType" class="form-control" >
<option selected="selected" value="">请选择</option>
<option value="采购入库">采购入库</option>
<option value="维护">维护</option>
<option value="报废">报废</option>
</select>
</div>
</td>
</tr>
<tr>
<td width="25%">办公用品库:</td>
<td>
<div class="col-xs-8 form-group">
<select id="libraryId" name="libraryId" class="form-control " onchange="getlibclassify();">
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>办公用品类别:</td>
<td>
<div class="col-xs-8 form-group">
<select id="classifyId" name="classifyId" class="form-control " onchange="getresName();" >
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>办公用品:</td>
<td>
<div class="col-xs-8 form-group">
<select id="resId" name="resId" class="form-control">
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>单价:</td>
<td>
<div class="col-xs-8 form-group">
<input id="resPrice" name="resPrice" class="form-control" />
</div>
</td>
</tr>
<tr>
<td>数量:</td>
<td>
<div class="col-xs-8 form-group">
<input id="maintNum" name="maintNum" class="form-control" />
</div>
</td>
</tr>
<tr>
<td>备注:</td>
<td>
<div class="col-xs-8 form-group">
<textarea rows="4" cols="40" id="maintRemary" name="maintRemary" class="form-control"></textarea>
</div>
</td>
</tr>
</table>
   </div>
   <div align="center">
  <button type="submit" disabled="disabled" name="send" value="" class="btn btn-primary" >确认</button>
<button type="reset" name="save" id="resetbutton" onclick="" class="btn btn-default">重置</button>
 </div>
 </form>
</body>
<script type="text/javascript">
$("#maintform").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 submitButtons: 'button[type="submit"]',
	 fields: {
		 resPrice: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             },numeric: {
	                 message: '请填写数字'
	             } 
	         }
	     },maintNum: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             },integer: {
	                 message: '请填写整数'
	             } 
	         }
	     },maintRemary: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },maintType:{
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },libraryId:{
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },classifyId:{
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },resId:{
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
         addmaint();
		});
   </script>
</html>