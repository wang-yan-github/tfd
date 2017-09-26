<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程监控</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/monitor.css"></link>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript">
function queryworkflow()
{
			$("#myTable").datagrid({
				 			rows:10,
				 			scrollbarSize :0,
				 	        collapsible: true,
				 	        url: contextPath+"/tfd/system/workflow/monitor/act/MonitorAct/getMonitorFlowAct.act",
				 	        method: 'POST',
				 	        sortName: 'ID',
				 	       sortOrder: 'DESC',
				 	       queryParams:{
				 	    	  		flowName:$("#flowName").val(),
				 	    	  		flowTitle:$("#flowTitle").val(),
				 	    	  		runId:$("#runId").val(),
				 	    	  		beginUserId:$("#beginUserId").val(),
				 	    	  		status:$("#status").val()
							},
				 	        loadMsg: "数据加载中...",
				 	        pagination:true,
				 	        striped: true,
				 	        singleSelect:true,  
				 	        remoteSort:true, 
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
				 	            {title: '序号', field: 'ID', width: '5%', align: 'center',sortable:false},
				 	           	{title: '流水号', field: 'RUN_ID', width: '20%', align: 'center',sortable:true},
				 	            {title: '标题', field: 'TITLE', width: '30%', align: 'center',sortable:true},
				 	           	{title: '状态', field: 'STATUS', width: '10%', align: 'center',sortable:true,
				 	            	formatter:function(value,rowData,rowIndex){
				 	       			if(rowData.STATUS==0){
				 	       				return '进行中';
				 	       			}else if(rowData.STATUS==1){
				 	       				return '已结束';
				 	       			}
				 	       		}		
				 	           	
				 	           	},
				 	            {title: '创建时间', field: 'BEGIN_TIME', width: '15%', align: 'center',sortable:true},
				 	            {title: '操作', field: 'OPT', width: '20%', align: 'center',sortable:false}
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

function read(runId,url)
{
	var url="<%=contextPath%>"+url;
	new SysFrame().tabs('update',{
		title: "查看工作流",
		url: url
	});
}
	
	function doprocess(runId)
	{
		window.location.href='doprocess.jsp?runId='+runId;
	}
</script>
</head>
<body style="overflow-y: hidden;margin:0px">
<img  src="<%=imgPath%>/workflow/monitor.png" style="width: 40px;height: 40px;padding-left: 5px;font-size: 14px;"><span style="padding-left: 5px;">流程监控</span>
<div class="list-group-item"  style="padding: 0px;">
   <table class="table table-striped table-condensed">
   <tr>
   <td width="150px">流程名称：</td>
   <td><div class="col-xs-10"><input type="text" class="form-control" id="flowName" name="flowName"></div></td>
   <td width="150px">流程标题：</td>
   <td><div class="col-xs-10"><input type="text" class="form-control" id="flowTitle" name="flowTitle"></div></td>
    <td width="150px">流水号：</td>
   <td><div class="col-xs-10"><input type="text" class="form-control" id="runId" name="runId"></div></td>
   </tr>
   <tr>
   <td width="150px">发起人：</td>
   <td>
   <div class="col-xs-10">
   <input type="hidden" class="form-control" id="beginUserId" name="beginUserId">
   <input  placeholder="请选择人员..." /  type="text" class="form-control" id="beginUserName" name="beginUserName" onclick="userinit(['beginUserId','beginUserName']);" readonly="readonly"></div></td>
   <td width="150px">流程状态：</td>
   <td>
   <div class="col-xs-10"><select id="status" name="status" class="form-control">
   <option value="">全部</option>
   <option value="0">执行</option>
   <option value="1">结束</option>
   </select></div>
   </td>
   <td colspan="2">
   <div align="center"><button type="button" onclick="queryworkflow();" class="btn btn-primary">查询</button></div>
   </td>
   </tr>
   </table>

</div>


<div class="well with-header with-footer">
<div class="header bg-warning">查询结果</div>
	<div id="myTable" name="myTable" >
	 <table class="MessageBox" style="margin-top:3px;" align="center" width="440" cellpadding="0" cellspacing="0" id="infotable">
									   <tbody><tr class="head-no-title">
									      <td class="left"></td>
									      <td class="center">
									      </td>
									      <td class="right"></td>
									   </tr>
									   <tr class="msg">
									      <td class="left"></td>
									      <td class="center info">
									         <div class="msg-content">暂无信息！</div>
									      </td>
									      <td class="right"></td>
									   </tr>
									   <tr class="foot">
									      <td class="left"></td>
									      <td class="center"><b></b></td>
									      <td class="right"></td>
									   </tr>
									   </tbody>
							</table>
	</div>
</div>

<div id="modaldialog"></div>
</body>
</html>