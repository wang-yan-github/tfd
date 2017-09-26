<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>信息服务-信息分类</title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/infoservice/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" href="<%=contextPath%>/infoservice/classfication/css/classfication-tree.css"/>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/infoservice/classfication/js/index.logic.js"></script>

<style>
	#body{width:80%;margin-top:20px;}
	.dropdown-menu-parent{width:100%;display:none;}
	.dropdown-menu-parent-list{width: 100%;}
	#form-add-tree,#form-update-tree{width:90%;max-height:300px;margin-left:10%;
		overflow-x:hidden;overflow-y:auto;
	}
	
		
	#alert-add-success,
	#alert-add-failed,
	#alert-update-success,
	#alert-update-failed,
	#alert-delete-success,
	#alert-delete-failed,
	#alert-tree-list-unselected
	{
		width:400px;height:80px;position:absolute;
		left:50%;margin-left:-200px;top:50%;margin-top:-40px;
		z-index:10001;
		text-align:center;display:none;
	}
	#alert-tree-list-unselected,
	#alert-delete-success,
	#alert-delete-failed
	{position: fixed;}
	
	#alert-tree-list-message{display:none;text-align:center;}
	.classfication-name{overflow:hidden;}
	
	.tooltip{z-index:20000 !important;}
	
	.tree-node-opt-bar{display:none;}
	a:hover .tree-node-opt-bar{display:block;}
</style>
</head>

<body>
	<div class="container" id="body">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="h4">信息分类</div>
			</div>
			<div class="panel-body">
				<div id="opt-bar">
					<button type="button" class="btn btn-primary" id="opt-new">新建</button>
					<button type="button" class="btn btn-primary" id="opt-edit">编辑</button>
				</div>
				<ul class="classfication-tree" id="tree-list"></ul>
				<div id="alert-tree-list-message" class="alert alert-info">
					<div class="h3">尚无分类，请添加！</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="modal-new-panel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="h4">新建分类</div>
				</div>
				<div class="modal-body">
					<div id="alert-add-success" class="alert alert-success">
						<div class="h3">添加成功！</div>
					</div>
					<div id="alert-add-failed" class="alert alert-danger">
						<div class="h3">添加失败！</div>
					</div>
					<form class="form-horizontal" id="form-add">
						<input type="hidden" name="id"/>
						<div class="form-group">
							<label for="name" class="col-xs-2 control-label">类别名称</label>
							<div class="col-xs-8">
								<input class="form-control" name="name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="parent" class="col-xs-2 control-label">父级</label>
							<div class="col-xs-8">
								<input type="hidden" class="form-control" name="parent" value="0"/>
								<input type="hidden" class="form-control" name="navigation" value="0"/>
								<input class="form-control" name="parentName" value="根目录" readonly="readonly"/>
								<div class="dropdown" id="dropdown-parent">
									<button class="btn btn-default dropdown-toggle dropdown-menu-parent" 
										data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									</button>
									
									<ul class="dropdown-menu dropdown-menu-parent-list" aria-labelledby="dropdown-menu-parent">
										<li>
											<ul id="form-add-tree" class="ztree"></ul>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="sort" class="col-xs-2 control-label">排序</label>
							<div class="col-xs-8">
								<input class="form-control" name="sort" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-xs-2 control-label">备注</label>
							<div class="col-xs-8">
								<textarea class="form-control" rows="3" name="remark"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-xs-10">
								<button class="btn btn-primary" type="submit">确定</button>
								<button class="btn btn-default" data-dismiss="modal">取 消</button>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="modal-edit-panel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="h4">编辑分类</div>
				</div>
				<div class="modal-body">
					<div id="alert-update-success" class="alert alert-success">
						<div class="h3">修改成功！</div>
					</div>
					<div id="alert-update-failed" class="alert alert-danger">
						<div class="h3">修改失败！</div>
					</div>
					<form class="form-horizontal" id="form-update">
						<input type="hidden" name="id"/>
						<div class="form-group">
							<label for="name" class="col-xs-2 control-label">类别名称</label>
							<div class="col-xs-8">
								<input class="form-control" name="name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="parent" class="col-xs-2 control-label">父级</label>
							<div class="col-xs-8">
								<input type="hidden" class="form-control" name="parent" value="0"/>
								<input type="hidden" class="form-control" name="navigation" value="0"/>
								<input class="form-control" name="parentName" value="根目录" readonly="readonly"/>
								<div class="dropdown" id="dropdown-parent">
									<button class="btn btn-default dropdown-toggle dropdown-menu-parent" 
										data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									</button>
									
									<ul class="dropdown-menu dropdown-menu-parent-list" aria-labelledby="dropdown-menu-parent">
										<li>
											<ul id="form-update-tree" class="ztree"></ul>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="sort" class="col-xs-2 control-label">排序</label>
							<div class="col-xs-8">
								<input class="form-control" name="sort" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-xs-2 control-label">备注</label>
							<div class="col-xs-8">
								<textarea class="form-control" rows="3" name="remark"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-xs-10">
								<button class="btn btn-primary" type="submit">确定</button>
								<button class="btn btn-default" data-dismiss="modal">取 消</button>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="alert-tree-list-unselected" class="alert alert-warning">
		<div class="h3">请选中层级！</div>
	</div>
	
	<div id="alert-delete-success" class="alert alert-success">
		<div class="h3">已删除！</div>
	</div>
	<div id="alert-delete-failed" class="alert alert-warning">
		<div class="h3">删除失败！</div>
	</div>
	
	
	
</body>
</html>