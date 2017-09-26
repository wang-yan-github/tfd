<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询会议室设备</title>
<script type="text/javascript" charset="utf-8"
	src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/boardroomdevice/js/queryboardroomdevice.js"></script>
</head>
<body class="bodycolor">
	<div align="center">
		<div id="bigdiv" style="width: 70%;">
			<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-top: 10px;">
<a style="cursor: auto;" class="list-group-item active">会议设备查询</a>
					<form enctype="multipart/form-data" id="form1" name="form1">
						<table class="table table-striped table-condensed">
							<tr>
								<td nowrap class="TableData title">设备编号：</td>
								<td><div class="col-xs-5">
										<input type="text" id="deviceId" name="deviceId" class="form-control "  >
										</div></td>
							</tr>
							<tr>
								<td >设备名称：</td>
								<td >
									<div class="col-xs-5">
							<input type="text" id="deviceName" name="deviceName" class="form-control "  ></div>
								</td>
							</tr>
							<tr>
								<td >设备类型</td>
								<td >
									<div class="col-xs-5">
							<input type="text" id="deviceType" name="deviceType" class="form-control "  ></div>
								</td>
							</tr>
							<tr>
								<td >设备状态：</td>
								<td >
									<div class="col-xs-5">
									<select id="deviceStatus" name="deviceStatus" class="form-control ">
									<option value="" selected="selected">全部</option>
									<option value="1" >可用</option>
									<option value="0" >不可用</option>
									</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>所属会议室</td>
								<td >
																		<div class="col-xs-5">
									<select id="boardroomId" name="boardroomId" class="form-control ">
									<option value="" selected="selected">全部</option>
									</select>
									</div>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2" ><input
									type="button" value="确定" class="btn btn-primary"
									onclick="queryboardroomdevice();">&nbsp;&nbsp; <input type="reset"
									value="重填" class="btn btn-default">&nbsp;&nbsp;</td>
							</tr>
						</table>
					</form>
			</div>
		</div>
	</div>
	<div id="table" style="display: none;">
		<div>
			<input type="button" value="返回查询页面" class="btn btn-primary"
				onclick="returnquery();">
			<div>&nbsp;</div>
		</div>
		<div id="myTable" name="myTable"></div>
	</div>
</body>
</html>