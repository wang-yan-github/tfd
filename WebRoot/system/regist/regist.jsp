<%@page import="com.system.global.SysConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<%
	Boolean registered=request.getAttribute("registred")==null?null:(Boolean)request.getAttribute("registred");
%>
<html>
<head>
    <title>tfdsys regist</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/regist/regist.logic.js"></script>
    <script>
    	var registred=<%=registered%>;
    	if(registred!=null&&!registred){
    		alert("不是有效的注册文件！");
    	}else if(registred==true){
    		alert("注册成功！");
    		location.href=contextPath+"/index.jsp";
    	}
    </script>
    
    <style>
    	#reg-detail{width:80%;margin:0px auto;}
        #reg-form input[name='fileshow']{width:350px;}
        #reg-form input[name='file']{display:none;}
    	.logo{background-color: #2DC3E8;height:60px;
    		line-height:60px;
        }
        .logo .title{
        	color:white;font-weight:bold;
        	font-size:20px;letter-spacing:5px;
        }
        .logo .title:HOVER {
			text-decoration: underline;
		}
		.logo .title,#validate-result{display:inline-block;}
		#validate-result{letter-spacing: 2px;font-weight:bold;}
		#validate-result[result='true']{color: green;}
		#validate-result[result='false']{color: white;}
    </style>
    
</head>
<body>
	<br/>
	<table class="table table-bordered" id="reg-detail">
		<tr>
			<tr>
				<td colspan="2" class="logo">
					<div class="title" id="productName" title="去登录"></div>
					<div id="validate-result"></div>
				</td>
			</tr>
		</tr>
		<tr>
			<td width="200px">产品版本</td>
			<td>
				<div id="version"></div>
			</td>
		</tr>
		<tr>
			<td>产品序列号</td>
			<td>
				<div id="sn"></div>
			</td>
		</tr>
		<tr>
			<td>产品官网</td>
			<td>
				<div id="productSite"></div>
			</td>
		</tr>
		<tr>
			<td>产品研发团队</td>
			<td>
				<div id="productTeam"></div>
			</td>
		</tr>
		<tr>
			<td>注册单位</td>
			<td>
				<div id="unit"></div>
			</td>
		</tr>
		<tr>
			<td>注册时间</td>
			<td>
				<div id="regTime"></div>
			</td>
		</tr>
		<tr>
			<td>产品使用截止日期</td>
			<td>
				<div id="deadline"></div>
			</td>
		</tr>
		<tr>
			<td>使用用户数上限</td>
			<td>
				<div id="userCount"></div>
			</td>
		</tr>
		<tr>
			<td>IM使用用户数上限</td>
			<td>
				<div id="imUserCount"></div>
			</td>
		</tr>
		<tr>
			<td>硬盘序列号</td>
			<td>
				<div id="diskSn"></div>
			</td>
		</tr>
		<tr>
			<td>产品注册</td>
			<td>
				<form id="reg-form" class="form-inline" action="<%=contextPath %>/tfd/system/regist/act/RegistAct/regist.act" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<input class="form-control" placeholder="请选择文件reg.lsq" name="fileshow" readonly="readonly"/>
				        <input type="file" name="file"/>
					</div>
			        <div class="form-group">
				        <button type="submit" class="btn btn-primary">注册</button>
			        </div>
			   </form>
			</td>
		</tr>
	</table>
</body>
</html>
