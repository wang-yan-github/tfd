<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>工作流表单预览
</title>
<script src="<%=contextPath%>/system/jsall/workflow/print.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/dowork.css"></link>
<%String runId=request.getParameter("runId"); %>
<script type="text/javascript">
var runId="<%=runId%>"
$(function(){
			getPrintData(runId);
			createXlist(runId);
			createXifream();
			getIdea();
			doXmacrotag();
 });
</script>
</head><body><p>单行：<div id="danhangdanhang" xtype="xinput" module="" name="danhangdanhang"></div></p>
<div align="center">
<div class="widget" style="width: 90%;"> 
<div class="widget-header bordered-bottom bordered-themesecondary">
<i class="widget-icon fa fa-tags themesecondary"></i>
<span class="widget-caption themesecondary">公共附件</span>
</div>
<table class="table table-striped  table-condensed" >
<tr>
<td width="150px;">附件列表：</td>
<td><div id="publicFiles" name="publicFiles"></div></td>
</tr>
</table>
</div>
</div>
<div align="center"><div class="widget" style="width: 90%;">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">会签结果</span>
</div>
<div id="allIdea" name="allIdea" align="left"></div>
</div></div>
<div align="center" style="background-color: silver;">
<button type="button" onclick="openprintwindow(runId);" class="btn btn-info btn-lg">打印</button></div>
</body>
</html>
