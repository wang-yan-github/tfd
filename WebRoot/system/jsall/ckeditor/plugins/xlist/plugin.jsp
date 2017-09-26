<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明细表</title>
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
		
		#xlist{width:60%;}
		#xlist td.title{width:120px;}
		
		#xlist-content{margin-top:10px;}
		#xlist-content thead td{font-weight:bold;font-size:12px;text-align: center;}
		#xlist-content thead .field-sum{width:60px;}
		#xlist-content thead .field-type{width:100px;}
		#xlist-body td{padding-left:2px;}
		
		#xlist-body *[name|='sum']{widht:16px;height:16px;}
	</style>
</head>
<body>
	<form>
		<table id="xlist">
			<tr>
				<td class="title">列表标题</td>
				<td>
					<div class="form-group">
						<input id="title" name="title" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">列表名称</td>
				<td>
					<div class="form-group">
						<input id="childtable" name="childtable" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">列表宽度</td>
				<td>
					<div class="form-group">
						<input id="width" name="width" class="form-control" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">表头单元格样式</td>
				<td>
					<div class="form-group">
						<textarea id="header" name="header" class="form-control"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">数据单元格样式</td>
				<td>
					<div class="form-group">
						<textarea id="data" name="data" class="form-control"></textarea>
					</div>
				</td>
			</tr>
		</table>
		<div id='xlist-content'></div>
		<button type="button" class="btn btn-link" id="xlist-item-add">添加表项目</button>
	</form>
</body>
</html>