$(function(){
    $("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: contextPath+"/meeting/act/MeetingsummaryAct/looksummaryAct.act",
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
           	{title: '名称', field: 'MEETING_NAME', width: '30%', align: 'center',sortable:true},
           	{title: '申请人', field:'ASK_NAME', width: '15%' ,align :'center' ,sortable:true },
            {title: '纪要员', field: 'USER_NAME', width: '30%', align: 'center',sortable:true},
            {title: '操作', field: 'OPT', width: '15%', align: 'center'}
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
});
function looksummary(summaryId){
	var url=contextPath+"/metting/summary/summarynews.jsp?summaryId="+summaryId;
	window.location=url;
}