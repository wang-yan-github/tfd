$(function(){
	getlibrary();
	});
function getresId(){
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
			$("#libraryId").val(data.libraryId);
			getlibclassify();
			$("#classifyId").val(data.classifyId);
			getresName();
			$("#resId").val(data.resId);
			$("#resType").val(data.resType);
			$("#maxnum").val(data.beforeStock);
			$("#applyNum").prop("readonly","");
			$("#btnminus").prop("disabled","");
			$("#btnplus").prop("disabled","");
		}
	});
}
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
			getresId();
	}
	});
}

function getlibclassify(){
	var libraryId=$("#libraryId").val();
	if(libraryId!=""){
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
			$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
			$("#resId").empty();
			$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
			if(data!=""){
			for(var i=0;i<data.length;i++){
				$("#dispatchStaff").val(data[i].dispatchStaff);
				$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
			}
	}
	});
	$("#applyNum").val("");
	}else{
			$("#applyNum").val("");
			$("#classifyId").empty();
			$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
			$("#resId").empty();
			$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
	}
	$("#applyNum").prop("readonly","readonly");	
	$("#btnminus").prop("disabled","disabled");
	$("#btnplus").prop("disabled","disabled");
}
function getresName(){
	var classifyId=$("#classifyId").val();
	if(classifyId!=""){
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
			$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
			if(data!=""){
			for(var i=0;i<data.length;i++){
				$("#resId").append("<option value=\""+data[i].resId+"\"> "+data[i].resName+"/库存"+data[i].beforeStock+"</option>");
			}
			}
	}
	});
	$("#applyNum").val("");
	}else{
		$("#applyNum").val("");
		$("#resId").empty();
		$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
	}
	$("#applyNum").prop("readonly","readonly");	
	$("#btnminus").prop("disabled","disabled");
	$("#btnplus").prop("disabled","disabled");
}
function addresapply(){
	var approvalStaff=$("#approvalStaff").val();
	if(approvalStaff==""){
		var libraryId=$("#libraryId").val();
		var url=contextPath+"/officesupplies/act/OfflibraryAct/getIdlibraryAct.act";
		$.ajax({
			url:url,
			type:"POST",
			dataType:"json",
			data:{libraryId:libraryId},
			async:false,
			error:function(e){
			},
			success:function(data){
				$("#approvalStaff").val(data.libraryStaff);
		}
		});
	}
	var para=$("#resapplyform").serialize();
	var url=contextPath+"/officesupplies/act/OffresapplyAct/addapplyAct.act";
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
				parent.layer.msg('申请成功');
				window.location.reload();
			}
			else{
			}
			}
	});
}
function gettype(){
	var resId=$("#resId").val();
	if(resId!=""){
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
			$("#resType").val(data.resType);
			$("#maxnum").val(data.beforeStock);
	}
	});
	$("#applyNum").val("");
	$("#applyNum").prop("readonly","");
	$("#btnminus").prop("disabled","");
	$("#btnplus").prop("disabled","");
	$('#resapplyform').bootstrapValidator('revalidateField', 'applyNum');
	}else{
	$("#cue").html("");
	$("#maxnum").val("");
	$("#resType").val("");
	$("#applyNum").val("");
	$("#applyNum").prop("readonly","readonly");	
	$("#btnminus").prop("disabled","disabled");
	$("#btnplus").prop("disabled","disabled");
	$('#resapplyform').bootstrapValidator('revalidateField', 'applyNum');
	}
}
function blurjudge(){
	if(parseInt($("#applyNum").val())>=parseInt($("#maxnum").val())){
		$("#applyNum").val($("#maxnum").val());
		$("#cue").html("&nbsp;&nbsp;最大值为"+$("#maxnum").val());
	}else{
		$("#cue").html("");
	}
	if(parseInt($("#applyNum").val())<=0){
		$("#applyNum").val(1);
	}
}
function btnminusfun(){
	if(parseInt($("#applyNum").val())>0){
		var num=parseInt($("#applyNum").val())-1;
		$("#applyNum").val(num);
	}
}
function btnplusfun(){
	if(parseInt($("#applyNum").val())<parseInt($("#maxnum").val())){
		var num=parseInt($("#applyNum").val())+1;
		$("#applyNum").val(num);
	}else{
		$("#cue").html("&nbsp;&nbsp;最大值为"+$("#maxnum").val());
	}
}