function returnquery() {
	$('#table').hide();
	$('#bigdiv').show();
}
function querysummary() {
	$("#bigdiv").hide();
	$('#table').show();
	var url = contextPath+"/meeting/act/MeetingsummaryAct/tremsummaryAct.act";
	$("#myTable").datagrid({
		width: document.body.clientWidth,
		rows : 5,
		collapsible : true,
		url : url,
		queryParams : {
			meetingName:$("#meetingName").val(),
			askStaff:$("#askStaff").val(),
			contentone:$("#contentone").val(),
			contenttwo:$("#contenttwo").val(),
			contentthree:$("#contentthree").val()
		},
		method : 'POST',
		sortName : 'ID',
		loadMsg : "数据加载中...",
		pagination : true,
		striped : true,
		rownumbers:true, 
		singleSelect : true,
		remoteSort : true,
		columns:[[
		           	{title: '会议名称', field: 'MEETING_NAME', width: '30%', align: 'center',sortable:true},
		           	{title: '会议申请人', field:'ASK_NAME', width: '15%' ,align :'center' ,sortable:true },
		            {title: '会议纪要员', field: 'USER_NAME', width: '15%', align: 'center',sortable:true},
		            {title: '操作', field: 'OPT', width: '20%', align: 'center'}
		        ]]
	});

	var p = $('#myTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为5  
		pageList : [ 10, 20, 30, 50 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

}
function minutesummary(summaryId){
	var url=contextPath+"/metting/summary/summarynews.jsp?summaryId="+summaryId;
	window.location=url;
}
