<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>智能选人</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId="<%=prcsId%>";
function save(){
	var autoUserModle={};
	autoUserModle.flowId=flowId;
	autoUserModle.prcsId=prcsId;
	autoUserModle.sPrcsAuto=$("#sPrcsAuto").val();
	autoUserModle.autoUserType=$('#autoUserType').combobox('getValue');
	autoUserModle.autoUserRule=$("#autoUserRule").val();
	autoUserModle.autoFormField=$("#autoFormField").combobox('getValue');
	autoUserModle.autoDeptId=$("#autoDeptId").val();
	autoUserModle.defUserId=$("#defUserId").val();
            var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/setAutoUserTypeAct.act";
        	$.ajax({
        		url:requestUrl,
        		dataType:"text",
        		data:autoUserModle,
        		async:false,
        		success:function(data){
        			if(data=="OK")
        				{
        				layer.msg("保存成功");
        				}else
        					{
        					layer.msg("保存失败");
        					}
        			
        		}
        	});
};
$(function (){ 
  	//初始化页面
  	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getAutoUserTypeJsonAct.act";
 	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{flowId:flowId,prcsId:prcsId},
 		async:false,
 		success:function(data){
 			var val = data.autoUserRule;
 			if(val=="3")
 			{
 				$("#setDeptDiv").show();
 			}else
 				{
 				$("#setDeptDiv").hide();
 				}
 			if(val=="5")
 				{
 					$("#formFieldDiv").show();
 				}else
 					{
 					$("#formFieldDiv").hide();
 					}
 			if(val=="6")
 			{
 				$("#formFieldIdDiv").show();
 			}else
 				{
 				$("#formFieldIdDiv").hide();
 				}
 			if(val=="7")
 			{
 				$("#defUserDiv").show();
 			}else
 				{
 				$("#defUserDiv").hide();
 				}
 			if(val=="8")
 			{
 				$("#autoPrcsIdDiv").show();
 			}else
 				{
 				$("#autoPrcsIdDiv").hide();
 				}
 			$("#sPrcsAuto").val(data.sPrcsAuto);
 			if(data.autoUserTypePrcsId!=""&&data.autoUserTypePrcsId!=null)
 				{
 				$('#autoUserType').combobox('setValue',data.autoUserTypePrcsId);
 				}
 			$("#autoUserRule").val(data.autoUserRule);
 			if(data.autoFormField!=""&&data.autoFormField!=null)
 				{
 				$("#formFieldDiv").show();
 				$("#autoFormField").combobox('setValue',data.autoFormField);
 				}
 			if(data.autoDeptId!=""&&data.autoDeptId!=null)
				{
				$("#autoDeptIdDiv").show();
				$("#autoDeptId").val(data.autoDeptId);
				$("#autoDeptName").val(data.autoDeptName);
				}
 			if(data.defUserId!=""&&data.defUserId!=null)
				{
				$("#defUserIdDiv").show();
				$("#defUserId").val(data.defUserId);
				$("#defUserName").val(data.defUserName);
				}
 			if(data.autoUserType!=""&&data.autoUserType!=null)
			{
			$("#autoPrcsIdDiv").show();
			$("#autoUserType").combobox('setValue',data.autoUserType);
			}
 		}
 	});
});
function show()
{
	var val = $("#autoUserRule").children('option:selected').val();
	if(val=="3")
	{
		$("#setDeptDiv").show();
	}else
		{
		$("#setDeptDiv").hide();
		}
	if(val=="5")
		{
			$("#formFieldDiv").show();
		}else
			{
			$("#formFieldDiv").hide();
			}
	if(val=="6")
	{
		$("#formFieldIdDiv").show();
	}else
		{
		$("#formFieldIdDiv").hide();
		}
	if(val=="7")
	{
		$("#defUserDiv").show();
	}else
		{
		$("#defUserDiv").hide();
		}
	if(val=="8")
	{
		$("#autoPrcsIdDiv").show();
	}else
		{
		$("#autoPrcsIdDiv").hide();
		}
	
}
</script>
</head>
<body>
 <div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">智能选人设置</span>
</div>
 <table class="table table-striped  table-condensed" >  
<tr>
	<td  style="width: 150px;">备选人员过滤：</td>
	<td>
					<div class="col-xs-6"><select name="sPrcsAuto" id="sPrcsAuto" class="form-control">
						<option value="0">不过滤</option>
						<option value="1">本部门经办人员</option>
						<option value="2">上级部门经办人员</option>
					</select></div>
	</td>
	</tr>
	<tr>
	<td>经办人筛选规则：</td>
	<td>
	<div class="col-xs-6"><select name="autoUserRule" id="autoUserRule" onchange="show();" class="form-control">
						<option value="0">不选择</option>
						<option value="1">本部门经理</option>
						<option value="2">上级部门领导</option>
						<option value="3">指定部门领导</option>
						<option value="4">上级分管领导</option>
						<option value="5">表单字段</option>
						<option value="6">表单字段ID</option>
						<option value="7">默认人员</option>
						<option value="8">指定步骤经办人</option>
	</select></div>
</td>
</tr>
<tr id="setDeptDiv">
<td>指定部门:</td>
<td>
<div class="col-xs-6"><input type="hidden" id="autoDeptId" name="autoDeptId"/>
<input type="text" id="autoDeptName" name="autoDeptName" class="form-control"/></div>
<a data-toggle="modal"  data-target="#myModal" onclick="deptinit(['autoDeptId','autoDeptName']);" style="cursor: pointer;">选择</a>
</td>
</tr>
<tr id="formFieldDiv">
<td>表单字段:</td>
<td>
      	<div class="col-xs-6"><input class="easyui-combobox form-control"  style="height: 34px;width: 100%;" name="autoFormField" id="autoFormField"
			data-options="
					url:'<%=contextPath%>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getTitleAndFieldNameByFlowIdAct.act?flowId=<%=flowId %>',
					method:'get',
					valueField:'fieldName',
					textField:'title',
					panelHeight:'auto'
			"/></div>
</td>
</tr>
	<tr id="autoPrcsIdDiv">
	<td>参照步骤：</td>
	<td>
       	<div class="col-xs-6"><input class="easyui-combobox form-control"  style="height: 34px;width: 100%;" name="autoUserType" id="autoUserType"
			data-options="
					url:'<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getFlowProcessSelsectAct.act?flowId=<%=flowId %>',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto'
			"/></div>
	</td>
	</tr>
<tr id="formFieldIdDiv">
<td>表单字段ID:</td>
<td><div class="col-xs-6"><select class="form-control">
<option>22</option>
</select></div></td>
</tr>
<tr id="defUserDiv">
<td>默认人员:</td>
<td>
<div class="col-xs-6"><input type="hidden" id="defUserId" name="defUserId"/>
<input type="text" id="defUserName"  name="defUserName"  class="form-control"/></div>
<a data-toggle="modal"  data-target="#myModal" onclick="userinit(['defUserId','defUserName']);" style="cursor: pointer;">选择</a>
</td>
</tr>
</table>
<div align="center"><button onclick="save();" class="btn btn btn-primary">保存</button></div>
<div id="modaldialog"></div>
</body>
</html>