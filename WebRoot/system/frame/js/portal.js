// JavaScript Document

//获取未读的新闻最新的三条
function  getNoreadnews(callback){
	var url=contextPath+"/news/act/NewsAct/getNoreadNewsAct.act";
	var returnData = "";
	jQuery.ajax({
			url:url,
			dataType:"json",
			async:true,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				var newsHtml="";
				jQuery.each(data,function(index,data){
					newsHtml+="<LI><A href=\"javascript:goNews('"+data.newsId+"')\">"+
					"<FONT color=\"\">【"+data.newstype+"】"+data.title+"</FONT></A>&nbsp;<span class=\"item-time\" style=\"float:right;\">"+data.createTime+"</sapn></LI>";
				});
				returnData = newsHtml;
				callback(returnData);
			}
	});
}
function getnotice(callback){
	var returnData = "";
	var url=contextPath+"/notice/act/NoticeAct/getreadNoticeAct.act";
	jQuery.ajax({
			url:url,
			dataType:"json",
			async:true,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				var noticeHtml ="";
				jQuery.each(data,function(index,data){
					noticeHtml+="<LI><A href=\"javascript:gonotice('"+data.noticeId+"')\">"+
					"<FONT color=\"\">【"+data.noticeType+"】"+data.noticeTitle+"</FONT></A>&nbsp;<span class=\"item-time\" style=\"float:right;\">"+data.createTime+"</sapn></LI>";
					
				});
				returnData = noticeHtml;
				callback(returnData);
			}
	});
}
function getNoReadEmail(callback){
	getNoReadNum();
	var returnData = "";
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getNoReadEmail.act';
	jQuery.ajax({
			url:requestUrl,
			dataType:"json",
			async:true,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				var emailHtml="";
				jQuery.each(data,function(index,data){
					var subject = "";
					if(data.subject.length > 15){
						subject = data.subject.substr(0,15);
					}else{
						subject = data.subject;
					}
					emailHtml+="<LI>"+data.fromName+" &nbsp;"+
						"<A title=\"\" href=\"javascript:goEmail('"+data.emailId+"')\">"+subject+"</A>"+
						"<span class=\"item-time\" style=\"float:right;\">"+data.sendTime+"</sapn></LI>";
					
					
				});
				returnData = emailHtml;
				callback(returnData);
			}
	});
}
function getNoReadNum()
{
    var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getEmailInCount.act';
    jQuery.ajax({
            url:requestUrl,
            dataType:"json",
            data:{},
            async:true,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                jQuery("#noReadNum").html("未读："+data.noReadNum);
                var percent = "";
                if(parseInt(data.InCount)==0){
                	percent = "0";
                }else{
                	percent = parseInt((parseInt(data.noReadNum)/parseInt(data.InCount))*100);
                }
                
                //var emailP = document.getElementById('emailPercent');
                jQuery('#emailPercent').prop("data-percent",percent);
                //emailP.dataset.percent = percent;
                jQuery("#emailPercent span").html(percent+"%");
            }
    });
}
function goEmail(emailId){
	top.goUrl("电子邮件",contextPath+"/system/email/index.jsp?emailId="+emailId);
}
function goNews(newsId){
	top.goUrl("查看新闻",contextPath+"/news/read/readnews.jsp?newsId="+newsId+"&status="+1);
}
function gonotice(noticeId){
	top.goUrl("查看公告",contextPath+"/notice/readnotice/readnotice.jsp?noticeId="+noticeId+"&status="+1);
}
function getCalendarList(callback){
	var returnData = "";
	var requestUrl=contextPath+'/calendar/act/CalendarAct/getTodayCalendar.act';
	jQuery.ajax({
		url:requestUrl,
		dataType:"json",
		async:true,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var calendarHtml="";
			jQuery.each(data,function(index,data){
				var content = "";
				if(data.content.length > 15){
					content = data.content.substr(0,15);
				}else{
					content = data.content;
				}
				calendarHtml+="<LI><A href=\"javascript:goCalendar()\">"+
				"<FONT color=\"\">"+content+"</FONT></A><span class=\"item-time\" style=\"float:right;\">"+data.startTime+"</span>&nbsp;</LI>";
			});
			returnData = calendarHtml;
			callback(returnData);
		}
	});
}

function goCalendar(){
	top.goUrl("个人日程",contextPath+"/calendar/index.jsp");
}

function getFristWorkFlow(){
    var requestUrl=contextPath+'/tfd/system/workflow/worklist/act/WorkListAct/getFristWorkListAct.act';
    var html="";
    jQuery.ajax({
            url:requestUrl,
            dataType:"json",
            async:true,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                for(var i=0;data.length>i;i++)
                    {
                      html+="<li class=\"order-item\">\n"+
                                            "<div class=\"row\" style=\"cursor: pointer;\" onclick=\"dowork('"+data[i].RUN_ID+"','"+data[i].URL+"');\">\n"+
                                                "<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-6 item-left\">\n"+
                                                    "<div class=\"item-booker\">"+data[i].TITLE+"</div>\n"+
                                                    "<div class=\"item-time\">\n"+
                                                        "<i class=\"fa fa-calendar\"></i>\n"+
                                                        "<span>"+data[i].CREATE_TIME+"</span>\n"+
                                                    "</div>\n"+
                                                "</div>\n"+
                                                "<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-6 item-right\">\n"+
                                                    "<div class=\"item-price\">\n"+
                                                        "<span class=\"currency\">步骤：</span>\n"+
                                                        "<span class=\"price\">"+data[i].PRCS_NAME+"</span>\n"+
                                                    "</div>\n"+
                                                "</div>\n"+
                                            "</div>\n"+
                                            "<a class=\"item-more\" style=\"cursor: pointer;\" onclick=\"dowork('"+data[i].RUN_ID+"','"+data[i].URL+"');\">\n"+
                                                "<i></i>\n"+
                                            "</a>\n"+
                                        "</li>\n";
                    }
                    jQuery("#flowList").html(html);
                }
    });
}
function init()
{
       var requestUrl=contextPath+'/tfd/system/workflow/worklist/act/WorkListAct/getFlowWorkCountAct.act';
    jQuery.ajax({
            url:requestUrl,
            dataType:"json",
            data:{},
            async:true,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                jQuery("#flowcount").html("待办："+data.zs);
            }
    });
}
function dowork(runId,url)
    {
        var urls=contextPath+url;
        top.goUrl("<div  style=\"display:none;\" id="+runId+"></div >办理工作流",urls);
    }
    
function goworklist()
{
    var urls=contextPath+"/system/workflow/worklist/index.jsp";
    top.goUrl("<div  style=\"display:none;\"></div >待办流程",urls);
}
