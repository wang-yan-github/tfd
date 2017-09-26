<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<title>工作流表单预览
</title>
<p style="text-align: center;"><strong><span style="font-size:28px;">功能测试表单</span></strong></p>

<hr />
<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 800px;">
	<tbody>
		<tr>
			<td>姓名：</td>
			<td><input datatype="text" fieldname="name" id="name" model="{type:8,format:'null'}" name="name" style="" title="姓名" type="text" value="{宏控件}" xtype="xmacro" /></td>
			<td>部门：</td>
			<td><input datatype="text" fieldname="dept" id="dept" model="{type:14,format:'null'}" name="dept" style="" title="部门" type="text" value="{宏控件}" xtype="xmacro" /></td>
		</tr>
		<tr>
			<td>单行输入：</td>
			<td><input datatype="varchar" fieldname="sinput" id="sinput" maxlength="20" name="sinput" title="单行输入" type="text" xtype="xinput" /></td>
			<td>下拉列表：</td>
			<td><select datatype="text" defaultvalue="C" fieldname="sselect" id="sselect" name="sselect" title="下拉列表" xtype="xselect"><option value="A">A</option><option value="B">B</option><option selected="selected" value="C">C</option></select></td>
		</tr>
		<tr>
			<td>富文本：</td>
			<td colspan="3" rowspan="1"><img datatype="text" fieldname="uedit" height="144" higth="50%" id="uedit" name="uedit" src="/tfd/system/styles/images/workflow/uedit.png" title="富文本" type="text" width="144" xtype="xtextuedit" /></td>
		</tr>
		<tr>
			<td>单选：</td>
			<td><input datatype="text" fieldname="aradio" id="aradio" name="aradio" title="单选" type="radio" value="A" xtype="xradio" />A<input datatype="text" fieldname="aradio" id="aradio" name="aradio" title="单选" type="radio" value="B" xtype="xradio" />B</td>
			<td>复选</td>
			<td><input checked="checked" datatype="text" fieldname="A" id="A" maxlength="2" name="A" title="复选" type="checkbox" value="1" xtype="xcheckbox" />复选1<input checked="checked" datatype="text" fieldname="B" id="B" maxlength="2" name="B" title="复选1" type="checkbox" value="1" xtype="xcheckbox" />复选2</td>
		</tr>
		<tr>
			<td>流程选择：</td>
			<td><img datatype="text" fieldname="selworkflow" id="selworkflow" model="1" name="selworkflow" src="/tfd/system/styles/images/workflow/workflow.png" title="流程选择" value="1" xtype="xworkflow" /></td>
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
</head></body>
</html>
