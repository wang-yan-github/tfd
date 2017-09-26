<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门树</title>
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
	if($.isEmptyObject(zNodes)){
		$("#deptTree").html("暂无目录");
	} 
});
function editinfo(event, treeId, treeNode)
{
	if($("#DateType").val()=='1'){
		parent["edit"].location=contextPath+"/calendar/leader/right.jsp?deptId="+treeNode.id;
	}else if($("#DateType").val()=='2'){
		parent["edit"].location=contextPath+"/calendar/leader/month.jsp?deptId="+treeNode.id;
	}else{
		parent["edit"].location=contextPath+"/calendar/leader/day.jsp?deptId="+treeNode.id;
	}
	
}
function goFromMe(event, treeId, treeNode)
{
	parent["edit"].location=contextPath+"/calendar/leader/fromMe.jsp";
	}
function goForMe(event, treeId, treeNode)
{
	parent["edit"].location=contextPath+"/calendar/leader/forMe.jsp";
	}
function goOutput(event, treeId, treeNode)
{
	parent["edit"].location=contextPath+"/calendar/leader/output.jsp";
	}
</script>
</head>
<body style="margin-top:0;">
<input type="hidden" id="DateType" value="1" >
<div class="list-group">
   <a  class="list-group-item active">
      <h5 class="list-group-item-heading">
         请选择部门
      </h5>
   </a>
   <div  class="list-group-item">
     <ul id="deptTree" class="ztree"></ul>
   </div>
   <a class="list-group-item" onclick="javascript:goOutput();">
      <h5 class="list-group-item-heading" align="center">
         查询/导出
      </h5>
   </a>
   <a style="cursor:pointer;" class="list-group-item" onclick="javascript:goFromMe()">
      <h5 class="list-group-item-heading" align="center">
         我分配的日程
      </h5>
   </a>
    <!-- <a style="cursor:pointer;"  class="list-group-item" onclick="javascript:goForMe()">
      <h5 class="list-group-item-heading" align="center">
         领导安排的日程
      </h5>
   </a> -->
</div>
</body>
</html>