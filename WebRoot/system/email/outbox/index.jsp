<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部邮件</title>
<script type="text/javascript" src="<%=contextPath%>/system/email/js/email.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style type="text/css">
html,body{
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
	overflow-x:hidden;
}
.title{position:fixed;height:40px;width:100%;font-size:16px;line-height:40px;background-color:#F5F5F5;padding-left:10px;z-index:99999;}
.buttons{position:fixed;top:40px;height:45px;width:100%;font-size:16px;line-height:40px;background-color:#F5F5F5;padding-left:10px;z-index:99999;border-top:#CCC 1px solid;border-bottom:#CCC 1px solid;}
.table{margin-top:84px;position: absolute;}
.child{height:60px;border-bottom:#CCC solid 1px;width:100%;cursor:pointer;}
.content{float:right;width:96.5%;}
.check{float:left;width:3.5%;}
.child-user{height:30px;font-size:13px;line-height:30px;}
.child-sub{height:30px;font-size:14px;line-height:30px;}
.checkbox{margin-top:8px;margin-left:7px;cursor:pointer;}
.buttonStyle{border:#CCC solid 1px; height:20px; margin-left:20px;background-color:#FFF;font-size:12px;cursor:pointer;}
.datagrid-header {position: absolute; visibility: hidden;margin-top:40px;}
.search{border:#CCC 1px solid;float:right;margin-top:5px;margin-right:10px;height:25px;width:200px;}
</style>
</head>
<body>
	<div class=title ><span>废件箱</span>
		<div class="input-group" style="float:right;width:240px;margin-top:3px;margin-right:20px;">
               <input type="text" placeholder="在废件箱中搜索" id="searchContent" class="form-control" style="width:200px;height:35px;" >
               <span class="input-group-btn">
                  <button id="search" class="btn btn-default" type="button" style="line-height:22px;margin-bottom:15px;" >
                     Go!
                  </button>
               </span>
            </div>
	</div>
	<div class="buttons" ></div>
	<div class="table" >
	<div id="myTable" style="margin-top:40px;" ></div>
	<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
	   <tbody><tr class="head-no-title">
	      <td class="left"></td>
	      <td class="center">
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="msg">
	      <td class="left"></td>
	      <td class="center info">
	         <div class="msg-content">暂无邮件</div>
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="foot">
	      <td class="left"></td>
	      <td class="center"><b></b></td>
	      <td class="right"></td>
	   </tr>
	   </tbody>
	</table>
	</div>
</body>
<script type="text/javascript">
var boxId = "<input  id=\"checkAll\" type=checkbox /><input class=\'btn btn-info\' style=\"margin-left:10px;\" type=button onclick=javascript:restoreEmail('') value=恢复  /><input style=\"margin-left:10px;\" class=\'btn btn-danger\' onclick=deleteEmail('') type=button value=彻底删除 />";
$(".buttons").html(boxId);
var boxIds = "4";
$(function(){
	var urls = '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailList.act?boxId=4';
	getEmail("");
	$('#search').click(function(){
		var searchContent = encodeURIComponent($('#searchContent').val());
		getEmail(searchContent);
	})
});

</script>
</html>