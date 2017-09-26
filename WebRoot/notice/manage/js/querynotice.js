function handle() {
		var radiovalue = $(':radio:checked').val();
		if (radiovalue == 1) {
			querynotice();
		}
		if (radiovalue == 2) {
			termdelnotice();
		}
	}
	function termdelnotice() {
		var url = contextPath + "/notice/act/NoticeAct/termdelnoticeAct.act";
		var parm = $('#form1').serialize();
		if (window.confirm("确定删除记录吗？\n")) {
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
	function querynotice() {
		$("#bigdiv").hide();
		$('#table').show();
		var url = contextPath + "/notice/act/NoticeAct/querynoticeAct.act";
		$("#myTable").datagrid({
			width: document.body.clientWidth,
			rows : 5,
			collapsible : true,
			url : url,
			queryParams : {
				noticeType : $('#noticeType').val(),
				noticeStatus : $('#noticeStatus').val(),
				 noticeTitle : $('#noticeTitle').val(),
				starttime : $('#starttime').val(),
				endtime : $('#endtime').val(),
				noticeContent : $('#noticeContent').val(),
				approvalStatus:$("#approvalStatus").val()
			},
			method : 'POST',
			sortName: 'CREATE_TIME',
			sortOrder:'DESC',
			loadMsg : "数据加载中...",
			pagination : true,
			rownumbers:true, 
			striped : true,
			singleSelect : true,
			remoteSort : true,
			onLoadSuccess:function(data){  
	           		if(data.total == 0){
	   	  				$('#myTable').datagrid('appendRow',{NOTICE_TITLE:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'NOTICE_TITLE', colspan: 7 });
	        		}
	        },
			columns :[[
			           	{title: '标题', field: 'NOTICE_TITLE', width: '18%', align: 'center',sortable:true},
			           	{title: '类型', field:'NOTICE_TYPE', width: '14%' ,align :'center' ,sortable:true },
			            {title: '发布时间', field: 'CREATE_TIME', width: '14%', align: 'center',sortable:true},
			            {title: '状态', field: 'NOTICE_STATUS', width: '8%', align: 'center',sortable:true,
			            	formatter: function (value, rowData, rowIndex) {
			            		if(rowData.NOTICE_STATUS==1)
			            			{
			            			return "已发布";
			            			}else{
			            				return "未发布";	
			            			}
			                }
			            },
			            {title: '审批状态', field: 'APPROVAL_STATUS', width: '14%', align: 'center',sortable:true,
			            	formatter: function (value, rowData, rowIndex) {
			            		if(rowData.APPROVAL_STATUS==1)
			            			{
			            			return "审批通过";
			            			}else{
			            				if(rowData.APPROVAL_STATUS==2){
			            					return "审批未通过";
			            				}
			            				else{
			            				return "未审批";	
			            				}
			            			}
			                }
			            },
			            {title: '点击数', field: 'ONCLICK_COUNT', width: '5%', align: 'center',sortable:true},
			            {title: '操作', field: 'OPT', width: '18%', align: 'center',sortable:true,
						formatter: function (value, rowData, rowIndex) {
							if (rowData.APPROVAL_STATUS != 0) {
								return "<a href=\"javascript:readnotice('" + rowData.NOTICE_ID + "');\">查看</a> <a href=\"javascript:delnotice('" + rowData.NOTICE_ID + "');\">删除</a>  ";
							} else {
								return rowData.OPT;
							}
						}

                }
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
	function updatenotice(noticeId)
	{
		window.location=contextPath+"/notice/manage/editnotice.jsp?noticeId="+noticeId;
		}
	function delnotice(noticeId){
		if(confirm("确认删除？")){
			var url=contextPath+"/notice/act/NoticeAct/delnoticeAct.act";
			$.ajax({
				url:url,
				dataType:"text",
				data:{noticeId:noticeId},
				async:false,
				error:function(e){
					
				},
				success:function(data){
					if(data==1)
						{						
						parent.layer.msg('删除成功');
						querynotice();
						}
				}
			});
		}
		}
		function readnotice(noticeId)
		{
			window.location =contextPath+"/notice/readnotice/readnotice.jsp?noticeId="+noticeId; 
		}
	$(function(){
		getSelect("notice","noticeType","");
		$('#noticeType').append("<option value=\"\" selected>全部</option>");
	});