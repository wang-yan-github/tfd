<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String flowTypeId=request.getParameter("flowTypeId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程分类管理</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowsort.css"></link> 
<script type="text/javascript">
var flowTypeId="<%=flowTypeId%>";
var zNodes;
$(function(){
	var requestUrl ="<%=contextPath%>/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/getWorkFlowTypeinfoAct.act";
			$.ajax({
	 			url:requestUrl,
	 			dataType:"json",
	 			data:{flowTypeId:flowTypeId},
	 			async:false,
	 			success:function(data){
	 				for(var name in data)
	 				{
	 					$("*[name='"+name+"']").val(data[name]);
	 				}
	 			}
	 		});
			
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

	document.onreadystatechange = function () {   
	    if(document.readyState=="complete") {  
	    		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	    		var node = treeObj.getNodeByParam("id", flowTypeId, null);
	    			if((node.isParent!=null)&&(node.isParent==true))
		    			{
			        		var pnode = node.getParentNode();
			        		if(pnode!=null)
			        			{
			        			var leaveName = $("#leaveName");
				        		leaveName.attr("value", pnode.name);
			        			}
		    			}
	     }   
	 } 
function deleteFlowType(){
	var requestUrl ="<%=contextPath%>/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/deleteWorkFLowTypeAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"text",
			data:{flowTypeId:flowTypeId},
			async:false,
			success:function(data){
				if(data=='OK')
					{
					 window.location.href=contextPath+ "/system/workflow/flowsort/add.jsp";
					}else
						{
						layer.msg('删除失败!');
						}
				
			}
		});
}
	
function back()
{
	location.href="add.jsp";
	}
</script>
<style type="text/css">
html,body
{
height: 100%;
}
	#menuContent{
		width: 300px;
		height: 300px;
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
<div align="center" class="widget" >
<form id="form1" name="form1" class="form-horizontal"  action="<%=contextPath%>/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/updateWorkFlowTypeAct.act" style="width: 80%;">
<input type="text" id="flowTypeId" name="flowTypeId" style="display:none;">
   <div class="widget-header bordered-bottom bordered-sky">
	<span class="widget-caption">修改流程分类</span>
</div>
<div class="panel-body">
<table class="table table-striped table-condensed" >
<tr>
<td width="150px">序号：
</td>
<td>
<div class="col-xs-6 form-group">
<input id="sortId" name="sortId" type="text" class="form-control"></div>
</td>
</tr>
<tr>
<td>层级：
</td>
<td>
<div class="col-xs-6">
<input id="leaveId" name="leaveId" type="text" style="display:none;" class="form-control">
<input id="leaveName" name="leaveName" type="text" readonly value="" onclick="showMenu()" class="form-control"></div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1000;">
<ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
</td>
</tr>
<tr>
<td>分类名称：
</td>
<td>
<div class="col-xs-6 form-group"><input id="flowTypeName" name="flowTypeName" type="text" class="form-control"></div>
</td>
</tr>
<tr>
<td>所属模块：
</td>
<td>
<div class="col-xs-6">
<select id="moduleId" name="moduleId" class="form-control">
<option value="workflow">workflow</option>
<option value="project">project</option>
</select>
</div>
</td>
</tr>
<tr>
<td>管理者：
</td>
<td>
<div class="col-xs-6"><input id="manageAccountId" name="manageAccountId" type="hidden" class="form-control">
<input id="manageAccountName" name="manageAccountName" readonly type="text" class="form-control">
</div>
</td>
</tr>
<!-- <tr> -->
<!-- <td>所属机构： -->
<!-- </td> -->
<!-- <td> -->
<!-- <div class="col-xs-6"><input id="orgId" name="orgId" value="1" type="text" disabled="disabled" class="form-control"></div> -->
<!-- </td> -->
<!-- </tr> -->
</table>
</div>
<br>
<div align="center">
<button type="submit" class="btn btn-primary">保存</button>
<button onclick="deleteFlowType();" class="btn btn-danger">删除</button>
<button onclick="back();" class="btn btn-info">返回</button></div>
</form>
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
		 //e.preventDefault();
			
	     // Get the form instance
	     //var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     //var bv = $form.data('bootstrapValidator');
	     parent["left"].location=contextPath+ "/system/workflow/flowsort/left.jsp";
		});
});
</script>
</body>
</html>