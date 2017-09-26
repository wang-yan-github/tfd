<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp" %>  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>宏控件</title>
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/formvalidation/css/formValidation.min.css">
	
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery.json.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/formvalidation/js/formValidation.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/RegexUtil.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery.json.js"></script>
	<script type="text/javascript" src="plugin.logic.js"></script>
	<style>
		form{margin:10px;}
		table{width: 100%;}
		table td{padding-top:5px;}
		table td.title{text-align: right;padding-right:5px;font-size:12px;font-weight:bold;}
	</style>
</head>
<body>
	<form>
		<table>
			<tr>
				<td class="title">控件名称</td>
				<td>
					<div class="form-group">
						<input id="title" name="title" class="form-control"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">字段名称</td>
				<td>
					<div class="form-group">
						<input id="fieldName" name="fieldName" class="form-control"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">控件类型</td>
				<td>
					<div class="form-group">
						<select id="type" name="type" class="form-control">
							<option value="1">当前年份</option>
							<option value="2">当前日期</option>
							<option value="3">当前时间</option>
							<option value="5">流程名称</option>
							<option value="6">流程编号</option>
							<option value="7">流程发起人帐号</option>
							<option value="8">流程发起人姓名</option>
							<option value="9">流程发起人部门</option>
							<option value="10">流程发起人角色</option>
							<option value="11">当前用户帐号</option>
							<option value="12">当前用户姓名</option>
							<option value="13">当前用户部门</option>
							<option value="14">当前用户长部门</option>
							<option value="15">当前用户角色</option>
							<option value="16">当前用户IP</option>
							<option value="17">流程发起人辅助部门选择</option>
							<option value="18">流程发起人辅助角色选择</option>
							<option value="19">当前用户辅助部门选择</option>
							<option value="20">当前用户辅助角色选择</option>
							<option value="21">流程文号</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">显示格式</td>
				<td>
					<div class="form-group">
						<select id="format" name="format" class="form-control">
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">控件样式</td>
				<td>
					<div class="form-group">
						<textarea id="style" name="style" class="form-control"></textarea>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>