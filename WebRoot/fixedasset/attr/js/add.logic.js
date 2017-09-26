$(function(){
	var REQUEST_URL_LOAD_ASSET_IMAGE=contextPath+"/fixedasset/act/FixedassetAct/loadAssetImage.act";
	var REQUEST_URL_TYPE_TREE=contextPath+"/fixedasset/act/FixedassetTypeAct/typeTree.act";
	
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
				onClick:assetTypeTreeNodeClick
			}
		};
	
	
	
	function assetTypeTreeNodeClick(event,treeId,treeNode){
		var typeTree=$.fn.zTree.getZTreeObj("asset-type-tree");
		$("#fixedassetRelation_assetType").combo("setValue",treeNode.id).combo("setText",treeNode.text).combo("hidePanel");
	}
	function loadAssetImage(){
		if (attachId!="") {
			var attachIdStrs=attachId.split(",");
			var attachIds=[];
			for (var i = 0; i < attachIdStrs.length; i++) {
				if (attachIdStrs[i]!=""&&$(".asset-a-image[value='"+attachIdStrs[i]+"']").length==0) {
					attachIds.push(attachIdStrs[i]);
				}
			}
			if (attachIds.length==0) {
				return ;
			}
			var tcount=3;
			var rcount=attachIdStrs.length%tcount>0?parseInt(attachIdStrs.length/tcount+"")+1:parseInt(attachIdStrs.length/tcount+"");
			//行
			var assetImageHtml="";
			for (var i = 0; i < rcount-$('.asset-image').length; i++) {
				assetImageHtml+="<div class='asset-image'></div>";
			}
			$(".asset-image-c").append(assetImageHtml);
			//列
			for (var i=0,attachIdI = 0; i < rcount; i++) {
				for (var j = 0; j < tcount-$(".asset-image:eq("+i+") .asset-a-image").length&&attachIdI<attachIds.length; j++,attachIdI++) {
					var assetAImageHtml="<div class='asset-a-image' value='"+attachIds[attachIdI]+"'>";
					assetAImageHtml+="		<div class='asset-a-image-delete' title='删除' value='"+attachIds[attachIdI]+"'>×</div>";
					assetAImageHtml+="		<div class='asset-a-image-screen'></div>";
					assetAImageHtml+="		<img src='"+REQUEST_URL_LOAD_ASSET_IMAGE+"?attachId="+attachIds[attachIdI]+"'/>";
					assetAImageHtml+="	</div>";
					$(".asset-image:eq("+i+")").append(assetAImageHtml);
					
				}
			}
			
			$(".asset-a-image-delete").unbind("click").click(function(){
				delAssetImage($(this).attr("value"));
			});
			$(".asset-a-image-screen").unbind("mouseover").mouseover(function(){
				$(this).animate({opacity:"0"},300);
			});
			$(".asset-a-image-screen").unbind("mouseout").mouseout(function(){
				$(this).animate({opacity:"0.2"},300);
			});
		}
	}
	function delAssetImage(attachId){
		delAttach(attachId);
		$(".asset-a-image[value='"+attachId+"']").remove();
	}
	
	function initPageData(){
		filesUpLoad("fixedasset");
		window.setInterval(function(){
			$("input[name='fixedassetAttr_image']").val(attachId);
			loadAssetImage();
		},500);
		$.fn.zTree.init($("#asset-type-tree"),assetTypeTreeSetting);
	}


	function initPageOpt(){
		$("#fixedassetRelation_assetType").combo("panel").append($("#asset-type-tree"));
		$("#opt-add").click(function(){
			$("#asset-form").get(0).submit();
		});
		
	}
	
	
	
	
	initPageData();
	initPageOpt();
});