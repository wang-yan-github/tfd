<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String sortId=request.getParameter("sortId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作流列表</title>
<script type="text/javascript">
var workFlowTypeId="<%=sortId%>";
function designFlow(flowTypeId)
{
	window.open(contextPath+"/system/workflow/flowprocess/designflow/index.jsp?flowTypeId="+flowTypeId);
}

function edit(flowTypeId)
{
	location.href=contextPath+"/system/workflow/workflow/manage.jsp?flowTypeId="+flowTypeId;
	}

function delFlow(flowTypeId)
{
	 var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/delWorkFlowByFlowTypeIdAct.act?flowTypeId="+flowTypeId;
		$.ajax({
			url:requestUrl,
			dataType:"text",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="1")
					{
					layer.msg("删除成功!");
					}else
						{
						layer.msg("流程正在使用中,请删除流程关联数据!");
						}
				doint();
			}
		});
}

function clearData(flowTypeId)
{
	 var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/clearFlowDataAct.act?flowTypeId="+flowTypeId;
		$.ajax({
			url:requestUrl,
			dataType:"text",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data!="0")
					{
					layer.msg("初始化成功!");
					}else
						{
						layer.msg("初始化失败!");
						}
				doint();
			}
		});
}
function moreOpt(flowTypeId)
{
	location.href=contextPath+"/system/workflow/workflow/managepriv.jsp?flowTypeId="+flowTypeId;
}

function doint(){
	    $("#myTable").datagrid({
			height: 'auto',
			rows:5,
	        collapsible: true,
	        url: "<%=contextPath%>/tfd/system/workflow/workflow/act/WorkFlowAct/getListByWorkFlowTypeIdAct.act?workFlowTypeId="+workFlowTypeId,
	        method: 'POST',
	        sortName: 'ID',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        rownumbers:true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
				{title: '序号', field: 'ID', width: '5%', align: 'center',sortable:true},
	            {title: '编号', field: 'FLOW_TYPE_ID', width: '20%', align: 'center',sortable:true},
	           	{title: '流程名称', field: 'FLOW_NAME', width: '10%', align: 'center',sortable:true},
	            {title: '流程类型', field: 'FLOW_TYPE', width: '10%', align: 'center',sortable:true,
	           		formatter:function(value,rowData,rowIndex){
	            		if(rowData.FLOW_TYPE=="1")
	            			{
	            			return "固定流程";
	            			}else if(rowData.FLOW_TYPE=="2")
	            				{
	            				return "自由流程";
	            				}else if(rowData.FLOW_TYPE=="3")
	            				{
	            					return "特殊流程";	
	            				}
	            	}
	            },
	           	{title: '功能模块', field: 'MODULE_ID', width: '8%', align: 'center',sortable:true},
	            {title: '创建者', field: 'USER_NAME', width: '10%', align: 'center',sortable:true},
	            {title: '机构', field: 'ORG_ID', width: '5%', align: 'center',sortable:true},
	            {title: '操作', field: 'OPT', width: '25%', align: 'center',sortable:false}
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



</script>
</head>
<body style="margin-top: 10px;" onload="doint();">
<div id="myTable" name="myTable"></div>
</body>
</html>