<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册浏览</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="panel panel-info" style="width: 100%;">
   <div class="panel-heading">
      <h3 class="panel-title">
        新建任务
      </h3>
   </div>
   <div class="panel-body">
<table class="table table-striped ">
<tr>
<td>任务标题：</td>
<td><input type="text" class="form-control"></td>
<td>创建人员：</td>
<td><input type="text" class="form-control"></td>
</tr>
<tr>
<td>任务类型：</td>
<td><select class="form-control">
<option value="1">周任务</option>
<option value="2">月任务</option>
<option value="3">季任务</option>
<option value="4">年任务</option>
</select></td>
<td>执行时间：</td>
<td><input type="text" class="form-control"></td>
</tr>
</table>
</div>
</div>
</body>
</html>