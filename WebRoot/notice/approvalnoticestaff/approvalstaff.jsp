<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告审批权限设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<script type="text/javascript" src="<%=contextPath%>/notice/approvalnoticestaff/approvalstaff.js"></script>
</head>
<body>
<form id="noticepowerform" name="noticepowerform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">公告审批权限设置</a>
   <table class="table table-striped ">
<tr>
<td>公告类型：</td>
<td>
<input type="hidden" id="powerId" name="powerId">
<div class="col-xs-4 form-group">
<input type="text" name="noticeType" id="noticeType" readonly="readonly" class="form-control ">
</div>
</td>
</tr>
<tr>
<td>人员选择:</td>
<td>
<input type="hidden"  id="approvalStaff" name="approvalStaff" />
<div class="col-xs-8">
<textarea class="form-control" rows="5" cols="100"  name="userName" readonly="readonly" id="userName"></textarea></div>
<a href="javascript:void(0)"  onclick="userinit(['approvalStaff','userName'],'true');">添加</a>
</td>
</tr>
</table>
<div align="center"><button type="submit" class="btn btn-primary">保 存</button></div>
</br>
   </div>
   </form>
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:98%;margin-left: 1%;margin-top: 1%;">
<a style="cursor: auto;" class="list-group-item active">权限列表</a>
<table class="table table-striped " id="approvalpowerstaff">
<tr>
<td align="center" width="5%">序号</td>
<td align="center" width="15%">公告类型</td>
<td align="center" width="60%">审批人员</td>
<td align="center" width="20%">操作</td>
</tr>
</table>
   </div>
   <div id="modaldialog"></div>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#noticepowerform").bootstrapValidator({
    		 message: 'Pas valide',
    		 container: 'tooltip',
    		 feedbackIcons: {
    		     valid: 'glyphicon glyphicon-ok',
    		     invalid: 'glyphicon glyphicon-remove',
    		     validating: 'glyphicon glyphicon-refresh'
    		 },
    		 fields: {
    			 noticeType: {
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
    	     addpower();
    		}); 
    });
    </script>
</body>
</html>