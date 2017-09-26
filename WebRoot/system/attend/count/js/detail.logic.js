var publicDay = "";
var holiDay = "";

function doinit(){
	ajaxLoading();
	getPublicDayById();
	createBaseTable();
	createTable();
	ajaxLoadEnd();

}
function createBaseTable(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendTime.act";
	$.ajax({
		url:requestUrl,
		data:{userId:accountId},
		type:"post",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var month = parseInt(date.substr(5,7))-1;
			var dayNum = getMonthDays(date.substr(0,4),month);
			for(var i = 1 ; i <= dayNum ; i++ ){
				var day = i;
				if(day<10){
					day = "0"+day;
				}
				var d = date+"-"+day;
				var isRest = isPublicDay(new Date(d).getDay());
				var isHoli = isHoliday(d);
				var html = "<tr><td colspan=\"8\" style=\"text-align:left;margin-left:10px;\" ><b>"+d+"</b></td></tr>";
				$.each(data,function(index,data){
					var type = "";
					if(data.type=='1'){
						type = "上班";
					}else{
						type = "下班";
					}
					html+="<tr>";
					html+="<td>第"+(index+1)+"次登记</td>";
					html+="<td>"+type+"登记</td>";
					html+="<td>"+data.time+"</td>";
					if(isHoli){
						html += "<td style=\"color:green;\">"+holiDay+"</td>";
						html += "<td style=\"color:green;\">"+holiDay+"</td>";
					}else if(isRest){
						html += "<td style=\"color:green;\">公休日</td>";
						html += "<td style=\"color:green;\">公休日</td>";
					}else{
						html+="<td id=\""+data.attendTimeId+day+data.type+"time\" ><span  style=\"color:red;\" >未登记</span></td>";
						html+="<td id=\""+data.attendTimeId+day+data.type+"status\" ><span  style=\"color:red;\" >未登记</span></td>";
					}
					html += "<td id =\""+data.attendTimeId+day+data.type+"option\" ></td></tr>";
				});
				$("#content").append(html);
			}
		}
	});
}
function createTable(){
	var now = new Date();
	var day = now.getDate();
	if(day<10){
		day = "0"+day;
	}
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getMonthRegistByDate.act";
	$.ajax({
		url:requestUrl,
		data:{date:date,accountId:accountId},
		type:"post",
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$.each(data,function(index,data){
				var day = data.registTime.substr(8,2);
				var id = data.attendTimeId+day+data.registType;
				var status = "";
				if(data.status=='2'){
					status = "<span style=\"color:red\" >迟到</span>";
				}else if(data.status=='3'){
					status = "<span style=\"color:red\" >早退</span>";
				}else if(data.status=='1'){
					status = "<span style=\"color:green\" >正常</span>";
				}
				var address = "";
				if(data.address!=null&&data.address!="null"){
					address = data.address;
				}
				var remark = "";
				if(data.remark!=null&&data.remark!="null"){
					remark = data.remark;
				}
				$("#"+id+"time").html(data.registTime.substr(11,8));
				$("#"+id+"status").html(status);
				$("#"+id+"address").html(address);
				$("#"+id+"pictrue").html("<div id='attach"+index+"Div' name='attach"+index+"Div' ></div>");
				$("#"+id+"remark").html(remark);
				readAttachDiv(data.pictrue,"attach"+index);
			});
		}
	});
}

function getPublicDayById(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendConfig.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			publicDay = data.publicDay;
		}
	});
}



function isPublicDay(week){
	var flag = false;
	if(week==0){
		week = 7;
	}
	if(publicDay!=''&&publicDay!="null"&&publicDay!=null){
		var days = publicDay.split(",");
		for(var i = 0 ; i < days.length ; i++ ){
			if(days[i]==week){
				flag = true;
			}
		}
	}
	return flag;
}

function isHoliday(date){
	var returnData = false;
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/IsHoliday.act";
	$.ajax({
		url:requestUrl,
		data:{date:date},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data.holidayName!=""){
				holiDay = data.holidayName;
				returnData = true;
			}
		}
	});
	return returnData;
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