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
</head><body><p style="text-align: center;"><strong><span style="font-size:28px;">功能测试表单</span></strong></p>

<hr />
<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 800px;">
	<tbody>
		<tr>
			<td>姓名：</td>
			<td><div id="name" xtype="xmacro" module="" name="name"></div></td>
			<td>部门：</td>
			<td><div id="dept" xtype="xmacro" module="" name="dept"></div></td>
		</tr>
		<tr>
			<td>单行输入：</td>
			<td><div id="sinput" xtype="xinput" module="" name="sinput"></div></td>
			<td>下拉列表：</td>
			<td><div id="sselect" xtype="xselect" module="" name="sselect"></div></td>
		</tr>
		<tr>
			<td>富文本：</td>
			<td colspan="3" rowspan="1"><div id="uedit" xtype="xtextuedit" module="" name="uedit"></div></td>
		</tr>
		<tr>
			<td>单选：</td>
			<td><input disabled="disabled"  type="radio" id="aradio" xtype="xradio" module="" name="aradio"/>A<input disabled="disabled"  type="radio" id="aradio" xtype="xradio" module="" name="aradio"/>B</td>
			<td>复选</td>
			<td><input disabled="disabled" type="checkbox" id="A" xtype="xcheckbox" module="" name="A"/>复选1<input disabled="disabled" type="checkbox" id="B" xtype="xcheckbox" module="" name="B"/>复选2</td>
		</tr>
		<tr>
			<td>流程选择：</td>
			<td><div id="selworkflow" xtype="xworkflow" module="" name="selworkflow"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>
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
<button onclick="printer();" class="btn btn-info btn-lg">打印</button></div>
</body>
</html>
