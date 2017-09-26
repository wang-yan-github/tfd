<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>SQL查询</title>
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/formvalidation/css/formValidation.min.css">
	
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery.json.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/formvalidation/js/formValidation.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/RegexUtil.js"></script>
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
				<td class="title">控件标题</td>
				<td>
					<div class="form-group">
						<input id="title" name="title" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">控件名称</td>
				<td>
					<div class="form-group">
						<input id="fieldName" name="fieldName" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">数据源名称</td>
				<td>
					<div class="form-group">
						<select id="dbSourceId" name="dbSourceId" class="form-control"
						</select>>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">SQL查询语句</td>
				<td>
					<div class="form-group">
						<textarea id="sql" name="sql" class="form-control">
						</textarea>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>