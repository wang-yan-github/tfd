var editor=null;
var zNodes = null;
var ztree = null;
$(function(){
	filesUpLoad("institution");
	fileUploadByAttach("institution");
	editor = CKEDITOR.instances.editor;
	var requestUrl=contextPath+ '/institution/act/DirectoryAct/getDirectoryList2.act';
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
	view: {showLine: false,
		selectedMulti:false
		}
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
	deptObj.attr("value", v);
	if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
	var deptIdObj = $("#directory_id");
	deptIdObj.attr("value",vid);
	$('#form1').bootstrapValidator('revalidateField', 'directory_Name');
	$("#dirContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function showDir(){
	var cityObj = $("#directory_Name");
	var cityOffset = $("#directory_Name").offset();
	$("#dirContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#dirContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "dirContent" || $(event.target).parents("#dirContent").length>0)) {
		hideMenu();
	}
}
function add(){
	var requestUrl=contextPath+'/institution/act/InstitutionAct/addInstitution.act';
	var content = encodeURIComponent(editor.getData());
	var name = $('#title').val();
	var dirId = $('#directory_id').val();
	if(confirm("确认添加？")){
		$.ajax({
			url:requestUrl,
			data:{name:name,content:content,dirId:dirId,attachId:$("#attachId").val()},
			dataType:"json",
			type:"POST",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data == "1"){
					top.layer.msg("添加成功");
					parent.document.getElementById("left").src = "/tfd/institution/manager/directory.jsp";
					window.location.href = "/tfd/institution/manager/manager.jsp";
				}
			}
		});
	}
}
function back(){
	window.location.href = "/tfd/institution/manager/manager.jsp";
}
