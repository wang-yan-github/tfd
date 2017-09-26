<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
第三方数据源
</title>
<script type="text/javascript">
function doinit(){
    $("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: "<%=contextPath%>/tfd/system/workflow/workflowdatasource/act/WorkFlowDataSourceAct/getDataSourceListJsonAct.act",
        method: 'POST',
        sortOrder: 'DESC',
        sortName: 'ID',
        loadMsg: "数据加载中...",
        pagination:true,
        striped: true,
        singleSelect:true,  
        remoteSort:true, 
        columns:[[
            {title: '序号', field: 'ID', width: 50, align: 'center',sortable:true},
           	{title: '数据源名称', field: 'DB_SOURCE_NAME', width: 100, align: 'center',sortable:true},
            {title: '数据源类型', field: 'DB_SOURCE_TYPE', width: 100, align: 'center',sortable:true},
           	{title: '数据库地址', field: 'DB_LINK', width: 400, align: 'center',sortable:true},
           	{title: '数据库用户名', field: 'DB_USER_NAME', width: 100, align: 'center',sortable:true},
           	{title: '数据库密码', field: 'DB_USER_PASSWD', width: 100, align: 'center',sortable:true},
           	{title: '数据库名', field: 'DB_NAME', width: 100, align: 'center',sortable:true},
            {title: '操作', field: 'OPT', width: 150, align: 'center',sortable:false}
        ]]
    });
     
    var p = $('#myTable').datagrid('getPager');  
        $(p).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为5  
        pageList: [10, 20],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });  
}
function add()
{
	var requestUrl=contextPath+"/tfd/system/workflow/workflowdatasource/act/WorkFlowDataSourceAct/addDataSourceAct.act";
	var paramData={
			dbSourceName:$("#dbSourceName").val(),
			dbSourceType:$("#dbSourceType").val(),
			dbLink:$("#dbLink").val(),
			dbUserName1:$("#dbUserName").val(),
			dbUserPasswd1:$("#dbUserPasswd").val(),
			dbName:$("#dbName").val()
			};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		success:function(data){
			if(data=="OK")
				{
				layer.msg("添加成功！");
				window.location.reload();
				}else
					{
					layer.msg("添加失败！")
					}
		 }
	});
	
	}
function del(dbSourceId)
{
	var requestUrl=contextPath+"/tfd/system/workflow/workflowdatasource/act/WorkFlowDataSourceAct/delDataSourceAct.act";
	var paramData={
			dbSourceId:dbSourceId
			};
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		success:function(data){
			if(data=="OK")
				{
				layer.msg("删除成功！");
				window.location.reload();
				}else
					{
					layer.msg("删除失败！")
					}
		 }
	});	
	}
function edit(dbSourceId)
{
	location.href=contextPath+"/system/workflow/datasource/editdatasource.jsp?dbSourceId="+dbSourceId;
	}
function read(dbSourceId)
{
	
	}
</script>
</head>
<body onload="doinit()">
<div class="container">
   <form id="form1" name="form1" class="form-horizontal">
<div class="panel panel-info"  align="center">
   <div class="panel-heading">
      <h3 class="panel-title">
        数据源管理
      </h3>
   </div>
<div class="panel-body" align="center">
<table class="table table-striped" align="center">
<tr>
<td width="15%">数据源名称：</td>
<td><div class="col-xs-4 form-group" ><input type="text" id="dbSourceName" id="dbSourceName" class="form-control"/></div></td>
</tr>
<tr>
<td>数据源类型：</td>
<td>
<div class="col-xs-4"><select id="dbSourceType" name="dbSourceType" class="form-control">
<option value="MSSQL">MSSQL</option>
<option value="MYSQL">MYSQL</option>
<option value="DB2">DB2</option>
</select></div>
</td>
</tr>
<tr>
<td>数据库地址：</td>
<td><div class="col-xs-4 form-group"><input type="text" id="dbLink" id="dbLink" class="form-control"/></div></td>
</tr>
<tr>
<td>数据库用户名：</td>
<td><div class="col-xs-4 form-group"><input type="text" id="dbUserName" id="dbUserName" class="form-control"/></div></td>
</tr>
<tr>
<td>数据库密码：</td>
<td><div class="col-xs-4 form-group"><input type="text" id="dbUserPasswd" id="dbUserPasswd" class="form-control"/></div></td>
</tr>
<tr>
<td>数据库名称：</td>
<td><div class="col-xs-4 form-group"><input type="text" id="dbName" id="dbName" class="form-control"/></div></td>
</tr>
</table>
<div align="center"><button type="submit" onclick="add();" class="btn btn-primary" >添加</button></div>
</div>
</div>
</form>
</div>
</br>
<div id="myTable" name="myTable"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('#form2').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			dbSourceName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据源名称不能为空！'
                    }
                }
            },
            dbLink: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据库连接不能为空！'
                    }
                }
            },
            dbUserName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据库用户名不能为空！'
                    }
                }
            },
            dbUserPasswd: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据库密码不能为空！'
                    }
                }
            },
            dbName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据库名称不能为空！'
                    }
                }
            }
		}
	});
})
</script>
</body>
</html>