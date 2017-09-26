function lookboardroom(){
	var url=contextPath+"/meeting/act/BoardroomAct/selectboardroomAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#boardroomtable").html("");
			for(var i=0;i<data.length;i++){
			var tr="<tr align=\"center\"><td>"+data[i].boardroomName+"</td><td>"+data[i].boardroomNum+"</td><td>"+data[i].userName+"</td><td>"+data[i].boardroomAddress+"</td><td><a href=\"javascript:void(0);\" onclick=\"updateboardroom('"+data[i].boardroomId+"');\">修改</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"delboardroom('"+data[i].boardroomId+"');\">删除</a></td></tr>";
			$("#boardroomtable").append(tr);
			}
	}
	});
}
function updateboardroom(boardroomId){
	var url=contextPath+"/metting/boardroom/updateboardroom.jsp?boardroomId="+boardroomId;
	window.location=url;
	
}
function delboardroom(boardroomId){
	if(confirm("确认删除？")){
	var url=contextPath+"/meeting/act/BoardroomAct/delboardroomAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{boardroomId:boardroomId},
		dataType:"text",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
			{
			parent.layer.msg('删除成功！');
			lookboardroom();
		}
	}
	});
	}
}
function newboardroom(){
	var url=contextPath+"/metting/boardroom/Newboardroom.jsp";
	window.location=url;
}