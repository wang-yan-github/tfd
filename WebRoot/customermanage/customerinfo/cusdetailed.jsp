<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>客户详细信息</title>
<%String customerId=request.getParameter("customerId"); %>
<%String status=request.getParameter("status"); %>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script>
var customerId="<%=customerId%>";
var status="<%=status%>";
var customerName="";
//初始化
$(function (){
	lookcustomer();
	getMessagePriv("calendar");
	$("#customerId").val(customerId);
	if(status==1){
		$("#uptbtn").show();
	}
	getIdName();
	getrecord();
});
//获取客户详细信息
function lookcustomer(){
	var url=contextPath+"/customermanage/act/CustomerinfoAct/getIdcustomerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{customerId:customerId},
		async:false,
		error:function(e){
		},
		success:function(data){
			var fromdata=data;
			customerName=data.customerName;
			for(var name in fromdata){
				if(fromdata[name]==null){
					
				}else{
				$("#"+name).text(fromdata[name]);
				}
			}
	}
	});
}
//修改
function updatecustomer(){
	var url=contextPath+"/customermanage/customerinfo/updatecustomer.jsp?customerId="+customerId;
	window.location=url;
}
//联系人
function linkmantable(){
	var url=contextPath+"/customermanage/linkman/linkmanTable.jsp?customerId="+customerId;
	window.location=url;
}
//添加联系人
function getNewlinkamn(){
	var url=contextPath+"/customermanage/linkman/Newlinkman.jsp?customerId="+customerId;
	window.location=url;
}
//联系记录
function getlinkrecord(){
	var url=contextPath+"/customermanage/linkrecord/index.jsp?customerId="+customerId;
	window.location=url;
}
//获取联系人（下拉菜单）
function getIdName(){
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
				if(i=0){
				scon+="<option value=\""+data[i].linkmanId+"\" selected=\"selected\">"+data[i].linkmanName+"</option>";
				}else{
					scon+="<option value=\""+data[i].linkmanId+"\">"+data[i].linkmanName+"</option>";
				}
			}
			$("#linkmanId").append(scon);
	}
	});
}
//添加联系记录
function addrecord(){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/addrecordAct.act";
	if($("#WarnContent").val()==""){
		$("#WarnContent").val("请尽快与客户："+customerName+"联系");
	}
	var parm=$("#recordfrom").serialize()+"&smsReminds="+getsmsRemind();
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:parm,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				location.reload();
			}else{
				
			}
	}
	});
}
//获取最近的联系记录
function getrecord(){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/getrecordAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{customerId:customerId},
		async:false,
		error:function(e){
		},
		success:function(data){
		for(var i=0;i<data.length;i++){
			$("#recordCon").append("<tr><td>"+data[i].recordTime+"&nbsp;,"+data[i].recordContent+"</td></tr>");
		}
	}
	});
}
//获取提醒内容
function getcontent(){
	var te=$("#linkmanId option:selected").text();
	if(te!=""){
	$("#WarnContent").val("请尽快与客户联系人:"+te+"联系");
	}
}
</script>
</head>
<body>
<div class="top_info" style="position: fixed;z-index: 99999;background-color: white;width: 100%;">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >客户资料</span>
</div>
</div>

<div class="list-group-item"  style="width:60%;cursor:auto; float: left;margin-top: 60px;padding: 0px;">
<a style="cursor: auto;" class="list-group-item active">客户详情</a>

<table class="table table-striped table-condensed" id="customer_info">
<tr>
<td>负责人：</td>
<td colspan="3">
<div class="col-xs-4 form-group" id="keeperName">
</div>
</td>
</tr>
<tr>
<td >客户名称：</td>
<td colspan="3">
<div class="col-xs-4 form-group" id="customerName">
</div>
</td>
</tr>
<tr>
<td>参与人：</td>
<td colspan="3">
<div class="col-xs-12 form-group" id="joinStaffName">
</div>
</td>
</tr>
<tr>
<td width="15%">电话：</td>
<td width="35%">
<div class="col-xs-10 form-group" id="telNumber">
</div>
</td>
<td width="15%">传真：</td>
<td width="35%">
<div class="col-xs-10 form-group" id="faxNumber">
</div>
</td>
</tr>
<tr>
<td>网址：</td>
<td>
<div class="col-xs-10 form-group" id="urlName">
</div>
</td>
<td>邮箱：</td>
<td>
<div class="col-xs-10 form-group" id="eMail">
</div>
</td>
</tr>
<tr>
<td> 地区：</td>
<td >
<div class="col-xs-12 form-group" id="areaName">
</div>
</td>
<td></td>
<td></td>
</tr>
<tr>
<td>详细地址：</td>
<td colspan="3">
<div class="col-xs-12 form-group" id="addName">
</div>
</td>
</tr>
<tr>
<td>共享人员：</td>
<td colspan="3">
<div class="col-xs-12 form-group" id="shareStaffName">
</div>
</td>
</tr>
<tr>
<td>
客户来源：
</td>
<td>
<div class="col-xs-10 form-group" id="customerOrigin">
</div>
</td>
<td>
客户类型：
</td>
<td>
<div class="col-xs-10 form-group" id="customerType">
</div>
</td>
</tr>
<tr>
<td>
所属行业：
</td>
<td>
<div class="col-xs-10 form-group" id="tradeType">
</div>
</td>
<td>
企业性质：
</td>
<td>
<div class="col-xs-10 form-group"id="firmNature" >
</div>
</td>
</tr>
<tr>
<td>企业简述：</td>
<td colspan="3">
<div class="col-xs-12 form-group" id="firmSummary">
</div>
</td>
</tr>
<tr>
<td>备注：</td>
<td colspan="3">
<div class="col-xs-12 form-group" id="remark">
</div>
</td>
</tr>
</tbody>
<tr>
<td colspan="4">
<div align="center">
<button class="btn btn-primary" onclick="updatecustomer();" id="uptbtn" style="display: none;"> 修改信息</button>
<button type="button" class="btn btn-primary" onclick="linkmantable();">联系人</button>
<button type="button" class="btn btn-primary" onclick="getNewlinkamn();">添加联系人</button>
<button type="button" class="btn btn-primary" onclick="getlinkrecord();">联系记录</button>
</div>
</td>
</tr>
</table>
</div>

<div style="width:38%;float: right;margin-top: 60px;">
<form id="recordfrom" name="recordfrom" class="form-horizontal">
 <div class="widget-header bg-blueberry" style="height: 42px;">
	<span class="widget-caption">添加联系记录</span>
	<button type="submit" name="send" class="btn btn-warning btn-sm" style="margin-top: 3px;margin-right: 3px;">保存</button>
</div>
<div class="list-group-item"  style="padding: 0px;cursor: auto;">
   <table class="table table-striped">
   <tr>
   <td width="20%">
   联系人：
   </td>
   <td>
   <input type="hidden" id="customerId" name="customerId"></input>
   <div class="col-xs-10 form-group" >
   <select id="linkmanId" name="linkmanId" class="form-control" onchange="getcontent();">
   <option value="">请选择</option>
   </select>
   </div>
   </td>
   </tr>
    <tr>
   <td>
   联系方式：
   </td>
   <td>
   <div class="col-xs-10 form-group" >
   <select id="recordlinkType" name="recordlinkType" class="form-control">
   <option value="电话沟通">电话沟通</option>
   <option value="上门拜访">上门拜访</option>
   <option value="客户来访">客户来访</option>
   <option value="邮件/短信">邮件/短信</option>
   </select>
   </div>
   </td>
   </tr>
   <tr>
   <td>联系提醒：</td>
   <td>
   <input type="hidden" id="WarnContent" name="WarnContent"  />
   <div class="col-xs-10 form-group" >
   <input type="text" name="recordWarn" id="recordWarn" size="20" readonly="readonly" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control" placeholder="请选择时间" /></div>
   </td>
   </tr>
   <tr>
   <td>
   联系内容：
   </td>
   <td>
   <div class="col-xs-10 form-group">
   <textarea  id="recordContent" name="recordContent" rows="3" class="form-control" ></textarea>
	</div>
   </td>
   </tr>
   </table>
   <table id="recordCon" class="table table-striped" >
   
   </table>
 		<div id="smsdiv" name="smsdiv" style="display: none;"></div>
   </form>
   </div>
   </div>
   <script type="text/javascript">
   $(document).ready(function() {
	   $("#recordfrom").bootstrapValidator({
	   	 message: 'Pas valide',
	   	 container: 'tooltip',
	   	 feedbackIcons: {
	   	     valid: 'glyphicon glyphicon-ok',
	   	     invalid: 'glyphicon glyphicon-remove',
	   	     validating: 'glyphicon glyphicon-refresh'
	   	 },
	   	 fields: {
	   		recordContent: {
	   	         validators: {
	   	        	 container: 'popover',
	   	             notEmpty: {
	   	                 message: '不能为空'
	   	             }
	   	         }
	   	     }
	   	 }
	   	 }).on('success.form.bv',function(e){
	   		 e.preventDefault();
	   			
	   	     // Get the form instance
	   	     var $form = $(e.target);

	   	     // Get the BootstrapValidator instance
	   	     var bv = $form.data('bootstrapValidator');
	   	     addrecord();
	   		}); 
	   });
   </script>
   
</body>
</html>