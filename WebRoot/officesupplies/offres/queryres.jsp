<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品查询</title>
<script type="text/javascript" charset="utf-8"
	src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/officesupplies/offres/js/queryres.js"></script>

<style type="text/css">
#bigdiv {
	width: 80%;
}
</style>
</head>
<body class="bodycolor">
	<div align="center">
		<div id="bigdiv">
			<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-top: 20px;">
<a style="cursor: auto;" class="list-group-item active">办公用品查询</a>
					<form enctype="multipart/form-data" id="form1" name="form1">
						<table class="table table-striped table-condensed">
							<tr>
								<td >办公用品库：</td>
								<td >
									 <div class="col-xs-8">
<select id="libraryId" name="libraryId" class="form-control " onchange="getlibclassify();">
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
							</tr>
							<tr>
								<td>办公用品类别：</td>
								<td>
									<div class="col-xs-8">
<select id="classifyId" name="classifyId" class="form-control "  onchange="getresName();">
<option selected="selected" value="">请选择</option>
</select>
</div>
								</td>
							</tr>
							
							<tr>
								<td >办公用品：</td>
								<td>
									<div class="col-xs-8">
<select id="resId" name="resId" class="form-control " >
<option selected="selected" value="">请选择</option>
</select>
</div>
								</td>
							</tr>
							<tr>
								<td >办公用品名称：</td>
								<td>
									<div class="col-xs-8">
								<input type="text" id="resName" name="resName" class="form-control"/>
</div>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2"><input
									type="button" value="查询" class="btn btn-primary"
									onclick="queryres();">&nbsp;&nbsp; <input type="button"
									value="导出" class="btn">&nbsp;&nbsp;
									
									</td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</div>
	<div id="table" style="display: none;margin-top: 10px;">
		<div style="margin-left:1%; height: 40px;">
			<input type="button" value="返回查询页面" class="btn btn-primary"
				onclick="returnquery();">
		</div>
		<div id="myTable" name="myTable"></div>
	</div>
</body>
</html>