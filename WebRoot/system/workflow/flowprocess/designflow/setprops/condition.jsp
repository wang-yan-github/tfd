<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<html>
<head>
<title>
</title>
<script src="<%=contextPath%>/system/jsall/json2.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId=<%=prcsId%>;
var nextPrcsList;
function doInit(){
	//获取步骤信息
	var fpdata;
	var fp;
	var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/getAllNextPrcsJson.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{flowId:flowId,prcsId:prcsId},
			async:false,
			success:function(data){
				fpdata = data;
			}
		});
	if(fpdata){
		nextPrcsList = fpdata.nextPrcs;
		fp=fpdata.prcs;
		//渲染步骤列表
		for(var i=0;i<nextPrcsList.length;i++){
			var data = nextPrcsList[i];
			var html = "<div class=\"panel panel-default\">";
			html += "<div class=\"panel-heading\">路径：[步骤"+fp.prcsId+"]"+fp.prcsName+"&nbsp;<i class=\"glyphicon glyphicon-arrow-right\"></i>&nbsp;[步骤"+data.prcsId+"]"+data.prcsName+"&nbsp;&nbsp;<button onclick=\"addCondition('c"+data.prcsId+"')\" class=\"btn-mini btn-warning\" type=\"button\">添加条件</button></div>";
			html+="<div class=\"panel-body\" style=\"padding:5px;\">";
			html+="<div id=\"c"+data.prcsId+"\" style=\"min-height:20px\"></div>";
// 			html+="<b>路径描述：</b>";
// 			html+="<div style=\"margin-top:5px;\"><input style=\"width:100%;\" id=\"label"+data.prcsId+"\" placeholder=\"为路径添加一个个性化标签说明\" type=\"text\" class=\"easyui-textbox\" title=\"为路径添加一个个性化标签说明\"></div>";
// 			html+="<br/>";
			html+="<b>条件表达式：</b>";
			html+="<div><input class=\"form-control\" id=\"ci"+data.prcsId+
			"\" placeholder=\"请输入条件表达式，例如   {1} and {2} or {3}\"></div>";
			html+="</div>";
			html+="</div>";
			html+="</div>";
			$(html).appendTo($("#renderBody"));
		}

		//渲染条件数据
		var prcsConditionJson;
		var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/getPrcsConditionAct.act";
		$.ajax({
				url:requestUrl,
				dataType:"json",
				data:{flowId:flowId,prcsId:prcsId},
				async:false,
				success:function(data){
					prcsConditionJson = data;
				}
			});
		
		
		
		var conditions =prcsConditionJson;
		if(conditions==null || !conditions){
			conditions = [];
		}
		
		for(var i=0;i<conditions.length;i++){
			var prcsTo = conditions[i].prcsTo;
			var condition = conditions[i].condition;
			var exp = conditions[i].exp;
			var label = conditions[i].label;
			for(var j=0;j<condition.length;j++){
				addCondition0("c"+prcsTo,condition[j]);
			}
			$("#ci"+prcsTo).attr("value",exp);
			$("#label"+prcsTo).attr("value",label);
		}
	}

	
	//获取对应表单字段
	var url = contextPath+"/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getTitleAndFieldNameByFlowIdAct.act";
	var items;
	$.ajax({
			url:url,
			dataType:"json",
			data:{flowId:flowId},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				items=data;
			}
		});
	var html = "<optgroup label=\"表单字段\">";
	for(var i=0;i<items.length;i++){
		var data = items[i];
		html+="<option value='"+data.fieldName+"'>"+data.title+"</option>";
	}
	html +="</optgroup>";
	html +="<optgroup label=\"流程实例信息\">";
	html +="<option value=\"[发起人姓名]\">[发起人姓名]</option>";
	html +="<option value=\"[发起人帐号]\">[发起人帐号]</option>";
	html +="<option value=\"[发起人部门]\">[发起人部门]</option>";
	html +="<option value=\"[发起人角色]\">[发起人角色]</option>";
	html +="<option value=\"[主办人会签意见]\">[主办人会签意见]</option>";
	html +="<option value=\"[经办人会签意见]\">[经办人会签意见]</option>";
	html +="<option value=\"[公共附件名称]\">[公共附件名称]</option>";
	html +="<option value=\"[公共附件个数]\">[公共附件个数]</option>";
	html +="<option value=\"[当前步骤序号]\">[当前步骤序号]</option>";
	html +="<option value=\"[当前步骤自增ID号]\">[当前步骤自增ID号]</option>";
	html +="<option value=\"[当前步骤名称]\">[当前步骤名称]</option>";
	html +="<option value=\"[当前主办人姓名]\">[当前主办人姓名]</option>";
	html +="<option value=\"[当前主办人帐号]\">[当前主办人帐号]</option>";
	html +="<option value=\"[当前主办人角色]\">[当前主办人角色]</option>";
	html +="<option value=\"[当前主办人辅助角色]\">[当前主办人辅助角色]</option>";
	html +="<option value=\"[当前主办人部门]\">[当前主办人部门]</option>";
	html +="<option value=\"[当前主办人上级部门]\">[当前主办人上级部门]</option>";
	html +="</optgroup>";
	html +="<optgroup label=\"流程变量\">";
	html +="</optgroup>";
	$("#fields").append(html);
}

function addCondition(div){
	var field = $("#fields option:selected");
	var oper = $("#oper option:selected");
	var val = $("#value");
	var content = "{"+field.html()+"} "+oper.html()+" {"+val.val()+"}";
	var model = "{'fieldName':'"+field.val()+"','name':'"+field.html()+"','value':'"+val.val()+"','oper':'"+oper.html()+"'}";
	addCondition0(div,eval("("+model+")"));
}

function addCondition0(div,model){
	var order = getOrder($("#"+div));
	$("#"+div).append("<div model='"+JSON.stringify(model)+"'><span>{"+(order+1)+"}</span>'"+model.name+"'&nbsp"+model.oper+"&nbsp;'"+model.value+"'<a href='javascript:void(0)' onclick='removeConditionItem(this,\""+div+"\")'>[x]</a></div>");
}

function getOrder(container){
	return $(container).children().length;
}

function recountOrder(div){
	$("#"+div).children().each(function(i,obj){
		$(obj).find("span:first").html("{"+(i+1)+"}");
	});
}

function removeConditionItem(cur,div){
	$(cur).parent().remove();
	recountOrder(div);
}

//提交
function commit(){
	var para = {};
	para["prcsId"] = prcsId;
	//组装请求数据
	para["requestDataModel"] = compositRequestData();
	para["flowId"] = flowId;
	var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/updatePrcsConditionAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:para,
			async:false,
			success:function(data){
				if(data.STATUS=="OK")
					{
					layer.msg("保存成功！");
					}
			}
		});
	
}

//组装请求数据
function compositRequestData(){
	var arr = new Array();
	for(var i=0;i<nextPrcsList.length;i++){
		var data = nextPrcsList[i];
		var item = {};
		item["prcsTo"]=data.prcsId;
		var conditionArr = new Array();
		var conditionItems = $("#c"+data.prcsId).children();
		for(var j=0;j<conditionItems.length;j++){
			var obj = eval("("+conditionItems[j].getAttribute("model")+")");
			conditionArr.push(obj);
		}
		item["condition"]=conditionArr;
		item["exp"] = $("#ci"+data.prcsId).val();
		item["label"] = $("#label"+data.prcsId).val();
		arr.push(item);
	}
	return JSON.stringify(arr);
}

</script>

</head>
<body onload="doInit()" id="body">
 <div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">流程条件设置</span>
</div>
 <table class="table table-striped  table-condensed" >
<tr>
<td width="100px">条件设置：</td>
<td>
<select style="float: left;" id="fields" class="form-control"></select>
</td>
<td>
	<select id="oper" class="form-control" >
			<option value="1">等于</option>
			<option value="2">不等于</option>
			<option value="3">大于</option>
			<option value="4">大于等于</option>
			<option value="5">小于</option>
			<option value="6">小于等于</option>
			<option value="7">以字符开头</option>
			<option value="8">以字符结尾</option>
			<option value="9">包含</option>
			<option value="10">不包含</option>
		</select>
</td>
<td>
<input id="value" class="form-control"/>
</td>
</tr>
<tr>
<td colspan="4">
<!-- 渲染主体 -->
<div id="renderBody" class="ui-layout-center" style="overflow:auto"></div>
</td>
</tr>
</table>
<div align="center"><button onclick="commit();" class="btn btn btn-primary">保存</button></div>
<br>
<br>
</body>
</html>