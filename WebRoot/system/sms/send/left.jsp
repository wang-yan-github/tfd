<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<html>
<head>
<title>内部邮件</title>
<script type="text/javascript">
	var smsJson;
	$(function() {
		var requestUrl = contextPath
				+ "/tfd/system/sms/act/SmsAct/getNoReadSmsAct.act";
		$.ajax({
			url : requestUrl,
			dataType : "json",
			data : {},
			async : false,
			error : function(e) {
				alert(e.message);
			},
			success : function(data) {
				smsJson = data;
				$("#smsCount").html("<b>共有 " + smsJson.length + " 条未读</b>");
			}
		});
	});
	function getemail() {
		alert(1);
	}
	function setemail() {
		alert(2);
	}
</script>
</head>
<body style="margin-top:0;">
<div class="list-group">
   <a href="javascript:void(0);" class="list-group-item active">
      <h5 class="list-group-item-heading">
         内部短信
      </h5>
   </a>
   <a href="javascript:void(0);" class="list-group-item" onclick="getemail();">
      <h5 class="list-group-item-heading" align="center">
        收件箱
      </h5>
   </a>
   
     <a href="javascript:void(0);" class="list-group-item" onclick="">
      <h5 class="list-group-item-heading" align="center">
        草稿箱
      </h5>
   </a>
   
   
   <a href="javascript:void(0);" class="list-group-item" onclick="">
      <h5 class="list-group-item-heading" align="center">
        废件箱
      </h5>
   </a>
   
</div>
</body>
</html>