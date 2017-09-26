<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建办公用品库</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/officelibrary/js/Newlibrary.js"></script>
</head>
<body>
   <form id="libraryform" name="libraryform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:70%;margin-left: 15%;margin-top: 2%;">
<a style="cursor: auto;" class="list-group-item active">新建办公用品库</a>
 <table class="table table-striped table-condensed">
          <tr>
          <td width="15%">办公用品库名称</td>
          <td>      
           <div class="col-xs-6 form-group">
			<input type="text" id="libraryName" name="libraryName" class="form-control "  placeholder="请输入名称......"></div>
        </td></tr>
     <tr>
     <td>所属部门</td>
     <td>
     <textarea rows="3" cols="40" name="belongDept" id="belongDept" style="display: none;"></textarea>
     <div class="col-xs-6 form-group">
<textarea rows="3" cols="40" name="deptName" id="deptName"  class="form-control" readonly="readonly"></textarea>
</div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="deptinit(['belongDept','deptName'],'false','deptNameval');">添加部门</a>
</div>
</td></tr>
<tr>
<td>库管理员</td>
<td>
<input type="text" id="libraryStaff" name="libraryStaff" class="form-control " style="display:none;"  />
<div class="col-xs-6 form-group">
			<input type="text" id="staffName" name="staffName" class="form-control " readonly="readonly" />
			</div>
			<div style="margin-top: 8px;">
         <a href="javascript:void(0);" onclick="userinit(['libraryStaff','staffName'],'true','staffNameval');">添加</a>
         </div>
</td>
</tr>
<tr>
<td>物品调度员</td>
<td>
<input type="text" id="dispatchStaff" name="dispatchStaff" class="form-control " style="display:none;" />
<div class="col-xs-6 form-group">
			<input type="text" id="dispatchName" name="dispatchName" class="form-control " readonly="readonly" /></div>
        <div style="margin-top: 8px;">
         <a href="javascript:void(0);" onclick="userinit(['dispatchStaff','dispatchName'],'true','dispatchNameval');">添加</a>
         </div>
</td>
</tr>
</table>
   </div>
   <div align="center">
  <button type="submit" disabled="disabled" name="send" class="btn btn-primary">保存</button>
<button type="button" name="save" onclick="history.back();" class="btn btn-default">返回</button>
 </div>
 </form>
<div id="modaldialog"></div>
<script type="text/javascript">
$(document).ready(function() {
 $("#libraryform").bootstrapValidator({
 message: 'Pas valide',
 container: 'tooltip',
 feedbackIcons: {
     valid: 'glyphicon glyphicon-ok',
     invalid: 'glyphicon glyphicon-remove',
     validating: 'glyphicon glyphicon-refresh'
 },
 fields: {
	 libraryName: {
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },deptName:{
    	 validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },dispatchName:{
    	 validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },staffName:{
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
     addlibrary();
	});
});
 </script>
</body>
</html>