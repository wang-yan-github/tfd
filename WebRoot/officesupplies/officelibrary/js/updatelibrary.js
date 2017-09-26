function updatelibrary(){
	var para=$("#libraryform").serialize();
	var url=contextPath+"/officesupplies/act/OfflibraryAct/updatelibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:para,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('修改成功');
				history.back();
			}else{
			}
	}
	});
}
function getIdlibrary(){
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
			$("#libraryId").val(data.libraryId);
			$("#libraryName").val(data.libraryName);
			$("#deptName").val(data.deptName);
			$("#belongDept").val(data.belongDept);
			$("#staffName").val(data.userName);
			$("#libraryStaff").val(data.libraryStaff);
			$("#dispatchName").val(data.dispatchName);
			$("#dispatchStaff").val(data.dispatchStaff);
	}
	});
}
$(function (){
	getIdlibrary();
});
function deptNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'deptName');
}
function staffNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'staffName');
}
function dispatchNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'dispatchName');
}
