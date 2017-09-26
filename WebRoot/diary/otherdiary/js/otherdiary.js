var page=1;
var rows=10;
var sort='DIARY_DATETIME';
var order='desc';
var i=0;
var titletime;

function searchalldiary(url,parm){
	$.ajax({
		url:url,
		type:'post',
		data:parm,
		dataType:'json',
		async:false,
		success:function(data){
			var parm=data.rows;			
			if(parm!=""){
			var divcon="";
			var fitval=getdiaryFit();
			var diaryType="";
			for(var i=0;i<parm.length;i++){
				var lookstaffName="";
				if(parm[i].LOOK_STAFF!=null&&parm[i].LOOK_STAFF!=""){
				 lookstaffName=getUserName(parm[i].LOOK_STAFF)+"&nbsp;已浏览";
				}else{
					lookstaffName="暂无人员浏览";
				}
				if(parm[i].DIARY_MOLD==0){
					diaryType="工作日志";
				}else{
					diaryType="个人日志";
				}
				divcon+="<div id=\"div"+parm[i].DIARY_ID+"\" class=\"feed  feed-text\">"
				
				+"<div class=\"feed-avatar\">\n"
				+"<div class=\"feed-blog-info\">\n"
				+"<a class=\"blog-avatar\" hidefocus=\"hidefocus\"  href=javascript:showPersonal('"+parm[i].ACCOUNT_ID+"')>"
				+"<img width=\"64\" height=\"64\" onerror=\"this.src='"+imgPath + "/personal/error.jpg'\" src=\""+contextPath+"/attachment/userinfo/"+parm[i].HEAD_IMG+"\">"+parm[i].USER_NAME+"</a>"
				+"</div>\n"
				+"</div>\n"
					
				+"<div class=\"feed-content-holder pop\">\n"
				+"<div class=\"ui-poptip-arrow ui-poptip-arrow-10\">\n"
				+"<em>◆</em>\n"
				+"<span>◆</span>\n"
				+"</div>\n"
				
				+"<div class=\"feed-container-top\"></div>\n"
				+"<div class=\"pop-content clearfix\">\n"
				+"<div class=\"feed-hd\">"                 
				+"<div title=\""+parm[i].DIARY_DATETIME+"\" class=\"feed-time\">"+parm[i].DIARY_DATETIME+"</div>\n"                 
				+"<div class=\"feed-basic\">\n"                      
				+"<a hidefocus=\"hidefocus\" class=\"feed-user\" href=javascript:showPersonal('"+parm[i].ACCOUNT_ID+"')>"
				+parm[i].USER_NAME+"</a>\n"                 
				+"<span class=\"feed-type\">"+diaryType+"</span>\n"                                   
				+"</div>"              
				+"</div>"
				
				
				+"<div class=\"feed-hd\">\n" 
				+"<h4 class=\"feed-title\"><a style=\"color:#000000;\" href=javascript:showdiary('"+parm[i].DIARY_ID+"')>"+parm[i].DIARY_NAME+"</a></h4>\n" 
				+"<div class=\"feed-ct\">\n"
				+"<div class=\"feed-txt-full rich-content\">\n" 
				+"<div class=\"feed-txt-summary\">\n"+parm[i].DIARY_CONTENT+"</div>\n" 
				+"<div class=\"feed-txt-more\"></div>\n"
				+"</div>\n"
				+"</div>\n"
				+"<div class=\"feed-act\">";
				if(parm[i].LAUD_NUM!=0){
								divcon+="<span id=\"laudNum"+parm[i].DIARY_ID+"\" class=\"laudNum\">"+parm[i].LAUD_NUM+"&nbsp;&nbsp;</span>";
							}
							else{
								divcon+="<span id=\"laudNum"+parm[i].DIARY_ID+"\" class=\"laudNum\">&nbsp;&nbsp;</span>";
								}
								if(parm[i].LAUD==0){
							divcon+="<a class=\"good-div\" id=\"laud"+parm[i].DIARY_ID+"\" onclick=\"getlaud('"+parm[i].DIARY_ID+"','"+parm[i].LAUD_NUM+"')\" title=\"赞\"></a>";
						}else{
							divcon+="<a class=\"good-ok-div\" title=\"已赞\"></a>";
						}
				if(fitval!=null){
							var startTime=fitval.startTime;
							var endTime=fitval.endTime;
							var title=parm[i].DIARY_TITLETIME;
							var commStatus=fitval.commStatus;
							var lockDay=fitval.lockDay;
							var mydate = new Date().format("yyyy-MM-dd"); 
							if((Date.parse(startTime)<=Date.parse(title))&&(Date.parse(endTime)>=Date.parse(title)||endTime=="")){
								if(commStatus==1){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								}
							}else{
								if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)||lockDay==0){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								}else{
									if(commStatus==1){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								}
								}
							}
						}else{
							divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
						}
						
						divcon+="<a href=\"javascript:void(0);\" onclick=\"lookstaff('"+parm[i].DIARY_ID+"');\">浏览</a></div></br>";
				
				divcon+="<div class=\"lookstaff\" id=\"lookstaff"+parm[i].DIARY_ID+"\">"+lookstaffName
						+"</div>"+
						"<form id=\"comments"+parm[i].DIARY_ID+"\">"
						+" <div id=\"comm"+parm[i].DIARY_ID+"\" class=\"commtable\">"+
						"<input id=\"commPid"+parm[i].DIARY_ID+"\" name=\"commPid\" value=\"0\" type=\"hidden\"></input>"+
						"<input name=\"diaryId\" value='"+parm[i].DIARY_ID+"' type=\"hidden\">"
						+"<div id=\"staffdiv"+parm[i].DIARY_ID+"\" style=\"display: none;\">"
						+"<div class=\"presentstaff form-control\">回复："+
						"<span id=\"staff"+parm[i].DIARY_ID+"\"></span>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\"  onclick=\"clearstaff('"+parm[i].DIARY_ID+"');\">X</a>"
						+"</div></div>"+
						"<textarea rows=\"4\" id=\"commContect"+parm[i].DIARY_ID+"\" name=\"commContect\" class=\"form-control\"></textarea></form>"+
						"<div class=\"remind\" id='remind"+parm[i].DIARY_ID+"'>"
						+"</div>"
						+"<div id=\"btnclick\"><button type=\"button\" class=\"btn btn-primary\" "+
						"onclick=\"commentsubmit('"+parm[i].DIARY_ID+"','"+parm[i].ACCOUNT_ID+"');\">评论"+
						"</button>"
						+"</div><br><br>"
						+"<div id=\"commtable"+parm[i].DIARY_ID+"\" class=\"diarycomments\"></div>"
						+"</div>";
						
				divcon+="</div>\n"
				+"</div>\n"
				+"</div>\n"
				+"</div>\n";
				
				
				
				
				
				// divcon+="<div class=\"list-group-item\" id=\"div"+parm[i].DIARY_ID+"\" style=\"padding: 0px;cursor: auto;\">"+
			          // "<table  class='table table-striped table-condensed' id=\"diary"+parm[i].DIARY_ID+"\">"+
			          // "<tr><td class=\"footdiv\">"+
			          // "<div class='titleName'><a href=javascript:showdiary('"+parm[i].DIARY_ID+"')>"+parm[i].DIARY_NAME+"</a></div>"+
			          // " <div class='diaryDatetime'><a href=javascript:showPersonal('"+parm[i].ACCOUNT_ID+"')>"+parm[i].USER_NAME+"</a>&nbsp;"+parm[i].DIARY_DATETIME+"</div></h3>"+
				     // "</div>"+
			          // "</td></tr>"+
			          // "<tr><td><div id='diarycontent"+parm[i].DIARY_ID+"' class=\"diarycontent\">"+parm[i].DIARY_CONTENT+"</div></td></tr>"
						// +"<tr><td align=\"right\">";
						// divcon+="<div class=\"btndiv\">";
						// if(fitval!=null){
							// var startTime=fitval.startTime;
							// var endTime=fitval.endTime;
							// var title=parm[i].DIARY_TITLETIME;
							// var commStatus=fitval.commStatus;
							// var lockDay=fitval.lockDay;
							// var mydate = new Date().format("yyyy-MM-dd"); 
							// if((Date.parse(startTime)<=Date.parse(title))&&(Date.parse(endTime)>=Date.parse(title)||endTime=="")){
								// if(commStatus==1){
									// divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								// }
							// }else{
								// if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)||lockDay==0){
									// divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								// }else{
									// if(commStatus==1){
									// divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								// }
								// }
							// }
						// }else{
							// divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"')\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
						// }
						// divcon+="<a href=\"javascript:void(0);\" onclick=\"lookstaff('"+parm[i].DIARY_ID+"');\">浏览</a></div>";
						// if(parm[i].LAUD_NUM!=0){
								// divcon+="<span id=\"laudNum"+parm[i].DIARY_ID+"\" class=\"laudNum\">"+parm[i].LAUD_NUM+"&nbsp;&nbsp;</span>";
							// }
							// else{
								// divcon+="<span id=\"laudNum"+parm[i].DIARY_ID+"\" class=\"laudNum\">&nbsp;&nbsp;</span>";
								// }
						// if(parm[i].LAUD==0){
							// divcon+="<div class=\"good-div\" id=\"laud"+parm[i].DIARY_ID+"\" onclick=\"getlaud('"+parm[i].DIARY_ID+"','"+parm[i].LAUD_NUM+"')\" title=\"赞\"></div>";
						// }else{
							// divcon+="<div class=\"good-ok-div\" title=\"已赞\"></div>";
						// }	
						// divcon+="</td></tr>"+
						// "</table><div class=\"lookstaff\" id=\"lookstaff"+parm[i].DIARY_ID+"\">"+lookstaffName+"</div>"+
						// "<form id=\"comments"+parm[i].DIARY_ID+"\"> <div id=\"comm"+parm[i].DIARY_ID+"\" class=\"commtable\">"+
						// "<input id=\"commPid"+parm[i].DIARY_ID+"\" name=\"commPid\" value=\"0\" type=\"hidden\"></input>"+
						// "<input name=\"diaryId\" value='"+parm[i].DIARY_ID+"' type=\"hidden\"><div id=\"staffdiv"+parm[i].DIARY_ID+"\" style=\"display: none;\"><div class=\"presentstaff form-control\">回复：<span id=\"staff"+parm[i].DIARY_ID+"\"></span>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\"  onclick=\"clearstaff('"+parm[i].DIARY_ID+"');\">X</a></div></div>"+
						// "<textarea rows=\"4\" id=\"commContect"+parm[i].DIARY_ID+"\" name=\"commContect\" class=\"form-control\"></textarea></form>"+
						// "<div class=\"remind\" id='remind"+parm[i].DIARY_ID+"'></div><div id=\"btnclick\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"commentsubmit('"+parm[i].DIARY_ID+"','"+parm[i].ACCOUNT_ID+"');\">评论</button></div><br><br><div id=\"commtable"+parm[i].DIARY_ID+"\" class=\"diarycomments\"></div></div>"+
						// "</div></br>";
			}
			$('#content').append(divcon); 
   		}
   	}
	});
}
function commentlook(diaryId){
	var url=contextPath+"/diary/act/DiaryCommentsAct/lookCommentsAct.act";
	$.ajax({
		url : url,
		dataType : "json",
		data:{
			diaryId:diaryId
		},
		async : false,
		success : function(data) {
			var main="";
			if(data!=""){
			for(var i=0;i<data.length;i++){
				if(data[i].commPid=="0"){
					main+="<div id=\""+data[i].commId+"\" style=\"line-height:25px;\"><div style=\"width:100%;\"><div style=\"float:left;color:#428BCA;\"><a href=javascript:showPersonal('"+data[i].accountId+"')>"+data[i].userName+
					"</a></div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[i].commTime+"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[i].commId+"\",\""+data[i].userName+"\",\""+data[i].diaryId+"\");'>回复</a>";
					if(beforestaff==data[i].accountId){
					main+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[i].commId+"\",\""+data[i].diaryId+"\");'>删除</a>";
					}
					main+="</div></div><br><div>"+data[i].commContect+"</div></div>";
				}
			}
			$("#commtable"+diaryId).append(main);
			for(var i=0;i<data.length;i++){
				for(var j=0;j<data.length;j++){
					var levelcomm="";
					if(data[i].commId==data[j].commPid){
						levelcomm="<div id=\""+data[j].commId+"\" style=\"margin-left:6%;line-height:25px;\"><div style=\"width:100%;\"><div style=\"float:left;color:#428BCA;\"><a href=javascript:showPersonal('"+data[j].accountId+"')>"+
						data[j].userName+"</a>&nbsp;<span style=\"color:#000;\">回复</span>&nbsp;<a href=javascript:showPersonal('"+data[i].accountId+"')>"+data[i].userName+
						"</a></div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[j].commTime+"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[j].commId+"\",\""+data[j].userName+"\",\""+data[j].diaryId+"\");'>回复</a>";
						if(beforestaff==data[j].accountId){
						levelcomm+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[j].commId+"\",\""+data[j].diaryId+"\");'>删除</a>";
						}
						levelcomm+="</div></div><br><div>"+data[j].commContect+"</div></div>";
					}
					$("#"+data[i].commId).after(levelcomm);
				}
			}
		}
		}
	});
}
function gaindiaryComm(diaryId){
	if($("#comm"+diaryId).css('display')=="none"){
		$(".commtable").hide();
		$(".remind").html("");
		$("#remind"+diaryId).html("<span style=\"float: left;\">提醒方式：</span><div id=\"smsdiv\" name=\"smsdiv\" style='float: left;'></div>");
		getMessagePriv("diary");
		clearstaff(diaryId);
		$("#commContect"+diaryId).val("");
		$("#lookstaff"+diaryId).hide();
		$("#commtable"+diaryId).html("");
		commentlook(diaryId);
	$("#comm"+diaryId).show();
	}else{
		$("#comm"+diaryId).hide();
	}
}

function init(){
	var url=contextPath + "/diary/act/DiaryAct/otheruserDiaryAct.act";
	var parm={page:page,rows:rows,sort:sort,order:order};
	searchalldiary(url,parm);
	getCount();
}
$(function(){
		init();
	 $(window).scroll(function (){
	        var scrollTop = $(this).scrollTop();
	        var scrollHeight = $(document).height();
	        var windowHeight = $(this).height();
	        if (scrollTop + windowHeight == scrollHeight) {
	        	page=page+1;
	        	if(i==0){
	        		 var url=contextPath + "/diary/act/DiaryAct/otheruserDiaryAct.act";
	        		 var parm={page:page,rows:rows,sort:sort,order:order};
	        	}else{
	        		url=contextPath+"/diary/act/DiaryAct/otherusertimeQueryDiaryAct.act";
	        		parm={page:page,rows:rows,sort:sort,order:order,titletime:titletime};
	        	}
	        	searchalldiary(url,parm);
	        }
	    });
	 $('#diarytime').calendar({    
		    current:new Date(),
			onSelect: function(date){
			i=1;
			titletime= date.format("yyyy-MM-dd");
			$('#presenttime').html(titletime);
			$('#time').show();
			url=contextPath+"/diary/act/DiaryAct/otherusertimeQueryDiaryAct.act";
			page=1;
			parm={page:page,rows:rows,sort:sort,order:order,titletime:titletime};
			$('#content').html("");
			searchalldiary(url,parm);
		}
		});
});