$(function () {
	getMessagePriv("news");
	var attachId;
	var requestUrl =contextPath+"/news/act/NewsAct/getNewsByNewsIdAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{newsId:newsId},
			async:false,
			success:function(data){
				if(data.isSms.sitesms==1){
				$("#sitesms").prop("checked",true);
			}
			if(data.isSms.mobilesms==1){
				$("#mobilesms").prop("checked",true);
			}
			if(data.isSms.emailsms==1){
				$("#emailsms").prop("checked",true);
			}
			if(data.isSms.webemilesms==1){
				$("#webemilesms").prop("checked",true);
			}
			if(data.isSms.wxsms==1){
				$("#wxsms").prop("checked",true);
			}
				$("#accountId").val(data.accountId);
				$("#deptPriv").val(data.deptPriv);
				$("#userPriv").val(data.userPriv);
				attachId=data.attachId;
				$("#createTime").val(data.createTime);
				readAttachDiv(attachId,"attach");
				$("#title").html("["+data.newstype+"]"+data.title);
				$("#contect").html(data.contect);
				$("#foot").html(data.createName+"&nbsp;&nbsp;发布于:&nbsp;&nbsp;"+data.createTime+"   点击数："+data.onclickcount);
			}
		});
});
function vianews()
{
	var url=contextPath+"/news/act/NewsAct/managenewsAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			newsId:newsId,
			approvalStatus:1,
			accountId:$("#accountId").val(),
			deptPriv:$("#deptPriv").val(),
			userPriv:$("#userPriv").val(),
			createTime:$("#createTime").val(),
			smsReminds:getsmsRemind()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1){
				parent.layer.msg('审批成功');
				history.back();
			}
	}
	});
}
function notnews(){
	var url=contextPath+"/news/act/NewsAct/managenewsAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			newsId:newsId,
			approvalStatus:2
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1){				
				parent.layer.msg('审批成功');
				history.back();
			}
	}
	});
}