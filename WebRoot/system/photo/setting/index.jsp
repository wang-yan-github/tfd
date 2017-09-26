<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>相册设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style type="text/css">
	.content-body{width:80%;margin-left:10%;margin-top:30px;}
</style>
<script type="text/javascript">
function setPriv(photoId)
{
	window.location = contextPath+"/system/photo/priv/index.jsp?photoId="+photoId;
}
	
function del(photoId)
{
	if(confirm("确定删除？")){
		var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/delPhotoAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{photoId:photoId},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					top.layer.msg("删除成功！");
					doinit();
				}
			}
		});
	}
}

function edit(photoId)
{
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoByIdAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{photoId:photoId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#photoId").val(data.photoId);
			$("#sortId").val(data.sortId);
			$("#photoName").val(data.photoName);
			$("#photoPath").val(data.photoPath);
			$("#btn_edit").trigger("click");
		}
	});
}
function editPhoto(){
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/updatePhotoAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{
			photoId:$("#photoId").val(),
			sortId:$("#sortId").val(),
			photoName:$("#photoName").val(),
			photoPath:$("#photoPath").val()
			},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='1'){
				top.layer.msg("修改成功!");
				$("#btn_close").trigger("click");
				doinit();
			}
		}
	});
}
function addphoto()
{
	window.location=contextPath+"/system/photo/add.jsp";
}

function doinit()
{
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getAllPhotoAct.act";
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
				html+="<td>"+data[i].photoName+"</td>";
				html+="<td>"+data[i].photoPath+"</td>";
				html+="<td><a onclick=\"javascript:showPersonal('"+data[i].createAccountId+"')\" href=\"javascript:void(0)\" >"+data[i].createUserName+"</a></td>";
				html+="<td>"+data[i].createDate+"</td>";
				html+="<td><a href=\"javascript:void(0)\" onclick=\"setPriv('"+data[i].photoId+"');\">设置权限</a></td>";
				html+="<td><a href=\"javascript:void(0)\" onclick=\"edit('"+data[i].photoId+"');\">编辑</a>    <a href=\"javascript:void(0)\" onclick=\"del('"+data[i].photoId+"');\">删除</a></td>";
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
</script>
</head>
<body onload="doinit();">
<div style="margin-top:10px;margin-bottom:5px;width:10%;text-align:center;margin-left:45%;" align="center" ><button type="button" onclick="addphoto();"  class="btn btn-success">新建相册</button></div>
<div class="content-body" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         相册设置
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped">
<tr>
<td>序号</td>
<td>相册名称</td>
<td>相册路径</td>
<td>创建人员</td>
<td>创建日期</td>
<td>设置</td>
<td>操作</td>
</tr>
<tbody id="content" name="content"></tbody>
</table>
</div>
</div>

<input type="hidden" data-toggle="modal" data-target="#myModal_update" id="btn_edit">
<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
		<form id="form1" name="form1" class="form-horizontal" >
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑相册
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
           <tr>
<td width="15%">序号:</td>
<td><div class="col-xs-8 form-group"><input class="form-control " type="text" id="sortId" name="sortId"  ><input class="form-control " type="hidden" id="photoId"  ></div></td>
</tr>
<tr>
<td>相册名称:</td>
<td><div class="col-xs-8 form-group"><input class="form-control "  type="text" id="photoName" name="photoName"  ></div>	</td>
</tr>
<tr>
<td>硬盘路径:</td>
<td><div class="col-xs-8 form-group"><input class="form-control "  type="text" id="photoPath" name="photoPath"  ></div>	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <input type="submit" id="btn_update"  class="btn btn-primary" value="确定" />
            <button type="button" id="btn_close" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
         </form>
      </div>
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
         <div class="msg-content">暂无相册</div>
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