<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>
</title>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/jquery.json.js" ></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/RegexUtil.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sys.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"></link>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
 <script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript"  charset="utf-8" src="<%=contextPath%>/system/jsall/workflow/form.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/dowork.css"></link>
<%String runId=request.getParameter("runId"); %>
<%String runPrcsId=request.getParameter("runPrcsId"); %>
<script type="text/javascript">
var runId="<%=runId%>";
var runPrcsId = "<%=runPrcsId%>";
var flowId="83524B7A-F541-CF35-4464-B10D93AAE996";
var prcsId="5";
var tableName="bxsqd";
$(function(){
$("table").addClass("table table-bordered table-striped");
$("select").addClass("form-control");
$("input[type='text']").addClass("form-control");
 			doint();
 });
</script>
</head>
<body>
<div id="modaldialog"></div>
<div class="easyui-layout" data-options="fit:true" style="overflow:hidden; ">
<div data-options="region:'north',height:40" style="overflow: hidden;" class="doworktop">
<div class="title-flow-name" style="width:60%;white-space:nowrap;overflow:hidden;">
<div class="inlineblock" style="height:40px;white-space:nowrap;padding-top: 10px;" id="flowTitle"   name="flowTitle"  title=""></div>
<input type="hidden" style="width:200px;loat: left;" id="flowTitleOld" name="flowTitleOld" value="">
</div>
<div class="title-flow-name" style="width:40%;white-space:nowrap;overflow:hidden;">
<div id="topPrcsName" name="topPrcsName" style="float: right;"></div>
</div>
</div>
<div data-options="region:'center',border:false" style="overflow:auto;">
<div id="formdata" style="width:90%;padding-left:5%;">
<p style="text-align: center;"><span style="font-size: 22px;">补休、加班、串休申请单</span></p>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 700px;">
	<tbody>
		<tr>
			<td>部门</td>
			<td><input datatype="text" fieldname="bm" disabled="disabled" readonly id="bm" model="{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}" name="bm" title="部门" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td>姓名</td>
			<td><input datatype="text" fieldname="name" disabled="disabled" readonly id="name" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="name" title="姓名" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td>角色</td>
			<td><input datatype="text" fieldname="zhiwei" disabled="disabled" readonly id="zhiwei" model="{&quot;type&quot;:&quot;10&quot;,&quot;format&quot;:null}" name="zhiwei" style="" title="职位" type="text" value="{宏控件}" xtype="xmacro" /></td>
		</tr>
		<tr>
			<td>申请类型</td>
			<td colspan="5"><input datatype="text" fieldname="sqlx" disabled="disabled" readonly name="sqlx" title="申请类型" type="radio" value="加班" xtype="xradio" />加班&nbsp;<input datatype="text" fieldname="sqlx" disabled="disabled" readonly name="sqlx" title="申请类型" type="radio" value="串休" xtype="xradio" />串休&nbsp;<input datatype="text" fieldname="sqlx" disabled="disabled" readonly name="sqlx" title="申请类型" type="radio" value="补休" xtype="xradio" />补休</td>
		</tr>
		<tr>
			<td>申请原因</td>
			<td colspan="5" rowspan="1"><textarea datatype="text" defaultvalue="" fieldname="sqyy" disabled="disabled" readonly id="sqyy" name="sqyy" style="width: 334px; height: 42px;" title="申请原因" xtype="xtextarea"></textarea></td>
		</tr>
		<tr>
			<td>开始时间</td>
			<td><input datatype="text" fieldname="firsttime" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="cursor: pointer;"  id="firsttime" model="{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd HH:mm&quot;}" style="" title="开始时间" type="text" value="时间{选择控件}" xtype="xfetch" /></td>
			<td>结束时间</td>
			<td colspan="3"><input datatype="text" fieldname="overtime" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="cursor: pointer;"  id="overtime" model="{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd HH:mm&quot;}" style="" title="结束时间" type="text" value="时间{选择控件}" xtype="xfetch" /></td>
		</tr>
		<tr>
			<td>部门经理意见</td>
			<td colspan="5" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="bmjlyj" disabled="disabled" readonly id="bmjlyj" name="bmjlyj" style="width: 500px; height: 42px;" title="部门经理意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmjlqm" disabled="disabled" readonly id="bmjlqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="bmjlqm" title="部门经理签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>部门总监意见</td>
			<td colspan="5" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="bmzjyj" disabled="disabled" readonly id="bmzjyj" name="bmzjyj" style="width: 500px; height: 42px;" title="部门总监意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmzjqm" disabled="disabled" readonly id="bmzjqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="bmzjqm" title="部门总监签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>董事长意见</td>
			<td colspan="5" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="ceoyj" disabled="disabled" readonly id="ceoyj" name="ceoyj" style="width: 500px; height: 42px;" title="董事长意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="ceoqm" disabled="disabled" readonly id="ceoqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="ceoqm" title="董事长签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>人资部确认</td>
			<td colspan="5" rowspan="1"><input datatype="text" fieldname="rzbqr" id="rzbqr" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="rzbqr" title="人资部签名" type="text" value="{宏控件}" xtype="xmacro" /></td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>
<div class="widget"> 
 <div class="widget-header bordered-bottom bordered-themesecondary">
<i class="widget-icon fa fa-tags themesecondary"></i>
<span class="widget-caption themesecondary">公共附件</span>
</div>
<table>
<tr>
 <td colspan="4"><div id="publicFileDiv"></div></td>
</tr>
<tr>
<td width="150px" >附件上传：</td>
 <td colspan="3">
 <div class="fieldset flash" id="publicFileProgress" style="display: none;"></div>
<div id="divStatus" style="display: none;"></div>
<a class="addfile"  href="javascript:void(0)" >单附件
<input type="file" onchange="fileUpLoad('workflow','publicFile');" hidefocus="true" size="1" id="filepublicFile"  name="filepublicFile" class="addfile">
<input type="hidden" id="publicFileId" name="publicFileId"/>
</a>
<a class="add_swfupload" href="javascript:void(0)" >多附件<span id="publicFile"></span></a>
<div style="display: none;"><a id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled">取消上传</a></div>
 </td>
</tr>
</table>
 </div>
<div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">会签结果</span>
</div>
<div id="allIdea" name="allIdea"></div>
</div>
<div class="widget">
<div class="widget-header bordered-bottom bordered-themesecondary">
<i class="widget-icon fa fa-tags themesecondary"></i>
<span class="widget-caption themesecondary">会签意见</span>
</div>
<table>
<tr>
<td style="width: 150px;">会签意见：</td>
<td>
<select name="idea" id="idea" style="width: 200px;">
<option value="1">同意</option>
<option value="0">不同意</option>
<option value="2">基本同意</option>
</select>
</td>
</tr>
<tr>
<td>填写意见：</td>
<td> <textarea rows="3" name="ideaText" id="ideaText" class="form-control"></textarea></td>
</tr>
 </table>
 </div>
</div>
</div>
<div data-options="region:'south',border:false" style="background-color: silver;text-align:right;overflow: hidden;">
<div style="margin-right: 20px;">
<span id="follow" name="follow" onclick="follow();" class="glyphicon glyphicon-eye-open" style="cursor:pointer;color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;"> <b>关注</b></span>
<span id="addopuser" name="addopuser" onclick="addopuser();"class="glyphicon glyphicon-edit" style="cursor:pointer;color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;" data-container="body" data-toggle="popover" data-placement="top" data-html="html" title="加签人员选择">
<b>加签</b></span>
<span id="flowView" name ="flowView" onclick="flowView();" class="glyphicon glyphicon-random" style="color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;cursor:pointer;"> <b>流程图</b></span>
<button onclick="nextprcs();" class="btn btn-primary btn-lg">提交表单</button>
<button onclick="savaform();" class="btn btn-primary">保存表单</button>
<button onclick="doGoBack();" class="btn btn-warning" id="goback" name="goback" data-container="body" data-toggle="popover" data-placement="top" data-html="html" title="回退步骤选择">回退</button>
<button onclick="returnback();" class="btn btn-danger" >返回</button>
</div>
</div>
</body>
</html>
