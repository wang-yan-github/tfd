var page=1;
var rows=10;
var sort='DIARY_DATETIME';
var order='desc';
var i=0;
var titletime;
function getdiaryFit(){
	var returndata=null;
	var url=contextPath+"/diary/act/DiaryFitAct/lookFitAct.act";
	$.ajax({
		   url:url,
		   type:'POST',
	   	   dataType:"json",
	   		async:false,
	   		success:function(data){
	   			if(!jQuery.isEmptyObject(data)){
	   			returndata=data;
	   		}
	   		}
	   });
	   return returndata;
}
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
			if(parm.length!=0){
				var divcon="";
				var fitval=getdiaryFit();
			for(var i=0;i<parm.length;i++){
				var lookstaffName="";
				if(parm[i].LOOK_STAFF!=null&&parm[i].LOOK_STAFF!=""){
				 lookstaffName=getUserName(parm[i].LOOK_STAFF)+"&nbsp;已浏览";
				}else{
					lookstaffName="暂无人员浏览";
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
				+"<span class=\"feed-type\">工作日志</span>\n"                                   
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
				if(beforestaff==parm[i].ACCOUNT_ID){
							
						if(fitval!=null){
							var startTime=fitval.startTime;
							var endTime=fitval.endTime;
							var title=parm[i].DIARY_TITLETIME;
							var commStatus=fitval.commStatus;
							var lockDay=fitval.lockDay;
							var mydate = new Date().format("yyyy-MM-dd"); 
							if((Date.parse(startTime)<=Date.parse(title))&&(Date.parse(endTime)>=Date.parse(title)||endTime=="")){
								if(commStatus==1){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								}
							}else{
								if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)||lockDay==0){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;"+
									"<a href=\"javascript:void(0);\" onclick=\"selectdiaryId('"+parm[i].DIARY_ID+"');\">编辑</a>&nbsp;&nbsp;&nbsp;"+
									"<a href=\"javascript:void(0);\" onclick=\"deleteDiary('"+parm[i].DIARY_ID+"');\">删除</a>&nbsp;&nbsp;&nbsp;";
								}else{
									if(commStatus==1){
									divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
								}
								}
							}
						}else{
							divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;"+
						"<a href=\"javascript:void(0);\" onclick=\"selectdiaryId('"+parm[i].DIARY_ID+"');\">编辑</a>&nbsp;&nbsp;&nbsp;"+
						"<a href=\"javascript:void(0);\" onclick=\"deleteDiary('"+parm[i].DIARY_ID+"');\">删除</a>&nbsp;&nbsp;&nbsp;";
						}	
						}else{
							var fitval=getdiaryFit();
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
								if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)){
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
						}
						divcon+="<a href=\"javascript:void(0);\" onclick=\"lookstaff('"+parm[i].DIARY_ID+"');\">浏览</a></div></br>";				
				divcon+="<div class=\"lookstaff\" id=\"lookstaff"+parm[i].DIARY_ID+"\">"+lookstaffName
						+"</div>"+
						 "<form id=\"comments"+parm[i].DIARY_ID+"\"> <div id=\"comm"+parm[i].DIARY_ID+"\" class=\"commtable\">"+
						 "<input id=\"commPid"+parm[i].DIARY_ID+"\" name=\"commPid\" value=\"0\" type=\"hidden\"></input>"+
						"<input name=\"diaryId\" value='"+parm[i].DIARY_ID+"' type=\"hidden\">"
						+"<div id=\"staffdiv"+parm[i].DIARY_ID+"\" style=\"display: none;\">"
						+"<div class=\"presentstaff form-control\">回复：<span id=\"staff"+parm[i].DIARY_ID+"\">"
						+"</span>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\"  onclick=\"clearstaff('"+parm[i].DIARY_ID+"');\">X</a>"
						+"</div></div>"+
						 "<textarea rows=\"4\" id=\"commContect"+parm[i].DIARY_ID+"\" name=\"commContect\" class=\"form-control\"></textarea></form>"+
						 "<div class=\"remind\" id='remind"+parm[i].DIARY_ID+"'></div><div id=\"btnclick\">"
						 +"<button type=\"button\" class=\"btn btn-primary\" onclick=\"commentsubmit('"+parm[i].DIARY_ID+"');\">评论</button>"
						 +"</div><br><br>"
						 +"<div id=\"commtable"+parm[i].DIARY_ID+"\" class=\"diarycomments\"></div>"+
						"</div></br>";
				divcon+="</div>\n"
				+"</div>\n"
				+"</div>\n"
				+"</div>\n";
				
			}
			$('#content').append(divcon); 
			$("#cue").hide();
			$("#main").show();
			}
			}else{
				$("#right").hide();
				$("#main").hide();
				$("#cuecontent").text("无日志内容！");
			}
   		}
	});
}
function lookstaff(diaryId){
	if($("#lookstaff"+diaryId).css('display')=="none"){
		$("#lookstaff"+diaryId).show();
		$("#comm"+diaryId).hide();
	}else{
		$("#lookstaff"+diaryId).hide();
	}
}


function commentsubmit(diaryId,accountId){
	var url=contextPath+"/diary/act/DiaryCommentsAct/addCommentsAct.act";
	var parm=$("#comments"+diaryId).serialize();
	parm+="&accountId="+accountId;
	parm+="&smsReminds="+getsmsRemind();
	$.ajax({
		url : url,
		type:'POST',
		dataType : "text",
		data:parm,
		async : false,
		success : function(data) {
				if(data!=0){
					clearstaff(diaryId);
					$("#commContect"+diaryId).val("");
					updatecomm(diaryId);
					updatecommcount(diaryId);
				}
		}
	});
}
function updatecommcount(diaryId){
	var url=contextPath+"/diary/act/DiaryCommentsAct/getIdcountAct.act";
	$.ajax({
		url : url,
		type:'POST',
		dataType : "json",
		data:{diaryId:diaryId},
		async : false,
		success : function(data) {
			if(data!=null){
				$("#Comment"+diaryId).text("("+data.commCount+")");
			}
		}
	});
}
function choicestaff(commId,userName,diaryId){
	$("#commPid"+diaryId).val(commId);
	$("#staff"+diaryId).html(userName);
	$("#staffdiv"+diaryId).show();
}
function clearstaff(diaryId){
	$("#commPid"+diaryId).val("0");
	$("#staffdiv"+diaryId).hide();
}
function updatecomm(diaryId){
	$("#commtable"+diaryId).html("");
	commentlook(diaryId);
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
			if(data!=""){
			var main="";
			for(var i=0;i<data.length;i++){
				if(data[i].commPid=="0"){
					main+="<div id=\""+data[i].commId+"\" style=\"line-height:25px;\"><div style=\"width:100%;\"><div style=\"float:left;color:#428BCA;\"><a href=javascript:showPersonal('"+data[i].accountId+"')>"+data[i].userName+
					"</div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[i].commTime+
					"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[i].commId+"\",\""+data[i].userName+"\",\""+data[i].diaryId+"\");'>回复</a>";
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
						levelcomm="<div id=\""+data[j].commId+"\" style=\"margin-left:6%;line-height:25px;\"><div style=\"width:100%;\">"+
						"<div style=\"float:left;color:#428BCA;\"><a href=javascript:showPersonal('"+data[j].accountId+"')>"+data[j].userName+
						"&nbsp;<span style=\"color:#000;\">回复</span>&nbsp;<a href=javascript:showPersonal('"+data[i].accountId+"')>"+data[i].userName+
						"</div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[j].commTime+"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[j].commId+"\",\""+data[j].userName+"\",\""+data[j].diaryId+"\");'>回复</a>";

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
//删除日志
function deleteDiary(id){
	var url=contextPath + "/diary/act/DiaryAct/deleteDiaryIDAct.act?id="+id;
	if(window.confirm("确定删除日志吗？\n")){
		$.ajax({
			   url:url,
			   type:'post',
		   		async:false,
		   		success:function(data){
		   			if(data!=0){
		   			$("#div"+id).remove();
		   			}
		   		}
		   });
	}
}
//修改日志
function selectdiaryId(id){
	var url=contextPath+"/diary/personaldiary/updatediary.jsp?id="+id;
	 window.location=url;
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
function delcomments(commId,diaryId){
	var url=contextPath+"/diary/act/DiaryCommentsAct/delCommAct.act";
	$.ajax({
		url : url,
		dataType : "json",
		data:{
			commId:commId
		},
		async : false,
		success : function(data) {
				if(data!=0){
					updatecomm(diaryId);
				}
		}
	});
}

function searchtimediary(url,parm){
	$.ajax({
		url:url,
		type:'post',
		data:parm,
		dataType:'json',
		async:false,
		success:function(data){
			var parm=data.rows;
			if(parm!=""){
			if(parm.length!=0){
				var divcon="";
				var fitval=getdiaryFit();
				for(var i=0;i<parm.length;i++){
					var lookstaffName="";
					if(parm[i].LOOK_STAFF!=null&&parm[i].LOOK_STAFF!=""){
					 lookstaffName=getUserName(parm[i].LOOK_STAFF)+"&nbsp;已浏览";
					}else{
						lookstaffName="暂无人员浏览";
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
					+"<span class=\"feed-type\">工作日志</span>\n"                                   
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
					if(beforestaff==parm[i].ACCOUNT_ID){
								
							if(fitval!=null){
								var startTime=fitval.startTime;
								var endTime=fitval.endTime;
								var title=parm[i].DIARY_TITLETIME;
								var commStatus=fitval.commStatus;
								var lockDay=fitval.lockDay;
								var mydate = new Date().format("yyyy-MM-dd"); 
								if((Date.parse(startTime)<=Date.parse(title))&&(Date.parse(endTime)>=Date.parse(title)||endTime=="")){
									if(commStatus==1){
										divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
									}
								}else{
									if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)||lockDay==0){
										divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;"+
										"<a href=\"javascript:void(0);\" onclick=\"selectdiaryId('"+parm[i].DIARY_ID+"');\">编辑</a>&nbsp;&nbsp;&nbsp;"+
										"<a href=\"javascript:void(0);\" onclick=\"deleteDiary('"+parm[i].DIARY_ID+"');\">删除</a>&nbsp;&nbsp;&nbsp;";
									}else{
										if(commStatus==1){
										divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;";
									}
									}
								}
							}else{
								divcon+="<a href=\"javascript:void(0);\" onclick=\"gaindiaryComm('"+parm[i].DIARY_ID+"');\">评论<span id=\"Comment"+parm[i].DIARY_ID+"\">("+parm[i].COMM_COUNT+")</span></a>&nbsp;&nbsp;&nbsp;"+
							"<a href=\"javascript:void(0);\" onclick=\"selectdiaryId('"+parm[i].DIARY_ID+"');\">编辑</a>&nbsp;&nbsp;&nbsp;"+
							"<a href=\"javascript:void(0);\" onclick=\"deleteDiary('"+parm[i].DIARY_ID+"');\">删除</a>&nbsp;&nbsp;&nbsp;";
							}	
							}else{
								var fitval=getdiaryFit();
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
									if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)){
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
							}
							divcon+="<a href=\"javascript:void(0);\" onclick=\"lookstaff('"+parm[i].DIARY_ID+"');\">浏览</a></div></br>";				
					divcon+="<div class=\"lookstaff\" id=\"lookstaff"+parm[i].DIARY_ID+"\">"+lookstaffName
							+"</div>"+
							 "<form id=\"comments"+parm[i].DIARY_ID+"\"> <div id=\"comm"+parm[i].DIARY_ID+"\" class=\"commtable\">"+
							 "<input id=\"commPid"+parm[i].DIARY_ID+"\" name=\"commPid\" value=\"0\" type=\"hidden\"></input>"+
							"<input name=\"diaryId\" value='"+parm[i].DIARY_ID+"' type=\"hidden\">"
							+"<div id=\"staffdiv"+parm[i].DIARY_ID+"\" style=\"display: none;\">"
							+"<div class=\"presentstaff form-control\">回复：<span id=\"staff"+parm[i].DIARY_ID+"\">"
							+"</span>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\"  onclick=\"clearstaff('"+parm[i].DIARY_ID+"');\">X</a>"
							+"</div></div>"+
							 "<textarea rows=\"4\" id=\"commContect"+parm[i].DIARY_ID+"\" name=\"commContect\" class=\"form-control\"></textarea></form>"+
							 "<div class=\"remind\" id='remind"+parm[i].DIARY_ID+"'></div><div id=\"btnclick\">"
							 +"<button type=\"button\" class=\"btn btn-primary\" onclick=\"commentsubmit('"+parm[i].DIARY_ID+"');\">评论</button>"
							 +"</div><br><br>"
							 +"<div id=\"commtable"+parm[i].DIARY_ID+"\" class=\"diarycomments\"></div>"+
							"</div></br>";
					divcon+="</div>\n"
					+"</div>\n"
					+"</div>\n"
					+"</div>\n";
					
				}
			$('#content').append(divcon); 
			}
			}
   		}
	});
}
function getCount(){
	var url=contextPath+"/diary/act/DiaryAct/getuserCountAct.act";
	$.ajax({
		   url:url,
		   type:'post',
		   data:{accountId:accountId},
	   	   dataType:"json",
	   		async:false,
	   		success:function(data){
	   			$(".username").text(userName+"(共"+data.countnum+"日志)");
	   		}
	   });
}

function init(){
	if(accountId!="null"){
	var url=contextPath + "/diary/act/DiaryAct/getaccountIdAct.act";
	var parm={page:page,rows:rows,sort:sort,order:order,accountId:accountId};
	searchalldiary(url,parm);
	getCount();
	}
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
	        		 var url=contextPath + "/diary/act/DiaryAct/getaccountIdAct.act";
	        		 var parm={page:page,rows:rows,sort:sort,order:order,accountId:accountId};
	        	}else{
	        		url=contextPath+"/diary/act/DiaryAct/gettimeuserQueryDiaryAct.act";
	        		parm={page:page,rows:rows,sort:sort,order:order,titletime:titletime,accountId:accountId};
	        	}
	        	searchtimediary(url,parm);
	        }
	    });
	 $('#diarytime').calendar({    
		    current:new Date(),
		onSelect: function(date){
			i=1;
			titletime= date.format("yyyy-MM-dd");
			$('#presenttime').html(titletime);
			$('#time').show();
			url=contextPath+"/diary/act/DiaryAct/gettimeuserQueryDiaryAct.act";
			page=1;
			parm={page:page,rows:rows,sort:sort,order:order,titletime:titletime,accountId:accountId};
			$('#content').html("");
			searchtimediary(url,parm);
		}
		});
});
function showdiary(diaryId){
	var url=contextPath+"/diary/personaldiary/diarydetails.jsp?diaryId="+diaryId;
	new SysFrame().tabs('update',{
		title: "日志详情",
		url:url
	});
}
function cleartime(){
	$('#content').html("");
	page=1;
	init();
	i=0;
	$('#time').hide();
}
function getlaud(id,num){
	var url=contextPath + "/diary/act/DiaryAct/getlaudAct.act";
	$.ajax({
		url : url,
		type:'POST',
		dataType : "text",
		data:{diaryId:id},
		async : false,
		success : function(data) {
				if(data!=0){
					$("#laud"+id).removeClass();
					$("#laud"+id).addClass("good-ok-div");	
					$("#laud"+id).attr("title","已赞");
					$("#laudNum"+id).html(parseInt(num)+1);
				}
		}
	});
}