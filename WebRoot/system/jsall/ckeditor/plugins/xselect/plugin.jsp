<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>下拉列表控件</title>
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
	<style>
		#xselect-content{width:100%;margin-top:10px;margin-bottom:10px;}
		#xselect-content thead td{text-align: center;font-weight: bold;}
		#xselect-content tbody td{text-align: center;}
		#xselect-content input[name='select-value']{width:120px;}
		
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
				<td class="title">字段名</td>
				<td>
					<div class="form-group">
						<input id="fieldName" name="fieldName" class="form-control" />
					</div>
				</td>
			</tr>
		</table>
		<table id="xselect-content" class="table">
			<thead>
				<tr>
					<td  class="title" style="text-align: left;">单选项</td>
					<td class="title">默认选中</td>
					<td class="title">操作</td>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<a href='javascript:void(0)' id="select-item-add">添加数据项</a>
	</form>
</body>
</html>