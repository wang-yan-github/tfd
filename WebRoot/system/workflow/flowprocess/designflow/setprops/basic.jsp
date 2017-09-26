<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
String type = request.getParameter("type");
%>
<html>
<head>
<title>流程设置</title>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/workflow/prcsbasic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var type="<%=type%>";
var flowId="<%=flowId%>";
var prcsId="<%=prcsId%>";
var nextPrcs=[];
if(type=="1")
	{
	layer.msg("保存成功！");
	}
function doinit(){
	var selecturl=contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveSeltctAct.act?flowId="+flowId;
	createselect("leadPrcsLeave",selecturl);
	var allPrcs;
	var url = "<%=contextPath %>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getPrcsBasicAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		data:{flowId:flowId,prcsId:prcsId},
		async:false,
		success:function(result){
			var data=result[0];
			var nextPrcsStr=data.nextPrcs;
			allPrcs=data.prcsAll;
			if(nextPrcsStr!=null && nextPrcsStr.indexOf(",")>0)
				{
				nextPrcs = nextPrcsStr.split(",");
				}else
					{
					nextPrcs[0]= nextPrcsStr;
					}
			for(var name in data){
				$("#"+name).val(data[name]);
			}
			if(data["prcsId"]=="1")
				{
				$("#prcsId").attr("readonly","readonly");
				}
		}
	});
	$("#nextPrcs").html(createAllNextPrcs(allPrcs));
	for(var i = 0; nextPrcs.length>i;i++)
	{
		if(!(nextPrcs[i]=="null"||nextPrcs[i]==null||nextPrcs[i]==""))
			{$("#id_"+nextPrcs[i]).get(0).checked=true;}
	}
}
</script>
</head>
<body onload="doinit()">
<form id="form1" method="post" name="form1" action="<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/updatePrcsBasicAct.act">
<input type="hidden" id="flowId" name="flowId" value="<%=flowId%>">
 <div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">基本属性设置</span>
</div>
 <table class="table table-striped  table-condensed" >
   <tr>
    <td width="150px">步骤序号：</td>
    <td>
       <div class="col-xs-3"><input type="text" name="prcsId" id="prcsId" class="form-control" /></div>
    </td>
   </tr>
   <tr>
    <td>节点类型：</td>
    <td>
	    <div class="col-xs-8"><select id="prcsType" name="prcsType" class="form-control">
		    <option value="1">开始节点</option>
		    <option value="2">结束节点</option>
		    <option value="3">普通节点</option>
		    <option value="6">子流程节点</option>
	    </select></div>
    </td>
   </tr>
   <tr>
    <td>步骤名称：</td>
    <td>
      <div class="col-xs-8">  <input type="text" name="prcsName" id="prcsName"  class="form-control"/></div>
    </td>
   </tr>
    <tr>
    <td>行政级别：</td>
    <td>
       	<div class="col-xs-8">
       	<select name="leadPrcsLeave"  id="leadPrcsLeave" class="form-control">
		</select>
			</div>
    </td>
   </tr>
   <tr>
    <td>强制并发：</td>
    <td>
       <div class="col-xs-8"> <select id="forceParallel" name="forceParallel" class="form-control">
       	<option value="1">强制并发</option>
       	<option value="0">允许并发</option>
       	<option value="2">禁止并发</option>
       </select></div>
    </td>
   </tr>
   <tr>
    <td>强制合并：</td>
    <td>
        <div class="col-xs-8"> <select id="forceAggregation" name="forceAggregation" class="form-control">
       	<option value="1">强制合并</option>
       	<option value="0">非强制合并</option>
       </select></div>
    </td>
   </tr>
     <tr>
    <td>回退设置：</td>
    <td>
       <div class="col-xs-8">
		<select class="form-control" id="goBack" name="goBack">
			<option value="0">禁止回退</option>
			<option value="1">回退上一步</option>
			<option value="2">回退任意一步</option>
		</select>
</div>
    </td>
   </tr>
   <tr>
    <td>下一步骤：</td>
    <td>
    <div id="nextPrcs">
    </div>
    </td>
   </tr>
   <tr>
   <td>
   是否可关注：
   </td>
   <td><div class="col-xs-8"><select id="follow" name="follow" class="form-control">
   <option value="0">否</option>
   <option value="1">是</option>
   </select></div>
   </td>
   </tr>
     <tr>
    <td>超时时限：</td>
    <td>
       <div class="col-xs-8"><input  type="text" name="passTime" id="passTime"  value="0"   class="form-control" /></div>小时
    </td>
   </tr>
   <tr>
    <td>节点说明：</td>
    <td>
       <div class="col-xs-8"><textarea name="memo" id="memo"  rows="2" class="form-control"></textarea></div>
    </td>
   </tr>
</table>
   <div align="center"> <button type="submit" class="btn btn-primary">保存</button></div>
</form>

</body>
</html>