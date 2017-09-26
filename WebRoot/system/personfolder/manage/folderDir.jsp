<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>
<title>公共资源</title>
<style type="text/css">
	html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
</style>
</head>
<body style="border-right:#CCC 1px solid;">
		<div class="list-group">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         个人文件柜
      </h5>
   </a>
   <div style="background-color:#F5F5F5;" class="list-group-item">
     <ul id="tree1" class="ztree"></ul>
   </div>
</div>
</body>
<script type="text/javascript">
var zNodes = null;
$(function(){
	var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/createFolder.act';
	$.ajax({
			url:requestUrl,
			type:'POST',
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
			}
	});
	var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/getFolderListByPriv.act';
	$.ajax({
			url:requestUrl,
			data:{isPublic:'2'},
			type:'POST',
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				zNodes = data;
			}
	});
});
var setting = {
	data: {
		simpleData: {
			enable: true}
			},
	callback:{onClick:getChild},
	view: {
		showLine: false
			},
	async: { //异步加载
         type: "post",
         enable: true,
         url: "<%=contextPath%>/tfd/system/folder/act/FolderAct/getFolderListById.act",
         autoParam:["id"],
         otherParam:{isPublic:"2"},
         data:filter
       }
};
$(document).ready(function(){
	$.fn.zTree.init($("#tree1"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	//treeObj.expandAll(true); 
});

function getChildNodes(treeNode) 
{
	var childNodes = ztree.transformToArray(treeNode);  
	var nodes = new Array();  
	for(i = 0; i < childNodes.length; i++) {  
		nodes[i] = childNodes[i].id;  
	}
	alert(nodes.join(","));  
} 
function filter(treeId, parentNode, childNodes) {
	var array = [];
	childNodes = childNodes.trees;//获取后台传递的数据
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
	repname = childNodes[i].name.replace(/\.n/g, '.');

	array[i] = {id:childNodes[i].id,name:repname,isParent:false};//将后台传过来的参数拼接成json格式，并放在数组中，如果有必要需要对其是否为父节点做处理
	}
	return array;
}
function getChild(event,treeId,treeNode){
	/* var zTreeObj=$.fn.zTree.getZTreeObj("tree1");
	if(treeNode.isParent){
		  if (!treeNode.open) {
			  zTreeObj.expandNode(treeNode, true);
			  if(treeNode.children==null||treeNode.children.length==0){
				var child = getChildData(treeNode);
				addChild(treeNode,child,true);
			  }
          }else{
        	  zTreeObj.expandNode(treeNode, false);
          }
	} */
	parent["right"].location=contextPath+"/system/personfolder/manage/manage.jsp?folderId="+encodeURIComponent(treeNode.id)+"&folderName="+encodeURIComponent(treeNode.name)+"&folderNo="+encodeURIComponent(treeNode.folderNo); 
}

function addChild(parentNode,newNodes,isSlient){
	var zTreeObj=$.fn.zTree.getZTreeObj("tree1");
	zTreeObj.addNodes(parentNode,newNodes,isSlient);
}
function getChildData(treeNode){
	var json = "";
	var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/getFolderListById.act';
	$.ajax({
			url:requestUrl,
			data:{id:treeNode.id,isPublic:'2'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				json = data;
			}
	});	
	return json;
}
</script>
</html>