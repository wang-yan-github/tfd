<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>行政级别类型设置</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/default/easyui.css"></link>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css"></link>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#myTable").datagrid({
//      title: "用户列表",
     width: document.body.clientwidth,
//      height: document.body.clientheight,
		height: 400,
     collapsible: true,
     url: "<%=contextPath %>/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveListAct.act",
     method: 'POST',
     sortName: 'id',
     loadMsg: "数据加载中...",
     pagination:true,
     striped: true,
     singleSelect:true,  
     remoteSort:true,  
     columns:[[
         {title: '序号', field: 'ID', width: 100, align: 'center',sortable:true},
         {title: '行政级别编号', field: 'PASS_LEAVE_ID', width: 200, align: 'center',sortable:true},
         {title: '行政级别名称', field: 'PASS_LEAVE_NAME', width: 200, align: 'center',sortable:true},
         {title: '下一行政级别', field: 'PASS_LEAVE_NEXT', width: 200, align: 'center',sortable:true},
         {title: '关联流程', field: 'FLOW_NAME', width: 200, align: 'center',sortable:true},
         {title: '操作', field: 'OPT', width: 150, align: 'center',sortable:true}
     ]]
 });
  
 var p = $('#myTable').datagrid('getPager');  
     $(p).pagination({  
     pageSize: 5,//每页显示的记录条数，默认为5  
     pageList: [5, 10],//可以设置每页记录条数的列表  
     beforePageText: '第',//页数文本框前显示的汉字  
     afterPageText: '页    共 {pages} 页',  
     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
 }); 
});

function setPassLeave(passLeaveId)
{
	location.href=contextPath+"/system/unit/leadleave/edit.jsp?passLeaveId="+passLeaveId;
	}

</script>
</head>
<body>
<div id="myTable" name="myTable"></div>
</body>

</html>