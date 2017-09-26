<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部邮件</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript">
function saveSms()
{
	}
function back()
{
	window.close();
	}
function sendSms()
{
	var url=contextPath+"/tfd/system/sms/act/SmsAct/sendSmsAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{
			smsTo:$("#accountId").val(),
			attachId:$("#attachId").val(),
			smsSendTime:$("#smsSendTime").val(),
			smsContect:UE.getEditor('editor').getContent()
		},
		async:false,
		error:function(e){
			alert("发送失败!");
		},
		success:function(data){
			if(data=="OK")
				{
				alert("发送成功!")
				}
			}
	});
	
	}
</script>
</head>
<body>
<div class="panel panel-info">
   <div class="panel-heading">
      <h3 class="panel-title">
        发送内部短信
      </h3>
   </div>
   <div class="panel-body">
   <table class="table table-striped ">
<tr>
<td width="200px;">收件人：</td>
<td><div class="col-xs-8"><textarea id="accountId" style="display:none;">
</textarea><textarea id="userName" class="form-control"></textarea></div><div style="float: left;"><a href="javascript:void(0);" onclick="userinit(['accountId','userName']);">添加人员</a></div>
</td>
</tr>
<tr>
<td colspan="2"> 
<div id="editor" type="text/plain" style="width:100%;height:200px;"></div>
</td>
</tr>
<tr>
<td>附件：</td>
<td><div class="col-xs-8"><input type="text" id="attachId" name="attachId" style="display:none;"/></div></td>
</tr>
<tr>
<td>定时发送：</td>
<td>
<div class="col-xs-4"><input type="text" name="smsSendTime" id="smsSendTime" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择时间"/></div>
</td>
</tr>
</table>
</div>
</div>
<div align="center">
<button onclick="sendSms();" class="btn btn-primary">发送</button>
<button class="btn btn-primary">保存</button>
<button onclick="back();" class="btn btn-primary">返回</button>
</div>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
UE.getEditor('editor');
</script>
</html>