<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String accountId = request.getParameter("accountId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建账号</title>
<script type="text/javascript">
var accountId="<%=accountId%>";
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/getAccountJsonByIdAct.act";
	var paramData={accountId:accountId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data["passwordType"]=="1")
				{
				data["passwordType"]="正常密码验证";
				}else if(data["passwordType"]=="2")
				{
					data["passwordType"]="动态密码验证";
					}
			if(data["language"]=="1")
			{
			data["language"]="简体中文";
			}else if(data["language"]=="2")
			{
				data["language"]="美式英文";
				}
			if(data["thmem"]=="1")
			{
			data["thmem"]="默认主题";
			}else if(data["thmem"]=="2")
			{
				data["thmem"]="主题1";
				}
			for(var name in data){
				$("#"+name).html(data[name]);
			}
		}
	});
})
</script>
</head>
<body>
<div align="center" style="margin-top:10px;">
<div class="list-group"  style="width:60%;">
<a class="list-group-item active">账号信息</a>
<div  class="list-group-item" >
   
<table class="table table-striped table-condensed" >
<tr>
<td width="150px">账号:</td>
<td><div name="accountId" id ="accountId"></div></td>
</tr>
<tr>
<td>初始密码:</td>
<td><div name="passWord" id="passWord"></div></td>
</tr>
<tr>
<td>密码类型:</td>
<td><div name="passwordType" id="passwordType"></div></td>
</tr>
<tr>
<td>系统主题:</td>
<td><div name="thmem" id="thmem"></div>
</td>
</tr>
<tr>
<td>权限组别:</td>
<td><div name="userPrivName" id="userPrivName"></div></td>
</tr>
<tr>
<tr>
<td>是否允许登陆:</td>
<td>
<div id="notLoginName" name="notLoginName"></div>
</td>
</tr>
<tr>
<tr>
<td>别名:</td>
<td><div type="text" name="byName" id="byName"></div></td>
</tr>
<tr>
<td>语言:</td>
<td>
<div id="language" name="language" ></div>
</td>
</tr>
</table>



   </div>
   </div>
<div align="center"><button type="button" onclick="javascript :history.back(-1);" class="btn btn-primary" >返回</button></div>
</div>
</body>
</html>