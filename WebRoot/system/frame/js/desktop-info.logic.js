$(function(){
	function hideDesktopInfo(){
		$("#desktop-info").animate({opacity:"0",right:"-300px"},200);
	}
	hideDesktopInfo();
	
	function showDesktopInfo(){
		$("#desktop-info").show().animate({opacity:"1",right:"0px"},300);
	}

	
	
	$(document).on("click.desktopinfo.hide",function(e){
		hideDesktopInfo();
	})
	.on("click","#desktop-info-toggle",function(){
		showDesktopInfo();
	})
	.on("click.desktopinfo.hide.stop","#desktop-info-toggle",function(e){
		$(".document-click-hide").hide();
		showDesktopInfo();
		e.stopPropagation();
	})
	.on("click.desktopinfo.hide.stop","#desktop-info",function(e){
		e.stopPropagation();
	})
	.on("click.to.hide","#desktop-info",function(e,confirm){
		if (confirm) {
			hideDesktopInfo();
		}
	})
	.on("click","#desktop-info .nav-close",function(){
		hideDesktopInfo();
	});
	
	
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
		var planList=[
			 {
				 date : "2015-09-26",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-09-27",
				 text : "中秋",
				 status : "休"
			 },
			 {
				 date : "2015-10-01",
				 text : "国庆",
				 status : "休"
			 },
			 {
				 date : "2015-10-02",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-10-03",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-10-04",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-10-05",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-10-06",
				 text : null,
				 status : "休"
			 },
			 {
				 date : "2015-10-07",
				 text : null,
				 status : "休"
			 }
		 ];
		return planList;
	}
	
	
	
	dayReset();
	
	$.ajax({
		url:"http://api.1-blog.com/biz/bizserver/news/list.do",
		type:"POST",
		dataType:"json",
		success:function(result){
			var newsHtml="";
			if(result.status=="000000"){
				var news=result.detail;
				for (var i = 0; i < news.length; i++) {
					newsHtml+="<div class='news'>";
					newsHtml+="	<div class='news-title'>" +
							"		<span class='news-article_url' data-article_url='"+news[i].article_url+"'>"+news[i].title+"</span>" +
							"	</div>";
					newsHtml+="	<div class='news-otherinfo'>" +
							"		<span class='news-source'>"+news[i].source+"</span>" +
							"		<span class='news-behot_time'>"+moment(news[i].behot_time).format("YYYY-MM-DD HH:mm:ss")+"</span>" +
							"	</div>";
					newsHtml+="	<div class='news-otherinfo'>" +
							"		<span class='news-digg_count'>赞"+news[i].digg_count+"</span>" +
							"		&nbsp;"+
							"		<span class='news-bury_count'>踩"+news[i].bury_count+"</span>" +
							"		&nbsp;"+
							"		<span class='news-repin_count'>收藏"+news[i].repin_count+"</span>" +
							"	</div>";
					newsHtml+="</div>";
				}
				$("#news").html(newsHtml);
			}
		}
	});
	
	$("#news").on("click",".news-article_url",function(){
		window.open($(this).attr("data-article_url"),"_blank");
	});
	
	

	function orgTreeLeafHover(treeId, treeNode) {
		$("#"+treeId).find(".tree-hover-opt-bar").hide();
		
		if (!("isUser" in treeNode)) {
			return false;
		}
		
		var $a= $("#" + treeNode.tId + "_a");
		if($a.find(".tree-hover-opt-bar").length==1){
			$a.find(".tree-hover-opt-bar").show();
		}else{
			var hoverHtml="<span class='tree-hover-opt-bar'>";
			hoverHtml+="	<span class='glyphicon glyphicon-comment tree-hover-opt' id='send-message' title='发送短消息'></span>";
			hoverHtml+="	<span class='glyphicon glyphicon-phone tree-hover-opt' id='send-sms' title='发送手机短信'></span>";
			hoverHtml+="	<span class='glyphicon glyphicon-envelope tree-hover-opt' id='send-email' title='发送邮件'></span>";
			hoverHtml+="   </span>";
			
			$a.append(hoverHtml);
		}
	}
	function orgTreeLeafHoverRemove(treeId, treeNode){
		$("#"+treeId).find(".tree-hover-opt-bar").hide();
	}
	
	$.fn.zTree.init($("#user-online-tree"),{
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPId:"0"
			},
	        key:{
	        	name:"text"
            }
		},
		async:{
			enable:true,
			url:contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeOfOnlineUser.act",
			autoParam:["id","deptLongId","deptChildCount"]
		},
		callback:{
			onNodeCreated:function(event, treeId, treeNode) {
				$("#"+treeNode.tId+"_switch").trigger("click");
			}
		},
		view:{
			addHoverDom:orgTreeLeafHover,
			removeHoverDom:orgTreeLeafHoverRemove
		}
		
	});
	
	$.fn.zTree.init($("#org-tree"),{
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPId:"0"
			},
	        key:{
	        	name:"text"
            }
		},
		async:{
			enable:true,
			url:contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeOfAllUser.act",
			autoParam:["id","deptChildCount","userCount"]
		},
		view:{
			addHoverDom:orgTreeLeafHover,
			removeHoverDom:orgTreeLeafHoverRemove
		}
	});
	$(document).on("click",".tree-hover-opt",function(){
		switch($(this).attr("id")){
			case "send-message":
				
				break;
			case "send-sms":
				break;
			case "send-email":
				break;
		
		}
	});
});