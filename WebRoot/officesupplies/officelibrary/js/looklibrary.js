function looklibrary(){
	var url=contextPath+"/officesupplies/act/OfflibraryAct/looklibraryAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			var j=1;
			var tr="";
			for(var i=0;i<data.length;i++){
			 tr+="<tr align=\"center\"><td>"+j+"</td><td>"+data[i].libraryName+"</td><td>"+data[i].className+"</td><td>"+data[i].deptName+"</td><td>"+data[i].userName+"</td><td>"+data[i].dispatchName+"</td><td><a href=\"javascript:void(0);\" onclick=\"updatelibrary('"+data[i].libraryId+"');\">修改</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"dellibrary('"+data[i].libraryId+"');\">删除</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"classifymanage('"+data[i].libraryId+"')\">分类管理</a></td></tr>";
			j++;
			}
			$("#offlibrary").append(tr);
	}
	});
}
function updatelibrary(libraryId){
	var url=contextPath+"/officesupplies/officelibrary/updatelibrary.jsp?libraryId="+libraryId;
	window.location=url;
	
}
function dellibrary(libraryId){
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
			parent.layer.msg('存在办公用品，不允许删除',function (){});
		}
	}
	});
	}
}
function newlibrary(){
	var url=contextPath+"/officesupplies/officelibrary/Newlibrary.jsp";
	window.location=url;
}
function classifymanage(libraryId){
	var url=contextPath+"/officesupplies/officeclassify/looklibclassify.jsp?libraryId="+libraryId;
	window.location=url;
}
$(function (){
	looklibrary();
});