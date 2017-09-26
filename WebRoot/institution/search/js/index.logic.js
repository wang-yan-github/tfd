var zNodes = null;
var ztree = null;
$(function(){
	searchName = encodeURIComponent($('#search_inst').val());
	var requestUrl=contextPath+'/institution/act/InstitutionAct/searchInstitution.act?searchName='+searchName;
	searchInst(requestUrl);
	$('#search_ok').click(function(){
		search();
	});
	$('#search_ok2').click(function(){
		search2();
	});
	$('.changeButton').click(function(){
		if(document.getElementById('search_content2').style.display == "none"){
			$('#search_content').attr("style","display:none;");
			$('#search_content2').attr("style","display:block;");
		}else{
			$('#search_content2').attr("style","display:none;");
			$('#search_content').attr("style","display:block;");
		}
		
	});
	document.onkeydown=function(event){
	    var e = event || window.event || arguments.callee.caller.arguments[0];
	    if(e && e.keyCode==13){ 
	    	if(document.getElementById('search_content2').style.display == "none"){
				search();
			}else{
				search2();
			}
	   }
	};
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
	$("#dirContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function showDir(){
	var cityObj = $("#directory_Name");
	cityObj.val("");
	$('#directory_id').val("");
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
function search(){
	searchName = encodeURIComponent($('#search_inst').val());
	var requestUrl=contextPath+'/institution/act/InstitutionAct/searchInstitution.act?searchName='+searchName;
	searchInst(requestUrl);
}
function search2(){
	var dirName = $('#directory_Name').val();
	var userName  = $('#userName').val();
	var beginTime = $('#beginTime').val();
	var endTime = $('#endTime').val();
	var par = "?dirName="+encodeURIComponent(dirName)+"&userName="+encodeURIComponent(userName)+"&beginTime="+beginTime+"&endTime="+endTime;
	var requestUrl=contextPath+'/institution/act/InstitutionAct/searchInstitution2.act'+par;
	searchInst(requestUrl);
}
function searchInst(requestUrl){
	ajaxLoading();
	$('#myTable').datagrid({
		url:requestUrl,
		columns:[[
					{field:'INST_NAME',title:'制度名称',width:'34%',align:'center',
						formatter:function(value,rowData,rowIndex){
							return '<a onclick=javascript:showInst("'+rowData.INST_ID+'");>'+rowData.INST_NAME+'</a>';
						}	
					},
					{field:'ALL_DIR',title:'所属目录',width:'24%',align:'center'},
					{field:'CREATE_TIME',title:'修订时间',width:'15%',align:'center',sortable:true,},
					{field:'USER_NAME',title:'修订人',width:'15%',align:'center',
						formatter:function(value,rowData,rowIndex){
							return "<a onclick=\"javascript:showPersonal('"+rowData.ACCOUNT_ID+"')\" href=\"javascript:void(0)\" >"+rowData.USER_NAME+"</a>";
						}
					},
					{field:'OPT',title:'操作',width:'11%',align:'center'}
				]],
				collapsible: true,
				method: 'POST',
			    sortName: 'CREATE_TIME',
				pagination:true,
				rownumbers:true,
				striped: true,
	        	singleSelect:true,  
	        	remoteSort:true, 
	        	onLoadSuccess:function(data){  
	           		if(data.total == 0){
	   	  				$('#myTable').datagrid('appendRow',{INST_NAME:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'INST_NAME', colspan: 5 });
	        		}
	        		ajaxLoadEnd();
	        	}
	});
	 var p = $('#myTable').datagrid('getPager');  
        $(p).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5, 10, 15 ,20],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
}
function showInst(id){
	var requestUrl=contextPath+'/institution/act/InstitutionAct/getInstitutionById.act';
	$.ajax({
		url:requestUrl,
		data:{id:id},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$('.tops').html(data[0].name);
			$('#contents').html(data[0].content);
			attachId=data[0].attachId;
			readAttachDiv(attachId,"attachDiv");
			$('#info').html("<p style='font-size:12px;' >修订人:<a onclick=\"javascript:showPersonal('"+data[0].accountId+"')\" href=\"javascript:void(0)\" >"+data[0].username+"</a></p><p style='font-size:12px;' >修订时间 : "+data[0].time+"</p>");
			$("#openModal").trigger("click");
		}
	});
}