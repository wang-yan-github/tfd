<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%String boardroomId=request.getParameter("boardroomId"); %>
<%String stime=request.getParameter("stime"); %>
<%String etime=request.getParameter("etime");%>
<script src="<%=contextPath%>/metting/requestmetting/bookingtime/module.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/book.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/jquery.ezmark.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/jquery.mousewheel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/requestmetting/bookingtime/mwheelIntent.js"></script>
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/uicore/jquery.ui.all.css" rel="stylesheet" type="text/css"> 
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/jquery.jscrollpane.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/index.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/apply.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/other.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/system/styles/css/style1/meeting/css/ezmark.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/css/style1/meeting/css/book.css">
<script type="text/javascript">
//日期权限
var weekdays;
var boardroomId="<%=boardroomId%>";

Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth()+3)/3),
        "S" : this.getMilliseconds()
    }
    
    if(/(y+)/.test(format))
    {
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
//预约的时间段
var bookerDate="";
function getdate(){
	var stime="<%=stime%>";
	var etime="<%=etime%>";
	if(stime=="null"){
	var endtime=new Date();
	endtime.setDate(endtime.getDate()+6);
	bookerDate=initDate(new Date().format("yyyy-MM-dd"),endtime.format("yyyy-MM-dd"));
	$("#BTIME").val(new Date().format("yyyy-MM-dd"));
	$("#ETIME").val(endtime.format("yyyy-MM-dd"));
	}else{
		bookerDate=initDate(stime,etime);
		$("#BTIME").val(stime);
		$("#ETIME").val(etime);
	}
}
//判断日期是星期几
function lowerCaseToUpper(num)
{
    var upperWeekNum;
    if (parseInt(num) == 0)
    {
        upperWeekNum = "日";
    }
    else if (parseInt(num) == 1)
    {
        upperWeekNum = "一";
    }
    else if (parseInt(num) == 2)
    {
        upperWeekNum = "二";
    }
    else if (parseInt(num) == 3)
    {
        upperWeekNum = "三";
    }
    else if (parseInt(num) == 4)
    {
        upperWeekNum = "四";
    }
    else if (parseInt(num) == 5)
    {
        upperWeekNum = "五";
    }
    else if (parseInt(num) == 6)
    {
        upperWeekNum = "六";
    }
    return upperWeekNum;
}

function stringToTime(string) {
    var f = string.split(' ', 2);
    var d = (f[0] ? f[0] : '').split('-', 3);
    var t = (f[1] ? f[1] : '').split(':', 3);
    return (new Date(
   parseInt(d[0], 10) || null,
   (parseInt(d[1], 10) || 1) - 1,
parseInt(d[2], 10) || null,
parseInt(t[0], 10) || null,
parseInt(t[1], 10) || null,
parseInt(t[2], 10) || null
)).getTime();
}

function DateDiff(date1, date2) {
    var type1 = typeof date1, type2 = typeof date2;
    if (type1 == 'string')
        date1 = stringToTime(date1);
    else if (date1.getTime)
        date1 = date1.getTime();
    if (type2 == 'string')
        date2 = stringToTime(date2);
    else if (date2.getTime)
        date2 = date2.getTime();
    return (date1 - date2) / (1000 * 60 * 60 * 24); 
}

function initDate(startTimeStr,endTimeStr)
{
    var differ = DateDiff(endTimeStr,startTimeStr);
    var nextDate;
    var localDate= new Date(Date.parse(startTimeStr.replace(/-/g,   "/"))); 
    var currentWeek;
    var dateArr=new Array();
    for(var i=0;i<=differ;i++)
    {
        nextDate=localDate.getTime() + i*24*60*60*1000;
        var declareNextDate=new Date(nextDate);
        currentWeek=declareNextDate.getDay();
        var formatDate=declareNextDate.format("yyyy-MM-dd")+"(周"+lowerCaseToUpper(currentWeek)+")";
        dateArr[i]=formatDate;
    }
    return dateArr;
}

function  getDateFromString(strDate)
{
    var  arrYmd = strDate.split("-");
    var  numYear = parseInt(arrYmd[0],10);
    var  numMonth = parseInt(arrYmd[1],10)-1;
    var  numDay= parseInt(arrYmd[2],10);
    var  leavetime=new Date(numYear,numMonth,numDay);
    return leavetime;
}

function display1(t)
{
    var el = document.getElementById("areabox");
    if (t.checked)
    {
        el.className = "areabox hidden";
    }
    else
    {
        el.className = "areabox";
    }
}
function getboardroomId(boardroomId){
	var url=contextPath+"/meeting/act/BoardroomAct/getIdboardroomAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{boardroomId:boardroomId},
		async:false,
		error:function(e){
		},
		success:function(data){
			weekdays=data.allowTime;
			$("#boardroomId").val(data.boardroomId);
	}
	});
}
function lookboardroom(){
	var url=contextPath+"/meeting/act/BoardroomAct/getaccountAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
			$("#boardroomId").append("<option value=\""+data[i].boardroomId+"\">"+data[i].boardroomName+"</option>");
			}
			getboardroomId(boardroomId);
	}
	});
}
function choiceboardroom(){
	var boardroomId=$("#boardroomId").val();
	var url=contextPath+"/metting/requestmetting/meetingtime.jsp?boardroomId="+boardroomId;
	window.location=url;
}
$(function() {
	lookboardroom();
	getdate();
    //do active
    $(".meeting:not(.disabled)").live("mousedown", function()
    {
        $(".active.meeting").removeClass("active");
        $(this).addClass("active");
    });
    
    $('html,body').bind('scroll', function()
    {
        fixHaslayout($('.week-booker, .axis-y-name>span'));
    });
    
    $('#show_more').click(function()
    {
        if($('#show_title').css('display') == 'none')
        {
            $('#show_more').css('background','url(/tfd/system/styles/css/style1/meeting/images/book/hscroll_arrow.png) -2px center no-repeat');
            $('#show_title').show();
            $('#show_more').attr('title','收缩00:00-07:00');
            $(".axis-x").width('2882px');
            $("#booker_title").width('2883px');
            bookers[0].setStart(0 * 60);
            bookers[0].setLimit(24 * 60 + 1);  
        }
        else
        {
            $('#show_more').css('background','url(/tfd/system/styles/css/style1/meeting/images/book/hscroll_arrow.png) -25px center no-repeat');
            $('#show_title').hide();
            $('#show_more').attr('title','展开00:00-07:00');
            $(".axis-x").width('2042px');
            $("#booker_title").width('2043px');
            bookers[0].setStart(7 * 60);
            bookers[0].setLimit(17 * 60 + 1);  
        }
    });
    
    $('input[type=checkbox]').ezMark();
    initMeetings();
    document.onselectstart = function()
    {
        return false;    
    }
});

function fixHaslayout(o)
{
    if(o.nodeType)
    {
        o.style.zoom = 1;
        o.style.zoom = 'normal';
    }
    else if(o instanceof jQuery)
    {
        o.each(function(){
            fixHaslayout(this);
        });
    }
}
var newMeeting = {};
var bookers = [];
function initMeetings()
{
	var rooms;
	var url=contextPath+"/meeting/act/MeetingrequestAct/getboardIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{boardroomId:boardroomId,stime:$("#BTIME").val(),etime:$("#ETIME").val()},
		async:false,
		error:function(e){
		},
		success:function(data){
			rooms=data;
	}
	}); 
	//day 是在该时间段的第几天  status 申请状态    id:会议申请的meeting_id
    $.each(rooms, function(i, e) {
        function handle(index, start, limit){
            newMeeting.id = index;
            newMeeting.day = index;
            newMeeting.roomId=e.id;
            newMeeting.start = start;
            newMeeting.limit=limit;
        }
        
        var date = [];
        $.each(bookerDate, function(j, date1) {
            if(weekdays.indexOf(date1.substr(12,1))>=0)
            {
                s=1;
            }
            else
            {
                s=0;
            }
            
            var info = {
                date: date1,
                status: s
            };
            date.push(info);
        });
        var wb = new WeekBooker({
            renderTo: "#meeting",
            distance: 30,
            name: e.name,
            date: date,
            update: handle,
            start: 7 * 60,
            limit: 17 * 60 + 1,
            beforecreate: function(day){
                if(e.blackList==0)
                {
                    parent.layer.msg('不能申请此会议室，由于您违法了会议室的规章制度！',function(){});
                    return false;
                }
                var d = bookerDate[day];
                if(weekdays.indexOf(d.substr(12,1))<0)
                {
                    parent.layer.msg('此会议室此时段禁止申请',function(){});
                    fixHaslayout($('body > .content'))
                    return false;
                }
            },
            oncreate: handle
        });
        bookers.push(wb);
        
        $.each(e.meeting, function(k, t) {
            var meeting = new Meeting({
                content: t.content,
                start: t.start,
                limit: t.limit,
                id: t.id,
                ppm: 2,
                onclick: function(e, meetingId, start, limit){
                    if(t.s_priv == '1')
                    {
                    	//弹出已预约的会议信息
                        window.open("/tfd/metting/requestmetting/getIdrequest.jsp?requestId="+t.id,'','height=500,width=820,status=1,toolbar=no,menubar=no,location=no,scrollbars=yes,top=100,left=200,resizable=yes');
                    }
                    else
                    {
                        parent.layer.msg('您没有权限查看该会议',function(){});
                    }
                },
                status: t.status,
                disabled: true
            });
            
            wb.bookers[t.day].add(meeting);
        });
      
    });
}

function getData()
{
    if(!newMeeting.start && newMeeting.start!=0)
    {
        parent.layer.msg('请选择会议室及申请时间！',function(){});
        return "";
    }
    
    for (var i in bookers)
    {
        var b = bookers[i];
        if (b.crowd())
        {
            parent.layer.msg('会议时间冲突',function(){});
            return false;
        }
    }
    
    var newMeetingDate=bookerDate[newMeeting.day].substr(0,10);
    var newMeetingStart=newMeeting.start;
    var newMeetingLimit=newMeeting.limit;
    var hourStart = parseInt(newMeetingStart/60);
    if(hourStart < 10)
    {
        hourStart="0"+hourStart;
    }
    var minStart=parseInt(newMeetingStart%60);
    if(minStart==0)
    {
        minStart="00";
    }
    else
    {
        minStart="30";
    }
    var startTime=newMeetingDate+" "+hourStart+":"+minStart+":00";//拼接出开始时间
    var newMeetingEnd=newMeetingStart+newMeetingLimit;
    var hourEnd=parseInt(newMeetingEnd/60);
    if(hourEnd < 10)
    {
        hourEnd="0"+hourEnd;
    }
    
    var minEnd=parseInt(newMeetingEnd%60);
    if(minEnd==0)
    {
        minEnd="00";
    }
    else
    {
        minEnd="30";
    }
    
    var endTime=newMeetingDate+" "+hourEnd+":"+minEnd+":00";//拼接出结束时间
    var meetingId=newMeeting.roomId;
    
    //跳转页面地址
    var url=contextPath+"/metting/requestmetting/requestmeeting.jsp?boardroomId="+boardroomId+"&startTime="+startTime+"&endTime="+endTime;
    window.location=url;
}
function revert(){
	var url=contextPath+"/metting/requestmetting/selectboardroom.jsp";
	window.location=url;
}
function checktime(){
	var btime=$("#BTIME").val();
	var etime=$("#ETIME").val();
	var boardroomId=$("#boardroomId").val();
	var url=contextPath+"/metting/requestmetting/meetingtime.jsp?boardroomId="+boardroomId+"&stime="+btime+"&etime="+etime;
	window.location=url;
}
</script>
</head>
<style>
	body,html{
		overflow-x:hidden !important;
	}
</style>
<body class="bodycolor" style="position:relative;">
<div id="query" style="align:center;top:0px;">
<form name="form1" action="">
    <table class="TableList" align="center" width="100%">
        <tbody><tr>
            <td style="text-align: right;">会议室选择：</td>              
            <td>
<select id="boardroomId" name="boardroomId" class="form-control " onchange="choiceboardroom();" >

</select>
</td>
               <td style="text-align: right;"> 开始时间：</td>
               <td> 
               <input size="10" type="text" class="form-control"  name="BTIME" id="BTIME" readonly="readonly" style="cursor: pointer;" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;})">
               </td>
                <td style="text-align: right;">结束时间：</td>
                 <td> <input size="10" type="text" class="form-control " readonly="readonly" style="cursor: pointer;" name="ETIME" id="ETIME" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;})">
               </td>
               <td>&nbsp;&nbsp;&nbsp; <input type="button" class="btn btn-primary" value="查询" onclick="checktime();" >
            </td>
        </tr>
    </tbody></table>
</form>
</div>

<div class="content" style="width:98%;min-width: 1000px;_display:inline-block;position:relative;">
    <div class="legend">
        <b>图例说明：</b>
        <label for=""><img src="<%=contextPath%>/system/styles/css/style1/meeting/images/book/explain-a.jpg"><span>未预约</span></label>
        <label for=""><img src="<%=contextPath%>/system/styles/css/style1/meeting/images/book/explain-b.jpg"><span>待批准</span></label>
        <label for=""><img src="<%=contextPath%>/system/styles/css/style1/meeting/images/book/explain-c.jpg"><span>已批准</span></label>
        <label for=""><img src="<%=contextPath%>/system/styles/css/style1/meeting/images/book/explain-d.jpg"><span>进行中</span></label>
        <label for=""><img src="<%=contextPath%>/system/styles/css/style1/meeting/images/book/explain-e.jpg"><span>已结束</span></label>
    </div>
   <div class="contentwrapper" style="padding-left: 160px;">
        <div class="content_left">
            <div class="booker-title" style="width: 100%;">
                <div class="table-title" style="width:152px; float: left;">
                    <ul>
                        <li style="width: 149px"><span>日期</span></li>
                    </ul>
                </div>
            </div>
            <div style="float: left;">
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <div class="axis-y-date"></div>
                            </td>
                            <td id="show_more" style="cursor:pointer;background: url(&#39;/tfd/system/styles/css/style1/meeting/images/book/hscroll_arrow.png&#39;) -25px center no-repeat;" title="展开00:00-07:00">
                                <div style="width:8px;">
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div style="width:1200px;overflow-x:auto;overflow-y:hidden;position:relative;">
            <div class="booker-title" id="booker_title" style="width: 2043px;">
                <div class="axis-x" style="width:2042px;">
                     <span id="show_title" style="display:none;">
                        <div class="first">00:00<br>00:30</div>
                        <div>00:30<br>01:00</div>
                        <div>01:00<br>01:30</div>
                        <div>01:30<br>02:00</div>
                        <div>02:00<br>02:30</div>
                        <div>02:30<br>03:00</div>
                        <div>03:00<br>03:30</div>
                        <div>03:30<br>04:00</div>
                        <div>04:00<br>04:30</div>
                        <div>04:30<br>05:00</div>
                        <div>05:00<br>05:30</div>
                        <div>05:30<br>06:00</div>
                        <div>06:00<br>06:30</div>
                        <div>06:30<br>07:00</div>
                    </span>
                    <div>07:00<br>07:30</div>
                    <div>07:30<br>08:00</div>
                    <div>08:00<br>08:30</div>
                    <div>08:30<br>09:00</div>
                    <div>09:00<br>09:30</div>
                    <div>09:30<br>10:00</div>
                    <div>10:00<br>10:30</div>
                    <div>10:30<br>11:00</div>
                    <div>11:00<br>11:30</div>
                    <div>11:30<br>12:00</div>
                    <div>12:00<br>12:30</div>
                    <div>12:30<br>13:00</div>
                    <div>13:00<br>13:30</div>
                    <div>13:30<br>14:00</div>
                    <div>14:00<br>14:30</div>
                    <div>14:30<br>15:00</div>
                    <div>15:00<br>15:30</div>
                    <div>15:30<br>16:00</div>
                    <div>16:00<br>16:30</div>
                    <div>16:30<br>17:00</div>
                    <div>17:00<br>17:30</div>
                    <div>17:30<br>18:00</div>
                    <div>18:00<br>18:30</div>
                    <div>18:30<br>19:00</div>
                    <div>19:00<br>19:30</div>
                    <div>19:30<br>20:00</div>
                    <div>20:00<br>20:30</div>
                    <div>20:30<br>21:00</div>
                    <div>21:00<br>21:30</div>
                    <div>21:30<br>22:00</div>
                    <div>22:00<br>22:30</div>
                    <div>22:30<br>23:00</div>
                    <div>23:00<br>23:30</div>
                    <div class="last">23:30<br>24:00</div>
                </div>
                
                <div style="clear:left;"></div>
                <div id="meeting_more" style="display:none;"></div>
                <div id="meeting">
                </div>
                
                <div style="clear:left;"></div>
                <div id="meeting_more" style="display:none;"></div>
                <div id="meeting"></div>
        </div>
        <div align="center" style="clear:both;">
        <br>
    <input type="submit" value="提交" class="btn btn-primary"onclick="getData()" >&nbsp;&nbsp;
            <input type="button"  value="返回" class="btn btn-default" onClick="revert()">
        </div>
    </div>
</div>
</body></html>