<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String deptId=request.getParameter("deptId");%>
<html>
<head>
<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
	
<script type="text/javascript">
var deptId='<%=deptId%>';
$(function(){
   query();
});
function query(){
	 $("#myTable").datagrid({
	        width:'auto',
			height: 'auto',
	        collapsible: true,
	        scrollbarSize :0,
	        url: "<%=contextPath %>/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoListAct.act?deptId=<%=deptId %>",
	        method: 'POST',
	        sortName: 'id',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true,  
	        columns:[[
	            {title: '序号', field: 'ID', width: 100, align: 'center',sortable:true},
	            {title: '账号', field: 'ACCOUNT_ID', width: 100, align: 'center',sortable:true},
	            {title: '姓名', field: 'USER_NAME', width: 100, align: 'center',sortable:true},
	            {title: '性别', field: 'SEX', width: 50, align: 'center',sortable:true},
	            {title: '权限组', field: 'USER_PRIV_NAME', width: 100, align: 'center',sortable:true},
	            {title: '行政级别', field: 'LEAD_LEAVE', width: 100, align: 'center',sortable:true,
	            	 formatter:function(value,rowData,rowIndex){
	            		 if(getLeaveName(rowData.LEAD_LEAVE)==""){
	            			 return"无";
	            		 }else
	            			 {
	         			return getLeaveName(rowData.LEAD_LEAVE);
	            			 }
	         		}
	            },
	            {title: '上级领导', field: 'LEAD_ID', width: 100, align: 'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	           		 if(getLeadName(rowData.LEAD_ID)==""){
	           			 return"无";
	           		 }else
	           			 {
	        			return getLeadName(rowData.LEAD_ID);
	           			 }
	        		}	
	            },
	            {title: '部门', field: 'DEPT_NAME', width: 150, align: 'center',sortable:true},
	            {title: '操作', field: 'OPT', width: 200, align: 'center',sortable:true}
	        ]]
	    });
	     
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 5,//每页显示的记录条数，默认为5  
	        pageList: [5, 10],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  
}
function del(accountId)
{
	var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/delAccountIdByIdAct.act";
	var paramData={accountId:accountId};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data!=0)
				{
				alert("注销账号成功!");
				query();
				}else {
					alert("注销账号失败！");
				}
			
		}
	});
}
function edit(accountId)
{
	parent["edit"].location=contextPath+"/system/unit/userinfo/edituserinfo.jsp?accountId="+accountId;
}
function add()
{
	parent["edit"].location=contextPath+"/system/unit/userinfo/adduser.jsp?deptId="+deptId;
}

function getLeaveName(leaveId)
{
	var returnData="";
	var paramData={leaveId:leaveId};
	var requestUrl=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/getLeaveNameByLeaveIdAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			returnData=data;
		}
	});
	return returnData;
}
function getLeadName(leadId)
{
	var returnData="";
	var paramData={accountIdStr:leadId};
	var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/getUserNameStrAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			returnData=data;
		}
	});
	return returnData;
}
</script>
</head>
<body style="margin-top: 10px;">
<div align="center">
<div style="width: 90%;">
<div class="widget-header bg-blueberry">
<span class="widget-caption">人员列表</span>
</div>
<div id="myTable" name="myTable" ></div>
</div>
</div>
</body>
</html>