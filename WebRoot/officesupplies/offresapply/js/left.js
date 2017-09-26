var zNodes = null;
	$(function(){
		var requestUrl=contextPath+"/officesupplies/act/OffztreeAct/getapplyztreeAct.act";
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
		callback:{onClick:goresUrl},
		view: {
			showLine: true
				}
	};
	$(document).ready(function(){
		$.fn.zTree.init($("#ztree1"), setting, zNodes);
		var treeObj = $.fn.zTree.getZTreeObj("ztree1");
		treeObj.expandAll(true);
		
	});
	
	
	function goresUrl(event,treeId,treeNode){
		if(treeNode.isParent && treeNode.pId!=null){
			parent.document.getElementById("right").src= "/tfd/officesupplies/offresapply/right.jsp";
		}
		if(!treeNode.isParent){
			parent.document.getElementById("right").src= "/tfd/officesupplies/offresapply/resinfo.jsp?resId="+encodeURIComponent(treeNode.id);
		}
	}