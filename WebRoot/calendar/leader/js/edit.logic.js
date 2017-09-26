
function doinit()
{
	setDate();
	getRemindTimeDataContent(00,00,00);
	getMessagePriv("calendar");
    var calendarObj = getCalByIds(calId);//获取日程
    if(calendarObj){
    	$("#CAL_CONTENT").val(calendarObj.title);
    	$("#Up_calId").val(calendarObj.id);
    	$("#Up_accountId").val(calendarObj.accountId);
        $("#START_DATE").val(calendarObj.startTimeStr);
        $("#END_DATE").val(calendarObj.endTimeStr);
  	  	$("#calLevel").val(calendarObj.calLevel);
  	  if(calendarObj.calLevel && calendarObj.calLevel != 0){
  		$("#cal_color").attr("class","color" + calendarObj.calLevel);
  	  }
  	  if(calendarObj.isWeekend == 1){
  		  $("#isWeekend")[0].checked = true;
  	  }
  	  // if(calendarObj.isWeekend == 1){
  		  // $("#smsRemind")[0].checked = true;
  	  // }
  	  $("#calType").val(calendarObj.calType);//工作类型
  	  $("#accountId").val(calendarObj.userId);//参与者
  	  var userName = calendarObj.userName;
  	  var name = "";
  	  $.each(userName,function(index,userName){
  	  	name += userName.name+",";
  	  });
  	  $("#userName").val(name);
  	  $("#affairId").val(calendarObj.affairId);
  	  if(calendarObj.calAffType == '1'){//周期性事务
  		  $("#calAffType")[0].checked = true;
  		  $("#remindType").val(calendarObj.remindType);
  		  setCalAffType($("#calAffType")[0]);
  		  /*if(calendarObj.remindType == 3 || calendarObj.remindType == 4 || calendarObj.remindType == 5){//周、月、年
				  $("#remindTime" + calendarObj.remindType).val(calendarObj.remindTimeStr);
			  }else{*/
			  //}
			 	sel_change(calendarObj.remindType);
			    $("#remindTime").val(calendarObj.remindTime);
			    $("#freDay").val(calendarObj.frequency);
			  if(calendarObj.remindType == 3){
				  $("#remindDate3").val(calendarObj.remindDate);
				  $("#freWeek").val(calendarObj.frequency);
				  $("#freDay").val(1);
			  }else if(calendarObj.remindType == 4){
				  $("#remindDate4" ).val(calendarObj.remindDate);
				  $("#freMon").val(calendarObj.frequency);
				  $("#freDay").val(1);
			  }else if(calendarObj.remindType == 5){
				  var remindDateArray = calendarObj.remindDate.split("-");
				  $("#remindDate5Mon").val(remindDateArray[0]);
				  $("#remindDate5Day").val(remindDateArray[1]);
				  $("#freYear").val(calendarObj.frequency);
				  $("#freDay").val(1);
			  }
			  $("#endWhile").val(calendarObj.endWhile);//结束重复时间
  	  }
  	  if(calendarObj.beforeTime!=null){
  	  	 $("#remindmins").val(calendarObj.beforeTime);
  	  }
  	  setSms(calendarObj.isSms);
    }
    checkCal();
    //$("#sid").val(cal_id);
}
function setDate(){
	/* var remindDate3 = "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate3\" name=\"remindDate3\">";//周
	for(var i= 1 ;  i <= 7 ;i++){

		remindDate3 = remindDate3 + "<option value=\""+i+"\" >" + weekArray[i] +"</option>";

	}
	remindDate3 = remindDate3 + "</select>"; */
	var remindDate3 = "<input style=\"width:35%;float:left;\" disabled=\"disabled\"  type=\"text\" id=\"remindDate3\" name=\"remindDate3\" class=\"form-control BigSelect\" onclick=\"javascript:choiceWeek();\" />";
	$("#week").html(remindDate3);
	var dayStr = "";//日
	for(var i= 1 ;  i < 32 ;i++){

		dayStr = dayStr + "<option value=\""+ i +"\"  >" + i +"日</option>";

	}
	//$("#remindDate4").html(dayStr);
	var monthStr = "";//月
	for(var i= 1 ;  i < 13 ;i++){

		monthStr = monthStr + "<option value=\""+ i +"\" >" + i +"月</option>";

	}
	$("#remindDate5Day").html(dayStr);
	$("#remindDate5Mon").html(monthStr);
	$("#endWhile").val(formatDate(new Date()));
}
function checkCal(){
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
		fields: {
			content: {
	            validators: {
	            	container: 'popover',
	                notEmpty: {
	                    message: '事务内容不能为空'
	                }
	            }
	       },
	        beforeDay: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        },
	        beforeHour: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        },
	        beforeMinute: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        }
		}
	});
}
function choiceWeek(){
	var week = "";//周
	 for(var i= 1 ;  i <= 7 ;i++){
	 	if($("#remindDate3").val().indexOf(weekArray[i])>-1){
	 		week = week + "<div class=\"checked checkeds\"  >" + weekArray[i] +"</div>";
	 	}else{
	 		week = week + "<div class=\"checked\"  >" + weekArray[i] +"</div>";	
	 	}
		 
	 }
	 $("#week-div-modal-dialog").width(180);
	 $("#week-div-modal-dialog").height(400);
	 $("#weekModalLabel").html("选择星期");
	 $("#week-modal-body").html(week);
	 $('#weekModal').modal({backdrop: 'static', keyboard: false});
	 $('#weekModal').modal('show');
	 docheck();
}
function choiceMonth(){
	var month = "";//周
	 for(var i= 1 ;  i <= 31 ;i++){
		 	if($("#remindDate4").val().indexOf(i+"日")>-1){
		 		month = month + "<div class=\"checked checkeds\"  >" + i +"日</div>";
	 	}else{
	 		month = month + "<div class=\"checked\"  >" + i +"日</div>";	
	 	}
		 
	 }
	 $("#week-div-modal-dialog").width(180);
	 //$("#week-div-modal-dialog").height(400);
	 $("#weekModalLabel").html("选择日期");
	 $("#week-modal-body").html(month);
	 $("#week-modal-body").height(210);
	 $("#week-modal-body").css("overflow","auto");
	 $('#weekModal').modal({backdrop: 'static', keyboard: false});
	 $('#weekModal').modal('show');
	 docheck();
}
function docheck(){
	$(".checked").click(function(){
		if($(this).hasClass("checkeds")){
			$(this).removeClass("checkeds");
		}else{
			$(this).addClass("checkeds");
		}
	});
}

function getCheck(){
	var returnData = "";
	var checks = $(".checkeds");
	for(var i = 0 ; i < checks.size(); i++){
		returnData += checks.eq(i).html()+",";
	}
	returnData = returnData.substr(0,returnData.length-1);
	if(returnData.indexOf("星期")>-1){
		$("#remindDate3").val(returnData);
	}else{
		$("#remindDate4").val(returnData);
	}
	
	$('#weekModal').modal('hide');
}
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
function setSms(isSms){
	if(isSms.sitesms==1){
		$("#sitesms").prop("checked",true);
	}
	if(isSms.mobilesms==1){
		$("#mobilesms").prop("checked",true);
	}
	if(isSms.emailsms==1){
		$("#emailsms").prop("checked",true);
	}
	if(data.isSms.webemilesms==1){
		$("#webemilesms").prop("checked",true);
	}
	if(isSms.wxsms==1){
		$("#wxsms").prop("checked",true);
	}
}