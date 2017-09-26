function designForm(formId,leavePath)
{
    window.open(contextPath+"/system/workflow/form/design/designform.jsp?formId="+formId+"&leavePath="+leavePath);
}

function edit(formId)
{
    location.href=contextPath+"/system/workflow/form/manage.jsp?formId="+formId;
    }

function delForm(formId)
{
    var requestUrl=contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/delFormByFormIdAct.act";
    $.ajax({
        url:requestUrl,
        data:{formId:formId},
        dataType:"text",
        async:false,
        error:function(e){
            alert(e.message);
        },
        success:function(data){
            if(data=="1")
                {
                    layer.msg("表单删除成功！");
                }
            doint();
        }
    }); 
}


function moreOpt(flowTypeId)
{
    alert("more"+flowTypeId);
}

function doinit(){
	if(type=="null")
		{
        $("#myTable").datagrid({
            width: 'auto',
            height: 'auto',
            rows:10,
            scrollbarSize :0,
            collapsible: true,
            url: contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getFormListByWorkFlowTypeIdAct.act?workFlowTypeId="+workFlowTypeId,
            method: 'POST',
            sortName: 'ID',
            loadMsg: "数据加载中...",
            pagination:true,
            striped: true,
            singleSelect:true,  
            remoteSort:true, 
            columns:[[
                {title: '序号', field: 'ID', width: 50, align: 'center',sortable:true},
                {title: '表单编号', field: 'FORM_ID', width: 250, align: 'center',sortable:true,hidden:true},
                {title: '表单名称', field: 'FORM_NAME', width: 200, align: 'center',sortable:true},
                {title: '关联数据库', field: 'FORM_TABLE_NAME', width: 80, align: 'center',sortable:true},
                {title: '所属部门', field: 'DEPT_NAME', width: 150, align: 'center',sortable:true},
                {title: '创建者', field: 'FORM_CREATE_USER', width: 80, align: 'center',sortable:false,
                formatter:function(value,rowData,rowIndex){
                        if(rowData.FORM_CREATE_USER!=null)
                            {
                            return getUserName(rowData.FORM_CREATE_USER);
                            }else
                                {
                                return "无结果!";
                                }
                    }   
                },
                {title: '创建时间', field: 'FORM_CREATE_TIME', width: 80, align: 'center',sortable:true},
                {title: '所属模块', field: 'FORM_TYPE', width: 100, align: 'center',sortable:true},
                {title: '操作', field: 'OPT', width: 200, align: 'center',sortable:true}
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
		}else
			{
			var formName=parent.$("#formName").val();
			$("#myTable").datagrid({
	            width: 'auto',
	            height: 'auto',
	            rows:10,
	            queryParams:{formName:formName},
	            scrollbarSize :0,
	            collapsible: true,
	            url: contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getFormListByFormNameAct.act",
	            method: 'POST',
	            sortName: 'ID',
	            loadMsg: "数据加载中...",
	            pagination:true,
	            striped: true,
	            singleSelect:true,  
	            remoteSort:true, 
	            columns:[[
	                {title: '序号', field: 'ID', width: 50, align: 'center',sortable:true},
	                {title: '表单编号', field: 'FORM_ID', width: 250, align: 'center',sortable:true,hidden:true},
	                {title: '表单名称', field: 'FORM_NAME', width: 200, align: 'center',sortable:true},
	                {title: '关联数据库', field: 'FORM_TABLE_NAME', width: 80, align: 'center',sortable:true},
	                {title: '所属部门', field: 'DEPT_NAME', width: 150, align: 'center',sortable:true},
	                {title: '创建者', field: 'FORM_CREATE_USER', width: 80, align: 'center',sortable:false,
	                formatter:function(value,rowData,rowIndex){
	                        if(rowData.FORM_CREATE_USER!=null)
	                            {
	                            return getUserName(rowData.FORM_CREATE_USER);
	                            }else
	                                {
	                                return "无结果!";
	                                }
	                    }   
	                },
	                {title: '创建时间', field: 'FORM_CREATE_TIME', width: 80, align: 'center',sortable:true},
	                {title: '所属模块', field: 'FORM_TYPE', width: 100, align: 'center',sortable:true},
	                {title: '操作', field: 'OPT', width: 200, align: 'center',sortable:true}
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
    };