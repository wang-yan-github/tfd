<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排班类型</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style type="text/css">
	.content-body{width:90%;margin-left:5%;margin-top:30px;}
	td{text-align:center;}
</style>
<script type="text/javascript">	
$(function(){
	doinit();
})
function del(attendConfigId)
{
	if(confirm("确定删除？")){
		var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/delAttendConfigById.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{attendConfigId:attendConfigId},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					alert("删除成功！");
					doinit();
				}
			}
		});
	}
}
function editAttendConfig(attendConfigId){
	window.location=contextPath+"/system/attend/setting/config/edit.jsp?attendConfigId="+attendConfigId;
}
function addAttendConfig()
{
	window.location=contextPath+"/system/attend/setting/config/add.jsp";
}

function doinit()
{
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendConfigList.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			for(var i=0;data.length>i;i++){
				html+="<tr>";
				html+="<td>"+(i+1)+"</td>";
				html+="<td>"+data[i].configName+"</td>";
				html+="<td>"+data[i].time1+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType1+"</td>";
				html+="<td>"+data[i].time2+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType2+"</td>";
				html+="<td>"+data[i].time3+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType3+"</td>";
				html+="<td>"+data[i].time4+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType4+"</td>";
				html+="<td>"+data[i].time5+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType5+"</td>";
				html+="<td>"+data[i].time6+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].timeType6+"</td>";
				html+="<td><a href=\"javascript:void(0)\" onclick=\"editPublicDay('"+data[i].attendConfigId+"');\">公休日</a>    <a href=\"javascript:void(0)\" onclick=\"editAttendConfig('"+data[i].attendConfigId+"');\">编辑</a>    <a href=\"javascript:void(0)\" onclick=\"del('"+data[i].attendConfigId+"');\">删除</a></td>";
			}
			$("#content").html(html);
			if(html == ""){
				$(".content-body").hide();
				$(".MessageBox").show();
			}else{
				$(".content-body").show();
				$(".MessageBox").hide();
			}
			
		}
	});
}
function editPublicDay(attendConfigId){
	window.location=contextPath+"/system/attend/setting/config/setDay.jsp?attendConfigId="+attendConfigId;
}
function ToIndex(){
	window.location = contextPath + "/system/attend/setting/index.jsp";
}
</script>
</head>
<body>
<div style="margin-top:10px;margin-bottom:5px;width:10%;text-align:center;margin-left:45%;" align="center" ><button type="button" onclick="addAttendConfig();"  class="btn btn-success">新建排版类型</button></div>
<div class="content-body" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         排版类型管理
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped">
<tr>
<td>序号</td>
<td>排版类型说明</td>
<td>第一次登记</td>
<td>第二次登记</td>
<td>第三次登记</td>
<td>第四次登记</td>
<td>第五次登记</td>
<td>第六次登记</td>
<td>操作</td>
</tr>
<tbody id="content" name="content"></tbody>
</table>
</div>
<div align="center" >
	<button type="button" class="btn btn-default" onclick="javascript:ToIndex();"  id="btn-back"  >返回</button>
</div>
</div>

</div>
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
         <div class="msg-content">暂无排版类型</div>
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
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			sortId: {
                validators: {
                	container: 'popover',
                    integer: {
                    message: '序号必须是一个整数！'
                        },
					notEmpty: {
            		message: '序号不能为空!'
                }
                }
            },
            photoName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                    message: '相册名称不能为空！'
                    }
                }
            },
            photoPath: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                    message: '相册路径不能为空！'
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
	     editPhoto();
	}); 
})
</script>
</html>