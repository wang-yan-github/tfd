<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>手机短信</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript">
function query()
{
			$("#myTable").datagrid({
				 	        width: document.body.clientWidth,
//				 	        height: document.body.clientHeight,
				 			height: 'auto',
				 			rows:5,
				 	        collapsible: true,
				 	        url: contextPath+"/tfd/system/mobilesms/act/MobileSmsAct/queryMoblieSmsAct.act",
				 	        sortName: 'ID',
				 	       queryParams:{
				 	    	 accountId:$("#accountId").val(),
				 	    	 outSideNo:$("#outSideNo").val(),
				 	    	 beginCreateTime:$("#beginCreateTime").val(),
				 	    	 endCreateTime:$("#endCreateTime").val(),
				 	    	 status:$("#status").val(),
				 	    	 delFlag:$("#delFlag").val()
							},
				 	        loadMsg: "数据加载中...",
				 	        pagination:true,
				 	        striped: true,
				 	        singleSelect:true,  
				 	        remoteSort:true, 
				 	        columns:[[
				 	            {title: '序号', field: 'ID', width: 50, align: 'center',sortable:true},
				 	           	{title: '短信流水号', field: 'MOBILE_SMS_ID', width: 250, align: 'center',sortable:true},
				 	           	{title: '收信人', field: 'REV_MOBLIE_NUMBER', width: 100, align: 'center',sortable:true},
				 	          	{title: '短信内容', field: 'SEND_CONTENT', width: 500, align: 'left',sortable:true},
				 	            {title: '发送时间', field: 'CREATE_TIME', width: 150, align: 'center',sortable:true},
				 	           	{title: '状态', field: 'STATUS', width: 80, align: 'center',sortable:true,
				 	            	formatter:function(value,rowData,rowIndex){
				 	       			if(rowData.STATUS==0){
				 	       				return '进行中';
				 	       			}else if(rowData.STATUS==1){
				 	       				return '发送成功';
				 	       			}
				 	       		}		
				 	           	}
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
<body>
<div style="height: 100%;"  align="center">
<div class="panel panel-info" style="width: 800px;">
   <div class="panel-heading" align="left">
      <h3 class="panel-title">
      手机短信查询
      </h3>
   </div>
   <div class="panel-body">
   <table class="table table-striped " style="width: 800px;" align="center">
   <tr>
   <td>按接收人：</td>
   <td>
   <div class="col-xs-4" style="float: left;" ><input type="hidden" name="accountId" id="accountId"/>
   <input type="text" name="userName" id="userName" class="form-control"/></div><div style="float: left;">
   <a style="cursor: pointer;" onclick="userinit(['accountId','userName'],true);"">添加</a>
    </div>
   </td>
   </tr>
      <tr>
   <td>外部人员：
   </td>
   <td>
   <div class="col-xs-4" >
   <input type="text" name="outSideNo" id="outSideNo" class="form-control"/>
   </div>
   </div>
   </td>
   </tr>
   <tr>
   <td>发送时间：</td>
   <td><div class="col-xs-4" style="float: left">
<input type="text" name="beginCreateTime" id="beginCreateTime"  style="cursor: pointer;"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择时间">
</div><div style="float: left;">至</div>
<div class="col-xs-4" style="float: left">
<input type="text" name="endCreateTime" id="endCreateTime" style="cursor: pointer;"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" placeholder="请选择时间">
</div>
</td>
   </tr>
     <tr>
   <td>发送状态：</td>
   <td><div class="col-xs-4">
<select name="status" id="status"  class="form-control">
<option value="">--全部--</option>
<option value="1">发送成功</option>
</select>
</div></td>
   </tr>
<tr>
   <td>计录状态：</td>
   <td><div class="col-xs-4">
<select name="delFlag" id="delFlag"  class="form-control">
<option value="">--全部--</option>
<option value="1">已删除</option>
</select>
</div></td>
   </tr>
   </table>
   </div>
   </div>
   </div>
   <div align="center"><button type="button" name="send"  id="send" class="btn btn-default"  onclick="query();">查询</button></div>
  </br>
   <div class="panel panel-info" style="margin-bottom:40px;">
   <div class="panel-heading">
      <h3 class="panel-title">
        查询结果
      </h3>
   </div>
   <div class="panel-body">
   <div id="myTable" name="myTable"></div>
   </div>
   </div>
   <div id="modaldialog"></div>
   </body>
   </html>