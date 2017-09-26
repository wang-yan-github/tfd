<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看待批会议</title>
<script type="text/javascript" >
$(function(){
	getnoratifymetting();
});
function getnoratifymetting(){
	 $("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/meeting/act/MeetingrequestAct/noratifyrequestAct.act",
	        method: 'POST',
	        sortName:'ID',
	      	sortOrder:'DESC',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        rownumbers:true, 
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	           	{title: '名称', field: 'MEETING_NAME', width: '25%', align: 'center',sortable:true},
	           	{title: '申请人', field:'USER_NAME', width: '10%' ,align :'center' ,sortable:true },
	            {title: '开始时间', field: 'MEETING_STARTTIME', width: '30%', align: 'center',sortable:true},
	            {title: '会议室', field: 'BOARDROOM_NAME', width: '10%', align: 'center',sortable:true    },
	            {title: '操作', field: 'OPT', width: '20%', align: 'center'}
	        ]]
	    });
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,
	        pageList: [10, 20, 30 ,50],
	        beforePageText: '第', 
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
}
function updatemeeting(requestId){
	var url=contextPath+"/metting/requestmetting/updaterequest.jsp?requestId="+requestId;
	window.location=url;
}
function delmeeting(requestId){
	if(confirm("确认删除？")){
		var url=contextPath+"/meeting/act/MeetingrequestAct/delrequestAct.act";
		$.ajax({
			   url:url,
			   type:'post',
		   		async:false,
		   		data:{requestId:requestId},
		   		success:function(data){
		   			if(data!=0){
		   			parent.layer.msg('删除成功！');
		   			getnoratifymetting();
		   			}
		   		}
		   });
	}
}
function selectmeeting(requestId){
	var url=contextPath+"/metting/requestmetting/getIdrequest.jsp?requestId="+requestId;
	window.location=url;
}
</script>
</head>
<body>
<div id="myTable" name="myTable"></div>
</div>
</body>
</html>