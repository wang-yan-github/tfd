<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<% 
String userName= request.getSession().getAttribute("USER_NAME").toString();
String userId = request.getSession().getAttribute("USER_ID").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作流分类</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowsort.css"></link>
<script type="text/javascript">
var zNodes;
$(function(){
	var requestUrl=contextPath+"/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/getWorkFlowTypeTreeAct.act";
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
	getSelect("workflowtype","moduleId","");
});
var setting = {
		view: {
			dblClickExpand: false,
			selectedMulti:false
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
			v += nodes[i].name + ",";
			vid+=nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		{
			$("#leaveName").val(v);
		}
		if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
		{
			$("#leaveId").val(vid);
		}
		$("#menuContent").fadeOut("fast");
	}

	function showMenu() {
		var cityObj = $("#leaveName");
		var cityOffset = $("#leaveName").offset();
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
<style type="text/css">
html,body
{
height: 100%;
}
	#menuContent{
		width: 200px;
		height: 200px;
		overflow: auto;
		display: none;
		position: absolute;
		border: solid #CCC 1px;
		background-color:white; 
		z-index: 999;
	}
</style>
</head>
<body style="margin-top: 10px;">
<div id="menuContent">
<table class="TableBlock no-top-border" style="width: 100%;">
			<tr>
				<td class="TableData"><div><ul id="treeDemo" class="ztree" style="margin-top:0;"></ul></div></td>
			</tr>
		</table>
</div>
<div align="center" class="widget" >
<form id="form1" name="form1"  class="form-horizontal"  action="<%=contextPath%>/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/addWorkFlowTypeAct.act" style="width: 80%;"> 
<div class="widget-header bordered-bottom bordered-sky">
<span class="widget-caption">新建流程分类</span>
</div>
<div class="panel-body">
<table class="table table-striped table-condensed" >
<tr>
<td width="150px">序号：
</td>
<td>
 <div class="col-xs-6 form-group" >
<input id="sortId" name="sortId" type="text" class="form-control"></div>
</td>
</tr>
<tr>
<td>层级：</td>
<td>
<div class="col-xs-6">
<input id="leaveId" name="leaveId" type="text" style="display:none;" class="form-control">
<input id="leaveName" name="leaveName" type="text" readonly value="" onclick="showMenu()" class="form-control">
</div>
</td>
</tr>
<tr>
<td>分类名称：
</td>
<td>
<div class="col-xs-6 form-group">
<input id="flowTypeName" name="flowTypeName" type="text" class="form-control"></div>
</td>
</tr>
<tr>
<td>所属模块：
</td>
<td>
<div class="col-xs-6">
<div id="moduleId" ></div>
</div>
</td>
</tr>
<tr>
<td>管理者：
</td>
<td>
<div class="col-xs-6">
<input id="manageAccountId" name="manageAccountId" type="hidden" class="form-control"  readonly  value="<%=userId%>">
<input id="manageAccountName" name="manageAccountName" type="text" class="form-control"  value="<%=userName%>">
</div>
</td>
</tr>
<!-- <tr> -->
<!-- <td>所属机构： -->
<!-- </td> -->
<!-- <td> -->
<!-- <div class="col-xs-6"><input id="orgId" name="orgId" type="text" value="1" disabled="disabled" class="form-control"></div> -->
<!-- </td> -->
<!-- </tr> -->
</table>
   </div>
   <br>
   <div align="center"><button type="submit" class="btn btn-primary">保存</button></div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			sortId: {
                validators: {
                	container: 'popover',
                	numeric: {
                        message: '请填写正确的数字！'
                    },
			        notEmpty: {
			            message: '请填写正确的数字！'
			        }
                }
            },
            flowTypeName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '分类名称不能为空！'
                    }
                }
            }
		}
	}).on('success.form.bv',function(e){
	     parent["left"].location=contextPath+ "/system/workflow/flowsort/left.jsp";
		}); 
});
</script>
</body>
</html>