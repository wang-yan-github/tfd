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
			for(var i=0;i<data.length;i++){
				var boardroomName="<option value='"+data[i].boardroomId+"'>"+data[i].boardroomName+"</option>";
				$("#boardroomId").append(boardroomName);
			}
	}
	});
}
$(function (){
	getboardroomName();
	getIddevice();
});
function updateboardroomdevice(){
	var url=contextPath+"/meeting/act/BoardroomdeviceAct/updatedeviceAct.act";
	$("#deviceDescription").val(CKEDITOR.instances.editor.getData());
	var para=$("#boardroomdevicefrom").serialize();
	para+="&boardroomdeviceId="+boardroomdeviceId;
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
				parent.layer.msg('修改设备成功！');
				history.back();
			}
	}
	});
}
function getIddevice(){
	var url=contextPath+"/meeting/act/BoardroomdeviceAct/getIddeviceAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{boardroomdeviceId:boardroomdeviceId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var formdata=data;
			for(var name in formdata){
				$("#"+name).val(formdata[name]);
				if(name=="deviceDescription"){
						 var editorElement = CKEDITOR.document.getById('editor');
	   					editorElement.setHtml(formdata[name]);
					}
			}
	}
	});
}