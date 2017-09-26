<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<%
	Boolean insertResult=request.getAttribute("insertResult")==null?null:(Boolean)request.getAttribute("insertResult");
	JSONArray resultData=request.getAttribute("resultData")==null?null:(JSONArray)request.getAttribute("resultData");
%>
<html>
<head>
    <title>数据导入</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
  	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/api/dataimport/js/index.logic.js"></script>
  	<script>
  		insertResult=<%=insertResult%>;
  		resultData=<%=resultData==null?null:resultData.toString()%>;
  	</script>

    <style>
      #import-form input[name='fileshow']{width:350px;}
      #import-form input[name='file']{display:none;}
      #other-error,#result-data{display:none;}
      #column-name td{font-weight:bold;white-space:nowrap; }
      #column-data td{white-space: nowrap;}
      #data-require{margin-top:20px;position:relative;overflow:hidden;}
      #data-require .expand{position:absolute;right:5px;top:5px;cursor:pointer;}
      #data-require .expand.expand-down{display:none;}
      #data-require table{margin-top:10px;}
      #import-alert{margin-top:20px;}
     
    </style>
</head>
<body>
  <br/>
  <div class="container" id="body">
    <div class="north">
      <form class="form-inline" id="import-form" 
      		action="<%=contextPath%>/api/dataimport/DataImportAct/dataImport.act" method="post" enctype="multipart/form-data">
        <div class="row">
        	<div class="col-xs-8 col-xs-offset-2">
	        	<span class="h3">学生信息导入</span>
	        	<a id="template-download" href="<%=contextPath%>/api/dataimport/DataImportAct/templateDownload.act">下载模板</a>
        	</div>
       	</div>
       	<br/>
      	<div class="row">
	        <div class="col-xs-8 col-xs-offset-2">
	        	<div class="form-group">
		          <input class="form-control" placeholder="请选择文件（xls、xlsx）" name="fileshow" readonly="readonly"/>
		          <input type="file" name="file"/>
		        </div>
		        <div class="form-group">
			        <button type="submit" class="btn btn-primary">导入</button>
		        </div>
	        </div>
      	</div>
      </form>
    </div>
    
    <div class="alert bg-info " id="data-require">
		<div class="expand expand-up" title="收起">
			<span class="glyphicon glyphicon-chevron-up"></span>
		</div>
		<div class="expand expand-down" title="展开">
			<span class="glyphicon glyphicon-chevron-down"></span>
		</div>
		<div class="h4">导入要求</div>
    	<table class="table">
    	</table>
    </div>
    
    <div class="center" id="import-alert">
    	<div class="row">
        	<div class="col-xs-8">
	        	<span class="h3" id="import-alert-title"></span>
        	</div>
       	</div>
       	<br/>
    	<div id="other-error" class="alert alert-danger"></div>
    	<div id="result-data">
    		<table class="table">
    			<tbody id="column-name"></tbody>
    			<tbody id="column-data"></tbody>
    		</table>
    	</div>
    </div>
  </div>
</body>
</html>
