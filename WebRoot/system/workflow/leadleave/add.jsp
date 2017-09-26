<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String flowId=request.getParameter("flowTypeId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>行政级别类型添加</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript">
var flowId = "<%=flowId%>";
$(function(){
	var selecturl=contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveSeltctAct.act?flowId="+flowId;
	createselect("passLeaveNext",selecturl);
	$("#flowId").val(flowId);
	
	$("#myTable").datagrid({
		queryParams:{flowId:flowId},
	     collapsible: true,
	     url: "<%=contextPath %>/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/getFlowPassLeaveListAct.act",
	     method: 'POST',
	     sortName: 'id',
	     loadMsg: "数据加载中...",
	     pagination:true,
	     striped: true,
	     singleSelect:true,  
	     remoteSort:true,  
	     columns:[[
	         {title: '序号', field: 'ID', width: 100, align: 'center',sortable:true},
	         {title: '行政级别编号', field: 'PASS_LEAVE_ID', width: 200, align: 'center',sortable:true},
	         {title: '行政级别名称', field: 'PASS_LEAVE_NAME', width: 200, align: 'center',sortable:true},
	         {title: '下一行政级别', field: 'PASS_LEAVE_NEXT', width: 200, align: 'center',sortable:true},
	         {title: '关联流程', field: 'FLOW_NAME', width: 200, align: 'center',sortable:true},
	         {title: '操作', field: 'OPT', width: 150, align: 'center',sortable:true}
	     ]]
	 });
	  
	 var p = $('#myTable').datagrid('getPager');  
	     $(p).pagination({  
	     pageSize: 5,//每页显示的记录条数，默认为5  
	     pageList: [5, 10],//可以设置每页记录条数的列表  
	     beforePageText: '第',//页数文本框前显示的汉字  
	     afterPageText: '页    共 {pages} 页',  
	     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	 });
});

function save()
{
	  $.ajax({
          url:contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/addFlowPassLeaveAct.act",
          dataType:"text",
          data:{
        	  flowId:$("#flowId").val(),
        	  passLeaveId:$("#passLeaveId").val(),
        	  passLeaveName:$("#passLeaveName").val(),
        	  passLeaveNext:$("#passLeaveNext ").val()
        	  },
          async:false,
          error:function(e){
              alert(e.message);
          },
          success:function(data){
        	  if(data>0)
        		  {
        		  layer.msg("添加成功！");
        		  window.location.reload();
        		  }
          }
      });
	
	}
function del(flowPassleaveId)
{
	var paramData={flowPassleaveId:flowPassleaveId};
	 $.ajax({
         url:contextPath+"/tfd/system/workflow/flowpassleave/act/FlowPassLeaveAct/delPassLeaveAct.act",
         dataType:"text",
         data:paramData,
         async:false,
         error:function(e){
             alert(e.message);
         },
         success:function(data){
       	  if(data>0)
       		  {
       		layer.msg("删除成功！");
       		  window.location.reload();
       		  }
         }
     });
}

function edit(flowPassLeaveId)
{
	parent["edit"].location=contextPath+"/system/workflow/leadleave/edit.jsp?flowPassLeaveId="+flowPassLeaveId+"&flowId="+flowId;
	}

</script>
</head>
<body  style="margin-top: 10px;">
<div  align="center" >
<div style="width: 80%">
<input type="hidden" name="flowId" id="flowId" class="form-control"/>
<div class="panel-body">
	<table class="table table-striped table-condensed" >
	<tr>
	<td width="150px">行政编号:</td>
	<td><div class="col-xs-6"><input type="text" name="passLeaveId" id="passLeaveId"  class="form-control"/></div><b style="color: red;line-height: 30px;">*请用数值,数值越小,级别越低</b></td>
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
	</table>
	</div>
	</div>
	</br>
		<div align="center">
			<button type="button" class="btn btn-primary"  onclick="save();">保存</button>
		</div>
</br>
<div>
<div id="myTable" name="myTable"></div>
</div>


</div>



</body>

</html>