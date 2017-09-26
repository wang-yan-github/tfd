var zNodes ;
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getUserPowerTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
		},
		success:function(data){
			zNodes=data;
			}
	});
});

var setting = {
			data: {
				simpleData: {
					enable: true
				}
				},
			callback:{
				onClick:goUrl
			}
		};
$(document).ready(function(){
			$.fn.zTree.init($("#userTree"), setting, zNodes);
		});
		function goUrl(event,treeId,treeNode){
		if(!treeNode.isParent){
			parent.document.getElementById("right").src= "/tfd/diary/otherdiary/rightuserdiary.jsp?accountId="+encodeURIComponent(treeNode.id)+"&userName="+encodeURIComponent(treeNode.name);
		}
	}