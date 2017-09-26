var id=null;
$(function(){
	var indexDetail=null;
	

	var treeSetting={
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
         url:contextPath+"/api/index/act/IndexAct/indexChoseTree.act",
         autoParam:["id"]
      },
      callback:{
         onClick:function(event,treeId,treeNode){
            $("#api-form input[name='parentId']").val(treeNode.id);
            $("#api-form input[name='parentName']").val(treeNode.text);
            $(".dropdown-menu-parent-list").trigger("click.bs.dropdown.data-api");
         }
      }
   };
	
	
	
	
	
	

   $.fn.zTree.init($("#index-tree"),treeSetting);

   
   $("#api-form").formValidation({
      framework:"bootstrap",
      err:{
         container:"tooltip"
      },
      icon:{
         valid:"glyphicon glyphicon-ok",
         invalid:"glyphicon glyphicon-remove",
         validating:"glyphicon glyphicon-refresh"
      },
      fields:{
         name:{
            validators:{
               notEmpty:{
                  message:"请填写"
               }
            }
         }
      }
   })
   .on("success.form.fv",function(e){
	  e.preventDefault();
	  
	  $("#api-form *[name='apiContent']").val(CKEDITOR.instances.apiContent.getData());
	  
      $.ajax({
         url:contextPath+"/api/index/act/IndexAct/save.act",
         data:$(e.target).serialize(),
         type:"POST",
         async:false,
         dataType:"json",
         success:function(rv){
        	 if(eval(rv)){
        		 parent.location.reload();
        	 }else{
        		 alert("操作失败！");
        		 location.reload();
        	 }
         }
      });
   });
   
   var apiContentEditor=CKEDITOR.replace(
		   $("#api-form").find("*[name='apiContent']")[0]
   );
   
	$("#api-form input[name='parentName']").on("click",function(){
		$(".dropdown-menu-parent").trigger("click");
	});

	$(document)
    .on('click.bs.dropdown.data-api', '.dropdown .ztree', function (e) { e.stopPropagation(); })
    .on('click.bs.dropdown.data-api', "input[name='parentName']", function (e) { e.stopPropagation(); });
	
	if(id!=""){
		$.ajax({
			url:contextPath+"/api/index/act/IndexAct/getIndexById.act",
			data:{id:id},
			type:"POST",
			dataType:"json",
			async:false,
			success:function(data){
				indexDetail=data;
				for(var fieldName in indexDetail){
					var $input=$("#api-form input[name='"+fieldName+"']");
					if ($input.length>0) {
						$input.val(indexDetail[fieldName]);
					}
				}
			}
		});
		
		var treeObj=parent.$.fn.zTree.getZTreeObj("index-tree");
		var selectedNode=treeObj.getSelectedNodes()[0];
		var parentNode=treeObj.getNodeByParam("id",selectedNode.pid);
		
		$("#api-form input[name='parentName']").val(parentNode?parentNode.text:"根目录");
		CKEDITOR.instances.apiContent.setData(indexDetail.apiContent);
	}
});