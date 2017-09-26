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
var flowId="2214A329-42B1-49F3-0A30-0FBB857FECCD";
var prcsId="2";
var tableName="kjcs";
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
<p><input datatype="varchar" defaultvalue="" fieldname="xinput" id="xinput" maxlength="100" name="xinput" title="单行输入框" type="text" value="" xtype="xinput" /></p>

<p><textarea datatype="text" defaultvalue="" fieldname="xtextarea" id="xtextarea" name="xtextarea" style="" title="多行输入框" xtype="xtextarea"></textarea></p>

<p><img datatype="text" defaultvalue="" fieldname="xuedit" id="xuedit" name="xuedit" src="/tfd/system/styles/images/workflow/uedit.png" style="" title="富文本框" type="text" value="" xtype="xtextuedit" /></p>

<p><select datatype="text" defaultvalue="" fieldname="xselect" id="xselect" model="[{&quot;value&quot;:&quot;xselect1&quot;,&quot;desc&quot;:&quot;xselect1&quot;},{&quot;value&quot;:&quot;xselect2&quot;,&quot;desc&quot;:&quot;xselect2&quot;}]" name="xselect" title="下拉框" xtype="xselect"><option value="xselect1">xselect1</option><option value="xselect2">xselect2</option></select></p>

<p><input datatype="text" fieldname="xradio" model="[{&quot;value&quot;:&quot;单选1&quot;,&quot;desc&quot;:&quot;单选1&quot;}]" name="xradio" title="单选框" type="radio" value="单选1" xtype="xradio" />单选1<input datatype="text" fieldname="xradio" model="[{&quot;value&quot;:&quot;单选2&quot;,&quot;desc&quot;:&quot;单选2&quot;}]" name="xradio" title="单选框" type="radio" value="单选2" xtype="xradio" />单选2</p>

<p><input datatype="text" fieldname="xcheckbox" model="[{&quot;value&quot;:&quot;复选1&quot;,&quot;desc&quot;:&quot;复选1&quot;}]" name="xcheckbox" title="复选框" type="checkbox" value="复选1" xtype="xcheckbox" />复选1<input datatype="text" fieldname="xcheckbox" model="[{&quot;value&quot;:&quot;复选2&quot;,&quot;desc&quot;:&quot;复选2&quot;}]" name="xcheckbox" title="复选框" type="checkbox" value="复选2" xtype="xcheckbox" />复选2</p>

<p>金额A：<input datatype="varchar" defaultvalue="" fieldname="moneya" id="moneya" maxlength="10" name="moneya" title="金额A" type="text" value="" xtype="xinput" />金额B：<input datatype="varchar" defaultvalue="" fieldname="moneyb" id="moneyb" maxlength="10" name="moneyb" title="金额B" type="text" value="" xtype="xinput" /></p>

<p>计算：<input datatype="text" fieldname="xcalculat" disabled="disabled" readonly id="xcalculat" model="{&quot;module&quot;:&quot;[金额A]+[金额B]&quot;}" module="[金额A]+[金额B]" name="xcalculat" title="计算控件" type="text" xtype="xcalculate" /></p>

<p><input datatype="text" fieldname="xfetchdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="cursor: pointer;"  id="xfetchdate" model="{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd HH:mm&quot;}" style="" title="日期选择" type="text" value="时间{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchtime" onfocus="WdatePicker({dateFmt:'HH时mm分'})" style="cursor: pointer;"  id="xfetchtime" model="{&quot;type&quot;:&quot;2&quot;,&quot;format&quot;:&quot;HH时mm分&quot;}" style="" title="时间选择" type="text" value="时间{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchdept"  onclick="deptinit(['','xfetchdept'],true);" style="cursor: pointer;" id="xfetchdept" model="{&quot;type&quot;:&quot;3&quot;,&quot;format&quot;:null}" style="" title="部门单选" type="text" value="部门{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchdepts"  onclick="deptinit(['','xfetchdepts'],false);" style="cursor: pointer;" id="xfetchdepts" model="{&quot;type&quot;:&quot;4&quot;,&quot;format&quot;:null}" style="" title="部门多选" type="text" value="部门{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchpriv"  onclick="privinit(['','xfetchpriv'],true);" style="cursor: pointer;" id="xfetchpriv" model="{&quot;type&quot;:&quot;5&quot;,&quot;format&quot;:null}" style="" title="角色单选" type="text" value="角色{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchprivs"  onclick="privinit(['','xfetchprivs'],false);" style="cursor: pointer;" id="xfetchprivs" model="{&quot;type&quot;:&quot;6&quot;,&quot;format&quot;:null}" style="" title="角色多选" type="text" value="角色{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchuser"  onclick="userinit(['','xfetchuser'],true);" style="cursor: pointer;" id="xfetchuser" model="{&quot;type&quot;:&quot;7&quot;,&quot;format&quot;:null}" style="" title="人员单选" type="text" value="人员{选择控件}" xtype="xfetch" /></p>

<p><input datatype="text" fieldname="xfetchusers"  onclick="userinit(['','xfetchusers'],false);" style="cursor: pointer;" id="xfetchusers" model="{&quot;type&quot;:&quot;8&quot;,&quot;format&quot;:null}" style="" title="人员多选" type="text" value="人员{选择控件}" xtype="xfetch" /></p>

<p>MACRO_FORM_NAME]<span xtype="macrotag" value="MACRO_RUN_NAME"></span><span xtype="macrotag" value="MACRO_NUMBERING"></span><span xtype="macrotag" value="MACRO_END_TIME"></span><span xtype="macrotag" value="MACRO_BEGIN_TIME"></span><span xtype="macrotag" value="MACRO_RUN_ID"></span><span xtype="macrotag" value="MACRO_RUN_GUID"></span><span xtype="macrotag" value="MACRO_BEGIN_USERNAME"></span><span xtype="macrotag" value="MACRO_BEGIN_ACCOUNT_ID"></span></p>

<p><img datatype="text" fieldname="xworkflow" onclick="selectworkflow('xworkflow','1');" id="xworkflow" model="1" name="xworkflow" src="/tfd/system/styles/images/workflow/workflow.png" title="流程选择" type="text" value="1" xtype="xworkflow" /></p>

<p><img datatype="text" fieldname="ximg" height="" id="ximg" name="ximg" src="/tfd/system/styles/images/workflow/imgup.png" title="图片上传" width="" xtype="ximg" /></p>

<p><input datatype="text" fieldname="xupload" id="xupload" name="xupload" title="单附件上传" type="file" xtype="xupload" /></p>

<p><input datatype="text" fieldname="xuploads" id="xuploads" name="xuploads" title="多附件上传" type="file" xtype="xuploads" /></p>

<p>&nbsp;</p>

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
