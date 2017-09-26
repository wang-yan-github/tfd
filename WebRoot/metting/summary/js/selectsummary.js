$(function(){
    $("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: contextPath+"/meeting/act/MeetingrequestAct/getStaffmeetingAct.act",
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
           	{title: '申请人', field:'USER_NAME', width: '15%' ,align :'center' ,sortable:true },
            {title: '主题', field: 'MEETING_THEME', width: '20%', align: 'center',sortable:true},
            {title: '会议室', field: 'BOARDROOM_NAME', width: '15%', align: 'center',sortable:true    },
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
});
function addsummary(requestId,meetingName,attendStaff,createUser){
	var url=contextPath+"/metting/summary/addsummary.jsp?requestId="+requestId+"&meetingName="+meetingName+"&attendStaff="+attendStaff+"&createUser="+createUser;
	window.location=url;
}