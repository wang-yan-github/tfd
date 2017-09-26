<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日程安排</title>
<script type="text/javascript"
	src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/calendar/js/calAffair.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/affair.js'></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/calendar/calendar.css"></link>
<style>
html, body {
	height: 100%;
	width: 100%;
	margin: 0px;
	padding: 0px;
}

.topDiv {
	width: 99.9%;
	border: #CCC 1px solid;
}

.bottomDiv {
	width: 99.9%;	
}
</style>
</head>
<body>
	<div class="topDiv">
	<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >查询/导出</span>
</div>
<div style=" float:right; width:420px;margin-top:6px;margin-right:20px;" class="btn-group" role="group" aria-label="..." >
	<button style="float:right;" class="btn btn-info" onclick="goForMe();" >领导安排的日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goPeriodicity();" >周期日程</button>
	<button style="float:right;" class="btn btn-info" onclick="goWeekManage();" >我的日程</button>
	<button style="float:right;" class="btn btn-primary" onclick="goQuery();" >查询/导出</button>
</div>
</div>
			<table class="table table-striped">
				<tr>
					<td width="8%" align="right">日期:</td>
					<td width="35%" style="line-height:30px;" ><input class="form-control"
						style="width: 45%;  float: left;" readonly="readonly" type="text" id="startDate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" /> <span
						style="float: left; margin-left: 10px;">至</span> <input
						class="form-control"
						style="width: 45%; margin-left: 10px; float: left" readonly="readonly" type="text"
						id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
					</td>
					<td width="8%" align="right">状态:</td>
					<td width="17%"><select class="form-control" id="status">
							<option value="" selected="selected">所有</option>
							<option value="0">未完成</option>
							<option value="1">已完成</option>
							<option value="3">进行中</option>
							<option value="4">已超时</option>
					</select></td>
				</tr>
				<tr>
					<td width="8%" align="right">事务内容:</td>
					<td width="35%"><input class="form-control" type="text"
						id="content" /></td>
					<td width="8%" align="right">事务类型:</td>
					<td width="17%"><select class="form-control" id="cal_Type">
							<option value="" selected="selected">所有</option>
							<option value="1">工作日程</option>
							<option value="2">个人日程</option>
							<option value="3">公开日程</option>
					</select></td>
					<td>
						<button type="button" id="btn_search" class="btn btn-primary">查询</button>
						<button type="button" id="btn_outPut" class="btn btn-default">导出</button>
					</td>
				</tr>
			</table>
	</div>
	<div class="bottomDiv"></div>
	<div id="myTable"></div>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModals" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
       <form   method="post" name="form1" id="form1" class="form-horizontal" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="myModalLabel">
               查看详细
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="modal-body" >
            <iframe id="modaliframe"  name="modaliframe" frameborder="0"></iframe>
         </div>
         <div class="modal-footer">
         <input type="submit" class="btn btn-primary"  id="savedata"  name="savedata" value="确定" >
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
<script type="text/javascript">
	$(function() {
		searchCalendar();
		$("#btn_search").click(function() {
			searchCalendar();
		});
		$("#btn_outPut").click(function() {
			var par = "?startDate=" + $("#startDate").val() + "&endDate="
			+ $("#endDate").val() + "&status=" + $("#status").val()
			+ "&calType=" + $("#cal_Type").val() + "&content="
			+ $("#content").val();
			window.location.href = contextPath + "/calendar/act/CalendarAct/CalendarExport.act" + par;
		});
	})
	function searchCalendar() {
		var par = "?startDate=" + $("#startDate").val() + "&endDate="
				+ $("#endDate").val() + "&status=" + $("#status").val()
				+ "&calType=" + $("#cal_Type").val() + "&content="
				+ $("#content").val();
		var requestUrl = contextPath
				+ "/calendar/act/CalendarAct/searchCalendar.act" + par;
		doSearch(requestUrl);
	}
	function doSearch(requestUrl) {
		ajaxLoading();
		$('#myTable').datagrid({
			url : requestUrl,
			columns : [ [ {
				field : 'START_DATE',
				title : '开始时间',
				width : '18%',
				align : 'center',
				sortable : true,
			}, {
				field : 'END_DATE',
				title : '结束时间',
				width : '18%',
				align : 'center',
			}, {
				field : 'CAL_TYPE',
				title : '事务类型',
				width : '12%',
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					if (rowData.CAL_TYPE == '1') {
						return "工作日程";
					} else if(rowData.CAL_TYPE == '2'){
						return "个人日程";
					}else{
						return "公开日程";
					}
				}
			}, {
				field : 'CONTENT',
				title : '事务内容',
				width : '25%',
				align : 'center'
			}, {
				field : 'STATUS',
				title : '完成状态',
				width : '12%',
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					if (rowData.STATUS == '0') {
						return "未开始";
					} else if(rowData.STATUS == '1'){
						return "已完成";
					}else if(rowData.STATUS == '3'){
						return "进行中";
					}else{
						return "已超时";
					}
				}
			}, {field : 'OPT',title : '操作',width : '16%',align : 'center',
				formatter:function(value,rowData,rowIndex){
                    if(rowData.ACCOUNT_ID==rowData.FROM_ID){
                        return rowData.OPT+"<a href=\"javascript:void(0);\" onclick=\"updateCal('"+rowData.CALENDAR_ID+"')\">修改</a><a href=\"javascript:void(0);\" onclick=\"deleteCal('"+rowData.CALENDAR_ID+"')\">删除</a>";
                    }else{
                        return  rowData.OPT;
                    }
                }
			} ] ],
			collapsible : true,
			method : 'POST',
			sortName : 'START_DATE',
			sortOrder: 'DESC',
			pagination : true,
			striped : true,
			singleSelect : true,
			remoteSort : true,
			onLoadSuccess:function(data){  
	            if(data.total == 0){
	   	  			$('#myTable').datagrid('appendRow',{START_DATE:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'START_DATE', colspan: 6 });
	   	  		 $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
	        	}
	            ajaxLoadEnd();
	        }
		});
		var p = $('#myTable').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15, 20 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	function showCal(cal_id){
	   $("#savedata").css("display","none");
	   var url = contextPath + "/calendar/detail.jsp?id=" + cal_id+"&type=2";
	   $("#modal-body").html("<iframe id=\"modaliframe\"  name=\"modaliframe\" frameborder=\"0\"></iframe>");
  	   $("#modaliframe").attr("src",url);
	   $("#myModalLabel").html("查看详情");
	   $("#div-modal-dialog").width(455);
	   $("#modaliframe").width(450);
	   $("#modaliframe").height(300);
	   $('#myModals').modal({backdrop: 'static', keyboard: false});
	   $('#myModals').modal('show');
	}
</script>
</html>
