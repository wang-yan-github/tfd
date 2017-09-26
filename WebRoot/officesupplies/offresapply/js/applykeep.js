$(function(){
	getapply();
	});
	function getapply(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffresapplyAct/getuserapplyAct.act",
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
	           	{title: '办公用品名称', field: 'RES_NAME', width: '15%', align: 'center',sortable:true},
	           	{title: '登记类型', field:'RES_TYPE', width: '10%' ,align :'center' ,sortable:true },
	            {title: '申请数量', field: 'APPLY_NUM', width: '8%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'APPLY_TIME', width: '15%', align: 'center',sortable:true},
	            {title: '审批人', field: 'USER_NAME', width: '10%', align: 'center'},
	            {title: '备注', field: 'APPLY_REMARY', width: '17%', align: 'center'},
	            {title: '状态', field: 'APPLY_STATUS', width: '8%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.APPLY_STATUS==0){
	        				return '待审批';
	        			}else{
	        				if(rowData.APPLY_STATUS==1){
	        					return '审批通过';
	        				}else{
	        					return '审批不通过';	
	        				}
	        			}
	        		}
	            },
	            {title: '操作', field: 'OPT', width: '15%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.APPLY_STATUS!=0){
	        				return "<a href=\"javascript:void(0)\" onclick='getapplId(\""+rowData.APPLY_ID+"\");' >详情</a>";
	        			}else{
	        				return rowData.OPT;
	        			}
	        		}
	            }
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
	function getapplId(applyId){
		var url=contextPath+"/officesupplies/offresapply/detailsapply.jsp?applyId="+applyId;
		window.location=url;
	}
	function delapply(applyId){
		if(confirm("确认删除？")){
			var url=contextPath+"/officesupplies/act/OffresapplyAct/delapplyAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{applyId:applyId},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data==1)
					{
					parent.layer.msg('删除成功');
					getapply();
				}else{
				}
			}
			});
			}
	}
	function updateapply(applyId){
		var url=contextPath+"/officesupplies/offresapply/updateapply.jsp?applyId="+applyId;
		window.location=url;
	}