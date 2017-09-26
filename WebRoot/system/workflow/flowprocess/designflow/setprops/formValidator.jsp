<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<html>
<head>
<title>数据验证</title>
 <script  type="text/javascript" src="<%=contextPath%>/system/workflow/flowprocess/designflow/js/formValidator.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId="<%=prcsId%>";
$(function (){ 
	var url = "<%=contextPath %>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getFieldByFormIdListAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		data:{flowId:flowId,prcsId:prcsId},
		async:false,
		success:function(data){
			createmustfield(data);
		}
	});
});

function save()
{
	var mustfield=null;
	var hiddenfield=null;
	var arraytemp=[]	;	
	$("input[name='mustfield']:checked").each(function(){
		arraytemp.push($(this).val());
	});
	mustfield=arraytemp.join(",");
	
	arraytemp=[]	;
	$("input[name='hiddenfield']:checked").each(function(){
		arraytemp.push($(this).val());
	});
	hiddenfield=arraytemp.join(",");
	
	
	var url = "<%=contextPath %>/tfd/system/workflow/flowprocess/act/FlowProcessAct/updateValidatorAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		data:{flowId:flowId,prcsId:prcsId,mustfield:mustfield,hiddenfield:hiddenfield},
		async:false,
		success:function(data){
			if(data=="1")
				{
				layer.msg("保存成功！");
				}
		}
	});
	}
</script>
</head>
<input type="hidden" id="flowId" name="flowId" value="<%=flowId%>">
<div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">数据验证设置</span>
</div>
 <table class="table table-striped  table-condensed" >
   <tr>
   <td width="150px;">必填字段：</td>
   <td><div id="createmustfield" name="createmustfield"></div></td>
   </tr>
   <tr>
   <td>隐藏字段：</td>
   <td><div id="createhiddenfield" name="createhiddenfield"></td>
   </tr>
   </table>
   <div align="center"><button type="button" class="btn btn-primary" onclick="save();">保存</button></div>
</body>
</html>