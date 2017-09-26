<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>添加会议纪要</title>
<%String requestId=request.getParameter("requestId"); %>
<%String meetingName=request.getParameter("meetingName"); %>
<%String attendStaff=request.getParameter("attendStaff"); %>
<%String createUser=request.getParameter("createUser"); %>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/summary/js/addsummary.js"></script>
<script type="text/javascript">
var requestId="<%=requestId%>";
var meetingName="<%=meetingName%>";
var attendStaff="<%=attendStaff%>";
var createUser="<%=createUser%>";
</script>
</head>
<body>
<form id="summaryform" name="summaryform" class="form-horizontal">
  <div class="list-group-item"  style="padding: 0px;cursor: auto;width:98%;margin-left: 1%;">
<a style="cursor: auto;" class="list-group-item active">添加会议纪要</a>
<table class="table table-striped table-condensed">
<tr>
<td width="15%">会议名称:</td>
<td>
<input type="hidden" id="requestId" name="requestId" class="form-control " >
<div class="col-xs-4" id="meetingname">

</div></td>
</tr>
<tr>
<td>指定读者:</td>
<td><div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="lookStaff" id="lookStaff" style="display: none;" ></textarea>
<textarea rows="3" cols="40" name="StaffName" id="StaffName" readonly="readonly" class="form-control"></textarea></div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="userinit(['lookStaff','StaffName'],'false','StaffNameval');">添加人员</a>
</div>
</td>
</tr>
<tr>
<td>实际参会人员:</td>
<td>
<div class="col-xs-4 form-group">
<textarea rows="3" cols="40" name="realityStaff" id="realityStaff" style="display: none;" ></textarea>
<textarea rows="3" cols="40" name="userName" id="userName" readonly="readonly" class="form-control"></textarea></div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="userinit(['realityStaff','userName'],'false','userNameval');">添加人员</a>
</div>
</td>
</tr>
<tr>
<td>附件:</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
<td>附件选择:</td>
<td>	
<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('meeting','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	
	
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

</div>

</td>
</tr>
<tr>
<td colspan="2"> 
纪要内容：
</td>
</tr>
<tr>
<td colspan="2"> 
<textarea id="editor" name="editor" style="width:100%;height:200px;"></textarea>
<script type="text/javascript">CKEDITOR.replace('editor')</script>
</td>
</tr>
</table>
   </div>
   <div align="center">
<button type="submit" name="save" disabled="disabled" class="btn btn-primary">保存</button>
<button type="button" name="save" onclick="history.back();" class="btn btn-default">返回</button>
 </div>
 </form>
<div id="modaldialog"></div>
<script type="text/javascript">
$(document).ready(function() {
$("#summaryform").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 StaffName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },userName: {
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
	     insetsummary();
		}); 
});
</script>

</body>
</html>