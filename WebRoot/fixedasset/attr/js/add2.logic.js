$(function(){
	var REQUEST_URL_TYPE_TREE=contextPath+"/fixedasset/act/FixedassetTypeAct/typeTree.act";
	var REQUEST_URL_FIXEDASSET_ADD=contextPath+"/fixedasset/act/FixedassetAct/add.act";
	var PAGE_URL_ASSET_LIST=contextPath+"/fixedasset/attr/list.jsp";
	
	
	var assetTypeTreeSetting={
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
		async:{
			enable:true,
			url:REQUEST_URL_TYPE_TREE,
			autoParam:["id=parentId"],
			otherParam:{"type":"parentTree"}
		},
		callback:{
			onNodeCreated:assetTypeTreeNodeLoaded,
			onClick:assetTypeTreeNodeClick
		}
	};
	
	
	function assetTypeTreeNodeLoaded(event,treeId,treeNode){
		if (treeNode.id==assetTypeTreeSetting.data.simpleData.rootPId+"") {
			var tree=$.fn.zTree.getZTreeObj("asset-type-tree");
			var topNode=tree.getNodeByParam("id",assetTypeTreeSetting.data.simpleData.rootPId+"");
			tree.expandNode(topNode,true);
		}
	}
	
	function assetTypeTreeNodeClick(event,treeId,treeNode){
		if(treeNode.id+""!="0"){
			$("#modal-asset-type").modal("hide");
			$("#FixedassetRelation_assetType").val(treeNode.id);
			$("#FixedassetRelation_assetTypeName").val(treeNode.text);
			$("#FixedassetRelation_unit").val(treeNode.unit);
			$("#FixedassetRelation_netSalvage").val(treeNode.netSalvage);
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
			$e.alert("close")
			.on("closed.bs.alert",callBack);
		},delay);
	}
	function initPageData(){
		$.fn.zTree.init($("#asset-type-tree"),assetTypeTreeSetting);
		$("#FixedassetRelation_postingDate").val(new Date().pattern("yyyy-MM-dd HH:mm:ss"));
		
		$('.form_date').datetimepicker({
			locale:"zh-cn",
			format:"YYYY-MM-DD HH:mm:ss"
	    });
	}

	function assetNoNotExist(){
        var assetNo=$("#FixedassetAttr_assetNo").val();
        var seqId=fixedasset_attr_logic.getSeqIdByAssetNo(assetNo);
        if (seqId.optResult) {
			if (seqId.data==null||seqId.data=="") {
				return true;
			}
		}
        return false;
	}
	function initPageOpt(){
		$("#FixedassetRelation_assetTypeName").focus(function(){
			$("#modal-asset-type").modal("show");
		});
		
		$("#asset-form").formValidation({
			framework:"bootstrap",
			fields:{
				FixedassetAttr_assetNo:{
					validators:{
						notEmpty:{
							message:'请填写！'
						},
						callback:{
							callback:assetNoNotExist,
							message:'该编号已存在！'
						}
					}
				},
				FixedassetAttr_unitPrice:{
					validators:{
						regexp:{
							message:"请填写大于0的数字！",
							regexp:/^[1-9]\d*(\.\d+)?$/
						}
					}
				},
				FixedassetRelation_number:{
					validators:{
						regexp:{
							message:"请填写大于0的数字",
							regexp:/^[1-9]\d*$/
						}
					}
				}
			}
		})
        .on('success.form.fv', function(e) {
            e.preventDefault();
           
            $.ajax({
				url:REQUEST_URL_FIXEDASSET_ADD,
				data:$(e.target).serialize(),
				type:"POST",
				dataType:"json",
				async:false,
				error:function(e){
					bAlert($("#alert-add-failed"), 1000, function(){
						location.reload();
					});
				},
				success:function(result){
					if (result) {
						bAlert($("#alert-add-success"), 1000, function(){
							location.href=PAGE_URL_ASSET_LIST;
						});
					}else{
						bAlert($("#alert-add-failed"), 1000, function(){
							location.reload();
						});
					}
				}
			});
        });
	}
	
	initPageData();
	initPageOpt();
});