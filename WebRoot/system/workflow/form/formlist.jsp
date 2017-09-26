<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String sortId=request.getParameter("sortId"); %>
<%String type=request.getParameter("type"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单维护</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/workflow/form/js/formlist.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/addform.css"></link>
<script type="text/javascript">
var workFlowTypeId="<%=sortId%>";
var type="<%=type%>";
</script>
</head> 
<body style="margin-top: 10px;" onload="doinit();">
<div align="center" class="widget" >
		<div style="width: 90%;">
			<div class="widget-header bordered-bottom bordered-sky">
			<span class="widget-caption">表单列表</span>
			</div>
			<div id="myTable" name="myTable" ></div>
		</div>
</div>
</body>
</html>
