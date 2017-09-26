<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<%
	String noticeId = request.getParameter("noticeId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<script type="text/javascript" charset="utf-8"
	src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/notice/approvalnotice/js/approvalnotice.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告审批</title>
<script type="text/javascript">
var noticeId="<%=noticeId%>";
</script>
</head>
<body>
	<div class="list-group-item" style="padding: 0px; cursor: auto; margin-left: 1%; margin-top: 10px; width: 98%;">
		<a style="cursor: auto;" class="list-group-item active">通知公告审批</a>
		<div style="background-color: white;">
			<div id="smsdiv" name="smsdiv" style="display: none;"></div>
			<textarea rows="3" cols="40" name="deptPriv" id="deptPriv"
				style="display: none;"></textarea>
			<textarea rows="3" cols="40" name="accountId" id="accountId"
				style="display: none;"></textarea>
			<input type="text" id="userPriv" name="userPriv"
				style="display: none;"> <input type="text" id="createTime"
				name="createTime" style="display: none;">
			<div align="center">
				<div id="title"
					style="font-weight: bold; font-size: 16px; line-height: 30px;"></div>
			</div>
			<div id="foot"
				style="font-size: 14px; background-color: #F2F2F2; line-height: 30px; height: 30px;"
				align="right"></div>
		</div>
		<div style="background-color: white;">
			<div id="noticeContent"></div>
		</div>
			<div style="float: left;" id="attachDiv" name="attachDiv"></div>
		</div>
	<div>
		审批意见：
		<textarea id="editor" name="editor"
			style="margin-left: 5%; width: 90%; height: 180px;"></textarea>
		<script type="text/javascript">
			CKEDITOR.replace('editor')
		</script>
	</div>
	<div align="center">
		<button type="button" class="btn  btn-primary" onclick="checkpass();">通过</button>
		<button type="button" class="btn btn-danger " onclick="checknopass();">不通过</button>
		<button type="button" class="btn btn-default " onclick="history.back()">返回</button>
	</div>
		</div>
</body>
</html>