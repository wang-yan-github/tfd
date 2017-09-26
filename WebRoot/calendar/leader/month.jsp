<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String deptId=request.getParameter("deptId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日程安排查询</title>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.min.js'></script>
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/monIndex.js"></script>
<script src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.css">
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/lender.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<style type="text/css">
body,html{padding:0px;margin:0px;height:100%;width:100%;}
</style>
<script>
var weekStartDate=getWeekStartDate(new Date());
var weekEndDate=getWeekEndDate(new Date());
var deptId="<%=deptId%>";
$(function (){
	$('#deptId').val(deptId);
	createUserHtml();
	//getAddUpdateCalFroms();
});

function setDate(){
	var now = new Date(); 
	var nowYear = now.getFullYear();
	$('#year').val(nowYear);
	var month = getNowMonth();
	$('#month').val(month);
	if(month < 10){
		month = "0"+month;
	}
	$("#DateTitle").html(nowYear+"年"+month+"月");
}

function createUserHtml()
{
	setDate();
	var requestUrl = "<%=contextPath%>/calendar/act/CalendarAct/getManageUser.act";
 	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{deptId:deptId},
 		beforeSend:ajaxLoading,
 		success:function(data){
 			//createTable(data,weekStartDate,weekEndDate);
 			var html = "";
 			$.each(data,function(index,data){
 				html += "<option value="+data.userId+" >"+data.userName+"</option>";
 			});
 			$("#userDiv").html(html);
 			createTableHtml($("#userDiv").val(),weekStartDate,weekEndDate,'-1');
 			ajaxLoadEnd();
 		}
 	});
}
</script>
<body>
<form name="form1" action="<%=contextPath%>/calendar/act/CalendarAct/getManageRangeCanlenarAct.act" style="margin-bottom:5px;" class="form-inline">
<table style="width: 100%;">
<tr>
<td align="left" style="padding-top:5px;" >
	<input type="hidden" value="" id="deptId" name="deptId">
   <input type="hidden" value="" name="BTN_OP">
   <input type="hidden" value="" name="OVER_STATUS">
   <input type="hidden" value="" name="MONTH">
   <input type="hidden" value="" name="DAY">
<!-------------- 年 ------------>
   <a href="javascript:set_year(-1);" class="ArrowButtonLL" title="上一年"></a>
   <a href="javascript:set_week(-1);" class="ArrowButtonL" style="margin-left:5px;" title="上一周"></a>
   <select style="float:left;height:30px;margin-left:10px;" name="YEAR" onChange="My_Submit();" id="year" class="form-control">
      <option value="2014" >2014年</option>
      <option value="2015" >2015年</option>
      <option value="2016" >2016年</option>
      <option value="2017" >2017年</option>
      <option value="2018" >2018年</option>
      <option value="2019" >2019年</option>
      <option value="2020" >2020年</option>
      <option value="2021" >2021年</option>
      <option value="2022" >2022年</option>
      <option value="2023" >2023年</option>
      <option value="2024" >2024年</option>
      <option value="2025" >2025年</option>
      <option value="2026" >2026年</option>
      <option value="2027" >2027年</option>
      <option value="2028" >2028年</option>
      <option value="2029" >2029年</option>
      <option value="2030" >2030年</option>
        </select>
<!-------------- 月 ------------>
   <select style="float:left;margin-left:5px;height:30px;" name="WEEK" id="month" onChange="My_Submit();" class="form-control">
      <option value="1" >1月</option>
      <option value="2" >2月</option>
      <option value="3" >3月</option>
      <option value="4" >4月</option>
      <option value="5" >5月</option>
      <option value="6" >6月</option>
      <option value="7" >7月</option>
      <option value="8" >8月</option>
      <option value="9" >9月</option>
      <option value="10" >10月</option>
      <option value="11" >11月</option>
      <option value="12" >12月</option>
   </select>
   <button type="button" style="float:left;margin-left:5px;height:30px;" onClick="javascript:GoToday();" class="btn btn-default">今天</button>
    <select id="status" style="float:left;margin-left:5px;height:30px;" onchange="javascript:My_Submit();"  class="form-control">
    	<option value="-1" selected="selected" >全部</option>
    	<option value="0" >未开始</option>
    	<option value="3" >进行中</option>
    	<option value="4" >已超时</option>
    	<option value="1" >已结束</option>
    </select>
    
    <div style="float:left;margin-left:5px;width:300px;height:30px;line-height:30px;margin-top:2px;">
    	<span style="float:left;font-size:12px;">未完成</span><div style="height:15px;width:30px;background-color:rgb(255, 136, 124);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">已完成</span><div style="height:15px;width:30px;background-color:rgb(58, 135, 173);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">进行中</span><div style="height:15px;width:30px;background-color:rgb(105, 240, 164);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">已超时</span><div style="height:15px;width:30px;background-color:rgb(245, 181, 46);float:left;margin-top:7px;"></div>
    </div>
</td>
<td align="right" style="width:300px;" >
<select id="userDiv" class="form-control" style="width:150px;margin-right:10px;height:30px;" onchange="My_Submit();" >
</select>
<button type="button" onClick="set_view('day','cal_info_view');" class="btn">日</button>
<button type="button" onClick="set_view('right','cal_info_view');" class="btn">周</button>
<button type="button" onClick="set_view('month','cal_info_view');" class="btn btn-info">月</button>
</td>
</tr>
</table>
</form>
<table style="width: 100%;">
<tr>
	<td class="fc-header-left" >
	<button onclick="javascript:set_month(-1);" class="btn" title="上一月">上一月</button>
   
	</td>
	<td class="fc-header-center" ><span class="fc-header-title" >
		<h2 id="DateTitle"></h2>
	</span></td>
	<td class="fc-header-right">
	<button onclick="javascript:set_month(1);" class="btn" title="下一月">下一月</button>
	</td>
</tr>

<tr>
<td colspan="3" >
<div id="calendarTable" name="calendarTable">
</div>
</td>
</tr>
</table>
<div align=right></div>
<div id="overlay"></div>

<!-- <div id="form_div" class="ModalDialog1">
  <div class="modal-header"><a class="operation" href="javascript:HideDialog('form_div');"><button type="button" class="close" data-dismiss="modal" aria-hidden="true" onClick="HideDialog('form_div');">&times;</button></a>
  <h3><span id="title" class="title">新建日程</span></h3>
  </div>
  <div id="form_body" class="modal-body" style="height:280px">
  </div>
</div> -->
<div id="modaldialog"></div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModals" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="">
   <div class="modal-dialog" id="div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="myModalLabel">
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"></iframe>
         </div>
         <div class="modal-footer">
         	<button class="btn btn-success" style="margin-left:200px;"  data-cmd="ok" id="btn_edit" type="button">修改</button>
			<button class="btn btn-danger"   id="btn_delete" type="button">删除</button>
			<button type="button" id="btn-save" class="btn btn-success" onclick="addOrUpdateCal();" >保存</button>
            <button type="button" class="btn btn-default btn-close"  data-dismiss="modal" id="btn_close">关闭  </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>



</body>
</html>

