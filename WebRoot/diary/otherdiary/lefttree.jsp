<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志检索</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/otherdiary/js/orgTree.js"></script>
</head>
<body>
<div class="list-group">
   <a style="cursor: auto;" class="list-group-item active">
      <h5 class="list-group-item-heading">
         日志检索
      </h5>
   </a>
   <div  class="list-group-item" width="200px;">
     <ul id="userTree" class="ztree"></ul>
   </div>
</div>
</body>
</html>