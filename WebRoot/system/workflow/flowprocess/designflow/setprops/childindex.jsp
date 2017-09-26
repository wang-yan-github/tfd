<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String prcsId=request.getParameter("prcsId"); %> 
<%String flowId=request.getParameter("flowId"); %>  
<html>
<head>
<title>
</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<script type="text/javascript">
var prcsId = "<%=prcsId%>";
var flowId= "<%=flowId%>"; 
function setbasic()
{
	window["edit"].location=contextPath+"/system/workflow/flowprocess/designflow/setprops/basic.jsp?prcsId="+prcsId+"&flowId="+flowId;
	}
function setchild()
{
	window["edit"].location=contextPath+"/system/workflow/flowprocess/designflow/setprops/childnodeset.jsp?prcsId="+prcsId+"&flowId="+flowId;
	}
function setcondition()
{
	window["edit"].location=contextPath+"/system/workflow/flowprocess/designflow/setprops/condition.jsp?prcsId="+prcsId+"&flowId="+flowId;
	}
function setautouser()
{
	window["edit"].location=contextPath+"/system/workflow/flowprocess/designflow/setprops/autouser.jsp?prcsId="+prcsId+"&flowId="+flowId;
	}
function setsendto()
{
	window["edit"].location=contextPath+"/system/workflow/flowprocess/designflow/setprops/sendto.jsp?prcsId="+prcsId+"&flowId="+flowId;
	}
function doinit()
{
	$("#layout").layout({auto:true});
	}
</script>
<style>
html,body{
height: 100%;
}
html{
overflow: hidden;
}
</style>
</head>
<body style="margin:0px" onload="doinit();">
<div id="layout">
	<div layout="west" id="west" min=20 width=200 style="width:200px;" align="center">
<a href="javascript:void(0);" class="list-group-item active">操作选择</a>
<a href="javascript:void(0);" class="list-group-item"  onclick="setbasic();" >基本属性</a>
<a href="javascript:void(0);" class="list-group-item" onclick="setchild();">子流程设置</a>
<a href="javascript:void(0);" class="list-group-item" onclick="setautouser();">智能选人</a>
<a href="javascript:void(0);" class="list-group-item" onclick="setcondition();">转交条件</a>
<a href="javascript:void(0);" class="list-group-item" onclick="setsendto();">提醒设置</a>
	</div>
<div layout="center">
<iframe name="edit" width="100%" height="100%" frameborder="0" scrolling="auto" id="edit"  src="<%=contextPath%>/system/workflow/flowprocess/designflow/setprops/basic.jsp?prcsId=<%=prcsId%>&flowId=<%=flowId%>"></iframe>
	</div>
</div>
</body>
</html>