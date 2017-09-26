<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<title>界面设置</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
html{
overflow: hidden;
}
.floder{width:50%;height:300px;position:absolute;top:50px;left:25%;}
</style>
</head>
<body>
<div class="floder" >
	<form id="form1" name="form1" class="form-horizontal" >
		<input type="hidden" id="interfaceId" />
		<div class="list-group" style="margin-bottom: 0px;">
		   <a class="list-group-item active">
		      <h5 class="list-group-item-heading">
		         界面设置
		      </h5>
		   </a>
		   <div class="panel-body" style="border:none;box-shadow:none;" >
		   <table class="table table-striped"  >
		   <tr>
		   <td width="110px" >主界面顶部文字:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" id="topTitle" name="topTitle" class="form-control" /></div></td>
		   </tr>
		   <tr>
		   <td>主界面顶部图片:</td>
		   <td><div class="col-xs-12 form-group" ><input type="hidden" id="topImg" /><input type="text" readonly="readonly" id="topImgName" name="topImgName" data-toggle="modal" data-target="#myModal" class="form-control" /></div></td>
		   </tr>
		   <tr>
		   <td>主界面底部文字:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" class="form-control"  id="bottomTitle" name="bottomTitle" /></div></td>
		   </tr>
		   <tr>
		   <td>浏览器窗口标题:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" id="browserTitle" name="browserTitle" class="form-control" /></div></td>
		   </tr>
		</table>
		</div>
		</div>
		<div align="center" >
			<input type="submit"  value="保存" class="btn btn-primary" id="btn_save" />
		</div>
	</form>
</div>


 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
	 <div class="modal-dialog"> <div class="modal-content"> 
		 <div class="modal-header"> 
			 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
			 <h4 class="modal-title" id="myModalLabel">上传图片</h4> 
		 </div>
			 <div class="modal-body">
					 <table>
					 	<tr>
					 	<td width="100px;">选择图片：</td>
					 	<td><input type="file" id="headfile" name="headfile"/></td>
					 	</tr>
					 </table>
					 </br>
					  <div class="modal-footer"> 
				 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
				 <button type="button" class="btn btn-primary" onclick="uphead('sysinfo','headfile');">上传</button> 
			 </div>
			 </div> 
		 </div> 
	 </div> 
 </div>
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
		topTitle: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '不能为空'
                }
            }
        },
        browserTitle: {
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
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     updateInter();
});
	$(function(){
		doinit();
	})
	function doinit(){
		var requestUrl = contextPath + "/tfd/system/interfaces/act/InterfaceAct/getInterface.act";
     	$.ajax({
   			url:requestUrl,
   			dataType:"json",
   			async:false,
   			error:function(e){
   				alert(e.message);
   			},
   			success:function(data){
   				$("#interfaceId").val(data.interfaceId);
   				$("#topTitle").val(data.topTitle);
   				$("#topImg").val(data.topImg);
   				$("#topImgName").val(data.topImg.substr(18,data.topImg.length));
   				$("#bottomTitle").val(data.bottomTitle);
   				$("#browserTitle").val(data.browserTitle);
   			}
     	});
	}
	function updateInter(){
		var requestUrl = contextPath + "/tfd/system/interfaces/act/InterfaceAct/updateInterface.act";
     	$.ajax({
     			url:requestUrl,
     			data:{interfaceId:$("#interfaceId").val(),topTitle:$("#topTitle").val(),topImg:$("#topImg").val(),bottomTitle:$("#bottomTitle").val(),browserTitle:$("#browserTitle").val()},
     			dataType:"json",
     			async:false,
     			error:function(e){
     				alert(e.message);
     			},
     			success:function(data){
     				if(data=='1'){
     					top.layer.msg("保存成功");
     				}
     			}
     	});
	}
	
	function uphead(module,fileId)
	{
		
		upmoduleimg(module,fileId,function(rv){
			var json=rv;
			if(json.msg=="1")
				{
					alert(json.conent);
				}else if(json.msg=="2")
				{
					var imgName = json.imgname.substr(18,json.imgname.length);
					$("#topImg").val(json.imgname);
					$("#topImgName").val(imgName);
					$('#myModal').modal('hide')
				}
		});
	}
</script>
</html>