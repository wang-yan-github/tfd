$(function(){
	getMessagePriv("notice");
	filesUpLoad("notice");
	getSelect("notice","noticeType","");
	var url=contextPath+"/notice/act/NoticeAct/getIdnoticAct.act";
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{noticeId:noticeId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var fromdata=data;
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
			approvalStatus=data.approvalStatus;
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
				if(fromdata[name]==1){
					$("#"+name).prop("checked",true);
				}
			}
			var editorElement = CKEDITOR.document.getById('editor');
	   		editorElement.setHtml(fromdata.noticeContent);
			attachId=fromdata.attachId;
			creatAttachDiv(attachId,"attach");
			if(data.endTime=='0'){
				$("#endTime").val("");
			}
		}
	});
	});
function updatenotice()
{	
	if($("#noticeTitle").val()==""){
		parent.layer.msg('标题不可为空',function(){});
	}else{
	var url=contextPath+"/notice/act/NoticeAct/updatenoticeAct.act";
	var top=0;
	if($('#top').prop("checked")){
		 top=1;
	}
	var attachPower=0;
				if($("#attachPower").prop("checked")){
					attachPower=1;
				}
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{
			noticeId:noticeId,
			noticeTitle:$("#noticeTitle").val(),
			noticeType:$("#noticeType").val(),
			accountId:$("#accountId").val(),
			deptPriv:$("#deptPriv").val(),
			userPriv:$("#userPriv").val(),
			createTime:$("#createTime").val(),
			attachId:$("#attachId").val(),
			attachPower:attachPower,
			noticeContent:CKEDITOR.instances.editor.getData(),
			top:top,
			endTime:$("#endTime").val(),
			smsReminds:getsmsRemind()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1){
				parent.layer.msg('修改成功');
				history.back();
			}
		}
	});
	}
	}
function publishupdatenotice()
{	
	var powerurl=contextPath+"/notice/act/ApprovalNoticePowerAct/looktypeapprovalAct.act";
	$.ajax({
		url:powerurl,
		type:"POST",
		dataType:"json",
		data:{
			noticeType:$("#noticeType").val()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(jQuery.isEmptyObject(data)||approvalStatus!=0){
				var url=contextPath+"/notice/act/NoticeAct/publishupdatenoticeAct.act";
				var top=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				var attachPower=0;
				if($("#attachPower").prop("checked")){
					attachPower=1;
				}
				$.ajax({
					url:url,
					type:"POST",
					dataType:"text",
					data:{
						noticeId:noticeId,
						noticeTitle:$("#noticeTitle").val(),
						noticeType:$("#noticeType").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						attachId:$("#attachId").val(),
						noticeContent:CKEDITOR.instances.editor.getData(),
						top:top,
						approvalStatus:1,
						attachPower:attachPower,
						endTime:$("#endTime").val(),
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1){
							parent.layer.msg('发布成功');
							history.back();
						}
					}
				});
			}
			else{
				approvalinit(data,2);	
			}
	}
	});
		}
			 function showother()
 {
	 if($("#showother").css('display')=="none")
		 {
		 $("#showother").show();
		 }else
			 {
			 $("#showother").hide();
			 }
 }
  function deptNameval(){
 	$('#noticeform').bootstrapValidator('revalidateField', 'deptName');
 	$('#noticeform').bootstrapValidator('revalidateField', 'userName');
 	$('#noticeform').bootstrapValidator('revalidateField', 'userPrivName');
 }