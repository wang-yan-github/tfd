<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
var zNodes ;
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			zNodes=data;
			}
	});
});

var setting = {
			data: {
				simpleData: {
					enable: true
				}
				},
			callback:{
				onClick:editinfo
			}
		};
$(document).ready(function(){
			$.fn.zTree.init($("#deptTree"), setting, zNodes);
		});
function editinfo(event, treeId, treeNode)
{
	parent["edit"].location=contextPath+"/system/unit/dept/manage.jsp?deptId="+treeNode.id;
	}
function addtask()
{
	parent["edit"].location=contextPath+"/task/addtask/right.jsp";
	}
</script>
</head>
<body style="margin-top:0;">
<div class="list-group">
   <a href="javascript:void(0);" class="list-group-item active">
      <h5 class="list-group-item-heading">
         任务管理
      </h5>
   </a>
   <div  class="list-group-item">
     <ul id="deptTree" class="ztree"></ul>
   </div>
   <a href="javascript:void(0);" class="list-group-item" onclick="addtask();">
      <h5 class="list-group-item-heading" align="center">
         添加任务
      </h5>
   </a>
      <a href="javascript:void(0);" class="list-group-item" onclick="">
      <h5 class="list-group-item-heading" align="center">
         任务查询
      </h5>
   </a>
</div>
</body>
</html>