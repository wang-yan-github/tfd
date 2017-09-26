<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品列表</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offres/js/left.js"></script>
</head>
<body>
<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">办公用品库设置</a>
   <table class="table table-striped">
<tr>
<td ><div><ul id="tree1" class="ztree"></ul></div></td>
</tr>
</table>
<a class="list-group-item" onclick="javascript:getquery();">
      <h5 class="list-group-item-heading" align="center">
         办公用品查询
      </h5>
   </a>
   <a class="list-group-item" onclick="javascript:getNewres();;">
      <h5 class="list-group-item-heading" align="center">
         新建办公用品
      </h5>
   </a>
</div>
</body>
</html>