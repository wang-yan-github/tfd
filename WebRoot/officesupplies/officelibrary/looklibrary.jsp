<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品库</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/officelibrary/js/looklibrary.js"></script>
</head>
<body>
<div align="center" style="width:100%;height: 50px;margin-top: 10px;">
<button type="button" class="btn btn-primary btn-large" onclick="newlibrary();"> 新建办公用品库</button>
</div>
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:90%;margin-left: 5%;">
<a style="cursor: auto;" class="list-group-item active">办公用品库设置</a>
<table class="table table-striped table-condensed" id="offlibrary">
<tr>
<td align="center" width="8%">序号</td>
<td align="center" width="10%">办公用品库</td>
<td align="center" width="20%">办公用品类别</td>
<td align="center" width="14%">所属部门</td>
<td align="center" width="14%">仓库管理员</td>
<td align="center" width="14%">物品调度员</td>
<td align="center" width="20%">操作</td>
</tr>
</table>
   </div>
</body>
</html>