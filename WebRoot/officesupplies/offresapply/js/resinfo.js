function getresId(){
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
			fromdata=data;
			for(var name in fromdata){
				$("#"+name).html(fromdata[name]);
			}
			var attachId=data.attachId;
			readAttachDiv(attachId,"attach");
			$("#stockPrice").html(data.resPrice*data.beforeStock);
	}
	});
}
$(function(){
	getresId();
});
function ask(){
	var url=contextPath+"/officesupplies/offresapply/applyres.jsp?resId="+resId;
	window.location=url;
}