<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/unit/dept/js/dept-tree.logic.js"></script>
<style>
	#dept-tree{overflow:auto;}
	#dept-add,#dept-import,#dept-export{text-align:center;}
</style>
</head>
<body style="margin-top:0;">
	<br/>
	<div class="list-group">
	  <a href="javascript:void(0);" class="list-group-item active">
	           所有部门
	  </a>
	  <li href="javascript:void(0);" class="list-group-item">
		<ul id="dept-tree" class="ztree"></ul>
	  </li>
	  <a href="javascript:void(0);" class="list-group-item" id="dept-add">
	  	添加部门
	  </a>
	  <a href="javascript:void(0);" class="list-group-item" id="dept-import">
		批量导入
	  </a>
	  <a href="javascript:void(0);" class="list-group-item" id="dept-export">
	  	部门导出
	  </a>
	</div>
</body>
</html>