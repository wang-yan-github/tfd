<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产来源处管理</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fixedasset/source/js/index.logic.js"></script>
<style>
	html,body{
		height:100%;margin:0px;padding:0px;
	}
	html{overflow:hidden;}
	
	
	.form-field-c{margin-left:10px;margin-top:10px;}
	.form-field-c td{padding:10px 0px;}
	.form-field-desc{width:100px;font-size:14px;letter-spacing:1px;}
	.form-field-c input.easyui-textbox{width:220px;}
	.form-field-c input.easyui-combo{width:220px;}
	.form-field-c input.easyui-numberbox{width:220px;}
	
	#body{width:80%;height:100%;margin:0px auto;margin-top:10px;}
	.padding-left-10{padding-left:10px;}
	
</style>
</head>
<body>
	<div id="body">
		<div id="tool-bar">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="opt-add">添加</a>
				<span class="padding-left-10"></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="opt-delete">删除</a>
		</div>
		<div id="source-list"></div>
	</div>
	
	<div id="panel-edit">
		<div class="easyui-panel" style="width:100%;height:100%;">
	        <table class="form-field-c" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td class="form-field-desc">
	    				<label for="typeName">资产来源:</label>
	    			</td>
	    			<td class="form-field-desc">
	    				<input class="easyui-textbox easyui-validatebox" id="source" name="source" data-options="height:25,required:true,missingMessage:'请填写！'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="form-field-desc">
	    				<label for="typeName">排序号:</label>
	    			</td>
	    			<td class="form-field-desc">
	    				<input class="easyui-numberbox" id="sort" name="sort" data-options="height:25"/>
	    			</td>
	    		</tr>
	   		</table>
		</div>
	</div>
</body>
</html>