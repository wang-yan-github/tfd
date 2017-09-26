<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%String userId=request.getParameter("userId");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
var userId='<%=userId%>';
var accountId="";
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoAct.act";
	var paramData={userId:userId};
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
				$("#"+name).html(data[name]);
			}
			accountId=data.accountId;
		}
	});
});
function edit()
{
	location.href=contextPath+"/system/unit/userinfo/edituserinfo.jsp?accountId="+accountId;
	}
</script>
<body style="margin-top: 10px;">
<div align="center">
<div class="list-group-item"  style="padding: 0px;width: 80%;">
<div class="widget-header bg-palegreen">
<span class="widget-caption">人员信息</span>
</div>
<table class="table table-striped table-condensed" >
<tr>
<td width="150px">用户账号：</td>
<td>
<div name="accountId" id="accountId"></div>
</td>
<td width="150px">用户姓名：</td>
<td>
<div name="userName" id="userName"></div>
</td>
</tr>
<tr>
<td>性别：</td>
<td>
<div name="sex" id="sex"></div>
</td>
<td>部门：</td>
<td>
<div name="deptId" id="deptId"></div>
</td>
</tr>
<tr>
<td>上级领导：</td>
<td>
<div name="leadUserName" id="leadUserName"></div>
</td>
<td>权限组：</td>
<td>
<div name="postPriv" id="postPriv"></div>
</td>
</tr>
<tr>
<td>行政级别：</td>
<td>
<div name="leadLeaveName" id="leadLeaveName"></div>
</td>
<td>兼职：</td>
<td>
<div name="otherPriv" id="otherPriv"></div>
</td>
</tr>
<tr>
<td>家庭地址：</td>
<td>
<div name="homeAdd" id="homeAdd"></div>
</td>
<td>家庭电话：</td>
<td>
<div name="homeTel" id="homeTel"></div>
</td>
</tr>
<tr>
<td>手机号码：</td>
<td>
<div name="mobileNo" id="mobileNo"></div>
</td>
<td>QQ号码：</td>
<td>
<div name="qQ" id="qQ"></div>
</td>
</tr>
<tr>
<td>电子邮箱：</td>
<td>
<div name="eMaile" id="eMaile"></div>
</td>
<td>工号：</td>
<td>
<div name="workId" id="workId"></div>
</td>
</tr>
<tr>
<td>管理范围：</td>
<td>
<div name="manageDept" id="manageDept"></div>
</td>
<td>管理部门：</td>
<td>
<div name="otherDept" id="otherDept"></div>
</td>
</tr>
<!-- <tr> -->
<!-- <td>所属机构：</td> -->
<!-- <td colspan="3"> -->
<!-- <div name="orgId" id="orgId"></div> -->
<!-- </tr> -->
</table>
</div>
</div>
<br>
<div align="center"><button onclick="edit();" class="btn btn-info" >编辑</button> <button onclick="JavaScript:history.go(-1);" class="btn btn-default">返回</button></div>
</body>
</html>