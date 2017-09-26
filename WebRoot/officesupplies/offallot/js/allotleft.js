var zNodes = null;
	$(function(){
		var requestUrl=contextPath+"/officesupplies/act/OffztreeAct/getallotztreeAct.act";
		$.ajax({
				url:requestUrl,
				dataType:"json",
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
				enable: true}
				},
		callback:{onClick:goUrl},
		view: {
			showLine: false
				}
	};
	$(document).ready(function(){
		$.fn.zTree.init($("#tree1"), setting, zNodes);
	});
	function goUrl(event,treeId,treeNode){
		if(!treeNode.isParent){
			parent.document.getElementById("right").src= "/tfd/officesupplies/offallot/getresallot.jsp?resId="+encodeURIComponent(treeNode.id);
		}
	}