var approvalview="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"+
"<div class=\"modal-dialog\"><div class=\"modal-content\"><div class=\"modal-header\">\n"+
"<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n"+
"<h4 class=\"modal-title\" id=\"myModalLabel\">审批意见</h4></div>\n"+
"<div class=\"modal-body\"><table id=\"approval\" style=\"width:100%;\" align=\"center\"><tr><td id=\"userName\">审批人：</td></tr><tr><td>审批意见:<div id=\"approvalContent\"></div></td></tr></table></div>\n"+
"<div class=\"modal-footer\">\n"+
"<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n"
+"</div></div></div></div>";
function viewcreateDialog(){
	$("#modaldialog").html(approvalview);
}
function addapprovalview(){
	viewcreateDialog();
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}
function selectapproval(noticeId){
		var url=contextPath+"/notice/act/ApprovalNoticeAct/lookapprovalAct.act";
		$.ajax({
				url:url,
				dataType:"json",
				data:{noticeId:noticeId},
				async:false,
				error:function(e){	
				},
				success:function(data){
					addapprovalview();
					if(data.userName!=undefined){
					$("#userName").append(data.userName);
					$("#approvalContent").append(data.approvalContent);
					}else{
						$("#approval").html("<tr><td>无审批意见</td></tr>");
					}
				}
			});
	} 
