<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
</head>
<style>
html,body{
margin:0px;
padding:0px;
}
#main{width:90%;margin-left:5%;margin-top:20px;}
#msg{color:red;font-size:12px;float:left;margin-left:8px;line-height:30px;}
</style>
<body>
<form id="form1" name="form1" class="form-horizontal" >
	<div id="main">
		<div class="userinfo" >
			<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         修改密码
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="15%">原密码:</td>
<td ><div class="col-xs-8 form-group" ><input class="form-control "  type="password" id="oldpwd" name="oldpwd"  ></div></td>
</tr>
<tr>
<td width="15%">新密码:</td>
<td ><div class="col-xs-8 form-group" ><input class="form-control "  type="password" id="newpwd" name="newpwd" ></div></td>
</tr>
<tr>
<td width="15%">确定密码:</td>
<td ><div class="col-xs-8 form-group" ><input class="form-control " style="float:left;" type="password" name="checkpwd" id="checkpwd"></div><label id="msg" style="display:none;" >两次密码不一致</label></td>
</tr>
<tr>
<td colspan="2" align="center" >
	<input type="submit"  style="margin-top:15px;margin-right:10px;" id="btn_ok" class="btn btn-primary" value="确认修改" >
	<button type="button"  style="margin-top:15px;"  class="btn btn-default btn_back">返回</button>
</td>
</tr>
</table>
</div>
</div>
</div>
</div>
</form>
</body>
<script type="text/javascript">
$('#form1').bootstrapValidator({
	container: 'tooltip',
	message: '这不是一个有效的值',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		oldpwd: {
            validators: {
                notEmpty: {
                    message: '原密码不能为空！'
                }
            }
        },
        newpwd: {
           validators: {
        	   notEmpty: {
                   message: '新密码不能为空'
               }
           }
        },
        checkpwd: {
            validators: {
         	   notEmpty: {
                    message: '确认密码不能为空'
                },
                callback: {
                    message: '两次密码不一样',
                    callback: function(value, validator) {
                    	if(value!=$("#newpwd").val()){
                    		return false;
                    	}else{
                    		return true;
                    	}
                    }
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
     updateInfo();
});
	var flag;
	var flagSys;
	var flagSys1;
	/* $('#btn_ok').click(function(){
		if($('#oldpwd').val()==""){
			alert("原密码不能为空");
			return false;
		}else if($('#newpwd').val()==""){
			alert("新密码不能为空");
			return false;
		}else if($('#checkpwd').val()==""){
			alert("确认密码不能为空");
			return false;
		}
		checkpwd();
		checkConfig($('#newpwd').val());
		if(!flag){
			alert("原密码不正确");
			return false;
		}else if(document.getElementById("msg").style.display == "block"){
			alert("两次密码不一致");
			return false;
		}else if(!flagSys){
			alert("密码长度不符合");
			return false;
		}else if(flagSys1){
			alert("密码需字母和数字组合");
			return false;
		}else{
			updateInfo();
		}
	}) */
	function checkpwd(){
		var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/checkPassword.act?';
    	$.ajax({
    			url:requestUrl,
    			data:{pwd:$('#oldpwd').val()},
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				if(data != '1'){
    					flag = false;
    				}else{
    					flag = true;
    				}
    			}
    	});
	}
	function updateInfo(){
		checkpwd();
		checkConfig($('#newpwd').val());
		if(!flag){
			top.layer.msg("原密码不正确");
			return false;
		}else if(document.getElementById("msg").style.display == "block"){
			top.layer.msg("两次密码不一致");
			return false;
		}else if(!flagSys){
			top.layer.msg("密码长度不符合");
			return false;
		}else if(flagSys1){
			top.layer.msg("密码需字母和数字组合");
			return false;
		}else{
			updateInfos();
		}
	}
	
	function updateInfos(){
		var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/updatePassword.act?';
    	$.ajax({
    			url:requestUrl,
    			data:{pwd:$('#newpwd').val()},
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				if(data=='1'){
    					top.layer.msg("修改成功");
    				}
    			}
    	});
	}
	function checkConfig(pwd){
		var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoList.act";
    	$.ajax({
    			url:requestUrl,
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				var valueArr = data.pwdWidth.split("-");
					if(pwd.length>=valueArr[0]&&pwd.length<=valueArr[1]){
						flagSys =  true;
					}else{
						flagSys =  false;
					}
					if(data.isAbc=='1'){
						if(parseInt(pwd)==pwd){
							flagSys1 = true;
						}else{
							flagSys1 = false;
						}
					}
    			}
    	});
	}
</script>
</html>