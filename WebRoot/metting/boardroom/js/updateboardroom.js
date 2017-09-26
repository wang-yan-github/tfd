var bstaff="";
function updateboardroom(){
	var allowTime="";
	for(var i=0; i<7;i++){
		if($("#time"+i).prop("checked")==true){
			allowTime+=$("#time"+i).val()+",";
		}
	}
	var para=$('#boardroomform').serialize();
	var boardroomSystem=CKEDITOR.instances.editor.getData();
	para+="&allowTime="+allowTime+"&boardroomId="+boardroomId+"&boardroomSystem="+boardroomSystem;
	var url=contextPath+"/meeting/act/BoardroomAct/updateboardroomAct.act";
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
				if(bstaff!=$("#boardroomStaff").val()){
	var url=contextPath+"/meeting/act/MeetingrequestAct/getlibIdupdateAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{boardroomId:boardroomId,
				boardroomStaff:$("#boardroomStaff").val()
			},
		async:false,
		error:function(e){
		},
		success:function(data){
			parent.layer.msg('修改成功！');
			history.back();
	}
	});
	}else{
		parent.layer.msg('修改成功！');
		history.back();
	}
			}
	}
	});
}
function getIdboardroom(){
 		var url=contextPath+"/meeting/act/BoardroomAct/getIdboardroomAct.act";
 		$.ajax({
 			url:url,
 			type:"POST",
 			dataType:"json",
 			data:{boardroomId:boardroomId},
 			async:false,
 			error:function(e){
 			},
 			success:function(data){
 				fromdata=data;
 				bstaff=data.boardroomStaff;
 				for(var name in fromdata){
 					if(name=="allowTime"){
 						var ids=fromdata[name].split(",");
 						var idsArray=["日","一","二","三","四","五","六"];
 						for(var i=0;i<ids.length;i++){
 							for(var j=0;j<idsArray.length;j++){
 								if(idsArray[j]==ids[i]){
 									$("#time"+j).prop("checked",true);
 								}
 							}
 						}
 					}else{
 						$("#"+name).val(fromdata[name]);
 					}
 					if(name=="boardroomSystem"&&fromdata[name]!=null){
 					   var editorElement = CKEDITOR.document.getById('editor');
	   					editorElement.setHtml(fromdata[name]);
 					}
 				}
 		}
 		});
}
$(function (){
	getIdboardroom();
});
function deptNameval(){
	$('#boardroomform').bootstrapValidator('revalidateField', 'deptName');
	$('#boardroomform').bootstrapValidator('revalidateField', 'userName');
}
function boardroomuserNameval(){
	$('#boardroomform').bootstrapValidator('revalidateField', 'boardroomuserName');
}