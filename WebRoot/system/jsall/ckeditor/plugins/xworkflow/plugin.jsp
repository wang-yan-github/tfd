<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>流程选择控件</title>
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
				<td class="title">选择方式</td>
				<td>
					<div class="form-group">
						<select id="model" name="model" class="form-control">
							<option value="1">选择我发起的流程</option>
							<option value="2">选择我管理的流程</option>
							<option value="3">选择我经办的流程</option>
							<option value="4">本流程相关的流程</option>
							<option value="5">与当前流程相同的</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
</body>

</html>