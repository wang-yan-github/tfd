<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
	String boxId = "1";
%>
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
.noread{width:18px;height:15px;float:left;margin-top:6px;background:url('<%=imgPath%>/email/mail.png')}
.noread{background-position:-47px 0px;}
</style>
</head>
<body>
	<div class=title ><span>收件箱</span>
            <div class="input-group" style="float:right;width:240px;margin-top:3px;margin-right:20px;">
               <input type="text" placeholder="在收件箱中搜索" id="searchContent" class="form-control" style="width:200px;height:35px;" >
               <span class="input-group-btn">
                  <button id="search" class="btn btn-default" type="button" style="line-height:22px;margin-bottom:15px;" >Go! </button>
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
var contextPath = "<%=contextPath%>";
var html = "";
var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailBoxList.act';
$.ajax({
	url:requestUrl,
	dataType:"json",
	async:false,
	error:function(e){
		alert(e.message);
	},
	success:function(data){
		if(!($.isEmptyObject(data))){
			$.each(data,function(index,data){
			   html += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:moveEmail('"+data.boxId+"')\" >"+data.boxName+"</a></li>"
			});
		}
	}
});
var boxId = "<input  id=\"checkAll\" type=checkbox /><input class=\"btn btn-primary\" style=\"margin-left:10px;\" type=button onclick=javascript:changeEmailFlag() value=标为已读 /><div style=\"margin-left:10px;\" class=\"btn-group\"><button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\" sytle=\"z-inde:99999\">移动到... <span class=\"caret\"></span></button><ul class=\"dropdown-menu\" role=\"menu\"><li><a href=\"javascript:void(0)\" onclick=\"javascript:moveEmail('1')\"  >收件箱</a></li>"+html+"</ul></div><input style=\"margin-left:10px;\" class=\"btn btn-info\" onclick=javascript:delEmail('') type=button value=删除 /><input style=\"margin-left:10px;\" class=\'btn btn-danger\' onclick=deleteEmail('') type=button value=彻底删除 />";
var boxIds = "1";
$(".buttons").html(boxId);
$(function(){
	var urls = '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailList.act?boxId=1';
	getEmail("");
	$('#search').click(function(){
		var searchContent = encodeURIComponent($('#searchContent').val());
		getEmail(searchContent);
	})
});
function moveEmail(boxId){
	var id = "";
	var s = 0;
	var checkbox = document.getElementsByName("checkboxs");
	for(var i=0; i<checkbox.length; i++){ 
		if(checkbox[i].checked){
			id += checkbox[i].value+",";
			s++;
		}
	}
	if(s<=0){
		top.layer.msg('请选择邮件',function(){});
		return false;
	}
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/restoreEmail.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id),boxId:boxId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			top.layer.msg('移动成功');
			parent["left"].getCount();
			history.go(0);
		}
	});
}
</script>
</html>