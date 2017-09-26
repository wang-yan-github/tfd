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
var flowId="DE6B6BE4-2824-1655-493F-6889A6C0C145";
var prcsId="2";
var tableName="bgypsld";
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
<p style="text-align: center;"><span style="font-size: 24px;">办公用品申领单</span></p>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 720px;">
	<tbody>
		<tr>
			<td style="text-align: center;">部门</td>
			<td><input datatype="text" fieldname="bm" disabled="disabled" readonly id="bm" model="{'type':'13','format':null}" name="bm" title="部门" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td style="text-align: center;">申请人</td>
			<td><input datatype="text" fieldname="name" disabled="disabled" readonly id="name" model="{'type':'8','format':null}" name="name" title="申请人" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td style="text-align: center;">职位</td>
			<td><input datatype="text" fieldname="zhiwei" disabled="disabled" readonly id="zhiwei" model="{'type':'10','format':null}" name="zhiwei" title="职位" type="text" value="{宏控件}" xtype="xmacro" /></td>
		</tr>
		<tr>
			<td style="text-align: center;">申请物品</td>
			<td colspan="3" rowspan="1"><input datatype="varchar" defaultvalue="" fieldname="sqwp" disabled="disabled" readonly id="sqwp" maxlength="100" name="sqwp" title="申请物品" type="text" value="" xtype="xinput" /></td>
			<td style="text-align: center;">数量</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="shuliang" disabled="disabled" readonly id="shuliang" maxlength="20" name="shuliang" title="数量" type="text" value="" xtype="xinput" /></td>
		</tr>
		<tr>
			<td style="text-align: center;">申请时间</td>
			<td><input datatype="text" fieldname="sqtime" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: pointer;"  id="sqtime" model="{'type':'1','format':'yyyy-MM-dd'}" title="申请时间" type="text" value="时间{选择控件}" xtype="xfetch" /></td>
			<td style="text-align: center;">使用人</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="syr" disabled="disabled" readonly id="syr" maxlength="10" name="syr" title="使用人姓名" type="text" value="" xtype="xinput" /></td>
			<td style="text-align: center;">是否实习生</td>
			<td><input datatype="text" fieldname="sfsxs" disabled="disabled" readonly name="sfsxs" title="是否实习生" type="radio" value="" xtype="xradio" />是 <input datatype="text" fieldname="sfsxs" disabled="disabled" readonly name="sfsxs" title="是否实习生" type="radio" value="" xtype="xradio" />否</td>
		</tr>
		<tr>
			<td style="text-align: center;">申请原因</td>
			<td colspan="5" rowspan="1"><textarea datatype="text" defaultvalue="" fieldname="sqresone" disabled="disabled" readonly id="sqresone" name="sqresone" style="width: 434px; height: 84px;" title="申请原因" xtype="xtextarea"></textarea></td>
		</tr>
		<tr>
			<td style="text-align: center;">备注</td>
			<td colspan="5" rowspan="1"><textarea datatype="text" defaultvalue="" fieldname="beizhu" disabled="disabled" readonly id="beizhu" name="beizhu" style="width: 434px; height: 50px;" title="备注" xtype="xtextarea"></textarea></td>
		</tr>
		<tr>
			<td style="text-align: center;">部门审批</td>
			<td colspan="5" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="bmspyi" id="bmspyi" name="bmspyi" style="width: 434px; height: 84px;" title="部门审批意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmldqm" id="bmldqm" model="{'type':'12','format':null}" name="bmldqm" title="部门领导签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>行政审批</td>
			<td colspan="5" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="xzspyj" disabled="disabled" readonly id="xzspyj" name="xzspyj" style="width: 434px; height: 84px;" title="行政审批意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="xzldqm" disabled="disabled" readonly id="xzldqm" model="{'type':'12','format':null}" name="xzldqm" title="行政领导签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
	</tbody>
</table>

<p style="text-align: center;">&nbsp;</p>
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
