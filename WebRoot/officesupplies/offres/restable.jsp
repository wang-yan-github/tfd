<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品列表</title>
<%String classifyId=request.getParameter("classifyId"); %>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offres/js/restable.js"></script>
<script type="text/javascript">
var classifyId="<%=classifyId%>";
</script>
</head>
<body>
<div align="left" style="width:95%;height: 50px;margin-top: 10px;margin-left: 1%;">
 <button type="button" class="btn btn-primary btn-large" onclick="newres();">
   新建办公用品
</button>
</div>
<div id="myTable" name="myTable"></div>
</body>
</html>