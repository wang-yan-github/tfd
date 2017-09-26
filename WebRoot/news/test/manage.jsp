<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻维护</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/news/news.css"></link>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<script type="text/javascript" src="<%=contextPath %>/news/manage/js/manage.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >新闻管理
</span>
<div class="col-xs-12 form-group" style="float: left;margin-top:8px; width: 150px;display: none" id="newSelect">
<div id="newstype"></div>
</div>
</div>
<div style=" float: right;margin-top:15px;margin-right: 20px;display: none;" id="btnitem">
       <button type="button" class="btn btn-danger btn-sm"  onclick="delNews();">删除</button>
       <button type="button" class="btn btn-warning btn-sm"  onclick="batchends('1');">终止所选新闻</button>
       <button type="button" class="btn btn-primary btn-sm"  onclick="batchends('0');">生效所选新闻</button>
</div>
</div>
<div id="myTable" name="myTable"></div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
×
</button>
<h4 class="modal-title" id="myModalLabel">
选择时间：</h4>
</div>
<div class="modal-body" style="height: 80px;">
<input id="newsId" name="newsId" type="hidden"></input>
<div style="float: left;margin-top: 8px;">选择终止时间：</div><div class="col-xs-6 form-group" style="float: left;">
			<input type="text" name="endTime" id="endTime" size="20" readonly="readonly" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择时间"></div>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default" data-dismiss="modal">取消
</button>
<button type="button" id="statusbtn" class="btn btn-primary">
确定
</button>
</div>
</div>
</div>
</div>

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
	         <div class="msg-content">没有发布新闻</div>
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