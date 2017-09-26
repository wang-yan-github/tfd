<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/infoservice/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css"></link>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/infoservice/org/audit/js/index.logic.js"></script>


<style>

	#body{width:80%;}
	#searchText{width:200px;}
	#top-bar{padding:10px 0px 10px 0px;}
	.row-opt-bar{text-align:center;}
	.row-opt-bar .row-opt{color:blue;}
	.row-opt-bar .row-opt:hover{text-decoration:underline;cursor:pointer;}
	
	#form-group-searchtext{margin-left:0px;}
</style>
</head>

<body>
	
	<br/>
	<div id="body" class="container">
		<div id="top-bar">
			<form class="form-inline">
				<div class="form-group" id="form-group-searchtext">
					<input class="form-control input-sm" name="searchText" id="searchText" placeholder="企业名称"/>
					<button type="submit" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-search"></span>
					</button>
					<button type="button" class="btn btn-primary btn-sm" id="search-advanced">
						高级查询
					</button>
				</div>
			</form>
		</div>
		<div>
			<table id="org-list"></table>
		</div>
	</div>
	
	
	<div class="modal" id="modal-search-advanced">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="h4">高级查询</div>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="orgName" class="col-xs-2 control-label">企业名称</label>
							<div class="col-xs-8">
								<input class="form-control" name="orgName"/>
							</div>
						</div>
						<div class="form-group">
							<label for="orgAdd" class="col-xs-2 control-label">企业地址</label>
							<div class="col-xs-8">
								<textarea class="form-control" name="orgAdd" rows="2"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="orgTel" class="col-xs-2 control-label">联系电话</label>
							<div class="col-xs-8">
								<input class="form-control" name="orgTel"/>
							</div>
						</div>
						<div class="form-group">
							<label for="orgEmail" class="col-xs-2 control-label">企业邮箱</label>
							<div class="col-xs-8">
								<div class="input-group">
									<div class="input-group-addon">@</div>
									<input class="form-control" name="orgEmail"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-xs-10">
								<button class="btn btn-primary" type="submit">确定</button>
								<button class="btn btn-default" data-dismiss="modal" type="button">取 消</button>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>