<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建工作流</title>
<script type="text/javascript">
$(function(){
	var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/getAllWorkAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			createDiv(data);
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
		 }else{
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

function createDiv(data)
{
	var flowId ="";
	var flowName="";
	var html="";
	for(var i=0;data.length>i;i++)
		{
			flowId = data[i].id;
			flowName=data[i].flowName;
			html="<tr>\n"+
			"<td >"+flowName+"</td>"+
			"<td align=\"center\"><button class=\"btn btn-default\" onclick=\"goFormView('"+flowId+"')\">原始表单</button>"+
			"<button class=\"btn btn-default\" onclick=\"goFlowView('"+flowId+"'); \">设计流程图</button></td>"+
			"<td align=\"center\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"creatWork('"+flowId+"');\">发起流程</button>&nbsp<button type=\"button\" class=\"btn btn-warning\" onclick=\"quickCreatWork('"+flowId+"');\">快速新建</button></td>"+
			"</tr>";
		}
	$("#tbody").html(html);
}
</script>
</head>
<body style="margin-left:10px;">
<div id="head" name="head" style="margin: 10px;">
<img src="<%=imgPath %>/workflow/newwork.png"  style="width: 30px;height:30px;"></img>
<b style="font-size:20px;">全部工作</b>
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