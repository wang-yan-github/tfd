<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys regist</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">

    <script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/layer/layer.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/RegexUtil.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/sysmanage/regist/regist.logic.js"></script>
    <style>
    	#reg-detail{width:80%;margin:0px auto;}
    	.logo{background-color: #2DC3E8;height:60px;
    		line-height:60px;
        }
        .logo .title{
        	color:white;font-weight:bold;
        	font-size:20px;letter-spacing:5px;
        }
        form .form-group{width:350px;}
        button[type='submit']{margin-left:300px;}
    </style>
</head>
<body>
	<br/>
   <form>
   		<table class="table table-bordered" id="reg-detail">
			<tr>
				<td colspan="2" class="logo">
					<div class="title">产品注册</div>
				</td>
			</tr>
   			<tr>
   				<td style="width:200px;">注册的产品名称</td>
   				<td>
   					<div class="form-group">
	   					<select name="productName" class="form-control">
	   						<option value="">请选择</option>
	   					</select>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册的产品版本</td>
   				<td>
   					<div class="form-group">
   						
	   					<select name="productVersion" class="form-control">
	   					</select>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册的产品序列号</td>
   				<td>
   					<div class="form-group">
	   					<input name="productSn" class="form-control" readonly="readonly"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册单位公司名称</td>
   				<td>
   					<div class="form-group">
	   					<input name="registUnit" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册用户数</td>
   				<td>
   					<div class="form-group">
	   					<input name="registUserCount" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册IM用户数</td>
   				<td>
   					<div class="form-group">
	   					<input name="registImUserCount" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>硬盘序列号</td>
   				<td>
   					<div class="form-group">
	   					<input name="registDiskSn" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>注册时间</td>
   				<td>
   					<div class="form-group">
	   					<input name="registTime" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>截止日期</td>
   				<td>
   					<div class="form-group">
	   					<input name="registDeadline" class="form-control"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>产品开发团队</td>
   				<td>
   					<div class="form-group">
	   					<input name="productTeam" class="form-control" readonly="readonly"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td>产品官网</td>
   				<td>
   					<div class="form-group">
	   					<input name="productSite" class="form-control" readonly="readonly"/>
   					</div>
   				</td>
   			</tr>
   			<tr>
   				<td colspan="2">
   					<button type="submit" class="btn btn-primary">注册</button>
   				</td>
   			</tr>
   		</table>
   </form>
    
    
</body>
</html>
