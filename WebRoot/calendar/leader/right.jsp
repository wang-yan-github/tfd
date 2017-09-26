<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String deptId=request.getParameter("deptId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日程安排查询</title>
<script  type="text/javascript"  src='<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.min.js'></script>
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/index.js"></script>
<script src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/calendar/css/calendar_person.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/fullcalendar/fullcalendar/fullcalendar.css">
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/lender.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>

<script>
var weekStartDate=getWeekStartDate(new Date());
var weekEndDate=getWeekEndDate(new Date());
var deptId="<%=deptId%>";
$(function (){
	$('#deptId').val(deptId);
	createTableHtml();
	//getAddUpdateCalFroms();
});

function setDate(){
	var now = new Date(); 
	var nowYear = now.getFullYear();
	$('#year').val(nowYear);
	var week = getNowWeek();
	$('#week').val(week);
	$("#DateTitle").html(nowYear+"年第"+week+"周");
}

function createTableHtml()
{
	var requestUrl = "<%=contextPath%>/calendar/act/CalendarAct/getManageRangeCanlenarAct.act";
 	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{deptId:deptId,weekStartDate:weekStartDate,weekEndDate:weekEndDate,status:'-1'},
 		beforeSend:ajaxLoading,
 		success:function(data){
 			createTable(data,weekStartDate,weekEndDate);
 			setDate();
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
<!-------------- 周 ------------>
   <select style="float:left;margin-left:5px;height:30px;" name="WEEK" id="week" onChange="My_Submit();" class="form-control">
      <option value="1" >第1周</option>
      <option value="2" >第2周</option>
      <option value="3" >第3周</option>
      <option value="4" >第4周</option>
      <option value="5" >第5周</option>
      <option value="6" >第6周</option>
      <option value="7" >第7周</option>
      <option value="8" >第8周</option>
      <option value="9" >第9周</option>
      <option value="10" >第10周</option>
      <option value="11" >第11周</option>
      <option value="12" >第12周</option>
      <option value="13" >第13周</option>
      <option value="14" >第14周</option>
      <option value="15" >第15周</option>
      <option value="16" >第16周</option>
      <option value="17" >第17周</option>
      <option value="18" >第18周</option>
      <option value="19" >第19周</option>
      <option value="20" >第20周</option>
      <option value="21" >第21周</option>
      <option value="22" >第22周</option>
      <option value="23" >第23周</option>
      <option value="24" >第24周</option>
      <option value="25" >第25周</option>
      <option value="26" >第26周</option>
      <option value="27" >第27周</option>
      <option value="28" >第28周</option>
      <option value="29" >第29周</option>
      <option value="30" >第30周</option>
      <option value="31" >第31周</option>
      <option value="32" >第32周</option>
      <option value="33" >第33周</option>
      <option value="34" >第34周</option>
      <option value="35" >第35周</option>
      <option value="36" >第36周</option>
      <option value="37" >第37周</option>
      <option value="38" >第38周</option>
      <option value="39" >第39周</option>
      <option value="40" >第40周</option>
      <option value="41" >第41周</option>
      <option value="42" >第42周</option>
      <option value="43" >第43周</option>
      <option value="44" >第44周</option>
      <option value="45" >第45周</option>
      <option value="46" >第46周</option>
      <option value="47" >第47周</option>
      <option value="48" >第48周</option>
      <option value="49" >第49周</option>
      <option value="50" >第50周</option>
      <option value="51" >第51周</option>
      <option value="52" >第52周</option>
      <option value="53" >第53周</option>
   </select>
   <a href="javascript:set_week(1);" class="ArrowButtonR" title="下一周"></a>
   <a href="javascript:set_year(1);" class="ArrowButtonRR" title="下一年"></a>
   <button type="button" style="float:left;margin-left:5px;height:30px;" onClick="javascript:createTableHtml();" class="btn btn-default">今天</button>
    <select id="status" style="float:left;margin-left:5px;height:30px;" onchange="javascript:My_Submit();"  class="form-control">
    	<option value="-1" selected="selected" >全部</option>
    	<option value="0" >未开始</option>
    	<option value="3" >进行中</option>
    	<option value="4" >已超时</option>
    	<option value="1" >已结束</option>
    </select>
    
    <div style="float:left;margin-left:5px;width:360px;height:30px;line-height:30px;margin-top:2px;">
    	<span style="float:left;font-size:12px;">未完成</span><div style="height:15px;width:30px;background-color:rgb(255, 136, 124);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">已完成</span><div style="height:15px;width:30px;background-color:rgb(58, 135, 173);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">进行中</span><div style="height:15px;width:30px;background-color:rgb(105, 240, 164);float:left;margin-top:7px;"></div>
    	<span style="float:left;margin-left:5px;font-size:12px;">已超时</span><div style="height:15px;width:30px;background-color:rgb(245, 181, 46);float:left;margin-top:7px;"></div>
    </div>
</td>
<td align="right">
<button type="button" onClick="set_view('day','cal_info_view');" class="btn">日</button>
<button type="button" onClick="set_view('right','cal_info_view');" class="btn btn-info">周</button>
<button type="button" onClick="set_view('month','cal_info_view');" class="btn">月</button>
</td>
</tr>
</table>
</form>
<table style="width: 100%;">
<tr>
	<td class="fc-header-left" >
	<button onclick="javascript:set_week(-1);" class="btn" title="上一月">上一周</button>
   
	</td>
	<td class="fc-header-center" ><span class="fc-header-title" >
		<h2 id="DateTitle"></h2>
	</span></td>
	<td class="fc-header-right">
	<button onclick="javascript:set_week(1);" class="btn" title="下一月">下一周</button>
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

