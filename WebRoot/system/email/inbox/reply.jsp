<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String emailId = request.getParameter("emailId");
	String i = request.getParameter("i");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部邮件</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/email/js/email.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<style type="text/css">
html,body{
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
}
.reply{font-size:12px;}
.hiddenTr{display:none;}
</style>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         邮件编辑(每3分钟自动保存一次)
      </h5>
   </a>
   <div class="panel-body">
   <table class="table table-striped">
<tr>
<td width="10%">收件人:</td>
<td>
	<div class="col-xs-4 form-group">
		<textarea rows="3" cols="40" name="accountId" id="accountId" style="display: none;" ></textarea>
		<textarea rows="3" cols="40" readonly="readonly" name="userName" style="resize:none;" id="userName" class="form-control"></textarea></div>
	<input type="button" style="margin-top:2px;" class="btn btn-default" value="添加人员" onclick="selectUser(['accountId','userName'],'false','checkUserName');"/>
	<a href="javascript:void(0);" style="position:relative;left:-70px;top:38px;"  id="showOther" onclick="showTr('Other','抄送')" >添加抄送</a>
	<a href="javascript:void(0);" style="position:relative;left:-40px;top:38px;"  id="showWeb" onclick="showTr('Web','web收件人')" >添加web收件人</a>
</td>
</tr>
<tr id="OtherTr" class="hiddenTr" >
<td width="10%">抄送:</td>
<td><div class="col-xs-4"><input type="text"  name="sendOther" id="sendOther" style="display: none;" />
		<input type="text" readonly="readonly" name="sendOtherName" id="sendOtherName" class="form-control" /></div>
	<input type="button" style="margin-top:2px;" class="btn btn-default" value="选择人员" onclick="selectUser(['sendOther','sendOtherName']);"/></td>
</tr>
<tr id="WebTr" class="hiddenTr" >
<td width="10%">Web收件人:</td>
<td><div class="col-xs-4">
		<input type="text" name="WebMail" id="WebMail" class="form-control" /></div>
</tr>
<tr>
<td width="10%">主题:</td>
<td><div class="col-xs-4"><input class="form-control " type="text" id="subject"></div>
	<select class="form-control " id="important" style="width:120px;float:left;" >
		<option value="1" >一般邮件</option>
		<option style="color:#FF6600;" value="2" >重要邮件</option>
		<option style="color:red;" value="3" >非常重要</option>
	</select>
</td>
</tr>
<tr>
	<td colspan="2"  width="100%" >
	<textarea id="editor" name="editor"  style="width:100%;height:200px;"></textarea>
<script type="text/javascript">CKEDITOR.replace('editor', { fullPage: true, allowedContent: true })</script>
	</td>
</tr>
<tr>
<td width="10%">附件:</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
<td width="10%" >附件选择:</td>
<td>	
<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('email','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	
	
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>
	
	<a class="add_swfupload" style="width:190px;" onclick="javascript:goOpen();"  href="javascript:void(0)">从文件柜和网络硬盘中选取附件</a>
	<input type="hidden" id="btn-open" data-toggle="modal" data-target="#attachModel" />

</div>
</td>
</tr>
<tr>
	<td width="10%" >提醒:</td>
	<td>
<!-- 	<input type="checkbox" id="isSms" />是否短信提醒 -->
	<div id="smsdiv" name="smsdiv"></div>
	</td>
</tr>
</table>

   </div>
   </div>
    <div align="right">
  <input type="submit" id="btn_send"  class="btn btn-primary" value="发送" />
<button type="button"  id="btn_save"  class="btn btn-info">保存到草稿箱</button>
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
   </form>
  <div id="modaldialog"></div>
</body>
<script type="text/javascript">
$('#form1').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		userName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '请填写收件人!'
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
     sendEmail(0);
}); 

var id = "<%=emailId%>";
var i = "<%=i%>"
var contextPath = "<%=contextPath%>";
var editor = CKEDITOR.instances.editor;
$(function(){
	getMessagePriv("email");
	filesUpLoad("email");
	fileUploadByAttach("email");
	getEmailById(id,i,'1');
	setTimeout("saveEmail(0)",180000);
	/* $('#btn_send').click(function(){
		sendEmail(0);
	}) */
	$('#btn_save').click(function(){
		saveEmail(0);	
	})
	$('#btn_back').click(function(){
		//window.location.href="readEmail.jsp?emailId="+id+"&boxId=1";
		history.go(-1);
		return false;
	})
})
</script>
</html>