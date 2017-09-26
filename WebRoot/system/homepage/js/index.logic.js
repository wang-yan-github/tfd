function doinit(){
	var requestUrl=contextPath+'/tfd/system/homepage/act/HomePageAct/getHomePage.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			$.each(data,function(index,data){
				html += "<tr><td>"+(index+1)+"</td><td>"+data.moduleName+"</td>"
					+"<td><a href=\"javascript:void(0);\" onclick=\"toUpdate('"+data.id+"')\" >编辑</a></td>";
				if(data.isOpen == '1'){
					html += "<td><select id=\"isOpen"+data.id+"\" onchange=\"closeModule('"+data.id+"');\" ><option selected = \"selected\" value=\"1\" >开</option><option value=\"0\" >关</option></select></td></tr>";
				}else{
					html += "<td><select id=\"isOpen"+data.id+"\" onchange=\"closeModule('"+data.id+"');\" ><option value=\"1\" >开</option><option selected = \"selected\" value=\"0\" >关</option></select></td></tr>";
				}
			});
			$("#content").html(html);
		}
	});
}

function closeModule(id){
	var isOpen = $("#isOpen"+id).val();
	var requestUrl=contextPath+'/tfd/system/homepage/act/HomePageAct/closeModule.act';
	$.ajax({
		url:requestUrl,
		data:{id:id,isOpen:isOpen},
		dataType:"json",
		async:false,
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("保存成功");
			}else{
				top.layer.msg("保存失败",function(){});
			}
		}
	});
}

function toUpdate(id){
	var requestUrl=contextPath+'/tfd/system/homepage/act/HomePageAct/getModuleById.act';
	$.ajax({
		url:requestUrl,
		data:{id:id},
		dataType:"json",
		async:false,
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$(".update_content").show();
			$("#update-name").val(data.moduleName);
			$("#update-id").val(data.id);
			$("#update-isOpen").val(data.isOpen);
		}
	});
}

function doUpdate(){
	var requestUrl=contextPath+'/tfd/system/homepage/act/HomePageAct/updateModuleById.act';
	$.ajax({
		url:requestUrl,
		data:{id:$("#update-id").val(),
			moduleName:$("#update-name").val(),
			isOpen:$("#update-isOpen").val()},
		dataType:"json",
		async:false,
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("保存成功");
			}else{
				top.layer.msg("保存失败",function(){});
			}
		}
	});
}

function doBack(){
	$(".update_content").hide();
}
