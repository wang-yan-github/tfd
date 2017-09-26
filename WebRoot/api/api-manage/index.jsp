<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys api</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
    <link rel="stylesheet" href="<%=contextPath%>/api/api-manage/css/ztree/zTreeStyle.css"/>


    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/api/api-manage/js/index.logic.js"></script>

    <style>
    	html,body{width:100%;height:100%;margin:0px;padding:0px;}
    	html{overflow:hidden;}
        #body{width:100%;height:100%;}
        #east{position:absolute;left:10px;top:10px;bottom:10px;width:200px;background-color:#f2f2f2;}
        #top{position:absolute;left:220px;top:10px;right:10px;height:50px;background-color:#f2f2f2;line-height:50px;}
        #center{position:absolute;left:220px;top:70px;bottom:10px;right:10px;background-color:#f2f2f2;z-index:20;}
        #api-detail{position:absolute;left:220px;top:70px;bottom:10px;right:10px;background-color:#f2f2f2;z-index:30;display:none;}
        #api-text{width:100%;height:100%;}
        #api-detail .top-bar{width:100%;height:50px;line-height:50px;position:absolute;top:0px;left:0px;}
        #api-detail-close{float:right;}
        #center-frame{width:100%;height:100%;}
        #api{width:100%;height:100%;}
        #east .title{text-align:center;}
        #api-edit,#api-delete{display:none;}
    </style>
</head>
<body>
    <div class="container-fluid" id="body">
		<div id="east">
			<div class="h3 title">TFDSYS API</div>
            <ul class="ztree" id="index-tree"></ul>
        </div>
       	<div id="top">
       		<div class="col-xs-4">
       			<button type="button" class="btn btn-primary" id="api-edit">
       				<span class="glyphicon glyphicon-pencil"></span>
       				修改
       			</button>
       			<button type="button" class="btn btn-primary" id="api-delete">
       				<span class="glyphicon glyphicon-minus"></span>
       				删除
      			</button>
       			<button type="button" class="btn btn-primary" id="api-view">
       				<span class="glyphicon glyphicon-search"></span>
       				查看源码
      			</button>
       		</div>
       		<div class="col-xs-8"></div>
        </div>
        <div id="center">
        	<div id="center-frame">
	            <iframe id="api" frameborder="0"></iframe>
        	</div>
        </div>
        <div id="api-detail">
        	<div class="top-bar">
        		<button type="button" class="btn btn-sm btn-default" id="api-detail-close">关闭</button>
        	</div>
        	<textarea id="api-text"></textarea>
        </div>
    </div>
    
    
</body>
</html>
