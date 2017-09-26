<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制度中心</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>
</head>
<script type="text/javascript">
	var zNodes = null;
	var ztree = null;
	$(function(){
		var requestUrl='<%=contextPath%>/institution/act/DirectoryAct/getDirectoryList2.act';
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
		data: {
			simpleData: {
				enable: true}
				},
		callback:{onClick:goUrl},
		view: {
			showLine: false
				}
	};
	$(document).ready(function(){
		$.fn.zTree.init($("#tree1"), setting, zNodes);
		var treeObj = $.fn.zTree.getZTreeObj("tree1");
		if($.isEmptyObject(zNodes)){
			$("#tree1").html("暂无目录");
		}else{
			treeObj.expandAll(true);
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
	function goUrl(event,treeId,treeNode){
		parent.document.getElementById("right").src= "/tfd/institution/dirManager/right.jsp?id="+encodeURIComponent(treeNode.id);		
	}
	function addDir(){
		parent.document.getElementById("right").src= "/tfd/institution/dirManager/addDir.jsp";
	}
</script>
<body>
<div class="list-group">
   <a  class="list-group-item active">
      <h5 class="list-group-item-heading">
        制度目录
      </h5>
   </a>
   <div class="list-group-item">
     <ul id="tree1" class="ztree"></ul>
   </div>
   <a href="javascript:void(0);" class="list-group-item" onclick="addDir();" >
      <h5 class="list-group-item-heading" align="center">
         添加目录
      </h5>
   </a>
</div>		
</body>
</html>