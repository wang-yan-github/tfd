function checkpower(){
	var url=contextPath+"/customermanage/act/CustomerpowerAct/checkpowerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0)
				{
				$("#btnitem").append("<button class=\"btn btn-danger\" onclick=\"delcustomer();\" >删除</button>");
			}else{
			}
	}
	});
}

function delcustomer(){
	 var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.CUSTOMER_ID);
	    });
	 	if(runIds.length!=0){
	 			var url=contextPath+"/customermanage/act/CustomerinfoAct/delcustomerAct.act";
	 			$.ajax({
	 				url:url,
	 				type:"POST",
	 				traditional: true,
	 				dataType:"text",
	 				data:{"customerId":runIds},
	 				async:false,
	 				error:function(e){
	 				},
	 				success:function(data){
	 					if(data!=0)
	 						{
	 						init();
	 					}
	 			}
	 			});
			}else{
	 		parent.layer.msg('请选择数据行！',function(){});
	 	}
} 
 function updatecustomer(){
	 var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.CUSTOMER_ID);
	    });
	 	if(runIds.length!=0){
	 		if(runIds.length>1){
	 			parent.layer.msg('只能选择一行数据',function(){});
	 		}else{
			var url=contextPath+"/customermanage/customerinfo/updatecustomer.jsp?customerId="+runIds[0];
			window.location=url;
	 		}
			}else{
	 		parent.layer.msg('请选择数据行',function(){});
	 	}
}
function updatetop(top,customerId){
	var topval="";
	if(top==1){
		topval=0;
	}else{
		topval=1;
	}
	var url=contextPath+"/customermanage/act/CustomerinfoAct/updatetopAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{customerId:customerId,
				top:topval	
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				init();
			}
	}
	});
}
function showcuctomer(customerId,status){
	var url=contextPath+"/customermanage/customerinfo/cusdetailed.jsp?customerId="+customerId+"&status="+status;
	  new SysFrame().tabs('update',{
		  title: "客户资料",
		  url:url
	  });
}