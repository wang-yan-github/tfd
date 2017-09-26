$(function(){
 
   var treeSetting={
      data:{
         simpleData:{
            enable:true,
            idKey:"id",
            pIdKey:"pid",
            rootPId:"0"
         },
		 key:{
			name:"html",
			title:"text"
		 }
      },
      async:{
         enable:true,
         url:contextPath+"/api/index/act/IndexAct/indexTree.act",
         autoParam:["id"]
      },
      callback:{
         onClick:function(event,treeId,treeNode){
        	$("#api-detail").hide();
        	var apiFrame=$("#api")[0].contentWindow; 
        	
            if(treeNode.path!=null&&treeNode.path!=""){
            	apiFrame.location=contextPath+treeNode.path;
            }else{
            	apiFrame.location=contextPath+"/api/api-manage/detail.jsp?id="+treeNode.id;
            }
            if(treeNode.path=="/api/api-manage/edit.jsp"){
            	$("#api-edit,#api-delete").hide();
            }else{
            	$("#api-edit,#api-delete").show();
            }
         }
      },
      view:{
    	  nameIsHTML:true
      }
   };

   $.fn.zTree.init($("#index-tree"),treeSetting);
   
   $("#api-delete").on("click",function(){
	   if (!window.confirm("确定删除？")) {
		   return false;
	   }
	   var tree=$.fn.zTree.getZTreeObj("index-tree");
	   
	   $.ajax({
         url:contextPath+"/api/index/act/IndexAct/delete.act",
         data:{id:tree.getSelectedNodes()[0].id},
         type:"POST",
         async:false,
         dataType:"json",
         success:function(rv){
        	 if(!eval(rv)){
        		 alert("操作失败！");
        		 location.reload();
        	 }
        	 location.reload();
         }
      });
   });
   
   $("#api-detail-close").on("click",function(){
	  $("#api-detail").hide().find("#api-text").val("");
	  
   });
   $("#api-view").on("click",function(){
	   var tree=$.fn.zTree.getZTreeObj("index-tree");
	   var selectedNodes=tree.getSelectedNodes();
	   var path="/api/api-manage/index.jsp";
	   if (selectedNodes&&selectedNodes.length>0) {
		   path=selectedNodes[0].path;
		   if(path==null||path==""){
			   path="/api/api-manage/detail.jsp";
		   }
	   }
	   $.ajax({
         url:contextPath+"/api/index/act/IndexAct/getApiText.act",
         data:{path:path},
         type:"POST",
         async:false,
         dataType:"json",
         success:function(rv){
        	 $("#api-detail").show().find("#api-text").val(rv.text);;
         }
	   });
   });
   $("#api-edit").on("click",function(){
  	 $("#api-detail").hide();
	   var tree=$.fn.zTree.getZTreeObj("index-tree");
	   var selectedNodes=tree.getSelectedNodes();
	   $("#api")[0].contentWindow.location=contextPath+"/api/api-manage/edit.jsp?id="+selectedNodes[0].id;
   });
});