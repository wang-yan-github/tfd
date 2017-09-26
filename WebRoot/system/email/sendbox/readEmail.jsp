<%@page import="tfd.system.unit.account.data.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<% 
	String emailId = request.getParameter("emailId"); 
	String boxId = request.getParameter("boxId");
	Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
	String userId = account.getAccountId();
 %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部邮件</title>
<script type="text/javascript" src="<%=contextPath%>/system/email/js/email.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<style type="text/css">
html,body{
	width:100%;
	margin:0px;
	padding:0px;
}
.title{position:fixed;height:39px;width:99%;font-size:16px;line-height:40px;background-color:#F5F5F5;padding-left:10px;z-index:99999;margin-top:-40px;}
.table{margin-top:40px;}
.buttons{width:480px;float:right;}
.buttonStyle{border:#CCC solid 1px; height:24px; margin-left:10px;background-color:#FFF;font-size:12px;cursor:pointer;line-height:24px;}
.info{height:70px;width:100%;border-bottom:#CCC 1px solid;}
.top{ height:30px;width:100%;line-height:30px;font-size:15px;color:#000;}
.moddle,.bottom{height:20px;width:100%;line-height:20px;}
.top span{margin-left:15px;}
.moddle span,.bottom span{margin-left:20px;}
#emailHtml{width:90%;margin-top:20px;margin-left:5%;}
#attachment{margin-top:300px;display:none;}
#attachDiv{margin-left:2%;border:solid 1px #CCC;min-height:40px;
		padding:10px 0px 10px 10px;width:96%;margin-top:5px;display:none;}
.read,.noread{width:18px;height:15px;float:left;background:url('<%=imgPath%>/email/mail.png')}
.read{background-position:-46px -15px;}
.noread{background-position:-47px 0px;}
</style>
</head>
<body>
	<div class=title >
		<a class="btn btn-default" href="javascript:void(0);" id="back_ok" role="button">返回</a>
		<div class="buttons" >
			<input type="button" class="btn btn-info" id="sendAgain" value="再次发送" />
			<input type="button" class="btn btn-info" id="editSendAgain" value="编辑后发送" />
			<input type="button" class="btn btn-danger" id="deleteEmail" value="彻底删除" />
			<input type="button" class="btn btn-success" onclick="javascript:goPrint(2)"  value="打印" />
			<input type="button" class="btn btn-default" onclick="javascript:goLast(2)"  value="上一封" />
			<input type="button" class="btn btn-default" onclick="javascript:goNext(2)" value="下一封" />
		</div>
	</div>
	<div class="table" >
		<div class="info">
		</div>
		<div class="content" >
			<div id="emailHtml">
			
			</div>
			<div id="attachment">
				<div id="attachDiv" name="attachDiv"></div>
			</div>
		</div>
	</div>
<div class="modal fade" id="toNameModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;margin-top:30px;">
   <div class="modal-dialog" id="toName-div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="toNameModalLabel">收件人详情</h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="toName-modal-body" >
         </div>
         <div class="modal-footer" align="center" >
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">
var id = "<%=emailId%>";
var userId = "<%=userId%>";
var boxId = "<%=boxId%>";
$(function(){
	changeEmailFlagById();
	setEmail(id,'2');
	$('#back_ok').click(function(){
		window.location.href="index.jsp";
		return false;
	})
	$('#sendAgain').click(function(){
		sendEmailAgain(id);
	})
	$('#editSendAgain').click(function(){
		window.location.href= contextPath + "/system/email/sendbox/edit.jsp?emailId="+id;
	})
	$('#delEmail').click(function(){
		delEmail(id);	
	})
	$('#deleteEmail').click(function(){
		deleteEmail(id);
	})
})

</script>
</html>