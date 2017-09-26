<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>联系记录表</title>
<%String customerId=request.getParameter("customerId"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script>
var customerId="<%=customerId%>";
$(function (){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/getcIdAct.act";
	gettable(url);
	getrecordName();
});
function gettable(url){
	$("#myTable").datagrid({
        width: '100%',
		rows:5,
        collapsible: true,
        url:url,
        method: 'POST',
        sortName: 'RECORD_TIME',
        sortOrder:'desc',
        loadMsg: "数据加载中...",
        pagination:true,
        queryParams:{customerId:customerId,
        		linkmanId:$("#linkmanId").val(),
        		recordTime:$("#recordTime").val()
        },
        onLoadSuccess:function(data){  
       		if(data.total == 0){
	  				$('#myTable').datagrid('appendRow',{USER_NAME:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'USER_NAME', colspan: 6 });
    		}
    	},
        striped: true,
        rownumbers:true, 
        singleSelect:true,  
        remoteSort:true, 
        columns:[[
			{title: '创建人', field: 'USER_NAME', width: '10%', align: 'center',sortable:true},
           	{title: '联系人', field: 'LINKMAN_NAME', width: '10%', align: 'center',sortable:true},
           	{title: '联系方式', field: 'RECORD_LINKTYPE', width: '15%', align: 'center',sortable:true},
           	{title: '联系内容', field: 'RECORD_CONTENT', width: '35%',sortable:true, align: 'center'},
           	{title: '联系提醒', field: 'RECORD_WARN', width: '15%', align: 'center',sortable:true},
           	{title: '操作', field: 'OPT', width: '10%', align: 'center'}
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
function updaterecord(recordId){
	
	parent.document.getElementById("con").src=contextPath+"/customermanage/linkrecord/updaterecord.jsp?recordId="+recordId;
	
}
function delrecord(recordId){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/delrecordAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{recordId:recordId},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				gettable();
			}
	}
	});
}
function getrecordName(){
	var url=contextPath+"/customermanage/act/CustomerlinkmanAct/getlinkmanNameAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{customerId:customerId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var scon="";
			for(var i=0;i<data.length;i++){
				scon+="<option value=\""+data[i].linkmanId+"\">"+data[i].linkmanName+"</option>";
			}
			$("#linkmanId").append(scon);
	}
	});
}
function searchclick(){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/termQueryAct.act";
	gettable(url);
}
</script>
</head>
<body>
   <form action="" id="searchform" name="searchform">
   <table style="width: 60%;height: 50px;">
   <tr>
   <td width="12%">记录时间：</td>
   <td width="36%">
   <div class="col-xs-12 form-group">
  <input type="text" name="recordTime" id="recordTime" size="20" readonly="readonly" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control" placeholder="请选择时间" /></div>
   </div>
   </td>
   <td width="10%">
   联系人：
   </td>
   <td width="36%">
    <div class="col-xs-12 form-group">
   <select id="linkmanId" name="linkmanId" class="form-control">
   <option value="">请选择</option>
   </select>
   </div>
   </td>
   <td>
   <button type="button" id="searchbtn" name="searchbtn" class="btn btn-primary" onclick="searchclick();">查询</button>
   </td>
   </tr>
   </table>
   </form>
<div id="myTable" name="myTable" ></div>
</body>
</html>