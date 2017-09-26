var beforestock;
var maintNum;
$(function (){
	getmaintId();
});
function getmaintId(){
	var url=contextPath+"/officesupplies/act/OffmaintAct/getIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{maintId:maintId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			var fromdata=data;
			beforestock=data.beforestock;
			maintNum=data.maintNum;
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
			}
			$("#libraryId").append("<option value='"+data.libraryId+"' selected='selected'>"+data.libraryName+"</option>");
			$("#classifyId").append("<option value='"+data.classifyId+"' selected='selected'>"+data.classifyName+"</option>");
			$("#resId").append("<option value='"+data.resId+"' select='selected'>"+data.resName+"</option>");
	}
	});
}
function updatemaint(){
	var para=$("#maintform").serialize();
	para+="&maintId="+maintId;
	var url=contextPath+"/officesupplies/act/OffmaintAct/updatemaintAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:para,
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				updateres();
			}
			else{
			}
	}
	});
}
function updateres(){
	var maintType=$("#maintType").val();
	var resId=$("#resId").val();
	var befores=$("#maintNum").val();
	 if(maintType=="采购入库"){
		var Num=parseInt($("#maintNum").val())-parseInt(maintNum);
		var url=contextPath+"/officesupplies/act/OffresAct/updateresNumAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId,maintNum:Num},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					parent.layer.msg('修改成功');
					history.back();
				}else{
				}
		}
		});
		}else{
			var Num=parseInt($("#maintNum").val())-parseInt(maintNum);
			var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{resId:resId,maintNum:Num},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data!=0){						
						parent.layer.msg('修改成功');
						history.back();
					}else{
					}
			}
			});
		} 
}