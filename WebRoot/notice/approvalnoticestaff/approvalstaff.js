function doinit()
{
	var requestUrl =contextPath+"/notice/act/ApprovalNoticePowerAct/lookpowerAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			async:false,
			success:function(data){
				var j=1;
				for(var i=0;i<data.length;i++){
					var tr="<tr><td align=\"center\" width=\"5%\">"+j+"</td><td align=\"center\" width=\"15%\">"+data[i].codevalue+"</td>"+
					"<td align=\"center\" width=\"60%\">"+
					"<input type=\"hidden\" name=\"approvalStaff\" id=\"approvalStaff\" value=\""+data[i].approvalStaff+"\">"+ 
					"<div name=\"userName1\"  id=\"userName1\">"+data[i].staffName+"</div></td>"+
					"<td align=\"center\" width=\"20%\"><a href=\"javascript:void(0);\" onclick=\"installpower('"+data[i].codevalue+"','"+data[i].approvalStaff+"','"+data[i].staffName+"','"+data[i].powerId+"');\" >设置</a></td></tr>";
					$("#approvalpowerstaff").append(tr);
					j++;
				}
			}
		});
	}
	function installpower( noticetype,approvalstaff,staffname,powerId){
		$("#noticeType").val(noticetype);
		if(approvalstaff=="null"){
		}else{
		$("#approvalStaff").val(approvalstaff);
		}
		if(staffname=="null"){}
		else{
		$("#userName").val(staffname);
		}
		$("#powerId").val(powerId);
		$('#noticepowerform').bootstrapValidator('revalidateField', 'noticeType');
	}
	function addpower(){
		if($("#powerId").val()=="null"){
			var url=contextPath+"/notice/act/ApprovalNoticePowerAct/addnoticepowerAct.act";
			$.ajax({
				url:url,
				dataType:"text",
				data:{
					noticeType:$("#noticeType").val(),
					approvalStaff:$("#approvalStaff").val()
				},
				async:false,
				success:function(data){
					if(data==1){
						parent.layer.msg('保存成功');
						window.history.go(0);
					}
				}
			});
		}else{
			var url=contextPath+"/notice/act/ApprovalNoticePowerAct/updatepowerAct.act";
			$.ajax({
				url:url,
				dataType:"text",
				data:{
					powerId:$("#powerId").val(),
					noticeType:$("#noticeType").val(),
					approvalStaff:$("#approvalStaff").val()
				},
				async:false,
				success:function(data){
					if(data==1){
						parent.layer.msg('保存成功');
						window.history.go(0);
					}
				}
			});
	}
	}
	$(function (){
		doinit();
	});