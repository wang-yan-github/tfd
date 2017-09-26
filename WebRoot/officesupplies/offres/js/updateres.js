$(function(){
	filesUpLoad("office");
	getlibrary();
	$("#resId").val(resId);
	});
function getlibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/getlibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
				$("#libraryId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
			}
			getresId();
	}
	});
}
	function getresId(){
		var url=contextPath+"/officesupplies/act/OffresAct/getIdresAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				fromdata=data;
				attachId=data.attachId;
		   		creatAttachDiv(attachId,"attach");
				for(var name in fromdata){
					if(name=="libraryId"){
						$("#"+name).val(fromdata[name]);
						getlibclassify(fromdata[name]);
					}else{
					$("#"+name).val(fromdata[name]);
					}
				}
		}
		});
	}
	function getlibclassify(libraryId){
		var library;
		if(libraryId==undefined){
		 library=$("#libraryId").val();
		}else{
			library=libraryId;
		}
		if(library!=""){
		var url=contextPath+"/officesupplies/act/OfflibraryAct/gettopIdNameAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{topId:library},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				$("#classifyId").empty();
				$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
				if(data!=""){
				$("#approveStaff").val(data[0].libraryStaff);
				$("#userName").val(data[0].userName); 
				$("#disName").val(data[0].disName);
				$("#dispatchStaff").val(data[0].dispatchStaff);
				for(var i=0;i<data.length;i++){
					$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
				}
				}
		}
		});
		}else{
			$("#classifyId").empty();
			$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
			$('#offresform').bootstrapValidator('revalidateField', 'classifyId');	
		}
	}
function updateres(){
	var para=$("#offresform").serialize();
	para+="&attachId="+$("#attachId").val();
	var url=contextPath+"/officesupplies/act/OffresAct/updateresAct.act";
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
				location.reload();
			}else{
			}
	}
	});
}
function delres(){
	if(confirm("确认删除？")){
	var url=contextPath+"/officesupplies/act/OffresAct/delIdresAct.act";
	$.ajax({
		url:url,
		type:"POST",
		data:{resId:resId},
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('删除成功');
				window.location=contextPath+"/officesupplies/offres/Newres.jsp";
				parent.document.getElementById("left").src= "/tfd/officesupplies/offres/left.jsp";
			}else{
			}
	}
	});
	}
}
function deptNameval(){
	$('#offresform').bootstrapValidator('revalidateField', 'deptName');
}