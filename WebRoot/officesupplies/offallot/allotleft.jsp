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
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offallot/js/allotleft.js"></script>
</head>
<body>
<div class="panel panel-info">
   <div class="panel-heading">
      <h3 class="panel-title">
       调拨
      </h3>
   </div>
   <table class="table table-striped">
<tr>
<td ><div><ul id="tree1" class="ztree"></ul></div></td>
</tr>
</table>
</div>

</body>
</html>