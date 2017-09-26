$(function(){
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
			$("#StaffName").html(data.lookName);
			$("#userName").html(data.realityName);
			$("#summaryContent").html(data.summaryContent);
			var attachId=data.attachId;
	   		readAttachDiv(attachId,"attach");
		}
	});
}