<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys regist</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/sysmanage/regist/registered-list.logic.js"></script>
    <style>
    	#registred-list{width:80%;margin:0px auto;}
    	.logo{background-color: #2DC3E8;height:60px;
    		line-height:60px;
        }
        .logo .title{
        	color:white;font-weight:bold;
        	font-size:20px;letter-spacing:5px;
        }
        .column td{font-weight:bold;}
    </style>
</head>
<body>
	<br/>
   <form>
   		<table class="table table-bordered" id="registred-list">
			<tr>
				<td colspan="10" class="logo">
					<div class="title">已注册单位</div>
				</td>
			</tr>
   			<tr class="column">
   				<td>产品序列号</td>
   				<td>产品名称</td>
   				<td>产品版本</td>
   				<td>注册单位</td>
   				<td>注册时间</td>
   				<td>使用截止日期</td>
   				<td>注册用户数</td>
   				<td>注册的IM用户数</td>
   				<td>硬盘序列号</td>
   				<td>操作</td>
   			</tr>
   			
   		</table>
   </form>
    
    
</body>
</html>
