$(function(){
	doinit();
	});
	function doinit(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			height: 'auto',
			rows:5,
	        collapsible: true,
	        url: contextPath+"/news/act/NewsAct/alreadyapprovalAct.act",
	        method: 'POST',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true, 
	        rownumbers:true, 
	        columns:[[
	           	{title: '标题', field: 'TITLE', width: '25%', align: 'center',sortable:true},
	           	{title: '类型', field: 'TYPE', width: '15%', align: 'center',sortable:true},
	            {title:'发布人', field:'CREATE_NAME' ,width:'10%',align:'center'},
	           	{title: '发布时间', field: 'CREATE_TIME', width: '14%', align: 'center',sortable:true},
	            {title:'审批状态',field:'APPROVAL_STATUS',width:'10%',align:'center',sorttable:true,
	           		formatter: function (value, rowData, rowIndex) {
	           	        if(rowData.APPROVAL_STATUS==1){
	           	        	return '批准';
	           	        }else{
	           	        	return '不批准';
	           	        }
	           	    }	
	            },
	           	{title: '操作', field: 'OPT', width: '14%', align: 'center'}
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
	}
function applynews(Id)
{
	window.location=contextPath+"/news/newsapproval/applynew.jsp?newsId="+Id; 
}