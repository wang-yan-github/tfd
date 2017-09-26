<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String diskId = request.getParameter("diskId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建公共资源</title>
<style type="text/css">
	.add_content{height:80%;width:60%;position:absolute;top:50px;left:20%;}
</style>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal" >
	<div class="add_content" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         编辑共享资源
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
<td width="15%">排序号:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="diskNo" name="diskNo" /></div><input type="hidden" id="diskId" /></td>
</tr>
<tr>
<td width="15%">共享目录名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="diskName" name="diskName"  /></div></td>
</tr>
<tr>
<td width="15%">共享目录路径:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="diskPath" name="diskPath" /></div></td>
</tr>
<tr>
<td width="15%">最大容量:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="spaceLimit" name="spaceLimit" /></div></td>
</tr>
<tr>
<td width="15%">默认排序:</td>
<td><div class="col-xs-8 form-group" >
	<select class="form-control" style="width:50%;float:left;" id="orderBy" >
		<option value="名称" >名称</option>
		<option value="大小" >大小</option>
		<option value="类型" >类型</option>
		<option value="最后修改时间" >最后修改时间</option>
	</select>
	<select class="form-control " style="width:50%;float:left;" id="ascDesc" >
		<option value="升序" >升序</option>
		<option value="降序" >降序</option>
	</select></div>
</td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <input type="submit" id="btn_ok" class="btn btn-success" value="确定" >
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
</div>
</form>
</body>
<script type="text/javascript">
$('#form1').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		diskNo: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '排序号不能为空'
                }
            }
        },
        diskName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '共享目录名称不能为空'
                }
            }
        },
        diskPath: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '共享目录路径不能为空'
                }
            }
        },
        spaceLimit: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '最大容量不能为空'
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
     editDisk();
}); 
var diskId = "<%=diskId%>";
$(function(){
	var requestUrl= '<%=contextPath%>/tfd/system/publicdisk/act/PublicDiskAct/getPublicDiskById.act';
		$.ajax({
				url:requestUrl,
				data:{diskId:encodeURIComponent(diskId)},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					$('#diskId').val(data.diskId);
					$('#diskNo').val(data.diskNo);
					$('#diskName').val(data.diskName);
					$('#diskPath').val(data.diskPath);
					$('#spaceLimit').val(data.spaceLimit);
					$('#orderBy').val(data.orderBy);
					$('#ascDesc').val(data.ascDesc);
				}
		});
	$('#btn_back').click(function(){
		history.go(-1);
		return false;
	})
});
function editDisk(){
	var requestUrl= '<%=contextPath%>/tfd/system/publicdisk/act/PublicDiskAct/updatePublicDisk.act';
	$.ajax({
			url:requestUrl,
			data:{
				diskId:$('#diskId').val(),
				diskNo:$('#diskNo').val(),
				diskName:$('#diskName').val(),
				diskPath:$('#diskPath').val(),
				spaceLimit:$('#spaceLimit').val(),
				orderBy:$('#orderBy').val(),
				ascDesc:$('#ascDesc').val()
			},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					top.layer.msg('修改成功');
				}
			}
	});
}
</script>
</html>