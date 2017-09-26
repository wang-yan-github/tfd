<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻审批权限设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/news/power/newspower.js"></script>
</head>
<body>
<form id="newspowerform" name="newspowerform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">新闻审批权限设置</a>
   <table class="table table-striped ">
<tr>
<td>类型：</td>
<td>
<input type="hidden" id="powerStatus" name="powerStatus" />
<div class="col-xs-4 form-group">
<input type="text" name="powerStatusval" id="powerStatusval" readonly="readonly" class="form-control ">
</div>
</td>
</tr>
<tr>
<td>人员选择:</td>
<td>
<input type="hidden"  id="accountId" name="accountId" />
<div class="col-xs-4 form-group">
<input class="form-control"  name="userName" id="userName"  readonly="readonly" /></div>
<div style="margin-top: 8px;">
<a href="javascript:void(0);"  onclick="userinit(['accountId','userName'],'true','userNameval');">添加</a>
</div>
</td>
</tr>
</table>
<div align="center"><button type="submit" class="btn btn-primary">保 存</button></div>
</br>
   </div>
   </form>
   <div class="list-group-item"  style="padding: 0px;cursor: auto;float:left; width:48%;margin-left: 1%;margin-top: 1%;">
  
   <div class="panel-heading" style="position:relative;">
      <span class="panel-title">无需审批的人员列表</span>
      <button type="button" type="button" class="btn btn-primary btn-sm" style="position:absolute;right:10px;top:5px;" onclick="addpower('1');">设置人员</button>
   </div>
<table class="table table-striped" id="dispensestaff">
<tr>
<td align="center" width="10%">序号</td>
<td align="center" width="60%">人员名称</td>
<td align="center" width="30%">操作</td>
</tr>
</table>
   </div>
   <div class="list-group-item"  style="padding: 0px;cursor: auto;float:left; width:48%;margin-left: 2%;margin-top: 1%;">
   <div class="panel-heading" style="position:relative;">
      <span class="panel-title">
        审批人员列表
      </span>
      <button type="button" class="btn btn-primary btn-sm" style="position:absolute;right:10px;top:5px;" onclick="addpower('2');">设置人员</button>
   </div>
<table class="table table-striped" id="approvalstaff">
<tr>
<td align="center" width="10%">序号</td>
<td align="center" width="60%">人员名称</td>
<td align="center" width="30%">操作</td>
</tr>
</table>
   </div>
    <div id="modaldialog"></div>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#newspowerform").bootstrapValidator({
    		 message: 'Pas valide',
    		 container: 'tooltip',
    		 feedbackIcons: {
    		     valid: 'glyphicon glyphicon-ok',
    		     invalid: 'glyphicon glyphicon-remove',
    		     validating: 'glyphicon glyphicon-refresh'
    		 },
    		 fields: {
    			 powerStatusval: {
		         validators: {
		        	 container: 'popover',
		             notEmpty: {
		                 message: '不能为空'
		             }
		         }
		     },
    			 userName: {
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
    	     insertnewstaff();
    		}); 
    });
    </script>
</body>
</html>