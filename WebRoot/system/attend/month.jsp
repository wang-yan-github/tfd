<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/system/attend/js/month.logic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/attend/attend.css"></link>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<style type="text/css">
	.content-body{width:80%;margin-left:10%;margin-top:30px;}
	.content-body table tr td{text-align:center;}
</style>
<script type="text/javascript">	
function doReady(){
	doinit();
}
$("#btn-back").click(function(){
	history.go(-1);
	return false;
})
</script>
</head>
<body onload="doReady();" >
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >本月考勤记录</span>
</div>
<div style=" float:right; width:300px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-info" onclick="goAddress();" >本月定位考勤</button>
	<button style="float:right;" class="btn btn-primary" onclick="goMonth();" >本月考勤</button>
	<button style="float:right;" class="btn btn-info" onclick="goToday();" >今日考勤</button>
</div>
</div>
<div class="content-body" >
<table class="table table-striped" style="margin-bottom:50px;" >
	<tr>
		<td>登记次序</td>
		<td>登记类型</td>
		<td>规定时间</td>
		<td>登记时间</td>
		<td>登记状态</td>
		<td>操作</td>
	</tr>
	<tbody id="content" name="content"></tbody>
</table>
</div>
<div class="modal fade" id="myModals" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
      <form   method="post" name="form1" id="form1" class="form-horizontal" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="myModalLabel">详细信息</h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"  ></iframe>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
         </div>
         </form>
      </div>
</div>
</div>
</body>
</html>