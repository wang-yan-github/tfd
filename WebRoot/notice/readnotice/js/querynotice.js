function querynotice() {
		$("#bigdiv").hide();
		$('#table').show();
		var url = contextPath + "/notice/act/NoticeAct/termqueryAct.act";
		$("#myTable").datagrid({
			width: document.body.clientWidth,
			rows : 5,
			collapsible : true,
			url : url,
			queryParams : {
				accountId:$("#accountId").val(),
				noticeType : $('#noticeType').val(),
				 noticeTitle : $('#noticeTitle').val(),
				starttime : $('#starttime').val(),
				endtime : $('#endtime').val(),
				noticeContent : $('#noticeContent').val(),
			},
			method : 'POST',
			sortName: 'CREATE_TIME',
			sortOrder:'desc',
			loadMsg : "数据加载中...",
			pagination : true,
			rownumbers:true, 
			striped : true,
			singleSelect : true,
			remoteSort : true,
			onLoadSuccess:function(data){  
	           		if(data.total == 0){
	   	  				$('#myTable').datagrid('appendRow',{NOTICE_TITLE:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'NOTICE_TITLE', colspan: 6 });
	        		}
	        },
			columns :[[
			           	{title: '标题', field: 'NOTICE_TITLE', width: '30%', align: 'center',sortable:true},
			           	{title: '类型', field:'NOTICE_TYPE', width: '15%' ,align :'center' ,sortable:true },
			            {title: '发布时间', field: 'CREATE_TIME', width: '15%', align: 'center',sortable:true},
			            {title: '点击数', field: 'ONCLICK_COUNT', width: '10%', align: 'center',sortable:true},
			            {title: '发布人', field: 'CREATE_NAME', width: '10%', align: 'center'},
			            {title: '操作', field: 'OPT', width: '15%', align: 'center',sortable:true}
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
	function returnquery() {
		$('#table').hide();
		$('#bigdiv').show();
	}
		$(function(){
		getSelect("notice","noticeType","");
		$('#noticeType').append("<option value=\"\" selected>全部</option>");
	});
	function readnotice(noticeId)
{
	window.location=contextPath+"/notice/readnotice/readnotice.jsp?noticeId="+noticeId; 
}