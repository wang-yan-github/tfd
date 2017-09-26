<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建账号</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>

<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
function dovalidate()
{
	$('#form1').bootstrapValidator('revalidateField', 'userPrivName');
	}
function addAccountId()
{
	var paramData=$("#form1").serialize(); 
	var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/addAccountAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data!=null)
				{
					window.location.href=contextPath+"/system/unit/userinfo/adduser.jsp?accountId="+data.accountId;
					alert("添加账号成功！");
				}else{
					alert("添加账号失败！");
				}
		}
	});
	}
	function checkAccountId(){
		var status;
		var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/checkAccountIdAct.act";
		$.ajax({
			type:'POST',
			url:requestUrl,
			dataType:"json",
			data:{accountId:$("#accountId").val()},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data!=0){
					status=false;
				}else{
					status=true;
				}
			}
		});
		return status;
	}
</script>
</head>
<body style="margin-top: 10px;">
<div align="center">
<form id="form1" name="form1"  class="form-horizontal"  style="width: 80%;">
<div class="widget-header bg-blueberry">
	<span class="widget-caption">添加账号</span>
</div>
<div class="list-group-item"  style="padding: 0px;">
   <table class="table table-striped table-condensed" >
		<tr>
		<td width="150px;">账号:</td>
		<td><div class="col-xs-6 form-group"><input type="text" name="accountId" id ="accountId" class="form-control"/></div></td>
		</tr>
		<tr>
		<td>初始密码:</td>
		<td><div class="col-xs-6"><input type="password" name="passWord" id="passWord" class="form-control"/></div></td>
		</tr>
		<tr>
		<td>密码类型:</td>
		<td><div class="col-xs-6"><select name="passwordType" id="paswordType" class="form-control"/>
		<option value="1">正常密码验证</option>
		<option value="2">动态密码验证</option>
		</select></div>
		</td>
		</tr>
		<tr>
		<td>系统主题:</td>
		<td><div class="col-xs-6"><select name="thmem" id="thmem" class="form-control">
		<option value="1" selected="selected" >默认主题</option>
		<option value="2">主题1</option>
		</select></div>
		</td>
		</tr>
		<tr>
		<td>权限组别:</td>
		<td><div class="col-xs-6 form-group"><input type="hidden" name="userPriv" id="userPriv"/>
		<input type="text" name="userPrivName" id="userPrivName" class="form-control" readonly/>
		</div><a href="javascript:void(0);" onclick="privinit(['userPriv','userPrivName'],'true','dovalidate');" style="line-height: 30px;">选择</a></td>
		</tr>
		<tr>
		<tr>
		<td>是否允许登陆:</td>
		<td>
		<div class="col-xs-6">
		<select id="notLogin" name="notLogin" class="form-control"/>
		<option value="1">禁止登陆</option>
		<option value="0">允许登陆</option>
		</select></div>
		</td>
		</tr>
		<tr>
		<tr>
		<td>别名:</td>
		<td><div class="col-xs-6"><input type="text" name="byName" id="byName" class="form-control"/></div></td>
		</tr>
		<tr>
		<td>语言:</td>
		<td>
		<div class="col-xs-6"><select id="language" name="language" class="form-control"/>
		<option value="1">简体中文</option>
		<option value="2">美式英文</option>
		</select></div>
		</td>
		</tr>
		<tr>
	</table>
</div>
<br>
<div align="center"><button type="submit" class="btn btn-primary"  >确 认</button></div>
</form>
</div>
   <div id="modaldialog"></div>
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
			accountId: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '账号不能为空！'
                    },
                    callback: {
                        message: '该用户已存在，请改名',
                        callback: function(value, validator) {
                        	return checkAccountId();
                        }
                    }
                }
            },
			userPrivName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '权限组别不能为空！'
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
		addAccountId();
		}); 
});
</script>
</body>
</html>