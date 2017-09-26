
//初始化
function approvalinit(data,id){
	initgetoptOkapproval(id,data.APPROVAL_STAFF);
	
}
//获取审批人员列表
function getapproval(data){
	for(var i = 0; i < data.length;i++){ 
			var privId="<div style='display:none;' id='privId"+data[i].accountId+"' >"+data[i].accountId+"<div>";
			 var privName="<div id='privName"+data[i].accountId+"' class='checkpriv' onMouseOut=\"mooutprivapproval('privName"+data[i].accountId+"');\" onMouseOver=\"mouseoverapproval('privName"+data[i].accountId+"');\" onclick=\"mousedownapproval('"+data[i].accountId+"','"+data[i].userName+"','privName"+data[i].accountId+"');\">"+data[i].userName+"</div>";
			 $('#selectPriv').append(privId);
			 $('#selectPriv').append(privName);
		} 
}

function initgetoptOkapproval(id,APPROVAL_STAFF){
		if(id==1){
		if(confirm("确认发布？")){
			var top=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				var attachPower=0;
				if($("#attachPower").prop("checked")){
					attachPower=1;
				}
				var url=contextPath+"/notice/act/NoticeAct/publishnoticeAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					data:{
						noticeTitle:$("#noticeTitle").val(),
						noticeType:$("#noticeType").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						attachId:$("#attachId").val(),
						noticeContent:CKEDITOR.instances.editor.getData(),
						top:top,
						approvalStaff:APPROVAL_STAFF,
						approvalStatus:0,
						attachPower:attachPower,
						endTime:$("#endTime").val(),
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1)
							{
							parent.layer.msg('发布成功');
					var url=contextPath+"/notice/manage/index.jsp";
						new SysFrame().tabs('select', "通知公告发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'通知公告维护',
							oldtitle:'通知公告发布',
							url: url 
						});
						}
				}
				});
				}
			}else{
				if(confirm("确认发布？")){
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
						attachPower:attachPower,
						approvalStaff:APPROVAL_STAFF,
						approvalStatus:0,
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
			}
}

 