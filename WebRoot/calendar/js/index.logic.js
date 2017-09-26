var currWeek = '5';
var currDay = '18';
var currMonth = '7';
var currTime = '00:00:00';

function getView(){
	var view = $('#calendar').fullCalendar('getView');
	alert("The view's title is " + view.title);
}

/***
 * 弹出
 */
function openAddCalendar(){
	 $.jBox(addUpdateCalFrom,{title:"新建日程",width:500,height:370,buttons: {}});
	 $("#START_DATE").val($.fullCalendar.formatDate(new Date(),'yyyy-MM-dd HH:mm'));
     $("#END_DATE").val($.fullCalendar.formatDate(new Date(),'yyyy-MM-dd ' + '23:59'));
	setRemindTimePopover();
}



/***
 * overstatus状态类型事件
 */
$(document).ready(function(){
	$("#current_status").mouseover(function(){
		$("#status_menu").css("display","block");
		$(this).css("border-bottom","none");
    });
	$("#current_status").mouseout(function(){
		$(this).css("border-bottom","1px solid #ccc");
    });
	$("#status_menu a").mouseover(function(){
		$("#current_status").css("border-bottom","none");
    });
	$("#status_menu a").mouseout(function(){
		$("#current_status").css("border-bottom","1px solid #ccc");
    });
	
	
	$("#status_menu a").click(function(event){
		var index = $(this).attr('index');//更改状态
		var calId =  $("#calId").val();//日程Id
		//alert(index +":"+ calId);
		if(index=='1'){
			showDiary(calId);
		}else{
			updateOverStatus(calId , index);
		}
		
	
	});
	//得到提醒时间设置select
	getRemindTimeDataContent(00,00,00);
	getAddUpdateCalFrom();//新建或者更新日程表单
});

/***
 * 更改状态
 * @para calId 日程Id
 * @para index 更改状态  0 -未完成  1-完成
 */
function updateOverStatus(calId , index){
	var url =  contextPath +  "/calendar/act/CalendarAct/updateOverStatusAct.act";
	var para = {sid:calId,overStatus:index};
	var returnData;
	$.ajax({
		url:url,
		dataType:"json",
		data:para,
		async:false,
		success:function(data){
			if(data){
				top.layer.msg("更新完成");
				returndata=data;
			}else{
				alert("无数据！");
				}
			}
	});
	if(returndata){
		$("#status_menu").css("display","none");
		calendar.fullCalendar('removeEvents' ,calId);//删除节点
		calendar.fullCalendar('renderEvent', returndata); //在添加
		if(index=="1"){
    		$("#status").text("已完成");
    	}else{
    		$("#status").text("未完成");
    	}
		return returndata;
	}else{
		alert("状态更改失败！");
	}
}
function showDiary(calId){
	 var url = contextPath + "/calendar/detail.jsp?id=" + calId+"&type=1";
     $("#modal-body").html("<iframe id=\"modaliframe\"  name=\"modaliframe\" frameborder=\"0\"></iframe>");
     $("#modaliframe").attr("src",url);
     $("#div-modal-dialog").width(455);
     $("#modaliframe").width(450);
     $("#modaliframe").height(320);
     $('#myModals').modal({backdrop: 'static', keyboard: false});
     $('#myModals').modal('show');
     $("#savedata").hide();
}
function goQuery(){
	window.location = contextPath + "/calendar/query/index.jsp";
}
function goForMe(){
	window.location = contextPath + "/calendar/forme/index.jsp";
}
function goPeriodicity(){
	window.location = contextPath + "/calendar/periodicity/index.jsp";
}
function goWeekManage(){
	window.location = contextPath + "/calendar/index.jsp";
} 