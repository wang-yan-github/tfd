$(function(){
	getmaint();
	});
	function getmaint(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffmaintAct/getUsermaintAct.act",
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
	           	{title: '登记类型', field:'MAINT_TYPE', width: '12%' ,align :'center' ,sortable:true },
	            {title: '申请数量', field: 'MAINT_NUM', width: '10%', align: 'center',sortable:true},
	            {title: '当前库存', field: 'BEFORESTOCK', width: '10%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'MAINT_TIME', width: '20%', align: 'center',sortable:true},
	            {title: '单价', field: 'RES_PRICE', width: '15%', align: 'center'},
	            {title: '操作', field: 'OPT', width: '12%', align: 'center'}
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
	function updatemaint(maintId){
		var url=contextPath+"/officesupplies/offmaint/updatemaint.jsp?maintId="+maintId;
		window.location=url;
	}
	function delmaint(maintId,maintNum,maintType,resId){
		var url=contextPath+"/officesupplies/act/OffmaintAct/delmaintAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{
				maintId:maintId
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					updateres(maintNum,maintType,resId);
				}
				else{
				}
		}
		});
	}
	function updateres(maintNum,maintType,resId){
		 if(maintType=="报废"){
			var url=contextPath+"/officesupplies/act/OffresAct/updateresNumAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{resId:resId,maintNum:maintNum},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data!=0){
						parent.layer.msg('操作成功');
						getmaint();
					}else{
					}
			}
			});
			}else{
				var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
				$.ajax({
					url:url,
					type:"POST",
					data:{resId:resId,maintNum:maintNum},
					dataType:"json",
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data!=0){
							parent.layer.msg('操作成功');
							getmaint();
						}else{
						}
				}
				});
			} 
	}