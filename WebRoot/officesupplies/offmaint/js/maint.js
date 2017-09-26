$(function(){
	getlibrary();
	});
function getlibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/getlibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
				$("#libraryId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
	}
	});
}
function getlibclassify(){
	var libraryId=$("#libraryId").val();
	var url=contextPath+"/officesupplies/act/OfflibraryAct/gettopIdNameAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{topId:libraryId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#classifyId").empty();
			$("#classifyId").append("<option selected=\"selected\" value=\"\">请选择</option>");
			$("#resId").empty();
			$("#resId").append("<option selected=\"selected\" value=\"\">请选择</option>");
			if(data!=""){
			for(var i=0;i<data.length;i++){
				
				$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
			}
	}
	});
}
function getresName(){
	var classifyId=$("#classifyId").val();
	var url=contextPath+"/officesupplies/act/OffresAct/getresNameAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{classifyId:classifyId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#resId").empty();
			$("#resId").append("<option selected=\"selected\" value=\"\">请选择</option>");
			if(data!=""){
			for(var i=0;i<data.length;i++){
				$("#resId").append("<option value=\""+data[i].resId+"\"> "+data[i].resName+"/库存"+data[i].beforeStock+"</option>");
			}
			}
	}
	});
}
function addmaint(){
	var url=contextPath+"/officesupplies/act/OffmaintAct/addmaintAct.act";
	var para=$("#maintform").serialize();
	$.ajax({
		url:url,
		type:"POST",
		data:para,
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				var maintType=$("#maintType").val();
				var resId=$("#resId").val();
				var maintNum=$("#maintNum").val();
				if(maintType=="采购入库"){
				var url=contextPath+"/officesupplies/act/OffresAct/updateresNumAct.act";
				$.ajax({
					url:url,
					type:"POST",
					data:{resId:resId,maintNum:maintNum},
					dataType:"json",
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data!=0){
							parent.layer.msg('添加成功');
							window.location.reload();
						}else{
						}
				}
				});
				}else{
					var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
					$.ajax({
						url:url,
						type:"POST",
						data:{resId:resId,maintNum:maintNum},
						dataType:"json",
						async:false,
						error:function(e){
						},
						success:function(data){
							if(data!=0){
								parent.layer.msg('添加成功');
								window.location.reload();
							}else{
							}
					}
					});
				}
			}else{
			}
	}
	});
	}