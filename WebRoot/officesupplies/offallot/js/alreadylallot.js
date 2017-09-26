$(function(){
	getallot();
	});
	function getallot(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffallotAct/getallotAct.act",
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
	           	{title: '办公用品名称', field: 'RES_NAME', width: '30%', align: 'center',sortable:true},
	            {title: '调拨数量', field: 'ALLOT_NUM', width: '10%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'ALLOT_TIME', width: '20%', align: 'center',sortable:true},
	            {title: '申请人', field: 'USER_NAME', width: '16%', align: 'center'},
	            {title: '审批结果', field: 'ALLOT_STATUS', width: '16%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	        			if(rowData.ALLOT_STATUS==1){
	        				return '已批准';
	        			}else{
	        				return '不批准';
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