function addlibrary(){
	var para=$("#libraryform").serialize();
	var url=contextPath+"/officesupplies/act/OfflibraryAct/addlibraryAct.act";
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
				parent.layer.msg('添加成功');
				history.back();
			}else{
			}
	}
	});
}
function deptNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'deptName');
}
function staffNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'staffName');
}
function dispatchNameval(){
	$('#libraryform').bootstrapValidator('revalidateField', 'dispatchName');
}
