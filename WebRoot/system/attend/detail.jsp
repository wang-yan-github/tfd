<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String attendId = request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/system/attend/js/index.logic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<style type="text/css">
.content-body table tr td{text-align:center;}
table{
table-layout:fixed;
word-break:break-all;
}
</style>
<script type="text/javascript">	
var attendId = "<%=attendId%>";
function getAttendById(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendById.act";
	$.ajax({
		url:requestUrl,
		data:{attendId:attendId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#registTime").html(data.registTime);
			$("#registType").html(data.registType);
			var status = data.status;
			if(status==""){
				$("#statusTr").hide();
			}else{
				if(data.status=='2'){
					status = "<span style=\"color:red\" >迟到</span>";
				}else if(data.status=='3'){
					status = "<span style=\"color:red\" >早退</span>";
				}else if(data.status=='1'){
					status = "<span style=\"color:green\" >正常</span>";
				}
				$("#status").html(status);
			}
			$("#address").html(data.address);
			$("#detail").html(data.detail);
			$("#remark").html(data.remark);
			readAttachDiv(data.pictrue,"attach");
		}
	});
}
</script>
</head>
<body onload="getAttendById();" >
<div class="list-group" style="margin-bottom: 0px;">
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped">
<tr>
<td width="20%" >登记时间</td>
<td id="registTime" ></td>
</tr>
<tr>
<td>登记类型</td>
<td id="registType" ></td>
</tr>
<tr id="statusTr" >
<td>登记状态</td>
<td id="status" ></td>
</tr>
<tr>
<td>登记位置</td>
<td id="address" ></td>
</tr>
<tr>
<td>详细位置</td>
<td id="detail" ></td>
</tr>
<tr>
<td>图片</td>
<td><div id="attachDiv" name="attachDiv" ></div></td>
</tr>
<tr>
<td>备注</td>
<td id="remark" ></td>
</tr>
</table>
</div>
</div>

</body>
</html>