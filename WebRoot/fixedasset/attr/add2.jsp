<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资产入库</title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/jquery/ztree/css/zTreeStyle/zTreeStyle.css"/>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sys.js"></script>
<script type="text/javascript" src="<%=contextPath %>/fixedasset/attr/js/add2.logic.js"></script>
<script type="text/javascript" src="<%=contextPath %>/fixedasset/attr/js/logic.js"></script>

<style>
	#body{max-width:800px;margin-top:10px;}
	#head-title{text-align:center;}
	#opt-bar{text-align:center;}
	#asset-type-tree{height:300px;overflow:auto;}
	
	#body label.control-label{font-size:13px;}
	
	#alert-add-success,#alert-add-failed{
		width:400px;height:80px;position:fixed;
		left:50%;margin-left:-200px;top:50%;margin-top:-40px;
		z-index:10001;
		text-align:center;display:none;
	}
</style>
</head>

<body>

	<div class="container" id="body">
		<form id="asset-form" class="form-horizontal">
			<div class="panel panel-info">
				<div class="panel-heading h3">
					基本信息
					<small><span style="color:red;">*</span>必填</small>
				</div>
				<div class="panel-body">
				  <div class="form-group">
				  	<label for="FixedassetAttr_assetNo" class="col-xs-2 control-label">
				  		<span style="color:red;">*</span>
				  		资产编号:
			  		</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetAttr_assetNo" id="FixedassetAttr_assetNo"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_assetName" class="col-xs-2 control-label">资产名称:</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetAttr_assetName" id="FixedassetAttr_assetName"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_standard" class="col-xs-2 control-label">规格型号:</label>
				    <div class="col-xs-8">
				      <textarea class="form-control" rows="3" name="FixedassetAttr_standard" id="FixedassetAttr_standard"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_manufacture" class="col-xs-2 control-label">生产厂家:</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetAttr_manufacture" id="FixedassetAttr_manufacture"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_exFactoryDate" class="col-xs-2 control-label">出厂日期:</label>
				    <div class="col-xs-8">
				    	<input class="form-control form_date" name="FixedassetAttr_exFactoryDate" id="FixedassetAttr_exFactoryDate"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_serviceLife" class="col-xs-2 control-label">使用年限:</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetAttr_serviceLife" id="FixedassetAttr_serviceLife"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_unitPrice" class="col-xs-2 control-label">单价:</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetAttr_unitPrice" id="FixedassetAttr_serviceLife"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetAttr_remark" class="col-xs-2 control-label">备注:</label>
				    <div class="col-xs-8">
				      <textarea class="form-control" rows="3" name="FixedassetAttr_remark" id="FixedassetAttr_remark"></textarea>
				    </div>
				  </div>
				</div>
				<div class="panel-heading h3">其他信息</div>
				<div class="panel-body">
				  <div class="form-group">
				  	<label for="FixedassetRelation_assetType" class="col-xs-2 control-label">资产类别:</label>
				    <div class="col-xs-8">
				      <input type="hidden" name="FixedassetRelation_assetType" id="FixedassetRelation_assetType"/>
				      <input class="form-control" id="FixedassetRelation_assetTypeName" readonly="readonly"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_number" class="col-xs-2 control-label">数量/计量单位:</label>
				    <div class="col-xs-4">
				      <input class="form-control" name="FixedassetRelation_number" id="FixedassetRelation_number"/>
				    </div>
				    <div class="col-xs-4">
				      <input class="form-control" name="FixedassetRelation_unit" id="FixedassetRelation_unit"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_netSalvage" class="col-xs-2 control-label">净残值率:</label>
				    <div class="col-xs-8">
				      <input class="form-control" name="FixedassetRelation_netSalvage" id="FixedassetRelation_netSalvage"/>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_assetSource" class="col-xs-2 control-label">资产来源:</label>
				    <div class="col-xs-8">
				      <textarea class="form-control" rows="2" name="FixedassetRelation_assetSource" id="FixedassetRelation_assetSource"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_useSituation" class="col-xs-2 control-label">使用情况:</label>
				    <div class="col-xs-8">
				      <textarea class="form-control" rows="3" name="FixedassetRelation_useSituation" id="FixedassetRelation_useSituation"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_storageLocation" class="col-xs-2 control-label">存放地点:</label>
				    <div class="col-xs-8">
				      <textarea class="form-control" rows="2" name="FixedassetRelation_storageLocation" id="FixedassetRelation_storageLocation"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label for="FixedassetRelation_postingDate" class="col-xs-2 control-label">入库日期:</label>
				    <div class="col-xs-8">
				    	<input class="form-control form_date" name="FixedassetRelation_postingDate" id="FixedassetRelation_postingDate"/>
				    </div>
				  </div>
				</div>
				<div class="panel-heading" id="opt-bar">
					<button class="btn btn-default btn-lg" type="submit">添加</button>
				</div>
			</div>
		</form>
	</div>
	
	
	<div class="modal fade" id="modal-asset-type">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	      		<span aria-hidden="true">&times;</span>
      		</button>
	        <h4 class="modal-title">资产类别选择</h4>
	      </div>
	      <div class="modal-body">
			<div class="ztree" id="asset-type-tree"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div id="alert-add-success" class="alert alert-success fade in">
		<div class="h3">添加成功！</div>
	</div>
	<div id="alert-add-failed" class="alert alert-danger fade in">
		<div class="h3">添加失败！</div>
	</div>
	
	
	
</body>
</html>