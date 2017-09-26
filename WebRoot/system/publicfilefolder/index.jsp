<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作资源设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style type="text/css">
	.content{width:80%;margin-left:10%;margin-top:30px;}
</style>
<script type="text/javascript">
$(function(){
	doinit();
	$("#btn_add").click(function(){
		window.location = contextPath+"/system/publicfilefolder/addfolder.jsp";
	})  
});
function doinit(){
	ajaxLoading();
	   $("#myTable").datagrid({
	        width: '100%',
			height: 400,
			rows:5,
	        collapsible: true,
	        url:'<%=contextPath%>/tfd/system/folder/act/FolderAct/getFolderList.act?isPublic=1',
	        method: 'POST',
	        sortName: 'ID',
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	            {title: '序号', field: 'FOLDER_NO', width: '24%', align: 'center'},
	           	{title: '目录名称', field: 'FOLDER_NAME', width: '42%', align: 'center'},
	            {title: '操作', field: 'OPT', width: '36%', align: 'center'}
	        ]],
	        onLoadSuccess:function(data){  
	            if(data.total == 0){
	   	  			//$('#myTable').datagrid('appendRow',{FOLDER_NO:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'FOLDER_NO', colspan: 3 });
	   	  			//$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
	            	$(".datagrid").hide();
	    	  		$(".MessageBox").show();
	        	}
	            ajaxLoadEnd();
	        }
	    });
	     
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为5  
	        pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  
}
function editFolder(id){
	window.location = contextPath+"/system/publicfilefolder/editfolder.jsp?folderId="+id;
}
function setPermission(id){
	window.location = contextPath+"/system/publicfilefolder/permission/index.jsp?folderId="+id;
}
function deleteFolder(id){
	if(confirm("确定删除?")){
		var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/deleteFolder.act';
 		$.ajax({
 				url:requestUrl,
 				data:{folderId:encodeURIComponent(id)},
 				dataType:"json",
 				async:false,
 				error:function(e){
 					alert(e.message);
 				},
 				success:function(data){
 					if(data=='1'){
 						top.layer.msg('删除成功');
 						doinit();
 					}
 				}
 		});
	}
}
</script>
</head>
<body>
<div style="margin-top:10px;margin-bottom:5px;width:10%;text-align:center;margin-left:45%;" align="center" ><button type="button"  id="btn_add" class="btn btn-success">新建文件夹</button></div>
<div class= "content" >
	<div id="myTable" name="myTable"></div>
	
	<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
	   <tbody><tr class="head-no-title">
	      <td class="left"></td>
	      <td class="center">
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="msg">
	      <td class="left"></td>
	      <td class="center info">
	         <div class="msg-content">暂无文件夹</div>
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="foot">
	      <td class="left"></td>
	      <td class="center"><b></b></td>
	      <td class="right"></td>
	   </tr>
	   </tbody>
	</table>
</div>
</body>
</html>