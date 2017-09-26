<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>查看会议纪要</title>
<%String requestId=request.getParameter("requestId"); %>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/summary/js/getIddetail.js"></script>
<script type="text/javascript">
var requestId="<%=requestId%>";
</script>
</head>
<body>
  <div class="list-group-item"  style="padding: 0px;cursor: auto;width:80%;margin-left: 10%;">
<a style="cursor: auto;" class="list-group-item active">会议纪要详情</a>
<table class="table table-striped table-condensed">
<tr>
<td width="15%">会议名称:</td>
<td>
<input type="hidden" id="requestId" name="requestId" class="form-control " >
<div class="col-xs-4" id="meetingname">

</div></td>
</tr>
<tr>
<td>指定读者:</td>
<td><div class="col-xs-4" id="StaffName"></div>
</td>
</tr>
<tr>
<td>实际参会人员:</td>
<td>
<div class="col-xs-4" id="userName">
</div>
</td>
</tr>
<tr>
<td>附件:</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
<td colspan="2"> 
纪要内容：
</td>
</tr>
<tr>
<td colspan="2"> 
<div id="summaryContent"></div>
</td>
</tr>
</table>
   </div>
   <div align="center">
<button type="button" name="save" onclick="history.back();" class="btn btn-default">返回</button>
 </div>
<div id="modaldialog"></div>
</body>
</html>