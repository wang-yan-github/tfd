<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<title>内部邮件</title>
<style>
html,body{height: 100%;margin:0px;padding:0px;}
html{overflow: hidden;}
.floder{width:60%;height:300px;margin-top:30px;margin-left:20%;}
</style>
<script>
	$(function(){
		doinit();
	});
	function doinit(){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailConfigById.act';
		$.ajax({
				url:requestUrl,
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					$("#configId").val(data.configId);
					$("#emailServer").val(data.emailServer);
					$("#serverPort").val(data.serverPort);
					$("#emailUser").val(data.emailUser);
					$("#emailPwd").val(data.emailPwd);
				}
		});
	}
	function editConfig(){
		var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/updateEmailConfig.act';
		$.ajax({
				url:requestUrl,
				data:{
					configId:$("#configId").val(),
					emailServer:$("#emailServer").val(),
					serverPort:$("#serverPort").val(),
					emailUser:$("#emailUser").val(),
					emailPwd:$("#emailPwd").val()
				},
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data=='1'){
						alert("修改成功!");
						doinit();
					}
				}
		});
	}
</script>
<body>
<table class="MessageBox" style="margin-top:20px;" align="center" width="500" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
      <div class="msg-content">设置企业邮件需到该邮件的设置开启POP3/SMTP服务</div>
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
<div class="floder" >
<form id="form1" name="form1" class="form-horizontal" >
<input type="hidden" id="configId" />
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         企业邮箱设置
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
   <td width="15%" >服务器地址</td>
   <td><div class="col-xs-8 form-group" ><input type="text" id="emailServer" name="emailServer" class="form-control" /></div></td>
   </tr>
   <tr>
   <td>服务器端口</td>
   <td><div class="col-xs-8 form-group" ><input type="text" id="serverPort" name="serverPort" class="form-control" /></div></td>
   </tr>
   <tr>
   <td>登陆账户</td>
   <td><div class="col-xs-8 form-group" ><div class="input-group">
<span class="input-group-addon">@</span>
         <input type="text" class="form-control" placeholder="xxxx@163.com" id="emailUser" name="emailPwd">
         </div></div></td>
   </tr>
   <tr>
   <td>登陆密码</td>
   <td><div class="col-xs-8 form-group" ><input type="password" id="emailPwd" name="emailPwd" class="form-control" /></div></td>
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
		emailPwd: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '账户密码不能为空'
                }
            }
        },
        emailUser: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '登陆账户不能为空'
                },
        		emailAddress:{
        			message: '邮箱格式不正确'
        		}
            }
        },
        serverPort: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '服务器端口不能为空'
                },
        		integer: {
        			message: '服务器端口只能为整数'
        		}
            }
        },
        emailServer: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '服务器地址不能为空'
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
     editConfig();
}); 
</script>
</html>