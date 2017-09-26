function queryworkflow()
{
            $("#myTable").datagrid({
                            width: 'auto',
                            hight:'auto',
                           scrollbarSize :0,
                            rows:10,
                            collapsible: true,
                            url: contextPath+"/tfd/system/workflow/maintenance/act/MaintenanceAct/querywfAct.act",
                            method: 'POST',
                            sortName: 'ID',
                           sortOrder: 'DESC',
                           queryParams:{
                                    runId:$("#runId").val(),
                                    flowName:$("#flowName").val(),
                                    flowType:$("#flowType").val(),
                                    runName:$("#runName").val(),
                                    flowStatus:$("#flowStatus").val(),
                                    flowManage:$("#flowManage").val(),
                                    beginUserId:$("#beginUserId").val(),
                                    beginTime:$("#beginTime").val(),
                                    endTime:$("#endTime").val()
                            },
                            loadMsg: "数据加载中...",
                            pagination:true,
                            striped: true,
                            singleSelect:true,  
                            remoteSort:true, 
                           singleSelect:false,  
                           checkOnSelect: false,
                           selectOnCheck: true,
                            onLoadSuccess: function (data) {
                                $("#infotable").remove();
                            },
                        onClickCell: function (rowIndex, field, value) {
                            IsCheckFlag = false;
                        },
                        onSelect: function (rowIndex, rowData) {
                            if (!IsCheckFlag) {
                                IsCheckFlag = true;
                                $("#myTable").datagrid("unselectRow", rowIndex);
                            }
                         },                    
                         onUnselect: function (rowIndex, rowData) {
                             if (!IsCheckFlag) {
                                 IsCheckFlag = true;
                                $("#myTable").datagrid("selectRow", rowIndex);
                             }
                        },
                            columns:[[
                                {title: '全选', field: '', width: '2%', align: 'center',sortable:true,checkbox:true},
                                {title: '序号', field: 'ID', width: '5%', align: 'center',sortable:true},
                                {title: '流水号', field: 'RUN_ID', width: '20%', align: 'center',sortable:true},
                                {title: '标题', field: 'TITLE', width: '30%', align: 'left',sortable:true},
                                {title: '状态', field: 'STATUS', width: '10%', align: 'center',sortable:true,checkOnSelect:false,
                                    formatter:function(value,rowData,rowIndex){
                                    if(rowData.STATUS==0){
                                        return '进行中';
                                    }else if(rowData.STATUS==1){
                                        return '已结束';
                                    }
                                }       
                                
                                },
                                {title: '创建时间', field: 'BEGIN_TIME', width: '15%', align: 'center',sortable:true},
                                {title: '操作', field: 'OPT', width: '18%', align: 'center',sortable:false,
                                formatter:function(value,rowData,rowIndex){
                                                var html= rowData.OPT+"<a onclick=\"flowView('"+rowData.RUN_ID+"')\">流程图</a>&nbsp;"+
                                                "<a>关注</a>&nbsp;<a onclick=\"reminders('"+rowData.RUN_ID+"')\">催办</a>&nbsp;<a onclick=\"delflow('"+rowData.RUN_ID+"')\">删除</a>";
                                                if(rowData.STATUS==0){
                                                    html+="&nbsp;<a onclick=\"doend('"+rowData.RUN_ID+"');\">结束</a>";
                                                }
                                               return html;              
                                             }
                                }
                            ]]
                        });
                         
                        var p = $('#myTable').datagrid('getPager');  
                            $(p).pagination({  
                            pageSize: 10,//每页显示的记录条数，默认为10  
                            pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
                            beforePageText: '第',//页数文本框前显示的汉字  
                            afterPageText: '页    共 {pages} 页',  
                            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
                        }); 
}
function reminders(runId)
{
    var requestUrl=contextPath+"/tfd/system/workflow/flowrun/act/FlowRunAct/remindersAct.act";
                    $.ajax({
                        url:requestUrl,
                        traditional: true,
                        data:{"runId":runId},
                        type:'POST',
                        dataType:"json",
                        async:false,
                        error:function(e){
                            alert(e.message);
                        },
                        success:function(data){
                            layer.msg("已通知"+data.userName+"等相关人员！");
                        }
                    });
}
function read(runId,url)
{
    var url=contextPath+url;
	new SysFrame().tabs('update',{
		title: "查看工作流",
		url:url
	});
}
 function delflow(runId)
 {
     var runIds=[];
     runIds.push(runId);
      if(confirm("确定删除吗"))
    {
             var requestUrl=contextPath+"/tfd/system/workflow/maintenance/act/MaintenanceAct/delWorkFlowAct.act";
                    $.ajax({
                        url:requestUrl,
                        traditional: true,
                        data:{"runIds":runIds},
                        type:'POST',
                        dataType:"text",
                        async:false,
                        error:function(e){
                            alert(e.message);
                        },
                        success:function(data){
                            if(data>0){
                                queryworkflow(); 
                                layer.msg("删除成功！");                             
                            }
                        }
                    });
    }else
    {
        return false;
    }
 }
    
function del()
{
    var checkedItems = $('#myTable').datagrid('getChecked');
    var runIds = [];
    $.each(checkedItems, function(index, item){
        runIds.push(item.RUN_ID);
    }); 
    if(runIds==undefined||runIds==""||runIds==null)
    {
        layer.msg("请选择一条流程!");
        return false;
    }
    if(confirm("确定删除吗？"))
    {
             var requestUrl=contextPath+"/tfd/system/workflow/maintenance/act/MaintenanceAct/delWorkFlowAct.act";
                    $.ajax({
                        url:requestUrl,
                        traditional: true,
                        data:{"runIds":runIds},
                        type:'POST',
                        dataType:"text",
                        async:false,
                        error:function(e){
                            alert(e.message);
                        },
                        success:function(data){
                            if(data>0){
                                queryworkflow();   
                                layer.msg("删除成功！");                             
                            }
                        }
                    });
    }else
    {
        return false;
    }
}

    function flowView(runId)
    {
        window.open(contextPath+"/system/workflow/flowgraph/historygraph/index.jsp?runId="+runId);
    }
    
function doend(runId)
{
if(confirm("确定结束流程吗？"))
    {
             var requestUrl=contextPath+"/tfd/system/workflow/flowrun/act/FlowRunAct/endFlowRunByIdAct.act";
                    $.ajax({
                        url:requestUrl,
                        traditional: true,
                        data:{"runId":runId},
                        type:'POST',
                        dataType:"json",
                        async:false,
                        error:function(e){
                            alert(e.message);
                        },
                        success:function(data){
                            if(data.isEnd=="END")
                            {
                                layer.msg("操作成功！");
                                queryworkflow();
                            }
                        }
                    });
    }else
    {
        return false;
    }
}
