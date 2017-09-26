<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>App权限设置</title>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery.json.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/apppower/js/addapp.js"></script>
</head>
<body>
	<div class="list-group-item"
		style="padding: 0px; cursor: auto; width: 60%; margin-left: 20%; margin-top: 10px;">
		<a style="cursor: auto;" class="list-group-item active">App权限设置</a>
		<table class="table table-striped">
			<tr>
				<td width="15%">选择人员：</td>
				<td>
					<div class="col-xs-10 form-group">
						<input name="accountId" id="accountId" type="hidden"/>
						<textarea rows="3" cols="40" name="userName" id="userName" readonly="readonly" class="form-control"></textarea>
					</div>
					<div style="margin-top: 30px;">
						<a href="javascript:void(0);" onclick="userinit(['accountId','userName']);">添加人员</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>选择部门：</td>
				<td>
					<div class="col-xs-10 form-group">
						<input name="deptId" id="deptId" type="hidden"/>
						<textarea rows="3" cols="40" name="deptName" id="deptName" readonly="readonly" class="form-control"></textarea>
					</div>
					<div style="margin-top: 30px;">
						<a href="javascript:void(0);" onclick="deptinit(['deptId','deptName']);">添加部门</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>选择角色：</td>
				<td>
					<div class="col-xs-10 form-group">
						<input id="userPriv" name="userPriv" type="hidden"/> 
						<textarea rows="3" cols="40" name="userPrivName" id="userPrivName" readonly="readonly" class="form-control"></textarea>
					</div> 
					<div style="margin-top: 30px;">
						<a href="javascript:void();" onclick="privinit(['userPriv','userPrivName']);">添加角色</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>选择权限：</td>
				<td id="appicon"></td>
			</tr>
		</table>
	</div>
	<div align="center" style="margin-top: 10px;">
		<button type="button" class="btn btn-primary" id="ok">确定</button>
		<button type="button" class="btn btn-default">重置</button>
	</div>
	<div id="modaldialog"></div>
</body>
</html>