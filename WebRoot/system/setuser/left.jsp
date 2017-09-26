<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/setuser/setuser.css"></link>
<style>
.title{width:150px;height:50px;line-height:50px;font-size:16px;margin-left:20px;}
td{cursor:pointer;}
</style>
<body style="margin-top:0;">
<div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">个人信息</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">

 <a href="javascript:void(0)" class="list-group-item" onclick="javascript:userinfo()">
      <h5 class="list-group-item-heading" align="center">
         个人资料
      </h5>
   </a>
   <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         昵称与头像
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         自定义用户组
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         人员关注
      </h5>
      </a>
   </div>
</div>
<!-- <div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         个人信息
      </h5>
   </a>
   <a href="javascript:void(0)" class="list-group-item" onclick="javascript:userinfo()">
      <h5 class="list-group-item-heading" align="center">
         个人资料
      </h5>
   </a>
   <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         昵称与头像
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         自定义用户组
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         人员关注
      </h5>
      </a>
</div> -->

<div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">账户与安全</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <a href="javascript:void(0)" class="list-group-item" onclick="javascript:updatepwd()">
      <h5 class="list-group-item-heading" align="center">
         我的OA账户
      </h5>
   </a>
   <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         安全日志
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         自定义用户组
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         Windows快捷组
      </h5>
      </a>
</div>
</div>


<div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">界面设置</span>
</div>
<div class="widget-body">
<div class="widget-main no-padding">
      <div class="task-container">
   <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         门户设置
      </h5>
   </a>
   <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
        信息中心设置
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="javascript:shortMenu()">
      <h5 class="list-group-item-heading" align="center">
          菜单快捷组
      </h5>
   </a>
      <a href="javascript:void(0)" class="list-group-item" >
      <h5 class="list-group-item-heading" align="center">
         消息推送设置
      </h5>
      </a>
      </div>
</div>
</body>
<script type="text/javascript">
	function userinfo(){
		parent["edit"].location=contextPath+"/system/setuser/right.jsp";
	}
	function updatepwd(){
		parent["edit"].location=contextPath+"/system/setuser/updatepwd.jsp";
	}
	function shortMenu(){
		parent["edit"].location=contextPath+"/system/setuser/shortMenu.jsp";
	}
</script>
</html>