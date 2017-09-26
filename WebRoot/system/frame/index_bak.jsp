<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<%
	String userName =request.getSession().getAttribute("USER_NAME").toString();
%>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎使用V1.0</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">

<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/notice.js"></script>
<script type="text/javascript" src="js/notices.js"></script>
<script type="text/javascript" src="js/index.logic.js"></script>

<script>
	userName="<%=userName%>";
</script>
<style>
	.menu_body,.shortMenu{width:100%; overflow:hidden;height:100%;background-color:#F5F5F5;}
	.content{height:100%;width:220px;overflow:auto;}
	.menu_list{width:200px;line-height:40px; background-color:#F5F5F5;}
	.menu_li{width:200px;line-height:40px;border-bottom:#CCC 1px solid;cursor:pointer;height:40px;font-size:14px;}
	.menu_li:HOVER{background-color:#FFF;}
	.menu_icon{height:40px;width:60px;float:left;text-align:center;}
	.menu_name{height:40px;width:140px;float:left;}
	.menu_child{width:200px;display:none;}
	.menu_list2{width:200px;line-height:35px;}
	.menu_li2{width:200px;line-height:35px;border-bottom:#CCC 1px solid;cursor:pointer;height:35px;font-size:12px; background-color:#F1F7FD;}
	.menu_li2:HOVER{background-color:#B8D6FB;}
	.menu_icon2{height:35px;width:60px;float:left;}
	.menu_name2{height:35px;width:140px;float:left;}
	.menu_li3{width:200px;line-height:30px;border-bottom:#CCC 1px solid;cursor:pointer;height:30px;font-size:11px; background-color:#F8F8FF; }
	.menu_li3:HOVER{background-color:#CBE1FC;}
	.menu_icon3{height:30px;width:60px;float:left;}
	.menu_name3{height:30px;width:140px;float:left;}
</style>
<style type="text/css">
	html,body {
		margin: 0px;
		padding: 0px;
		width: 100%;
		height: 100%;
	}
	html{overflow:hidden;}
	#body{width:100%;height:100%;}
	.title{height: 70px; line-height: 70px; font-size: 20px;font-weight:bold;float:left;text-indent:20px;letter-spacing:2px;}
	.top-bar{width:200px;height:30px;line-height:30px;margin-top:40px;float:right;}
	.top-bar div{cursor:pointer;}
	.top-bar div:hover{color:white;}
	#south{background: #A9FACD; line-height: 25px;}
	#north{background: url('<%=imgPath%>/headerbg.png');color:white;}
	#userName,#personSet,#logout{background:url('<%=imgPath%>/menu/25-32.png');background-repeat:no-repeat;height:25px;width:32px;float:right;}
	.userinfo{float:right;}
	#userName{background-position:-608px -143px;float:left;}
	#userName:hover{background-position:-608px -168px;}
	#personSet{background-position:-782px 7px;float:right;}
	#personSet:hover{background-position:-982px -94px;}
	#logout{background-position:-756px -168px;float:right;margin-right:10px;}
	.south-text{position:relative;}
	#copyright,#online-user-count{position:absolute;}
	#copyright{width:50%;text-align:center;left:25%;}
	#online-user-count{right:10px;}
	#tt .panel-body{overflow:hidden;}
	#menu_left,#menu_right{width:50%;float:left;background-color:#F5F5F5;cursor:pointer;
	height:30px;line-height:30px;border-bottom:#CCC 1px solid;text-align:center;}
	#menu_left:HOVER,#menu_right:HOVER{background-color:#FFF;}
	#menu_left{border-right:#CCC 1px solid;}
</style>
</head>
<body>
	<div class="easyui-layout" id="body" data-options="fit:true">
		<div id="north" data-options="region:'north',border:false,height:70">
			<div class="title">
				智能业务平台 v1.0
			</div>
			<div class="top-bar"></div>
		</div>
		<div id="west" data-options="region:'west',width:200" style="overflow:hidden;">
		<div style="height: 28px;"><div id="menu_left" >主菜单</div><div id="menu_right">快捷菜单</div></div>
			<div class="menu_body">
				<div class="content" id="menu_content"></div>
	    	</div>
	    	<div class="shortMenu">
				<div class="content" id="shortMenu_content"></div>
	    	</div>
		</div>
		<div id="center" data-options="region:'center',border:false" style="overflow:auto;width: 100%;">
			<div id="tt" data-options="region:'center',border:false,fit:true"></div>
		</div>
		<div id="south" data-options="region:'south',height:25,border:false">
			<div class="south-text">
				<div id="copyright">Copyright @ 2011-2014 WWW.TONGFEIDA2000.COM</div>
				<div id="online-user-count">在线人数:</div>
			</div>
		</div>
	</div>
	<div id="Notice"></div>
</body>
</html>