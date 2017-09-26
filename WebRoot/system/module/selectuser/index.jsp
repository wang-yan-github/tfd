<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员选择控件</title>
</head>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/select/selectuser.js"></script>
<script type="text/javascript">
var zNodes ;
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			zNodes=eval(data);
			}
	});
});

function getDeptUser(event, treeId, treeNode)
{
	var userList;
	var deptId=treeNode.id;
	var requestUrl=contextPath+"/tfd/system/module/selectuser/act/SelectUserAct/getDeptUserJsonAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{deptId:deptId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			userListDiv(data, "userList","selectedUser");
			}
	});
	}
var setting = {
			data: {
				simpleData: {
					enable: true
							}
					},
			callback:{
				onClick:getDeptUser
			}
		};
$(document).ready(function(){
			$.fn.zTree.init($("#deptTree"), setting, zNodes);
		});
</script>
<body>
<table>
<tr>
<td colspan="3" style="width: 100%">
<div style="height: 50px;background: blue;"></div>
</td>
</tr>
<tr>
<td><div id="deptTree" class="ztree" style="overflow:scroll; height:400px;width: 150px"> </div></td>
<td><div id="userList" class="ztree" style="overflow:scroll; height:400px;width: 150px"></div></td>
<td style="width: 30%"><div id="selectedUser" class="ztree" style="overflow:scroll; height:400px;width: 150px"></div></td>
</tr>
</table>
<br>
<div align="center"><input type="button" value="确定"></div>
</body>
</html>