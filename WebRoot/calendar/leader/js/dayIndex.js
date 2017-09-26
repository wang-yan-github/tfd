
function createTable(data,weekStartDate,weekEndDate,week){
	var weekArray = ['星期日','星期一' , '星期二' ,'星期三','星期四' , '星期五' ,'星期六' ];
	var tableHead="<table id=\"cal_table\" class=\"table table-bordered\" style=\"width: 100%;\">" +
			"<tr id=\"tbl_header\" class=\"tbl_header\">" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\" width=\"150px;\" >姓名</td>";
			if(weekStartDate==formatDate(new Date())){
				tableHead +="<td class=\"fc-agenda-axis fc-first table_head\" style=\"background-color:#FCF8E3;\" align=\"center\">"+weekArray[week]+"<br>"+weekStartDate+"</td>";
			}else{
				tableHead +="<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">"+weekArray[week]+"<br>"+weekStartDate+"</td>";
			}
			 tableHead += "</tr>";
	var tableBody="";
	var tableFoot="</table>";
	if(data!=null){
			for(var i=0;data.length>i;i++){
				tableBody+="<tr><td style=\" text-align:center\" align=\"center\" nowrap>";
				tableBody+=data[i].userName+"</td>";
				var calendarData=data[i].calendarData;
				if(formatDate(new Date())==weekStartDate){
					tableBody+="<td onmouseover=\"showMenu('"+i+"-"+1+"');\" onmouseout=\"hideMenu('"+i+"-"+1+"');\" class=\"TableData\" style=\"background-color:#FCF8E3;\" align=\"center\">";
				}else{
					tableBody+="<td onmouseover=\"showMenu('"+i+"-"+1+"');\" onmouseout=\"hideMenu('"+i+"-"+1+"');\" class=\"TableData\" align=\"center\">";
				}		
				if(data[i].isManager=="true"){
					tableBody+="<div style=\"height:25px;width:100px;margin-bottom:3px;margin-top:-5px;\"  title=\"单击建立日程\"><div id=\"new_affair_"+i+"-"+1+"\"   style=\"display:none;height:100%;width:100%;border:#CCC 1px dashed;line-height:25px;\"><a href=\"javascript:void(0)\" onclick=\"javascript:addCalByLeader('"+data[i].accountId+"','"+weekStartDate+"','"+1+"')\" >新建日程</a></div></div>";
				}
				var dayData1=[];
				if(calendarData!=null){
					for(var j=0;calendarData.length>j;j++){
						var tmp= {};
						var cDaty=getWeekDay(calendarData[j].start,0);
						if(cDaty==weekStartDate){
							tmp.calendarId=calendarData[j].id;
							tmp.startDate=calendarData[j].start;
							tmp.endDate=calendarData[j].end;
							tmp.content=calendarData[j].title;
							tmp.className=calendarData[j].className;
							tmp.fromName=calendarData[j].fromName;
							tmp.editable=calendarData[j].editable;
							dayData1.push(tmp);
						}
					}
					for(var s=0;dayData1.length>s;s++){
						tableBody+="<div onclick=\"CancelBuble('"+dayData1[s].calendarId+"','"+dayData1[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData1[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
							+"(<span style=\"color:#00CD00;\">"+dayData1[s].fromName+"</span>)"+dayData1[s].startDate.substr(10,14)+"<br>"+dayData1[s].content+"</a></div></div>";
					}	
				}
			tableBody+="</td>";
			tableBody+="</tr>";
		}
	}
	var htmlCode=tableHead+tableBody+tableFoot;
	$("#calendarTable").html(htmlCode);	
}

//获得本周的开端日期 
function getWeekStartDate(date) 
{ 
	var nowDayOfWeek = date.getDay()-1; //今天本周的第几天 
	var nowDay = date.getDate(); //当前日 
	var nowMonth = date.getMonth(); //当前月 
	var nowYear = date.getFullYear(); //当前年 
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek); 
	return formatDate(weekStartDate); 
}
//获得本周的停止日期 
function getWeekEndDate(date) 
{ 
	var nowDayOfWeek = date.getDay()-1; //今天本周的第几天 
	var nowDay = date.getDate(); //当前日 
	var nowMonth = date.getMonth(); //当前月 
	var nowYear = date.getFullYear(); //当前年 
	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek)); 
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
	date.setDate(date.getDate() + parseInt(i));
	var year = date.getFullYear();
	var month=date.getMonth()+1;
	if(month<10)
		{
		month="0"+month;
		}
	var day=date.getDate();
	if(day < 10){
		day = "0"+day;
	}
	return year+"-"+month+"-"+day;
}
function show_menu(){
	if(document.getElementById("status_menu").style.display=="none"){
        document.getElementById("status_menu").style.display="block";
    }else if(document.getElementById("status_menu").style.display=="block") 
    {
         document.getElementById("status_menu").style.display="none";
    }
}
function addCalByLeader(accountId,weekStartDate,k){
	weekStartDate = getWeekDay(weekStartDate,k-1);
     var url = contextPath + "/calendar/leader/add.jsp?accountId="+accountId+"&weekStartDate="+weekStartDate+"&k="+k;
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

function set_day(i){
	var days = $("#day").find("option").length;
    if(i==1){
		if($("#day").val()==days){
			if($("#month").val()=='12'){
				$("#year").val(parseInt($("#year").val())+1);
				$("#month").val(1);
				$("#day").val(1);
			}else{
				$("#month").val(parseInt($("#month").val())+1);
				$("#day").val(1);
			}
		}else{
			$("#day").val(parseInt($("#day").val())+1);
		}
		My_Submit();
	}else{
		if($("#day").val()=='1'){
			if($("#month").val()=='1'){
				$("#year").val($("#year").val()-1);
				$("#month").val(12);
				$("#day").val(31);
			}else{
				$("#month").val($("#month").val()-1);
				var month = $("#month").val();
  				var nowYear = $("#year").val();
  				var day = 0;
  				if(month==2){
	 				day= nowYear % 4 == 0 ? 29 : 28;
  				}else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
					day = 31;
  				}else{
					day = 30;
  				}
				$("#day").val(day);
			}
		}else{
			$("#day").val($("#day").val()-1);
		}
		My_Submit();
	}
}
function set_month()
{
  var month = $("#month").val();
  var nowYear = $("#year").val();
  var days = 0;
  if(month==2){
	 days= nowYear % 4 == 0 ? 29 : 28;
  }else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
	days = 31;
  }else{
	days = 30;
  }
  setDayHtml(days);
  My_Submit();
}
function My_Submit()
{
	var nowMonth = 0;
	if($('#month').val() < 10){
		nowMonth = "0"+$('#month').val();
	}else{
		nowMonth = $('#month').val();
	}
	var nowDay = 0;
	if($('#day').val() < 10){
		nowDay = "0"+$('#day').val();
	}else{
		nowDay = $('#day').val();
	}
	var startDate = $('#year').val()+"-"+nowMonth+"-"+nowDay;
	var endDate = $('#year').val()+"-"+nowMonth+"-"+nowDay;
	$("#DateTitle").html($('#year').val()+"年"+nowMonth+"月"+nowDay+"日");
    getTableHtml($('#deptId').val(),startDate,endDate,$('#status').val());
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
	var date = new Date(weekStartDate);
	var week = date.getDay();
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
 			createTable(data,weekStartDate,weekEndDate,week);
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

function setDayHtml(days){
	var nowDay = $("#day").val();
	var str = "";
	for(var i = 1 ; i <= days ; i++){
		str += "<option value="+i+" >"+i+"日</option>";
	}
	$("#day").html(str);
	$("#day").val(nowDay);
}
