function setPriv(photoId)
{
	window.location = contextPath+"/system/photo/priv/index.jsp?photoId="+photoId;
}
	
function del(photoId)
{
	if(confirm("确定删除？")){
		var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/delPhotoAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{photoId:photoId},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					top.layer.msg("删除成功！");
					doinit();
				}
			}
		});
	}
}

function edit(photoId)
{
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoByIdAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{photoId:photoId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#photoId").val(data.photoId);
			$("#sortId").val(data.sortId);
			$("#photoName").val(data.photoName);
			$("#photoPath").val(data.photoPath);
			$("#btn_edit").trigger("click");
		}
	});
}
function editPhoto(){
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/updatePhotoAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{
			photoId:$("#photoId").val(),
			sortId:$("#sortId").val(),
			photoName:$("#photoName").val(),
			photoPath:$("#photoPath").val()
			},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='1'){
				top.layer.msg("修改成功!");
				$("#btn_close").trigger("click");
				doinit();
			}
		}
	});
}

function doinit()
{
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getAllPhotoAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			for(var i=0;data.length>i;i++)
				{
				html+="<tr>";
				html+="<td>"+(i+1)+"</td>";
				html+="<td>"+data[i].photoName+"</td>";
				html+="<td>"+data[i].photoPath+"</td>";
				html+="<td>"+data[i].createUserName+"</td>";
				html+="<td>"+data[i].createDate+"</td>";
				html+="<td><a href=\"javascript:void(0)\" onclick=\"setPriv('"+data[i].photoId+"');\">设置权限</a></td>";
				html+="<td><a href=\"javascript:void(0)\"  onclick=\"edit('"+data[i].photoId+"');\">编辑</a>    <a href=\"javascript:void(0)\" onclick=\"del('"+data[i].photoId+"');\">删除</a></td>";
				}
				$("#content").html(html);
			}
	});
}