<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String accountId = request.getParameter("accountId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>编辑账号</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
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
			for(var name in data){
				$("#"+name).val(data[name]);
			}
		}
	});
})

function updateAccountId()
{
	var paramData=$("#form1").serialize(); 
	var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/updateAccountAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=="1")
				{
					alert("更新成功！");
				}else{
					alert("更新失败！");
				}
		}
	});
}


</script>
</head>
<body style="margin-top: 10px;">
<div align="center">
 <form id="form1" name="form1" style="width: 80%;">
<div class="list-group-item"  style="padding: 0px;">
<table class="table table-striped table-condensed" >
<tr>
<td width="150px;">账号:</td>
<td><div class="col-xs-6"><input type="text" name="accountId" id ="accountId"  readonly class="form-control" /></div></td>
</tr>
<tr>
<td>初始密码:</td>
<td><div class="col-xs-6"><input type="password" name="passWord" id="passWord" class="form-control" /></div></td>
</tr>
<tr>
<td>密码类型:</td>
<td><div class="col-xs-6"><select name="passwordType" id="passwordType" class="form-control" />
<option value="1">正常密码验证</option>
<option value="2">动态密码验证</option>
</select></div>
</td>
</tr>
<tr>
<td>系统主题:</td>
<td><div class="col-xs-6"><select name="thmem" id="thmem" class="form-control" >
<option value="1" selected="selected">默认主题</option>
<option value="2">主题1</option>
</select></div>
</td>
</tr>
<tr>
<td>权限组别:</td>
<td>
<input type="hidden" name="userPriv" id="userPriv" class="form-control" >
<div class="col-xs-6">
<input type="text" name="userPrivName" id="userPrivName" class="form-control"/>
</div><a href="javascript:void(0);" onclick="privinit(['userPriv','userPrivName'],'true');" style="line-height: 30px;">选择</td>
</tr>
<tr>
<tr>
<td>是否允许登陆:</td>
<td>
<div class="col-xs-6"><select id="notLogin" name="notLogin" class="form-control" >
<option value="0">允许登陆</option>
<option value="1">禁止登陆</option>
</select></div>
</td>
</tr>
<tr>
<tr>
<td>别名:</td>
<td><div class="col-xs-6"><input type="text" name="byName" id="byName" class="form-control" ></div></td>
</tr>
<tr>
<td>语言:</td>
<td>
<div class="col-xs-6"><select id="language" name="language" class="form-control" >
<option value="1">简体中文</option>
<option value="2">美式英文</option>
</select></div>
</td>
</tr>
</table>
</div>
<br>
<div align="center"> <button type="submit" class="btn btn-primary" onclick="updateAccountId();">确认</button>
<button type="button" onclick="javascript :history.back(-1);" class="btn btn-warning">返回</button></div>
</form>  
</div>

 <div id="modaldialog"></div>
</body>
</html>