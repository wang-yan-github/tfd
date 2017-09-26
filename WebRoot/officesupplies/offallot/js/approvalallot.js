var resFormat;
var resType;
var resUnit;
var resPrice;
var attachId;
var resSupplier;
var minStock;
var userPriv;
var maxStock;
var deptPriv;
var resName;
var approveStaff;
var dispatchStaff;
var beforeStock;
var libraryId;
var classifyId;
$(function(){
	getallot();
	});
	function getallot(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffallotAct/getnotallotAct.act",
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
	            {title: '操作', field: 'OPT', width: '20%', align: 'center'}
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
	function approvalallot(allotId,resId){
		var url=contextPath+"/officesupplies/act/OffresAct/getIdresAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
		   		resFormat=data.resFormat;
		   		resType=data.resType;
		   		resUnit=data.resUnit;
		   		resPrice=data.resPrice;
		   		attachId=data.attachId;
		   		resSupplier=data.resSupplier;
		   		minStock=data.minStock;
		   		userPriv=data.userPriv;
		   		maxStock=data.maxStock;
		   		deptPriv=data.deptPriv;
		   		resName=data.resName;
		   		var url=contextPath+"/officesupplies/act/OffallotAct/getIdallotAct.act";
				$.ajax({
					url:url,
					type:"POST",
					data:{allotId:allotId},
					dataType:"json",
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data!=""){
							approveStaff=data.depotStaff;
							dispatchStaff=data.allotStaff;
							beforeStock=data.allotNum;
							libraryId=data.libraryId;
							classifyId=data.classifyId;
							addres(allotId,resId);
						}
				}
				});
		}
		});
	}
	function addres(allotId,resId){
		var url=contextPath+"/officesupplies/act/OffresAct/addresAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{
				resFormat:resFormat,
				resType:resType,
				resUnit:resUnit,
				resPrice:resPrice,
				attachId:attachId,
				resSupplier:resSupplier,
				minStock:minStock,
				maxStock:maxStock,
				userPriv:userPriv,
				deptPriv:deptPriv,
				resName:resName,
				approveStaff:approveStaff,
		   		dispatchStaff:dispatchStaff,
		   		beforeStock:beforeStock,
		   		libraryId:libraryId,
		   		classifyId:classifyId
			},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					getredNum(allotId,resId);
				}
		}
		});
	}
	function getredNum(allotId,resId){
		var url=contextPath+"/officesupplies/act/OffresAct/prrupdateresNumAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{resId:resId,maintNum:beforeStock},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					updatestatus(allotId,1);
				}
		}
		});
	}
	function updatestatus(allotId,allotstatus){
		var url=contextPath+"/officesupplies/act/OffallotAct/updateStatusAct.act";
		$.ajax({
			url:url,
			type:"POST",
			data:{allotId:allotId,allotStatus:allotstatus},
			dataType:"json",
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data!=0){
					parent.layer.msg('审批成功！');
					getallot();
				}
		}
		});
	}
	function notapprovalallot(allotId,resId){
		updatestatus(allotId,2);
		getallot();
	}