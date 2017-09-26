function addboardroom(){
	var allowTime="";
	for(var i=0; i<7;i++){
		if($("#time"+i).prop("checked")==true){
			allowTime+=$("#time"+i).val()+",";
		}
	}
	var para=$('#boardroomform').serialize();
	var boardroomSystem=CKEDITOR.instances.editor.getData();
	para+="&allowTime="+allowTime+"&boardroomSystem="+boardroomSystem;
	var url=contextPath+"/meeting/act/BoardroomAct/addboardroomAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:para,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				parent.layer.msg('保存成功！');
				history.back();
			}
	}
	});
}
function deptNameval(){
	$('#boardroomform').bootstrapValidator('revalidateField', 'deptName');
	$('#boardroomform').bootstrapValidator('revalidateField', 'userName');
}
function boardroomuserNameval(){
	$('#boardroomform').bootstrapValidator('revalidateField', 'boardroomuserName');
}
