<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>子流程属性设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css"></link>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId="<%=prcsId%>";
var childFieldFlowId="";
var form;
var zNodes;
$(function(){
	var requestUrl ="<%=contextPath %>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getPrcsChildAct.act";
			$.ajax({
	 			url:requestUrl,
	 			dataType:"json",
	 			data:{flowId:flowId,prcsId:prcsId},
	 			async:false,
	 			success:function(data){
	 				form=data;
	 				for(var name in data)
	 				{
	 					$("*[name='"+name+"']").val(form[name]);
	 				}
	 				childFieldFlowId=form['childFlow'];
	 				getWorkFlowTree();
	 			}
	 		});

			$('#cc1').combobox({   
				url:'<%=contextPath%>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getAllFieldAct.act?flowId='+flowId,   
				valueField:'id',   
				textField:'text' 
				});
			$('#cc2').combobox({   
				url:'<%=contextPath%>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getAllFieldAct.act?flowId='+childFieldFlowId,   
				valueField:'id',   
				textField:'text' 
				});  

	 });

function getWorkFlowTree()
{
	var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getWorkFlowTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			layer.msg(e.message);
		},
		success:function(data){
			zNodes=data;
			}
	});
}

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
		var deptObj = $("#childFlowName");
		deptObj.attr("value", v);
		if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
		var deptIdObj = $("#childFlow");
		deptIdObj.attr("value",vid);
		if($("#childFlow").val()=="")
		{
			$("#childFlow").val(form.childFlow);
			$("#childFlowName").val(form.formName);
		}
	}
	function showMenu() {
		var cityObj = $("#childFlowName");
		var cityOffset = $("#childFlowName").offset();
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
function createCopyStr()
{
	var p=$('#cc1').combobox('getValue');
	var c=$('#cc2').combobox('getValue');
	var tmp = p+":"+c;
	var code = $("#copyToChild").val();
	if(code.length>0)
		{
		code=code+",";
		}
	$("#copyToChild").val(code+tmp);
	}
</script>
</head>
<body style="padding:0px;">
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1000;">
<ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
</div>

<div class="panel panel-info">
   <div class="panel-heading">
      <h3 class="panel-title">
        设置子流程结点信息
      </h3>
   </div>
   <div class="panel-body">
   <form id="form1" name="form2" action="<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/updateForChildFlowAct.act">
<input type="hidden" id="" name="flowId" value="<%=flowId%>"/>
<input type="hidden" id="" name="prcsId" value="<%=prcsId%>"/>
   <table class="table table-striped">
<tr>
<td>序号：
</td>
<td><div class="col-xs-4"><input type="text" name="prcsId" id="prcsId" style="width:40px;" class="form-control" /></div>
</td>
</tr>
<tr>
<td nowrap="nowrap">步骤名称：
</td>
<td><div class="col-xs-4"><input type="text" name="prcsName" id="prcsName" class="form-control" /></div>
</td>
</tr>
<tr>
<td>步骤类型：
</td>
<td>
<div class="col-xs-4"><select id="prcsType" name="prcsType" class="form-control" >
		    <option value="1">开始节点</option>
		    <option value="2">结束节点</option>
		    <option value="3">普通节点</option>
		    <option value="6">子流程节点</option>
</select></div>
</td>
</tr>
<tr>
<td>关联流程：
</td>
<td>
<div class="col-xs-4"><input type="hidden" name="childFlow" id="childFlow" class="form-control" />
<input type="text"   name="childFlowName" id="childFlowName" class="form-control" readonly value="" onclick="showMenu();"/></div>
</td>
</tr>
<tr>
<td>强制等待：
</td>
<td>
<div class="col-xs-4"><select id="parnetWait" name="parnetWait" class="form-control" >
<option value="0">无需等待</option>
<option value="1">强制等待</option>
<option value="2">指定等待</option>
</select></div>
</td>
</tr>
<tr>
<td>数据映射:
</td>
<td>
<div class="col-xs-4" style="float: left;"><div id="cc1"></div></div>
			<div style="float: left;margin-left: 10px;">关联</div>
<div class="col-xs-4" style="float: left;"><div id="cc2"></div></div>
<a onclick="createCopyStr();" style="cursor: pointer;margin-left: 10px;">添加</a>
<br/>
<div class="col-xs-11"><textarea  style="height:60px" rows="4" id="copyToChild" name="copyToChild" readonly="readonly"  class="form-control"></textarea></div>
</td>
</tr>
<tr>
<td>附件共享:
</td>
<td>
<div class="col-xs-4"><select id="shareFlowDoc" class="form-control"  name="shareFlowDoc" >
<option value="1">共享</option>
<option value="0">不共享</option>
</select></div>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<button type="submit"  class="btn btn-info btn-lg">保存</button>
</td>
</tr>
</table>
</form>
   </div>
   </div>
</body>
</html>