var meetinghtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"+
   "<div class=\"modal-dialog\">\n"+
      "<div class=\"modal-content\"><div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"+
            "<h4 class=\"modal-title\" id=\"myModalLabel\">审批意见</h4>\n"+
         "</div><div class=\"modal-body\"><div id=\"approvalContent\"></div></div><div class=\"modal-footer\">\n"+
            "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n"+
         "</div></div></div></div>";
function lookapprovalmeeting(requestId){
	approvalcreateDialog();
	addapprovalmeeting();
	var url=contextPath+"/meeting/act/BpprovalmeetingAct/getmeetingIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{meetingId:requestId},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data.approvalContent!=""&&data.approvalContent!=undefined){
			$("#approvalContent").html(data.approvalContent);
		  }else{
		  	$("#approvalContent").html("无记录");
		  }
		}
	}); 
}
function approvalcreateDialog(){
	$("#modaldialog").html(meetinghtml);
}
function addapprovalmeeting(){
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}
