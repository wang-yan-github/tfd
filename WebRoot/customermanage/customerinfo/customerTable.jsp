<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script type="text/javascript" src="<%=contextPath%>/customermanage/js/customer.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<title>客户信息列表</title>
<script>
$(function (){
	init();
	checkpower();
});
function init(){
	var url=contextPath+"/customermanage/act/CustomerinfoAct/getNamecustomerAct.act?status="+1;
	gettable(url);
}
function gettable(url,parm){
	$("#myTable").datagrid({
        width: '100%',
        hight:'auto',
		rows:10,
        collapsible: true,
        url:url,
        method: 'POST',
        loadMsg: "数据加载中...",
        pagination:true,
        striped: true,
        singleSelect:false,
        remoteSort:true, 
        queryParams:{
        	termtype:$("#termtype").val(),
        	customerName:$("#customerName").val(),
        	linkmanName:$("#linkmanName").val()
        },
        onLoadSuccess:function(data){ 
        	if(data.total == 0){
        		$(".datagrid").hide();
        		$(".MessageBox").show();
  			}else{
  				var tableview=$('#myTable').datagrid('getPager');
  				tableview.show();
  				$("#searchform").show();
        		$("#btnitem").show(); 
  			}
        },
        onBeforeLoad:function(param){
        	var tableview=$('#myTable').datagrid('getPager');
        	tableview.hide();
        },
        columns:[[
			{title: '全选', field: '', width: '2%', align: 'center',sortable:true,checkbox:true},
           	{title: '客户名称', field: 'CUSTOMER_NAME', width: '20%', align: 'center',sortable:true,
           		formatter:function(value,rowData,rowIndex){
           			if(rowData.CUSTOMER_NAME==null){
           				rowData.CUSTOMER_NAME="";
           			}
					return '<a href=javascript:void(0); onclick=showcuctomer("'+rowData.CUSTOMER_ID+'","1");>'+rowData.CUSTOMER_NAME+'</a>';
				}	
           	},
           	{title: '客户类型', field: 'CUSTOMER_TYPE', width: '15%', align: 'center',sortable:true},
           	{title: '客户负责人', field: 'KEEPER_NAME', width: '15%', align: 'center',sortable:true},
           	{title:'状态',field:'CUSTOMER_STATUS',width:'10%',align:'center',sortable:true},
           	{title: '电话', field: 'TEL_NUMBER', width: '20%', align: 'center',sortable:true},
           	{title: '操作', field: 'OPT', width: '8%', align: 'center',
           		formatter:function(value,rowData,rowIndex){
        			if(rowData.TOP==0){
        				return "<a href=\"javascript:updatetop('"+rowData.TOP+"','"+rowData.CUSTOMER_ID+"');\"><img src=\""+imgPath+"/customer/star_2.png\"/></a>";
        			}else{
        				return "<a href=\"javascript:updatetop('"+rowData.TOP+"','"+rowData.CUSTOMER_ID+"');\"><img src=\""+imgPath+"/customer/star_1.png\"/></a>";
        			}
        		}
           	}
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

function getNewcustomer(){
	var url=contextPath+"/customermanage/customerinfo/Newcustomer.jsp?status="+1;
	window.location=url;
}
function searchclick(){
	var url=contextPath+"/customermanage/act/CustomerinfoAct/gettermcusAct.act";
	gettable(url);
}
</script>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >客户列表</span>
</div>
<div id="btnitem">
       <button class="btn btn-primary"  onclick="getNewcustomer();">新建</button>
       <button class="btn btn-warning"  onclick="updatecustomer();">修改</button>
</div>
</div>

   <form action="" id="searchform" name="searchform">
   <table style="width: 60%;height: 50px;">
   <tr>
   <td width="12%">客户名称：</td>
   <td width="36%">
   <input type="hidden" name="termtype" id="termtype" value="1"></input>
   <div class="col-xs-12 form-group">
   <input type="text" id="customerName" name="customerName" class="form-control" />
   </div>
   </td>
   <td width="10%">
   联系人：
   </td>
   <td width="36%">
    <div class="col-xs-12 form-group">
   <input type="text" id="linkmanName" name="linkmanName" class="form-control" />
   </div>
   </td>
   <td>
   <button type="button" id="searchbtn" name="searchbtn" class="btn btn-primary" onclick="searchclick();">查询</button>
   </td>
   </tr>
   </table>
   </form>
<div id="myTable" name="myTable"></div>
<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
	   <tbody><tr class="head-no-title">
	      <td class="left"></td>
	      <td class="center">
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="msg">
	      <td class="left"></td>
	      <td class="center info">
	         <div class="msg-content">没有客户</div>
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
</body>
</html>