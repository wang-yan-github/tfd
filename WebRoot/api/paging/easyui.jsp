<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>分页</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css"></link>
<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script>
$(function(){
	$("#student-list").datagrid({
		url:"<%=contextPath%>/api/paging/PagingAct/studentList.act",
        columns:[
             [
				{field:"name",title:"姓名",width:"60%"},
				{field:"age",title:"年龄",width:"20%"}
             ]
        ],
        pagination:true,
        pageNumber:1,
        displayMsg:"第{from}-{to}，总条数：{total}"
	});
	$("#student-list").datagrid("getPager").pagination({
		pageNumber:1,
		beforePageText:"第",
		afterPageText:"页,共{pages}页",
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});
</script>
</head>
<body>
	<table id="student-list">
	
	</table>
</body>
</html>