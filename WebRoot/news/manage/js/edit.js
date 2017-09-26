$(function(){
	getMessagePriv("news");
	if(newsstatus==1){
		$("#editbtn").hide();
	}
	filesUpLoad("news");
	getSelect("news","newstype","");
	var url=contextPath+"/news/act/NewsAct/getNewsByNewsIdAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		data:{newsId:newsId},
		async:false,
		error:function(e){
		},
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
			fromdata=data;
			attachId=data.attachId;
	   		creatAttachDiv(attachId,"attach");
	   		editorElement.setHtml(fromdata.contect);
	   		if(data.top==1){
					$("#top").prop("checked",true);
				}
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
			}
			if(data.endTime=='0'){
				$("#endTime").val("");
			}
		}
	});
});
function updateNews()
{	
	if($("#title").val()==""){		
		parent.layer.msg('标题不可为空',function(){});
	}else{
	var url=contextPath+"/news/act/NewsAct/updateNewsAct.act";
	var top=0;
	if($('#top').prop("checked")){
		 top=1;
	}
	$.ajax({
		url:url,
		dataType:"text",
		data:{newsId:newsId,
			title:$("#title").val(),
			newstype:$("#newstype").val(),
			accountId:$("#accountId").val(),
			deptPriv:$("#deptPriv").val(),
			userPriv:$("#userPriv").val(),
			createTime:$("#createTime").val(),
			endTime:$("#endTime").val(),
			attachId:$("#attachId").val(),
			contect:editor.getData(),
			commentStatus:$("#commentStatus").val(),
			top:top,
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
function checkstatus(){
	var url=contextPath+"/news/act/NewsPowerAct/getaccountIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				publishupdateNews();
			}else{
				var url=contextPath+"/news/act/NewsPowerAct/getapprovalAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data.length==0){
							publishupdateNews();
						}else{
						approvalinit(data,2);
						}
					}
				});
			}
	}
	});
}
function publishupdateNews()
{	
	var top=0;
	var endStatus=0;
	if($('#top').prop("checked")){
		 top=1;
	}
	if($("#endStatus").prop("checked")){
		endStatus=1;
	}
	var url=contextPath+"/news/act/NewsAct/publishUpdateNewsAct.act";
	$.ajax({
		url:url,
		dataType:"text",
		data:{newsId:newsId,
			title:$("#title").val(),
			newstype:$("#newstype").val(),
			accountId:$("#accountId").val(),
			deptPriv:$("#deptPriv").val(),
			userPriv:$("#userPriv").val(),
			createTime:$("#createTime").val(),
			endTime:$("#endTime").val(),
			attachId:$("#attachId").val(),
			contect:editor.getData(),
			top:top,
			endStatus:endStatus,
			approvalStatus:1,
			commentStatus:$("#commentStatus").val(),
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
 	$('#editnews').bootstrapValidator('revalidateField', 'deptName');
 	$('#newsform').bootstrapValidator('revalidateField', 'userName');
 	$('#newsform').bootstrapValidator('revalidateField', 'userPrivName');
 }