function createTableHtml(userId,weekStartDate,weekEndDate,status){
	if(userId==null){
		createTable(null,weekStartDate,weekEndDate);
	}else{
		var requestUrl = contextPath+ "/calendar/act/CalendarAct/getCalendarByUserId.act";
	 	$.ajax({
	 		url:requestUrl,
	 		dataType:"json",
	 		data:{userId:userId,weekStartDate:weekStartDate,weekEndDate:weekEndDate,status:status},
	 		beforeSend:ajaxLoading,
	 		success:function(data){
	 			createTable(data,weekStartDate,weekEndDate);
	 			ajaxLoadEnd();
	 		}
	 		
	 	});	
 	}
}
function createTable(data,weekStartDate,weekEndDate)
{
	var tableHead="<table id=\"cal_table\" class=\"table table-bordered\" style=\"padding:0px;\">" +
			"<tr id=\"tbl_header\" class=\"tbl_header\">" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\" nowrap>周数</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期一</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期二</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期三</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期四</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期五</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期六</td>" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">星期天</td>" +
			"</tr>";
	var tableBody="";
	var tableFoot="</table>";
	
		var weeks = (DateDiff(weekStartDate,weekEndDate)+1)/7 ;
		var weekNum = getWeekNumber(weekStartDate);
		var nextDate = weekStartDate;
		for(var i = 0;i < weeks ; i++){
			tableBody += "<tr ><td style=\"text-align:center;width:80px;\" >第"+weekNum+"周</td>";
			for(var j = 0 ; j < 7 ; j++){
				if(nextDate.substr(5,2)==$("#DateTitle").html().substr(5,2)){
					if(formatDate(new Date())==nextDate){
						tableBody+="<td onmouseover=\"showMenu('"+i+"-"+j+"');\" style=\"width:120px;height:80px;background-color:#FCF8E3;vertical-align: top; \" onmouseout=\"hideMenu('"+i+"-"+j+"');\" ><b style=\"float:right;font-size:12px;\">"+nextDate.substr(8,10)+"</b>";
					}else{
						tableBody+="<td onmouseover=\"showMenu('"+i+"-"+j+"');\" style=\"width:120px;height:80px;vertical-align: top;\" onmouseout=\"hideMenu('"+i+"-"+j+"');\" ><div style=\"float:right;font-size:12px;margin-top:0px !important;\">"+nextDate.substr(8,10)+"</div>";
					}
					
				}else{
					tableBody+="<td onmouseover=\"showMenu('"+i+"-"+j+"');\" style=\"width:120px;height:80px; vertical-align: top;\" onmouseout=\"hideMenu('"+i+"-"+j+"');\" ><b style=\"float:right;font-size:12px;color:#CCC;\">"+nextDate.substr(8,10)+"</b>";
				}
				if(data){
					if(data.isManager=="true"){
						tableBody+="<div style=\"height:25px;width:100px;z-index:2;margin-left:10px;\"  title=\"单击建立日程\"><div id=\"new_affair_"+i+"-"+j+"\"   style=\"display:none;height:100%;width:100%;border:#CCC 1px dashed;line-height:25px;text-align:center;\"><a href=\"javascript:void(0)\" onclick=\"javascript:addCalByLeader('"+nextDate.substr(0,10)+"')\" >新建日程</a></div></div>";
					}
					var rows = data.rows;
					if(rows!=null){
						$.each(rows,function(index,row){
							if(row.start.substr(0,10)==nextDate){
								 tableBody+="<div onclick=\"CancelBuble('"+row.id+"','"+row.editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+row.className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
									+"(<span style=\"color:#00CD00;\">"+row.fromName+"</span>)"+row.start.substr(10,14)+"<br>"+row.title+"</a></div></div>";
							}
							
						});
					}
				}
				tableBody +="</td>";
				nextDate = new Date(nextDate);
				nextDate = new Date(nextDate.getTime() + 24*60*60*1000);
				var month ="";
				var day = "";
				if(nextDate.getMonth()+1 < 10){
					month = "0"+(nextDate.getMonth()+1);
				}else{
					month = nextDate.getMonth()+1;
				}
				if(nextDate.getDate() < 10){
					day = "0"+nextDate.getDate();
				}else{
					day = nextDate.getDate();
				}
				nextDate = nextDate.getFullYear()+"-"+month+"-"+day;
			}
			tableBody +="</tr>";
			weekNum++;
		}
		var htmlCode=tableHead+tableBody+tableFoot;
	$("#calendarTable").html(htmlCode);	
	}
	

//获得本月的开端日期 
function getWeekStartDate(date) 
{ 
	var nowMonth = date.getMonth()+1; //当前月 
	var nowYear = date.getFullYear(); //当前年 
	date = new Date(nowYear,(nowMonth-1),"1");
	var nowDayOfWeek = date.getDay()-1; //今天本周的第几天 
	var nowDay = date.getDate(); //当前日 
	var weekStartDate = new Date(nowYear, (nowMonth-1), nowDay - nowDayOfWeek); 
	return formatDate(weekStartDate); 
}
//获得本周的停止日期 
function getWeekEndDate(date) 
{ 	
	var nowMonth = date.getMonth()+1; //当前月 
	var nowYear = date.getFullYear(); //当前年
	var days = "";
	if(nowMonth==2){
		 days= nowYear % 4 == 0 ? 29 : 28;
	}else if(nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth == 8 || nowMonth == 10 || nowMonth == 12){
		days = 31;
	}else{
		days = 30;
	}
	date = new Date(nowYear,(nowMonth-1),days);
	var nowDayOfWeek = date.getDay()-1; //今天本周的第几天 
	var nowDay = date.getDate(); //当前日 
	var weekEndDate = new Date(nowYear, (nowMonth-1), nowDay + (6 - nowDayOfWeek)); 
	
	return formatDate(weekEndDate); 
}
//格局化日期：yyyy-MM-dd 
function formatDate(date) 
{ 
	var myyear = date.getFullYear(); 
	var mymonth = date.getMonth()+1; 
	var myweekday = date.getDate(); 
	if(mymonth < 10)
	{ 
		mymonth = "0" + mymonth; 
		} 
	if(myweekday < 10)
	{ 
		myweekday = "0" + myweekday; 
		} 
	return (myyear+"-"+mymonth + "-" + myweekday); 
	}
//日期计算
function getWeekDay(daystr,i)
{
	var str = daystr.replace(/-/g,"/");
	var date=new Date(Date.parse(str));
	date.setDate(date.getDate() + i);
	var year = date.getFullYear();
	var month=date.getMonth()+1;
	if(month<10)
		{
		month="0"+month;
		}
	var day=date.getDate();
	if(day<10)
		{
			day="0"+day;
		}
	return year+"-"+month+"-"+day;
}
   function DateDiff(startDate, endDate){ 
    var d1 = new Date(startDate.replace(/-/g, "/")); 
    var d2 = new Date(endDate.replace(/-/g, "/")); 
    var time = d2.getTime() - d1.getTime(); 
    return parseInt(time / (1000 * 60 * 60 * 24));
}   
function show_menu(){
	if(document.getElementById("status_menu").style.display=="none"){
        document.getElementById("status_menu").style.display="block";
    }else if(document.getElementById("status_menu").style.display=="block") 
    {
         document.getElementById("status_menu").style.display="none";
    }
}
function addCalByLeader(weekStartDate){
	var userId = $("#userDiv").val();
    var url = contextPath + "/calendar/leader/add.jsp?accountId="+userId+"&weekStartDate="+weekStartDate;
    $("#modaliframe").attr("src",url);
    $("#myModalLabel").html("添加日程");
    $("#div-modal-dialog").width(700);
    $("#modaliframe").width(695);
    $("#modaliframe").height(500);
    $('#myModals').modal({backdrop: 'static', keyboard: true});
    $('#myModals').modal('show');
    $('#btn_edit').hide();
    $('#btn_delete').hide();
    $("#btn-save").show();
}
function set_view(view, cname){
	if(view=='month'){
		parent["sort"].$("#DateType").val(2);
	}else if(view=='day'){
		parent["sort"].$("#DateType").val(3);
	}else{
		parent["sort"].$("#DateType").val(1);
	}
    window.location = contextPath + "/calendar/leader/"+view+".jsp?deptId="+deptId;
}
function getNowMonth(){
	var d = new Date();
	var month=d.getMonth()+1;
	return month;
}

function set_week(op)
{
  document.form1.BTN_OP.value=op+" week";
  My_Submit();
}
function set_month(i){
	if(i==1){
		if($("#month").val()=='12'){
			$("#year").val(parseInt($("#year").val())+1);
			$("#month").val(1);
		}else{
			$("#month").val(parseInt($("#month").val())+1);
		}
		My_Submit();
	}else{
		if($("#month").val()=='1'){
			$("#year").val($("#year").val()-1);
			$("#month").val(12);
		}else{
			$("#month").val($("#month").val()-1);
		}
		My_Submit();
	}
}

function set_year(op)
{
  document.form1.BTN_OP.value=op+" year";
  My_Submit();
}
function My_Submit()
{
	
	var days = "";
	var nowMonth = $('#month').val();
	var nowYear = $('#year').val();
	if(nowMonth==2){
		 days= nowYear % 4 == 0 ? 29 : 28;
	}else if(nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth == 8 || nowMonth == 10 || nowMonth == 12){
		days = 31;
	}else{
		days = 30;
	}
	if(nowMonth < 10){
		nowMonth = "0"+nowMonth;
	}
	var startDate = $('#year').val()+"-"+nowMonth+"-01";
	var endDate = $('#year').val()+"-"+nowMonth+"-"+days;
	startDate = getWeekStartDate(new Date(startDate));
	endDate = getWeekEndDate(new Date(endDate));
	var month = $("#month").val();
	if(month < 10){
		month = "0"+month;
	}
	$("#DateTitle").html($("#year").val()+"年"+month+"月");
    createTableHtml($('#userDiv').val(),startDate,endDate,$('#status').val());
}

//这个方法将取得某年(year)第几周(weeks)的星期几(weekDay)的日期
function getXDate(year,weeks,weekDay){
//用指定的年构造一个日期对象，并将日期设置成这个年的1月1日
//因为计算机中的月份是从0开始的,所以有如下的构造方法
var date = new Date(year,"0","1");

//取得这个日期对象 date 的长整形时间 time
var time = date.getTime();

//将这个长整形时间加上第N周的时间偏移
//因为第一周就是当前周,所以有:weeks-1,以此类推
//7*24*3600000 是一星期的时间毫秒数,(JS中的日期精确到毫秒)
time+=(weeks-1)*7*24*3600000;

//为日期对象 date 重新设置成时间 time
date.setTime(time);
return getNextDate(date,weekDay);
}
function getNextDate(nowDate,weekDay){
//0是星期日,1是星期一,...
weekDay%=7;
var day = nowDate.getDay();
var time = nowDate.getTime();
var sub = weekDay-day;
time+=sub*24*3600000;
nowDate.setTime(time);
return nowDate;
}
function getTableHtml(deptId,weekStartDate,weekEndDate,status)
{
	var requestUrl = contextPath+"/calendar/act/CalendarAct/getManageRangeCanlenarAct.act";
 	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{deptId:deptId,weekStartDate:weekStartDate,weekEndDate:weekEndDate,status:status},
 		error:function(e){
 			alert(e.message);
 		},
 		beforeSend:ajaxLoading,
 		success:function(data){
 			createTable(data,weekStartDate,weekEndDate);
 			ajaxLoadEnd();
 		}
 	});
}
function showMenu(id){
	$('#new_affair_'+id).css("display","block");
}
function hideMenu(id){
	$('#new_affair_'+id).css("display","none"); 
}
function CancelBuble(id,flag){
	if(flag == "false"){
		$("#btn_delete").hide();
		$("#btn_edit").hide();
	}else{
		$('#btn_edit').show();
    	$('#btn_delete').show();
	}
    var url = contextPath + "/calendar/detail.jsp?id=" + id+"&type=2";
    $("#modaliframe").attr("src",url);
    $("#myModalLabel").html("查看详情");
    $("#div-modal-dialog").width(455);
    $("#modaliframe").width(450);
    $("#modaliframe").height(280);
    $('#myModals').modal({backdrop: 'static', keyboard: false});
    $('#myModals').modal('show');
    $('#btn-save').hide();
}

function GoToday(){
	var weekStartDate=getWeekStartDate(new Date());
	var weekEndDate=getWeekEndDate(new Date());
	setDate();
	createTableHtml($("#userDiv").val(),weekStartDate,weekEndDate,$("#status").val());
}

 function isLeapYear(year) {
     return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
 }

 /**
10  * 获取某一年份的某一月份的天数
11  *
12  * @param {Number} year
13  * @param {Number} month
14  */
 function getMonthDays(year, month) {
     return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (isLeapYear(year) ? 29 : 28);
 }
/**
27  * 获取某年的某天是第几周
28  * @param {Number} y
29  * @param {Number} m
30  * @param {Number} d
31  * @returns {Number}
  */
 function getWeekNumber(date) {
     var now = new Date(date),
         year = now.getFullYear(),
         month = now.getMonth(),
         days = now.getDate();
     //那一天是那一年中的第多少天
     for (var i = 0; i < month; i++) {
         days += getMonthDays(year, i);
     }
 
     //那一年第一天是星期几
     var yearFirstDay = new Date(year, 0, 1).getDay() || 7;
 
     var week = null;
     if (yearFirstDay == 1) {
         week = Math.ceil(days / yearFirstDay);
     } else {
         days -= (7 - yearFirstDay + 1);
         week = Math.ceil(days / 7) + 1;
    }

     return week;
 }
