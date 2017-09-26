$(function() {
	$.fn.zTree.init(
		$("#dept-tree"),
		{
	        data:{
	          simpleData:{
	            enable:true,
	            idKey:"id",
	            pIdKey:"pid",
	            rootPId:"0"
	          },
	          key:{
	            name:"text"
	          }
	        },
	        async:{
	          enable:true,
	          url:contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTree.act",
	          autoParam:["id"]
	        },
	        callback:{
	        	onClick:function(event,treeId,treeNode){
					parent.$("iframe[name='edit']")[0].contentWindow.location = contextPath+"/system/unit/dept/edit.jsp?deptId=" + treeNode.id;
	        	}
	        }
	    }
	);
	$("#dept-add").on("click",function(){
		parent.$("iframe[name='edit']")[0].contentWindow.location = contextPath + "/system/unit/dept/add.jsp";
	});
	$("#dept-import").on("click",function(){
		parent.$("iframe[name='edit']")[0].contentWindow.location = contextPath + "/system/unit/dept/import.jsp";
	});
	$("#dept-export").on("click",function(){
		location.href = contextPath + "/tfd/system/unit/dept/act/DeptExportAct/dataExport.act";
	});
	
});