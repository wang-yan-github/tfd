var zNodes = null;
	$(function(){
		var requestUrl=contextPath+"/officesupplies/act/OffztreeAct/getztreeAct.act";
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
		if(treeNode.isParent && treeNode.pId!=null){
			parent.document.getElementById("right").src= "/tfd/officesupplies/offres/restable.jsp?classifyId="+encodeURIComponent(treeNode.id);
		}
		if(!treeNode.isParent){
			parent.document.getElementById("right").src= "/tfd/officesupplies/offres/updateres.jsp?resId="+encodeURIComponent(treeNode.id);
		}
	}
	
	function getquery(){
		parent.document.getElementById("right").src= "/tfd/officesupplies/offres/queryres.jsp";
	}
	function getNewres(){
		parent.document.getElementById("right").src= "/tfd/officesupplies/offres/Newres.jsp";
	}