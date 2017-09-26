<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/infoservice/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/district/jquery.fn.district.css">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/main/index.css"></link>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/ckeditor_basic/ckeditor.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/district/jquery.fn.district.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>


<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/api/sys.unit.api.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/api/sys.infoservice.org.api.js"></script>
<script type="text/javascript" src="<%=contextPath%>/infoservice/org/js/edit.logic.js"></script>

<style>
	#body{width:80%;}
	#body-bottom{text-align:center;}
	.service-item-remove{float:right;margin-top:10px;margin-right:10px;}
	.service-item-i{float:left;margin-top:10px;margin-left:10px;}
	.service-item-i .i{font-weight:bold;}
	
	.alert-success,
	.alert-danger,
	.alert-service-item-remove,
	.alert-service-item-remove-failed
	{
		width:300px;text-align:center;display:none;position:fixed;
		top:30%;margin-top:-50px;
		left:50%;margin-left:-150px;
		z-index:20000;
	}
	
	.infoserviceServiceItem{border-top:solid 1px #f2f2f2;border-bottom:solid 1px #f2f2f2;}
	.panel-body,.infoserviceServiceItem{position:relative;}
	.form-screen{
		width:100%;height:100%;position:absolute;top:0px;left:0px;
		z-index:20000;
		border-bottom:solid 1px #fff;
	}
	.form-screen .screen{
		width:100%;height:100%;position:absolute;top:0px;left:0px;
		text-align:center;
		background-color:#f2f2f2;
		z-index:1;
	}
	.form-screen .screen .alert-text{font-size:14px;letter-spacing:1px;font-weight:bold;line-height:50px;}
	.form-screen .top-bar{
		width:100%;position:absolute;top:0px;left:0px;
		z-index:10;
	}
	.form-screen .top-bar{line-height:50px;}
	.form-screen .top-bar .form-edit{float:right;margin:10px 10px 0px 0px;}
	.form-screen .top-bar .service-item-i{margin:0px;margin-left:25px;line-height:50px;}
	
	.form-save-bar{display:none;}
	
	#service-area-all{border-bottom:solid 1px #f2f2f2;padding-bottom:10px;}
	.service-area-box{width:100%;padding-top:10px;display:block;}
	.service-area{width:300px;display:inline-block;}
	
	.panel-form-edit-slideup{height:50px;overflow:hidden;}
	
	.form-save-bar{text-align:center;}
	
	#service-item-opt-bar{margin:15px;margin-bottom:0px;}
	
	
	.form-screen[value='#unit'] .form-edit{margin-right:25px;}
	
	.item-delete-popover{position:absolute;z-index:20001;}
</style>
</head>

<body>
	<br/>
	<div id="body" class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="h4">企业基本资料</div>
			</div>
			<div class="panel-body panel-form-edit panel-form-edit-slideup" value="#unit">
				<div class="form-screen" value="#unit">
					<div class="top-bar">
						<button class="form-edit btn btn-primary" value="#unit">
							<span class="glyphicon glyphicon-pencil"></span>
							编辑
						</button>
					</div>
					<div class="screen">
						<span class="alert-text">点击编辑详情。。。</span>
					</div>
				</div>
				<form class="form-horizontal" id="unit">
					<input type="hidden" name="orgId"/>
					<input type="hidden" name="orgPost"/>
					<input name="infoserviceOrg_businessLicense" type="hidden"/>
					<input name="infoserviceOrg_orgCodeCertificate" type="hidden"/>
					<input name="infoserviceOrg_taxRegistrationCertificate" type="hidden"/>
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
						<label for="orgFax" class="col-xs-2 control-label">企业传真</label>
						<div class="col-xs-8">
							<input class="form-control" name="orgFax"/>
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
						<label for="infoserviceOrg_orgIntroduce" class="col-xs-2 control-label">企业介绍</label>
						<div class="col-xs-8">
							<textarea class="form-control" name="infoserviceOrg_orgIntroduce"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">服务区域</label>
						<div class="col-xs-8">
							<input class="form-control" name="infoserviceOrg_serviceArea" type="hidden"/>
							<div>
								<button type="button" class="btn btn-sm btn-primary" id="service-area-add">添加</button>
							</div>
							<div id="service-area-all">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">营业执照</label>
						<div class="col-xs-8" id="infoserviceOrg_businessLicense-input">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">组织机构代码证</label>
						<div class="col-xs-8" id="infoserviceOrg_orgCodeCertificate-input">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">税务登记证</label>
						<div class="col-xs-8" id="infoserviceOrg_taxRegistrationCertificate-input">
						</div>
					</div>
					<div class="form-group form-save-bar">
						<div class="col-xs-12">
							<button type="submit" class="btn btn-primary">保存</button>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-primary form-slide" value="#unit">收起</button>
						</div>
					</div>
				</form>
			</div>
		
			<div class="panel-heading">
				<div class="h4">
					企业服务项
				</div>
			</div>
			<div id="service-item-opt-bar">
				<button class="btn btn-primary" id="service-item-add">添加服务项</button>
			</div>
			<div class="panel-body" id="service-items">
			</div>
		</div>
	</div>
	
	
	<div class="alert alert-success" id="alert-success">
		<div class="h3">已保存！</div>
	</div>
	<div class="alert alert-danger" id="alert-danger">
		<div class="h3">无法保存！</div>
	</div>
	<div class="alert alert-success" id="alert-service-item-remove">
		<div class="h3">已删除！</div>
	</div>
	<div class="alert alert-danger" id="alert-service-item-remove-failed">
		<div class="h3">删除失败！</div>
	</div>
</body>
</html>