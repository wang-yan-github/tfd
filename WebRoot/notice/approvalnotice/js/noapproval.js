$(function(){
	    $("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/notice/act/NoticeAct/lookNoapprovalnoticeAct.act",
	        method: 'POST',
	        sortName:'CREATE_TIME',
	      	sortOrder:'DESC',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	        	{title: '发布人', field: 'CREATE_NAME', width: '15%', align: 'center',sortable:true},
	           	{title: '标题', field: 'NOTICE_TITLE', width: '30%', align: 'center',sortable:true},
	           	{title: '类型', field:'NOTICE_TYPE', width: '15%' ,align :'center' ,sortable:true },
	            {title: '发布时间', field: 'CREATE_TIME', width: '15%', align: 'center',sortable:true},
	            {title: '操作', field: 'OPT', width: '15%', align: 'center',sortable:true}
	        ]]
	    });
	     
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为5  
	        pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  
	});
function approvalnotice(noticeId)
{
	window.location =contextPath+"/notice/approvalnotice/approvalnotice.jsp?noticeId="+noticeId; 
}