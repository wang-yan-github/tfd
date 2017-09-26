<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<html>
<head>
<title>工作流办理权限设置
</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId=<%=prcsId%>;
$(function(){
	 var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/getUserPrivAct.act";
		$.ajax({
			url:requestUrl,
			type:"POST",
			dataType:"json",
			data:{flowId:flowId,prcsId:prcsId},
			async:false,
			success:function(data){
				for(var name in data)
					{
					var inputEle = $("*[name='" + name + "']");
					inputEle.val(data[name]);
					}
			}
		});
	});
function autousersave()
{
	var userId = $("#userId").val();
	var deptId = $("#deptId").val();
	var userPrivId = $("#userPrivId").val();
    var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/setUserPrivAct.act";
	$.ajax({
		url:requestUrl,
		type:"POST",
		dataType:"text",
		data:{flowId:flowId,prcsId:prcsId,userId:userId,deptId:deptId,userPrivId:userPrivId},
		async:false,
		success:function(data){
			if(data=="OK")
				{
				layer.msg("保存成功!");				
				}else
					{
					layer.msg("保存失败!");					
					}
		}
	});
}
</script>
</head>
<body>
 <div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">办理权限设置</span>
</div>
 <table class="table table-striped  table-condensed" >
   <tr>
<td width="150px" >人员：</td>
<td>
<div class="col-xs-8" style="float: left;">
<textarea rows="2" cols="40" name="userId" id="userId" style="display: none;"></textarea>
<textarea rows="2" cols="40" name="userName" id="userName" class="form-control" readonly></textarea>
</div>
<a onclick="userinit(['userId','userName']);" style="cursor: pointer;line-height: 80px;">添加</a>
</td>
</tr>
<tr>
<td width="150px" >部门：</td>
<td>
<div class="col-xs-8" >
<textarea rows="2" cols="40" name="deptId" id="deptId" style="display: none;"></textarea>
<textarea rows="2" cols="40" name="deptName" id="deptName" class="form-control" readonly></textarea>
</div>
<a onclick="deptinit(['deptId','deptName']);" style="cursor: pointer;line-height: 80px;">添加</a>
</td>
</tr>
<tr>
<td width="150px" >角色：</td>
<td><div class="col-xs-8">
<textarea rows="2" cols="40" name="userPrivId" id="userPrivId" style="display: none;"></textarea>
<textarea rows="2" cols="40" name="userPrivName" id="userPrivName" class="form-control" readonly></textarea>
</div>
<a onclick="privinit(['userPrivId','userPrivName']);" style="cursor: pointer;line-height: 80px;">添加</a>
</td>
</tr>
</table>
</br>
<div align="center"><button onclick="autousersave();" class="btn btn-primary">保存</button></div>
<div id="modaldialog"></div>
</body>
</html>