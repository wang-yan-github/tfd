<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室列表</title>
<script type="text/javascript">
function lookboardroom(){
	var url=contextPath+"/meeting/act/BoardroomAct/getaccountAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
			var tr="<tr align=\"center\"><td>"+data[i].boardroomName+"</td><td>"+data[i].boardroomNum+"</td><td>"+data[i].userName+"</td><td>"+data[i].boardroomAddress+"</td><td><a href=\"javascript:void(0)\" onclick=\"approvalboardroom('"+data[i].boardroomId+"');\">申请</a>&nbsp;</td></tr>";
			$("#boardroom").append(tr);
			}
	}
	});
}
function approvalboardroom(boardroomId){
	var url=contextPath+"/metting/requestmetting/meetingtime.jsp?boardroomId="+boardroomId;
	window.location=url;
}
</script>
</head>
<body onload="lookboardroom();">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:90%;margin-left: 5%;margin-top: 3%;">
<a style="cursor: auto;" class="list-group-item active">会议室列表</a>
<table class="table table-striped table-condensed" id="boardroom">
<tr>
<td align="center" width="20%">名称</td>
<td align="center" width="15%">可容纳人数</td>
<td align="center" width="15%">会议管理员</td>
<td align="center" width="30%">地址</td>
<td align="center" width="20%">操作</td>
</tr>
</table>
   </div>
</body>
</html>