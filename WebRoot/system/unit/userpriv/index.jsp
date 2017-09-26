<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限组修改</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userpriv.css"></link>
<script type="text/javascript">
$(function(){
	    $("#myTable").datagrid({
			height: 'auto',
			rows:10,
	        collapsible: true,
	        scrollbarSize :0,
	        url: "<%=contextPath %>/tfd/system/unit/userpriv/act/UserPrivAct/getAllUserPrivAct.act",
	        method: 'POST',
	        sortName: 'ID',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	            {title: '序号', field: 'ID', width: 100, align: 'center',sortable:true},
	           	{title: '权限编号', field: 'USER_PRIV_LEAVE', width: 200, align: 'center',sortable:true},
	            {title: '权限组名称', field: 'USER_PRIV_NAME', width: 200, align: 'center',sortable:true},
	            {title: '操作', field: 'OPT', width: 300, align: 'center',sortable:true}
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
	});
	function setPriv(userPrivId)
	{
		location.href="<%=contextPath %>/system/unit/userpriv/setpriv.jsp?userPrivId="+userPrivId;
	}
	function edit(userPrivId)
	{
		location.href="<%=contextPath %>/system/unit/userpriv/edit.jsp?userPrivId="+userPrivId;
	}
	function readPriv(userPrivId)
	{
		location.href="<%=contextPath %>/system/unit/userpriv/readpriv.jsp?userPrivId="+userPrivId;
	}
		
	function copy(userPrivId)
	{
		var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/copyUserPrivAct.act";
		var paramData={userPrivId:userPrivId};
		$.ajax({
			url:requestUrl,
			dataType:"text",
			data:paramData,
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="1")
				{
					location.href="<%=contextPath %>/system/unit/userpriv/edit.jsp?userPrivId="+userPrivId;
				}
				
			}
		});
	}
	function delPriv(userPrivId)
	{
		var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/delByUserPrivIdAct.act";
		var paramData={userPrivId:userPrivId};
		$.ajax({
			url:requestUrl,
			dataType:"text",
			data:paramData,
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="OK")
					{
						alert("删除成功！");
					}
				location.href="<%=contextPath %>/system/unit/userpriv/index.jsp";
				}
		});
	}
	function addpriv(){
		location.href=contextPath+"/system/unit/userpriv/add.jsp";
	}
    </script>
</head>
<body>
<div align="center" style="width: 100%;">
	<div style="width: 80%;">
			<div style="height: 40px;margin-top: 5px;">
						<input type="button" value="添加权限" class="btn btn-primary"
							onclick="addpriv();">
			</div>
			<div class="widget-header bordered-bottom bordered-sky">
			  	<span class="widget-caption">权限组列表</span>
			</div>
			<div id="myTable" name="myTable"  ></div>
	</div>
</div>
</body>
</html>