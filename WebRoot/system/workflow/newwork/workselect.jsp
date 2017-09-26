<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/system/returnapi/api.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>流程选择</title>
<script type="text/javascript">
$(function(){
	var workselect=parent.$("#workselect").val();
	var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getWorkFlowByFlowNameAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{workselect:workselect},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var tmpHtml="";
			for(var i=0;data.length>i;i++)
				{
				tmpHtml+="<tr>\n"+
				"<td >"+data[i].flowName+"</td>"+
				"<td align=\"center\"><button class=\"btn btn-default\" onclick=\"goFormView('"+data[i].flowTypeId+"')\">原始表单</button>"+
				"<button class=\"btn btn-default\" onclick=\"goFlowView('"+data[i].flowTypeId+"'); \">设计流程图</button></td>"+
				"<td align=\"center\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"creatWork('"+data[i].flowTypeId+"');\">发起流程</button>&nbsp<button type=\"button\" class=\"btn btn-warning\" onclick=\"quickCreatWork('"+data[i].flowTypeId+"');\">快速新建</button></td>"+
				"</tr>";
				}
			$("#tbody").html(tmpHtml);
			}
	});
});

function goFlowView(flowId)
{
	window.open(contextPath+"/system/workflow/flowgraph/designgraph/index.jsp?flowId="+flowId);
	}
function goFormView(flowId)
{
	var leavePath ="";
	var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getPreviewPathAct.act";
	var paramData={flowTypeId:flowId};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		success:function(data){
			leavePath=data;
		 }
	});
	window.open(contextPath+"/system/workflow/dowork/"+leavePath);
	}
	
function quickCreatWork(flowId)
{
	var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/createWorkAct.act";
	var paramData={flowId:flowId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		success:function(data){
			var url=contextPath+data.url;
			goUrl(data.flowId,data.title,url)
		 }
	});
	}
	
function goUrl(flowId,title,url)
{
		var sysFrame=new SysFrame();
		 if (sysFrame.tabs('exists', title)){ 
			 sysFrame.tabs('select', title); 
		 }else
			 {
			 	sysFrame.tabs('add',{
					title: title,
					url:url
				});
		}
}	
	
function creatWork(flowId)
{
location.href="new.jsp?flowId="+flowId;
	}
	
</script>
</head>
<body>
<body style="margin-left:10px;">
<div id="head" name="head" style="margin: 10px;">
<img src="<%=imgPath %>/workflow/newwork.png"  style="width: 30px;height:30px;"></img>
<b style="font-size:20px;">查询结果</b>
</div>
   <table class="table table-striped">
<tr>
<td>流程名称</td>
<td align="center">表单&流程图 </td>
<td align="center">操作</td>
</tr>
<tbody id="tbody" name="tbody">

</tbody>
</table>
</div>
</table>
</body>
</html>