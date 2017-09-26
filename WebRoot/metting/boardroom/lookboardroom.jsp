<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室列表</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/metting/metting.css"></link>
<script type="text/javascript" src="<%=contextPath%>/metting/boardroom/js/lookboardroom.js"></script>
</head>
<body onload="lookboardroom();">
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >会议室管理
</span>
</div>
<div style=" float: right;margin-top:15px;margin-right: 20px;" id="btnitem">
       <button type="button" class="btn btn-primary btn-sm" onclick="newboardroom();"> 新建会议室</button>
</div>
</div>
    <div class="list-group-item"  style="padding: 0px;cursor: auto;width:90%;margin-left: 5%;margin-top: 20px;">
<table class="table table-striped table-condensed" >	
<thead>
<tr>
<td align="center" width="20%">名称</td>
<td align="center" width="15%">可容纳人数</td>
<td align="center" width="15%">会议管理员</td>
<td align="center" width="30%">地址</td>
<td align="center" width="20%">操作</td>
</tr>
</thead>
<tbody id="boardroomtable"></tbody>
</table>
   </div>
</body>
</html>