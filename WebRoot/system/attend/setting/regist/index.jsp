<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部邮件</title>
<style>
html,body{height: 100%;margin:0px;padding:0px;}
html{overflow: hidden;}
.floder{width:40%;height:300px;position:absolute;top:50px;left:30%;}
.beLeft{line-height:30px;float:left;}
</style>
<script>
	$(function(){
		doinit();
		$("#btn_back").click(function(){
			history.go(-1);
			return false;
		});
	});
	function doinit(){
		var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/getAttendRegistById.act';
		$.ajax({
				url:requestUrl,
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					$("#attendRegistId").val(data.attendRegistId);
					$("#beforeWork").val(data.beforeWork);
					$("#afterWork").val(data.afterWork);
					$("#beforeBack").val(data.beforeBack);
					$("#afterBack").val(data.afterBack);
				}
		});
	}
	function editRegist(){
		var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/editAttendRegistById.act';
		$.ajax({
				url:requestUrl,
				data:{
					attendRegistId:$("#attendRegistId").val(),
					beforeWork:$("#beforeWork").val(),
					afterWork:$("#afterWork").val(),
					beforeBack:$("#beforeBack").val(),
					afterBack:$("#afterBack").val()
				},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data=='1'){
						top.layer.msg("保存成功");
						doinit();
					}
				}
		});
	}
</script>
<body>
<div class="floder" >
<form id="form1" name="form1" class="form-horizontal" >
<input type="hidden" id="attendRegistId" />
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
        设置上下班登记时间段
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
   <td><span class="beLeft" >提前</span><div class="col-xs-8 form-group" ><input type="text" id="beforeWork" name="beforeWork" class="form-control" /></div><span class="beLeft" >分钟允许上班登记</span></td>
   </tr>
   <tr>
   <td><span class="beLeft" >延后</span><div class="col-xs-8 form-group" ><input type="text" id="afterWork" name="afterWork" class="form-control" /></div><span class="beLeft" >分钟允许上班登记</span></td>
   </tr>
   <tr>
   <td><span class="beLeft" >提前</span><div class="col-xs-8 form-group" ><input type="text" id="beforeBack" name="beforeBack" class="form-control" ></div><span class="beLeft" >分钟允许下班登记</span></td>
   </tr>
   <tr>
   <td><span class="beLeft" >延后</span><div class="col-xs-8 form-group" ><input type="text" id="afterBack" name="afterBack" class="form-control" /></div><span class="beLeft" >分钟允许下班登记</span></td>
   </tr>
</table>
</div>
</div>
<div align="center" >
	<input type="submit"  value="保存" class="btn btn-primary" id="btn_save" />
	<input type="button"  value="返回" class="btn btn-info" id="btn_back" />
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
		beforeWork: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '不能为空'
                },
                integer: {
        			message: '只能为整数'
        		}
            }
        },
        afterWork: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '不能为空'
                },
                integer: {
        			message: '只能为整数'
        		}
            }
        },
        beforeBack: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '不能为空'
                },
        		integer: {
        			message: '只能为整数'
        		}
            }
        },
        afterBack: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '不能为空'
                },
                integer: {
        			message: '只能为整数'
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
     editRegist();
}); 
</script>
</html>