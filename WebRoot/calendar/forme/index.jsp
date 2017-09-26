<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日程安排</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link href='<%=contextPath%>/calendar/css/calendar.css' rel='stylesheet' />
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/affair.js'></script>
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/forMe.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/calendar/calendar.css"></link>

<style>
html,body {
	height: 100%;
	width:100%;
	margin:0px;
	padding:0px;
}
.bottomDiv{width:90%;margin-left:5%;margin-top:20px;}
.checked{height:30px;line-height:30px;cursor:pointer;text-align:center;}
	.checked:hover{background-color:#F5F5F5;}
	.checkeds{background-color:#CCC;}
</style>
</head>
<body>

<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >领导安排的日程</span>
</div>
<div style=" float:right; width:420px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-primary" onclick="goForMe();" >领导安排的日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goPeriodicity();" >周期日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goWeekManage();" >我的日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goQuery();" >查询/导出</button>
</div>
</div>
<div class="bottomDiv" align="center"  >
<div class="input-group" style="width:240px;">
               <input type="text" placeholder="请输入查询内容" id="searchContent" class="form-control" style="width:200px;height:35px;" >
               <span class="input-group-btn">
                  <button id="btn_search" class="btn btn-default" type="button" style="line-height:22px;margin-bottom:15px;" >Go! </button>
               </span>
            </div>
	<div id="myTable" ></div>
</div>
<div id="modaldialog"></div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModals" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
       <form   method="post" name="form1" id="form1" class="form-horizontal" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="myModalLabel">
               修改日程
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"></iframe>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default"  data-dismiss="modal">关闭  </button>
         </div>
         </form>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<div class="modal fade" id="weekModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="week-div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="weekModalLabel">
               选择星期
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="week-modal-body" >
         </div>
         <div class="modal-footer" align="center" >
         	<button type="button" class="btn btn-primary" onclick="getCheck();" >确定  </button>
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</body>
</html>
