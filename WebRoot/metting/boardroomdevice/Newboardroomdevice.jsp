<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建会议室设备</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/boardroomdevice/js/Newboardroomdevice.js"></script>
</head>
<body>
 <form  id="boardroomdevicefrom" name="boardroomdevicefrom"  method="post" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">新建会议室设备</a>
<table class="table table-striped table-condensed">
<tr>
<td width="15%">设备编号:</td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="deviceId" name="deviceId" class="form-control "  ></div>
</td>
</tr>
<tr>
<td>设备名称:</td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="deviceName" name="deviceName" class="form-control "  ></div>
</td>
</tr>
<td>设备型号:</td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="deviceType" name="deviceType" class="form-control "  ></div>
</td>
</tr>
<tr>
<td>所属会议室：</td>
<td>
<div class="col-xs-4 form-group">
<select id="boardroomId" name="boardroomId" class="form-control ">
</select>
</div>
</td>
</tr>
<tr>
<td>设备状态：</td>
<td>
<div class="col-xs-4 ">
<select id="deviceStatus" name="deviceStatus" class="form-control ">
<option value="1" selected="selected">可用</option>
<option value="0" >不可用</option>
</select>
</div>
</td>
</tr>
<tr>
<td>同类设备:</td>
<td>
<div class="col-xs-4">
<select id="deviceSimilar" name="deviceSimilar" class="form-control ">
<option value="1" selected="selected">没有</option>
<option value="0" >有</option>
</select>
</div>
</td>
</tr>
<tr>
<td colspan="2">设备描述：</td>
</tr>
<tr>
<td colspan="2"> 
<input type="hidden" id="deviceDescription" name="deviceDescription" >
<textarea id="editor" name="editor" style="width:98%;height:200px;"></textarea>
<script type="text/javascript">CKEDITOR.replace('editor')</script>
</td>
</tr>
</table>
   </div>
   <div align="center">
  <button type="submit" disabled="disabled" name="send"  class="btn btn-primary" >保存</button>
  <button type="button" onclick="history.back();"  name="his"  class="btn btn-default" >返回</button>
 </div>
 </form>
</body>
<script type="text/javascript">	
$("#boardroomdevicefrom").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 deviceId: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },deviceName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },deviceType: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },boardroomId:{
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
	     Newboardroomdevice();
		});
</script>
</html>