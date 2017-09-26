<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<%  
              String id=request.getParameter("id"); 
  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制度中心</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/institution/js/institution.logic.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />

<style>
	html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	.top{
		width: 100%;
		height: 60px;
		text-align: center;
		font-size: 23px;
		padding-top: 15px;
		font: bolder;
		border-right: solid #ADD8E6 1px;
	}
	.main{
		width: 100%;
		min-height:88.5%;
		border-right: solid #ADD8E6 1px;
	}
	.moddle{
		width:100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	#info{
		margin-top:5px;
		float:right;
		padding-right:2%;
	}
	.right{
		width:100%;
		height:100%;
		margin-top:50px;
	}
	.buttonStyle{
		border:none;
	}
	#box{margin-top:20px;}
	.comments{width:100%;height:80%;border-top:dashed #CCC 1px;margin-top:10px;overflow:auto;}
	.reply{width:100%;min-height:40px;line-height:20px;font-size:12px;border:#CCC solid 1px;margin-top:5px;}
	.opt{width:30px;height:20px;float:right;font-size:11px;}
	.opt span{display:none;}
	.opt:hover span{display:block;}
	.timeStyle{float:right;font-size:11px;color:#CCC;}
	.textStyle{height:20px;margin-left:5px;margin-top:3px;}
	.buttonStyle{width:35px;height:18px;font-size:11px;margin-left:10px;text-aglin:center;}
	#attachDiv{margin-left:2%;border:solid 1px #CCC;min-height:40px;
		padding:10px 0px 10px 10px;width:96%;margin-top:5px;display:none;}
	#content{width:96%;margin-left:2%;border:solid 1px #CCC;padding:10px 0px 10px 10px;min-height:400px;}
</style>

<script type="text/javascript">
var id = "<%=id%>";
</script>
<body>
<div id="inst-content" >
	<div class="moddle"  >
		<div class="top" >
		</div>
		<div class="main" >
			<div id="content" ></div>
			<div id='attachDiv' name='attachDiv' ></div>
			<div id="info"></div>
		</div>
		
	</div>
<div class="right" >
	<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         评论区
      </h5>
   </a>
   </div>
		<div class="comments">
			
		</div>
		<div id="box" >
			<textarea rows="3" id="com_content" name="com_content" class="form-control "  style="width:100%;"></textarea>
			<input class="btn btn-primary" type="button"  value="评论" style="float:right;margin-left:5px;margin-top:2px;" id="comment_ok" />
		</div>
	</div>
</div>
<table class="MessageBox" style="display:none;margin-top:150px;" align="center" width="440" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
         <div class="msg-content"></div>
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
</body>	
</html>