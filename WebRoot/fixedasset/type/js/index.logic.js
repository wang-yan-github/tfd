$(function(){
	var REQUEST_URL_TYPE_TREE=contextPath+"/fixedasset/act/FixedassetTypeAct/typeTree.act";
	var REQUEST_URL_TYPE_UPDATE=contextPath+"/fixedasset/act/FixedassetTypeAct/update.act";
	var REQUEST_URL_TYPE_DELETE=contextPath+"/fixedasset/act/FixedassetTypeAct/delete.act";
	var REQUEST_URL_TYPE_EXPORT=contextPath+"/fixedasset/act/FixedassetTypeAct/typeExport.act";
	var PAGE_URL_TYPE_IMPORT=contextPath+"/fixedasset/type/import-index.jsp";
	
	var treeSetting={
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPId:0
			},
			key:{
				name:"text"
			}
		},
		check:{
			enable:true,
			chkStyle:"checkbox"
			
		},
		async:{
			enable:true,
			url:REQUEST_URL_TYPE_TREE,
			autoParam:["id=parentId"],
			otherParam:{"type":"parentTree"}
		},
		callback:{
			onNodeCreated:treeNodeLoaded,
			onClick:treeNodeClick,
			onExpand:treeNodeExpand,
			onDbClick:treeNodeDbClick
		}
		
	};
	
	var parentTreeSetting={
		data:treeSetting.data,
		async:{
			enable:treeSetting.async.enable,
			url:treeSetting.async.url,
			autoParam:treeSetting.async.autoParam,
			otherParam:{"type":"parentTree"}
		},
		callback:{
			onClick:parentTreeNodeClick,
			onDbClick:parentTreeNodeDbClick,
			onNodeCreated:parentTreeNodeLoaded,
			onExpand:parentTreeNodeExpand
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	function treeNodeAsyncAuto(treeNode,treeObj){
		var nodeTemp=treeObj.getNodeByParam("id",treeNode.id);
		var nodeTempChildren=treeObj.getNodesByParam("pid",treeNode.id);
		if (nodeTempChildren==null||nodeTempChildren.length==0) {
			treeObj.reAsyncChildNodes(nodeTemp);
		}
		return nodeTemp;
	}
	

	function treeNodeLoaded(event,treeId,treeNode){
		if (treeNode.id==treeSetting.data.simpleData.rootPId+"") {
			var tree=$.fn.zTree.getZTreeObj("type-tree");
			var topNode=tree.getNodeByParam("id",treeSetting.data.simpleData.rootPId+"");
			tree.expandNode(topNode,true);
		}
	}
	function treeNodeDbClick(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("parent-tree");
		treeNodeAsyncAuto(treeNode,treeObj);
	}
	function treeNodeExpand(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("parent-tree");
		treeNodeAsyncAuto(treeNode,treeObj);
	}
	function treeNodeClick(event,treeId,treeNode){
		if (treeNode.id==treeSetting.data.simpleData.rootPId+"") {
			openAddView();
			return ;
		}
		
		var parentTree=$.fn.zTree.getZTreeObj("parent-tree");
		
		$("#opt-add-view").linkbutton("unselect");
		
		$("#opt-add-c").hide();
		$("#opt-edit-c").show();
		
		
		$("input[name='seqId']").val(treeNode.id);
		$("#typeName").textbox("setValue",treeNode.text);
		
		var parentNode=treeNode.getParentNode();
		var nodeTemp=null;
		if (parentNode==null) {
			nodeTemp=parentTree.getNodeByParam("id",parentTreeSetting.data.simpleData.rootPId+"");
		}else{
			nodeTemp=parentTree.getNodeByParam("id",parentNode.id);
		}
		setParentByTreeNode(nodeTemp);
		
		$("#unit").textbox("setValue",treeNode.unit);
		$("#netSalvage").numberbox("setValue",treeNode.netSalvage);
		
		
		parentTree.selectNode(parentTree.getNodeByParam("id",treeNode.pid),false);
	}
	
	
	

	function parentTreeNodeDbClick(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("type-tree");
		treeNodeAsyncAuto(treeNode,treeObj);
	}
	function parentTreeNodeExpand(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("type-tree");
		treeNodeAsyncAuto(treeNode,treeObj);
	}
	function parentTreeNodeLoaded(event,treeId,treeNode){
		if (treeNode.id==treeSetting.data.simpleData.rootPId+"") {
			var tree=$.fn.zTree.getZTreeObj("parent-tree");
			var topNode=tree.getNodeByParam("id",treeSetting.data.simpleData.rootPId+"");
			setParentByTreeNode(topNode);
			tree.expandNode(topNode,true);
		}
	}
	function setParentByTreeNode(treeNode){
		$("#parentId").combo("setValue",treeNode.id).combo("setText",treeNode.text).combo("hidePanel");
		$("input[name='levelId']").val(treeNode.levelId);
		var tree=$.fn.zTree.getZTreeObj("parent-tree");
		tree.selectNode(treeNode,false);
	}
	function parentTreeNodeClick(event,treeId,treeNode){
		var typeTree=$.fn.zTree.getZTreeObj("type-tree");
		
		var seqId=$("input[name='seqId']").val();
		if (seqId=="") {
			setParentByTreeNode(treeNode);
			
			typeTree.selectNode(typeTree.getNodeByParam("id",treeNode.id),false);
		}else{
			var nodeTemp=typeTree.getNodeByParam("id",seqId);
			var levelId=nodeTemp.levelId;
			
			var parentLevelId=treeNode.levelId;
			if (parentLevelId==levelId||parentLevelId.indexOf(levelId)>-1) {
				$("#parentId").combo("hidePanel");
				$.messager.alert({
					title:"提示",
					msg:"父级不能选自己或子级！"
				});
			}else{
				setParentByTreeNode(treeNode);
				
				typeTree.selectNode(typeTree.getNodeByParam("id",treeNode.id),false);
			}
			
		}
		
	}
	
	
	function loadTypeTree(){
		$.fn.zTree.init($("#type-tree"),treeSetting);
	}
	function loadParentTree(){
		$.fn.zTree.init($("#parent-tree"),parentTreeSetting);
	}
	
	function submit(){
		var typeNameValid=$("#typeName").validatebox("isValid");
		if (typeNameValid) {
			$.ajax({
				url:REQUEST_URL_TYPE_UPDATE,
				data:$("#asset-type-form").serialize(),
				type:"POST",
				asnyc:true,
				dataType:"text",
				success:function(data){
					if (data=="true") {
						$.messager.alert({
							title:"提示",
							msg:"保存成功！",
							icon:"info",
							fn:function(){
								
								var seqId=$("input[name='seqId']").val();
								if (seqId=="") {
									openAddView();
								}
								
								var pid=$("#parentId").combo("getValue");
								
								var typeTree=$.fn.zTree.getZTreeObj("type-tree");
								var parentTree=$.fn.zTree.getZTreeObj("parent-tree");
								if (pid==treeSetting.data.simpleData.rootPId+"") {
									typeTree.reAsyncChildNodes(null,"refresh");
									parentTree.reAsyncChildNodes(null,"refresh");
								}else{
									var nodeTemp=typeTree.getNodeByParam("id",pid);
									nodeTemp.isParent=true;
									typeTree.reAsyncChildNodes(nodeTemp,"refresh");
									
									nodeTemp=parentTree.getNodeByParam("id",pid);
									nodeTemp.isParent=true;
									parentTree.reAsyncChildNodes(nodeTemp,"refresh");
								}
								
							}
						});
					}else{
						$.messager.alert({
							title:"提示",
							msg:"操作失败！",
							icon:"error",
							fn:function(){
								location.reload();
							}
						});
					}
				}
			});
		}else{
			$("#typeName").textbox("textbox").focus();
		}
	}
	function openAddView(){
		$("#opt-add-view").linkbutton("select");
		
		$("#opt-add-c").show();
		$("#opt-edit-c").hide();
		
		$("input[name='seqId']").val("");
		$("#typeName").textbox("setValue","");

		var tree=$.fn.zTree.getZTreeObj("type-tree");
		var parentTree=$.fn.zTree.getZTreeObj("parent-tree");
		
		var selectedNodes=tree.getSelectedNodes();
		if (selectedNodes!=null&&selectedNodes.length>0) {
			var node=parentTree.getNodeByParam("id",selectedNodes[0].id);
			setParentByTreeNode(node);
		}else{
			var topNode=parentTree.getNodeByParam("id",treeSetting.data.simpleData.rootPId+"");
			setParentByTreeNode(topNode);
		}
		
		$("#unit").textbox("setValue","");
		$("#netSalvage").numberbox("reset");
		

		$("#typeName").textbox("textbox").focus();
	}
	
	function typeDelete(levelId){
		$.ajax({
			url:REQUEST_URL_TYPE_DELETE,
			data:"levelId="+levelId,
			type:"POST",
			async:true,
			dataType:"text",
			success:function(data){
				if (data=="true") {
					$.messager.alert({
						title:"提示",
						msg:"删除成功！",
						icon:"info",
						fn:function(){
							openAddView();
							
							var typeTree=$.fn.zTree.getZTreeObj("type-tree");
							var parentTree=$.fn.zTree.getZTreeObj("parent-tree");
							
							typeTree.reAsyncChildNodes(null,"refresh");
							parentTree.reAsyncChildNodes(null,"refresh");
							
						}
					});
				}else{
					$.messager.alert({
						title:"提示",
						msg:"操作失败！",
						icon:"error",
						fn:function(){
							location.reload();
						}
					});
				}
			}
		});
	}
	
	function typeDeleteConfirm(){
		var seqId=$("input[name='seqId']").val();
		var typeTree=$.fn.zTree.getZTreeObj("type-tree");
		var nodeTemp=typeTree.getNodeByParam("id",seqId);
		var msg="确定删除吗？";
		if (nodeTemp.isParent) {
			msg="该类别下还有子级，您的操作将删除该类别及其子级，确定继续删除吗？";
		}
		$.messager.confirm({
			title:"提示",
			msg:msg,
			fn:function(data){
				if (data) {
					var levelId=$("input[name='levelId']").val();
					levelId+="."+seqId;
					typeDelete(levelId);
				}
			}
		});
	}
	function typeBatchDeleteConfirm(){
		var typeTree=$.fn.zTree.getZTreeObj("type-tree");
		var checkedNodes=typeTree.getCheckedNodes();
		if (checkedNodes==null||checkedNodes.length==0) {
			$.messager.alert({
				title:"提示",
				msg:"请选择需要删除的类别！",
				icon:"info"
			});
		}else{
			$.messager.confirm({
				title:"提示",
				msg:"选择的类别中，如果还有子级，子级也会被删除，确认删除选中吗？",
				fn:function(data){
					if (data) {
						var levelIds=[];
						for (var i = 0; i < checkedNodes.length; i++) {
							levelIds.push(checkedNodes[i].levelId);
						}
						typeDelete(levelIds.join(","));
					}
				}
			});
		}
	}
	function typeExport(){
		location.href=REQUEST_URL_TYPE_EXPORT;
	}
	
	
	
	
	
	
	function initPageData(){
		loadTypeTree();
		loadParentTree();
		window.setTimeout(function(){
			$("#typeName").textbox("textbox").focus();
		},200);
		$("#opt-add-view").linkbutton("select");
	}
	function initPageOpt(){
		$("#parentId").combo("panel").append($("#parent-tree"));
		$("#opt-add").click(function(){
			submit();
		});
		$("#opt-add-view").click(function(){
			openAddView();
		});
		$("#opt-save").click(function(){
			submit();
		});
		$("#opt-delete").click(function(){
			typeDeleteConfirm();
		});
		$("#opt-batch-delete").click(function(){
			typeBatchDeleteConfirm();
		});
		$("#opt-batch-import").click(function(){
			location.href="import.jsp";
		});
		$("#opt-export").click(function(){
			typeExport();
		});
	}
	initPageData();
	initPageOpt();
});