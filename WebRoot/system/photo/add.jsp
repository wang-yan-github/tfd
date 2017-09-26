<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加相册</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript">
function addphoto()
{
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/addPhotoAct.act";
	$.ajax({
		url:requestUrl,
		data:{
			sortId:$("#sortId").val(),
			photoName:$("#photoName").val(),
			photoPath:$("#photoPath").val()
		},
		dataType:"text",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
		if(data=="1")
			{
			top.layer.msg("保存成功");
			}
		}
	});
	}
	$(function(){
		$('#btn_back').click(function(){
			history.go(-1);
			return false;
		})
	})
</script>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal"  >
<div align="center" style="margin-top:30px;" >
<div class="list-group" style="margin-bottom: 0px;width:80%;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         添加相册
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
<table class="table table-striped ">
<tr>
<td>序号：</td>
<td><div class="col-xs-8 form-group"><input type="text" id="sortId" name="sortId"  class="form-control"/></div></td>
</tr>
<tr>
<td>相册名称：</td>
<td><div class="col-xs-8 form-group"><input type="text" id="photoName" name="photoName" class="form-control"/></div></td>
</tr>
<tr>
<td>硬盘路径：</td>
<td><div class="col-xs-8 form-group"><input type="text" id="photoPath" name="photoPath" class="form-control"/></div></td>
</tr>
</table>
</div>
</div>
</div>
<div align="center">
<input type="submit" class="btn btn-primary"   value="确定" >
<button type="button" class="btn btn-default"  id="btn_back"  >取消</button>
</div>
</form>
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
	     addphoto();
	}); 
})
</script>
</body>
</html>