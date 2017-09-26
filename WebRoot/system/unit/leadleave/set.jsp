<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>行政级别类型设置</title>
<script type="text/javascript">
function doinit()
{
	$("#myTable").datagrid({
//      title: "用户列表",
     width: document.body.clientwidth,
//      height: document.body.clientheight,
		height: 'auto',
     collapsible: true,
     url: "<%=contextPath %>/tfd/system/unit/userleave/act/UserLeaveAct/getUserLeaveListAct.act",
     method: 'POST',
     sortName: 'id',
     loadMsg: "数据加载中...",
     pagination:true,
     striped: true,
     singleSelect:true,  
     remoteSort:true,  
     columns:[[
         {title: '序号', field: 'ID', width: 100, align: 'center',sortable:true},
         {title: '编号', field: 'LEAVE_ID', width: 100, align: 'center',sortable:true,hidden:true},
         {title: '行政级别', field: 'LEAVE_NO_ID', width: 100, align: 'center',sortable:true},
         {title: '行政级别名称', field: 'LEAVE_NAME', width: 200, align: 'center',sortable:true},
         {title: '上级政级别', field: 'MIDDING', width: 200, align: 'center',sortable:true,
        	 formatter:function(value,rowData,rowIndex){
        		 if(getLeaveName(rowData.MIDDING)==""){
        			 return"无";
        		 }else
        			 {
     			return getLeaveName(rowData.MIDDING);
        			 }
     		}		 
         },
         {title: '操作', field: 'OPT', width: 150, align: 'center',sortable:true,
        	 formatter:function(value,rowData,rowIndex){
        		 var d = '<a href="#" mce_href="#" onclick="delleave(\''+ rowIndex +'\')">删除</a> ';  
                 return value+d;
     		}		 
        	 
         }
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

function setleave(leaveId)
{
	location.href=contextPath+"/system/unit/leadleave/edit.jsp?leaveId="+leaveId;
	}


function delleave(indexId)
{
	var rows = $('#myTable').datagrid('getRows');
	var leaveId=rows[indexId]['LEAVE_ID'];
	$('#myTable').datagrid('deleteRow',indexId);
	var requestUrl=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/delByLeaveIdAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:{leaveId:leaveId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
			doinit();
			layer.msg("删除成功！");
			}else
				{
				layer.msg("删除失败！");
				}
		}
	});
	}
</script>
</head>
<body onload="doinit();">
<div id="myTable" name="myTable"></div>
</body>

</html>