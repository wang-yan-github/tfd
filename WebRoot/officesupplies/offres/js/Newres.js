$(function(){
	filesUpLoad("office");
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
			$('#offresform').bootstrapValidator('revalidateField', 'classifyId');
			if(data!=""){
			$("#approveStaff").val(data[0].libraryStaff);
			$("#userName").val(data[0].userName);
			$("#disName").val(data[0].disName);
			$("#dispatchStaff").val(data[0].dispatchStaff);
			for(var i=0;i<data.length;i++){
				$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
			}
	}
	});
	}else{
			$("#classifyId").empty();
			$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
			$('#offresform').bootstrapValidator('revalidateField', 'classifyId');
	}
}
function addres(){
	var url=contextPath+"/officesupplies/act/OffresAct/addresAct.act";
	var prar=$("#offresform").serialize();
	prar+="&attachId="+$("#attachId").val();
	$.ajax({
		url:url,
		type:"POST",
		data:prar,
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('添加成功');
				parent.document.getElementById("left").src= "/tfd/officesupplies/offres/left.jsp";
				window.location.reload();
			}else{
			}
	}
	});
}
function deptNameval(){
	$('#offresform').bootstrapValidator('revalidateField', 'deptName');
}
