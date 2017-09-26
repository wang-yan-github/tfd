<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>工作流表单预览
</title>
<script src="<%=contextPath%>/system/jsall/workflow/print.js" type="text/javascript"></script>
<script src="<%=contextPath%>/system/jsall/printtool/printtooljsall.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/dowork.css"></link>
<%String runId=request.getParameter("runId"); %>
<script type="text/javascript">
var runId="<%=runId%>"
$(function(){
var binfo = getBrowerinfo();
if(binfo=="firefox"||binfo=="chrome"||binfo=="safari")
{
$("#print1").hide();
$("#print2").hide();
}
			getPrintData(runId);
			createXlist(runId);
			createXifream();
			getIdea();
			doXmacrotag();
 });
</script>
<style media="print">
.noprint { display : none; }
</style>
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
<div align="right" class="noprint" style="background-color: silver;position:fixed;width:100%;bottom:0px;">
<button id="print1" type="button" onclick="printer(1);" class="btn btn-info btn-lg">打印设置</button>
<button id="print2" type="button" onclick="printer(2);" class="btn btn-info btn-lg">打印预览</button>
<button id="print3" type="button" onclick="printer(3);" class="btn btn-info btn-lg">直接打印</button>
<button id="print4" type="button" onclick="printer(4);" class="btn btn-info btn-lg">关闭窗口</button>
<object id="printWB"  style="dispaly:none" classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"  height="0" width="0"></object>
</div>
</body>
</html>
