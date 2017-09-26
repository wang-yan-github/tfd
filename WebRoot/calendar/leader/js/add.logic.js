$(function(){
	setDate();
	getRemindTimeDataContent(00,00,00);
	getMessagePriv("calendar");
	$("#START_DATE").val(weekStartDate+" "+"00:00");
	$("#END_DATE").val(weekStartDate+" "+"23:59");
	checkCal();
});
function setDate(){
	/* var remindDate3 = "<select style=\"width:35%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate3\" name=\"remindDate3\">";//周
	for(var i= 1 ;  i <= 7 ;i++){

		remindDate3 = remindDate3 + "<option value=\""+i+"\" >" + weekArray[i] +"</option>";

	}
	remindDate3 = remindDate3 + "</select>"; */
	var remindDate3 = "<input style=\"width:35%;float:left;\"  type=\"text\" id=\"remindDate3\" name=\"remindDate3\" class=\"form-control BigSelect\" onclick=\"javascript:choiceWeek();\" />";
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
	}).on('success.form.bv',function(e){
		 // e.preventDefault();
	     // // Get the form instance
	     // var $form = $(e.target);
// 
	     // // Get the BootstrapValidator instance
	     // var bv = $form.data('bootstrapValidator');
	     // addOrUpdateCal();
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