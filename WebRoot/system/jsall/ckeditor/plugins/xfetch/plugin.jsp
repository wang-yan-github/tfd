<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>选择器控件</title>
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
				<td class="title">名称</td>
				<td>
					<div class="form-group">
						<input id="title" name="title" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">字段名称</td>
				<td>
					<div class="form-group">
						<input id="fieldName" name="fieldName" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">控件类型</td>
				<td>
					<div class="form-group">
						<select id="type" name="type" class="form-control">
							<option value="1">日期选择器</option>
							<option value="2">时间选择器</option>
							<option value="3">部门选择器</option>
							<option value="4">部门选择器[多部门]</option>
							<option value="5">角色选择器</option>
							<option value="6">角色选择器[多角色]</option>
							<option value="7">人员选择器</option>
							<option value="8">人员选择器[多人员]</option>
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
						<textarea id="style" name="style" class="form-control" style="width: 100%; height: 70px"></textarea>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>