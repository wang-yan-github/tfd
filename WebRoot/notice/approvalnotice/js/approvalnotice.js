var decide;
$(function () {
	getMessagePriv("notice");
	var attachId;
	var requestUrl =contextPath+"/notice/act/NoticeAct/getIdnoticAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{noticeId:noticeId},
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
				$("#createTime").val(data.createTime);
				attachId=data.attachId;
				readAttachDiv(attachId,"attachDiv");
				decide=data.approvalStatus;
				if(data.approvalStatus!=0){
					var url=contextPath+"/notice/act/ApprovalNoticeAct/lookapprovalAct.act";
		$.ajax({
				url:url,
				dataType:"json",
				data:{noticeId:noticeId},
				async:false,
				error:function(e){	
				},
				success:function(data){
					var editorElement = CKEDITOR.document.getById('editor');
	   					editorElement.setHtml(data.approvalContent);
				}
			});
				}
				$("#title").html(" ["+data.noticeType+"]"+data.noticeTitle);
				$("#noticeContent").html(data.noticeContent);
				$("#foot").html(data.createName+"  发布于:  "+data.createTime);
			}
		});
});
function checkpass(){
	if(decide==0){
		passapproval(1);
	}else{
		updatepassapproval(1);
	}
}
function checknopass(){
	if(decide==0){
		passapproval(2);
	}else{
		updatepassapproval(2);
	}
}
function updatepassapproval(id){
	var url=contextPath+"/notice/act/ApprovalNoticeAct/updateapprovalAct.act";
	$.ajax({
		url:url,
		dataType:"text",
		data:{noticeId:noticeId,
			approvalContent:CKEDITOR.instances.editor.getData()
		},
		async:false,
		success:function(data){
			if(data==1){
				if(id==1){
				passnotice();
				}else{
					nopassnotice();
				}
			}
		}
	});
}
function passapproval(){
	var url=contextPath+"/notice/act/ApprovalNoticeAct/addapprovalAct.act";
	$.ajax({
		url:url,
		dataType:"text",
		data:{noticeId:noticeId,
			approvalContent:CKEDITOR.instances.editor.getData()
		},
		async:false,
		success:function(data){
			if(data==1){
				passnotice();
			}
		}
	});
}
function passnotice(){
	var returnUrl=contextPath+"/notice/act/NoticeAct/approvalpassnoticeAct.act";
				$.ajax({
					url:returnUrl,
					dataType:"text",
					data:{noticeId:noticeId,
							accountId:$("#accountId").val(),
							deptPriv:$("#deptPriv").val(),
							userPriv:$("#userPriv").val(),
							createTime:$("#createTime").val(),
							smsReminds:getsmsRemind()
						},
					async:false,
					success:function(data){
						if(data==1){
							parent.layer.msg('审批通过');
							history.back();
						}
					}
				});
}
function nopassnotice(){
	var returnUrl=contextPath+"/notice/act/NoticeAct/approvalnopassnoticeAct.act";
				$.ajax({
					url:returnUrl,
					dataType:"text",
					data:{noticeId:noticeId},
					async:false,
					success:function(data){
						if(data==1){
							parent.layer.msg('审批不通过');
							history.back();
						}
						
					}
				});
}
