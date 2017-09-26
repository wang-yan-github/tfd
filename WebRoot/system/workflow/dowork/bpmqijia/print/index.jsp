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
</head><body><p style="text-align: center;"><span style="font-size: 32px;">请假申请单</span></p>

<table align="center" border="1" cellpadding="0" cellspacing="0" style="width: 685px;" width="684">
	<tbody>
		<tr>
			<td style="height: 38px;" width="129">
			<p align="center">姓名</p>
			</td>
			<td style="height: 38px;" width="91"><div id="name" xtype="xmacro" module="" name="name"></div></td>
			<td style="height: 38px;" width="82">
			<p align="center">岗位</p>
			</td>
			<td style="height: 38px;" width="133"><div id="wangwei" xtype="xmacro" module="" name="wangwei"></div></td>
			<td style="height: 38px;" width="73">
			<p align="center">部门</p>
			</td>
			<td style="height: 38px;" width="163"><div id="bumen" xtype="xmacro" module="" name="bumen"></div></td>
		</tr>
		<tr>
			<td style="height: 110px;" width="129">
			<p align="center">请假事由</p>
			</td>
			<td colspan="5" style="height: 110px;" width="542"><div id="resons" xtype="xtextarea" module="" name="resons"></div></td>
		</tr>
		<tr>
			<td style="height: 66px;" width="129">
			<p align="center">请假类别</p>
			</td>
			<td colspan="5" style="height: 66px;" width="542">
			<p><input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>病假&nbsp;<input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>事假&nbsp;<input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>婚假&nbsp;<input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>产假&nbsp;<input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>丧假&nbsp;<input disabled="disabled"  type="radio" id="qjlb" xtype="xradio" module="" name="qjlb"/>陪产假</p>
			</td>
		</tr>
		<tr>
			<td style="height: 126px;" width="129">
			<p align="center">请假日期</p>
			</td>
			<td colspan="5" style="height: 126px;" width="542">
			<p>从<div id="firsttimes" xtype="xfetch" module="" name="firsttimes"></div>到<div id="secondtime" xtype="xfetch" module="" name="secondtime"></div>总计<div id="zts" xtype="xinput" module="" name="zts"></div>天</p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">部门经理意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><div id="bmjlyj" xtype="xtextarea" module="" name="bmjlyj"></div></p>

			<p>签名：</p>

			<p><div id="bmjlqm" xtype="xmacro" module="" name="bmjlqm"></div></p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">部门总监意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><div id="xzjybm" xtype="xtextarea" module="" name="xzjybm"></div></p>

			<p>签名：</p>

			<p><div id="bmzjqm" xtype="xmacro" module="" name="bmzjqm"></div></p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">董事长意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><div id="dszyj" xtype="xtextarea" module="" name="dszyj"></div></p>

			<p>签名：</p>

			<p><div id="ceoqm" xtype="xmacro" module="" name="ceoqm"></div></p>
			</td>
		</tr>
		<tr>
			<td style="height: 105px;" width="129">
			<p align="center">备 注</p>
			</td>
			<td colspan="5" style="height: 105px;" width="542">
			<p>请假三天以内的，由部门总监批准结束；</p>

			<p>请假三天（含）以上的，应报董事长批准；</p>
			</td>
		</tr>
	</tbody>
</table>
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
