$(function(){
	getapply();
	});
	function getapply(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffresapplyAct/getdisStaffAct.act",
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
	           	{title: '办公用品名称', field: 'RES_NAME', width: '20%', align: 'center',sortable:true},
	           	{title: '登记类型', field:'RES_TYPE', width: '12%' ,align :'center' ,sortable:true },
	           	{title: '当前库存', field: 'BEFORESTOCK', width: '8%', align: 'center',sortable:true},
	            {title: '申请数量', field: 'APPLY_NUM', width: '10%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'APPLY_TIME', width: '15%', align: 'center',sortable:true},
	            {title: '申请人', field: 'USER_NAME', width: '10%', align: 'center'},
	            {title: '申请状态', field: 'APPLY_STATUS', width: '10%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.APPLY_STATUS==1){
	        				return '审批通过';
	        			}	
	        		}
	            },
	            {title: '操作', field: 'OPT', width: '10%', align: 'center'}
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
	function resgive(applyId){
			var url=contextPath+"/officesupplies/act/OffresapplyAct/updategiveAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{
					applyId:applyId
				},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data!=0){
						parent.layer.msg('发放成功！');
						 window.location.reload();
					}
			}
			});
	}