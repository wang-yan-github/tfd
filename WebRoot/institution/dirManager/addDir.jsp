<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制度中心</title>
</head>

<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/institution/dirManager/js/addDir.logic.js"></script>
<style>
	html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	#dirContent{
		width: 300px;
		height: 300px;
		overflow: auto;
		display: none;
		position: absolute;
		border: solid #CCC 1px;
		background-color:white; 
		z-index: 999;
	}
	#content{width:60%;margin-left:20%;margin-top:40px;}
</style>

<body>
		<div id="dirContent" >
		<table class="TableBlock no-top-border" style="width: 100%;">
			<tr>
				<td class="TableData"><div><ul id="treeDemo" class="ztree"></ul></div></td>
			</tr>
		</table>
	</div>
	<div id="content"  >
<form id="form1" name="form1" class="form-horizontal" >


<div class="panel panel-info">
   <div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         目录信息
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="20%">目录名称：</td>
<td>
			<div class="col-xs-9 form-group" ><input class="form-control " type="text" id="directory" name="directory"/></div></td>
</tr>
<tr>
<td width="20%">所属层级：</td>
<td><input type="hidden" id="directory_id" />
			<div class="col-xs-9 form-group" ><input type="text" class="form-control " readonly="readonly" id="directory_Name" name="directory_Name"  onclick="showDir();"/></div></td>
</tr>
</table>
</div>
</div>
	</div>
	 <div align="right">
<input type="submit" style="width: 50px;" class="btn btn-success" title="添加"  value="添加" ></input>
				<button style="width: 50px;" class="btn btn-default" title="返回" onclick="back()" >返回</button>
   </div>

	</form>
	   </div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: 'Pas valide',
		 container: 'tooltip',
		 feedbackIcons: {
		     valid: 'glyphicon glyphicon-ok',
		     invalid: 'glyphicon glyphicon-remove',
		     validating: 'glyphicon glyphicon-refresh'
		 },
		fields: {
			directory: {
	            validators: {
	            	container: 'popover',
	                notEmpty: {
	                    message: '目录名称不能为空!'
	                }
	            }
	        }
		}
	}).on('success.form.bv',function(e){
		 e.preventDefault();
	     var $form = $(e.target);
	     var bv = $form.data('bootstrapValidator');

	     add();
	});
});
</script>
</html>