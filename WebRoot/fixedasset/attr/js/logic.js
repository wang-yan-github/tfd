var fixedasset_attr_logic=new Object();
(function(){
	var REQUEST_URL_FIXEDASSET_ATTR_GET_SEQ_ID_BY_ASSET_NO=contextPath+"/fixedasset/act/FixedassetAttrAct/getSeqIdByAssetNo.act";
	fixedasset_attr_logic["getSeqIdByAssetNo"]=function(assetNo){
		var seqId=null;
		var param=new Object();
		param["assetNo"]=assetNo;
		$.ajax({
			url:REQUEST_URL_FIXEDASSET_ATTR_GET_SEQ_ID_BY_ASSET_NO,
			data:param,
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(result){
				seqId=result;
			}
		});
		return seqId;
	};
})();