$(function(){
	filesUpLoad("meeting");
	getIdsummary();
});
function getIdsummary(){
	var url=contextPath+"/meeting/act/MeetingsummaryAct/getIdsummaryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			requestId:requestId
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#meetingname").html(data.meetingName);
			$("#lookStaff").val(data.lookStaff);
			$("#StaffName").val(data.lookName);
			$("#userName").val(data.realityName);
			$("#realityStaff").val(data.realityStaff);
			if(data.summaryContent!=null){
		   var editorElement = CKEDITOR.document.getById('editor');
	   					editorElement.setHtml(data.summaryContent);
			}
			$("#attachId").val(data.attachId);
			attachId=data.attachId;
	   		creatAttachDiv(attachId,"attach");
		}
	});
}
	function updatesummary(){
		var summaryContent=CKEDITOR.instances.editor.getData();
		var url=contextPath+"/meeting/act/MeetingsummaryAct/updatesummaryAct.act";
		$.ajax({
			url:url,
			type:"POST",
			dataType:"text",
			data:{
				requestId:requestId,
				lookStaff:$("#lookStaff").val(),
				realityStaff:$("#realityStaff").val(),
				summaryContent:summaryContent,
				attachId:$("#attachId").val()
			},
			async:false,
			error:function(e){
			},
			success:function(data){
				var url=contextPath+"/meeting/act/MeetingrequestAct/getIdsummaryAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"text",
					data:{requestId:requestId},
					async:false,
					error:function(e){
					},
					success:function(data){
							if(data!=0){
								parent.layer.msg('保存成功！');
								history.back();
							}
					}
				});
			}
		});
	}
		function userNameval(){
		$('#summaryform').bootstrapValidator('revalidateField', 'userName');
	}
function StaffNameval(){
	$('#summaryform').bootstrapValidator('revalidateField', 'StaffName');
}