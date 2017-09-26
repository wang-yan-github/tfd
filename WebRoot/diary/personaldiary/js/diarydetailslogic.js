var id;
var accountId;
var lookStaff="";
var downdiaryId="";
var onediaryId="";
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

$(function(){
	var url=contextPath+"/diary/act/DiaryAct/getIdDiaryAct.act?diaryId="+diaryId; 
	detaildoinit(url);
	getMessagePriv("diary");
});
function timecheck(titletime){
	var fitval=getdiaryFit();
						if(fitval!=null){
							var startTime=fitval.startTime;
							var endTime=fitval.endTime;
							var title=titletime;
							var commStatus=fitval.commStatus;
							var lockDay=fitval.lockDay;
							var mydate = new Date().format("yyyy-MM-dd"); 
							if((Date.parse(startTime)<=Date.parse(title))&&(Date.parse(endTime)>=Date.parse(title)||endTime=="")){
								if(commStatus==1){
									$("#diaryComments").show();
								}
							}else{
								if(Date.parse(title)>=(Date.parse(mydate)-lockDay*24*60*60*1000)||lockDay==0){
									$("#diaryComments").show();
								}else{
									if(commStatus==1){
									$("#diaryComments").show();
								}
								}
							}
						}else{
							$("#diaryComments").show();
						}
}
	function detaildoinit(url){		
	$.ajax({
		   url:url,
	   	   dataType:"json",
	   	   async:false,
	   	   success:function(data){
	   	   	if(!jQuery.isEmptyObject(data)){
	   	   		
	   	   						var diarymold;
									if (data.diaryMold == 0) {
										diarymold = '(工作日志)';
									} else {
										diarymold = '(个人日志)';
									}
						id=data.ID;
						diaryId=data.diaryId;
						accountId=data.accountId;
						if(data.lookStaff!=null){
						lookStaff=data.lookStaff;
						}
						if(data.lookStaffName!=""){
							$("#staffName").html(data.lookStaffName+"已浏览");
						}
						var attachId=data.attachId;
						readAttachDiv(attachId,"attach");
						$('#titleName').html("<a href=javascript:showPersonal('"+data.accountId+"')>"+data.userName+"</a>&nbsp;"+diarymold);
						$('#diaryDatetime').html(data.diaryDatetime);
						$('#diaryname').html(data.diaryName);
						$('#diarycontent').html(data.diaryContent);
						timecheck(data.diaryTitletime);
						judgedown();
						judgetone();
						lookcomments(diaryId,accountId);
						addlookStaff();
					}
					}
		});
	}
	function addlookStaff(){
		if(beforestaff!=accountId){
		var status=true;
		if(lookStaff!=""){
		var lookStaffs=lookStaff.split(",");
		for (var i=0; i <lookStaffs.length;i++) {
		  if(lookStaffs[i]==beforestaff){
		  	status=false;
		  }
		};
		}		
		if(status||lookStaff==""){
			var Bstaff="";
			if(lookStaff==""){
				Bstaff=beforestaff;
			}else{
			Bstaff=lookStaff+","+beforestaff;	
			}
			$.ajax({
			url :contextPath+ "/diary/act/DiaryAct/addlookStaffAct.act",
			dataType : "json",
			data:{lookStaff:Bstaff,diaryId:diaryId},
			async : false,
			success:function(data){
			}
		});
		}
		}
	}
function judgedown(){
	$.ajax({
		url :contextPath+ "/diary/act/DiaryAct/judgedowndiaryAct.act",
		dataType : "json",
		data:{id:id,accountId:accountId},
		async : false,
		success:function(data){
			if($.isEmptyObject(data)){
				$("#downdiary").hide();
			}else{
				downdiaryId=data.diaryId;				
			}
		}
	});
}
	function downdiary() {
			$("#tonediary").show();
				var url=contextPath+"/diary/act/DiaryAct/getIdDiaryAct.act?diaryId="+downdiaryId; 
				$.ajax({
							url : url,
							dataType : "json",
							async : false,
							success : function(data) {
									if(!jQuery.isEmptyObject(data)){
									var diarymold;
									if (data.diaryMold == 0) {
										diarymold = '(工作日志)';
									} else {
										diarymold = '(个人日志)';
									}
									id=data.ID;
									diaryId=data.diaryId;
									accountId=data.accountId;
									$('#titleName').html("<a href=javascript:showPersonal('"+data.accountId+"')>"+data.userName+"</a>&nbsp;"+diarymold);
									$('#diaryDatetime').html(data.diaryDatetime);
									$('#diaryname').html(data.diaryName);
									$('#diarycontent').html(data.diaryContent);	
									var attachId=data.attachId;
									readAttachDiv(attachId,"attach");
									if(data.lookStaff!=null){
									lookStaff=data.lookStaff;
									}else{
										lookStaff="";
									}
									if(data.lookStaffName!=""){
										$("#staffName").html(data.lookStaffName+"已浏览");
									}else{
										$("#staffName").html("暂无浏览人员");
									}
									timecheck(data.diaryTitletime);
									judgedown();
									judgetone();
									lookcomments(diaryId,accountId);
									addlookStaff();
							}
							}
						});
	}
	function judgetone(){
		$.ajax({
			url :contextPath+ "/diary/act/DiaryAct/judgetonediaryAct.act",
			dataType : "json",
			data:{id:id,accountId:accountId},
			async : false,
			success:function(data){
				if($.isEmptyObject(data)){
					$("#tonediary").hide();
				}else{
					onediaryId=data.diaryId;
				}
			}
		});
	}
	function tonediary() {
			$("#downdiary").show();
				var url=contextPath+"/diary/act/DiaryAct/getIdDiaryAct.act?diaryId="+onediaryId; 
				$.ajax({
							url : url,
							dataType : "json",
							async : false,
							success : function(data) {
									if(!jQuery.isEmptyObject(data)){
								var diarymold;
								if (data.diaryMold == 0) {
									diarymold = '(工作日志)';
								} else {
									diarymold = '(个人日志)';
								}
								id=data.ID;
								diaryId=data.diaryId;
								accountId=data.accountId;								
								$('#titleName').html("<a href=javascript:showPersonal('"+data.accountId+"')>"+data.userName+"</a>&nbsp;"+diarymold);
								$('#diaryDatetime').html(data.diaryDatetime);
								$('#diaryname').html(data.diaryName);
								$('#diarycontent').html(data.diaryContent);
								var attachId=data.attachId;
								readAttachDiv(attachId,"attach");
								if(data.lookStaff!=null){
									lookStaff=data.lookStaff;
									}else{
										lookStaff="";
									}
									if(data.lookStaffName!=""){
										$("#staffName").html(data.lookStaffName+"已浏览");
									}else{
										$("#staffName").html("暂无浏览人员");
									}
								timecheck(data.diaryTitletime);
								judgetone();
								judgedown();
								lookcomments(diaryId,accountId);
								addlookStaff();
							}
							}
						});
	}
	function commsubmit(){
	var url=contextPath+"/diary/act/DiaryCommentsAct/addCommentsAct.act";
	$.ajax({
		url : url,
		dataType : "text",
		data:{
			commContect:$("#commContect").val(),
			diaryId:diaryId,
			commPid:$("#commPid").val(),
			accountId:accountId,
			smsReminds:getsmsRemind()
		},
		async : false,
		success : function(data) {
				if(data!=0){
					clearstaff();
					$("#commContect").val("");
					lookcomments(diaryId,accountId);
				}
		}
	});
}
function delcomments(commId){
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
					lookcomments(diaryId,accountId);
				}
		}
	});
}
function lookcomments(diaryId,accountId){
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
			for(var i=0;i<data.length;i++){
				if(data[i].commPid==""){
					main+="<div id=\""+data[i].commId+"\" style=\"line-height:25px;\"><div style=\"width:100%;\"><div style=\"float:left;color:#428BCA;\">"+data[i].userName+
					"</div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[i].commTime+"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[i].commId+"\",\""+data[i].userName+"\");'>回复</a>";
				
				if(accountId==beforestaff){
					main+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[i].commId+"\");'>删除</a>";
				}else{
					if(beforestaff==data[i].accountId){
						main+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[i].commId+"\");'>删除</a>";
					}
				}
					main+="</div></div><br><div>"+data[i].commContect+"</div></div>";
				}
			}
			$("#commtable").html(main);
			for(var i=0;i<data.length;i++){
				for(var j=0;j<data.length;j++){
					var levelcomm="";
					if(data[i].commId==data[j].commPid){
					levelcomm+="<div id=\""+data[j].commId+"\" style=\"margin-left:6%;line-height:25px;\"><div style=\"width:100%;\"><div style=\"float:left;color:#428BCA;\">"+data[j].userName+"&nbsp;<span style=\"color:#000;\">回复</span>&nbsp;"+data[i].userName+
						"</div><div style=\"float:right\"><sapn style=\"color:#B4B4B4\">"+data[i].commTime+"</sapn>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='choicestaff(\""+data[j].commId+"\",\""+data[j].userName+"\");'>回复</a>";
					if(accountId==beforestaff){
						levelcomm+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[i].commId+"\");'>删除</a>";
						}else{
						if(beforestaff==data[j].accountId){
						levelcomm+="&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='delcomments(\""+data[j].commId+"\");'>删除</a>";
						}
						}
						levelcomm+="</div></div><br><div>"+data[j].commContect+"</div></div>";
					}
					$("#"+data[i].commId).after(levelcomm);
				}
			}
		}
	});
}
function choicestaff(id,userName){
	$("#commPid").val(id);
	$("#staff").html(userName);
	$("#staffdiv").show();
}
function clearstaff(){
	$("#commPid").val("");
	$("#staffdiv").hide();
}