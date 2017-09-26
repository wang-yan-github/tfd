<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>行政级别类型添加</title>
<script type="text/javascript">
function save()
{
	var paramData=$("#form1").serialize(); 
	var requestUrl=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/addUserLeaveAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=="1")
				{
				layer.msg("添加成功！");
					location.href=contextPath+"/system/unit/leadleave/add.jsp";
				}else{
					layer.msg("添加失败！");
				}
		}
	});
	}
	$(function(){
		var url=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/getUserLeaveSelectAct.act";
		$.ajax({
			type:'POST',
			url:url,
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				for(var i=0;i<data.length;i++){
					$("#midding").append("<option value=\""+data[i].id+"\">"+data[i].text+"</option>");
				}
			}
		});
	} );
</script>
</head>
<body>
<div align="center">
  <form id="form1" name="form1"  class="form-horizontal">
  <div class="list-group"  style="width:60%;">
  		<a class="list-group-item active">行政级别设置</a>
	  <div  class="list-group-item" style="height: 55px;">
	  			<div style="float: left;width: 120px;padding-top:8px;">行政编号:</div>
	  			<div class="form-group col-xs-4" ><input type="text" name="leaveNoId" id="leaveNoId"  class="form-control"/></div><a style="color: red;">*请用数值,数值越小,级别越低</a>
	 </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">行政级别名称:</div>
	 	 	<div class="form-group col-xs-4" ><input type="text" name="leaveName" id="leaveName" class="form-control"/></div>
	  </div>
	  <div class="list-group-item" style="height: 55px;">
	  		<div style="float: left;width: 120px;padding-top:8px;">上级行政级别:</div>
	  		<div class="form-group col-xs-4"><select class="form-control"  style="height: 34px;width: 100%;" name="midding" id="midding">
				<option value="">请选择</option>
				</select>
			</div>
	  </div>
</div>
<div align="center"><button type="submit" class="btn btn-primary" disabled="disabled" >保存</button>
</div>
</form>
  </div>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		container: 'tooltip',
		message: '这不是一个有效的值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			leaveNoId: {
                validators: {
                	numeric: {
                        message: '请填写正确的数字！'
                    },
			        notEmpty: {
			            message: '请填写正确的数字！'
			        }
                }
            },
            leaveName: {
                validators: {
                    notEmpty: {
                        message: '行政级别名称不能为空！'
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
	     save();
		}); 
});
</script>
</body>

</html>