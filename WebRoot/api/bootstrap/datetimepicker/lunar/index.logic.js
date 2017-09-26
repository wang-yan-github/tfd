$(function() {
	
	$('#calendar').datetimepicker({
		inline : true,
		format : "YYYY-MM-DD"
	}).on("dp.update", function() {
		dayReset();
	}).on("dp.change", function() {
		dayReset();
	});
	
	function dayReset() {
		var planList = getCalendarPlan();
		$(".datepicker-days .day").each(function() {
			var dataDay = $(this).attr("data-day");
			
			var plan=null;
			for (var i = 0; i < planList.length; i++) {
				var planTemp=planList[i];
				if (planTemp.date == dataDay) {
					plan=planTemp;
					break;
				}
			}
			
			var date = new Date(dataDay.replace(/-/g, '/'));
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			
			var lunar = new LunarCalendarUtil().calendar.solar2lunar(year, month, day);

			var dayText=plan!=null&&plan.text!=null?plan.text:lunar.IDayCn;
			var planText=plan!=null&&plan.text!=null?"p"+plan.text:"";
			var planStatus=plan!=null&&plan.status!=null?"p"+plan.status:"";
			
			var dateHtml = "<div data-plan-status='"+planStatus+"' class='day-container"+ (plan == null ? "" : " day-plan") + "'>";
			dateHtml += "<div class='day-solar'>" + $(this).html()+ "</div>";
			dateHtml += "<div class='day-lunar' data-plan-text='"+planText+"'>" +dayText+ "</div>";
			if (plan!=null) {
				dateHtml += "<div class='day-plan-status'>"+ plan.status + "</div>";
			}
			dateHtml += "</div>";
			
			$(this).html(dateHtml);

		});
	}
	
	function getCalendarPlan() {
		return [ {
			date : "2015-09-25",
			text : "中秋",
			status : "休"
		}, {
			date : "2015-09-26",
			text : null,
			status : "休"
		}, {
			date : "2015-09-27",
			text : null,
			status : "休"
		}, {
			date : "2015-09-28",
			text : null,
			status : "班"
		} ];
	}
	
	
	
	dayReset();

});