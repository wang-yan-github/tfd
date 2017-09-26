<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
	String calId = request.getParameter("id");
	String type = request.getParameter("type");
%>
<html>
<head>
<title>日程安排</title>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/lib/jquery-ui.custom.min.js'></script>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.min.js'></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link href='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='<%=contextPath%>/calendar/css/calendar.css' rel='stylesheet' />

<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/lender.js"></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/affair.js'></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/detail.logic.js'></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
</head>
<script type="text/javascript">
var calId = "<%=calId%>";
var type = "<%=type%>";
var ii = 0;
</script>
<style>
table{
table-layout:fixed;
word-break:break-all;
}
</style>
<body>
<div>
	<div class="panel panel-info" style="border:none;" >
		<div class="panel-body" style="border:none;" >
  		 <table class="table table-striped">
  		 	<tr>
  		 	<input type="hidden" id="cal_Id"  />
  		 	<input type="hidden" id="d_Id"  />
				<td width="20%">开始时间:</td>
				<td id="startDate" ></td>
			</tr>
			<tr>
				<td width="20%">结束时间:</td>
				<td id="endDate" ></td>
			</tr>
			<tr>
				<td width="20%">分配人:</td>
				<td id="accountName" ></td>
			</tr>
			<tr>
				<td width="20%">参与人员:</td>
				<td id="userName" ></td>
			</tr>
			<tr>
				<td width="20%">事务内容:</td>
				<td id="content" ></td>
			</tr>
			<tr>
				<td width="20%">状态:</td>
				<td id="status" ></td>
			</tr>
			<tr id="finishedTr" >
				<td width="20%">完成情况:</td>
				<td> <textarea  name="diary_content" id="diary_content" style="float:left;" class="form-control" required="true" rows="3" cols="30"></textarea></td>
			</tr>
			<tr id="diaryTr" style="display:none;" >
				<td width="20%">完成日志:</td>
				<td id="diaryTd"> </td>
			</tr>
			</table>
	</div>
	</div>
	</div>
</div>
<div id="modaldialog"></div>
</body>
</html>
