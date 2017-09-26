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
		window.location = contextPath+"/system/publicdisk/adddisk.jsp";
	})  
});
function doinit(){
	ajaxLoading();
	   $("#myTable").datagrid({
	        width: '100%',
			height: 400,
			rows:5,
	        collapsible: true,
	       	url:'<%=contextPath%>/tfd/system/publicdisk/act/PublicDiskAct/getPublicDiskList.act',
	        method: 'POST',
	        sortName: 'ID',
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	            {title: '序号', field: 'DISK_NO', width: '4%', align: 'center'},
	           	{title: '目录名称', field: 'DISK_NAME', width: '24%', align: 'center'},
	            {title: '目录路径', field: 'DISK_PATH', width: '24%', align: 'center'},
	            {title: '限制容量', field: 'SPACE_LIMIT', width: '16%', align: 'center'},
	            {title: '默认排序', field: 'ORDER_BY', width: '16%', align: 'center',
	            	formatter:function(value, rowData, rowIndex){
	            		return rowData.ORDER_BY+"("+rowData.ASC_DESC+")";
	            	}
	            },
	            {title: '操作', field: 'OPT', width: '18%', align: 'center'}
	        ]],
	        onLoadSuccess:function(data){  
	            if(data.total == 0){
	   	  			//$('#myTable').datagrid('appendRow',{DISK_NO:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'DISK_NO', colspan: 6 });
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
function editDisk(id){
	window.location = contextPath+"/system/publicdisk/editdisk.jsp?diskId="+id;
}
function setPermission(id){
	window.location = contextPath+"/system/publicdisk/permission/index.jsp?diskId="+id;
}
function deleteDisk(id){
	if(confirm("确定删除?")){
		var requestUrl= '<%=contextPath%>/tfd/system/publicdisk/act/PublicDiskAct/deletePublicDisk.act';
 		$.ajax({
 				url:requestUrl,
 				data:{diskId:encodeURIComponent(id)},
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
<div style="margin-top:10px;margin-bottom:5px;width:10%;text-align:center;margin-left:45%;" align="center" ><button type="button"  id="btn_add" class="btn btn-success">新建资源共享</button></div>
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
	         <div class="msg-content">暂无公共资源</div>
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