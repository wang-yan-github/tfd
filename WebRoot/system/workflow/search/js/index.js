function queryworkflow(){
    ajaxLoading();
        $("#myTable").datagrid({
            width: "100%",
            rows:10,
            scrollbarSize :0,
            collapsible: true,
            url : contextPath + "/core/system/workflow/search/act/SearchAct/querywfAct.act",
            method: 'POST',
            sortName: 'ID',
           sortOrder: 'DESC',
            striped: true,
            singleSelect:false,  
            remoteSort:true, 
           pagePosition:'top',
            pagination:true,
           queryParams : {
            runId : $("#runId").val(),
            flowName : $("#flowName").val(),
            flowType : $("#flowType").val(),
            runName : $("#runName").val(),
            flowStatus : $("#flowStatus").val(),
            flowManage : $("#flowManage").val(),
            beginUserId : $("#beginUserId").val(),
            beginTime : $("#beginTime").val(),
            endTime : $("#endTime").val()
        },
           onLoadSuccess:function(data){  
              if(data.total>0)
              {
              $("#infotable").hide();
              }
              ajaxLoadEnd();
           },
            columns : [[{
            title : '全选',
            field : '',
            width : '2%',
            align : 'center',
            sortable : true,
            checkbox : true
        }, {
            title : '序号',
            field : 'ID',
            width : '5%',
            align : 'center',
            sortable : true
        }, {
            title : '流水号',
            field : 'RUN_ID',
            width : '20%',
            align : 'center',
            sortable : true
        }, {
            title : '标题',
            field : 'TITLE',
            width : '25%',
            align : 'left',
            sortable : true
        },
        {
            title : '当前步骤',
            field : 'PRCS_NAME',
            width : '10%',
            align : 'center',
            sortable : false,
            },
        {
            title : '状态',
            field : 'STATUS',
            width : '5%',
            align : 'center',
            sortable : true,
            formatter : function(value, rowData, rowIndex) {
                if (rowData.STATUS == 0) {
                    return '进行中';
                } else if (rowData.STATUS == 1) {
                    return '已结束';
                }
            }
        }, {
            title : '创建时间',
            field : 'BEGIN_TIME',
            width : '15%',
            align : 'center',
            sortable : true
        }, {
            title : '操作',
            field : 'OPT',
            width : '18%',
            align : 'center',
            sortable : false,
            formatter : function(value, rowData, rowIndex) {
            	if(rowData.DOSTATUS=="0")
            		{
            		return rowData.OPT+"&nbsp;<a onclick=\"del('"+rowData.RUN_ID+"');\">删除</a>";
            		}else
            			{
            			return rowData.OPT + "<a onclick=\"flowView('"+rowData.RUN_ID+"')\">流程图</a>&nbsp;<a>关注</a>&nbsp;<a onclick=\"todowork('"+rowData.RUN_ID+"');\">催办</a>";
            			}
            }
        }]]
        });
         
        var p = $('#myTable').datagrid('getPager');  
            $(p).pagination({  
            pageSize: 10,//每页显示的记录条数，默认为5  
            pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
            beforePageText: '第',//页数文本框前显示的汉字  
            afterPageText: '页    共 {pages} 页',  
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        });  
    };

function read(runId, url) {
    var url = contextPath + url;
    new SysFrame().tabs('update', {
        title : "查看工作流",
        url : url
    });
}

function del(runId) {
    var checkedItems = $('#myTable').datagrid('getChecked');
    var runIds = [];
    $.each(checkedItems, function(index, item) {
    	if(item.DOSTATUS=="0")
    	{
    		runIds.push(item.RUN_ID);
    	}
        
    });
    if(runId == undefined || runId == "" || runId == null)
    	{
    	 if (runIds == undefined || runIds == "" || runIds == null) {
    	        layer.msg(" 无可删除的流程！请选择一条流程！");
    	        return false;
    	    }
    	}else
    		{
    		runIds.push(runId);
    		}
   
    if (confirm("确定删除吗")) {
        var requestUrl = contextPath + "/core/system/workflow/maintenance/act/MaintenanceAct/delWorkFlowAct.act";
        $.ajax({
            url : requestUrl,
            traditional : true,
            data : {
                "runIds" : runIds
            },
            type : 'POST',
            dataType : "text",
            async : false,
            error : function(e) {
                alert(e.message);
            },
            success : function(data) {
                if (data > 0) {
                    queryworkflow();
                    layer.msg('删除成功');
                }else
                {
                    queryworkflow();
                    layer.msg('删除失败');
                }
            }
        });
    } else {
        return false;
    }
}
function flowView(runId)
{
    window.open(contextPath+"/system/workflow/flowgraph/historygraph/index.jsp?runId="+runId);
}
function senior()
{
    location.href="senior.jsp";
}


function todowork(runId)
{
	getMessagePriv("todowork");
	$("#smsContent").val("");
	 $('#todoworkmodal').modal({backdrop: 'static', keyboard: false});
	    $('#todoworkmodal').on('shown.bs.modal',function(){
	    $("#todoworkbut").unbind('click').click(function (){
	    var smsRemind=getsmsRemind();
	    var smsContent=$("#smsContent").val();	
	    var requestUrl = contextPath + "/core/system/workflow/flowrun/act/FlowRunAct/toDoWorkAct.act";
        $.ajax({
            url : requestUrl,
            traditional : true,
            data : {
                "runId" : runId,
                "smsRemind":smsRemind,
                "smsContent":smsContent
            },
            type : 'POST',
            dataType : "text",
            async : false,
            error : function(e) {
                alert(e.message);
            },
            success : function(data) {
            }
        });
	    
	    
		});
			
	 });
	}