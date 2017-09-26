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
var flowId="284EEC80-5C09-B134-84BD-FEF64014DC35";
var prcsId="2";
var tableName="bpmqijia";
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
<p style="text-align: center;"><span style="font-size: 32px;">请假申请单</span></p>

<table align="center" border="1" cellpadding="0" cellspacing="0" style="width: 685px;" width="684">
	<tbody>
		<tr>
			<td style="height: 38px;" width="129">
			<p align="center">姓名</p>
			</td>
			<td style="height: 38px;" width="91"><input datatype="text" fieldname="name" disabled="disabled" readonly id="name" model="{type:8,format:'null'}" name="name" title="姓名" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td style="height: 38px;" width="82">
			<p align="center">岗位</p>
			</td>
			<td style="height: 38px;" width="133"><input datatype="text" fieldname="wangwei" disabled="disabled" readonly id="wangwei" model="{type:10,format:'null'}" name="wangwei" title="岗位" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td style="height: 38px;" width="73">
			<p align="center">部门</p>
			</td>
			<td style="height: 38px;" width="163"><input datatype="text" fieldname="bumen" disabled="disabled" readonly id="bumen" model="{type:13,format:'null'}" name="bumen" title="部门" type="text" value="{宏控件}" xtype="xmacro" /></td>
		</tr>
		<tr>
			<td style="height: 110px;" width="129">
			<p align="center">请假事由</p>
			</td>
			<td colspan="5" style="height: 110px;" width="542"><textarea datatype="text" fieldname="resons" disabled="disabled" readonly id="resons" name="resons" style="margin: 0px; width: 552px; height: 82px;" title="请假事由" xtype="xtextarea"></textarea></td>
		</tr>
		<tr>
			<td style="height: 66px;" width="129">
			<p align="center">请假类别</p>
			</td>
			<td colspan="5" style="height: 66px;" width="542">
			<p><input datatype="text" fieldname="qjlb" disabled="disabled" readonly id="qjlb" name="qjlb" title="请假类别" type="radio" value="病假" xtype="xradio" />病假&nbsp;<input datatype="text" fieldname="qjlb" disabled="disabled" readonly id="qjlb" name="qjlb" title="请假类别" type="radio" value="事假" xtype="xradio" />事假&nbsp;<input datatype="text" fieldname="qjlb" disabled="disabled" readonly id="qjlb" name="qjlb" title="请假类别" type="radio" value="婚假" xtype="xradio" />婚假&nbsp;<input datatype="text" fieldname="qjlb" disabled="disabled" readonly id="qjlb" name="qjlb" title="请假类别" type="radio" value="产假" xtype="xradio" />产假&nbsp;<input datatype="text" fieldname="qjlb" disabled="disabled" readonly name="qjlb" title="请假类别" type="radio" value="" xtype="xradio" />丧假&nbsp;<input datatype="text" fieldname="qjlb" disabled="disabled" readonly name="qjlb" title="请假类别" type="radio" value="" xtype="xradio" />陪产假</p>
			</td>
		</tr>
		<tr>
			<td style="height: 126px;" width="129">
			<p align="center">请假日期</p>
			</td>
			<td colspan="5" style="height: 126px;" width="542">
			<p>从<input datatype="text" fieldname="firsttimes" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: pointer;"  id="firsttimes" model="{'type':1,'format':'yyyy-MM-dd','def':1}" name="firsttimes" title="请假开始时间" type="text" value="时间{选择控件}" xtype="xfetch" />到<input datatype="text" fieldname="secondtime" disabled="disabled" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: pointer;"  id="secondtime" model="{'type':1,'format':'yyyy-MM-dd','def':1}" name="secondtime" title="请假结束时间" type="text" value="时间{选择控件}" xtype="xfetch" />总计<input datatype="varchar" fieldname="zts" disabled="disabled" readonly id="zts" maxlength="10" name="zts" title="总天数" type="text" xtype="xinput" />天</p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">部门经理意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><textarea datatype="text" defaultvalue="" fieldname="bmjlyj" disabled="disabled" readonly id="bmjlyj" name="bmjlyj" style="margin: 0px; width: 552px; height: 82px;" title="部门经理意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmjlqm" disabled="disabled" readonly id="bmjlqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="bmjlqm" title="部门经理签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">部门总监意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><textarea datatype="text" defaultvalue="" fieldname="xzjybm" disabled="disabled" readonly id="xzjybm" name="xzjybm" style="margin: 0px; width: 552px; height: 82px;" title="部门总监意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="bmzjqm" disabled="disabled" readonly id="bmzjqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="bmzjqm" title="部门总监签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
			</td>
		</tr>
		<tr>
			<td style="height: 76px;" width="129">
			<p align="center">董事长意见</p>
			</td>
			<td colspan="5" style="height: 76px;" width="542">
			<p><textarea datatype="text" fieldname="dszyj" disabled="disabled" readonly id="dszyj" name="dszyj" style="margin: 0px; width: 552px; height: 82px;" title="董事长意见" xtype="xtextarea"></textarea></p>

			<p>签名：</p>

			<p><input datatype="text" fieldname="ceoqm" disabled="disabled" readonly id="ceoqm" model="{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}" name="ceoqm" title="董事长签名" type="text" value="{宏控件}" xtype="xmacro" /></p>
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
