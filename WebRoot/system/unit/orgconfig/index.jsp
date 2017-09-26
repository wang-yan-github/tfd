<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>机构设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript">
function add()
{
	var orgName=$("#orgName").val();
	var orgAdmin=$("#orgAdmin").val()
	if(orgName==""||orgAdmin=="")
		{
		layer.msg("所有信息项不能为空！");
		return;
		}
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/addOrgConfigAct.act";
	$.ajax({
		url:url,
		data:{orgName:$("#orgName").val(),orgAdmin:$("#orgAdmin").val(),userPriv:$("#userPriv").val()},
		dataType:"text",
		async:false,
		success:function(data){
			if(data=="1")
				{
				window.location.href=contextPath+"/system/unit/orgconfig/index.jsp";
				}
		}
	});
}
function doinit()
{
	var html="<table class=\"table table-striped table-condensed\">";
	html+="<tr>";
	html+="<td align=\"center\">序号</td>";
	html+="<td align=\"center\">机构标识</td>";
	html+="<td align=\"center\">机构名称</td>";
	html+="<td align=\"center\">机构管理员</td>";
	html+="<td align=\"center\">操作</td>";
	html+="</tr>";
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/getOrgConfigJsonAct.act";
	$.ajax({
		url:url,
		data:{},
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;data.length>i;i++)
				{
				html+="<tr>";
				html+="<td align=\"center\">"+data[i].Id+"</td>";
				html+="<td align=\"center\">"+data[i].orgId+"</td>";
				html+="<td align=\"center\">"+data[i].orgName+"</td>";
				html+="<td align=\"center\">"+data[i].orgAdmin+"</td>";
				html+="<td align=\"center\"><a href=\"javascript:del('"+data[i].orgId+"');\">删除</a>  <a href=\"javascript:edit('"+data[i].orgId+"');\" >修改</a></td>";
				html+="</tr>";
				}
			html+="</table>";
			$("#list").html(html);
		}
	});
	
	createselect("userPriv","/tfd/tfd/system/unit/userleave/act/UserLeaveAct/getUserLeaveSelectAct.act");
	}
	
function del(orgId)
{
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/delOrgConfigAct.act";
	$.ajax({
		url:url,
		data:{orgId:orgId},
		dataType:"text",
		async:false,
		success:function(data){
		if(data==1)
			{
			window.location.href=contextPath+"/system/unit/orgconfig/index.jsp";
			layer.msg("删除成功！");
			}else
				{
				layer.msg("删除失败！");
				}
		}
	});
	}
	
function edit(orgId)
{
	window.location.href=contextPath+"/system/unit/orgconfig/edit.jsp?orgId="+orgId;
	}
</script>
</head>
<body onload="doinit();">
<table class="table table-striped table-condensed" >
<tr>
<td width="150px" align="center">机构名称：</td>
<td >
<input class="form-control" name="orgName" id="orgName"/>
</td>
<td width="150px" align="center">机构管理员：</td>
<td>
<input class="form-control" name="orgAdmin" id="orgAdmin"/>
</td>
<td width="150px" align="center">预制权限：</td>
<td width="150px">
	<select class="form-control"  name="userPriv"  id="userPriv">
	</select>
	</div>
</td>
<td align="center" width="150px" ><button type="button" class="btn btn-primary"  onclick="add();">添加</button></td>
</tr>
</table>

<div id="list" name="list">

</div>
</body>
</html>