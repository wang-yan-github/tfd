$(function(){
	doinit();
     $('#add').click(function(){
    	$('.update_content').attr("style","display:none");
    	$('.add_content').attr("style","display:block");
    	$('.child_content').attr("style","display:none");
    	$('.child_add_content').attr("style","display:none");
    	$('.child_update_content').attr("style","display:none");
     });
     $('#add_child').click(function(){
    	$('.update_content').attr("style","display:none");
     	$('.add_content').attr("style","display:none");
     	$('.child_content').attr("style","display:none");
     	$('.child_add_content').attr("style","display:block");
     	$('.child_update_content').attr("style","display:none");
     	getSelectAdd($('#child-add-id').val());
     });
     $('.btn_back').click(function(){
    	 $('.update_content').attr("style","display:none");
     	$('.add_content').attr("style","display:none");
     	$('.child_content').attr("style","display:none");
     	$('.child_add_content').attr("style","display:none");
     	$('.child_update_content').attr("style","display:none");
     });
      $('.btn_back_child').click(function(){
    	$('.update_content').attr("style","display:none");
     	$('.add_content').attr("style","display:none");
     	$('.child_content').attr("style","display:block");
     	$('.child_add_content').attr("style","display:none");
     	$('.child_update_content').attr("style","display:none");
     });
});
function doinit(){
	var urls =contextPath+ '/tfd/system/code/act/CodeAct/getCodeList.act';
	$('#myTable').datagrid({
		url:urls,
		width:'100%',
		height:'100%',
		columns:[[
					 {field:'CODE_NAME',title:'名称',align:'center',width:'40%',},
					 {field:'aa',title:'编辑',align:'center',width:'20%',
						formatter:function(value, rowData, rowIndex){
							return "<a href='javascript:void(0)' onclick=javascript:showupdate('"+rowData.CODE_ID+"') >编辑</a>";
						}	 
					 },
					 {field:'ss',title:'下一级',align:'center',width:'25%',
						formatter:function(value, rowData, rowIndex){
							return "<a href='javascript:void(0)' onclick=javascript:getChild('"+rowData.CODE_ID+"') >下一级</a>";
						}	 
					 },
					 {field:'dd',title:'删除',align:'center',width:'20%',
						formatter:function(value, rowData, rowIndex){
							return "<a href='javascript:void(0)' onclick=javascript:delCode('"+rowData.CODE_ID+"') >删除</a>";
						}	 
					 }
				]],
		collapsible: true,
		method: 'POST',
		pagination:true,
		striped: true,
        singleSelect:true,  
        remoteSort:true, 
	});
	 var p = $('#myTable').datagrid('getPager');  
     $(p).pagination({  
	     pageSize: 10,//每页显示的记录条数，默认为10  
	     pageList: [5, 10, 15 ,20],//可以设置每页记录条数的列表  
	     beforePageText: '第',//页数文本框前显示的汉字  
	     afterPageText: '页    共 {pages} 页',  
	     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
     }); 
}
function showupdate(id){
	$('.update_content').attr("style","display:block");
	$('.add_content').attr("style","display:none");
	$('.child_content').attr("style","display:none");
	$('.child_add_content').attr("style","display:none");
	$('.child_update_content').attr("style","display:none");
	var requestUrl=contextPath+ '/tfd/system/code/act/CodeAct/getCodeById.act';
	$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#update-id').val(data[0].id);
				$('#update-name').val(data[0].name);
		    	$('#update-value').val(data[0].value);
			}
	});
}
function delCode(id){
	if(confirm("确定删除?")){
		var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/delCode.act?';
     	$.ajax({
     			url:requestUrl,
     			data:{id:id},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				getChild(id);
     				doinit();
     				
     			}
     	});
	}
}
function delCodeChild(id,pId){
	if(confirm("确定删除?")){
		var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/delCode.act?';
     	$.ajax({
     			url:requestUrl,
     			data:{id:id},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				getChild(pId);
     			}
     	});
	}
}
function getChild(id){
	$('.update_content').attr("style","display:none");
	$('.add_content').attr("style","display:none");
	$('.child_content').attr("style","display:block");
	$('.child_add_content').attr("style","display:none");
	$('.child_update_content').attr("style","display:none");
	$('#trtitle').html("");
	var requestUrl= contextPath+'/tfd/system/code/act/CodeAct/findChild.act';
	$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#child-add-id').val(id);
				if($.isEmptyObject(data)){
				}else{
					var str = "<tr><td align='center' width='50%'>名称</td><td align='center' width='25%' >编辑</td><td align='center' width='25%'>删除</td></tr>";
					$.each(data,function(index,data){
						str += "<tr><td align='center' width='50%'>"+data.name+"</td><td align='center' width='25%'><a href='javascript:void(0)' onclick=javascript:showChildUpdate('"+data.id+"') >编辑</a></td><td align='center' width='25%'><a href='javascript:void(0)' onclick=javascript:delCodeChild('"+data.id+"','"+data.pId+"') >删除</a></td></tr>";	
					});
					$('#trtitle').html(str);
				}
			}
	});
}
function showChildUpdate(id){
	$('#child-update-id').val("");
	$('#child-update-pId').val("");
	$('#child-update-name').val("");
	$('#child-update-value').val("");
	$('.update_content').attr("style","display:none");
	$('.add_content').attr("style","display:none");
	$('.child_content').attr("style","display:none");
	$('.child_add_content').attr("style","display:none");
	$('.child_update_content').attr("style","display:block");
	getSelectUpdate(id);
	var requestUrl=contextPath+ '/tfd/system/code/act/CodeAct/getCodeById.act';
	$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#child-update-id').val(data[0].id);
				$('#child-update-pId').val(data[0].pId);
				$('#child-update-name').val(data[0].name);
		    	$('#child-update-value').val(data[0].value);
			}
	});
}
function getSelectAdd(id){
	var requestUrl = contextPath+'/tfd/system/code/act/CodeAct/getCodeList.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var row = data.rows;
			var str = "<select class='form-control' id='child-add-pId'  >";
			$.each(row,function(index,row){
				if(id == row.CODE_ID){
					str += "<option value='"+row.CODE_ID+"' selected='selected' >"+row.CODE_NAME+"</option>";
				}else{
					str += "<option value='"+row.CODE_ID+"' >"+row.CODE_NAME+"</option>";	
				}
			});
			str += "</select>";
			$('#select-parent-add').html(str);
		}
	});
}
function getSelectUpdate(id){
	var requestUrl = contextPath+'/tfd/system/code/act/CodeAct/getCodeList.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var row = data.rows;
			var str = "<select class='form-control' id='child-update-pId'  >";
			$.each(row,function(index,row){
				if(id == row.CODE_ID){
					str += "<option value='"+row.CODE_ID+"' selected='selected' >"+row.CODE_NAME+"</option>";
				}else{
					str += "<option value='"+row.CODE_ID+"' >"+row.CODE_NAME+"</option>";	
				}
				
			});
			str += "</select>";
			$('#select-parent-update').html(str);
		}
	});
}

 function btn_add(){
    	var name = $('#add-name').val();
    	var value = $('#add-value').val();
    	var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/addCode.act?';
    	$.ajax({
    			url:requestUrl,
    			data:{name:name,value:value},
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				doinit();
    			}
    	});
 };
 
 function btn_update(){
    	var name = $('#update-name').val();
     	var value = $('#update-value').val();
     	var id = $('#update-id').val();
     	var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/updateCode.act?';
     	$.ajax({
     			url:requestUrl,
     			data:{id:id,name:name,value:value},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				doinit();
     			}
     	});
};
function btn_add_child(){
    	var pId = $('#child-add-pId').val();
    	var name = $('#child-add-name').val();
     	var value = $('#child-add-value').val();
     	var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/addCode.act?';
     	$.ajax({
     			url:requestUrl,
     			data:{pId:pId,name:name,value:value},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				$('#child-add-pId').val("");
     		    	$('#child-add-name').val("");
     		     	$('#child-add-value').val("");
     				getChild(pId);
     			}
     	});
     };
     
function btn_update_child(){
    	var pId = $('#child-update-pId').val();
    	var name = $('#child-update-name').val();
     	var value = $('#child-update-value').val();
     	var id = $('#child-update-id').val();
     	var requestUrl=contextPath+'/tfd/system/code/act/CodeAct/updateCode.act?';
     	$.ajax({
     			url:requestUrl,
     			data:{id:id,pId:pId,name:name,value:value},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				getChild(pId);
     			}
     	});
     };