<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>提醒设置</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId="<%=prcsId%>";

function doinit()
{
	var pram={flowId:flowId,prcsId:prcsId};
	var requestUrl = "<%=contextPath %>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getSmsConfigAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:pram,
		async:false,
		success:function(data){
			$("input[name='sms1'][value='"+data.sms1+"']").attr("checked",true); 
			$("input[name='sms2'][value='"+data.sms2+"']").attr("checked",true); 
			$("input[name='sms3'][value='"+data.sms3+"']").attr("checked",true); 
			$("input[name='sms4'][value='"+data.sms4+"']").attr("checked",true); 
			$("input[name='sms5'][value='"+data.sms5+"']").attr("checked",true); 
		}
	});
	}


function save()
{
	var sms1 =$('input:radio[name="sms1"]:checked').val();
	var sms2 =$('input:radio[name="sms2"]:checked').val();
	var sms3 =$('input:radio[name="sms3"]:checked').val();
	var sms4 =$('input:radio[name="sms4"]:checked').val();
	var sms5 =$('input:radio[name="sms5"]:checked').val();
	var pram ={flowId:flowId,prcsId:prcsId,smsConfig:"{\"sms1\":\""+sms1+"\",\"sms2\":\""+sms2+"\",\"sms3\":\""+sms3+"\",\"sms4\":\""+sms4+"\",\"sms5\":\""+sms5+"\"}"};
	var requestUrl = "<%=contextPath %>/tfd/system/workflow/flowprocess/act/FlowProcessAct/setSmsConfigAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:pram,
		async:false,
		success:function(data){
			if(data==1)
				{
				layer.msg("设置成功！")
				}
		}
	});
	}
</script>
</head>
<body	onload="doinit();">
<form id="form1" method="post" name="form1">
<input type="hidden" id="flowId" name="flowId" value="<%=flowId%>">
 <div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">提醒设置</span>
</div>
 <table class="table table-striped  table-condensed" >  
   <tr>
   <td>内部短信：</td>
   <td><input type="radio" name="sms1" id="sms1" value="0" checked>禁用<input type="radio" name="sms1" id="sms1" value="1">允许<input type="radio" name="sms1" id="sms1" value="2">默认</td>
   </tr>
   <tr>
   <td>内部邮件：</td>
   <td><input type="radio" name="sms2" id="sms2" value="0" checked>禁用<input type="radio" name="sms2" id="sms2" value="1">允许<input type="radio" name="sms2" id="sms2" value="2">默认</td>
   </tr>
   <tr>
   <td>外部邮件：</td>
   <td><input type="radio" name="sms3" id="sms3" value="0" checked>禁用<input type="radio" name="sms3" id="sms3" value="1">允许<input type="radio" name="sms3" id="sms3" value="2">默认</td>
   </tr>
    <tr>
   <td>手机短信：</td>
   <td><input type="radio" name="sms4" id="sms4" value="0" checked>禁用<input type="radio" name="sms4" id="sms4" value="1">允许<input type="radio" name="sms4" id="sms4" value="2">默认</td>
   </tr>
   <tr>
   <td>企业微信：</td>
   <td><input type="radio" name="sms5" id="sms5" value="0" checked>禁用<input type="radio" name="sms5" id="sms5" value="1">允许<input type="radio" name="sms5" id="sms5" value="2">默认</td>
   </tr>
   </table>
   </br>
   <div align="center"><button type="button" class="btn btn-primary" onclick="save()">保存</button></div>
</form>
</body>
</html>