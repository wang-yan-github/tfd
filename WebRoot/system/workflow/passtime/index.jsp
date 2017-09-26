<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>工作流时效分析</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript">
$(function ()
{
	var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/getNewTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
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
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	};
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	vid="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		if(!nodes[i].isParent)
			{
			v += nodes[i].name + ",";
			vid+=nodes[i].id + ",";
			}
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	var deptObj = $("#flowName");
	deptObj.attr("value", v);
	if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
	var deptIdObj = $("#flowId");
	deptIdObj.attr("value",vid);
}

function showMenu() {
	var cityObj = $("#flowName");
	var cityOffset = $("#flowName").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});
</script>
</head>
<body>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1000;">
<ul id="treeDemo" class="ztree" style="margin-top:0; "></ul>
</div>
<div class="panel panel-info"  style="width: 80%;margin-left: 10%">
   <div class="panel-heading">
      <h3 class="panel-title">
        工作流时效分析
      </h3>
   </div>
   <div class="panel-body" >
<table class="table table-striped" >
<tr>
<td width="200px">时间段：</td>
<td><div class="col-xs-8" >
<div style="float: left;"><input style="cursor: pointer;" readonly  type="text" name="beginTime" id="beginTime" 	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control"/></div>
<div style="float: left;">至</div>
<div style="float: left;"><input style="cursor: pointer;" readonly type="text" name="endTime" id="endTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control"/></div>
</div></td>
</tr>
<tr>
<td>选择流程：</td>
<td><div class="col-xs-4" >
<input type="hidden"  id="flowId" name="flowId" class="form-control"/>
<input type="text" id="flowName" name="flowName" class="form-control"  value="" onclick="showMenu();" readonly/>
</div></td>
</tr>
<tr>
<td>分组对象：</td>
<td>
<div class="col-xs-4" >
<select id="oderby" name="oderby" class="form-control">
<option value="dept">部门</option>
<option value="user">用户</option>
<option value="moth">月份</option>
</select>
</div>
</td>
</tr>
<tr>
<td>选择人员：</td>
<td><div class="col-xs-4" >
<input  type="hidden"  class="form-control"  id="userId" name="userId" />
<textarea class="form-control" id="userName" name="userName" type="text"></textarea>
</div></td>
</tr>
<tr>
<td>选择部门：</td>
<td><div class="col-xs-4" >
<input  type="hidden"  class="form-control"  id="deptId" name="deptId" />
<textarea class="form-control" id="deptName" name="deptName"></textarea></div></td>
</tr>
<tr>
<td>选择职务：</td>
<td><div class="col-xs-4" >
<input  type="hidden"  class="form-control"  id="userPrivId" name="userPrivId" />
<textarea class="form-control" id="userPrivId" name="userPrivId"></textarea></div></td>
</tr>
</table>
<div align="center"><button type="button" class="btn btn-primary">超时统计</button></div>
</div>
</div>
</body>
</html>