$(function(){
	getlibrary();
});
var k=1;
function getlibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/alllibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			var librarycon="";
			var library=data.library;
			for(var i=0;i<library.length;i++){
				librarycon+="<div class=\"list-group-item\"  style=\"padding: 0px;cursor: auto;margin-left:8%;width:84%;\">"+
					"<a style=\"cursor: auto;\" class=\"list-group-item active\">"+library[i].libraryName+"</a>"+
				"<table class=\"table table-striped table-condensed\" id=\""+library[i].libraryId+"\">"+
				"<tr align=\"center\"><td width=\"20%\">名称</td><td width='15%'>库存</td><td width='15%'>单价</td><td width=\"50%\">数量</td></tr>"+
				"</table>"+
				"</div>";
			}
			$("#resapplyform").append(librarycon);
			var classify=data.classify;
			for (var i=0; i < classify.length; i++) {
			 var classifycon="<tr id=\""+classify[i].libraryId+"\"><td colspan=\"4\"><h3 class=\"panel-title\">"+classify[i].libraryName+"</h3></td></tr>";
				$("#"+classify[i].topId).append(classifycon);
			}
			var res=data.res;
			for (var j=0; j < res.length; j++) {
			  var rescon="<tr align=\"center\"><td width=\"20%\">"+res[j].resName+"</td><td width=\"20%\">"+
				 		"<input type=\"hidden\" id=\"stock"+k+"\" value=\""+res[j].beforeStock+"\">"+res[j].beforeStock+"</td><td width=\"20%\">"+res[j].resPrice+"</td><td width=\"40%\"><input type=\"hidden\" id='resId"+k+"' value='"+res[j].resId+"'>"+
				 		"<div style=\"float: left;margin-left: 20%;\">"+
				 		"<input type=\"button\" value=\"-\" id='btnminus' onclick='btnmin("+k+");' style=\"font-weight: 20px;\" class=\"btn btn-default\"></input>"+
				 		"</div><div style=\"float: left;margin-left: 0px;width:40%;\" class=\"col-xs-4 form-group\">"+
				 		"<input id=\"applyNum"+k+"\" class=\"form-control\"  name=\"Numapply[]\" onblur='checknum("+k+");'/></div>"+
				 		"<div style=\"float: left;\" >"+
						"<input type=\"button\" value=\"+\" id='btnplus' onclick='btnplu("+k+");' class=\"btn btn-default\"></input></div>"+
				 		"</td></tr>";
				 	$("#"+res[j].classifyId).after(rescon);
				 		k++;
			}
			$("#resapplyform").append("<div align=\"center\"><button type=\"submit\" class=\"btn btn-primary btn-lg\">提交</button></div>");
	}
	});
}
function checknum(id){
	var num=parseInt($("#stock"+id).val());
	var beforenum=parseInt($("#applyNum"+id).val());
	if(beforenum<0){
		$("#applyNum"+id).val(1);
	}else{
	if(beforenum>num){
		$("#applyNum"+id).val(num);
	}
	}
}
function btnmin(id){
	if(parseInt($("#applyNum"+id).val())==0||$("#applyNum"+id).val()==""){
		
	}else{
		var num=parseInt($("#applyNum"+id).val())-1;
		$("#applyNum"+id).val(num);
	}
}
function btnplu(id){
	if(parseInt($("#applyNum"+id).val())==parseInt($("#stock"+id).val())||$("#applyNum"+id).val()==""){
		
	}else{
		var num=parseInt($("#applyNum"+id).val())+1;
		$("#applyNum"+id).val(num);
	}
}
function addapply(){
	var status=0;
	for(var j=1;j<k;j++){
		if($("#applyNum"+j).val()!=0 && $("#applyNum"+j).val()!=""){
			status=getresId($("#resId"+j).val(),$("#applyNum"+j).val());
		}
	}
	if(status!=0){
		parent.layer.msg('申请成功');
		window.location.reload();
	}
	else{
	}
}
function getresId(resId,Num){
	var libraryId;
	var classifyId;
	var resType;
	var applyNum=Num;
	var dispatchStaff;
	var approvalStaff;
	var status;
	var applyRemary=$("#applyRemary").val();
	var url=contextPath+"/officesupplies/act/OffresAct/getIdresAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{resId:resId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(!jQuery.isEmptyObject(data)){
			libraryId=data.libraryId;
			classifyId=data.classifyId;
			resType=data.resType;
			dispatchStaff=data.dispatchStaff;
			var applyStaff=data.approveStaff;
			if($("#approvalStaff").val()!=""){
				 approvalStaff=$("#approvalStaff").val();	
			}
			else{
				approvalStaff=applyStaff;
			}
			var url=contextPath+"/officesupplies/act/OffresapplyAct/addapplyAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{
					resId:resId,
					libraryId:libraryId,
					classifyId:classifyId,
					resType:resType,
					applyNum:applyNum,
					dispatchStaff:dispatchStaff,
					approvalStaff:approvalStaff,
					applyRemary:applyRemary
				},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					status=data;
					}
			});
			}

	}
	});
	return status;
}
function checksubmit(){
	var j;
	for(j=1;j<k;j++){
		if($("#applyNum"+j).val()!=0 && $("#applyNum"+j).val()!=""){
			$('#applymyModal').modal({backdrop: 'static', keyboard: false});
			$("#applymyModal").modal('show'); 	
			break;	
		}else{
		if( k-j==1&& $("#applyNum"+j).val()==0 && $("#applyNum"+j).val()==""){			
			parent.layer.msg('请填写申请数量',function(){});
		}
		}
	}
}