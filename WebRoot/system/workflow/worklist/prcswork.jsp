<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>过程中流程</title>
 <link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript"> 
function doinit(){
	 ajaxLoading();
 	    $("#myTable").datagrid({
 	        width: "100%",
 			rows:10,
 	        collapsible: true,
 	        url: "<%=contextPath%>/tfd/system/workflow/worklist/act/WorkListAct/getProcessAct.act",
 	        method: 'POST',
 	        sortName: 'ID',
 	       	sortOrder: 'DESC',
 	        pagination:true,
 	        striped: true,
 	        singleSelect:true,  
 	        remoteSort:true, 
 	       pagePosition:'top',
 	       queryParams:{
  	    	  module:'workflow',
  	    	  flowName:$("#flowName").val(),
  	    	  flowTitle:$("#flowTitle").val(),
  	    	  runId:$("#runId").val(),
  	    	 beginUser:$("#beginUser").val()
  	       },
  	     onLoadSuccess:function(data){ 
  	    	  if(data.total>0)
              {
              $("#infotable").hide();
              }
             ajaxLoadEnd();
          },
 	        columns:[[
 	            {title: '序号', field: 'ID', width: '5%', align: 'center',sortable:true},
 	           {title: '流水号', field: 'RUN_ID', width: '15%', align: 'center',sortable:true},
 	            {title: '标题', field: 'TITLE', width: '30%', align: 'center',sortable:true},
 	          	{title: '参与人员', field: 'OP_USER_STR', width: '15%', align: 'center',sortable:true,
 	            	formatter:function(value,rowData,rowIndex){
 	            		if(rowData.OP_USER_STR!=null)
 	            			{
 	            			return getUserName(rowData.OP_USER_STR);
 	            			}else
 	            				{
 	            				return "无结果!";
 	            				}
 	            		
 	            	}
 	          	},
 	            {title: '发起时间', field: 'BEGIN_TIME', width: '15%', align: 'center',sortable:true},
 	            {title: '操作', field: 'OPT', width: '20%', align: 'center',sortable:true}
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
	//查看工作流表单
 	function read(runId,url)
 	{
 		var url="<%=contextPath%>"+url;
 		var sysFrame=new SysFrame();
 		 if (sysFrame.tabs('exists',"<div  style=\"display:none;\" id="+runId+"></div >查看工作流")){ 
 			sysFrame.tabs('select',"<div  style=\"display:none;\" id="+runId+"></div >查看工作流"); 
		 }else
			 {
			 top.goUrl("<div  style=\"display:none;\" id="+runId+"></div >查看工作流",url);
			 }
 	}
    </script>
</head>
<body onload="doinit();">
<table class="table table-striped  table-condensed" >
<tr>
<td width="100px" nowrap="nowrap">流程名称：</td>
<td ><div class="col-xs-12"><input class="form-control" name="flowName"  id="flowName"/></div></td>
<td width="100px" nowrap="nowrap">流程标题：</td>
<td><div class="col-xs-12"><input class="form-control" name="flowTitle" id="flowTitle"/></div></td>
<td width="100px" nowrap="nowrap">流水号：</td>
<td><div class="col-xs-12"><input class="form-control" name="runId" id="runId"/></div></td>
<td width="100px" nowrap="nowrap">发起人：</td>
<td><div class="col-xs-10" style="float: left;">
<input class="form-control" name="beginUser" id="beginUser"  type="hidden"/>
<input class="form-control" name="userName" id="userName" readonly="readonly"/>
</div><a href="javascript:void(0);" onclick="userinit(['beginUser','userName'],'true');" style="line-height: 30px;">选择</a></td>
</tr>
</table>
<div align="center"><button type="button" class="btn btn-primary" onclick="doinit();">查询</button></div>
<div id="myTable" name="myTable"></div>
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
<div id="modaldialog"></div>
</body>
</html>
