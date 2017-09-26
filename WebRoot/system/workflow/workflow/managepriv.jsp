<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String flowTypeId=request.getParameter("flowTypeId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作权限设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/managepriv.css"></link>

<script type="text/javascript">
var flowTypeId="<%=flowTypeId%>";
var formData;
function back()
{
	history.go(-1);
	}
function doint()
{
	var requestUrl ="<%=contextPath%>/tfd/system/workflow/workflow/act/WorkFlowAct/getWorkFlowManagePrivAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{flowTypeId:flowTypeId},
			async:false,
			success:function(data){
				formData=data;
					$("#userName1").html(data.allQueryUser);
					$("#userName2").html(data.deptQueryUser);
					$("#userName3").html(data.otherDeptQueryUser);
					$("#userName4").html(data.leaveQueryUser);
					$("#userName5").html(data.allManageUser);
					$("#userName6").html(data.deptManageUser);
					$("#userName7").html(data.otherDeptManageUser);
			}
		});
	
	}
function save()
{
	var accountId=$("#accountId").val();
	var privType=$("#privType").val();
	var requestUrl ="<%=contextPath%>/tfd/system/workflow/workflow/act/WorkFlowAct/setWorkFlowManagePrivAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"text",
			data:{flowTypeId:flowTypeId,privType:privType,accountId:accountId},
			async:false,
			success:function(data){
				if(data=="OK")
					{
					layer.msg("设置成功!");
					 doint();
					}
			}
		});
	}
	function opt(type)
	{
		if(type=="1")
			{
				$("#privType").val("1");
				$("#accountId").val(formData.allQueryAccountId);
				$("#userName").val(formData.allQueryUser);
			}else if(type=="2")
			{
				$("#privType").val("2");
				$("#accountId").val(formData.deptQueryAccountId);
				$("#userName").val(formData.deptQueryUser);
			}else if(type=="3")
			{
				$("#privType").val("3");
				$("#accountId").val(formData.otherDeptQueryAccountId);
				$("#userName").val(formData.otherDeptQueryUser);
			}
			else if(type=="4")
			{
				$("#privType").val("4");
				$("#accountId").val(formData.leaveQueryAccountId);
				$("#userName").val(formData.leaveQueryUser);
			}
			else if(type=="5")
			{
				$("#privType").val("5");
				$("#accountId").val(formData.allManageAccountId);
				$("#userName").val(formData.allManageUser);
			}
			else if(type=="6")
			{
				$("#privType").val("6");
				$("#accountId").val(formData.deptManageAccountId);
				$("#userName").val(formData.deptManageUser);
			}
			else if(type=="7")
			{
				$("#privType").val("7");
				$("#accountId").val(formData.otherDeptManageAccountId);
				$("#userName").val(formData.otherDeptManageUser);
			}
		
	}
	function clear()
	{
		$("#accountId").val("");
		$("#userName").val("");
	}
</script>
</head>
<body onload="doint();" style="padding-top:10px;">
<div align="center">
<div style="width: 90%;border: 1px solid silver;box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);">
<div align="center" class="widget" >
<div class="widget-header bordered-bottom bordered-sky">
<span class="widget-caption">管理权限设置</span>
</div>
<table class="table table-striped  table-condensed" >
<tr>
<td>管理权限选程:</td>
<td ><div class="col-xs-4"><select name="privType"  id="privType" class="form-control"> 
<option value="1">全局查询</option>
<option value="2">本部门查询</option>
<option value="3">指定部门查询</option>
<option value="4">按用户级别查询</option>
<option value="5">全局监控</option>
<option value="6">本部门监控</option>
<option value="7">指定部门监控</option>
</select></div></td>
</tr>
<tr>
<td width="150px;">人员选择:</td>
<td>
<div class="col-xs-12">
<div style="float: left"><input type="hidden"  name="accountId"  id="accountId" />
<textarea class="form-control" rows="3" cols="50"  name="userName"  id="userName"></textarea></div><div style="float: left;">
<div style="line-height: 80px;">&nbsp;<a  onclick="userinit(['accountId','userName']);">添加</a> <a onclick="clear();">删除</a></div>
</div>
</td>
</tr>
</table>
<div>
<div align="center"><button onclick="save();" class="btn btn-primary">保 存</button>  <button onclick="back();" class="btn btn-default">返 回</button></div>
</br>
<div align="center" class="widget" >
<div class="widget-header bordered-bottom bordered-sky">
<span class="widget-caption">权限列表</span>
</div>
<table class="table table-striped  table-condensed" >
<tr>
<td align="center" width="5%">序号</td>
<td align="center" width="15%">权限名称</td>
<td align="center" width="60%">用户姓名</td>
<td align="center" width="20%">操作</td>
</tr>
<tr>
<td align="center" width="5%">1</td>
<td align="center" width="15%">全局查询</td>
<td align="center" width="60%"><div name="userName1"  id="userName1"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('1');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">2</td>
<td align="center" width="15%">本部门查询</td>
<td align="center" width="60%"><div name="userName2"  id="userName2"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('2');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">3</td>
<td align="center" width="15%">指定部门查询</td>
<td align="center" width="60%"><div name="userName3"  id="userName3"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('3');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">4</td>
<td align="center" width="15%">按用户级别查询</td>
<td align="center" width="60%"><div name="userName4"  id="userName4"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('4');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">5</td>
<td align="center" width="15%">全局监控</td>
<td align="center" width="60%"><div name="userName5"  id="userName5"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('5');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">6</td>
<td align="center" width="15%">本部门监控</td>
<td align="center" width="60%"><div name="userName6"  id="userName6"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('6');">设置</a></td>
</tr>
<tr>
<td align="center" width="5%">7</td>
<td align="center" width="15%">指定部门监控</td>
<td align="center" width="60%"><div name="userName7"  id="userName7"></div></td>
<td align="center" width="20%"><a href="javascript:void(0);"  onclick="opt('7');">设置</a></td>
</tr>
</table>
</div>
   <div id="modaldialog"></div>
</body>
</html>