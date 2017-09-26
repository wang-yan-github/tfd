$(function(){
    $("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: contextPath+"/meeting/act/BoardroomdeviceAct/lookdeviceAct.act",
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
           	{title: '设备编号', field: 'DEVICE_ID', width: '25%', align: 'center',sortable:true},
           	{title: '设备名称', field:'DEVICE_NAME', width: '12%' ,align :'center' ,sortable:true },
            {title: '所属会议室', field: 'BOARDROOM_NAME', width: '10%', align: 'center',sortable:true},
            {title: '设备状态', field: 'DEVICE_STATUS', width: '10%', align: 'center',sortable:true,
            	formatter: function (value, rowData, rowIndex) {
                    if(rowData.DEVICE_STATUS=="1"){
                    	return "可用";
                    }else{
            		return "不可用";
                    }
                }	
            },
            {title: '设备描述', field: 'DEVICE_DESCRIPTION', width: '20%', align: 'center',sortable:true},
            {title: '操作', field: 'OPT', width: '15%', align: 'center'}
        ]]
    });
     
    var p = $('#myTable').datagrid('getPager');  
        $(p).pagination({  
        pageSize: 10,
        pageList: [10, 20, 30 ,50],
        beforePageText: '第', 
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });  
});
function updatedevice(boardroomdeviceId){
	var url=contextPath+"/metting/boardroomdevice/updateboardroomdevice.jsp?boardroomdeviceId="+boardroomdeviceId;
	window.location=url;
}
function deldevice(boardroomdeviceId){
	if(confirm("确认删除？")){
		var url=contextPath+"/meeting/act/BoardroomdeviceAct/deldeviceAct.act";
		$.ajax({
			   url:url,
			   type:'post',
		   		async:false,
		   		data:{boardroomdeviceId:boardroomdeviceId},
		   		success:function(data){
		   			if(data!=0){
		   			parent.layer.msg('删除成功！');
		   			window.location.reload();
		   			}
		   		}
		   });
	}
}
function Newsdevice(){
	var url=contextPath+="/metting/boardroomdevice/Newboardroomdevice.jsp";
	window.location=url;
}
