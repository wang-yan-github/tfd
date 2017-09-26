<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys api</title>
    <link rel="stylesheet" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">

    <script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    
   	<script>
   	$(function(){
   		$("#time").datetimepicker({
   			locale:"zh-cn",
   			format:"YYYY-MM-DD"
   	    });
   	});

   	</script>
</head>
<body>
        <div style="position: relative;"><!-- position:relative; 方便浮动的时间选择容器定位 -->
              <input id="time"/>
        </div>
        
        <br/>
        <a href="http://eonasdan.github.io/bootstrap-datetimepicker/" target="_blank">官方教程</a>
        &nbsp;&nbsp;
        <a href="http://momentjs.com/docs/#/displaying/format/" target="_blank">日期格式说明</a>
</body>
</html>
