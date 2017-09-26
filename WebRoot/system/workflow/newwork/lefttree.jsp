<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>工作流分类</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/newwork/js/lefttree.js"></script>
</head>
<body style="margin-top:0;">
<div class="list-group">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
        工作流分类
      </h5>
   </a>
   <div class="list-group-item">
     <ul id="workflowtree" class="ztree"></ul>
   </div>
   <a class="list-group-item" onclick="historyWork();">
      <h5 class="list-group-item-heading" align="center">
         历史工作
      </h5>
   </a>
   <a class="list-group-item" onclick="showAll();">
      <h5 class="list-group-item-heading" align="center">
         全部工作
      </h5>
   </a>
</div>
</body>
</html>