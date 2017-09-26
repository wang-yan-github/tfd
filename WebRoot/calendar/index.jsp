<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日程安排</title>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/lib/jquery-ui.custom.min.js'></script>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.min.js'></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link href='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='<%=contextPath%>/calendar/css/calendar.css' rel='stylesheet' />
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/index.logic.js'></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/calendar.js'></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/affair.js'></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/calendar/calendar.css"></link>
<style type="text/css">
	.checked{height:30px;line-height:30px;cursor:pointer;text-align:center;}
	.checked:hover{background-color:#F5F5F5;}
	.checkeds{background-color:#CCC;}
.fc-event-color4,.fc-event-color3,.fc-event-color1,.fc-event-color,.fc-state-default,.easyui-linkbutton {
  -webkit-box-sizing: content-box;
     -moz-box-sizing: content-box;
          box-sizing: content-box;
}
body {
	height: 100%;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
}

#calendar {
	width: 98%;
	margin: 0 auto;
	margin-top:7px;
	height:40%;
}
</style>
</head>
<body>
<!-- <div class="list-group" style="margin-bottom: 0px;">
	<a class="list-group-item active">
 		<h5 class="list-group-item-heading">
   		我的日程
 		</h5>
 		<div style="margin-top: -28px; float: right; width: 420px;">
 			<button style="float:right;" class="btn btn-info" onclick="goImport();" >导入/导出</button>
 			<button style="float:right;" class="btn btn-info" onclick="goPeriodicity();" >周期日程</button>
 			<button style="float:right;" class="btn btn-primary" onclick="goWeekManage();" >我的日程</button>
 			<button style="float:right;" class="btn btn-info" onclick="goQuery();" >查询</button>
		</div>
	</a>
</div> -->
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >我的日程</span>
</div>
<div style=" float:right; width:420px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-info" onclick="goForMe();" >领导安排的日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goPeriodicity();" >周期日程</button>
	<button style="float:right;" class="btn btn-primary" onclick="goWeekManage();" >我的日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goQuery();" >查询/导出</button>
</div>
</div>
<div id='calendar'></div>
<!-- 点击触发事件，快速新建 -->
<div id='calendar-quick-setup' class="popover  right " style='background-color:#fbfafb;'>
    <div class="arrow"></div>
    <div class="popover-content">
        
        <form class="form2" id="form2" name="form2" class="form-horizontal" >            
            <div class="control-group form-group" style="margin-bottom:10px;">
                <textarea id="quick-calendar-title" placeholder="内容" name="quick_calendar_title" class="form-control input" style="width:240px;"></textarea>
                <div id="smsdiv" style="display:none;" name="smsdiv"></div>
            </div>
            <div class="control-group" style="margin-bottom:10px;">
                <span id="quick-begin-time"></span>
                <span style="margin:0 2px;" id="quick-name">至</span>
                <span id="quick-finish-time"></span>
            </div>
          
        <div class="row-fluid" >
          
            <div class="span9" >
	            <div class="pull-left" style="padding-bottom:10px;">
	            	<button class="btn btn-primary" data-cmd="editmore" type="button">完整编辑</button>
	            </div>
                <div class="pull-right">
                    <input class="btn btn-primary"  data-cmd="ok" disabled="disabled" type="submit" value="确定" >
                    <button class="btn btn-default" data-cmd="cancel" type="button">取消</button>
                </div>
            </div>
        </div>
        </form>  
    </div>
</div>


<!-- 点击日程弹出浮动框   --  查询 -->
<div id='calendar-quick-detail' class="popover fade auto" style="width:300px;max-width:300px;background-color:#fbfafb;">
    <div class="arrow"></div>
    <div class="popover-content">
        
       <form class="form">
            <div class="control-group" style="width:200px;min-height:50px;">
            	<h4 style="word-break: break-all;"></h4>
                <h5 style="word-break: break-all;"></h5>
            </div>

            <div class="control-group" >
                <span id="BEGIN_TIME"></span>
                <span id="TO_SPAN" style="margin:0 2px;">至</span>
                <span id="FINISH_TIME"></span>
              
            </div>
        </form>            
        <div class="row-fluid">
            
            <div class="span9">
            	<div class="pull-left">
              	    <button class="btn btn-danger" data-cmd="delete" type="button" id="delete" style="display:none;" >删除</button>
          	    </div>
                <div class="pull-right">                    
                    <button class="btn btn-primary" data-cmd="detail" type="button" id="detail">详情</button>
                    <button class="btn btn-primary" data-cmd="edit" type="button" id="edit">编辑</button>
                    <button class="btn btn-default"  data-cmd="cancel" type="button">取消</button>
                </div>
            </div>
        </div>
        <div class="btn-group" id="statuslist" >
            <span id="current_status" ><span id="status"></span><span class="caret"></span></span>
            <div id="status_menu" class="attach_div small" >
              <a href="javascript:;" id="no-finished" index='0' ><i class=""></i>未完成</a><br/>
              <a href="javascript:;" id="finished" index='1'><i class="icon-dropdown-checkbox"></i>已完成</a>
            </div>
        </div>
        <input type="hidden" id="calId" value='0'/>
        
    </div>
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
               详细信息
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"></iframe>
         </div>
         <div class="modal-footer">
         <button type="button" class="btn btn-primary"  id="btn_finished"  >已完成</button>
         	<input type="submit" class="btn btn-primary"  id="savedata"  name="savedata" value="确定" >
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
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
<script type="text/javascript">
   $("#form2").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 quick_calendar_title: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     }
	 }
	 }).on('success.form.bv',function(e){
		 e.preventDefault();
	     // Get the form instance
	     var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     var bv = $form.data('bootstrapValidator');
	     quickAdd();
	});
</script>
</html>
