<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜单添加</title>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
	<link rel="stylesheet" href="<%=stylePath%>/menu/menu.css"/>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/menu/js/add.js"></script>
	
	<style>
		.menuContent{
			height:300px;
			position: absolute; z-index: 1000;
			overflow:auto;
			display: none;
			background-color: #F5F5F5; 
			border:solid 1px #ccc;
		}
	</style>
</head>
<body>
	<form id="menu-form" name="menu-form" class="form-horizontal">
		<div style="width: 70%;margin:0px auto;margin-top:10px;">
			
			<div align="center" class="widget">
				<div class="widget-header bordered-bottom bordered-sky">
					<span class="widget-caption">添加菜单</span>
				</div>
				<table class="table table-striped  table-condensed table-bordered">
					<tr>
						<td width="150px">菜单名称：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input id="sysMenuName" name="sysMenuName" class="form-control"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>菜单编码：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input id="sysMenuCode" name="sysMenuCode" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>所属层级：</td>
						<td>
							<div class="col-xs-6">
								<input type="hidden" id="sysMenuLeave" name="sysMenuLeave" class="form-control" /> 
								<input id="sysMenuLeaveName" name="sysMenuLeaveName" readonly="readonly" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>菜单图片：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input id="sysMenuPic" name="sysMenuPic" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>菜单URL：</td>
						<td>
							<div class="col-xs-6">
								<input id="sysMenuUrl" name="sysMenuUrl" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>菜单参数：</td>
						<td>
							<div class="col-xs-6">
								<input id="sysMenuParm" name="sysMenuParm" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>打开发式：</td>
						<td>
							<div class="col-xs-6">
								<select id="sysMenuOpen" name="sysMenuOpen"
									class="form-control">
									<option value="1">本窗口打开</option>
									<option value="2">新页面打开</option>
								</select>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: center;">
				<button type="submit" class="btn btn-primary">添加</button>
			</div>
	</form>
	
	<div id="menuContent" class="menuContent">
		<ul id="menuTree" class="ztree"></ul>
	</div>
</body>
</html>