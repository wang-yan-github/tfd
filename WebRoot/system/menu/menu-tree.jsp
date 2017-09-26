<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript">
		$(function() {
			var zNodes;
			$.ajax({
				url : contextPath+ "/tfd/system/menu/act/SysMenuAct/getAllMenuInfoAct.act",
				dataType : "json",
				async : false,
				error : function(e) {
				},
				success : function(data) {
					zNodes = data;
				}
			});
			var setting = {
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						onClick : function(event, treeId, treeNode) {
							parent["edit"].location = contextPath+ "/system/menu/update.jsp?sysMenuId=" + treeNode.id
						}
					},
					view : {
						showLine : false
					}
				};
			$.fn.zTree.init($("#tree1"), setting, zNodes);
			
			$("#add-menu").on("click",function(){
				parent["edit"].location = contextPath+ "/system/menu/add.jsp?sysMenuId=0";
			});
			
		});
	</script>
</head>
	<body>
		<div class="list-group" style="margin-top:10px;">
			<a href="javascript:void(0);" class="list-group-item active">
				<h5 class="list-group-item-heading">菜单管理</h5>
			</a>
			<div class="list-group-item">
				<ul id="tree1" class="ztree"></ul>
			</div>
			<a class="list-group-item" id="add-menu">
				<h5 class="list-group-item-heading" align="center">添加菜单</h5>
			</a>
		</div>
	</body>
</html>