function getboardroomName(){
	var url=contextPath+"/meeting/act/BoardroomAct/selectboardroomAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			var boardroomName="<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				 boardroomName+="<option value='"+data[i].boardroomId+"'>"+data[i].boardroomName+"</option>";
			}
			$("#boardroomId").append(boardroomName);
	}
	});
}
$(function (){
	getboardroomName();
});
function Newboardroomdevice(){
	var url=contextPath+"/meeting/act/BoardroomdeviceAct/adddeviceAct.act";
	$("#deviceDescription").val(CKEDITOR.instances.editor.getData());
	var para=$("#boardroomdevicefrom").serialize();
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:para,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data=='1'){
				parent.layer.msg('添加设备成功！');
				history.back();
			}
	}
	});
}