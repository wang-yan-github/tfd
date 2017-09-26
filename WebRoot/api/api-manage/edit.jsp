<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<%
	String id=request.getParameter("id");
	id=id==null?"":id;
%>
<html>
<head>
    <title>tfdsys api</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath%>/api/api-manage/css/bootstrap-defined.css">
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
    <link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>

    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/api/api-manage/js/edit.logic.js"></script>
	<script>id="<%=id%>";</script>

    <style>
		.dropdown-menu-parent{width:100%;display:none;}
		.dropdown-menu-parent-list{width: 100%;}
    </style>
</head>
<body>
	<br/>
    <div class="container">
        <form class="form-horizontal" id="api-form">
        	<input type="hidden" name="id"/>
            <div class="form-group">
                <label for="name" class="col-xs-2 control-label">名称</label>
                <div class="col-xs-6">
                    <input class="form-control" name="name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="parentName" class="col-xs-2 control-label">分类</label>
                <div class="col-xs-6">
                    <input type="hidden" name="parentId"/>
                    <input class="form-control" name="parentName" readonly="readonly" value="根目录"/>
					<div class="dropdown" id="dropdown-parent">
						<button class="btn btn-default dropdown-toggle dropdown-menu-parent" 
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						</button>
						<ul class="dropdown-menu dropdown-menu-parent-list" aria-labelledby="dropdown-menu-parent">
							<li>
								<ul id="index-tree" class="ztree"></ul>
							</li>
						</ul>
					</div>
                </div>
            </div>
            <div class="form-group">
                <label for="path" class="col-xs-2 control-label">路径</label>
                <div class="col-xs-6">
                    <input class="form-control" name="path" placeholder="如:/api/district/index.jsp"/>
                </div>
            </div>
            <div class="form-group">
                <label for="apiContent" class="col-xs-2 control-label">内容</label>
                <div class="col-xs-6">
                    <textarea class="form-control" name="apiContent"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-xs-offset-2">
                    <button class="btn btn-primary" type="submit">保存</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
