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
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/api/api-manage/js/detail.logic.js"></script>
	<script>id="<%=id%>";</script>

    <style>
    </style>
</head>
<body>
	<br/>
    <div class="container">
        <div id="apiContent"></div>
    </div>
</body>
</html>
