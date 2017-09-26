function dowork(runId,url)
    {
        var urls=contextPath+url;
      
        new SysFrame().tabs("update",{
            title:"<div  style=\"display:none;\" id="+runId+"></div >办理工作流",
            url:urls
        });
    }
    //查看工作流表单
    function read(runId,url)
    {
        var url=contextPath+url;
        var sysFrame=new SysFrame();
         if (sysFrame.tabs('exists',"<div  style=\"display:none;\" id="+runId+"></div >查看工作流")){ 
        	 sysFrame.tabs('select',"<div  style=\"display:none;\" id="+runId+"></div >查看工作流"); 
         }else
             {
             top.goUrl("<div  style=\"display:none;\" id="+runId+"></div >查看工作流",url);
             }
    }
function delWorkFlow(runId)
{
    var url=contextPath+"/tfd/system/workflow/flowrun/act/FlowRunAct/delFlowRunAct.act";
    $.ajax({
        url:url,
        dataType:"text",
        data:{runId:runId},
        async:false,
        error:function(e){
        },
        success:function(data){
            if(data=="1")
                {
                doinit();
                layer.msg('删除成功！');
            }else{
                doinit();
                layer.msg('删除失败！');
            }
    }
    });
}

function doinit(){
    ajaxLoading();
        $("#myTable").datagrid({
            width: "100%",
            rows:10,
            scrollbarSize :0,
            collapsible: true,
            url: contextPath+"/tfd/system/workflow/worklist/act/WorkListAct/getDoWorkListAct.act",
            method: 'POST',
            sortName: 'ID',
           sortOrder: 'DESC',
            striped: true,
            singleSelect:false,  
            remoteSort:true, 
           pagePosition:'top',
            pagination:true,
           queryParams:{
              module:'workflow',
              flowName:$("#flowName").val(),
              flowTitle:$("#flowTitle").val(),
              runId:$("#runId").val(),
             beginUser:$("#beginUser").val()
           },
           onLoadSuccess:function(data){  
        	   if(data.total>0)
               {
               $("#infotable").hide();
               }
              ajaxLoadEnd();
           },
            columns:[[
                {title: '序号', field: 'ID', width: '5%', align: 'center',sortable:true},
                {title: '流水号', field: 'RUN_ID', width: '15%', align: 'center',sortable:true},
                {title: '标题', field: 'TITLE', width: '30%', align: 'center',sortable:false,
                formatter:function(value,rowData,rowIndex){
                    if(rowData.TITLE!=null)
                        {
                    return "<a style=\"cursor: pointer;\" onclick=\"dowork('"+rowData.RUN_ID+"','"+rowData.URL+"');\">"+rowData.TITLE+"</a>";
                        }
                }               
                },
                {title: '步骤名称', field: 'PRCS_NAME', width: '10%', align: 'center',sortable:false},
                {title: '较交人', field: 'TRANSFER_USER_NAME', width: '15%', align: 'center',sortable:false},
                {title: '创建时间', field: 'CREATE_TIME', width: '10%', align: 'center',sortable:true},
                {title: '操作', field: 'OPT', width: '15%', align: 'center',sortable:false,
                    formatter:function(value,rowData,rowIndex){
                        if(rowData.RUN_PRCS_ID=="1")
                            {
                            return rowData.OPT+"<a style=\"cursor: pointer;\" onclick=\"delWorkFlow('"+rowData.RUN_ID+"')\">删除</a>";
                            }else
                                {
                            return  rowData.OPT;
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
    };
    
    function flowView(runId)
    {
        window.open(contextPath+"/system/workflow/flowgraph/historygraph/index.jsp?runId="+runId);
    }