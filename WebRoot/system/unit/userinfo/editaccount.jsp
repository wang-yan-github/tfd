<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账号维护</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
function edit(accountId)
{
	location.href=contextPath+"/system/unit/userinfo/accountedit.jsp?accountId="+accountId;
}
function read(accountId)
{
	location.href=contextPath+"/system/unit/userinfo/accountread.jsp?accountId="+accountId;
}
function setUser(accountId)
{
	var requestUrl=contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/checkUserinfoAct.act";
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
				location.href=contextPath+"/system/unit/userinfo/edituserinfo.jsp?accountId="+accountId+"&notLogin="+1;	
				}else {
					location.href=contextPath+"/system/unit/userinfo/adduser.jsp?accountId="+accountId;	
				}
		}
	});
}
function delAccount(accountId)
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
function query()
{
			$("#myTable").datagrid({
				 	       scrollbarSize :0,
				 			rows:5,
				 	        collapsible: true,
				 	        url: contextPath+"/tfd/system/unit/account/act/AccountAct/getMenageAccountListAct.act",
				 	        method: 'POST',
				 	        sortName: 'ID',
				 	       queryParams:{
				 	    	  		accountId:$("#accountId").val(),
				 	    	  		notLogin:$("#notLogin").val(),
				 	    	  		userName:$("#userName").val(),
				 	    	  		deptId:$("#deptId").val()
							},
				 	        loadMsg: "数据加载中...",
				 	        pagination:true,
				 	        striped: true,
				 	        singleSelect:true,  
				 	        remoteSort:true, 
				 	        columns:[[
				 	            {title: '序号', field: 'ID', width: '5%', align: 'center',sortable:true},
				 	           	{title: '账号', field: 'ACCOUNT_ID', width: '25%', align: 'center',sortable:true},
				 	            {title: '密码类型', field: 'PASSWORD_TYPE', width: '10%', align: 'center',sortable:true,
				 	           	formatter:function(value,rowData,rowIndex){
					 	   			if(rowData.PASSWORD_TYPE==1){
					 	   				return '正常密码验证';
					 	   			}else if(rowData.PASSWORD_TYPE==2){
					 	   				return '动态密码验证';
					 	   			}
					 	   			}	
				 	            },
				 	           	{title: '权限组', field: 'USER_PRIV_NAME', width: '25%', align: 'center',sortable:true},
				 	            {title: '语言', field: 'LANGUAGE', width: '15%', align: 'center',sortable:true,
				 	             	formatter:function(value,rowData,rowIndex){
						 	   			if(rowData.LANGUAGE==1){
						 	   				return '简体中文';
						 	   			}else if(rowData.LANGUAGE==2){
						 	   				return '美式英文';
						 	   			}
						 	   			}	
				 	            },
				 	            {title: '操作', field: 'OPT', width: '20%', align: 'center',sortable:true}
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
</script>
</head>
<body style="margin-top: 10px;">
<div align="center">
<div class="list-group-item"  style="padding: 0px;width: 80%;" align="center" >
<div class="widget-header bg-palegreen">
<span class="widget-caption">查询条件</span>
</div>
		<table class="table table-striped table-condensed" >
		<tr>
		<td width="100px">账号：</td>
		<td><div class="col-xs-10"><input name="accountId" id="accountId" type="text" class="form-control"></div></td>
		<td width="100px">启用状态：</td>
		<td><div class="col-xs-10"><select name="notLogin" id="notLogin" class="form-control"><option value="0" >已启用</option><option value="1">未启用</option></select></div></td>
		</tr>
		<tr>
		<td>人员姓名：</td>
		<td><div class="col-xs-10"><input name="userName" id="userName" type="text" class="form-control"></div></td>
		<td>部门：</td>
		<td><div class="col-xs-10"><input type="hidden" name="deptId" id="deptId" class="form-control">
		<input type="text" name="deptName" id="deptName" class="form-control" readonly>
		</div><a href="javascript:void(0);" onclick="deptinit(['deptId','deptName'],'true');" style="line-height: 30px;">选择</a></td>
		</tr>
		</table>
</div>
</div>
<br>
<div align="center"><button onclick="query();" class="btn btn-primary">查 询</button></div>
<br>
<div class="list-group"  align="center">
  		<div  class="list-group-item"  style="width: 80%;padding: 0px;" >
  			<a class="list-group-item active">维护账号列表</a>
  			<div id="myTable" name="myTable" ></div>
  		</div>
 </div>

 <div id="modaldialog"></div>
</body>
</html>