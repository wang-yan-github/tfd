$(function(){
	getapply();
	});
	function getapply(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffresapplyAct/alreadlyaplyAct.act",
	        method: 'POST',
	        sortName:'APPLY_TIME',
	      	sortOrder:'DESC',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        rownumbers:true, 
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	           	{title: '办公用品名称', field: 'RES_NAME', width: '22%', align: 'center',sortable:true},
	           	{title: '登记类型', field:'RES_TYPE', width: '12%' ,align :'center' ,sortable:true },
	            {title: '当前库存', field:'BEFORESTOCK', width: '12%' ,align :'center' },
	            {title: '申请数量', field: 'APPLY_NUM', width: '10%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'APPLY_TIME', width: '15%', align: 'center',sortable:true},
	            {title: '申请人', field: 'USER_NAME', width: '13%', align: 'center'},
	            {title: '审批状态', field: 'APPLY_STATUS', width: '10%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.APPLY_STATUS==1){
	        				return '已批准';
	        			}else{
	        				return '未批准';
	        			}	
	        		}
	            },
	            {title: '发放状态', field: 'GIVE_STATUS', width: '10%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.GIVE_STATUS==1){
	        				return '已发放';
	        			}else{
	        				return '未发放';
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