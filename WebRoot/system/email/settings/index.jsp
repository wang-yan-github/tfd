<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/setuser/setuser.css"></link>
<title>内部邮件</title>
<style>
html,body{height: 100%;margin:0px;padding:0px;}
html{overflow: hidden;}
.floder{width:90%;height:90%;position:absolute;top:5%;left:5%;}
</style>
<script>
	$(function(){
		doinit();
	})
	function doinit(){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailBoxList.act';
		$.ajax({
				url:requestUrl,
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					var trHtml = "<tr><td align='center' >文件夹名称</td><td align='center' >操作</td></tr>";
					$.each(data,function(index,data){
						trHtml += "<tr><td align='center' >"+data.boxName+"</td><td align='center' ><a href='javascript:void(0)' onclick=\"javascript:editBox('"+data.boxId+"')\" >编辑</a><a href='javascript:void(0)' style=\"margin-left:10px;\" onclick=\"javascript:delBox('"+data.boxId+"')\" >删除</a></td></tr>"
					})
					$("#tabltHtml").html(trHtml);
				}
		});
	}
	
	function updateBox(){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/updateEmailBox.act';
		$.ajax({
				url:requestUrl,
				data:{boxId:$("#boxId").val(),boxName:$("#boxNames").val(),sortId:$("#sortIds").val()},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data=='1'){
						top.layer.msg("修改成功");
						parent["left"].location=contextPath+ "/system/email/left.jsp";
						$(".btn_close").trigger("click");
						doinit();
					}
				}
		});
	}
	function editBox(boxId){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailBoxById.act';
		$.ajax({
				url:requestUrl,
				data:{boxId:boxId},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					$("#sortIds").val(data.sortId);
					$("#boxId").val(data.boxId);
					$("#boxNames").val(data.boxName);
					$("#update_box").trigger("click");
				}
		});
		
	}
	function delBox(boxId){
		if(confirm("确认删除?")){
			var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/deleteEmailBox.act';
			$.ajax({
					url:requestUrl,
					data:{boxId:boxId},
					dataType:"json",
					async:false,
					error:function(e){
						alert(e.message);
					},
					success:function(data){
						if(data == '0'){
							top.layer.msg("请先删除该文件夹下的邮箱",function(){});
							doinit();
						}
						if(data=='1'){
							top.layer.msg("删除成功");
							parent["left"].location=contextPath+ "/system/email/left.jsp";
							doinit();
						}
					}
			});
		}
	}
	function addBox(){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/addEmailBox.act';
		$.ajax({
				url:requestUrl,
				data:{boxName:$("#boxName").val(),sortId:$("#sortId").val()},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data == '1'){
						top.layer.msg("添加成功");
						$(".btn_close").trigger("click");
						parent["left"].location=contextPath+ "/system/email/left.jsp";
						doinit();
						$("#sortId").val("");
						$("#boxName").val("");
					}
				}
		});
	}
</script>
<body>
<div class="floder" >
   <div class="widget">
<div class="widget-header bordered-bottom bordered-themeprimary">
<i class="widget-icon fa fa-tasks themeprimary"></i>
<span class="widget-caption themeprimary">高级设置
<span style=""><button type="button" style="position:absolute;right:1%;top:0.5%;" id="add_folder" data-toggle="modal" data-target="#myModal_add"  class="btn btn-success btn-sm">添加文件夹</button></span>
</span>
</div>
   <div class="panel-body" style="border:none;" >
   <table class="table table-striped" id="tabltHtml"  >
</table>
</div>
</div>
</div>
<div class="modal fade" id="myModal_add" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <form id="form1" name="form1" class="form-horizontal" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建文件夹
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
         <tr>
<td>排序号:</td>
<td><div class="form-group" ><input class="form-control "  type="text" id="sortId" name="sortId"  ></div></td>
</tr>
<tr>
<td>名称:</td>
<td><div class="form-group" ><input class="form-control "  type="text" id="boxName" name="boxName"  ></div></td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <input type="submit" id="btn_add" class="btn btn-primary"  value="确认" />
            <button type="button" class="btn btn-default btn_close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</form>
</div>
<input type="hidden" id="update_box" data-toggle="modal" data-target="#myModal_update" />
<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <form id="form2" name="form2" class="form-horizontal" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑文件夹
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
         <tr>
<td>排序号:</td>
<td><div class="form-group" ><input class="form-control "  type="text" id="sortIds" name="sortIds"  ></div></td>
</tr>
<tr>
<td>名称:<input type="hidden" id="boxId" /></td>
<td><div class="form-group" ><input class="form-control "  type="text" id="boxNames" name="boxNames" ></input>	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
         <input type="submit" id="btn_update" class="btn btn-primary"  value="确认" />
            <button type="button" class="btn btn-default btn_close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</form>
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
		boxName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '文件夹名称不能为空'
                }
            }
        },
        sortId: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '排序号不能为空'
                },
                integer:{
                	message: '排序号只能为正整数'
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
     addBox();
}); 
$('#form2').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		boxNames: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '文件夹名称不能为空'
                }
            }
        },
	    sortIds: {
	        validators: {
	        	container: 'popover',
	            notEmpty: {
	                message: '排序号不能为空'
	            },
	            integer:{
	            	message: '排序号只能为正整数'
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
     updateBox();
}); 
</script>
</html>