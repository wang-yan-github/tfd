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
<title>邮件打印</title>
<script type="text/javascript" src="<%=contextPath%>/system/email/js/email.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script src="<%=contextPath%>/system/jsall/printtool/printtooljsall.js" type="text/javascript"></script>

<style type="text/css">
html,body{
	width:100%;
	margin:0px;
	padding:0px;
}
.buttons{width:550px;float:right;}
.buttonStyle{border:#CCC solid 1px; height:24px; margin-left:10px;background-color:#FFF;font-size:12px;cursor:pointer;line-height:24px;}
.info{min-height:70px;width:100%;border-bottom:#CCC 1px solid;}
.top{ min-height:30px;width:100%;line-height:30px;font-size:15px;color:#000;}
.moddle,.bottom{min-height:20px;width:100%;line-height:20px;}
.top span{margin-left:15px;}
.moddle span,.bottom span{margin-left:20px;}
#emailHtml{width:90%;margin-top:20px;margin-left:5%;}
#attachment{margin-top:300px;display:none;margin-bottom:50px;}
.reply{font-size:12px;}
#attachDiv{margin-left:2%;border:solid 1px #CCC;min-height:40px;box-shadow:-3px 0px 9px #ececec,0px -3px 9px #ececec,0px 3px 9px #ececec,0px 0px 9px #ececec;
		padding:10px 0px 10px 10px;width:96%;margin-top:5px;display:none;}
.read,.noread{width:18px;height:15px;float:left;background:url('<%=imgPath%>/email/mail.png')}
.read{background-position:-46px -15px;}
.noread{background-position:-47px 0px;}
td{text-align:center;}
</style>
<style media="print">
.noprint { display : none; }
</style>
</head>
<body >
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
<div class="modal fade" id="toNameModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
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
<div align="right" class="noprint" style="background-color: silver;position:fixed;width:100%;bottom:0px;">
<button id="print1" type="button" onclick="printer(1);" class="btn btn-info btn-lg">打印设置</button>
<button id="print2" type="button" onclick="printer(2);" class="btn btn-info btn-lg">打印预览</button>
<button id="print3" type="button" onclick="printer(3);" class="btn btn-info btn-lg">直接打印</button>
<button id="print4" type="button" onclick="printer(4);" class="btn btn-info btn-lg">关闭窗口</button>
<object id="printWB"  style="dispaly:none" classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"  height="0" width="0"></object>
</div>
</body>
<script type="text/javascript">
var id = "<%=emailId%>";
var boxId = "<%=boxId%>";
var userId = "<%=userId%>";
$(function(){
	setEmail(id,boxId);
	var binfo = getBrowerinfo();
	if(binfo=="firefox"||binfo=="chrome"||binfo=="safari"){
		$("#print1").hide();
		$("#print2").hide();
	}
	createXifream();
	getIdea();
	doXmacrotag();
});

</script>
</html>