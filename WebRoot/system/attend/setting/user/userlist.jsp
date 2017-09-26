<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String deptId=request.getParameter("deptId");%>
<html>
<head>
<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
var deptId='<%=deptId%>';
var deptName = "";
$(function(){
   query();
});
function query(){
	ajaxLoading();
	 $("#myTable").datagrid({
	        width:'auto',
			height: 'auto',
	        collapsible: true,
	        scrollbarSize :0,
	        url: "<%=contextPath %>/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoListAct.act?deptId=<%=deptId %>",
	        method: 'POST',
	        sortName: 'id',
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        onClickRow: function (rowIndex, rowData) {
                $(this).datagrid('unselectRow', rowIndex);
            },
	        remoteSort:true,  
	        columns:[[
	            {title: '序号', field: 'ID', width: '12%', align: 'center',sortable:true},
	            {title: '姓名', field: 'USER_NAME', width: '24%', align: 'center',sortable:true},
	            {title: '部门', field: 'DEPT_NAME', width: '36%', align: 'center',
	            	formatter:function(value,rowData,rowIndex){
	            		deptName = value;
	            		return value;
	            	}
	            },
	            {title: '排版类型', field: 'ATTEND_CONFIG_NAME', width: '28%', align: 'center'}
	        ]],
	        onLoadSuccess:function(data){ 
	        	var length = $('#myTable').datagrid('getData').rows.length;
	        	$('#myTable').datagrid("appendRow", {ID: deptName,ATTEND_CONFIG_NAME:getAttendConfig() }).datagrid('mergeCells',{index:length,field: 'ID',colspan:3});
	        	ajaxLoadEnd();
	   		}
	    });
	    var p = $('#myTable').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为5  
	        pageList: [5, 10,15,20],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });
}

function getAttendConfig(){
	var returnData = "";
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendConfigList.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			selectHtml ="<select id=\"attendConfig\" >";
			$.each(data,function(index,data){
				selectHtml +="<option value="+data.attendConfigId+" >"+data.configName+"</option>";
			});
			selectHtml += "</select>";
			returnData = selectHtml;
		}
	});
	return returnData;
}

function edit()
{
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/changeDeptAttendConfig.act";
	var paramData={deptId:deptId,attendConfigId:$("#attendConfig").val()};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data!=0){
				top.layer.msg("保存成功");
				query();
			}
		}
	});
}
function backToIndex(){
	parent.window.location = contextPath + "/system/attend/setting/index.jsp";
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
<div align="center"><button onclick="edit();" class="btn btn-info" >保存</button> <button onclick="javascript:backToIndex();" class="btn btn-default">返回</button></div>
</div>
</div>
</body>
</html>