$(function(){
	if(id!="null"){
		$('#content').show();
		$(".MessageBox").hide();
		doinit();
	}else{
		$("#content").hide();
		$(".MessageBox").show();
		$(".msg-content").html("请选择制度进行操作");
	}
	var requestUrl=contextPath+'/institution/act/DirectoryAct/getDirectoryList2.act';
	$.ajax({
			url:requestUrl,
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				zNodes=data;
			}
		});
	
});
var setting = {
	data: {simpleData: {enable: true}},
	callback:{onClick:onClick},
	view: {showLine: false,selectedMulti:false}
};
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	if($.isEmptyObject(zNodes)){
		$("#treeDemo").html("暂无目录");
	}
});
function getChildNodes(treeNode) 
{
	var childNodes = ztree.transformToArray(treeNode);  
	var nodes = new Array();  
	for(i = 0; i < childNodes.length; i++) {  
		nodes[i] = childNodes[i].id;  
	}
} 
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	vid="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		vid+=nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	var deptObj = $("#directory_Name");
	deptObj.val(v);
	if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
	var deptIdObj = $("#directory_id");
	deptIdObj.val(vid);
	$('#form1').bootstrapValidator('revalidateField', 'directory_Name');
	$("#dirContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function showDir(){
	var cityObj = $("#directory_Name");
	cityObj.val("");
	var cityOffset = $("#directory_Name").offset();
	$("#dirContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideDir() {
	$("#dirContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "dirContent" || $(event.target).parents("#dirContent").length>0)) {
		hideDir();
	}
}
function doinit(){
	var requestUrl=contextPath+'/institution/act/DirectoryAct/getDirectoryById.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id)},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$('#directory').val(data[0].name);
			$('#directory_Name').val(data[0].dirName);
			$('#directory_id').val(data[0].topId);
		}
	});
}
function del(){
	var requestUrl=contextPath+'/institution/act/DirectoryAct/delDirectoryById.act';
	if(confirm("确认删除？")){
		$.ajax({
			url:requestUrl,
			data:{id:id},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data == "1"){
					top.layer.msg("删除成功");
					parent.document.getElementById("left").src = contextPath+"/institution/dirManager/left.jsp";
					parent.document.getElementById("right").src = contextPath+"/institution/dirManager/right.jsp";
				}
			}
		});
	}
}
function update(){
	var requestUrl=contextPath+'/institution/act/DirectoryAct/updateDirectoryById.act';
	var name = $('#directory').val();
	var topId = $('#directory_id').val();
	$.ajax({
		url:requestUrl,
		data:{id:id,name:name,topId:topId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data == "1"){
				top.layer.msg("修改成功");
				parent.document.getElementById("left").src = contextPath+"/institution/dirManager/left.jsp";
				window.location.reload();
			}
		}
	});
}
function back(){
	window.location.href = contextPath+"/institution/dirManager/right.jsp";
}