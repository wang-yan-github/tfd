

$(function(){
	var REQUEST_URL_TREE=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/tree.act";
	var REQUEST_URL_TREE_LIST=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/treeList.act";
	var REQUEST_URL_ADD=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/add.act";
	var REQUEST_URL_UPDATE=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/update.act";
	var REQUEST_URL_DELETE=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/delete.act";
	var REQUEST_URL_SEARCH=contextPath+"/infoservice/classfication/act/InfoserviceClassficationAct/search.act";
	
	var treeListSetting={
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
			url:REQUEST_URL_TREE_LIST,
			autoParam:["id=parent"]
		},
		callback:{
			onExpand:treeListNodeExpand,
			onCollapse:treeListNodeCollapse,
			onAsyncSuccess:function(event,treeId,treeNode,msg){
				var treeList=$.fn.zTree.getZTreeObj("tree-list");
				var nodes=treeList.getNodesByParam("pid","0");
				if (nodes.length==0) {
					$("#alert-tree-list-message").show();
				}else{
					$("#alert-tree-list-message").hide();
				}
			}
		},
		view:{
			showLine:false,
			nameIsHTML:true,
			addDiyDom:function(treeId,treeNode){
				if($(".tree-node-opt-bar[value='"+treeNode.id+"']").length>0) return;
				var optHtml="";
				optHtml+="<span class='tree-node-opt-bar' value='"+treeNode.id+"'>";
				optHtml+="	<button class='btn btn-xs btn-primary tree-node-opt-new' value='"+treeNode.id+"'>新建</button>";
				optHtml+="	<button class='btn btn-xs btn-primary tree-node-opt-edit' value='"+treeNode.id+"'>编辑</button>";
				optHtml+="	<button class='btn btn-xs btn-primary tree-node-opt-delete' value='"+treeNode.id+"'>删除</button>";
				optHtml+="</span>";
				
				$("#"+treeNode.tId+"_a").append(optHtml);
			},
			expandSpeed:""
		}
	};
	var treeSetting={
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPId:"0"
			},
			key:{
				name:"html"
			}
		},
		async:{
			enable:true,
			url:REQUEST_URL_TREE,
			autoParam:["id=parent"]
		},
		callback:{
			onClick:parentChose
		},
		view:{
			expandSpeed:""
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	function treeListNodeCollapse(event, treeId, treeNode) {
	    $("#"+treeNode.tId+" .icon-folder[value='"+treeNode.id+"']").removeClass("glyphicon-folder-open").addClass("glyphicon-folder-close");
	};
	function treeListNodeExpand(event, treeId, treeNode) {
	    $("#"+treeNode.tId+" .icon-folder[value='"+treeNode.id+"']").removeClass("glyphicon-folder-close").addClass("glyphicon-folder-open");
	};
	function parentChose(event,treeId,treeNode){
		var formId="form-add";
		var navigation=treeNode.navigation;
		if (treeId=="form-update-tree"){
			formId="form-update";
			navigation=navigation+"."+$form(formId,"input[name='id']").val();
		}
		
		$form(formId,"input[name='parent']").val(treeNode.id);
		$form(formId,"input[name='navigation']").val(navigation);
		$form(formId,"input[name='parentName']").val(treeNode.text);
		
		$form(formId,".dropdown-menu-parent-list").trigger("click.bs.dropdown.data-api");
		
		$("#"+formId).data("formValidation")
		.updateStatus("name","NOT_VALIDATED")
		.validateField("name");
		
		if (formId=="form-update") {
			$("#"+formId).data("formValidation")
			.updateStatus("parentName","NOT_VALIDATED")
			.validateField("parentName");
		}
	}
	function bAlert($e,delay,callBack){
		if(!$e||$e.length==0){
			return false;
		}
		if (!delay) {
			delay=1000;
		}
		$e.fadeIn();
		window.setTimeout(function(){
			$e.fadeOut(200,callBack);
		},delay);
	}
	function treeNodeToClassfication(treeNode){
		var classfication=new Object();
		
		var treeList=$.fn.zTree.getZTreeObj("tree-list");
		var parentName="根目录";
		var pNode=treeList.getNodeByParam("id",treeNode.pid);
		if (pNode) {
			parentName=pNode.text;
		}
		
		classfication.id=treeNode.id;
		classfication.name=treeNode.text;
		classfication.parent=treeNode.pid;
		classfication.parentName=parentName;
		classfication.navigation=treeNode.navigation;
		classfication.sort=treeNode.sort;
		classfication.remark=treeNode.remark;
		
		return classfication
	}
	function loadClassfication(formId,classfication){
		for(var fieldName in classfication){
			$form(formId,"input[name='"+fieldName+"']").val(classfication[fieldName]);
		}
	}
	function clearClassfication(formId,id){
		var classfication=new Object();
		
		classfication.id="";
		classfication.name="";
		classfication.parent="0";
		classfication.parentName="根目录";
		classfication.navigation="0";
		classfication.sort="0";
		classfication.remark="";
		
		var treeList=$.fn.zTree.getZTreeObj("tree-list");
		var selectedNode=treeList.getNodeByParam("id",id);
		
		if (selectedNode) {
			classfication.parent=selectedNode.id;
			classfication.navigation=selectedNode.navigation;
			classfication.parentName=selectedNode.text;
		}
		
		loadClassfication(formId,classfication);
		
		$("input[name='name']").focus();
	}
	function classficationDelete(navigation){
		if (!window.confirm("此操作，将会删除该分类及其子分类，是否继续？")) {
			return false;
		}
		$.ajax({
			url:REQUEST_URL_DELETE,
			data:{navigation:navigation},
			type:"POST",
			dataType:"json",
			async:true,
			error:function(e){
				bAlert($("#alert-delete-failed"), 1000, function(){
					location.reload();
				});
			},
			success:function(result){
				if (result>0) {
					bAlert($("#alert-delete-success"), 1000, function(){
						treeOperateSuccess();
					});
				}else{

					bAlert($("#alert-delete-failed"), 1000, function(){
						location.reload();
					});
				}
			}
			
		})
	}
	function search(param){
		var result=null;
		$.ajax({
			url:REQUEST_URL_SEARCH,
			data:param,
			type:"POST",
			async:false,
			dataType:"json",
			success:function(data){
				result=data;
			}
		});
		return result;
	}
	function nameRepeatCheck(value, validator, $field,formId){
		value=$.trim(value);
		$field.val(value);
		
		if (formId=="form-update") {
			var id=$form(formId,"input[name='id']").val();
			var treeList=$.fn.zTree.getZTreeObj("tree-list");
			var treeNode=treeList.getNodeByParam("id",id);
			var parent=$form(formId,"input[name='parent']").val();
			
			if(treeNode.text==value&&treeNode.pid==parent) return true;
		}
		
		var parent=$form(formId,"input[name='parent']").val();
		var result=search({name:value,parent:parent});
		if (result==null) {//查询失败
			return false;
		}
		
		return result.length==0;
	}
	function $form(formId,ele){
		return $("#"+formId+" "+ele);
	}
	function treeOperateSuccess(){
		$.fn.zTree.getZTreeObj("tree-list").reAsyncChildNodes(null,"refresh");
		$.fn.zTree.getZTreeObj("form-add-tree").reAsyncChildNodes(null,"refresh");
		$.fn.zTree.getZTreeObj("form-update-tree").reAsyncChildNodes(null,"refresh");
	}
	
	
	
	
	
	
	
	$.fn.zTree.init($("#tree-list"),treeListSetting);
	$.fn.zTree.init($("#form-add-tree"),treeSetting);
	$.fn.zTree.init($("#form-update-tree"),treeSetting);
	
	$("#form-add").formValidation({
		framework:"bootstrap",
		err:{
			container:"tooltip"
		},
		icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields:{
			name:{
				trigger:"keyup",
				validators:{
					notEmpty:{
						message:"请填写"
					},
					callback:{
						message:"同级目录下名称不可重复！",
						callback:function(value, validator, $field){
							return nameRepeatCheck(value, validator, $field,"form-add");
						}
					}
				}
			},
			sort:{
				validators:{
					integer:{
						message:"必须为整数"
					}
				}
			}
		}
	})
	.on("success.form.fv",function(e){
		e.preventDefault();
		
		var alertSuccess="#alert-add-success";
		var alertFailed="#alert-add-failed";
		$.ajax({
			url:REQUEST_URL_ADD,
			data:$(e.target).serialize(),
			type:"POST",
			async:false,
			dataType:"json",
			error:function(e){
				bAlert($(alertFailed), 1000, function(){
					location.reload();
				});
			},
			success:function(result){
				if (result>0) {
					bAlert($(alertSuccess), 1000, function(){
						$("#modal-new-panel").modal("hide");
						treeOperateSuccess();
					});
				}else{
					bAlert($(alertFailed), 1000, function(){
						location.reload();
					});
				}
			}
		});
	});
	$("#form-update").formValidation({
		framework:"bootstrap",
		err:{
			container:"tooltip"
		},
		icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields:{
			name:{
				trigger:"keyup",
				validators:{
					notEmpty:{
						message:"请填写"
					},
					callback:{
						message:"同级目录下名称不可重复！",
						callback:function(value, validator, $field){
							return nameRepeatCheck(value, validator, $field,"form-update");
						}
					}
				}
			},
			sort:{
				validators:{
					integer:{
						message:"必须为整数"
					}
				}
			},
			parentName:{
				trigger:"blur",
				validators:{
					callback:{
						message:"父级不能选择自己的自己或自己的子级！",
						callback:function(value, validator, $field){
							var tree=$.fn.zTree.getZTreeObj("form-update-tree");
							var id=$form("form-update","input[name='id']").val();
							var navigation=tree.getNodeByParam("id",id).navigation;
							return $form("form-update","input[name='navigation']").val().indexOf(navigation+".")==-1;
						}
					}
				}
			}
		}
	})
	.on("success.form.fv",function(e){
		e.preventDefault();
		var alertSuccess="#alert-update-success";
		var alertFailed="#alert-update-failed";
		$.ajax({
			url:REQUEST_URL_UPDATE,
			data:$(e.target).serialize(),
			type:"POST",
			async:false,
			dataType:"json",
			error:function(e){
				bAlert($(alertFailed), 1000, function(){
					location.reload();
				});
			},
			success:function(result){
				if (result>0) {
					bAlert($(alertSuccess), 1000, function(){
						$("#modal-edit-panel").modal("hide");
						treeOperateSuccess();
					});
				}else{
					bAlert($(alertFailed), 1000, function(){
						location.reload();
					});
				}
			}
		});
	});
	
	$(document)
    .on('click.bs.dropdown.data-api', '.dropdown .ztree', function (e) { e.stopPropagation(); })
    .on('click.bs.dropdown.data-api', "input[name='parentName']", function (e) { e.stopPropagation(); });
	
	$("#opt-edit").on("click",function(param){
		var treeList=$.fn.zTree.getZTreeObj("tree-list");
		var selectedNode=null;
		selectedNode=treeList.getNodeByParam("id",param?param.id:"");
		if (!selectedNode) {
			var selectedNodes=treeList.getSelectedNodes();
			if (selectedNodes!=null&&selectedNodes.length>0) {
				selectedNode=selectedNodes[0];
			}
		}
		
		if (selectedNode) {
			$("#modal-edit-panel").off("shown.bs.modal").on("shown.bs.modal",function(){
				$("#form-update").formValidation("resetForm",true);
				var classfication=treeNodeToClassfication(selectedNode);
				loadClassfication("form-update",classfication);
			}).modal("show");
		}else{
			bAlert($("#alert-tree-list-unselected"), 2000);
		}
	});
	$("#opt-new").on("click",function(){
		$("#modal-new-panel").off("shown.bs.modal").on("shown.bs.modal",function(){
			$("#form-add").formValidation("resetForm",true);
		}).modal("show");
	});
	
	$("#tree-list").on("click",".tree-node-opt-new",function(){
		$("#opt-new").trigger("click");
		var id=$(this).attr("value");
		clearClassfication("form-add",id);
	});
	$("#tree-list").on("click",".tree-node-opt-edit",function(){
		var id=$(this).attr("value");
		$("#opt-edit").trigger("click",{id:id});
	});
	$("#tree-list").on("click",".tree-node-opt-delete",function(){
		var id=$(this).attr("value");
		var treeList=$.fn.zTree.getZTreeObj("tree-list");
		var navigation=treeList.getNodeByParam("id",id).navigation;
		classficationDelete(navigation);
	});
	$("#tree-list").on("click",".icon,.text",function(){
		var treeList=$.fn.zTree.getZTreeObj("tree-list");
		var id=$(this).attr("value");
		var treeNode=treeList.getNodeByParam("id",id);
		if (treeNode.isParent) {
			if(treeNode.open){
				treeList.expandNode(treeNode,false);
				treeListNodeCollapse(null,null,treeNode);
			}else{
				treeList.expandNode(treeNode,true);
				treeListNodeExpand(null,null,treeNode);
			}
		}
	});
	
	
	$("#form-add input[name='parentName']").on("click",function(){
		$form("form-add",".dropdown-menu-parent").trigger("click");
	});
	$("#form-update input[name='parentName']").on("click",function(){
		$form("form-update",".dropdown-menu-parent").trigger("click");
	});
	
});