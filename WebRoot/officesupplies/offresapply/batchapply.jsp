<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量申领</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offresapply/js/batchapply.js"></script>
</head>
<body>
<form method="post" id="resapplyform" name="resapplyform" class="form-horizontal">
 </form>
 <div class="modal fade" id="applymyModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
             申领条件
            </h4>
         </div>
         <table style="width:100%;">
           <tr>
<td width="20%">部门审批人：</td>
<td>
<div class="col-xs-8">
<input type="hidden" name="approvalStaff" id="approvalStaff" />
<input type="text" name="userName" id="userName" readonly="readonly" class="form-control"/>
</div>
<div style="margin-top: 8px;">
<a href="javascript:void(0);"  onclick="userinit(['approvalStaff','userName'],'true');">添加人员</a></br></br>
</div>
</td>
</tr>
<tr>
<td>备注：</td>
<td>
<div class="col-xs-8">
<textarea rows="4" cols="40" id="applyRemary" name="applyRemary" class="form-control"></textarea>
</div>
</td>
</tr>
</table>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addapply();">
               确定
            </button>
         </div>
      </div>
</div>
</div>
<div id="modaldialog"></div>
</body>
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
		 'Numapply[]': {
	         validators: {
	        	 container: 'popover',
	        	 integer: {
	                 message: '请填写整数'
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
	     checksubmit();
		});
});
   </script>
</html>