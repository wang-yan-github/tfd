function handle() {
		var radiovalue = $(':radio:checked').val();
		if (radiovalue == 1) {
			queryNews();
		}
		if (radiovalue == 2) {
			delallNews();
		}
	}
	function delallNews() {
		var url = contextPath + "/news/act/NewsAct/DelnewsAct.act";
		var parm = $('#form1').serialize();
		if (confirm("确定删除记录吗？\n")) {
			$.ajax({
				url : url,
				dataType : "text",
				data : parm,
				async : false,
				error : function(e) {
				},
				success : function(data) {
					if (data != 0) {
						parent.layer.msg('删除成功');
					} else {
						parent.layer.msg('没有可删除的数据');
					}
				}
			});
		}
	}
	function queryNews() {
		$("#bigdiv").hide();
		$('#table').show();
		var url = contextPath + "/news/act/NewsAct/QuerynewsAct.act";
		$("#myTable").datagrid({
			width : document.body.clientWidth,
			rows : 5,
			collapsible : true,
			url : url,
			queryParams : {
				newstype : $('#newstype').val(),
				status :$('#status').val(),
				top :$('#top').val(),
				title : $('#title').val(),
				starttime : $('#starttime').val(),
				endtime : $('#endtime').val(),
				contect : $('#contect').val()
			},
			method : 'POST',
			sortName: 'CREATE_TIME',
			sortOrder:'DESC',
			loadMsg : "数据加载中...",
			pagination : true,
			striped : true,
			singleSelect : true,
			remoteSort : true,
			rownumbers:true, 
			onLoadSuccess:function(data){  
	           		if(data.total == 0){
	   	  				$('#myTable').datagrid('appendRow',{TITLE:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'TITLE', colspan: 7 });
	        		}
	        },
			columns : [ [  {
				title : '标题',
				field : 'TITLE',
				width : '20%',
				align : 'center',
				sortable : true
			}, {title: '类型', field: 'TYPE', width: '14%', align: 'center',sortable:true},
			{
				title : '发布时间',
				field : 'CREATE_TIME',
				width : '14%',
				align : 'center',
				sortable : true
			}, {
				title : '状态',
				field : 'STATUS',
				width : '14%',
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					if (rowData.STATUS == 1) {
						return "已发布";
					} else {
						return "未发布";
					}
				}
			}, {
				title : '点击数',
				field : 'ONCLICK_COUNT',
				width : '5%',
				align : 'center',
				sortable : true
			},{title:'审批结果',field:'APPROVAL_STATUS',width:'10%',align:'center',
            	formatter: function (value, rowData, rowIndex) {
	                   if(rowData.APPROVAL_STATUS==1){
	                	   return '通过';
	                   }else{
	                	   if(rowData.APPROVAL_STATUS==0){
	                		   return '待审批';
	                	   }
	                	   else{
	                	   		return '不通过';
	                	   }
	                   }
	                }	
	           	},
	           	{
				title : '操作',
				field : 'OPT',
				width : '18%',
				align : 'center',
				formatter: function (value, rowData, rowIndex) {
            		if(rowData.APPROVAL_STATUS==1)
            			{
            			return "<a href=\"javascript:javascript:readNews('"+rowData.NEWS_ID+"')\">查看</a><a href=\"javascript:delNews('"+rowData.NEWS_ID+"')\">删除</a>";
            			}else{
            				return rowData.OPT;	
            			}
                }
			} ] ]
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
	function readNews(newsId)
	{
		window.location=contextPath+"/news/read/readnews.jsp?newsId="+newsId; 
	}
	function editNews(newsId)
	{
		window.location=contextPath+"/news/manage/edit.jsp?newsId="+newsId;
		}
	function delNews(newsId)
	{	
		if (confirm("确定删除记录吗？\n")) {
		var url=contextPath+"/news/act/NewsAct/delNewsAct.act";
		$.ajax({
		url:url,
		dataType:"text",
		data:{newsId:newsId},
		async:false,
		error:function(e){
			
		},
		success:function(data){
			if(data==1)
				{
				parent.layer.msg('删除成功');
				queryNews();
				}
		}
	});
	}
}
	$(function(){
		getSelect("news","newstype","");
		$('#newstype').append("<option value=\"\" selected>全部</option>");
	});