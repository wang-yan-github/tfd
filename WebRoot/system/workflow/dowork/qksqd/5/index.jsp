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
var flowId="2B969F8D-07EE-5E4F-8DB1-2307472132BB";
var prcsId="5";
var tableName="qksqd";
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
<p style="text-align: center;"><span style="font-size: 24px;">请款申请单</span></p>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 630px;">
	<tbody>
		<tr>
			<td>部门</td>
			<td><input datatype="text" fieldname="bm" disabled="disabled" readonly id="bm" model="{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}" name="bm" title="部门" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td>请款人</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="qkr" disabled="disabled" readonly id="qkr" maxlength="10" name="qkr" title="请款人" type="text" value="" xtype="xinput" /></td>
		</tr>
		<tr>
			<td>请款时间</td>
			<td><input datatype="text" fieldname="qktime" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: pointer;"  id="qktime" model="{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd&quot;}" title="请款时间" type="text" value="时间{选择控件}" xtype="xfetch" /></td>
			<td>请款项目</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="qkxm" disabled="disabled" readonly id="qkxm" maxlength="30" name="qkxm" title="请款项目" type="text" value="" xtype="xinput" /></td>
		</tr>
		<tr>
			<td>请款金额大写</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="qljedx" disabled="disabled" readonly id="qljedx" maxlength="40" name="qljedx" title="请款金额大写" type="text" value="" xtype="xinput" /></td>
			<td>小写</td>
			<td><input datatype="varchar" defaultvalue="" fieldname="xxje" disabled="disabled" readonly id="xxje" maxlength="30" name="xxje" title="小写金额" type="text" value="" xtype="xinput" /></td>
		</tr>
		<tr>
			<td>请款事由</td>
			<td colspan="3" rowspan="1"><textarea datatype="text" defaultvalue="" fieldname="qkreason" disabled="disabled" readonly id="qkreason" name="qkreason" style="width: 449px; height: 75px;" title="请款事由" xtype="xtextarea"></textarea></td>
		</tr>
		<tr>
			<td>部门总监审批</td>
			<td colspan="3" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="bmzjsp" disabled="disabled" readonly id="bmzjsp" name="bmzjsp" style="width: 449px; height: 50px;" title="部门总监审批" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmzjqm" disabled="disabled" readonly id="bmzjqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="bmzjqm" title="部门总监签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>董事长审批</td>
			<td colspan="3" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="ceosp" disabled="disabled" readonly id="ceosp" name="ceosp" style="width: 449px; height: 50px;" title="董事长审批" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="ceoqm" disabled="disabled" readonly id="ceoqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="ceoqm" title="董事长签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>财务审核</td>
			<td colspan="3" rowspan="1">
			<p><textarea datatype="text" defaultvalue="" fieldname="cwsh" disabled="disabled" readonly id="cwsh" name="cwsh" style="width: 449px; height: 50px;" title="财务审核" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="cwqm" disabled="disabled" readonly id="cwqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="cwqm" title="财务签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td>出纳确认</td>
			<td colspan="3" rowspan="1"><input datatype="text" fieldname="cnqr" id="cnqr" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="cnqr" title="出纳确认" type="text" value="{宏控件}" xtype="xmacro" /></td>
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
