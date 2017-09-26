$(function(){
	getapply();
	});
	function getapply(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffresapplyAct/getnotapplyAct.act",
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
	           	{title: '办公用品名称', field: 'RES_NAME', width: '25%', align: 'center',sortable:true},
	           	{title: '登记类型', field:'RES_TYPE', width: '12%' ,align :'center' ,sortable:true },
	            {title: '当前库存', field:'BEFORESTOCK', width: '12%' ,align :'center' },
	            {title: '申请数量', field: 'APPLY_NUM', width: '10%', align: 'center',sortable:true},
	            {title: '操作日期', field: 'APPLY_TIME', width: '15%', align: 'center',sortable:true},
	            {title: '申请人', field: 'USER_NAME', width: '10%', align: 'center'},
	            {title: '操作', field: 'OPT', width: '15%', align: 'center'}
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
					parent.layer.msg('删除成功！');
					getapply();
				}
			}
			});
			}
	}
	function approvalapply(applyId,resId,approvalStaff,applyNum,beforestock){
		var url=contextPath+"/officesupplies/act/OffresAct/getIdresAct.act";
		var Staff;
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				 Staff=data.approveStaff;
		}
		});	
		if(Staff==approvalStaff){
			var url=contextPath+"/officesupplies/act/OffresapplyAct/updateapprovalAct.act";
			var applyStatus=1;
			$.ajax({
				url:url,
				type:"POST",
				data:{
						applyId:applyId,
						applyStatus:applyStatus
				},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data==1)
					{
						if(beforestock>=applyNum){
					if(updateres(resId,applyNum)){
						parent.layer.msg('审批成功');
						getapply();	
					}
						}else{
							parent.layer.msg('申请数量大于当前库存数',function(){});	
						}
				}
			}
			});  
		}else{
			var url=contextPath+"/officesupplies/act/OffresapplyAct/updateStaffAct.act";
			var applyStatus=1;
			$.ajax({
				url:url,
				type:"POST",
				data:{
						applyId:applyId,
						approvalStaff:Staff
				},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data==1)
					{
					parent.layer.msg('审批成功');
					getapply();
				}
			}
			});
		}
	}
	function notapprovalapply(applyId){
		var url=contextPath+"/officesupplies/act/OffresapplyAct/updateapprovalAct.act";
		var applyStatus=2;
		$.ajax({
			url:url,
			type:"POST",
			data:{applyId:applyId,
					applyStatus:applyStatus
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data==1)
				{
				parent.layer.msg('审批成功');
				getapply();
			}
		}
		});
	}
	function updateres(resId,applyNum){
		var status;
		var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId,maintNum:applyNum},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					status=true;
				}else{
					status=false;
				}
		}
		});
		return status;
	}