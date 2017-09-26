var id=null;
$(function(){
	var indexDetail=null;
	$.ajax({
		url:contextPath+"/api/index/act/IndexAct/getIndexById.act",
		data:{id:id},
		type:"POST",
		dataType:"json",
		success:function(data){
			indexDetail=data;
			for(var fieldName in indexDetail){
				var $div=$("#"+fieldName);
				if ($div.length>0) {
					$div.html(indexDetail[fieldName]);
				}
			}
		}
	});
});