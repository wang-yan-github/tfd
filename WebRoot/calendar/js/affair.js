var remindTimeDataContent = "";

var affairTypeNames = ["每日","每周","每月","每年"];//周期性是类型
var weekNames = ["一","二","三","四","五","六","日"];//周数组
/**
 * 生成提醒时间浮动信息
 * @param HOUR
 * @param MINUTE
 * @param SECOND
 */
function getRemindTimeDataContent(HOUR , MINUTE , SECOND){
	var HOURSELECT = "<select class='BigSelect' name='remindTimeHour' id='remindTimeHour'>";//小时
	var MINUTESELECT = "<select class='BigSelect' name='remindTimeMinute' id='remindTimeMinute'>";//分钟、
	var SECONDSELECT = "<select class='BigSelect' name='remindTimeSecond' id='remindTimeSecond'>";//秒
	
	for ( var i = 0; i < 24; i++) {
		var iStr = i;
		if(i<10){
			iStr = "0"+ i;
		}
		var selected = "";
		if(HOUR == iStr){
			selected = "selected='selected'";
		}
		HOURSELECT = HOURSELECT + "<option value='" + iStr + "' " + selected + ">  " + iStr + "  </option>";
	}
	HOURSELECT = HOURSELECT + "</select>";
	
	for ( var i = 0; i < 60; i++) {
		var iStr = i;
		if(i<10){
			iStr = "0"+ i;
		}
		var selected = "";
		if(MINUTE == iStr){
			selected = "selected='selected'";
		}
		MINUTESELECT = MINUTESELECT + "<option value='" + iStr + "'  " + selected + ">  " + iStr + "  </option>";
	}
	MINUTESELECT = MINUTESELECT + "</select>";
	
	for ( var i = 0; i < 60; i++) {
		var iStr = i;
		if(i<10){
			iStr = "0"+ i;
		}
		var selected = "";
		if(SECOND == iStr){
			selected = "selected='selected'";
		}
		SECONDSELECT = SECONDSELECT + "<option value='" + iStr + "' " + selected + ">  " + iStr + "  </option>";
	}
	SECONDSELECT = SECONDSELECT + "</select>";
	remindTimeDataContent = HOURSELECT + "&nbsp;&nbsp;" + MINUTESELECT + "&nbsp;&nbsp;" + SECONDSELECT;
	
	remindTimeDataContent =  remindTimeDataContent + "&nbsp;&nbsp;<input type='button' value='确定' class='btn btn-primary' id='dd' onclick='setRemindTimeFun(this);'></input>";
/*	$("#dd").click(function(event){
		setRemindTime2();
	});*/
	return remindTimeDataContent;
}
/**
 * 设置提醒时间
 */
function setRemindTimeFun(thisObj){

	var remindTime =  $(thisObj).parent().children("[name='remindTimeHour']").val() + ":" +
					$(thisObj).parent().children("[name='remindTimeMinute']").val() + ":" +
					$(thisObj).parent().children("[name='remindTimeSecond']").val() ;
	var remindType = $("#remindType").val();
	//alert(remindType +":"+ $("#remindTime5")[0] +":"+ remindTime)
	$("#remindTime").val(remindTime);
	//$("input[data-toggle='popover']").popover('hide');// 隐藏
	//$("#remindTime").popover('toggle');
	$("#remindTime").popover('destroy');
}


var aff_type="day";
//设置重复类型
function sel_change(calTypeValue)
{
 if(aff_type!=""){
    document.getElementById(aff_type).style.display="none";
 }
 if(calTypeValue == "2"){
    aff_type="day";
    $("#dayShow").show();
    $("#forday").show();
    $("#forweek,#formon,#foryears").hide();
 }
 if(calTypeValue=="3"){
    aff_type="week";
    $("#dayShow").hide();
    $("#forweek").show();
    $("#forday,#formon,#foryears").hide();
 }
 if(calTypeValue=="4"){
    aff_type="mon";
    $("#dayShow").hide();
    $("#formon").show();
    $("#forweek,#forday,#foryears").hide();
 }
 if(calTypeValue=="5"){
    aff_type="years";
    $("#dayShow").hide();
    $("#foryears").show();
    $("#forweek,#formon,#forday").hide();
 }
 document.getElementById(aff_type).style.display="block";
}



/**
 * 弹出查询周期性事务详情
 */
function toAffairDetail(id){
	top.$.jBox("iframe:"+ contextPath + "/oa/core/base/calendar/affair/detail.jsp?id=" + id,{title:"查看周期性事务",width:500,height:400,buttons: {}});
    
}
/**
 * 查询周期性事务详情
 * @param id
 */
function affairDetail(id){
	var url =   contextPath + "/affairManage/selectById.action";
	var para =  {sid:id};
	var jsonObj = tools.requestJsonRs(url,para);
	if(jsonObj.rtState){
		
	}else{
		alert(jsonObj.rtMsg);
	}
}

/**
 * 点击提醒类型弹出
 */
function setRemindTime(obj){
	$("#remindTime").popover();
	$(obj).attr("data-content",remindTimeDataContent);
}

/**
 * 设置周期性事务 提醒时间
 */
function setRemindTimePopover(){
	aff_type = 'day';//重新设置day  --对应affair
	//处理提醒时间点击后处理样式
	$("input[data-toggle='popover']").on('shown.bs.popover', function () {//展示后执行触发时间
		  // do something…
		  $(".popover .popover-content").css({"background-color":"#fbfafb"});
		  var remindTimeValue = $("#remindTime").val();
		  if(remindTimeValue && remindTimeValue != ""){
			  var remindTimeValueArr = remindTimeValue.split(":");
			  $("#remindTimeHour").val(remindTimeValueArr[0]);
			  $("#remindTimeMinute").val(remindTimeValueArr[1]);
			  $("#remindTimeSecond").val(remindTimeValueArr[2]);
		  }
	});
}


/***
 * 删除  ById
 * @param id
 */
function deleteCalAff(id){
	var url = contextPath + "/calendar/act/CalendarAct/deleteCalAct.act";
	$.ajax({
		url:url,
		dataType:"text",
		data:{sid:id},
		async:false,
		success:function(data){
			if(data=="OK"){
				 alert('删除成功！');
				 return true;
			}else{
				 alert("删除失败！");
				}
			}
	});
	return false;
	}