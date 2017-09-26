<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建会议室</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/boardroom/js/Newboardroom.js"></script>
</head>
<style>
	.weekday label{margin-left:5px;}
</style>
<body>
<form method="post" id="boardroomform" name="boardroomform" class="form-horizontal">
    <div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">新建会议室</a>
<table class="table table-striped table-condensed">
<tr>
<td width="15%">会议室名称:</td>
<td><div class="col-xs-4 form-group">
<input type="text" id="boardroomName" name="boardroomName" class="form-control "  placeholder="请输入会议室名称......"></div></td>
</tr>
<tr>
<td>会议室描述:</td>
<td><div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="boardroomDepict" id="boardroomDepict" class="form-control"></textarea>
</div></td>
</tr>
<tr>
<td>会议室管理员:</td>
<td>
<input type="hidden" name="boardroomStaff" id="boardroomStaff" ></input>
<div class="col-xs-4 form-group">
<input type="text" name="boardroomuserName" readonly="readonly" id="boardroomuserName" class="form-control"></input></div>
<div style="margin-top: 5px;">
<a href="javascript:void(0);" onclick="userinit(['boardroomStaff','boardroomuserName'],'true','boardroomuserNameval');">添加人员</a>
</div>
</td>
</tr>
<tr>
<td>申请权限(部门):</td>
<td>
<div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="deptPriv" id="deptPriv" style="display: none;"></textarea>
<textarea rows="3" cols="40" name="deptName" id="deptName" readonly="readonly" class="form-control"></textarea>
</div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="deptinit(['deptPriv','deptName'],'false','deptNameval');">添加部门</a>
</div>
</td>
</tr>
<tr>
<td>申请权限(人员):</td>
<td>
<div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="userPriv" id="userPriv" style="display: none;" ></textarea>
<textarea rows="3" cols="40" name="userName" readonly="readonly" id="userName" class="form-control"></textarea>
</div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="userinit(['userPriv','userName'],'false','deptNameval');">添加人员</a>
</div>
</td>
</tr>
<tr>
<td>可容纳人数:</td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="boardroomNum" name="boardroomNum" class="form-control" >
</div>
</td>
</tr>
<tr>
 <td>可申请时间：</td>
 <td>
                        <div class="col-xs-6 form-group">
                        <div style="margin-top: 8px;" class="weekday" >
                                <label>
                                    <input type="checkbox" name="time[]"  value="日" id="time0" />星期日
                                </label>
                                <label>
                                   <input type="checkbox" name="time[]"  value="一" id="time1" />星期一
                                </label>
                                <label>
                                    <input type="checkbox" name="time[]"  value="二" id="time2" />星期二
                                </label>
                                <label>
                                    <input type="checkbox" name="time[]"  value="三" id="time3" />星期三
                                </label>
                                <label>
                                    <input type="checkbox" name="time[]"  value="四" id="time4" />星期四
                                </label>
                                <label>
                                   <input type="checkbox" name="time[]"  value="五" id="time5" />星期五
                                </label>
                                <label>
                                   <input type="checkbox" name="time[]"  value="六" id="time6" />星期六
                                </label>
                                </div>
                    </div>
 </td>
</tr>
<tr>
<td>设备情况:</td>
<td>
<div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="equipment" id="equipment"  class="form-control"></textarea>
</div>
</td>
</tr>
<tr>
<td>地址:</td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="boardroomAddress" name="boardroomAddress" class="form-control" >
</div>
</td>
</tr>
<tr>
	<td valign="top">会议管理制度：</td>
	<td>
	<div  id="editor" name="editor" style="width: 100%; height: 180px;" ></div>
	<script type="text/javascript">CKEDITOR.replace('editor')</script>
	</td>
</tr>
</table>
   </div>
   <div align="center">
<button type="submit"  name="send" disabled="disabled" class="btn btn-primary">保存</button>
<button type="button" onclick="history.back();" class="btn btn-default">返回</button>
 </div>
 </form>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
$("#boardroomform").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 boardroomName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },boardroomDepict: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },equipment: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '请选择时间'
	             }
	         }
	     },boardroomNum: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             },integer: {
	                 message: '请填写整数'
	             } 
	         }
	     },boardroomAddress: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },boardroomuserName:{
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },deptName:{
	         validators: {
	        	 container: 'popover',
	        	 callback: {
	                    message: '不能为空',
	                    callback: function(value, validator) {
	                    	if($("#deptName").val()!=""||$("#userName").val()!="" ){
	                    		return true;
	                    	}else{
	                    		return false;
	                    	}
	                    }
	                }
	         }
	     },userName:{
	         validators: {
	        	 container: 'popover',
	        	 callback: {
	                    message: '不能为空',
	                    callback: function(value, validator) {
	                    	if($("#deptName").val()!=""||$("#userName").val()!="" ){
	                    		return true;
	                    	}else{
	                    		return false;
	                    	}
	                    }
	                }
	         }
	     },'time[]':{
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
	     addboardroom();
		});
});
</script>
</html>