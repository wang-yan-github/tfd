
function createTable(data,weekStartDate,weekEndDate)
{
	var weekArray = ['','星期一' , '星期二' ,'星期三','星期四' , '星期五' ,'星期六' ,'星期日'];
	var tableHead="<table id=\"cal_table\" class=\"table table-bordered\" style=\"width: 100%;\">" +
			"<tr id=\"tbl_header\" class=\"tbl_header\">" +
			"<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\" nowrap>姓名</td>";
			for (var i=0; i < 7; i++) {
				if(getWeekDay(weekStartDate,i)==formatDate(new Date())){
					tableHead +="<td class=\"fc-agenda-axis fc-first table_head\" style=\"background-color:#FCF8E3;\" align=\"center\">"+weekArray[i+1]+"<br>"+getWeekDay(weekStartDate,i)+"</td>";
				}else{
					tableHead +="<td class=\"fc-agenda-axis fc-first table_head\" align=\"center\">"+weekArray[i+1]+"<br>"+getWeekDay(weekStartDate,i)+"</td>";
				}
			};
			 tableHead += "</tr>";
	var tableBody="";
	var tableFoot="</table>";
	if(data!=null)
		{
			for(var i=0;data.length>i;i++)
				{
				tableBody+="<tr><td style=\" text-align:center\" align=\"center\" nowrap>";
				tableBody+=data[i].userName+"</td>";
					var calendarData=data[i].calendarData;
					for(var k=1;k<=7;k++)
						{
							if(formatDate(new Date())==getWeekDay(weekStartDate,k-1)){
								tableBody+="<td onmouseover=\"showMenu('"+i+"-"+k+"');\" onmouseout=\"hideMenu('"+i+"-"+k+"');\" class=\"TableData\" style=\"background-color:#FCF8E3;\" align=\"center\">";
							}else{
								tableBody+="<td onmouseover=\"showMenu('"+i+"-"+k+"');\" onmouseout=\"hideMenu('"+i+"-"+k+"');\" class=\"TableData\" align=\"center\">";
							}
						if(data[i].isManager=="true"){
							tableBody+="<div style=\"height:25px;width:100px;margin-bottom:3px;margin-top:-5px;\"  title=\"单击建立日程\"><div id=\"new_affair_"+i+"-"+k+"\"   style=\"display:none;height:100%;width:100%;border:#CCC 1px dashed;line-height:25px;\"><a href=\"javascript:void(0)\" onclick=\"javascript:addCalByLeader('"+data[i].accountId+"','"+weekStartDate+"','"+k+"')\" >新建日程</a></div></div>";
						}
						var dayData1=[];
						var dayData2=[];
						var dayData3=[];
						var dayData4=[];
						var dayData5=[];
						var dayData6=[];
						var dayData7=[];
						if(calendarData!=null)
						{
							for(var j=0;calendarData.length>j;j++)
							{
								var tmp= {};
								var cDaty=getWeekDay(calendarData[j].start,0);
								if(cDaty==weekStartDate)
									{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData1.push(tmp);
									}else if(cDaty==getWeekDay(weekStartDate,1))
									{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData2.push(tmp);
									}else if(cDaty==getWeekDay(weekStartDate,2))
										{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData3.push(tmp);
									}else if(cDaty==getWeekDay(weekStartDate,3))
										{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData4.push(tmp);
									}else if(cDaty==getWeekDay(weekStartDate,4))
										{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData5.push(tmp);
									}else  if(cDaty==getWeekDay(weekStartDate,5))
										{
										tmp.calendarId=calendarData[j].id;
										tmp.startDate=calendarData[j].start;
										tmp.endDate=calendarData[j].end;
										tmp.content=calendarData[j].title;
										tmp.className=calendarData[j].className;
										tmp.fromName=calendarData[j].fromName;
										tmp.editable=calendarData[j].editable;
										dayData6.push(tmp);
									}
									else  if(cDaty==weekEndDate)
									{
									tmp.calendarId=calendarData[j].id;
									tmp.startDate=calendarData[j].start;
									tmp.endDate=calendarData[j].end;
									tmp.content=calendarData[j].title;
									tmp.className=calendarData[j].className;
									tmp.fromName=calendarData[j].fromName;
									tmp.editable=calendarData[j].editable;
									dayData7.push(tmp);
								}
							}
							if(k==1)
								{
									for(var s=0;dayData1.length>s;s++)
										{
										tableBody+="<div onclick=\"CancelBuble('"+dayData1[s].calendarId+"','"+dayData1[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData1[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
											+"(<span style=\"color:#00CD00;\">"+dayData1[s].fromName+"</span>)"+dayData1[s].startDate.substr(10,14)+"<br>"+dayData1[s].content+"</a></div></div>";
										}
								}
							if(k==2)
							{
								for(var s=0;dayData2.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData2[s].calendarId+"','"+dayData2[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData2[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\"  style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData2[s].fromName+"</span>)"+dayData2[s].startDate.substr(10,14)+"<br>"+dayData2[s].content+"</a></div></div>";
									}
							}
							if(k==3)
							{
								for(var s=0;dayData3.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData3[s].calendarId+"','"+dayData3[s].editable+"');\"  class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData3[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData3[s].fromName+"</span>)"+dayData3[s].startDate.substr(10,14)+"<br>"+dayData3[s].content+"</a></div></div>";
									}
							}
							if(k==4)
							{
								for(var s=0;dayData4.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData4[s].calendarId+"','"+dayData4[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData4[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData4[s].fromName+"</span>)"+dayData4[s].startDate.substr(10,14)+"<br>"+dayData4[s].content+"</a></div></div>";
									}
							}
							if(k==5)
							{
								for(var s=0;dayData5.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData5[s].calendarId+"','"+dayData5[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData5[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData5[s].fromName+"</span>)"+dayData5[s].startDate.substr(10,14)+"<br>"+dayData5[s].content+"</a></div></div>";
									}
							}
							if(k==6)
							{
								for(var s=0;dayData6.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData6[s].calendarId+"','"+dayData6[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData6[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData6[s].fromName+"</span>)"+dayData6[s].startDate.substr(10,14)+"<br>"+dayData6[s].content+"</a></div></div>";
									}
							}
							if(k==7)
							{
								for(var s=0;dayData7.length>s;s++)
									{
									tableBody+="<div onclick=\"CancelBuble('"+dayData7[s].calendarId+"','"+dayData7[s].editable+"');\" class=\"fc-event fc-event-vert fc-event-draggable fc-event-start fc-event-end ui-draggable ui-resizable "+dayData7[s].className+"\" style=\"margin-bottom:5px;\"><div class=\"fc-event-inner\"><span style=\"white-space:nowrap;\"></span><a  class=\"fc-event-title\" id=\"task_840\" href=\"javascript:my_task_note(840,1,1,\"\")\" style=\"color:#686868\" title=\"点击查看任务详情\">"
										+"(<span style=\"color:#00CD00;\">"+dayData7[s].fromName+"</span>)"+dayData7[s].startDate.substr(10,14)+"<br>"+dayData7[s].content+"</a></div></div>";
									}
							}
						}
						tableBody+="</td>";
					}
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
	if(day<10)
		{
			day="0"+day;
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
function getNowWeek(){
	var d = new Date();
	d.setFullYear(d.getFullYear(),0,1);
	var day = d.getDay();
	var fistweekleft = (7-day)%7;
	d.setFullYear(d.getFullYear(),0,1+fistweekleft+1);
	var d1 = new Date();
	return 2+parseInt((d1.getTime()-d.getTime())/1000/60/60/24/7);
}

function set_week(i){
  if(i==1){
		if($("#week").val()=='53'){
			$("#year").val(parseInt($("#year").val())+1);
			$("#week").val(1);
		}else{
			$("#week").val(parseInt($("#week").val())+1);
		}
		My_Submit();
	}else{
		if($("#week").val()=='1'){
			$("#year").val($("#year").val()-1);
			$("#week").val(53);
		}else{
			$("#week").val($("#week").val()-1);
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
	var startDate = getXDate($('#year').val(),$('#week').val(),1);
	var endDate = getXDate($('#year').val(),parseInt($('#week').val())+1,0);
	$("#DateTitle").html($('#year').val()+"年第"+$('#week').val()+"周");
    getTableHtml($('#deptId').val(),formatDate(startDate),formatDate(endDate),$('#status').val());
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
 		beforeSend:ajaxLoading,
 		error:function(e){
 			alert(e.message);
 		},
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