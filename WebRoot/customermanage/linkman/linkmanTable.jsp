<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<%String customerId=request.getParameter("customerId"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<title>联系人列表</title>
<script>
var customerId="<%=customerId%>";
$(function(){
	getlinkman();
});
function getlinkman(){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/getcIdlinkmanAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{customerId:customerId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var linkmanCon="";
			for(var i=0;i<data.length;i++){
				linkmanCon+="<tr align=\"center\">"+
				"<td>"+data[i].linkmanName+"</td>"+
				"<td>"+data[i].linkmanJob+"</td>"+
				"<td>"+data[i].cellPhone+"</td>"+
				"<td>"+data[i].eMail+"</td>"+
				"<td>"+data[i].qqNumber+"</td>"+
				"<td><a href=\"javascript:void(0);\" onclick=\"getupdatelinkman('"+data[i].linkmanId+"');\">编辑</a></td>"+
				"</tr>";
				
				//"<td><a href=\"javascript:void(0);\" onclick=\"getupdatelinkman('"+data[i].linkmanId+"');\">编辑</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"dellinkman('"+data[i].linkmanId+"');\">删除</a></td>"+
			}
			$("#linkman").append(linkmanCon);
	}
	});
}
function dellinkman(linkmanId){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/delLinkmanAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{linkmanId:linkmanId},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				window.location.reload();
			}
	}
	});
}
function getNewlinkamn(){
	var url=contextPath+"/customermanage/linkman/Newlinkman.jsp?customerId="+customerId;
	window.location=url;
}
function getupdatelinkman(linkmanId){
	var url=contextPath+"/customermanage/linkman/updatelinkman.jsp?linkmanId="+linkmanId;
	window.location=url;
}
</script>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >联系人</span>
</div>
<div style=" float: right;margin-top:6px;margin-right: 20px;">
<button class="btn btn-primary btn-large" onclick="getNewlinkamn();"> 添加联系人</button>
<button class="btn btn-default btn-large" onclick="history.back();"> 返回</button>
</div>
</div>
<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<table class="table table-striped " id="linkman">
<tr>
<td align="center" width="16%">姓名</td>
<td align="center" width="16%">职位</td>
<td align="center" width="16%">手机号码</td>
<td align="center" width="16%">邮箱</td>
<td align="center" width="16%">QQ</td>
<td align="center" width="20%">操作</td>
</tr>
</table>
</div>
</body>
</html>