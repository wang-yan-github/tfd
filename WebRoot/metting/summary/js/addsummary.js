$(function(){
	filesUpLoad("meeting");
	$("#meetingname").html(meetingName);
	$("#lookStaff").val(attendStaff);
	var url=contextPath+"/meeting/act/MeetingrequestAct/getattendstaffAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{attendStaff:attendStaff},
		async:false,
		error:function(e){
		},
		success:function(data){
				$("#StaffName").val(data);
		}
	});
	
});
	function insetsummary(){
		var summaryContent=CKEDITOR.instances.editor.getData();
		var url=contextPath+"/meeting/act/MeetingsummaryAct/addsummaryAct.act";
		$.ajax({
			url:url,
			type:"POST",
			dataType:"text",
			data:{lookStaff:attendStaff,
				requestId:requestId,
				meetingName:meetingName,
				realityStaff:$("#realityStaff").val(),
				askStaff:createUser,
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
