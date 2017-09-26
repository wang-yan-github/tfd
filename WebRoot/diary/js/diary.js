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

function cleartime(){
	$('#content').html("");
	page=1;
	init();
	i=0;
	$('#time').hide();
}
function Newdiary(){
	var url=contextPath+"/diary/personaldiary/Newdiary.jsp";
	window.location=url;
}


//跳转到修改日志页面
function selectdiaryId(id){
	var url=contextPath+"/diary/personaldiary/updatediary.jsp?id="+id;
	 window.location=url;
}
function showdiary(diaryId){
	var url=contextPath+"/diary/personaldiary/diarydetails.jsp?diaryId="+diaryId;
	new SysFrame().tabs('update',{
		title: "日志详情",
		url:url
	});
}
function getCount(){
	var url=contextPath+"/diary/act/DiaryAct/getCountAct.act";
	$.ajax({
		   url:url,
		   type:'post',
	   	   dataType:"json",
	   		async:false,
	   		success:function(data){
	   			$('.username').text(accountName);
	   			$('#allCount').html(data.allCount+"<br>全部日志");
	   			$('#myCount').html(data.myCount+"<br>我的日志");
	   			$('#otherCount').html(data.otherCount+"<br>他人日志");
	   		}
	   });
}
//删除日志
function deleteDiary(id){
	var url=contextPath + "/diary/act/DiaryAct/deleteDiaryIDAct.act?id="+id;
	if(window.confirm("确定删除所有记录吗？\n")){
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
function updatecomm(diaryId){
	$("#commtable"+diaryId).html("");
	commentlook(diaryId);
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
function lookstaff(diaryId){
	if($("#lookstaff"+diaryId).css('display')=="none"){
		$("#lookstaff"+diaryId).show();
		$("#comm"+diaryId).hide();
	}else{
		$("#lookstaff"+diaryId).hide();
	}
}
function getlaud(id,num){
	if($("#laud"+id).prop('class')!="good-ok-div"){
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
					$("#laudNum"+id).html((parseInt(num)+1)+"&nbsp;&nbsp;");
					$("#laud"+id).click(function(){});
				}
		}
	});
	}
}
