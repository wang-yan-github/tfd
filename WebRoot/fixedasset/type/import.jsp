<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产类别导入</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fixedasset/type/js/import.logic.js"></script>
<style>
	html,body{
		height:100%;margin:0px;padding:0px;
	}
	html{overflow:hidden;}
	#head{width:100%;height:50px;position:absolute;top:30%;margin-top:-25px;}
	#import-box{position:absolute;left:50%;margin-left:-200px;}
	#import-file{width:400px;}
	.padding-top-5{padding-top:5px;}
	.padding-left-10{padding-left:10px;}
	#opt-box{text-align:center;}
	
	#back-box{position:absolute;left:10px;top:10px;}
	
	#protective-screen{
		width:100%;height:100%;
		position:absolute;
		background-color:#fff;
		
		filter: alpha(opacity =   90);
		-moz-opacity: 0.9;
		-khtml-opacity: 0.9;
		opacity: 0.9;
		z-index:1001;
		display:none;
	}
	#import-progress{
		width:300px;
		position:absolute;
		top:50%;left:50%;margin-left:-150px;margin-top:-30px;
		z-index:1002;
		background-color:#f2f2f2;
		display:none;
	}
</style>
</head>
<body>
	<div id="back-box">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-back'" id="opt-back">返回</a>
	</div>
	<div id="head">
		<span id="import-box">
			<form id="import-form" action="<%=contextPath %>/fixedasset/act/FixedassetTypeAct/importBatch.act" method="post" enctype="multipart/form-data">
				<input class="easyui-filebox" id="import-file" name="import-file" data-options="buttonText:'选择文件',prompt:'请严格按照模板填写导入文件！',height:25"/>
			</form>
			<div class="padding-top-5"></div>
			<div id="opt-box">
				<a class="easyui-linkbutton" id="opt-template-download">模板下载</a>
				<span class="padding-left-10"></span>
				<a class="easyui-linkbutton" id="opt-import">开始导入</a>
			</div>
		</span>
	</div>
	
	<div class="progress" id="import-progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
	    0%
	  </div>
	</div>
	
	<div id="protective-screen"></div>
</body>
</html>