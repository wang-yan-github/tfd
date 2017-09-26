<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowPassLeaveId=request.getParameter("flowPassLeaveId"); 
String flowId = request.getParameter("flowId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>行政级别类型设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript">
var flowPassLeaveId='<%=flowPassLeaveId%>';
var flowId ="<%=flowId%>";
$(function(){
	var selecturl=contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveSeltctAct.act?flowId="+flowId;
	createselect("passLeaveNext",selecturl);
	var requestUrl=contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveAct.act";
	var paramData={flowPassLeaveId:flowPassLeaveId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			for(var name in data){
				$("#"+name).val(data[name]);
			}
		}
	});
});

function update()
{
	  $.ajax({
          url:contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/updatePassLeaveAct.act",
          dataType:"text",
          data:{
        	  flowPassLeaveId:flowPassLeaveId,
        	  flowId:flowId,
        	  passLeaveId:$("#passLeaveId").val(),
        	  passLeaveName:$("#passLeaveName").val(),
        	  passLeaveNext:$("#passLeaveNext").val()
        	  },
          async:false,
          error:function(e){
              alert(e.message);
          },
          success:function(data){
        	  if(data>0)
        		  {
        		  layer.msg("修改成功！");
        		  }
          }
      });
	}
</script>
</head>
<body>
<body  style="margin-top: 10px;">
<div  align="center" >
<div style="width: 80%">
<input type="hidden" name="flowId" id="flowId" class="form-control"/>
<div class="panel-body">
	<table class="table table-striped table-condensed" >
<tr>
<td width="200px">行政编号:</td>
<td><div class="col-xs-6"><input type="text" name="passLeaveId" id="passLeaveId"  class="form-control"/><a style="color: red;">*请用数值,数值越小,级别越低</a></div></td>
</tr>
<tr>
<td>行政级别名称:</td>
<td><div class="col-xs-6"><input type="text" name="passLeaveName" id="passLeaveName" class="form-control"/></div></td>
</tr>
<tr>
<td>下一行政级别:</td>
<td>
	<div class="col-xs-6">
		<select name="passLeaveNext"  id="passLeaveNext" class="form-control">
		</select>
	</div>
</td>
</tr>
<tr>
<td>关流程:</td>
<td><div class="col-xs-6">
<input type="text" name="flowName" id="flowName" readonly value="" onclick="showMenu();" class="form-control"/></div>
</td>
</tr>
</table>
   </div>
</br>
		<div align="center">
			<button type="button" class="btn btn-primary"  onclick="update();">保存</button>
			<button type="button" class="btn btn-primary"  onclick="javascript:history.go(-1);">返回</button>
		</div>
   </div>

</body>
</html>