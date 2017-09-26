<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<link rel="stylesheet"
	href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
	var zNodes;
	$(function() {
		var requestUrl = contextPath
				+ "/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoTreeAct.act";
		$.ajax({
			url : requestUrl,
			dataType : "json",
			data : {},
			async : false,
			error : function(e) {
				alert(e.message);
			},
			success : function(data) {
				zNodes = data;
			}
		});
	});

	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : editinfo
		},
		async:{
			enable:true,
			url:contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getZtreedeptAlluserAct.act",
			autoParam:["id"]
		}
	};
	$(document).ready(function() {
		$.fn.zTree.init($("#userTree"), setting, zNodes);
	});

	function editinfo(event, treeId, treeNode) {
		if (treeNode.isParent) {
			parent["edit"].location = contextPath
					+ "/system/attend/setting/user/userlist.jsp?deptId="
					+ treeNode.id;
		} else {
			parent["edit"].location = contextPath
					+ "/system/attend/setting/user/manage.jsp?userId=" + treeNode.id;
		}
	}
</script>
</head>
<body >
	<div class="list-group">
		<a class="list-group-item active">
			<h5 class="list-group-item-heading">在职人员</h5>
		</a>
		<div class="list-group-item">
			<ul id="userTree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>