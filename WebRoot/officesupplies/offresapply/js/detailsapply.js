$(function(){
	getapplyId();
	});
function getapplyId(){
	var url=contextPath+"/officesupplies/act/OffresapplyAct/getapplyIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{applyId:applyId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#libraryId").html(data.libraryName);
			$("#classifyId").html(data.classifyName);
			$("#resId").html(data.resName);
			$("#resType").html(data.resType);
			$("#applyNum").html(data.applyNum);
			$("#applyRemary").html(data.applyRemary);
		}
	});
}