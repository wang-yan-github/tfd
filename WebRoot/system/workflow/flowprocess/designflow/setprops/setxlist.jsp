<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
String childTable=request.getParameter("childTable");
%>
<html>
<head>
<title>列表控件设置
</title>
<script src="<%=contextPath%>/system/jsall/workflow/setxlist.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var childTable="<%=childTable%>";
var prcsId="<%=prcsId%>";
$(function (){ 
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getXlistAct.act";
	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{childTable:childTable,flowId:flowId},
 		async:false,
 		error:function(e){
 			layer.msg("查询出错！");
 			},
 		success:function(data){
 			createXlistTable(data);
 		}
 	}); 
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getXlistAct.act";
	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{prcsId:prcsId,flowId:flowId,table:childTable},
 		async:false,
 		success:function(data){
 			var writer=","+data.writerfield+",";
 			var read=","+data.read+",";
 			var print=","+data.print+",";
 			var a = [];
 			$("input[name^='writer']").each(function(i, o){
 			    a[i] = $(o).val();
 			    if(writer.indexOf(","+a[i]+",")>=0)
 			    	{
 			    	$(this).attr("checked",true);
 			    	}
 			});
 			$("input[name^='read']").each(function(i, o){
 			    a[i] = $(o).val();
 			    if(read.indexOf(","+a[i]+",")>=0)
 			    	{
 			    	$(this).attr("checked",true);
 			    	}
 			});
 			$("input[name^='print']").each(function(i, o){
 			    a[i] = $(o).val();
 			    if(print.indexOf(","+a[i]+",")>=0)
 			    	{
 			    	$(this).attr("checked",true);
 			    	}
 			});
 		}
 	}); 
});
</script>
</head>
<body>
<div id="xlist"></div>
<div align="center">
<button class="btn btn-primary" onclick="saveXlistSet('<%=flowId%>','<%=prcsId%>',getSetData('<%=childTable %>'))">保存</button>
<button class="btn btn-warning" onclick="javascript:history.go(-1);return false;">返回</button>
</div>
</body>
</html>

