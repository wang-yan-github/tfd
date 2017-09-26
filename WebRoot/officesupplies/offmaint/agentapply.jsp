<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代登记</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offmaint/js/agentapply.js"></script>
</head>
<body>
<form  id="resapplyform" name="resapplyform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:60%;margin-left: 20%;margin-top: 1%;">
<a style="cursor: auto;" class="list-group-item active">代登记</a>
<table class="table table-striped table-condensed">
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
<select id="resId" name="resId" class="form-control "  onchange="gettype();" >
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>申领类型:</td>
<td>
<div class="col-xs-8">
<input type="text" id="resType" name="resType" class="form-control" readonly="readonly"/>
</div>
</td>
</tr>
<tr>
<td>申请数量:</td>
<td>
<input type="hidden" id="maxnum" ></input>
<div style="float: left;margin-left: 15px;">
<input type="button" value="-" id="btnminus"  disabled="disabled" style="font-weight: 20px;" class="btn btn-default" onclick="btnminusfun();" ></input>
</div>
<div class="col-xs-4 form-group" style="float: left;margin-left: 0px;">
<input id="applyNum" name="applyNum" class="form-control" readonly="readonly" onblur="blurjudge();" />
</div>
<div style="float: left;" >
<input type="button" value="+" id="btnplus" disabled="disabled" onclick="btnplusfun();" class="btn btn-default"></input>
<span id="cue" style="color:red;"></span>
</div>
</td>
</tr>
<tr>
<td>申请人:</td>
<td>
<input type="hidden" name="dispatchStaff" id="dispatchStaff"></input>
<input type="hidden" name="createUser" id="createUser" />
<div class="col-xs-8 form-group">
<input type="text" name="userName" id="userName" readonly="readonly" class="form-control"/>
</div>
<div style="margin-top: 8px;">
<a href="javascript:void(0);"  onclick="userinit(['createUser','userName'],'true','userNameval');">添加人员</a></br></br>
</div>
</td>
</tr>
<tr>
<td>备注:</td>
<td>
<div class="col-xs-8 form-group">
<textarea rows="4" cols="40" id="applyRemary" name="applyRemary" class="form-control"></textarea>
</div>
</td>
</tr>
</table>
   </div>
   <div align="center">
  <button type="submit" name="send" value="" class="btn btn-primary">确认</button>
<button type="reset" name="save" onclick="" class="btn btn-default">重置</button>
 </div>
 </form>
 <div id="modaldialog"></div>
 <script type="text/javascript">
 $(document).ready(function() {
 $("#resapplyform").bootstrapValidator({
 message: 'Pas valide',
 container: 'tooltip',
 feedbackIcons: {
     valid: 'glyphicon glyphicon-ok',
     invalid: 'glyphicon glyphicon-remove',
     validating: 'glyphicon glyphicon-refresh'
 },
 fields: {
	 applyNum: {
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             },integer: {
                 message: '请填写数字'
             } 
         }
     },applyRemary: {
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
     },userName:{
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
     addresapply();
	}); 
 });
 </script>
</body>
</html>