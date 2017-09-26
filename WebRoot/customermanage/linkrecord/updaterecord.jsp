<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>修改联系记录</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/customer/customer.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<%String recordId=request.getParameter("recordId"); %>
<script>
var recordId="<%=recordId%>";
$(function (){
	getMessagePriv("calendar");
	getRecord();
	$("#recordId").val(recordId);
});
function getRecord(){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/getrecordIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{recordId:recordId},
		async:false,
		error:function(e){
		},
		success:function(data){
			getIdName(data.customerId);
			var fromdata=data;
			$("#time").val(data.recordWarn);
			$("#staff").val(data.linkmanId);
			for(var name in fromdata){
				$("#"+name).val(fromdata[name]);
			}
	}
	});
}
function getIdName(customerId){
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
function updaterecord(){
	var url=contextPath+"/customermanage/act/CustomerrecordAct/updaterecordAct.act";
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
					history.back();	
			}
	}
	});
}
function getcontent(){
	var te=$("#linkmanId option:selected").text();
	$("#WarnContent").val("请尽快与客户"+te+"联系");
}
</script>
</head>
<body>
<form id="recordfrom" name="recordfrom" class="form-horizontal">
<div class="panel panel-info" style="width: 80%; margin-left: 10%;margin-top: 1%;">
  <div class="widget-header bg-blueberry">
	<span class="widget-caption">修改记录信息</span>
</div>
   <table class="table table-striped table-condensed">
   <tr>
   <td width="15%">
   联系人：
   </td>
   <td>
   <input id="recordId" name="recordId" type="hidden"></input>
   <input id="customerId" name="customerId" type="hidden"></input>
   <input id="time" name="time" type="hidden"></input>
   <input id="staff" name="staff" type="hidden"></input>
   <div class="col-xs-4 form-group" >
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
   <div class="col-xs-4 form-group" >
   <select id="recordlinkType" name="recordlinkType" class="form-control">
   <option value="">请选择</option>
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
   <div class="col-xs-4 form-group" >
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
   <textarea  id="recordContent" name="recordContent" rows="5" class="form-control" ></textarea>
	</div>
   </td>
   </tr>
   </table>
   </div>
   <div align="center">
 	<button type="submit" name="send" class="btn btn-primary" >保存</button>
	<button type="button" onclick="history.back();"  class="btn btn-default">返回</button>
 		</div>
 		<div id="smsdiv" name="smsdiv" style="display: none;"></div>
   </form>
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
	   		linkmanId: {
	   	         validators: {
	   	        	 container: 'popover',
	   	             notEmpty: {
	   	                 message: '不能为空'
	   	             }
	   	         }
	   	     },recordlinkType:{
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
	   	  	 updaterecord();
	   		}); 
	   });
   </script>
</body>
</html>