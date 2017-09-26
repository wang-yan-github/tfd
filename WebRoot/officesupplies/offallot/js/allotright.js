var resFormat;
var resType;
var resUnit;
var resPrice;
var attachId;
var resSupplier;
var approveStaff;
var minStock;
var userPriv;
var maxStock;
var dispatchStaff;
var deptPriv;
var resName;
var beforedispatchStaff;
$(function(){
	getlibrary();
	getdislibrary();
	});
function getlibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/getlibraryNameAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
				$("#beforelibraryId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
	}
	});
}
function getdislibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/getdislibraryAct.act";
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
function getlibclassify(id){
	var libraryId;
	if(id==1){
		libraryId=$("#beforelibraryId").val();
	}else{
		var libraryId=$("#libraryId").val();	
	}
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
			if(id==1){
				$("#beforeclassifyId").empty();
				$("#beforeclassifyId").append("<option selected=\"selected\">请选择</option>");
				$("#beforeresId").empty();
				$("#beforeresId").append("<option selected=\"selected\">请选择</option>");
				if(data!=""){
					beforedispatchStaff=data[0].dispatchStaff;
				for(var i=0;i<data.length;i++){
					$("#beforeclassifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
				}
				}
			}else{
			$("#classifyId").empty();
			$("#classifyId").append("<option selected=\"selected\">请选择</option>");
			if(data!=""){
				approveStaff=data[0].libraryStaff;
				dispatchStaff=data[0].dispatchStaff;
			for(var i=0;i<data.length;i++){
				$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
			}
			}
	}
	});
}
function getresName(){
	var classifyId=$("#beforeclassifyId").val();
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
			$("#resId").append("<option selected=\"selected\">请选择</option>");
			if(data!=""){
			for(var i=0;i<data.length;i++){
				$("#resId").append("<option value=\""+data[i].resId+"\"> "+data[i].resName+"/库存"+data[i].beforeStock+"</option>");
			}
			}
	}
	});
}
function getresId(){
	var resId=$("#resId").val();
	var url=contextPath+"/officesupplies/act/OffresAct/getIdresAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{resId:resId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
	   		 resFormat=data.resFormat;
	   		resType=data.resType;
	   		resUnit=data.resUnit;
	   		resPrice=data.resPrice;
	   		attachId=data.attachId;
	   		resSupplier=data.resSupplier;
	   		minStock=data.minStock;
	   		userPriv=data.userPriv;
	   		maxStock=data.maxStock;
	   		deptPriv=data.deptPriv;
	   		resName=data.resName;
	}
	});
}
function addallot(){
	if(beforedispatchStaff ==userId){
		var url=contextPath+"/officesupplies/act/OffallotAct/addallotAct.act";
		var allotStatus=1;
		$.ajax({
			url:url,
			type:"POST",
			data:{
				resId:$("#resId").val(),
				allotStatus:allotStatus,
				approvalStaff:beforedispatchStaff,
		   		allotNum:$("#beforeStock").val(),
		   		libraryId:$("#libraryId").val(),
		   		classifyId:$("#classifyId").val()
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					var url=contextPath+"/officesupplies/act/OffresAct/addresAct.act";
					$.ajax({
						url:url,
						type:"POST",
						data:{
							resFormat:resFormat,
							resType:resType,
							resUnit:resUnit,
							resPrice:resPrice,
							attachId:attachId,
							resSupplier:resSupplier,
							minStock:minStock,
							maxStock:maxStock,
							userPriv:userPriv,
							deptPriv:deptPriv,
							resName:resName,
							approveStaff:approveStaff,
					   		dispatchStaff:dispatchStaff,
					   		beforeStock:$("#beforeStock").val(),
					   		libraryId:$("#libraryId").val(),
					   		classifyId:$("#classifyId").val()
						},
						dataType:"json",
						async:false,
						error:function(e){
						},
						success:function(data){
							if(data!=0){
								getredNum();
							}
					}
					});
				}
		}
		});
	}
	else{
		var url=contextPath+"/officesupplies/act/OffallotAct/addallotAct.act";
		var allotStatus=0;
		$.ajax({
			url:url,
			type:"POST",
			data:{
				resId:$("#resId").val(),
				allotStatus:allotStatus,
				approvalStaff:beforedispatchStaff,
		   		allotNum:$("#beforeStock").val(),
		   		libraryId:$("#libraryId").val(),
		   		classifyId:$("#classifyId").val(),
		   		deptoStaff:approveStaff
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					parent.layer.msg('已发送审批');
					window.location.reload();
				}
		}
		});
	}
	
}
function getredNum(){
	var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{resId:$("#resId").val(),maintNum:$("#beforeStock").val()},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('添加成功！');
				parent.document.getElementById("left").src= "/tfd/officesupplies/offallot/allotleft.jsp";
				window.location.reload();
			}
	}
	});
}
