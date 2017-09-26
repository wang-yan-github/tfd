var belongDept;
var dispatchStaff;
var libraryStaff;
function looklibclassify(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/getIdlibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{libraryId:topId},
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#libraryName").text(data.libraryName);
			belongDept=data.belongDept;
			libraryStaff=data.libraryStaff;
			dispatchStaff=data.dispatchStaff;
	}
	});
	var url=contextPath+"/officesupplies/act/OfflibraryAct/gettopIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{topId:topId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var j=1;
			for(var i=0;i<data.length;i++){
			var tr="<tr align=\"center\"><td>"+j+"</td><td>"+data[i].libraryName+"</td><td>"+data[i].topName+"</td><td>"+data[i].deptName+"</td><td>"+data[i].sortId+"</td><td><a href=\"javascript:void(0);\" onclick=\"updatelibclassify('"+data[i].libraryId+"');\">修改</a>&nbsp;"+
			"<a href=\"javascript:void(0);\" onclick=\"dellibclassify('"+data[i].libraryId+"');\">删除</a>&nbsp;</td></tr>";
			j++;
			$("#offlibclassify").append(tr);
			}
	}
	});
}
function updatelibclassify(libraryId){
	var updateclassifyId=libraryId;
	$(document).ready(function(){ 
		$('#updatemyModal').modal({backdrop: 'static', keyboard: false});
		$("#updatemyModal").modal('show'); 
		});
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
			$("#updatelibraryName").text(data.topName);
			$("#updateclassifyName").val(data.libraryName);
			$("#updatesortId").val(data.sortId);
	}
	});
	
	
	$("#updateclassify").click(function (){
		var url=contextPath+"/officesupplies/act/OfflibraryAct/updatelibraryAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{
				libraryId:updateclassifyId,
				belongDept:belongDept,
			libraryStaff:libraryStaff,
			dispatchStaff:dispatchStaff,
			libraryName:$("#updateclassifyName").val(),
			sortId:$("#updatesortId").val()
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					parent.layer.msg('添加成功！');
					 window.location.reload();
				}
		}
		});
	});
	
}
function dellibclassify(libraryId){
	if(confirm("确认删除？")){
		var url=contextPath+"/officesupplies/act/OfflibraryAct/dellibraryAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{libraryId:libraryId},
			dataType:"text",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data==1)
			{
			parent.layer.msg('删除成功');
			window.location.reload();
		}else if(data==0){
		}else{
			parent.layer.msg('存在办公用品，不允许删除',function(){});
		}
		}
		});
		}
}
$(function (){
	looklibclassify();
	$(".delempty").click(function (){
		$("#classifyName").val("");
		$("#updateclassifyName").val("");
		$("#sortId").val("");
		$("#updatesortId").val("");
	});
});
function addclassify(){
	var para={
			belongDept:belongDept,
			libraryStaff:libraryStaff,
			dispatchStaff:dispatchStaff,
			libraryName:$("#classifyName").val(),
			topId:topId,
			sortId:$("#sortId").val()
	};
	var url=contextPath+"/officesupplies/act/OfflibraryAct/addlibclassifyAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			belongDept:belongDept,
			libraryStaff:libraryStaff,
			dispatchStaff:dispatchStaff,
			libraryName:$("#classifyName").val(),
			topId:topId,
			sortId:$("#sortId").val()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('添加成功');
				 window.location.reload();
			}else{				
			}
	}
	});
}
function modalshow(){
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}
