<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys api</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap-datetimepicker.css">

    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/LunarCalendarUtil.js"></script>
    <script type="text/javascript" src="index.logic.js"></script>
    	
    </script>
    <style>
    	#calendar{width:330px;}
    	.day-container{
   		}
    	.day-plan{
    		position:absolute;top:0px;left:0px;right:0px;bottom:0px;
    		background-color:#FEEDE9;border:solid 1px #fff;color:rgb(51,51,51);
   		}
    	.day-plan[data-plan-status='p班']{background-color:#f2f2f2;}
   		
    	.day-plan-status{
    		position:absolute;right:0px;top:0px;
    		font-size:10px !important;
    		background-color:#F84E29;color:#fff;
    		line-height:normal;padding:1px;
    	}
    	.day-plan[data-plan-status='p班'] .day-plan-status{color:rgb(51,51,51);background-color:#ccc;}
    	
    	.day-solar{margin-top:5px;}
    	.day-lunar{font-size:12px;}
    	.day-lunar[data-plan-text^='p']{color:#F96D4E;}
    	
		.datepicker-days .day{position:relative;padding:0px;}
    </style>
</head>
<body>
	<div id="calendar"></div>
</body>
</html>
